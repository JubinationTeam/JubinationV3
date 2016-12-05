/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.service;


import com.jubination.backend.service.products.thyrocare.ProductFetcher;
import com.jubination.model.dao.AdminDAOImpl;
import com.jubination.model.dao.ProductsDAOImpl;
import com.jubination.model.pojo.admin.AdminSettings;
import com.jubination.model.pojo.booking.Campaigns;
import com.jubination.model.pojo.booking.Products;
import java.io.IOException;
import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

/**
 *
 * @author MumbaiZone
 */
@Service
@Transactional
public class ProductService {
    
    @Autowired
    ProductsDAOImpl pdao;    
    @Autowired 
    AdminDAOImpl adao;
    @Autowired
    ProductFetcher operator;
    String settings = "settings";
    
   
    public List<Products> fetchAllProducts() throws IOException, ParserConfigurationException, SAXException, JAXBException{
        List<Products> list =pdao.fetchProductEntities();
        if(list!=null&&list.size()>0){
            System.out.println("No products saved");
            return list;
        }
        else{
            list=operator.fetchWithoutSaving();
            
            System.out.println("Saving products");
            for(Products p:list){
                
            System.out.println("Saving products");
                    buildProducts(p);
            }
            return list;
        }
    }
    
    public Products buildProducts(Products p){
        return (Products) pdao.buildEntity(p);
    }
    
    
    
    public AdminSettings readSettings(String settingsName){
        return (AdminSettings) adao.readSettingsProperty(settingsName);
    }

    
    public List<Campaigns> fetchAllCampaigns() {
        
            return (List<Campaigns>) pdao.fetchCampaignEntities();
    }
    
    public Boolean buildCampaign(Campaigns campaign){
        return pdao.buildCampaignEntity(campaign)!=null;
    }
    
    public Campaigns readCampaign(String name){
        return (Campaigns) pdao.readCampaignProperty(name);
    }
    
    public Boolean updateCampaign(Campaigns camp){
        return pdao.updateCampaignEntity(camp);
    }
}
