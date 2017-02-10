/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.service.core.leadcall.parallel.worker;

/**
 *
 * @author MumbaiZone
 */
public interface CallService <T>{
    
    public T makeCall(String callerId);
    public T checkStatus(String sid);
}
