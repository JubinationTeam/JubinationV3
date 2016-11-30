/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.service.freshcall.parallel.worker;

import com.jubination.backend.service.email.sendgrid.EmailService;
import com.jubination.backend.service.customcall.CallBox;
import com.jubination.backend.service.freshcall.parallel.master.CallManager;
import com.jubination.model.pojo.admin.AdminSettings;
import com.jubination.model.pojo.ivr.exotel.Call;
import com.jubination.model.pojo.crm.Client;
import com.jubination.model.pojo.crm.Lead;
import com.jubination.service.AdminMaintainService;
import com.jubination.service.CallMaintainService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author MumbaiZone
 */
@Component
@Scope("prototype")
public class CallWorkerSlave3Leftover {
       @Autowired
     private  CallMaintainService service;
       @Autowired
     private  AdminMaintainService adminService;
    @Autowired
     private  CallManager manager;
    @Autowired
          private   CallBox callBox;
    
    private String settings="settings";
      void work() {
          
                            if(manager.getClientStage1().isEmpty()&&manager.getClientStage2().isEmpty()){
                                             //fetching all the call values
                                                        for(int i=0;i<manager.getStageThreeUpdates().size();i++){
                                                            try{
                                                            Call callUpdated=manager.getStageThreeUpdates().get(i);
                                                        if(callUpdated!=null){
                                                                    List<Call> callList=service.getCallBySid(callUpdated.getSid());
                                                                    if(callList!=null&&!callList.isEmpty()){
                                                                                        Call  call=callList.get(0);
                                                                                          System.out.println("Stage 3: Update service running");
                                                                                         if(call!=null){
                                                                                                             System.out.println("Stage 3:Call message present in database already and not null");
                                                                                                             call.setMessage("Stage 3 Tracking");
                                                                                                             call.setStatus("completed");
                                                                                                             call.setDialCallDuration(callUpdated.getDialCallDuration());
                                                                                                             call.setCallType(callUpdated.getCallType());
                                                                                                             call.setTrackStatus(callUpdated.getTrackStatus());
                                                                                                             call.setDialWhomNumber(callUpdated.getDialWhomNumber());
                                                                                                             call.setRecordingUrl(callUpdated.getRecordingUrl());
                                                                                                             call.setStartTime(callUpdated.getStartTime());
                                                                                                             call.setDirection(callUpdated.getDirection());
                                                                                                 
                                                                                                             System.out.println("Stage 3:Updating database properties");
                                                                                                             //saving to database
                                                                                                             List<Client> clientList=service.getClientsByPhoneNumber(call.getCallTo());
                                                                                                             if(clientList!=null&&!clientList.isEmpty()){
                                                                                                             for(Client client:clientList){
                                                                                                                client=service.getClientDetailsWithList(client);
                                                                                                                
                                                                                                                Lead lead=client.getLead().get(client.getLead().size()-1);
                                                                                                                if(call.getTrackStatus().contains("did not speak")){
                                                                                                                            if(call.getCallType().contains("client-hangup")){
                                                                                                                                lead.setLeadStatus("Hanged up while connecting");
                                                                                                                                if(client!=null){
                                                                                                                                    sendEmailToFailCall(client.getEmailId());
                                                                                                                                }
                                                                                                                            }
                                                                                                                            else if(call.getCallType().contains("incomplete")){
                                                                                                                                lead.setLeadStatus("We missed client's call");
                                                                                                                            }
                                                                                                                             else if(call.getCallType().contains("trans")){
                                                                                                                                lead.setLeadStatus("Hanged up while greetings");
                                                                                                                                        if(client!=null){
                                                                                                                                            sendEmailToFailCall(client.getEmailId());
                                                                                                                                        }
                                                                                                                                }
                                                                                                                            
                                                                                                                            else{
                                                                                                                                        lead.setLeadStatus(call.getStatus()+"|"+call.getTrackStatus()+"|"+call.getCallType());
                                                                                                                                        if(client!=null){
                                                                                                                                                sendEmailToFailCall(client.getEmailId());
                                                                                                                                            }
                                                                                                                            }
                                                                                                                    
                                                                                                                }
                                                                                                                else if(call.getTrackStatus().contains("spoke")){
                                                                                                                                lead.setFollowUpDate("");
                                                                                                                                lead.setNotification(false);
                                                                                                                                lead.setPending(false);
                                                                                                                                if(lead==null||lead.getLeadStatus()==null){
                                                                                                                                    sendEmailNotUpdated("disha@jubination.com",call.getCallFrom(),call.getDialWhomNumber());
                                                                                                                                    sendEmailNotUpdated("trupti@jubination.com",call.getCallFrom(),call.getDialWhomNumber());
                                                                                                                                    sendEmailNotUpdated("reshma@jubination.com",call.getCallFrom(),call.getDialWhomNumber());
                                                                                                                                    sendEmailNotUpdated("tauseef@jubination.com",call.getCallFrom(),call.getDialWhomNumber());
                                                                                                                                    sendEmailNotUpdated("vinay@jubination.com",call.getCallFrom(),call.getDialWhomNumber());
                                                                                                                                    sendEmailNotUpdated("souvik@jubination.com",call.getCallFrom(),call.getDialWhomNumber());
                                                                                                                                    
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
                                                                                                                    
                                                                                                                                }
                                                                                                                                else if((lead.getLeadStatus()==null||lead.getLeadStatus().isEmpty())){

                                                                                                                                       lead.setLeadStatus("Spoke but not updated");
                                                                                                                                   }
                                                                                                                                else{
                                                                                                                                  sendEmailNotUpdated("disha@jubination.com",call.getCallFrom(),call.getDialWhomNumber());
                                                                                                                                sendEmailNotUpdated("trupti@jubination.com",call.getCallFrom(),call.getDialWhomNumber());
                                                                                                                                sendEmailNotUpdated("reshma@jubination.com",call.getCallFrom(),call.getDialWhomNumber());
                                                                                                                                sendEmailNotUpdated("tauseef@jubination.com",call.getCallFrom(),call.getDialWhomNumber());
                                                                                                                                sendEmailNotUpdated("vinay@jubination.com",call.getCallFrom(),call.getDialWhomNumber());
                                                                                                                                sendEmailNotUpdated("souvik@jubination.com",call.getCallFrom(),call.getDialWhomNumber());

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
                                                                                                                }
                                                                                                                else{
                                                                                                                        lead.setLeadStatus(call.getStatus()+"|"+call.getTrackStatus()+"|"+call.getCallType());
                                                                                                                }
                                                                                                                service.updateLeadOnly(lead);
                                                                                                                
                                                                                                             call.setLead(client.getLead().get(client.getLead().size()-1));
                                                                                                             service.updateCallAPIMessage(call);
                                                                                                             }
                                                                                                             }
                                                                                                             else{
                                                                                                                 service.updateCallAPIMessage(call);
                                                                                                             }
                                                                                                             
                                                                                                            //removing all the call values
                                                                                                            
                                                                                                            manager.getStageThreeUpdates().remove(callUpdated);
                                                                                                                try {
                                                                                                                    Thread.sleep(100);
                                                                                                                } catch (InterruptedException ex) {
                                                                                                                    Logger.getLogger(CallWorker.class.getName()).log(Level.SEVERE, null, ex);
                                                                                                                }
                                                                                                            System.out.println("Stage 3:Call Message out of queue");
                                                                                         }
                                                                    }
                                                                    else{
                                                                                            System.out.println("Stage 3:Call message not present in database");
                                                                                            callUpdated.setMessage("Incoming Tracking");
                                                                                            //saving to databse
                                                                                            List<Client> clientList=service.getClientsByPhoneNumber(callUpdated.getCallFrom());
                                                                                            
                                                                                            if(clientList==null||clientList.isEmpty()){
                                                                                                System.out.println("Stage 3 : Client not present");
                                                                                                    service.buildCallAPIMessage(callUpdated);
                                                                                                    if(callUpdated.getTrackStatus().contains("requested for callback")){
                                                                                                        System.out.println("Request Callback");
                                                                                                                    callBox.getNumbers().push(callUpdated.getCallFrom());
                                                                                                                    callBox.setFlag(true);
                                                                                                 }
                                                                                            }
                                                                                            else{
                                                                                                System.out.println("Stage 3 : Client present");
                                                                                                for(Client client:clientList){
                                                                                                    client=service.getClientDetailsWithList(client);
                                                                                                    if(callUpdated.getTrackStatus().contains("spoke to us")){
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
                                                                                                    }
                                                                                                    service.addClientCall(client,client.getLead().get(client.getLead().size()-1),callUpdated);
                                                                                                    callUpdated.setLead(client.getLead().get(client.getLead().size()-1));
                                                                                                             service.updateCallAPIMessage(callUpdated);
                                                                                                }
                                                                                                 if(callUpdated.getTrackStatus().contains("requested for callback")){
                                                                                                     System.out.println("Request Callback");
                                                                                                                    manager.getClientStage1().push(clientList.get(0));
                                                                                                 }
                                                                                            }
                                                                                            
                                                                                           
                                                                                            
                                                                                            //removing all the call values
                                                                                             manager.getStageThreeUpdates().remove(callUpdated);
                                                                                             System.out.println("Stage 3:Call Message out of queue");
                                                                                            System.out.println("Stage 3:Built new CallMessage");
                                                                    }
                                                        }
                                                        else{
                                                                
                                                                                             manager.getStageThreeUpdates().remove(callUpdated);
                                                            }
                                                        if(manager.getStageThreeUpdates().isEmpty()){
                                                            service.sendEmailUpdate();
                                                        }
                                                        }
                                                        catch(Exception e){
                                                                e.printStackTrace();
                                                                }
                                                        }
                                              
                            }            
                        
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
                                                "Request you to suggest a suitable slot for a call-back or call us on 02233835916 or WhatsApp your name & email id on 9930421623 or mail us on support@jubination.com <br/>" +
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
                                                "02233835916 " ,adminSettings.getMyUsername(),adminSettings.getMyPassword(),adminSettings.getAuth(),adminSettings.getStarttls(),adminSettings.getHost(),adminSettings.getPort(),adminSettings.getSendgridApi()).start();
     }

       
}
