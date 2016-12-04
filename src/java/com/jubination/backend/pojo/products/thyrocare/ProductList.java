/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.pojo.products.thyrocare;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;


    @JsonIgnoreProperties(ignoreUnknown = true)
public class ProductList implements Serializable {

  
    String B2B_MASTERS;
    String RESPONSE;
    String RES_ID;
    String USER_TYPE;
    
@JsonManagedReference
    Master MASTERS;

    public ProductList() {
    }


    public String getB2B_MASTERS() {
        return B2B_MASTERS;
    }

    public void setB2B_MASTERS(String B2B_MASTERS) {
        this.B2B_MASTERS = B2B_MASTERS;
    }

    public String getRESPONSE() {
        return RESPONSE;
    }

    public void setRESPONSE(String RESPONSE) {
        this.RESPONSE = RESPONSE;
    }

    public String getRES_ID() {
        return RES_ID;
    }

    public void setRES_ID(String RES_ID) {
        this.RES_ID = RES_ID;
    }

    public String getUSER_TYPE() {
        return USER_TYPE;
    }

    public void setUSER_TYPE(String USER_TYPE) {
        this.USER_TYPE = USER_TYPE;
    }
    
    public Master getMASTERS() {
        return MASTERS;
    }

    public void setMASTERS(Master MASTERS) {
        this.MASTERS = MASTERS;
    }

 
    
}
