/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.pojo.crm;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author MumbaiZone
 */
@Entity
@Table(name="data_analytics"
    ,catalog="jubination"
)
public class DataAnalytics implements Serializable {
    @Id
@GeneratedValue(strategy=GenerationType.AUTO) 
 long id;
    Long total;
    
    Long  busy;
    
    Long greetingsHangup;
    
    Long  spoke;
    
    Long  hangupOnConnect;
    
    Long  missCall;
    
    Long  failed;
    
    Long  noAnswer;
    
    Long  others;
    
    Long  requestedCallBack;
    
    String date;
    String fromDate;
    String toDate;
    String type;
    
      Long booked;
            Long  done;
             Long cancelled;
             Long serviced;
             Long revised;
             Long deferredStatus;
             Long  appointment;
            Long yetToAssign;

    String requestedTime;
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getBusy() {
        return busy;
    }

    public void setBusy(Long busy) {
        this.busy = busy;
    }

    public Long getGreetingsHangup() {
        return greetingsHangup;
    }

    public void setGreetingsHangup(Long greetingsHangup) {
        this.greetingsHangup = greetingsHangup;
    }

    public Long getSpoke() {
        return spoke;
    }

    public void setSpoke(Long spoke) {
        this.spoke = spoke;
    }

    public Long getHangupOnConnect() {
        return hangupOnConnect;
    }

    public void setHangupOnConnect(Long hangupOnConnect) {
        this.hangupOnConnect = hangupOnConnect;
    }

    public Long getMissCall() {
        return missCall;
    }

    public void setMissCall(Long missCall) {
        this.missCall = missCall;
    }

    public Long getFailed() {
        return failed;
    }

    public void setFailed(Long failed) {
        this.failed = failed;
    }

    public Long getNoAnswer() {
        return noAnswer;
    }

    public void setNoAnswer(Long noAnswer) {
        this.noAnswer = noAnswer;
    }

    public Long getOthers() {
        return others;
    }

    public void setOthers(Long others) {
        this.others = others;
    }

    public Long getRequestedCallBack() {
        return requestedCallBack;
    }

    public void setRequestedCallBack(Long requestedCallBack) {
        this.requestedCallBack = requestedCallBack;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRequestedTime() {
        return requestedTime;
    }

    public void setRequestedTime(String requestedTime) {
        this.requestedTime = requestedTime;
    }

    public Long getBooked() {
        return booked;
    }

    public void setBooked(Long booked) {
        this.booked = booked;
    }

    public Long getDone() {
        return done;
    }

    public void setDone(Long done) {
        this.done = done;
    }

    public Long getCancelled() {
        return cancelled;
    }

    public void setCancelled(Long cancelled) {
        this.cancelled = cancelled;
    }

    public Long getServiced() {
        return serviced;
    }

    public void setServiced(Long serviced) {
        this.serviced = serviced;
    }

    public Long getRevised() {
        return revised;
    }

    public void setRevised(Long revised) {
        this.revised = revised;
    }

    public Long getDeferredStatus() {
        return deferredStatus;
    }

    public void setDeferredStatus(Long deferredStatus) {
        this.deferredStatus = deferredStatus;
    }

    public Long getAppointment() {
        return appointment;
    }

    public void setAppointment(Long appointment) {
        this.appointment = appointment;
    }

    public Long getYetToAssign() {
        return yetToAssign;
    }

    public void setYetToAssign(Long yetToAssign) {
        this.yetToAssign = yetToAssign;
    }

  
    
    
    
}
