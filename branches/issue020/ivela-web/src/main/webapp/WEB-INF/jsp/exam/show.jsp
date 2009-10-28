<%-- 
    Document   : show Exam
    Created on : Jul 3, 2008, 3:14:59 PM
    Author     : Emanuelle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="exam.show.pageTitle" /></title>
        <link href="css/questions.css" rel="stylesheet" type="text/css" />
        <s:head />
    </head>
    
    
    
    <s:form id="questionForm" method="get" action="exam!saveResultQuestion.action">
        
        <s:hidden id="course_id" value="%{course.id}" name="course.id"/> 
        <s:hidden id="question_id" value="%{question.id}" name="question.id"/> 
        <s:hidden id="question_type" value="%{question.type}" name="question.type"/> 
        <s:hidden id="exam_id" value="%{exam.id}" name="exam.id"/> 
        <s:hidden id="unitContent_id" value="%{unitContent.id}" name="unitContent.id"/> 
        <s:hidden id="answerStudentExamId" value="%{answerStudentExamId}" name="answerStudentExamId"/>
        <h1><s:text name="exam.show.name" /> </h1>
        <s:if test="result==null">
            <s:if test="question.type==questionAuditive"> <br />
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
                
            </s:if> <s:else>
                <s:if test="question.type==questionExternal">
                
                    <iframe height="500" width="768" scrolling="auto" frameborder="0" src="<s:property value="urlExternal"/>" >
                        
                    </iframe>
                    
                    
                </s:if> <s:else>
                    <s:if test="questionText!=null">
                        <p>"<s:property value="questionText.text"/>"</p>
                        <br />
                        <s:if test="questionText.audio!=null && questionText.audio!=''">
                        
                            <center>
                                <applet code=" br.ufc.ivela.voice.sound.PlayerApplet"
                                        archive="/ivela-web/applet/ivela_sound.jar, 
                                        /ivela-web/applet/jogg-0.0.7.jar, 
                                        /ivela-web/applet/jorbis-0.0.15.jar, 
                                        /ivela-web/applet/tritonus_share.jar, 
                                        /ivela-web/applet/vorbisspi1.0.3.jar"
                                        
                                        width=112 height=27>
                                    
                                    <param name="audioHost" value="http://200.17.41.215/public_content/" >
                                    <param name="audio_url" value="<s:property value="questionText.audio"/>" >
                                    
                                </applet> 
                            </center>
                            
                        </s:if>
                        
                    </s:if>
                    <br/>
                    
                    
                    <%-- Description--%> 
                    <h2><s:property value="page"/>) <s:property value="question.question"/></h2> <br/>
                    <span id="error" class="error" />      
                    <s:if test="question.type==questionObjective">
                        
                        
                        <s:if test="objectiveAnswer.id==null">
                            <s:radio id="objetiveAnswer_id"list="listObjectiveAnswer" required="true" listValue="answer+'<br />'" listKey="id" name="objectiveAnswer.id" />
                        </s:if>
                        <s:else>
                            <s:radio id="objetiveAnswer_id" list="listObjectiveAnswer" value="%{objectiveAnswer.id}" required="true" listValue="answer+'<br />'" listKey="id" name="objectiveAnswer.id" />
                        </s:else>
                        
                    </s:if>
                    <s:else>
                        <s:if test="question.type==questionSubjective">
                            
                            <s:if test="cleanSubjective==false">
                                <s:textarea id="subjectiveAnswer" name="subjectiveAnswer" cols="100" rows="10" value="%{subjectiveAnswer}" />
                            </s:if><s:else>
                                <s:textarea id="subjectiveAnswer" name="subjectiveAnswer" cols="100" rows="10" value="" />
                            </s:else>
                            
                        </s:if>
                    </s:else>
                    
                    
                </s:else> 
            </s:else>
        </s:if> <s:else>
            <table>
                <tr>
                    <td><s:text name="exam.show.question" /></td>
                    <td><s:text name="exam.show.score" /></td>
                </tr>
                
                
                <s:iterator value="result" status="count">
                    <tr>
                        <td><s:property value="%{(#count.index) +1}" />)</td>
                        <td> <s:property /></td>
                    </tr>
                    
                </s:iterator>
            </table>
        </s:else>
        
    </s:form>
    
    <br />
    <%-- PAGINATOR--%>
        <%-- 
        <jsp:include page="paginator.jsp"/>
        --%>
    <jsp:include page="paginator_naveg.jsp"/>
    
    
</html>


