/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.service;

import com.jubination.backend.service.report.parallel.worker.ReportOperator;
import com.jubination.backend.service.report.parallel.worker.thyrocare.XMLBackendProcess;
import com.jubination.model.dao.ReportDAOImpl;
import com.jubination.model.pojo.report.Report;
import com.jubination.model.pojo.status.thyrocare.ReportStatus;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

/**
 *
 * @author MumbaiZone
 */
@Service
@Transactional
public class XMLReportService {
    
    @Autowired
    ReportOperator rOperator;
    @Autowired
    XMLBackendProcess operator;
  @Autowired
    ReportDAOImpl reportDAO;
   
    //CLEAN AND SAVE TO DATABASE
                           public  void buildReport(Report report){
                                           
                                            reportDAO.buildEntity(report);
                                            
                           }
      
                           
      public Report getReport(String reportId){
           
           return (Report) reportDAO.fetchEntity(new Report(reportId));
       } 
                  
             public ReportStatus buildReportStatus(ReportStatus reportStatus){
                 return (ReportStatus) reportDAO.buildReportStatus(reportStatus);
             }              
    
}
