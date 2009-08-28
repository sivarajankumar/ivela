<%-- 
    Document   : input Topic
    Created on : Jun 5, 2008, 9:14:56 AM
    Author     : leoomoreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link href="../css/topic_admin.css" rel="stylesheet" type="text/css" />
        <s:head />
    </head>
    <div id="breadcrumb">
        <p><s:text name="admin.breadcrumb.youAreHere"/></p>
        <ul>
            <li><a href="home.action" title="<s:property value="admin.home.title"/>"><s:text name="admin.home"/></a></li>
            <li><a href="forum!list.action" title="<s:property value="forum.pageTitle"/>"><s:text name="forum.pageTitle"/></a></li>
            <li><a href="topic!listByForum.action?forum.id=<s:property value="topic.forum.id"/>" title="<s:property value="topic.pageTitle"/>"><s:text name="topic.pageTitle"/></a></li>
            <li class="current" title="<s:property value="topic.edit.pageTitle"/>"><s:text name="topic.edit.pageTitle"/></li>
        </ul>
    </div>
    <h2><s:text name="topic.edit.pageTitle" /></h2>
    <s:actionerror />
    <s:form theme="simple" action="topic!update.action" method="post" id="form-topic">
        <s:hidden name="topic.id"/>
        <s:hidden name="topic.forum.id"/>
        <label><s:text name="topic.input.title" /></label><br/>
        <s:textfield  name="topic.title" cssClass="name-topic"/><br/>
        <label><s:text name="topic.input.description" /></label><br/>
        <s:textarea name="topic.description" cssClass="textarea-topic"/><br/>
        <s:submit key="topic.edit.submit" cssClass="btn-save"/>
        <s:a href="topic!list.action" cssClass="btn-cancel"><s:text name="topic.edit.cancel" /></s:a>
    </s:form>
</html>