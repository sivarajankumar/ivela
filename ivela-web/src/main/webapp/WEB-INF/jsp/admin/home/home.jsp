<%-- 
    Document   : show grade
    Created on : Sep 15, 2008, 2:07:13 PM
    Author     : marcus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--@ taglib prefix="cal" uri="/jscalendar" --%>

<s:actionerror />

<div id="breadcrumb">
    <p><s:text name="breadcrumb.youAreHere"/></p>
    <ul>
        <li class="current"><s:text name="admin.home" /></li>
    </ul>
</div>

<div id="col-1-home">
    <h2><s:text name="admin.pageTitle" /></h2>
    <div id="vertical_container2" >
        <h3 class="no_grades"><a href="home.action" title="<s:property value="admin.controlPanel" />"><s:text name="admin.controlPanel" /></a></h3>
        <h3 class="no_grades"><a href="systemUser!show.action" title="<s:property value="back.people.title" />"><s:text name="admin.people" /></a></h3>
        <h3 class="no_grades"><a href="course!show.action" title="<s:property value="back.courses.title" />"><s:text name="admin.courses" /></a></h3>
        <h3 class="no_grades"><a href="grade!show.action" title="<s:property value="aback.grades.title" />"><s:text name="admin.grade" /></a></h3>   
    </div>
</div>
<!-- end col-1-home -->

<div id="showCourse">
    <div id="col-2-home">
        <s:text name="home.welcome"/>
    </div>    
</div>

<!-- end col-2-home -->
<br class="clear" />

<!-- end content -->
<br class="clear" />
