/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.pojo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author MumbaiZone
 */

@Entity
@Table(name="client"
    ,catalog="jubination"
)

public class Client {
@Id
@GeneratedValue(strategy=GenerationType.AUTO) 
 long clientId;
 @Column(name="email_id",unique = true)
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
  
     @Column(name="priority")
     private Integer priority;
  @Column(name="overnight")
 boolean overnight;
 @Column(name="temp_lead_details")
 String tempLeadDetails;
  @JsonManagedReference 
 @OneToMany(mappedBy="client")
      @Cascade({CascadeType.PERSIST,CascadeType.DELETE,CascadeType.SAVE_UPDATE})
      @NotFound(action=NotFoundAction.IGNORE)
        private List<Lead> lead= new ArrayList<>();

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }
      

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public List<Lead> getLead() {
        return lead;
    }

    public void setLead(List<Lead> lead) {
        this.lead = lead;
    }

    public boolean isOvernight() {
        return overnight;
    }

    public void setOvernight(boolean overnight) {
        this.overnight = overnight;
    }

    public String getTempLeadDetails() {
        return tempLeadDetails;
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
 
 
 
 
}
