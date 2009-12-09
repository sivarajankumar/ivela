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
# File: list.jsp                                                                            #
# Document: Question List Page                                                              # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 20-JUN-2008 - Emanuelle (UFC)                   - XXXXXX - Initial Version                #
# 22-JUN-2009 - otofuji (Instituto Eldorado)      - 000010 - i18n Issues                    #
#############################################################################################
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
                <td><s:a href="%{editUrl}"><s:text name="general.edit"/></s:a>/<s:a href="%{deleteUrl}" onclick="return confirm('<s:text name="general.areyousure"/>')"><s:text name="general.delete"/></s:a></td>                    
            </tr>
        </s:iterator>
    </table>
    <s:a href="question!input.action"><s:text name="question.input.createnew"/></s:a>
    
</html>
