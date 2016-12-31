/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.service.core.chatbot;

import com.jubination.backend.pojo.core.chatbot.DietChart;
import com.jubination.controller.web.UpdateAndBookingController;
import com.jubination.model.pojo.admin.Admin;
import com.jubination.model.pojo.crm.Beneficiaries;
import com.jubination.model.pojo.crm.Client;
import com.jubination.model.pojo.crm.Lead;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author MumbaiZone
 */
@Component
public class DietChartUpdater {
    
     public String sendAutomatedUpdate(DietChart dietChart){
            String responseText="";
            try {   
                String url="http://mypage.jubination.com/dietchart/api";
                ObjectMapper mapper = new ObjectMapper();
                //Object to JSON in String
                String jsonString= mapper.writeValueAsString(dietChart);
                HttpClient httpClient = HttpClientBuilder.create().build();
                System.out.println(jsonString);
                StringEntity requestEntity = new StringEntity(
                jsonString,
                ContentType.APPLICATION_JSON);
                HttpPost postMethod = new HttpPost(url);
                postMethod.setEntity(requestEntity);
                HttpResponse response = httpClient.execute(postMethod);
                HttpEntity entity = response.getEntity();
                responseText = EntityUtils.toString(entity, "UTF-8");
                
            } 
            catch (Exception ex) {
                           Logger.getLogger(UpdateAndBookingController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return responseText;
    }
}
