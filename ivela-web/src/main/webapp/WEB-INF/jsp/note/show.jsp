<%-- 
    Document   : show
    Created on : Oct 22, 2008, 10:00:05 AM
    Author     : marcus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="org.springframework.security.context.SecurityContextHolder"%>
<%@ page import="org.springframework.security.userdetails.UserDetails"%>
<%@ page import="br.ufc.ivela.commons.model.SystemUser"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<head>
    <script type="text/javascript" src="js/ead/tools.js"></script>
</head>

<%

            Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            SystemUser systemUser = null;


            if (obj instanceof UserDetails) {
                systemUser = (SystemUser) obj;
            }

%>

<div id="breadcrumb">
    <p><s:text name="breadcrumb.youAreHere"/></p>
    <ul>
        <li><a href="index.action"><s:text name="home.name"/></a></li>
        <li class="current"><s:text name="front.calendar"/></li>
    </ul>
</div>

<script>
    function getJsonFromUrl(url){
        var json;
        new Ajax.Request(url,
        {
            method:'get',
            requestHeaders: {Accept: 'application/json'}, 
            asynchronous: false,
            onSuccess: function(transport) {
                json = transport.responseText.evalJSON(true);
            },
            onFailure: function() { alert('Message: Something went wrong...') }
        });
        return json;
    }    
    window.onload = function () {
        $('calendariframe').src = 'http://<%= request.getServerName()%>:<%= request.getServerPort()%>/webical/app/calendar?wicket:interface=:0:headerPanel:logoutLink::ILinkListener::';
        var text = '';
        while (text != '') {
            text = $('calendariframe').innerHTML;
        }
        $('calendariframe').src = 'http://<%= systemUser.getUsername()%>:<%= systemUser.getUsername()%>@<%= request.getServerName()%>:<%= request.getServerPort()%>/webical/app/calendar';
    }
</script>

<!-- http://<%= systemUser.getUsername()%>:<%= systemUser.getUsername()%>@<%= request.getServerName()%>:<%= request.getServerPort()%>/webical/app/calendar -->
<iframe id="calendariframe"
        src =""
        frameborder="0"
        width="100%"
        height="500px">
</iframe>