/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.pojo.status.thyrocare;

import com.jubination.model.pojo.status.thyrocare.TspMaster;
import com.jubination.model.pojo.status.thyrocare.BenMaster;
import com.jubination.model.pojo.status.thyrocare.LeadHistoryMaster;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
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
@Table(name="report_status", catalog="jubination")
    @JsonIgnoreProperties(ignoreUnknown = true)
public class ReportStatus implements Serializable {
    
            @Id
    @Column(name="res_id")
  String RES_ID;
    @Column(name="response")
    String RESPONSE; 

  
    @JsonManagedReference
 @OneToMany(mappedBy="reportStatus")
      @Cascade({CascadeType.PERSIST,CascadeType.DELETE,CascadeType.SAVE_UPDATE})
  List<TspMaster> TSP_MASTER= new ArrayList<>();     
    
    @JsonManagedReference
 @OneToMany(mappedBy="reportStatus")
      @Cascade({CascadeType.PERSIST,CascadeType.DELETE,CascadeType.SAVE_UPDATE})
  List<BenMaster> BEN_MASTER= new ArrayList<>(); 
    
    @JsonManagedReference
 @OneToMany(mappedBy="reportStatus")
      @Cascade({CascadeType.PERSIST,CascadeType.DELETE,CascadeType.SAVE_UPDATE})
  List<LeadHistoryMaster> LEADHISORY_MASTER = new ArrayList<>();
   
    @JsonManagedReference
    @OneToMany(mappedBy="reportStatus")
      @Cascade({CascadeType.PERSIST,CascadeType.DELETE,CascadeType.SAVE_UPDATE})
   List<OrderMaster> ORDER_MASTER = new ArrayList<>();
   
  


    public List<LeadHistoryMaster> getLEADHISORY_MASTER() {
        return LEADHISORY_MASTER;
    }

   
    public List<OrderMaster> getORDER_MASTER() {
        return ORDER_MASTER;
    }

    public void setORDER_MASTER(List<OrderMaster> ORDER_MASTER) {
        this.ORDER_MASTER = ORDER_MASTER;
    }

   

    public String getRESPONSE() {
        return RESPONSE;
    }

    public void setRESPONSE(String RESPONSE) {
        this.RESPONSE = RESPONSE;
    }

    public String getRES_ID() {
        return RES_ID;
    }

    public void setRES_ID(String RES_ID) {
        this.RES_ID = RES_ID;
    }

    public List<TspMaster> getTSP_MASTER() {
        return TSP_MASTER;
    }

    public void setTSP_MASTER(List<TspMaster> TSP_MASTER) {
        this.TSP_MASTER = TSP_MASTER;
    }



    public void setLEADHISORY_MASTER(List<LeadHistoryMaster> LEADHISORY_MASTER) {
        this.LEADHISORY_MASTER = LEADHISORY_MASTER;
    }

    public List<BenMaster> getBEN_MASTER() {
        return BEN_MASTER;
    }

    public void setBEN_MASTER(List<BenMaster> BEN_MASTER) {
        this.BEN_MASTER = BEN_MASTER;
    }


  

}
