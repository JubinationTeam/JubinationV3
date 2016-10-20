
package com.jubination.model.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="reference_range"
    ,catalog="jubination"
)
public class ReferenceRange implements Serializable {
    @Id
      @GeneratedValue(strategy=GenerationType.AUTO)
    private long orderId;
    @Column(name="name")          
    String name;
    @Column(name="value",length=3000)
    String value;
    @ManyToOne
    Test test;

    public ReferenceRange() {
    }

    public ReferenceRange(String name, String value) {
        this.name = name;
        this.value = value.replace("–", "-").replace(":", "");
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
        this.value = value.replace("–", "-").replace(":", "");
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "ReferenceRange{" + "name=" + name + ", value=" + value + '}';
    }
    
    
}
