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
# File: index.jsp                                                                           #
# Document: Home Index Page                                                                 # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 05-JUN-2008 - Leo Moreira                       - XXXXXX - Initial Version                #
# 08-JUN-2009 - Otofuji (Instituto Eldorado)      - 000007 - IE7 compatibility              #
# 15-JUL-2009 - Rafael Lagoa (Instituto Eldorado) - 000012 - Remove breadcrumb area         #
# 23-JUL-2009 - Rafael Lagoa (Instituto Eldorado) - 000012 - Unicode (UTF-8) support        #
# 26-AUG-2009 - Rafael Lagoa (Instituto Eldorado) - 001165 - Align no enrollments message   #
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
    <script>
        <!--
        var it_nocourses = '<s:text name="home.nocourses" />';
        var it_nodisciplines = '<s:text name="home.nodisciplines" />';
        var it_nounits = '<s:text name="home.nounits" />';
        var it_nounitcontents = '<s:text name="home.nounitcontents" />';
        var it_gotocourse = '<s:text name="home.gotocourse" />';
        -->
    </script>
    <link href="css/accordion.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/ead/tools.js"></script>
    <script type="text/javascript" src="js/accordion.js"></script>
    <script type="text/javascript" src="js/util/ajax.js"></script>
    <script type="text/javascript" src="js/ead/index.js"></script>
    
</head>

<s:if test="newsFlash != null">
    <div class="newsflash" id="hiddenFlash">
        <div class="content-newsflash">
            <h3><s:text name="home.warning"/></h3>
            <p><s:property value="newsFlash.message"/></p>
            <span class="btn-close-news" onclick="markNews('<s:property value="newsFlash.id"/>');"><s:text name="home.close" /></span>
        </div>
    </div>
</s:if>

<div id="vertical_container" style="display:none">
    
    <s:iterator value="enrollmentList" >
    
        <h3 class="accordion_toggle">
            <s:if test="(grade.course.name.length() > 25)">
                <s:property value="grade.course.name.substring(0, 25)" />...
            </s:if>
            <s:else>
                <s:property value="grade.course.name" />    
            </s:else>
        </h3>
        <div class="accordion_content" id="<s:property value="grade.course.id" />">
            <dl>
                <dt><s:text name="home.progress" /></dt>
                <p class="progress" style="margin-bottom: -10px;">
                    <span class="box-bar"><img id="course.<s:property value="grade.course.id" />.image" height="11" width="35" src="images/progress-bar/bar.gif" alt="progress bar" /></span>
                    <span class="label" id="course.<s:property value="grade.course.id" />.progress">0%</span>
                </p>
                <br class="clear" />
                <dt><s:text name="home.professor" /></dt>
                <%--<s:iterator value="grade.professors">
                    <dd><s:property value="username"/></dd>
                </s:iterator>--%>
                <dt><s:text name="home.start" /></dt>
                <dd><s:date name="grade.startDatetime" format="%{getText('formatDateLanguage1')}" /> </dd>
                
                
                <dt><s:text name="home.end" /></dt>
                
                <dd><s:date name="grade.endDatetime" format="%{getText('formatDateLanguage1')}"/></dd>
                
                <dt><s:text name="home.structury"/></dt>
                <a style ="float:left;  height: 30px;  width: 103px; padding: 0 15px 0 0; margin-top: 10px; background: url(images/icon/course-structure.gif) no-repeat top right;"  href="spacetree.jsp?id=<s:property value="grade.course.id" />" class="lightwindow page-options" params="lightwindow_type=external,lightwindow_width=1024,lightwindow_height=500"></a>
            </dl>
            <div class="partner">
                
                <img src="RenderServletPartner?id=<s:property value="grade.course.id" />" width="100" />
            </div>
        </div>
        <script>
            <!--
            getCourseProgress(<s:property value="grade.course.id" />);
            -->
        </script>
    </s:iterator>
</div>

<s:if test="enrollmentList == null || enrollmentList.size() == 0">
    <s:if test="pendentList == null || pendentList.size() == 0">
        <br /><br />
        <h5 style="text-align:center"> <s:text name="front.firstAccess" /> </h5>
    </s:if>
</s:if>
<s:else>
    <h2 class="accordion_content_discipline" >
        <s:text name="course.discipline" />
    </h2>
</s:else>

<div id="vertical_container2">
    
</div>

<br class="clear" />
<br class="clear" />

<s:if test="pendentList != null && pendentList.size() > 0">    
    <div>
        <h3 class="pendent_toggle" ><s:text name="front.pendent" /></h3>
        <div class="pendent_content">
            <ul>
                <s:iterator value="pendentList">
                    <li><s:property value="grade.course.name" /> <a href="home!cancel.action?enrollment.id=<s:property value="id" />" ><s:text name="pending.remove" /></a></li>
                </s:iterator>
            </ul>
        </div>
        <br class="clear" />
    </div>
</s:if>