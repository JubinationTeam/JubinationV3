/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.service.core.chatbot;

import com.jubination.backend.pojo.core.chatbot.ChatBotRequest;
import com.jubination.backend.pojo.core.chatbot.ChatBotResponse;
import com.jubination.backend.pojo.core.chatbot.DietChart;
import com.jubination.backend.service.sendgrid.EmailService;
import com.jubination.model.pojo.admin.AdminSettings;
import com.jubination.service.AdminMaintainService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author MumbaiZone
 */
@Component
public class ChatBotMaintainService {
    
    
       @Autowired
     private  AdminMaintainService adminService;
        @Autowired
     private  DietChartUpdater updater;
       
       
       private String settings="settings";
    private HashMap<String,List<ChatBotResponse>> map = new HashMap<>();
private HashMap<String,List<String>> answerMap = new HashMap<>();
    
    
    
    public List<ChatBotResponse> generateFlow(){
        List<ChatBotResponse> responses =new ArrayList<>();
        responses.add(new ChatBotResponse(1, "Hi, I am Ruhi and today I want to help you get in the habit of clean eating. Lets generate a diet chart for you. Can I get your name?", "text", null));
        
        List<String> optionsGender = new ArrayList<>();
        optionsGender.add("Male");
        optionsGender.add("Female");
        responses.add(new ChatBotResponse(2, ", wish you avery healthy and happy new year. Your gender? ", "options", optionsGender));
        
        responses.add(new ChatBotResponse(3, "How much do you weigh? (in kgs)", "text", null));
        
        responses.add(new ChatBotResponse(4, ", what's your height? (ft.inches)", "text", null));
        
        responses.add(new ChatBotResponse(5, "How old are you? (in years)", "text", null));
        
         List<String> optionsFood = new ArrayList<>();
        optionsFood.add("Veg");
        optionsFood.add("Non veg");
        responses.add(new ChatBotResponse(6, "What is your meal preference?", "options", optionsFood));
        
        
        List<String> optionsLifestyle = new ArrayList<>();
        optionsLifestyle.add("Sedentary");
        optionsLifestyle.add("Moderately Active");
        optionsLifestyle.add("Active");
        responses.add(new ChatBotResponse(7, ", how active are you?", "options", optionsLifestyle));

        List<String> optionsDiseases = new ArrayList<>();
        optionsDiseases.add("Diabetes");
        optionsDiseases.add("High Lipid (Cholesterol) level");
        optionsDiseases.add("Hypothyroidism (High TSH levels)");
        optionsDiseases.add("High Blood Pressure");
        optionsDiseases.add("PCOS / PCOD");
        optionsDiseases.add("Hyperthyroidism (Low TSH levels)");
        optionsDiseases.add("None");
        responses.add(new ChatBotResponse(8, ", do you have any existing diseases?", "options", optionsDiseases));
        
        responses.add(new ChatBotResponse(9, "Can I get your email id?", "text", null));
        responses.add(new ChatBotResponse(10, "Your phone number?", "text", null));
        
        responses.add(new ChatBotResponse(11, "Click me to get your diet chart", "link", null));
        return responses;
    }
    
    
    
    
    public ChatBotResponse getResponse(ChatBotRequest request, String sessionId){
            int countId=request.getLastId();
            System.out.println("Service"+countId);
            countId++;
            System.out.println("Service after"+countId);
            if(countId==1){
                map.put(sessionId, generateFlow());
                answerMap.put(sessionId, new ArrayList<String>());
            }
            answerMap.get(sessionId).add(request.getLastAnswer());
            
            
          
            
            
            if(answerMap.get(sessionId).size()==10){
              //send to lms
                System.out.println("send to lms");
                DietChart diet = new DietChart();
                diet.setDietChart(answerMap.get(sessionId));
                String response=updater.sendAutomatedUpdate(diet);
                System.out.println(response+":::::::::::::::::::::::::::");
                if(response.equalsIgnoreCase("Failed")){
                    map.get(sessionId).get(countId-1).setQuestion("Oops! Seems like you have not registered. Click me to register.");
                }
            }
           
            
             switch (countId-1) {
               case 0:
                   break;
               case 1: map.get(sessionId).get(countId-1).setQuestion("Hi "+answerMap.get(sessionId).get(1)+map.get(sessionId).get(countId-1).getQuestion());
                   break;
               case 2:
                   break;
                   case 3:map.get(sessionId).get(countId-1).setQuestion(answerMap.get(sessionId).get(1)+map.get(sessionId).get(countId-1).getQuestion());
                   break;
               case 4:
                   break;
               case 5:
                   break;
                   case 6:map.get(sessionId).get(countId-1).setQuestion("Oooh, I love "+answerMap.get(sessionId).get(6)+" too"+map.get(sessionId).get(countId-1).getQuestion());
                   
                   break;
               case 7: map.get(sessionId).get(countId-1).setQuestion(answerMap.get(sessionId).get(1)+map.get(sessionId).get(countId-1).getQuestion());
                   break;
               case 8:
                   break;
                   case 9:
                   break;
               default:
                   break;
           }
            
            
            System.out.println(answerMap.toString());
            return map.get(sessionId).get(countId-1);

    }
   
    
}
