/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.service;
import com.jubination.backend.service.sendgrid.EmailService;
import com.jubination.model.dao.CallAPIMessageDAOImpl;
import com.jubination.model.dao.ClientDAOImpl;
import com.jubination.model.dao.DataAnalyticsDAOImpl;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MumbaiZone
 */
@Service
@Transactional
public class DataAnalyticsService {
    
    @Autowired
 private DataAnalyticsDAOImpl daDao;
     @Autowired
 private CallAPIMessageDAOImpl callDao;
          @Autowired
 private ClientDAOImpl clientDao;
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
            
            private final String firstTotal="firstTotal";
          private final String firstBusy=  "firstBusy";
            private final String firstFailed="firstFailed";
            private final String firstNoAnswer="firstNoAnswer";
            private final String firstGreetingsOnHangup="firstGreetingsOnHangup";
            private final String firstHangUpConnect="firstHangUpConnect";
            private final String firstMissCall="firstMissCall";
            private final String firstSpoke="firstSpoke";
            private final String firstRequestedCallBack="firstRequestedCallBack";
            private final String firstOthers="firstOthers";
            
            private final String secondTotal="secondTotal";
          private final String secondBusy=  "secondBusy";
            private final String secondFailed="secondFailed";
            private final String secondNoAnswer="secondNoAnswer";
            private final String secondGreetingsOnHangup="secondGreetingsOnHangup";
            private final String secondHangUpConnect="secondHangUpConnect";
            private final String secondMissCall="secondMissCall";
            private final String secondSpoke="secondSpoke";
            private final String secondRequestedCallBack="secondRequestedCallBack";
            private final String secondOthers="thirdOthers";
            
            private final String thirdTotal="thirdTotal";
          private final String thirdBusy=  "thirdBusy";
            private final String thirdFailed="thirdFailed";
            private final String thirdNoAnswer="thirdNoAnswer";
            private final String thirdGreetingsOnHangup="thirdGreetingsOnHangup";
            private final String thirdHangUpConnect="thirdHangUpConnect";
            private final String thirdMissCall="thirdMissCall";
            private final String thirdSpoke="thirdSpoke";
            private final String thirdRequestedCallBack="thirdRequestedCallBack";
            private final String thirdOthers="thirdOthers";
            
            private final String fourthTotal="fourthTotal";
          private final String fourthBusy=  "fourthBusy";
            private final String fourthFailed="fourthFailed";
            private final String fourthNoAnswer="fourthNoAnswer";
            private final String fourthGreetingsOnHangup="fourthGreetingsOnHangup";
            private final String fourthHangUpConnect="fourthHangUpConnect";
            private final String fourthMissCall="fourthMissCall";
            private final String fourthSpoke="fourthSpoke";
            private final String fourthRequestedCallBack="fourthRequestedCallBack";
            private final String fourthOthers="fourthOthers";
            
            private final String fiveTotal="fiveTotal";
          private final String fiveBusy=  "fiveBusy";
            private final String fiveFailed="fiveFailed";
            private final String fiveNoAnswer="fiveNoAnswer";
            private final String fiveGreetingsOnHangup="fiveGreetingsOnHangup";
            private final String fiveHangUpConnect="fiveHangUpConnect";
            private final String fiveMissCall="fiveMissCall";
            private final String fiveSpoke="fiveSpoke";
            private final String fiveRequestedCallBack="fiveRequestedCallBack";
            private final String fiveOthers="fiveOthers";
            
            private final String sixthTotal="sixthTotal";
          private final String sixthBusy=  "sixthBusy";
            private final String sixthFailed="sixthFailed";
            private final String sixthNoAnswer="sixthNoAnswer";
            private final String sixthGreetingsOnHangup="sixthGreetingsOnHangup";
            private final String sixthHangUpConnect="sixthHangUpConnect";
            private final String sixthMissCall="sixthMissCall";
            private final String sixthSpoke="sixthSpoke";
            private final String sixthRequestedCallBack="sixthRequestedCallBack";
            private final String sixthOthers="sixthOthers";
            
            private final String seventhTotal="seventhTotal";
          private final String seventhBusy=  "seventhBusy";
            private final String seventhFailed="seventhFailed";
            private final String seventhNoAnswer="seventhNoAnswer";
            private final String seventhGreetingsOnHangup="seventhGreetingsOnHangup";
            private final String seventhHangUpConnect="seventhHangUpConnect";
            private final String seventhMissCall="seventhMissCall";
            private final String seventhSpoke="seventhSpoke";
            private final String seventhRequestedCallBack="seventhRequestedCallBack";
            private final String seventhOthers="seventhOthers";
            
            private final String booked="booked";
            private final String done="done";
            private final String cancelled="cancelled";
            private final String serviced="serviced";
            private final String revised="revised";
            private final String deferred="deferred";
             private final String appointment="appointment";
            private final String yetToAssign="yetToAssign";
            

            public List<DataAnalytics> readAnalytics(String date){
                List<DataAnalytics> listTemp=(List<DataAnalytics>) daDao.readPropertyByDate(date);
                List<DataAnalytics> list= new ArrayList<>();
                if(listTemp.size()>0){
                    list.add(listTemp.get(listTemp.size()-1));
                }
                return list;
            }

            public List<DataAnalytics> readRecentAnalytics() {
                    return (List<DataAnalytics>) daDao.readPropertyByRecency();
              
            }
            
            
            public void doAnalytics() {
               Long total= callDao.fetchEntitySize(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"Total");
               Long busy= callDao.fetchEntitySize(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"Busy");
               Long failed= callDao.fetchEntitySize(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"Failed");
               Long noAnswer= callDao.fetchEntitySize(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"NoAnswer");
               Long greetingsHangUp= callDao.fetchEntitySize(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"GreetingsHangUp");
               Long hangUpOnConnect= callDao.fetchEntitySize(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"HangUpOnConnect");
               Long missCall= callDao.fetchEntitySize(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"MissCall");
               Long spoke= callDao.fetchEntitySize(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"Spoke");
               Long requestedCallBack= callDao.fetchEntitySize(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"RequestedCallBack");
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
                
                DataAnalytics daFirst = new DataAnalytics();
                daFirst.setType("first");
                DataAnalytics daSecond = new DataAnalytics();
                daSecond.setType("second");
                DataAnalytics daThird = new DataAnalytics();
                daThird.setType("third");
                DataAnalytics daFourth = new DataAnalytics();
                daFourth.setType("fourth");
                DataAnalytics daFive = new DataAnalytics();
                daFive.setType("five");
                DataAnalytics daSixth = new DataAnalytics();
                daSixth.setType("sixth");
                DataAnalytics daSeventh = new DataAnalytics();
                daSeventh.setType("seventh");

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

               Long total= callDao.fetchEntitySize(fromDate, toDate,"Total");
               Long busy= callDao.fetchEntitySize(fromDate, toDate,"Busy");
               Long failed= callDao.fetchEntitySize(fromDate, toDate,"Failed");
               Long noAnswer= callDao.fetchEntitySize(fromDate, toDate,"NoAnswer");
               Long greetingsHangUp= callDao.fetchEntitySize(fromDate, toDate,"GreetingsHangUp");
               Long hangUpOnConnect= callDao.fetchEntitySize(fromDate, toDate,"HangUpOnConnect");
               Long missCall= callDao.fetchEntitySize(fromDate, toDate,"MissCall");
               Long spoke= callDao.fetchEntitySize(fromDate, toDate,"Spoke");
               Long requestedCallBack= callDao.fetchEntitySize(fromDate, toDate,"RequestedCallBack");
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

               Map<String, Long> counts=doReportingOperationSize(clientDao.fetchFreshCallEntity(fromDate, toDate));


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
               
               
                
               
               //First Call
               daFirst.setTotal(counts.get(firstTotal));
               daFirst.setBusy(counts.get(firstBusy));
               daFirst.setFailed(counts.get(firstFailed));
               daFirst.setNoAnswer(counts.get(firstNoAnswer));
               daFirst.setGreetingsHangup(counts.get(firstGreetingsOnHangup));
               daFirst.setHangupOnConnect(counts.get(firstHangUpConnect));
               daFirst.setMissCall(counts.get(firstMissCall));
               daFirst.setSpoke(counts.get(firstSpoke));
               daFirst.setRequestedCallBack(counts.get(firstRequestedCallBack));
               daFirst.setOthers(counts.get(firstOthers));
               daDao.buildEntity(daFirst);
               
               //Second Call
               daSecond.setTotal(counts.get(secondTotal));
               daSecond.setBusy(counts.get(secondBusy));
               daSecond.setFailed(counts.get(secondFailed));
               daSecond.setNoAnswer(counts.get(secondNoAnswer));
               daSecond.setGreetingsHangup(counts.get(secondGreetingsOnHangup));
               daSecond.setHangupOnConnect(counts.get(secondHangUpConnect));
               daSecond.setMissCall(counts.get(secondMissCall));
               daSecond.setSpoke(counts.get(secondSpoke));
               daSecond.setRequestedCallBack(counts.get(secondRequestedCallBack));
               daSecond.setOthers(counts.get(secondOthers));
               daDao.buildEntity(daSecond);
              
               //Third Call
               daThird.setTotal(counts.get(thirdTotal));
               daThird.setBusy(counts.get(thirdBusy));
               daThird.setFailed(counts.get(thirdFailed));
               daThird.setNoAnswer(counts.get(thirdNoAnswer));
               daThird.setGreetingsHangup(counts.get(thirdGreetingsOnHangup));
               daThird.setHangupOnConnect(counts.get(thirdHangUpConnect));
               daThird.setMissCall(counts.get(thirdMissCall));
               daThird.setSpoke(counts.get(thirdSpoke));
               daThird.setRequestedCallBack(counts.get(thirdRequestedCallBack));
               daThird.setOthers(counts.get(thirdOthers));
               daDao.buildEntity(daThird);
               
               //Fourth Call
               daFourth.setTotal(counts.get(fourthTotal));
               daFourth.setBusy(counts.get(fourthBusy));
               daFourth.setFailed(counts.get(fourthFailed));
               daFourth.setNoAnswer(counts.get(fourthNoAnswer));
               daFourth.setGreetingsHangup(counts.get(fourthGreetingsOnHangup));
               daFourth.setHangupOnConnect(counts.get(fourthHangUpConnect));
               daFourth.setMissCall(counts.get(fourthMissCall));
               daFourth.setSpoke(counts.get(fourthSpoke));
               daFourth.setRequestedCallBack(counts.get(fourthRequestedCallBack));
               daFourth.setOthers(counts.get(fourthOthers));
               daDao.buildEntity(daFourth);
               
               //Five Call
               daFive.setTotal(counts.get(fiveTotal));
               daFive.setBusy(counts.get(fiveBusy));
               daFive.setFailed(counts.get(fiveFailed));
               daFive.setNoAnswer(counts.get(fiveNoAnswer));
               daFive.setGreetingsHangup(counts.get(fiveGreetingsOnHangup));
               daFive.setHangupOnConnect(counts.get(fiveHangUpConnect));
               daFive.setMissCall(counts.get(fiveMissCall));
               daFive.setSpoke(counts.get(fiveSpoke));
               daFive.setRequestedCallBack(counts.get(fiveRequestedCallBack));
               daFive.setOthers(counts.get(fiveOthers));
               daDao.buildEntity(daFive);
               
               //Sixth Call
               daSixth.setTotal(counts.get(sixthTotal));
               daSixth.setBusy(counts.get(sixthBusy));
               daSixth.setFailed(counts.get(sixthFailed));
               daSixth.setNoAnswer(counts.get(sixthNoAnswer));
               daSixth.setGreetingsHangup(counts.get(sixthGreetingsOnHangup));
               daSixth.setHangupOnConnect(counts.get(sixthHangUpConnect));
               daSixth.setMissCall(counts.get(sixthMissCall));
               daSixth.setSpoke(counts.get(sixthSpoke));
               daSixth.setRequestedCallBack(counts.get(sixthRequestedCallBack));
               daSixth.setOthers(counts.get(sixthOthers));
               daDao.buildEntity(daSixth);
               
               //Seventh Call
               daSeventh.setTotal(counts.get(seventhTotal));
               daSeventh.setBusy(counts.get(seventhBusy));
               daSeventh.setFailed(counts.get(seventhFailed));
               daSeventh.setNoAnswer(counts.get(seventhNoAnswer));
               daSeventh.setGreetingsHangup(counts.get(seventhGreetingsOnHangup));
               daSeventh.setHangupOnConnect(counts.get(seventhHangUpConnect));
               daSeventh.setMissCall(counts.get(seventhMissCall));
               daSeventh.setSpoke(counts.get(seventhSpoke));
               daSeventh.setRequestedCallBack(counts.get(seventhRequestedCallBack));
               daSeventh.setOthers(counts.get(seventhOthers));
               daDao.buildEntity(daSeventh);
               
               

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
                    
                    counts.put(firstTotal,0l);
                    counts.put(firstBusy,0l);
                    counts.put(firstFailed,0l);
                    counts.put(firstNoAnswer,0l);
                    counts.put(firstGreetingsOnHangup,0l);
                    counts.put(firstHangUpConnect,0l);
                    counts.put(firstMissCall,0l);
                    counts.put(firstSpoke,0l);
                    counts.put(firstRequestedCallBack,0l);
                    counts.put(firstOthers,0l);
                   
                     counts.put(secondTotal,0l);
                    counts.put(secondBusy,0l);
                    counts.put(secondFailed,0l);
                    counts.put(secondNoAnswer,0l);
                    counts.put(secondGreetingsOnHangup,0l);
                    counts.put(secondHangUpConnect,0l);
                    counts.put(secondMissCall,0l);
                    counts.put(secondSpoke,0l);
                    counts.put(secondRequestedCallBack,0l);
                    counts.put(secondOthers,0l);
                    
                     counts.put(thirdTotal,0l);
                    counts.put(thirdBusy,0l);
                    counts.put(thirdFailed,0l);
                    counts.put(thirdNoAnswer,0l);
                    counts.put(thirdGreetingsOnHangup,0l);
                    counts.put(thirdHangUpConnect,0l);
                    counts.put(thirdMissCall,0l);
                    counts.put(thirdSpoke,0l);
                    counts.put(thirdRequestedCallBack,0l);
                    counts.put(thirdOthers,0l);
                    
                     counts.put(fourthTotal,0l);
                    counts.put(fourthBusy,0l);
                    counts.put(fourthFailed,0l);
                    counts.put(fourthNoAnswer,0l);
                    counts.put(fourthGreetingsOnHangup,0l);
                    counts.put(fourthHangUpConnect,0l);
                    counts.put(fourthMissCall,0l);
                    counts.put(fourthSpoke,0l);
                    counts.put(fourthRequestedCallBack,0l);
                    counts.put(fourthOthers,0l);
                    
                     counts.put(fiveTotal,0l);
                    counts.put(fiveBusy,0l);
                    counts.put(fiveFailed,0l);
                    counts.put(fiveNoAnswer,0l);
                    counts.put(fiveGreetingsOnHangup,0l);
                    counts.put(fiveHangUpConnect,0l);
                    counts.put(fiveMissCall,0l);
                    counts.put(fiveSpoke,0l);
                    counts.put(fiveRequestedCallBack,0l);
                    counts.put(fiveOthers,0l);
                    
                     counts.put(sixthTotal,0l);
                    counts.put(sixthBusy,0l);
                    counts.put(sixthFailed,0l);
                    counts.put(sixthNoAnswer,0l);
                    counts.put(sixthGreetingsOnHangup,0l);
                    counts.put(sixthHangUpConnect,0l);
                    counts.put(sixthMissCall,0l);
                    counts.put(sixthSpoke,0l);
                    counts.put(sixthRequestedCallBack,0l);
                    counts.put(sixthOthers,0l);
                    
                     counts.put(seventhTotal,0l);
                    counts.put(seventhBusy,0l);
                    counts.put(seventhFailed,0l);
                    counts.put(seventhNoAnswer,0l);
                    counts.put(seventhGreetingsOnHangup,0l);
                    counts.put(seventhHangUpConnect,0l);
                    counts.put(seventhMissCall,0l);
                    counts.put(seventhSpoke,0l);
                    counts.put(seventhRequestedCallBack,0l);
                    counts.put(seventhOthers,0l);
                 
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
                else if(i==1){
                        counts.replace(firstBusy, counts.get(firstBusy)+1);
                        counts.replace(firstTotal, counts.get(firstTotal)+1);
                }
                 else if(i==2){
                        counts.replace(secondBusy, counts.get(secondBusy)+1);
                        counts.replace(secondTotal, counts.get(secondTotal)+1);
                }
                  else if(i==3){
                        counts.replace(thirdBusy, counts.get(thirdBusy)+1);
                        counts.replace(thirdTotal, counts.get(thirdTotal)+1);
                }
                   else if(i==4){
                        counts.replace(fourthBusy, counts.get(fourthBusy)+1);
                        counts.replace(fourthTotal, counts.get(fourthTotal)+1);
                }
                   else if(i==5){
                        counts.replace(fiveBusy, counts.get(fiveBusy)+1);
                        counts.replace(fiveTotal, counts.get(fiveTotal)+1);
                }
                     else if(i==6){
                        counts.replace(sixthBusy, counts.get(sixthBusy)+1);
                        counts.replace(sixthTotal, counts.get(sixthTotal)+1);
                }
                 else if(i==7){
                        counts.replace(seventhBusy, counts.get(seventhBusy)+1);
                        counts.replace(seventhTotal, counts.get(seventhTotal)+1);
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
                else if(i==1){
                        counts.replace(firstFailed, counts.get(firstFailed)+1);
                        counts.replace(firstTotal, counts.get(firstTotal)+1);
                }
                 else if(i==2){
                        counts.replace(secondFailed, counts.get(secondFailed)+1);
                        counts.replace(secondTotal, counts.get(secondTotal)+1);
                }
                  else if(i==3){
                        counts.replace(thirdFailed, counts.get(thirdFailed)+1);
                        counts.replace(thirdTotal, counts.get(thirdTotal)+1);
                }
                   else if(i==4){
                        counts.replace(fourthFailed, counts.get(fourthFailed)+1);
                        counts.replace(fourthTotal, counts.get(fourthTotal)+1);
                }
                   else if(i==5){
                        counts.replace(fiveFailed, counts.get(fiveFailed)+1);
                        counts.replace(fiveTotal, counts.get(fiveTotal)+1);
                }
                     else if(i==6){
                        counts.replace(sixthFailed, counts.get(sixthFailed)+1);
                        counts.replace(sixthTotal, counts.get(sixthTotal)+1);
                }
                 else if(i==7){
                        counts.replace(seventhFailed, counts.get(seventhFailed)+1);
                        counts.replace(seventhTotal, counts.get(seventhTotal)+1);
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
                else if(i==1){
                        counts.replace(firstNoAnswer, counts.get(firstNoAnswer)+1);
                        counts.replace(firstTotal, counts.get(firstTotal)+1);
                }
                 else if(i==2){
                        counts.replace(secondNoAnswer, counts.get(secondNoAnswer)+1);
                        counts.replace(secondTotal, counts.get(secondTotal)+1);
                }
                  else if(i==3){
                        counts.replace(thirdNoAnswer, counts.get(thirdNoAnswer)+1);
                        counts.replace(thirdTotal, counts.get(thirdTotal)+1);
                }
                   else if(i==4){
                        counts.replace(fourthNoAnswer, counts.get(fourthNoAnswer)+1);
                        counts.replace(fourthTotal, counts.get(fourthTotal)+1);
                }
                   else if(i==5){
                        counts.replace(fiveNoAnswer, counts.get(fiveNoAnswer)+1);
                        counts.replace(fiveTotal, counts.get(fiveTotal)+1);
                }
                     else if(i==6){
                        counts.replace(sixthNoAnswer, counts.get(sixthNoAnswer)+1);
                        counts.replace(sixthTotal, counts.get(sixthTotal)+1);
                }
                 else if(i==7){
                        counts.replace(seventhNoAnswer, counts.get(seventhNoAnswer)+1);
                        counts.replace(seventhTotal, counts.get(seventhTotal)+1);
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
                else if(i==1){
                        counts.replace(firstHangUpConnect, counts.get(firstHangUpConnect)+1);
                        counts.replace(firstTotal, counts.get(firstTotal)+1);
                }
                 else if(i==2){
                        counts.replace(secondHangUpConnect, counts.get(secondHangUpConnect)+1);
                        counts.replace(secondTotal, counts.get(secondTotal)+1);
                }
                  else if(i==3){
                        counts.replace(thirdHangUpConnect, counts.get(thirdHangUpConnect)+1);
                        counts.replace(thirdTotal, counts.get(thirdTotal)+1);
                }
                   else if(i==4){
                        counts.replace(fourthHangUpConnect, counts.get(fourthHangUpConnect)+1);
                        counts.replace(fourthTotal, counts.get(fourthTotal)+1);
                }
                   else if(i==5){
                        counts.replace(fiveHangUpConnect, counts.get(fiveHangUpConnect)+1);
                        counts.replace(fiveTotal, counts.get(fiveTotal)+1);
                }
                     else if(i==6){
                        counts.replace(sixthHangUpConnect, counts.get(sixthHangUpConnect)+1);
                        counts.replace(sixthTotal, counts.get(sixthTotal)+1);
                }
                 else if(i==7){
                        counts.replace(seventhHangUpConnect, counts.get(seventhHangUpConnect)+1);
                        counts.replace(seventhTotal, counts.get(seventhTotal)+1);
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
                else if(i==1){
                        counts.replace(firstGreetingsOnHangup, counts.get(firstGreetingsOnHangup)+1);
                        counts.replace(firstTotal, counts.get(firstTotal)+1);
                }
                 else if(i==2){
                        counts.replace(secondGreetingsOnHangup, counts.get(secondGreetingsOnHangup)+1);
                        counts.replace(secondTotal, counts.get(secondTotal)+1);
                }
                  else if(i==3){
                        counts.replace(thirdGreetingsOnHangup, counts.get(thirdGreetingsOnHangup)+1);
                        counts.replace(thirdTotal, counts.get(thirdTotal)+1);
                }
                   else if(i==4){
                        counts.replace(fourthGreetingsOnHangup, counts.get(fourthGreetingsOnHangup)+1);
                        counts.replace(fourthTotal, counts.get(fourthTotal)+1);
                }
                   else if(i==5){
                        counts.replace(fiveGreetingsOnHangup, counts.get(fiveGreetingsOnHangup)+1);
                        counts.replace(fiveTotal, counts.get(fiveTotal)+1);
                }
                     else if(i==6){
                        counts.replace(sixthGreetingsOnHangup, counts.get(sixthGreetingsOnHangup)+1);
                        counts.replace(sixthTotal, counts.get(sixthTotal)+1);
                }
                 else if(i==7){
                        counts.replace(seventhGreetingsOnHangup, counts.get(seventhGreetingsOnHangup)+1);
                        counts.replace(seventhTotal, counts.get(seventhTotal)+1);
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
                else if(i==1){
                        counts.replace(firstMissCall, counts.get(firstMissCall)+1);
                        counts.replace(firstTotal, counts.get(firstTotal)+1);
                }
                 else if(i==2){
                        counts.replace(secondMissCall, counts.get(secondMissCall)+1);
                        counts.replace(secondTotal, counts.get(secondTotal)+1);
                }
                  else if(i==3){
                        counts.replace(thirdMissCall, counts.get(thirdMissCall)+1);
                        counts.replace(thirdTotal, counts.get(thirdTotal)+1);
                }
                   else if(i==4){
                        counts.replace(fourthMissCall, counts.get(fourthMissCall)+1);
                        counts.replace(fourthTotal, counts.get(fourthTotal)+1);
                }
                   else if(i==5){
                        counts.replace(fiveMissCall, counts.get(fiveMissCall)+1);
                        counts.replace(fiveTotal, counts.get(fiveTotal)+1);
                }
                     else if(i==6){
                        counts.replace(sixthMissCall, counts.get(sixthMissCall)+1);
                        counts.replace(sixthTotal, counts.get(sixthTotal)+1);
                }
                 else if(i==7){
                        counts.replace(seventhMissCall, counts.get(seventhMissCall)+1);
                        counts.replace(seventhTotal, counts.get(seventhTotal)+1);
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
                else if(i==1){
                        counts.replace(firstSpoke, counts.get(firstSpoke)+1);
                        counts.replace(firstTotal, counts.get(firstTotal)+1);
                }
                 else if(i==2){
                        counts.replace(secondSpoke, counts.get(secondSpoke)+1);
                        counts.replace(secondTotal, counts.get(secondTotal)+1);
                }
                  else if(i==3){
                        counts.replace(thirdSpoke, counts.get(thirdSpoke)+1);
                        counts.replace(thirdTotal, counts.get(thirdTotal)+1);
                }
                   else if(i==4){
                        counts.replace(fourthSpoke, counts.get(fourthSpoke)+1);
                        counts.replace(fourthTotal, counts.get(fourthTotal)+1);
                }
                   else if(i==5){
                        counts.replace(fiveSpoke, counts.get(fiveSpoke)+1);
                        counts.replace(fiveTotal, counts.get(fiveTotal)+1);
                }
                     else if(i==6){
                        counts.replace(sixthSpoke, counts.get(sixthSpoke)+1);
                        counts.replace(sixthTotal, counts.get(sixthTotal)+1);
                }
                 else if(i==7){
                        counts.replace(seventhSpoke, counts.get(seventhSpoke)+1);
                        counts.replace(seventhTotal, counts.get(seventhTotal)+1);
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
                else if(i==1){
                        counts.replace(firstOthers, counts.get(firstOthers)+1);
                        counts.replace(firstTotal, counts.get(firstTotal)+1);
                }
                 else if(i==2){
                        counts.replace(secondOthers, counts.get(secondOthers)+1);
                        counts.replace(secondTotal, counts.get(secondTotal)+1);
                }
                  else if(i==3){
                        counts.replace(thirdOthers, counts.get(thirdOthers)+1);
                        counts.replace(thirdTotal, counts.get(thirdTotal)+1);
                }
                   else if(i==4){
                        counts.replace(fourthOthers, counts.get(fourthOthers)+1);
                        counts.replace(fourthTotal, counts.get(fourthTotal)+1);
                }
                   else if(i==5){
                        counts.replace(fiveOthers, counts.get(fiveOthers)+1);
                        counts.replace(fiveTotal, counts.get(fiveTotal)+1);
                }
                     else if(i==6){
                        counts.replace(sixthOthers, counts.get(sixthOthers)+1);
                        counts.replace(sixthTotal, counts.get(sixthTotal)+1);
                }
                 else if(i==7){
                        counts.replace(seventhOthers, counts.get(seventhOthers)+1);
                        counts.replace(seventhTotal, counts.get(seventhTotal)+1);
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

    public void mailSpokeAnalytics() {
        
        
        Map<String, Long> counts=doReportingOperationSize(clientDao.fetchFreshCallEntity("2016-12-01 00:00:00", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
                long total=8766+counts.get(freshTotal);
                long spoke=2605+counts.get(freshSpoke);
                long book=2330+counts.get(booked);
                //data till november
                float spokePercentage=(spoke*100)/total;
                float bookPercentage=(book*100)/total;
                
                counts=doReportingOperationSize(clientDao.fetchFreshCallEntity(new SimpleDateFormat("yyyy-MM-dd").format(new Date())+" 00:00:00", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
                total=counts.get(freshTotal);
                spoke=counts.get(freshSpoke);
                book=counts.get(booked);
                
                if(spokePercentage!=0&&total!=0){
                    spokePercentage=(((spoke*100)/total)*100)/spokePercentage-100;
                }
                
                if(bookPercentage!=0&&total!=0){
                    bookPercentage=(((book*100)/total)*100)/bookPercentage-100;
                }
                float spokeToday=((spoke*100)/total);
                float bookToday=((book*100)/total);
                String message="";
                  message=message+"Total number of unique leads today till now is "+total+". <br/>";
                  message=message+"Fresh Spoke today : "+spoke+"  (" +spokeToday+"%)<br/>";
                  message=message+"Total Booked today : "+book+"  (" +bookToday+"%)<br/>";
                  
                if(spokePercentage>0){
                    
                                message=message+"Spoke rate has improved by "+spokePercentage+"% today. ";
                }
                else if (spokePercentage<0){
                            spokePercentage=-spokePercentage;    
                               message=message+"Spoke rate has detoriated by "+spokePercentage+"% today. ";
                }
                else {
                                message=message+"Calls not made yet. ";
                }
                     
                  if(bookPercentage>0){
                    
                      message=message+"Lead quality has improved by "+bookPercentage+"% today.";
                }
                else if (bookPercentage<0){
                            bookPercentage=-bookPercentage;    
                           message=message+"Lead quality has detoriated by "+bookPercentage+"% today.";
                              
                }
                else {
                                message=message+"Bookings not made yet. ";
                }   
                  
                  
                                 sendEmailLeadQuality("subhadeep@jubination.com",message);
                                 sendEmailLeadQuality("disha@jubination.com",message);
                                 sendEmailLeadQuality("trupti@jubination.com",message);
                                 sendEmailLeadQuality("vinay@jubination.com",message);
                                 sendEmailLeadQuality("tauseef@jubination.com",message);
                                 sendEmailLeadQuality("souvik@jubination.com",message);
                                 
                
               
    }
    
    
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
