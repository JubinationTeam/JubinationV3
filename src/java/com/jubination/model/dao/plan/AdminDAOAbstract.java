/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.dao.plan;

import com.jubination.model.dao.algos.CrudPropertyDAOInterface;
import com.jubination.model.pojo.admin.Admin;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MumbaiZone
 */
@Repository
public abstract class AdminDAOAbstract {
    
    @Autowired
    protected SessionFactory sessionFactory;
    
    @Autowired
    @Qualifier("crudPropertyDAO")
    protected CrudPropertyDAOInterface property;

    
    public abstract Admin buildEntity(Admin entity);
    public abstract Admin readProperty(String paramId);
    public abstract boolean updateProperty(Admin entity);
    public abstract boolean deleteEntity(Admin entity);
     
     public List fetchEntities(int power) {
            List list=null;
            Session session = sessionFactory.getCurrentSession();
            Criteria criteria=session.createCriteria(Admin.class,"admin");
            criteria.addOrder(Order.asc("admin.name"));
            criteria.addOrder(Order.asc("admin.power"));
            list=criteria.list();
            return list;
        
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
