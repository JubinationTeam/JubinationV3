/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.dao.impl;

import com.jubination.model.pojo.admin.AdminSettings;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MumbaiZone
 */
@Repository
public class SettingsDAO<T> implements java.io.Serializable {
     private Session session=null;
    @Autowired
    private SessionFactory sessionFactory;

    
    
     
    @Transactional(propagation = Propagation.REQUIRED)
    public Object buildEntity(Object entity) {
         AdminSettings adminSettings = (AdminSettings) entity;
         session = getSessionFactory().getCurrentSession();
            session.save(adminSettings);
            adminSettings = (AdminSettings) session.get(AdminSettings.class, adminSettings.getId());
        
        return (T) adminSettings;

    }
    
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public Object readProperty(Object paramId) {
        AdminSettings adminSettings=null;
             session = getSessionFactory().getCurrentSession();
            
            adminSettings=(AdminSettings) session.get(AdminSettings.class, (String) paramId);
            
        
            return (T) adminSettings;
        
    }
    
@Transactional(propagation = Propagation.REQUIRED)
    public boolean updateProperty(Object entity) {
         boolean flag=false;
        AdminSettings adminSettings=(AdminSettings) entity;
             session = getSessionFactory().getCurrentSession();
             session.merge(adminSettings);
            return flag;
        
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteEntity(Object entity) {
             boolean flag=false;
        AdminSettings adminSettings = (AdminSettings) entity;
             session = getSessionFactory().getCurrentSession();
            
            adminSettings = (AdminSettings) session.get(AdminSettings.class, adminSettings.getId());
            
            session.delete(adminSettings);
            flag=true;
        
        adminSettings=null;
        return flag;
    }

    /////////////////////
     public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
