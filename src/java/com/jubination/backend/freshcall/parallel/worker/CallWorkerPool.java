/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.freshcall.parallel.worker;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 *
 * @author MumbaiZone
 */
@Component
public class CallWorkerPool extends ThreadPoolTaskExecutor{

    private List<CallWorker> workerList = new ArrayList<>();
    private int maxSize=10;
    
                    public CallWorkerPool(){
                        super();
                        setCorePoolSize(maxSize);
                        setMaxPoolSize(maxSize);
                        setWaitForTasksToCompleteOnShutdown(true);
                    }
                    
                     public CallWorker getCallWorker(int i){
                         return workerList.get(i);
                     }   
                     
                     public boolean isRunning(int i){
                        return false;
                     }
                     
                     
                     public void startTask(){
                         for(int i=0;i<maxSize;i++){
                                if(!workerList.get(i).isStatus()){
                                    workerList.get(i).setStatus(true);
                                    super.execute(workerList.get(i));
                                    return;
                                }
                            }
                    }

    @Override
    public int getActiveCount() {
        int count=0;
        for(CallWorker callWorker:workerList){
            if(callWorker.isStatus()){
                count++;
            }
        }
//        return count>super.getActiveCount()?count:super.getActiveCount(); 
return count;
    }
                     
                     
                     @Autowired
                     public void setCallWorker0(CallWorker callWorker){
                         workerList.add(callWorker);
                     }
                        
                     @Autowired
                     public void setCallWorker1(CallWorker callWorker){
                         workerList.add(callWorker);
                     }
                        
                     @Autowired
                     public void setCallWorker2(CallWorker callWorker){
                         workerList.add(callWorker);
                     }
                     
                     @Autowired
                     public void setCallWorker3(CallWorker callWorker){
                         workerList.add(callWorker);
                     }
                     
                     @Autowired
                     public void setCallWorker4(CallWorker callWorker){
                         workerList.add(callWorker);
                     }
                     
                     @Autowired
                     public void setCallWorker5(CallWorker callWorker){
                         workerList.add(callWorker);
                     }
                     
                     @Autowired
                     public void setCallWorker6(CallWorker callWorker){
                         workerList.add(callWorker);
                     }
                     
                     @Autowired
                     public void setCallWorker7(CallWorker callWorker){
                         workerList.add(callWorker);
                     }
                     
                     @Autowired
                     public void setCallWorker8(CallWorker callWorker){
                         workerList.add(callWorker);
                     }
                     
                     @Autowired
                     public void setCallWorker9(CallWorker callWorker){
                         workerList.add(callWorker);
                     }

    @Override
    public void setThreadNamePrefix(String threadNamePrefix) {
        super.setThreadNamePrefix("worker - "+threadNamePrefix); //To change body of generated methods, choose Tools | Templates.
    }
                     
}
