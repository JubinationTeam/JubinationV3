 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.dao.impl;

import com.jubination.model.dao.plan.GenericDAOAbstract;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Welcome
 */
@Repository
public class CallAPIMessageDAO extends GenericDAOAbstract implements java.io.Serializable {

    public CallAPIMessageDAO() {
        setClassType(CallAPIMessageDAO.class);
    }

    
//    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
//    public Object getByProperty(Object entity, String listType) {
//        List<Call> list = new ArrayList<>();
//        switch(listType){
//            case "Number":
//                    String number= (String) entity;
//                    Session session = sessionFactory.getCurrentSession();
//                      
//                      Criteria criteria = session.createCriteria(Call.class, "call");
//                      criteria.add(Restrictions.eq("CallTo", number));
//                      list=criteria.list();
//                 
//                break;
//            case "OrderId":
//                    Long orderId= (Long) entity;
//                        session = sessionFactory.getCurrentSession();
//                        list.add((Call) session.get(Call.class, (Long)orderId));
//                    
//                break;
//            case "Sid":
//                    String sid= (String) entity;
//                    session = sessionFactory.getCurrentSession();
//                      criteria = session.createCriteria(Call.class, "call");
//                      criteria.add(Restrictions.eq("Sid", sid));
//                      list= criteria.list();
//                      
//                break;
//                case "DateCreated":
//                    String dateCreated= (String) entity;
//                    session = sessionFactory.getCurrentSession();
//                     criteria = session.createCriteria(Call.class, "call");
//                      criteria.add(Restrictions.like("DateCreated", dateCreated,MatchMode.START));
//                      list= criteria.list();
//                    
//                break;
//               case "PendingOnDate":
//                    String pendingOnDate= (String) entity;
//                    session = sessionFactory.getCurrentSession();
//                    criteria = session.createCriteria(Call.class, "call");
//                       criteria.add(Restrictions.or(
//                               Restrictions.and(
//                                    Restrictions.like("DateCreated", pendingOnDate,MatchMode.START),
//                                    Restrictions.like("TrackStatus", "did not",MatchMode.ANYWHERE)),
//                               Restrictions.and(
//                                    Restrictions.like("DateCreated", pendingOnDate,MatchMode.START),
//                                    Restrictions.like("TrackStatus", "Pressed 2",MatchMode.START)),
//			       Restrictions.and(
//                                    Restrictions.like("DateCreated", pendingOnDate,MatchMode.START),
//                                    Restrictions.like("Status", "failed",MatchMode.START)),
//			       Restrictions.and(
//                                    Restrictions.like("DateCreated", pendingOnDate,MatchMode.START),
//                                    Restrictions.like("Status", "no-answer",MatchMode.START))
//                                    ));
//                      list= criteria.list();
//                     
//                break;
//            default: System.err.println("Not a valid option");
//                break;
//        }
//    return list;
//    }
   
//    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
//    public Long fetchEntitySize(String fromDate, String toDate, String type) {
//        Long size = 0l;
//        switch(type){
//            case "Total":
//                
//                    Session session = sessionFactory.getCurrentSession();
//                      Criteria criteria = session.createCriteria(Call.class, "call");
//                      criteria.setReadOnly(true);
//                      criteria.add(
//                              Restrictions.and(
//                                      Restrictions.ge("DateCreated",fromDate),
//                                      Restrictions.le("DateCreated",toDate),
//                                      Restrictions.isNull("lead")
//                              )
//                      );
//                      criteria.setProjection(Projections.rowCount());
//                      size = (Long) criteria.uniqueResult();
//                      
//                    break;
//            case "Busy":
//                    session = sessionFactory.getCurrentSession();
//                      criteria = session.createCriteria(Call.class, "call");
//                      criteria.setReadOnly(true);
//                      criteria.add(
//                              Restrictions.and(
//                                      Restrictions.ge("DateCreated",fromDate),
//                                      Restrictions.le("DateCreated",toDate),
//                                      Restrictions.isNull("lead")
//                              )
//                      );
//                      criteria.add(Restrictions.like("Status", "busy",MatchMode.ANYWHERE));
//                      criteria.setProjection(Projections.rowCount());
//                      size = (Long) criteria.uniqueResult();
//                     
//                 break;
//            case "Failed":
//                    session = sessionFactory.getCurrentSession();
//                      criteria = session.createCriteria(Call.class, "call");
//                      criteria.setReadOnly(true);
//                      criteria.add(
//                              Restrictions.and(
//                                      Restrictions.ge("DateCreated",fromDate),
//                                      Restrictions.le("DateCreated",toDate),
//                                      Restrictions.isNull("lead")
//                              )
//                      );
//                      criteria.add(Restrictions.like("Status", "failed",MatchMode.ANYWHERE));
//                      criteria.setProjection(Projections.rowCount());
//                      size = (Long) criteria.uniqueResult();
//                 
//                 break;
//            case "NoAnswer":
//                    session = sessionFactory.getCurrentSession();
//                      criteria = session.createCriteria(Call.class, "call");
//                      criteria.setReadOnly(true);
//                      criteria.add(
//                              Restrictions.and(
//                                      Restrictions.ge("DateCreated",fromDate),
//                                      Restrictions.le("DateCreated",toDate),
//                                      Restrictions.isNull("lead")
//                              )
//                      );
//                      criteria.add(Restrictions.like("Status", "no-answer",MatchMode.ANYWHERE));
//                      criteria.setProjection(Projections.rowCount());
//                      size = (Long) criteria.uniqueResult();
//                      
//                 break;
//              case "RequestedCallBack":
//                    session = sessionFactory.getCurrentSession();
//                      criteria = session.createCriteria(Call.class, "call");
//                      criteria.setReadOnly(true);
//                      criteria.add(
//                              Restrictions.and(
//                                      Restrictions.ge("DateCreated",fromDate),
//                                      Restrictions.le("DateCreated",toDate),
//                                      Restrictions.isNull("lead")
//                              )
//                      );
//                      criteria.add(Restrictions.like("TrackStatus", "requested for callback",MatchMode.ANYWHERE));
//                      criteria.setProjection(Projections.rowCount());
//                      size = (Long) criteria.uniqueResult();
//
//                 break;
//            case "GreetingsHangUp":
//                    session = sessionFactory.getCurrentSession();
//                      criteria = session.createCriteria(Call.class, "call");
//                      criteria.setReadOnly(true);
//                      criteria.add(
//                              Restrictions.and(
//                                      Restrictions.ge("DateCreated",fromDate),
//                                      Restrictions.le("DateCreated",toDate),
//                                      Restrictions.isNull("lead")
//                              )
//                      );
//                      criteria.add(Restrictions.like("CallType", "trans",MatchMode.ANYWHERE));
//                      criteria.add(Restrictions.like("Status", "completed",MatchMode.ANYWHERE));
//                      criteria.add(Restrictions.isNull("TrackStatus"));
//                      criteria.setProjection(Projections.rowCount());
//                      size = (Long) criteria.uniqueResult();
//                      
//                       break;
//            case "HangUpOnConnect":
//                    session = sessionFactory.getCurrentSession();
//                      criteria = session.createCriteria(Call.class, "call");
//                      
//                      criteria.setReadOnly(true);
//                      criteria.add(
//                              Restrictions.and(
//                                      Restrictions.ge("DateCreated",fromDate),
//                                      Restrictions.le("DateCreated",toDate),
//                                      Restrictions.isNull("lead")
//                              )
//                      );
//                      criteria.add(Restrictions.like("TrackStatus", "did not speak",MatchMode.ANYWHERE));
//                      criteria.add(Restrictions.like("CallType", "client-hangup",MatchMode.ANYWHERE));
//                      criteria.setProjection(Projections.rowCount());
//                      size = (Long) criteria.uniqueResult();
//                      
//                  break;
//            case "MissCall": 
//                    session = sessionFactory.getCurrentSession();
//                      criteria = session.createCriteria(Call.class, "call");
//                      criteria.setReadOnly(true);
//                      criteria.add(
//                              Restrictions.and(
//                                      Restrictions.ge("DateCreated",fromDate),
//                                      Restrictions.le("DateCreated",toDate),
//                                      Restrictions.isNull("lead")
//                              )
//                      );
//                      criteria.add(Restrictions.like("TrackStatus", "did not speak",MatchMode.ANYWHERE));
//                      criteria.add(Restrictions.like("CallType", "incomplete",MatchMode.ANYWHERE));
//                      criteria.setProjection(Projections.rowCount());
//                      size = (Long) criteria.uniqueResult();
//             break;
//            case "Spoke":
//                    session = sessionFactory.getCurrentSession();
//                      criteria = session.createCriteria(Call.class, "call");
//                      criteria.setReadOnly(true);
//                      criteria.add(
//                              Restrictions.and(
//                                      Restrictions.ge("DateCreated",fromDate),
//                                      Restrictions.le("DateCreated",toDate),
//                                      Restrictions.isNull("lead")
//                              )
//                      );
//                      criteria.add(Restrictions.like("TrackStatus", "spoke",MatchMode.ANYWHERE));
//                      criteria.setProjection(Projections.rowCount());
//                      size = (Long) criteria.uniqueResult();
//     
//                       break;
//                
//        }
//        return size;
//    }
//       
//    
//    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
//    public Long fetchEntitySize(String date, String type) {
//        Long size = 0l;
//        switch(type){
//            case "Total":
//                
//                    Session session = sessionFactory.getCurrentSession();
//                      Criteria criteria = session.createCriteria(Call.class, "call");
//                      criteria.setReadOnly(true);
//                      criteria.add(Restrictions.like("DateCreated", date,MatchMode.START));
//                      criteria.setProjection(Projections.rowCount());
//                      size = (Long) criteria.uniqueResult();
//                      
//                    break;
//            case "Busy":
//                    session = sessionFactory.getCurrentSession();
//                      criteria = session.createCriteria(Call.class, "call");
//                      criteria.setReadOnly(true);
//                      criteria.add(Restrictions.like("DateCreated", date,MatchMode.START));
//                      criteria.add(Restrictions.like("Status", "busy",MatchMode.ANYWHERE));
//                      criteria.setProjection(Projections.rowCount());
//                      size = (Long) criteria.uniqueResult();
//                     
//                 break;
//            case "Failed":
//                    session = sessionFactory.getCurrentSession();
//                      criteria = session.createCriteria(Call.class, "call");
//                      criteria.setReadOnly(true);
//                      criteria.add(Restrictions.like("DateCreated", date,MatchMode.START));
//                      criteria.add(Restrictions.like("Status", "failed",MatchMode.ANYWHERE));
//                      criteria.setProjection(Projections.rowCount());
//                      size = (Long) criteria.uniqueResult();
//                 
//                 break;
//            case "NoAnswer":
//                    session = sessionFactory.getCurrentSession();
//                      criteria = session.createCriteria(Call.class, "call");
//                      criteria.setReadOnly(true);
//                      criteria.add(Restrictions.like("DateCreated", date,MatchMode.START));
//                      criteria.add(Restrictions.like("Status", "no-answer",MatchMode.ANYWHERE));
//                      criteria.setProjection(Projections.rowCount());
//                      size = (Long) criteria.uniqueResult();
//                      
//                 break;
//              case "RequestedCallBack":
//                    session = sessionFactory.getCurrentSession();
//                      criteria = session.createCriteria(Call.class, "call");
//                      criteria.setReadOnly(true);
//                      criteria.add(Restrictions.like("DateCreated", date,MatchMode.START));
//                      criteria.add(Restrictions.like("TrackStatus", "requested for callback",MatchMode.ANYWHERE));
//                      criteria.setProjection(Projections.rowCount());
//                      size = (Long) criteria.uniqueResult();
//
//                 break;
//            case "GreetingsHangUp":
//                    session = sessionFactory.getCurrentSession();
//                      criteria = session.createCriteria(Call.class, "call");
//                      criteria.setReadOnly(true);
//                      criteria.add(Restrictions.like("DateCreated", date,MatchMode.START));
//                      criteria.add(Restrictions.like("CallType", "trans",MatchMode.ANYWHERE));
//                      criteria.add(Restrictions.like("Status", "completed",MatchMode.ANYWHERE));
//                      criteria.add(Restrictions.isNull("TrackStatus"));
//                      criteria.setProjection(Projections.rowCount());
//                      size = (Long) criteria.uniqueResult();
//                      
//                       break;
//            case "HangUpOnConnect":
//                    session = sessionFactory.getCurrentSession();
//                      criteria = session.createCriteria(Call.class, "call");
//                      
//                      criteria.setReadOnly(true);
//                      criteria.add(Restrictions.like("DateCreated", date,MatchMode.START));
//                      criteria.add(Restrictions.like("TrackStatus", "did not speak",MatchMode.ANYWHERE));
//                      criteria.add(Restrictions.like("CallType", "client-hangup",MatchMode.ANYWHERE));
//                      criteria.setProjection(Projections.rowCount());
//                      size = (Long) criteria.uniqueResult();
//                      
//                  break;
//            case "MissCall": 
//                    session = sessionFactory.getCurrentSession();
//                      criteria = session.createCriteria(Call.class, "call");
//                      criteria.setReadOnly(true);
//                      criteria.add(Restrictions.like("DateCreated", date,MatchMode.START));
//                      criteria.add(Restrictions.like("TrackStatus", "did not speak",MatchMode.ANYWHERE));
//                      criteria.add(Restrictions.like("CallType", "incomplete",MatchMode.ANYWHERE));
//                      criteria.setProjection(Projections.rowCount());
//                      size = (Long) criteria.uniqueResult();
//             break;
//            case "Spoke":
//                    session = sessionFactory.getCurrentSession();
//                      criteria = session.createCriteria(Call.class, "call");
//                      criteria.setReadOnly(true);
//                      criteria.add(Restrictions.like("DateCreated", date,MatchMode.START));
//                      criteria.add(Restrictions.like("TrackStatus", "spoke",MatchMode.ANYWHERE));
//                      criteria.setProjection(Projections.rowCount());
//                      size = (Long) criteria.uniqueResult();
//     
//                       break;
//                
//        }
//        return size;
//    }
//       
//   
    
    
}
