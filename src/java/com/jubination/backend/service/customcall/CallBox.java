package com.jubination.backend.service.customcall;


import com.jubination.model.pojo.ivr.exotel.Call;
import com.jubination.service.AdminMaintainService;
import com.jubination.service.CallMaintainService;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;
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
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Welcome
 */
@Component
public class CallBox {
                    private boolean flag=false;
                    private boolean checkFlag=false;
                    private int count = 0;
                    private Stack<String> numbers = new Stack<>();
                    private ConcurrentLinkedQueue<String> sids = new ConcurrentLinkedQueue<>();
                    private Stack<Call> stageThreeUpdates = new Stack<>();
                    
                    private int executives=0;
                    private String appId;
                    private String callerId;
                   
                    

                    @Autowired
                    AdminMaintainService adminMaintain;
                    @Autowired
                    CallMaintainService callMaintain;
                    @Async
                    @Scheduled(fixedRate=2500)
                    void callDicyCustomer() throws IOException,InterruptedException, JAXBException{
                                            Long startTime=System.currentTimeMillis();
                                            if(!numbers.isEmpty()&&sids.size()<getExecutives()){
                                                                    String callerId=numbers.peek();
                                                                    if(numbers.isEmpty()){
                                                                                    flag=false;
                                                                                }
                                                                    
                                                                    
                                                                    
                                                                    
                                                                    String responseText="NA";
                                                                    Document doc=null;
                                                                    //System.out.println("Stage 1:"+numbers.size()+" Sids in queue to be sent to exotel to process "+Thread.currentThread());
                                                                    if(callerId!=null){
                                                                                            CloseableHttpResponse response=null;
                                                                                            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                                                                                            DocumentBuilder builder;
                                                                                            InputSource is;
                                                                                            try { 
                                                                                                                        //requesting exotel to initiate call
                                                                                                                        CloseableHttpClient httpclient = HttpClients.createDefault();
                                                                                                                        HttpPost httpPost = new HttpPost("https://jubination:ce5e307d58d8ec07c8d8456e42ed171ff8322fd0@twilix.exotel.in/v1/Accounts/jubination/Calls/connect");
                                                                                                                        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
                                                                                                                        formparams.add(new BasicNameValuePair("From",callerId));
                                                                                                                        //formparams.add(new BasicNameValuePair("To",callerId));
                                                                                                                        formparams.add(new BasicNameValuePair("CallerId",getCallerId()));
                                                                                                                        formparams.add(new BasicNameValuePair("CallerType","trans"));
                                                                                                                        formparams.add(new BasicNameValuePair("Url","http://my.exotel.in/exoml/start/"+getAppId()));
                                                                                                                        formparams.add(new BasicNameValuePair("TimeLimit","1800"));
                                                                                                                        formparams.add(new BasicNameValuePair("TimeOut","30"));
                                                                                                                        formparams.add(new BasicNameValuePair("SatusCallback",""));
                                                                                                                        formparams.add(new BasicNameValuePair("CustomField","customCall"));
                                                                                                                        UrlEncodedFormEntity uEntity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
                                                                                                                        httpPost.setEntity(uEntity);
                                                                                                                        response = httpclient.execute(httpPost);
                                                                                                                        //System.out.println("Stage 1:Number sent to exotel");
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
                                                                    ExotelMessage eMessage = (ExotelMessage) jaxbUnmarshaller.unmarshal(doc);
                                                                    Call message=null;
                                                                    //System.out.println("Stage 1:Got xml message");
                                                                    if(eMessage!=null){
                                                                                            //System.out.println("Stage 1:xml message not null");
                                                                                            if(eMessage.getSuccessMessage()!=null){
                                                                                                                //System.out.println("Stage 1:xml message success");
                                                                                                                message=eMessage.getSuccessMessage();
                                                                                                                message.setTrackStatus("Call request sent");
                                                                                                                message.setMessage("Stage 1 Calling");
                                                                                                                message.setCallTo(callerId);
                                                                                                                //adding all the sid values of calls placed
                                                                                                                sids.offer(message.getSid());
                                                                                             }
                                                                                            else if (eMessage.getFailureMessage()!=null){
                                                                                                               //System.out.println("Stage 1:xml message failed");
                                                                                                                message=eMessage.getFailureMessage();
                                                                                                                message.setCallTo(callerId);
                                                                                                                message.setDateCreated(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                                                                                                                message.setTrackStatus(message.getMessage());
                                                                                            }
                                                                                            else{
                                                                                                                //System.out.println("Stage 1:xml message unknown error");
                                                                                                                message.setCallTo(callerId);
                                                                                                                message.setTrackStatus("Unknown Error");
                                                                                                                message.setDateCreated(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                                                                                            }
                                                                    }
                                                                    //System.out.println("Stage 1:adding message to database");
                                                                    //saving call details
                                                                    callMaintain.addCallAPIMessage(message);
                                                                                            //removing number
                                                                                            numbers.pop();
                                                                                            //System.out.println("Stage 1:Number out of queue");
                                                                    flag=!numbers.isEmpty();
                                                                    if(!flag){
                                                                        //initiate call status update after call are over
                                                                        checkFlag=true;
                                                                    }
                                                                    Long endTime=System.currentTimeMillis();
                                            Long timeTaken=endTime-startTime;
                                            float tt=timeTaken/1000;
                                            //System.out.println("Time Taken "+tt+" seconds");
                                            }
                                            
                    }

   
                    @Async
                    @Scheduled(fixedDelay=3000)
                    void checkCalledCustomers() throws IOException, JAXBException{
                                        Long startTime=System.currentTimeMillis();
                                        if(isCheckFlag()||sids.size()>=getExecutives()){
                                                            String sid=null;
                                                            String responseText="NA";
                                                            Document doc=null;
                                                            //System.out.println("Stage 2:"+sids.size()+" Sids in queue to be sent to exotel to process "+Thread.currentThread());
                                                                                //fetching all the sid values
                                                                                sid=sids.peek();
                                                                                if(sids.isEmpty()){
                                                                                    checkFlag=false;
                                                                                }
                                                                                
                                                             CloseableHttpResponse response=null;
                                                             DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                                                             DocumentBuilder builder;
                                                             InputSource is;
                                                             try { 
                                                                         //checking sid details with exotel
                                                                         CloseableHttpClient httpclient = HttpClients.createDefault();
                                                                         HttpGet httpGet = new HttpGet("https://jubination:ce5e307d58d8ec07c8d8456e42ed171ff8322fd0@twilix.exotel.in/v1/Accounts/jubination/Calls/"+sid);
                                                                         response = httpclient.execute(httpGet);
                                                                         //System.out.println("Stage 2:Sid sent to exotel");
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
                                                             //parsing xml response from exotel
                                                             JAXBContext jaxbContext = JAXBContext.newInstance(ExotelMessage.class);
                                                             Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                                                             ExotelMessage eMessage = (ExotelMessage) jaxbUnmarshaller.unmarshal(doc);
                                                             Call message=null;
                                                             //System.out.println("Stage 2:Got xml message");
                                                             if(eMessage!=null){
                                                                            //System.out.println("Stage 2:xml message not null");
                                                                            if(eMessage.getSuccessMessage()!=null){
                                                                                            message=eMessage.getSuccessMessage();
                                                                                            //System.out.println("Stage 2:xml message success "+message.getStatus()+message);
                                                                                            if(message.getStatus().contains("queued")||message.getStatus().contains("ringing")||message.getStatus().contains("in-progress")){
                                                                                                                        count++;
                                                                                                                        //System.out.println("Stage 2:sid out of queue. Trying for"+count+"th time");
                                                                                            }
                                                                                            else {
                                                                                                                        Call storedMessage=callMaintain.getCallRecordBySid(message.getSid());
                                                                                                                        if(storedMessage!=null){
                                                                                                                                                //System.out.println("Stage 2:sid of the number was present already");
                                                                                                                                                 message.setOrderId(storedMessage.getOrderId());
                                                                                                                                                 message.setMessage("Stage 2 Tracking");
                                                                                                                                                //updating call details
                                                                                                                                                if(!storedMessage.getMessage().equals("Stage 3 Tracking")){
                                                                                                                                                                            //System.out.println("Stage 2:stage 3 not updated yet");
                                                                                                                                                                            message.setCallTo(storedMessage.getCallTo());
                                                                                                                                                                            callMaintain.updateCallAPIMessage(message);
                                                                                                                                                  }
                                                                                                                                                  else{
                                                                                                                                                                            //System.out.println("Stage 2:stage 3 updated already");
                                                                                                                                                                            storedMessage.setStatus(message.getStatus());
                                                                                                                                                                            storedMessage.setCallType(message.getCallType());
                                                                                                                                                                            storedMessage.setRecordingUrl(message.getRecordingUrl());
                                                                                                                                                                            callMaintain.updateCallAPIMessage(storedMessage);
                                                                                                                                                   }
                                                                                                                        }
                                                                                                                                                 //removing all the sid values
                                                                                                                                                sids.poll();
                                                                                                                                                count=0;
                                                                                                                                                //System.out.println("Stage 2:Sid out of queue");
                                                                                            }
                                                                                            if(count>=300){
                                                                                                                        //System.out.println("Stage 2:Time out. sid out of queue. Trying for next sid");
                                                                                                                        sids.poll();
                                                                                                                        count=0;
                                                                                            }

                                                                            }
                                                             }
                                                             //stop operation if all done
                                                             checkFlag=!sids.isEmpty();
                                                              Long endTime=System.currentTimeMillis();
                                            Long timeTaken=endTime-startTime;
                                            float tt= timeTaken/1000;
                                            //System.out.println("Time Taken "+tt+" seconds");
                                        }
                                                                 
                  
                    }

                    //Depricated
           /*         @Async
                    @Scheduled(fixedDelay=3000)
                    void checkStageThreeCustomers() throws IOException, JAXBException{
                       Long startTime=System.currentTimeMillis();
                        if(!flag&&!checkFlag&&!stageThreeUpdates.isEmpty()){
                                             //fetching all the call values
                                                        Call callUpdated= stageThreeUpdates.peek();
                                                                           
                                                                           
                                                        if(callUpdated!=null){
                                                                    List<Call> callList=callMaintain.getCallBySid(callUpdated.getSid());
                                                                    if(callList!=null&&!callList.isEmpty()){
                                                                                        Call  call=callList.get(0);
                                                                                          //System.out.println("Stage 3:"+stageThreeUpdates.size()+"left. Update service started");
                                                                                         if(call!=null){
                                                                                                             //System.out.println("Stage 3:Call message present in database already and not null");
                                                                                                             call.setMessage("Stage 3 Tracking");
                                                                                                             call.setStatus("completed");
                                                                                                             call.setDailCallDuration(callUpdated.getDailCallDuration());
                                                                                                             call.setCallType(callUpdated.getCallType());
                                                                                                             call.setTrackStatus(callUpdated.getTrackStatus());
                                                                                                             call.setDigits(callUpdated.getDigits());
                                                                                                             call.setDailWhomNumber(callUpdated.getDailWhomNumber());
                                                                                                             call.setRecordingUrl(callUpdated.getRecordingUrl());
                                                                                                             call.setStartTime(callUpdated.getStartTime());
                                                                                                             call.setDirection(callUpdated.getDirection());
                                                                                                             //System.out.println("Stage 3:Updating database properties");
                                                                                                             callMaintain.updateCallAPIMessage(call);
                                                                                                             synchronized(this){
                                                                                                                               //removing all the call values
                                                                                                                                stageThreeUpdates.pop();
                                                                                                                                //System.out.println("Stage 3:Call Message out of queue");
                                                                                                            }
                                                                                         }
                                                                    }
                                                                    else{
                                                                                            //System.out.println("Stage 3:Call message not present in database");
                                                                                            callUpdated.setMessage("Incoming Tracking");
                                                                                            callMaintain.buildCallAPIMessage(callUpdated);
                                                                                            //removing all the call values
                                                                                             stageThreeUpdates.pop();
                                                                                             //System.out.println("Stage 3:Call Message out of queue");
                                                                                            //System.out.println("Stage 3:Built new CallMessage");
                                                                    }
                                                        }
                                                        if(stageThreeUpdates.isEmpty()){
                                                            callMaintain.sendEmailUpdate();
                                                        }
                                                              Long endTime=System.currentTimeMillis();
                                            Long timeTaken=endTime-startTime;
                                            float tt= timeTaken/1000;
                                            //System.out.println("Time Taken "+tt+" seconds");
                                        }
                                                            
                  
                    }
*/

                    public boolean isFlag() {
                        synchronized(this){         
                        return flag;
                        }
                    }

                    public void setFlag(boolean flag) {
                        synchronized(this){ 
                        this.flag = flag;
                        }
                    }

    public boolean isCheckFlag() {
        synchronized(this){
        return checkFlag;
        }
    }

    public void setCheckFlag(boolean checkFlag) {
        synchronized(this){
        this.checkFlag = checkFlag;
        }
    }
                    

     public Stack<String> getNumbers() {
       synchronized(numbers){
        return numbers;
       }
    }

    public ConcurrentLinkedQueue<String> getSids() {
        return sids;
    }

    public Stack<Call> getStageThreeUpdates() {
        synchronized(stageThreeUpdates){
        return stageThreeUpdates;
        }
    }

    public int getExecutives() {
        return executives;
    }

    public void setExecutives(int executives) {
        this.executives = executives;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getCallerId() {
        return callerId;
    }

    public void setCallerId(String callerId) {
        this.callerId = callerId;
    }

                   
        
}