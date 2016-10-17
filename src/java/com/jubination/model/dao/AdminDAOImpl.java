/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jubination.model.dao;

import com.jubination.model.pojo.Admin;
import com.jubination.model.pojo.MailMessage;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Welcome
 */
@Repository
public class AdminDAOImpl<T> implements java.io.Serializable, GenericDAO {
    private Session session=null;
    @Autowired
    private SessionFactory sessionFactory;

    public AdminDAOImpl() {
    }
   
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Object buildEntity(Object entity, boolean coded) {
         Admin admin = (Admin) entity;
         if(!coded){
            Md5PasswordEncoder encoder = new Md5PasswordEncoder();
            admin.setPassword(encoder.encodePassword(admin.getPassword(), null));
         }
            session = getSessionFactory().getCurrentSession();
            session.save(admin);
            admin = (Admin) session.get(Admin.class, admin.getUsername());
        
        return (T) admin;

    }


    @Override
    public boolean addPropertyList(Object entity, Object property, String listType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object readProperty(Object paramId) {
        Admin admin=null;
             session = getSessionFactory().getCurrentSession();
            
            admin=(Admin) session.get(Admin.class, (String) paramId);
            
        
            return (T) admin;
        
    }

   
    @Override
    public Object readPropertyList(Object entity,String listType){
        Admin admin =(Admin) entity;
        List list=null;
        switch (listType){
            case "Inbox":
                     session = getSessionFactory().getCurrentSession();
                    
                    admin=(Admin) session.get(Admin.class, admin.getUsername());
                  
                   
                    Criteria criteria = session.createCriteria(MailMessage.class,"msg");
                   criteria.add(Restrictions.eq("msg.receiver.username", admin.getUsername()));
                   criteria.addOrder(Order.desc("messageId"));
                  
                   criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
                    criteria.setFirstResult(0);
                    criteria.setMaxResults(10);
                   list=criteria.list();
                    
                    
                
                break;
             case "Sent":
                     session = getSessionFactory().getCurrentSession();
                    
                   
                    admin=(Admin) session.get(Admin.class, admin.getUsername());
         
                   
                    criteria = session.createCriteria(MailMessage.class,"msg");
                   criteria.add(Restrictions.eq("msg.sender.username", admin.getUsername()));
                   criteria.addOrder(Order.desc("messageId"));
                  
                   criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
                    criteria.setFirstResult(0);
                    criteria.setMaxResults(10);
                    list=criteria.list();
                    
             
                break;   
             default :
                System.out.println("Not an option");
                admin=null;
                break;
        }
        
        return (T) list;
    }
    @Override
    public boolean updateProperty(Object entity, Object paramVal, String paramType) {
         boolean flag=false;
        Admin admin=(Admin) entity;
             session = getSessionFactory().getCurrentSession();
            
            if(!paramType.equals("All")){
                    admin=(Admin) session.get(Admin.class,admin.getUsername());
            }
            switch(paramType){
                case "Password":
                    
                String password=new Md5PasswordEncoder().encodePassword((String)paramVal, null);
                    admin.setPassword(password);
                    password=null;
                    break;
                case "All":
                    admin = (Admin) paramVal;
                    break;
                default:
                    System.out.println("Not an option");
                    break;
            }
            
            session.update(admin);
            
            flag=true;
            admin=null;
            return flag;
        
    }

    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteEntity(Object entity) {
             boolean flag=false;
        Admin admin = (Admin) entity;
             session = getSessionFactory().getCurrentSession();
            
            admin = (Admin) session.get(Admin.class, admin.getUsername());
            
            session.delete(admin);
            flag=true;
        
        admin=null;
        return flag;
    }

    
    @Override
@Transactional(propagation = Propagation.REQUIRED,readOnly = true)
public List fetchEntities(String paramVal) {
        List list=null;
        if(paramVal.equals("OrderStatus")){
            session = getSessionFactory().getCurrentSession();
            Criteria criteria=session.createCriteria(Admin.class,"admin");
            criteria.add(Restrictions.eq("admin.orderStatus", 1));
            list=criteria.list();

        }
          return list;
    }

@Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public List fetchEntities(int power) {
        List list=null;
        
            session = getSessionFactory().getCurrentSession();
            Criteria criteria=session.createCriteria(Admin.class,"admin");
         //   criteria.add(Restrictions.or(Restrictions.eq("admin.power", power),Restrictions.gt("admin.power", power)));
            criteria.addOrder(Order.asc("admin.name"));
            criteria.addOrder(Order.asc("admin.power"));
            list=criteria.list();
            return list;
        
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Object buildEntity(Object entity) {
        Admin admin = (Admin) entity;
         
            Md5PasswordEncoder encoder = new Md5PasswordEncoder();
            admin.setPassword(encoder.encodePassword(admin.getPassword(), null));
          session = getSessionFactory().getCurrentSession();
            session.save(admin);
            admin = (Admin) session.get(Admin.class, admin.getUsername());
           
        return (T) admin;
    
    }
     public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public boolean updatePropertyList(Object entity, Object property, String listType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object deletePropertyList(Object entity, Object property, String listType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public Object fetchEntity(Object property) {
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

}
