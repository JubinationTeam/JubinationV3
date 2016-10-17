/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.report.thyrocare.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.stereotype.Component;

/**
 *
 * @author MumbaiZone
 */
@Component
@XmlRootElement(name="TEST")
public class ThyrocareTest {

    String sDate;
    String profileCode;
    String testCode;
    String testValue;
    String normalVal;
    String testGroup;
    String reportGroupId;
    String reportPrintOrder;
   String indicator;
   String units;
   String r3;
   String description;

    public String getsDate() {
        return sDate;
    }
@XmlElement(name = "SDATE")
    public void setsDate(String sDate) {
        this.sDate = sDate;
    }

    public String getProfileCode() {
        return profileCode;
    }
@XmlElement(name = "PROFILE_CODE")
    public void setProfileCode(String profileCode) {
        this.profileCode = profileCode;
    }

    public String getTestCode() {
        return testCode;
    }
@XmlElement(name = "TEST_CODE")
    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getTestValue() {
        return testValue;
    }
@XmlElement(name = "TEST_VALUE")
    public void setTestValue(String testValue) {
        this.testValue = testValue;
    }

    public String getNormalVal() {
        return normalVal;
    }
@XmlElement(name = "NORMAL_VAL")
    public void setNormalVal(String normalVal) {
        this.normalVal = normalVal;
    }

    public String getTestGroup() {
        return testGroup;
    }
@XmlElement(name = "TEST_GROUP")
    public void setTestGroup(String testGroup) {
        this.testGroup = testGroup;
    }

    public String getReportGroupId() {
        return reportGroupId;
    }
@XmlElement(name = "REPORT_GROUP_ID")
    public void setReportGroupId(String reportGroupId) {
        this.reportGroupId = reportGroupId;
    }

    public String getReportPrintOrder() {
        return reportPrintOrder;
    }
@XmlElement(name = "REPORT_PRINT_ORDER")
    public void setReportPrintOrder(String reportPrintOrder) {
        this.reportPrintOrder = reportPrintOrder;
    }

    public String getIndicator() {
        return indicator;
    }
@XmlElement(name = "INDICATOR")
    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public String getUnits() {
        return units;
    }
@XmlElement(name = "UNITS")
    public void setUnits(String units) {
        this.units = units;
    }

    public String getR3() {
        return r3;
    }
@XmlElement(name = "R3")
    public void setR3(String r3) {
        this.r3 = r3;
    }

    public String getDescription() {
        return description;
    }
@XmlElement(name = "Description")
    public void setDescription(String description) {
        this.description = description;
    }
   
   
   
   
}
