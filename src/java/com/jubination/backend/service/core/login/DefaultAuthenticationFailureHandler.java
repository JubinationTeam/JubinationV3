/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jubination.backend.service.core.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author Welcome
 */
@Component
public class DefaultAuthenticationFailureHandler implements AuthenticationFailureHandler{
    
    private AuthenticationFailureHandler defaultHandler;

    public DefaultAuthenticationFailureHandler() {
    }

    public DefaultAuthenticationFailureHandler(AuthenticationFailureHandler defaultHandler) {
        this.defaultHandler = defaultHandler;
    }
    @Override
    public void onAuthenticationFailure(HttpServletRequest hsr, HttpServletResponse hsr1, AuthenticationException ae) throws IOException, ServletException {
       
            defaultHandler.onAuthenticationFailure(hsr, hsr1, ae);
        
    }
    
}
