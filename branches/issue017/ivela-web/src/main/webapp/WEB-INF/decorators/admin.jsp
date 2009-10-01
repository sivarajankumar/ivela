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
# File: admin.jsp                                                                           #
# Document: Decorator for Admin pages                                                       #
# Date        - Author(Company)                   - Issue# - Summary                        #
# 05-JUN-2008 - leoomoreira                       - XXXXXX - Initial Version                #
# 08-JUN-2009 - otofuji (Instituto Eldorado)      - 000007 - IE7 compatibility              #
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="org.springframework.security.context.SecurityContextHolder"%>
<%@ page import="org.springframework.security.userdetails.UserDetails"%>
<%@ page import="br.ufc.ivela.commons.model.SystemUser"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="front.pageTitle" /></title>
        <!-- Colocar a logo para aparecer no navegador <link rel="icon" href="../logotipo/logo.jpg" type="image/gif" /> -->
        <link href="../css/base_admin.css" rel="stylesheet" type="text/css" />
        <link href="../css/lightwindow.css" rel="stylesheet" type="text/css" />
        <link href="../css/accordion_admin.css"  rel="stylesheet" type="text/css" />
        <!--[if IE 6]>
            <link href="../css/ie6adm.css" rel="stylesheet" type="text/css" />
            <script type="text/javascript">
                var ie6browser = true;         
            </script>            
        <![endif]-->
        <script type="text/javascript" src="../js/util/util.js"></script>
        <script type="text/javascript" src="../js/prototype/prototype.js"></script>
        <script type="text/javascript" src="../js/scriptaculous/scriptaculous.js"></script>
        <script type="text/javascript" src="../js/scriptaculous/effects.js"></script>
        <script type="text/javascript" src="../js/admin/lightwindow.js"></script>
        <script type="text/javascript" src="../js/accordion.js"></script>
        <script type="text/javascript" src="../js/admin/ajax.js"></script>
        
        <decorator:head />
    </head>
    <%

            Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            SystemUser systemUser = null;
            
            if (obj instanceof UserDetails) {
                systemUser = (SystemUser) obj;
            }

            String auth = systemUser.getAuthorities()[0].toString();                       
    %>
    
    <body>
        <div id="container">
            <div id="header">
                <h1><a href="../index.jsp" class="logotipo" title="<s:property value="front.home.title" />"><s:text name="front.pageTitle" /></a></h1>
                <div class="profile">
                    <span><img src="../RenderServletProfile?id=<%= systemUser.getId()%>" title="Perfil"  height="54px" width="54px" /></span>
                    <h2><%= (systemUser.getProfile() != null && !systemUser.getProfile().getFirstName().isEmpty()) ? systemUser.getProfile().getFirstName() : systemUser.getUsername()%></h2>
                    <p>
                    <% if(auth.equals("ROLE_COORD")) { %>
                        (<s:text name="systemUser.type.coordinator"/>)
                    <% } else if(auth.equals("ROLE_TUTOR")) { %>
                        (<s:text name="systemUser.type.tutor"/>)
                    <% } else if(auth.equals("ROLE_PROFESSOR")) {%>
                        (<s:text name="systemUser.type.professor"/>)
                    <% } else if(auth.equals("ROLE_ADMIN")) { %>
                        (<s:text name="systemUser.type.admin"/>)
                    <% } %>
                    </p>                    
                    <a  href="../profile!edit.action" class="lightwindow page-options" params="lightwindow_type=external,lightwindow_width=1024"><s:text name="front.editProfile" /></a>
                    <a href="../j_spring_security_logout" class="logout" title="<s:property value="front.logout" /><s:text name="home.logout" />"><s:property value="front.logout" /></a>
                </div>
                <!-- end profile -->
                <br class="clear" />
            </div>
            <!-- end header -->
            <div class="tools">                
                <span class="btn-tools" onclick="Effect.toggle('hidden', 'slide')" title="<s:text name="main.tools" />"><img src="../images/icon/icon-tools.gif" /><s:text name="main.tools"/><img src="../images/icon/icon-tools-arrow.gif" /></span>                
                <span class="btn-manual" onclick="document.location = '/ivela-manual'"><img src="../images/icon/icon-faq.gif" title="<s:text name='main.manual'/>" /></span>                
                
                <div class="container-tools" id="hidden" style="display:none;">
                    <div class="content-tools">                        
                        <jsp:include page="../jsp/admin/message/tools.jsp"/>
                        
                        <jsp:include page="../jsp/admin/forum/tools.jsp"/>
                        
                        <br class="clear" />
                    </div>
                    
                    <!-- end content-tools-->
                </div>
                <!-- end container-tools-->
            </div>

            <div id="menu">
                <ul>   
                    <li id="menu_load"></li>             	                    
                    <li id="menu_1" ><a href="javascript:showMenuLoading('home.action');" title="<s:property value="admin.controlPanel" />"><s:text name="admin.controlPanel" /></a></li>
                    <li id="menu_4" ><a href="javascript:showMenuLoading('systemUser!show.action');" title="<s:property value="back.people.title" />"><s:text name="admin.people" /></a></li>
                    <sec:authorize ifAnyGranted="ROLE_ADMIN, ROLE_COORD, ROLE_PROFESSOR">
                    <li id="menu_2" ><a href="javascript:showMenuLoading('course!show.action');" title="<s:property value="back.courses.title" />"><s:text name="admin.courses" /></a></li>
                    </sec:authorize>
                    <li id="menu_3" ><a href="javascript:showMenuLoading('grade!show.action');" title="<s:property value="back.grades.title" />"><s:text name="admin.grade" /></a></li>
                </ul>
            </div>
            
            <div id="horizontal-line-t" class="horizontal-line-t">                 
            </div>
            
            <script type="text/javascript">
                <!--
                    var get = "<%=request.getRequestURI()%>";
                    var action = get.substring(get.lastIndexOf('/') + 1);                
                    var tab = 1;

                    if(action.startsWith("home")){
                        tab = 1;
                    } else if(action.startsWith("course")) {
                        tab = 2;
                    } else if(action.startsWith("grade")){
                        tab = 3;
                    } else if(action.startsWith("systemUser")){
                        tab = 4;
                    } else if(action.startsWith("index")){
                        tab = 0;
                    }
                    
                    if(tab != 0){
                        $('menu_'+tab).setAttribute(classCss(),"current");
                    }
                -->
            </script>
            
            
            <!-- end menu -->
            <div id="content">
                <s:actionmessage/>
                
                <decorator:body/>
                <br class="clear" />
            </div>
        </div> 
        
        <div id="footer">
            <div id="content-footer">
                <img class="logo" src="../images/logo-footer.gif" alt="<s:property value="front.logo.footer" />" />
                <ul>
                   <li><a href="javascript:showMenuLoading('home.action');" title="<s:property value="back.home.title" />"><s:text name="admin.controlPanel" /></a></li>
                    <li><a href="javascript:showMenuLoading('systemUser!show.action');" title="<s:property value="back.people.title" />"><s:text name="admin.people" /></a></li>
                    <sec:authorize ifAnyGranted="ROLE_ADMIN, ROLE_COORD, ROLE_PROFESSOR">
                    <li><a href="javascript:showMenuLoading('course!show.action');" title="<s:property value="back.courses.title" />"><s:text name="admin.courses" /></a></li>
                    </sec:authorize>
                    <li><a href="javascript:showMenuLoading('grade!show.action');" class="last" title="<s:property value="back.grades.title" />"><s:text name="admin.grade" /></a></li>
                </ul>
            </div>
            <br class="clear" />
        </div>
    </body>
</html>
