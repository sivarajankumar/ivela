<%-- 
    Document   : input Unit
    Created on : Jul 30, 2008, 3:59:50 PM
    Author     : Maristella Myrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <s:head />
    </head>
    
    <tr>
        <td colspan="2"><h1><s:text name="unit.input.sessionTitle"/></h1></td>
    </tr>
    <s:form action="unit!add.action">
        <s:hidden name="unit.discipline.id"/>
        <s:textfield name="unit.name" key="unit.input.name"/>
        <s:submit key="unit.input.create"/>
    </s:form>
    <s:url id="listUnitUrl" action="unit" method="listByDiscipline">
        <s:param name="discipline.id" value="unit.discipline.id"/>
    </s:url>
    <s:a href="%{listUnitUrl}"><s:text name="unit.input.cancel" /></s:a>
    
</html>
