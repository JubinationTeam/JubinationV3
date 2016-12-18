package com.jubination.test.web;


import com.jubination.controller.web.CallController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test.xml")
@WebAppConfiguration
public class CallTester {
   
    @Autowired
    CallController controller;
    
    @Before
       void beforeSetup(){
           
       }
       
       @Test
       void test(){
           
       }  
    
    
    
}
