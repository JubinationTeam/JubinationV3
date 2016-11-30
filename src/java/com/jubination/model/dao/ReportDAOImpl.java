/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.dao;

import com.jubination.model.pojo.report.Barcode;
import com.jubination.model.pojo.ivr.exotel.Call;
import com.jubination.model.pojo.report.Report;
import com.jubination.model.pojo.report.Profile;
import com.jubination.model.pojo.report.Test;
import com.jubination.model.pojo.status.thyrocare.ReportStatus;
import java.util.ArrayList;
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
public class ReportDAOImpl<T> implements java.io.Serializable, GenericDAO{
private Session session=null;
    @Autowired
    private SessionFactory sessionFactory;


    @Override
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
            reportStatus = (ReportStatus) session.get(ReportStatus.class, reportStatus.getRES_ID());

        return (T) reportStatus;
    }

    @Override
    
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public Object fetchEntity(Object property) {
        Report report=(Report) property;
            session = getSessionFactory().getCurrentSession();
            report = (Report) session.get(Report.class, report.getReportId());
            report.getBarcodes().size();
            report.getProfileData().size();
            for(Profile profile:report.getProfileData()){
                profile.getTestData().size();
                for(Test test:profile.getTestData()){
                    test.getRangeValues().size();
                }
            }
        return (T) report;
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
