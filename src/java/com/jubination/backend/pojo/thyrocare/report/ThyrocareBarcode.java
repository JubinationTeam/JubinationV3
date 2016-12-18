/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.pojo.thyrocare.report;

import java.io.Serializable;
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
@XmlRootElement(name="BARCODE")
public class ThyrocareBarcode implements Serializable{
    private ThyrocareBarcode.Tests tests;
                   
                    public ThyrocareBarcode.Tests getTests() {
                                    return tests;
                    }

                   
                    @XmlElement(name = "TESTRESULTS")
                    public void setTests(ThyrocareBarcode.Tests tests) {
                                    this.tests = tests;
                    }

                    public static class Tests {
                                      @XmlElement(name = "TESTDETAIL")
                                    private List<ThyrocareTest> test;

                                    public List<ThyrocareTest> getTest() {
                                                if (test == null) {
                                                                test = new ArrayList<>();
                                                }
                                                return this.test;
                                    }


        

                           
                    }
}
