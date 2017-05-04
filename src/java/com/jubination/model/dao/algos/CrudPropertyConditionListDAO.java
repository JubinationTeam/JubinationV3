/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.dao.algos;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.EntityMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MumbaiZone
 * @param <T>
 * @param <K>
 */
@Repository
public class CrudPropertyConditionListDAO<T,K> implements CrudPropertyConditionListDAOInterface<Object,Object,SessionFactory,Class<K>> {
    private Class<K> type;
   private SessionFactory sessionFactory;
   
    
  
   @Override
    public void setOperator(SessionFactory operator){
        this.sessionFactory=operator;
    }

    @Override
    public SessionFactory getOperator() {
        return sessionFactory;
    }

    @Override
    public List<Object> fetchByNativeMax(String type) {
        List<Object> list=new ArrayList<>();
        
                   Session session = sessionFactory.getCurrentSession();
                   Criteria criteria =session.createCriteria(getMyType());
                   criteria =criteria.setProjection(Projections.projectionList().add(Projections.max(type)));
                   List<Object> tempList =criteria.list();
                    if(tempList.size()>0){
                    list =session.createCriteria(getMyType())
                           .add(Restrictions.like(type, tempList.get(0)))
                            .list();
                   }

               return  list;
    }

   @Override
   public Class<K> getMyType() {
         return this.type;
     }
   
    @Override
   public void setMyType(Class<K> type) {
          this.type = type;
     }

    @Override
    public Long countByNativeRange(String type, Object fromValue, Object toValue) {
         Session session = sessionFactory.getCurrentSession();
                     Criteria criteria = session.createCriteria(getMyType());
                      criteria.setReadOnly(true);
                             criteria.add(
                              Restrictions.and(
                                      Restrictions.ge(type,fromValue),
                                      Restrictions.le(type,toValue)
                                              
                              )
                      );
                      criteria.setProjection(Projections.rowCount());
                      return (Long) criteria.uniqueResult();
    }

    @Override
    public Long countByNative(String type, Object property, MatchMode m) {
         Session session = sessionFactory.getCurrentSession();
             Criteria criteria=session.createCriteria(getMyType());
            criteria.setReadOnly(true);
            criteria.add(Restrictions.ilike(type, (String) property, m)) .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
             criteria.setProjection(Projections.rowCount());
                      return (Long) criteria.uniqueResult();
            
    }

    @Override
    public Long countByNativeFilterByTwo(String type1, Object property1, MatchMode m1,String type2, Object property2,MatchMode m2) {
         Session session = sessionFactory.getCurrentSession();
                      Criteria criteria = session.createCriteria(getMyType());
                      criteria.setReadOnly(true);
                      criteria.add(Restrictions.like(type1, (String) property1,m1));
                      criteria.add(Restrictions.like(type2, (String) property2,m2));
                      criteria.setProjection(Projections.rowCount());
                      return (Long) criteria.uniqueResult();
    }

    @Override
    public Long countByRangeWithFilter(String type1, Object fromValue, Object toValue, String type2, Object property,MatchMode m) {
                    Session session = sessionFactory.getCurrentSession();
                      Criteria criteria = session.createCriteria(getMyType());
                      criteria.setReadOnly(true);
                       criteria.add(
                              Restrictions.and(
                                      Restrictions.ge(type1,fromValue),
                                      Restrictions.le(type1,toValue)
                              )
                      );
                      criteria.add(Restrictions.like(type2, (String) property,m));
                      criteria.setProjection(Projections.rowCount());
                      return (Long) criteria.uniqueResult();
    }

    @Override
    public List<Object> fetchByNativeFilterByTwo(String type1, Object property1, MatchMode m1, String type2, Object property2, MatchMode m2) {
               Session session = sessionFactory.getCurrentSession();
             Criteria criteria=session.createCriteria(getMyType());
            return criteria.add(Restrictions.ilike(type1, (String) property1, m1))
                    .add(Restrictions.ilike(type2, (String) property2, m2))
                    .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public List<Object> fetchByNativeRange(String type, Object fromValue, Object toValue) {
        Session session = sessionFactory.getCurrentSession();
                     Criteria criteria = session.createCriteria(getMyType());
                      criteria.setReadOnly(true);
                             criteria.add(
                              Restrictions.and(
                                      Restrictions.ge(type,fromValue),
                                      Restrictions.le(type,toValue)
                                              
                              )
                      );
                      return criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public List<Object> executeDetachedCriteria(DetachedCriteria dc) {
        Session session = sessionFactory.getCurrentSession();
        return dc.getExecutableCriteria(session).list();
    }

    @Override
    public Object readPropertyEagerly(Object paramId) {
        
            Session session = getOperator().getCurrentSession();
            Object obj =   session.get(getMyType(), (Serializable) paramId);
            ClassMetadata classMetadata=session.getSessionFactory().getClassMetadata(getClass());
                for(Object objInner:classMetadata.getPropertyValues((T)obj)){
                   if(objInner instanceof List){
                       List list=(List) objInner;
                       list.size();
                   }
                }
            return (T)obj;
    }

    @Override
    public List<Object> fetchByNativeFilterByGreaterThanOrEqual(String type, Object property) {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(getMyType()).add(Restrictions.ge(type, property)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
        
    }

    @Override
    public List<Object> fetchByNativeFilterByLessThanOrEqual(String type, Object property) {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(getMyType()).add(Restrictions.le(type, property)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
        
    }

    @Override
    public List<Object> fetchByInnerNative(String type,String innerType, Object innerProperty,MatchMode m) {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(getMyType()).createAlias(type, "innerVal").add(Restrictions.like("innerVal."+innerType, (String) innerProperty, m)).list();
    }

    
   
   

}
