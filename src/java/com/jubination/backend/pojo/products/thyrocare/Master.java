/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.pojo.products.thyrocare;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;


    @JsonIgnoreProperties(ignoreUnknown = true)
public class Master implements Serializable {
    
   @JsonBackReference
     ProductList productList;
   
@JsonManagedReference 
    List<TestEntity> OFFER = new ArrayList<>();

@JsonManagedReference
    List<TestEntity> POP= new ArrayList();

@JsonManagedReference    
List<TestEntity> PROFILE= new ArrayList<>();

@JsonManagedReference
List<TestEntity> TESTS = new ArrayList<>();

    public List<TestEntity> getOFFER() {
        return OFFER;
    }

    public void setOFFER(List<TestEntity> OFFER) {
        this.OFFER = OFFER;
    }

    public List<TestEntity> getPOP() {
        return POP;
    }

    public void setPOP(List<TestEntity> POP) {
        this.POP = POP;
    }

    public List<TestEntity> getPROFILE() {
        return PROFILE;
    }

    public void setPROFILE(List<TestEntity> PROFILE) {
        this.PROFILE = PROFILE;
    }

    public List<TestEntity> getTESTS() {
        return TESTS;
    }

    public void setTESTS(List<TestEntity> TESTS) {
        this.TESTS = TESTS;
    }

    public ProductList getProductList() {
        return productList;
    }

    public void setProductList(ProductList productList) {
        this.productList = productList;
    }

    
}
