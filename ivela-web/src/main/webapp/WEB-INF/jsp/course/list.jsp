<%-- 
    Document   : list Course
    Created on : Jun 26, 2008, 3:59:45 PM
    Author     : Maristella Myrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<head>
<link href="css/course.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/ead/tools.js"></script>
</head>

<div id="breadcrumb">
    <p><s:text name="breadcrumb.youAreHere"/></p>
    <ul>
        <li><a href="home.action"><s:text name="home.name"/></a></li>
        <li class="current"><s:text name="front.courses"/></li>
    </ul>
</div>

<h1><s:text name="course.list.title"/></h1>
<s:actionerror />
<div id="col-1-courses"> 
    <table class="table-courses">
        <s:iterator value="courseList">
            
            <s:url id="listDisciplineUrl" action="discipline" method="listByCourse">
                <s:param name="course.id" value="id"/>
            </s:url>
            
            
            <tr>
                <td><span><s:property value="name" /></span></td>
                <td align="right"><a class="title-enrollment" href="enrollment!listGrades.action?course.id=<s:property value="id" />"><s:text name="enrollment.add.action"/></a></td>
            </tr>
            <tr>
                <td colspan="2"><p><s:property value="description" /></p></td>
                
            </tr>
            
        </s:iterator>
    </table>
    
    <p class="page-courses"><span class="courses-listed"><s:property value="%{courseList.size()}" />&nbsp;<s:text name="front.courses" /> </span><s:text name="front.of" /> <s:property value="count" /></p>
    
    <jsp:include page="paginator.jsp"/>            
    
        
</div>
