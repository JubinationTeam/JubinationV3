/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.pojo.report.thyrocare.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.stereotype.Component;

/**
 *
 * @author MumbaiZone
 */
@Component
@XmlRootElement(name="DATE")
public class ThyrocareDate {
                    private ThyrocareDate.Orders orders;
            

                 
                 
                    public ThyrocareDate.Orders getOrders() {
                                    return orders;
                    }

                    @XmlElement(name = "ORDERS")
                    public void setOrders(ThyrocareDate.Orders value) {
                                    this.orders = value;
                    }

                    public static class Orders {

                                    @XmlElement(name = "ORDERNO")
                                    private List<ThyrocareOrder> order;
      
                                    public List<ThyrocareOrder> getOrder() {
                                                if ( order== null) {
                                                                order = new ArrayList<>();
                                                }
                                                return this.order;
                                    }

                                  

                                    
                    }

}
