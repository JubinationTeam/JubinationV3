/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.pojo.status.thyrocare;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author MumbaiZone
 */
@Entity
@Table(name="lead_history_master", catalog="jubination")
    @JsonIgnoreProperties(ignoreUnknown = true)
public class LeadHistoryMaster  implements Serializable {
      @Id
     @GeneratedValue(strategy=GenerationType.AUTO)
     Long id;
      @JsonManagedReference
 @OneToMany(mappedBy="leadHistoryMaster")
      @Cascade({CascadeType.PERSIST,CascadeType.DELETE,CascadeType.SAVE_UPDATE})
      private List<DateDetails> APPOINT_ON = new ArrayList<>();
      @JsonManagedReference
 @OneToMany(mappedBy="leadHistoryMaster")
      @Cascade({CascadeType.PERSIST,CascadeType.DELETE,CascadeType.SAVE_UPDATE})
      private List<DateDetails> ASSIGN_TSP_ON = new ArrayList<>();
      @JsonManagedReference
 @OneToMany(mappedBy="leadHistoryMaster")
      @Cascade({CascadeType.PERSIST,CascadeType.DELETE,CascadeType.SAVE_UPDATE})
      private List<DateDetails> BOOKED_ON = new ArrayList<>();
      @JsonManagedReference
 @OneToMany(mappedBy="leadHistoryMaster")
      @Cascade({CascadeType.PERSIST,CascadeType.DELETE,CascadeType.SAVE_UPDATE})
      private List<DateDetails> DELIVERD_ON = new ArrayList<>();
      @JsonManagedReference
 @OneToMany(mappedBy="leadHistoryMaster")
      @Cascade({CascadeType.PERSIST,CascadeType.DELETE,CascadeType.SAVE_UPDATE})
      private List<DateDetails> REAPPOINT_ON = new ArrayList<>();
      @JsonManagedReference
 @OneToMany(mappedBy="leadHistoryMaster")
      @Cascade({CascadeType.PERSIST,CascadeType.DELETE,CascadeType.SAVE_UPDATE})
      private List<DateDetails> REJECTED_ON = new ArrayList<>();
      @JsonManagedReference
 @OneToMany(mappedBy="leadHistoryMaster")
      @Cascade({CascadeType.PERSIST,CascadeType.DELETE,CascadeType.SAVE_UPDATE})
      private List<DateDetails> REPORTED_ON = new ArrayList<>();
      @JsonManagedReference
 @OneToMany(mappedBy="leadHistoryMaster")
      @Cascade({CascadeType.PERSIST,CascadeType.DELETE,CascadeType.SAVE_UPDATE})
      private List<DateDetails> SERVICED_ON = new ArrayList<>();
 @JsonBackReference
 @ManyToOne
 ReportStatus reportStatus;
    public List<DateDetails> getAPPOINT_ON() {
        return APPOINT_ON;
    }

    public List<DateDetails> getASSIGN_TSP_ON() {
        return ASSIGN_TSP_ON;
    }

    public List<DateDetails> getBOOKED_ON() {
        return BOOKED_ON;
    }

    public List<DateDetails> getDELIVERD_ON() {
        return DELIVERD_ON;
    }

    public List<DateDetails> getREAPPOINT_ON() {
        return REAPPOINT_ON;
    }

    public List<DateDetails> getREJECTED_ON() {
        return REJECTED_ON;
    }

    public List<DateDetails> getREPORTED_ON() {
        return REPORTED_ON;
    }

    public List<DateDetails> getSERVICED_ON() {
        return SERVICED_ON;
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
