
package com.jubination.model.pojo.report;

import com.jubination.model.pojo.report.Profile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
@Entity
@Table(name="test"
    ,catalog="jubination"
)
public class Test implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="test_id")
    private long testId;
    @Column(name="name")
    private String name;
    @Column(name="value")
    private String value;
    @Column(name="reference_chunk",length=3000)
    private String referenceChunk;
    @Column(name="range_type")
    private String rangeType;
    @Column(name="units")
    private String units;
     @Column(name="warning")
    private Boolean warning;
    @Column(name="meaning")
    private String meaning;
    @ManyToOne
    Profile profile;
    
      @OneToMany(mappedBy="test")
//      @LazyCollection(LazyCollectionOption.FALSE)
//      @Lob
      @Cascade({CascadeType.PERSIST,CascadeType.DELETE,CascadeType.SAVE_UPDATE})
      @NotFound(action=NotFoundAction.IGNORE)
    private List<ReferenceRange> rangeValues=new ArrayList<>();

    public Test() {
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReferenceChunk() {
        return referenceChunk;
    }

    public void setReferenceChunk(String referenceChunk) {
        this.referenceChunk = referenceChunk;
    }

    public String getRangeType() {
        return rangeType;
    }

    public void setRangeType(String rangeType) {
        this.rangeType = rangeType;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public List<ReferenceRange> getRangeValues() {
        return rangeValues;
    }

    public void setRangeValues(List<ReferenceRange> rangeValues) {
        this.rangeValues = rangeValues;
    }


   

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getTestId() {
        return testId;
    }

    public void setTestId(long testId) {
        this.testId = testId;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Boolean getWarning() {
        return warning;
    }

    public void setWarning(Boolean warning) {
        this.warning = warning;
    }

  
    
}
