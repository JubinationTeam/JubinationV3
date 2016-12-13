/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.pojo.crm;

import com.jubination.model.pojo.crm.Lead;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author MumbaiZone
 */
@Entity
@Table(catalog="jubination",name="beneficiaries")
public class Beneficiaries implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    Long id;
    
    @Column(name="name")
    String name;
    @Column(name="gender")
    String gender;
    @Column(name="age")
    String age;

@JsonBackReference  
   @ManyToOne
    Lead lead;
 
@Transient
 boolean persistent;
    public Beneficiaries() {
    }

    public Beneficiaries(String name, String gender, String age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Lead getLead() {
        return lead;
    }

    public void setLead(Lead lead) {
        this.lead = lead;
    }
    
      public boolean isPersistent() {
        return persistent;
    }

    @PostLoad
    @PostPersist
    public void setPersistent() {
        this.persistent = true;
    }

    
    
    
}
