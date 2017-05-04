/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.dao.impl;

import com.jubination.model.dao.plan.GenericDAOAbstract;
import com.jubination.model.pojo.crm.Beneficiaries;
import com.jubination.model.pojo.exotel.Call;
import com.jubination.model.pojo.crm.Client;
import com.jubination.model.pojo.crm.Lead;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MumbaiZone
 * @param <T>
 */

@Repository
public class ClientDAO<T>  extends GenericDAOAbstract implements java.io.Serializable{
        
//       
//        //  read lead and its inner elements
//        @Transactional(propagation = Propagation.REQUIRED, readOnly = true)  
//        public Object readInnerPropertyList(Object entity) {
//       Lead lead=(Lead) entity;
//            Session session = sessionFactory.getCurrentSession();
//            lead = (Lead) session.get(Lead.class, lead.getLeadId());
//            
//            if(lead!=null){
//                if(lead.getCall()!=null){
//                        lead.getCall().size();
//                }
//                 if(lead.getBeneficiaries()!=null){
//                                                                      lead.getBeneficiaries().size();
//                 }
//            }
//                 System.out.println("READ LEAD WITH INNER ELEMENTS :::::::::::::::::::::::::::::::::::::::::::::::CHECK");
//        return (T) lead;
//    }
//       
//        // read lead with variable
//        @Transactional(propagation = Propagation.REQUIRED, readOnly = true)      
//        public Object fetchInnerEntities(String param, String type) {
//         List<Lead> list=null;
//         if(param.equals("Lead")){
//             if(type.equals("NotificationOn")){
//                    Session session = sessionFactory.getCurrentSession();
//                    list=(List<Lead>) session.createCriteria(Lead.class).add(Restrictions.isNotNull("followUpDate")).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
//             }
//             
//              if(type.equals("Pending")){
//                  Session session = sessionFactory.getCurrentSession();
//                    list=(List<Lead>) session.createCriteria(Lead.class).add(Restrictions.ge("count", 1)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
//        
//             }
//             
//         }
//         else if(param.equals("Number")){
//             Session session = sessionFactory.getCurrentSession();
//            list=(List<Lead>) session.createCriteria(Lead.class).createAlias("client", "c").
//                   add(Restrictions.eq("c.phoneNumber", type)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
//        
//         }
//          else if(param.equals("MissedAppointmentStatusToday")){
//             Session session = sessionFactory.getCurrentSession();
//            list=(List<Lead>) session.createCriteria(Lead.class).
//                   add(Restrictions.and(Restrictions.like("missedAppointmentStatus", type,MatchMode.ANYWHERE),Restrictions.eq("appointmentDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date())))).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
//        
//         }
//         else if(param.equals("ActiveSourceLeads")){
//             Session session = sessionFactory.getCurrentSession();
//            list=(List<Lead>) session.createCriteria(Lead.class,"l").createAlias("client", "c").
//                   add(Restrictions.and(Restrictions.ge("l.count", 1),Restrictions.eq("c.source", type))).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
//        
//         }
//         if(list!=null){
//            for(Lead lead:list){
//                if(lead!=null){
//                    if(lead.getBeneficiaries()!=null){
//                                                                         lead.getBeneficiaries().size();
//                                                   }
//               }
//            }
//         }
//         System.out.println("READ LEAD WITH A STATUS :::::::::::::::::::::::::::::::::::::::::::::::CHECK");
//         return (T) list;
//    }
//      
//        //read client with inner elements
//        @Transactional(propagation = Propagation.REQUIRED, readOnly = true)  
//        public Object readEntityLists(Object entity){
//        Client client=(Client) entity;
//            Session session = sessionFactory.getCurrentSession();
//            client=(Client) session.merge(client);
//            client.getLead().size();
//            for(Lead lead:client.getLead()){
//                 if(lead.getBeneficiaries()!=null){
//                                                                      lead.getBeneficiaries().size();
//                                                }
//            }
//        System.out.println("READ CLIENT WITH INNER ELEMENTS :::::::::::::::::::::::::::::::::::::::::::::::CHECK");
//         return client;
//    }
//        
//        //read client with a property
//        @Transactional(propagation = Propagation.REQUIRED,readOnly = true)  
//        public Object getByProperty(Object entity, String listType) {
//        List<Client> list = new ArrayList<Client>();
//        switch(listType){
//            case "Email":
//                    String emailId= (String) entity;
//                      Session session = sessionFactory.getCurrentSession();
//                      Criteria criteria = session.createCriteria(Client.class, "client");
//                      criteria.add(Restrictions.eq("emailId", emailId));
//                      list=criteria.list();
//                    
//       
//                break;
//                case "City":
//                    String city= (String) entity;
//                      session = sessionFactory.getCurrentSession();
//                      criteria = session.createCriteria(Client.class, "client");
//                      criteria.add(Restrictions.like("city", city, MatchMode.START));
//                      list=criteria.list();
//
//       
//                break;
//            case "Id":
//                    Long orderId= (Long) entity;
//                        session = sessionFactory.getCurrentSession();
//                        list.add((Client) session.get(Client.class, orderId));
//                       
//                
//                break;
//                
//                 case "LeadId":
//                    String leadId= (String) entity;
//                        session = sessionFactory.getCurrentSession();
//                        criteria = session.createCriteria(Client.class, "client");
//                      criteria.add(Restrictions.eq("tempLeadDetails", leadId));
//                      list=criteria.list();
//                       
//                
//                break;
//            case "Number":
//                    String number= (String) entity;
//                    session = sessionFactory.getCurrentSession();
//                      criteria = session.createCriteria(Client.class);
//                      criteria.add(Restrictions.like("phoneNumber", number,MatchMode.ANYWHERE));
//                      list= criteria.list();
//                      for(Client client :list){
//                          client.getLead().size();
//                          for(Lead lead:client.getLead()){
//                              if(lead.getBeneficiaries()!=null){
//                                    lead.getBeneficiaries().size();
//                              }
//                          }
//                      }
//
//                break;
//                 case "Name":
//                    String name= (String) entity;
//                      session = sessionFactory.getCurrentSession();
//                      criteria = session.createCriteria(Client.class);
//                      criteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
//                      list= criteria.list();
//                    
//                break;
//                case "DateCreated":
//                    String dateCreated= (String) entity;
//                    session = sessionFactory.getCurrentSession();
//                      
//                      criteria = session.createCriteria(Client.class);
//                      criteria.add(Restrictions.like("dateCreation", dateCreated, MatchMode.START));
//                      list= criteria.list();
//                     
//                break;
//                     case "DateCreatedLeadProperty":
//                    dateCreated= (String) entity;
//                    session = sessionFactory.getCurrentSession();
//                      
//                      criteria = session.createCriteria(Lead.class);
//                      criteria.createAlias("client", "c").add(Restrictions.like("c.dateCreation", dateCreated, MatchMode.START));
//                      list= criteria.list();
//                     
//                break;
//               case "DateUpdatedFull":
//                    String dateUpdated= (String) entity;
//                     session = sessionFactory.getCurrentSession();
//                      
//                      criteria = session.createCriteria(Client.class,"client");
//                       criteria.createAlias("client.lead", "l");
//                       criteria.createAlias("l.call", "c");
//                      criteria.add(Restrictions.like("c.DateUpdated", dateUpdated, MatchMode.START));
//                      criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
//                      list= criteria.list();
//                      
//                      for(Client client:list){
//                          client.getLead().size();
//                          for(Lead lead:client.getLead()){
//                              lead.getCall().size();
//                              if(lead.getBeneficiaries()!=null){
//                                    lead.getBeneficiaries().size();
//                              }
//                          }
//                      }
//                      
//
//                break;
//                    
//            default: System.err.println("Not a valid option");
//                break;
//        }
//        System.out.println("READ CLIENT WITH A PROPERTY (INNER AND NON INNER MIXED) :::::::::::::::::::::::::::::::::::::::::::::::CHECK");
//    return list;
//    }
//        
//        //read client with status
//        @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
//        public List fetchEntities(String paramVal) {
//        List list=null;     
//       
//                 
//                 switch (paramVal) {
//                     
//                     case "PendingMinusOne":
//                                            Session session = sessionFactory.getCurrentSession();
//                                            Criteria criteria = session.createCriteria(Client.class,"c");
//                                            criteria.createAlias("c.lead", "l");
//                                            criteria.add(
//                                                            Restrictions.and(
//                                                                    Restrictions.lt("l.count", 0),
//                                                                    Restrictions.isNull("l.followUpDate"),
//                                                                    Restrictions.gt("l.leadId", "50000")
//                                                            ));
//                                            criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
//                                            list = criteria.list();
//                                            
//                                            
//                                            
//                                            for(Client client:(List<Client>)list){
//                                                client.getLead().size();
//                                                for(Lead lead:client.getLead()){
//                                                if(lead.getBeneficiaries()!=null){
//                                                                      lead.getBeneficiaries().size();
//                                                }
//                                                    lead.getCall().size();
//                                                }
//                                            }
//                         break;
//                         case "PendingInProgress":
//                                            session = sessionFactory.getCurrentSession();
//                                            criteria = session.createCriteria(Client.class,"c");
//                                            criteria.createAlias("c.lead", "l");
//                                            criteria.createAlias("l.call", "call");
//                                            criteria.add(
//                                                            Restrictions.and(
//                                                                    Restrictions.le("l.count", 0),
//                                                                    Restrictions.gt("l.leadId", "50000"),
//                                                                    Restrictions.eq("call.Status", "in-progress")
//                                                            ));
//                                            criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
//                                            list = criteria.list();
//                                            
//                                            
//                                            
//                                            for(Client client:(List<Client>)list){
//                                                client.getLead().size();
//                                                for(Lead lead:client.getLead()){
//                                                if(lead.getBeneficiaries()!=null){
//                                                                      lead.getBeneficiaries().size();
//                                                }
//                                                    lead.getCall().size();
//                                                }
//                                            }
//                         break;
//                      case "PendingAndNotified":
//                                            session = sessionFactory.getCurrentSession();
//                                            criteria = session.createCriteria(Client.class,"c");
//                                            criteria.createAlias("c.lead", "l");
//                                            criteria.add(
//                                                Restrictions.or(
//                                                            Restrictions.and(
//
//                                                                    Restrictions.ge("l.count", 1),
//                                                                    Restrictions.eq("l.followUpDate",""),
//                                                                    Restrictions.isNull("l.followUpDate")
//                                                            ),
//                                                    
//                                                            Restrictions.and(
//                                                                Restrictions.ge("l.count", 1),
//                                                                Restrictions.ne("l.followUpDate",""),
//                                                                Restrictions.isNotNull("l.followUpDate"),
//                                                                Restrictions.le("l.followUpDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
//                                                            ),
//                                                            Restrictions.eq("l.followUpDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
//                                                )
//                                                            
//                                            );
//                                            criteria.addOrder(Order.desc("l.followUpDate"));
//                                            criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
//                                            list = criteria.list();
//                                            
//                                            
//                                            
//                                            for(Client client:(List<Client>)list){
//                                                client.getLead().size();
//                                                for(Lead lead:client.getLead()){
//                                                if(lead.getBeneficiaries()!=null){
//                                                                      lead.getBeneficiaries().size();
//                                                }
//                                                    lead.getCall().size();
//                                                }
//                                            }
//                         break;
//                    
//                     case "Pending":
//                                            session = sessionFactory.getCurrentSession();
//                                            criteria = session.createCriteria(Client.class, "c");
//                                            criteria.createAlias("c.lead", "l");
//                                            criteria.add(
//                                              Restrictions.and(
//                                                    Restrictions.and(
//                                                            Restrictions.ge("l.count", 1),
//                                                            Restrictions.isNull("l.missedAppointment"),
//                                                            Restrictions.isNull("l.followUpDate")
//                                                    )
//                                              )
//                                            );
//                                            
//                                            criteria.addOrder(Order.asc("l.count"));
//                                            criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
//                                            list = criteria.list();
//                                            for(Client client:(List<Client>)list){
//                                                client.getLead().size();
//                                                for(Lead lead:client.getLead()){
//                                                     if(lead.getBeneficiaries()!=null){
//                                                                      lead.getBeneficiaries().size();
//                                                     }
//                                                    lead.getCall().size();
//                                                    
//                                                }
//                                            }
//                         break;
//                         case "Notified":
//                                            session = sessionFactory.getCurrentSession();
//                                            criteria = session.createCriteria(Client.class,"c");
//                                            criteria.createAlias("c.lead", "l");
//                                            criteria.add(
//                                                    Restrictions.or(
//                                                        Restrictions.and(
//                                                                Restrictions.and(
//                                                                        Restrictions.ge("l.count", 1),
//                                                                        Restrictions.and(
//                                                                                Restrictions.le("l.followUpDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date())),
//                                                                                Restrictions.gt("l.followUpDate", "2016-01-01")
//                                                                        )
//                                                                ),
//                                                                Restrictions.isNull("l.missedAppointment")
//                                                        ),
//                                                         Restrictions.and(
//                                                            Restrictions.eq("l.followUpDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date())),
//                                                            Restrictions.eq("l.leadStatus", "Follow up/Call back")
//                                                        )
//                                                    )
//                                            );
//                                            criteria.addOrder(Order.asc("l.followUpDate"));
//                                            criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
//                                            list = criteria.list();
//                                            for(Client client:(List<Client>)list){
//                                                client.getLead().size();
//                                                for(Lead lead:client.getLead()){
//                                                if(lead.getBeneficiaries()!=null){
//                                                                      lead.getBeneficiaries().size();
//                                                }
//                                                    lead.getCall().size();
//                                                }
//                                            }
//                         break;
//                         case "PendingAndNotifiedMA":
//                                            session = sessionFactory.getCurrentSession();
//                                            criteria = session.createCriteria(Client.class,"c");
//                                            criteria.createAlias("c.lead", "l");
//                                            criteria.add(
//                                                    Restrictions.and(
//                                                            Restrictions.ge("l.count", 1),
//                                                            Restrictions.eq("l.missedAppointment", true),
//                                                             Restrictions.or(
//                                                                     Restrictions.isNull("l.followUpDate"),
//                                                                    Restrictions.le("l.followUpDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
//                                                             )
//                                                    )
//                                            );
//                                          
//                                            criteria.addOrder(Order.desc("l.count"));
//                                            criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
//                                            list = criteria.list();
//                                            for(Client client:(List<Client>)list){
//                                                client.getLead().size();
//                                                for(Lead lead:client.getLead()){
//                                                     if(lead.getBeneficiaries()!=null){
//                                                                      lead.getBeneficiaries().size();
//                                                     }
//                                                    lead.getCall().size();
//                                                }
//                                            }
//                         break;
//                         case "NotifiedMA":
//                                            session = sessionFactory.getCurrentSession();
//                                            criteria = session.createCriteria(Client.class);
//                                            criteria.createAlias("lead", "l");
//                                             criteria.add(
//                                                    Restrictions.and(
//                                                            Restrictions.and(
//                                                                    Restrictions.ge("l.count", 1),
//                                                                    Restrictions.and(
//                                                                            Restrictions.le("l.followUpDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date())),
//                                                                            Restrictions.gt("l.followUpDate", "2016-01-01")
//                                                                    )
//                                                            ),
//                                                            Restrictions.eq("l.missedAppointment", true)
//                                                    )
//                                            );
//                                            criteria.addOrder(Order.asc("l.followUpDate"));
//                                            criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
//                                            list = criteria.list();
//                                            for(Client client:(List<Client>)list){
//                                                client.getLead().size();
//                                                for(Lead lead:client.getLead()){
//                                                if(lead.getBeneficiaries()!=null){
//                                                                      lead.getBeneficiaries().size();
//                                                }
//                                                    lead.getCall().size();
//                                                }
//                                            }
//                         break;
//                         
//                         case "Overnight":
//                                            session = sessionFactory.getCurrentSession();
//                                            criteria = session.createCriteria(Client.class);
//                                            criteria.add(Restrictions.eq("overnight", true));
//                                            list = criteria.list();
//                                            for(Client client:(List<Client>)list){
//                                                  for(Lead lead:client.getLead()){
//                                                     if(lead.getBeneficiaries()!=null){
//                                                                      lead.getBeneficiaries().size();
//                                                    }
//                                                    lead.getCall().size();
//                                                }
//                                                client.getLead().size();
//                                            }
//                         break;
//                     
//                     default:
//                                            
//                         break;
//                 }
//                  if(list!=null){           
//                                                System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+list.size()+"$$$"+paramVal);
//                                             }
//                
//            System.out.println("READ CLIENT WITH INNER ELEMENTS WITH STATUS :::::::::::::::::::::::::::::::::::::::::::::::CHECK");
//             return list;
//       
//    }
//    
//        
//        //update inner details of lead
//        @Transactional(propagation = Propagation.REQUIRED)   
//        public Object updateInnerPropertyList(Object entity,Object property,String listType) {
//                    if(listType.equals("Call")){
//                                    Lead lead=(Lead) entity;
//                                    Call call= (Call) property;
//                                    Call storedCall=null;
//                                    Session session = sessionFactory.getCurrentSession();
//                                    lead=(Lead) session.merge(lead);
//                                    storedCall=(Call) session.merge(call);
//                                    if(storedCall!=null){
//                                                    call=storedCall;
//                                    }
//                                    lead.getCall().size();
//                                    lead.getBeneficiaries().size();
//                                    lead.getCall().add(call);
//                                   // call.setLead(lead);
//                                    session.update(lead);
//                                    System.out.println("UPDATE CALL DETAILS OF LEAD :::::::::::::::::::::::::::::::::::::::::::::::CHECK");
//                                    return lead;
//                    }
//                    else if(listType.equals("Beneficiaries")){
//                                    Lead lead=(Lead) entity;
//                                    Beneficiaries ben= (Beneficiaries) property;
//                                    Beneficiaries storedBen=null;
//                                    Session session = sessionFactory.getCurrentSession();
//                                    lead=(Lead) session.merge(lead);
//                                    storedBen=(Beneficiaries) session.merge(ben);
//                                    if(storedBen!=null){
//                                                    ben=storedBen;
//                                    }
//                                    
//                                    lead.getCall().size();
//                                    lead.getBeneficiaries().size();
//                                    lead.getBeneficiaries().add(ben);
//                                    session.update(lead);
//                                    ben.setLead(lead);
//                                    session.update(ben);
//                                    System.out.println("UPDATE BEN DETAILS OF LEAD :::::::::::::::::::::::::::::::::::::::::::::::CHECK");
//                                    return lead;
//                    }
//                    return null;
//        }
//        
//        //update client lead and beneficiaries
//        @Transactional(propagation = Propagation.REQUIRED)   
//        public boolean updatePropertyList(Object entity,Object property,String listType) {
//                    if(listType.equals("AddLead")){
//                                      Client client=(Client) entity;
//                                    Lead lead=(Lead) property;
//                                    Lead storedLead=null;
//                                        Session session = sessionFactory.getCurrentSession();
//                                        client=(Client) session.merge(client);
//                                        lead.setClient(client);
//                                        storedLead = (Lead) session.merge(lead);
//                                        if(storedLead!=null){
//                                            lead=storedLead;
//                                        }
//                                        client.getLead().add(lead);
//                                        client.getLead().size();
//                                        session.update(client);  
////                                        lead.setClient(client);
////                                        session.update(lead);
////                                    System.out.println("ADD LEAD :::::::::::::::::::::::::::::::::::::::::::::::CHECK");
////                                    session.createCriteria(TempClient.class, "client").add(Restrictions.eq("tempLeadDetails", lead.getLeadId())).list();
//                                    return true;
//                    }
//                    System.out.println("ADD LEAD :::::::::::::::::::::::::::::::::::::::::::::::FAIL");
//                    return false;
//        }
//     
        

   
}
