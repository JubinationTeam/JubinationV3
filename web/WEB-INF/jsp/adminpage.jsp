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
            <title>Home</title>
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
        
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="row">
                <div class="col-xs-3">
            <form class="form-signin" action="${pageContext.request.contextPath}/admin/callanalytics/get" >                                                               
                <h3 class="form-signin-heading">Get Report</h3>
                <input type="text" class="form-control"  name='date' placeholder="Date (yyyy-mm-dd)" required>
                <br/>
                <button class="btn btn-sm" style="background-color:#0081c2;color:#ffffff" type="submit">Fetch</button>
              </form>
                <br/>
                     <a href="${pageContext.request.contextPath}/admin/callanalytics/get/recent"><button class="btn btn-sm"   style="background-color:#515151;color:#ffffff"  type="button">Recent Analytics</button></a><br/><br/>
                  
                </div>
               
                <div class="col-xs-3">
                     <form action="${pageContext.request.contextPath}/admin/setExecs/auto" method="GET" class="form-signin-heading" >
                         <h3 class="form-signin-heading">Calling Executives</h3>
                         <input type="text" class="form-control"  name="ex" value="${ex}" required="true"/><br/>
                               <button type="submit" class="btn btn-sm" style="background-color:#515151;color:#ffffff">Change</button>
                                </form><br/>
                                ${message}
                </div>
                 
                
             
            </div>
               
                   <hr/>
                   <c:if test="${not empty analytics}">
                        <div class="row">
                             <div class="col-sm-12 table-responsive">

                          <table  class="table table-bordered table-striped">

                                  <thead>
                                  <th>Type</th>
                                   <th>On Date</th>
                                   <th>From Date</th>
                                   <th>To Date</th>
                                   <th>Requested Time</th>
                                  <th>Total</th>
                                  <th>Busy</th>
                                  <th>Failed</th>
                                  <th>No Answer</th>
                                  <th>Client hangs up while greeting</th>
                                   <th>Client hangs up while connecting</th>
                                   <th>MissCall</th>
                                   <th>Spoke</th>
                                   <th>Requested Call Back</th>
                                   <th>Others</th>
                                  </thead>
                                  <tbody>
                          <c:forEach items="${analytics}" var="item">
                              <tr>
                                   <td>${item.type}</td>
                                  <td>${item.date}</td>
                                  <td>${item.fromDate}</td>
                                  <td>${item.toDate}</td>
                                  <td>${item.requestedTime}</td>
                                  <td>${item.total}</td>
                                  <td>${item.busy}</td>
                                  <td>${item.failed}</td>
                                  <td>${item.noAnswer}</td>
                                  <td>${item.greetingsHangup}</td>
                                  <td>${item.hangupOnConnect}</td>
                                  <td>${item.missCall}</td>
                                  <td>${item.spoke}</td>
                                  <td>${item.requestedCallBack}</td>
                                  <td>${item.others}</td>
                              </tr>

                          </c:forEach>
                               </tbody>
                               </table>
                      </div>
                        </div>
                 </c:if>
               
                   
                   
        </div>
    </div>
</div>
    </body>
</html>
