/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.dao;



import com.jubination.model.pojo.status.ReportStatus;
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
public class ReportDAOImpl<T> implements java.io.Serializable{
private Session session=null;
    @Autowired
    private SessionFactory sessionFactory;


    
    
    @Transactional(propagation = Propagation.REQUIRED)
    public Object buildReportStatus(Object entity) {
         ReportStatus reportStatus=(ReportStatus) entity;
            session = getSessionFactory().getCurrentSession();
            session.save(reportStatus);
            reportStatus = (ReportStatus) session.get(ReportStatus.class, reportStatus.getId());

        return (T) reportStatus;
    }

    
    
     
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
