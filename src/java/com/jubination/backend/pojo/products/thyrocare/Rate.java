/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.pojo.products.thyrocare;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author MumbaiZone
 */

    @JsonIgnoreProperties(ignoreUnknown = true)
public class Rate implements Serializable {
    
        String b2b; 
          String b2c;
          String id; 
          String offer_rate;
          String pay_amt;
@JsonBackReference
 TestEntity entity;
    public String getB2b() {
        return b2b;
    }

    public void setB2b(String b2b) {
        this.b2b = b2b;
    }

    public String getB2c() {
        return b2c;
    }

    public void setB2c(String b2c) {
        this.b2c = b2c;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOffer_rate() {
        return offer_rate;
    }

    public void setOffer_rate(String offer_rate) {
        this.offer_rate = offer_rate;
    }

    public String getPay_amt() {
        return pay_amt;
    }

    public void setPay_amt(String pay_amt) {
        this.pay_amt = pay_amt;
    }

    public TestEntity getEntity() {
        return entity;
    }

    public void setEntity(TestEntity entity) {
        this.entity = entity;
    }

     
}
