/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.dao;

import com.jubination.model.pojo.Campaigns;
import com.jubination.model.pojo.products.thyrocare.json.ProductList;
import com.jubination.model.pojo.products.thyrocare.json.TestEntity;
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
public class ProductsDAOImpl<T> implements Serializable, GenericDAO {
private Session session=null;
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Object buildEntity(Object entity) {
        ProductList pl=(ProductList) entity;
            session = getSessionFactory().getCurrentSession();
            session.save(pl);
            pl = (ProductList) session.get(ProductList.class, pl.getDateId());
        
        return (T) pl;
    }
@Override
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Object fetchEntity(Object property) {
        ProductList pl=(ProductList) property;
            session = getSessionFactory().getCurrentSession();
            pl = (ProductList) session.get(ProductList.class, pl.getDateId());
           pl.getMASTERS().getOFFER().size();
           for(TestEntity te:pl.getMASTERS().getOFFER()){
               te.getChilds().size();
           }
           pl.getMASTERS().getPOP().size();
           for(TestEntity te:pl.getMASTERS().getPOP()){
               te.getChilds().size();
           }
           pl.getMASTERS().getPROFILE().size();
           for(TestEntity te:pl.getMASTERS().getPROFILE()){
               te.getChilds().size();
           }
           pl.getMASTERS().getTESTS().size();
           for(TestEntity te:pl.getMASTERS().getTESTS()){
               te.getChilds().size();
           }
                   
        return (T) pl;
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
    @Override
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public ProductList readProperty(Object paramId) {
        String id=(String) paramId;
            session = getSessionFactory().getCurrentSession();
            return  (ProductList) session.get(ProductList.class,id);
    }
    
    @Override
    public Object buildEntity(Object entity, boolean coded) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addPropertyList(Object entity, Object property, String listType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   

    @Override
    public Object readPropertyList(Object entity, String listType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateProperty(Object entity, Object paramVal, String paramType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updatePropertyList(Object entity, Object property, String listType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteEntity(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object deletePropertyList(Object entity, Object property, String listType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List fetchEntities(String paramVal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    @Override
    public Object buildInnerPropertyList(Object entity, Object property, String listType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object readInnerPropertyList(Object entity, String listType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteInnerPropertyList(Object entity, Object property, String listType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
}
