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
# File: course.jsp                                                                          #
# Document: Decorator for Course Content pages                                              # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 18-AUG-2009 - Otofuji (Instituto Eldorado)      - 000015 - Initial Version                #
#############################################################################################
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="org.springframework.security.context.SecurityContextHolder"%>
<%@ page import="org.springframework.security.userdetails.UserDetails"%>
<%@ page import="br.ufc.ivela.commons.model.SystemUser"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="front.pageTitle" /></title>       
        <link href="css/courses/base_course.css" rel="stylesheet" type="text/css" />
        <link href="css/lightwindow.css" rel="stylesheet" type="text/css" />        
        <script type="text/javascript" src="js/prototype/prototype.js"></script>
        <script type="text/javascript" src="js/scriptaculous/scriptaculous.js"></script>
        <script type="text/javascript" src="js/scriptaculous/effects.js"></script>
        <script type="text/javascript" src="js/scriptaculous/lightwindow.js"></script>

        <decorator:head />
    </head>
    <%

        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SystemUser systemUser = null;


        if (obj instanceof UserDetails) {
            systemUser = (SystemUser) obj;
        }

    %>

    <body>

    <div id="container">
       <div id="header">
       </div>            

       <div id="content">
            <s:actionmessage/>
            
            <div class="course-header">
                <span class="corner-left">.</span>
                <span class="corner-right">.</span>
                <div class="partner">
                    <img src="RenderServletPartner?id=<s:property value="discipline.course.id" />" width="100"  />
                </div>    
                <h1><s:text name="discipline.show.course" /><s:property value="discipline.course.name" /></h1>
                <h2><s:text name="discipline.show.discipline" /><s:property value="discipline.name" /></h2>                                                            
                                
                <div class="profile-menu">       
                    <a href="home.action"> <s:text name="front.back" /> </a>                                                        
                </div>                
            </div>
            <br class="clear" />            
            <div id="course-content">
            <decorator:body/>
            </div>
            <br class="clear" />
        </div>
    </div>
        
        
        <div id="footer">
            <div id="content-footer">
                <ul>
                    <li><a href="index.jsp" title="<s:text name="front.controlPanel.title" />"><s:text name="front.controlPanel" /></a></li>
                    <li><a href="course!list.action" title="<s:property value="front.courses.title" />"><s:text name="front.courses" /></a></li>
                    <li><a href="history!show.action" title="<s:text name="front.history.title" />"><s:text name="front.history" /></a></li>
                    <li><a href="note!show.action" title="<s:text name="front.calendar" />" class="last"><s:text name="front.calendar" /></a></li>
                </ul>
                <img class="logo" src="images/logo-footer.png" alt="<s:property value="front.logo.footer" />" />
            </div>
            <br class="clear" />
        </div>
    </body>
</html>
