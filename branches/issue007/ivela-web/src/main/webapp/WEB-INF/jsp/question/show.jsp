<%-- 
    Document   : show Question
    Created on : Jun 27, 2008, 11:21:04 AM
    Author     : emanuelle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <s:actionerror />
    <div id="breadcrumb">
        <p><s:text name="breadcrumb.youAreHere"/></p>
        <ul>
            <li><a href="home.action" title="Back to home"><s:text name="home.name"/></a></li>
            <li><a href="discipline!listByCourse.action?course.id=6&grade.id=10"><s:text name="Inglês para Negócio"/></a></li>
            <li class="current"><s:text name="Exercise Voice"/></li>
        </ul>
    </div>
    <s:if test="question.type==questionAuditive">
    
        <br />
        <center>
            <table>
                <tr><td>
                        
                        <applet code=" br.ufc.ivela.voice.BlackBoardApplet.class"
                                archive="/ivela-web/applet/ivela_voice.jar, 
                                /ivela-web/applet/jogg-0.0.7.jar, 
                                /ivela-web/applet/jorbis-0.0.15.jar, 
                                /ivela-web/applet/tritonus_share.jar, 
                                /ivela-web/applet/vorbisspi1.0.3.jar"
                                
                                width="692" height="346">
                            
                            <param name="question" value="<s:property value="question.question"/>" >
                            
                            <param name="exe" value="<s:property value="sentenceAuditiveQuestion"/>" >
                            
                            <param name="conf" value="<s:property value="fileConf"/>"> 
                            
                            <param name="audio" value="<s:property value="audioAuditiveQuestion"/>">
                            
                            <param name="boardVersion" value="0">
                            
                        </applet>
                </td></tr>
            </table>
        </center>
        
    </s:if>
    <s:else>
        <s:if test="question.type==questionReading">
        
            <center>
                <applet code=" br.ufc.ivela.voice.sound.PlayerApplet"
                        archive="/ivela-web/applet/ivela_sound.jar, 
                        /ivela-web/applet/jogg-0.0.7.jar, 
                        /ivela-web/applet/jorbis-0.0.15.jar, 
                        /ivela-web/applet/tritonus_share.jar, 
                        /ivela-web/applet/vorbisspi1.0.3.jar"
                        
                        width=70 height=60>
                    
                    <param name="audio_url" value="http://200.17.41.209:80/audio/finale.ogg" >
                    
                </applet> 
            </center>
            
            <b>Listen and read the text bellow:</b> <br /> <br />
            
            <p>"<s:property value="question.question" />" </p><br />
            
            <b>Mark the correct answer:</b> <br /> <br />
            
            <p>
                <s:iterator value="listReading">
                    <s:property value="text" /> <br />
                </s:iterator>
                
                 
            </p>
            
        </s:if>
        
        <s:else>
            <tr>
                <td colspan="2"><h1><s:text name="question.show.sessionTitle"/></h1></td>
            </tr>
            <table border="1">
                <tr>
                    <td><b><s:text name="question.show.id"/></b></td>
                    <td><b><s:text name="question.show.question"/></b></td>
                    <td><b><s:text name="question.show.type"/></b></td>
                    <td><b><s:text name="question.show.createdBy"/></b></td>
                    <td><b><s:text name="question.show.createdAt"/></b></td>
                </tr>
                <tr>
                    <td><s:property value="question.id"/></td>
                    <td><s:property value="question.question"/></td>
                    <td><s:property value="question.type"/></td>
                    <td><s:property value="question.createdBy.profile.name"/></td>
                    <td><s:property value="question.createdAt"/></td>
                </tr>
                
            </table>
            <br/>
            <s:if test="question.type==questionObjective">
                <tr>
                    <td colspan="2"><h2><s:text name="question.show.options"/></h2></td>
                </tr>           
                <table border="1">
                    <tr>
                        
                        <td><b><s:text name="question.show.id"/></b></td>
                        <td><b><s:text name="question.show.answer"/></b></td>
                        
                    </tr>
                    <s:iterator value="listObjectiveAnswer">
                        <tr>
                            <td><s:property value="id"/></td>
                            <td><s:property value="answer"/></td>
                            
                        </tr>
                    </s:iterator>
                </table>
                <br/>
                <tr>
                    <td colspan="2"><h2><s:text name="question.show.answer"/></h2></td>
                </tr> 
                <table border="1">
                    <tr>
                        <td><b><s:text name="question.show.idQuestionObjective"/></b></td>
                        <td><b><s:text name="question.show.idQuestionAnswer"/></b></td>
                        <td><b><s:text name="question.show.correctAnswer"/></b></td>
                    </tr>
                    <tr>
                        <td><s:property value="objectiveQuestion.id"/></td>
                        <td><s:property value="objectiveQuestion.correctAnswer.id"/></td>
                        <td><s:property value="objectiveQuestion.correctAnswer.answer"/></td>
                    </tr>
                </table>
                <br/>
            </s:if>
            <s:url id="editUrl" action="question" method="edit">
                <s:param name="question.id" value="question.id"/>
            </s:url>
            <s:a href="%{editUrl}">Edit Question</s:a>
        </s:else>
    </s:else>
    
</html>
