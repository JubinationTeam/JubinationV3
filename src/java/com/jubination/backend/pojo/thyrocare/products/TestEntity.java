/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.pojo.thyrocare.products;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;


    @JsonIgnoreProperties(ignoreUnknown = true)
public class TestEntity implements Serializable {

    String Additional_Tests;
    String New;
    String ben_max;
    String ben_min;
    String ben_multiple;
    String code;
    String disease_group;
    String edta; 
    String fasting; 
    String fluoride;
    String group_name;
    String hc; 
    String image_location;
    String margin;
    String name; 
    String normal_val;
    String pay_type;
    String serum; 
    String specimen_type;
    String test_count;
    String testnames; 
    String type; 
    String units;
    String urine; 
    String valid_to;
    String volume; 
        
     @JsonManagedReference
        Rate rate;
        
         @JsonBackReference
        Master master;
         
         @JsonManagedReference
     List<Childs> childs = new ArrayList<>(); 

    public String getAdditional_Tests() {
        return Additional_Tests;
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

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

     
     
}
