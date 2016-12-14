/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.service.report.parallel.worker;


import com.jubination.backend.service.report.parallel.worker.thyrocare.ReportWorker;
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
public class ReportWorkerPool extends ThreadPoolTaskExecutor{

    private List<ReportWorker> workerList = new ArrayList<>();
    private int maxSize=10;
    
                    public ReportWorkerPool(){
                        super();
                        setCorePoolSize(maxSize);
                        setMaxPoolSize(maxSize);
                        setWaitForTasksToCompleteOnShutdown(true);
                    }
                    
                     public ReportWorker getReportWorker(int i){
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
        for(ReportWorker reportWorker:workerList){
            if(reportWorker.isStatus()){
                count++;
            }
        }
return count;
    }
                     
                     
                     @Autowired
                     public void setReportWorker0(ReportWorker reportWorker){
                         workerList.add(reportWorker);
                     }
                        
                     @Autowired
                     public void setReportWorker1(ReportWorker reportWorker){
                         workerList.add(reportWorker);
                     }
                        
                     @Autowired
                     public void setReportWorker2(ReportWorker reportWorker){
                         workerList.add(reportWorker);
                     }
                     
                     @Autowired
                     public void setReportWorker3(ReportWorker reportWorker){
                         workerList.add(reportWorker);
                     }
                     
                     @Autowired
                     public void setReportWorker4(ReportWorker reportWorker){
                         workerList.add(reportWorker);
                     }
                     
                     @Autowired
                     public void setReportWorker5(ReportWorker reportWorker){
                         workerList.add(reportWorker);
                     }
                     
                     @Autowired
                     public void setReportWorker6(ReportWorker reportWorker){
                         workerList.add(reportWorker);
                     }
                     
                     @Autowired
                     public void setReportWorker7(ReportWorker reportWorker){
                         workerList.add(reportWorker);
                     }
                     
                     @Autowired
                     public void setReportWorker8(ReportWorker reportWorker){
                         workerList.add(reportWorker);
                     }
                     
                     @Autowired
                     public void setReportWorker9(ReportWorker reportWorker){
                         workerList.add(reportWorker);
                     }

    @Override
    public void setThreadNamePrefix(String threadNamePrefix) {
        super.setThreadNamePrefix("worker - "+threadNamePrefix); //To change body of generated methods, choose Tools | Templates.
    }
                     
}
