/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.controller.api;

import com.jubination.backend.pojo.core.chatbot.ChatBotRequest;
import com.jubination.backend.pojo.core.chatbot.ChatBotResponse;
import com.jubination.backend.service.core.chatbot.ChatBotMaintainService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author MumbaiZone
 */
@Controller
public class ChatBotAPIController {
    
    
    @Autowired 
    ChatBotMaintainService service;
            
    
    @RequestMapping(value="/chatbot",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE,headers="Accept=*/*")
    public @ResponseBody ChatBotResponse getDump(@RequestBody ChatBotRequest cRequest,HttpServletRequest request) throws IOException{
            System.out.println("Chatbot request"+cRequest.getLastId());
            
        return service.getResponse(cRequest, request.getSession().getId());
            
        
    }
    
    
}
