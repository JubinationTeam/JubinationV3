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
        List<String> questions = new ArrayList<>();
        
        questions.add("Hi, I am Ruhi.Today I want to help you get in the habit of clean eating. Lets create a diet-chart for you.");
        questions.add( "Let's get started");
        questions.add("Can I get your name?");
        responses.add(new ChatBotResponse(1, questions, "text", null));
        
        
        questions = new ArrayList<>();
        questions.add(", wish you a very healthy and happy new year. ");
        questions.add( "Help me with your gender please ");
        List<String> optionsGender = new ArrayList<>();
        optionsGender.add("Male");
        optionsGender.add("Female");
        responses.add(new ChatBotResponse(2,  questions, "options", optionsGender));
        
        
         questions = new ArrayList<>();
        questions.add("Knowing your weight is important to me ");
        questions.add( "How much do you weigh? (please answer in kgs)");
        responses.add(new ChatBotResponse(3, questions , "text", null));
        
         questions = new ArrayList<>();
        questions.add(", what's your height? (please answer in ft.inches)");
        responses.add(new ChatBotResponse(4, questions , "text", null));
        
        questions = new ArrayList<>();
        questions.add("What's your age? (please answer 'in years')");
        responses.add(new ChatBotResponse(5, questions, "text", null));
        
         List<String> optionsFood = new ArrayList<>();
        optionsFood.add("Veg");
        optionsFood.add("Non veg");
         questions = new ArrayList<>();
        questions.add("What is your diet-preference?");
        responses.add(new ChatBotResponse(6, questions, "options", optionsFood));
        
        
        List<String> optionsLifestyle = new ArrayList<>();
        optionsLifestyle.add("Sedentary");
        optionsLifestyle.add("Moderately Active");
        optionsLifestyle.add("Active");
         questions = new ArrayList<>();
        questions.add(", wanted to know a little more about your daily lifestyle" );
        questions.add("How active are you?" );
        responses.add(new ChatBotResponse(7, questions  , "options", optionsLifestyle));

        List<String> optionsDiseases = new ArrayList<>();
        optionsDiseases.add("Diabetes");
        optionsDiseases.add("High Lipid (Cholesterol) level");
        optionsDiseases.add("Hypothyroidism (High TSH levels)");
        optionsDiseases.add("High Blood Pressure");
        optionsDiseases.add("PCOS / PCOD");
        optionsDiseases.add("Hyperthyroidism (Low TSH levels)");
        optionsDiseases.add("None");
        questions = new ArrayList<>();
        questions.add(", do you have any existing lifestyle conditions? I shall get the diet-chart prepared accordingly in that case." );
        responses.add(new ChatBotResponse(8, questions , "options", optionsDiseases));
        
        
        questions = new ArrayList<>();
        questions.add(  " Thank you. We are almost there"  );
         questions.add(  "Help me with  your email id pls. I need to send you your customized clean-eating chart when ready"  );
        responses.add(new ChatBotResponse(9, questions , "text", null));
        
        
         questions = new ArrayList<>();
        questions.add(  " And what's your phone number. We shall use this to create your account" );
        responses.add(new ChatBotResponse(10, questions  , "text", null));
        
        
        questions = new ArrayList<>();
        questions.add(" Thanks for being so supportive");
        questions.add( "Here's where you access your diet-chart");
        questions.add("Please let me know if you have any queries on your chart");
        questions.add("You can drop me an email to at support@jubination.com. I will get everything sorted");
        responses.add(new ChatBotResponse(11, questions , "link", null));
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
            else if( answerMap.get(sessionId)==null){
                map.put(sessionId, generateFlow());
                answerMap.put(sessionId, new ArrayList<String>());
                
            }
          
            
          
            
            
            
           String answer=request.getLastAnswer();
            
             switch (countId-1) {
               case 0:
                   answerMap.get(sessionId).add(answer);
                   break;
                   //name
               case 1: 
                   
                   if(answer!=null){
                       answer=answer.trim();
                       if(answer.contains(" ")){
                            answer=answer.split(" ")[answer.split(" ").length-1];
                            answer=answer.substring(0, 1).toUpperCase()+answer.substring(1).toLowerCase();
                         }    
                   }
                   answerMap.get(sessionId).add(answer);
                   map.get(sessionId).get(countId-1).getQuestion().set(0,"Hi "+answerMap.get(sessionId).get(1)+map.get(sessionId).get(countId-1).getQuestion().get(0));
                   break;
                   //gender
               case 2:
                   if(answer.equals("Male")){
                           answer="male";
                       }
                       else if(answer.equals("Female")){
                           answer="female";
                       }
                   answerMap.get(sessionId).add(answer);
                   break;
                   //weight
                   case 3:
                   answerMap.get(sessionId).add(answer);
                   map.get(sessionId).get(countId-1).getQuestion().set(0,answerMap.get(sessionId).get(1)+map.get(sessionId).get(countId-1).getQuestion().get(0));
                   break;
                   //height
               case 4:
                   answerMap.get(sessionId).add(answer);
                   break;
                   //age
               case 5:
                   answerMap.get(sessionId).add(answer);
                   break;
                   //meal preference
                   case 6:
                       if(answer.equals("Veg")){
                           answer="veg";
                       }
                       else if(answer.equals("Non veg")){
                           answer="non-veg";
                       }
                   answerMap.get(sessionId).add(answer);
                   map.get(sessionId).get(countId-1).getQuestion().set(0,"Oooh, I love "+answerMap.get(sessionId).get(6)+" too"+map.get(sessionId).get(countId-1).getQuestion().get(0));
                   break;
                   //active
               case 7: 
                   
        if(answer.equals("Sedentary")){
            answer="sedentary";
        }
        else if(answer.equals("Moderately Active")){
            answer="moderately-active";
        }
        else if(answer.equals("Active")){
            answer="very-active";
        }


                   answerMap.get(sessionId).add(answer);
                   map.get(sessionId).get(countId-1).getQuestion().set(0,answerMap.get(sessionId).get(1)+map.get(sessionId).get(countId-1).getQuestion().get(0));
                   break;
                   //diseases
               case 8:
             
        
             if(answer.equals("Diabetes")){
            answer="diabetes";
        }
        else if(answer.equals("High Lipid (Cholesterol) level")){
            answer="high-lipid-cholesterol-level";
        }
        else if(answer.equals("High Blood Pressure")){
            answer="high-blood-pressure";
        }
              else if(answer.equals("Hypothyroidism (High TSH levels)")){
            answer="hypothyroidism-high-tsh-levels";
        }
        else if(answer.equals("Hyperthyroidism (Low TSH levels)")){
            answer="hypothyroidism-high-tsh-levels";
        }
              else if(answer.equals("PCOS / PCOD")){
            answer="pcos-pcod";
        }
        else if(answer.equals("None")){
            answer="none";
        }
                   answerMap.get(sessionId).add(answer);
                   
                   break;
                   //email
                   case 9:
                   answerMap.get(sessionId).add(answer);
                   
                   break;
                   //phone num
               default:
                   answerMap.get(sessionId).add(answer);
                   
                   break;
           }
            
            
            
            
            if(answerMap.get(sessionId).size()==11){
              //send to lms
                System.out.println("send to lms");
                DietChart diet = new DietChart();
                diet.setDietChart(answerMap.get(sessionId));
                String response=updater.sendAutomatedUpdate(diet);
                System.out.println(response+":::::::::::::::::::::::::::");
            }
            System.out.println(answerMap.toString());
            map.get(sessionId).get(countId-1).setSessionId(sessionId);
            return map.get(sessionId).get(countId-1);

    }
   
    
}
