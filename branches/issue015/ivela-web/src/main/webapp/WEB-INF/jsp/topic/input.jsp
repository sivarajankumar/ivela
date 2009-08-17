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
        <title><s:text name="topic.input.pageTitle" /></title>
        <s:head theme="ajax" />
    </head>
    <tr>
        <td colspan="2"><h1><s:text name="topic.input.list"/></h1></td>
    </tr>
    <s:form action="topic!add.action">
        
        <s:textfield name="topic.forum.id" readonly="true" key="topic.input.id"/>
        <s:textfield name="topic.title" key="topic.input.title"/>
        <s:textfield name="topic.description" key="topic.input.description" />
        <s:submit key="topic.input.create"/>
        <s:url id="urlListByForum" action="topic" method="listByForum" includeParams="false">
            <s:param name="forum.id" value="topic.forum.id"/>
        </s:url>
        <s:a href="%{urlListByForum}" onclick="return confirm('Are you sure?')">,<s:text name="topic.input.cancel" /></s:a>
    </s:form>
</html>
