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
    Long weeklyTotal;
    Long  monthlyTotal;
    
    Long  busy;
    Long  weeklyBusy;
    Long  monthlyBusy;
    
    Long greetingsHangup;
    Long weeklygreetingsHangup;
    Long monthlygreetingsHangup;
    
    Long  spoke;
    Long  weeklySpoke;
    Long  monthlySpoke;
    
    Long  hangupOnConnect;
    Long  weeklyhangupOnConnect;
    Long  monthlyhangupOnConnect;
    
    Long  missCall;
    Long  weeklyMissCall;
    Long  monthlyMissCall;
    
    Long  failed;
    Long  weeklyFailed;
    Long  monthlyFailed;
    
    Long  noAnswer;
    Long  weeklyNoAnswer;
    Long  monthlyNoAnswer;
    
    Long  others;
    Long  weeklyOthers;
    Long  monthlyOthers;
    
    Long  requestedCallBack;
    Long  weeklyRequestedCallBack;
    Long  monthlyRequestedCallBack;
    
    String date;

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

    public Long getWeeklyTotal() {
        return weeklyTotal;
    }

    public void setWeeklyTotal(Long weeklyTotal) {
        this.weeklyTotal = weeklyTotal;
    }

    public Long getMonthlyTotal() {
        return monthlyTotal;
    }

    public void setMonthlyTotal(Long monthlyTotal) {
        this.monthlyTotal = monthlyTotal;
    }

    public Long getBusy() {
        return busy;
    }

    public void setBusy(Long busy) {
        this.busy = busy;
    }

    public Long getWeeklyBusy() {
        return weeklyBusy;
    }

    public void setWeeklyBusy(Long weeklyBusy) {
        this.weeklyBusy = weeklyBusy;
    }

    public Long getMonthlyBusy() {
        return monthlyBusy;
    }

    public void setMonthlyBusy(Long monthlyBusy) {
        this.monthlyBusy = monthlyBusy;
    }

    public Long getGreetingsHangup() {
        return greetingsHangup;
    }

    public void setGreetingsHangup(Long greetingsHangup) {
        this.greetingsHangup = greetingsHangup;
    }

    public Long getWeeklygreetingsHangup() {
        return weeklygreetingsHangup;
    }

    public void setWeeklygreetingsHangup(Long weeklygreetingsHangup) {
        this.weeklygreetingsHangup = weeklygreetingsHangup;
    }

    public Long getMonthlygreetingsHangup() {
        return monthlygreetingsHangup;
    }

    public void setMonthlygreetingsHangup(Long monthlygreetingsHangup) {
        this.monthlygreetingsHangup = monthlygreetingsHangup;
    }

    public Long getSpoke() {
        return spoke;
    }

    public void setSpoke(Long spoke) {
        this.spoke = spoke;
    }

    public Long getWeeklySpoke() {
        return weeklySpoke;
    }

    public void setWeeklySpoke(Long weeklySpoke) {
        this.weeklySpoke = weeklySpoke;
    }

    public Long getMonthlySpoke() {
        return monthlySpoke;
    }

    public void setMonthlySpoke(Long monthlySpoke) {
        this.monthlySpoke = monthlySpoke;
    }

    public Long getHangupOnConnect() {
        return hangupOnConnect;
    }

    public void setHangupOnConnect(Long hangupOnConnect) {
        this.hangupOnConnect = hangupOnConnect;
    }

    public Long getWeeklyhangupOnConnect() {
        return weeklyhangupOnConnect;
    }

    public void setWeeklyhangupOnConnect(Long weeklyhangupOnConnect) {
        this.weeklyhangupOnConnect = weeklyhangupOnConnect;
    }

    public Long getMonthlyhangupOnConnect() {
        return monthlyhangupOnConnect;
    }

    public void setMonthlyhangupOnConnect(Long monthlyhangupOnConnect) {
        this.monthlyhangupOnConnect = monthlyhangupOnConnect;
    }

    public Long getMissCall() {
        return missCall;
    }

    public void setMissCall(Long missCall) {
        this.missCall = missCall;
    }

    public Long getWeeklyMissCall() {
        return weeklyMissCall;
    }

    public void setWeeklyMissCall(Long weeklyMissCall) {
        this.weeklyMissCall = weeklyMissCall;
    }

    public Long getMonthlyMissCall() {
        return monthlyMissCall;
    }

    public void setMonthlyMissCall(Long monthlyMissCall) {
        this.monthlyMissCall = monthlyMissCall;
    }

    public Long getFailed() {
        return failed;
    }

    public void setFailed(Long failed) {
        this.failed = failed;
    }

    public Long getWeeklyFailed() {
        return weeklyFailed;
    }

    public void setWeeklyFailed(Long weeklyFailed) {
        this.weeklyFailed = weeklyFailed;
    }

    public Long getMonthlyFailed() {
        return monthlyFailed;
    }

    public void setMonthlyFailed(Long monthlyFailed) {
        this.monthlyFailed = monthlyFailed;
    }

    public Long getNoAnswer() {
        return noAnswer;
    }

    public void setNoAnswer(Long noAnswer) {
        this.noAnswer = noAnswer;
    }

    public Long getWeeklyNoAnswer() {
        return weeklyNoAnswer;
    }

    public void setWeeklyNoAnswer(Long weeklyNoAnswer) {
        this.weeklyNoAnswer = weeklyNoAnswer;
    }

    public Long getMonthlyNoAnswer() {
        return monthlyNoAnswer;
    }

    public void setMonthlyNoAnswer(Long monthlyNoAnswer) {
        this.monthlyNoAnswer = monthlyNoAnswer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getRequestedCallBack() {
        return requestedCallBack;
    }

    public void setRequestedCallBack(Long requestedCallBack) {
        this.requestedCallBack = requestedCallBack;
    }

    public Long getWeeklyRequestedCallBack() {
        return weeklyRequestedCallBack;
    }

    public void setWeeklyRequestedCallBack(Long weeklyRequestedCallBack) {
        this.weeklyRequestedCallBack = weeklyRequestedCallBack;
    }

    public Long getMonthlyRequestedCallBack() {
        return monthlyRequestedCallBack;
    }

    public void setMonthlyRequestedCallBack(Long monthlyRequestedCallBack) {
        this.monthlyRequestedCallBack = monthlyRequestedCallBack;
    }

    public Long getOthers() {
        return others;
    }

    public void setOthers(Long others) {
        this.others = others;
    }

    public Long getWeeklyOthers() {
        return weeklyOthers;
    }

    public void setWeeklyOthers(Long weeklyOthers) {
        this.weeklyOthers = weeklyOthers;
    }

    public Long getMonthlyOthers() {
        return monthlyOthers;
    }

    public void setMonthlyOthers(Long monthlyOthers) {
        this.monthlyOthers = monthlyOthers;
    }
    
    
    
    
    
}
