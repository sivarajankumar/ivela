<%-- 
    Document   : edit
    Created on : Jul 24, 2008, 12:45:15 PM
    Author     : nelson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="cal" uri="/jscalendar" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="grade.edit.pageTitle" /></title>
        <cal:head/>
        <s:head />
    </head>
    <tr>
        <td colspan="2"><h1><s:text name="grade.edit.title"/></h1></td>
    </tr>
    <s:form action="grade!update.action">
        <s:hidden name="grade.id" />
        <s:hidden name="grade.repositoryStructure"/>
        <s:select list="courseList" listKey="id" listValue="name" name="grade.course.id" key="grade.input.course" />
        <s:textfield name="grade.name" key="grade.input.name"/>
        <s:textfield name="grade.period" key="grade.input.period"/>
        <s:textfield name="grade.maxStudents" key="grade.input.maxStudents"/>
        <s:select name="grade.status" list="#{'0':'Inactive', '1':'Period of enrollment', '2':'Registration finished'}" key="grade.input.status" />
        <s:select list="systemUserList" listKey="id" listValue="username" name="grade.coordinator.id" key="grade.input.coordinator" />
        <s:radio name="grade.requiresEnrollmentValidation" key="grade.input.requiresEnrollmentValidation" list="#{'true':'true','false':'false'}" />
        <cal:jscalendar name="grade.startDatetime" title="grade.input.startDatetime" />
        <cal:jscalendar name="grade.endDatetime" title="grade.input.endDatetime" />
        <s:submit key="grade.edit.create"/>
        <s:a href="grade!list.action" onclick="return confirm('Are you sure?')"><s:text name="grade.list.action"/></s:a>
    </s:form> 
</html>