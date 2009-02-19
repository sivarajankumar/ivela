<%-- 
    Document   : spacetree
    Created on : Sep 15, 2008, 12:42:35 PM
    Author     : marcus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Space Tree</title>
        
        <link type="text/css" rel="stylesheet" href="css/spacetree.css" />
        
        <script type="text/javascript" src="js/spacetree/mootools-1.2.js"></script>	
        <script language="javascript" type="text/javascript" src="js/prototype/prototype.js"></script>
        <script language="javascript" type="text/javascript" src="js/spacetree/Spacetree.js"></script>
        <script language="javascript" type="text/javascript" src="js/spacetree/infovis.js"></script>
        <script language="javascript" type="text/javascript" src="js/ead/init-spacetree.js"></script>
    </head>
    <body onload="init(<%=request.getParameter("id")%>);">
        <div id="header"></div>
        
        <div id="left"></div>
        
        <canvas id="infovis"></canvas>
        <div id="label_container"></div>
    </body>
</html>
