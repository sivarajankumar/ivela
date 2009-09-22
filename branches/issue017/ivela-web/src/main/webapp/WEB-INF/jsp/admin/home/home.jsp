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
# File: home.jsp                                                                            #
# Document: Home Page for Administrators                                                    # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 15-SEP-2008 - marcus                            - XXXXXX - Initial Version                #
# 17-JUN-2009 - otofuji (Instituto Eldorado)      - 000007 - IE7 compatibility              #
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<head>
    <script type="text/javascript" src="../js/admin/tools.js"></script>
</head>

<s:actionerror />

<div id="breadcrumb">
    <p><s:text name="breadcrumb.youAreHere"/></p>
    <ul>
        <li class="current"><s:text name="admin.home" /></li>
    </ul>
</div>

<div id="col-1-home">
    <h2><s:text name="admin.pageTitle" /></h2>
    <div id="vertical_container2" >
        <h3 class="no_grades"><a href="home.action" title="<s:property value="admin.controlPanel" />"><s:text name="admin.controlPanel" /></a></h3>
        <h3 class="no_grades"><a href="systemUser!show.action" title="<s:property value="back.people.title" />"><s:text name="admin.people" /></a></h3>
        <h3 class="no_grades"><a href="course!show.action" title="<s:property value="back.courses.title" />"><s:text name="admin.courses" /></a></h3>
        <h3 class="no_grades"><a href="grade!show.action" title="<s:property value="aback.grades.title" />"><s:text name="admin.grade" /></a></h3>   
    </div>
</div>
<!-- end col-1-home -->

<div id="showCourse">
    <div id="col-2-home">
        <s:text name="home.welcome"/>
    </div>    
</div>

<!-- end col-2-home -->
<br class="clear" />

<!-- end content -->
<br class="clear" />
