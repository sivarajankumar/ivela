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
        <!--[if IE 6]>
            <link href="css/ie6.css" rel="stylesheet" type="text/css" />
            <script type="text/javascript">
                var ie6browser = true;         
            </script>            
        <![endif]-->        
        <link href="css/lightwindow.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/prototype/prototype.js"></script>
         <!-- Ext relies on its default css so include it here. -->
		  <!-- This must come BEFORE javascript includes! -->
		  <link rel="stylesheet" type="text/css" href="js/ext/resources/css/ext-all.css">
 
		  <!-- Include here your own css files if you have them. -->
 
		  <!-- First of javascript includes must be an adapter... -->
		  <script type="text/javascript" src="js/ext/adapter/ext/ext-base.js"></script>
		 
		  <!-- ...then you need the Ext itself, either debug or production version. -->
		  <script type="text/javascript" src="js/ext/ext-all.js"></script>
		  <script type="text/javascript" src="js/ext/miframe.js"></script>
        
        <script type="text/javascript">
            var idCourse = '<s:property value="course.id" />' 
            var idDiscipline = '<s:property value="discipline.id" />'
            var idUnit = '<s:property value="unit.id" />'
            var idUnitContent = '<s:property value="unitContent.id" />' 
            var idGrade = '<s:property value="grade.id" />'  
        </script>
        <decorator:head />
        <script type="text/javascript" src="js/contentInfo/contentInfo.js"></script>
    </head>
    <%

        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SystemUser systemUser = null;


        if (obj instanceof UserDetails) {
            systemUser = (SystemUser) obj;
        }

    %>

    <body <decorator:getProperty property="body.onload" writeEntireProperty="true" />>
    <decorator:body/>    
    <div id="hide"></div>
  </body>
</html>
