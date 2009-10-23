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
# File: chat.jsp                                                                            #
# Document: Chat Page                                                                       # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 07-JAN-2009 - Jefferson (UFC)                   - XXXXXX - Initial Version                #
# 01-SEP-2009 - Rafael Lagôa (Instituto Eldorado) - 000016 - Fix Chat title                 #
#############################################################################################
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><s:text name="chat.title"/></title>
  </head>
  <body>
    <center>
      <h2><s:text name="chat.name"/></h2>     
      <applet code="org.jdamico.ircivelaclient.view.HandleApplet"
              archive="applet/ivela_chat.jar, applet/jerklib.jar"
              width="920" height="505">
        <param name="nick" value='<s:property value="nick" />'>
        <param name="teacher" value='<s:property value="teacherName" />'>
        <param name="channel" value='<s:property value="chatRoomName" />'>
        <param name="bgcolor" value='<s:property value="chatColor" />'>
        <param name="server" value='<s:property value="ircServer" />'>
        <param name="server" value='<s:property value="ircServer" />'>
        <param name="remotefile" value='<s:property value="blackboardServerGet" />' >
        <param name="remoteservlet" value='<s:property value="blackboardServerSave" />' >
      </applet>
    </center>
  </body>
</html>