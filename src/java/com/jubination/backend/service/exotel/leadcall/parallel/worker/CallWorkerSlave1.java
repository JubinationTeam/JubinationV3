/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.service.exotel.leadcall.parallel.worker;

import com.jubination.backend.pojo.exotel.ExotelMessage;
import com.jubination.backend.service.sendgrid.EmailService;
import com.jubination.backend.service.core.leadcall.parallel.master.CallManager;
import com.jubination.backend.service.exotel.api.ExotelCallService;
import com.jubination.model.pojo.admin.AdminSettings;
import com.jubination.model.pojo.exotel.Call;
import com.jubination.model.pojo.crm.Client;
import com.jubination.model.pojo.crm.Lead;
import com.jubination.service.AdminMaintainService;
import com.jubination.service.CallMaintainService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author MumbaiZone
 */
@Component
@Scope("prototype")
public class CallWorkerSlave1 {
    @Autowired
     private  CallMaintainService service;
    @Autowired
     private  CallManager manager;
    @Autowired
     private CallWorkerSlave2 worker2;
    @Autowired
    private AdminMaintainService adminService;
    @Autowired
    private  ExotelCallService callService;
    
    private static final String settings="settings";
    
      
      
        public  void work(){
            try{
                          if(!manager.getClientStage1().empty()){
                              
                             Client client=manager.getClientStage1().pop();
                               if(client!=null&&client.getLead()!=null&&client.getLead().size()>0){
                                Lead lead=client.getLead().get(client.getLead().size()-1);
                                 lead.setLastCallingThread(Thread.currentThread().getName());
                                    service.updateLeadOnly(lead);
                                    if(lead.getLastCallingThread().equals(Thread.currentThread().getName())){

                                    worker2.work(readAndSaveMessage(callService.makeCall(client.getPhoneNumber()), client));
                                    }
                               }else{
                                                sendTestEmail("Stage 1 line 80");
                                            }
                       }
            }
            catch(Exception e){
                System.out.println("Error @ outer work Slave 1");
                e.printStackTrace();
            }
            finally{
            }
                   
                      
            }

          
          
          private Client readAndSaveMessage(ExotelMessage eMessage, Client client) {
              boolean saved=false;
              boolean leadsAttached=false;
            if(client!=null&&client.getLead()!=null&&client.getLead().size()>0){
              Lead lead=client.getLead().get(client.getLead().size()-1);
             
                 try{
                          
                     //exotel message to call details
                             String callerId=client.getPhoneNumber();
                             Call call=null;
                                boolean passOn=false;
                                if(eMessage!=null){
                                        System.out.println(Thread.currentThread().getName()+" "+"Stage 1:xml message not null");
                                        if(eMessage.getSuccessMessage()!=null){
                                                System.out.println(Thread.currentThread().getName()+" "+"Stage 1:xml message success");
                                                call=eMessage.getSuccessMessage();
                                                call.setTrackStatus("Call request sent");
                                                call.setMessage("Stage 1 Calling");
                                                call.setCallTo(callerId);
                                                //adding all the sid values of calls placed
                                                passOn=true;
                                         }
                                        else if (eMessage.getFailureMessage()!=null){
                                                System.out.println(Thread.currentThread().getName()+" "+"Stage 1:xml message failed");
                                                 call=eMessage.getFailureMessage();
                                                 call.setCallTo(callerId);
                                                 call.setDateCreated(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                                                 call.setTrackStatus(call.getMessage());
                                                 sendEmailToFailCall("disha@jubination.com", client.getTempLeadDetails(), client.getPhoneNumber());
                                                 sendEmailToFailCall("trupti@jubination.com", client.getTempLeadDetails(), client.getPhoneNumber());
                                                 sendEmailToFailCall("vinay@jubination.com", client.getTempLeadDetails(), client.getPhoneNumber());
                                                 sendEmailToFailCall("tauseef@jubination.com", client.getTempLeadDetails(), client.getPhoneNumber());
                                                 sendEmailToFailCall("souvik@jubination.com", client.getTempLeadDetails(), client.getPhoneNumber());
                                        }
                                        else{
                                                System.out.println(Thread.currentThread().getName()+" "+"Stage 1:xml message unknown error");
                                                call.setCallTo(callerId);
                                                call.setTrackStatus("Call details not accepted by Exotel");
                                                call.setDateCreated(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                                        }
                                    }
                                //Settings to make objects ready
                                 client.setOvernight(false);
                                 
                                 
                                leadsAttached=true;
                                lead.setCount(lead.getCount()-1);
                                    
                                    //If count zero, make all the leads count zero
                                        if(lead.getCount()==0){
                                            lead.setPending(false);
                                            lead.setNotification(false);
                                            if(lead.getLastCallingThread().equals(Thread.currentThread().getName())){
                                                
                                                List<Lead> leadList=service.getDuplicateLeads(client.getPhoneNumber());
                                                     for(Lead l:leadList){
                                                                     l.setNotification(false);
                                                                     l.setPending(false);
                                                                     l.setCount(0);
                                                                     service.updateLeadOnly(l);
                                                     }
                                                }
                                            else{
                                                sendTestEmail("Stage 1 line 213");
                                            }
                                            }
                                            
                                            //Try Saving to database 10 times
                                            System.out.println(Thread.currentThread().getName()+" "+"Stage 1:adding message to database");

                                            int count=0;

                                            while(!saved&&count<10){
                                                        try{
                                                            
                                                            if(lead.getLastCallingThread().equals(Thread.currentThread().getName())){
                                                                    saved=service.addClientAndUnmarkBackupClient(client, lead, call);
                                                                    if(passOn){
                                                                                    lead.getCall().add(call);
                                                                                    manager.getClientStage2().offer(client);
                                                                     }
                                                            }else{
                                                                sendTestEmail("Stage 1 line 230");
                                                            }
                                                        }
                                                        catch(Exception e){
                                                            sendTestEmail("Stage 1 line 234"+e.toString());
                                                            if(lead!=null){
                                                                System.out.println("Tried saving client to database "+count+"th time "+lead.getLeadId()+"::::::::::::::::::::::%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                                                            }
                                                            e.printStackTrace();
                                                            
                                                            Thread.sleep(300);

                                                        }    
                                                            count++;
                                            }
                                            System.out.println(Thread.currentThread().getName()+" "+"Stage 1:Call out of queue");
                                            
                                           
                                     
                          
                            }
                            catch(Exception e){
                                    sendTestEmail("Stage 1 line 251"+e.toString());
                                    if(!saved){
                                        
                                            if(lead.getLastCallingThread().equals(Thread.currentThread().getName())){
                                               client.setTempLeadDetails(client.getTempLeadDetails()+"|Error");
                                               manager.setExecutives(manager.getExecutives()-1, "CALLBOT");
                                            }
                                            else{
                                                sendTestEmail("Stage 1 line 254"+"not saved @ database");
                                            }
                                           
                                    }
                                    
                                            System.err.println("Failed to save object to database in Stage 1::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                                     e.printStackTrace();
                                            
                            } 
            }
            else{
                System.err.println("NO LEAD ATTACHED::::::::::::::::::::::::::::::::::::::::::::::::: Slave1");
            }
                 return client;
          }
          
          private void sendTestEmail(String text){
           AdminSettings adminSettings = adminService.readSettings(settings);
            new EmailService("souvik@jubination.com",text,
                                          text+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),adminSettings.getMyUsername(),
                    adminSettings.getMyPassword(),
                    adminSettings.getAuth(),
                    adminSettings.getStarttls(),
                    adminSettings.getHost(),adminSettings.getPort(),adminSettings.getSendgridApi()).start();
     }
          
          
          private void sendEmailToFailCall(String email,String leadId,String number){
           AdminSettings adminSettings = adminService.readSettings(settings);
            new EmailService(email,"Calls failed",
                                          "Hi,<br/>" +
                                                "<br/>" +
                                                "I am call Bot!<br/>" +
                                                "<br/>" +
                                                "Exotel has failed to call the following lead/number" +
                                                "<br/>" +
                                                "Lead "+leadId+", number "+number+
                                                "<br/>" +
                                                "<br/>" +
                                                "Wish you a happy & healthy day!<br/>" +
                                                "<br/>" +
                                                "<br/>" +
                                                "Regards,<br/>" + 
                                                "Call Bot ",adminSettings.getMyUsername(),adminSettings.getMyPassword(),adminSettings.getAuth(),adminSettings.getStarttls(),adminSettings.getHost(),adminSettings.getPort(),adminSettings.getSendgridApi()).start();
     }
          
}
