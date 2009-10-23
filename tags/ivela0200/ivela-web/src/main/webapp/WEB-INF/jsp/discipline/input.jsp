<%-- 
    Document   : input Disciplina
    Created on : Jul 15, 2008, 1:48:54 PM
    Author     : nelson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="discipline.input.pageTitle" /></title>
        <s:head />
    </head>
    <s:actionerror />
    <tr>
        <td colspan="2"><h1><s:text name="discipline.input.list"/></h1></td>
    </tr>
    <s:form action="discipline!add.action">
        <s:hidden name="discipline.course.id" />
        <s:textfield name="discipline.name" key="discipline.input.name"/>
        <s:submit key="discipline.input.create"/>
    </s:form>
    <s:url id="listDisciplineUrl" action="discipline" method="listByCourse">
        <s:param name="course.id" value="discipline.course.id"/>
    </s:url>
    <s:a href="%{listDisciplineUrl}"><s:text name="course.input.cancel" /></s:a>
</html>