package com.jubination.init;





import com.jubination.model.dao.impl.AdminDAO;
import com.jubination.model.dao.impl.CallAPIMessageDAO;
import com.jubination.model.dao.impl.ClientDAO;
import com.jubination.model.dao.impl.DataAnalyticsDAO;
import com.jubination.model.pojo.admin.Admin;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;


/**
 *
 * @author Welcome
 */
public class Init {
   
    public static void main(String[] args) {
       
       
         ClientDAO cDao = new ClientDAO();
        cDao.setSessionFactory(HibernateUtil.getSessionFactory());
        
        
         AdminDAO adao = new AdminDAO();
       Admin admin= new Admin("support@jubination.com","abcdef","ROLE_ADMINISTRATOR","Support",0,"Administrator");
         Md5PasswordEncoder encoder = new Md5PasswordEncoder();
            admin.setPassword(encoder.encodePassword(admin.getPassword(), null));
        adao.buildInitEntity(admin,HibernateUtil.getSessionFactory());
          

        System.err.println("Constructed:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        
     
        
        
    }
}
