/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.dao;

import com.jubination.model.pojo.booking.Campaigns;
import com.jubination.backend.pojo.products.thyrocare.ProductList;
import com.jubination.model.pojo.booking.Products;
import java.io.Serializable;
import java.util.List;
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
public class ProductsDAOImpl<T> implements Serializable {
    
    @Autowired
    private SessionFactory sessionFactory;
    private Session session=null;
    
    
    
    @Transactional(propagation = Propagation.REQUIRED)
    public Object buildEntity(Object entity) {
            Products p=(Products) entity;
            session = getSessionFactory().getCurrentSession();
            session.save(p);
            p = (Products) session.get(Products.class, p.getId());
            return (T) p;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<T> fetchProductEntities() {
            session = getSessionFactory().getCurrentSession();
            return session.createCriteria(Products.class).list();
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteAllProductEntities() {
            session = getSessionFactory().getCurrentSession();
            List<Products> list=session.createCriteria(Products.class).list();
            for(Products p:list){
                session.delete(p);
            }
            return true;
    }
    
    
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Object fetchCampaignEntities() {
            session = getSessionFactory().getCurrentSession();
            return session.createCriteria(Campaigns.class).list();
           
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public Object buildCampaignEntity(Object entity) {
            Campaigns camp=(Campaigns) entity;
            session = getSessionFactory().getCurrentSession();
            session.saveOrUpdate(camp);
            camp = (Campaigns) session.get(Campaigns.class, camp.getName());
        
        return (T) camp;
           
    }
 @Transactional(propagation = Propagation.REQUIRED)
    public Boolean updateCampaignEntity(Object entity) {
        Campaigns camp = (Campaigns) entity;
            session = getSessionFactory().getCurrentSession();
            camp=(Campaigns) session.merge(camp);
            session.update(camp);
            return true;
           
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public Object readCampaignProperty(String param) {
            session = getSessionFactory().getCurrentSession();
            Campaigns camp = (Campaigns) session.get(Campaigns.class, param);
        
        return (T) camp;
           
    }
 
    
  
     public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
}
