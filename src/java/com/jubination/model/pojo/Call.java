/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the ediTor.
 */
package com.jubination.model.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonBackReference;

/**
 *
 * @author Welcome
 */

@Entity
@Table(name="callAPIMessage"
    ,catalog="jubination"
)

@XmlRootElement(name="Call")
public class Call {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long orderId;
    @Column(name="sid")
    private String Sid;
    @Column(name="parent_call_sid")
    private String ParentCallSid;
    @Column(name="date_created")
    private String DateCreated;
    @Column(name="date_updated")    
    private String DateUpdated;
    @Column(name="account_sid")    
    private String AccountSid;
    @Column(name="call_to")    
    private String CallTo;
    @Column(name="call_from")    
    private String CallFrom;
    @Column(name="phone_number_sid")    
    private String PhoneNumberSid;
    @Column(name="status")    
    private String Status;
    @Column(name="start_time")    
    private String StartTime;
    @Column(name="end_time")    
    private String EndTime;
    @Column(name="duration")    
    private String Duration;
    @Column(name="price")    
    private String Price;
    @Column(name="direction")    
    private String Direction;
    @Column(name="answered_by")    
    private String AnsweredBy;
    @Column(name="forwarded_from")    
    private String ForwardedFrom;
    @Column(name="caller_name")    
    private String CallerName;
    @Column(name="uri")    
    private String Uri;
    @Column(name="recording_uri")    
    private String RecordingUrl;
    @Column(name="calltype")    
    private String CallType;
    @Column(name="message")    
    private String Message;
    @Column(name="track_status")    
    private String TrackStatus;
    @Column(name="dail_call_duration")    
    private String DialCallDuration;
    @Column(name="digits")
    private String Digits;
    @Column(name="dail_whom_number")
    private String DialWhomNumber;
  
     @Column(name="date_to_call")
    private String DateToCall;
     @JsonBackReference
          @ManyToOne
    Lead lead;
    
    public long getOrderId() {
        return orderId;
    }
    
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
    
    public String getSid() {
        return Sid;
    }
    @XmlElement(name="Sid")
    public void setSid(String Sid) {
        this.Sid = Sid;
    }

    public String getParentCallSid() {
        return ParentCallSid;
    }
    @XmlElement(name="ParentCallSid")
    public void setParentCallSid(String ParentCallSid) {
        this.ParentCallSid = ParentCallSid;
    }

    public String getDateCreated() {
        return DateCreated;
    }
    @XmlElement(name="DateCreated")
    public void setDateCreated(String DateCreated) {
        this.DateCreated = DateCreated;
    }

    public String getDateUpdated() {
        return DateUpdated;
    }
    @XmlElement(name="DateUpdated")
    public void setDateUpdated(String DateUpdated) {
        this.DateUpdated = DateUpdated;
    }

    public String getAccountSid() {
        return AccountSid;
    }
    @XmlElement(name="AccountSid")
    public void setAccountSid(String AccountSid) {
        this.AccountSid = AccountSid;
    }

    public String getCallTo() {
        return CallTo;
    }
    @XmlElement(name="To")
    public void setCallTo(String CallTo) {
        this.CallTo = CallTo;
    }

    public String getCallFrom() {
        return CallFrom;
    }
    @XmlElement(name="From")
    public void setCallFrom(String CallFrom) {
        this.CallFrom = CallFrom;
    }
    
    public String getTrackStatus() {
        return TrackStatus;
    }
    public void setTrackStatus(String TrackStatus) {
        this.TrackStatus = TrackStatus;
    }
    

    public String getPhoneNumberSid() {
        return PhoneNumberSid;
    }
    @XmlElement(name="PhoneNumberSid")
    public void setPhoneNumberSid(String PhoneNumberSid) {
        this.PhoneNumberSid = PhoneNumberSid;
    }

    public String getStatus() {
        return Status;
    }
    @XmlElement(name="Status")
    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getStartTime() {
        return StartTime;
    }
    @XmlElement(name="StartTime")
    public void setStartTime(String StartTime) {
        this.StartTime = StartTime;
    }

    public String getEndTime() {
        return EndTime;
    }
    @XmlElement(name="EndTime")
    public void setEndTime(String EndTime) {
        this.EndTime = EndTime;
    }

    public String getDuration() {
        return Duration;
    }
    @XmlElement(name="Duration")
    public void setDuration(String Duration) {
        this.Duration = Duration;
    }

    public String getPrice() {
        return Price;
    }
    @XmlElement(name="Price")
    public void setPrice(String Price) {
        this.Price = Price;
    }

    public String getDirection() {
        return Direction;
    }
    @XmlElement(name="Direction")
    public void setDirection(String Direction) {
        this.Direction = Direction;
    }

    public String getAnsweredBy() {
        return AnsweredBy;
    }
    @XmlElement(name="AnsweredBy")
    public void setAnsweredBy(String AnsweredBy) {
        this.AnsweredBy = AnsweredBy;
    }

    public String getForwardedFrom() {
        return ForwardedFrom;
    }
    @XmlElement(name="ForwardedFrom")
    public void setForwardedFrom(String ForwardedFrom) {
        this.ForwardedFrom = ForwardedFrom;
    }

    public String getCallerName() {
        return CallerName;
    }
    @XmlElement(name="CallerName")
    public void setCallerName(String CallerName) {
        this.CallerName = CallerName;
    }

    public String getUri() {
        return Uri;
    }
    @XmlElement(name="Uri")
    public void setUri(String Uri) {
        this.Uri = Uri;
    }

    public String getRecordingUrl() {
        return RecordingUrl;
    }
    @XmlElement(name="RecordingUrl")
    public void setRecordingUrl(String RecordingUrl) {
        this.RecordingUrl = RecordingUrl;
    }

    public String getCallType() {
        return CallType;
    }
    @XmlElement(name="CallType")
    public void setCallType(String CallType) {
        this.CallType = CallType;
    }

    public String getMessage() {
        return Message;
    }
    @XmlElement(name="Message")
    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getDialCallDuration() {
        return DialCallDuration;
    }
    
    public void setDialCallDuration(String DialCallDuration) {
        this.DialCallDuration = DialCallDuration;
    }

  
    public String getDigits() {
        return Digits;
    }
    public void setDigits(String Digits) {
        this.Digits = Digits;
    }

    public String getDialWhomNumber() {
        return DialWhomNumber;
    }
    
    public void setDialWhomNumber(String DialWhomNumber) {
        this.DialWhomNumber = DialWhomNumber;
    }

    public Lead getLead() {
        return lead;
    }

    public void setLead(Lead lead) {
        this.lead = lead;
    }

   

    public String getDateToCall() {
        return DateToCall;
    }

    public void setDateToCall(String DateToCall) {
        this.DateToCall = DateToCall;
    }

  

    
    
    
    
    
}
