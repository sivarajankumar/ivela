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
    <body>
        <h2><s:text name="error.exception.error"/></h2>
        <p>
            <s:text name="error.exception.contact"/>
        </p>
        <hr/>
        <h3><s:text name="error.exception.message"/></h3>
        <s:actionerror/>
        <p>
            <s:property value="%{exception.message}"/>
        </p>
        <hr/>
        <h3><s:text name="error.exception.details"/></h3>
        <p>
            <s:property value="%{exceptionStack}"/>
        </p>
    </body>
</html>
