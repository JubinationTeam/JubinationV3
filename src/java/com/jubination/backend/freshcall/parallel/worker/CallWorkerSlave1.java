/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.freshcall.parallel.worker;

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
    
      
      
          void work(){
                          if(!manager.getClientStage1().empty()){
                              
                             Client client=manager.getClientStage1().pop();
                             
                             worker2.work(readAndSaveMessage(makeCall(client), client));
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
                                                formparams.add(new BasicNameValuePair("CallerId","02233835916"));
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
          
          
          private Client readAndSaveMessage(ExotelMessage eMessage, Client client){
                 try{
                          
                             String callerId=client.getPhoneNumber();
                                
                             Call message=null;
                                boolean passOn=false;
                                if(eMessage!=null){
                                        System.out.println(Thread.currentThread().getName()+" "+"Stage 1:xml message not null");
                                        if(eMessage.getSuccessMessage()!=null){
                                                System.out.println(Thread.currentThread().getName()+" "+"Stage 1:xml message success");
                                                message=eMessage.getSuccessMessage();
                                                message.setTrackStatus("Call request sent");
                                                message.setMessage("Stage 1 Calling");
                                                message.setCallTo(callerId);
                                                //adding all the sid values of calls placed
                                                passOn=true;
                                         }
                                        else if (eMessage.getFailureMessage()!=null){
                                                System.out.println(Thread.currentThread().getName()+" "+"Stage 1:xml message failed");
                                                 message=eMessage.getFailureMessage();
                                                 message.setCallTo(callerId);
                                                 message.setDateCreated(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                                                 message.setTrackStatus(message.getMessage());
                                        }
                                        else{
                                                System.out.println(Thread.currentThread().getName()+" "+"Stage 1:xml message unknown error");
                                                message.setCallTo(callerId);
                                                message.setTrackStatus("Unknown Error");
                                                message.setDateCreated(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                                        }
                                    }
                                    System.out.println(Thread.currentThread().getName()+" "+"Stage 1:adding message to database");
                                    
                                    Lead lead=client.getLead().get(client.getLead().size()-1);
                                    lead.setCount(lead.getCount()-1);
                                    if(lead.getCount()==0){
                                       lead.setPending(false);
                                    lead.setNotification(false);
                                       List<Lead> leadList=service.getDuplicateLeads(client.getPhoneNumber());
                                            for(Lead l:leadList){
                                                            
                                                            l.setNotification(false);
                                                            l.setPending(false);
                                                            l.setCount(0);
                                                            service.updateLeadOnly(l);
                                            }
                                    }
                                    
                                    client.setOvernight(false);
                                    
                                    if(service.addClientCall(client,lead,message)){
                                        
                                        if(passOn){
                                                                                     client.getLead().get(client.getLead().size()-1).getCall().add(message);
                                                                                     
                                                                                     manager.getClientStage2().offer(client);
                                        }
//                                        if(!manager.getClientStage1().empty()){
//                                                   manager.getClientStage1().pop();
//                                        }
                                    }
                                    System.out.println(Thread.currentThread().getName()+" "+"Stage 1:Call out of queue");
                          
                            }
                            catch(Exception e){
                                if(!manager.getClientStage1().empty()){
//                                    client=manager.getClientStage1().pop();
                                }
                                    client.setTempLeadDetails(client.getTempLeadDetails()+"|Error");
                                    service.saveTemporaryClient(client);
                                    e.printStackTrace();
                            } 
                 return client;
          }
          
}
