<%-- 
    Document   : input BulletinBoard
    Created on : Jun 5, 2008, 9:14:56 AM
    Author     : leoomoreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="bulletin.pageTitle" /></title>
        <link href="../css/bulletin_admin.css" rel="stylesheet" type="text/css" />
        <s:head />
    </head>

        <div id="breadcrumb">
            <p><s:text name="admin.breadcrumb.youAreHere"/></p>
            <ul>
                <li><a href="home.action" title="<s:property value="admin.home.title"/>"><s:text name="admin.home"/></a></li>
                <li><a href="bulletinBoard!list.action" title="<s:property value="bulletin.pageTitle"/>"><s:text name="bulletin.pageTitle"/></a></li>
                <li class="current" title="<s:property value="bulletin.edit.sessionTitle"/>"><s:text name="bulletin.edit.sessionTitle"/></li>
            </ul>
        </div>
        <h2><s:text name="bulletin.edit.sessionTitle" /></h2>
        <s:actionerror />
        <s:form theme="simple" action="bulletinBoard!update.action" method="post" id="form-bulletin">
            <s:hidden name="bulletinBoard.id"/>
            <label><s:text name="bulletin.input.title" /></label><br/>
            <s:textfield  name="bulletinBoard.title" cssClass="name-bulletin"/><br/>
            <label><s:text name="bulletin.input.description" /></label><br/>
            <s:textarea name="bulletinBoard.description" cssClass="textarea-bulletin"/><br/>
            <s:submit key="bulletin.edit.submit" cssClass="btn-save"/>
            <s:a href="bulletinBoard!list.action" cssClass="btn-cancel"><s:text name="bulletin.edit.cancel" /></s:a>
        </s:form>
  
</html>