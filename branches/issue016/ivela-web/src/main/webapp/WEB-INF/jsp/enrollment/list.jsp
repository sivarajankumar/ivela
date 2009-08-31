<%--
###############################################################################################
# Copyright(c) 2008-2009 by IBM Brasil Ltda and others                                        #
# This file is part of ivela project, an open-source                                          #
# Program URL   : http://code.google.com/p/ivela/                                             #  
#                                                                                             #
# This program is free software; you can redistribute it and/or modify it under the terms     #
# of the GNU General Public License as published by the Free Software Foundation; either      #
# version 3 of the License, or (at your option) any later version.                            #
#                                                                                             #
# This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;   #
# without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.   #
# See the GNU General Public License for more details.                                        #  
#                                                                                             #
###############################################################################################
# File: list.jsp                                                                              #
# Document: list BulletinBoard                                                                # 
# Date        - Author(Company)                   - Issue# - Summary                          #
# 23-JUL-2008 - Emanuelle                         - XXXXXX - Initial Version                  #
# 15-JUL-2009 - Rafael Lagoa (Instituto Eldorado) - 000012 - Remove breadcrumb area           #
# 28-AGO-2009 - Mileine      (Instituto Eldorado) - 000016 - Same course enrollment msg added #
###############################################################################################
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>

<link href="css/course.css" rel="stylesheet" type="text/css" />

<h1><s:text name="enrollment.list.title"/></h1>
<br />
<h2><s:text name="home.course" />&nbsp;: <s:property value="course.name" /></h2>
<s:if test="gradeList != null">
    <div id="col-1-courses">
        <s:actionmessage />
        <table class="table-grades">
            <s:iterator value="gradeList">
                <tr>
                    <td><span class="grade"><s:property value="name" /></span> </td>
                    
                    <s:url id="enrollUrl" action="enrollment" method="enroll">
                        <s:param name="grade.id" value="id"/>
                    </s:url>
                    
                    <td align="right"><table><tr><td>
                        <s:a href="%{enrollUrl}" cssClass="title-enrollment" ><s:text name="enrollment.add.action"/></s:a>
                    </td></tr></table></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <p>
                            <b><s:text name="grade.input.requiresNavigation" />:</b><s:property value="requiresEnrollmentValidation" /> 
                            <b><s:text name="grade.input.startdatetime" />:</b><s:date name="startDatetime" format="%{getText('formatDateLanguage2')}" /> 
                            <b><s:text name="grade.input.enddatetime" />:</b><s:date name="endDatetime" format="%{getText('formatDateLanguage2')}" /> 
                            <b><s:text name="grade.input.maxstudents" />:</b><s:property value="maxStudents" /> 
                            <b><s:text name="grade.input.enrolled" /></b>:<s:property value="enrollments.size" /> 
                        </p>
                    </td>
                </tr>
            </s:iterator>
</s:if>
        
<s:elseif test="message=='enrolled'">
	<p class="no-courses"><s:text name="enrollment.list.alreadyEnrolled" /></p>
</s:elseif>
        
<s:elseif test="message=='noGrades'">
  	<p class="no-courses"><s:text name="enrollment.list.noGrade" /></p>
</s:elseif>

    </table> 
</div>
