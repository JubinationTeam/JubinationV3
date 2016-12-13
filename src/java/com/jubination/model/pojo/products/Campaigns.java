/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.pojo.products;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author MumbaiZone
 */
@Entity
@Table(name="campaigns",catalog="jubination")
public class Campaigns implements java.io.Serializable{
    
    @Id
    @Column(name="name")
    String name;
    @Column(name="products")
    String products;
    @Column(name="rate")
    String rate;
    @Column(name="hc")
    String hc;
    @Column(name="margin")
   String margin; 
    @Column(name="passon")
   String passon; 
    @Column(name="report_code")
    String reportCode;
    @Column(name="service_type")
    String serviceType;

  
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getHc() {
        return hc;
    }

    public void setHc(String hc) {
        this.hc = hc;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getMargin() {
        return margin;
    }

    public void setMargin(String margin) {
        this.margin = margin;
    }

    public String getPasson() {
        return passon;
    }

    public void setPasson(String passon) {
        this.passon = passon;
    }

    
    
    
    
    
}
