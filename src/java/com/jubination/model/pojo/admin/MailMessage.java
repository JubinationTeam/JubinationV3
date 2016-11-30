/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.pojo.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Welcome
 */
@Entity
 @Table(name="message", catalog="jubination")       
public class MailMessage implements java.io.Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long messageId;
    @Column(name="body",length=200)
    private String body;
    @Column(name="subject",length=100)
    private String subject; 
    @ManyToOne
    Admin sender;
    @ManyToOne
    Admin receiver;

    public MailMessage() {
    }

    public MailMessage(String body, String subject, Admin sender, Admin receiver) {
        this.body = body;
        this.subject = subject;
        this.sender = sender;
        this.receiver = receiver;
    }

    public MailMessage(String body) {
        this.body = body;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Admin getSender() {
        return sender;
    }

    public void setSender(Admin sender) {
        this.sender = sender;
    }

    public Admin getReceiver() {
        return receiver;
    }

    public void setReceiver(Admin receiver) {
        this.receiver = receiver;
    }
    
    
}
