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
            <title>Call Updates</title>
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
               
               <div class="col-sm-2 " style="background-color: rgba(50,50,50,0.1);border-radius: 10px;margin-left: 50px">
                                    <br/>
                                    <h2 >Get Lead Details</h2>
                                    <br/>
                                    <p>${message}</p>
                          <form action="${pageContext.request.contextPath}/admin/callupdates/values" method="GET" class="form-signin-heading" >
                                    <input type="text" class="form-control" placeholder="Lead Id" name="leadId"/><br/>
                               <button type="submit" class="btn btn-sm" style="background-color:#515151;color:#ffffff" >Submit</button>
                                </form>
                                    <br/><br/>
                                     <form action="${pageContext.request.contextPath}/admin/callupdates/phone" method="GET" class="form-signin-heading" >
                                    <input type="text" class="form-control" placeholder="Phone Numbers" name="phone_numbers"/><br/>
                                  
                                 <c:if test="${clients==null}">   
                               <button type="submit" class="btn btn-sm" style="background-color:#515151;color:#ffffff" >Submit</button>
                                 </c:if>
                                </form>
                                      <c:if test="${not empty clients}">
                                        <c:forEach items="${clients}" var="client">
                                            <c:forEach items="${client.lead}" var="lead">
                                                   <a href="${pageContext.request.contextPath}/admin/callupdates/values?leadId=${lead.leadId}" class="btn btn-sm" style="background-color:#0081c2;color:#ffffff" >${lead.leadId}</a>&nbsp;
                                            </c:forEach>
                                        </c:forEach>
                                    <br/>
                                    </c:if>
                                    <br/>
                                    
               </div>
               <div class="col-sm-8" style="background-color: rgba(50,50,50,0.1);border-radius: 10px;margin-left: 50px">
                   <br/>
                    <h2 >Update Lead Details</h2>
                   <br/>
               <form action="${pageContext.request.contextPath}/admin/callupdates/update" method="GET" class="form-signin-heading" >
                   <div class="container">
                       <div class="row">
                           <div class="col-sm-3">
                                <h3 >Client Details</h3>
                                            <label>Lead Id</label>
                                            <input type="text" class="form-control" placeholder="Lead Id" name="id" readonly="true" value="${lead.leadId}"/>
                                            <br/>
                                              <label>Follow up left</label>
                                            <input type="text" class="form-control" placeholder="Count" name="count" readonly="true" value="${lead.count}"/>
                                            <br/>
                                            <label>Phone number</label>
                                            <input type="text" class="form-control" placeholder="Number" name="number" readonly="true" value="${lead.client.phoneNumber}"/>
                                         <br/>
                                            <label>Name</label>
                                             <input type="text" class="form-control" placeholder="Name" name="name" value="${lead.client.name}"/>
                                         
                                             <label>Email</label>
                                            <input type="text" class="form-control" placeholder="Email" name="email" value="${lead.client.emailId}"/>
                                       
                                            <label>Age</label>
                                            <input type="text" class="form-control" placeholder="Age" name="age" value="${lead.client.age}"/>
                                          
                                            <label>Gender</label>
                                            <input type="text" class="form-control" placeholder="Gender" name="gender" value="${lead.client.gender}"/>
                                         
                                            <label>Address</label>
                                            <textarea type="text" class="form-control" placeholder="Address" rows="2" name="address" >${lead.client.address}</textarea>
                               
                                            <label>Pincode</label>
                                            <input type="text" class="form-control" placeholder="Pincode" name="pincode" value="${lead.client.pincode}"/>
                                            
                                            <label>City</label>
                                            <input type="text" class="form-control" placeholder="City" name="city" value="${lead.client.city}"/>
                                            <br/>
                                                <label>Entry Comments</label>
                                            <textarea type="text" class="form-control" placeholder="Entry Comments" rows="2" name="initialComment" value="">${lead.client.initialComments}</textarea>
                                            
                                          
                           </div>
                           <div class="col-sm-3">
                                <h3 >Lead Details</h3>
                                 <label>Lead Status</label>
                                            <input type="text" class="form-control" placeholder="Status"  readonly="true"  value="${lead.leadStatus}"/>
                                            <select  name="leadStatus"   class="form-control"  value="${lead.leadStatus}">
                                                <option name="leadStatus"  value="" >Select lead status</option>
                                                <option name="leadStatus"  value="Follow up/Call back" >Follow up/Call back</option>
                                                <option name="leadStatus"  value="Lead sent to Thyrocare" >Lead sent to Thyrocare</option>
                                                <option name="leadStatus"  value="Not interested">Not interested</option>
                                                <option name="leadStatus"  value="Not registered">Not registered</option>
                                                <option name="leadStatus"  value="Language not recognizable">Language not recognizable</option>
                                                <option name="leadStatus"  value="No Service">No Service</option>
                                                <option name="leadStatus"  value="Customer complained">Customer Complained</option>
                                            </select>
                                            <br/>
                                            <label>Lead Comments</label>
                                            <textarea type="text" class="form-control" placeholder="Lead Comments" rows="2" name="comment" value="">${lead.comments}</textarea>
                                            <br/>
                                            <label>Follow up Date</label>
                                            <input type="text" class="form-control" placeholder="Follow up date (yyyy-mm-dd)" name="date" value="${lead.followUpDate}"/>
                                          <br/>
                                            <label>Product</label>
                                            <input type="text" class="form-control" placeholder="Product" name="product" value="${lead.client.campaignName}"/>
                                            <br/>
                                            <label>Hardcopy</label>
                                            <input type="text" class="form-control" placeholder="Hardcopy" name="hardcopy" value="${lead.hardcopy}"/>
                                            <br/>
                                            <label>Appointment Date</label>
                                            <input type="text" class="form-control" placeholder="Appointment Date" name="appt_date" value="${lead.appointmentDate}"/>
                                            <br/>
                                            <label>Appointment Time</label>
                                            <input type="text" class="form-control" placeholder="Appointment Time" name="appt_time" value="${lead.appointmentTime}"/>
                                           <br/>
                                            <label>Pay Type</label>
                                            <input type="text" class="form-control" placeholder="Pay Type" name="pay_type" value="${lead.payType}"/><%--
                                            <label>Service</label>
                                            <input type="text" class="form-control" placeholder="Service Type" readonly="true" name="service_type" value="${lead.serviceType}"/>
                                             <label>Report Code</label>
                                            <input type="text" class="form-control" placeholder="Report Code" readonly="true" name="report_code" value="${lead.reportCode}"/>
                                             <label>Rate</label>
                                            <input type="text" class="form-control" placeholder="Rate" readonly="true" name="rate" value="${lead.rate}"/>
                                             <label>Margin</label>
                                            <input type="text" class="form-control" placeholder="Margin" readonly="true" name="margin" value="${lead.margin}"/>
                                           <label>Handling Charges</label>
                                            <input type="text" class="form-control" placeholder="Handling Charges" readonly="true" name="handling_charges" value="${lead.handlingCharges}"/>
                                           <label>Order Id</label>
                                            <input type="text" class="form-control" placeholder="Order Id" readonly="true" name="order_id" value="${lead.orderId}"/>
                                             <label>Order By</label>
                                            <input type="text" class="form-control" placeholder="Order By" readonly="true" name="order_by" value="${lead.orderBy}"/>
                                           <label>Ben Count</label>
                                            <input type="text" class="form-control" placeholder="Beneficiary Count" readonly="true" name="ben_count" value="${lead.ben_count}"/>--%>
                                           
                                            
                           
                           </div>
                                            <div class="col-sm-3">
                                                
                                <h3 >Beneficiary Details</h3>
                                                 <c:if test="${lead.beneficiaries ne null and not empty lead.beneficiaries}">
                                            <c:forEach items="${lead.beneficiaries}" var="ben" varStatus="i">
                                                                    <label>Beneficiary ${i}</label>
                                                                 <input type="text" class="form-control" placeholder="Name,Age,Gender" name="ben_${i}" value="${ben.name},${ben.age},${ben.gender}"/>
                                            <br/>
                                            </c:forEach>
                                            </c:if>
                                       <%--     <c:if test="${lead.beneficiaries eq null or empty lead.beneficiaries}">
                                                
                                                    <label>Beneficiary 0</label>
                                                    <input type="text" class="form-control" placeholder="Name,Age,Gender" name="ben_0" value="${lead.payType}"/>
                                                    <br/>
                                                    <label>Beneficiary 1</label>
                                                    <input type="text" class="form-control" placeholder="Name,Age,Gender" name="ben_1" value="${lead.payType}"/>
                                                    <br/>
                                                    <label>Beneficiary 2</label>
                                                    <input type="text" class="form-control" placeholder="Name,Age,Gender" name="ben_2" value="${lead.payType}"/>
                                                    <br/>
                                                    <label>Beneficiary 3</label>
                                                    <input type="text" class="form-control" placeholder="Name,Age,Gender" name="ben_3" value="${lead.payType}"/>
                                                    <br/>
                                                    <label>Beneficiary 4</label>
                                                    <input type="text" class="form-control" placeholder="Name,Age,Gender" name="ben_4" value="${lead.payType}"/>
                                                    <br/>
                                                    <label>Beneficiary 5</label>
                                                    <input type="text" class="form-control" placeholder="Name,Age,Gender" name="ben_5" value="${lead.payType}"/>
                                                    <br/>
                                                    <label>Beneficiary 6</label>
                                                    <input type="text" class="form-control" placeholder="Name,Age,Gender" name="ben_6" value="${lead.payType}"/>
                                                    <br/>
                                                    <label>Beneficiary 7</label>
                                                    <input type="text" class="form-control" placeholder="Name,Age,Gender" name="ben_7" value="${lead.payType}"/>
                                                    <br/>
                                                    <label>Beneficiary 8</label>
                                                    <input type="text" class="form-control" placeholder="Name,Age,Gender" name="ben_8" value="${lead.payType}"/>
                                                    <br/>
                                                    <label>Beneficiary 9</label>
                                                    <input type="text" class="form-control" placeholder="Name,Age,Gender" name="ben_9" value="${lead.payType}"/>
                                              
                                            <br/>
                                            </c:if>--%>
                                            </div>
                       </div>
                   </div>
                   <br/>
                   
              <button type="submit" class="btn btn-sm" style="background-color:#515151;color:#ffffff" >Submit</button>
               </form>
                   <br/>
                        
               </div>
                                 
           </div>
             <hr/>
            </center>

       </div>
    </div>
</div>
    
    </body>
</html>
