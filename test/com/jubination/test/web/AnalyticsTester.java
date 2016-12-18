/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.test.web;


import com.jubination.controller.web.AnalyticsController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test.xml")
public class AnalyticsTester {
   @Autowired
    AnalyticsController controller;
    
    @Before
       void beforeSetup(){
           
       }
       
       @Test
       void test(){
           
       }    
}
