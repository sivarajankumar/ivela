<%-- 
    Document   : list Unit
    Created on : May 15, 2008, 1:38:17 PM
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="unit.pageTitle" /></title>
        <link href="../css/unit_admin.css" rel="stylesheet" type="text/css" />
         <link href="../css/base_admin.css"  rel="stylesheet" type="text/css" />
        <s:head />
        <cal:head />
    </head>
    <div id="breadcrumb">
        <p><s:text name="admin.breadcrumb.youAreHere"/></p>
        <ul>
            <li><a href="home.action" title="<s:property value="admin.home.title"/>"><s:text name="admin.home"/></a></li>
            <li><a href="course!list.action" title="<s:property value="course.pageTitle"/>"><s:text name="course.pageTitle"/></a></li>
            <li><a href="discipline!list.action?course.id=<s:property value="discipline.course.id"/>" title="<s:property value="discipline.course.name"/>"><s:property value="discipline.course.name"/></a></li>
            
            <li class="current"><s:property value="discipline.name"/></li>
        </ul>
    </div>
    
    <h2><s:text name="unit.pageTitle" /></h2>
    <h3><s:text name="unit.discipline" />: <s:property value="discipline.name" /></h3>
    <s:url id="addUrl" action="unit" method="input">
        <s:param name="discipline.id" value="discipline.id"/>
    </s:url>
    <s:a href="%{addUrl}" cssClass="add-unit"><s:text name="unit.list.add" /></s:a>
    
    <s:actionerror />
    
    <table id="units">
        <s:iterator value="unitList">
            <s:url id="editUrl" action="unit" method="edit">
                <s:param name="unit.id" value="id"/>
            </s:url>                
            <tr>
                <td class="title-unit"><s:a href="%{editUrl}"><s:property value="name" /></s:a></td>
                <td class="edit-unit"><s:a href="%{editUrl}"><s:text name="unit.list.edit" /></s:a></td>
                <td class="delete-unit"><a href="unit!remove.action?unit.id=<s:property value="id" />&discipline.id=<s:property value="discipline.id" />" onclick="return confirm('<s:text name="admin.alert.remove"/>')"><s:text name="unit.list.remove" /></a></td>
            </tr>              
        </s:iterator>
    </table>
</html>