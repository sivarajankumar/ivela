<%-- 
    Document   : list Question
    Created on : Jun 20, 2008, 9:11:55 AM
    Author     : emanuelle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <s:actionerror />
    <tr>
        <td colspan="2"><h1><s:text name="question.list.sessionTitle"/></h1></td>
    </tr>
    <table border="1">
        <tr>
            <td><b><s:text name="question.list.id"/></b></td>
            <td><b><s:text name="question.list.question"/></b></td>               
            <td><b><s:text name="question.list.type"/></b></td>   
            <td><b><s:text name="question.list.createdBy"/></b></td>         
            <td><b><s:text name="question.list.createdAt"/></b></td>  
            <td><b><s:text name="question.list.options"/></b></td>  
        </tr>
        <s:iterator value="questionList">
            <tr>
                <s:url id="listQuestionUrl" action="question" method="show">
                    <s:param name="question.id" value="id"/>
                </s:url> 
                <td><s:a href="%{listQuestionUrl}"><s:property value="id"/></s:a></td>
                <td><s:a href="%{listQuestionUrl}"><s:property value="question"/></s:a></td>
                <td><s:property value="type"/></td>
                <td><s:property value="createdBy.profile.name"/></td>
                <td><s:property value="createdAt"/></td>
                
                <s:url id="editUrl" action="question" method="edit">
                    <s:param name="question.id" value="id"/>
                </s:url>
                <s:url id="deleteUrl" action="question" method="remove">
                    <s:param name="question.id" value="id"/>
                </s:url>
                <td><s:a href="%{editUrl}">Edit</s:a>/<s:a href="%{deleteUrl}" onclick="return confirm('Are you sure?')">Delete</s:a></td>                    
            </tr>
        </s:iterator>
    </table>
    <s:a href="question!input.action">Create a new question</s:a>
    
</html>
