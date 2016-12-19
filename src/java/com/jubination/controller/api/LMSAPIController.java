/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.controller.api;

import com.jubination.backend.service.core.leadcall.parallel.master.CallManager;
import com.jubination.model.pojo.crm.Beneficiaries;
import com.jubination.model.pojo.crm.Client;
import com.jubination.model.pojo.crm.Lead;
import com.jubination.model.pojo.exotel.Call;
import com.jubination.model.pojo.status.ReportStatus;
import com.jubination.service.CallMaintainService;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author MumbaiZone
 */
@Controller
public class LMSAPIController {
    @Autowired
    CallMaintainService callMaintain;
    @Autowired
    CallManager eCallHandler;
    
     @RequestMapping(value="/API/freshCall/Asdf7984sdfkjsdhfKFHDJFhshksdjflSFDAKHDfsjdhfrww",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,headers="Accept=*/*")
    public ResponseEntity freshCalls(@RequestBody Client client,HttpServletRequest request) throws IOException{
           if(client!=null){
                if(client.getTempLeadDetails()!=null&&client.getPhoneNumber()!=null&&client.getEmailId()!=null){
                    try{
                        if(eCallHandler.getStatus()){        
                            if(callMaintain.buildBackupClient(client)!=null){
                                return new ResponseEntity(HttpStatus.OK);
                            }
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    
    @RequestMapping(value="/API/getDump/Asdf7984sdfkjsdhfKFHDJFhshksdjflSFDAKHDfsjdhfrww",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,headers="Accept=*/*")
    public @ResponseBody List<Client> getDump(HttpServletRequest request) throws IOException{
            System.out.println(request.getParameter("Date"));
            List<Client> list=callMaintain.getClientDump(request.getParameter("Date"));
            for(Client c:list){
               for(Lead l:c.getLead()){
                   l.setClient(null);
                   l.setAdmin(null);
                   for(Call call:l.getCall()){
                       call.setLead(null);
                   }
                   for(Beneficiaries ben:l.getBeneficiaries()){
                       ben.setLead(null);
                   }
               }
            }
            return list;
    }
    
     @RequestMapping(value="/API/reportStatus/Asdf7984sdfkjsdhfKFHDJFhshksdjflSFDAKHDfsjdhfrww",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,headers="Accept=*/*")
    public ResponseEntity reportStatusEntry(@RequestBody ReportStatus reportStatus,HttpServletRequest request) throws IOException{
            if(callMaintain.addMissedAppointment(reportStatus)){
                return new ResponseEntity(HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}