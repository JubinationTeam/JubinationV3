package com.jubination.controller.web;

import com.jubination.backend.service.exotel.numbercall.serial.CallBox;
import com.jubination.backend.service.core.leadcall.parallel.master.CallScheduler;
import com.jubination.backend.service.core.leadcall.parallel.master.CallManager;
import com.jubination.model.pojo.admin.Admin;
import com.jubination.model.pojo.exotel.Call;
import com.jubination.service.AdminMaintainService;
import com.jubination.service.CallMaintainService;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CallController {
    @Autowired
    CallMaintainService callMaintain;
    @Autowired
    AdminMaintainService adminMaintain;
    @Autowired
    CallScheduler operator;
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
            getCallSettingsParam(model);
            model.addObject("cs1", callHandler.isFlag());
            model.addObject("cs2", callHandler.isCheckFlag());
            return model;
    }
    
    @RequestMapping(value="/admin/callsettings/setExecs/custom")
    public ModelAndView setCustomExecsCustom(HttpServletRequest request, Principal principal) throws IOException {
            ModelAndView model= new ModelAndView("admincallsettings");
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            callHandler.setExecutives(Integer.parseInt(request.getParameter("exc")));
            model.addObject("message","Processed");
            getCallSettingsParam(model);
            model.addObject("cs1", callHandler.isFlag());
            model.addObject("cs2", callHandler.isCheckFlag());
            return model;
    }
    
     @RequestMapping(value="/admin/callsettings/followup/check")
    public ModelAndView setFollowup(HttpServletRequest request, Principal principal) throws IOException {
            ModelAndView model= new ModelAndView("admincallsettings");
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            callMaintain.checkFollowUp();
            getCallSettingsParam(model);
            model.addObject("cs1", callHandler.isFlag());
            model.addObject("cs2", callHandler.isCheckFlag());
            return model;
    }
    
      @RequestMapping(value="/admin/callsettings/setExecs/auto")
    public ModelAndView setCustomExecsAuto(HttpServletRequest request, Principal principal) throws IOException {
            ModelAndView model= new ModelAndView("admincallsettings");
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            eCallHandler.setExecutives(Integer.parseInt(request.getParameter("ex")));
           
            model.addObject("message","Processed");
            getCallSettingsParam(model);
            model.addObject("cs1", callHandler.isFlag());
            model.addObject("cs2", callHandler.isCheckFlag());
            return model;
    }
    
    @RequestMapping(value="/admin/callsettings/set/count")
    public ModelAndView callSettingsSetCount(HttpServletRequest request, Principal principal) throws IOException {
            ModelAndView model= new ModelAndView("admincallsettings");
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            operator.setCount(Integer.parseInt(request.getParameter("count")));
            getCallSettingsParam(model);
            return model;
    }
    
    @RequestMapping(value="/admin/callsettings/stage1/on")
    public ModelAndView callSettingsSwitchOnStage1(HttpServletRequest request, Principal principal) throws IOException {
            ModelAndView model= new ModelAndView("admincallsettings");
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            eCallHandler.setStatus(true);
            getCallSettingsParam(model);
            return model;
    }
    
    @RequestMapping(value="/admin/callsettings/stage2/on")
    public ModelAndView callSettingsSwitchOnStage2(HttpServletRequest request, Principal principal) throws IOException {
            ModelAndView model= new ModelAndView("admincallsettings");
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            getCallSettingsParam(model);
            return model;
    }
    
    @RequestMapping(value="/admin/callsettings/stage3/on")
    public ModelAndView callSettingsSwitchOnStage3(HttpServletRequest request, Principal principal) throws IOException {
            ModelAndView model= new ModelAndView("admincallsettings");
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            getCallSettingsParam(model);
            return model;
    }
    
    @RequestMapping(value="/admin/callsettings/stage1/off")
    public ModelAndView callSettingsSwitchOffStage1(HttpServletRequest request, Principal principal) throws IOException {
            ModelAndView model= new ModelAndView("admincallsettings");
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            eCallHandler.setStatus(false);
            getCallSettingsParam(model);
            return model;
    }

    @RequestMapping(value="/admin/callsettings/stage2/off")
    public ModelAndView callSettingsSwitchOffStage2(HttpServletRequest request, Principal principal) throws IOException {
            ModelAndView model= new ModelAndView("admincallsettings");
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            getCallSettingsParam(model);
            return model;
    }
    
    @RequestMapping(value="/admin/callsettings/stage3/off")
    public ModelAndView callSettingsSwitchOffStage3(HttpServletRequest request, Principal principal) throws IOException {
            ModelAndView model= new ModelAndView("admincallsettings");
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            getCallSettingsParam(model);
            return model;
    }
    
    @RequestMapping(value="/admin/callsettings/stage1/flush")
    public ModelAndView callSettingsFlushStage1Numbers(HttpServletRequest request, Principal principal) throws IOException {
            ModelAndView model= new ModelAndView("admincallsettings");
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            eCallHandler.getClientStage1().clear();
            getCallSettingsParam(model);
            return model;
    }
    
    @RequestMapping(value="/admin/callsettings/stage2/flush")
    public ModelAndView callSettingsFlushStage2(HttpServletRequest request, Principal principal) throws IOException {
            ModelAndView model= new ModelAndView("admincallsettings");
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            eCallHandler.getClientStage2().clear();
            getCallSettingsParam(model);
            return model;
    }
    
    @RequestMapping(value="/admin/callsettings/stage3/flush")
    public ModelAndView callSettingsFlushStage3(HttpServletRequest request, Principal principal) throws IOException {
            ModelAndView model= new ModelAndView("admincallsettings");
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            eCallHandler.getStageThreeUpdates().clear();
            getCallSettingsParam(model);
            return model;
    }
     
    @RequestMapping(value="/admin/callsettings/cc/stage1/flush")
    public ModelAndView callSettingsFlushCCStage1Numbers(HttpServletRequest request, Principal principal) throws IOException {
            ModelAndView model= new ModelAndView("admincallsettings");
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            callHandler.getNumbers().clear();
            callHandler.setFlag(false);
            getCallSettingsParam(model);
            return model;
    }
    
    @RequestMapping(value="/admin/callsettings/cc/stage2/flush")
    public ModelAndView callSettingsFlushCCStage2(HttpServletRequest request, Principal principal) throws IOException {
            ModelAndView model= new ModelAndView("admincallsettings");
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            callHandler.getSids().clear();
            callHandler.setCheckFlag(false);
            getCallSettingsParam(model);
            return model;
    }
    
    @RequestMapping(value="/admin/callsettings/cc/stage3/flush")
    public ModelAndView callSettingsFlushCCStage3(HttpServletRequest request, Principal principal) throws IOException {
            ModelAndView model= new ModelAndView("admincallsettings");
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            callHandler.getStageThreeUpdates().clear();
            getCallSettingsParam(model);
            return model;
    }
   
    @RequestMapping(value="/admin/callsettings/followup/on")
    public ModelAndView FollowupOn(HttpServletRequest request, Principal principal) throws IOException {
            ModelAndView model= new ModelAndView("admincallsettings");
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            operator.setFollowupFlag(true);
            getCallSettingsParam(model);
            return model;
    }    
    
    @RequestMapping(value="/admin/callsettings/followup/off")
    public ModelAndView noonFollowupOff(HttpServletRequest request, Principal principal) throws IOException {
            ModelAndView model= new ModelAndView("admincallsettings");
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            operator.setFollowupFlag(false);
            getCallSettingsParam(model);
            return model;
    }    
    
   

    @RequestMapping(value="/admin/callrecords")
    public ModelAndView callRecords(HttpServletRequest request, Principal principal) throws IOException {
            ModelAndView model= new ModelAndView("admincallrecords");
            List<Call> list=callMaintain.getAllCallRecordsByDate(request.getParameter("date"));
            if(list!=null&&!list.isEmpty()){   
                model.addObject("callrecords",list);
            }
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            model.addObject("message", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
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
    
   
    private ModelAndView getCallSettingsParam(ModelAndView model){
            model.addObject("ex",eCallHandler.getExecutives());
            model.addObject("sFlag1", eCallHandler.getStatus());
            model.addObject("sCount1", eCallHandler.getClientStage1().size());
            model.addObject("sCount2", eCallHandler.getClientStage2().size());
            model.addObject("sCount3", eCallHandler.getStageThreeUpdates().size());
            model.addObject("sCountCC1", callHandler.getNumbers().size());
            model.addObject("sCountCC2", callHandler.getSids().size());
            model.addObject("sCountCC3", callHandler.getStageThreeUpdates().size());
            model.addObject("followUpCount", operator.getCount());
            model.addObject("followupFlag", operator.isFollowupFlag());
             model.addObject("ex", eCallHandler.getExecutives());
            model.addObject("exc",callHandler.getExecutives());
            return model;
    }
    
}
