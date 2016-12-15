/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.service.leadcall.parallel.worker.exotel;

import com.jubination.backend.service.email.sendgrid.EmailService;
import com.jubination.backend.service.numbercall.serial.exotel.CallBox;
import com.jubination.backend.service.leadcall.parallel.master.CallManager;
import com.jubination.backend.service.update.lms.Updater;
import com.jubination.controller.UpdateAndBookingController;
import com.jubination.model.pojo.admin.AdminSettings;
import com.jubination.model.pojo.crm.Beneficiaries;
import com.jubination.model.pojo.ivr.exotel.Call;
import com.jubination.model.pojo.crm.Client;
import com.jubination.model.pojo.crm.Lead;
import com.jubination.service.AdminMaintainService;
import com.jubination.service.CallMaintainService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author MumbaiZone
 */
@Component
@Scope("prototype")
public class CallWorkerSlave3 {
       @Autowired
     private  CallMaintainService service;
    @Autowired
     private  CallManager manager;
    @Autowired
          private   CallBox callBox;
       @Autowired
     private  AdminMaintainService adminService;
        @Autowired
    Updater updater;
     private  String settings="settings";
    
    
          public boolean work(String sid){
              try{
                           //STAGE 3-----------------------UPDATE CALL STATUS----------------------------------------------------------------//    
                            //see if passthru update available
                            
                            if(!manager.getStageThreeUpdates().isEmpty()){
                            try{

                                            //get all the list of updates
                                            List<Call> callList=manager.getStageThreeUpdates();
                                            
                                                //see through the list of updates
                                                for(int i=0;i<callList.size();i++){
                                                    
                                                    Call callUpdatedFromList=callList.get(i);
                                                    
                                                    //if the sids match
                                                    if(callUpdatedFromList.getSid().equals(sid)){
                                                                
                                                                System.out.println("##########"+Thread.currentThread().getName()+" "+"Got the sid matched");
                                                                
                                                                //get the call data stored with same sid
                                                                List<Call> callDatabase=service.getCallBySid(sid);
                                                                
                                                                //Expected sids
                                                                if(callDatabase!=null&&!callDatabase.isEmpty()){
                                                                    
                                                                        for(int j=0;j<callDatabase.size();j++){
                                                                            
                                                                              updateCallStatusOfExpectedSids(callUpdatedFromList,callDatabase.get(j));
                                                                              
                                                                        }

                                                                }
                                                                //Unexpected sids
                                                                else{
                                                                                 updateCallStatusOfUnexpectedSids(callUpdatedFromList);
                                                                }

                                                                if(manager.getStageThreeUpdates().isEmpty()){
                                                                    service.sendEmailUpdate();
                                                                }

                                                                manager.getStageThreeUpdates().remove(callUpdatedFromList);
                                                                return true;
                                                    }
                                                }
                                        }
                                        catch(Exception e){
                                        e.printStackTrace();
                                                        return false; 
                                          }
                            }
                            System.out.println("############"+Thread.currentThread().getName()+" "+"Stage 3:Could not find sid.");
                           return false; 
                            
                            ////////////////////////////////////////////////////////////////////////////////////////////////////////
                               }
            catch(Exception e){
                System.out.println("Error @ outer work Slave 3");
                e.printStackTrace();
                return false; 
            }
              finally{
            
              }
                            
           }
          
          private void updateCallStatusOfUnexpectedSids(Call callUpdatedFromList) {
                        System.out.println(Thread.currentThread().getName()+" "+"Stage 3:Call message not present in database");
                            callUpdatedFromList.setMessage("Incoming Tracking");

                            //saving to databse
                            List<Client> clientList=service.getClientsByPhoneNumber(callUpdatedFromList.getCallFrom());

                            if(clientList==null||clientList.isEmpty()){
                                System.out.println(Thread.currentThread().getName()+" "+"Stage 3 : Client not present");
                                    service.buildCallAPIMessage(callUpdatedFromList);
                                    if(callUpdatedFromList.getTrackStatus().contains("requested for callback")){
                                        System.out.println(Thread.currentThread().getName()+" "+"Request Callback");
                                                    callBox.getNumbers().push(callUpdatedFromList.getCallFrom());
                                                    callBox.setFlag(true);
                                 }
                            }
                            else{
                                System.out.println(Thread.currentThread().getName()+" "+"Stage 3 : Client present");
                                   List<Call> callDatabase=service.getCallBySid(callUpdatedFromList.getSid());
                                             for(int j=0;j<callDatabase.size();j++){

                                                         Client client=service.getClientDetailsWithList(callDatabase.get(j).getLead().getClient());
                                                        if(callUpdatedFromList.getTrackStatus().contains("spoke to us")){
                                                            client.getLead().get(client.getLead().size()-1).setPending(false);
                                                            client.getLead().get(client.getLead().size()-1).setCount(0);
                                                            List<Lead> leadList=service.getDuplicateLeads(client.getPhoneNumber());
                                                            for(Lead l:leadList){
                                                                            
                                                                            l.setNotification(false);
                                                                            l.setPending(false);
                                                                            l.setCount(0);
                                                                            service.updateLeadOnly(l);
                                                            }
                                                        }
                                                        service.addClientCall(client,client.getLead().get(client.getLead().size()-1),callUpdatedFromList);
                                                        callUpdatedFromList.setLead(client.getLead().get(client.getLead().size()-1));
                                                                 service.updateCallAPIMessage(callUpdatedFromList);
                                                     if(callUpdatedFromList.getTrackStatus().contains("requested for callback")){
                                                         System.out.println(Thread.currentThread().getName()+" "+"Request Callback");
                                                                        manager.getClientStage1().push(clientList.get(0));
                                                     }
                                             }
                            }



                            //removing all the call values
                             System.out.println(Thread.currentThread().getName()+" "+"Stage 3:Call Message out of queue");
                            System.out.println(Thread.currentThread().getName()+" "+"Stage 3:Built new CallMessage");
          }
          
          private void updateCallStatusOfExpectedSids(Call callUpdatedFromList,Call callDatabase) {
              System.out.println(Thread.currentThread().getName()+" "+"Stage 3:Call message present in database already and not null");
            //change the call details
             callDatabase.setMessage("Stage 3 Tracking");
             callDatabase.setStatus("completed");
             callDatabase.setDialCallDuration(callUpdatedFromList.getDialCallDuration());
             callDatabase.setCallType(callUpdatedFromList.getCallType());
             callDatabase.setTrackStatus(callUpdatedFromList.getTrackStatus());
             callDatabase.setDialWhomNumber(callUpdatedFromList.getDialWhomNumber());
             callDatabase.setRecordingUrl(callUpdatedFromList.getRecordingUrl());
             callDatabase.setStartTime(callUpdatedFromList.getStartTime());
             callDatabase.setDirection(callUpdatedFromList.getDirection());
             //get client details from database
           
                           Client client=service.getClientDetailsWithList(callDatabase.getLead().getClient());

                           Lead lead=client.getLead().get(client.getLead().size()-1);
                           if(callDatabase.getTrackStatus().contains("did not speak")){
                                      updateNotSpoken(client,lead,callDatabase);
                                      

                           }
                           else if(callDatabase.getTrackStatus().contains("spoke")){
                               updateSpoke(client, lead, callDatabase);
                           }
                           else{
                               updateUnpredictableScenario(client, lead, callDatabase);
                           }
                        
                        
                      System.out.println(Thread.currentThread().getName()+" "+"Stage 3:Call Message out of queue");
          }
          
          
          private void updateUnpredictableScenario(Client client, Lead lead, Call call){
                System.out.println("##########"+Thread.currentThread().getName()+" "+"Stage 3 : "+lead.getLeadStatus()+"|"+call.getStatus()+"|"+call.getTrackStatus()+"|"+call.getCallType());

                                   lead.setLeadStatus(call.getStatus()+"|"+call.getTrackStatus()+"|"+call.getCallType());

                           service.updateLeadOnly(lead);

                        call.setLead(client.getLead().get(client.getLead().size()-1));
                        service.updateCallAPIMessage(call);
          }
          
          
          private void updateSpoke(Client client, Lead lead, Call call){
                                           
                                            lead.setFollowUpDate("");
                                            lead.setNotification(false);
                                            lead.setPending(false);
                                           if((lead.getLeadStatus()==null||lead.getLeadStatus().isEmpty())){
                                               sendEmailNotUpdated("disha@jubination.com",call.getCallFrom(),call.getDialWhomNumber());
                                               sendEmailNotUpdated("trupti@jubination.com",call.getCallFrom(),call.getDialWhomNumber());
                                               sendEmailNotUpdated("reshma@jubination.com",call.getCallFrom(),call.getDialWhomNumber());
                                               sendEmailNotUpdated("tauseef@jubination.com",call.getCallFrom(),call.getDialWhomNumber());
                                               sendEmailNotUpdated("vinay@jubination.com",call.getCallFrom(),call.getDialWhomNumber());
                                               sendEmailNotUpdated("souvik@jubination.com",call.getCallFrom(),call.getDialWhomNumber());
                                               

                                                   System.out.println("##########"+Thread.currentThread().getName()+" "+"Stage 3 : SPOKE BUT NOT UPDATED");
                                                  lead.setLeadStatus("Spoke but not updated");

                                           }
                                          else if(lead.getLeadStatus().contains("Follow up/Call back")||
                                                   lead.getLeadStatus().contains("Lead sent to Thyrocare")||
                                                   lead.getLeadStatus().contains("Not interested")||
                                                   lead.getLeadStatus().contains("Not registered")||
                                                   lead.getLeadStatus().contains("Language not recognizable")||
                                                   lead.getLeadStatus().contains("No Service")||
                                                   lead.getLeadStatus().contains("Customer complained")||
                                                   lead.getLeadStatus().contains("Disapproved")){
                                                  lead.setLeadStatus(lead.getLeadStatus());
                                                  System.out.println("##########"+Thread.currentThread().getName()+" "+"Stage 3: SPOKE AND UPDATED");
                                           }
                                           else{
                                               sendEmailNotUpdated("disha@jubination.com",call.getCallFrom(),call.getDialWhomNumber());
                                               sendEmailNotUpdated("trupti@jubination.com",call.getCallFrom(),call.getDialWhomNumber());
                                               sendEmailNotUpdated("reshma@jubination.com",call.getCallFrom(),call.getDialWhomNumber());
                                               sendEmailNotUpdated("tauseef@jubination.com",call.getCallFrom(),call.getDialWhomNumber());
                                               sendEmailNotUpdated("vinay@jubination.com",call.getCallFrom(),call.getDialWhomNumber());
                                               sendEmailNotUpdated("souvik@jubination.com",call.getCallFrom(),call.getDialWhomNumber());

                                                   System.out.println("##########"+Thread.currentThread().getName()+" "+"Stage 3 : SPOKE BUT NOT UPDATED");
                                                  lead.setLeadStatus("Spoke but not updated|prev-"+lead.getLeadStatus());

                                           }
                                                client.getLead().get(client.getLead().size()-1).setPending(false);
                                                client.getLead().get(client.getLead().size()-1).setNotification(false);
                                               client.getLead().get(client.getLead().size()-1).setCount(0);
                                               List<Lead> leadList=service.getDuplicateLeads(client.getPhoneNumber());
                                                    for(Lead l:leadList){
                                                                    
                                                                    l.setNotification(false);
                                                                    l.setPending(false);
                                                                    l.setCount(0);
                                                                    service.updateLeadOnly(l);
                                                    }

                                                call.setLead(client.getLead().get(client.getLead().size()-1));
                                                service.updateCallAPIMessage(call);
                                                try {
                                                    Thread.sleep(45000);
                                                } catch (InterruptedException ex) {
                                                    Logger.getLogger(CallWorkerSlave3.class.getName()).log(Level.SEVERE, null, ex);
                                                }
          }
          
          
          private void updateNotSpoken(Client client, Lead lead, Call call){
               if(call.getCallType().contains("client-hangup")){
                                           lead.setLeadStatus("Hanged up while connecting");

                                                   System.out.println("##########"+Thread.currentThread().getName()+" "+"Stage 3 : HC");
                                           if(client!=null){
                                               sendEmailToFailCall(client.getEmailId());
                                           }
                                           
                                       }
                                       else if(call.getCallType().contains("incomplete")){

                                                   System.out.println("##########"+Thread.currentThread().getName()+" "+"Stage 3 : MISSED");
                                           lead.setLeadStatus("We missed client's call");
                                       }
                                        else if(call.getCallType().contains("trans")){
                                           lead.setLeadStatus("Hanged up while greetings");

                                                   System.out.println("##########"+Thread.currentThread().getName()+" "+"Stage 3 : HG");
                                                   if(client!=null){
                                                       sendEmailToFailCall(client.getEmailId());
                                                   }
                                           }

                                       else{
                                             System.out.println("##########"+Thread.currentThread().getName()+" "+"Stage 3 : did not speak|"+lead.getLeadStatus()+"|"+call.getStatus()+"|"+call.getTrackStatus()+"|"+call.getCallType());

                                                   lead.setLeadStatus(call.getStatus()+"|"+call.getTrackStatus()+"|"+call.getCallType());
                                                   if(client!=null){
                                                           sendEmailToFailCall(client.getEmailId());
                                                       }
                                       }

                           service.updateLeadOnly(lead);
                             updater.sendAutomatedUpdate(lead.getLeadId());
                           
                        call.setLead(client.getLead().get(client.getLead().size()-1));
                        service.updateCallAPIMessage(call);
          }
          
          private void sendEmailNotUpdated(String email,String number,String exec) {
           AdminSettings adminSettings = adminService.readSettings(settings);
            new EmailService(email,"Spoke But Not Updated",
                                          "Hi,<br/>" +
                                                "<br/>"+
                                                   "<br/>"+
                                                  "Spoke to "+number+". Not updated yet."+
                                                  "<br/>"+
                                                  "Call routed to "+exec+
                                                  "<br/>"+
                                                   "<br/>"+
                                                   "Regards,"+
                                                  "<br/>"+
                                                  "Jubination"
                                        ,adminSettings.getMyUsername(),adminSettings.getMyPassword(),adminSettings.getAuth(),adminSettings.getStarttls(),adminSettings.getHost(),adminSettings.getPort(),adminSettings.getSendgridApi()).start();
    }
     private void sendEmailToFailCall(String email){
           AdminSettings adminSettings = adminService.readSettings(settings);
            new EmailService(email,"Your pending health checkup",
                                          "Hi,<br/>" +
                                                "<br/>" +
                                                "Greetings from Jubination!<br/>" +
                                                "<br/>" +
                                                "It's great to have you as a part of Jubination family!<br/>" +
                                                "<br/>" +
                                                "We received your inquiry for Thyrocare health check-up package. We have been trying to get in touch with you to fix your appointment but was unable to get through.<br/>" +
                                                "<br/>" +
                                                "Request you to suggest a suitable slot for a call-back or call us on 02239652819 or WhatsApp your name & email id on 9930421623 or mail us on support@jubination.com <br/>" +
                                                "<br/>" +
                                                "<br/>" +
                                                "Look forward to hearing from you soon. <br/>" +
                                                "<br/>" +
                                                "<br/>" +
                                                "Wish you a happy & healthy day!<br/>" +
                                                "<br/>" +
                                                "<br/>" +
                                                "Regards,<br/>" +
                                                "Trupti<br/>" +
                                                "Customer Happiness Manager<br/>" +
                                                "02239652819 " ,adminSettings.getMyUsername(),adminSettings.getMyPassword(),adminSettings.getAuth(),adminSettings.getStarttls(),adminSettings.getHost(),adminSettings.getPort(),adminSettings.getSendgridApi()).start();
     }

       

     
}
