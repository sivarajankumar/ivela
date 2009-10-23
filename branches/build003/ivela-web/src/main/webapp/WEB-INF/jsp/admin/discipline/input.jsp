<%-- 
    Document   : input Discipline
    Created on : Aug 5, 2008, 9:14:56 AM
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="discipline.pageTitle" /></title>
        <link href="../css/discipline_admin.css" rel="stylesheet" type="text/css" />
        <s:head />
    </head>
    <div id="breadcrumb">
        <p><s:text name="admin.breadcrumb.youAreHere"/></p>
        <ul>
            <li><a href="home.action" title="<s:property value="admin.home.title"/>"><s:text name="admin.home"/></a></li>
            <li><a href="course!list.action" title="<s:property value="course.pageTitle"/>"><s:text name="course.pageTitle"/></a></li>
            <li><a href="discipline!list.action?course.id=<s:property value="course.id"/>" title="<s:property value="course.id"/>"><s:property value="course.name"/></a></li>
            <li class="current" title="<s:property value="discipline.input.sessionTitle"/>"><s:text name="discipline.input.sessionTitle"/></li>
        </ul>
    </div>
    <h2><s:text name="discipline.input.sessionTitle" /></h2>
    <s:actionerror />
    <s:form theme="simple" action="discipline!add.action" method="post" id="form-discipline">
        <s:hidden name="course.id"/>
        <label><s:text name="discipline.input.name" /></label><br/>
        <s:textfield name="discipline.name" cssClass="name-discipline"/><br/>
        <s:submit key="discipline.input.submit" cssClass="btn-save" />
        <s:url id="listUnitUrl" action="discipline" method="list">
            <s:param name="course.id" value="course.id"/>
        </s:url>
        <s:a href="%{listUnitUrl}" cssClass="btn-cancel"><s:text name="discipline.input.cancel" /></s:a>
    </s:form>
</html>
