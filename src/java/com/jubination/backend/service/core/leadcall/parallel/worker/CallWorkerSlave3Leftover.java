/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.service.core.leadcall.parallel.worker;

import com.jubination.backend.service.core.leadcall.parallel.worker.CallWorker;
import com.jubination.backend.service.sendgrid.EmailService;
import com.jubination.backend.service.exotel.numbercall.serial.CallBox;
import com.jubination.backend.service.core.leadcall.parallel.master.CallManager;
import com.jubination.backend.service.lms.Updater;
import com.jubination.controller.web.UpdateAndBookingController;
import com.jubination.model.pojo.admin.AdminSettings;
import com.jubination.model.pojo.crm.Beneficiaries;
import com.jubination.model.pojo.exotel.Call;
import com.jubination.model.pojo.crm.Client;
import com.jubination.model.pojo.crm.Lead;
import com.jubination.service.AdminMaintainService;
import com.jubination.service.CallMaintainService;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class CallWorkerSlave3Leftover {
       @Autowired
     private  CallMaintainService service;
       @Autowired
     private  AdminMaintainService adminService;
    @Autowired
     private  CallManager manager;
    @Autowired
          private   CallBox callBox;
     @Autowired
    Updater updater;
    private String settings="settings";
      public void work() {
          try{
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
                                                                                                                if(call.getTrackStatus().contains("Automated")){
                                                                                                                        updater.sendAutomatedUpdate(lead.getLeadId());
                                                                                                                    
                                                                                                                }
                                                                                                                else if(call.getTrackStatus().contains("did not speak")){
                                                                                                                            if(call.getCallType().contains("client-hangup")){
                                                                                                                                lead.setLeadStatus("Hanged up while connecting");
                                                                                                                                
                                                                                                                                    sendEmailToFailCall(client.getEmailId());
                                                                                                                                
                                                                                                                            }
                                                                                                                            else if(call.getCallType().contains("incomplete")){
                                                                                                                                lead.setLeadStatus("We missed client's call");
                                                                                                                            }
                                                                                                                             else if(call.getCallType().contains("trans")){
                                                                                                                                lead.setLeadStatus("Hanged up while greetings");
                                                                                                                                       
                                                                                                                                            sendEmailToFailCall(client.getEmailId());
                                                                                                                                        
                                                                                                                                }
                                                                                                                            
                                                                                                                            else{
                                                                                                                                        lead.setLeadStatus(call.getStatus()+"|"+call.getTrackStatus()+"|"+call.getCallType());
                                                                                                                                     
                                                                                                                                                sendEmailToFailCall(client.getEmailId());
                                                                                                                                            
                                                                                                                            }
                                                                                                                    
                                                                                                                }
                                                                                                                else if(call.getTrackStatus().contains("spoke")){
                                                                                                               //                 lead.setFollowUpDate("");
                                                                                                                  //              lead.setNotification(false);
                                                                                                                     //           lead.setPending(false);
                                                                                                                                if(lead.getLeadStatus()==null){
                                                                                                                                    
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
                                                                                                                                   lead.setLeadStatus("Spoke but not updated|prev-"+lead.getLeadStatus());
                                                                                                                                }
                                                                                                                                     lead.setPending(false);
                                                                                                                                     lead.setNotification(false);
                                                                                                                                    lead.setCount(0);
                                                                                                                                    
//                                                                                                                                    List<Lead> leadList=service.getDuplicateLeads(client.getPhoneNumber());
//                                                                                                                                    for(Lead l:leadList){
//                                                                                                                                                    
//                                                                                                                                                    l.setNotification(false);
//                                                                                                                                                     l.setPending(false);
//                                                                                                                                                    l.setCount(0);
//                                                                                                                                                    service.updateLeadOnly(l);
//                                                                                                                                    }
                                                                                                                }
                                                                                                                else{
                                                                                                                        lead.setLeadStatus(call.getStatus()+"|"+call.getTrackStatus()+"|"+call.getCallType());
                                                                                                                }
                                                                                                                service.updateLeadOnly(lead);
                                                                                                                
                                                                                                                updater.sendAutomatedUpdate(lead.getLeadId());
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
                                                                                                    if(callUpdated.getTrackStatus().contains("requested for callback")||callUpdated.getTrackStatus().contains("Customer did not speak to us")){
                                                                                                        System.out.println("Request Callback");
                                                                                                                    callBox.getNumbers().push(callUpdated.getCallFrom());
                                                                                                                    callBox.setFlag(true);
                                                                                                 }
                                                                                            }
                                                                                            else{
                                                                                                System.out.println("Stage 3 : Client present");
                                                                                                for(Client client:clientList){
                                                                                                    if(client.getLead().size()>0){
                                                                                                   Lead lead=client.getLead().get(client.getLead().size()-1);
                                                                                                   
                                                                                                    if(callUpdated.getCallTo().equals("0"+client.getPhoneNumber())||callUpdated.getCallFrom().equals("0"+client.getPhoneNumber())||
                                                                                                            callUpdated.getCallTo().equals(client.getPhoneNumber())||callUpdated.getCallFrom().equals(client.getPhoneNumber())){
                                                                                                    client=service.getClientDetailsWithList(client);
                                                                                                       if(callUpdated.getTrackStatus().contains("Automated")){
                                                                                                                        updater.sendAutomatedUpdate(lead.getLeadId());
                                                                                                                    
                                                                                                                }
                                                                                                    if(callUpdated.getTrackStatus().contains("spoke to us")){
                                                                                                        
                                                                                                        lead.setPending(false);
                                                                                                       lead.setNotification(false);
                                                                                                        lead.setCount(0);
                                                                                                        if(!(lead.getLeadStatus().contains("Follow up/Call back")||
                                                                                                            lead.getLeadStatus().contains("Lead sent to Thyrocare")||
                                                                                                            lead.getLeadStatus().contains("Rescheduled")||
                                                                                                            lead.getLeadStatus().contains("Not interested")||
                                                                                                            lead.getLeadStatus().contains("Not registered")||
                                                                                                            lead.getLeadStatus().contains("Language not recognizable")||
                                                                                                            lead.getLeadStatus().contains("No Service")||
                                                                                                            lead.getLeadStatus().contains("Customer complained")||
                                                                                                            lead.getLeadStatus().contains("Disapproved"))){
                                                                                                        lead.setLeadStatus("Spoke but not updated|prev-s3l-"+lead.getLeadStatus());
                                                                                                        }
                                                                                                                     service.updateLeadOnly(lead);
                                                                                                        
//                                                                                                                List<Lead> leadList=service.getDuplicateLeads(client.getPhoneNumber());
//                                                                                                                for(Lead l:leadList){
//                                                                                                                                l.setNotification(false);
//                                                                                                                                 l.setPending(false);
//                                                                                                                                l.setCount(0);
//                                                                                                                                service.updateLeadOnly(l);
//                                                                                                                }
                                                                                                    }
                                                                                                }
                                                                                                    service.addClientCall(client,lead,callUpdated);
                                                                                                    callUpdated.setLead(lead);
                                                                                                      service.updateCallAPIMessage(callUpdated);
                                                                                                }
                                                                                                }
                                                                                            
                                                                                                 if(callUpdated.getTrackStatus().contains("requested for callback")||callUpdated.getTrackStatus().contains("Customer did not speak to us")){
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
                                                      
                                                        }
                                                        catch(Exception e){
                                                                e.printStackTrace();
                                                                }
                                                        }
                                              
                            }            
                               }
            catch(Exception e){
                System.out.println("Error @ outer work Slave 3 Leftover");
                e.printStackTrace();
            }
             finally{
            
              }           
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
      
// private void sendTestEmail(String text){
//           AdminSettings adminSettings = adminService.readSettings(settings);
//            new EmailService("souvik@jubination.com","stage 3",
//                                          text+" "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),adminSettings.getMyUsername(),
//                    adminSettings.getMyPassword(),
//                    adminSettings.getAuth(),
//                    adminSettings.getStarttls(),
//                    adminSettings.getHost(),adminSettings.getPort(),adminSettings.getSendgridApi()).start();
//     }
       
}
