<%-- 
    Document   : list Course
    Created on : Aug 7, 2008, 9:14:56 AM
    Author     : Maristella Myrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/course_admin.css" rel="stylesheet" type="text/css" />
         <link href="../css/base_admin.css"  rel="stylesheet" type="text/css" />
        <s:head />
    </head>
    <div id="breadcrumb">
        <p><s:text name="admin.breadcrumb.youAreHere"/></p>
        <ul>
            <li><a href="home.action" title="<s:property value="admin.home.title"/>"><s:text name="admin.home"/></a></li>
            <li class="current" title="<s:property value="course.pageTitle"/>"><s:text name="course.pageTitle"/></li>
        </ul>
    </div>
    <h2><s:text name="course.pageTitle" /></h2>
    <s:a href="course!input.action" cssClass="add-course"><s:text name="course.list.add" /></s:a>
    
    <s:actionerror />
    <table id="courses">
        <s:iterator value="courseList">
            <s:url id="editUrl" action="course" method="edit">
                <s:param name="course.id" value="id"/>
            </s:url>
            
            <s:url id="deleteUrl" action="course" method="remove">
                <s:param name="course.id" value="id"/>
            </s:url>
            
            <tr>
                <td class="title-course"><s:a href="%{editUrl}"><s:property value="name" /></s:a></td>
                <td class="edit-course"><s:a href="%{editUrl}"><s:text name="course.list.edit" /></s:a></td>
                <td class="delete-course"><a href="course!remove.action?course.id=<s:property value="id" />" onclick="return confirm('<s:text name="admin.alert.remove"/>')"><s:text name="course.list.remove" /></a></td>
                <td class="show-disciplines"><a href="discipline!list.action?course.id=<s:property value="id" />"><s:text name="course.list.disciplines" /></a></td>
            </tr>
            <tr>
                <td colspan="4" class="course-description">
                    <p>
                        <s:if test="description.length() > 250">
                            <s:property value="description.substring(0,250)" /> ...
                        </s:if>
                        <s:else>
                            <s:property value="description" />
                        </s:else>                            
                    </p>
                </td>
            </tr>                
        </s:iterator>
    </table>
    
</html>