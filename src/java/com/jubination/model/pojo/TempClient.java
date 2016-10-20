/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.pojo;

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

    public TempClient() {
    }

    public TempClient(String name,String campaignName,String age,String gender,String emailId, String phoneNumber, String address, String city, String pincode, String dateCreation, String dateUpdated, boolean overnight, String tempLeadDetails,String ipAddress,String initialComments,String callStatus) {
       this.name=name;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.pincode = pincode;
        this.dateCreation = dateCreation;
        this.dateUpdated = dateUpdated;
        this.overnight = overnight;
        this.tempLeadDetails = tempLeadDetails;
        this.campaignName=campaignName;
        this.age=age;
        this.gender=gender;
        this.ipAddress=ipAddress;
        this.initialComments=initialComments;
        this.callStatus=callStatus;
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

   

    
}
