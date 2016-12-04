/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.dao;

import com.jubination.model.pojo.admin.MailMessage;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
public class MessageDAOImpl<T> implements java.io.Serializable {
        private Session session=null;
    @Autowired
    private SessionFactory sessionFactory;

    public MessageDAOImpl() {
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    
    @Transactional(propagation = Propagation.REQUIRED)    
    public Object buildEntity(Object entity) {
      MailMessage message = (MailMessage) entity;
            session = getSessionFactory().getCurrentSession();        
            session.save(message);
            message = (MailMessage) session.get(MailMessage.class, message.getMessageId());
        return (T) message;
    }

}
