/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.service.thyrocare.products;


import com.jubination.model.pojo.products.Products;
import com.jubination.service.ProductService;
import java.io.IOException;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
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
public class ThyrocareProductOperator {
@Autowired
    ProductService service;
@Autowired
ProductFetcher fetcher;
    private final String fetchProducts="0 15 4-8 * * *"; 
    
     @Async
    @Scheduled(cron = fetchProducts)
     public void fetchProducts() throws IOException, ParserConfigurationException, SAXException, JAXBException{
         fetch();
     }

    private void fetch() throws IOException, ParserConfigurationException, SAXException, JAXBException {
        for(Products p:fetcher.fetchWithoutSaving()){
            service.buildProducts(p);
        }
    }
    
    
    
}
