<%-- 
    Document   : input Post
    Created on : Jun 13, 2008, 3:00:26 PM
    Author     : rodrigo
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<link href="css/forum.css" rel="stylesheet" type="text/css" />

        <h2><s:text name="post.input.sessionTitle" /></h2>
        <s:form action="post!add.action" method="POST" enctype="multipart/form-data" theme="simple" id="form-answer">
            <s:hidden name="post.topic.id" />
            <label><s:text name="post.input.title" />:</label><br />
            <s:textfield name="post.title" theme="simple" /><br />
            <label><s:text name="post.input.message" />:</label><br />
            <s:textarea name="post.message" cssStyle="width:300px;" theme="simple" /><br />
            <label><s:text name="repository.input.file" />:</label><br />
            <s:file name="upload" theme="simple"/><br />
            <label><s:text name="repository.input.file" />:</label><br />
            <s:file name="upload" theme="simple"/><br />
            <label><s:text name="repository.input.file" />:</label><br />
            <s:file name="upload" theme="simple"/><br />
            <s:submit value="Send" cssClass="btn-send" theme="simple"/>
        </s:form>