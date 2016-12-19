/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jubination.backend.service.core.login;

import com.jubination.model.dao.AdminDAOImpl;
import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Welcome
 */
@Component
public class DefaultAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

     private AuthenticationSuccessHandler defaultHandler;
 

    public DefaultAuthenticationSuccessHandler() {
    }

    public DefaultAuthenticationSuccessHandler(AuthenticationSuccessHandler defaultHandler) {
        this.defaultHandler = defaultHandler;
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest hsr, HttpServletResponse hsr1, Authentication a) throws IOException, ServletException {
        System.out.println("LOGGED IN::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"); 
        defaultHandler.onAuthenticationSuccess(hsr, hsr1, a);   
         
    }
     
     
}
