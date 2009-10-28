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
# Document: Admin Grade Edit Page                                                           # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 07-AUG-2008 - Leonardo Oliveira Moreira         - XXXXXX - Initial Version                #
# 26-JUN-2009 - otofuji (Instituto Eldorado)      - 000010 - i18n fix                       #
# 28-AGO-2009 - lagoa   (Instituto Eldorado)      - 000016 - Set date field to readonly     #
#############################################################################################
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="cal" uri="/jscalendar" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="grade.pageTitle" /></title>
        <link href="../css/grade_admin.css" rel="stylesheet" type="text/css" />
        <cal:head/>
        <script type="text/javascript" src="../js/util/calendar.js"></script>
        <s:head />
    </head>
    <div id="breadcrumb">
        <p><s:text name="admin.breadcrumb.youAreHere"/></p>
        <ul>
            <li><a href="home.action" title="<s:property value="admin.home.title"/>"><s:text name="admin.home"/></a></li>
            <li><a href="grade!list.action" title="<s:property value="grade.pageTitle"/>"><s:text name="grade.pageTitle"/></a></li>
            <li class="current" title="<s:property value="grade.name"/>"><s:property value="grade.name"/></li>
        </ul>
    </div>
    <h2><s:text name="grade.edit.pageTitle" /></h2>
    <s:actionerror/>
    <s:form theme="simple" action="grade!update.action" method="post" id="form-grade">
        <s:hidden name="grade.id" />
        <s:hidden name="grade.repositoryStructure" />
        <label><s:text name="grade.input.course" /></label><br />
        <s:select list="courseList" listKey="id" listValue="name" name="grade.course.id" /><br />
        <label><s:text name="grade.input.name" /></label><br />
        <s:textfield name="grade.name" cssClass="name-grade" /><br />
        <label><s:text name="grade.input.period" /></label><br />
        <s:textfield name="grade.period" cssClass="name-grade" /><br />
        <label><s:text name="grade.input.maxStudents" /></label><br />
        <s:textfield name="grade.maxStudents" cssClass="name-grade" /><br />
        <label><s:text name="grade.input.status" /></label><br />
        <s:select name="grade.status" list="gradeStatusList" /><br />
        <label><s:text name="grade.input.coordinator" /></label><br />
        <s:select list="systemUserList" listKey="id" listValue="username" name="grade.coordinator.id" /><br />
        <label><s:text name="grade.input.requiresEnrollmentValidation" /></label><br />
        <s:radio name="grade.requiresEnrollmentValidation" value="false" list="radioBooleanList" /><br />
        <label><s:text name="grade.input.startDatetime" /></label><br/>
        <cal:jscalendar name="grade.startDatetime" title="grade.input.startDatetime" onfocus="this.readOnly=true;"/><br />
        <script>document.getElementsByName('grade.startDatetime')[0].readOnly=true;</script>
        <label><s:text name="grade.input.endDatetime" /></label><br />
        <cal:jscalendar name="grade.endDatetime" title="grade.input.endDatetime" onfocus="this.readOnly=true;"/><br />
        <script>document.getElementsByName('grade.input.endDatetime')[0].readOnly=true;</script>
        <s:submit key="grade.edit.create" cssClass="btn-save" />
        <s:a href="grade!list.action" cssClass="btn-cancel"><s:text name="grade.input.cancel" /></s:a>
    </s:form>
</html>