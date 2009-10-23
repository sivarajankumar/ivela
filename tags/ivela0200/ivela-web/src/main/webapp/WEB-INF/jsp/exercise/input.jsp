<%-- 
    Document   : input Exercise
    Created on : Jun 24, 2008, 2:31:48 PM
    Author     : Maristella Myrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="exercise.input.pageTitle" /></title>
        <s:head/> 
    </head>
    
    <tr>
        <td colspan="2"><h1><s:text name="exercise.input.list"/></h1></td>
    </tr>
    <s:form action="exercise!add.action" method="get">
        <s:hidden name="exercise.grade.id"/>
        <s:hidden name="exercise.createdAt"/>
        <s:textfield name="exercise.title" size="56" key="exercise.input.title"/>
        <div>
            <s:text name="exercise.input.configurate"/>
        </div>  
        <div>
            
        </div>   
        <br />
        <s:submit key="exercise.input.create"/>
        <s:a href="exercise!list.action" onclick="return confirm('Are you sure?')"><s:text name="exercise.show.cancel" /></s:a>
    </s:form> 
    
</html>