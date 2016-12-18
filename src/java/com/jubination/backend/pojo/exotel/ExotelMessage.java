package com.jubination.backend.pojo.exotel;

import com.jubination.model.pojo.exotel.Call;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.stereotype.Component;


@Component
@XmlRootElement(name="TwilioResponse")
public class ExotelMessage implements Serializable{ 
    
    Call successMessage;
    Call failureMessage;

    public Call getSuccessMessage() {
        return successMessage;
    }
    @XmlElement(name="Call")
    public void setSuccessMessage(Call successMessage) {
        this.successMessage = successMessage;
    }

    public Call getFailureMessage() {
        return failureMessage;
    }
    @XmlElement(name="RestException")
    public void setFailureMessage(Call failureMessage) {
        this.failureMessage = failureMessage;
    }

    
    
    
}
