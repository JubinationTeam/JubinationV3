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
public class DietChart {
    
    List<String> dietChart=new ArrayList<>();
    String source="api";

    public List<String> getDietChart() {
        return dietChart;
    }

    public void setDietChart(List<String> dietChart) {
        this.dietChart = dietChart;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

 
    
    
}
