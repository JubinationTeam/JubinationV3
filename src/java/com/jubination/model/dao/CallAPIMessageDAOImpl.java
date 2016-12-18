/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.dao;

import com.jubination.model.pojo.exotel.Call;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Welcome
 */
@Repository
public class CallAPIMessageDAOImpl<T> implements java.io.Serializable {
private Session session=null;
    @Autowired
    private SessionFactory sessionFactory;

        
    @Transactional(propagation = Propagation.REQUIRED)
    public Object buildEntity(Object entity) {
        Call msg=(Call) entity;
            session = getSessionFactory().getCurrentSession();
            session.save(msg);
            msg = (Call) session.get(Call.class, msg.getOrderId());
    
        return (T) msg;

    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public Object getByProperty(Object entity, String listType) {
        List<Call> list = new ArrayList<Call>();
        switch(listType){
            case "Number":
                    String number= (String) entity;
                    session = getSessionFactory().getCurrentSession();
                      
                      Criteria criteria = session.createCriteria(Call.class, "call");
                      criteria.add(Restrictions.eq("CallTo", number));
                      list=criteria.list();
                 
                break;
            case "OrderId":
                    Long orderId= (Long) entity;
                        session = getSessionFactory().getCurrentSession();
                        list.add((Call) session.get(Call.class, (Long)orderId));
                    
                break;
            case "Sid":
                    String sid= (String) entity;
                    session = getSessionFactory().getCurrentSession();
                      criteria = session.createCriteria(Call.class, "call");
                      criteria.add(Restrictions.eq("Sid", sid));
                      list= criteria.list();
                      
                break;
                case "DateCreated":
                    String dateCreated= (String) entity;
                    session = getSessionFactory().getCurrentSession();
                     criteria = session.createCriteria(Call.class, "call");
                      criteria.add(Restrictions.like("DateCreated", dateCreated,MatchMode.START));
                      list= criteria.list();
                    
                break;
               case "PendingOnDate":
                    String pendingOnDate= (String) entity;
                    session = getSessionFactory().getCurrentSession();
                    criteria = session.createCriteria(Call.class, "call");
                       criteria.add(Restrictions.or(
                               Restrictions.and(
                                    Restrictions.like("DateCreated", pendingOnDate,MatchMode.START),
                                    Restrictions.like("TrackStatus", "did not",MatchMode.ANYWHERE)),
                               Restrictions.and(
                                    Restrictions.like("DateCreated", pendingOnDate,MatchMode.START),
                                    Restrictions.like("TrackStatus", "Pressed 2",MatchMode.START)),
			       Restrictions.and(
                                    Restrictions.like("DateCreated", pendingOnDate,MatchMode.START),
                                    Restrictions.like("Status", "failed",MatchMode.START)),
			       Restrictions.and(
                                    Restrictions.like("DateCreated", pendingOnDate,MatchMode.START),
                                    Restrictions.like("Status", "no-answer",MatchMode.START))
                                    ));
                      list= criteria.list();
                     
                break;
            default: System.err.println("Not a valid option");
                break;
        }
    return list;
    }
   
    @Transactional(propagation = Propagation.REQUIRED)
    public Object updateProperty(Object entity) {
        
        Call msg=(Call) entity;
       
                    session = getSessionFactory().getCurrentSession();
            msg=(Call) session.merge(msg);
            
        
            return msg;
    }
 
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public Long fetchEntitySize(String fromDate, String toDate, String type) {
        Long size = 0l;
        switch(type){
            case "Total":
                
                    session = getSessionFactory().getCurrentSession();
                      Criteria criteria = session.createCriteria(Call.class, "call");
                      criteria.setReadOnly(true);
                      criteria.add(
                              Restrictions.and(
                                      Restrictions.ge("DateCreated",fromDate),
                                      Restrictions.le("DateCreated",toDate),
                                      Restrictions.isNull("lead")
                              )
                      );
                      criteria.setProjection(Projections.rowCount());
                      size = (Long) criteria.uniqueResult();
                      
                    break;
            case "Busy":
                    session = getSessionFactory().getCurrentSession();
                      criteria = session.createCriteria(Call.class, "call");
                      criteria.setReadOnly(true);
                      criteria.add(
                              Restrictions.and(
                                      Restrictions.ge("DateCreated",fromDate),
                                      Restrictions.le("DateCreated",toDate),
                                      Restrictions.isNull("lead")
                              )
                      );
                      criteria.add(Restrictions.like("Status", "busy",MatchMode.ANYWHERE));
                      criteria.setProjection(Projections.rowCount());
                      size = (Long) criteria.uniqueResult();
                     
                 break;
            case "Failed":
                    session = getSessionFactory().getCurrentSession();
                      criteria = session.createCriteria(Call.class, "call");
                      criteria.setReadOnly(true);
                      criteria.add(
                              Restrictions.and(
                                      Restrictions.ge("DateCreated",fromDate),
                                      Restrictions.le("DateCreated",toDate),
                                      Restrictions.isNull("lead")
                              )
                      );
                      criteria.add(Restrictions.like("Status", "failed",MatchMode.ANYWHERE));
                      criteria.setProjection(Projections.rowCount());
                      size = (Long) criteria.uniqueResult();
                 
                 break;
            case "NoAnswer":
                    session = getSessionFactory().getCurrentSession();
                      criteria = session.createCriteria(Call.class, "call");
                      criteria.setReadOnly(true);
                      criteria.add(
                              Restrictions.and(
                                      Restrictions.ge("DateCreated",fromDate),
                                      Restrictions.le("DateCreated",toDate),
                                      Restrictions.isNull("lead")
                              )
                      );
                      criteria.add(Restrictions.like("Status", "no-answer",MatchMode.ANYWHERE));
                      criteria.setProjection(Projections.rowCount());
                      size = (Long) criteria.uniqueResult();
                      
                 break;
              case "RequestedCallBack":
                    session = getSessionFactory().getCurrentSession();
                      criteria = session.createCriteria(Call.class, "call");
                      criteria.setReadOnly(true);
                      criteria.add(
                              Restrictions.and(
                                      Restrictions.ge("DateCreated",fromDate),
                                      Restrictions.le("DateCreated",toDate),
                                      Restrictions.isNull("lead")
                              )
                      );
                      criteria.add(Restrictions.like("TrackStatus", "requested for callback",MatchMode.ANYWHERE));
                      criteria.setProjection(Projections.rowCount());
                      size = (Long) criteria.uniqueResult();

                 break;
            case "GreetingsHangUp":
                    session = getSessionFactory().getCurrentSession();
                      criteria = session.createCriteria(Call.class, "call");
                      criteria.setReadOnly(true);
                      criteria.add(
                              Restrictions.and(
                                      Restrictions.ge("DateCreated",fromDate),
                                      Restrictions.le("DateCreated",toDate),
                                      Restrictions.isNull("lead")
                              )
                      );
                      criteria.add(Restrictions.like("CallType", "trans",MatchMode.ANYWHERE));
                      criteria.add(Restrictions.like("Status", "completed",MatchMode.ANYWHERE));
                      criteria.add(Restrictions.isNull("TrackStatus"));
                      criteria.setProjection(Projections.rowCount());
                      size = (Long) criteria.uniqueResult();
                      
                       break;
            case "HangUpOnConnect":
                    session = getSessionFactory().getCurrentSession();
                      criteria = session.createCriteria(Call.class, "call");
                      
                      criteria.setReadOnly(true);
                      criteria.add(
                              Restrictions.and(
                                      Restrictions.ge("DateCreated",fromDate),
                                      Restrictions.le("DateCreated",toDate),
                                      Restrictions.isNull("lead")
                              )
                      );
                      criteria.add(Restrictions.like("TrackStatus", "did not speak",MatchMode.ANYWHERE));
                      criteria.add(Restrictions.like("CallType", "client-hangup",MatchMode.ANYWHERE));
                      criteria.setProjection(Projections.rowCount());
                      size = (Long) criteria.uniqueResult();
                      
                  break;
            case "MissCall": 
                    session = getSessionFactory().getCurrentSession();
                      criteria = session.createCriteria(Call.class, "call");
                      criteria.setReadOnly(true);
                      criteria.add(
                              Restrictions.and(
                                      Restrictions.ge("DateCreated",fromDate),
                                      Restrictions.le("DateCreated",toDate),
                                      Restrictions.isNull("lead")
                              )
                      );
                      criteria.add(Restrictions.like("TrackStatus", "did not speak",MatchMode.ANYWHERE));
                      criteria.add(Restrictions.like("CallType", "incomplete",MatchMode.ANYWHERE));
                      criteria.setProjection(Projections.rowCount());
                      size = (Long) criteria.uniqueResult();
             break;
            case "Spoke":
                    session = getSessionFactory().getCurrentSession();
                      criteria = session.createCriteria(Call.class, "call");
                      criteria.setReadOnly(true);
                      criteria.add(
                              Restrictions.and(
                                      Restrictions.ge("DateCreated",fromDate),
                                      Restrictions.le("DateCreated",toDate),
                                      Restrictions.isNull("lead")
                              )
                      );
                      criteria.add(Restrictions.like("TrackStatus", "spoke",MatchMode.ANYWHERE));
                      criteria.setProjection(Projections.rowCount());
                      size = (Long) criteria.uniqueResult();
     
                       break;
                
        }
        return size;
    }
       
    
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public Long fetchEntitySize(String date, String type) {
        Long size = 0l;
        switch(type){
            case "Total":
                
                    session = getSessionFactory().getCurrentSession();
                      Criteria criteria = session.createCriteria(Call.class, "call");
                      criteria.setReadOnly(true);
                      criteria.add(Restrictions.like("DateCreated", date,MatchMode.START));
                      criteria.setProjection(Projections.rowCount());
                      size = (Long) criteria.uniqueResult();
                      
                    break;
            case "Busy":
                    session = getSessionFactory().getCurrentSession();
                      criteria = session.createCriteria(Call.class, "call");
                      criteria.setReadOnly(true);
                      criteria.add(Restrictions.like("DateCreated", date,MatchMode.START));
                      criteria.add(Restrictions.like("Status", "busy",MatchMode.ANYWHERE));
                      criteria.setProjection(Projections.rowCount());
                      size = (Long) criteria.uniqueResult();
                     
                 break;
            case "Failed":
                    session = getSessionFactory().getCurrentSession();
                      criteria = session.createCriteria(Call.class, "call");
                      criteria.setReadOnly(true);
                      criteria.add(Restrictions.like("DateCreated", date,MatchMode.START));
                      criteria.add(Restrictions.like("Status", "failed",MatchMode.ANYWHERE));
                      criteria.setProjection(Projections.rowCount());
                      size = (Long) criteria.uniqueResult();
                 
                 break;
            case "NoAnswer":
                    session = getSessionFactory().getCurrentSession();
                      criteria = session.createCriteria(Call.class, "call");
                      criteria.setReadOnly(true);
                      criteria.add(Restrictions.like("DateCreated", date,MatchMode.START));
                      criteria.add(Restrictions.like("Status", "no-answer",MatchMode.ANYWHERE));
                      criteria.setProjection(Projections.rowCount());
                      size = (Long) criteria.uniqueResult();
                      
                 break;
              case "RequestedCallBack":
                    session = getSessionFactory().getCurrentSession();
                      criteria = session.createCriteria(Call.class, "call");
                      criteria.setReadOnly(true);
                      criteria.add(Restrictions.like("DateCreated", date,MatchMode.START));
                      criteria.add(Restrictions.like("TrackStatus", "requested for callback",MatchMode.ANYWHERE));
                      criteria.setProjection(Projections.rowCount());
                      size = (Long) criteria.uniqueResult();

                 break;
            case "GreetingsHangUp":
                    session = getSessionFactory().getCurrentSession();
                      criteria = session.createCriteria(Call.class, "call");
                      criteria.setReadOnly(true);
                      criteria.add(Restrictions.like("DateCreated", date,MatchMode.START));
                      criteria.add(Restrictions.like("CallType", "trans",MatchMode.ANYWHERE));
                      criteria.add(Restrictions.like("Status", "completed",MatchMode.ANYWHERE));
                      criteria.add(Restrictions.isNull("TrackStatus"));
                      criteria.setProjection(Projections.rowCount());
                      size = (Long) criteria.uniqueResult();
                      
                       break;
            case "HangUpOnConnect":
                    session = getSessionFactory().getCurrentSession();
                      criteria = session.createCriteria(Call.class, "call");
                      
                      criteria.setReadOnly(true);
                      criteria.add(Restrictions.like("DateCreated", date,MatchMode.START));
                      criteria.add(Restrictions.like("TrackStatus", "did not speak",MatchMode.ANYWHERE));
                      criteria.add(Restrictions.like("CallType", "client-hangup",MatchMode.ANYWHERE));
                      criteria.setProjection(Projections.rowCount());
                      size = (Long) criteria.uniqueResult();
                      
                  break;
            case "MissCall": 
                    session = getSessionFactory().getCurrentSession();
                      criteria = session.createCriteria(Call.class, "call");
                      criteria.setReadOnly(true);
                      criteria.add(Restrictions.like("DateCreated", date,MatchMode.START));
                      criteria.add(Restrictions.like("TrackStatus", "did not speak",MatchMode.ANYWHERE));
                      criteria.add(Restrictions.like("CallType", "incomplete",MatchMode.ANYWHERE));
                      criteria.setProjection(Projections.rowCount());
                      size = (Long) criteria.uniqueResult();
             break;
            case "Spoke":
                    session = getSessionFactory().getCurrentSession();
                      criteria = session.createCriteria(Call.class, "call");
                      criteria.setReadOnly(true);
                      criteria.add(Restrictions.like("DateCreated", date,MatchMode.START));
                      criteria.add(Restrictions.like("TrackStatus", "spoke",MatchMode.ANYWHERE));
                      criteria.setProjection(Projections.rowCount());
                      size = (Long) criteria.uniqueResult();
     
                       break;
                
        }
        return size;
    }
       
   
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    
}
