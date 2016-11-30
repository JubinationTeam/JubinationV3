/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.pojo.products.thyrocare;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author MumbaiZone
 */
@Entity
@Table(name="master", catalog="jubination")
  @JsonIgnoreProperties(ignoreUnknown = true)
public class Master implements Serializable {
    
    @Id
      @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
     @JsonBackReference
    @OneToOne
     ProductList productList;
    
    
    @JsonManagedReference
 @OneToMany(mappedBy="master")
      @Cascade({CascadeType.PERSIST,CascadeType.DELETE,CascadeType.SAVE_UPDATE})
    List<TestEntity> OFFER = new ArrayList<>();
    @JsonManagedReference
 @OneToMany(mappedBy="master")
      @Cascade({CascadeType.PERSIST,CascadeType.DELETE,CascadeType.SAVE_UPDATE})
    List<TestEntity> POP= new ArrayList();
    @JsonManagedReference
 @OneToMany(mappedBy="master")
      @Cascade({CascadeType.PERSIST,CascadeType.DELETE,CascadeType.SAVE_UPDATE})
    List<TestEntity> PROFILE= new ArrayList<>();
    @JsonManagedReference
 @OneToMany(mappedBy="master")
      @Cascade({CascadeType.PERSIST,CascadeType.DELETE,CascadeType.SAVE_UPDATE})
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductList getProductList() {
        return productList;
    }

    public void setProductList(ProductList productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "Master{" + "id=" + id + ", productList=" + productList + ", OFFER=" + OFFER + ", POP=" + POP + ", PROFILE=" + PROFILE + ", TESTS=" + TESTS + '}';
    }


    
}
