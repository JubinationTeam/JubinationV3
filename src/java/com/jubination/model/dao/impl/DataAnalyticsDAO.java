/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jubination.model.dao.impl;

import com.jubination.model.dao.plan.GenericDAOAbstract;
import com.jubination.model.pojo.crm.DataAnalytics;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Welcome
 */
@Repository
public class DataAnalyticsDAO extends GenericDAOAbstract<DataAnalytics, Object> implements java.io.Serializable {

    public DataAnalyticsDAO() {
        setClassType(DataAnalytics.class);
    }
  

//    @Transactional(propagation=Propagation.REQUIRED,readOnly = true)
//    public Object readPropertyByRecency() {
//       List<DataAnalytics> list=new ArrayList<>();
//        
//                   Session session = sessionFactory.getCurrentSession();
//                   Criteria criteria =session.createCriteria(DataAnalytics.class);
//                   criteria =criteria.setProjection(Projections.projectionList().add(Projections.max("requestedTime")));
//                   List<String> tempList =criteria.list();
//                                    
//                   
//                   if(tempList.size()>0){
//                    list =session.createCriteria(DataAnalytics.class)
//                           .add(Restrictions.like("requestedTime", tempList.get(0)))
//                            .list();
//                   }
//
//               return  list;
//        
//    }
   
   

}
