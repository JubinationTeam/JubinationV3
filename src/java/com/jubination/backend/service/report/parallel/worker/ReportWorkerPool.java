/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.service.report.parallel.worker;


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
    private final int maxSize=10;
    
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
                         System.out.println("REQUESTED FOR NEW REPORTWORKER0");
                         workerList.add(reportWorker);
                     }
                        
                     @Autowired
                     public void setReportWorker1(ReportWorker reportWorker){
                         System.out.println("REQUESTED FOR NEW REPORTWORKER1");
                         workerList.add(reportWorker);
                     }
                        
                     @Autowired
                     public void setReportWorker2(ReportWorker reportWorker){
                         System.out.println("REQUESTED FOR NEW REPORTWORKER2");
                         workerList.add(reportWorker);
                     }
                     
                     @Autowired
                     public void setReportWorker3(ReportWorker reportWorker){
                         System.out.println("REQUESTED FOR NEW REPORTWORKER3");
                         workerList.add(reportWorker);
                     }
                     
                     @Autowired
                     public void setReportWorker4(ReportWorker reportWorker){
                         System.out.println("REQUESTED FOR NEW REPORTWORKER4");
                         workerList.add(reportWorker);
                     }
                     
                     @Autowired
                     public void setReportWorker5(ReportWorker reportWorker){
                         System.out.println("REQUESTED FOR NEW REPORTWORKER5");
                         workerList.add(reportWorker);
                     }
                     
                     @Autowired
                     public void setReportWorker6(ReportWorker reportWorker){
                         System.out.println("REQUESTED FOR NEW REPORTWORKER6");
                         workerList.add(reportWorker);
                     }
                     
                     @Autowired
                     public void setReportWorker7(ReportWorker reportWorker){
                         System.out.println("REQUESTED FOR NEW REPORTWORKER7");
                         workerList.add(reportWorker);
                     }
                     
                     @Autowired
                     public void setReportWorker8(ReportWorker reportWorker){
                         System.out.println("REQUESTED FOR NEW REPORTWORKER8");
                         workerList.add(reportWorker);
                     }
                     
                     @Autowired
                     public void setReportWorker9(ReportWorker reportWorker){
                         System.out.println("REQUESTED FOR NEW REPORTWORKER9");
                         workerList.add(reportWorker);
                     }

    @Override
    public void setThreadNamePrefix(String threadNamePrefix) {
        super.setThreadNamePrefix("reportworker - "+threadNamePrefix); //To change body of generated methods, choose Tools | Templates.
    }
                     
}
