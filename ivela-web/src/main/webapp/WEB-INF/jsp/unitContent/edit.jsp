<%-- 
    Document   : edit
    Created on : Aug 22, 2008, 9:10:29 AM
    Author     : emanuelle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="unitContent.edit.pageTitle" /></title>
        <s:head />
    </head>
    <tr>
        <td colspan="2"><h1><s:text name="unitContent.edit.pageTitle"/></h1></td>
    </tr>
    <s:form action="unitContent!update.action">
        <s:hidden name="unitContent.id" value="%{unitContent.id}"/>
        <s:hidden name="unitContent.unit.id" value="%{unitContent.unit.id}"/>
        <s:hidden name ="unitContent.grade.id" value="%{unitContent.grade.id}"/>
        <s:textfield name="unitContent.title" key = "unitContent.edit.title"/>
        <s:textfield name="unitContent.description" key = "unitContent.edit.description"/>
        <s:submit key="unitContent.edit.create"/>
    </s:form>    
    <s:url id="listUnitUrl" action="unitContent" method="listByUnit">
        <s:param name="unit.id" value="unitContent.unit.id"/>
    </s:url>
    <s:a href="%{listUnitUrl}"><s:text name="unit.edit.cancel" /></s:a>
    
</html>