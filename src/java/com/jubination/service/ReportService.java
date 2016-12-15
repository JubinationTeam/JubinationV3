/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.service;

import com.jubination.backend.service.report.parallel.worker.thyrocare.PDFBackendProcess;
import com.jubination.backend.service.report.parallel.worker.ReportOperator;
import com.jubination.model.dao.ReportDAOImpl;
import com.jubination.model.pojo.report.Profile;
import com.jubination.model.pojo.report.ReferenceRange;
import com.jubination.model.pojo.report.Report;
import com.jubination.model.pojo.report.Test;
import com.pdfcrowd.Client;
import com.pdfcrowd.PdfcrowdError;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReportService {
    
    @Autowired
    ReportDAOImpl reportDAO;

  
     
     //SAVE TO DATABASE
                           public  void buildReport(Report report){
                                           
                                            reportDAO.buildEntity(report);
                                            
                           }
                           
       public Report getReport(String reportId){
           
           return (Report) reportDAO.fetchEntity(new Report(reportId));
       } 
                          ////////////////
 
    
    
 
   
 //Save Pdf////////////////////////////
    public boolean getPdf(String reportId){
        URL url;
        String ExtraParams="";
        String htmlUrl="http://162.246.21.98/jubination/report/transform/"+reportId;
          String filename="C:\\Users\\Administrator\\Documents\\NetBeansProjects\\Jubination\\web\\custompdf\\"+reportId+".pdf";
        String BaseURL="http://api.html2pdfrocket.com/pdf";
        String APIKey="3f108b87-7662-4fb3-b763-d03a7533bae8";
        String Params="";
        try {
            if(ExtraParams!=null&&!"".equals(ExtraParams)){
                Params=ExtraParams;
                if(!Params.substring(0,1).equals("&")){
                    Params="&"+Params;
                }
            }
             
            htmlUrl=URLEncoder.encode(htmlUrl,java.nio.charset.StandardCharsets.UTF_8.toString() );
            htmlUrl+=Params;
 
            // Append parameters for API call
            url = new URL(BaseURL+"?apikey="+APIKey+"&value="+htmlUrl);
         
            // Download PDF file
            URLConnection connection = url.openConnection();
            InputStream Instream = connection.getInputStream();
 
            // Write PDF file
            File f = new File(filename);
            if(!f.createNewFile()){
                return false;
            }
            BufferedInputStream BISin = new BufferedInputStream(Instream);
            FileOutputStream FOSfile = new FileOutputStream(filename);
            BufferedOutputStream out = new BufferedOutputStream(FOSfile);
             
            int i;
            while ((i = BISin.read()) != -1) {
                out.write(i);
            }
 
            // Clean up
            out.flush();
            out.close();
            System.out.println("File "+filename+" created");
            return true;
 
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    /////////////////////////////////////////////
    
    

}
