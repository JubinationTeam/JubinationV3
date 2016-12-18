/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.backend.service.backupserver;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 *
 * @author MumbaiZone
 */
@Component
public class ServerUpdater {
    
    
    
    
    
    public void forwardToBackupServer(HttpServletRequest request){
         String url = getTestURL(request);
            System.out.println("URL:::::::::::::::::::::; "+url);
//            CloseableHttpResponse response=null;
//                            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//                            DocumentBuilder builder;
//                            InputSource is;
//                            String responseText="NA";
//                    Document doc=null;
//        
//                                    //requesting exotel to initiate call
//                                    CloseableHttpClient httpclient = HttpClients.createDefault();
//                                    HttpGet httpGet = new HttpGet(getTestURL(request));
//                                    HttpResponse response = httpclient.execute(httpGet);
//                                    HttpEntity entity = response.getEntity();
//                                    responseText = EntityUtils.toString(entity, "UTF-8");
    }
    
public String getTestURL(HttpServletRequest req) {

    String scheme = req.getScheme();             // http
    String serverName = "54.149.67.96";     // hostname.com
    int serverPort = req.getServerPort();        // 80
    String contextPath = req.getContextPath();   // /mywebapp
    String servletPath = req.getServletPath();   // /servlet/MyServlet
    String pathInfo = req.getPathInfo();         // /a/b;c=123
    String queryString = req.getQueryString();          // d=789

    // Reconstruct original requesting URL
    StringBuilder url = new StringBuilder();
    url.append(scheme).append("://").append(serverName);

    if (serverPort != 80 && serverPort != 443) {
        url.append(":").append(serverPort);
    }

    url.append(contextPath).append(servletPath);

    if (pathInfo != null) {
        url.append(pathInfo);
    }
    if (queryString != null) {
        url.append("?").append(queryString);
    }
    return url.toString();
}
}
