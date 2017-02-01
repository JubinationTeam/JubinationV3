/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.service.exotel.leadcall.parallel.worker;

import com.jubination.backend.pojo.exotel.ExotelMessage;
import com.jubination.backend.service.sendgrid.EmailService;
import com.jubination.backend.service.core.leadcall.parallel.master.CallManager;
import com.jubination.model.pojo.admin.AdminSettings;
import com.jubination.model.pojo.exotel.Call;
import com.jubination.model.pojo.crm.Client;
import com.jubination.model.pojo.crm.Lead;
import com.jubination.service.AdminMaintainService;
import com.jubination.service.CallMaintainService;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class CallWorkerSlave1 {
    @Autowired
     private  CallMaintainService service;
    @Autowired
     private  CallManager manager;
    @Autowired
     private CallWorkerSlave2 worker2;
    @Autowired
    private AdminMaintainService adminService;
    
    private static final String settings="settings";
    
      
      
        public  void work(){
            try{
                          if(!manager.getClientStage1().empty()){
                              
                             Client client=manager.getClientStage1().pop();
                             
                             worker2.work(readAndSaveMessage(makeCall(client), client));
                       }
            }
            catch(Exception e){
                System.out.println("Error @ outer work Slave 1");
                e.printStackTrace();
            }
            finally{
            }
                   
                      
            }

          private ExotelMessage makeCall(Client client){
              ExotelMessage eMessage=null;
              try{
                    String callerId=client.getPhoneNumber();
                    String responseText="NA";
                    Document doc=null;
                    if(callerId!=null){
                            CloseableHttpResponse response=null;
                            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                            DocumentBuilder builder;
                            InputSource is;
                            try { 
                                    //requesting exotel to initiate call
                                    CloseableHttpClient httpclient = HttpClients.createDefault();
                                    HttpPost httpPost = new HttpPost("https://jubination:ce5e307d58d8ec07c8d8456e42ed171ff8322fd0@twilix.exotel.in/v1/Accounts/jubination/Calls/connect");
                                    List<NameValuePair> formparams = new ArrayList<>();
                                    formparams.add(new BasicNameValuePair("From",callerId));
                                   // formparams.add(new BasicNameValuePair("To",callerId));
                                    formparams.add(new BasicNameValuePair("CallerId","02239698495"));
                                    formparams.add(new BasicNameValuePair("CallerType","trans"));
                                    formparams.add(new BasicNameValuePair("Url","http://my.exotel.in/exoml/start/102261"));
                                    formparams.add(new BasicNameValuePair("TimeLimit","1800"));
                                    formparams.add(new BasicNameValuePair("TimeOut","30"));
                                    formparams.add(new BasicNameValuePair("SatusCallback",""));
                                    formparams.add(new BasicNameValuePair("CustomField","testcall"));
                                    UrlEncodedFormEntity uEntity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
                                    httpPost.setEntity(uEntity);
                                    response = httpclient.execute(httpPost);
                                    System.out.println(Thread.currentThread().getName()+" "+"Stage 1:Calls sent to exotel");
                                    HttpEntity entity = response.getEntity();

                                    responseText = EntityUtils.toString(entity, "UTF-8");
                                    builder = factory.newDocumentBuilder();
                                    is = new InputSource(new StringReader(responseText));
                                    doc = builder.parse(is);
                                    doc.getDocumentElement().normalize();
                            } 
                            catch(IOException | ParseException | ParserConfigurationException | SAXException | DOMException e){
                                    e.printStackTrace();
                            }
                            finally {
                                    if(response!=null){
                                            response.close();
                                    }
                           }
                    }
                    //parsing xml response from exotel
                    JAXBContext jaxbContext = JAXBContext.newInstance(ExotelMessage.class);
                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    eMessage = (ExotelMessage) jaxbUnmarshaller.unmarshal(doc);
                    System.out.println(Thread.currentThread().getName()+" "+"Stage 1:Got xml message");
              }
              catch(IOException | JAXBException e){
                  e.printStackTrace();
              }
          
           return eMessage;
          }
          
          
          private Client readAndSaveMessage(ExotelMessage eMessage, Client client) {
              boolean saved=false;
              boolean leadsAttached=false;
            if(client!=null&&client.getLead()!=null&&client.getLead().size()>0){
              Lead lead=client.getLead().get(client.getLead().size()-1);
              lead.setLastCallingThread(Thread.currentThread().getName());
              service.updateLeadOnly(lead);
                 try{
                          
                     //exotel message to call details
                             String callerId=client.getPhoneNumber();
                             Call call=null;
                                boolean passOn=false;
                                if(eMessage!=null){
                                        System.out.println(Thread.currentThread().getName()+" "+"Stage 1:xml message not null");
                                        if(eMessage.getSuccessMessage()!=null){
                                                System.out.println(Thread.currentThread().getName()+" "+"Stage 1:xml message success");
                                                call=eMessage.getSuccessMessage();
                                                call.setTrackStatus("Call request sent");
                                                call.setMessage("Stage 1 Calling");
                                                call.setCallTo(callerId);
                                                //adding all the sid values of calls placed
                                                passOn=true;
                                         }
                                        else if (eMessage.getFailureMessage()!=null){
                                                System.out.println(Thread.currentThread().getName()+" "+"Stage 1:xml message failed");
                                                 call=eMessage.getFailureMessage();
                                                 call.setCallTo(callerId);
                                                 call.setDateCreated(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                                                 call.setTrackStatus(call.getMessage());
                                                 sendEmailToFailCall("disha@jubination.com", client.getTempLeadDetails(), client.getPhoneNumber());
                                                 sendEmailToFailCall("trupti@jubination.com", client.getTempLeadDetails(), client.getPhoneNumber());
                                                 sendEmailToFailCall("vinay@jubination.com", client.getTempLeadDetails(), client.getPhoneNumber());
                                                 sendEmailToFailCall("tauseef@jubination.com", client.getTempLeadDetails(), client.getPhoneNumber());
                                                 sendEmailToFailCall("souvik@jubination.com", client.getTempLeadDetails(), client.getPhoneNumber());
                                        }
                                        else{
                                                System.out.println(Thread.currentThread().getName()+" "+"Stage 1:xml message unknown error");
                                                call.setCallTo(callerId);
                                                call.setTrackStatus("Call details not accepted by Exotel");
                                                call.setDateCreated(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                                        }
                                    }
                                //Settings to make objects ready
                                 client.setOvernight(false);
                                 
                                 
                                leadsAttached=true;
                                lead.setCount(lead.getCount()-1);
                                    
                                    //If count zero, make all the leads count zero
                                        if(lead.getCount()==0){
                                            lead.setPending(false);
                                            lead.setNotification(false);
                                            if(lead.getLastCallingThread().equals(Thread.currentThread().getName())){
                                                
                                                List<Lead> leadList=service.getDuplicateLeads(client.getPhoneNumber());
                                                     for(Lead l:leadList){
                                                                     l.setNotification(false);
                                                                     l.setPending(false);
                                                                     l.setCount(0);
                                                                     service.updateLeadOnly(l);
                                                     }
                                                }
                                            else{
                                                sendTestEmail("Stage 1 line 213");
                                            }
                                            }
                                            
                                            //Try Saving to database 10 times
                                            System.out.println(Thread.currentThread().getName()+" "+"Stage 1:adding message to database");

                                            int count=0;

                                            while(!saved&&count<10){
                                                        try{
                                                            
                                                            if(lead.getLastCallingThread().equals(Thread.currentThread().getName())){
                                                                    saved=service.addClientAndUnmarkBackupClient(client, lead, call);
                                                                    if(passOn){
                                                                                    lead.getCall().add(call);
                                                                                    manager.getClientStage2().offer(client);
                                                                     }
                                                            }else{
                                                                sendTestEmail("Stage 1 line 230");
                                                            }
                                                        }
                                                        catch(Exception e){
                                                            sendTestEmail("Stage 1 line 234"+e.toString());
                                                            if(lead!=null){
                                                                System.out.println("Tried saving client to database "+count+"th time "+lead.getLeadId()+"::::::::::::::::::::::%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                                                            }
                                                            e.printStackTrace();
                                                            
                                                            Thread.sleep(300);

                                                        }    
                                                            count++;
                                            }
                                            System.out.println(Thread.currentThread().getName()+" "+"Stage 1:Call out of queue");
                                            
                                           
                                     
                          
                            }
                            catch(Exception e){
                                    sendTestEmail("Stage 1 line 251"+e.toString());
                                    if(!saved){
                                        
                                            if(lead.getLastCallingThread().equals(Thread.currentThread().getName())){
                                               client.setTempLeadDetails(client.getTempLeadDetails()+"|Error");
                                               manager.setExecutives(manager.getExecutives()-1, "CALLBOT");
                                            }
                                            else{
                                                sendTestEmail("Stage 1 line 254"+"not saved @ database");
                                            }
                                           
                                    }
                                    
                                            System.err.println("Failed to save object to database in Stage 1::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                                     e.printStackTrace();
                                            
                            } 
            }
            else{
                System.err.println("NO LEAD ATTACHED::::::::::::::::::::::::::::::::::::::::::::::::: Slave1");
            }
                 return client;
          }
          
          private void sendTestEmail(String text){
           AdminSettings adminSettings = adminService.readSettings(settings);
            new EmailService("souvik@jubination.com",text,
                                          text+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),adminSettings.getMyUsername(),
                    adminSettings.getMyPassword(),
                    adminSettings.getAuth(),
                    adminSettings.getStarttls(),
                    adminSettings.getHost(),adminSettings.getPort(),adminSettings.getSendgridApi()).start();
     }
          
          
          private void sendEmailToFailCall(String email,String leadId,String number){
           AdminSettings adminSettings = adminService.readSettings(settings);
            new EmailService(email,"Calls failed",
                                          "Hi,<br/>" +
                                                "<br/>" +
                                                "I am call Bot!<br/>" +
                                                "<br/>" +
                                                "Exotel has failed to call the following lead/number" +
                                                "<br/>" +
                                                "Lead "+leadId+", number "+number+
                                                "<br/>" +
                                                "<br/>" +
                                                "Wish you a happy & healthy day!<br/>" +
                                                "<br/>" +
                                                "<br/>" +
                                                "Regards,<br/>" + 
                                                "Call Bot ",adminSettings.getMyUsername(),adminSettings.getMyPassword(),adminSettings.getAuth(),adminSettings.getStarttls(),adminSettings.getHost(),adminSettings.getPort(),adminSettings.getSendgridApi()).start();
     }
          
}
