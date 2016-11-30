/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.pojo.products.thyrocare;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name="test_entity", catalog="jubination")
  @JsonIgnoreProperties(ignoreUnknown = true)
public class TestEntity implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(name="Additional_Tests")
    String Additional_Tests;
    @Column(name="New")
    String New;
    @Column(name="ben_max")
    String ben_max;
    @Column(name="ben_min")
    String ben_min;
    @Column(name="ben_multiple")
    String ben_multiple;
    @Column(name="code")
        String code;
    @Column(name="disease_group")
        String disease_group;
    @Column(name="edta") 
        String edta; 
    @Column(name="fasting")
        String fasting; 
    @Column(name="fluoride")
        String fluoride;
    @Column(name="group_name") 
        String group_name;
    @Column(name="hc") 
        String hc; 
    @Column(name="image_location")
        String image_location;
    @Column(name="margin")
        String margin;
    @Column(name="name")
        String name; 
    @Column(name="normal_val")
        String normal_val;
    @Column(name="pay_type")
        String pay_type;
    @Column(name="serum") 
        String serum; 
    @Column(name="specimen_type")
        String specimen_type;
    @Column(name="test_count") 
        String test_count;
    @Column(name="testnames")
        String testnames;
    @Column(name="type") 
        String type; 
    @Column(name="units")
        String units;
    @Column(name="urine")
        String urine; 
    @Column(name="valid_to")
        String valid_to;
    @Column(name="volume") 
        String volume; 
        
        @JsonManagedReference
        @OneToOne(mappedBy="entity")
      @Cascade({CascadeType.PERSIST,CascadeType.DELETE,CascadeType.SAVE_UPDATE})
        Rate rate;
        
         @JsonBackReference
        @ManyToOne
        Master master;
         
         @JsonManagedReference
 @OneToMany(mappedBy="entity")
      @Cascade({CascadeType.PERSIST,CascadeType.DELETE,CascadeType.SAVE_UPDATE})
     List<Childs> childs = new ArrayList<>(); 

    public String getAdditional_Tests() {
        return Additional_Tests;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public void setAdditional_Tests(String Additional_Tests) {
        this.Additional_Tests = Additional_Tests;
    }

    public String getNew() {
        return New;
    }

    public void setNew(String New) {
        this.New = New;
    }

    public String getBen_max() {
        return ben_max;
    }

    public void setBen_max(String ben_max) {
        this.ben_max = ben_max;
    }

    public String getBen_min() {
        return ben_min;
    }

    public void setBen_min(String ben_min) {
        this.ben_min = ben_min;
    }

    public String getBen_multiple() {
        return ben_multiple;
    }

    public void setBen_multiple(String ben_multiple) {
        this.ben_multiple = ben_multiple;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDisease_group() {
        return disease_group;
    }

    public void setDisease_group(String disease_group) {
        this.disease_group = disease_group;
    }

    public String getEdta() {
        return edta;
    }

    public void setEdta(String edta) {
        this.edta = edta;
    }

    public String getFasting() {
        return fasting;
    }

    public void setFasting(String fasting) {
        this.fasting = fasting;
    }

    public String getFluoride() {
        return fluoride;
    }

    public void setFluoride(String fluoride) {
        this.fluoride = fluoride;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getHc() {
        return hc;
    }

    public void setHc(String hc) {
        this.hc = hc;
    }

    public String getImage_location() {
        return image_location;
    }

    public void setImage_location(String image_location) {
        this.image_location = image_location;
    }


    public String getMargin() {
        return margin;
    }

    public void setMargin(String margin) {
        this.margin = margin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNormal_val() {
        return normal_val;
    }

    public void setNormal_val(String normal_val) {
        this.normal_val = normal_val;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public String getSerum() {
        return serum;
    }

    public void setSerum(String serum) {
        this.serum = serum;
    }

    public String getSpecimen_type() {
        return specimen_type;
    }

    public void setSpecimen_type(String specimen_type) {
        this.specimen_type = specimen_type;
    }

    public String getTest_count() {
        return test_count;
    }

    public void setTest_count(String test_count) {
        this.test_count = test_count;
    }

    public String getTestnames() {
        return testnames;
    }

    public void setTestnames(String testnames) {
        this.testnames = testnames;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getUrine() {
        return urine;
    }

    public void setUrine(String urine) {
        this.urine = urine;
    }

    public String getValid_to() {
        return valid_to;
    }

    public void setValid_to(String valid_to) {
        this.valid_to = valid_to;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public List<Childs> getChilds() {
        return childs;
    }

    public void setChilds(List<Childs> childs) {
        this.childs = childs;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "TestEntity{" + "id=" + id + ", Additional_Tests=" + Additional_Tests + ", New=" + New + ", ben_max=" + ben_max + ", ben_min=" + ben_min + ", ben_multiple=" + ben_multiple + ", code=" + code + ", disease_group=" + disease_group + ", edta=" + edta + ", fasting=" + fasting + ", fluoride=" + fluoride + ", group_name=" + group_name + ", hc=" + hc + ", image_location=" + image_location + ", margin=" + margin + ", name=" + name + ", normal_val=" + normal_val + ", pay_type=" + pay_type + ", serum=" + serum + ", specimen_type=" + specimen_type + ", test_count=" + test_count + ", testnames=" + testnames + ", type=" + type + ", units=" + units + ", urine=" + urine + ", valid_to=" + valid_to + ", volume=" + volume + ", rate=" + rate + ", master=" + master + ", childs=" + childs + '}';
    }

    public void setId(Long id) {
        this.id = id;
    }
     
     
}
