<%-- 
    Document   : input Exam
    Created on : Jun 19, 2008, 3:03:17 PM
    Author     : Maristella Myrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="cal" uri="/jscalendar" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="exam.input.pageTitle" /></title>
        <cal:head/>
        <s:head />
    </head>
        <s:actionerror />
        <tr>
            <td colspan="2"><h1><s:text name="exam.input.list"/></h1></td>
        </tr>
        <s:form action="exam!add.action" method="get">
            <s:hidden name="exam.id"/>
            <s:textfield name="exam.discipline.id" key="exam.input.discipline"/>
            <s:textfield name="exam.title" key="exam.input.title"/>
            <s:textfield name="exam.maxTimes" key="exam.input.maxTimes"/>
            <s:textfield name="exam.duration" key="exam.input.duration"/>
            <s:textfield name="exam.questionsPerPage" key="exam.input.questionsPerPage"/>
            <s:textfield name="exam.randomizeQuestionsOrder" key="exam.input.randomizeQuestionsOrder"/>
            <s:textfield name="exam.navigable" key="exam.input.navigable"/>
            <s:textfield name="exam.finals" key="exam.input.finals"/>
            <cal:jscalendar name="exam.startDatetime" label="Date and time" format="%m/%d/%Y %H:%M:00" showstime="true"/>
            <cal:jscalendar name="exam.endDatetime" label="End of Date and time" format="%m/%d/%Y %H:%M:00" showstime="true"/>
            <s:text name="Select Questions:"/>        
            <table border="1">
                <tr>
                    <td><b><s:text name="question.list.id"/></b></td>
                    <td><b><s:text name="question.list.question"/></b></td>
                    <td><b><s:text name="question.list.type"/></b></td>
                    <td><b><s:text name="question.list.options"/></b></td>
                </tr>
                <s:iterator value="checkItens">  
                    <tr>
                        <td>                       
                            <s:checkbox name="checkItensMap['%{id}'].value" value="value" theme="simple" />
                        </td>
                        
                        <td>
                            <s:url id="questionUrl" action="question" method="show">
                                <s:param name="question.id" value="id"/>
                            </s:url>
                            <s:a href="%{questionUrl}"><s:property value="object.question" /></s:a>
                        </td>
                        <td>
                            <s:property value="object.type"/>
                        </td>
                    </tr>
                </s:iterator>
            </table>
            <br/>
            <s:submit key="exam.input.create"/>
            <s:a href="exam!list.action" onclick="return confirm('Are you sure?')"><s:text name="exam.input.cancel" /></s:a>
        </s:form> 
</html>
