package com.jubination.controller;

import com.jubination.backend.service.report.thyrocare.ReportMessage;
import com.jubination.model.pojo.report.Barcode;
import com.jubination.model.pojo.report.Profile;
import com.jubination.model.pojo.report.ReferenceRange;
import com.jubination.model.pojo.report.Report;
import com.jubination.model.pojo.report.Test;
import com.jubination.service.PDFReportService;
import com.jubination.service.XMLReportService;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ReportAPIController {
    
    @Autowired
    PDFReportService servicePDF;
    @Autowired
    XMLReportService serviceXML;
    
    @RequestMapping(value="/pdf/parser/{clinic}/{test}/{reportId}",method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE,headers="Accept=*/*")
    public   @ResponseBody ReportMessage startReportParsing(@RequestBody ReportMessage msg, HttpServletRequest request,@PathVariable("clinic") String clinic,@PathVariable("test") String test,@PathVariable("reportId") String reportId,Principal principal) {
            if(clinic.equalsIgnoreCase("thyrocare")){   
                if(test.equalsIgnoreCase("blood")){  
                    try{
                        System.out.println("Thyrocare Blood");
                        if(serviceXML.parseXMLToTextThyrocreBlood(msg.getReportXml(),reportId)){
                            System.out.println("XML parsing Done");
                            msg.setBody("Success");
                             return msg;
                        }
                        else if(servicePDF.parsePDFToTextThyrocreBlood(msg.getReportUrl(),reportId)){
                             msg.setBody("Success");
                            System.out.println("PDF parsing Done");
                             return msg;
                         }
                    }
                    catch(Exception e){
                         msg.setBody("Error "+e.toString());
                         e.printStackTrace();
                         return msg;
                     }
                }
            }
            msg.setBody("No proper Value");
            return msg;
}
    
    @RequestMapping(value="/report/{reportId}",method=RequestMethod.GET,headers="Accept=*/*")
    public ModelAndView  getReportView(HttpServletRequest request,@PathVariable("reportId") String reportId,Principal principal) {
            ModelAndView model=new ModelAndView("reportView");
            Report report=servicePDF.getReport(reportId);
            model.addObject("report",report );
            return model;
     }
    
    @RequestMapping(value="/report/transform/{reportId}",method=RequestMethod.GET,headers="Accept=*/*")
    public ModelAndView  getReportViewToTransform(HttpServletRequest request,@PathVariable("reportId") String reportId,Principal principal) {
            ModelAndView model=new ModelAndView("reportTransform");
            Report report=servicePDF.getReport(reportId);
            model.addObject("report",report );
            return model;
     }
  
    @RequestMapping(value="/report/json/{reportId}",method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, headers="Accept=*/*")
    public @ResponseBody Report  getReportViewJson(HttpServletRequest request,@PathVariable("reportId") String reportId,Principal principal) {
            Report report=servicePDF.getReport(reportId);
            for(Barcode b:report.getBarcodes()){
                b.setReport(new Report());
            }
            for(Profile profile:report.getProfileData()){
                profile.setReport(new Report());
                for(Test test:profile.getTestData()){
                    test.setProfile(new Profile());
                    for(ReferenceRange ref:test.getRangeValues()){
                        ref.setTest(new Test());
                    }
                }
            }
            return report;
    }
    
     @RequestMapping(value="/report/pdf/{reportId}",method=RequestMethod.POST,  headers="Accept=*/*")
    public ResponseEntity  getReportViewPdf(HttpServletRequest request,@PathVariable("reportId") String reportId,Principal principal) {
            if(servicePDF.getPdf(reportId)){
                return new ResponseEntity(HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    
}
