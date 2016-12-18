/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.pojo.status;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author MumbaiZone
 */
@Entity
@Table(name="report_status", catalog="jubination")
    @JsonIgnoreProperties(ignoreUnknown = true)
public class ReportStatus implements Serializable {
    
      @Id
      @GeneratedValue(strategy=GenerationType.AUTO)
    private long orderId;
      
    @Column(name="leadId")
  private String leadId;
    @Column(name="status")
    private String status; 

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getLeadId() {
        return leadId;
    }

    public void setLeadId(String leadId) {
        this.leadId = leadId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    

  
  
   
  

}
