<%-- 
    Document   : input Note Discipline
    Created on : Jun 5, 2008, 9:14:56 AM
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
            <li><a href="discipline!list.action?course.id=<s:property value="discipline.course.id"/>" title="<s:property value="discipline.course.id"/>"><s:property value="discipline.course.name"/></a></li>
            <li class="current"><s:property value="discipline.name"/></li>
        </ul>
    </div>
    <h2><s:text name="discipline.edit.sessionTitle" /></h2>
    <s:actionerror />
    <s:form theme="simple" action="discipline!update.action" method="post" id="form-discipline">
        <s:hidden name="course.id"/>
        <s:hidden name="discipline.id"/>
        <label><s:text name="discipline.input.name" /></label><br/>
        <s:textfield  name="discipline.name" cssClass="name-discipline"/><br/>
        <s:submit key="discipline.edit.submit" cssClass="btn-save"/>
        <s:url id="listUnitUrl" action="discipline" method="list">
            <s:param name="course.id" value="course.id"/>
        </s:url>
        <s:a href="%{listUnitUrl}" cssClass="btn-cancel"><s:text name="discipline.edit.cancel" /></s:a>
    </s:form>
</html>