 <%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

  
  <tr>
    <td>
    	<section class="header">
            <div class="container_fluid">
                <div class="logo">
                    <a href="#"><img src="<c:url value="/resources/images/logo.png"/>" /></a>
                </div>
                <!--<div class="healthmanager">
                	 <select name="cars">
                      <option value="volvo">Health Manager</option>
                      <option value="saab">+91 9876 543 321</option>
                      <option value="fiat">info@jubination.com</option>
                    </select>
                </div>-->
            </div>
    	</section>    
    </td>
  </tr>
  <tr>
  <td>
       <div class="patient_desc">
            <div class="container">
                <div class="patient_bgicon">
                    <div class="patient_img">
                       <%-- <img src="<c:url value="/resources/images/user_1.png"/>" />--%>
                    </div>   
                    <div class="name_patient">
                    	<p>Patient: <strong>${report.name}</strong></p>
                        <c:if test="${report.gender eq 'M'}">
                    	<p>Gender: <strong>Male</strong></p>
                        </c:if>
                        <c:if test="${report.gender eq 'F'}">
                        <p>Gender: <strong>Female</strong></p>
                        </c:if>
                    	<p>Age: <strong>${report.age}</strong></p>
                    </div> 
                    <div class="lab_patient">
                    	<p>Lab: <strong>Thyrocare (NABL,CAP accredited) </strong></p>
						<p>D-37/1,TTC MIDC, Turbe,</p>
						<p>Navi Mumbai-400 703</p>
                    </div>  
                    <div class="lab_collected">
                    	<p>Date: <strong>${report.sampleCollectedDate}</strong></p>
                    </div>    
                    <div class="lab_collec_timing">
                    	<p>Collection timing: <strong>8.30 am</strong></p>
                    </div>    
                </div>
            </div>
        </div>
    </td>
  </tr>
    <tr>
  	<td>
    	<div class="container">
        	<div class="menu_bx">
            	<div class="profile">
                	<p><strong>Profiles Included</strong> (3 profile): </p>
                </div>
                <div class="menu">
                	<ul>
                            <c:forEach items="${report.profileData}" var="profile" varStatus="i">
                                <c:if test="${profile.name eq 'CHOLESTEROL'}">
                                    <li><a href="#">${profile.name}</a></li>
                                </c:if>
                                <c:if test="${profile.name eq 'ARTHRITIS'}">
                                <li><a href="#">${profile.name}</a></li>
                                </c:if>
                                <c:if test="${profile.name eq 'COMPLETE BLOOD COUNT'}">
                                <li><a href="#">BLOOD</a></li>
                                </c:if>
                                
                                
                                
                            </c:forEach>
                    
                    </ul>
                </div>
                <div class="clearfix"></div>
            </div>
            <div class="appears_bx">
            	<ul>
                	<li>Lorem ipsum dolor sit amet: </li>
                	<li class="appear_norm"><a href="#">Appears normal</a></li>
                	<li class="appear_atten"><a href="#">May need attention</a></li>
                </ul>
                <div class="clearfix"></div>
            </div>            
            <div class="clearfix"></div>
        </div>
    </td>
  </tr>