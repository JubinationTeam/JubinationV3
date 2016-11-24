/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.service;

import com.jubination.backend.ThyrocareBookingOperator;
import com.jubination.model.dao.AdminDAOImpl;
import com.jubination.model.dao.ProductsDAOImpl;
import com.jubination.model.pojo.AdminSettings;
import com.jubination.model.pojo.Campaigns;
import com.jubination.model.pojo.products.thyrocare.json.ProductList;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
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
   String settings = "settings";
    public boolean checkIfProductCheckedToday(){
        return pdao.readProperty(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))!=null;
    }
    public ProductList fetchAllProducts() throws IOException, ParserConfigurationException, SAXException, JAXBException{
        if(pdao.readProperty(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))!=null){
            return (ProductList) pdao.fetchEntity(new ProductList(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
        }
        else{
////            String responseApi ="";
            String apiKey= readSettings(settings).getApiKeyThyrocare();
            String url="https://www.thyrocare.com/APIS/master.svc/"+apiKey+"/ALL/products";
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);
            ProductList pl =null;
//            int count=0;
//            while(!responseApi.equals("SUCCESS")&&count<30){
//                    System.out.println("trying to fetch products");
                    HttpResponse response = client.execute(request);
                    HttpEntity entity = response.getEntity();
                    String responseText = EntityUtils.toString(entity, "UTF-8");
                    ObjectMapper mapper = new ObjectMapper();
                    pl = mapper.readValue(responseText, ProductList.class);
//                    responseApi=pl.getRESPONSE();
//                    if(responseApi==null){
//                        responseApi ="";
//                        System.out.println(responseText);
//                    }
//                    count++;
//                }
            pl=buildProductList(pl);
            pl=null;
            return (ProductList) pdao.fetchEntity(new ProductList(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
        }
    }
    public ProductList buildProductList(ProductList pl){
        pl.setDateId(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        return (ProductList) pdao.buildEntity(pl);
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
