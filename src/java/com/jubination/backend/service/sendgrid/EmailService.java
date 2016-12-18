/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jubination.backend.service.sendgrid;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;
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
    private String apiKey;
    
    public EmailService(String emailId,String mailSubject, String mailContent,final String myUsername, final String myPassword, String auth, String starttls, String host, String port,String apiKey) {
        this.mailContent = mailContent;
        this.mailSubject = mailSubject;
        this.emailId = emailId;
        this.myUsername=myUsername;
        this.myPassword=myPassword;
        this.auth=auth;
        this.starttls=starttls;
        this.host=host;
        this.port=port;
        this.apiKey=apiKey;
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
    
   
    public void sendOrder(String emailId) throws SendGridException{
        
    SendGrid sendgrid = new SendGrid(apiKey);

    SendGrid.Email email = new SendGrid.Email();

    email.addTo(emailId);
    email.setFrom("support@jubination.com");
    email.setSubject(mailSubject);
    email.setHtml(mailContent);

    SendGrid.Response response = sendgrid.send(email); 
    }
    
     
    
}
