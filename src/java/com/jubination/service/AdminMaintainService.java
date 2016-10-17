/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jubination.service;

import com.jubination.backend.call.CallBox;
import com.jubination.backend.EmailService;
import com.jubination.backend.LoginInfoService;
import com.jubination.model.dao.AdminDAOImpl;
import com.jubination.model.dao.CallAPIMessageDAOImpl;
import com.jubination.model.dao.MessageDAOImpl;
import com.jubination.model.pojo.Admin;
import com.jubination.model.pojo.Call;
import com.jubination.model.pojo.MailMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Welcome
 */
@Service
@Transactional
public class AdminMaintainService {
    
@Autowired 
AdminDAOImpl adao;
@Autowired 
MessageDAOImpl mdao;

    public Admin checkPresence(Admin admin){
       admin = (Admin) adao.readProperty(admin.getUsername());
       return admin;
    }

    public boolean buildEmployee(String username, String name, String work, String initiatorName) {
        Admin creator =  checkPresence(new Admin(initiatorName));
        
        
            Integer passcode=null;
            Random r = new Random();
            passcode=r.nextInt(9)*1000+r.nextInt(9)*100+r.nextInt(9)*10+r.nextInt(9);
            Md5PasswordEncoder encoder = new Md5PasswordEncoder();
            String pass=encoder.encodePassword(passcode.toString(), null).toString();
           
            new LoginInfoService("Welcome to Jubination!!",
                    "Hi, "
                    + "<br/>"
                    + "<br/>"
                    +" We welcome you to Jubination's new Call Monitoring website. Please note your following credentials. You can change your password through settings.<br/>"
                    + "<br/> "
                    + "<br/>"
                    + "Your user id is "+username+" and password is : "+pass
                    + "<br/>"
                    + "Login URL http://162.246.21.98/jubination/admin"
                    + "<br/>"
                    + "<br/>"
                    + "Regards,<br/>Jubination Support",
                    "admin",username).start();
             System.err.println(pass);
            passcode=null;
            encoder=null;
            
        
        if(creator==null){
            return false;
        }
      
        Admin admin=new Admin(username);
       admin.setPassword(pass);
       admin.setRole("ROLE_ADMINISTRATOR");
       admin.setWork(work);
       admin.setPower(creator.getPower()+1);
       admin.setName(name);
        pass=null;
                
                if(adao.buildEntity(admin)!=null){
                    admin=null;
                    return true;
                }
    return false;
    }

      public List<Admin> getHrList(int power){
          return (List<Admin>) adao.fetchEntities(power);
      }      

    public boolean setPassword(Admin admin, String parameter) {
       return adao.updateProperty(admin, parameter, "Password");
    }

    public boolean deleteEmployee(Admin admin) {
       return adao.deleteEntity(admin);
    }

    public boolean sendMail(Admin sender, Admin receiver, String subject, String mail) {
    MailMessage msg= new MailMessage();
    msg.setReceiver(receiver);
    msg.setSender(sender);
    msg.setSubject(subject);
    msg.setBody(mail);
    msg=(MailMessage) mdao.buildEntity(msg);
    
    return msg!=null;
    }

    public List<MailMessage> inboxMail(Admin admin) {
    return (List<MailMessage>) adao.readPropertyList(admin, "Inbox");
  
    }

    public List<MailMessage> sentMail(Admin admin) {
    return (List<MailMessage>) adao.readPropertyList(admin, "Sent");
    }


}
