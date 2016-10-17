/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.freshcall.parallel.worker;

import com.jubination.backend.EmailService;
import com.jubination.backend.call.CallBox;
import com.jubination.backend.call.ExotelMessage;
import com.jubination.backend.freshcall.parallel.master.CallManager;
import com.jubination.model.pojo.Call;
import com.jubination.model.pojo.Client;
import com.jubination.model.pojo.Lead;
import com.jubination.service.CallMaintainService;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 *
 * @author MumbaiZone
 */
@Component
@Scope("prototype")
public class CallWorker implements Runnable{
    
    @Autowired
    CallWorkerSlave1 slave1;
      @Autowired
     private CallWorkerSlave3Leftover slave3Leftover;
      @Autowired
      private CallManager manager;
      
   private  boolean status;
    
    
    @Override
    public void run() {
                    try{
                           slave1.work();
                           if(manager.getClientStage1().isEmpty()&&manager.getClientStage2().isEmpty()&&!manager.getStageThreeUpdates().isEmpty()){
                                        slave3Leftover.work();
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
