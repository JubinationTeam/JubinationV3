/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jubination.service;


import com.jubination.backend.service.core.email.LoginInfoService;
import com.jubination.model.dao.plan.GenericDAOAbstract;
import com.jubination.model.pojo.admin.Admin;
import com.jubination.model.pojo.admin.AdminSettings;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("adminDAO")
    private GenericDAOAbstract adao;   
    @Autowired
    @Qualifier("settingsDAO")
    private GenericDAOAbstract asdao;

String settings ="settings";

    public Admin checkPresence(Admin admin){
       admin = (Admin) adao.readProperty(admin.getUsername());
       return admin;
    }

    public boolean buildEmployee(String username, String name, String work, String initiatorName,String number) {
        Admin creator =  checkPresence(new Admin(initiatorName));
        
        
            Integer passcode=null;
            Random r = new Random();
            passcode=r.nextInt(9)*1000+r.nextInt(9)*100+r.nextInt(9)*10+r.nextInt(9);
            Md5PasswordEncoder encoder = new Md5PasswordEncoder();
            String pass=encoder.encodePassword(passcode.toString(), null).toString();
           AdminSettings adminSettings=readSettings(settings);
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
                    "admin",username,adminSettings.getMyUsername(),adminSettings.getMyPassword(),adminSettings.getAuth(),adminSettings.getStarttls(),adminSettings.getHost(),adminSettings.getPort()).start();
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
       admin.setNumber(number);
        pass=null;
        encoder = new Md5PasswordEncoder();
            admin.setPassword(encoder.encodePassword(admin.getPassword(), null));
                if(adao.buildEntity(admin)!=null){
                    admin=null;
                    return true;
                }
    return false;
    }

      public List<Admin> getHrList(int power){
          return (List<Admin>) adao.fetchAll("power", null);
      }      

    public boolean setPassword(Admin admin, String parameter) {
        String password=new Md5PasswordEncoder().encodePassword(parameter, null);
                    admin.setPassword(password);
       return adao.updateProperty(admin);
    }

    public boolean deleteEmployee(Admin admin) {
       return adao.deleteEntity(admin);
    }


    
    
    public AdminSettings readSettings(String settingsName){
        return (AdminSettings) asdao.readProperty(settingsName);
    }
    
    public boolean setSettings(AdminSettings settings){
        return asdao.updateProperty(settings);
    }
    public AdminSettings  buildSettings(AdminSettings settings){
        return (AdminSettings) asdao.buildEntity(settings);
    }
    

}
