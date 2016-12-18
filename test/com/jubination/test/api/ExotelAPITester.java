package com.jubination.test.api;

import com.jubination.controller.api.ExotelAPIController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test.xml")
public class ExotelAPITester {

 @Autowired
    ExotelAPIController controller;
       
       @Before
       void beforeSetup(){
           
       }
       
       @Test
       void test(){
           
       }    

}
