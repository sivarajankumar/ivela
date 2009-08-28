<%--    
#############################################################################################
# Copyright(c) 2009 by IBM Brasil Ltda and others                                           #
# This file is part of ivela project, an open-source                                        #
# Program URL   : http://code.google.com/p/ivela/                                           #  
#                                                                                           #
# This program is free software; you can redistribute it and/or modify it under the terms   #
# of the GNU General Public License as published by the Free Software Foundation; either    #
# version 3 of the License, or (at your option) any later version.                          #
#                                                                                           #
# This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; #
# without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. #
# See the GNU General Public License for more details.                                      #  
#                                                                                           #
#############################################################################################
# File: edit.jsp                                                                            #
# Document: Edit Exam Page                                                                  # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 27-JUN-2008 - Maristella Myrian (UFC)           - XXXXXX - Initial Version                #
# 28-AUG-2009 - lagoa   (Instituto Eldorado)      - 000016 - Set date field as readonly     #
#############################################################################################
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="cal" uri="/jscalendar" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="exam.edit.pageTitle" /></title>
        <cal:head/>
        <script type="text/javascript" src="js/util/calendar.js"></script>
        <s:head />
    </head>
    <s:actionerror />
    <tr>
        <td colspan="2"><h1><s:text name="exam.edit.list"/></h1></td>
    </tr>
    <s:form action="exam!update.action" method="post">
        
        <s:hidden name="exam.id"/>
        <s:textfield name="exam.discipline.id" readonly="true" key="exam.edit.discipline"/>
        <s:textfield name="exam.title" key="exam.edit.title"/>
        <cal:jscalendar name="exam.startDatetime" label="Start Date Time" format="%m/%d/%Y %H:%M:00" showstime="true" onfocus="this.readOnly=true;"/>
        <script>document.getElementsByName('exam.startDatetime')[0].readOnly=true;</script>
        <cal:jscalendar name="exam.endDatetime" label="End Date Time" format="%m/%d/%Y %H:%M:00" showstime="true" onfocus="this.readOnly=true;"/>
        <script>document.getElementsByName('exam.endDatetime')[0].readOnly=true;</script>
        <s:textfield name="exam.maxTimes" key="exam.edit.maxTimes"/>
        <s:textfield name="exam.duration" key="exam.edit.duration"/>
        <s:textfield name="exam.questionsPerPage" key="exam.edit.questionsPerPage"/>
        <s:textfield name="exam.randomizeQuestionsOrder" key="exam.edit.randomizeQuestionsOrder"/>
        <s:textfield name="exam.navigable" key="exam.edit.navigable"/>
        <s:textfield name="exam.finals" key="exam.edit.finals"/>
        
        <br/>
        <s:text name="Exam's Question:"/>
        
        <table border="1" >
            <tr>
                <td><b><s:text name="question.list.id"/></b></td>
                <td><b><s:text name="question.list.question"/></b></td>
                <td><b><s:text name="question.list.type"/></b></td> 
                <td><b><s:text name="question.list.options"/></b></td> 
                
            </tr>
            <s:iterator value="questionExamList" status="stat">
                <tr>
                    <td><s:textfield name="questionExamList[%{#stat.index}].question.id" readonly="true" key="question.list.id" theme="simple" /></td>
                    <td><s:textfield name="questionExamList[%{#stat.index}].question.question"  readonly="true" key="question.list.question" theme="simple" /></td>
                    <td><s:textfield name="questionExamList[%{#stat.index}].question.type" readonly="true" key="question.list.type" theme="simple" /></td>
                    
                    <s:url id="removeUrl" action="exam" method="removeQuestionExam">
                        <s:param name="questionExam.id" value="id"/>
                        <s:param name="exam.id" value="exam.id"/>
                    </s:url>
                    <td><s:a href="%{removeUrl}"><s:text name="exam.edit.remove" /></s:a></td>
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
        
        
        <s:submit key="exam.edit.update"/>
        
    </s:form>
</html>