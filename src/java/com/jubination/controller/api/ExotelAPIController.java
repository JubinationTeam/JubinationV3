/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.controller.api;

import com.jubination.backend.service.backupserver.ServerUpdater;
import com.jubination.backend.service.core.leadcall.parallel.master.CallManager;
import com.jubination.backend.service.core.leadcall.parallel.master.CallScheduler;
import com.jubination.backend.service.lms.Updater;
import com.jubination.backend.service.sendgrid.EmailService;
import com.jubination.model.pojo.admin.AdminSettings;
import com.jubination.model.pojo.crm.Client;
import com.jubination.model.pojo.exotel.Call;
import com.jubination.service.AdminMaintainService;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author MumbaiZone
 */
@Controller
public class ExotelAPIController {

    @Autowired
    CallScheduler operator;
    @Autowired
    CallManager eCallHandler;
    @Autowired
    ServerUpdater updater;
    @Autowired
     private  AdminMaintainService adminService;
    
     private  String settings="settings";
    @RequestMapping(value="/exotel/{value}",method=RequestMethod.GET)
    public ResponseEntity callUpdateGet(HttpServletRequest request,@PathVariable("value") String status, Principal principal) throws IOException {
            System.out.println("@ Stage 3"); 
            Call call= new Call();
            call.setCallFrom(request.getParameter("From"));
            call.setCallTo(request.getParameter("To"));
            call.setSid(request.getParameter("CallSid"));
            call.setCallType(request.getParameter("CallType"));
            call.setDialCallDuration(request.getParameter("DialCallDuration"));
            call.setDialWhomNumber(request.getParameter("DialWhomNumber"));
            call.setRecordingUrl(request.getParameter("RecordingUrl"));
            call.setDirection(request.getParameter("Direction"));
            call.setDateCreated(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            boolean flag=true;
            System.out.println("CallUpdate by exotel"+status);
            
            switch(status){
               case "1": 
                   call.setTrackStatus("Pressed 1. Customer spoke to us");
                   break;
               case "2":
                   call.setTrackStatus("Pressed 2. Customer requested for callback"); 
                   break;
               case "3":
                   call.setTrackStatus("Pressed 3. Customer not registered");
                   break;
               case "4":
                   call.setTrackStatus("Pressed 4. Customer spoke to us");
                   break;
               case "12":
                   call.setTrackStatus("Pressed 1. Customer did not speak to us");
                   break;
               case "42":
                   call.setTrackStatus("Pressed 4. Customer did not speak to us");
                   break;
                case "5":
                   call.setTrackStatus("Pressed none. Customer spoke to us");
                   break;
               case "52":
                   call.setTrackStatus("Pressed none. Customer did not speak to us");
                   break;
               case "6":
                 call.setTrackStatus("Pressed invalid number. Customer spoke to us");
                   break;
               case "62":
                   call.setTrackStatus("Pressed invalid number. Customer did not speak to us");
                   break;
               case "13":
                 call.setTrackStatus("Pressed pressed 1. Confirmed booking");
                   break;
               case "14":
                 call.setTrackStatus("Repeat Client pick up");
                   break;   
                case "15":
                call.setTrackStatus("Repeat Client did not pick up");
                   break;    
                   
                 case "freshleadBook":
                call.setTrackStatus("Automated Booking Order");
                   break;  
               case "agent":
                  for(Client client:eCallHandler.getClientStage2()){
                      if(request.getParameter("From").contains(client.getPhoneNumber())&&request.getParameter("Status").equals("busy")){
                          client.setRealTimeData(request.getParameter("DialWhomNumber"));
                          sendTestEmail("Picked up by "+request.getParameter("DialWhomNumber")+". Called "+client.getPhoneNumber()+", "+client.getName());
                      }
                  }
               default:
                   flag=false;
                   System.out.println("Exotel details. Not an option");
                   break;
           }
            
           updater.forwardToBackupServer(request);
                    
            if(flag){
                operator.doStageThreeCall(call);
                return new ResponseEntity(HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
   
     private void sendTestEmail(String text){
           AdminSettings adminSettings = adminService.readSettings(settings);
            new EmailService("souvik@jubination.com","picked up",
                                          text+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),adminSettings.getMyUsername(),
                    adminSettings.getMyPassword(),
                    adminSettings.getAuth(),
                    adminSettings.getStarttls(),
                    adminSettings.getHost(),adminSettings.getPort(),adminSettings.getSendgridApi()).start();
            
     }

}
