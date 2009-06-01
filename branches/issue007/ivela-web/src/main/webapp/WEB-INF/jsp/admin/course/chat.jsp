<%-- 
    Document   : chat
    Created on : Jan 7, 2009, 1:02:20 PM
    Author     : jefferson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="cal" uri="/jscalendar" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<br />        
<center>
<applet code="org.jdamico.ircivelaclient.view.HandleApplet"
	archive="http://200.17.41.212/public_content/chatlib/ivela_chat.jar, http://200.17.41.212/public_content/chatlib/jerklib.jar"
	
	width=920 height=505>

<param name="nick" value='<s:property value="nick" />' >

<param name="teacher" value='<s:property value="teacherName" />' >

<param name="channel" value='<s:property value="chatRoomName" />'>

<param name="bgcolor" value="FFFFFF">

<param name="server" value="200.17.41.212">



</applet> 
</center>
