<%-- 
    Document   : adminlogin
    Created on : 22 Sep, 2014, 9:58:51 PM
    Author     : Welcome
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <meta name="description" content="">
            <meta name="author" content="Souvik Das">
            <title>Call Settings</title>
             <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
            
               <link type="text/css" href="<c:url value="/resources/css/jquery-ui-1.10.4.css" />" rel="stylesheet">
            
             <link type="text/css" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />" rel="stylesheet">
          <script async type="text/javascript" src="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js" />"></script>
            
            <link type="text/css" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css" />" rel="stylesheet">            
            
         
             <script  type="text/javascript" src="<c:url value="//ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"/>"></script>
          
            <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
            
            
            <script async type="text/javascript" src="<c:url value="//cdnjs.cloudflare.com/ajax/libs/bootbox.js/4.3.0/bootbox.min.js" />"></script>
           
            <%--
           
           <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.js"></script>
           <script type="text/javascript" src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.js"></script>
           <link type="text/css" rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.css">
            --%>
            
           
           
           <%--
            <script type="text/javascript" src="<c:url value="/resources/js/doc.js" />"></script> 
            <script type="text/javascript" src="<c:url value="/resources/js/respond.js" />"></script>
                 --%>
            
           <link type="text/css" href="<c:url value="/resources/css/dashboard.css" />" rel="stylesheet">
                                    <script src="<c:url value="/resources/js/dashboard.js" />" type="text/javascript"></script>
            
    </head>
    <body>
        <tiles:insertAttribute name="navigation"/>
        
        <div  class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h2>Calling Settings</h2>
            <hr/>
             <div class="row">
                    <div style="border: #0077b3 1px;border-style:solid ;border-radius: 5px;margin: 5px 5px 5px 5px" class="col-xs-3">
                        <br/>
                        <b>To generate today's analytics report </b>
                        <br/>
                        <br/>
                             <a href="${pageContext.request.contextPath}/admin/callsettings/do/analytics"><button class="btn btn-sm"   style="background-color:#0081c2;color:#ffffff"  type="button">Do Analytics</button></a><br/><br/>
                    
                    </div>
                  
                     <div style="border: #0077b3 1px;border-style:solid ;border-radius: 5px;margin: 5px 5px 5px 5px" class="col-xs-3">
                    <h3>Auto Calling</h3>
                    <c:if test="${sFlag1}">
                        <h4><b>On</b></h4>
                        <a href="${pageContext.request.contextPath}/admin/callsettings/stage1/off"><button class="btn btn-sm" style="background-color:#ed5d0a;color:#ffffff" type="button">Switch off</button></a><br/><br/>
                    </c:if>
                   <c:if test="${!sFlag1}">
                        <h4><b>Off</b></h4>
                        <a href="${pageContext.request.contextPath}/admin/callsettings/stage1/on"><button class="btn btn-sm"   style="background-color:#0081c2;color:#ffffff"  type="button">Switch on</button></a><br/><br/>
                    </c:if>
                </div>
                               <div style="border: #0077b3 1px;border-style:solid ;border-radius: 5px;margin: 5px 5px 5px 5px" class="col-xs-3">
                         <h3>Total Call count</h3><br/><br/>
                         <h4><b>${followUpCount}</b></h4>
                    </div>
                </div>
                    <hr/>
          <%--  
            <div class="row">
                   <div style="border: #0077b3 1px;border-style:solid ;border-radius: 5px;margin: 5px 5px 5px 5px" class="col-xs-3">
                    <h3>Stage 2 status</h3><br/>
                    <c:if test="${sFlag2}">
                        <h4><b>On</b></h4>
                        <a href="${pageContext.request.contextPath}/admin/callsettings/stage2/off"><button class="btn btn-sm" style="background-color:#ed5d0a;color:#ffffff" type="button">Switch off</button></a><br/><br/>
                    </c:if>
                   <c:if test="${!sFlag2}">
                        <h4><b>Off</b></h4>
                        
                        <a href="${pageContext.request.contextPath}/admin/callsettings/stage2/on"><button class="btn btn-sm"  style="background-color:#0081c2;color:#ffffff"  type="button">Switch on</button></a><br/><br/>
                    </c:if>
                </div>
                <div style="border: #0077b3 1px;border-style:solid ;border-radius: 5px;margin: 5px 5px 5px 5px" class="col-xs-3">
                    <h3>Stage 3 status</h3><br/>
                    <c:if test="${sFlag3}">
                        <h4><b>On</b></h4>
                        
                        <a href="${pageContext.request.contextPath}/admin/callsettings/stage3/off"><button class="btn btn-sm" style="background-color:#ed5d0a;color:#ffffff" type="button">Switch off</button></a><br/><br/>
                    </c:if>
                   <c:if test="${!sFlag3}">
                        <h4><b>Off</b></h4>
                        
                        <a href="${pageContext.request.contextPath}/admin/callsettings/stage3/on"><button class="btn btn-sm"  style="background-color:#0081c2;color:#ffffff"  type="button">Switch on</button></a><br/><br/>
                    </c:if>
                </div>
            </div>--%>
                <div class="row">
                <div style="border: #0077b3 1px;border-style:solid ;border-radius: 5px;margin: 5px 5px 5px 5px" class="col-xs-3">
                    <h3>Calls left (Auto)</h3><br/>
                    <h4><b>${sCount1}</b></h4>
                    
                   <%--     <a href="${pageContext.request.contextPath}/admin/callsettings/stage1/flush"><button class="btn btn-sm" style="background-color:#ed5d0a;color:#ffffff" type="button">Flush</button></a><br/><br/>
              --%>  </div>
                <div style="border: #0077b3 1px;border-style:solid ;border-radius: 5px;margin: 5px 5px 5px 5px" class="col-xs-3">
                    <h3>In process (Auto)</h3><br/>
                    <h4><b>${sCount2}</b></h4>
                  <%--  
                        <a href="${pageContext.request.contextPath}/admin/callsettings/stage2/flush"><button class="btn btn-sm" style="background-color:#ed5d0a;color:#ffffff" type="button">Flush</button></a><br/><br/>
               --%> </div>
                <div style="border: #0077b3 1px;border-style:solid ;border-radius: 5px;margin: 5px 5px 5px 5px" class="col-xs-3">
                    <h3>To be updated</h3><br/>
                    <h4><b>${sCount3}</b></h4>
               <%--     
                        <a href="${pageContext.request.contextPath}/admin/callsettings/stage3/flush"><button class="btn btn-sm" style="background-color:#ed5d0a;color:#ffffff" type="button">Flush</button></a><br/><br/>
             --%>   </div>
             </div>
                <div class="row">
             <div style="border: #0077b3 1px;border-style:solid ;border-radius: 5px;margin: 5px 5px 5px 5px" class="col-xs-3">
                    <h3>Calls left (Custom)</h3><br/>
                   
                    <h4><b>${sCountCC1}</b> - ${cs1}</h4>
                    <%--
                        <a href="${pageContext.request.contextPath}/admin/callsettings/cc/stage1/flush"><button class="btn btn-sm" style="background-color:#ed5d0a;color:#ffffff" type="button">Flush</button></a><br/><br/>
              --%>  </div>
                <div style="border: #0077b3 1px;border-style:solid ;border-radius: 5px;margin: 5px 5px 5px 5px" class="col-xs-3">
                    <h3>In process (Custom)</h3><br/>
                    <h4><b>${sCountCC2}</b> - ${cs2}</h4>
                 <%--   
                        <a href="${pageContext.request.contextPath}/admin/callsettings/cc/stage2/flush"><button class="btn btn-sm" style="background-color:#ed5d0a;color:#ffffff" type="button">Flush</button></a><br/><br/>
             --%>   </div>
            </div>
                <hr/>
                <div class="row">
                    <div style="border: #0077b3 1px;border-style:solid ;border-radius: 5px;margin: 5px 5px 5px 5px" class="col-xs-2">
                         <h3>Followup switch</h3><br/>
                     <c:if test="${followupFlag}">
                        <h4><b>On</b></h4>
                        
                        <a href="${pageContext.request.contextPath}/admin/callsettings/followup/off"><button class="btn btn-sm" style="background-color:#ed5d0a;color:#ffffff" type="button">Switch off</button></a><br/><br/>
                    </c:if>
                   <c:if test="${!followupFlag}">
                        <h4><b>Off</b></h4>
                        
                        <a href="${pageContext.request.contextPath}/admin/callsettings/followup/on"><button class="btn btn-sm"  style="background-color:#0081c2;color:#ffffff"  type="button">Switch on</button></a><br/><br/>
                    </c:if>
                    </div>
                    </div>
                <hr/>
               
                
          <%--   <h2>Custom Calling</h2>
                   
                <div class="row">
                
               <div style="border: #0077b3 1px;border-style:solid ;border-radius: 5px;margin: 5px 5px 5px 5px" class="col-xs-3">
                    <h3>Stage 3 count</h3><br/>
                    <h4><b>${sCountCC3}</b></h4>
                    
                        <a href="${pageContext.request.contextPath}/admin/callsettings/cc/stage3/flush"><button class="btn btn-sm" style="background-color:#ed5d0a;color:#ffffff" type="button">Flush</button></a><br/><br/>
                </div>
            </div>
                <hr/>--%>
                
        </div>
    </div>
</div>
    </body>
</html>
