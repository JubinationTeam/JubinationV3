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
@XmlRootElement(name="ORDERNO")
public class ThyrocareOrder {
    private ThyrocareOrder.Leads leads;
   

                    public ThyrocareOrder.Leads getLeads() {
                                    return leads;
                    }

                    
                    
                    @XmlElement(name = "LEADS")
                    public void setLeads(ThyrocareOrder.Leads leads) {
                                    this.leads = leads;
                    }

                    public static class Leads {

                                    @XmlElement(name = "LEADDETAILS")
                                    private List<ThyrocareLead> lead;
                    

                  
                                    public List<ThyrocareLead> getLead() {
                                                if ( lead== null) {
                                                                lead = new ArrayList<>();
                                                }
                                                return this.lead;
                                    }

                                 

                                    
                    }
}
