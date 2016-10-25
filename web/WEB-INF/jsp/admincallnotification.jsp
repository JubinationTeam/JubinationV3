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
            <title>Follow Up Calls</title>
            
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
             <div class="row" >
                <h3  style="margin-left: 50px">Clients Pending (${fn:length(lead)})</h3>
                <c:if test="${not empty lead}">
               <div class="col-sm-12 table-responsive">
                  
                   <table  class="table table-bordered table-striped">
                       
                           <thead>
                           <th>ID</th>
                           <th>Customer Details</th>
                           <th>Campaign Name & Source</th>
                           <th>Email id</th>
                           <th>Number</th>
                           <th>Address</th>
                            <th>Comments</th>
                            <th>Executive</th>
                            <th>Follow ups left</th>
                            <th>Date</th>
                            <th>Follow up Date</th>
                            <th>Booking Manually</th>
                            <th>Disapproved</th>
                           </thead>
                           <tbody>
                   <c:forEach items="${lead}" var="lead">
                       <tr>
                            <td><a class="btn btn-small"  style="background-color:#0081c2;color:#ffffff" href="${pageContext.request.contextPath}/admin/callupdates/values?leadId=${lead.leadId}"  target="_blank">${lead.leadId}</a></td>
                           <td>${lead.client.name}, ${lead.client.age}, ${lead.client.gender}</td>
                           <td>${lead.client.campaignName} - ${lead.client.source}</td>
                           <td>${lead.client.emailId}</td>
                           <td>${lead.client.phoneNumber}</td>
                           <td>${lead.client.address},${lead.client.city} - ${lead.client.pincode}</td>
                           <td>${lead.client.initialComments},${lead.comments}</td>
                           <td>${lead.admin.name}</td>
                           <td>${lead.count}</td>
                           <td>${lead.client.dateCreation}</td>
                           <td>${lead.followUpDate}</td>
                           <c:if test="${not lead.notification}">
                           <td><a href="${pageContext.request.contextPath}/admin/callnotification/on/${lead.leadId}"><button class="btn btn-sm" style="background-color:#0081c2;color:#ffffff" type="button">Booking Done</button></a></td>
                           <td><a href="${pageContext.request.contextPath}/admin/callnotification/off/${lead.leadId}"><button class="btn btn-sm" style="background-color:#ed5d0a;color:#ffffff" type="button">Disapproved</button></a></td>
                           </c:if>
                           <c:if test="${lead.notification}">
                           <td></td><td></td>
                           </c:if>
                       </tr>
                      
                   </c:forEach>
                        </tbody>
                        </table>
               </div>
                </c:if>
                <c:if test="${empty lead}">
                    <p  style="margin-left: 50px">None</p>
                </c:if>
                    <hr/>
             </div>
        </div>
    </div>
</div>
    </body>
</html>
