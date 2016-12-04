/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.service.products.thyrocare;

import com.jubination.backend.pojo.products.thyrocare.ProductList;
import com.jubination.backend.pojo.products.thyrocare.TestEntity;
import com.jubination.model.pojo.booking.Products;
import com.jubination.service.AdminMaintainService;
import com.jubination.service.ProductService;
import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;


@Component
public class ProductFetcher {
        @Autowired
    ProductService service;
    @Autowired
    AdminMaintainService admin;
    String settings = "settings";
    
    
    public List<Products> fetchWithoutSaving() throws IOException, ParserConfigurationException, SAXException, JAXBException {
            String apiKey= admin.readSettings(settings).getApiKeyThyrocare();
            String url="https://www.thyrocare.com/APIS/master.svc/"+apiKey+"/ALL/products";
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            String responseText = EntityUtils.toString(entity, "UTF-8");
            ObjectMapper mapper = new ObjectMapper();
            List<Products> pList = new ArrayList<>();
            ProductList pl=mapper.readValue(responseText, ProductList.class);
            List<TestEntity> tList= new ArrayList<>();
            if(pl.getMASTERS()!=null){
                System.out.println("Masters not null");
                if(pl.getMASTERS().getTESTS()!=null){
                    System.out.println("Tests not null");
                    tList.addAll(pl.getMASTERS().getTESTS());
                }
//                    if(pl.getMASTERS().getOFFER()!=null){
//                    System.out.println("Offers not null");
//                    tList.addAll(pl.getMASTERS().getOFFER());
//                }
//                    if(pl.getMASTERS().getPOP()!=null){
//                    System.out.println("Pop not null");
//                    tList.addAll(pl.getMASTERS().getPOP());
//                }
//                    if(pl.getMASTERS().getPROFILE()!=null){
//                    System.out.println("Profile not null");
//                    tList.addAll(pl.getMASTERS().getPROFILE());
//                }
            }
                else{
                    
                System.out.println("Masters is null");
                }
            for(TestEntity te:tList){
                            Products p = new Products();
                            if(te.getTestnames()!=null){
                                p.setName(te.getTestnames()+" , "+te.getDisease_group()+" , "+te.getRate());
                           }
                           else{
                                p.setName(te.getName()+" , "+te.getDisease_group()+" , "+te.getRate());
                            }
                            
                            if(te.getTestnames()!=null){
                                p.setProducts(te.getTestnames());
                           }
                            else{
                                if(te.getCode().startsWith("P")&&te.getCode().length()==4||te.getCode().startsWith("PNL")||te.getCode().startsWith("ALR")){
                                    p.setProducts(te.getName());
                                 }
                                else{
                                     p.setProducts(te.getCode());
                                }
                            }
                            
                            
                               if(te.getCode().startsWith("PROJ")){
                                   p.setReportCode(te.getCode());
                               }         
                            
                               if(te.getRate()!=null){
                                  p.setRate(te.getRate().getOffer_rate()+te.getRate().getPay_amt());
                              }
                               
                               p.setServiceType("H");
                              
                               p.setHc(te.getHc());
                              
                              p.setMargin(te.getMargin());
            }
            tList=null;
            return pList;
    }
    
}
