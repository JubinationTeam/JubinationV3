package com.jubination.backend.service.core.leadcall.parallel.master;

import com.jubination.backend.service.core.leadcall.parallel.worker.CallWorkerPool;
import com.jubination.backend.service.sendgrid.EmailService;
import com.jubination.model.pojo.admin.AdminSettings;
import com.jubination.model.pojo.exotel.Call;
import com.jubination.model.pojo.crm.Client;
import com.jubination.service.AdminMaintainService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class CallManager {
    
                    
                         @Autowired
                        AdminMaintainService adminService;
                        private static final long time=100;
                    
                        private Boolean status=true;
                    
                        private int executives=0;
                        
                        private final String settings="settings";
                        
                        @Autowired
                        private CallWorkerPool workerPool;
                        
                        
                      
                         private Stack<Client> client= new Stack<>();
                        private ConcurrentLinkedQueue<Client> tempClient= new ConcurrentLinkedQueue<>();
                        private Stack<Client> realTimeClient= new Stack<>();
                        private List<Call> message= new ArrayList<>();
                        boolean stageThreeFlag=true;
                        
                      

                    @Async
                    @Scheduled(fixedRate=time)
                    void callDicyCustomer() throws IOException,InterruptedException, JAXBException{
                                     if(getStatus()&&!getClientStage1().empty()){
                                         stageThreeFlag=true;
                                         if(executives>tempClient.size()&&executives>workerPool.getActiveCount()){
                                             
                                         System.out.println(workerPool.getActiveCount()+" Active count for normal operation");
                                                workerPool.startTask();
                                         }
                                     }
                                     else if(!getStageThreeUpdates().isEmpty()&&stageThreeFlag){
                                         System.out.println(workerPool.getActiveCount()+" Active count for stage 3 updates");
                                                workerPool.startTask();
                                                stageThreeFlag=false;
                                     }
                                     else{
                                         stageThreeFlag=true;
                                         
                                     }
                    }

                public Boolean getStatus() {
                    synchronized(status){
                        return status;
                    }
                }

                public void setStatus(Boolean status) {
                    synchronized(status){
                        this.status = status;
                    }
                }




                    public Stack<Client> getClientStage1() {
                        synchronized(client){
                            return client;
                        }
                    }



                    public List<Call> getStageThreeUpdates() {
                        synchronized(message){
                                return message;
                        }
                    }

    public ConcurrentLinkedQueue<Client> getClientStage2() {
        return tempClient;
    }

    public Stack<Client> getRealTimeInCall() {
        synchronized(realTimeClient){
            return realTimeClient;
        }
    }
       
                    

   
    public int getExecutives() {
            return executives;
    }

    public void setExecutives(int executives, String name) {
            if(this.executives!=executives){
                                                 sendEmailToFailCall("disha@jubination.com", executives,this.executives, name);
                                                 sendEmailToFailCall("trupti@jubination.com", executives,this.executives, name);
                                                 sendEmailToFailCall("vinay@jubination.com", executives,this.executives, name);
                                                 sendEmailToFailCall("tauseef@jubination.com", executives,this.executives, name);
                                                 sendEmailToFailCall("souvik@jubination.com", executives,this.executives, name);
                                                 this.executives = executives;
            }
    }

private void sendEmailToFailCall(String email,int numberNew, int numberPrev,String name){
           AdminSettings adminSettings = adminService.readSettings(settings);
            new EmailService(email,"Executives changed",
                                          "Hi,<br/>" +
                                                "<br/>" +
                                                "I am call Bot!<br/>" +
                                                "<br/>" +
                                                "Number of executive is changed from" +numberPrev +" to "+numberNew+" by "+name+
                                                "<br/>" +
                                                "<br/>" +
                                                "Wish you a happy & healthy day!<br/>" +
                                                "<br/>" +
                                                "<br/>" +
                                                "Regards,<br/>" + 
                                                "Call Bot ",adminSettings.getMyUsername(),adminSettings.getMyPassword(),adminSettings.getAuth(),adminSettings.getStarttls(),adminSettings.getHost(),adminSettings.getPort(),adminSettings.getSendgridApi()).start();
     }
                    
}
