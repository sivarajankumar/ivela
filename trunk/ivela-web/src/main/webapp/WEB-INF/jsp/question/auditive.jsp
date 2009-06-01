<%-- 
    Document   : auditive
    Created on : Sep 10, 2008, 12:46:42 PM
    Author     : jefferson
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
    </head>

        <center>
            
            <table>
                <tr><td>
                        <applet code=" br.ufc.ivela.voice.BlackBoardApplet.class"
                                archive="/ivela-web/applet/ivela_voice.jar, 
                                /ivela-web/applet/jogg-0.0.7.jar, 
                                /ivela-web/applet/jorbis-0.0.15.jar, 
                                /ivela-web/applet/tritonus_share.jar, 
                                /ivela-web/applet/vorbisspi1.0.3.jar"
                                
                                width=600 height=400>
                            
                                    
                                   <param name="question" value="<%=request.getParameter("title")%>" >
                                   
                                   <param name="exe" value="<%=request.getParameter("exe")%>" >
                                   
                                   <param name="conf" value="<%=request.getParameter("conf")%>"> 
                                   
                                   <param name="audio" value="<%=request.getParameter("audio")%>">
                                   
                                   <param name="boardVersion" value="0">
                            
                        </applet>
                </td></tr>
            </table>
        </center>

</html>

