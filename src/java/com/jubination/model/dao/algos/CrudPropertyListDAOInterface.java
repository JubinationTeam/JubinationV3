/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.dao.algos;

import java.util.List;
import org.hibernate.criterion.MatchMode;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MumbaiZone
 * @param <T>
 * @param <K>
 * @param <M>
 * @param <N>
 * @param <O>
 */
@Repository
public interface CrudPropertyListDAOInterface<T,K,M,N,O> {
    
    public void setOperator(M operator);
    public M getOperator();
     public void setMyType(N type) ;
     public N getMyType();
     public List<T> fetchAll(String ascOrder,String descOrder);
     public boolean deleteAll();
     public List<T> fetchByNative(String type, K property,String ascOrder,String descOrder,MatchMode m);
     public List<T> fetchByUserDefined(O type, K property,String ascOrder,String descOrder,MatchMode m);
}
