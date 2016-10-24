/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.pojo.products.thyrocare.json;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author MumbaiZone
 */
@Entity
@Table(name="product_list", catalog="jubination")
    @JsonIgnoreProperties(ignoreUnknown = true)
public class ProductList implements Serializable {

    @Id
    private String dateId;
  @Column(name="B2B_MASTERS")
    String B2B_MASTERS;
  @Column(name="RESPONSE")
    String RESPONSE;
  @Column(name="RES_ID")
    String RES_ID;
  @Column(name="USER_TYPE")
    String USER_TYPE;
    
@JsonManagedReference
 @OneToOne(mappedBy="productList")
      @Cascade({CascadeType.PERSIST,CascadeType.DELETE,CascadeType.SAVE_UPDATE})
    Master MASTERS;

    public ProductList() {
    }

    public ProductList(String dateId) {
        this.dateId = dateId;
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

    public String getDateId() {
        return dateId;
    }

    public void setDateId(String dateId) {
        this.dateId = dateId;
    }

    @Override
    public String toString() {
        return "ProductList{" + "dateId=" + dateId + ", B2B_MASTERS=" + B2B_MASTERS + ", RESPONSE=" + RESPONSE + ", RES_ID=" + RES_ID + ", USER_TYPE=" + USER_TYPE + ", MASTERS=" + MASTERS + '}';
    }


   
    
}
