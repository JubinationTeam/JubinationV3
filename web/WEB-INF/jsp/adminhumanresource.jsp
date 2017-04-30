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
            <title>Human resources</title>
             <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
            
               <link type="text/css" href="<c:url value="/resources/css/jquery-ui-1.10.4.css" />" rel="stylesheet">
            
             <link type="text/css" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />" rel="stylesheet">
          <script async type="text/javascript" src="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js" />"></script>
            
            <link type="text/css" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css" />" rel="stylesheet">            
            
         
             <script  type="text/javascript" src="<c:url value="//ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"/>"></script>
          
            <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
            
            
            <script async type="text/javascript" src="<c:url value="//cdnjs.cloudflare.com/ajax/libs/bootbox.js/4.3.0/bootbox.min.js" />"></script>
           
            <script async type="text/javascript" src="<c:url value="/resources/js/adminhr.js" />"></script>
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
        <br/>
    <center>
       <a class="btn btn-small" href="${pageContext.request.contextPath}/admin/hr" >Refresh</a>
        <br/> 
   <%--    <a class="btn btn-info btn-mini" id="assign-employee-link" href="#make-employee">Assign Employee</a>--%>
<br/> 
     </center>           
    
       <br/> 
        <table class="table table-hover">
              <thead>
                <tr>
                  <th>Username/Email Id</th>
                  <th>Name</th>
                  
                  <th>Role</th>
                   <c:if test="${admin.work=='CEO'}">  
                    <th>Power</th>
                  </c:if>
                </tr>
              </thead>
              <tbody>
                    <c:forEach items="${admin_list}" var="entity">
                    
                    <tr>
                <td>
                                 <c:if test="${not empty entity.username}"/>
                                 <p  class="view-${entity.username}"><c:out value="${entity.username}"/></p>
                
                </td>
                 <td>
                                 <c:if test="${not empty entity.name}"/>
                                 <p  class="view-${entity.name}"><c:out value="${entity.name}"/></p>
                                 
                 </td>
                <td>
                                <c:if test="${not empty entity.work}"/>
                                 <p  class="view-${entity.work}"><c:out value="${entity.work}"/></p>
                                 
                                 
                </td>
                  <td>
                                <c:if test="${not empty entity.number}"/>
                                 <p  class="view-${entity.number}"><c:out value="${entity.number}"/></p>
                                 
                                 
                </td>
                <td>
                    <c:if test="${not empty entity.power}">  
                    <c:if test="${admin.work=='CEO'}">  
                                 <p  class="view-${entity.power}"><c:out value="${entity.power}"/></p>
                    </c:if>
                    </c:if>
                        
                </td>
             <td>
                    <c:if test="${entity.power>admin.power}">  
                         <c:if test="${admin.work=='CEO'}">  
                          <c:set var="temp" value="${entity.username}"/>      
                          <c:set var="username" value="${fn:replace(temp, '.', '$')}" />
                          <a class="btn btn-sm btn-warning remove"  href="${pageContext.request.contextPath}/admin/hr/delete/${username}" type="button" >Remove</a>       
                         </c:if>
                         </c:if>
                        
                </td>
      
            </tr>
                                              
                     </c:forEach>
              </tbody>
                        </table>
  <center>   
      <hr/>
       <br/> 
        <p class="message"><c:out value="${message}"/></p>
       <div id="make-employee" class="container" >
            <div class="well span2" style="max-width: 500px">
          <form id="admin-employee-form" class="form-signin" action="${pageContext.request.contextPath}/admin/assign_admin" method="post" >                                                               
                <h2 >New Employee</h2>
                <input type="text" class="form-control" name='username' placeholder="Email Id" required autofocus>
                <input type="text" class="form-control" name='name' placeholder="Name" required>
                <input type="text" class="form-control" name='work' placeholder="Role" required>
                 <input type="text" class="form-control" name='number' placeholder="Number" required>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Assign</button>
              </form>
            </div>
       </div>
        </div></div></div>
      </center>  
    </body>
</html>
