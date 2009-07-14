<%-- 
    Document   : edit Exercise
    Created on : Jun 27, 2008, 3:04:44 PM
    Author     : Maristella Myrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="exercise.edit.pageTitle" /></title>
        <s:head />
    </head>
    <tr>
        <td colspan="2"><h1><s:text name="exercise.edit.list"/></h1></td>
    </tr>
    <s:form action="exercise!update.action" method="post">
        
        <s:hidden name="exercise.id" />
        <s:hidden name="exercise.createdAt"/>
        <s:textfield name="exercise.discipline.id" key="exercise.edit.discipline"/>
        <s:textfield name="exercise.title" key="exercise.edit.title"/>
        <s:textfield name="exercise.createdBy.id" key="exercise.edit.createdBy"/>
        <s:textfield name="exercise.finals" key="exercise.edit.finals"/>
        <br/>
        <s:text name="Exercise's Question:"/>
        <table border="1">
            <tr>
                <td><b><s:text name="question.list.id"/></b></td>
                <td><b><s:text name="question.list.question"/></b></td>
                <td><b><s:text name="question.list.type"/></b></td> 
                <td><b><s:text name="question.list.options"/></b></td> 
                
            </tr>
            <s:iterator value="questionExerciseList" status ="stat">
                <tr>
                    <td><s:textfield name="questionExerciseList[%{#stat.index}].question.id" readonly="true" label="ID" theme="simple" /></td>
                    <td><s:textfield name="questionExerciseList[%{#stat.index}].question.question"  readonly="true" label="Questions" theme="simple" /></td>
                    <td><s:textfield name="questionExerciseList[%{#stat.index}].question.type" readonly="true" label="Type" theme="simple" /></td>
                    
                    <s:url id="remove" action="exercise" method="removeQuestionExercise">
                        <s:param name="questionExercisem.id" value="id"/>
                        <s:param name="exercise.id" value="exercise.id"/>
                    </s:url>
                    <td><s:a href="%{remove}"><s:text name="exercise.paginator.remove" /></s:a></td>
                </tr>
            </s:iterator>
        </table>
        
        <br/>
        <s:text name="Insert new questions:"/>
        
        <table border="1">    
            <s:iterator value="checkItens">  
                <tr>
                    <td>                       
                        <s:checkbox name="checkItensMap['%{id}'].value" value="value" theme="simple" />
                    </td>
                    
                    <td>
                        <s:property value="object.question"/>  
                    </td>
                    
                    <td>
                        <s:property value="object.type"/>
                    </td>
                    
                </tr>
            </s:iterator>
        </table>
        
        <s:submit value="Update"/>
        
    </s:form>
</html>
