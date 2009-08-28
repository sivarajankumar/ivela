<%-- 
    Document   : list Discipline
    Created on : May 15, 2008, 1:38:17 PM
    Author     : rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="discipline.pageTitle" /></title>
        <link href="../css/discipline_admin.css" rel="stylesheet" type="text/css" />
         <link href="../css/base_admin.css"  rel="stylesheet" type="text/css" />
        <s:head />
    </head>
    
    <div id="breadcrumb">
        <p><s:text name="admin.breadcrumb.youAreHere"/></p>
        <ul>
            <li><a href="home.action" title="<s:property value="admin.home.title"/>"><s:text name="admin.home"/></a></li>
            <li><a href="course!list.action" title="<s:property value="course.pageTitle"/>"><s:text name="course.pageTitle"/></a></li>
            <li class="current"><s:property value="course.name"/></li>
        </ul>
    </div>
    
    <h2><s:text name="discipline.pageTitle" /></h2>
    <h3><s:text name="discipline.course" />: <s:property value="course.name" /></h3>
    <s:url id="addUrl" action="discipline" method="input">
        <s:param name="course.id" value="course.id"/>
    </s:url>
    <s:a href="%{addUrl}" cssClass="add-discipline"><s:text name="discipline.list.add" /></s:a>
    
    <s:actionerror />
    
    <table id="disciplines">
        <s:iterator value="disciplineList">
            <s:url id="editUrl" action="discipline" method="edit">
                <s:param name="course.id" value="course.id"/>
                <s:param name="discipline.id" value="id"/>
            </s:url>                
            <tr>
                <td class="title-discipline"><s:a href="%{editUrl}"><s:property value="name" /></s:a></td>
                <td class="edit-discipline"><s:a href="%{editUrl}"><s:text name="discipline.list.edit" /></s:a></td>
                <td class="delete-discipline"><a href="discipline!remove.action?course.id=<s:property value="course.id" />&discipline.id=<s:property value="id" />" onclick="return confirm('<s:text name="admin.alert.remove"/>')"><s:text name="discipline.list.remove" /></a></td>
                <td class="show-units"><a href="unit!list.action?discipline.id=<s:property value="id" />"><s:text name="discipline.list.show" /></a></td>
            </tr>
        </s:iterator>
    </table>
</html>
