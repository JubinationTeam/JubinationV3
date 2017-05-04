/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.service;
import com.jubination.backend.service.sendgrid.EmailService;
import com.jubination.model.dao.impl.CallAPIMessageDAO;
import com.jubination.model.dao.plan.GenericDAOAbstract;
import com.jubination.model.dao.plan.GenericDAOAbstract;
import com.jubination.model.pojo.admin.AdminSettings;
import com.jubination.model.pojo.crm.Client;
import com.jubination.model.pojo.crm.DataAnalytics;
import com.jubination.model.pojo.crm.Lead;
import com.jubination.model.pojo.exotel.Call;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MumbaiZone
 */
@Service
@Transactional
public class DataAnalyticsService {
    
     @Autowired
    @Qualifier("dataAnalyticsDAO")
    private GenericDAOAbstract daDao;
     @Autowired
    @Qualifier("callAPIMessageDAO")
     private GenericDAOAbstract callDao;
         @Autowired
    @Qualifier("clientDAO")
     private GenericDAOAbstract clientDao;
             @Autowired
    private AdminMaintainService adminService;
           
             private final String settings="settings";
          private final String freshTotal="freshTotal";
          private final String freshBusy=  "freshBusy";
            private final String freshFailed="freshFailed";
            private final String freshNoAnswer="freshNoAnswer";
            private final String freshGreetingsOnHangup="freshGreetingsOnHangup";
            private final String freshHangUpConnect="freshHangUpConnect";
            private final String freshMissCall="freshMissCall";
            private final String freshSpoke="freshSpoke";
            private final String freshRequestedCallBack="freshRequestedCallBack";
            private final String freshOthers="freshOthers";
            private final String followUpTotal="followUpTotal";
            private final String followUpBusy="followUpBusy";
            private final String followUpFailed="followUpFailed";
            private final String followUpNoAnswer="followUpNoAnswer";
            private final String followUpGreetingsOnHangup="followUpGreetingsOnHangup";
            private final String followUpHangUpConnect="followUpHangUpConnect";
            private final String followUpMissCall="followUpMissCall";
            private final String followUpSpoke="followUpSpoke";
            private final String followUpRequestedCallBack="followUpRequestedCallBack";
            private final String followUpOthers="followUpOthers";
            private final String callBackTotal="callBackTotal";
            private final String callBackBusy="callBackBusy";
            private final String callBackFailed="callBackFailed";
            private final String callBackNoAnswer="callBackNoAnswer";
            private final String callBackGreetingsOnHangup="callBackGreetingsOnHangup";
            private final String callBackHangUpConnect="callBackHangUpConnect";
            private final String callBackMissCall="callBackMissCall";
            private final String callBackSpoke="callBackSpoke";
            private final String callBackRequestedCallBack="callBackRequestedCallBack";
            private final String callBackOthers="callBackOthers";
            private final String missedTotal="missedTotal";
            private final String missedBusy="missedBusy";
            private final String missedFailed="missedFailed";
            private final String missedNoAnswer="missedNoAnswer";
            private final String missedGreetingsOnHangup="missedGreetingsOnHangup";
            private final String missedHangUpConnect="missedHangUpConnect";
            private final String missedMissCall="missedMissCall";
            private final String missedSpoke="missedSpoke";
            private final String missedRequestedCallBack="missedRequestedCallBack";
            private final String missedOthers="missedOthers";
            private final String booked="booked";
            private final String done="done";
            private final String cancelled="cancelled";
            private final String serviced="serviced";
            private final String revised="revised";
            private final String deferred="deferred";
             private final String appointment="appointment";
            private final String yetToAssign="yetToAssign";
            
@Transactional(propagation = Propagation.REQUIRED)
            public List<DataAnalytics> readAnalytics(String date){
                List<DataAnalytics> listTemp=(List<DataAnalytics>) daDao.fetchByNative("date", date, null, null, MatchMode.START);
                List<DataAnalytics> list= new ArrayList<>();
                if(listTemp.size()>0){
                    list.add(listTemp.get(listTemp.size()-1));
                }
                return list;
            }
@Transactional(propagation = Propagation.REQUIRED)
            public List<DataAnalytics> readRecentAnalytics() {
                    return (List<DataAnalytics>) daDao.fetchByNativeMax("requestedTime");
              
            }
           @Transactional(propagation = Propagation.REQUIRED)     
            public void doAnalytics() {
               Long total= callDao.countByNative("DateCreated",new SimpleDateFormat("yyyy-MM-dd").format(new Date()),MatchMode.START);
               Long busy= callDao.countByNativeFilterByTwo("DateCreated",new SimpleDateFormat("yyyy-MM-dd").format(new Date()), MatchMode.START,"Status","busy",MatchMode.ANYWHERE);
               Long failed= callDao.countByNativeFilterByTwo("DateCreated",new SimpleDateFormat("yyyy-MM-dd").format(new Date()), MatchMode.START,"Status","failed",MatchMode.ANYWHERE);
               Long noAnswer=  callDao.countByNativeFilterByTwo("DateCreated",new SimpleDateFormat("yyyy-MM-dd").format(new Date()), MatchMode.START,"Status","no-answer",MatchMode.ANYWHERE);
               Long greetingsHangUp=  callDao.countByNativeFilterByTwo("DateCreated",new SimpleDateFormat("yyyy-MM-dd").format(new Date()), MatchMode.START,"CallType", "trans",MatchMode.ANYWHERE);
               Long hangUpOnConnect=  callDao.countByNativeFilterByTwo("DateCreated",new SimpleDateFormat("yyyy-MM-dd").format(new Date()), MatchMode.START,"CallType", "client-hangup",MatchMode.ANYWHERE);
               Long missCall=  callDao.countByNativeFilterByTwo("DateCreated",new SimpleDateFormat("yyyy-MM-dd").format(new Date()), MatchMode.START,"CallType","incomplete",MatchMode.ANYWHERE);
               Long spoke=  callDao.countByNativeFilterByTwo("DateCreated",new SimpleDateFormat("yyyy-MM-dd").format(new Date()), MatchMode.START,"TrackStatus","spoke",MatchMode.ANYWHERE);
               Long requestedCallBack= callDao.countByNativeFilterByTwo("DateCreated",new SimpleDateFormat("yyyy-MM-dd").format(new Date()), MatchMode.START,"TrackStatus", "requested for callback",MatchMode.ANYWHERE);
               Long others = total-busy-failed-noAnswer-greetingsHangUp-hangUpOnConnect-missCall-spoke-requestedCallBack;
               DataAnalytics da= new DataAnalytics();
               da.setTotal(total);
               da.setBusy(busy);
               da.setFailed(failed);
               da.setNoAnswer(noAnswer);
               da.setGreetingsHangup(greetingsHangUp);
               da.setHangupOnConnect(hangUpOnConnect);
               da.setMissCall(missCall);
               da.setSpoke(spoke);
               da.setRequestedCallBack(requestedCallBack);
               da.setOthers(others);
               da.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
               daDao.buildEntity(da);
            }
           @Transactional(propagation = Propagation.REQUIRED)
            public void doCustomAnalytics(String fromDate,String toDate) {
                DataAnalytics daFresh = new DataAnalytics();
                daFresh.setType("fresh");
                DataAnalytics daCallBack = new DataAnalytics();
                daCallBack.setType("callBack");
                DataAnalytics daFollowUp = new DataAnalytics();
                daFollowUp.setType("followUp");
                DataAnalytics daCustom = new DataAnalytics();
                daCustom.setType("custom");
                DataAnalytics daMissed = new DataAnalytics();
                daMissed.setType("missed");

                String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                daFresh.setRequestedTime(time);
                daCallBack.setRequestedTime(time);
                daFollowUp.setRequestedTime(time);
                daCustom.setRequestedTime(time);
                daMissed.setRequestedTime(time);

                daFresh.setFromDate(fromDate);
                daCallBack.setFromDate(fromDate);
                daFollowUp.setFromDate(fromDate);
                daCustom.setFromDate(fromDate);
                daMissed.setFromDate(fromDate);

                daFresh.setToDate(toDate);
                daCallBack.setToDate(toDate);
                daFollowUp.setToDate(toDate);
                daCustom.setToDate(toDate);
                daMissed.setToDate(toDate);
               Long total = callDao.countByNativeRange("DateCreated", fromDate,toDate);
               Long busy= callDao.countByRangeWithFilter("DateCreated",fromDate, toDate,"Status","busy",MatchMode.ANYWHERE);
               Long failed= callDao.countByRangeWithFilter("DateCreated",fromDate, toDate,"Status","failed",MatchMode.ANYWHERE);
               Long noAnswer= callDao.countByRangeWithFilter("DateCreated",fromDate, toDate,"Status","no-answer",MatchMode.ANYWHERE);
               Long greetingsHangUp= callDao.countByRangeWithFilter("DateCreated",fromDate, toDate,"CallType", "trans",MatchMode.ANYWHERE);
               Long hangUpOnConnect= callDao.countByRangeWithFilter("DateCreated",fromDate, toDate,"CallType", "client-hangup",MatchMode.ANYWHERE);
               Long missCall= callDao.countByRangeWithFilter("DateCreated",fromDate, toDate,"CallType","incomplete",MatchMode.ANYWHERE);
               Long spoke= callDao.countByRangeWithFilter("DateCreated",fromDate, toDate,"TrackStatus","spoke",MatchMode.ANYWHERE);
               Long requestedCallBack= callDao.countByRangeWithFilter("DateCreated",fromDate, toDate,"TrackStatus", "requested for callback",MatchMode.ANYWHERE);
               Long others = total-busy-failed-noAnswer-greetingsHangUp-hangUpOnConnect-missCall-spoke-requestedCallBack;
               daCustom.setTotal(total);
               daCustom.setBusy(busy);
               daCustom.setFailed(failed);
               daCustom.setNoAnswer(noAnswer);
               daCustom.setGreetingsHangup(greetingsHangUp);
               daCustom.setHangupOnConnect(hangUpOnConnect);
               daCustom.setMissCall(missCall);
               daCustom.setSpoke(spoke);
               daCustom.setRequestedCallBack(requestedCallBack);
               daCustom.setOthers(others);
               daDao.buildEntity(daCustom);

               Map<String, Long> counts=doReportingOperationSize(clientDao.fetchByNativeRange("dateCreation",fromDate, toDate));


               //CallBack
               daCallBack.setTotal(counts.get(callBackTotal));
               daCallBack.setBusy(counts.get(callBackBusy));
               daCallBack.setFailed(counts.get(callBackFailed));
               daCallBack.setNoAnswer(counts.get(callBackNoAnswer));
               daCallBack.setGreetingsHangup(counts.get(callBackHangUpConnect));
               daCallBack.setHangupOnConnect(counts.get(callBackGreetingsOnHangup));
               daCallBack.setMissCall(counts.get(callBackMissCall));
               daCallBack.setSpoke(counts.get(callBackSpoke));
               daCallBack.setRequestedCallBack(counts.get(callBackRequestedCallBack));
               daCallBack.setOthers(counts.get(callBackOthers));
               daDao.buildEntity(daCallBack);

               //Fresh Call
               daFresh.setTotal(counts.get(freshTotal));
               daFresh.setBusy(counts.get(freshBusy));
               daFresh.setFailed(counts.get(freshFailed));
               daFresh.setNoAnswer(counts.get(freshNoAnswer));
               daFresh.setGreetingsHangup(counts.get(freshGreetingsOnHangup));
               daFresh.setHangupOnConnect(counts.get(freshHangUpConnect));
               daFresh.setMissCall(counts.get(freshMissCall));
               daFresh.setSpoke(counts.get(freshSpoke));
               daFresh.setRequestedCallBack(counts.get(freshRequestedCallBack));
               daFresh.setOthers(counts.get(freshOthers));
               
               daFresh.setAppointment(counts.get(appointment));
               daFresh.setBooked(counts.get(booked));
               daFresh.setCancelled(counts.get(cancelled));
               daFresh.setDeferredStatus(counts.get(deferred));
               daFresh.setDone(counts.get(done));
               daFresh.setRevised(counts.get(revised));
               daFresh.setServiced(counts.get(serviced));
               daFresh.setYetToAssign(counts.get(yetToAssign));
               daDao.buildEntity(daFresh);

               //Follow up
               daFollowUp.setTotal(counts.get(followUpTotal));
               daFollowUp.setBusy(counts.get(followUpBusy));
               daFollowUp.setFailed(counts.get(followUpFailed));
               daFollowUp.setNoAnswer(counts.get(followUpNoAnswer));
               daFollowUp.setGreetingsHangup(counts.get(followUpGreetingsOnHangup));
               daFollowUp.setHangupOnConnect(counts.get(followUpHangUpConnect));
               daFollowUp.setMissCall(counts.get(followUpMissCall));
               daFollowUp.setSpoke(counts.get(followUpSpoke));
               daFollowUp.setRequestedCallBack(counts.get(followUpRequestedCallBack));
               daFollowUp.setOthers(counts.get(followUpOthers));
               daDao.buildEntity(daFollowUp);
               
               //Missed Appointment
               daMissed.setTotal(counts.get(missedTotal));
               daMissed.setBusy(counts.get(missedBusy));
               daMissed.setFailed(counts.get(missedFailed));
               daMissed.setNoAnswer(counts.get(missedNoAnswer));
               daMissed.setGreetingsHangup(counts.get(missedGreetingsOnHangup));
               daMissed.setHangupOnConnect(counts.get(missedHangUpConnect));
               daMissed.setMissCall(counts.get(missedMissCall));
               daMissed.setSpoke(counts.get(missedSpoke));
               daMissed.setRequestedCallBack(counts.get(missedRequestedCallBack));
               daMissed.setOthers(counts.get(missedOthers));
               daDao.buildEntity(daMissed);

            }
            @Transactional(propagation = Propagation.REQUIRED)
            public Map<String, Long> doReportingOperationSize(List<Client> list){

                    Map<String,Long> counts =new LinkedHashMap<>();
                    
                    counts.put(appointment,0l);
                    counts.put(booked,0l);
                    counts.put(done,0l);
                    counts.put(yetToAssign,0l);
                    counts.put(deferred,0l);
                    counts.put(cancelled,0l);
                    counts.put(revised,0l);
                    counts.put(serviced,0l);
                    
                    
                    
                    counts.put(freshTotal,0l);
                    counts.put(freshBusy,0l);
                    counts.put(freshFailed,0l);
                    counts.put(freshNoAnswer,0l);
                    counts.put(freshGreetingsOnHangup,0l);
                    counts.put(freshHangUpConnect,0l);
                    counts.put(freshMissCall,0l);
                    counts.put(freshSpoke,0l);
                    counts.put(freshRequestedCallBack,0l);
                    counts.put(freshOthers,0l);
                    counts.put(followUpTotal,0l);
                    counts.put(followUpBusy,0l);
                    counts.put(followUpFailed,0l);
                    counts.put(followUpNoAnswer,0l);
                    counts.put(followUpGreetingsOnHangup,0l);
                    counts.put(followUpHangUpConnect,0l);
                    counts.put(followUpMissCall,0l);
                    counts.put(followUpSpoke,0l);
                    counts.put(followUpRequestedCallBack,0l);
                    counts.put(followUpOthers,0l);
                    counts.put(callBackTotal,0l);
                    counts.put(callBackBusy,0l);
                    counts.put(callBackFailed,0l);
                    counts.put(callBackNoAnswer,0l);
                    counts.put(callBackGreetingsOnHangup,0l);
                    counts.put(callBackHangUpConnect,0l);
                    counts.put(callBackMissCall,0l);
                    counts.put(callBackSpoke,0l);
                    counts.put(callBackRequestedCallBack,0l);
                    counts.put(callBackOthers,0l);
                    counts.put(missedTotal,0l);
                    counts.put(missedBusy,0l);
                    counts.put(missedFailed,0l);
                    counts.put(missedNoAnswer,0l);
                    counts.put(missedGreetingsOnHangup,0l);
                    counts.put(missedHangUpConnect,0l);
                    counts.put(missedMissCall,0l);
                    counts.put(missedSpoke,0l);
                    counts.put(missedRequestedCallBack,0l);
                    counts.put(missedOthers,0l);
                    if(list!=null){
                    for(Client client:list){
                        int count=0;
                        if(client!=null&&client.getLead()!=null&&client.getLead().size()>=1){
                            
                           
                            
                            
                            Lead lead = client.getLead().get(client.getLead().size()-1);
                            if(lead!=null&&lead.getCall().size()>0){     
                                    for(int i=lead.getCall().size()-1;i>=0;i--){
                                        System.out.println("In FOR LOOP ::::::::::::::::::::::::::::::::::::::::::::REPORTING");
                                                    Call call=lead.getCall().get(i);
                                                    if(call==null){
                                                        break;
                                                    }
                                                    System.out.println(count+" "+i+" "+call.getDateCreated());
                                                    System.out.println("STARTED OPERATION ::::::::::::::::::::::::::::::::::::::::::::REPORTING");

                                                if(call.getStatus()!=null&&call.getStatus().contains("busy")){

                                                    setBusyToCount(counts, lead, call, i);

                                                }
                                                else if(call.getStatus()!=null&&call.getStatus().contains("failed")){
                                                    setFailedToCount(counts, lead, call, i);
                                                }
                                                else if(call.getStatus()!=null&&call.getStatus().contains("no-answer")){
                                                    setNoAnswerToCount(counts, lead, call, i);
                                                }
                                                else if(call.getStatus()!=null&&call.getStatus().contains("completed")&&call.getCallType().contains("trans")){
                                                    setGreetingsHangUpToCount(counts, lead, call, i);
                                                }
                                                else if(call.getTrackStatus()!=null&&call.getTrackStatus().contains("did not speak")&&call.getCallType().contains("client-hangup")){
                                                    setHangUpOnConnectToCount(counts, lead, call, i);
                                                }

                                                else if(call.getTrackStatus()!=null&&call.getTrackStatus().contains("did not speak")&&call.getCallType().contains("incomplete")){
                                                       setMissCallToCount(counts, lead, call, i);
                                                }
                                            else if(call.getTrackStatus()!=null&&call.getTrackStatus().contains("spoke")){
                                                   setConverstionCount(counts, lead);
                                                    setSpokeToCount(counts, lead, call, i);
                                                }
                                            else{

                                                if(i==lead.getCall().size()-1){
                                                        if(lead.getLeadStatus()!=null){
                                                                    if(lead.getLeadStatus()!=null&&(lead.getLeadStatus().contains("Follow up/Call back")||
                                                                               lead.getLeadStatus().contains("Not interested")||
                                                                               lead.getLeadStatus().contains("Not registered")||
                                                                               lead.getLeadStatus().contains("Language not recognizable")||
                                                                               lead.getLeadStatus().contains("No Service")||
                                                                               lead.getLeadStatus().contains("Customer complained")||
                                                                               lead.getLeadStatus().contains("Disapproved")||
                                                                               lead.getLeadStatus().contains("Lead sent to Thyrocare")||
                                                                               lead.getLeadStatus().contains("Spoke but not updated")||
                                                                               lead.getLeadStatus().contains("Rescheduled")
                                                                               )){
                                                                        setConverstionCount(counts, lead);
                                                                        setSpokeToCount(counts, lead, call, i);
                                                                    }
                                                                    else if(lead.getLeadStatus().contains("Busy")){
                                                                        setBusyToCount(counts, lead, call, i);
                                                                    }
                                                                    else if(lead.getLeadStatus().contains("Failed")){
                                                                        setFailedToCount(counts, lead, call, i);
                                                                    }
                                                                    else if(lead.getLeadStatus().contains("No Answer")){
                                                                         setNoAnswerToCount(counts, lead, call, i);
                                                                    }
                                                                    else if(lead.getLeadStatus().contains("Hanged up while greetings")){
                                                                        setGreetingsHangUpToCount(counts, lead, call, i);
                                                                    }
                                                                    else if(lead.getLeadStatus().contains("Hanged up while connecting")){
                                                                        setHangUpOnConnectToCount(counts, lead, call, i);
                                                                    }
                                                                    else{
                                                                        setOthersToCount(counts, lead, call, i);
                                                                    }

                                                         }
                                                        else{
                                                                        setOthersToCount(counts, lead, call, i);
                                                         }
                                                }
                                                else{
                                                                        setOthersToCount(counts, lead, call, i);
                                                }
                                            }
                                        
                                   }
                                    
                                   
                                    
                                    
                                    
                                    
                        }
                            
                    
                    }
                    }
            }
                    list=null;
                    return counts;
            }
           @Transactional(propagation = Propagation.REQUIRED)
            private Map<String, Long> setBusyToCount(Map<String, Long> counts,Lead lead, Call call, Integer i){
                //compareto - argument should be lexicographically greater than the comparing string
                if(lead.getFollowUpDate()!=null&&lead.getFollowUpDate().compareTo(call.getDateCreated().split(" ")[0])<=0&&lead.getCount()<1){
                        counts.replace(callBackBusy, counts.get(callBackBusy)+1);
                        counts.replace(callBackTotal, counts.get(callBackTotal)+1);
                }
                else if(i==0){
                        counts.replace(freshBusy, counts.get(freshBusy)+1);
                        counts.replace(freshTotal, counts.get(freshTotal)+1);
                }
                else if(i==lead.getCall().size()-1&&lead.isMissedAppointment()!=null&&lead.isMissedAppointment()&&lead.getCount()<1){
                       counts.replace(missedBusy, counts.get(missedBusy)+1);
                        counts.replace(missedTotal, counts.get(missedTotal)+1);
                }
                else{
                        counts.replace(followUpBusy, counts.get(followUpBusy)+1);
                        counts.replace(followUpTotal, counts.get(followUpTotal)+1);
                }
                return counts;
            }
           @Transactional(propagation = Propagation.REQUIRED)
            private Map<String, Long> setFailedToCount(Map<String, Long> counts,Lead lead, Call call, Integer i){
                //compareto - argument should be lexicographically greater than the comparing string
                    if(lead.getFollowUpDate()!=null&&lead.getFollowUpDate().compareTo(call.getDateCreated().split(" ")[0])<=0&&lead.getCount()<1){
                            counts.replace(callBackFailed, counts.get(callBackFailed)+1);
                            counts.replace(callBackTotal, counts.get(callBackTotal)+1);
                    }
                    else if(i==0){
                            counts.replace(freshFailed, counts.get(freshFailed)+1);
                            counts.replace(freshTotal, counts.get(freshTotal)+1);
                    }
                    else if(i==lead.getCall().size()-1&&lead.isMissedAppointment()!=null&&lead.isMissedAppointment()&&lead.getCount()<1){
                           counts.replace(missedFailed, counts.get(missedFailed)+1);
                            counts.replace(missedTotal, counts.get(missedTotal)+1);
                    }
                    else{
                            counts.replace(followUpFailed, counts.get(followUpFailed)+1);
                            counts.replace(followUpTotal, counts.get(followUpTotal)+1);
                    }
                return counts;
            }
           @Transactional(propagation = Propagation.REQUIRED)
            private Map<String, Long> setNoAnswerToCount(Map<String, Long> counts,Lead lead, Call call, Integer i){
                //compareto - argument should be lexicographically greater than the comparing string
                    if(lead.getFollowUpDate()!=null&&lead.getFollowUpDate().compareTo(call.getDateCreated().split(" ")[0])<=0&&lead.getCount()<1){
                            counts.replace(callBackNoAnswer, counts.get(callBackNoAnswer)+1);
                            counts.replace(callBackTotal, counts.get(callBackTotal)+1);
                    }
                    else if(i==0){
                            counts.replace(freshNoAnswer, counts.get(freshNoAnswer)+1);
                            counts.replace(freshTotal, counts.get(freshTotal)+1);
                    }
                    else if(i==lead.getCall().size()-1&&lead.isMissedAppointment()!=null&&lead.isMissedAppointment()&&lead.getCount()<1){
                           counts.replace(missedNoAnswer, counts.get(missedNoAnswer)+1);
                            counts.replace(missedTotal, counts.get(missedTotal)+1);
                    }
                    else{
                            counts.replace(followUpNoAnswer, counts.get(followUpNoAnswer)+1);
                            counts.replace(followUpTotal, counts.get(followUpTotal)+1);
                    }

                return counts;
            }
            @Transactional(propagation = Propagation.REQUIRED)
            private Map<String, Long> setHangUpOnConnectToCount(Map<String, Long> counts,Lead lead, Call call, Integer i){
                //compareto - argument should be lexicographically greater than the comparing string
                if(lead.getFollowUpDate()!=null&&lead.getFollowUpDate().compareTo(call.getDateCreated().split(" ")[0])<=0&&lead.getCount()<1){
                        counts.replace(callBackHangUpConnect, counts.get(callBackHangUpConnect)+1);
                        counts.replace(callBackTotal, counts.get(callBackTotal)+1);
                }
                else if(i==0){
                        counts.replace(freshHangUpConnect, counts.get(freshHangUpConnect)+1);
                        counts.replace(freshTotal, counts.get(freshTotal)+1);
                }
                     else if(i==lead.getCall().size()-1&&lead.isMissedAppointment()!=null&&lead.isMissedAppointment()&&lead.getCount()<1){
                           counts.replace(missedHangUpConnect, counts.get(missedHangUpConnect)+1);
                            counts.replace(missedTotal, counts.get(missedTotal)+1);
                    }
                else{
                        counts.replace(followUpHangUpConnect, counts.get(followUpHangUpConnect)+1);
                        counts.replace(followUpTotal, counts.get(followUpTotal)+1);
                }
                return counts;
            }
            @Transactional(propagation = Propagation.REQUIRED)
            private Map<String, Long> setGreetingsHangUpToCount(Map<String, Long> counts,Lead lead, Call call, Integer i){
                 //compareto - argument should be lexicographically greater than the comparing string
                    if(lead.getFollowUpDate()!=null&&lead.getFollowUpDate().compareTo(call.getDateCreated().split(" ")[0])<=0&&lead.getCount()<1){
                            counts.replace(callBackGreetingsOnHangup, counts.get(callBackGreetingsOnHangup)+1);
                            counts.replace(callBackTotal, counts.get(callBackTotal)+1);
                    }
                    else if(i==0){
                            counts.replace(freshGreetingsOnHangup, counts.get(freshGreetingsOnHangup)+1);
                            counts.replace(freshTotal, counts.get(freshTotal)+1);
                    }
                     else if(i==lead.getCall().size()-1&&lead.isMissedAppointment()!=null&&lead.isMissedAppointment()&&lead.getCount()<1){
                           counts.replace(missedGreetingsOnHangup, counts.get(missedGreetingsOnHangup)+1);
                            counts.replace(missedTotal, counts.get(missedTotal)+1);
                    }
                    else{
                            counts.replace(followUpGreetingsOnHangup, counts.get(followUpGreetingsOnHangup)+1);
                            counts.replace(followUpTotal, counts.get(followUpTotal)+1);
                    }
                return counts;
            }
            @Transactional(propagation = Propagation.REQUIRED)
            private Map<String, Long> setMissCallToCount(Map<String, Long> counts,Lead lead, Call call, Integer i){
                 //compareto - argument should be lexicographically greater than the comparing string
                if(lead.getFollowUpDate()!=null&&lead.getFollowUpDate().compareTo(call.getDateCreated().split(" ")[0])<=0&&lead.getCount()<1&&lead.getCount()<1){
                        counts.replace(callBackMissCall, counts.get(callBackMissCall)+1);
                        counts.replace(callBackTotal, counts.get(callBackTotal)+1);
                }
                else if(i==0){
                        counts.replace(freshMissCall, counts.get(freshMissCall)+1);
                        counts.replace(freshTotal, counts.get(freshTotal)+1);
                }
                else if(i==lead.getCall().size()-1&&lead.isMissedAppointment()!=null&&lead.isMissedAppointment()&&lead.getCount()<1){
                           counts.replace(missedMissCall, counts.get(missedMissCall)+1);
                            counts.replace(missedTotal, counts.get(missedTotal)+1);
                    }
                else{
                        counts.replace(followUpMissCall, counts.get(followUpMissCall)+1);
                        counts.replace(followUpTotal, counts.get(followUpTotal)+1);
                }
                return counts;
            }
            @Transactional(propagation = Propagation.REQUIRED)
            private Map<String, Long> setSpokeToCount(Map<String, Long> counts,Lead lead, Call call, Integer i){
                 //compareto - argument should be lexicographically greater than the comparing string
                    if(lead.getFollowUpDate()!=null&&lead.getFollowUpDate().compareTo(call.getDateCreated().split(" ")[0])<=0&&lead.getCount()<1){
                            counts.replace(callBackSpoke, counts.get(callBackSpoke)+1);
                            counts.replace(callBackTotal, counts.get(callBackTotal)+1);
                    }
                    else if(i==0){
                            counts.replace(freshSpoke, counts.get(freshSpoke)+1);
                            counts.replace(freshTotal, counts.get(freshTotal)+1);
                    }
                else if(i==lead.getCall().size()-1&&lead.isMissedAppointment()!=null&&lead.isMissedAppointment()&&lead.getCount()<1){
                           counts.replace(missedSpoke, counts.get(missedSpoke)+1);
                            counts.replace(missedTotal, counts.get(missedTotal)+1);
                    }
                    else{
                            counts.replace(followUpSpoke, counts.get(followUpSpoke)+1);
                            counts.replace(followUpTotal, counts.get(followUpTotal)+1);
                    }
                return counts;
            }
            @Transactional(propagation = Propagation.REQUIRED)
            private Map<String, Long> setOthersToCount(Map<String, Long> counts,Lead lead, Call call, Integer i){
                //compareto - argument should be lexicographically greater than the comparing string
                    if(lead.getFollowUpDate()!=null&&lead.getFollowUpDate().compareTo(call.getDateCreated().split(" ")[0])<=0&&lead.getCount()<1){
                            counts.replace(callBackOthers, counts.get(callBackOthers)+1);
                            counts.replace(callBackTotal, counts.get(callBackTotal)+1);
                    }
                    else if(i==0){
                            counts.replace(freshOthers, counts.get(freshOthers)+1);
                            counts.replace(freshTotal, counts.get(freshTotal)+1);
                    }
                    else if(i==lead.getCall().size()-1&&lead.isMissedAppointment()!=null&&lead.isMissedAppointment()&&lead.getCount()<1){
                           counts.replace(missedOthers, counts.get(missedOthers)+1);
                            counts.replace(missedTotal, counts.get(missedTotal)+1);
                    }
                    else{
                            counts.replace(followUpOthers, counts.get(followUpOthers)+1);
                            counts.replace(followUpTotal, counts.get(followUpTotal)+1);
                    }
                return counts;
            }
            @Transactional(propagation = Propagation.REQUIRED)
                public void mailSpokeAnalytics(String date) {
        String tillDate="";
        if(date==null){
            date=new SimpleDateFormat("yyyy-MM-dd").format(new Date())+" 00:00:00";
             tillDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        }
        else{
            tillDate=date+" 23:30:00";
        }
        Map<String, Long> counts=doReportingOperationSize(clientDao.fetchByNativeRange("dateCreation","2016-12-01 00:00:00", date));
                long total=8766+counts.get(freshTotal);
                long spoke=2605+counts.get(freshSpoke);
                long book=2330+counts.get(booked);
                //data till november
                if(total==0){
                    total=1l;
                }
                float spokeRatio=(spoke*100)/total;
                float bookToSpokeRatio = (book*100)/spoke;
                float bookRatio=(book*100)/total;
                
                counts=doReportingOperationSize(clientDao.fetchByNativeRange("dateCreation",date, tillDate));
                total=counts.get(freshTotal);
                spoke=counts.get(freshSpoke);
                book=counts.get(booked);
                
                float spokeRatioToday=(spoke*100)/total;
                float bookToSpokeRatioToday = (book*100)/spoke;
                float bookRatioToday=(book*100)/total;
                
                float increases=spokeRatioToday-spokeRatio;
                float increasebs= bookToSpokeRatioToday-bookToSpokeRatio;
                float increaseb= bookRatioToday-bookRatio;

                
                StringBuilder text=new StringBuilder();
              
                text.append("")
                        .append("Hi,<br/><br/> <br/>I am Call Bot!<br/><br/>")
                        .append("Average Spoke Ratio : ")
                        .append(spokeRatio)
                            .append("%<br/>")
                        .append("Average book to spoke Ratio : ")
                            .append(bookToSpokeRatio)
                            .append("%<br/>")
                            .append("Average book Ratio : ")
                            .append(bookRatio)
                            .append("%<br/>")
                            .append("<br/>Today's Spoke Ratio : ")
                            .append(spokeRatioToday)
                            .append("%<br/>")
                            .append("Today's book to spoke Ratio : ")
                            .append(bookToSpokeRatioToday)
                            .append("%<br/>")
                            .append("Today's book Ratio : ")
                            .append(bookRatioToday)
                            .append("%<br/>")
                            .append("<br/>Increase in spoke ratio : ")
                            .append(increases)
                            .append("%<br/>")
                        .append("Increase in book to spoke Ratio : ")
                        .append(increasebs)
                        
                            .append("%<br/>")
                        
                            .append("Increase in book ratio : ")
                            .append(increaseb)
                        .append("%<br/></br>")
                .append("Regards,<br/>Call Bot");
           
                
                
               
                  
                                 sendEmailLeadQuality("subhadeep@jubination.com",text.toString());
                                 sendEmailLeadQuality("disha@jubination.com",text.toString());
                                 sendEmailLeadQuality("trupti@jubination.com",text.toString());
                                 sendEmailLeadQuality("vinay@jubination.com",text.toString());
                                 sendEmailLeadQuality("tauseef@jubination.com",text.toString());
                                 sendEmailLeadQuality("souvik@jubination.com",text.toString());
                                 
                
               
    }
    
                
@Transactional(propagation = Propagation.REQUIRED)
     private void sendEmailLeadQuality(String email,String content){
           AdminSettings adminSettings = adminService.readSettings(settings);
            new EmailService(email,"Lead quality of affiliates",
                                          "Hi,<br/>" +
                                                "<br/>" +
                                                "I am call Bot! <br/>" +
                                                "<br/>" +
                                                content+
                                                "<br/>" +
                                                "<br/>" +
                                                "Regards,<br/>" + 
                                                "Call Bot ",adminSettings.getMyUsername(),adminSettings.getMyPassword(),adminSettings.getAuth(),adminSettings.getStarttls(),adminSettings.getHost(),adminSettings.getPort(),adminSettings.getSendgridApi()).start();
     }
@Transactional(propagation = Propagation.REQUIRED)
    private Map<String, Long> setConverstionCount(Map<String, Long> counts, Lead lead) {
          if(lead!=null&&lead.isMissedAppointment()!=null){
                                        if(lead.getMissedAppointmentStatus().contains("DONE")){
                                             System.out.println("In FOR LOOP ::::::::::::::::::::::::::::::::::::::::::::DONE");
                                            counts.replace(done, counts.get(done)+1);
                                        }
                                        else if(lead.getMissedAppointmentStatus().contains("CANCELLED")){
                                            
                                             System.out.println("In FOR LOOP ::::::::::::::::::::::::::::::::::::::::::::CANCELED");
                                                counts.replace(cancelled, counts.get(cancelled)+1);
                                                }
                                          else if(lead.getMissedAppointmentStatus().contains("DEFERRED")||(lead.getMissedAppointmentStatus().contains("YET TO ASSIGN")&&lead.getCount()>0)){
                                             System.out.println("In FOR LOOP ::::::::::::::::::::::::::::::::::::::::::::DEFERED");
                                                counts.replace(deferred, counts.get(deferred)+1);
                                                }
                                          else if(lead.getMissedAppointmentStatus().contains("REVISED")){
                                             System.out.println("In FOR LOOP ::::::::::::::::::::::::::::::::::::::::::::REVISED");
                                                counts.replace(revised, counts.get(revised)+1);
                                                }
                                          else if(lead.getMissedAppointmentStatus().contains("SERVICED")){
                                             System.out.println("In FOR LOOP ::::::::::::::::::::::::::::::::::::::::::::SERVICED");
                                                counts.replace(serviced, counts.get(serviced)+1);
                                                }
                                          else if(lead.getMissedAppointmentStatus().contains("APPOINTMENT")){
                                             System.out.println("In FOR LOOP ::::::::::::::::::::::::::::::::::::::::::::APPOINTMENT");
                                                counts.replace(appointment, counts.get(appointment)+1);
                                                }
                                          else if(lead.getMissedAppointmentStatus().contains("YET TO ASSIGN")){
                                             System.out.println("In FOR LOOP ::::::::::::::::::::::::::::::::::::::::::::YET TO ASSIGN");
                                                counts.replace(yetToAssign, counts.get(yetToAssign)+1);
                                                }
                                    }
                                    if(lead!=null&&lead.getLeadStatus()!=null&&lead.getLeadStatus().contains("Lead sent to Thyrocare")){
                                          
                                        System.out.println("In FOR LOOP ::::::::::::::::::::::::::::::::::::::::::::BOOKED");
                                        counts.replace(booked, counts.get(booked)+1);
                                    }
                                    return counts;
    }

}
