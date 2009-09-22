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
# File: show.jsp                                                                            #
# Document: Bulletin Board Show                                                             # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 31-JUL-2008 - leomoreira (UFC)                  - XXXXXX - Initial Version                #
# 24-JUN-2009 - otofuji (Instituto Eldorado)      - 000007 - IE7 compatibility              #
# 15-JUL-2009 - Rafael Lagoa (Instituto Eldorado) - 000012 - Remove breadcrumb area         #
#############################################################################################
--%>

<%-- 
    Bulletin Board has not been implemented yet, this page is a prototype.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/base.css" rel="stylesheet" type="text/css" />
        <link href="css/bulletin.css" rel="stylesheet" type="text/css" />
        <title><s:text name="bulletin.pageTitle" /></title>
        <s:head />
    </head>
    <h1><s:text name="bulletin.pageTitle"/></h1>
    
    <div id="col-1-bulletins">
        <h3><s:text name="bulletin.search"/></h3>
        <div id="box-search">
            <form action="" class="form-system-bulletin">
                <input type="text" name="searchBulletin" id="searchBulletin" class="name-search" value="<s:text name="bulletin.search.initialText"/>" />
                <br />
                <input type="submit" value="<s:text name="bulletin.search"/>" class="button" />                  
            </form>
        </div>
        <ul class="bulletin-action">
            <li><a class="inbox-bulletin" href="bulletinBoard!list.action"><s:text name="bulletin.list"/></a></li>
        </ul>
    </div>
    
    <div id="col-2-bulletins">
        
        <h2><s:text name="bulletin.show.sessionTitle" /></h2>
        <p>
            <span class="topic-bulletin"><s:text name="bulletin.list.title" />:</span><br />
            <span class="title-subject-bulletin"><s:property value="bulletinBoard.title" /></span>
        </p>
        <p>
            <span class="topic-bulletin"><s:text name="bulletin.list.sender" />:</span><br /><s:property value="bulletinBoard.createdBy.username" />
        </p>
        <p class="bulletin-reply">
            <span class="topic-bulletin"><s:text name="bulletin.list.description" />:</span><br /><s:property value="bulletinBoard.description" />
        </p>
        
    </div>
    <br class="clear" />        
</html>