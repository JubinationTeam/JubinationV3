/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.pojo.products.thyrocare;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;


    @JsonIgnoreProperties(ignoreUnknown = true)
public class Childs implements Serializable {
     @JsonBackReference
 TestEntity entity;
        String code; 
        String group_name; 
        String name; 
        String type; 
 
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TestEntity getEntity() {
        return entity;
    }

    public void setEntity(TestEntity entity) {
        this.entity = entity;
    }
       
}
