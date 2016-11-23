/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.pojo.products.thyrocare.json;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author MumbaiZone
 */@Entity
@Table(name="childs", catalog="jubination")
   @JsonIgnoreProperties(ignoreUnknown = true)
public class Childs implements Serializable {
         @Id
      @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
     @Column(name="code")
        String code; 
     @Column(name="group_name")
        String group_name; 
     @Column(name="name")
        String name; 
     @Column(name="type")
        String type; 
 @JsonBackReference
 @ManyToOne
 TestEntity entity;
 
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Childs{" + "id=" + id + ", code=" + code + ", group_name=" + group_name + ", name=" + name + ", type=" + type + ", entity=" + entity + '}';
    }
        
}
