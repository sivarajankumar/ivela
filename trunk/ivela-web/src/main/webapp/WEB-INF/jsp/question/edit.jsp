<%-- 
    Document   : edit Question
    Created on : Jun 25, 2008, 1:12:13 PM
    Author     : emanuelle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <tr>
        <td colspan="2">
            <h1><s:text name="question.edit.sessionTitle"/></h1>
            <s:actionerror />
        </td>
    </tr>
    <s:form action="question!update.action" method="post" name="form">
        <tr> 
            <td><s:textfield name="question.question" value="%{question.question}" key="question.edit.description"/></td>
        </tr>
        <s:hidden name="question.id" value="%{question.id}"/>
        <s:hidden name="question.type" value="%{question.type}"/>
        <s:if test="question.type==2">
            <tr>
                <td><h2><s:text name="Option" /></h2></td>
            </tr>
            <table border="1">
                <s:iterator id="listObjectiveAnswer" value="listObjectiveAnswer" status="stat">
                    <tr>
                        <s:if test="id==objectiveQuestion.correctAnswer.id">
                            <td><input  id ="question_radio<s:property value="%{#stat.index}"/>" type="radio" value="<s:property value="%{#stat.index}"/>" checked name="radio"/></td>
                        </s:if>
                        <s:else>
                            <td><input  id ="question_radio<s:property value="%{#stat.index}"/>" type="radio" value="<s:property value="%{#stat.index}"/>" name="radio"/></td>
                        </s:else>
                        <td><s:textfield name="listObjectiveAnswer[%{#stat.index}].answer" value="%{answer}" key="question.edit.answer" theme="simple"/></td>
                        <s:hidden name="listObjectiveAnswer[%{#stat.index}].id" value="%{id}"/>
                    </tr>
                </s:iterator>
            </table>
            <s:hidden name="objectiveQuestion.id" value="%{objectiveQuestion.id}"/>
        </s:if>
        <s:submit value="question.edit.update"/>
    </s:form>
    
</html>
