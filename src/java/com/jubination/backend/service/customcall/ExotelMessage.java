/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.service.customcall;

import com.jubination.model.pojo.ivr.exotel.Call;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.stereotype.Component;

/**
 *
 * @author MumbaiZone
 */
@Component
@XmlRootElement(name="TwilioResponse")
public class ExotelMessage{ 
    
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
