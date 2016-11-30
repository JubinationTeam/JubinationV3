package com.jubination.controller;



import com.jubination.backend.service.customcall.CallBox;
import com.jubination.backend.service.freshcall.parallel.master.CallManager;
import com.jubination.model.pojo.admin.Admin;
import com.jubination.model.pojo.admin.MailMessage;
import com.jubination.service.AdminMaintainService;
import com.jubination.service.CallMaintainService;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AdminController {
    @Autowired
    AdminMaintainService adminMaintain;
    @Autowired
    CallMaintainService callMaintain;
    @Autowired
    CallBox callBox;
    @Autowired
    CallManager eCallBox;

    public AdminController() {
    }
    
    @RequestMapping(value = "/admin")
    public  ModelAndView adminLoginCheck(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("adminpage");
            model.addObject("ex",eCallBox.getExecutives());
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            return model;
    }
    
    @RequestMapping(value="/logout")
    public ModelAndView redirectLogOut(HttpSession session,HttpServletRequest request,Principal principal) {
            SecurityContextHolder.getContext().setAuthentication(null);
            session.invalidate();
            return new ModelAndView("redirect:/adminlogin");
    }
    
    @RequestMapping(value = "/adminlogin")
    public  ModelAndView adminLogin(HttpServletRequest request,Principal principal) {
            return new ModelAndView("adminlogin");
    }

    @RequestMapping(value = "/admin/mail")
    public  ModelAndView adminMail(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("adminmail");
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            List<MailMessage> inbox = adminMaintain.inboxMail(new Admin(principal.getName()));
            model.addObject("mails", inbox);
            return model;
    }

    @RequestMapping(value = "/admin/mail/sent")
    public  ModelAndView adminMailSent(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("adminsentmail");
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            List<MailMessage> sent = adminMaintain.sentMail(new Admin(principal.getName()));
            model.addObject("mails", sent);
            return model;
    }
    @RequestMapping(value = "/admin/send/mail", method = RequestMethod.POST)
    public  ModelAndView adminSendMail(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("adminmail");
            Admin sender =adminMaintain.checkPresence(new Admin(principal.getName()));
            List<MailMessage> inbox = adminMaintain.inboxMail(new Admin(principal.getName()));
            model.addObject("mails", inbox);
            String id=request.getParameter("receiver");
            String subject=request.getParameter("subject");
            String mail=request.getParameter("comment");
            Admin receiver=null;
            if(id!=null){
                receiver = adminMaintain.checkPresence(new Admin(id));
            }
            System.out.println(id+":"+principal.getName()+":"+subject+":"+mail);
            if(sender!=null&&receiver!=null){
                if(adminMaintain.sendMail(sender,receiver,subject,mail)){
                     model.addObject("message","Processed."); 
                } 
                else{
                    model.addObject("message","Not processed. Please try again!"); 
                } 
                model.addObject("admin",sender);
                sender=null;
                receiver=null;
                return model;
            }
            else{
                model.addObject("message","Error. Please try again!"); 
                return model; 
            }
     }
        
        @RequestMapping(value = "/admin/settings")
        public  ModelAndView adminSettings(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("adminsettings");
            if(request.getParameter("msg")!=null){
                if(request.getParameter("msg").toString().equals("true")){
                     model.addObject("message","Changed."); 
                } 
                else if(request.getParameter("msg").toString().equals("false")){
                    model.addObject("message","Not changed. Please try again!"); 
                } 
             }
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            return model;
        }
        
       @RequestMapping(value = "/admin/settings/change_pswrd")
        public  ModelAndView adminChangePassword(HttpServletRequest request,Principal principal) {
            Admin admin=adminMaintain.checkPresence(new Admin(principal.getName()));
            Md5PasswordEncoder encoder = new Md5PasswordEncoder();
            String temPassword=encoder.encodePassword(request.getParameter("oldpassword"), null);
            encoder=null;
            if(admin.getPassword().equals(temPassword)&&request.getParameter("newpassword").equals(request.getParameter("rnewpassword"))){
                if(request.getParameter("newpassword").length()>=6){
                     if(adminMaintain.setPassword(admin,request.getParameter("newpassword"))){
                         admin=null;
                            return new ModelAndView("redirect:/admin/settings?msg=true"); 
                     }
                }
            }
            admin=null;
            return new ModelAndView("redirect:/admin/settings?msg=false"); 
        }
       
     
     @RequestMapping(value = "/admin/assign_admin", method = RequestMethod.POST)
    public ModelAndView assignAdmin(Principal principal,HttpServletRequest request) {
            if(adminMaintain.buildEmployee(request.getParameter("username"),request.getParameter("name"),request.getParameter("work"),principal.getName())){
                return new ModelAndView("redirect:/admin/hr?msg=true"); 
            }
            return new ModelAndView("redirect:/admin/hr?msg=false"); 
    }
    
    @RequestMapping(value = "/admin/hr/delete/{username}", method = RequestMethod.GET)
    public ModelAndView deleteAdmin(@PathVariable("username") String username,Principal principal,HttpServletRequest request) {
    Admin destructor =adminMaintain.checkPresence(new Admin(principal.getName()));
            if(adminMaintain.checkPresence(new Admin(username.replace("$", "."))).getPower()>destructor.getPower()){
                destructor=null;
                if(adminMaintain.deleteEmployee(new Admin(username.replace("$", ".")))){
                    return new ModelAndView("redirect:/admin/hr?msg=true"); 
                }
                return new ModelAndView("redirect:/admin/hr?msg=false"); 
            }
           else{
                return new ModelAndView("redirect:/admin"); 
            }
    }
    
    @RequestMapping(value = "/admin/hr")
    public  ModelAndView adminHr(HttpServletRequest request,Principal principal) {
            Admin admin =adminMaintain.checkPresence(new Admin(principal.getName()));
            ModelAndView model= new ModelAndView("adminhr");
             if(request.getParameter("msg")!=null){
                if(request.getParameter("msg").equals("true")){
                     model.addObject("message","Processed."); 
                } 
                else if(request.getParameter("msg").equals("false")){
                    model.addObject("message","Not processed. Please try again!"); 
                } 
             }
            model.addObject("admin_list",adminMaintain.getHrList(admin.getPower()));
            model.addObject("admin",admin);
            admin=null; 
            return model;
    }
    
}
