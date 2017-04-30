/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jubination.service;

import com.jubination.model.dao.plan.AdminDAOAbstract;
import com.jubination.model.pojo.admin.Admin;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
@Service
@Transactional
public class AdminLoginService implements UserDetailsService {
     
    @Autowired
    @Qualifier("adminDAO")
    private AdminDAOAbstract adminDAO;   
    private Admin domainUser;
    
    
 
    @Override
    public UserDetails loadUserByUsername(String login)throws UsernameNotFoundException, DataAccessException {


           domainUser = (Admin) adminDAO.readProperty(login);
            try{
                domainUser.getUsername();
                domainUser.getPassword();
            }
            catch(Exception e){
                System.out.println("Error in Admin Login Service");
            }
         
         
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
 
        return new User(
                domainUser.getUsername(), 
                domainUser.getPassword(), 
                enabled, 
                accountNonExpired, 
                credentialsNonExpired, 
                accountNonLocked,
                getAuthorities()
        );
    }
     
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles());
        return authList;
    }
     
    public List<String> getRoles() {
 
        List<String> roles = new ArrayList<>();
        try{
         roles.add(domainUser.getRole());
        
        }
        catch(Exception e){
              System.out.println("Error in Admin fetching role");
        }
        
        return roles;
    }
     
    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
         
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
 
}