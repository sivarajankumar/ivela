<%/*
####################################################################################################
# Copyright(c) 2009 by IBM Brasil Ltda and others                                                  #
# This file is part of ivela project, an open-source                                               #
# Program URL   : http://code.google.com/p/ivela/                                                  #
#                                                                                                  #
# This program is free software; you can redistribute it and/or modify it under the terms          #
# of the GNU General Public License as published by the Free Software Foundation; either           #
# version 3 of the License, or (at your option) any later version.                                 #
#                                                                                                  #
# This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;        #
# without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.        #
# See the GNU General Public License for more details.                                             #
#                                                                                                  #
####################################################################################################
# File:     no_login.jsp                                                                           #
# Document: Decorator                                                                              #
# Date        - Author(Company)                    - Issue# - Summary                              #
# 05-JUN-2008 - Leonardo Moreira                   - XXXXXX - Initial Version                      #
# 08-JUN-2009 - Mileine Assato(Instituto Eldorado) - 000007 - SYSTEM_VERSION value reference added #
####################################################################################################
*/ %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//ENhttp://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="org.springframework.security.context.SecurityContextHolder"%>
<%@ page import="org.springframework.security.userdetails.UserDetails"%>
<%@ page import="br.ufc.ivela.commons.model.SystemUser"%>
<%@ page import="br.ufc.ivela.commons.Constants"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>


<html>
    <head>
        <title><s:text name="front.pageTitle" /></title>
        <!-- Colocar a logo para aparecer no navegador <link rel="icon" href="../logotipo/logo.jpg" type="image/gif" /> -->
        <link href="css/base_no_login.css" rel="stylesheet" type="text/css" />
                   
        <decorator:head/>
    </head>
    
    <body>
        <div id="container">
            <div id="header">
                <h1><a href="/ivela-web/index.jsp" class="logotipo" title="<s:property value="front.home.title" />"><s:text name="front.pageTitle" /></a></h1>
                <div class="language">
                    <a href="?request_locale=pt" title="Português"><img src="images/language/pt-br.gif" /></a>
                    <a href="?request_locale=en" title="English"><img src="images/language/en-us.gif" /></a>
                    <!-- a href="?request_locale=es" title="Español"><img src="images/language/sp-es.gif" /></a-->
                </div>
            </div>
            <!-- end header -->
            
            <!-- end menu -->
            <div id="content">
                <decorator:body/>
                <br class="clear" />
            </div>
        </div> 
        <div id="footer">
            <div id="content-footer">
               <p id="copyright"> &copy; 2009 Ivela <%= Constants.SYSTEM_VERSION %> - All rights reserved.</p>
           
                <img class="logo" src="images/logo-footer.png" alt="<s:property value="front.logo.footer" />" />
            </div>
  
        </div>
      </body>
</html>