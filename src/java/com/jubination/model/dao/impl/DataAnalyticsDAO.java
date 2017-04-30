/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jubination.model.dao.impl;

import com.jubination.model.pojo.admin.Admin;
import com.jubination.model.pojo.crm.DataAnalytics;
import java.util.ArrayList;
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
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Welcome
 */
@Repository
public class DataAnalyticsDAO<T> implements java.io.Serializable {
    private Session session=null;
    @Autowired
    private SessionFactory sessionFactory;

    public DataAnalyticsDAO() {
    }
   
    
    @Transactional(propagation = Propagation.REQUIRED)
    public Object buildEntity(Object entity) {
         DataAnalytics da = (DataAnalytics) entity;
        
                    session = getSessionFactory().getCurrentSession();
            session.save(da);
            da = (DataAnalytics) session.get(DataAnalytics.class, da.getId());
            
        return (T) da;

    }


@Transactional(propagation=Propagation.REQUIRED,readOnly = true)
    public Object readPropertyByDate(Object paramId) {
       List<DataAnalytics> list=null;
        
                    session = getSessionFactory().getCurrentSession();
                   list =session.createCriteria(DataAnalytics.class)
                           .add(Restrictions.like("date", (String)paramId, MatchMode.START))
                            .list();
                   


               return (T) list;
        
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly = true)
    public Object readPropertyByRecency() {
       List<DataAnalytics> list=new ArrayList<>();
        
                    session = getSessionFactory().getCurrentSession();
                   List<String> tempList =session.createCriteria(DataAnalytics.class)
                                    .setProjection(Projections.projectionList()
                                        .add(Projections.max("requestedTime"))
                                        //.add(Projections.groupProperty("type"))
                                        ).list();
                   
                   if(tempList.size()>0){
                    list =session.createCriteria(DataAnalytics.class)
                           .add(Restrictions.like("requestedTime", tempList.get(0)))
                            .list();
                   }

               return (T) list;
        
    }
   
   
     public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
