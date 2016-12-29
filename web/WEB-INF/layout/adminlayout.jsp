<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--

<center>
    
        <h2><a href="${pageContext.request.contextPath}/admin" >
                <img   src="${pageContext.request.contextPath}/resources/css/images/logo.png"  alt="Jubination">
                             HRMS</a></h2>

        <h3>${admin.name}, ${admin.work}</h3>
        <br/>
        <br/>
        
        <a class="btn btn-small btn-default" type="button" href="${pageContext.request.contextPath}/admin" >Home &nbsp; <span class="glyphicon glyphicon-home">&nbsp;</span> </a>
       <a class="btn btn-small btn-default" type="button" href="${pageContext.request.contextPath}/admin/mail" >Mail &nbsp; <span class="glyphicon glyphicon-envelope">&nbsp;</span></a>
         <c:if test="${admin.power<3||admin.work=='HR'}">  
            <a class="btn btn-small btn-default" type="button" href="${pageContext.request.contextPath}/admin/hr" >Employees &nbsp; <span class="glyphicon glyphicon-user">&nbsp;</span></a>
    
     <%--    </c:if>  
        <a class="btn btn-small btn-default" href="${pageContext.request.contextPath}/admin/callinterface" >Call Interface &nbsp; <span class="glyphicon glyphicon-phone">&nbsp;</span></a>
        <a class="btn btn-small btn-default" href="${pageContext.request.contextPath}/admin/callrecords" >Call Records &nbsp; <span class="glyphicon glyphicon-phone-alt">&nbsp;</span></a>
      
        <a class="btn btn-small btn-default" href="${pageContext.request.contextPath}/admin/settings" >Settings &nbsp; <span class="glyphicon glyphicon-cog">&nbsp;</span></a>
      
        <a class="btn btn-small btn-danger" href="${pageContext.request.contextPath}/logout" >Log out &nbsp; <span class="glyphicon glyphicon-log-out">&nbsp;</span></a>


</center>--%>
     
     
     <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Jubination Call Bot</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="${pageContext.request.contextPath}/admin/">Dashboard</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/settings">Settings</a></li>
            
            <li><a href="${pageContext.request.contextPath}/logout">Log out</a></li>
          </ul>
         <%-- <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>--%>
        </div>
      </div>
    </nav>
        
        
        
        
        
        
   
     

  <div class="container-fluid">
      <div class="row">
        
<div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar" >
              <li ><a style="color:#515151;border-bottom: #c0c0c0 0.5px solid ;border-radius: 5px" href="${pageContext.request.contextPath}/admin"><span style="font-size: 20px;" class="glyphicon glyphicon-bullhorn"></span>&nbsp;Calls Analytics </a></li>
            <li><a style="color:#515151;border-bottom: #c0c0c0 0.5px solid ;border-radius: 5px" href="${pageContext.request.contextPath}/admin/callinterface"><span style="font-size: 20px;" class="glyphicon glyphicon-time"></span>&nbsp;Calls in Real time</a></li>
            <li><a style="color:#515151;border-bottom: #c0c0c0 0.5px solid ;border-radius: 5px" href="${pageContext.request.contextPath}/admin/callnotification"><span style="font-size: 20px;"class="glyphicon glyphicon-list-alt"></span>&nbsp;Follow Up Calls</a></li>
            <li><a style="color:#515151;border-bottom: #c0c0c0 0.5px solid ;border-radius: 5px" href="${pageContext.request.contextPath}/admin/callupdates"><span style="font-size: 20px;"class="glyphicon glyphicon-upload"></span>&nbsp;Call Updates</a></li>  
            
            
            <li><a style="color:#515151;border-bottom: #c0c0c0 0.5px solid ;border-radius: 5px" href="${pageContext.request.contextPath}/admin/callrecords/?date=<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>"><span style="font-size: 20px;"class="glyphicon glyphicon-align-left"></span>&nbsp;Call Records</a></li>  
            
            
            
            
        <%--    <li><a style="color:#515151;border-bottom: #c0c0c0 0.5px solid ;border-radius: 5px" href="${pageContext.request.contextPath}/admin/callcustom"><span style="font-size: 20px;" class="glyphicon glyphicon-phone"></span>&nbsp;Custom Calling</a></li>  
         --%>    <li><a style="color:#515151;border-bottom: #c0c0c0 0.5px solid ;border-radius: 5px" href="${pageContext.request.contextPath}/admin/callsettings"><span style="font-size: 20px;"class="glyphicon glyphicon-cog"></span>&nbsp;Call Settings</a></li>  
                    
            <li><a style="color:#515151;border-bottom: #c0c0c0 0.5px solid ;border-radius: 5px" href="${pageContext.request.contextPath}/admin/products"><span style="font-size: 20px;" class="glyphicon glyphicon-tasks"></span>&nbsp;Products</a></li>  
             <li><a style="color:#515151;border-bottom: #c0c0c0 0.5px solid ;border-radius: 5px" href="${pageContext.request.contextPath}/admin/campaign"><span style="font-size: 20px;"class="glyphicon glyphicon-briefcase"></span>&nbsp;Campaigns</a></li>  
                   
             
             <li><a style="color:#515151;border-bottom: #c0c0c0 0.5px solid ;border-radius: 5px" href="${pageContext.request.contextPath}/admin/hr"><span style="font-size: 20px;"class="glyphicon glyphicon-user"></span>&nbsp;Employees</a></li>
         
           <li><a style="color:#515151;border-bottom: #c0c0c0 0.5px solid ;border-radius: 5px" href="${pageContext.request.contextPath}/admin/mail"><span style="font-size: 20px;"class="glyphicon glyphicon-envelope"></span>&nbsp;MailBox</a></li>
      
          </ul>
         
        </div>







