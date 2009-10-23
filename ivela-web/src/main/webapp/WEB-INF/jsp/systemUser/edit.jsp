<%--    
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
# Document: Edit Profile Page                                                               # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 30-JUL-2008 - Maristella Myrian (UFC)           - XXXXXX - Initial Version                #
# 22-JUN-2009 - otofuji (Instituto Eldorado)      - 000010 - General Issues                 #
#############################################################################################
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="systemUser.edit.pageTitle" /></title>
        <s:head />
    </head>
    
    <tr>
        <td colspan="2"><h1><s:text name="systemUser.edit.pageTitle"/></h1></td>
    </tr>
    <s:form action="systemUser!update.action" method="post">
        <s:hidden name="systemUser.id"/>
        <s:textfield name="systemUser.username" key="systemUser.list.username"/>
        <s:textfield name="systemUser.email" key="systemUser.list.email"/>
        <s:select name="systemUser.enabled" list="disabilitiesList" key="systemUser.list.enabled"/>
        
        <s:select multiple="true" list="systemUser.authentications" listKey="id" 
                  listValue="name" key="systemUser.edit.authentications"/>
        
        <s:select multiple="true" list="systemUser.functionalities" listKey="id" 
                  listValue="name" key="systemUser.edit.functionalities"/>
        
        <s:submit key="systemUser.edit.update"/>
        
        <s:a href="systemUser!list.action" onclick="return confirm('Are you sure?')">Cancel</s:a>
    </s:form>
    
</html>