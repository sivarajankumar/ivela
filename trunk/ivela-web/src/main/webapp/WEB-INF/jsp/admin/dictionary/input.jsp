<%-- 
    Document   : input
    Created on : Oct 16, 2008, 4:43:27 PM
    Author     : nelson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="titlePage.dictionary" /></title>
    </head>
    <body>
        <h2><s:text name="dictionary.input" /></h2>
        <s:form action="dictionary!add.action" method="post" >
            <s:textfield name="dictionary.title" label="Title"/>
            <s:textarea name="dictionary.description" cols="30" label="Description"/>
            <s:submit value="Submit"/>
        </s:form>
    </body>
</html>
