package com.jubination.init;





import com.jubination.model.dao.impl.AdminDAO;
import com.jubination.model.dao.impl.CallAPIMessageDAO;
import com.jubination.model.dao.impl.ClientDAO;
import com.jubination.model.dao.impl.DataAnalyticsDAO;
import com.jubination.model.dao.impl.ReportDAO;
import com.jubination.model.pojo.admin.Admin;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;


/**
 *
 * @author Welcome
 */
public class Init {
   
    public static void main(String[] args) {
       
        AdminDAO adao = new AdminDAO();
         CallAPIMessageDAO callDao =new CallAPIMessageDAO();
         ClientDAO cDao = new ClientDAO();
         DataAnalyticsDAO daDao = new DataAnalyticsDAO();
        ReportDAO reportDao =new ReportDAO();
        callDao.setSessionFactory(HibernateUtil.getSessionFactory());
        reportDao.setSessionFactory(HibernateUtil.getSessionFactory());
        cDao.setSessionFactory(HibernateUtil.getSessionFactory());
        daDao.setSessionFactory(HibernateUtil.getSessionFactory());
       Admin admin= new Admin("support@jubination.com","abcdef","ROLE_ADMINISTRATOR","Support",0,"Administrator");
         Md5PasswordEncoder encoder = new Md5PasswordEncoder();
            admin.setPassword(encoder.encodePassword(admin.getPassword(), null));
        adao.buildInitEntity(admin,HibernateUtil.getSessionFactory());
          

        System.err.println("Constructed:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        
     
        
        
    }
}
