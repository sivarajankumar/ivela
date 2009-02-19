<%-- 
    Document   : list Grade
    Created on : Jul 23, 2008, 4:58:43 PM
    Author     : nelson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="grade.list.pageTitle"/></title>
    </head>
    
    <h2><s:text name="grade.list.title"/></h2>
    <a href="grade!input.action"><s:text name="grade.add.action"/></a>
    <table border="1" >
        <tr>
            <td><b><s:text name="grade.list.name"/></b></td>
            <td><b><s:text name="grade.list.course"/></b></td>
            <td><b><s:text name="grade.list.period" /></b></td>
            <td><b><s:text name="grade.list.maxStudents"/></b></td>
            <td><b><s:text name="grade.list.status"/></b></td>
            <td><b><s:text name="grade.list.requiresEnrollmentValidation"/></b></td>
            <td><b><s:text name="grade.list.coordinator"/></b></td>
            <td><b><s:text name="grade.list.startDatetime"/></b></td>
            <td><b><s:text name="grade.list.endDatetime"/></b></td>
            <td><b><s:text name="grade.list.options"/></b></td>
        </tr>
        <s:iterator value="gradeList">
            <tr>
                <td><s:property value="name" /></td>
                <td><s:property value="course.name" /></td>
                <td><s:property value="period" /></td>
                <td><s:property value="maxStudents" /></td>
                <td><s:property value="status" /></td>
                <td><s:property value="requiresEnrollmentValidation" /></td>
                <td><s:property value="coordinator.username" /></td>
                <td><s:property value="startDatetime" /></td>
                <td><s:property value="endDatetime" /></td>
                
                <s:url id="editUrl" action="grade" method="edit">
                    <s:param name="grade.id" value="id"/>
                </s:url>
                
                <s:url id="deleteUrl" action="grade" method="remove">
                    <s:param name="grade.id" value="id"/>
                </s:url>
                
                <td><s:a href="%{editUrl}"><s:text name="grade.edit.action"/></s:a>/<s:a href="%{deleteUrl}" onclick="return confirm('Are you sure?')"><s:text name="grade.remove.action"/></s:a></td>
            </tr>
        </s:iterator>
    </table>
</html>
