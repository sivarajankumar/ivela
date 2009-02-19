<%-- 
    Document   : input Grade
    Created on : Aug 7, 2008, 9:14:56 AM
    Author     : leoomoreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="cal" uri="/jscalendar" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="grade.pageTitle" /></title>
        <link href="../css/grade_admin.css" rel="stylesheet" type="text/css" />
        <cal:head />
        <s:head />
    </head>
    <div id="studentesEnrollmentOk">
        <s:form id="teste">
       
            <s:iterator value="studentsEnrollment" status="stat">
                <input type="hidden" name="<s:property/>" value="<s:property/>">
            </s:iterator> 
        
        </s:form>
    </div>
    
    <%--
    <div id="breadcrumb">
        <p><s:text name="admin.breadcrumb.youAreHere"/></p>
        <ul>
            <li><a href="home.action" title="<s:property value="admin.home.title"/>"><s:text name="admin.home"/></a></li>
            <li><a href="grade!list.action" title="<s:property value="grade.pageTitle"/>"><s:text name="grade.pageTitle"/></a></li>
            <li class="current" title="<s:property value="grade.input.sessionTitle"/>"><s:text name="grade.input.sessionTitle"/></li>
        </ul>
    </div>
    <h2><s:text name="grade.input.pageTitle" /></h2>
    <s:actionerror />
    <s:form theme="simple" action="grade!add.action" method="post" id="form-grade">
        <label><s:text name="grade.input.course" /></label><br />
        <s:select list="courseList" listKey="id" listValue="name" name="grade.course.id" /><br />
        <label><s:text name="grade.input.name" /></label><br />
        <s:textfield name="grade.name" cssClass="name-grade" />
        <label><s:text name="grade.input.period" /></label><br />
        <s:textfield name="grade.period" cssClass="name-grade" /><br />
        <label><s:text name="grade.input.polo" /></label><br />
        <s:select list="poloList" listKey="id" listValue="name" name="grade.polo.id" /><br />
        <label><s:text name="grade.input.maxStudents" /></label><br />
        <s:textfield name="grade.maxStudents" cssClass="name-grade" /><br />
        <label><s:text name="grade.input.status" /></label><br />
        <s:select name="grade.status" list="#{'0':'Inactive', '1':'Period of enrollment', '2':'Registration finished'}" /><br />
        <label><s:text name="grade.input.coordinator" /></label><br />
        <s:select list="systemUserList" listKey="id" listValue="username" name="grade.coordinator.id" /><br />        
        <label><s:text name="grade.input.requiresEnrollmentValidation" /></label><br/>
        <s:radio name="grade.requiresEnrollmentValidation" value="false" list="#{'true':'true','false':'false'}" /><br />
        <label><s:text name="grade.input.startDatetime" /></label><br />
        <cal:jscalendar name="grade.startDatetime" title="grade.input.startDatetime" /><br />
        <label><s:text name="grade.input.endDatetime" /></label><br />
        <cal:jscalendar name="grade.endDatetime" title="grade.input.endDatetime" /><br />
        <s:submit key="grade.input.create" cssClass="btn-save" />
        <s:a href="grade!list.action" cssClass="btn-cancel"><s:text name="grade.input.cancel" /></s:a>
    </s:form>
    --%>
</html>
