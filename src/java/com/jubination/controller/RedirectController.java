package com.jubination.controller;



import com.jubination.backend.EmailService;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class RedirectController {
    private String ownerEmailId="support@jubination.com";


    public RedirectController() {
    }
    
  
    
  
  @RequestMapping(value="/")
    public ModelAndView init(HttpSession session,HttpServletRequest request) {
    return new ModelAndView("welcome");
    }
      
  @RequestMapping(value="/index.htm")
    public ModelAndView initAlias(HttpSession session,HttpServletRequest request) {
    return new ModelAndView("welcome");
    }
     @RequestMapping(value="/about")
    public ModelAndView initAbout(HttpSession session,HttpServletRequest request) {
    return new ModelAndView("about");
    }    
      @RequestMapping(value="/products")
    public ModelAndView initProducts(HttpSession session,HttpServletRequest request) {
    return new ModelAndView("products");
    } 
    
    
    @RequestMapping(value="/contact")
    public ModelAndView initContact(HttpSession session,HttpServletRequest request) {
    return new ModelAndView("contact");
    } 
    @RequestMapping(value="/contacted", method=RequestMethod.POST)
    public ModelAndView contacted(HttpServletRequest request){
        
        
        return new ModelAndView("contacted");
    }
  
    @RequestMapping(value="/sendEmail")
    public ModelAndView sendEmail(HttpServletRequest request){
        String name=request.getParameter("name");
        String mobile=request.getParameter("mobile");
        String email=request.getParameter("email");
        String message=request.getParameter("message");
        if(name==null||name.equals("")){
            name="Visitor";
        }
        if(mobile!=null&&email!=null&&message!=null){
            
            System.out.println(name+mobile+email+" messege sent");
        //message to customer
        new EmailService(email, "Thank You for contacting us", 
        "  Dear "+name+",<br/><br/>"
                +"We would reply or call you at "+mobile+" shortly.<br/>"
                +"Thank you for showing interest.<br/><br/>"
                +"Regards,<br/>"
                +"Imperial Technology Solutions"
        
        ).start();
        //message to owner
        new EmailService(ownerEmailId, name+" messaged you through website", 
        name+", Mobile :"+mobile+", Email : "+email+" has messaged you,<br/><br/>"
                +message+
                "<br/><br/>Thank You"
                
        
        ).start();
        }
        else{
            System.out.println(name+mobile+email+" all Null");
        }
        return new ModelAndView("contacted");
    }
    
    @RequestMapping(value ="/googlelocate")
    public ModelAndView googleLocate(HttpServletRequest request){
        
        if(request.getParameter("longitude")!=null&&request.getParameter("latitude")!=null){
            System.out.println(request.getParameter("longitude")+request.getParameter("latitude"));   
        }
        
        return new ModelAndView("googlelocate");
        
        
    }
}
