<%--
#############################################################################################
# Copyright(c) 2008-2009 by IBM Brasil Ltda and others                                      #
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
# Document: list BulletinBoard                                                              # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 30-JUL-2008 - Rodrigo Felix                     - XXXXXX - Initial Version                #
# 15-JUL-2009 - Rafael Lagoa (Instituto Eldorado) - 000012 - Remove breadcrumb area         #
#############################################################################################
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="globals/css/base.css" rel="stylesheet" type="text/css" />
        <link href="css/bulletin.css" rel="stylesheet" type="text/css" />
        <title><s:text name="bulletin.pageTitle" /></title>
        <s:head />
    </head>
    <h1><s:text name="bulletin.pageTitle"/></h1>
    <s:actionerror />
    <div id="col-1-bulletins">
        <h3><s:text name="bulletin.search"/></h3>
        <div id="box-search">
            <s:form theme="simple" action="bulletinBoard!list.action" cssClass="form-system-bulletin">
                <input type="text" name="searchBulletin" id="searchBulletin" class="name-search" value="<s:text name="bulletin.search.initialText"/>" /><br />
                <input type="submit" value="<s:text name="bulletin.search"/>" class="button" />                  
            </s:form>
        </div>
        <ul class="bulletin-action">
            <li><a class="inbox-bulletin" href="bulletinBoard!list.action"><s:text name="bulletin.list"/></a></li>
        </ul>
    </div>
    
    <div id="col-2-bulletins">
        <h2><s:text name="bulletin.list.sessionTitle" /></h2>
        <div class="navegation">
            <a class="previous" href=""><s:text name="bulletin.previous"/></a>
            <a class="next" href=""><s:text name="bulletin.next"/></a>
        </div>
        
        <table class="table-bulletin">
            <tr>
                <th><s:text name="bulletin.list.title" /></th>
                <th><s:text name="bulletin.list.sender" /></th>
                <th><s:text name="bulletin.list.datetime" /></th>
            </tr>
            <s:iterator value="bulletinList">
                <s:url id="listMessage" action="bulletinBoard" method="show">
                    <s:param name="bulletinBoard.id" value="id"/>
                </s:url>
                <tr>
                    <td class="subject"><img src="images/icon/icon-message-no-read.gif" align="left" alt="<s:text name="bulletin.list.title" />" /><s:a href="%{listMessage}"><s:property value="title" /></s:a></td>
                    <td class="author"><s:a href="%{listMessage}"><s:property value="createdBy.username" /></s:a></td>
                    <td class="date-bulletin"><s:property value="formatDate(createdAt,\"dd-MM-yy HH:mm\")" /></td>
                </tr>
            </s:iterator>
        </table>
        
        <div class="navegation">
            <a class="previous" href=""><s:text name="bulletin.previous"/></a>
            <a class="next" href=""><s:text name="bulletin.next"/></a>
        </div>
        
    </div>               
    <br class="clear" />  
</html>
