<%-- 
    Document   : list
    Created on : Jul 23, 2008, 3:38:03 PM
    Author     : emanuelle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>

<link href="css/course.css" rel="stylesheet" type="text/css" />

<div id="breadcrumb">
    <p><s:text name="breadcrumb.youAreHere"/></p>
    <ul>
        <li><a href="index.action"><s:text name="home.name"/></a></li>
        <li><a href="course!list.action"><s:text name="front.courses.title"/></a></li>
        <li class="current"><s:text name="front.grade"/></li>
    </ul>
</div>

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
                    
                    <td align="right">
                        <s:a href="%{enrollUrl}" cssClass="title-enrollment" ><s:text name="enrollment.add.action"/></s:a>
                    </td>
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
        <s:else>
            <%-- Tratar o caso de já estar matriculado --%>
            
            <p class="no-courses"><s:text name="enrollment.list.noGrade" /></p>
            
        </s:else>
    </table> 
</div>