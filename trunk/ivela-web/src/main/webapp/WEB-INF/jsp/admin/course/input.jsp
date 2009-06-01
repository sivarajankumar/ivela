<%-- 
    Document   : input Course
    Created on : Aug 7, 2008, 9:14:56 AM
    Author     : Maristella Myrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="cal" uri="/jscalendar" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="course.pageTitle" /></title>
        <link href="../css/course_admin.css" rel="stylesheet" type="text/css" />
        <cal:head/>
        <s:head />
    </head>
    <div id="breadcrumb">
        <p><s:text name="admin.breadcrumb.youAreHere"/></p>
        <ul>
            <li><a href="home.action" title="<s:property value="admin.home.title"/>"><s:text name="admin.home"/></a></li>
            <li><a href="course!list.action" title="<s:property value="course.pageTitle"/>"><s:text name="course.pageTitle"/></a></li>
            <li class="current" title="<s:property value="course.input.sessionTitle"/>"><s:text name="course.input.sessionTitle"/></li>
        </ul>
    </div>
    <h2><s:text name="course.input.sessionTitle" /></h2>
    <s:actionerror />
    <s:form theme="simple" action="course!add.action" method="post" id="form-course">
        <label><s:text name="course.input.name" /></label><br/>
        <s:textfield name="course.name" cssClass="name-course"/><br/>
        <label><s:text name="course.input.description"/></label><br/>
        <s:textarea name="course.description" cssClass="btn-course" cols="50"/><br/>
        <label><s:text name="course.input.image"/></label><br/>
        <s:textarea name="course.image" cssClass="btn-course" cols="50"/><br/>
        <label><s:text name="course.input.targetAudience"/></label><br/>
        <s:textarea name="course.targetAudience" cssClass="textarea-course" cols="50"/><br/>
        <label><s:text name="course.input.contents"/></label><br/>
        <s:textarea name="course.contents" cssClass="textarea-course" cols="50"/><br/>
        
        <s:submit key="course.input.submit" cssClass="btn-save" />
        <s:a href="course!list.action" cssClass="btn-cancel"><s:text name="course.input.cancel" /></s:a>
    </s:form>
    
</html>
