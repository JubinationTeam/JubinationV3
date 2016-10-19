/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.controller;

import com.jubination.backend.call.CallBox;
import com.jubination.backend.freshcall.parallel.master.CallOperator;
import com.jubination.backend.freshcall.parallel.master.CallManager;
import com.jubination.model.pojo.Admin;
import com.jubination.model.pojo.Call;
import com.jubination.model.pojo.Client;
import com.jubination.model.pojo.Lead;
import com.jubination.service.AdminMaintainService;
import com.jubination.service.CallMaintainService;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author MumbaiZone
 */

@Controller
public class CallController {
    @Autowired
CallMaintainService callMaintain;
    @Autowired
    AdminMaintainService adminMaintain;
    @Autowired
    CallOperator operator;
      @Autowired
CallManager eCallHandler;
        @Autowired
CallBox callHandler;

        @RequestMapping(value="/admin/callcustom/call/lead",method = RequestMethod.POST)
    public ModelAndView callCustomLeads(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallcustom");
        
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            
            if(request.getParameter("numbers")!=null){
                operator.doLeadCall(request.getParameter("numbers"));
                model.addObject("message", "Keep Calm and attend calls..:P");
                return model;
            }
            model.addObject("message", "Error during call");
            return model;
       
        
    }
       @RequestMapping(value="/admin/callcustom/call/cust",method = RequestMethod.POST)
    public ModelAndView callCustomCustomers(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallcustom");
        
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            
            if(request.getParameter("numbers")!=null){
                operator.doCustCall(request.getParameter("numbers"));
                model.addObject("message", "Keep Calm and attend calls..:P");
                return model;
            }
            model.addObject("message", "Error during call");
            return model;
       
        
    }
     @RequestMapping(value="/admin/callcustom")
    public ModelAndView customCallInterface(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallcustom");
                    model.addObject("ex",callHandler.getExecutives());
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            
            return model;
       
        
    }
    
     @RequestMapping(value="/admin/setExecs/custom")
    public ModelAndView setCustomExecsCustom(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallcustom");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
        callHandler.setExecutives(Integer.parseInt(request.getParameter("ex")));
        
            model.addObject("ex",request.getParameter("ex"));
            model.addObject("message","Processed");
        
        
                                      
        
           return model;
       
        
    }
    
     @RequestMapping(value="/admin/callinterface")
    public ModelAndView callInterface(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallinterface");
        
         model.addObject("clientStage",eCallHandler.getRealTimeInCall());
              model.addObject("clientStage1",eCallHandler.getClientStage1());
         model.addObject("clientStage2",eCallHandler.getClientStage2());
           model.addObject("callStage3",eCallHandler.getStageThreeUpdates()); 
            return model;
       
        
    }
     @RequestMapping(value="/admin/callsettings")
    public ModelAndView callSettings(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallsettings");
        
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
        model.addObject("sFlag1", eCallHandler.getStatus());
//        model.addObject("sFlag2", eCallHandler.isStage2());
//        model.addObject("sFlag3", eCallHandler.isStage3());
        model.addObject("sCount1", eCallHandler.getClientStage1().size());
        model.addObject("sCount2", eCallHandler.getClientStage2().size());
        model.addObject("sCount3", eCallHandler.getStageThreeUpdates().size());
        
          model.addObject("sCountCC1", callHandler.getNumbers().size());
        model.addObject("sCountCC2", callHandler.getSids().size());
        model.addObject("sCountCC3", callHandler.getStageThreeUpdates().size());
        model.addObject("followUpCount", operator.getCount());
        model.addObject("followNoon", operator.isNoonFlag());
        model.addObject("followAfternoon", operator.isAfternoonFlag());
        model.addObject("followMorn", operator.isMornFlag());
        model.addObject("followLunch", operator.isLunchFlag());
        model.addObject("followEvening", operator.isEveningFlag());
        
        model.addObject("cs1", callHandler.isFlag());
        model.addObject("cs2", callHandler.isCheckFlag());
//        model.addObject("s1", eCallHandler.isFlag1());
//        model.addObject("s2",eCallHandler.isFlag2());
            return model;
       
        
    }
    
        @RequestMapping(value = "/admin/callanalytics/get")
	public  ModelAndView adminLoginCheck(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("adminpage");
            model.addObject("analytics",callMaintain.readAnalytics(request.getParameter("date")));
            
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            
            return model;
}
    
    @RequestMapping(value="/admin/callsettings/set/count")
    public ModelAndView callSettingsSetCount(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallsettings");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
        operator.setCount(Integer.parseInt(request.getParameter("count")));
           model.addObject("sFlag1", eCallHandler.getStatus());
        model.addObject("sCount1", eCallHandler.getClientStage1().size());
        model.addObject("sCount2", eCallHandler.getClientStage2().size());
        model.addObject("sCount3", eCallHandler.getStageThreeUpdates().size());
          model.addObject("sCountCC1", callHandler.getNumbers().size());
        model.addObject("sCountCC2", callHandler.getSids().size());
        model.addObject("sCountCC3", callHandler.getStageThreeUpdates().size());
        
        model.addObject("followUpCount", operator.getCount());
        model.addObject("followNoon", operator.isNoonFlag());
        model.addObject("followAfternoon", operator.isAfternoonFlag());
       model.addObject("followMorn", operator.isMornFlag());
        model.addObject("followLunch", operator.isLunchFlag());
        model.addObject("followEvening", operator.isEveningFlag());
        
            return model;
       
        
    }
       @RequestMapping(value="/admin/callsettings/stage1/on")
    public ModelAndView callSettingsSwitchOnStage1(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallsettings");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
        eCallHandler.setStatus(true);
       model.addObject("sFlag1", eCallHandler.getStatus());
        model.addObject("sCount1", eCallHandler.getClientStage1().size());
        model.addObject("sCount2", eCallHandler.getClientStage2().size());
        model.addObject("sCount3", eCallHandler.getStageThreeUpdates().size());
          model.addObject("sCountCC1", callHandler.getNumbers().size());
        model.addObject("sCountCC2", callHandler.getSids().size());
        model.addObject("sCountCC3", callHandler.getStageThreeUpdates().size());
        
        model.addObject("followUpCount", operator.getCount());
        model.addObject("followNoon", operator.isNoonFlag());
        model.addObject("followAfternoon", operator.isAfternoonFlag());
       model.addObject("followMorn", operator.isMornFlag());
        model.addObject("followLunch", operator.isLunchFlag());
        model.addObject("followEvening", operator.isEveningFlag());
        
            return model;
       
        
    }
    @RequestMapping(value="/admin/callsettings/stage2/on")
    public ModelAndView callSettingsSwitchOnStage2(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallsettings");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
           model.addObject("sFlag1", eCallHandler.getStatus());
        model.addObject("sCount1", eCallHandler.getClientStage1().size());
        model.addObject("sCount2", eCallHandler.getClientStage2().size());
        model.addObject("sCount3", eCallHandler.getStageThreeUpdates().size());
          model.addObject("sCountCC1", callHandler.getNumbers().size());
        model.addObject("sCountCC2", callHandler.getSids().size());
        model.addObject("sCountCC3", callHandler.getStageThreeUpdates().size());
        
        model.addObject("followUpCount", operator.getCount());
        model.addObject("followNoon", operator.isNoonFlag());
        model.addObject("followAfternoon", operator.isAfternoonFlag());
       model.addObject("followMorn", operator.isMornFlag());
        model.addObject("followLunch", operator.isLunchFlag());
        model.addObject("followEvening", operator.isEveningFlag());
        
            return model;
       
        
    }
    @RequestMapping(value="/admin/callsettings/stage3/on")
    public ModelAndView callSettingsSwitchOnStage3(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallsettings");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
         model.addObject("sFlag1", eCallHandler.getStatus());
        model.addObject("sCount1", eCallHandler.getClientStage1().size());
        model.addObject("sCount2", eCallHandler.getClientStage2().size());
        model.addObject("sCount3", eCallHandler.getStageThreeUpdates().size());
          model.addObject("sCountCC1", callHandler.getNumbers().size());
        model.addObject("sCountCC2", callHandler.getSids().size());
        model.addObject("sCountCC3", callHandler.getStageThreeUpdates().size());
        
        model.addObject("followUpCount", operator.getCount());
        model.addObject("followNoon", operator.isNoonFlag());
        model.addObject("followAfternoon", operator.isAfternoonFlag());
       model.addObject("followMorn", operator.isMornFlag());
        model.addObject("followLunch", operator.isLunchFlag());
        model.addObject("followEvening", operator.isEveningFlag());
        
            return model;
       
        
    }
       @RequestMapping(value="/admin/callsettings/stage1/off")
    public ModelAndView callSettingsSwitchOffStage1(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallsettings");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
        eCallHandler.setStatus(false);
         model.addObject("sFlag1", eCallHandler.getStatus());
        model.addObject("sCount1", eCallHandler.getClientStage1().size());
        model.addObject("sCount2", eCallHandler.getClientStage2().size());
        model.addObject("sCount3", eCallHandler.getStageThreeUpdates().size());
          model.addObject("sCountCC1", callHandler.getNumbers().size());
        model.addObject("sCountCC2", callHandler.getSids().size());
        model.addObject("sCountCC3", callHandler.getStageThreeUpdates().size());
        
        model.addObject("followUpCount", operator.getCount());
        model.addObject("followNoon", operator.isNoonFlag());
        model.addObject("followAfternoon", operator.isAfternoonFlag());
      model.addObject("followMorn", operator.isMornFlag());
        model.addObject("followLunch", operator.isLunchFlag());
        model.addObject("followEvening", operator.isEveningFlag());
        
            return model;
       
        
    }
    @RequestMapping(value="/admin/callsettings/stage2/off")
    public ModelAndView callSettingsSwitchOffStage2(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallsettings");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
          model.addObject("sFlag1", eCallHandler.getStatus());
        model.addObject("sCount1", eCallHandler.getClientStage1().size());
        model.addObject("sCount2", eCallHandler.getClientStage2().size());
        model.addObject("sCount3", eCallHandler.getStageThreeUpdates().size());
          model.addObject("sCountCC1", callHandler.getNumbers().size());
        model.addObject("sCountCC2", callHandler.getSids().size());
        model.addObject("sCountCC3", callHandler.getStageThreeUpdates().size());
        
        model.addObject("followUpCount", operator.getCount());
        model.addObject("followNoon", operator.isNoonFlag());
        model.addObject("followAfternoon", operator.isAfternoonFlag());
        model.addObject("followMorn", operator.isMornFlag());
        model.addObject("followLunch", operator.isLunchFlag());
        model.addObject("followEvening", operator.isEveningFlag());
        
            return model;
       
        
    }
    @RequestMapping(value="/admin/callsettings/stage3/off")
    public ModelAndView callSettingsSwitchOffStage3(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallsettings");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
          model.addObject("sFlag1", eCallHandler.getStatus());
        model.addObject("sCount1", eCallHandler.getClientStage1().size());
        model.addObject("sCount2", eCallHandler.getClientStage2().size());
        model.addObject("sCount3", eCallHandler.getStageThreeUpdates().size());
          model.addObject("sCountCC1", callHandler.getNumbers().size());
        model.addObject("sCountCC2", callHandler.getSids().size());
        model.addObject("sCountCC3", callHandler.getStageThreeUpdates().size());
        
        model.addObject("followUpCount", operator.getCount());
        model.addObject("followNoon", operator.isNoonFlag());
        model.addObject("followAfternoon", operator.isAfternoonFlag());
        model.addObject("followMorn", operator.isMornFlag());
        model.addObject("followLunch", operator.isLunchFlag());
        model.addObject("followEvening", operator.isEveningFlag());
        
            return model;
       
        
    }
       @RequestMapping(value="/admin/callsettings/stage1/flush")
    public ModelAndView callSettingsFlushStage1Numbers(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallsettings");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
        eCallHandler.getClientStage1().clear();
        model.addObject("sFlag1", eCallHandler.getStatus());
        model.addObject("sCount1", eCallHandler.getClientStage1().size());
        model.addObject("sCount2", eCallHandler.getClientStage2().size());
        model.addObject("sCount3", eCallHandler.getStageThreeUpdates().size());
          model.addObject("sCountCC1", callHandler.getNumbers().size());
        model.addObject("sCountCC2", callHandler.getSids().size());
        model.addObject("sCountCC3", callHandler.getStageThreeUpdates().size());
        
        model.addObject("followUpCount", operator.getCount());
        model.addObject("followNoon", operator.isNoonFlag());
        model.addObject("followAfternoon", operator.isAfternoonFlag());
        model.addObject("followMorn", operator.isMornFlag());
        model.addObject("followLunch", operator.isLunchFlag());
        model.addObject("followEvening", operator.isEveningFlag());
        
            return model;
       
        
    }
    @RequestMapping(value="/admin/callsettings/stage2/flush")
    public ModelAndView callSettingsFlushStage2(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallsettings");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
        eCallHandler.getClientStage2().clear();
        model.addObject("sFlag1", eCallHandler.getStatus());
        model.addObject("sCount1", eCallHandler.getClientStage1().size());
        model.addObject("sCount2", eCallHandler.getClientStage2().size());
        model.addObject("sCount3", eCallHandler.getStageThreeUpdates().size());
          model.addObject("sCountCC1", callHandler.getNumbers().size());
        model.addObject("sCountCC2", callHandler.getSids().size());
        model.addObject("sCountCC3", callHandler.getStageThreeUpdates().size());
        
        model.addObject("followUpCount", operator.getCount());
        model.addObject("followNoon", operator.isNoonFlag());
        model.addObject("followAfternoon", operator.isAfternoonFlag());
        model.addObject("followMorn", operator.isMornFlag());
        model.addObject("followLunch", operator.isLunchFlag());
        model.addObject("followEvening", operator.isEveningFlag());
            return model;
       
        
    }
    @RequestMapping(value="/admin/callsettings/stage3/flush")
    public ModelAndView callSettingsFlushStage3(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallsettings");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
        eCallHandler.getStageThreeUpdates().clear();
         model.addObject("sFlag1", eCallHandler.getStatus());
        model.addObject("sCount1", eCallHandler.getClientStage1().size());
        model.addObject("sCount2", eCallHandler.getClientStage2().size());
        model.addObject("sCount3", eCallHandler.getStageThreeUpdates().size());
          model.addObject("sCountCC1", callHandler.getNumbers().size());
        model.addObject("sCountCC2", callHandler.getSids().size());
        model.addObject("sCountCC3", callHandler.getStageThreeUpdates().size());
        
        
        model.addObject("followUpCount", operator.getCount());
        model.addObject("followNoon", operator.isNoonFlag());
        model.addObject("followAfternoon", operator.isAfternoonFlag());
        model.addObject("followMorn", operator.isMornFlag());
        model.addObject("followLunch", operator.isLunchFlag());
        model.addObject("followEvening", operator.isEveningFlag());
            return model;
       
        
    }
   
 
    
    
    
    
    
     
       @RequestMapping(value="/admin/callsettings/cc/stage1/flush")
    public ModelAndView callSettingsFlushCCStage1Numbers(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallsettings");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
        callHandler.getNumbers().clear();
        callHandler.setFlag(false);
        model.addObject("sFlag1", eCallHandler.getStatus());
        model.addObject("sCount1", eCallHandler.getClientStage1().size());
        model.addObject("sCount2", eCallHandler.getClientStage2().size());
        model.addObject("sCount3", eCallHandler.getStageThreeUpdates().size());
          model.addObject("sCountCC1", callHandler.getNumbers().size());
        model.addObject("sCountCC2", callHandler.getSids().size());
        model.addObject("sCountCC3", callHandler.getStageThreeUpdates().size());
        
        model.addObject("followUpCount", operator.getCount());
        model.addObject("followNoon", operator.isNoonFlag());
        model.addObject("followAfternoon", operator.isAfternoonFlag());
        model.addObject("followMorn", operator.isMornFlag());
        model.addObject("followLunch", operator.isLunchFlag());
        model.addObject("followEvening", operator.isEveningFlag());
        
            return model;
       
        
    }
    @RequestMapping(value="/admin/callsettings/cc/stage2/flush")
    public ModelAndView callSettingsFlushCCStage2(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallsettings");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
        callHandler.getSids().clear();
        callHandler.setCheckFlag(false);
        model.addObject("sFlag1", eCallHandler.getStatus());
        model.addObject("sCount1", eCallHandler.getClientStage1().size());
        model.addObject("sCount2", eCallHandler.getClientStage2().size());
        model.addObject("sCount3", eCallHandler.getStageThreeUpdates().size());
          model.addObject("sCountCC1", callHandler.getNumbers().size());
        model.addObject("sCountCC2", callHandler.getSids().size());
        model.addObject("sCountCC3", callHandler.getStageThreeUpdates().size());
        
        model.addObject("followUpCount", operator.getCount());
        model.addObject("followNoon", operator.isNoonFlag());
        model.addObject("followAfternoon", operator.isAfternoonFlag());
        model.addObject("followMorn", operator.isMornFlag());
        model.addObject("followLunch", operator.isLunchFlag());
        model.addObject("followEvening", operator.isEveningFlag());
            return model;
       
        
    }
    @RequestMapping(value="/admin/callsettings/cc/stage3/flush")
    public ModelAndView callSettingsFlushCCStage3(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallsettings");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
        callHandler.getStageThreeUpdates().clear();
         model.addObject("sFlag1", eCallHandler.getStatus());
        model.addObject("sCount1", eCallHandler.getClientStage1().size());
        model.addObject("sCount2", eCallHandler.getClientStage2().size());
        model.addObject("sCount3", eCallHandler.getStageThreeUpdates().size());
          model.addObject("sCountCC1", callHandler.getNumbers().size());
        model.addObject("sCountCC2", callHandler.getSids().size());
        model.addObject("sCountCC3", callHandler.getStageThreeUpdates().size());
        
        model.addObject("followUpCount", operator.getCount());
        model.addObject("followNoon", operator.isNoonFlag());
        model.addObject("followAfternoon", operator.isAfternoonFlag());
        model.addObject("followMorn", operator.isMornFlag());
        model.addObject("followLunch", operator.isLunchFlag());
        model.addObject("followEvening", operator.isEveningFlag());
            return model;
       
        
    }
   
 
    
    
    
    
    
    
    
    
    
    
    
    
     @RequestMapping(value="/admin/callsettings/followup/noon/on")
    public ModelAndView noonFollowupOn(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallsettings");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
        operator.setNoonFlag(true);
         model.addObject("sFlag1", eCallHandler.getStatus());
//        model.addObject("sFlag2", eCallHandler.isStage2());
//        model.addObject("sFlag3", eCallHandler.isStage3());
        model.addObject("sCount1", eCallHandler.getClientStage1().size());
        model.addObject("sCount2", eCallHandler.getClientStage2().size());
        model.addObject("sCount3", eCallHandler.getStageThreeUpdates().size());
          model.addObject("sCountCC1", callHandler.getNumbers().size());
        model.addObject("sCountCC2", callHandler.getSids().size());
        model.addObject("sCountCC3", callHandler.getStageThreeUpdates().size());
        
        model.addObject("followUpCount", operator.getCount());
        model.addObject("followNoon", operator.isNoonFlag());
        model.addObject("followAfternoon", operator.isAfternoonFlag());
        model.addObject("followMorn", operator.isMornFlag());
        model.addObject("followLunch", operator.isLunchFlag());
        model.addObject("followEvening", operator.isEveningFlag());
            return model;
       
        
    }    
    @RequestMapping(value="/admin/callsettings/followup/noon/off")
    public ModelAndView noonFollowupOff(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallsettings");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
        operator.setNoonFlag(false);
         model.addObject("sFlag1", eCallHandler.getStatus());
//        model.addObject("sFlag2", eCallHandler.isStage2());
//        model.addObject("sFlag3", eCallHandler.isStage3());
        model.addObject("sCount1", eCallHandler.getClientStage1().size());
        model.addObject("sCount2", eCallHandler.getClientStage2().size());
        model.addObject("sCount3", eCallHandler.getStageThreeUpdates().size());
          model.addObject("sCountCC1", callHandler.getNumbers().size());
        model.addObject("sCountCC2", callHandler.getSids().size());
        model.addObject("sCountCC3", callHandler.getStageThreeUpdates().size());
        
        model.addObject("followUpCount", operator.getCount());
        model.addObject("followNoon", operator.isNoonFlag());
        model.addObject("followAfternoon", operator.isAfternoonFlag());
        model.addObject("followMorn", operator.isMornFlag());
        model.addObject("followLunch", operator.isLunchFlag());
        model.addObject("followEvening", operator.isEveningFlag());
            return model;
       
        
    }    
    
    @RequestMapping(value="/admin/callsettings/followup/afternoon/on")
    public ModelAndView afternoonFollowupOn(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallsettings");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
        operator.setAfternoonFlag(true);
         model.addObject("sFlag1", eCallHandler.getStatus());
//        model.addObject("sFlag2", eCallHandler.isStage2());
//        model.addObject("sFlag3", eCallHandler.isStage3());
        model.addObject("sCount1", eCallHandler.getClientStage1().size());
        model.addObject("sCount2", eCallHandler.getClientStage2().size());
        model.addObject("sCount3", eCallHandler.getStageThreeUpdates().size());
          model.addObject("sCountCC1", callHandler.getNumbers().size());
        model.addObject("sCountCC2", callHandler.getSids().size());
        model.addObject("sCountCC3", callHandler.getStageThreeUpdates().size());
        
        model.addObject("followUpCount", operator.getCount());
        model.addObject("followNoon", operator.isNoonFlag());
        model.addObject("followAfternoon", operator.isAfternoonFlag());
       model.addObject("followMorn", operator.isMornFlag());
        model.addObject("followLunch", operator.isLunchFlag());
        model.addObject("followEvening", operator.isEveningFlag());
            return model;
       
        
    }    
    @RequestMapping(value="/admin/callsettings/followup/afternoon/off")
    public ModelAndView afternoonFollowupOff(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallsettings");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
        operator.setAfternoonFlag(false);
         model.addObject("sFlag1", eCallHandler.getStatus());
//        model.addObject("sFlag2", eCallHandler.isStage2());
//        model.addObject("sFlag3", eCallHandler.isStage3());
        model.addObject("sCount1", eCallHandler.getClientStage1().size());
        model.addObject("sCount2", eCallHandler.getClientStage2().size());
        model.addObject("sCount3", eCallHandler.getStageThreeUpdates().size());
          model.addObject("sCountCC1", callHandler.getNumbers().size());
        model.addObject("sCountCC2", callHandler.getSids().size());
        model.addObject("sCountCC3", callHandler.getStageThreeUpdates().size());
        
        model.addObject("followUpCount", operator.getCount());
        model.addObject("followNoon", operator.isNoonFlag());
        model.addObject("followAfternoon", operator.isAfternoonFlag());
        model.addObject("followMorn", operator.isMornFlag());
        model.addObject("followLunch", operator.isLunchFlag());
        model.addObject("followEvening", operator.isEveningFlag());
            return model;
       
        
    }    
    
    
    @RequestMapping(value="/admin/callsettings/followup/morn/on")
    public ModelAndView mornFollowupOn(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallsettings");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
        operator.setMornFlag(true);
         model.addObject("sFlag1", eCallHandler.getStatus());
//        model.addObject("sFlag2", eCallHandler.isStage2());
//        model.addObject("sFlag3", eCallHandler.isStage3());
        model.addObject("sCount1", eCallHandler.getClientStage1().size());
        model.addObject("sCount2", eCallHandler.getClientStage2().size());
        model.addObject("sCount3", eCallHandler.getStageThreeUpdates().size());
          model.addObject("sCountCC1", callHandler.getNumbers().size());
        model.addObject("sCountCC2", callHandler.getSids().size());
        model.addObject("sCountCC3", callHandler.getStageThreeUpdates().size());
        
        model.addObject("followUpCount", operator.getCount());
        model.addObject("followNoon", operator.isNoonFlag());
        model.addObject("followAfternoon", operator.isAfternoonFlag());
        model.addObject("followMorn", operator.isMornFlag());
        model.addObject("followLunch", operator.isLunchFlag());
        model.addObject("followEvening", operator.isEveningFlag());
            return model;
       
        
    }    
    @RequestMapping(value="/admin/callsettings/followup/morn/off")
    public ModelAndView mornFollowupOff(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallsettings");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
        operator.setMornFlag(false);
         model.addObject("sFlag1", eCallHandler.getStatus());
//        model.addObject("sFlag2", eCallHandler.isStage2());
//        model.addObject("sFlag3", eCallHandler.isStage3());
        model.addObject("sCount1", eCallHandler.getClientStage1().size());
        model.addObject("sCount2", eCallHandler.getClientStage2().size());
        model.addObject("sCount3", eCallHandler.getStageThreeUpdates().size());
          model.addObject("sCountCC1", callHandler.getNumbers().size());
        model.addObject("sCountCC2", callHandler.getSids().size());
        model.addObject("sCountCC3", callHandler.getStageThreeUpdates().size());
        
        model.addObject("followUpCount", operator.getCount());
        model.addObject("followNoon", operator.isNoonFlag());
        model.addObject("followAfternoon", operator.isAfternoonFlag());
        model.addObject("followMorn", operator.isMornFlag());
        model.addObject("followLunch", operator.isLunchFlag());
        model.addObject("followEvening", operator.isEveningFlag());
            return model;
       
        
    }    
    
    @RequestMapping(value="/admin/callsettings/followup/lunch/on")
    public ModelAndView lunchFollowupOn(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallsettings");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
        operator.setLunchFlag(true);
         model.addObject("sFlag1", eCallHandler.getStatus());
//        model.addObject("sFlag2", eCallHandler.isStage2());
//        model.addObject("sFlag3", eCallHandler.isStage3());
        model.addObject("sCount1", eCallHandler.getClientStage1().size());
        model.addObject("sCount2", eCallHandler.getClientStage2().size());
        model.addObject("sCount3", eCallHandler.getStageThreeUpdates().size());
          model.addObject("sCountCC1", callHandler.getNumbers().size());
        model.addObject("sCountCC2", callHandler.getSids().size());
        model.addObject("sCountCC3", callHandler.getStageThreeUpdates().size());
        
        model.addObject("followUpCount", operator.getCount());
        model.addObject("followNoon", operator.isNoonFlag());
        model.addObject("followAfternoon", operator.isAfternoonFlag());
        model.addObject("followMorn", operator.isMornFlag());
        model.addObject("followLunch", operator.isLunchFlag());
        model.addObject("followEvening", operator.isEveningFlag());
            return model;
       
        
    }    
    @RequestMapping(value="/admin/callsettings/followup/lunch/off")
    public ModelAndView LunchFollowupOff(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallsettings");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
        operator.setLunchFlag(false);
         model.addObject("sFlag1", eCallHandler.getStatus());
//        model.addObject("sFlag2", eCallHandler.isStage2());
//        model.addObject("sFlag3", eCallHandler.isStage3());
        model.addObject("sCount1", eCallHandler.getClientStage1().size());
        model.addObject("sCount2", eCallHandler.getClientStage2().size());
        model.addObject("sCount3", eCallHandler.getStageThreeUpdates().size());
          model.addObject("sCountCC1", callHandler.getNumbers().size());
        model.addObject("sCountCC2", callHandler.getSids().size());
        model.addObject("sCountCC3", callHandler.getStageThreeUpdates().size());
        
        model.addObject("followUpCount", operator.getCount());
        model.addObject("followNoon", operator.isNoonFlag());
        model.addObject("followAfternoon", operator.isAfternoonFlag());
        model.addObject("followMorn", operator.isMornFlag());
        model.addObject("followLunch", operator.isLunchFlag());
        model.addObject("followEvening", operator.isEveningFlag());
            return model;
       
        
    }    
    
    @RequestMapping(value="/admin/callsettings/followup/evening/on")
    public ModelAndView eveningFollowupOn(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallsettings");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
        operator.setEveningFlag(true);
         model.addObject("sFlag1", eCallHandler.getStatus());
//        model.addObject("sFlag2", eCallHandler.isStage2());
//        model.addObject("sFlag3", eCallHandler.isStage3());
        model.addObject("sCount1", eCallHandler.getClientStage1().size());
        model.addObject("sCount2", eCallHandler.getClientStage2().size());
        model.addObject("sCount3", eCallHandler.getStageThreeUpdates().size());
          model.addObject("sCountCC1", callHandler.getNumbers().size());
        model.addObject("sCountCC2", callHandler.getSids().size());
        model.addObject("sCountCC3", callHandler.getStageThreeUpdates().size());
        
        model.addObject("followUpCount", operator.getCount());
        model.addObject("followNoon", operator.isNoonFlag());
        model.addObject("followAfternoon", operator.isAfternoonFlag());
        model.addObject("followMorn", operator.isMornFlag());
        model.addObject("followLunch", operator.isLunchFlag());
        model.addObject("followEvening", operator.isEveningFlag());
            return model;
       
        
    }    
    @RequestMapping(value="/admin/callsettings/followup/evening/off")
    public ModelAndView eveningFollowupOff(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallsettings");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
        operator.setEveningFlag(false);
         model.addObject("sFlag1", eCallHandler.getStatus());
//        model.addObject("sFlag2", eCallHandler.isStage2());
//        model.addObject("sFlag3", eCallHandler.isStage3());
        model.addObject("sCount1", eCallHandler.getClientStage1().size());
        model.addObject("sCount2", eCallHandler.getClientStage2().size());
        model.addObject("sCount3", eCallHandler.getStageThreeUpdates().size());
          model.addObject("sCountCC1", callHandler.getNumbers().size());
        model.addObject("sCountCC2", callHandler.getSids().size());
        model.addObject("sCountCC3", callHandler.getStageThreeUpdates().size());
        
        model.addObject("followUpCount", operator.getCount());
        model.addObject("followNoon", operator.isNoonFlag());
        model.addObject("followAfternoon", operator.isAfternoonFlag());
        model.addObject("followMorn", operator.isMornFlag());
        model.addObject("followLunch", operator.isLunchFlag());
        model.addObject("followEvening", operator.isEveningFlag());
            return model;
       
        
    }    
    
    
    
    
    
    @RequestMapping(value="/admin/callsettings/do/analytics")
    public ModelAndView doCallAnalytics(HttpServletRequest request, Principal principal) throws IOException {
            ModelAndView model= new ModelAndView("adminpage");
             callMaintain.doAnalytics();
            
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            
            return model;
}
     @RequestMapping(value="/admin/callupdates")
    public ModelAndView callUpdates(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallupdates");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
        
                                      
        
           return model;
       
        
    }
    
    @RequestMapping(value="/admin/callrecords")
    public ModelAndView callRecords(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallrecords");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
        model.addObject("message", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        
                                      
        
           return model;
       
        
    }
     @RequestMapping(value="/admin/callupdates/values")
    public ModelAndView callUpdateValues(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallupdates");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
        if(request.getParameter("leadId")!=null&&!request.getParameter("leadId").isEmpty()){
        model.addObject("lead",callMaintain.getClientDetails(request.getParameter("leadId")));
        
    }
        model.addObject("message", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        
                                      
        
           return model;
       
        
    }
    
   
      @RequestMapping(value="/admin/setExecs/auto")
    public ModelAndView setCustomExecsAuto(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("adminpage");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
        eCallHandler.setExecutives(Integer.parseInt(request.getParameter("ex")));
        
            model.addObject("ex",request.getParameter("ex"));
            model.addObject("message","Processed");
        
        
                                      
        
           return model;
       
        
    }
     @RequestMapping(value="/admin/callupdates/update")
    public ModelAndView updateClient(HttpServletRequest request, Principal principal) throws IOException {
                                    ModelAndView model= new ModelAndView("admincallupdates");
                                    Admin admin=adminMaintain.checkPresence(new Admin(principal.getName()));
                                    model.addObject("admin",admin);
                                    String id=request.getParameter("id");
                                    String comment=request.getParameter("comment");
                                    String initialComment=request.getParameter("initialComment");
                                    String booked =request.getParameter("booked");
                                    String email =request.getParameter("email");
                                    String date =request.getParameter("date");
                                    String address=request.getParameter("address");
                                    String age =request.getParameter("age");
                                    String gender =request.getParameter("gender");
                                    String city =request.getParameter("city");
                                    String pincode =request.getParameter("pincode");
                                    String leadStatus =request.getParameter("leadStatus");
                                    String number =request.getParameter("number");
                                    if(id!=null){
                                            if(!id.isEmpty()){
                                                                            Lead lead=null;
                                                                            lead = new Lead();
                                                                            lead.setLeadId(id);
                                                                            lead=callMaintain.readLead(lead);
                                                                            if(lead!=null){
                                                                                                        Client client=lead.getClient();
                                                                                                        lead.setAdmin(admin);
                                                                                                           lead.setNotification(false);
                                                                                                         if(leadStatus!=null){
                                                                                                             if(!leadStatus.isEmpty()){
                                                                                                                 lead.setLeadStatus(leadStatus);
                                                                                                                            if(leadStatus.equals("Lead sent to Thyrocare")){
                                                                                                                                
                                                                                                                                    List<Lead> leadList=callMaintain.getDuplicateLeads(number);
                                                                                                                                    for(Lead l:leadList){
                                                                                                                                                    l.setBooked(true);
                                                                                                                                                    l.setNotification(false);
                                                                                                                                                    l.setPending(false);
                                                                                                                                                    l.setCount(0);
                                                                                                                                                    callMaintain.updateLeadOnly(l);
                                                                                                                                    }
                                                                                                                                    lead.setBooked(true);
                                                                                                                                    lead.setNotification(false);
                                                                                                                                    lead.setCount(0);
                                                                                                                            }
                                                                                                                 
                                                                                                             }
                                                                                                        }
                                                                                                       if(leadStatus!=null){ 
                                                                                                        if(leadStatus.contains("Follow")&&date!=null){
                                                                                                            if(!date.isEmpty()){
                                                                                                                lead.setNotification(true);
                                                                                                                lead.setFollowUpDate(date);
                                                                                                            }
                                                                                                        }
                                                                                                       }
                                                                                                        if(comment!=null){
                                                                                                            if(!comment.isEmpty()){
                                                                                                                    lead.setComments(comment);
                                                                                                            }
                                                                                                        }
                                                                                                        if(address!=null){
                                                                                                            address=address.trim();
                                                                                                            if(!address.isEmpty()){
                                                                                                                client.setAddress(address);
                                                                                                            }
                                                                                                        }
                                                                                                        if(age!=null){
                                                                                                            age=age.trim();
                                                                                                            if(!age.isEmpty()){
                                                                                                                client.setAge(age);
                                                                                                            }
                                                                                                        }
                                                                                                        if(email!=null){
                                                                                                            email=email.trim();
                                                                                                            if(!email.isEmpty()){
                                                                                                                client.setEmailId(email);
                                                                                                            }
                                                                                                        }
                                                                                                        if(gender!=null){
                                                                                                            gender=gender.trim();
                                                                                                            if(!gender.isEmpty()){
                                                                                                                client.setGender(gender);
                                                                                                            }
                                                                                                        }
                                                                                                        if(city!=null){
                                                                                                            city=city.trim();
                                                                                                            if(!city.isEmpty()){
                                                                                                                client.setCity(city);
                                                                                                            }
                                                                                                        }
                                                                                                        if(pincode!=null){
                                                                                                            pincode=pincode.trim();
                                                                                                            if(!pincode.isEmpty()){
                                                                                                                client.setPincode(pincode);
                                                                                                            }
                                                                                                        }  
                                                                                                        if(initialComment!=null){
                                                                                                            if(!initialComment.isEmpty()){
                                                                                                                client.setInitialComments(initialComment);
                                                                                                            }
                                                                                                        }
                                                                                                        if(callMaintain.updateClientOnly(client)&&callMaintain.updateLeadOnly(lead)){
                                                                                                                model.addObject("message", "Updated Database");
                                                                                                                return model;
                                                                                                            }
                                                                                                }
                                                                    }
                                          }
                                    
           model.addObject("message", "Not Updated");
           return model;
    }
    @RequestMapping(value="/admin/callrecords/get")
    public ModelAndView getCallRecords(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallrecords");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
        List<Call> list=callMaintain.getAllCallRecordsByDate(request.getParameter("date"));
        if(list!=null&&!list.isEmpty()){   
            model.addObject("callrecords",list);
            model.addObject("message", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            model.addObject("excel",callMaintain.createCallExcel(list)&&callMaintain.createClientExcel(request.getParameter("date")));
            
            
            
            
            
        }
        else{
            model.addObject("message","No such records found");
        }
            return model;
       
        
    }
    
    @RequestMapping(value="/admin/callnotification")
    public ModelAndView callNotifications(HttpServletRequest request, Principal principal) throws IOException {
     ModelAndView model= new ModelAndView("admincallnotification");
     
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
     model.addObject("lead", callMaintain.readNotifiedLead());
        return model;
    }
     @RequestMapping(value="/admin/callnotification/on/{leadId}")
    public ModelAndView swichOnCallNotifications(HttpServletRequest request,@PathVariable("leadId") String leadId, Principal principal) throws IOException {
     ModelAndView model= new ModelAndView("admincallnotification");
     
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
     Lead lead = new Lead();
     lead.setLeadId(leadId);
     lead=callMaintain.readLead(lead);
     lead.setNotification(false);
     lead.setPending(false);
     lead.setCount(0);
     lead.setLeadStatus("Lead sent to Thyrocare");
     callMaintain.updateLeadOnly(lead);
     model.addObject("lead", callMaintain.readNotifiedLead());
        return model;
    }
    @RequestMapping(value="/admin/callnotification/off/{leadId}")
    public ModelAndView swichOffCallNotifications(HttpServletRequest request,@PathVariable("leadId") String leadId, Principal principal) throws IOException {
     ModelAndView model= new ModelAndView("admincallnotification");
     
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
     Lead lead = new Lead();
     lead.setLeadId(leadId);
     lead=callMaintain.readLead(lead);
     lead.setNotification(false);
     lead.setPending(false);
     lead.setCount(0);
     lead.setLeadStatus("Disapproved");
     callMaintain.updateLeadOnly(lead);
     model.addObject("lead", callMaintain.readNotifiedLead());
        return model;
    }
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
              
               default:
                   flag=false;
                   System.out.println("Exotel details. Not an option");
                   break;
           }
            if(flag){
                operator.doStageThreeCall(call);
               return new ResponseEntity(HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
       
        
    }
    

    @RequestMapping(value="/API/freshCall/Asdf7984sdfkjsdhfKFHDJFhshksdjflSFDAKHDfsjdhfrww",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,headers="Accept=*/*")
    public ResponseEntity freshCalls(@RequestBody Client client,HttpServletRequest request) throws IOException{
          if(client!=null){
                if(client.getTempLeadDetails()!=null&&client.getPhoneNumber()!=null&&client.getEmailId()!=null){
                 //         if(callMaintain.buildBackupClient(client)!=null&&callMaintain.readBackupClient(client.getTempLeadDetails()).getTempLeadDetails()==null){
                 
                   if(callMaintain.buildBackupClient(client)!=null&&eCallHandler.getStatus()){         
                 operator.getNumbers().offer(client);
                          operator.setFreshFlag(true);          
                                        return new ResponseEntity(HttpStatus.OK);
                          }
                }
            }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    
    @RequestMapping(value="/API/getDump/Asdf7984sdfkjsdhfKFHDJFhshksdjflSFDAKHDfsjdhfrww",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,headers="Accept=*/*")
    public @ResponseBody List<Client> freshCalls(HttpServletRequest request) throws IOException{
        System.out.println(request.getParameter("Date"));
        List<Client> list=callMaintain.getClientDump(request.getParameter("Date"));
        for(Client c:list){
           for(Lead l:c.getLead()){
               l.setClient(null);
               l.setAdmin(null);
               for(Call call:l.getCall()){
                   call.setLead(null);
               }
           }
        }
        callMaintain.doAnalytics();
        return list;
    }

}
