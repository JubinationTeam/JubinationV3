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
            <title>Please sign in</title>
            
            <link href="<c:url value="/resources/css/images/logo.jpg" />" rel="shortcut icon">
            
             <link type="text/css" href="<c:url value="/resources/css/jquery-ui-1.10.4.css" />" rel="stylesheet">
              <link type="text/css" href="<c:url value="/resources/css/admin.css" />" rel="stylesheet">
             <link type="text/css" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />" rel="stylesheet">
          <script async type="text/javascript" src="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js" />"></script>
          
            <link type="text/css" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css" />" rel="stylesheet">            
            
         
             <script  type="text/javascript" src="<c:url value="//ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"/>"></script>
          
            
            
            
            <script type="text/javascript" src="<c:url value="//cdnjs.cloudflare.com/ajax/libs/bootbox.js/4.3.0/bootbox.min.js" />"></script>
           
           <%--  
           
           <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.js"></script>
           <script type="text/javascript" src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.js"></script>
           <link type="text/css" rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.css">
            
           
           
          
            <script type="text/javascript" src="<c:url value="/resources/js/doc.js" />"></script> 
            <script type="text/javascript" src="<c:url value="/resources/js/respond.js" />"></script>
                 --%>
       
           
            
    </head>
    <body>
        <br/><br/>
        <div class="container">
            <div class="container glass" >
          
                <form id="admin-login-form" class="form-signin" action="${pageContext.request.contextPath}/admin/j_spring_security_check" method="post" name="f" >                                                               
        <h2 class="form-signin-heading" ><span style="color:#0081c2"class="glyphicon glyphicon-flash"></span>&nbsp;Call Bot V3</h2>
        <input type="text" class="form-control" name='j_username' placeholder="User Id" required autofocus>
        <input type="password" class="form-control" name='j_password'  placeholder="Password" required>
        <label class="checkbox" >
          <input type="checkbox" value="remember-me" name="_spring_security_remember_me"> Remember me
        </label>
        <button class="btn btn-lg btn-block" style="background-color:#0081c2;color:#ffffff" type="submit">Sign in</button>
      </form>
          <br/>
        <p style="color:#9b9b9b"><span  class="glyphicon glyphicon-flash"></span>Faster  - <span class="glyphicon glyphicon-leaf"></span>Lightweight  - <span  class="glyphicon glyphicon-cog"></span>Deca-Core</p>
        <br/>
            </div>
    </div> <!-- /container -->
       
    </body>
</html>
