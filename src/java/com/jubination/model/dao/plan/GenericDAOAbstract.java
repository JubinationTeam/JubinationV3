/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.dao.plan;

import com.jubination.model.dao.algos.CrudPropertyConditionListDAOInterface;
import com.jubination.model.dao.algos.CrudPropertyDAOInterface;
import com.jubination.model.dao.algos.CrudPropertyListDAOInterface;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MumbaiZone
 * @param <T>
 * @param <K>
 */
@Repository
public abstract class GenericDAOAbstract<T,K>  implements java.io.Serializable{
       @Autowired
    protected SessionFactory sessionFactory;
    
    @Autowired
    @Qualifier("crudPropertyDAO")
    protected CrudPropertyDAOInterface property;
    
    @Autowired
    @Qualifier("crudPropertyListDAO")
    protected CrudPropertyListDAOInterface propertyList;
         
    
    @Autowired
    @Qualifier("crudPropertyConditionListDAO")
    protected CrudPropertyConditionListDAOInterface propertyConditionList;
    
    private Class<T> classType;

    @Transactional(propagation = Propagation.MANDATORY)
    public T buildEntity(T entity) {
        property.setMyType(getClassType());
            property.setOperator(sessionFactory);
        return (T) property.buildEntity(entity);
    }
    
    @Transactional(propagation = Propagation.MANDATORY,readOnly = true)
    public T readProperty(Object paramId) {
        property.setMyType(getClassType());
            property.setOperator(sessionFactory);
     return (T) property.readProperty(paramId);
    }
@Transactional(propagation = Propagation.MANDATORY)
    public boolean updateProperty(T entity) {
        property.setMyType(getClassType());
            property.setOperator(sessionFactory);
     return property.updateProperty(entity);
    }
 @Transactional(propagation = Propagation.MANDATORY)
    public boolean deleteEntity(T entity) {
        property.setMyType(getClassType());
            property.setOperator(sessionFactory);
            return property.deleteEntity(entity);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public List<Object> fetchAll(String ascOrder, String descOrder)  {
        propertyList.setMyType(getClassType());
            propertyList.setOperator(sessionFactory);
            return propertyList.fetchAll(ascOrder,descOrder);
    }
    
     @Transactional(propagation = Propagation.MANDATORY,readOnly = true)
    public T readPropertyEagerly(Object paramId) {
        propertyConditionList.setMyType(getClassType());
            propertyConditionList.setOperator(sessionFactory);
     return (T) propertyConditionList.readPropertyEagerly(paramId);
    }
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Object>  fetchByUserDefined(Class<K> type, Object property,String descOrder,String ascOrder,MatchMode m)   {
        propertyList.setMyType(getClassType());
            propertyList.setOperator(sessionFactory);
            return propertyList.fetchByUserDefined(type, property, ascOrder, descOrder,m);
    }
    
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Object> fetchByNative(String type, Object property, String ascOrder, String descOrder,MatchMode m)  {
        propertyList.setMyType(getClassType());
            propertyList.setOperator(sessionFactory);
            return propertyList.fetchByNative(type, property, ascOrder, descOrder,m);
    }
    
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Object> fetchByNativeMax(String type)  {
        propertyConditionList.setMyType(getClassType());
            propertyConditionList.setOperator(sessionFactory);
            return propertyConditionList.fetchByNativeMax(type);
    }
     @Transactional(propagation = Propagation.MANDATORY)
    public List<Object> fetchByNativeFilterByTwo(String type1, K property1,MatchMode m1 , String type2, K property2,MatchMode m2) {
        propertyConditionList.setMyType(getClassType());
            propertyConditionList.setOperator(sessionFactory);
            return propertyConditionList.fetchByNativeFilterByTwo(type1,property1,m1 ,  type2, property2, m2);
    }
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Object> fetchByNativeRange(String type,Object fromValue, Object toValue) {
        propertyConditionList.setMyType(getClassType());
            propertyConditionList.setOperator(sessionFactory);
            return propertyConditionList.fetchByNativeRange(type,fromValue, toValue);
    }
      @Transactional(propagation = Propagation.MANDATORY)
    public Long countByNativeRange(String type,Object fromValue, Object toValue) {
        propertyConditionList.setMyType(getClassType());
            propertyConditionList.setOperator(sessionFactory);
            return propertyConditionList.countByNativeRange(type,fromValue, toValue);
    }
     @Transactional(propagation = Propagation.MANDATORY)
    public Long countByNative(String type, Object property,MatchMode m) {
        propertyConditionList.setMyType(getClassType());
            propertyConditionList.setOperator(sessionFactory);
            return propertyConditionList.countByNative(type, property,m);
    }
     @Transactional(propagation = Propagation.MANDATORY)
    public Long countByNativeFilterByTwo(String type1, K property1,MatchMode m1 , String type2, K property2,MatchMode m2) {
        propertyConditionList.setMyType(getClassType());
            propertyConditionList.setOperator(sessionFactory);
            return propertyConditionList.countByNativeFilterByTwo(type1,property1,m1 ,  type2, property2, m2);
    }
    @Transactional(propagation = Propagation.MANDATORY)
    public Long countByRangeWithFilter(String type1, Object fromValue, Object toValue, String type2, Object property,MatchMode m) {
        propertyConditionList.setMyType(getClassType());
            propertyConditionList.setOperator(sessionFactory);
            return propertyConditionList.countByRangeWithFilter( type1, fromValue, toValue, type2, property, m) ;
    }
    
      @Transactional(propagation = Propagation.MANDATORY)
    public List<Object> executeDetachedCriteria(DetachedCriteria dc) {
        propertyConditionList.setMyType(getClassType());
            propertyConditionList.setOperator(sessionFactory);
            return propertyConditionList.executeDetachedCriteria(dc);
    }
    
       @Transactional(propagation = Propagation.MANDATORY)
    public List<Object> fetchByNativeFilterByGreaterThanOrEqual(String type, K property) {
        propertyConditionList.setMyType(getClassType());
            propertyConditionList.setOperator(sessionFactory);
            return propertyConditionList.fetchByNativeFilterByGreaterThanOrEqual(type,property);
    }
    
      @Transactional(propagation = Propagation.MANDATORY)
    public List<Object> fetchByNativeFilterByLessThanOrEqual(String type, K property) {
        propertyConditionList.setMyType(getClassType());
            propertyConditionList.setOperator(sessionFactory);
            return propertyConditionList.fetchByNativeFilterByLessThanOrEqual(type,property);
    }
    
       @Transactional(propagation = Propagation.MANDATORY)
    public List<Object> fetchByInnerNative(String type,String innerType, Object innerProperty,MatchMode m) {
        propertyConditionList.setMyType(getClassType());
            propertyConditionList.setOperator(sessionFactory);
            return propertyConditionList.fetchByNativeFilterByLessThanOrEqual(type,property);
    }
    
    public Class<T> getClassType() {
        return classType;
    }

    public void setClassType(Class<T> classType) {
        this.classType = classType;
    }

   
     
}
