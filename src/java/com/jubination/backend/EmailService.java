/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jubination.backend;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Component;

/**
 *
 * @author Welcome
 */
public class EmailService  extends Thread{
       
    private String mailContent;
    private String mailSubject;
    private String emailId;
    private final String myUsername;
    private final String myPassword;
    private String auth;
    private String starttls; 
    private String host;
    private String port;
    
    public EmailService(String emailId,String mailSubject, String mailContent,final String myUsername, final String myPassword, String auth, String starttls, String host, String port) {
        this.mailContent = mailContent;
        this.mailSubject = mailSubject;
        this.emailId = emailId;
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
			
           if(new EmailValidator().validate(emailId)){
                    this.sendOrder(emailId);
           }
                
        }
		
		
        catch(Exception e) 
        {
		System.err.println("Error in sending message "+e);		
		
        }

    }
    
   
    public void sendOrder(String adminEmailId){
        Properties prop = new Properties();
        prop.put("mail.smtp.auth",auth);//true
        prop.put("mail.smtp.starttls.enable",starttls);//true
        prop.put("mail.smtp.host",host);//smtp.gmail.com
        prop.put("mail.smtp.port",port);//587
        
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
