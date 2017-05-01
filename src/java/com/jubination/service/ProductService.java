/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.service;


import com.jubination.backend.service.thyrocare.products.ProductFetcher;
import com.jubination.model.dao.plan.GenericDAOAbstract;
import com.jubination.model.pojo.admin.AdminSettings;
import com.jubination.model.pojo.products.Campaigns;
import com.jubination.model.pojo.products.Products;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("productsDAO")
    private GenericDAOAbstract pdao;  
     @Autowired
    @Qualifier("settingsDAO")
    private GenericDAOAbstract asdao;
     @Autowired
    @Qualifier("campaignsDAO")
    private GenericDAOAbstract cdao;
    @Autowired
    ProductFetcher operator;
    String settings = "settings";
    
   
    public List<Object> fetchAllProducts() throws IOException, ParserConfigurationException, SAXException, JAXBException{
        List<Object> list =pdao.fetchAll(null,null);
        if(list!=null&&list.size()>0){
            return list;
        }
        else{
            List<Products> listp=operator.fetchWithoutSaving();
            System.out.println("Saving products");
            for(Object p:listp){
                    buildProducts((Products)p);
            }
            return list;
        }
    }
    
    public Products buildProducts(Products p){
        return (Products) pdao.buildEntity(p);
    }
    
    
    
    public AdminSettings readSettings(String settingsName){
        return (AdminSettings) asdao.readProperty(settingsName);
    }

    
    public List<Campaigns> fetchAllCampaigns() {
        
            return (List<Campaigns>) cdao.fetchAll(null, null);
    }
    
    public Boolean buildCampaign(Campaigns campaign){
        return cdao.buildEntity(campaign)!=null;
    }
    
    public Campaigns readCampaign(String name){
        return (Campaigns) cdao.readProperty(name);
    }
    
    public Boolean updateCampaign(Campaigns camp){
        return cdao.updateProperty(camp);
    }

    public List<String> autoCompleteProducts(String productsTag) {
       List<String> listP= pdao.fetchByNative("name", productsTag, null, null,MatchMode.START);
       List<String> listC= cdao.fetchByNative("name", productsTag, null, null,MatchMode.START);
       List<String> list=new ArrayList<>();
       if(listC!=null){
            list.addAll(listC);
       }
       if(listP!=null){
                list.addAll(listP);
       }
     
       
       return list;
       
    }
}
