/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jubination.backend.service.email;

import com.jubination.model.pojo.admin.Admin;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Welcome
 */
public class LoginInfoService extends Thread{
    
    
    private String mailContent;
    private String mailSubject;
    private String entityType;
    private Object entity;
    private final String myUsername;
    private final String myPassword;
    private String auth;
    private String starttls; 
    private String host;
    private String port;

    public LoginInfoService(String mailSubject, String mailContent,  String entityType, Object entity,final String myUsername, final String myPassword, String auth, String starttls, String host, String port) {
        this.mailContent = mailContent;
        this.mailSubject = mailSubject;
        this.entityType = entityType;
        this.entity = entity;
        this.myUsername=myUsername;
        this.myPassword=myPassword;
        this.auth=auth;
        this.starttls=starttls;
        this.host=host;
        this.port=port;
    }
    
    
    
    @Override
    public void run() {
        try
        {
			
            switch (entityType) {
                
                case "admin":
                    this.authenticate( (String) entity);
                    break;
            }
                
        }
		
		
        catch(Exception e) 
        {
		System.err.println("Error in sending message "+e);		
		
        }

    }
    
    
    public void authenticate(String adminEmailId){
        Properties prop = new Properties();
        prop.put("mail.smtp.auth",auth);
        prop.put("mail.smtp.starttls.enable",starttls);
        prop.put("mail.smtp.host",host);
        prop.put("mail.smtp.port",port);
        
        Session session = Session.getInstance(prop, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
               return new PasswordAuthentication(myUsername,myPassword); 
            }
        
        }); 
        
        try{

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myUsername));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(adminEmailId));
            message.setSubject(mailSubject);
            message.setContent(mailContent,"text/html; charset=utf-8");
            Transport.send(message);
        }
        catch(MessagingException e){
            System.out.println("Error in authenticating : "+e);
        }       
    }
}
