/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.pojo.products.thyrocare;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;

/**
 *
 * @author MumbaiZone
 */
@Entity
@Table(name="rate", catalog="jubination")
  @JsonIgnoreProperties(ignoreUnknown = true)
public class Rate implements Serializable {
    @Id
      @GeneratedValue(strategy=GenerationType.AUTO)
    private Long rateId;
     @Column(name="b2b")
    String b2b; 
     @Column(name="b2c")
          String b2c;
     @Column(name="id")
          String id; 
     @Column(name="offer_rate")
          String offer_rate;
     @Column(name="pay_amt") 
          String pay_amt;
 @JsonBackReference
 @OneToOne
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

    public Long getRateId() {
        return rateId;
    }

    public void setRateId(Long rateId) {
        this.rateId = rateId;
    }

    @Override
    public String toString() {
        return "Rate{" + "rateId=" + rateId + ", b2b=" + b2b + ", b2c=" + b2c + ", id=" + id + ", offer_rate=" + offer_rate + ", pay_amt=" + pay_amt + ", entity=" + entity + '}';
    }
          
}
