<%--    
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
# File: printVersion.jsp                                                                    #
# Document: My Score Print Version page                                                     # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 25-OCT-2008 - jefferson                         - XXXXXX - Initial Version                #
# 17-JUN-2009 - otofuji (Instituto Eldorado)      - 000007 - IE7 compatibility              #
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
            <s:text name="student.input.username"/>: <s:property value="systemUser.username"/>
    </td></tr>
    <tr><td>
            <s:text name="student.input.email"/>: <s:property value="systemUser.email"/>
    </td></tr>
</table>
<br />
<table class="table-exercises-result" width="568" border="0" cellspacing="0" cellpadding="0">
    <tr>
    <td width="368" class="titulo"><center><s:text name="history.table.course"/></center></td>
        <td width="368" class="titulo"><center><s:text name="history.table.grade"/></center></td>
        <td width="368" class="titulo"><center><s:text name="history.table.average.exercise"/></center></td>
        <td width="368" class="titulo"><center><s:text name="history.table.average.exam"/></center></td>
        <td width="368" class="titulo"><center><s:text name="history.table.average"/></center></td>
        <td width="368" class="titulo"><center><s:text name="history.table.result"/></center></td>        
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
                <td class="manual-mark"><s:text name="history.status.failed"/></td>
            </s:if>
            <s:if test="status ==1">
                <td class="manual-mark"><s:text name="history.status.approved"/></td>
            </s:if>
            <s:if test="status==3">
                <td class="manual-mark"><s:text name="history.status.inprogress"/></td>
            </s:if>
        </tr> 
    </s:iterator>
    
</table>   

<DIV ALIGN="CENTER">
<FORM>
<INPUT NAME="print" TYPE="button" VALUE=<s:text name="history.print.action"/>
ONCLICK="varitext()">
</FORM>
</DIV>

</center>
<br>



