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
            <title>Product Display</title>
            
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
                <h3  style="margin-left: 50px">Thyrocare Products</h3>
                <c:if test="${not empty products}">
               <div class="col-sm-12 table-responsive">
                  
                   <table  class="table table-bordered table-striped">
                       
                           <thead>
                           <th>Test Details</th>
                           
                               <th>Name</th>
                           <th>Report Code</th>
                           <th>Service Type</th>
                           <th>HC</th>
                           <th>Rate</th>
                            
                            <th>Margin</th>
                           </thead>
                           <tbody>
                     <c:forEach items="${products.MASTERS.TESTS}" var="offer">
                       <tr>
                           <%--Test Details--%>
                           <c:if test="${not empty offer.testnames}">
                            <td>${offer.testnames} , ${offer.disease_group}</td>
                           </c:if>
                           <c:if test="${empty offer.testnames}">
                             <td>${offer.name} , ${offer.disease_group}</td>
                               
                           </c:if>
                             
                              <%--Name--%>
                             <c:if test="${not empty offer.testnames}">
                                    <td>${offer.testnames}</td>
                           </c:if>
                            <c:if test="${empty offer.testnames}">
                                <c:if test="${(fn:startsWith(offer.code, 'P') and fn:length(offer.code) eq 4) or fn:startsWith(offer.code, 'PNL') or fn:startsWith(offer.code, 'ALR')}">
                                    <td>${offer.name}</td>
                                </c:if>
                                    <c:if test="${not ((fn:startsWith(offer.code, 'P') and fn:length(offer.code) eq 4) or fn:startsWith(offer.code, 'PNL') or fn:startsWith(offer.code, 'ALR'))}">
                                    <td>${offer.code}</td>
                                </c:if>
                           </c:if>
                                    
                             <%--Report Code--%>
                             <c:if test="${fn:startsWith(offer.code, 'PROJ')}">
                            <td>${offer.code}</td>
                                </c:if>
                            <c:if test="${not fn:startsWith(offer.code, 'PROJ')}">
                            <td></td>
                                </c:if>
                             <%--Service type--%>
                             <td>H</td>
                             <%--Handling Charges--%>
                            <td>${offer.hc}</td> 
                             <%--Rate--%>
                           <td>${offer.rate.offer_rate} ${offer.rate.pay_amt}</td> 
                            <%--Margin--%>
                           <td>${offer.margin}</td> 
                           
                       </tr>
                    </c:forEach>   
                        </tbody>
                        </table>
               </div>
                </c:if>
                    <hr/>
             </div>
        </div>
    </div>
</div>
    </body>
</html>
