/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.model.pojo;

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
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author MumbaiZone
 */
@Entity
@Table(name="profile"
    ,catalog="jubination"
)
public class Profile implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="profile_id")
    private long profileId;
     @Column(name="name")
    private String name;
      @Column(name="significance")
    private String significance;
      @Column(name="about")
    private String about;
      @Column(name="warning")
      private boolean warning;
    @ManyToOne
    Report report;
    
    @OneToMany(mappedBy="profile")
      @Cascade({CascadeType.PERSIST,CascadeType.DELETE,CascadeType.SAVE_UPDATE})
      @NotFound(action=NotFoundAction.IGNORE)
        private List<Test> testData= new ArrayList<>();

    public Profile() {
    }

    public Profile(String name) {
        this.name = name;
    }

    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSignificance() {
        return significance;
    }

    public void setSignificance(String significance) {
        this.significance = significance;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public List<Test> getTestData() {
        return testData;
    }

    public void setTestData(List<Test> testData) {
        this.testData = testData;
    }

    public boolean isWarning() {
        return warning;
    }

    public void setWarning(boolean warning) {
        this.warning = warning;
    }

  
    
    
}
