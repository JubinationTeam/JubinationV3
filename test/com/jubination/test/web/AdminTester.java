package com.jubination.test.web;


import com.jubination.controller.web.AdminController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test.xml")
public class AdminTester {
   @Autowired
    AdminController controller;
    
    @Before
       void beforeSetup(){
           
       }
       
       @Test
       void test(){
           
       }    
    
}
