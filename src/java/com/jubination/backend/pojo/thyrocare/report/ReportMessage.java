/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.pojo.thyrocare.report;

/**
 *
 * @author MumbaiZone
 */
public class ReportMessage {
    String reportUrl;
    String reportXml;
    String body;
    String reportId;

    public String getReportUrl() {
        return reportUrl;
    }

    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }

    public String getReportXml() {
        return reportXml;
    }

    public void setReportXml(String reportXml) {
        this.reportXml = reportXml;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }
    
    
}
