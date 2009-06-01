<%-- 
    Document   : input Forum
    Created on : Jun 5, 2008, 9:14:56 AM
    Author     : leoomoreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="forum.input.pageTitle" /></title>
        <s:head />
    </head>
    <tr>
        <td colspan="2"><h1><s:text name="forum.input.list"/></h1></td>
    </tr>
    <s:form action="forum!add.action" method="post">
        <s:textfield name="forum.grade.id" key="forum.input.grade"/>
        <s:textfield name="forum.title" key="forum.input.title"/>
        <s:radio name="forum.studentCreateTopic" key="forum.input.createTopic" list="#{'true':'true','false':'false'}" />
        <s:radio name="forum.studentUploadPost" key="forum.input.uploadPost" list="#{'true':'true','false':'false'}" />
        <s:radio name="forum.studentUploadRepository" key="forum.input.uploadRepository" list="#{'true':'true','false':'false'}" />
        <s:radio name="forum.studentLinkPost" key="forum.input.linkPost" list="#{'true':'true','false':'false'}" />
        <s:submit key="forum.input.create"/>
        <s:a href="forum!list.action" onclick="return confirm('Are you sure?')">Cancel</s:a>
    </s:form>
</html>
