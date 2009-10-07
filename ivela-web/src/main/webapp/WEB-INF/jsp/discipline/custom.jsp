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
# Document: Show Discipline                                                                 #
# Date        - Author(Company)                   - Issue# - Summary                        #
# 15-JUL-2008 - Nelson                            - XXXXXX - Initial Version                #
# 08-JUN-2009 - Fabio Fantato(Instituto Eldorado) - 000007 - IE7 compatibility              #
# 30-JUN-2009 - Fabio Fantato(Instituto Eldorado) - 000010 - JS no IE/FF opening repository #
# 15-JUL-2009 - Rafael Lagoa (Instituto Eldorado) - 000012 - Remove breadcrumb area         #
# 23-JUL-2009 - Rafael Lagoa (Instituto Eldorado) - 000012 - Unicode (UTF-8) support        #
# 18-AUG-2009 - Otofuji (Instituto Eldorado)      - 000015 - Initial Layout Change          #
############################################################################################# 
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- código para testar o layout 2 --%>
<%@ page import="org.springframework.security.context.SecurityContextHolder"%>
<%@ page import="org.springframework.security.userdetails.UserDetails"%>
<%@ page import="br.ufc.ivela.commons.model.SystemUser"%>
    <%

        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SystemUser systemUser2 = null;

        if (obj instanceof UserDetails) {
            systemUser2 = (SystemUser) obj;
        }
        
    %>

<head>        
    <script type="text/javascript" src="js/ead/tools.js"></script>    
    <script type="text/javascript" src="js/util/ajax.js"></script>
    <script type="text/javascript" src="js/util/util.js"></script>
 
    <script type="text/javascript">
        Event.observe(window, 'load', showUnitContent, false);
        
        
                function showUnitContent() {
                	_width = screen.availWidth - 10;
            		_height = screen.availHeight - 10;
                  html = '<iframe id="html" name="UnitContentFrame" onload="addUnitContentListener(this);" scrolling="no" frameborder="0" width="' + _width + '" height="' + _height + '" src="RenderServlet?file=1/1/1/1/index.html"></iframe> <br class="clear"/>';
                    $('unitContent').innerHTML = html;
                }
    </script>

</head>
<s:actionerror />

<!-- Lesson Content -->
<div class="lesson-content">
     <div id="unitContent"></div>
</div>
    
    
    <br class="clear" />

