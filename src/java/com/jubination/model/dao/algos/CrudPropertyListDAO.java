/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.dao.algos;


import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MumbaiZone
 * @param <T>
 * @param <K>
 */
@Repository
public class CrudPropertyListDAO<T,K> implements CrudPropertyListDAOInterface<Object,Object,SessionFactory,Class<T>,Class<K>>{

  private Class<T> type;
   private SessionFactory sessionFactory;
   
   @Override
   public Class<T> getMyType() {
         return this.type;
     }
   
    @Override
   public void setMyType(Class<T> type) {
          this.type = type;
     }
   
  
   @Override
    public void setOperator(SessionFactory operator){
        this.sessionFactory=operator;
    }

    @Override
    public SessionFactory getOperator() {
        return sessionFactory;
    }

    @Override
    public List<Object> fetchAll(String ascOrder, String descOrder) {
            Session session = sessionFactory.getCurrentSession();
            Criteria criteria=session.createCriteria(getMyType());
            if(ascOrder!=null){
                    criteria.addOrder(Order.asc(ascOrder));
            }
            if(descOrder!=null){
                  criteria.addOrder(Order.asc(descOrder));
            }
            return criteria.list();
    }
    
    

    @Override
    public List<Object> fetchByUserDefined(Class<K> type, Object property,String descOrder,String ascOrder,MatchMode m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> fetchByNative(String type, Object property, String ascOrder, String descOrder,MatchMode m) {
            Session session = sessionFactory.getCurrentSession();
             Criteria criteria=session.createCriteria(getMyType());
            if(ascOrder!=null){
                    criteria.addOrder(Order.asc(ascOrder));
            }
            if(descOrder!=null){
                  criteria.addOrder(Order.asc(descOrder));
            }
            return criteria.add(Restrictions.ilike(type, (String) property, m))
                    .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public boolean deleteAll() {
            Session session = sessionFactory.getCurrentSession();
            List<Object> list=session.createCriteria(getMyType()).list();
            for(Object obj:list){
                session.delete(obj);
            }
            return true;
    
    }

    
}
