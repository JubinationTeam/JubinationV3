/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.service.core.leadcall.parallel.master;

import com.jubination.backend.service.sendgrid.EmailService;
import com.jubination.backend.service.exotel.numbercall.serial.CallBox;
import com.jubination.model.pojo.admin.AdminSettings;
import com.jubination.model.pojo.exotel.Call;
import com.jubination.model.pojo.crm.Client;
import com.jubination.model.pojo.crm.Lead;
import com.jubination.service.AdminMaintainService;
import com.jubination.service.CallMaintainService;
import com.jubination.service.DataAnalyticsService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author MumbaiZone
 */
@Component
public class CallScheduler {
    @Autowired
CallBox callHandler;
    @Autowired
CallManager eCallHandler;
    @Autowired
       private CallMaintainService service;
    @Autowired
       private DataAnalyticsService analyticsService;
        @Autowired
    private AdminMaintainService adminService;
    
                    
                    
                    private boolean freshFlag=false;
                    private boolean followupFlag=false;
                    private boolean dumpRetriever=true;
                    private boolean missedAppointment=true;
                   
                    private int count=7;
                    
                    private final String settings="settings";
                    
                    
                    private final String  dumpOvernight="*/4 * 20-23,0-8 * * *";
                    private final String freshCall="*/4 * 9-19 * * *";
                    private final String retreiveDump="2 0 9 * * *";
                    private final String missedCallCheck="0/10 */8 9-19 * * *";
                    private final String missedAppointmentScheduling="0/10 59 */2 * * *";
                    private final String followUpCall="0/10 */15 10-19 * * *";
                    private final String calculateAnalytics="0/10 30 23 * * *";
                    
                    private final String appIdLead="102261";
                    private final String appIdReEngage="107784";
                    private final String callerIdLead="02233835916";
                    private final String callerIdCust="02239698372";
                    
  
                    private ConcurrentLinkedQueue<Client> clients = new ConcurrentLinkedQueue<>();

    public CallScheduler() {
        count=count+1;
    }
                     
  
                    
                    @Async
                    @Scheduled(cron = freshCall)//9am to 7pm do calling
                    void freshCustomerCall(){
                        if(isFreshFlag()||!clients.isEmpty()&&eCallHandler.getStatus()){
                            System.out.println("In 10-17 fresh calling");
                                        while(!clients.isEmpty()){
                                             if(clients.peek().getLead()==null||clients.peek().getLead().isEmpty()){
                                                                List<Lead> leadList = new ArrayList<>();
                                                                leadList.add(new Lead(clients.peek().getTempLeadDetails()));
                                                                clients.peek().setLead(leadList);
                                                        }
                                                        if(service.readLead(clients.peek().getLead().get(0))==null){
                                                                clients.peek().getLead().get(0).setCount(count);
                                                                clients.peek().getLead().get(0).setPending(true);
                                                                clients.peek().setPriority(5);
                                                        }
                                                        //duplicacy check
                                                        Client clientToBeSent=clients.poll();
                                                        for(Client clientPresent:eCallHandler.getClientStage1()){
                                                            if(clientPresent.getTempLeadDetails().equals(clientToBeSent.getTempLeadDetails())){
                                                                clientToBeSent=null;
                                                                break;
                                                            }
                                                        }
                                                        if(clientToBeSent!=null){
                                                                eCallHandler.getClientStage1().push(clientToBeSent);
                                                            
                                                        }
                                                }
                                        setFreshFlag(false);
                                        
                        }
                    }
                    
                   
                    
                    @Async
                    @Scheduled(cron = dumpOvernight)//7pm to 8am save temp daa
                    void freshCustomerCallSave(){
                        if(isFreshFlag()||!clients.isEmpty()&&eCallHandler.getStatus()){
                            while(!clients.isEmpty()){
                                                            System.out.println("In 19-9 overnight save dump");
//                                                            clients.peek().setOvernight(true);
//                                                            clients.peek().setPriority(6);
//                                                            if(clients.peek().getLead()==null||clients.peek().getLead().isEmpty()){
//                                                                        List<Lead> leadList = new ArrayList<>();
//                                                                        leadList.add(new Lead(clients.peek().getTempLeadDetails()));
//                                                                        clients.peek().setLead(leadList);
//                                                                }
//                                                           if(service.updateTemporaryClient(clients.peek(),clients.peek().getLead().get(0))){
                                                               service.updateOvernightClient(clients.poll().getTempLeadDetails());
//                                                               clients.poll();
//                                                           }
                                      } 
                        }
                        setFreshFlag(!clients.isEmpty());
                    }
                    @Async
                    @Scheduled(cron = retreiveDump)//9.05 am get overnight dump
                    void updateCustomerData(){
                   System.out.println("Retreiving overnight dump");
//                                     for(Client client:service.getAllTemporaryClients()){
//                                            if(client!=null){
//                                                        if(client.getLead()==null||client.getLead().isEmpty()){
//                                                                List<Lead> leadList = new ArrayList<>();
//                                                                leadList.add(new Lead(client.getTempLeadDetails()));
//                                                                client.setLead(leadList);
//                                                        }
//                                                        client.getLead().get(0).setCount(count);
//                                                        client.getLead().get(0).setPending(true);
//                                                        client.setPriority(5);
//                                                        eCallHandler.getClientStage1().push(client);
//                                            }
//                                     }
                                     
                                    
                                            System.out.println("overnight calls added");
                                            for(Client client:service.getMarkedClients()){
                                                    getClients().offer(client);
                                            }

                                            setFreshFlag(true);     
                                        
                    }
                    
                    
                     @Async
                    @Scheduled(cron = missedCallCheck)//9am to 7pm do calling
                    void missedCustomerCall(){
                            System.out.println("miss call check");
                        if(clients.isEmpty()&&eCallHandler.getStatus()&&eCallHandler.getClientStage1().isEmpty()){
                            
                            System.out.println("miss call check started");
                            for(Client client:service.getMarkedClients()){
                                    getClients().offer(client);
                            }
                            
                            setFreshFlag(true);     
                        }
                    }
                    
                    @Async
                    @Scheduled(cron = missedAppointmentScheduling)//9am to 7pm do calling
                    void missedAppointmentAdd(){
                        if(missedAppointment){
                            System.out.println("miss appt add");
                            service.addTodaysMissedAppointment();
                        }
                    }
                    
                     @Async
                    @Scheduled(cron = followUpCall)
                    void followUpCustomer() throws InterruptedException{
                        if(isFollowupFlag()&&eCallHandler.getStatus()&&eCallHandler.getClientStage1().isEmpty()&&eCallHandler.getClientStage2().isEmpty()&&eCallHandler.getStageThreeUpdates().isEmpty()){
                            System.out.println("follow up ");
                                long pending=0l;
                                long notification=0l;
                                long pendingMa=0l;
                                long notificationMa=0l;
                                long pendingFresh=0l;
                                long pendingMinusOne=0l;
                                long inProgress=0l;
                                
                               List<Client> list=service.getPendingCallsWithNotificationAndRecentLead("Pending");
                               if(list!=null){
                                    for(Client client:list){
                                        if(client!=null){
                                                    Lead lead=client.getLead().get(client.getLead().size()-1);
                                                    if(lead.getCount()!=(getCount()-lead.getCall().size())){
                                                                lead.setCount(getCount()-lead.getCall().size());
                                                                service.updateLeadOnly(lead);
                                                    }
                                                    
                                                     if(lead.getCount()>0&&lead.getLeadStatus()!=null&&(!lead.getLeadStatus().contains("Follow up/Call back")&&
                                                        !lead.getLeadStatus().contains("Not interested")&&
                                                        !lead.getLeadStatus().contains("Not registered")&&
                                                        !lead.getLeadStatus().contains("Language not recognizable")&&
                                                        !lead.getLeadStatus().contains("No Service")&&
                                                        !lead.getLeadStatus().contains("Customer complained")&&
                                                        !lead.getLeadStatus().contains("Disapproved")
                                                        )){
                                                         //duplicacy check
                                                        for(Client clientPresent:eCallHandler.getClientStage1()){
                                                            if(clientPresent.getTempLeadDetails().equals(client.getTempLeadDetails())){
                                                                client=null;
                                                                break;
                                                            }
                                                        }
                                                        if(client!=null){
                                                                eCallHandler.getClientStage1().push(client);
                                                            pending++;
                                                        }
                                                    }
                                                     else{
                                                          eCallHandler.getClientStage1().push(client);
                                                            pending++;
                                                     }
                                                   
                                        }
                                        
                                    }
                               }
                               list=service.getPendingCallsWithNotificationAndRecentLead("Notified");
                               if(list!=null){
                                 for(Client client:list){
                                    client.setPriority(4);    
                                     Lead lead=client.getLead().get(client.getLead().size()-1);
                                    if(lead.getCall().size()>=2*getCount()){
                                                lead.setCount(0);
                                                service.updateLeadOnly(lead);
                                    }
                                    if(lead.getCount()>0){
                                            //duplicacy check
                                                        for(Client clientPresent:eCallHandler.getClientStage1()){
                                                            if(clientPresent.getTempLeadDetails().equals(client.getTempLeadDetails())){
                                                                client=null;
                                                                break;
                                                            }
                                                        }
                                                        if(client!=null){
                                                                eCallHandler.getClientStage1().push(client);
                                                            notification++;
                                                        }
                                    }
                                   
                                }
                               }
                               list=service.getPendingCallsWithNotificationAndRecentLead("PendingMA");
                               if(list!=null){
                                for(Client client:list){
                                    client.setPriority(4);         
                                    Lead lead=client.getLead().get(client.getLead().size()-1);
                                    if(lead.getCall().size()>=2*getCount()){
                                                lead.setCount(0);
                                               service.updateLeadOnly(lead);
                                    }
                                    if(lead.getCount()>0){
                                             //duplicacy check
                                                        for(Client clientPresent:eCallHandler.getClientStage1()){
                                                            if(clientPresent.getTempLeadDetails().equals(client.getTempLeadDetails())){
                                                                client=null;
                                                                break;
                                                            }
                                                        }
                                                        if(client!=null){
                                                                eCallHandler.getClientStage1().push(client);
                                                            pendingMa++;
                                                        }
                                    }
                                    
                                }
                               }
                               list=service.getPendingCallsWithNotificationAndRecentLead("NotifiedMA");
                               if(list!=null){
                                 for(Client client:list){
                                    client.setPriority(4);         
                                    Lead lead=client.getLead().get(client.getLead().size()-1);
                                    if(lead.getCall().size()>=2*getCount()){
                                                lead.setCount(0);
                                                service.updateLeadOnly(lead);
                                    }
                                    if(lead.getCount()>0){
                                              //duplicacy check
                                                        for(Client clientPresent:eCallHandler.getClientStage1()){
                                                            if(clientPresent.getTempLeadDetails().equals(client.getTempLeadDetails())){
                                                                client=null;
                                                                break;
                                                            }
                                                        }
                                                        if(client!=null){
                                                                eCallHandler.getClientStage1().push(client);
                                                            notificationMa++;
                                                        }
                                    }
                                   
                                }
                               }
                                 list=service.getPendingCallsWithNotificationAndRecentLead("PendingMinusOne");
                               if(list!=null){
                                 for(Client client:list){
                                    client.setPriority(4);         
                                    Lead lead=client.getLead().get(client.getLead().size()-1);
                                    if(lead.getCall().size()<=getCount()&&lead.getCount()<0){
                                             lead.setCount(getCount()-lead.getCall().size());
                                           service.updateLeadOnly(lead);
                                              //duplicacy check
                                                        for(Client clientPresent:eCallHandler.getClientStage1()){
                                                            if(clientPresent.getTempLeadDetails().equals(client.getTempLeadDetails())){
                                                                client=null;
                                                                break;
                                                            }
                                                        }
                                                        if(client!=null){
                                                                eCallHandler.getClientStage1().push(client);
                                                            pendingMinusOne++;
                                                        }
                                    }
                                   
                                }
                               }
                               
                               list=service.getPendingCallsWithNotificationAndRecentLead("PendingInProgress");
                               if(list!=null){
                                 for(Client client:list){
                                    client.setPriority(4);         
                                    Lead lead=client.getLead().get(client.getLead().size()-1);
                                    if(lead.getCall().size()<=getCount()&&lead.getCount()<0){
                                             lead.setCount(getCount()-lead.getCall().size());
                                           service.updateLeadOnly(lead);
                                            eCallHandler.getClientStage1().push(client);
                                              //duplicacy check
                                                        for(Client clientPresent:eCallHandler.getClientStage1()){
                                                            if(clientPresent.getTempLeadDetails().equals(client.getTempLeadDetails())){
                                                                client=null;
                                                                break;
                                                            }
                                                        }
                                                        if(client!=null){
                                                                eCallHandler.getClientStage1().push(client);
                                                            inProgress++;
                                                        }
                                    }
                                   
                                }
                               }
                               
                               //miss call added as well
                               System.out.println("miss call check started");
                                for(Client client:service.getMarkedClients()){
                                        getClients().offer(client);
                                        pendingFresh++;
                                }
                                setFreshFlag(true);  
                                  sendEmailFollowupUpdate("disha@jubination.com","Fresh Followup : "+pending+", Fresh Callback : "+notification+"Followup Missed Appointment : "+pendingMa+" CallBack Missed Appointment : "+notificationMa+" Fresh Call pending : "+pendingFresh+" Minus One pending : "+pendingMinusOne+" In progress leads : "+inProgress);
                                sendEmailFollowupUpdate("trupti@jubination.com","Fresh Followup : "+pending+", Fresh Callback : "+notification+"Followup Missed Appointment : "+pendingMa+" CallBack Missed Appointment : "+notificationMa+" Fresh Call pending : "+pendingFresh+" Minus One pending : "+pendingMinusOne+" In progress leads : "+inProgress);
                                sendEmailFollowupUpdate("vinay@jubination.com","Fresh Followup : "+pending+", Fresh Callback : "+notification+"Followup Missed Appointment : "+pendingMa+" CallBack Missed Appointment : "+notificationMa+" Fresh Call pending : "+pendingFresh+" Minus One pending : "+pendingMinusOne+" In progress leads : "+inProgress);
                                sendEmailFollowupUpdate("tauseef@jubination.com","Fresh Followup : "+pending+", Fresh Callback : "+notification+"Followup Missed Appointment : "+pendingMa+" CallBack Missed Appointment : "+notificationMa+" Fresh Call pending : "+pendingFresh+" Minus One pending : "+pendingMinusOne+" In progress leads : "+inProgress);
                                sendEmailFollowupUpdate("souvik@jubination.com","Fresh Followup : "+pending+", Fresh Callback : "+notification+"Followup Missed Appointment : "+pendingMa+" CallBack Missed Appointment : "+notificationMa+" Fresh Call pending : "+pendingFresh+" Minus One pending : "+pendingMinusOne+" In progress leads : "+inProgress);
                             
                        }
                    }
                    
                    
                  @Async
                    @Scheduled(cron = calculateAnalytics)//11.30pm do analytics
                    void doAnalytics() throws InterruptedException{
                        while(!clients.isEmpty()){
                          Thread.sleep(60000);
                        }
                        
                        analyticsService.mailSpokeAnalytics(null);
                        analyticsService.doAnalytics();
                    }

                    
                        public void doLeadCall(String numbers){
                          if(numbers!=null){
                          for(String number:numbers.trim().split(System.lineSeparator())){
                              if(!number.isEmpty()){
                              if(!number.startsWith("0")){
                                                      number="0"+number;
                                  }
                                                              callHandler.setAppId(appIdLead);
                                                              callHandler.setCallerId(callerIdLead);
                                                              callHandler.getNumbers().push(number);


                                                              if(!callHandler.isFlag()){
                                                                      callHandler.setFlag(true);
                                                                 }

                                          }
                          }
                          }

                      }
                      public void doCustCall(String numbers){
                                        if(numbers!=null){
                                        for(String number:numbers.trim().split(System.lineSeparator())){
                                            if(!number.isEmpty()){
                                            if(!number.startsWith("0")){
                                                                    number="0"+number;
                                                }
                                                                             callHandler.setAppId(appIdReEngage);
                                                                             callHandler.setCallerId(callerIdCust);
                                                                            callHandler.getNumbers().push(number);

                                                                            if(!callHandler.isFlag()){
                                                                                    callHandler.setFlag(true);
                                                                               }

                                                        }
                                        }
                                        }

                                    }
                     
                                public void doStageThreeCall(Call call){
                                           if(call!=null){
                                                                
                                                                eCallHandler.getStageThreeUpdates().add(call);
                                           }
                                           
                                        System.out.println("Adding Stage 3 Calling. "+eCallHandler.getStageThreeUpdates().size()+" updates added");

                                    }
                                
             public  void followUpCustomerManual() {
                    System.out.println("follow up ");
                                long pending=0l;
                                long notification=0l;
                                long pendingMa=0l;
                                long notificationMa=0l;
                                long pendingFresh=0l;
                                long pendingMinusOne=0l;
                                long inProgress=0l;
                                
                               List<Client> list=service.getPendingCallsWithNotificationAndRecentLead("Pending");
                               if(list!=null){
                                    for(Client client:list){
                                        if(client!=null){
                                                    Lead lead=client.getLead().get(client.getLead().size()-1);
                                                    if(lead.getCount()!=(getCount()-lead.getCall().size())){
                                                                lead.setCount(getCount()-lead.getCall().size());
                                                                service.updateLeadOnly(lead);
                                                    }
                                                    
                                                     if(lead.getCount()>0&&lead.getLeadStatus()!=null&&(!lead.getLeadStatus().contains("Follow up/Call back")&&
                                                        !lead.getLeadStatus().contains("Not interested")&&
                                                        !lead.getLeadStatus().contains("Not registered")&&
                                                        !lead.getLeadStatus().contains("Language not recognizable")&&
                                                        !lead.getLeadStatus().contains("No Service")&&
                                                        !lead.getLeadStatus().contains("Customer complained")&&
                                                        !lead.getLeadStatus().contains("Disapproved")
                                                        )){
                                                         //duplicacy check
                                                        for(Client clientPresent:eCallHandler.getClientStage1()){
                                                            if(clientPresent.getTempLeadDetails().equals(client.getTempLeadDetails())){
                                                                client=null;
                                                                break;
                                                            }
                                                        }
                                                        if(client!=null){
                                                                eCallHandler.getClientStage1().push(client);
                                                            pending++;
                                                        }
                                                    }
                                                      else{
                                                          eCallHandler.getClientStage1().push(client);
                                                            pending++;
                                                     }
                                        }
                                        
                                    }
                               }
                               list=service.getPendingCallsWithNotificationAndRecentLead("Notified");
                               if(list!=null){
                                 for(Client client:list){
                                    client.setPriority(4);    
                                     Lead lead=client.getLead().get(client.getLead().size()-1);
                                    if(lead.getCall().size()>=2*getCount()){
                                                lead.setCount(0);
                                                service.updateLeadOnly(lead);
                                    }
                                    if(lead.getCount()>0){
                                            //duplicacy check
                                                        for(Client clientPresent:eCallHandler.getClientStage1()){
                                                            if(clientPresent.getTempLeadDetails().equals(client.getTempLeadDetails())){
                                                                client=null;
                                                                break;
                                                            }
                                                        }
                                                        if(client!=null){
                                                                eCallHandler.getClientStage1().push(client);
                                                            notification++;
                                                        }
                                    }
                                   
                                }
                               }
                               list=service.getPendingCallsWithNotificationAndRecentLead("PendingMA");
                               if(list!=null){
                                for(Client client:list){
                                    client.setPriority(4);         
                                    Lead lead=client.getLead().get(client.getLead().size()-1);
                                    if(lead.getCall().size()>=2*getCount()){
                                                lead.setCount(0);
                                               service.updateLeadOnly(lead);
                                    }
                                    if(lead.getCount()>0){
                                             //duplicacy check
                                                        for(Client clientPresent:eCallHandler.getClientStage1()){
                                                            if(clientPresent.getTempLeadDetails().equals(client.getTempLeadDetails())){
                                                                client=null;
                                                                break;
                                                            }
                                                        }
                                                        if(client!=null){
                                                                eCallHandler.getClientStage1().push(client);
                                                            pendingMa++;
                                                        }
                                    }
                                    
                                }
                               }
                               list=service.getPendingCallsWithNotificationAndRecentLead("NotifiedMA");
                               if(list!=null){
                                 for(Client client:list){
                                    client.setPriority(4);         
                                    Lead lead=client.getLead().get(client.getLead().size()-1);
                                    if(lead.getCall().size()>=2*getCount()){
                                                lead.setCount(0);
                                                service.updateLeadOnly(lead);
                                    }
                                    if(lead.getCount()>0){
                                              //duplicacy check
                                                        for(Client clientPresent:eCallHandler.getClientStage1()){
                                                            if(clientPresent.getTempLeadDetails().equals(client.getTempLeadDetails())){
                                                                client=null;
                                                                break;
                                                            }
                                                        }
                                                        if(client!=null){
                                                                eCallHandler.getClientStage1().push(client);
                                                            notificationMa++;
                                                        }
                                    }
                                   
                                }
                               }
                                 list=service.getPendingCallsWithNotificationAndRecentLead("PendingMinusOne");
                               if(list!=null){
                                 for(Client client:list){
                                    client.setPriority(4);         
                                    Lead lead=client.getLead().get(client.getLead().size()-1);
                                    if(lead.getCall().size()<=getCount()&&lead.getCount()<0){
                                             lead.setCount(getCount()-lead.getCall().size());
                                           service.updateLeadOnly(lead);
                                              //duplicacy check
                                                        for(Client clientPresent:eCallHandler.getClientStage1()){
                                                            if(clientPresent.getTempLeadDetails().equals(client.getTempLeadDetails())){
                                                                client=null;
                                                                break;
                                                            }
                                                        }
                                                        if(client!=null){
                                                                eCallHandler.getClientStage1().push(client);
                                                            pendingMinusOne++;
                                                        }
                                    }
                                   
                                }
                               }
                               
                               list=service.getPendingCallsWithNotificationAndRecentLead("PendingInProgress");
                               if(list!=null){
                                 for(Client client:list){
                                    client.setPriority(4);         
                                    Lead lead=client.getLead().get(client.getLead().size()-1);
                                    if(lead.getCall().size()<=getCount()&&lead.getCount()<0){
                                             lead.setCount(getCount()-lead.getCall().size());
                                           service.updateLeadOnly(lead);
                                            eCallHandler.getClientStage1().push(client);
                                              //duplicacy check
                                                        for(Client clientPresent:eCallHandler.getClientStage1()){
                                                            if(clientPresent.getTempLeadDetails().equals(client.getTempLeadDetails())){
                                                                client=null;
                                                                break;
                                                            }
                                                        }
                                                        if(client!=null){
                                                                eCallHandler.getClientStage1().push(client);
                                                            inProgress++;
                                                        }
                                    }
                                   
                                }
                               }
                               
                               //miss call added as well
                               System.out.println("miss call check started");
                                for(Client client:service.getMarkedClients()){
                                        getClients().offer(client);
                                        pendingFresh++;
                                }
                                setFreshFlag(true);  
                                  sendEmailFollowupUpdate("disha@jubination.com","Fresh Followup : "+pending+", Fresh Callback : "+notification+"Followup Missed Appointment : "+pendingMa+" CallBack Missed Appointment : "+notificationMa+" Fresh Call pending : "+pendingFresh+" Minus One pending : "+pendingMinusOne+" In progress leads : "+inProgress);
                                sendEmailFollowupUpdate("trupti@jubination.com","Fresh Followup : "+pending+", Fresh Callback : "+notification+"Followup Missed Appointment : "+pendingMa+" CallBack Missed Appointment : "+notificationMa+" Fresh Call pending : "+pendingFresh+" Minus One pending : "+pendingMinusOne+" In progress leads : "+inProgress);
                                sendEmailFollowupUpdate("vinay@jubination.com","Fresh Followup : "+pending+", Fresh Callback : "+notification+"Followup Missed Appointment : "+pendingMa+" CallBack Missed Appointment : "+notificationMa+" Fresh Call pending : "+pendingFresh+" Minus One pending : "+pendingMinusOne+" In progress leads : "+inProgress);
                                sendEmailFollowupUpdate("tauseef@jubination.com","Fresh Followup : "+pending+", Fresh Callback : "+notification+"Followup Missed Appointment : "+pendingMa+" CallBack Missed Appointment : "+notificationMa+" Fresh Call pending : "+pendingFresh+" Minus One pending : "+pendingMinusOne+" In progress leads : "+inProgress);
                                sendEmailFollowupUpdate("souvik@jubination.com","Fresh Followup : "+pending+", Fresh Callback : "+notification+"Followup Missed Appointment : "+pendingMa+" CallBack Missed Appointment : "+notificationMa+" Fresh Call pending : "+pendingFresh+" Minus One pending : "+pendingMinusOne+" In progress leads : "+inProgress);
                             
                        
                    }
                    
                         
                    
    public boolean isFreshFlag() {
        synchronized(this){
        return freshFlag;
        }
    }

    public void setFreshFlag(boolean freshFlag) {
        synchronized(this){
        this.freshFlag = freshFlag;
        }
    }

 
    public ConcurrentLinkedQueue<Client> getClients() {
        return clients;
    }

    public void setClients(ConcurrentLinkedQueue<Client> clients) {
        this.clients = clients;
    }

    public boolean isDumpRetriever() {
        return dumpRetriever;
    }

    public void setDumpRetriever(boolean dumpRetriever) {
        this.dumpRetriever = dumpRetriever;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isFollowupFlag() {
        return followupFlag;
    }

    public void setFollowupFlag(boolean followupFlag) {
        this.followupFlag = followupFlag;
    }

    public boolean isMissedAppointment() {
        return missedAppointment;
    }

    public void setMissedAppointment(boolean missedAppointment) {
        this.missedAppointment = missedAppointment;
    }

  
 private void sendEmailFollowupUpdate(String email,String content){
           AdminSettings adminSettings = adminService.readSettings(settings);
            new EmailService(email,"Follow up started",
                                          "Hi,<br/>" +
                                                "<br/>" +
                                                "I am call Bot!<br/>" +
                                                "<br/>" +
                                                "Follow u  has started" +
                                                "<br/>" +
                                                "<br/>" +
                                                content+
                                                "<br/>" +
                                                "<br/>" +
                                                "Regards,<br/>" + 
                                                "Call Bot ",adminSettings.getMyUsername(),adminSettings.getMyPassword(),adminSettings.getAuth(),adminSettings.getStarttls(),adminSettings.getHost(),adminSettings.getPort(),adminSettings.getSendgridApi()).start();
     }


       
                    
}
