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
# File: exception.jsp                                                                       #
# Document: Exception   age                                                                 # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 02-JUL-2008 - Marcus                            - XXXXXX - Initial Version                #
# 23-JUL-2009 - Rafael Lagoa (Instituto Eldorado) - 000012 - Unicode (UTF-8) support        #
#############################################################################################
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/exception.css" rel="stylesheet" type="text/css" />
    </head>
    <body>        

        <br/>
        <h3><s:text name="error.exception.error"/></h3>
        <p class="message" >
            <s:text name="error.exception.contact"/>
        </p>
        <hr/>
        <h3><s:text name="error.exception.message"/></h3>
        <s:actionerror/>
        <p class="message">
            <s:property value="%{exception.message}"/>
        </p>
        <hr/>        
        <h3><s:text name="error.exception.like"/></h3>
        <div id="menu">
          <ul>
            <li id="menu_1" ><a href="index.jsp" title="">Home</a></li>            
          </ul>
        </div>
        
    </body>
</html>