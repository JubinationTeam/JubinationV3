/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.service.report.parallel.worker;

import com.jubination.backend.pojo.report.thyrocare.ReportMessage;
import com.jubination.backend.service.report.parallel.worker.ReportOperator;
import com.jubination.backend.service.report.parallel.worker.thyrocare.PDFBackendProcess;
import com.jubination.backend.service.report.parallel.worker.thyrocare.XMLBackendProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author MumbaiZone
 */
@Component
@Scope("prototype")
public class ReportWorker implements Runnable{
       

      
   private  boolean status;
   
   @Autowired
   ReportOperator operator;
   
   @Autowired
   PDFBackendProcess pdfOperator;
   
   @Autowired
   XMLBackendProcess xmlOperator;
    
    
    @Override
    public void run() {
                    try{
                           //runs
                           ReportMessage msg =operator.getReportMessage().poll();
                           if(!xmlOperator.parseAndBuildXML(msg)){
                                        if(pdfOperator.parseAndBuildPDF(msg)){
                                            System.out.println("PDF parsing Done");
                                        }
                                        else{
                                            System.out.println("Parsing failed");
                                        }
                                   }
                           else{
                               System.out.println("XML parsing Done");
                           }
                               
                           status=!status;
                    }
                    catch(Exception e){
                            e.printStackTrace();
                    }
          }
           
           
          
     
    
   
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

   
}
