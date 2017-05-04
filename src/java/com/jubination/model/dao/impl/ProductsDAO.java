/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.dao.impl;

import com.jubination.model.dao.plan.GenericDAOAbstract;
import com.jubination.model.pojo.products.Campaigns;
import com.jubination.model.pojo.products.Products;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MumbaiZone
 * @param <T>
 */
@Repository
public class ProductsDAO extends GenericDAOAbstract<Products,Campaigns>implements java.io.Serializable{

    public ProductsDAO() {
        setClassType(Products.class);
    }
    
    
   
    
}