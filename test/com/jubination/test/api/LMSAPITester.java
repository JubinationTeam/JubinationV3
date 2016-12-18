package com.jubination.test.api;

import com.jubination.controller.api.LMSAPIController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test.xml")
public class LMSAPITester {
    
       @Autowired
    LMSAPIController controller;
       
       @Before
       void beforeSetup(){
           
       }
       
       @Test
       void test(){
           
       }
}
