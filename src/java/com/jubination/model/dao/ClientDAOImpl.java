/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.dao;

import com.jubination.model.pojo.Call;
import com.jubination.model.pojo.Client;
import com.jubination.model.pojo.Lead;
import com.jubination.model.pojo.TempClient;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MumbaiZone
 */

@Repository
public class ClientDAOImpl<T> implements GenericDAO,Serializable{
private Session session=null;
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Object buildEntity(Object entity) {
            Client client=(Client) entity;
            session = getSessionFactory().getCurrentSession();
            session.save(client);
            client = (Client) session.get(Client.class, client.getClientId());
            return (T) client;
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public Object buildBackupEntity(Object entity) {
       TempClient client=(TempClient) entity;
            session = getSessionFactory().getCurrentSession();
            session.save(client);
            client = (TempClient) session.get(TempClient.class, client.getClientId());
       
        return (T) client;
    }
    
    
         
        @Transactional(propagation = Propagation.REQUIRED, readOnly = true)  
      public List<TempClient> readBackupEntity(String leadId) {
                 List<TempClient> list=null;
                      session = getSessionFactory().getCurrentSession();
                      Criteria criteria = session.createCriteria(TempClient.class, "client");
                      criteria.add(Restrictions.eq("tempLeadDetails", leadId));
                      list=criteria.list();
                     return list;
    }
      
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true)  
      public List<TempClient> readBackupEntityByNumberToday(String number) {
                 
                      session = getSessionFactory().getCurrentSession();
                      return session.createCriteria(TempClient.class).add(Restrictions.and(Restrictions.eq("phoneNumber", number),Restrictions.ilike("dateCreation",new SimpleDateFormat("yyyy-MM-dd").format(new Date()),MatchMode.START ))) .list();
                    
    }
      
      @Transactional(propagation = Propagation.REQUIRED)  
      public boolean updateBackupEntity(Object entity) {
                      TempClient client = (TempClient) entity;
                      session = getSessionFactory().getCurrentSession();
                    client = (TempClient) session.merge(client);
                    session.update(client);
                    return true;
                    
    }
     
      @Transactional(propagation = Propagation.REQUIRED)  
      public List<TempClient> readClientWithStatus(String param) {
                    session = getSessionFactory().getCurrentSession();
                    Criteria criteria = session.createCriteria(TempClient.class);
                                            criteria.add(Restrictions.eq("client.callStatus", param));
                                             criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
                                          List<TempClient>  list = criteria.list();
                                          return list;
                    
    }
      
      
      
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)  
        public Object readInnerPropertyList(Object entity) {
       Lead lead=(Lead) entity;
            session = getSessionFactory().getCurrentSession();
            lead = (Lead) session.get(Lead.class, lead.getLeadId());

        return (T) lead;
    }
   
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)      
     public Object fetchInnerEntities(String param, String type) {
         List<Lead> list=null;
         if(param.equals("Lead")){
             if(type.equals("NotificationOn")){
                    session = getSessionFactory().getCurrentSession();
                    list=(List<Lead>) session.createCriteria(Lead.class).add(Restrictions.eq("notification", true)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
             }
             
              if(type.equals("Pending")){
                  session = getSessionFactory().getCurrentSession();
                    list=(List<Lead>) session.createCriteria(Lead.class).add(Restrictions.and(Restrictions.eq("pending", true),Restrictions.ge("count", 1))).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
        
             }
         }
         else if(param.equals("Number")){
             session = getSessionFactory().getCurrentSession();
            list=(List<Lead>) session.createCriteria(Lead.class).createAlias("client", "c").
                   add(Restrictions.eq("c.phoneNumber", type)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
        
         }
         return (T) list;
    }
     
     @Transactional(propagation = Propagation.REQUIRED, readOnly = true)  
    public Object readEntityLists(Object entity){
        Client client=(Client) entity;
            session = getSessionFactory().getCurrentSession();
            client=(Client) session.merge(client);
            client.getLead().size();
        
         return client;
    }
    
  @Transactional(propagation = Propagation.REQUIRED)   
public  Object updateProperty(Object entity) {
        
        Client client=(Client) entity;

            session = getSessionFactory().getCurrentSession();
            client=(Client) session.merge(client);
          
         return client;
    }
@Override
@Transactional(propagation = Propagation.REQUIRED)   
public boolean updatePropertyList(Object entity,Object property,String listType) {
        if(listType.equals("AddLead")){
                    Client client=(Client) entity;
                    Lead lead=(Lead) property;
                    Lead storedLead=null;
                        session = getSessionFactory().getCurrentSession();
                        client=(Client) session.merge(client);
                        storedLead = (Lead) session.merge(lead);
                        if(storedLead!=null){
                            lead=storedLead;
                        }
                        client.getLead().add(lead);
                        client.getLead().size();
                        session.update(client);    
                        return true;
        }
        return false;
    }

@Transactional(propagation = Propagation.REQUIRED)   
public boolean updateInnerPropertyOfList(Object entity,String listType){
    if(listType.equals("Lead")){
                    Lead lead=(Lead) entity;
                        session = getSessionFactory().getCurrentSession();
                        session.merge(lead);
                        return true;
    }
    return false;
}

@Transactional(propagation = Propagation.REQUIRED)   
public Object updateInnerPropertyList(Object entity,Object property,String listType) {
        if(listType.equals("Call")){
                    Lead lead=(Lead) entity;
                    Call call= (Call) property;
                    
                    Call storedCall=null;
                        session = getSessionFactory().getCurrentSession();
                        lead=(Lead) session.merge(lead);
                        storedCall=(Call) session.merge(call);
                        if(storedCall!=null){
                            call=storedCall;
                        }
                        lead.getCall().size();
                        lead.getCall().add(call);
                        session.update(lead);
                  
                    
                        return lead;
        }
        return null;
    }
@Transactional(propagation = Propagation.REQUIRED,readOnly = true)  
    public Object getByProperty(Object entity, String listType) {
        List<Client> list = new ArrayList<Client>();
        switch(listType){
            case "Email":
                    String emailId= (String) entity;
                      session = getSessionFactory().getCurrentSession();
                      Criteria criteria = session.createCriteria(Client.class, "client");
                      criteria.add(Restrictions.eq("emailId", emailId));
                      list=criteria.list();
                    
       
                break;
                case "City":
                    String city= (String) entity;
                      session = getSessionFactory().getCurrentSession();
                      criteria = session.createCriteria(Client.class, "client");
                      criteria.add(Restrictions.like("city", city, MatchMode.START));
                      list=criteria.list();

       
                break;
            case "Id":
                    Long orderId= (Long) entity;
                        session = getSessionFactory().getCurrentSession();
                        list.add((Client) session.get(Client.class, (Long)orderId));
                       
                
                break;
            case "Number":
                    String number= (String) entity;
                    session = getSessionFactory().getCurrentSession();
                      criteria = session.createCriteria(Client.class);
                      criteria.add(Restrictions.like("phoneNumber", number,MatchMode.ANYWHERE));
                      list= criteria.list();

                break;
                 case "Name":
                    String name= (String) entity;
                      session = getSessionFactory().getCurrentSession();
                      criteria = session.createCriteria(Client.class);
                      criteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
                      list= criteria.list();
                    
                break;
                case "DateCreated":
                    String dateCreated= (String) entity;
                    session = getSessionFactory().getCurrentSession();
                      
                      criteria = session.createCriteria(Client.class);
                      criteria.add(Restrictions.like("DateCreation", dateCreated, MatchMode.START));
                      list= criteria.list();
                     
                break;
               case "DateUpdatedFull":
                    String dateUpdated= (String) entity;
                     session = getSessionFactory().getCurrentSession();
                      
                      criteria = session.createCriteria(Client.class,"client");
                      criteria.createAlias("client.lead", "l");
                      criteria.createAlias("l.call", "c");
                      criteria.add(Restrictions.like("c.DateUpdated", dateUpdated, MatchMode.START));
                      criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
                      list= criteria.list();
                      
                      for(Client client:list){
                          client.getLead().size();
                          for(Lead lead:client.getLead()){
                              lead.getCall().size();
                          }
                      }
                      

                break;
                    
            default: System.err.println("Not a valid option");
                break;
        }
    return list;
    }
 
       @Override
       @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public List fetchEntities(String paramVal) {
        List list=null;     
       
                 
                 switch (paramVal) {
                     
                     case "PendingAndNotifiedFor11":
                                            session = getSessionFactory().getCurrentSession();
                                            Criteria criteria = session.createCriteria(Client.class);
                                            criteria.createAlias("lead", "l");
                                            criteria.add(Restrictions.or(Restrictions.and(Restrictions.eq("l.pending", true),Restrictions.ge("l.count", 1)),
                                            Restrictions.and(Restrictions.eq("l.notification", true),Restrictions.eq("l.followUpDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"-11"))));
                                            criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
                                            list = criteria.list();
                                            for(Client client:(List<Client>)list){
                                                client.getLead().size();
                                                for(Lead lead:client.getLead()){
                                                    lead.getCall().size();
                                                }
                                            }
                         break;
                         case "PendingAndNotifiedFor12":
                                            session = getSessionFactory().getCurrentSession();
                                            criteria = session.createCriteria(Client.class);
                                            criteria.createAlias("lead", "l");
                                            criteria.add(Restrictions.or(Restrictions.and(Restrictions.eq("l.pending", true),Restrictions.ge("l.count", 1)),
                                            Restrictions.and(Restrictions.eq("l.notification", true),Restrictions.eq("l.followUpDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"-12"))));
                                             criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
                                            list = criteria.list();
                                            for(Client client:(List<Client>)list){
                                                client.getLead().size();
                                                for(Lead lead:client.getLead()){
                                                    lead.getCall().size();
                                                }
                                            }
                         break;
                         case "PendingAndNotifiedFor3":
                                            session = getSessionFactory().getCurrentSession();
                                            criteria = session.createCriteria(Client.class);
                                            criteria.createAlias("lead", "l");
                                            criteria.add(Restrictions.or(Restrictions.and(Restrictions.eq("l.pending", true),Restrictions.ge("l.count", 1)),
                                            Restrictions.and(Restrictions.eq("l.notification", true),Restrictions.eq("l.followUpDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"-3"))));
                                             criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
                                            list = criteria.list();
                                            for(Client client:(List<Client>)list){
                                                client.getLead().size();
                                                for(Lead lead:client.getLead()){
                                                    lead.getCall().size();
                                                }
                                            }
                         break;
                         case "PendingAndNotifiedFor4":
                                            session = getSessionFactory().getCurrentSession();
                                            criteria = session.createCriteria(Client.class);
                                            criteria.createAlias("lead", "l");
                                            criteria.add(Restrictions.or(Restrictions.and(Restrictions.eq("l.pending", true),Restrictions.ge("l.count", 1)),
                                            Restrictions.and(Restrictions.eq("l.notification", true),Restrictions.eq("l.followUpDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"-4"))));
                                             criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
                                            list = criteria.list();
                                            for(Client client:(List<Client>)list){
                                                client.getLead().size();
                                                for(Lead lead:client.getLead()){
                                                    lead.getCall().size();
                                                }
                                            }
                         break;
                         case "PendingAndNotifiedFor6":
                                            session = getSessionFactory().getCurrentSession();
                                            criteria = session.createCriteria(Client.class);
                                            criteria.createAlias("lead", "l");
                                            criteria.add(Restrictions.or(Restrictions.and(Restrictions.eq("l.pending", true),Restrictions.ge("l.count", 1)),
                                            Restrictions.and(Restrictions.eq("l.notification", true),Restrictions.eq("l.followUpDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"-6"))));
                                             criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
                                            list = criteria.list();
                                            for(Client client:(List<Client>)list){
                                                client.getLead().size();
                                                for(Lead lead:client.getLead()){
                                                    lead.getCall().size();
                                                }
                                            }
                         break;
                     case "Pending":
                                            session = getSessionFactory().getCurrentSession();
                                            criteria = session.createCriteria(Client.class);
                                            criteria.createAlias("lead", "l");
                                            criteria.add(Restrictions.and(Restrictions.eq("l.pending", true),Restrictions.ge("l.count", 1)));
                                            criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
                                            list = criteria.list();
                                            for(Client client:(List<Client>)list){
                                                client.getLead().size();
                                                for(Lead lead:client.getLead()){
                                                    lead.getCall().size();
                                                }
                                            }
                         break;
                         case "Overnight":
                                            session = getSessionFactory().getCurrentSession();
                                            criteria = session.createCriteria(Client.class);
                                            criteria.add(Restrictions.eq("overnight", true));
                                            list = criteria.list();
                                            for(Client client:(List<Client>)list){
                                                client.getLead().size();
                                            }
                         break;
                     
                     default:
                                                        System.out.println("No criteria for pending");
                         break;
                 }
                
         
             return list;
       
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
    public Object readProperty(Object paramId) {
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
    public boolean deleteEntity(Object entity) {
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
        
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

  
   
}
