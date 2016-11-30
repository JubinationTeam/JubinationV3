package com.jubination.backend.pojo.report.thyrocare.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.stereotype.Component;
@Component
@XmlRootElement(name="ThyrocareResult")
public class ThyrocareReport {
    
                    private ThyrocareReport.Dates dates;
                   
                    public ThyrocareReport.Dates getDates() {
                                    return dates;
                    }

                   
                    @XmlElement(name = "DATES")
                    public void setDates(ThyrocareReport.Dates value) {
                                    this.dates = value;
                    }

                    public static class Dates {
                                      @XmlElement(name = "DATE")
                                    private List<ThyrocareDate> date;

                                    public List<ThyrocareDate> getDate() {
                                                if (date == null) {
                                                                date = new ArrayList<>();
                                                }
                                                return this.date;
                                    }


        

                           
                    }

  
    
    
}
