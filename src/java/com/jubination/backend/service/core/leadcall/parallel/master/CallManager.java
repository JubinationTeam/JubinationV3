package com.jubination.backend.service.core.leadcall.parallel.master;

import com.jubination.backend.service.core.leadcall.parallel.worker.CallWorkerPool;
import com.jubination.model.pojo.exotel.Call;
import com.jubination.model.pojo.crm.Client;
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
    
                    
                        
                        private static final long time=100;
                    
                        private Boolean status=true;
                    
                        private int executives=0;
                        
                        
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

    public void setExecutives(int executives) {
        this.executives = executives;
    }


                    
}
