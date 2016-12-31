/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.service.core.chatbot;

import com.jubination.backend.pojo.core.chatbot.ChatBotRequest;
import com.jubination.backend.pojo.core.chatbot.ChatBotResponse;
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
       
       private String settings="settings";
    private HashMap<String,List<ChatBotResponse>> map = new HashMap<>();
private HashMap<String,List<String>> answerMap = new HashMap<>();
    
    
    
    public List<ChatBotResponse> generateFlow(){
        List<ChatBotResponse> responses =new ArrayList<>();
        responses.add(new ChatBotResponse(1, "What is your name?", "text", null));
        
        List<String> optionsGender = new ArrayList<>();
        optionsGender.add("Male");
        optionsGender.add("Female");
        responses.add(new ChatBotResponse(2, "Your gender?", "options", optionsGender));
        
        responses.add(new ChatBotResponse(3, "What's your weight?", "text", null));
        
        responses.add(new ChatBotResponse(4, "What's your height? (ft.inches)", "text", null));
        
        responses.add(new ChatBotResponse(5, "What is your age?", "text", null));
        
         List<String> optionsFood = new ArrayList<>();
        optionsFood.add("Veg");
        optionsFood.add("Non veg");
        responses.add(new ChatBotResponse(6, "What is your food habit?", "options", optionsFood));
        
        
        List<String> optionsLifestyle = new ArrayList<>();
        optionsLifestyle.add("Sedentary");
        optionsLifestyle.add("Moderately Active");
        optionsLifestyle.add("Active");
        responses.add(new ChatBotResponse(7, "Lifestyle?", "options", optionsLifestyle));

        List<String> optionsDiseases = new ArrayList<>();
        optionsDiseases.add("Diabetes");
        optionsDiseases.add("High Lipid (Cholesterol) level");
        optionsDiseases.add("Hypothyroidism (High TSH levels)");
        optionsDiseases.add("High Blood Pressure");
        optionsDiseases.add("PCOS / PCOD");
        optionsDiseases.add("Hyperthyroidism (Low TSH levels)");
        optionsDiseases.add("None");
        responses.add(new ChatBotResponse(8, "Existing diseases?", "options", optionsDiseases));
        
        responses.add(new ChatBotResponse(9, "Your email id?", "text", null));
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
            
            if(answerMap.get(sessionId).size()==11){
              //send to lms
                System.out.println("send to lms");
            }
            
            System.out.println(answerMap.toString());
            return map.get(sessionId).get(countId-1);

    }
    private void sendEmail(String email, String content){
           AdminSettings adminSettings = adminService.readSettings(settings);
            new EmailService(email,"Your pending health checkup",
                                          "Hi,<br/>" +
                                                "<br/>" +
                                                "Greetings from Jubination!<br/>" +
                                                "<br/>" +
                                                  content+"<br/><b/>"+
                                                "Trupti<br/>" +
                                                "Diet Chat Bot" ,adminSettings.getMyUsername(),adminSettings.getMyPassword(),adminSettings.getAuth(),adminSettings.getStarttls(),adminSettings.getHost(),adminSettings.getPort(),adminSettings.getSendgridApi()).start();
     }
    
}
