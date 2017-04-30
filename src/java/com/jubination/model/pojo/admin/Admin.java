/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jubination.model.pojo.admin;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author Welcome
 */
@Entity
@Table(name="admin",catalog="jubination")
public class Admin  implements java.io.Serializable{
    
    @Id 

    @Column(name="username", unique=true, nullable=false, length=50)
     private String username;
    @Column(name="password", length=300)
    private String password;
    @Column(name="role", nullable=false,length=50)
     private String role;
    @Column(name="work", length=50)
     private String work;
     @Column(name="power", nullable=false)
     private int power;
     @Column(name="name", nullable=false, length=50)
     private String name;
     @Column(name="number", nullable=false, length=50)
     private String number;
              
     
     
    public Admin() {
    }

    public Admin(String username) {
        this.username = username;
    }

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Admin(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Admin(String username, String password, String role, int power, String name) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.power = power;
        this.name = name;
    }

    public Admin(String username, String password, String role, String work, int power, String name) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.work = work;
        this.power = power;
        this.name = name;
    }

   

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
   

  

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }


    public String getString() {
        return name;
    }

    public void setString(String name) {
        this.name = name;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

  
    
    
    
}
