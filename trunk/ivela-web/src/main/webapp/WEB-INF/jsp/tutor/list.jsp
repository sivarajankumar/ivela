<%-- 
    Document   : list Tutor
    Created on : Aug 11, 2008, 4:58:43 PM
    Author     : Maristella Myrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="tutor.list.pageTitle"/></title>
    </head>
    <h2><s:text name="tutor.list.title"/></h2>
    <table border="1">
        <tr>
            <td><b><s:text name="tutor.list.id"/></b></td>
            <td><b><s:text name="tutor.list.systemUser"/></b></td>
            <td><b><s:text name="tutor.list.grade" /></b></td>
            
        </tr>
        <s:iterator value="tutorList">
            <tr>
                <td><s:property value="id" /></td>
                <td><s:property value="systemUser.username" /></td>
                <td><s:property value="grade.name" /></td>
            </tr>
        </s:iterator>
    </table>
</html>
