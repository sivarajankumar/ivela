<%-- 
    Document   : edit Discipline
    Created on : Jul 15, 2008, 1:49:08 PM
    Author     : nelson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="discipline.edit.pageTitle" /></title>
        <cal:head/>
        <s:head />
    </head>
    <s:actionerror />
    <tr>
        <td colspan="2"><h1><s:text name="discipline.edit.pageTitle"/></h1></td>
    </tr>
    <s:form action="discipline!update.action">
        <s:hidden name="discipline.id" />
        <s:textfield name="discipline.name" key="discipline.edit.name"/>
        <s:hidden name="discipline.course.id" />
        <s:submit key="discipline.edit.create"/>
    </s:form>
    <s:url id="listDisciplineUrl" action="discipline" method="listByCourse">
        <s:param name="course.id" value="discipline.course.id"/>
    </s:url>
    <s:a href="%{listDisciplineUrl}"><s:text name="course.input.cancel" /></s:a>
</html>