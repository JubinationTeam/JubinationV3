/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.controller.web;

import com.jubination.model.pojo.admin.Admin;
import com.jubination.model.pojo.crm.DataAnalytics;
import com.jubination.service.AdminMaintainService;
import com.jubination.service.CallMaintainService;
import com.jubination.service.DataAnalyticsService;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author MumbaiZone
 */

@Controller
public class AnalyticsController {
    @Autowired
    DataAnalyticsService analyticsMaintain;
    @Autowired
    AdminMaintainService adminMaintain;
    
    @RequestMapping(value = "/admin")
    public  ModelAndView adminLoginCheck(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("adminpage");
            model.addObject("fDate",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            model.addObject("tDate",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            return model;
    }
   
     @RequestMapping(value = "/admin/callanalytics/getquality")
    public  ModelAndView adminmailSpoke(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("adminpage");
            analyticsMaintain.mailSpokeAnalytics();
            model.addObject("fDate",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            model.addObject("tDate",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            return model;
    }
    
    @RequestMapping(value = "/admin/callanalytics/get")
    public  ModelAndView readAnalytics(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("adminpage");
            model.addObject("analytics",analyticsMaintain.readAnalytics(request.getParameter("date")));
            model.addObject("fDate",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            model.addObject("tDate",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            return model;
    }    
     @RequestMapping(value = "/admin/callanalytics/get/recent")
    public  ModelAndView readRecentAnalyics(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("adminpage");
            model.addObject("analytics",analyticsMaintain.readRecentAnalytics());
            model.addObject("fDate",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            model.addObject("tDate",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            return model;
    }
    
     @RequestMapping(value="/admin/do/analytics")
    public ModelAndView doCallAnalytics(HttpServletRequest request, Principal principal) throws IOException {
            ModelAndView model= new ModelAndView("adminpage");
            if(request.getParameter("fromDate")!=null&&request.getParameter("toDate")!=null){
                analyticsMaintain.doCustomAnalytics(request.getParameter("fromDate")+" 00:00:00",request.getParameter("toDate")+" 23:59:59");
                model.addObject("fDate",request.getParameter("fromDate"));
                model.addObject("tDate",request.getParameter("toDate"));
                model.addObject("analytics",analyticsMaintain.readRecentAnalytics());
            }
            else{
                analyticsMaintain.doAnalytics();
                model.addObject("fDate",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                model.addObject("tDate",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                model.addObject("analytics",analyticsMaintain.readAnalytics(new SimpleDateFormat("yyyy").format(new Date())));
            }
            
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            
            return model;
    }

    
}
