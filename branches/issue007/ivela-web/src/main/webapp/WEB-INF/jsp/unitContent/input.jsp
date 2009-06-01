<%-- 
    Document   : input
    Created on : Aug 21, 2008, 3:34:40 PM
    Author     : emanuelle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <s:head />
    </head>
        <tr>
            <td colspan="2"><h1><s:text name="unitContent.input.sessionTitle"/></h1></td>
        </tr>
        <s:form action="unitContent!add.action">
            <s:hidden name="unit.id" value="%{unit.id}"/>
            <s:hidden name="grade.id" value="%{grade.id}"/>
            <s:textfield name="unitContent.title" key="unitContent.input.title"/>
            <s:textfield name="unitContent.description" key="unitContent.input.description"/>
            <s:submit key="unitContent.input.create"/>
        </s:form>
        <s:url id="listUnitUrl" action="unitContent" method="listByUnit">
            <s:param name="unit.id" value="unit.id"/>
        </s:url>
        <s:a href="%{listUnitUrl}"><s:text name="unit.input.cancel" /></s:a>
</html>
