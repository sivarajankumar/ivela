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
# File: tools.jsp                                                                           #
# Document: Tools Messages                                                                  # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 23-AUG-2008 - Leo Moreira                       - XXXXXX - Initial Version                #
# 23-JUL-2009 - Rafael Lagoa (Instituto Eldorado) - 000012 - Unicode (UTF-8) support        #
#############################################################################################
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<head>
    <script type="text/javascript">
        var from = '<s:text name="tools.messages.from" />';
    </script>
</head>

<div class="messages">
    <h3><s:text name="tools.messages" /></h3>
    
    <div id="messages.empty">
        <p class="first-access"><s:text name="home.message.empty" /></p>
    </div>
    <div id="messages.list">
        <%--
        <ul>
                <li>
                    <a class="lightwindow page-options" href="message!getInbox.action?message.id=<s:property value="id" />"  
                       params="lightwindow_type=external,lightwindow_width=1024">
                    <s:property value="title" /> </a> <br /> 
                    <s:text name="tools.messages.from" /> <s:property value="sender.username"/> - <s:date name="datetime" format="M/d/y H:m:s"/>
                </li>
        </ul>
        --%>
    </div>
    <a href="message!inbox.action" id="btn-goto-messages" class="lightwindow page-options" 
       params="lightwindow_type=external,lightwindow_width=1024" ><s:text name="tools.goMessages" /></a>
</div>