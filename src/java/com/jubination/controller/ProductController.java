package com.jubination.controller;

import com.jubination.model.pojo.admin.Admin;
import com.jubination.model.pojo.booking.Campaigns;
import com.jubination.service.AdminMaintainService;
import com.jubination.service.ProductService;
import java.io.IOException;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;



@Controller
public class ProductController {
    @Autowired
    AdminMaintainService adminService;
    @Autowired
    ProductService service;
    
    @RequestMapping(value="/admin/products")
    public ModelAndView products(HttpServletRequest request,Principal principal){
            ModelAndView model= new ModelAndView("adminproductdisplay");
            try {
                model.addObject("products",service.fetchAllProducts());
            } catch (IOException | ParserConfigurationException | SAXException | JAXBException ex) {
                Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
            model.addObject("admin",adminService.checkPresence(new Admin(principal.getName())));
            return model;
    }
    
    @RequestMapping(value="/admin/campaign")
    public ModelAndView campaigns(HttpServletRequest request,Principal principal){
            ModelAndView model= new ModelAndView("admincampaigndisplay");
            model.addObject("campaign",service.fetchAllCampaigns());
            model.addObject("admin",adminService.checkPresence(new Admin(principal.getName())));
            return model;
    }
    
    @RequestMapping(value="/API/create/campaign/Asdf7984sdfkjsdhfKFHDJFhshksdjflSFDAKHDfsjdhfrww",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,headers="Accept=*/*")
    public ResponseEntity freshCalls(@RequestBody Campaigns campaign,HttpServletRequest request) throws IOException{
            if(service.buildCampaign(campaign)){
                         return new ResponseEntity(HttpStatus.OK);
            }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    
}
