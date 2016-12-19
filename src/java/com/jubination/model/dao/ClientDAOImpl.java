/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.dao;

import com.jubination.model.pojo.crm.Beneficiaries;
import com.jubination.model.pojo.exotel.Call;
import com.jubination.model.pojo.crm.Client;
import com.jubination.model.pojo.crm.Lead;
import com.jubination.model.pojo.crm.TempClient;
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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MumbaiZone
 * @param <T>
 */

@Repository
public class ClientDAOImpl<T> implements Serializable{
        
        private Session session=null;
        @Autowired
        private SessionFactory sessionFactory;
        
        //Building client 
        @Transactional(propagation = Propagation.REQUIRED)
        public Object buildEntity(Object entity) {
            Client client=(Client) entity;
            session = getSessionFactory().getCurrentSession();
            session.save(client);
            client = (Client) session.get(Client.class, client.getClientId());
            
                 System.out.println("BUILD CLIENT :::::::::::::::::::::::::::::::::::::::::::::::CHECK");
            return (T) client;
    }
        //building temp client
        @Transactional(propagation = Propagation.REQUIRED)
        public Object buildBackupEntity(Object entity) {
       TempClient client=(TempClient) entity;
            session = getSessionFactory().getCurrentSession();
            session.save(client);
            client = (TempClient) session.get(TempClient.class, client.getClientId());
       
                 System.out.println("BUILD TEMP CLIENT:::::::::::::::::::::::::::::::::::::::::::::::CHECK");
        return (T) client;
    }
       //building beneficiaries 
        @Transactional(propagation = Propagation.REQUIRED)
        public Object buildBeneficiaryEntity(Object entity) {
       Beneficiaries ben=(Beneficiaries) entity;
            session = getSessionFactory().getCurrentSession();
            session.save(ben);
            ben = (Beneficiaries) session.get(Beneficiaries.class, ben.getId());
       
                 System.out.println("BUILD BENEFICIARIES:::::::::::::::::::::::::::::::::::::::::::::::CHECK");
        return (T) ben;
    }
        
         //read temp client with lead id
        @Transactional(propagation = Propagation.REQUIRED, readOnly = true)  
        public List<TempClient> readBackupEntity(String leadId) {
                 List<TempClient> list=null;
                 System.out.println("READ TEMP CLIENT FROM LEAD ID:::::::::::::::::::::::::::::::::::::::::::::::CHECK");
                      session = getSessionFactory().getCurrentSession();
                      Criteria criteria = session.createCriteria(TempClient.class, "client");
                      criteria.add(Restrictions.eq("tempLeadDetails", leadId));
                      list=criteria.list();
                     return list;
    }
        //read temp clients registered on a date with a phone number
        @Transactional(propagation = Propagation.REQUIRED, readOnly = true)  
        public List<TempClient> readBackupEntityByNumberAndDate(String number, String date) {
                 System.out.println("READ TEMP CLIENT TODAY WITH SAME NUMBER:::::::::::::::::::::::::::::::::::::::::::::::CHECK");
                      session = getSessionFactory().getCurrentSession();
                      return session.createCriteria(TempClient.class).add(Restrictions.and(Restrictions.eq("phoneNumber", number),Restrictions.ilike("dateCreation",date,MatchMode.START ))) .list();
                    
    }
        //read temp clients wih a lead id
        @Transactional(propagation = Propagation.REQUIRED, readOnly = true)  
        public List<TempClient> readBackupEntityByLeadId(String leadId) {
                 System.out.println("READ TEMP CLIENT TODAY WITH SAME LEAD ID:::::::::::::::::::::::::::::::::::::::::::::::CHECK");
                      session = getSessionFactory().getCurrentSession();
                      return session.createCriteria(TempClient.class).add(Restrictions.eq("tempLeadDetails", leadId)) .list();
                    
    }
        //read temp client with a status
        @Transactional(propagation = Propagation.REQUIRED)  
        public List<TempClient> readClientWithStatus(String param) {
                    session = getSessionFactory().getCurrentSession();
                    Criteria criteria = session.createCriteria(TempClient.class);
                    criteria.add(Restrictions.eq("callStatus", param));
                     criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
                  List<TempClient>  list = criteria.list();
                 System.out.println("READ TEMP CLIENT WITH A STATUS :::::::::::::::::::::::::::::::::::::::::::::::CHECK");
                  return list;
                    
    }
        //  read lead and its inner elements
        @Transactional(propagation = Propagation.REQUIRED, readOnly = true)  
        public Object readInnerPropertyList(Object entity) {
       Lead lead=(Lead) entity;
            session = getSessionFactory().getCurrentSession();
            lead = (Lead) session.get(Lead.class, lead.getLeadId());
            
            if(lead!=null){
                if(lead.getCall()!=null){
                        lead.getCall().size();
                }
                 if(lead.getBeneficiaries()!=null){
                                                                      lead.getBeneficiaries().size();
                                                }
            }
                 System.out.println("READ LEAD WITH INNER ELEMENTS :::::::::::::::::::::::::::::::::::::::::::::::CHECK");
        return (T) lead;
    }
        // read lead with variable
        @Transactional(propagation = Propagation.REQUIRED, readOnly = true)      
        public Object fetchInnerEntities(String param, String type) {
         List<Lead> list=null;
         if(param.equals("Lead")){
             if(type.equals("NotificationOn")){
                    session = getSessionFactory().getCurrentSession();
                    list=(List<Lead>) session.createCriteria(Lead.class).add(Restrictions.isNotNull("followUpDate")).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
             }
             
              if(type.equals("Pending")){
                  session = getSessionFactory().getCurrentSession();
                    list=(List<Lead>) session.createCriteria(Lead.class).add(Restrictions.ge("count", 1)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
        
             }
             
         }
         else if(param.equals("Number")){
             session = getSessionFactory().getCurrentSession();
            list=(List<Lead>) session.createCriteria(Lead.class).createAlias("client", "c").
                   add(Restrictions.eq("c.phoneNumber", type)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
        
         }
          else if(param.equals("MissedAppointmentStatusToday")){
             session = getSessionFactory().getCurrentSession();
            list=(List<Lead>) session.createCriteria(Lead.class).
                   add(Restrictions.and(Restrictions.like("missedAppointmentStatus", type,MatchMode.ANYWHERE),Restrictions.eq("appointmentDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date())))).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
        
         }
         else if(param.equals("ActiveSourceLeads")){
             session = getSessionFactory().getCurrentSession();
            list=(List<Lead>) session.createCriteria(Lead.class,"l").createAlias("client", "c").
                   add(Restrictions.and(Restrictions.ge("l.count", 1),Restrictions.eq("c.source", type))).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
        
         }
         if(list!=null){
            for(Lead lead:list){
                if(lead!=null){
                    if(lead.getBeneficiaries()!=null){
                                                                         lead.getBeneficiaries().size();
                                                   }
               }
            }
         }
         System.out.println("READ LEAD WITH A STATUS :::::::::::::::::::::::::::::::::::::::::::::::CHECK");
         return (T) list;
    }
        //read client with inner elements
        @Transactional(propagation = Propagation.REQUIRED, readOnly = true)  
        public Object readEntityLists(Object entity){
        Client client=(Client) entity;
            session = getSessionFactory().getCurrentSession();
            client=(Client) session.merge(client);
            client.getLead().size();
            for(Lead lead:client.getLead()){
                 if(lead.getBeneficiaries()!=null){
                                                                      lead.getBeneficiaries().size();
                                                }
            }
        System.out.println("READ CLIENT WITH INNER ELEMENTS :::::::::::::::::::::::::::::::::::::::::::::::CHECK");
         return client;
    }
        //read client with a property
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
                      for(Client client :list){
                          client.getLead().size();
                          for(Lead lead:client.getLead()){
                              if(lead.getBeneficiaries()!=null){
                                    lead.getBeneficiaries().size();
                              }
                          }
                      }

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
                      criteria.add(Restrictions.like("dateCreation", dateCreated, MatchMode.START));
                      list= criteria.list();
                     
                break;
                     case "DateCreatedLeadProperty":
                    dateCreated= (String) entity;
                    session = getSessionFactory().getCurrentSession();
                      
                      criteria = session.createCriteria(Lead.class);
                      criteria.createAlias("client", "c").add(Restrictions.like("c.dateCreation", dateCreated, MatchMode.START));
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
                              if(lead.getBeneficiaries()!=null){
                                    lead.getBeneficiaries().size();
                              }
                          }
                      }
                      

                break;
                    
            default: System.err.println("Not a valid option");
                break;
        }
        System.out.println("READ CLIENT WITH A PROPERTY (INNER AND NON INNER MIXED) :::::::::::::::::::::::::::::::::::::::::::::::CHECK");
    return list;
    }
        
        //read client with status
        @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
        public List fetchEntities(String paramVal) {
        List list=null;     
       
                 
                 switch (paramVal) {
                      case "PendingAndNotified":
                                            session = getSessionFactory().getCurrentSession();
                                            Criteria criteria = session.createCriteria(Client.class);
                                            criteria.createAlias("lead", "l");
                                            criteria.add(
                                                Restrictions.or(
                                                    Restrictions.and(Restrictions.ge("l.count", 1),Restrictions.eq("l.followUpDate", "")),
                                                    Restrictions.and(Restrictions.ge("l.count", 1),Restrictions.le("l.followUpDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))),
                                                    Restrictions.eq("l.followUpDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                                            ));
                                            criteria.addOrder(Order.desc("l.followUpDate"));
                                            criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
                                            list = criteria.list();
                                            
                                            
                                            
                                            for(Client client:(List<Client>)list){
                                                client.getLead().size();
                                                for(Lead lead:client.getLead()){
                                                if(lead.getBeneficiaries()!=null){
                                                                      lead.getBeneficiaries().size();
                                                }
                                                    lead.getCall().size();
                                                }
                                            }
                         break;
                    
                     case "Pending":
                                            session = getSessionFactory().getCurrentSession();
                                            criteria = session.createCriteria(Client.class);
                                            criteria.createAlias("lead", "l");
                                            criteria.add(Restrictions.and(Restrictions.ge("l.count", 1),Restrictions.eq("l.followUpDate", ""),Restrictions.eq("l.missedAppointment", false)));
                                            criteria.addOrder(Order.asc("l.count"));
                                            criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
                                            list = criteria.list();
                                            for(Client client:(List<Client>)list){
                                                client.getLead().size();
                                                for(Lead lead:client.getLead()){
                                                     if(lead.getBeneficiaries()!=null){
                                                                      lead.getBeneficiaries().size();
                                                     }
                                                    lead.getCall().size();
                                                }
                                            }
                         break;
                         case "Notified":
                                            session = getSessionFactory().getCurrentSession();
                                            criteria = session.createCriteria(Client.class);
                                            criteria.createAlias("lead", "l");
                                            criteria.add(
                                                Restrictions.or(
                                                    Restrictions.and(Restrictions.ge("l.count", 1),Restrictions.le("l.followUpDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date())),Restrictions.eq("l.missedAppointment", false)),
                                                    Restrictions.eq("l.followUpDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                                            ));
                                            criteria.addOrder(Order.asc("l.followUpDate"));
                                            criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
                                            list = criteria.list();
                                            for(Client client:(List<Client>)list){
                                                client.getLead().size();
                                                for(Lead lead:client.getLead()){
                                                if(lead.getBeneficiaries()!=null){
                                                                      lead.getBeneficiaries().size();
                                                }
                                                    lead.getCall().size();
                                                }
                                            }
                         break;
                         case "PendingMA":
                                            session = getSessionFactory().getCurrentSession();
                                            criteria = session.createCriteria(Client.class);
                                            criteria.createAlias("lead", "l");
                                            criteria.add(Restrictions.and(Restrictions.ge("l.count", 1),Restrictions.eq("l.followUpDate", ""),Restrictions.eq("l.missedAppointment", true)));
                                            criteria.addOrder(Order.desc("l.count"));
                                            criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
                                            list = criteria.list();
                                            for(Client client:(List<Client>)list){
                                                client.getLead().size();
                                                for(Lead lead:client.getLead()){
                                                     if(lead.getBeneficiaries()!=null){
                                                                      lead.getBeneficiaries().size();
                                                     }
                                                    lead.getCall().size();
                                                }
                                            }
                         break;
                         case "NotifiedMA":
                                            session = getSessionFactory().getCurrentSession();
                                            criteria = session.createCriteria(Client.class);
                                            criteria.createAlias("lead", "l");
                                            criteria.add(
                                                Restrictions.or(
                                                    Restrictions.and(Restrictions.ge("l.count", 1),Restrictions.le("l.followUpDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date())),Restrictions.eq("l.missedAppointment", true)),
                                                    Restrictions.eq("l.followUpDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                                            ));
                                            criteria.addOrder(Order.asc("l.followUpDate"));
                                            criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
                                            list = criteria.list();
                                            for(Client client:(List<Client>)list){
                                                client.getLead().size();
                                                for(Lead lead:client.getLead()){
                                                if(lead.getBeneficiaries()!=null){
                                                                      lead.getBeneficiaries().size();
                                                }
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
                                                  for(Lead lead:client.getLead()){
                                                     if(lead.getBeneficiaries()!=null){
                                                                      lead.getBeneficiaries().size();
                                                    }
                                                    lead.getCall().size();
                                                }
                                                client.getLead().size();
                                            }
                         break;
                     
                     default:
                                                        System.out.println("No criteria for pending");
                         break;
                 }
                
            System.out.println("READ CLIENT WITH INNER ELEMENTS WITH STATUS :::::::::::::::::::::::::::::::::::::::::::::::CHECK");
             return list;
       
    }
    
        //update temp client
        @Transactional(propagation = Propagation.REQUIRED)  
        public boolean updateBackupEntity(Object entity) {
                    TempClient tClient = (TempClient) entity;
                    session = getSessionFactory().getCurrentSession();
                    tClient = (TempClient) session.merge(tClient);
                    session.update(tClient);
                    System.out.println("UPDATE TEMP CLIENT :::::::::::::::::::::::::::::::::::::::::::::::CHECK");
                                    session.createCriteria(TempClient.class, "client").add(Restrictions.eq("tempLeadDetails", tClient.getTempLeadDetails())).list();
                    return true;
        }
        //update client only
        @Transactional(propagation = Propagation.REQUIRED)   
        public  Object updateProperty(Object entity) {
                    Client client=(Client) entity;
                    session = getSessionFactory().getCurrentSession();
                    client=(Client) session.merge(client);
                    System.out.println("UPDATE CLIENT ONLY:::::::::::::::::::::::::::::::::::::::::::::::CHECK");
                                    session.createCriteria(TempClient.class, "client").add(Restrictions.eq("tempLeadDetails", client.getTempLeadDetails())).list();
                    return client;
        }
        //update lead only
        @Transactional(propagation = Propagation.REQUIRED)   
        public boolean updatePropertyOfList(Object entity,String listType){
                    if(listType.equals("Lead")){
                                    Lead lead=(Lead) entity;
                                    session = getSessionFactory().getCurrentSession();
                                    session.merge(lead);
                                    
                                      System.out.println("UPDATE LEAD ONLY:::::::::::::::::::::::::::::::::::::::::::::::CHECK");
                                    
                                    return true;
                    }
                                    System.out.println("UPDATE LEAD ONLY::::::::::::::::::::::::::::::::::::::::::::::FAIL");
                    return false;
        }
        //update inner details of lead
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
                                    lead.getBeneficiaries().size();
                                    lead.getCall().add(call);
                                   // call.setLead(lead);
                                    session.update(lead);
                                    System.out.println("UPDATE CALL DETAILS OF LEAD :::::::::::::::::::::::::::::::::::::::::::::::CHECK");
                                    return lead;
                    }
                    else if(listType.equals("Beneficiaries")){
                                    Lead lead=(Lead) entity;
                                    Beneficiaries ben= (Beneficiaries) property;
                                    Beneficiaries storedBen=null;
                                    session = getSessionFactory().getCurrentSession();
                                    lead=(Lead) session.merge(lead);
                                    storedBen=(Beneficiaries) session.merge(ben);
                                    if(storedBen!=null){
                                                    ben=storedBen;
                                    }
                                    
                                    lead.getCall().size();
                                    lead.getBeneficiaries().size();
                                    lead.getBeneficiaries().add(ben);
                                    session.update(lead);
                                    ben.setLead(lead);
                                    session.update(ben);
                                    System.out.println("UPDATE BEN DETAILS OF LEAD :::::::::::::::::::::::::::::::::::::::::::::::CHECK");
                                    return lead;
                    }
                    return null;
        }
        //update client lead and beneficiaries
        @Transactional(propagation = Propagation.REQUIRED)   
        public boolean updatePropertyList(Object entity,Object property,String listType) {
                    if(listType.equals("AddLead")){
                                      Client client=(Client) entity;
                                    Lead lead=(Lead) property;
                                    Lead storedLead=null;
                                        session = getSessionFactory().getCurrentSession();
                                        client=(Client) session.merge(client);
                                        lead.setClient(client);
                                        storedLead = (Lead) session.merge(lead);
                                        if(storedLead!=null){
                                            lead=storedLead;
                                        }
                                        client.getLead().add(lead);
                                        client.getLead().size();
                                        session.update(client);  
//                                        lead.setClient(client);
//                                        session.update(lead);
//                                    System.out.println("ADD LEAD :::::::::::::::::::::::::::::::::::::::::::::::CHECK");
//                                    session.createCriteria(TempClient.class, "client").add(Restrictions.eq("tempLeadDetails", lead.getLeadId())).list();
                                    return true;
                    }
                    System.out.println("ADD LEAD :::::::::::::::::::::::::::::::::::::::::::::::FAIL");
                    return false;
        }
     
        @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public List<Object> fetchFreshCallEntity(String fromDate,String toDate, String type) {
        List list=null;
                    session = getSessionFactory().getCurrentSession();
                     Criteria criteria =session.createCriteria(Client.class,"client")
                              .add(Restrictions.and(
                                      Restrictions.ge("client.dateCreation",fromDate),
                                      Restrictions.le("client.dateCreation",toDate)
                              )
                      );
                      criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
                     list= criteria.list();
                     for(Client c:(List<Client>)list){
                         c.getLead().size();
                         for(Lead l:c.getLead()){
                             l.getCall().size();
                         }
                     }
        
            System.out.println("IMPORTANT:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"+list.size());
        return list;
    }
public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
        public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
   
}
