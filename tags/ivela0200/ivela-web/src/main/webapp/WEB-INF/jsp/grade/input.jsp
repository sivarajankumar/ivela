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
# File: input.jsp                                                                           #
# Document: Input Grade  Page                                                               # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 23-JUL-2008 - nelson                            - XXXXXX - Initial Version                #
# 28-AUG-2009 - lagoa   (Instituto Eldorado)      - 000016 - Set date field as readonly     #
#############################################################################################
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="cal" uri="/jscalendar" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="grade.input.pageTitle" /></title>
        <cal:head/>
        <script type="text/javascript" src="js/util/calendar.js"></script>
        <s:head />
    </head>
    <tr>
        <td colspan="2"><h1><s:text name="grade.input.title"/></h1></td>
    </tr>
    <s:form action="grade!add.action">
        <s:select list="courseList" listKey="id" listValue="name" name="grade.course.id" key="grade.input.course" />
        <s:textfield name="grade.name" key="grade.input.name"/>
        <s:textfield name="grade.period" key="grade.input.period"/>
        <s:textfield name="grade.maxStudents" key="grade.input.maxStudents"/>
        <s:select name="grade.status" list="#{'0':'Inactive', '1':'Period of enrollment', '2':'Registration finished'}" key="grade.input.status" />
        <s:select list="systemUserList" listKey="id" listValue="username" name="grade.coordinator.id" key="grade.input.coordinator" />
        <s:radio name="grade.requiresEnrollmentValidation" key="grade.input.requiresEnrollmentValidation" value="false" list="#{'true':'true','false':'false'}" />
        <cal:jscalendar name="grade.startDatetime" title="grade.input.startDatetime" onfocus="this.readOnly=true;"/>
        <script>document.getElementsByName('grade.startDatetime')[0].readOnly=true;</script>
        <cal:jscalendar name="grade.endDatetime" title="grade.input.endDatetime" onfocus="this.readOnly=true;"/>
        <script>document.getElementsByName('grade.endDatetime')[0].readOnly=true;</script>
        <s:submit key="grade.input.create"/>
        <s:a href="grade!list.action" onclick="return confirm('Are you sure?')"><s:text name="grade.list.action"/></s:a>
    </s:form> 
</html>