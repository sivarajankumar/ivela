<%-- 
    Document   : printVersion
    Created on : Oct 25, 2008, 12:33:16 PM
    Author     : jefferson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="cal" uri="/jscalendar" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">


<head>
    <link href="css/scorecard.css" rel="stylesheet" type="text/css" />
    <script>

            <!-- This script and many more are available free online at -->
            <!-- The JavaScript Source!! http://javascript.internet.com -->


             function varitext(text){
            text=document
            print(text)
            }

    </script>
</head>



<br />
<br />
<br />
<center>
<table>
    <tr><td>
            Name: <s:property value="systemUser.username"/>
    </td></tr>
    <tr><td>
            E-mail: <s:property value="systemUser.email"/>
    </td></tr>
</table>
<br />
<table class="table-exercises-result" width="568" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td width="368" class="titulo"><center>Course</center></td>
        <td width="368" class="titulo"><center>Average Exercise</center></td>
        <td width="368" class="titulo"><center>Average Exam</center></td>
        <td width="368" class="titulo"><center>Final Average</center></td>
        <td width="368" class="titulo"><center>Result</center></td>
    </tr>
    <s:iterator value="transcriptList">
        <tr>
            <td class="score">
                <s:property value="grade.course.name"/>
            </td>
            <td class="score">
                <s:if test="averageExercise<0.0">--</s:if>
                <s:else><s:property value="averageExercise"/></s:else>
            </td>
            <td class="score">
                <s:if test="averageExam<0.0">--</s:if>
                <s:else><s:property value="averageExam"/></s:else>
            </td>
            <td class="score">
                <s:if test="score<0.0">--</s:if>
                <s:else><s:property value="score"/></s:else>
            </td>
            <s:if test="status==0">
                <td class="manual-mark">FAILED</td>
            </s:if>
            <s:if test="status ==1">
                <td class="manual-mark">APPROVED</td>
            </s:if>
            <s:if test="status==3">
                <td class="manual-mark">ENROLLMENT</td>
            </s:if>
        </tr> 
    </s:iterator>
    
</table>   

<DIV ALIGN="CENTER">
<FORM>
<INPUT NAME="print" TYPE="button" VALUE="Print"
ONCLICK="varitext()">
</FORM>
</DIV>

</center>
<br>



