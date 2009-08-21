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
# File: list.jsp                                                                            #
# Document: List Course                                                                     #
# Date        - Author(Company)                   - Issue# - Summary                        #
# 26-JUN-2008 - Maristella Myrian                 - XXXXXX - Initial Version                #
# 01-JUN-2009 - Fabio Fantato(Instituto Eldorado) - 000007 - IE7 compatibility              #
# 30-JUN-2009 - Mileine Assato(Instituto Eldorado)- 000010 - Courses available color fixed  # 
# 15-JUL-2009 - Rafael Lagoa (Instituto Eldorado) - 000012 - Remove breadcrumb area         #
#############################################################################################
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<head>
<link href="css/course.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/ead/tools.js"></script>
</head>

<h1><s:text name="course.list.title"/></h1>
<s:actionerror />
<div id="col-1-courses"> 
    <table class="table-courses">
        <s:iterator value="courseList">
            
            <s:url id="listDisciplineUrl" action="discipline" method="listByCourse">
                <s:param name="course.id" value="id"/>
            </s:url>
            
            
            <tr>
                <td><span><s:property value="name" /></span></td>
                <td align="right"><table><tr><td><a class="title-enrollment" href="enrollment!listGrades.action?course.id=<s:property value="id" />"><s:text name="enrollment.add.action"/></a></td></tr></table></td>
            </tr>
            <tr>
                <td colspan="2"><p><s:property value="description" /></p></td>
                
            </tr>
            
        </s:iterator>
    </table>
    
    <p class="page-courses"><span class="courses-listed"><s:property value="%{courseList.size()}" />&nbsp;<s:text name="front.courses" /> <s:text name="front.of" /> <s:property value="count" /></span></p>
    
    <jsp:include page="paginator.jsp"/>            
    
        
</div>
