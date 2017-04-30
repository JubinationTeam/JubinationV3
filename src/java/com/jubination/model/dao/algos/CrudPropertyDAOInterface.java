/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.dao.algos;

import org.springframework.stereotype.Repository;


/**
 *
 * @author MumbaiZone
 * @param <T>
 * @param <K>
 * @param <M>
 * @param <N>
 */
@Repository
public interface CrudPropertyDAOInterface<T,K,M,N> {
    
    public void setOperator(M operator);
    public M getOperator();
    public T buildEntity(T entity);
    public T readProperty(K paramId) ;
    public boolean updateProperty(T entity);
     public boolean deleteEntity(T entity);
     public void setMyType(N type) ;
     public N getMyType();
}
