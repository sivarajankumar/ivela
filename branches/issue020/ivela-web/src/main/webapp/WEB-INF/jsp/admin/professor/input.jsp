<%-- 
    Document   : input Professor
    Created on : Aug 7, 2008, 9:14:56 AM
    Author     : Maristella Myrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="professor.pageTitle" /></title>
        <link href="../css/professor_admin.css" rel="stylesheet" type="text/css" />
        <s:head />
    </head>
    <div id="breadcrumb">
        <p><s:text name="admin.breadcrumb.youAreHere"/></p>
        <ul>
            <li><a href="home.action" title="<s:property value="admin.home.title"/>"><s:text name="admin.home"/></a></li>
            <li><a href="professor!list.action" title="<s:property value="professor.pageTitle"/>"><s:text name="professor.pageTitle"/></a></li>
            <li class="current" title="<s:property value="professor.input.sessionTitle"/>"><s:text name="professor.input.sessionTitle"/></li>
        </ul>
    </div>
    <h2><s:text name="professor.input.sessionTitle" /></h2>
    <s:actionerror />
    <s:form theme="simple" action="grade!addProfessor.action" method="post" id="form-professor">
        <label><s:hidden name="grade.id" /></label><br/>
        <label><s:text name="professor.input.systemUser"/></label><br/>
        <s:select list="systemUserList" listKey="id" listValue="username" name="systemUser.id"></s:select>
        
        <s:submit key="professor.input.submit" cssClass="btn-save" />
        <s:a href="grade!list.action" cssClass="btn-cancel"><s:text name="professor.input.cancel" /></s:a>
    </s:form>
</html>
