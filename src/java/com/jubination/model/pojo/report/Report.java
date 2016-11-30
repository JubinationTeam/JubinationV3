
package com.jubination.model.pojo.report;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
@Entity
 @Table(name="report", catalog="jubination")    
public class Report implements java.io.Serializable {
    @Id
    @Column(name="report_id")
    private String reportId;
    @Column(name="gender")    
    private String gender="";
    @Column(name="age")
        private String age="";
    @Column(name="name")
        private String name="";
    @Column(name="address")
        private String address="";
    @Column(name="sample_collected_date")
        private String sampleCollectedDate;
    @Column(name="test_count")
        private int testCount;
    @Column(name="page_count")
        private int pageCount;
    @Column(name="test_names")
        private String testNames;
    
        
        
      @OneToMany(mappedBy="report")
//      @LazyCollection(LazyCollectionOption.FALSE)
//      @Lob
      @Cascade({CascadeType.PERSIST,CascadeType.DELETE,CascadeType.SAVE_UPDATE})
      @NotFound(action=NotFoundAction.IGNORE)
        private List<Profile> profileData= new ArrayList<>();
      
      
        @OneToMany(mappedBy="report")
//        @LazyCollection(LazyCollectionOption.FALSE)
//        @Lob
      @Cascade({CascadeType.PERSIST,CascadeType.DELETE,CascadeType.SAVE_UPDATE})
      @NotFound(action=NotFoundAction.IGNORE)
        private List<Barcode> barcodes=new ArrayList<>();

    public Report() {
    }

    public Report(String reportId) {
        this.reportId = reportId;
    }
      

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSampleCollectedDate() {
        return sampleCollectedDate;
    }

    public void setSampleCollectedDate(String sampleCollectedDate) {
        this.sampleCollectedDate = sampleCollectedDate;
    }

    public int getTestCount() {
        return testCount;
    }

    public void setTestCount(int testCount) {
        this.testCount = testCount;
    }

    public List<Profile> getProfileData() {
        return profileData;
    }

    public void setProfileData(List<Profile> profileData) {
        this.profileData = profileData;
    }

   

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getTestNames() {
        return testNames;
    }

    public void setTestNames(String testNames) {
        this.testNames = testNames;
    }



    public List<Barcode> getBarcodes() {
        return barcodes;
    }

    public void setBarcodes(List<Barcode> barcodes) {
        this.barcodes = barcodes;
    }

   

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }
        
        
        
}
