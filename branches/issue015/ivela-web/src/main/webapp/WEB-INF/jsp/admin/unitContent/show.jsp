<%-- 
    Document   : show
    Created on : Jan 29, 2009, 4:50:50 PM
    Author     : emanuelle
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="cal" uri="/jscalendar" %>


    <head>
       
    <link href="css/profile.css" rel="stylesheet" type="text/css" />
        
    <script type="text/javascript" src="js/prototype/prototype.js"></script>
    <script type="text/javascript" src="js/scriptaculous/scriptaculous.js"></script>    
    <script type="text/javascript" src="js/systemUser/base.js"></script>
        <cal:head/>
        <s:head />
    </head>
    <iframe id="html" frameborder="0"  src="../RenderServlet?<s:property value="%{file}"/>"></iframe> <br class="clear"/>