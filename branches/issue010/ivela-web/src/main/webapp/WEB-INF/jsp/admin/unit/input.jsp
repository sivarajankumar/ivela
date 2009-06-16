<%-- 
    Document   : input Unit
    Created on : Aug 5, 2008, 9:14:56 AM
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="cal" uri="/jscalendar" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="unit.pageTitle" /></title>
        <link href="../css/unit_admin.css" rel="stylesheet" type="text/css" />
        <cal:head/>
        <s:head />
    </head>
    <div id="breadcrumb">
        <p><s:text name="admin.breadcrumb.youAreHere"/></p>
        <ul>
            <li><a href="home.action" title="<s:property value="admin.home.title"/>"><s:text name="admin.home"/></a></li>
            <li><a href="course!list.action" title="<s:property value="course.pageTitle"/>"><s:text name="course.pageTitle"/></a></li>
            <li><a href="discipline!list.action?course.id=<s:property value="discipline.course.id"/>" title="<s:property value="discipline.course.name"/>"><s:property value="discipline.course.name"/></a></li>
            <li><a href="unit!list.action?discipline.id=<s:property value="discipline.id"/>" title="<s:property value="discipline.pageTitle"/>"><s:property value="discipline.name"/></a></li>
            <li class="current" title="<s:property value="unit.input.sessionTitle"/>"><s:text name="unit.input.sessionTitle"/></li>
        </ul>
    </div>
    <h2><s:text name="unit.input.sessionTitle" /></h2>
    <s:actionerror />
    <s:form theme="simple" action="unit!add.action" method="post" id="form-unit">
        <s:hidden name="discipline.id"/>
        <label><s:text name="unit.input.name" /></label><br/>
        <s:textfield name="unit.name" cssClass="name-unit"/><br/>
        <s:submit key="unit.input.submit" cssClass="btn-save" />
        <s:url id="listUnitUrl" action="unit" method="list">
            <s:param name="discipline.id" value="discipline.id"/>
        </s:url>
        <s:a href="%{listUnitUrl}" cssClass="btn-cancel"><s:text name="unit.input.cancel" /></s:a>
    </s:form>
</html>
