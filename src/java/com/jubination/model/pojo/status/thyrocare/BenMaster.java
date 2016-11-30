/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.pojo.status.thyrocare;

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
@Table(name="ben_master", catalog="jubination")
    @JsonIgnoreProperties(ignoreUnknown = true)
public class BenMaster implements Serializable {
    
    @Id
      @Column(name="id")
      String ID;
    @Column(name="age")
    String AGE;
    @Column(name="gender")
      String GENDER;
    
    @Column(name="mobile")
      String MOBILE;
    @Column(name="name")
      String NAME;
    @Column(name="reminder")
      String REMINDER;
    @Column(name="status")
      String STATUS;
    @Column(name="url")
      String URL;
     @JsonBackReference
 @ManyToOne
 ReportStatus reportStatus;

    public String getAGE() {
        return AGE;
    }

    public void setAGE(String AGE) {
        this.AGE = AGE;
    }

    public String getGENDER() {
        return GENDER;
    }

    public void setGENDER(String GENDER) {
        this.GENDER = GENDER;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMOBILE() {
        return MOBILE;
    }

    public void setMOBILE(String MOBILE) {
        this.MOBILE = MOBILE;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getREMINDER() {
        return REMINDER;
    }

    public void setREMINDER(String REMINDER) {
        this.REMINDER = REMINDER;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

   

    public ReportStatus getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(ReportStatus reportStatus) {
        this.reportStatus = reportStatus;
    }
      
}
