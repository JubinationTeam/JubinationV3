/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jubination.model.dao.impl;

import com.jubination.model.dao.plan.AdminDAOAbstract;
import com.jubination.model.pojo.admin.Admin;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Welcome
 */
@Repository
public class AdminDAO extends AdminDAOAbstract implements java.io.Serializable{
 
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Admin buildEntity(Admin entity) {
        property.setMyType(Admin.class);
            property.setOperator(sessionFactory);
        return (Admin) property.buildEntity(entity);
    }
    
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    @Override
    public Admin readProperty(String paramId) {
        property.setMyType(Admin.class);
            property.setOperator(sessionFactory);
     return (Admin) property.readProperty(paramId);
    }
@Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean updateProperty(Admin entity) {
        property.setMyType(Admin.class);
            property.setOperator(sessionFactory);
     return property.updateProperty(entity);
    }
 @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deleteEntity(Admin entity) {
        property.setMyType(Admin.class);
            property.setOperator(sessionFactory);
            return property.deleteEntity(entity);
    }

    
}
