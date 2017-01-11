/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.service.core.chatbot;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @author MumbaiZone
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DashBot {
    String text;
    String userId;

    public DashBot(String text, String userId) {
        this.text = text;
        this.userId = userId;
    }
    
    

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    
}
