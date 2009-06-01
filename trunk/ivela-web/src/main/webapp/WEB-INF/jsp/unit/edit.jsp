<%-- 
    Document   : edit Unit
    Created on : Jul 30, 2008, 3:02:43 PM
    Author     : Maristella Myrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="unit.edit.pageTitle" /></title>
        <s:head />
    </head>
    <tr>
        <td colspan="2"><h1><s:text name="unit.edit.pageTitle"/></h1></td>
    </tr>
    <s:form action="unit!update.action">
        <s:hidden name="unit.id"/>
        <s:textfield name="unit.name" key = "unit.edit.name"/>
        <s:hidden name ="unit.discipline.id"/>
        <s:submit key="unit.edit.create"/>
    </s:form>    
    <s:url id="listUnitUrl" action="unit" method="listByDiscipline">
        <s:param name="discipline.id" value="unit.discipline.id"/>
    </s:url>
    <s:a href="%{listUnitUrl}"><s:text name="unit.edit.cancel" /></s:a>
</html>