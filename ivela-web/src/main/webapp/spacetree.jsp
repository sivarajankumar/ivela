<%--    
# Copyright(c) 2008-2009 by IBM Brasil Ltda and others                                           #
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
# File: spacetree.jsp                                                                       #
# Document: Page for the Space tree Canvas                                                  # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 15-SEP-2008 - marcus                            - XXXXXX - Initial Version                #
# 09-JUN-2009 - otofuji (Instituto Eldorado)      - 000007 - IE7 compatibility              #
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Space Tree</title>
        
        <link type="text/css" rel="stylesheet" href="css/spacetree.css" />
        
        <script type="text/javascript" src="js/spacetree/mootools-1.2.2-core-yc.js"></script>	
        <script language="javascript" type="text/javascript" src="js/spacetree/excanvas.js"></script>                          
        <script language="javascript" type="text/javascript" src="js/spacetree/jit-yc.js"></script>                       
        <script language="javascript" type="text/javascript" src="js/ead/init-spacetree.js"></script>
    </head>
    <body onload="init(<%=request.getParameter("id")%>);">
        <div id="header"></div>
        
        <div id="left"></div>
        
        <div id="infovis"></div>
        <div id="label_container"></div>
    </body>
</html>
