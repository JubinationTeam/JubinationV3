/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jubination.model.dao.impl;

import com.jubination.model.dao.plan.GenericDAOAbstract;
import com.jubination.model.pojo.admin.Admin;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Welcome
 */
@Repository
public class AdminDAO  extends GenericDAOAbstract implements java.io.Serializable{

    public AdminDAO() {
        setClassType(Admin.class);
    }
 
   
    
    
//Hibernate code
    public Object buildInitEntity(Object entity,SessionFactory sessionFactory) {
   Admin admin = (Admin) entity;
        Session session=null; 
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(admin);
            admin = (Admin) session.get(Admin.class, session.getSessionFactory().getClassMetadata(Admin.class).getIdentifier(admin, (SessionImplementor)session));
            session.getTransaction().commit();
        }
        catch(HibernateException e){
            if(session!=null){
                session.getTransaction().rollback();
                System.out.println("Error in building Admin and its properties at AdminDAO "+e);
                e.printStackTrace();
                admin=null;
            }
        }
        return  admin;

    }
    /////////////////////
    
}
