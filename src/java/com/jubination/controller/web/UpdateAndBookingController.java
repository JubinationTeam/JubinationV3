package com.jubination.controller.web;

import com.jubination.backend.service.core.leadcall.parallel.master.CallScheduler;
import com.jubination.backend.service.lms.Updater;
import com.jubination.model.pojo.status.ReportStatus;
import com.jubination.model.pojo.admin.Admin;
import com.jubination.model.pojo.crm.Client;
import com.jubination.model.pojo.crm.Lead;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UpdateAndBookingController {
    
    @Autowired
    AdminMaintainService adminMaintain; 
    @Autowired
    CallMaintainService callMaintain;
    @Autowired
    Updater updater;
    @Autowired
    CallScheduler operator;
    
    @RequestMapping(value="/admin/callupdates/update")
    public ModelAndView updateClient(HttpServletRequest request, Principal principal) throws IOException {
        ModelAndView model= new ModelAndView("admincallupdates");
        Admin admin=adminMaintain.checkPresence(new Admin(principal.getName()));
        model.addObject("admin",admin);
        String id=request.getParameter("id");
        String comment=request.getParameter("comment");
        String initialComment=request.getParameter("initialComment");
        String email =request.getParameter("email");
        String date =request.getParameter("date");
        String address=request.getParameter("address");
        String age =request.getParameter("age");
        String gender =request.getParameter("gender");
        String city =request.getParameter("city");
        String pincode =request.getParameter("pincode");
        String leadStatus =request.getParameter("leadStatus");
        String name =request.getParameter("name");
        String number =request.getParameter("number");
        
        String product =request.getParameter("product");
        String hardcopy =request.getParameter("hardcopy");
        String apptDate =request.getParameter("appt_date");
        String apptTime =request.getParameter("appt_time");
        String payType =request.getParameter("pay_type");
        String ben0 =request.getParameter("ben_0");
        String ben1 =request.getParameter("ben_1");
        String ben2 =request.getParameter("ben_2");
        String ben3 =request.getParameter("ben_3");
        String ben4 =request.getParameter("ben_4");
        String ben5 =request.getParameter("ben_5");
        String ben6 =request.getParameter("ben_6");
        String ben7 =request.getParameter("ben_7");
        String ben8 =request.getParameter("ben_8");
        String ben9 =request.getParameter("ben_9");
        Integer benCount=0;
        if(id!=null){
            if(!id.isEmpty()){
                Lead lead=null;
                lead = new Lead();
                lead.setLeadId(id);
                lead=callMaintain.readLead(lead);
                if(lead!=null){
                System.out.println("BEN SIZE::::::::::::::::::::::::::::::::::::::::::;"+lead.getBeneficiaries().size());
                    Client client=lead.getClient();
                    lead.setAdmin(admin);
                    lead.setNotification(false);
                        
                      
                      if(product!=null){
                           if(!product.isEmpty()){
                               lead.setProduct(product);
                               
                           }
                       }
                       if(hardcopy!=null){
                           if(!hardcopy.isEmpty()){
                               lead.setHardcopy(hardcopy);
                           }
                       }
                       if(apptDate!=null){
                           if(!apptDate.isEmpty()){
                               lead.setAppointmentDate(apptDate);
                           }
                       }
                       if(apptTime!=null){
                           if(!apptTime.isEmpty()){
                               lead.setAppointmentTime(apptTime);
                           }
                       }
                       if(payType!=null){
                           if(!payType.isEmpty()){
                               lead.setPayType(payType);
                           }
                       }
                       if(ben0!=null&&ben0.contains(",")){
                           if(!ben0.isEmpty()){
                               if(!ben0.contains(",,")){
                                        lead.getBeneficiaries().get(0).setName(ben0.split(",")[0]);
                                        lead.getBeneficiaries().get(0).setAge(ben0.split(",")[1]);
                                        lead.getBeneficiaries().get(0).setGender(ben0.split(",")[2]);
                                        benCount++;
                               }
                           }
                       }
                       if(ben1!=null&&ben1.contains(",")){
                           if(!ben1.isEmpty()){
                               if(!ben1.contains(",,")){
                                        lead.getBeneficiaries().get(1).setName(ben1.split(",")[0]);
                                        lead.getBeneficiaries().get(1).setAge(ben1.split(",")[1]);
                                        lead.getBeneficiaries().get(1).setGender(ben1.split(",")[2]);
                                        benCount++;
                               }
                           }
                       }
                       if(ben2!=null&&ben2.contains(",")){
                           if(!ben2.isEmpty()){
                               if(!ben2.contains(",,")){
                                    lead.getBeneficiaries().get(2).setName(ben2.split(",")[0]);
                                    lead.getBeneficiaries().get(2).setAge(ben2.split(",")[1]);
                                    lead.getBeneficiaries().get(2).setGender(ben2.split(",")[2]);
                                    benCount++;
                               }
                           }
                       }
                       if(ben3!=null&&ben3.contains(",")){
                           if(!ben3.isEmpty()){
                               if(!ben3.contains(",,")){
                                        lead.getBeneficiaries().get(3).setName(ben3.split(",")[0]);
                                        lead.getBeneficiaries().get(3).setAge(ben3.split(",")[1]);
                                        lead.getBeneficiaries().get(3).setGender(ben3.split(",")[2]);
                                        benCount++;
                               }
                           }
                       }
                       if(ben4!=null&&ben4.contains(",")){
                           if(!ben4.isEmpty()){
                               if(!ben4.contains(",,")){
                                        lead.getBeneficiaries().get(4).setName(ben4.split(",")[0]);
                                        lead.getBeneficiaries().get(4).setAge(ben4.split(",")[1]);
                                        lead.getBeneficiaries().get(4).setGender(ben4.split(",")[2]);
                                        benCount++;
                               }
                           }
                       }
                       if(ben5!=null&&ben5.contains(",")){
                           if(!ben5.isEmpty()){
                               if(!ben5.contains(",,")){
                                        lead.getBeneficiaries().get(5).setName(ben5.split(",")[0]);
                                        lead.getBeneficiaries().get(5).setAge(ben5.split(",")[1]);
                                        lead.getBeneficiaries().get(5).setGender(ben5.split(",")[2]);
                                        benCount++;
                               }
                           }
                       }
                       if(ben6!=null&&ben6.contains(",")){
                           if(!ben6.isEmpty()){
                               if(!ben6.contains(",,")){
                                    lead.getBeneficiaries().get(6).setName(ben6.split(",")[0]);
                                    lead.getBeneficiaries().get(6).setAge(ben6.split(",")[1]);
                                    lead.getBeneficiaries().get(6).setGender(ben6.split(",")[2]);
                                    benCount++;
                               }
                           }
                       }
                       if(ben7!=null&&ben7.contains(",")){
                           if(!ben7.isEmpty()){
                               if(!ben7.contains(",,")){
                                    lead.getBeneficiaries().get(7).setName(ben7.split(",")[0]);
                                    lead.getBeneficiaries().get(7).setAge(ben7.split(",")[1]);
                                    lead.getBeneficiaries().get(7).setGender(ben7.split(",")[2]);
                                     benCount++;
                               }
                           }
                       }
                       if(ben8!=null&&ben8.contains(",")){
                           if(!ben8.isEmpty()){
                               if(!ben8.contains(",,")){
                                    lead.getBeneficiaries().get(8).setName(ben8.split(",")[0]);
                                    lead.getBeneficiaries().get(8).setAge(ben8.split(",")[1]);
                                    lead.getBeneficiaries().get(8).setGender(ben8.split(",")[2]);
                                     benCount++;
                               }
                           }
                       }
                       if(ben9!=null&&ben9.contains(",")){
                           if(!ben9.isEmpty()){
                               if(!ben9.contains(",,")){
                                    lead.getBeneficiaries().get(9).setName(ben9.split(",")[0]);
                                    lead.getBeneficiaries().get(9).setAge(ben9.split(",")[1]);
                                    lead.getBeneficiaries().get(9).setGender(ben9.split(",")[2]);
                                      benCount++;
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
                        if(leadStatus!=null&&!leadStatus.isEmpty()){
                               lead.setLeadStatus(leadStatus);
                        }
                       
                       lead.setBenCount(benCount);
                      client.setName(name);
                      client.setPhoneNumber(number);
                      
                        if(callMaintain.updateClientOnly(client)){
                                        if(leadStatus!=null&&!leadStatus.isEmpty()){
                                            
                                          if(leadStatus.equals("Lead sent to Thyrocare")){
                                               
                                                            lead.setNotification(false);
                                                             lead.setPending(false);
                                                             lead.setCount(0);
                                                             callMaintain.updateLeadOnly(lead);
//                                                         List<Lead> leadList=callMaintain.getDuplicateLeads(number);
//                                                        for(Lead l:leadList){
//                                                             l.setNotification(false);
//                                                             l.setPending(false);
//                                                             l.setCount(0);
//                                                             callMaintain.updateLeadOnly(l);
//                                                         }
                                                 
                                         }
                                          else if(leadStatus.contains("Follow")&&date!=null){
                                                    if(!date.isEmpty()){
                                                        lead.setNotification(true);
                                                        lead.setFollowUpDate(date);
                                                        lead.setCount(operator.getCount()-4);
                                                        callMaintain.updateLeadOnly(lead);
                                                    }
                                         }
                                          else{
                                              lead.setNotification(false);
                                                lead.setPending(false);
                                                lead.setCount(0);
                                                callMaintain.updateLeadOnly(lead);
                                          }
                                          
                                                String bookingResponse=updater.sendAutomatedUpdate(id);
                                              model.addObject("response", bookingResponse);
                                              System.out.println("Response:"+bookingResponse);
                                         if(bookingResponse.endsWith("SUCCESS")){
                                                        lead.setBooked(true);
                                                        lead.setNotification(false);
                                                             lead.setPending(false);
                                                             lead.setCount(0);
                                                        callMaintain.updateLeadOnly(lead);
                                         }
                                  }
                                model.addObject("message", "Updated Database");
                                return model;
                       }
                    }
                }
           }
           model.addObject("message", "Not Updated");
           return model;
    }
    
       @RequestMapping(value="/admin/callupdates")
    public ModelAndView callUpdates(HttpServletRequest request, Principal principal) throws IOException {
            ModelAndView model= new ModelAndView("admincallupdates");
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            return model;
    }
    
    @RequestMapping(value="/admin/callupdates/values")
    public ModelAndView callUpdateValues(ModelAndView modelIn, HttpServletRequest request, Principal principal) throws IOException {
            ModelAndView model= new ModelAndView("admincallupdates");
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            if(request.getParameter("leadId")!=null&&!request.getParameter("leadId").isEmpty()){
                model.addObject("lead",callMaintain.getClientDetails(request.getParameter("leadId")));
            }
            model.addObject("message", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
           return model;
    }
    
     @RequestMapping(value="/admin/callupdates/phone")
    public ModelAndView callUpdatePhoneNumbrs(HttpServletRequest request, Principal principal) throws IOException {
            ModelAndView model= new ModelAndView("admincallupdates");
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            if(request.getParameter("phone_numbers")!=null&&!request.getParameter("phone_numbers").isEmpty()){
                model.addObject("clients",callMaintain.getClientsByPhoneNumber(request.getParameter("phone_numbers")));
            }
            model.addObject("message", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
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
    @RequestMapping(value="/admin/callnotification/off/source")
    public ModelAndView swichOffCallNotifications(HttpServletRequest request, Principal principal) throws IOException {
            ModelAndView model= new ModelAndView("admincallnotification");
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            List<Lead> list=callMaintain.readLeadsBySource(request.getParameter("source"));
            for(Lead lead:list){
                if(lead.isMissedAppointment()!=null&&lead.isMissedAppointment()!=true){
                    lead.setNotification(false);
                    lead.setPending(false);
                    lead.setCount(0);
                    lead.setLeadStatus("Disapproved");
                    callMaintain.updateLeadOnly(lead);
                }
            }
            model.addObject("lead", callMaintain.readNotifiedLead());
            return model;
    }
}
