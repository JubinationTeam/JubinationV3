//
//package com.jubination.backend.call;
//
//
//import com.jubination.backend.EmailService;
//import com.jubination.model.pojo.Call;
//import com.jubination.model.pojo.Client;
//import com.jubination.model.pojo.Lead;
//import com.jubination.service.CallMaintainService;
//import java.io.IOException;
//import java.io.StringReader;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Stack;
//import java.util.concurrent.ConcurrentLinkedQueue;
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Unmarshaller;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import org.apache.http.Consts;
//import org.apache.http.HttpEntity;
//import org.apache.http.NameValuePair;
//import org.apache.http.ParseException;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.w3c.dom.DOMException;
//import org.w3c.dom.Document;
//import org.xml.sax.InputSource;
//import org.xml.sax.SAXException;
//
///**
// *
// * @author Welcome
// */
//@Component
//public class CallBoxIntegrated {
//                    private boolean flag1=false;
//                    private boolean flag2=false;
//                    private int count = 0;
//                    private boolean realtimeFlag=false;
////                    private Comparator<Client> comparator = new  Comparator<Client>() {
////                        @Override
////                        public int compare(Client x, Client y) {
////                            if (x.getPriority() > y.getPriority())
////                            {
////                                return 1;
////                            }
////                            
////                            if (x.getPriority() < y.getPriority())
////                            {
////                                return -1;
////                            }
////                           
////                            return 0;
////                        }
////                    };
////                    private PriorityBlockingQueue<Client> clientStage1 = new PriorityBlockingQueue<>(10, comparator);
//                    private Stack<Client> clientStage1 = new Stack<>();
//                    private ConcurrentLinkedQueue<Client> clientStage2 = new ConcurrentLinkedQueue<>();
//                    private Stack<Call> messageStage3 = new Stack<>();
//                    
//                    private Stack<Client> realTimeInCall = new Stack<>();
//                    
//                    private static final long time_1=15000;
//                    private static final long time_2=2500;
//                    private static final long time_3=2500;
//                    private boolean stage1=true;
//                    private boolean stage2=true;
//                    private boolean stage3=true;
//                    private int executives=1;
//
//                    
//                    
//                    @Autowired
//                    private CallMaintainService service;
//                     @Autowired
//                    private CallBox callBox; 
//                   
//
//                       
//                     
//                    @Async
//                    @Scheduled(fixedRate=time_1)
//                    void callDicyCustomer() throws IOException,InterruptedException, JAXBException{
//                        if(stage1){
//                            try{
//                                            Long startTime=System.currentTimeMillis();
//                                            if(isFlag1()&&!clientStage1.isEmpty()&&clientStage2.size()<getExecutives()){
//                                                
//                                                                    Client client=clientStage1.peek();
//                                                                    String callerId=client.getPhoneNumber();
//                                                                    String responseText="NA";
//                                                                    Document doc=null;
//                                                                    System.out.println("Stage 1:"+clientStage1.size()+" Calls in queue to be sent to exotel to process "+Thread.currentThread());
//                                                                    if(callerId!=null){
//                                                                                            CloseableHttpResponse response=null;
//                                                                                            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//                                                                                            DocumentBuilder builder;
//                                                                                            InputSource is;
//                                                                                            try { 
//                                                                                                                        //requesting exotel to initiate call
//                                                                                                                        CloseableHttpClient httpclient = HttpClients.createDefault();
//                                                                                                                        HttpPost httpPost = new HttpPost("https://jubination:ce5e307d58d8ec07c8d8456e42ed171ff8322fd0@twilix.exotel.in/v1/Accounts/jubination/Calls/connect");
//                                                                                                                        List<NameValuePair> formparams = new ArrayList<>();
//                                                                                                                        formparams.add(new BasicNameValuePair("From",callerId));
//                                                                                                                       // formparams.add(new BasicNameValuePair("To",callerId));
//                                                                                                                        formparams.add(new BasicNameValuePair("CallerId","02233835916"));
//                                                                                                                        formparams.add(new BasicNameValuePair("CallerType","trans"));
//                                                                                                                        formparams.add(new BasicNameValuePair("Url","http://my.exotel.in/exoml/start/102261"));
//                                                                                                                        formparams.add(new BasicNameValuePair("TimeLimit","1800"));
//                                                                                                                        formparams.add(new BasicNameValuePair("TimeOut","30"));
//                                                                                                                        formparams.add(new BasicNameValuePair("SatusCallback",""));
//                                                                                                                        formparams.add(new BasicNameValuePair("CustomField","testcall"));
//                                                                                                                        UrlEncodedFormEntity uEntity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
//                                                                                                                        httpPost.setEntity(uEntity);
//                                                                                                                        response = httpclient.execute(httpPost);
//                                                                                                                        System.out.println("Stage 1:Calls sent to exotel");
//                                                                                                                        HttpEntity entity = response.getEntity();
//
//                                                                                                                        responseText = EntityUtils.toString(entity, "UTF-8");
//                                                                                                                        builder = factory.newDocumentBuilder();
//                                                                                                                        is = new InputSource(new StringReader(responseText));
//                                                                                                                        doc = builder.parse(is);
//                                                                                                                        doc.getDocumentElement().normalize();
//                                                                                            } 
//                                                                                            catch(IOException | ParseException | ParserConfigurationException | SAXException | DOMException e){
//                                                                                                                        e.printStackTrace();
//                                                                                            }
//                                                                                            finally {
//                                                                                                                        if(response!=null){
//                                                                                                                                                response.close();
//                                                                                                                        }
//                                                                                           }
//                                                                    }
//                                                                    //parsing xml response from exotel
//                                                                    JAXBContext jaxbContext = JAXBContext.newInstance(ExotelMessage.class);
//                                                                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//                                                                    ExotelMessage eMessage = (ExotelMessage) jaxbUnmarshaller.unmarshal(doc);
//                                                                    Call message=null;
//                                                                    System.out.println("Stage 1:Got xml message");
//                                                                    boolean passOn=false;
//                                                                    if(eMessage!=null){
//                                                                                            System.out.println("Stage 1:xml message not null");
//                                                                                            if(eMessage.getSuccessMessage()!=null){
//                                                                                                                System.out.println("Stage 1:xml message success");
//                                                                                                                message=eMessage.getSuccessMessage();
//                                                                                                                message.setTrackStatus("Call request sent");
//                                                                                                                message.setMessage("Stage 1 Calling");
//                                                                                                                message.setCallTo(callerId);
//                                                                                                                //adding all the sid values of calls placed
//                                                                                                                passOn=true;
//                                                                                             }
//                                                                                            else if (eMessage.getFailureMessage()!=null){
//                                                                                                               System.out.println("Stage 1:xml message failed");
//                                                                                                                message=eMessage.getFailureMessage();
//                                                                                                                message.setCallTo(callerId);
//                                                                                                                message.setDateCreated(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//                                                                                                                message.setTrackStatus(message.getMessage());
//                                                                                            }
//                                                                                            else{
//                                                                                                                System.out.println("Stage 1:xml message unknown error");
//                                                                                                                message.setCallTo(callerId);
//                                                                                                                message.setTrackStatus("Unknown Error");
//                                                                                                                message.setDateCreated(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//                                                                                            }
//                                                                    }
//                                                                    System.out.println("Stage 1:adding message to database");
//                                                                   
//                                                                    //saving call details
//                                                                    //passing on number
////                                                                     if(client.getLead().get(client.getLead().size()-1).getCount()==0){
////                                                                                                                                        client.getLead().get(client.getLead().size()-1).setPending(false);
////                                                                       }
////                                                                    if(client.getLead().get(client.getLead().size()-1).isPending()){
//                                                                        client.getLead().get(client.getLead().size()-1).setCount(client.getLead().get(client.getLead().size()-1).getCount()-1);
//                                                                        if(client.getLead().get(client.getLead().size()-1).getCount()==0){
//                                                                            client.getLead().get(client.getLead().size()-1).setPending(false);
//                                                                        }
//                                                                        client.setOvernight(false);
//                                                                        client.getLead().get(client.getLead().size()-1).setNotification(false);
//                                                                            if(service.addClientCall(client,client.getLead().get(client.getLead().size()-1),message)){
//                                                                                if(passOn){
//                                                                                                                             client.getLead().get(client.getLead().size()-1).getCall().add(message);
//                                                                                                                            clientStage2.offer(client);
//                                                                                }
//                                                                                clientStage1.pop();
//                                                                            }
////                                                                   }
//                                                                
//                                                                    
//                                                                    System.out.println("Stage 1:Call out of queue");
//                                                                    setFlag1(!clientStage1.isEmpty());
//                                                                    
//                                                                    if(!isFlag1()){
//                                                                        //initiate call status update after call are over
//                                                                        flag2=true;
//                                                                    }
//                                            Long endTime=System.currentTimeMillis();
//                                            Long timeTaken=endTime-startTime;
//                                            float tt=timeTaken/1000;
//                                            System.out.println("Time Taken "+tt+" seconds");
//                                            }
//                            }
//                            catch(Exception e){
//                                Client client=clientStage1.pop();
//                                client.setTempLeadDetails(client.getTempLeadDetails()+"|Error");
//                                service.saveTemporaryClient(client);
//                                e.printStackTrace();
//                            }
//                    }
//                    }
//
//   
//                    @Async
//                    @Scheduled(fixedDelay=time_2)
//                    void checkCalledCustomers() throws IOException, JAXBException{
//                        if(stage2){
//                                        Long startTime=System.currentTimeMillis();
//                                        if((isFlag2()&&!isFlag1()&&!clientStage2.isEmpty())||clientStage2.size()>=getExecutives()){
//                                                            String sid=null;
//                                                            Client client =null;
//                                                            String responseText="NA";
//                                                            Document doc=null;
//                                                            System.out.println("Stage 2:"+clientStage2.size()+" Sids in queue to be sent to exotel to process "+Thread.currentThread());
//                                                                                //fetching all the sid values
//                                                                                try{
//                                                                                client=clientStage2.peek();
//                                                                                sid=client.getLead().get(client.getLead().size()-1).getCall().get(client.getLead().get(client.getLead().size()-1).getCall().size()-1).getSid();
//                                                                                }
//                                                                                catch(Exception e){
//                                                                                    if(client!=null){
//                                                                                        e.printStackTrace();
//                                                                                    System.out.println("Problem in "+client.getTempLeadDetails());
//                                                                                    clientStage2.poll();
//                                                                                    return;
//                                                                                    }
//                                                                                }
//                                                                                CloseableHttpResponse response=null;
//                                                             DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//                                                             DocumentBuilder builder;
//                                                             InputSource is;
//                                                             try { 
//                                                                         //checking sid details with exotel
//                                                                         CloseableHttpClient httpclient = HttpClients.createDefault();
//                                                                         HttpGet httpGet = new HttpGet("https://jubination:ce5e307d58d8ec07c8d8456e42ed171ff8322fd0@twilix.exotel.in/v1/Accounts/jubination/Calls/"+sid);
//                                                                         response = httpclient.execute(httpGet);
//                                                                         System.out.println("Stage 2:Sid sent to exotel");
//                                                                         HttpEntity entity = response.getEntity();
//                                                                         responseText = EntityUtils.toString(entity, "UTF-8");
//                                                                         builder = factory.newDocumentBuilder();
//                                                                         is = new InputSource(new StringReader(responseText));
//                                                                         doc = builder.parse(is);
//                                                                         doc.getDocumentElement().normalize();
//                                                              } 
//                                                             catch(IOException | ParseException | ParserConfigurationException | SAXException | DOMException e){
//                                                                        e.printStackTrace();
//                                                              }
//                                                             finally {
//                                                                        if(response!=null){
//                                                                                            response.close();
//                                                                        }
//                                                              }
//                                                             //parsing xml response from exotel
//                                                             JAXBContext jaxbContext = JAXBContext.newInstance(ExotelMessage.class);
//                                                             Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//                                                             ExotelMessage eMessage = (ExotelMessage) jaxbUnmarshaller.unmarshal(doc);
//                                                             Call message=null;
//                                                             System.out.println("Stage 2:Got xml message");
//                                                             if(eMessage!=null){
//                                                                            System.out.println("Stage 2:xml message not null");
//                                                                            if(eMessage.getSuccessMessage()!=null){
//                                                                                            message=eMessage.getSuccessMessage();
//                                                                                            System.out.println("Stage 2:xml message success "+message.getStatus()+" "+message.getCallType()+" "+message.getDialCallDuration());
//                                                                                              
//                                                                                            if(message.getStatus().contains("queued")||message.getStatus().contains("ringing")||message.getStatus().contains("in-progress")){
//                                                                                                                        count++;
//                                                                                                                           if(count>3&&message.getStatus().contains("in-progress")&&message.getCallType().contains("trans")&&!realtimeFlag){
//                                                                                                                                        realTimeInCall.push(client);
//                                                                                                                                        realtimeFlag=true;
//                                                                                                                        }    
//                                                                                                                        System.out.println("Stage 2:sid out of queue. Trying for"+count+"th time");
//                                                                                                              
//                                                                                            }
//                                                                                            else {
//                                                                                                                        Call storedMessage=service.getCallRecordBySid(message.getSid());
//                                                                                                                        if(storedMessage!=null){
//                                                                                                                                                System.out.println("Stage 2:sid of the number was present already");
//                                                                                                                                                 message.setOrderId(storedMessage.getOrderId());
//                                                                                                                                                 message.setMessage("Stage 2 Tracking");
//                                                                                                                                                //updating call details
//                                                                                                                                                if(!storedMessage.getMessage().equals("Stage 3 Tracking")){
//                                                                                                                                                                            System.out.println("Stage 2:stage 3 not updated yet");
//                                                                                                                                                                            message.setCallTo(storedMessage.getCallTo());
//                                                                                                                                                                            //saving in database
//                                                                                                                                                                            message.setLead(client.getLead().get(client.getLead().size()-1));
//                                                                                                                                                                            if(message.getStatus().contains("busy")){
//                                                                                                                                                                                message.getLead().setLeadStatus("Busy");
//                                                                                                                                                                                service.updateLeadOnly(message.getLead());
//                                                                                                                                                                                if(client!=null){
//                                                                                                                                                                                    sendEmailToFailCall(client.getEmailId());
//                                                                                                                                                                                }
//                                                                                                                                                                                
//                                                                                                                                                                            }
//                                                                                                                                                                            else if(message.getStatus().contains("failed")){
//                                                                                                                                                                                message.getLead().setLeadStatus("Failed");
//                                                                                                                                                                                service.updateLeadOnly(message.getLead());
//                                                                                                                                                                                 if(client!=null){
//                                                                                                                                                                                    sendEmailToFailCall(client.getEmailId());
//                                                                                                                                                                                }
//                                                                                                                                                                            }
//                                                                                                                                                                            else if(message.getStatus().contains("no-answer")){
//                                                                                                                                                                                message.getLead().setLeadStatus("No Answer");
//                                                                                                                                                                                service.updateLeadOnly(message.getLead());
//                                                                                                                                                                                 if(client!=null){
//                                                                                                                                                                                    sendEmailToFailCall(client.getEmailId());
//                                                                                                                                                                                }
//                                                                                                                                                                            }
////                                                                                                                                                                            else if(message.getStatus().contains("completed")&&message.getCallType().contains("trans")){
////                                                                                                                                                                                message.getLead().setLeadStatus("Hanged up while greetings");
////                                                                                                                                                                                service.updateLeadOnly(message.getLead());
////                                                                                                                                                                                 if(client!=null){
////                                                                                                                                                                                    sendEmailToFailCall(client.getEmailId());
////                                                                                                                                                                                }
////                                                                                                                                                                            }
////                                                                                                                                                                            
////                                                                                                                                                                            else{
////                                                                                                                                                                                message.getLead().setLeadStatus(message.getStatus()+"|"+message.getTrackStatus()+"|"+message.getCallType());
////                                                                                                                                                                                service.updateLeadOnly(message.getLead());
////                                                                                                                                                                                
////                                                                                                                                                                            }
//                                                                                                                                               
////                                                                                                                                                                            
//                                                                                                                                                                            service.updateCallAPIMessage(message);
//                                                                                                                                                                            
//                                                                                                                                                  }
//                                                                                                                                                  else{
//                                                                                                                                                                            System.out.println("Stage 2:stage 3 updated already");
//                                                                                                                                                                            storedMessage.setStatus(message.getStatus());
//                                                                                                                                                                            storedMessage.setCallType(message.getCallType());
//                                                                                                                                                                            storedMessage.setRecordingUrl(message.getRecordingUrl());
//                                                                                                                                                                            //saving in database
//                                                                                                                                                                            message.setLead(client.getLead().get(client.getLead().size()-1));
//                                                                                                                                                                            if(message.getStatus().contains("busy")){
//                                                                                                                                                                                message.getLead().setLeadStatus("Busy");
//                                                                                                                                                                                service.updateLeadOnly(message.getLead());
//                                                                                                                                                                                if(client!=null){
//                                                                                                                                                                                    sendEmailToFailCall(client.getEmailId());
//                                                                                                                                                                                }
//                                                                                                                                                                            }
//                                                                                                                                                                            else if(message.getStatus().contains("failed")){
//                                                                                                                                                                                message.getLead().setLeadStatus("Failed");
//                                                                                                                                                                                service.updateLeadOnly(message.getLead());
//                                                                                                                                                                                if(client!=null){
//                                                                                                                                                                                    sendEmailToFailCall(client.getEmailId());
//                                                                                                                                                                                }
//                                                                                                                                                                            }
//                                                                                                                                                                            else if(message.getStatus().contains("no-answer")){
//                                                                                                                                                                                message.getLead().setLeadStatus("No Answer");
//                                                                                                                                                                                service.updateLeadOnly(message.getLead());
//                                                                                                                                                                                if(client!=null){
//                                                                                                                                                                                    sendEmailToFailCall(client.getEmailId());
//                                                                                                                                                                                }
//                                                                                                                                                                            }
//                                                                                                                                                                             
//                                                                                                                                                                            service.updateCallAPIMessage(storedMessage);
//                                                                                                                                                   }
//                                                                                                                        }
//                                                                                                                                                 //removing all the sid values
//                                                                                                                                                clientStage2.poll();
//                                                                                                                                                count=0;
//                                                                                                                                                realtimeFlag=false;
//                                                                                                                                                System.out.println("Stage 2:Sid out of queue");
//                                                                                            }
//                                                                                            if(count>=300){
//                                                                                                                        
//                                                                                                                        System.out.println("Stage 2:Time out. sid out of queue. Trying for next sid");
//                                                                                                                        clientStage2.poll();
//                                                                                                                        count=0;
//                                                                                                                        realtimeFlag=false;
//                                                                                            }
//
//                                                                            }
//                                                             }
//                                                             //stop operation if all done
//                                                             flag2=!clientStage2.isEmpty();
////                                                              if(!flag2){
////                                                                        //initiate call status update after call are over
////                                                                        flag3=true;
////                                                                    }
//                                                              Long endTime=System.currentTimeMillis();
//                                            Long timeTaken=endTime-startTime;
//                                            float tt= timeTaken/1000;
//                                            System.out.println("Time Taken "+tt+" seconds");
//                                        }
//                                                                  
//                    }
//                    }
//
//@Async
//                    @Scheduled(fixedDelay=time_3)
//                    void checkStageThreeCustomers() throws IOException, JAXBException{
//                        if(stage3){
//                       Long startTime=System.currentTimeMillis();
//                        if(!callBox.isFlag()&&!callBox.isCheckFlag()&&!isFlag1()&&!isFlag2()&&!messageStage3.isEmpty()){
//                                             //fetching all the call values
//                                                        Call callUpdated= messageStage3.peek();
//                                                                           
//                                                                           
//                                                        if(callUpdated!=null){
//                                                                    List<Call> callList=service.getCallBySid(callUpdated.getSid());
//                                                                    if(callList!=null&&!callList.isEmpty()){
//                                                                                        Call  call=callList.get(0);
//                                                                                          System.out.println("Stage 3:"+messageStage3.size()+"left. Update service started");
//                                                                                         if(call!=null){
//                                                                                                             System.out.println("Stage 3:Call message present in database already and not null");
//                                                                                                             call.setMessage("Stage 3 Tracking");
//                                                                                                             call.setStatus("completed");
//                                                                                                             call.setDialCallDuration(callUpdated.getDialCallDuration());
//                                                                                                             call.setCallType(callUpdated.getCallType());
//                                                                                                             call.setTrackStatus(callUpdated.getTrackStatus());
//                                                                                                             call.setDialWhomNumber(callUpdated.getDialWhomNumber());
//                                                                                                             call.setRecordingUrl(callUpdated.getRecordingUrl());
//                                                                                                             call.setStartTime(callUpdated.getStartTime());
//                                                                                                             call.setDirection(callUpdated.getDirection());
//                                                                                                 
//                                                                                                             System.out.println("Stage 3:Updating database properties");
//                                                                                                             //saving to database
//                                                                                                             List<Client> clientList=service.getClientsByPhoneNumber(call.getCallTo());
//                                                                                                             if(clientList!=null&&!clientList.isEmpty()){
//                                                                                                             for(Client client:clientList){
//                                                                                                                client=service.getClientDetailsWithList(client);
//                                                                                                                
//                                                                                                                Lead lead=client.getLead().get(client.getLead().size()-1);
//                                                                                                                if(call.getTrackStatus().contains("did not speak")){
//                                                                                                                            if(call.getCallType().contains("client-hangup")){
//                                                                                                                                lead.setLeadStatus("Hanged up while connecting");
//                                                                                                                                if(client!=null){
//                                                                                                                                    sendEmailToFailCall(client.getEmailId());
//                                                                                                                                }
//                                                                                                                            }
//                                                                                                                            else if(call.getCallType().contains("incomplete")){
//                                                                                                                                lead.setLeadStatus("We missed client's call");
//                                                                                                                            }
//                                                                                                                             else if(call.getCallType().contains("trans")){
//                                                                                                                                lead.setLeadStatus("Hanged up while greetings");
//                                                                                                                                        if(client!=null){
//                                                                                                                                            sendEmailToFailCall(client.getEmailId());
//                                                                                                                                        }
//                                                                                                                                }
//                                                                                                                            
//                                                                                                                            else{
//                                                                                                                                        lead.setLeadStatus(call.getStatus()+"|"+call.getTrackStatus()+"|"+call.getCallType());
//                                                                                                                                        if(client!=null){
//                                                                                                                                                sendEmailToFailCall(client.getEmailId());
//                                                                                                                                            }
//                                                                                                                            }
//                                                                                                                    
//                                                                                                                }
//                                                                                                                else if(call.getTrackStatus().contains("spoke")){
//                                                                                                                                if(lead==null||lead.getLeadStatus()==null){
//                                                                                                                                    sendEmailNotUpdated("disha@jubination.com",call.getCallFrom(),call.getDialWhomNumber());
//                                                                                                                                    sendEmailNotUpdated("trupti@jubination.com",call.getCallFrom(),call.getDialWhomNumber());
//                                                                                                                                    sendEmailNotUpdated("reshma@jubination.com",call.getCallFrom(),call.getDialWhomNumber());
//                                                                                                                                    sendEmailNotUpdated("tauseef@jubination.com",call.getCallFrom(),call.getDialWhomNumber());
//                                                                                                                                    sendEmailNotUpdated("vinay@jubination.com",call.getCallFrom(),call.getDialWhomNumber());
//                                                                                                                                    sendEmailNotUpdated("souvik@jubination.com",call.getCallFrom(),call.getDialWhomNumber());
//                                                                                                                                    
//                                                                                                                                }
//                                                                                                                                else if(lead.getLeadStatus().contains("Follow up/Call back")||
//                                                                                                                                        lead.getLeadStatus().contains("Lead sent to Thyrocare")||
//                                                                                                                                        lead.getLeadStatus().contains("Not interested")||
//                                                                                                                                        lead.getLeadStatus().contains("Not registered")||
//                                                                                                                                        lead.getLeadStatus().contains("Language not recognizable")||
//                                                                                                                                        lead.getLeadStatus().contains("No Service")||
//                                                                                                                                        lead.getLeadStatus().contains("Customer complained")||
//                                                                                                                                        lead.getLeadStatus().contains("Disapproved")){
//                                                                                                                                       lead.setLeadStatus(lead.getLeadStatus());
//                                                                                                                    
//                                                                                                                                }
//                                                                                                                                else if((lead.getLeadStatus()==null||lead.getLeadStatus().isEmpty())){
//
//                                                                                                                                       lead.setLeadStatus("Spoke but not updated");
//                                                                                                                                   }
//                                                                                                                                else{
//                                                                                                                                    lead.setLeadStatus(lead.getLeadStatus()+"|"+call.getStatus()+"|"+call.getTrackStatus()+"|"+call.getCallType());
//                                                                                                                                    
//                                                                                                                                }
//                                                                                                                                     client.getLead().get(client.getLead().size()-1).setPending(false);
//                                                                                                                                    client.getLead().get(client.getLead().size()-1).setCount(0);
//                                                                                                                }
//                                                                                                                else{
//                                                                                                                        lead.setLeadStatus(call.getStatus()+"|"+call.getTrackStatus()+"|"+call.getCallType());
//                                                                                                                }
//                                                                                                                service.updateLeadOnly(lead);
//                                                                                                                
//                                                                                                             call.setLead(client.getLead().get(client.getLead().size()-1));
//                                                                                                             service.updateCallAPIMessage(call);
//                                                                                                             }
//                                                                                                             }
//                                                                                                             else{
//                                                                                                                 service.updateCallAPIMessage(call);
//                                                                                                             }
//                                                                                                             
//                                                                                                            //removing all the call values
//                                                                                                            messageStage3.pop();
//                                                                                                            System.out.println("Stage 3:Call Message out of queue");
//                                                                                         }
//                                                                    }
//                                                                    else{
//                                                                                            System.out.println("Stage 3:Call message not present in database");
//                                                                                            callUpdated.setMessage("Incoming Tracking");
//                                                                                            //saving to databse
//                                                                                            List<Client> clientList=service.getClientsByPhoneNumber(callUpdated.getCallFrom());
//                                                                                            
//                                                                                            if(clientList==null||clientList.isEmpty()){
//                                                                                                System.out.println("Stage 3 : Client not present");
//                                                                                                    service.buildCallAPIMessage(callUpdated);
//                                                                                                    if(callUpdated.getTrackStatus().contains("requested for callback")){
//                                                                                                        System.out.println("Request Callback");
//                                                                                                                    callBox.getNumbers().push(callUpdated.getCallFrom());
//                                                                                                                    callBox.setFlag(true);
//                                                                                                 }
//                                                                                            }
//                                                                                            else{
//                                                                                                System.out.println("Stage 3 : Client present");
//                                                                                                for(Client client:clientList){
//                                                                                                    client=service.getClientDetailsWithList(client);
//                                                                                                    if(callUpdated.getTrackStatus().contains("spoke to us")){
//                                                                                                        client.getLead().get(client.getLead().size()-1).setPending(false);
//                                                                                                        client.getLead().get(client.getLead().size()-1).setCount(0);
//                                                                                                    }
//                                                                                                    service.addClientCall(client,client.getLead().get(client.getLead().size()-1),callUpdated);
//                                                                                                    callUpdated.setLead(client.getLead().get(client.getLead().size()-1));
//                                                                                                             service.updateCallAPIMessage(callUpdated);
//                                                                                                }
//                                                                                                 if(callUpdated.getTrackStatus().contains("requested for callback")){
//                                                                                                     System.out.println("Request Callback");
//                                                                                                                    getClientStage1().push(clientList.get(0));
//                                                                                                                    setFlag1(true);
//                                                                                                 }
//                                                                                            }
//                                                                                            
//                                                                                           
//                                                                                            
//                                                                                            //removing all the call values
//                                                                                             messageStage3.pop();
//                                                                                             System.out.println("Stage 3:Call Message out of queue");
//                                                                                            System.out.println("Stage 3:Built new CallMessage");
//                                                                    }
//                                                        }
//                                                        else{
//                                                                messageStage3.pop();
//                                                            }
//                                                        if(messageStage3.isEmpty()){
//                                                            service.sendEmailUpdate();
//                                                        }
//                                                                Long endTime=System.currentTimeMillis();
//                                            Long timeTaken=endTime-startTime;
//                                            float tt= timeTaken/1000;
//                                            System.out.println("Time Taken "+tt+" seconds");
//                                        }
//                                                          
//                        }
//                    }
//            
//            
//
//    public Stack<Client> getClientStage1() {
//        synchronized(clientStage1){
//        return clientStage1;
//        }
//    }
//
//    public ConcurrentLinkedQueue<Client> getClientStage2() {
//        synchronized(clientStage2){
//        return clientStage2;
//        }
//    }
//
//    public Stack<Call> getStageThreeUpdates() {
//       synchronized(messageStage3){
//        return messageStage3;
//       }
//    }
//
//    public boolean isStage1() {
//         synchronized(this){
//        return stage1;
//         }
//    }
//
//    public void setStage1(boolean stage1) {
//         synchronized(this){
//        this.stage1 = stage1;
//         }
//    }
//
//    public boolean isStage2() {
//     synchronized(this){
//        return stage2;
//     }
//    }
//
//    public void setStage2(boolean stage2) {
//         synchronized(this){
//        this.stage2 = stage2;
//         }
//    }
//
//    public boolean isStage3() {
//         synchronized(this){
//        return stage3;
//         }
//    }
//
//    public void setStage3(boolean stage3) {
//         synchronized(this){
//        this.stage3 = stage3;
//         }
//    }
//
//    public Stack<Client> getRealTimeInCall() {
//         synchronized(this){
//        return realTimeInCall;
//         }
//    }
//
//    public void setRealTimeInCall(Stack<Client> realTimeInCall) {
//         synchronized(realTimeInCall){
//        this.realTimeInCall = realTimeInCall;
//         }
//    }
//
//    public boolean isFlag1() {
//         synchronized(this){
//        return flag1;
//         }
//    }
//
//    public void setFlag1(boolean flag1) {
//         synchronized(this){
//        this.flag1 = flag1;
//         }
//    }
//
//    public boolean isFlag2() {
//         synchronized(this){
//        return flag2;
//        }
//    }
//
//    public void setFlag2(boolean flag2) {
//         synchronized(this){
//        this.flag2 = flag2;
//         }
//    }
//
//    public int getExecutives() {
//        return executives;
//    }
//
//    public void setExecutives(int executives) {
//        this.executives = executives;
//    }
////     public void sendEmailToPassedCall(String email){
////            new EmailService(email,"Your pending health checkup",
////                                            "Hi, "
////                                            + "<br/>"
////                                            + "<br/>"
////                                           +" Call records are updated. <br/>"
////                                            + "<br/> "
////                                            + "check http://162.246.21.98/jubination/admin"
////                                            + "<br/>"
////                                            + "<br/>"
////                                            + "Regards,<br/>Jubination Support").start();
////     }   
//     public void sendEmailToFailCall(String email){
////            new EmailService(email,"Your pending health checkup",
////                                          "Hi,<br/>" +
////                                                "<br/>" +
////                                                "Greetings from Jubination!<br/>" +
////                                                "<br/>" +
////                                                "It's great to have you as a part of Jubination family!<br/>" +
////                                                "<br/>" +
////                                                "We received your inquiry for Thyrocare health check-up package. We have been trying to get in touch with you to fix your appointment but was unable to get through.<br/>" +
////                                                "<br/>" +
////                                                "Request you to suggest a suitable slot for a call-back or call us on 02233835916 or WhatsApp your name & email id on 9930421623 or mail us on support@jubination.com <br/>" +
////                                                "<br/>" +
////                                                "<br/>" +
////                                                "Look forward to hearing from you soon. <br/>" +
////                                                "<br/>" +
////                                                "<br/>" +
////                                                "Wish you a happy & healthy day!<br/>" +
////                                                "<br/>" +
////                                                "<br/>" +
////                                                "Regards,<br/>" +
////                                                "Trupti<br/>" +
////                                                "Customer Happiness Manager<br/>" +
////                                                "02233835916 ").start();
//     }
//
//    private void sendEmailNotUpdated(String email,String number,String exec) {
////            new EmailService(email,"Spoke But Not Updated",
////                                          "Hi,<br/>" +
////                                                "<br/>"+
////                                                   "<br/>"+
////                                                  "Spoke to "+number+". Not updated yet."+
////                                                  "<br/>"+
////                                                  "Call routed to "+exec+
////                                                  "<br/>"+
////                                                   "<br/>"+
////                                                   "Regards,"+
////                                                  "<br/>"+
////                                                  "Jubination"
////                                        ).start();
//    }
//    
//        
//}
