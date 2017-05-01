/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.dao.impl;



import com.jubination.model.dao.plan.GenericDAOAbstract;
import com.jubination.model.pojo.status.ReportStatus;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MumbaiZone
 */
@Repository
public class ReportDAO extends GenericDAOAbstract<ReportStatus,Object>implements java.io.Serializable{

    public ReportDAO() {
        setClassType(ReportStatus.class);
    }

    
    
}
