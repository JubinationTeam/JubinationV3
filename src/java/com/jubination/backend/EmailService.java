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

    public EmailService(String emailId,String mailSubject, String mailContent) {
        this.mailContent = mailContent;
        this.mailSubject = mailSubject;
        this.emailId = emailId;
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
        final String myUsername = "support@jubination.com";
        final String myPassword = "support_jubination_12345";
        Properties prop = new Properties();
        prop.put("mail.smtp.auth","true");
        prop.put("mail.smtp.starttls.enable","true");
        prop.put("mail.smtp.host","smtp.gmail.com");
        prop.put("mail.smtp.port","587");
        
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
