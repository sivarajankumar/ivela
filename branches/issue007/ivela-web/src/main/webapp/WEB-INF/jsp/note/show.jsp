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
#############################################################################################
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

var keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

function encode64(input) {
   var output = "";
   var chr1, chr2, chr3;
   var enc1, enc2, enc3, enc4;
   var i = 0;

   do {
      chr1 = input.charCodeAt(i++);
      chr2 = input.charCodeAt(i++);
      chr3 = input.charCodeAt(i++);

      enc1 = chr1 >> 2;
      enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
      enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
      enc4 = chr3 & 63;

      if (isNaN(chr2)) {
         enc3 = enc4 = 64;
      } else if (isNaN(chr3)) {
         enc4 = 64;
      }

      output = output + keyStr.charAt(enc1) + keyStr.charAt(enc2) + 
         keyStr.charAt(enc3) + keyStr.charAt(enc4);
   } while (i < input.length);
   
   return output;
}

function make_base_auth(username, password) {
    var tok = username + ':' + password;
    var hash = encode64(tok);
    return "Basic " + hash;
}

    function webicalAuthentication(url){
        new Ajax.Request(url,
        {
            method:'get',
            requestHeaders: {Authorization: make_base_auth('<%= systemUser.getUsername()%>', '<%= systemUser.getUsername()%>')}, 
            asynchronous: false,
            onSuccess: function(transport) {  },
            onFailure: function() {  }
        });
    }    
    window.onload = function () {
        if (Prototype.Browser.IE) {
        <%
          if (!"true".equals((String)session.getAttribute("webical"))) {
        %>
            webicalAuthentication('http://<%= request.getServerName()%>:<%= request.getServerPort()%>/webical/app/calendar?wicket:interface=:0:headerPanel:logoutLink::ILinkListener::');
        <%
              session.setAttribute("webical", "true");
          }
        %>
            webicalAuthentication('http://<%= request.getServerName()%>:<%= request.getServerPort()%>/webical/app/calendar');
            $('calendariframe').src = 'http://<%= request.getServerName()%>:<%= request.getServerPort()%>/webical/app/calendar';
        } else {
            $('calendariframe').src = 'http://<%= request.getServerName()%>:<%= request.getServerPort()%>/webical/app/calendar?wicket:interface=:0:headerPanel:logoutLink::ILinkListener::';
            $('calendariframe').src = 'http://<%= systemUser.getUsername()%>:<%= systemUser.getUsername()%>@<%= request.getServerName()%>:<%= request.getServerPort()%>/webical/app/calendar';
        }
    }
</script>

<iframe id="calendariframe"
        src =""
        frameborder="0"
        width="100%"
        height="500px">
</iframe>