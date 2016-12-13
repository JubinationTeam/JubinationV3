package com.jubination.controller;

import com.jubination.backend.service.numbercall.serial.exotel.CallBox;
import com.jubination.backend.service.leadcall.parallel.master.CallScheduler;
import com.jubination.backend.service.leadcall.parallel.master.CallManager;
import com.jubination.model.pojo.admin.Admin;
import com.jubination.model.pojo.crm.Beneficiaries;
import com.jubination.model.pojo.ivr.exotel.Call;
import com.jubination.model.pojo.crm.Client;
import com.jubination.model.pojo.crm.Lead;
import com.jubination.service.AdminMaintainService;
import com.jubination.service.CallMaintainService;
import com.jubination.service.ProductService;
import java.io.IOException;
import java.io.StringReader;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
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
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


@Controller
public class CallController {
    @Autowired
    CallMaintainService callMaintain;
    @Autowired
    ProductService productService;
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
            getCallSettingsParam(model);
            model.addObject("cs1", callHandler.isFlag());
            model.addObject("cs2", callHandler.isCheckFlag());
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
    
    @RequestMapping(value="/admin/callsettings/do/analytics")
    public ModelAndView doCallAnalytics(HttpServletRequest request, Principal principal) throws IOException {
            ModelAndView model= new ModelAndView("adminpage");
            callMaintain.doAnalytics();
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
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
   
    @RequestMapping(value="/admin/setExecs/auto")
    public ModelAndView setCustomExecsAuto(HttpServletRequest request, Principal principal) throws IOException {
            ModelAndView model= new ModelAndView("adminpage");
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            eCallHandler.setExecutives(Integer.parseInt(request.getParameter("ex")));
            List<Call> list=callMaintain.getAllCallRecordsByDate(request.getParameter("date"));
            model.addObject("callrecords",list);
            model.addObject("ex",request.getParameter("ex"));
            model.addObject("message","Processed");
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
               case "agent":
                  for(Client client:eCallHandler.getClientStage2()){
                      if(request.getParameter("From").contains(client.getPhoneNumber())&&request.getParameter("Status").equals("busy")){
                          client.setRealTimeData(request.getParameter("DialWhomNumber"));
                      }
                  }
               default:
                   flag=false;
                   System.out.println("Exotel details. Not an option");
                   break;
           }
            
            String url = getTestURL(request);
            System.out.println("URL:::::::::::::::::::::; "+url);
//            CloseableHttpResponse response=null;
//                            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//                            DocumentBuilder builder;
//                            InputSource is;
//                            String responseText="NA";
//                    Document doc=null;
//        
//                                    //requesting exotel to initiate call
//                                    CloseableHttpClient httpclient = HttpClients.createDefault();
//                                    HttpGet httpGet = new HttpGet(getTestURL(request));
//                                    HttpResponse response = httpclient.execute(httpGet);
//                                    HttpEntity entity = response.getEntity();
//                                    responseText = EntityUtils.toString(entity, "UTF-8");
                           
                    
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
                   for(Beneficiaries ben:l.getBeneficiaries()){
                       ben.setLead(null);
                   }
               }
            }
            callMaintain.doAnalytics();
            return list;
    }
    
    private ModelAndView getCallSettingsParam(ModelAndView model){
            model.addObject("sFlag1", eCallHandler.getStatus());
            model.addObject("sCount1", eCallHandler.getClientStage1().size());
            model.addObject("sCount2", eCallHandler.getClientStage2().size());
            model.addObject("sCount3", eCallHandler.getStageThreeUpdates().size());
            model.addObject("sCountCC1", callHandler.getNumbers().size());
            model.addObject("sCountCC2", callHandler.getSids().size());
            model.addObject("sCountCC3", callHandler.getStageThreeUpdates().size());
            model.addObject("followUpCount", operator.getCount());
            model.addObject("followupFlag", operator.isFollowupFlag());
            return model;
    }
public String getTestURL(HttpServletRequest req) {

    String scheme = req.getScheme();             // http
    String serverName = "54.149.67.96";     // hostname.com
    int serverPort = req.getServerPort();        // 80
    String contextPath = req.getContextPath();   // /mywebapp
    String servletPath = req.getServletPath();   // /servlet/MyServlet
    String pathInfo = req.getPathInfo();         // /a/b;c=123
    String queryString = req.getQueryString();          // d=789

    // Reconstruct original requesting URL
    StringBuilder url = new StringBuilder();
    url.append(scheme).append("://").append(serverName);

    if (serverPort != 80 && serverPort != 443) {
        url.append(":").append(serverPort);
    }

    url.append(contextPath).append(servletPath);

    if (pathInfo != null) {
        url.append(pathInfo);
    }
    if (queryString != null) {
        url.append("?").append(queryString);
    }
    return url.toString();
}
}
