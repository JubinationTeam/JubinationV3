package com.jubination.controller.web;



import com.jubination.backend.service.thyrocare.products.ProductFetcher;
import com.jubination.init.Init;
import com.jubination.model.pojo.products.Products;
import com.jubination.service.ProductService;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.xml.sax.SAXException;



@Controller
public class RedirectController {

    @Autowired
    ProductFetcher pf;
    
    @Autowired
    ProductService service;
    
    
    public RedirectController() {
    }
    
    @PostConstruct
    public void init()  {
        
        Init.main(null);
        try{
              for(Products p:pf.fetchWithoutSaving()){
                        service.buildProducts(p);
                    }
        }
        catch(IOException | JAXBException | ParserConfigurationException | SAXException e){
            e.printStackTrace();
        }
            
    }
    
}
