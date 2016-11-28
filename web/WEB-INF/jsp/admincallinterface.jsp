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
            
            <title>Real Time Calling</title>
             <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
               <link type="text/css" href="<c:url value="/resources/css/jquery-ui-1.10.4.css" />" rel="stylesheet">
            
             <link type="text/css" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />" rel="stylesheet">
          <script async type="text/javascript" src="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js" />"></script>
            
            <link type="text/css" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css" />" rel="stylesheet">            
            
         
             <script  type="text/javascript" src="<c:url value="//ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"/>"></script>
          
            <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
            
              <script async type="text/javascript" src="<c:url value="/resources/js/adminmail.js" />"></script>
          
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
                     <script src="<c:url value="/resources/js/admincallinterface.js" />" type="text/javascript"></script>
           
           
    </head>
    <body>
       <tiles:insertAttribute name="navigation"/>
       
       <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            
           <h2 class="jumbotron">Real Time Calling</h2>
           <div class="row" >
               
               <%--  <h3  style="margin-left: 50px">Customers who picked up the call today</h3>
                 <c:if test="${not empty clientStage}">
               <div class="col-sm-12 table-responsive">
                    <table style="border-radius: 5px"  class="table table-bordered table-striped">
                           <thead>
                            <th>ID</th>
                           <th>Name</th>
                           <th>Age</th>
                           <th>Gender</th>
                           <th>Campaign Name</th>
                            <th>Source</th>
                           <th>Email id</th>
                           <th>Number</th>
                           <th>Address</th>
                           <th>City</th>
                            <th>Pincode</th>
                            <th>Comments</th>
                            
                           </thead>
                           <tbody>
                               <c:forEach items="${clientStage}" var="client">
                       <tr>
                            <td><a class="btn btn-small btn-primary" href="${pageContext.request.contextPath}/admin/callupdates/values?leadId=${client.tempLeadDetails}"  target="_blank">${client.tempLeadDetails}</a></td>
                           <td>${client.name}</td>
                           <td>${client.age}</td>
                           <td>${client.gender}</td>
                           <td>${client.campaignName}</td>
                           <td>${client.source}</td>
                           <td>${client.emailId}</td>
                           <td>${client.phoneNumber}</td>
                           <td>${client.address}</td>
                           <td>${client.city}</td>
                           <td>${client.pincode}</td>
                           <td>${client.initialComments}</td>
                          </tr>
                   </c:forEach>
                           </tbody>
                    </table>
               </div>
                 </c:if>
                 <c:if test="${empty clientStage}">
                      <p  style="margin-left: 50px">None</p>
                 </c:if>
                  <hr/>
               
               
               --%>
               
               
               
               
               
               
               
            <%--
                <h3  style="margin-left: 50px">Clients to be called</h3>
                <c:if test="${not empty clientStage1}">
               <div class="col-sm-12 table-responsive">
                  
                   <table  class="table table-bordered table-striped">
                       
                           <thead>
                           <th>ID</th>
                           <th>Name</th>
                           <th>Age</th>
                           <th>Gender</th>
                           <th>Campaign Name</th>
                           <th>Email id</th>
                           <th>Number</th>
                           <th>Address</th>
                           <th>City</th>
                            <th>Pincode</th>
                            <th>Comments</th>
                           </thead>
                           <tbody>
                   <c:forEach items="${clientStage1}" var="client1">
                       <tr>
                            <td>${client1.tempLeadDetails}</td>
                           <td>${client1.name}</td>
                           <td>${client1.age}</td>
                           <td>${client1.gender}</td>
                           <td>${client1.campaignName}</td>
                           <td>${client1.emailId}</td>
                           <td>${client1.phoneNumber}</td>
                           <td>${client1.address}</td>
                           <td>${client1.city}</td>
                           <td>${client1.pincode}</td>
                           <td>${client1.initialComments}</td>
                       </tr>
                      
                   </c:forEach>
                        </tbody>
                        </table>
               </div>
                </c:if>
                <c:if test="${empty clientStage1}">
                    <p  style="margin-left: 50px">None</p>
                </c:if>
                    <hr/>--%>
                 <h3  style="margin-left: 50px">Now processing</h3>
                 <c:if test="${not empty clientStage2}">
               <div class="col-sm-12 table-responsive">
                    <table style="border-radius: 5px"  class="table table-bordered table-striped">
                           <thead>
                            <th>ID</th>
                           <th>Name</th>
                           <th>Speaking to</th>
                           <th>Age</th>
                           <th>Gender</th>
                           <th>Campaign Name</th>
                           <th>Source</th>
                           <th>Email id</th>
                           <th>Number</th>
                           <th>Address</th>
                            <th>Comments</th>
                           </thead>
                           <tbody>
                   <c:forEach items="${clientStage2}" var="client2">
                       <tr>
                           <td><a class="btn btn-small"  style="background-color:#0081c2;color:#ffffff" href="${pageContext.request.contextPath}/admin/callupdates/values?leadId=${client2.tempLeadDetails}"  target="_blank">${client2.tempLeadDetails}</a></td>
                           <td>${client2.name}</td>
                           <td>${client2.realTimeData}</td>
                           <td>${client2.age}</td>
                           <td>${client2.gender}</td>
                           <td>${client2.campaignName}</td>
                           <td>${client2.source}</td>
                           <td>${client2.emailId}</td>
                           <td>${client2.phoneNumber}</td>
                           <td>${client2.address} ${client2.city} ${client2.pincode}</td>
                           <td>${client2.initialComments}</td>
                           </tr>
                   </c:forEach>
                           </tbody>
                    </table>
               </div>
                 </c:if>
                 <c:if test="${empty clientStage2}">
                     <br/>
                      <p  style="margin-left: 50px">None</p>
                 </c:if>
                  <hr/>
                 <h3  style="margin-left: 50px">Calls to be updated</h3>
                 <br/>
                 <c:if test="${not empty callStage3}">
               <div class="col-sm-12 table-responsive">
                   <table style="border-radius: 5px"  class="table table-bordered table-striped">
                           <thead>
                           <th>To Number</th>
                           <th>From Number</th>
                           <th>Picked up by</th>
                           <th>Trac Status</th>
                           <th>RecordingUrl</th>
                           </thead>
                           <tbody>
                   <c:forEach items="${callStage3}" var="call">
                       <tr>
                           <td>${call.callTo}</td>
                           <td>${call.callFrom}</td>
                            <td>${call.dialWhomNumber}</td>
                            <td>${call.trackStatus}</td>
                            <td>
                                 <c:if test="${not empty call.recordingUrl}">
                                <a href="${call.recordingUrl}" target="_blank">Recording URL</a>
                                 </c:if>
                            </td>
                       </tr>
                   </c:forEach>
                                 </tbody>
                    </table>
               </div>
                 </c:if>
                 <c:if test="${empty callStage3}">
                     <p  style="margin-left: 50px">None</p>
                 </c:if>
              <hr/>
           </div>
         
           
           
           
       </div>
    </div>
</div>
    </body>
</html>



