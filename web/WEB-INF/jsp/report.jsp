<%-- Document : report Created on : Jul 12, 2016, 4:42:20 PM Author : MumbaiZone --%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title> Jubination </title>
    <link type="text/css" href="<c:url value="/resources/css/reportstyle.css"/>" rel="stylesheet" >
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet"> 
    <!--
    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
    <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>-->
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<table width="100%" border="0">
            <tiles:insertAttribute name="start"/>

  <c:forEach items="${report.profileData}" var="profile" varStatus="i">
             <c:if test="${profile.name eq 'CHOLESTEROL'}">
                            <tr>


                                <td>

                                             <div class="container">
                           
                                  <div class="lipid_profile">
                                  <div class="title_lipid">
                                          <h2>${profile.name}</h2>
                                  </div>
                                  <div class="bx_lipid">
                                          <div class="imglipid">
                                              <img src="<c:url value="/resources/images/img_lipid.png"/>" />
                                      </div>
                                      <div class="lipid_txt">
                                          <h3>About your text profile:</h3>
                                          <p>Lipid profile or lipid panel is a panel of blood tests that serves as an initial broad medical screening tool for abnormalities in lipids, such as cholesterol and triglycerides. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut congue pretium sagittis. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut congue pretium sagittis. </p>
                                      </div>
                                      <div class="clearfix"></div>
                                  </div>
                                  <div class="appears_bx"></div>
                              </div>
                              </div>

                              </td>
                              </tr>
                            <tr>
                           <td>

                                  <div class="container">
                                      <div class="test_report">
                                          <div class="title_testreport">
                                              <h3>Your test reports</h3>
                                          </div>
                                          
                                                  <c:forEach items="${profile.testData}" var="test" varStatus="i">    
                                                                   <div class="text_reportul">
                                              
                                                  <ul>
                                                <c:choose>
                                                    <c:when test="${test.rangeValues[0].name=='Range'}">    

                                                        <c:set var="rangeVal" value="${fn:split(test.rangeValues[0].value, '-')}" />
                                                        <fmt:parseNumber var="lessRange" value="${fn:trim(rangeVal[0])}" />
                                                        <fmt:parseNumber var="moreRange" value="${fn:trim(rangeVal[1])}" />
                                                        <fmt:parseNumber var="val" value="${fn:trim(test.value)}" />
                                                       
                                                      <li>
                                                          <div class="title_report">
                                                          <h4>${test.name}</h4>
                                                          <p>Normal Value: <i> ${test.rangeValues[0].value}</i></p>
                                                      </div>
                                                      <div class="value_choles">
                                                          <c:choose>
                                                          <c:when test="${test.meaning eq 'Low'}">
                                                          <div class="bx_values bx_values_min">
                                                                  <h2>Your value</h2>
                                                              <p><strong>${val}</strong> ${test.units} </p>
                                                              <div class="value_arrow">
                                                                  <img src="<c:url value="/resources/images/blue_arrow.png"/>" />
                                                              </div>
                                                          </div>
                                                          </c:when>
                                                          
                                                          <c:when test="${test.meaning eq 'High'}">
                                                          <div class="bx_values bx_values_max">
                                                                  <h2>Your value</h2>
                                                              <p><strong>${val}</strong> ${test.units} </p>
                                                              <div class="value_arrow">
                                                                  <img src="<c:url value="/resources/images/blue_arrow.png"/>" />
                                                              </div>
                                                          </div>
                                                          </c:when>
                                                          <c:otherwise>
                                                               <div class="bx_values">
                                                                  <h2>Your value</h2>
                                                              <p><strong>${val}</strong> ${test.units} </p>
                                                              <div class="value_arrow">
                                                                  <img src="<c:url value="/resources/images/blue_arrow.png"/>" />
                                                              </div>
                                                          </div>
                                                          </c:otherwise>
                                                          </c:choose>
                                                                                          <div class="percen_bx">                            	
                                                              <div class="values_first">                       	
                                                              </div>
                                                              <div class="values_middle">
                                                                  <div class="values_minimize">
                                                                          <p>${lessRange}</p>
                                                                  </div>
                                                                  <div class="values_maximize">
                                                                          <p>${moreRange}</p>
                                                                  </div>
                                                                  <div class="values_minimize">
                                                                          <p>${lessRange}</p>
                                                                  </div>
                                                                  <div class="values_maximize">
                                                                          <p>${moreRange}</p>
                                                                  </div>
                                                              </div>
                                                              <div class="values_last"></div>
                                                              <div class="clearfix"></div>
                                                          </div>                               
                                                      </div>
                                                  </li>
                                                  
                   
                                                    </c:when>
                                                </c:choose>
                                                         </ul>
                                          </div>
                                      
                                     </c:forEach>
                                      </div>
                                      <div class="appears_bx"></div>
                                  </div>
                              </td>
                               </tr>
                            <tr>
                                  <td>
                                
                           <tiles:insertAttribute name="footer"/>
                              </td>
                            </tr>
             </c:if>
  </c:forEach>
  <c:forEach items="${report.profileData}" var="profile" varStatus="i">
             <c:if test="${profile.name eq 'ARTHRITIS'}">
                            <tr>
                            <td>
                              <div class="container">
                                  <div class="lipid_profile">
                                  <div class="title_lipid">
                                                <h2>${profile.name}</h2>
                                  </div>
                                  <div class="bx_lipid">
                                          <div class="imglipid">
                                              <img src="<c:url value="/resources/images/img_lipid_2.png"/>" />
                                      </div>
                                      <div class="lipid_txt">
                                          <h3>About your text profile:</h3>
                                          <p>Lipid profile or lipid panel is a panel of blood tests that serves as an initial broad medical screening tool for abnormalities in lipids, such as cholesterol and triglycerides. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut congue pretium sagittis. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut congue pretium sagittis. </p>
                                      </div>
                                      <div class="clearfix"></div>
                                  </div>
                                  <div class="appears_bx margin_top_0px"></div>
                              </div>
                              </div>
                              </td>
                            </tr>
                            <tr>
                                  <td>
                                  <div class="container">
                                      <div class="test_report">
                                          <div class="title_testreport">
                                              <h3>Your test reports</h3>
                                          </div>
                                                   <c:forEach items="${profile.testData}" var="test" varStatus="i">    
                                          
                                                           <div class="text_reportul">
                                                  <ul>
                                                     
                                                      <c:choose>
                                                          <c:when test="${test.rangeValues[0].name=='Range'}">    
                                                              

                                                        <c:set var="rangeVal" value="${fn:split(test.rangeValues[0].value, '-')}" />
                                                        <fmt:parseNumber var="lessRange" value="${fn:trim(rangeVal[0])}" />
                                                        <fmt:parseNumber var="moreRange" value="${fn:trim(rangeVal[1])}" />
                                                        <fmt:parseNumber var="val" value="${fn:trim(test.value)}" />
                                                   
                                                      <li>
                                                          <div class="title_report">
                                                          <h4>${test.name}</h4>
                                                          <p>Normal Value: <i> ${test.rangeValues[0].value}</i></p>
                                                      </div>
                                                      <div class="value_choles">
                                                          <c:choose>
                                                          <c:when test="${test.meaning eq 'Low'}">
                                                          <div class="bx_values bx_values_min">
                                                                  <h2>Your value</h2>
                                                              <p><strong>${val}</strong> ${test.units} </p>
                                                              <div class="value_arrow">
                                                                  <img src="<c:url value="/resources/images/blue_arrow.png"/>" />
                                                              </div>
                                                          </div>
                                                          </c:when>
                                                          
                                                          <c:when test="${test.meaning eq 'High'}">
                                                          <div class="bx_values bx_values_max">
                                                                  <h2>Your value</h2>
                                                              <p><strong>${val}</strong> ${test.units} </p>
                                                              <div class="value_arrow">
                                                                  <img src="<c:url value="/resources/images/blue_arrow.png"/>" />
                                                              </div>
                                                          </div>
                                                          </c:when>
                                                          <c:otherwise>
                                                               <div class="bx_values">
                                                                  <h2>Your value</h2>
                                                              <p><strong>${val}</strong> ${test.units} </p>
                                                              <div class="value_arrow">
                                                                  <img src="<c:url value="/resources/images/blue_arrow.png"/>" />
                                                              </div>
                                                          </div>
                                                          </c:otherwise>
                                                          </c:choose>
                                                                                          <div class="percen_bx">                            	
                                                              <div class="values_first">                       	
                                                              </div>
                                                              <div class="values_middle">
                                                                  <div class="values_minimize">
                                                                          <p>${lessRange}</p>
                                                                  </div>
                                                                  <div class="values_maximize">
                                                                          <p>${moreRange}</p>
                                                                  </div>
                                                                  <div class="values_minimize">
                                                                          <p>${lessRange}</p>
                                                                  </div>
                                                                  <div class="values_maximize">
                                                                          <p>${moreRange}</p>
                                                                  </div>
                                                              </div>
                                                              <div class="values_last"></div>
                                                              <div class="clearfix"></div>
                                                          </div>                               
                                                      </div>
                                                  </li>
                                                  
                   
                                                          </c:when>
                                                   <c:when test="${test.rangeValues[0].name=='NegEqPosRange'}">
                                                        <c:set var="rangeVal" value="" />
                                                        <c:forEach items="${test.rangeValues}" var="range" varStatus="i">
                                                            <c:if test="${range.name=='Eq'}">
                                                                <c:set var="rangeVal" value="${range.value}" />
                                                            </c:if>
                                                        </c:forEach>
                                                        <c:set var="rangeVal" value="${fn:split(rangeVal, '-')}" />
                                                        <fmt:parseNumber var="lessRange" value="${fn:trim(rangeVal[0])}}" />
                                                        <fmt:parseNumber var="moreRange" value="${fn:trim(rangeVal[1])}" />
                                                        <fmt:parseNumber var="val" value="${fn:trim(test.value)}" />
                                                           <li>
                                                          <div class="title_report">
                                                          <h4>${test.name}</h4>
                                                      </div>
                                                      <div class="value_choles">
                                                          <c:choose>
                                                          <c:when test="${test.meaning eq 'Low'}">
                                                          <div class="bx_values bx_values_min">
                                                                  <h2>Your value</h2>
                                                              <p><strong>${val}</strong> ${test.units} </p>
                                                              <div class="value_arrow">
                                                                  <img src="<c:url value="/resources/images/blue_arrow.png"/>" />
                                                              </div>
                                                          </div>
                                                          </c:when>
                                                          
                                                          <c:when test="${test.meaning eq 'High'}">
                                                          <div class="bx_values bx_values_max">
                                                                  <h2>Your value</h2>
                                                              <p><strong>${val}</strong> ${test.units} </p>
                                                              <div class="value_arrow">
                                                                  <img src="<c:url value="/resources/images/blue_arrow.png"/>" />
                                                              </div>
                                                          </div>
                                                              
                                                              
                                                          </c:when>
                                                          <c:otherwise>
                                                               <div class="bx_values">
                                                                  <h2>Your value</h2>
                                                              <p><strong>${val}</strong> ${test.units} </p>
                                                              <div class="value_arrow">
                                                                  <img src="<c:url value="/resources/images/blue_arrow.png"/>" />
                                                              </div>
                                                          </div>
                                                          </c:otherwise>
                                                          </c:choose>
                                                     <div class="percen_bx">                            	
                                                        <div class="values_first">
                                                            <div class="txt_minimize">
                                                                <b><p>< ${lessRange}</p></b>
                                                                <h3>Negative</h3>
                                                            </div>
                                                        </div>
                                                        <div class="values_middle">
                                                            <div class="txt_middle">
                                                                <b><p> ${lessRange} - ${moreRange}</p></b>
                                                                <h3>Equivocal</h3>
                                                            </div>
                                                        </div>
                                                        <div class="values_last">
                                                            <div class="txt_maximize">
                                                                <b><p>> ${moreRange}</p></b>
                                                                <h3>Positive</h3>
                                                            </div>
                                                        </div>
                                                        <div class="clearfix"></div>
                                                    </div>
                                               
                                                           </li>
                                                   </c:when>
                                                            <c:when test="${test.rangeValues[0].name=='LessRange'}">
                                                                    <c:set var="rangeVal" value="${fn:split(test.rangeValues[0].value, '<')}" />
                                                                    <fmt:parseNumber var="lessRange" value="${fn:trim(rangeVal[0])}" />
                                                                    <fmt:parseNumber var="val" value="${fn:trim(test.value)}" />
                                                                           
                                                                            <li>
                                                                                        <div class="title_report">
                                                                                        <h4>${test.name}</h4>
                                                                                        <p>Normal value (Adult): < ${lessRange}</p>
                                                                                    </div>
                                                                                    <div class="value_choles value_choles_strepto">
                                                                                        <c:choose>
                                                                                            <c:when test="${test.meaning eq 'High'}">
                                                                                        <div class="bx_values bx_values_max">
                                                                                                <h2>your value</h2>
                                                                                            <p><strong>${val}</strong> mg/dl </p>
                                                                                            <div class="value_arrow">
                                                                                               <img src="<c:url value="/resources/images/blue_arrow.png"/>" />
                                                                                            </div>
                                                                                        </div>
                                                                                            </c:when>
                                                                                            <c:otherwise>
                                                                                        <div class="bx_values bx_values_min">
                                                                                                <h2>your value</h2>
                                                                                            <p><strong>${val}</strong> mg/dl </p>
                                                                                            <div class="value_arrow">
                                                                                               <img src="<c:url value="/resources/images/blue_arrow.png"/>" />
                                                                                            </div>
                                                                                        </div>        
                                                                                            </c:otherwise>
                                                                                        </c:choose>
                                                                                                                        <div class="percen_bx">                            	
                                                                                            <div class="values_first values_first_strepto">
                                                                                                <div class="txt_first_strepto">
                                                                                                    <b><p>< ${lessRange}</p></b>
                                                                                                </div>
                                                                                            </div>
                                                                                            <div class="values_last values_last_strepto">
                                                                                                <div class="txt_last_strepto">
                                                                                                    <b><p>> ${lessRange}</p></b>
                                                                                                </div>
                                                                                            </div>
                                                                                            <div class="clearfix"></div>
                                                                                        </div>                                
                                                                                    </div>
                                                                                </li>
                                                                 </c:when>
                                                   
                                                          <c:otherwise>
                                                              <li>
                                                          <div class="title_report">
                                                          <h4>${test.name}</h4>
                                                      </div>
                                                      <div class="value_choles">
                                                          <div class="hla_bx">
                                                              <p>${test.value} ${test.units}</p>
                                                          </div>                                
                                                      </div>
                                                  </li>
                                                   </c:otherwise>
                                                      </c:choose>
                                           
                                                 </ul>
                                             
                                          </div>
                                                              
                                                   </c:forEach>
                                      </div>
                                      <div class="appears_bx margin_top_0px"></div>
                                  </div>
                              </td>
                            </tr>
                            <tr>
                                  <td>
                             
                           <tiles:insertAttribute name="footer"/>
                                  <div class="clearfix"></div>
                              </td>
                            </tr>
             </c:if>
  </c:forEach>
  <c:forEach items="${report.profileData}" var="profile" varStatus="i">       
      <c:if test="${profile.name eq 'COMPLETE BLOOD COUNT'}">
                            <tr>
                            <td>
                              <div class="container">
                              <div class="page_end"></div>
                                  <div class="lipid_profile">
                                  <div class="title_lipid">
                                          <h2>${profile.name} </h2>
                                  </div>
                                  <div class="bx_lipid">
                                          <div class="imglipid">
                                              <img src="<c:url value="/resources/images/img_lipid_3.png"/>" />
                                      </div>
                                      <div class="lipid_txt">
                                          <h3>About your text profile:</h3>
                                          <p>Lipid profile or lipid panel is a panel of blood tests that serves as an initial broad medical screening tool for abnormalities in lipids, such as cholesterol and triglycerides. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut congue pretium sagittis. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut congue pretium sagittis. </p>
                                      </div>
                                      <div class="clearfix"></div>
                                  </div>
                                  <div class="appears_bx"></div>
                              </div>
                              </div>
                              </td>
                            </tr>
                            <tr>
                                  <td>
                                  <div class="container">
                                      <div class="test_report">
                                          <div class="title_testreport">
                                              <h3>Your test reports</h3>
                                          </div>
                                             <c:forEach items="${profile.testData}" var="test" varStatus="i">    
                                          <div class="text_reportul">
                                              <ul>
                                                  
                                                      <c:choose>
                                                          <c:when test="${test.rangeValues[0].name=='Range'}">
                                                              
                                                        <c:set var="rangeVal" value="${fn:split(test.rangeValues[0].value, '-')}" />
                                                        <fmt:parseNumber var="lessRange" value="${fn:trim(rangeVal[0])}" />
                                                        <fmt:parseNumber var="moreRange" value="${fn:trim(rangeVal[1])}" />
                                                        <fmt:parseNumber var="val" value="${fn:trim(test.value)}" />
                                                       
                                                      <li>
                                                          <div class="title_report">
                                                          <h4>${test.name}</h4>
                                                          <p>Normal Value: <i> ${test.rangeValues[0].value}</i></p>
                                                      </div>
                                                      <div class="value_choles">
                                                          <c:choose>
                                                          <c:when test="${test.meaning eq 'Low'}">
                                                          <div class="bx_values bx_values_min">
                                                                  <h2>Your value</h2>
                                                              <p><strong>${val}</strong> ${test.units} </p>
                                                              <div class="value_arrow">
                                                                  <img src="<c:url value="/resources/images/blue_arrow.png"/>" />
                                                              </div>
                                                          </div>
                                                          </c:when>
                                                          
                                                          <c:when test="${test.meaning eq 'High'}">
                                                          <div class="bx_values bx_values_max">
                                                                  <h2>Your value</h2>
                                                              <p><strong>${val}</strong> ${test.units} </p>
                                                              <div class="value_arrow">
                                                                  <img src="<c:url value="/resources/images/blue_arrow.png"/>" />
                                                              </div>
                                                          </div>
                                                          </c:when>
                                                          <c:otherwise>
                                                               <div class="bx_values">
                                                                  <h2>Your value</h2>
                                                              <p><strong>${val}</strong> ${test.units} </p>
                                                              <div class="value_arrow">
                                                                  <img src="<c:url value="/resources/images/blue_arrow.png"/>" />
                                                              </div>
                                                          </div>
                                                          </c:otherwise>
                                                          </c:choose>
                                                                                          <div class="percen_bx">                            	
                                                              <div class="values_first">                       	
                                                              </div>
                                                              <div class="values_middle">
                                                                  <div class="values_minimize">
                                                                          <p>${lessRange}</p>
                                                                  </div>
                                                                  <div class="values_maximize">
                                                                          <p>${moreRange}</p>
                                                                  </div>
                                                                  <div class="values_minimize">
                                                                          <p>${lessRange}</p>
                                                                  </div>
                                                                  <div class="values_maximize">
                                                                          <p>${moreRange}</p>
                                                                  </div>
                                                              </div>
                                                              <div class="values_last"></div>
                                                              <div class="clearfix"></div>
                                                          </div>                               
                                                      </div>
                                                  </li>
                                                  
                   
                                                          </c:when>
                                                               <c:when test="${test.rangeValues[0].name=='LessRange'}">
                                                                    <c:set var="rangeVal" value="${fn:split(test.rangeValues[0].value, '<')}" />
                                                                    <fmt:parseNumber var="lessRange" value="${fn:trim(rangeVal[0])}" />
                                                                    <fmt:parseNumber var="val" value="${fn:trim(test.value)}" />
                                                                           
                                                                            <li>
                                                                                        <div class="title_report">
                                                                                        <h4>${test.name}</h4>
                                                                                        <p>Normal value (Adult): < ${lessRange}</p>
                                                                                    </div>
                                                                                    <div class="value_choles value_choles_strepto">
                                                                                        <c:choose>
                                                                                            <c:when test="${test.meaning eq 'High'}">
                                                                                        <div class="bx_values bx_values_max">
                                                                                                <h2>your value</h2>
                                                                                            <p><strong>${val}</strong> mg/dl </p>
                                                                                            <div class="value_arrow">
                                                                                               <img src="<c:url value="/resources/images/blue_arrow.png"/>" />
                                                                                            </div>
                                                                                        </div>
                                                                                            </c:when>
                                                                                            <c:otherwise>
                                                                                        <div class="bx_values bx_values_min">
                                                                                                <h2>your value</h2>
                                                                                            <p><strong>${val}</strong> mg/dl </p>
                                                                                            <div class="value_arrow">
                                                                                               <img src="<c:url value="/resources/images/blue_arrow.png"/>" />
                                                                                            </div>
                                                                                        </div>        
                                                                                            </c:otherwise>
                                                                                        </c:choose>
                                                                                                                        <div class="percen_bx">                            	
                                                                                            <div class="values_first values_first_strepto">
                                                                                                <div class="txt_first_strepto">
                                                                                                    <b><p>< ${lessRange}</p></b>
                                                                                                </div>
                                                                                            </div>
                                                                                            <div class="values_last values_last_strepto">
                                                                                                <div class="txt_last_strepto">
                                                                                                    <b><p>> ${lessRange}</p></b>
                                                                                                </div>
                                                                                            </div>
                                                                                            <div class="clearfix"></div>
                                                                                        </div>                                
                                                                                    </div>
                                                                                </li>
                                                                 </c:when>
                                                                                <c:when test="${(test.rangeValues[0].name=='Male' or test.rangeValues[0].name=='Female')and fn:length(fn:split(test.rangeValues[0].value, '-')) eq 2}">
                                                                                      <c:if test="${test.rangeValues[0].name=='Male'}">
                                                                                                   
                                                                                                                          <c:set var="rangeValMale" value="${fn:split(test.rangeValues[0].value, '-')}" />
                                                                                                                            <fmt:parseNumber var="lessRangeMale" value="${fn:trim(rangeValMale[0])}" />
                                                                                                                            <fmt:parseNumber var="moreRangeMale" value="${fn:trim(rangeValMale[1])}" />
                                                                                                                            
                                                                                                                          <c:set var="rangeValFemale" value="${fn:split(test.rangeValues[1].value, '-')}" />
                                                                                                                            <fmt:parseNumber var="lessRangeFemale" value="${fn:trim(rangeValFemale[0])}" />
                                                                                                                            <fmt:parseNumber var="moreRangeFemale" value="${fn:trim(rangeValFemale[1])}" />
                                                                                                                            
                                                                                                                            <fmt:parseNumber var="val" value="${fn:trim(test.value)}" />
                                                                                                        
                                                                                                        <c:if test="${report.gender=='M'}">
                                                                                                                  <li>
                                                                                                                        <div class="title_report">
                                                                                                                        <h4>${test.name}</h4>
                                                                                                                         <p>Normal value : Female - ${test.rangeValues[1].value} Male - ${test.rangeValues[0].value} </p>
                                                                                                                    </div>
                                                                                                                    <div class="value_choles value_hemoglobin">
                                                                                                                        <div class="box_female">                                	
                                                                                                                            
                                                                                                                             <c:choose>
                                                                                                                    <c:when test="${test.meaning eq 'Low'}">
                                                                                                                         <div class="bx_values bx_hemoglobin bx_values_min">
                                                                                                                                <h2>Your value</h2>
                                                                                                                                <p><strong>${test.value}</strong> ${test.units} </p>
                                                                                                                                <div class="value_arrow">
                                                                                                                                        <img src="<c:url value="/resources/images/blue_arrow.png"/>" />
                                                                                                                                </div>
                                                                                                                            </div>
                                                                                                                    </c:when>

                                                                                                                    <c:when test="${test.meaning eq 'High'}">
                                                                                                                         <div class="bx_values bx_hemoglobin bx_hemoglobin_max">
                                                                                                                                <h2>Your value</h2>
                                                                                                                                <p><strong>${test.value}</strong> ${test.units} </p>
                                                                                                                                <div class="value_arrow">
                                                                                                                                        <img src="<c:url value="/resources/images/blue_arrow.png"/>" />
                                                                                                                                </div>
                                                                                                                            </div>
                                                                                                                    </c:when>
                                                                                                                    <c:otherwise>
                                                                                                                         <div class="bx_values bx_hemoglobin">
                                                                                                                                <h2>Your value</h2>
                                                                                                                                <p><strong>${test.value}</strong> ${test.units} </p>
                                                                                                                                <div class="value_arrow">
                                                                                                                                        <img src="<c:url value="/resources/images/blue_arrow.png"/>" />
                                                                                                                                </div>
                                                                                                                            </div>
                                                                                                                    </c:otherwise>
                                                                                                                    </c:choose>
                                                                                                                            <div class="hemoglobin_bx"> 
                                                                                                                                <div class="out_malebx"> 
                                                                                                                                     <div class="female_bx male_bx">                          	
                                                                                                                                         <div class="hemoglobin_first hemoglobin_first_60px"><div class="txt_female_min"><p>${lessRangeMale}</p></div></div>
                                                                                                                                        <div class="hemoglobin_middle hemoglobin_middle_60px hemoglobin_yellow"></div>
                                                                                                                                        <div class="hemoglobin_last hemoglobin_last_60px"><div class="txt_female_max"><p>${moreRangeMale}</p></d</div></div>
                                                                                                                                    </div>
                                                                                                                                  
                                                                                                                                </div>      
                                                                                                                                      <b> <p>Male</p></b>
                                                                                                                                <div class="clearfix"></div>
                                                                                                                            </div>    
                                                                                                                            <div class="clearfix"></div> 
                                                                                                                        </div>
                                                                                                             
                                                                                                                        <div class="clearfix"></div>                        
                                                                                                                    </div>
                                                                                                                </li>
                                                                                                            </c:if>
                                                                                                        <c:if test="${report.gender=='F'}">
                                                                                                                  <li>
                                                                                                                        <div class="title_report">
                                                                                                                        <h4>${test.name}</h4>
                                                                                                                         <p>Normal value : Female - ${test.rangeValues[1].value} Male - ${test.rangeValues[0].value} </p>
                                                                                                                    </div>
                                                                                                                    <div class="value_choles value_hemoglobin">
                                                                                                                        <div class="box_female">                                	
                                                                                                                            
                                                                                                                             <c:choose>
                                                                                                                    <c:when test="${test.meaning eq 'Low'}">
                                                                                                                         <div class="bx_values bx_hemoglobin bx_values_min">
                                                                                                                                <h2>Your value</h2>
                                                                                                                                <p><strong>${test.value}</strong> ${test.units} </p>
                                                                                                                                <div class="value_arrow">
                                                                                                                                        <img src="<c:url value="/resources/images/blue_arrow.png"/>" />
                                                                                                                                </div>
                                                                                                                            </div>
                                                                                                                    </c:when>

                                                                                                                    <c:when test="${test.meaning eq 'High'}">
                                                                                                                         <div class="bx_values bx_hemoglobin bx_hemoglobin_max">
                                                                                                                                <h2>Your value</h2>
                                                                                                                                <p><strong>${test.value}</strong> ${test.units} </p>
                                                                                                                                <div class="value_arrow">
                                                                                                                                        <img src="<c:url value="/resources/images/blue_arrow.png"/>" />
                                                                                                                                </div>
                                                                                                                            </div>
                                                                                                                    </c:when>
                                                                                                                    <c:otherwise>
                                                                                                                         <div class="bx_values bx_hemoglobin">
                                                                                                                                <h2>Your value</h2>
                                                                                                                                <p><strong>${test.value}</strong> ${test.units} </p>
                                                                                                                                <div class="value_arrow">
                                                                                                                                        <img src="<c:url value="/resources/images/blue_arrow.png"/>" />
                                                                                                                                </div>
                                                                                                                            </div>
                                                                                                                    </c:otherwise>
                                                                                                                    </c:choose>
                                                                                                                            <div class="hemoglobin_bx"> 
                                                                                                                                <div class="out_malebx"> 
                                                                                                                                     <div class="female_bx male_bx">                          	
                                                                                                                                         <div class="hemoglobin_first hemoglobin_first_60px"><div class="txt_female_min"><p>${lessRangeFemale}</p></div></div>
                                                                                                                                        <div class="hemoglobin_middle hemoglobin_middle_60px hemoglobin_yellow"></div>
                                                                                                                                        <div class="hemoglobin_last hemoglobin_last_60px"><div class="txt_female_max"><p>${moreRangeFemale}</p></d</div></div>
                                                                                                                                    </div>
                                                                                                                                  
                                                                                                                                </div>      
                                                                                                                                      <b> <p>Female</p></b>
                                                                                                                                <div class="clearfix"></div>
                                                                                                                            </div>    
                                                                                                                            <div class="clearfix"></div> 
                                                                                                                        </div>
                                                                                                             
                                                                                                                        <div class="clearfix"></div>                        
                                                                                                                    </div>
                                                                                                                </li>
                                                                                                            </c:if>
                                                                                                        
                                                                                        </c:if>
                                                                                         <c:if test="${test.rangeValues[0].name=='Female'}">
                                                                                                                            <c:set var="rangeValMale" value="${fn:split(test.rangeValues[1].value, '-')}" />
                                                                                                                            <fmt:parseNumber var="lessRangeMale" value="${fn:trim(rangeValMale[0])}" />
                                                                                                                            <fmt:parseNumber var="moreRangeMale" value="${fn:trim(rangeValMale[1])}" />
                                                                                                                            
                                                                                                                          <c:set var="rangeValFemale" value="${fn:split(test.rangeValues[0].value, '-')}" />
                                                                                                                            <fmt:parseNumber var="lessRangeFemale" value="${fn:trim(rangeValFemale[0])}" />
                                                                                                                            <fmt:parseNumber var="moreRangeFemale" value="${fn:trim(rangeValFemale[1])}" />   
                                                                                                        
                                                                                                                            <fmt:parseNumber var="val" value="${fn:trim(test.value)}" />
                                                                                                        
                                                                                                           <c:if test="${report.gender=='M'}">
                                                                                                                   
                                                                                                            </c:if>
                                                                                                          <c:if test="${report.gender=='F'}">
                                                                                                                    
                                                                                                            </c:if>
                                                                                                        
                                                                                        </c:if>              
                                                                         
                                                                                </c:when>
                                                                                
                                                                                <c:when test="${test.rangeValues[0].name=='NilRange'}">
                                                        <li>
                                                            <div class="title_report">
                                                            <h4>${test.name}</h4>
                                                            <p>Normal Value: Nil</p>
                                                        </div>
                                                        <div class="red_value_choles">
                                                            <div class="redhla_bx">
                                                                    <h4>Your value</h4>
                                                                <p>${test.value} in ${test.units}</p>
                                                            </div>                                
                                                        </div>
                                                    </li>

                                                   </c:when>
                                                    
                                                          <c:otherwise>
                                                              <li>
                                                          <div class="title_report">
                                                          <h4>${test.name}</h4>
                                                      </div>
                                                      <div class="value_choles">
                                                          <div class="hla_bx">
                                                              <p>${test.value} ${test.units}</p>
                                                          </div>                                
                                                      </div>
                                                  </li>
                                                          </c:otherwise>
                                                      </c:choose>
                                              </ul>
                                          </div>
                                             </c:forEach>
                                             
                                             </div>
                                      <div class="appears_bx margin_top_0px"></div>
                                  </div>
                              </td>
                            </tr>  
                            <tr>
                                  <td>
                           <tiles:insertAttribute name="footer"/>
                                      
                                      
                                  <div class="clearfix"></div>
                              </td>
                            </tr>
      </c:if>
  </c:forEach>
</table>

	
</body>
</html>
