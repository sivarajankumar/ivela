<%--
    Document   : input
    Created on : Jun 5, 2008, 9:14:56 AM
    Author     : leoomoreira
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//ENhttp://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="org.springframework.security.context.SecurityContextHolder"%>
<%@ page import="org.springframework.security.userdetails.UserDetails"%>
<%@ page import="br.ufc.ivela.commons.model.SystemUser"%>
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
                    <a href="?request_locale=es" title="Español"><img src="images/language/sp-es.gif" /></a>
                </div>
            </div>
            <!-- end header -->
            
            <!-- end menu -->
            <div id="content">
                <decorator:body/>
                <br class="clear" />
            </div>
        </div> 
    </body>
</html>