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
# File: notfound.jsp                                                                        #
# Document: Default Not Found Page                                                          # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 24-JUN-2009 - otofuji (Instituto Eldorado)      - 000010 - General Fixes, Initial Batch   #
#############################################################################################
--%>

<%-- Placeholder Version  --%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>
<s:text name="error.404" />
</title>
</head>
<body>

<h1><s:text name="error.404" /></h1>
<br/>
<p>
<s:text name="error.404.description" />
</p>

<br/>
<p>
<s:text name="error.404.options" />
</p>
<br/>   
<a href="javascript: history.back(1)"><s:text name="error.404.options.1" /> </a>
</body>
</html>
