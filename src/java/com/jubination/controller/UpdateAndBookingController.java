/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.controller;

import com.jubination.model.pojo.Admin;
import com.jubination.model.pojo.Beneficiaries;
import com.jubination.model.pojo.Client;
import com.jubination.model.pojo.Lead;
import com.jubination.model.pojo.products.thyrocare.json.ProductList;
import com.jubination.service.AdminMaintainService;
import com.jubination.service.CallMaintainService;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author MumbaiZone
 */
@Controller
public class UpdateAndBookingController {
    @Autowired
    AdminMaintainService adminMaintain; 
    @Autowired
    CallMaintainService callMaintain;
    
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
                                                                                                                                    String bookingResponse=bookItThroughLMS(id);
                                                                                                                                        model.addObject("response", bookingResponse);
                                                                                                                                    if(bookingResponse.endsWith("Success")){
                                                                                                                                            lead.setBooked(true);
                                                                                                                                    }
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

    private String bookItThroughLMS(String id){
        String responseText="";
        try {   

                        String url="https://mypage.jubination.com/api/booking";
                        ObjectMapper mapper = new ObjectMapper();

                        //Object to JSON in String
                        Lead lead=callMaintain.getClientDetails(id);
                        lead.setCall(null);
                        lead.getAdmin().setReceivedMessageList(null);
                        lead.getAdmin().setSentMessageList(null);
                        lead.getAdmin().setPassword(null);
                        for(Beneficiaries ben:lead.getBeneficiaries()){
                            ben.setLead(null);
                        }
                        lead.getClient().setLead(null);

                        String jsonString= mapper.writeValueAsString(lead);

                        HttpClient httpClient = HttpClientBuilder.create().build();

                        StringEntity requestEntity = new StringEntity(
                        jsonString,
                        ContentType.APPLICATION_JSON);

                        HttpPost postMethod = new HttpPost(url);
                        postMethod.setEntity(requestEntity);

                        HttpResponse response = httpClient.execute(postMethod);
                        HttpEntity entity = response.getEntity();
                        responseText = EntityUtils.toString(entity, "UTF-8");
                    
          } 
         catch (Exception ex) {
                        Logger.getLogger(UpdateAndBookingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return responseText;
    }
}
