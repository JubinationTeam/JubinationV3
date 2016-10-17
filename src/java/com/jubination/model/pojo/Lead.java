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
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonBackReference;
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
@Table(name="lead"
    ,catalog="jubination"
)

public class Lead {
    @Id
    String leadId;
    @JsonBackReference
        @ManyToOne
    Client client;
         @Column(name="count")
    private int count;
      @Column(name="pending")
    private boolean pending;
       @Column(name="booked")
    private boolean booked;
          @Column(name="notification")
    private boolean notification;
         @Column(name="follow_up_date")
    private String followUpDate;  
    @Column(name="comments")
    private String comments;
    @Column(name="lead_status")
    private String leadStatus;
      @JsonManagedReference 
         @OneToMany(mappedBy="lead")
      @Cascade({CascadeType.PERSIST,CascadeType.DELETE,CascadeType.SAVE_UPDATE})
      @NotFound(action=NotFoundAction.IGNORE)
        private List<Call> call= new ArrayList<>();
      
      @OneToOne
      private Admin admin;

    public Lead() {
    }

    public Lead(String leadId) {
        this.leadId = leadId;
    }

    public String getLeadId() {
        return leadId;
    }

    public void setLeadId(String leadId) {
        this.leadId = leadId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Call> getCall() {
        return call;
    }

    public void setCall(List<Call> call) {
        this.call = call;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public boolean isNotification() {
        return notification;
    }

    public void setNotification(boolean notification) {
        this.notification = notification;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getFollowUpDate() {
        return followUpDate;
    }

    public void setFollowUpDate(String followUpDate) {
        this.followUpDate = followUpDate;
    }

    public String getLeadStatus() {
        return leadStatus;
    }

    public void setLeadStatus(String leadStatus) {
        this.leadStatus = leadStatus;
    }


        
    
}
