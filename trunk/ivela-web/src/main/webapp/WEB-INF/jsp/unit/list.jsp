<%-- 
    Document   : list Unit
    Created on : Jul 30, 2008, 3:59:45 PM
    Author     : Maristella Myrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="unit.list.pageTitle" /></title>
        <s:head />
    </head>
    
    <tr>
        <td colspan="2"><h1><s:text name="unit.list.pageTitle"/></h1></td>
    </tr>
    <table border="1">
        <tr>
            <td><b><s:text name="unit.list.id"/></b></td>
            <td><b><s:text name="unit.list.name"/></b></td>
            <td><b><s:text name="unit.list.discipline" /></b></td>
            <td><b><s:text name="unit.list.option"/></b></td>
        </tr>
        <s:iterator value="unitList">
            <tr>
                <td><s:property value="id" /></td>
                <td><s:property value="name" /></td>
                <td><s:property value="discipline.name" /></td>
                
                
                <s:url id="editUrl" action="unit" method="edit">
                    <s:param name="unit.id" value="id"/>
                </s:url>
                
                <s:url id="deleteUrl" action="unit" method="remove">
                    <s:param name="unit.id" value="id"/>
                    
                </s:url>
                
                <td><s:a href="%{editUrl}"><s:text name="unit.list.edit" /></s:a>/<s:a href="%{deleteUrl}" onclick="return confirm('Are you sure?')">Delete</s:a></td>
            </tr>
        </s:iterator>
    </table>
    <s:url id="urlInput" action="unit" method="input" includeParams="false">
        <s:param name="unit.discipline.id" value="discipline.id"/>
    </s:url>
    <s:a href="%{urlInput}"><s:text name="unit.list.new" /></s:a>
    
</html>
