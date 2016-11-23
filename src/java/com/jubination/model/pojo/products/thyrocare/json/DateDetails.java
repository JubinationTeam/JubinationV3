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
@Table(name="date_details", catalog="jubination")
    @JsonIgnoreProperties(ignoreUnknown = true)
public class DateDetails implements Serializable {
    @Id
     @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;
    @Column(name="date_")
    String DATE;
    @Column(name="lead_id")
     String LEAD_ID;
 @JsonBackReference
 @ManyToOne
 LeadHistoryMaster leadHistoryMaster;
    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getLEAD_ID() {
        return LEAD_ID;
    }

    public void setLEAD_ID(String LEAD_ID) {
        this.LEAD_ID = LEAD_ID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LeadHistoryMaster getLeadHistoryMaster() {
        return leadHistoryMaster;
    }

    public void setLeadHistoryMaster(LeadHistoryMaster leadHistoryMaster) {
        this.leadHistoryMaster = leadHistoryMaster;
    }
     
}
