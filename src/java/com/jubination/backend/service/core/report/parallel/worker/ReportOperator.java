/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.service.core.report.parallel.worker;

import com.jubination.backend.pojo.thyrocare.report.ReportMessage;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author MumbaiZone
 */
@Component
public class ReportOperator {
    
    @Autowired
    private ReportWorkerPool pool;
    private ConcurrentLinkedQueue<ReportMessage> reportMessage= new ConcurrentLinkedQueue<>();

    
    //start with 1 report at a time
    public boolean startParsing(ReportMessage msg){
        if(pool.getActiveCount()<1&&reportMessage.size()<1){
            reportMessage.offer(msg);
            pool.startTask();
            return true;
        }
        return false;
    }
    
    
    
    
    public ConcurrentLinkedQueue<ReportMessage> getReportMessage() {
        return reportMessage;
    }

    
}
