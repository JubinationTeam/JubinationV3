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
 */
@Repository
public interface CrudPropertyConditionListDAOInterface<T,K,M,N> {
     public void setOperator(M operator);
    public M getOperator();
     public void setMyType(N type) ;
     public N getMyType();
     public List<T> fetchByNativeMax(String type);
      public List<T> fetchByNativeFilterByTwo(String type1, K property1,MatchMode m1 , String type2, K property2,MatchMode m2);
      public Long countByNativeFilterByTwo(String type1, K property1,MatchMode m1 , String type2, K property2,MatchMode m2);
      public Long countByNativeRange(String type,K fromValue, K toValue);
      public Long countByNative(String type, K property,MatchMode m);
      public Long countByRangeWithFilter(String type1, K fromValue, K toValue,String type2, K property,MatchMode m);
     
}
