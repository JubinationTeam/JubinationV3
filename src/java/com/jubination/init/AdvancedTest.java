package com.jubination.init;





import com.jubination.model.dao.AdminDAOImpl;
import com.jubination.model.dao.CallAPIMessageDAOImpl;
import com.jubination.model.dao.ClientDAOImpl;
import com.jubination.model.dao.DataAnalyticsDAOImpl;
import com.jubination.model.dao.MessageDAOImpl;
import com.jubination.model.dao.ReportDAOImpl;
import com.jubination.model.pojo.admin.Admin;


/**
 *
 * @author Welcome
 */
public class AdvancedTest {
   
    public static void main(String[] args) {
       
        MessageDAOImpl mdao = new MessageDAOImpl();
        AdminDAOImpl adao = new AdminDAOImpl();
         CallAPIMessageDAOImpl callDao =new CallAPIMessageDAOImpl();
         ClientDAOImpl cDao = new ClientDAOImpl();
         DataAnalyticsDAOImpl daDao = new DataAnalyticsDAOImpl();
        ReportDAOImpl reportDao =new ReportDAOImpl();
        mdao.setSessionFactory(HibernateUtil.getSessionFactory());
        adao.setSessionFactory(HibernateUtil.getSessionFactory());
        callDao.setSessionFactory(HibernateUtil.getSessionFactory());
        reportDao.setSessionFactory(HibernateUtil.getSessionFactory());
        cDao.setSessionFactory(HibernateUtil.getSessionFactory());
        daDao.setSessionFactory(HibernateUtil.getSessionFactory());
        adao.buildInitEntity(new Admin("support@jubination.com","abcdef","ROLE_ADMINISTRATOR","Support",0,"Administrator"));
          

        System.err.println("Constructed:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        
     
        
        
    }
}
