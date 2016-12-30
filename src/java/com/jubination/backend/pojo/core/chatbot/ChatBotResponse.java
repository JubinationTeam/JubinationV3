/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.pojo.core.chatbot;

import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @author MumbaiZone
 */
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatBotResponse {
    
    
    private int serialNumber;
    private int id;
    private String question;
    private String answerType;
    private String answer;
    private String sessionId;
    private List<String> options = new ArrayList<>();

    public ChatBotResponse(){
        
    }
    
    public ChatBotResponse(int id, String question, String answerType, List<String> options) {
        this.id = id;
        this.question = question;
        this.answerType = answerType;
        if(options!=null){
            this.options=options;
        }
    }
    
    
    

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

   

    
    
    
    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    
    
    
}
