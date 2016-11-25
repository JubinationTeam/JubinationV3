/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.freshcall.parallel.master;

import com.jubination.backend.call.CallBox;
import com.jubination.model.pojo.Call;
import com.jubination.model.pojo.Client;
import com.jubination.model.pojo.Lead;
import com.jubination.service.CallMaintainService;
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
public class CallOperator {
    @Autowired
CallBox callHandler;
    @Autowired
CallManager eCallHandler;
    @Autowired
       private CallMaintainService service;
                    private boolean freshFlag=false;
                   
                    private boolean followupFlag=false;
                    
                    private boolean dumpRetriever=true;
                    private int count=7;
                    
                    private final String  dumpOvernight="*/3 * 20-23,0-8 * * *";
                    private final String freshCall="*/3 * 9-19 * * *";
                    private final String retreiveDump="0 5 9 * * *";
                    private final String missedCallCheck="0 30 9-19 * * *";
                    
                    
                    
                    
                    
                    private final String followUpCall="0 0 10-19 * * *";
                    private final String calculateAnalytics="0 30 23 * * *";
                    
                     private String appIdLead="102261";
                    private String appIdReEngage="107784";
                    private String callerIdLead="02233835916";
                    private String callerIdCust="02239698372";
                    
                    //test params
//                    private final String dumpOvernight="*/3 * 10-18 * * *";
//                   private final String freshCall="*/3 * 19-23,0-9 * * *";
//                    private final String retreiveDump="0 5 19 * * *";
               
//                    private final String noonFollowUpCall="0 0 14 * * *";
                    
                    private ConcurrentLinkedQueue<Client> numbers = new ConcurrentLinkedQueue<>();

    public CallOperator() {
        count=count+1;
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
                    
                    @Async
                    @Scheduled(cron = freshCall)//9am to 7pm do calling
                    void freshCustomerCall(){
                        if(isFreshFlag()||!numbers.isEmpty()&&eCallHandler.getStatus()){
                            System.out.println("In 10-17 fresh calling");
                                        while(!numbers.isEmpty()){
                                                        if(!(numbers.peek().getLead()!=null&&!numbers.peek().getLead().isEmpty())){
                                                                List<Lead> leadList = new ArrayList<>();
                                                                leadList.add(new Lead(numbers.peek().getTempLeadDetails()));
                                                                numbers.peek().setLead(leadList);
                                                        }
                                                        numbers.peek().getLead().get(0).setCount(count);
                                                        numbers.peek().getLead().get(0).setPending(true);
                                                        numbers.peek().setPriority(5);
                                                        eCallHandler.getClientStage1().push(numbers.poll());
                                                }
                                        setFreshFlag(false);
                                        
                        }
                    }
                    
                   
                    
                    @Async
                    @Scheduled(cron = dumpOvernight)//7pm to 8am save temp daa
                    void freshCustomerCallSave(){
                        if(isFreshFlag()||!numbers.isEmpty()&&eCallHandler.getStatus()){
                            System.out.println("In 19-9 overnight save dump");
                            while(!numbers.isEmpty()){
                                                            numbers.peek().setOvernight(true);
                                                            numbers.peek().setPriority(6);
                                                           if(service.saveTemporaryClient(numbers.peek())){
                                                               numbers.poll();
                                                           }
                                      } 
                            
                        }
                    }
                    @Async
                    @Scheduled(cron = retreiveDump)//9.05 am get overnight dump
                    void updateCustomerData(){
                   System.out.println("Retreiving overnight dump");
                                     for(Client client:service.getAllTemporaryClients()){
                                                         if(!(client.getLead()!=null&&!client.getLead().isEmpty())){
                                                                List<Lead> leadList = new ArrayList<>();
                                                                leadList.add(new Lead(client.getTempLeadDetails()));
                                                                client.setLead(leadList);
                                                        }
                                                        client.getLead().get(0).setCount(count);
                                                        client.getLead().get(0).setPending(true);
                                                        client.setPriority(5);
                                                        eCallHandler.getClientStage1().push(client);
                                                        
                                     }
                                     
                        
                    }
                    
                     @Async
                    @Scheduled(cron = missedCallCheck)//9am to 7pm do calling
                    void missedCustomerCall(){
                            System.out.println("miss call check");
                        if(numbers.isEmpty()&&eCallHandler.getStatus()&&eCallHandler.getClientStage1().isEmpty()){
                            
                            System.out.println("miss call check started");
                            for(Client client:service.getMarkedClients()){
                                    getNumbers().offer(client);
                            }
                            
                            setFreshFlag(true);     
                        }
                    }
                    
                    
                     @Async
                    @Scheduled(cron = followUpCall)
                    void followUpCustomerCallMorn() throws InterruptedException{
                        if(isFollowupFlag()&&eCallHandler.getStatus()){
                            System.out.println("follow up ");
                            int count=10;
                            while(!eCallHandler.getClientStage1().isEmpty()){
                                Thread.sleep(60000);
                                count--;
                                if(count==0){
                                    break;
                                }
                            }
                            if(eCallHandler.getClientStage1().isEmpty()){
                           for(Client client:service.getPendingCallsWithNotificationAndRecentLead("PendingAndNotified")){
                               client.setPriority(4);         
                               eCallHandler.getClientStage1().push(client);
                               
                           }
                             }
                        }
                    }
                    
                    
                  @Async
                    @Scheduled(cron = calculateAnalytics)//11.30pm do analytics
                    void doAnalytics() throws InterruptedException{
                        while(!numbers.isEmpty()){
                          Thread.sleep(60000);
                        }
                        
                        eCallHandler.getRealTimeInCall().clear();
                        service.doAnalytics();
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

 
    public ConcurrentLinkedQueue<Client> getNumbers() {
        return numbers;
    }

    public void setNumbers(ConcurrentLinkedQueue<Client> numbers) {
        this.numbers = numbers;
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

  



       
                    
}
