package com.jubination.backend.service.core.numbercall.serial;


import com.jubination.backend.pojo.exotel.ExotelMessage;
import com.jubination.backend.service.exotel.api.ExotelCallService;
import com.jubination.model.pojo.exotel.Call;
import com.jubination.service.AdminMaintainService;
import com.jubination.service.CallMaintainService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

/**
 *
 * @author Welcome
 */
@Component
public class CallBox {
                    private boolean flag=false;
                    private boolean checkFlag=false;
                    private int count = 0;
                    private Stack<String> numbers = new Stack<>();
                    private ConcurrentLinkedQueue<String> sids = new ConcurrentLinkedQueue<>();
                    private Stack<Call> stageThreeUpdates = new Stack<>();
                    
                    private int executives=0;
                   
                    
                    @Autowired
                    ExotelCallService callService;
                    @Autowired
                    AdminMaintainService adminMaintain;
                    @Autowired
                    CallMaintainService callMaintain;
                    @Async
                    @Scheduled(fixedRate=2500)
                    void callDicyCustomer() throws IOException,InterruptedException, JAXBException{
                        
                        
                                      //      System.out.println("CUSTOM CALLING ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                                            Long startTime=System.currentTimeMillis();
                                            if(!numbers.isEmpty()&&sids.size()<getExecutives()){
                                                   String callerId=numbers.peek();
                                                    
                                                 if(callerId!=null){
                                                      System.out.println("NOT EMPTY ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"+callerId);
                                                              
                                                                    if(numbers.isEmpty()){
                                                                                    flag=false;
                                                                    }
                                                                    //System.out.println("Stage 1:"+numbers.size()+" Sids in queue to be sent to exotel to process "+Thread.currentThread());
                                                                   
                                                                     ExotelMessage eMessage =callService.makeCall(callerId);
                                                                    Call message=null;
                                                                    //System.out.println("Stage 1:Got xml message");
                                                                    if(eMessage!=null){
                                                                                            //System.out.println("Stage 1:xml message not null");
                                                                                            if(eMessage.getSuccessMessage()!=null){
                                                                                                                //System.out.println("Stage 1:xml message success");
                                                                                                                message=eMessage.getSuccessMessage();
                                                                                                                message.setTrackStatus("Call request sent");
                                                                                                                message.setMessage("Stage 1 Calling");
                                                                                                                message.setCallTo(callerId);
                                                                                                                //adding all the sid values of calls placed
                                                                                                                sids.offer(message.getSid());
                                                                                             }
                                                                                            else if (eMessage.getFailureMessage()!=null){
                                                                                                               //System.out.println("Stage 1:xml message failed");
                                                                                                                message=eMessage.getFailureMessage();
                                                                                                                message.setCallTo(callerId);
                                                                                                                message.setDateCreated(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                                                                                                                message.setTrackStatus(message.getMessage());
                                                                                            }
                                                                                            else{
                                                                                                                //System.out.println("Stage 1:xml message unknown error");
                                                                                                                message.setCallTo(callerId);
                                                                                                                message.setTrackStatus("Unknown Error");
                                                                                                                message.setDateCreated(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                                                                                            }
                                                                    }
                                                                    //System.out.println("Stage 1:adding message to database");
                                                                    //saving call details
                                                                    callMaintain.addCallAPIMessage(message);
                                                                                            //removing number
                                                                                            numbers.pop();
                                                                                            //System.out.println("Stage 1:Number out of queue");
                                                                    flag=!numbers.isEmpty();
                                                                    if(!flag){
                                                                        //initiate call status update after call are over
                                                                        checkFlag=true;
                                                                    }
                                                                    Long endTime=System.currentTimeMillis();
                                            Long timeTaken=endTime-startTime;
                                            float tt=timeTaken/1000;
                                            //System.out.println("Time Taken "+tt+" seconds");
                                            }
                    }
                    }

   
                    @Async
                    @Scheduled(fixedDelay=3000)
                    void checkCalledCustomers() throws IOException, JAXBException{
                                        Long startTime=System.currentTimeMillis();
                                        if(isCheckFlag()||(sids.size()>=getExecutives()&&sids.size()>0)){
                                                            String sid=null;
                                                            //System.out.println("Stage 2:"+sids.size()+" Sids in queue to be sent to exotel to process "+Thread.currentThread());
                                                                                //fetching all the sid values
                                                                                sid=sids.peek();
                                                                                if(sids.isEmpty()){
                                                                                    checkFlag=false;
                                                                                }
                                                                                
                                                             ExotelMessage eMessage=callService.checkStatus(sid);
                                                             Call message=null;
                                                             //System.out.println("Stage 2:Got xml message");
                                                             if(eMessage!=null){
                                                                            //System.out.println("Stage 2:xml message not null");
                                                                            if(eMessage.getSuccessMessage()!=null){
                                                                                            message=eMessage.getSuccessMessage();
                                                                                            //System.out.println("Stage 2:xml message success "+message.getStatus()+message);
                                                                                            if(message.getStatus().contains("queued")||message.getStatus().contains("ringing")||message.getStatus().contains("in-progress")){
                                                                                                                        count++;
                                                                                                                        //System.out.println("Stage 2:sid out of queue. Trying for"+count+"th time");
                                                                                            }
                                                                                            else {
                                                                                                                        Call storedMessage=callMaintain.getCallRecordBySid(message.getSid());
                                                                                                                        if(storedMessage!=null){
                                                                                                                                                //System.out.println("Stage 2:sid of the number was present already");
                                                                                                                                                 message.setOrderId(storedMessage.getOrderId());
                                                                                                                                                 message.setMessage("Stage 2 Tracking");
                                                                                                                                                //updating call details
                                                                                                                                                if(!storedMessage.getMessage().equals("Stage 3 Tracking")){
                                                                                                                                                                            //System.out.println("Stage 2:stage 3 not updated yet");
                                                                                                                                                                            message.setCallTo(storedMessage.getCallTo());
                                                                                                                                                                            callMaintain.updateCallAPIMessage(message);
                                                                                                                                                  }
                                                                                                                                                  else{
                                                                                                                                                                            //System.out.println("Stage 2:stage 3 updated already");
                                                                                                                                                                            storedMessage.setStatus(message.getStatus());
                                                                                                                                                                            storedMessage.setCallType(message.getCallType());
                                                                                                                                                                            storedMessage.setRecordingUrl(message.getRecordingUrl());
                                                                                                                                                                            callMaintain.updateCallAPIMessage(storedMessage);
                                                                                                                                                   }
                                                                                                                        }
                                                                                                                                                 //removing all the sid values
                                                                                                                                                sids.poll();
                                                                                                                                                count=0;
                                                                                                                                                //System.out.println("Stage 2:Sid out of queue");
                                                                                            }
                                                                                            if(count>=300){
                                                                                                                        //System.out.println("Stage 2:Time out. sid out of queue. Trying for next sid");
                                                                                                                        sids.poll();
                                                                                                                        count=0;
                                                                                            }

                                                                            }
                                                             }
                                                             //stop operation if all done
                                                             checkFlag=!sids.isEmpty();
                                                              Long endTime=System.currentTimeMillis();
                                            Long timeTaken=endTime-startTime;
                                            float tt= timeTaken/1000;
                                            //System.out.println("Time Taken "+tt+" seconds");
                                        }
                                                                 
                  
                    }

                    //Depricated
           /*         @Async
                    @Scheduled(fixedDelay=3000)
                    void checkStageThreeCustomers() throws IOException, JAXBException{
                       Long startTime=System.currentTimeMillis();
                        if(!flag&&!checkFlag&&!stageThreeUpdates.isEmpty()){
                                             //fetching all the call values
                                                        Call callUpdated= stageThreeUpdates.peek();
                                                                           
                                                                           
                                                        if(callUpdated!=null){
                                                                    List<Call> callList=callMaintain.getCallBySid(callUpdated.getSid());
                                                                    if(callList!=null&&!callList.isEmpty()){
                                                                                        Call  call=callList.get(0);
                                                                                          //System.out.println("Stage 3:"+stageThreeUpdates.size()+"left. Update service started");
                                                                                         if(call!=null){
                                                                                                             //System.out.println("Stage 3:Call message present in database already and not null");
                                                                                                             call.setMessage("Stage 3 Tracking");
                                                                                                             call.setStatus("completed");
                                                                                                             call.setDailCallDuration(callUpdated.getDailCallDuration());
                                                                                                             call.setCallType(callUpdated.getCallType());
                                                                                                             call.setTrackStatus(callUpdated.getTrackStatus());
                                                                                                             call.setDigits(callUpdated.getDigits());
                                                                                                             call.setDailWhomNumber(callUpdated.getDailWhomNumber());
                                                                                                             call.setRecordingUrl(callUpdated.getRecordingUrl());
                                                                                                             call.setStartTime(callUpdated.getStartTime());
                                                                                                             call.setDirection(callUpdated.getDirection());
                                                                                                             //System.out.println("Stage 3:Updating database properties");
                                                                                                             callMaintain.updateCallAPIMessage(call);
                                                                                                             synchronized(this){
                                                                                                                               //removing all the call values
                                                                                                                                stageThreeUpdates.pop();
                                                                                                                                //System.out.println("Stage 3:Call Message out of queue");
                                                                                                            }
                                                                                         }
                                                                    }
                                                                    else{
                                                                                            //System.out.println("Stage 3:Call message not present in database");
                                                                                            callUpdated.setMessage("Incoming Tracking");
                                                                                            callMaintain.buildCallAPIMessage(callUpdated);
                                                                                            //removing all the call values
                                                                                             stageThreeUpdates.pop();
                                                                                             //System.out.println("Stage 3:Call Message out of queue");
                                                                                            //System.out.println("Stage 3:Built new CallMessage");
                                                                    }
                                                        }
                                                        if(stageThreeUpdates.isEmpty()){
                                                            callMaintain.sendEmailUpdate();
                                                        }
                                                              Long endTime=System.currentTimeMillis();
                                            Long timeTaken=endTime-startTime;
                                            float tt= timeTaken/1000;
                                            //System.out.println("Time Taken "+tt+" seconds");
                                        }
                                                            
                  
                    }
*/

                    public boolean isFlag() {
                        synchronized(this){         
                        return flag;
                        }
                    }

                    public void setFlag(boolean flag) {
                        synchronized(this){ 
                        this.flag = flag;
                        }
                    }

    public boolean isCheckFlag() {
        synchronized(this){
        return checkFlag;
        }
    }

    public void setCheckFlag(boolean checkFlag) {
        synchronized(this){
        this.checkFlag = checkFlag;
        }
    }
                    

     public Stack<String> getNumbers() {
       synchronized(numbers){
           
                return numbers;
       }
    }

    public ConcurrentLinkedQueue<String> getSids() {
        return sids;
    }

    public Stack<Call> getStageThreeUpdates() {
        synchronized(stageThreeUpdates){
        return stageThreeUpdates;
        }
    }

    public int getExecutives() {
        return executives;
    }

    public void setExecutives(int executives) {
        this.executives = executives;
    }

    
        
}