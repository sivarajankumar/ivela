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
# Document: Admin Forum Edit Page                                                           # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 05-JUN-2008 - Leonardo Oliveira Moreira         - XXXXXX - Initial Version                #
# 26-JUN-2009 - otofuji (Instituto Eldorado)      - 000010 - i18n fix                       #
#############################################################################################
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="forum.pageTitle" /></title>
        <link href="../css/forum_admin.css" rel="stylesheet" type="text/css" />
        <cal:head/>
        <s:head />
    </head>
    <div id="breadcrumb">
        <p><s:text name="admin.breadcrumb.youAreHere"/></p>
        <ul>
            <li><a href="home.action" title="<s:property value="admin.home.title"/>"><s:text name="admin.home"/></a></li>
            <li><a href="forum!list.action" title="<s:property value="forum.pageTitle"/>"><s:text name="forum.pageTitle"/></a></li>
            <li class="current" title="<s:property value="forum.edit.pageTitle"/>"><s:text name="forum.edit.pageTitle"/></li>
        </ul>
    </div>
    <h2><s:text name="forum.edit.pageTitle" /></h2>
    <s:actionerror />
    <s:form theme="simple" action="forum!update.action" method="post" id="form-forum">
        <s:hidden name="forum.id"/>
        <label><s:text name="forum.input.title" /></label><br/>
        <s:textfield  name="forum.title" cssClass="name-forum"/><br/>
        <label><s:text name="forum.input.description" /></label><br/>
        <s:textarea name="forum.description" cssClass="textarea-forum"/><br/>
        <label><s:text name="forum.input.grade" /></label><br/>
        <s:select list="gradeList" listKey="id" listValue="name" name="forum.grade.id"></s:select><br/>   
        <%--<s:textfield name="forum.grade.id" cssClass="name-forum"/><br/>--%>
        <label><s:text name="forum.input.createTopic"/></label><br/>
        <s:radio name="forum.studentCreateTopic" key="forum.input.createTopic" list="radioBooleanList" /><br/>
        <label><s:text name="forum.input.uploadPost"/></label><br/>
        <s:radio name="forum.studentUploadPost" key="forum.input.uploadPost" list="radioBooleanList" /><br/>
        <label><s:text name="forum.input.uploadRepository"/></label><br/>
        <s:radio name="forum.studentUploadRepository" key="forum.input.uploadRepository" list="radioBooleanList" /><br/>
        <label><s:text name="forum.input.linkPost"/></label><br/>
        <s:radio name="forum.studentLinkPost" key="forum.input.linkPost" list="radioBooleanList" /><br/>
        <label><s:text name="forum.input.public"/></label><br/>
        <s:radio name="forum.public1" key="forum.input.public" list="radioBooleanList" /><br/>        
        <s:submit key="forum.edit.submit" cssClass="btn-save"/>
        <s:a href="forum!list.action" cssClass="btn-cancel"><s:text name="forum.edit.cancel" /></s:a>
    </s:form>
</html>