package com.jubination.controller.web;



import com.jubination.init.AdvancedTest;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Controller;



@Controller
public class RedirectController {

    public RedirectController() {
    }
    
    @PostConstruct
    public void init() {
        AdvancedTest.main(null);
    }
    
}
