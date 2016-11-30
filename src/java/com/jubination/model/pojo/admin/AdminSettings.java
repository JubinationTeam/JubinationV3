/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.pojo.admin;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author MumbaiZone
 */
@Entity
@Table(name="admin_settings"
    ,catalog="jubination"
)
public class AdminSettings implements Serializable {
        @Id
    private String id;
        
    @Column(name="email_username")  
        String myUsername;
    @Column(name="email_password")
        String myPassword;
    @Column(name="sendgrid_api")  
        String sendgridApi;
    @Column(name="auth")
        String auth;
    @Column(name="starttls")
        String starttls;
    
    @Column(name="host")
        String host;
    
    @Column(name="port")
        String port;
    @Column(name="ref_code_thyrocare")
    String refCodeThyrocare;
     @Column(name="pass_thyrocare")
    String passThyrocare;
    @Column(name="api_key_thyrocare")
    String apiKeyThyrocare;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    public String getMyUsername() {
        return myUsername;
    }

    public void setMyUsername(String myUsername) {
        this.myUsername = myUsername;
    }

    public String getMyPassword() {
        return myPassword;
    }

    public void setMyPassword(String myPassword) {
        this.myPassword = myPassword;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getStarttls() {
        return starttls;
    }

    public void setStarttls(String starttls) {
        this.starttls = starttls;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getRefCodeThyrocare() {
        return refCodeThyrocare;
    }

    public void setRefCodeThyrocare(String refCodeThyrocare) {
        this.refCodeThyrocare = refCodeThyrocare;
    }

    public String getApiKeyThyrocare() {
        return apiKeyThyrocare;
    }

    public void setApiKeyThyrocare(String apiKeyThyrocare) {
        this.apiKeyThyrocare = apiKeyThyrocare;
    }

    public String getPassThyrocare() {
        return passThyrocare;
    }

    public void setPassThyrocare(String passThyrocare) {
        this.passThyrocare = passThyrocare;
    }

    public String getSendgridApi() {
        return sendgridApi;
    }

    public void setSendgridApi(String sendgridApi) {
        this.sendgridApi = sendgridApi;
    }
        
        
}
