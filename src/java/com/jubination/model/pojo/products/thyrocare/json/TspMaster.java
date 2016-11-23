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
@Table(name="tsp_master", catalog="jubination")
    @JsonIgnoreProperties(ignoreUnknown = true)
public class TspMaster implements Serializable {
    @Id
     @GeneratedValue(strategy=GenerationType.AUTO)
     Long id;
     @Column(name="bct_mobile")
    String BCT_MOBILE;
     @Column(name="bct_name")
      String BCT_NAME;
     @Column(name="email")
      String EMAIL;
     @Column(name="landline")
      String LANDLINE;
     @Column(name="mobile")
      String MOBILE;
     @Column(name="tsp")
      String TSP;
 @JsonBackReference
 @ManyToOne
 ReportStatus reportStatus;
    public String getBCT_MOBILE() {
        return BCT_MOBILE;
    }

    public void setBCT_MOBILE(String BCT_MOBILE) {
        this.BCT_MOBILE = BCT_MOBILE;
    }

    public String getBCT_NAME() {
        return BCT_NAME;
    }

    public void setBCT_NAME(String BCT_NAME) {
        this.BCT_NAME = BCT_NAME;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getLANDLINE() {
        return LANDLINE;
    }

    public void setLANDLINE(String LANDLINE) {
        this.LANDLINE = LANDLINE;
    }

    public String getMOBILE() {
        return MOBILE;
    }

    public void setMOBILE(String MOBILE) {
        this.MOBILE = MOBILE;
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
