/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author MumbaiZone
 */
@Entity
@Table(name="barcode"
    ,catalog="jubination"
)
public class Barcode implements Serializable {
    @Id
      @GeneratedValue(strategy=GenerationType.AUTO)
    private long orderId;
    @Column(name="name")          
    String name;
    @Column(name="value")
    String value;
    @ManyToOne
    Report report;

    public Barcode() {
    }

    public Barcode(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    @Override
    public String toString() {
        return "Barcode{" + "name=" + name + ", value=" + value + '}';
    }
    
    
}
