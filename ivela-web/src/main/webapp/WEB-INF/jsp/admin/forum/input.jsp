<%-- 
    Document   : input Forum
    Created on : Aug 5, 2008, 9:14:56 AM
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="cal" uri="/jscalendar" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="forum.pageTitle" /></title>
        <link href="../css/forum_admin.css" rel="stylesheet" type="text/css" />
        <cal:head/>
        <s:head />
    </head>
    <div id="breadcrumb">
        <p><s:text name="admin.breadcrumb.youAreHere"/></p>
        <ul>
            <li><a href="home.action" title="<s:property value="admin.home.title"/>"><s:text name="admin.home"/></a></li>
            <li><a href="forum!list.action" title="<s:property value="forum.pageTitle"/>"><s:text name="forum.pageTitle"/></a></li>
            <li class="current" title="<s:property value="forum.input.pageTitle"/>"><s:text name="forum.input.pageTitle"/></li>
        </ul>
    </div>
    <h2><s:text name="forum.input.pageTitle" /></h2>
    <s:actionerror />
    <s:form theme="simple" action="forum!add.action" method="post" id="form-forum">
        <label><s:text name="forum.input.title" /></label><br/>
        <s:textfield name="forum.title" cssClass="name-forum"/><br/>
        <label><s:text name="forum.input.description"/></label><br/>
        <s:textarea name="forum.description" cssClass="textarea-forum" cols="50"/><br/>
        <label><s:text name="forum.input.grade" /></label><br/>
        <%--<s:textfield name="forum.grade.id" cssClass="name-forum"/><br/>--%>
        <s:select list="gradeList" listKey="id" listValue="name" name="forum.grade.id"></s:select><br/>
        <label><s:text name="forum.input.createTopic"/></label><br/>
        <s:radio name="forum.studentCreateTopic" key="forum.input.createTopic" list="#{'true':'true','false':'false'}" /><br/>
        <label><s:text name="forum.input.uploadPost"/></label><br/>
        <s:radio name="forum.studentUploadPost" key="forum.input.uploadPost" list="#{'true':'true','false':'false'}" /><br/>
        <label><s:text name="forum.input.uploadRepository"/></label><br/>
        <s:radio name="forum.studentUploadRepository" key="forum.input.uploadRepository" list="#{'true':'true','false':'false'}" /><br/>
        <label><s:text name="forum.input.linkPost"/></label><br/>
        <s:radio name="forum.studentLinkPost" key="forum.input.linkPost" list="#{'true':'true','false':'false'}" /><br/>
        <label><s:text name="forum.input.public"/></label><br/>
        <s:radio name="forum.public1" key="forum.input.public" list="#{'true':'true','false':'false'}" /><br/>
        <s:submit key="forum.input.submit" cssClass="btn-save" />
        <s:a href="forum!list.action" cssClass="btn-cancel"><s:text name="forum.input.cancel" /></s:a>
    </s:form>
</html>
