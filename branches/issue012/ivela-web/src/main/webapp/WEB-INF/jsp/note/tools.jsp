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
# Document: Tools Notes                                                                     # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 23-AUG-2008 - Leo Moreira                       - XXXXXX - Initial Version                #
# 23-JUL-2009 - Rafael Lagoa (Instituto Eldorado) - 000012 - Unicode (UTF-8) support        #
#############################################################################################
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="org.springframework.security.context.SecurityContextHolder"%>
<%@ page import="org.springframework.security.userdetails.UserDetails"%>
<%@ page import="br.ufc.ivela.commons.model.SystemUser"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

    <%

            Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            SystemUser systemUser = null;


            if (obj instanceof UserDetails) {
                systemUser = (SystemUser) obj;
            }

    %>

<div class="agenda">
    
    <h3><s:text name="tools.note" /></h3> 
    
    <s:if test="(noteList == null || noteList.size() == 0)">
        <p class="first-access"><s:text name="home.note.empty" /></p>
    </s:if> 
    <s:else>
        <ul>
            <s:iterator value="noteList">
                <li>
                    <a href="http://webical:webical@200.17.41.215:8080/webical/app/calendar"
                       class="lightwindow page-options" 
                       params="lightwindow_type=external,lightwindow_width=1024" 
                       title="<s:property value="title" />"  caption="note">                        
                        <s:property value="title" /> <br />
                    </a> 
                    <s:text name="tools.note.on" /><s:date name="datetime" format="M/d/y H:m:s"/> 
                </li>
            </s:iterator>   
        </ul>   
    </s:else>
        <!-- http://<%= systemUser.getUsername() %>:<%= systemUser.getUsername() %>@<%= request.getServerName() %>:<%= request.getServerPort() %>/webical/app/calendar -->
    <a href="http://webical:webical@200.17.41.215:8080/webical/app/calendar" id="btn-goto-agenda" class="lightwindow page-options" 
       params="lightwindow_type=external,lightwindow_width=1024" ><s:text name="tools.note.goNote" /></a>        
    
</div>    
