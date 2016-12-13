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
            <title>Call Records</title>
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
            
    </head>
    <body>
       <tiles:insertAttribute name="navigation"/>
       
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
         
       <center>         
       <div class="row">
          <br/> 
           <form id="admin-employee-form" class="form-signin" action="${pageContext.request.contextPath}/admin/callrecords/get" method="get" >                                                               
               <h3 class="form-signin-heading">Enter Call Record Date</h3>
                <input  name="date" placeholder="Date e.g. 2016-05-26" required>
                <button class="btn btn-sm"  style="background-color:#0081c2;color:#ffffff" type="submit">Find</button>
              </form>
               <br/>
               <center>
                   <div clas="container">
               <%--        <c:if test="${excel}">--%>
                                    <a class="btn btn-small "  style="background-color:#515151;color:#ffffff" href="${pageContext.request.contextPath}/admin/data.xls" >Download Call Excel Report &nbsp; <span class="glyphicon glyphicon-earphone">&nbsp;</span></a>
                    
                                    <a class="btn btn-small"  style="background-color:#515151;color:#ffffff" href="${pageContext.request.contextPath}/admin/client.xls" >Download Client Excel Report &nbsp; <span class="glyphicon glyphicon-user">&nbsp;</span></a>
                     
                                    <%--  </c:if>--%>
                   </div>
               </center>
               <br/>
               <h2> Spoke but not updated / Missed calls</h2>
               <div class="table-responsive">
               <table class="table table-striped table-condensed table-hover table-bordered">
              <thead>
                <tr>
                                 <th>Lead Id</th> 
                                 <th>Name</th> 
                                 <th>Email Id</th>  
                                 <th>Campaign</th>  
                                 <th>Source</th>  
                                <%-- <th>Pub Id</th>  
                                 <th>Initial Comments</th>
                                 <th>Follow up left</th>
                                 <th>Follow up Date</th>
                                 <th>Notified</th>
                                 <th>Lead Status</th>
                              <th>Comments</th>--%>
                                  <th>Executive</th>
                              <%--  <th>CallFrom</th>  
                                  <th>CallTo</th>
				  <th>Status</th>
				  <th>TrackStatus</th>
				  <th>CallType</th>--%>
				  <th>DialCallDuration</th>
				 <%-- <th>Message</th>
				   <th>DateUpdated</th> --%>
                   <th>Dial Whom Number</th> 
                    <th>Call Details</th>				   
					<th>Call Data</th> 				   
                    <th>RecordingUrl</th>   
                   
                   
                   
                   
                   
                   
     
                </tr>
              </thead>
              <tbody>
                    <c:forEach items="${callrecords}" var="item">
                        <c:if test="${
                              (not empty item.recordingUrl &&not empty item.lead.leadId &&  empty item.lead.followUpDate &&
                                      item.lead.leadStatus ne 'Lead sent to Thyrocare'&&
                                      item.lead.leadStatus ne 'Follow up/Call back'&&
                                      item.lead.leadStatus ne 'Not interested'&&
                                      item.lead.leadStatus ne 'Not registered'&&
                                      item.lead.leadStatus ne 'Language not recognizable'&&
                                      item.lead.leadStatus ne 'No Service'&&
                                      item.lead.leadStatus ne 'Disapproved'&&
                                      item.lead.leadStatus ne 'Customer complained')||
                                      
                                      (not empty item.recordingUrl && not empty item.lead.leadId&& not empty item.lead.followUpDate && 
                                      item.lead.leadStatus ne 'Lead sent to Thyrocare'&&
                                      item.lead.leadStatus ne 'Busy'&&
                                      item.lead.leadStatus ne 'No Answer'&&
                                      item.lead.leadStatus ne 'Failed'&&
                                      item.lead.leadStatus ne 'Hanged up while greetings'&&
                                      item.lead.leadStatus ne 'Hanged up while connecting'&&
                                      item.lead.leadStatus ne 'Follow up/Call back'&&
                                      item.lead.leadStatus ne 'Not interested'&&
                                      item.lead.leadStatus ne 'Not registered'&&
                                      item.lead.leadStatus ne 'Language not recognizable'&&
                                      item.lead.leadStatus ne 'No Service'&&
                                      item.lead.leadStatus ne 'Disapproved'&&
                                      item.lead.leadStatus ne 'Customer complained')
                              }">
                    <tr>
                        
                                        <td><a class="btn btn-small"  style="background-color:#0081c2;color:#ffffff" href="${pageContext.request.contextPath}/admin/callupdates/values?leadId=${item.lead.leadId}"  target="_blank">${item.lead.leadId}</a></td>
                          
                      
                        <td>
                                        <p  class="view-${item.orderId}"><c:out value="${item.lead.client.name}"/></p>
                        </td>
                        <td>
                                        <p  class="view-${item.orderId}"><c:out value="${item.lead.client.emailId}"/></p>
                        </td>
                        <td>
                                        <p  class="view-${item.orderId}"><c:out value="${item.lead.client.campaignName}"/></p>
                        </td>
                        
                        <td>
                                        <p  class="view-${item.orderId}"><c:out value="${item.lead.client.source}"/></p>
                        </td>
                       <%-- <td>
                                        <p  class="view-${item.orderId}"><c:out value="${item.lead.client.pubId}"/></p>
                        </td>
                       
                         <td>
                                        <p  class="view-${item.orderId}"><c:out value="${item.lead.client.initialComments}"/></p>
                        </td>
                        <td>
                                        <p  class="view-${item.orderId}"><c:out value="${item.lead.count}"/></p>
                        </td>
                        <td>
                                        <p  class="view-${item.orderId}"><c:out value="${item.lead.followUpDate}"/></p>
                        </td>
                        <td>
                                        <p  class="view-${item.orderId}"><c:out value="${item.lead.notification}"/></p>
                        </td>
                        <td>
                                        <p  class="view-${item.orderId}"><c:out value="${item.lead.leadStatus}"/></p>
                        </td>
                      <td>
                                        <p  class="view-${item.orderId}"><c:out value="${item.lead.comments}"/></p>
                        </td>--%>
                        <td>
                                        <p  class="view-${item.orderId}"><c:out value="${item.lead.admin.name}"/></p>
                        </td>
                        <%--
                        		<td>
                                        <p  class="view-${item.orderId}"><c:out value="${item.callFrom}"/></p>
                        </td>
						<td>
                                         <p  class="view-${item.orderId}"><c:out value="${item.callTo}"/></p>
                         </td>
						 <td>
                                        <p  class="view-${item.orderId}"><c:out value="${item.status}"/></p>
                        </td>
						<td>
                                         <p  class="view-${item.orderId}"><c:out value="${item.trackStatus}"/></p>
                         </td>
						 <td>
                                         <p  class="view-${item.orderId}"><c:out value="${item.callType}"/></p>
                         </td>
			
		--%>				<td>
                                        <p  class="view-${item.orderId}"><c:out value="${item.dialCallDuration}"/></p>
                        </td>
		<%--				 <td>
                                        <p  class="view-${item.orderId}"><c:out value="${item.message}"/></p>
                        </td>
		
						<td>
                                         <p  class="view-${item.orderId}"><c:out value="${item.dateUpdated}"/></p>
                         </td>
		--%>				
                         <td>
                                         <p  class="view-${item.orderId}"><c:out value="${item.dialWhomNumber}"/></p>
                         </td>
		
                        <td><a href="http://my.exotel.in/jubination/callindex/query=${item.sid}" target="_blank">Call Details</a></td>
						
                                          <td><a href="http://jubination:ce5e307d58d8ec07c8d8456e42ed171ff8322fd0@twilix.exotel.in${item.uri}" target="_blank">Call Data</a></td>
                         
						 
                       <td>
                           <c:if test="${not empty item.recordingUrl}">
                                            <a href="${item.recordingUrl}" target="_blank">Recording Url</a>
                           </c:if>
                       </td>
			
                         
                
                    </tr>
                        </c:if>                   
                     </c:forEach>
              </tbody>
           </table>
               </div>
       </div>
                   ${message}</center>
       </div>
    </div>
</div>
    
    </body>
</html>
