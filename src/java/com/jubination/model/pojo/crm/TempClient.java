/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.pojo.crm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author MumbaiZone
 */

@Entity
@Table(name="temp_client"
    ,catalog="jubination"
)

public class TempClient implements Serializable {
@Id
@GeneratedValue(strategy=GenerationType.AUTO) 
 long clientId;
 @Column(name="email_id")
 String emailId;
  @Column(name="name")
 String name;
  @Column(name="campaign_name")
 String campaignName;
  @Column(name="age")
 String age;
  @Column(name="gender")
 String gender;
 @Column(name="phone_number")
 String phoneNumber;
 @Column(name="address")
 String address;
 @Column(name="city")
 String city;
 @Column(name="pincode")
 String pincode;
 @Column(name="date_creation")
 String dateCreation;
 @Column(name="date_updated")
 String dateUpdated;
 @Column(name="ip_address")
  String ipAddress;
  @Column(name="initial_comments")
  String initialComments;
  
  @Column(name="source")
  String source;
  @Column(name="pub_id")
  String pubId;
  @Column(name="call_status")
String callStatus;
  @Column(name="overnight")
 boolean overnight;
 @Column(name="temp_lead_details")
 String tempLeadDetails;
     @Column(name="hardcopy")
    private String hardcopy;
    @Column(name="order_id")
    private String orderId;
    @Column(name="product")
    private String product;
    @Column(name="service_type")
    private String serviceType;
    @Column(name="order_by")
    private String orderBy;
    @Column(name="appointment_date")
    private String appointmentDate;
    @Column(name="appointment_time")
    private String appointmentTime;
    @Column(name="ben_count")
    private Integer benCount;
    
    @Column(name="report_code")
    private String reportCode;
    @Column(name="rate")
    private String rate;
    @Column(name="margin")
    private String margin;
    @Column(name="passon")
    private String passon;
    @Column(name="pay_type")
    private String payType;
    @Column(name="handling_charges")
    private String handlingCharges;
     @Column(name="beneficiaries")
    private String beneficiaries;

    public TempClient() {
    }

    public TempClient(String emailId, String name, String campaignName, String age, String gender, String phoneNumber, String address, String city, String pincode, String dateCreation, String dateUpdated, String ipAddress, String initialComments, String source, String pubId, String callStatus, boolean overnight, String tempLeadDetails, String hardcopy, String orderId, String product, String serviceType, String orderBy, String appointmentDate, String appointmentTime, Integer benCount, String reportCode, String rate, String margin, String passon,String payType, String handlingCharges,String beneficiaries) {
        this.emailId = emailId;
        this.name = name;
        this.campaignName = campaignName;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.pincode = pincode;
        this.dateCreation = dateCreation;
        this.dateUpdated = dateUpdated;
        this.ipAddress = ipAddress;
        this.initialComments = initialComments;
        this.source = source;
        this.pubId = pubId;
        this.callStatus = callStatus;
        this.overnight = overnight;
        this.tempLeadDetails = tempLeadDetails;
        this.hardcopy = hardcopy;
        this.orderId = orderId;
        this.product = product;
        this.serviceType = serviceType;
        this.orderBy = orderBy;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.benCount = benCount;
        this.reportCode = reportCode;
        this.rate = rate;
        this.margin = margin;
        this.passon=passon;
        this.payType = payType;
        this.handlingCharges = handlingCharges;
        this.beneficiaries=beneficiaries;
    }

    

    public long getClientId() {
        return clientId;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPincode() {
        return pincode;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public boolean isOvernight() {
        return overnight;
    }

    public String getTempLeadDetails() {
        return tempLeadDetails;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public void setOvernight(boolean overnight) {
        this.overnight = overnight;
    }

    public void setTempLeadDetails(String tempLeadDetails) {
        this.tempLeadDetails = tempLeadDetails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getInitialComments() {
        return initialComments;
    }

    public void setInitialComments(String initialComments) {
        this.initialComments = initialComments;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPubId() {
        return pubId;
    }

    public void setPubId(String pubId) {
        this.pubId = pubId;
    }

    public String getCallStatus() {
        return callStatus;
    }

    public void setCallStatus(String callStatus) {
        this.callStatus = callStatus;
    }

    public String getHardcopy() {
        return hardcopy;
    }

    public void setHardcopy(String hardcopy) {
        this.hardcopy = hardcopy;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Integer getBenCount() {
        return benCount;
    }

    public void setBenCount(Integer benCount) {
        this.benCount = benCount;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
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

   

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getHandlingCharges() {
        return handlingCharges;
    }

    public void setHandlingCharges(String handlingCharges) {
        this.handlingCharges = handlingCharges;
    }

    public String getBeneficiaries() {
        return beneficiaries;
    }

    public void setBeneficiaries(String beneficiaries) {
        this.beneficiaries = beneficiaries;
    }

   

    
}