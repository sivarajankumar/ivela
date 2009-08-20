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
# File: show.jsp                                                                            #
# Document: Display calendar for users                                                      # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 22-OCT-2008 - Marcus                            - XXXXXX - Initial Version                #
# 10-JUN-2009 - Rafael Lagoa (Instituto Eldorado) - 000007 - IE7 compatibility              #
# 15-JUL-2009 - Rafael Lagoa (Instituto Eldorado) - 000012 - Remove breadcrumb area         #
#############################################################################################
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="org.springframework.security.context.SecurityContextHolder"%>
<%@ page import="org.springframework.security.userdetails.UserDetails"%>
<%@ page import="br.ufc.ivela.commons.model.SystemUser"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<head>
    <script type="text/javascript" src="js/ead/tools.js"></script>

    <style type="text/css">
      .box-error {height:auto; background:#FFFFCC; border:1px solid #ff7202; padding: 10px;}
      .box-error h4 {width: 500px; height:auto; margin-bottom: 20px; font: bold 17px Arial, Helvetica, sans-serif; color:#ff3333; background: url(../images/icon/icon-error.gif) no-repeat 0 3px; padding-left: 20px;}
      .box-error ul {list-style-type: none; color:#333333; font: 11px Arial, Helvetica, sans-serif;}
      .box-error ul li{ background: url(../images/icon/icon-item-error.gif) no-repeat 0 5px; padding-left: 20px; margin-bottom: 10px;}
    </style>
</head>

<%

            Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            SystemUser systemUser = null;


            if (obj instanceof UserDetails) {
                systemUser = (SystemUser) obj;
            }

%>

<script>
    function webicalAuthentication(url) {
        var req = new XMLHttpRequest();  
        req.open('GET', url, true, '<%= systemUser.getUsername()%>', '<%= systemUser.getUsername()%>');  
        req.onreadystatechange = function (aEvt) {  
            if (req.readyState == 4) {  
                if(req.status == 200) {
                    // if success, do nothing  
                } else {
                    // if fail, do nothing
                }
            }  
        };  
        req.send(null);  
    }

    window.onload = function () {
        if (Prototype.Browser.IE) {
            $('calendariframe').src = 'http://<%= request.getServerName()%>:<%= request.getServerPort()%>/webical/app/calendar?wicket:interface=:0:headerPanel:logoutLink::ILinkListener::';
            webicalAuthentication('http://<%= request.getServerName()%>:<%= request.getServerPort()%>/webical/app/calendar');
            $('calendariframe').src = 'http://<%= request.getServerName()%>:<%= request.getServerPort()%>/webical/app/calendar';
        } else {
            $('calendariframe').src = 'http://<%= request.getServerName()%>:<%= request.getServerPort()%>/webical/app/calendar?wicket:interface=:0:headerPanel:logoutLink::ILinkListener::';
            $('calendariframe').src = 'http://<%= systemUser.getUsername()%>:<%= systemUser.getUsername()%>@<%= request.getServerName()%>:<%= request.getServerPort()%>/webical/app/calendar';
        }
    }
</script>

<!--[if IE]>
    <div class='box-error'><h4>Add Event operation not supported!</h4></div>
<![endif]-->

<iframe id="calendariframe"
        src =""
        frameborder="0"
        width="100%"
        height="500px">
</iframe>