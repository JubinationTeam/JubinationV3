/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.dao;


import com.jubination.model.pojo.report.Report;
import com.jubination.model.pojo.report.Profile;
import com.jubination.model.pojo.report.Test;
import com.jubination.model.pojo.status.ReportStatus;
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
public class ReportDAOImpl<T> implements java.io.Serializable{
private Session session=null;
    @Autowired
    private SessionFactory sessionFactory;


    
    @Transactional(propagation = Propagation.REQUIRED)
    public Object buildEntity(Object entity) {
         Report report=(Report) entity;
            session = getSessionFactory().getCurrentSession();
            Report reportTemp = (Report) session.get(Report.class, report.getReportId());
            if(reportTemp!=null){
                session.delete(reportTemp);
            }
            session.save(report);
        
        return (T) report;
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public Object buildReportStatus(Object entity) {
         ReportStatus reportStatus=(ReportStatus) entity;
            session = getSessionFactory().getCurrentSession();
            session.save(reportStatus);
            reportStatus = (ReportStatus) session.get(ReportStatus.class, reportStatus.getId());

        return (T) reportStatus;
    }

    
    
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public Object fetchEntity(Object property) {
        Report report=(Report) property;
            session = getSessionFactory().getCurrentSession();
            report = (Report) session.get(Report.class, report.getReportId());
            if(report!=null){
                if(report.getBarcodes()!=null){
                    report.getBarcodes().size();
                }
                if(report.getProfileData()!=null){
                    report.getProfileData().size();
                }
                for(Profile profile:report.getProfileData()){
                    profile.getTestData().size();
                    for(Test test:profile.getTestData()){
                        test.getRangeValues().size();
                    }
                }
            }
        return (T) report;
    }

    
     
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
