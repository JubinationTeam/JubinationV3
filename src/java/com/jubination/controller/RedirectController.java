package com.jubination.controller;



import com.jubination.test.AdvancedTest;
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
