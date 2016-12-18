/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.pojo.thyrocare.report;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.stereotype.Component;

/**
 *
 * @author MumbaiZone
 */
@Component
@XmlRootElement(name="LEADDETAILS")
public class ThyrocareLead implements Serializable{
  
                        String patient;
                        String serialNumber;
                        String mobile;
                        String email;
                        String tests;
                        String labCode;
                        String sct;
                        String bvt;
                        String rrt;
                        String workOrderId;
                        String importDate;
                        String leadId;
                        String couponCode;
                        String offerName;
                        String sampleType;
                        private ThyrocareLead.Barcodes barcodes;
   

                    public ThyrocareLead.Barcodes getBarcodes() {
                                    return barcodes;
                    }

                    
                    
                    @XmlElement(name = "BARCODES")
                    public void setBarcodes(ThyrocareLead.Barcodes barcodes) {
                                    this.barcodes = barcodes;
                    }
    public String getSerialNumber() {
        return serialNumber;
    }
             
    @XmlElement(name = "SL_NO")
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getPatient() {
        return patient;
    }
    @XmlElement(name = "PATIENT")
    public void setPatient(String patient) {
        this.patient = patient;
    }

                        
                        
    public String getMobile() {
        return mobile;
    }
    @XmlElement(name = "MOBILE")
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }
@XmlElement(name = "EMAIL")
    public void setEmail(String email) {
        this.email = email;
    }

    public String getTests() {
        return tests;
    }
@XmlElement(name = "TESTS")
    public void setTests(String tests) {
        this.tests = tests;
    }

    public String getLabCode() {
        return labCode;
    }
@XmlElement(name = "LAB_CODE")
    public void setLabCode(String labCode) {
        this.labCode = labCode;
    }

    public String getSct() {
        return sct;
    }
@XmlElement(name = "SCT")
    public void setSct(String sct) {
        this.sct = sct;
    }

    public String getBvt() {
        return bvt;
    }
@XmlElement(name = "BVT")
    public void setBvt(String bvt) {
        this.bvt = bvt;
    }

    public String getRrt() {
        return rrt;
    }
@XmlElement(name = "RRT")
    public void setRrt(String rrt) {
        this.rrt = rrt;
    }

    public String getWorkOrderId() {
        return workOrderId;
    }
@XmlElement(name = "WORKORDERID")
    public void setWorkOrderId(String workOrderId) {
        this.workOrderId = workOrderId;
    }

    public String getImportDate() {
        return importDate;
    }
@XmlElement(name = "IMPORT_DATE")
    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public String getLeadId() {
        return leadId;
    }
@XmlElement(name = "LEADID")
    public void setLeadId(String leadId) {
        this.leadId = leadId;
    }

    public String getCouponCode() {
        return couponCode;
    }
@XmlElement(name = "COUPON_CODE")
    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getOfferName() {
        return offerName;
    }
@XmlElement(name = "OFFER_NAME")
    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getSampleType() {
        return sampleType;
    }
@XmlElement(name = "SAMPLETYPE")
    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

   public static class Barcodes {

                                    @XmlElement(name = "BARCODE")
                                    private List<ThyrocareBarcode> barcode;
                    

                  
                                    public List<ThyrocareBarcode> getBarcode() {
                                                if ( barcode== null) {
                                                                barcode = new ArrayList<>();
                                                }
                                                return this.barcode;
                                    }

                                 

                                    
                    }
                       
                        
                        
                        
                        
}
