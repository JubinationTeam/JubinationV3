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
            <title>Custom Calling</title>
            
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
           
                <div class="col-sm-5">
                    
                     <div class="col-xs-12">
                     <form action="${pageContext.request.contextPath}/admin/setExecs/custom" method="GET" class="form-signin-heading" >
                         <h3 class="form-signin-heading">Calling Executives</h3>
                         <input type="text" class="form-control"  name="ex" value="${ex}"/><br/>
                               <button type="submit" class="btn btn-sm"  style="background-color:#515151;color:#ffffff">Change</button>
                                </form><br/>
                                ${message}
                </div>
                <hr/>
                    <div id="compose" class="form-group" >
                       <form action="${pageContext.request.contextPath}/admin/callcustom/call/lead" method="post">
                         

                            <textarea name="numbers" class="form-control" rows="10" placeholder="Add numbers here...(Each in new line)" id="comment"></textarea>
                            </br>
                            <button type="submit" class="btn btn-sm"  style="background-color:#0081c2;color:#ffffff" id="button-submit">Lead Call</button>
                       </form>                       
                    </div>
                       <hr/>
              <%--       <div id="compose" class="form-group" >
                       <form action="${pageContext.request.contextPath}/admin/callcustom/call/cust" method="post">


                            <textarea name="numbers" class="form-control" rows="10" placeholder="Add numbers here...(Each in new line)" id="comment"></textarea>
                            </br>
                            <button type="submit" class="btn btn-lg btn-info" id="button-submit">Customer Call</button>
                       </form>                       
                    </div>--%>
                            
               </div>
                            
       </div>
                   
        </div>
    </div>
</div>
    </body>
</html>
