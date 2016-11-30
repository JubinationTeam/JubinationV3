/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.service.products.thyrocare;

import com.jubination.model.pojo.products.thyrocare.ProductList;
import com.jubination.service.AdminMaintainService;
import com.jubination.service.ProductService;
import java.io.IOException;
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
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

/**
 *
 * @author MumbaiZone
 */
@Component
public class ThyrocareBookingOperator {
    @Autowired
    ProductService service;
    @Autowired
    AdminMaintainService admin;
    String settings = "settings";
    private final String fetchProducts="0 15 4-8 * * *"; 
    
     @Async
    @Scheduled(cron = fetchProducts)
     public void fetchProducts() throws IOException, ParserConfigurationException, SAXException, JAXBException{
         fetch();
     }

    public void fetch() throws IOException, ParserConfigurationException, SAXException, JAXBException {
//         String responseApi ="";
            String apiKey= admin.readSettings(settings).getApiKeyThyrocare();
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
//                    System.out.println(responseText);
//                    responseApi=pl.getRESPONSE();
//                     if(responseApi==null){
//                        responseApi ="";
//                        System.out.println(responseText);
//                    }
//                    count++;
//                }
           pl= service.buildProductList(pl);
           pl=null;
    }
}
