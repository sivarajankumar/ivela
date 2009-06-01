<%-- 
    Document   : edit Tutor
    Created on : Aug 11, 2008, 12:45:15 PM
    Author     : Maristella Myrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="cal" uri="/jscalendar" %>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="tutor.edit.pageTitle" /></title>
        <cal:head/>
        <s:head />
    </head>
    
    <tr>
        <td colspan="2"><h1><s:text name="tutor.edit.title"/></h1></td>
    </tr>
    <s:form action="tutor!update.action">
        <s:hidden name="tutor.id" />
        <s:textfield name="tutor.systemUser" key="tutor.input.systemUser"/>
        <s:textfield name="tutor.grade" key="tutor.input.grade"/>
        <s:submit key="tutor.edit.create"/>
        <s:a href="tutor!list.action" onclick="return confirm('Are you sure?')"><s:text name="tutor.list.action"/></s:a>
    </s:form> 
    
</html>