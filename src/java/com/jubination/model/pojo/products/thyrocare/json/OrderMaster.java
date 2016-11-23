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
 */
@Entity
@Table(name="order_master", catalog="jubination")
    @JsonIgnoreProperties(ignoreUnknown = true)
public class OrderMaster implements Serializable {
     @Id
     @GeneratedValue(strategy=GenerationType.AUTO)
     Long id;
     @Column(name="address")
      String ADDRESS;
     @Column(name="booking_through")
      String BOOKING_THROUGH;
     @Column(name="ids")
      String IDS;
     @Column(name="names_")
      String NAMES;
     @Column(name="order_no")
      String ORDER_NO;
     @Column(name="pay_type")
      String PAY_TYPE;
     @Column(name="products")
      String PRODUCTS;
     @Column(name="rate")
      String RATE;
     @Column(name="remarks")
      String REMARKS;
     @Column(name="servicce_type")
      String SERVICE_TYPE;
     @Column(name="status")
      String STATUS;
     @Column(name="tsp")
      String TSP;
 @JsonBackReference
 @ManyToOne
 ReportStatus reportStatus;
    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getBOOKING_THROUGH() {
        return BOOKING_THROUGH;
    }

    public void setBOOKING_THROUGH(String BOOKING_THROUGH) {
        this.BOOKING_THROUGH = BOOKING_THROUGH;
    }

    public String getIDS() {
        return IDS;
    }

    public void setIDS(String IDS) {
        this.IDS = IDS;
    }

    public String getNAMES() {
        return NAMES;
    }

    public void setNAMES(String NAMES) {
        this.NAMES = NAMES;
    }

    public String getORDER_NO() {
        return ORDER_NO;
    }

    public void setORDER_NO(String ORDER_NO) {
        this.ORDER_NO = ORDER_NO;
    }

    public String getPAY_TYPE() {
        return PAY_TYPE;
    }

    public void setPAY_TYPE(String PAY_TYPE) {
        this.PAY_TYPE = PAY_TYPE;
    }

    public String getPRODUCTS() {
        return PRODUCTS;
    }

    public void setPRODUCTS(String PRODUCTS) {
        this.PRODUCTS = PRODUCTS;
    }

    public String getRATE() {
        return RATE;
    }

    public void setRATE(String RATE) {
        this.RATE = RATE;
    }

    public String getREMARKS() {
        return REMARKS;
    }

    public void setREMARKS(String REMARKS) {
        this.REMARKS = REMARKS;
    }

    public String getSERVICE_TYPE() {
        return SERVICE_TYPE;
    }

    public void setSERVICE_TYPE(String SERVICE_TYPE) {
        this.SERVICE_TYPE = SERVICE_TYPE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getTSP() {
        return TSP;
    }

    public void setTSP(String TSP) {
        this.TSP = TSP;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReportStatus getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(ReportStatus reportStatus) {
        this.reportStatus = reportStatus;
    }
      
      
}
