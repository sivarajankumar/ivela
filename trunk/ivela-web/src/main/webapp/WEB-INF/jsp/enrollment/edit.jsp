<%-- 
    Document   : edit
    Created on : Jul 25, 2008, 1:55:58 PM
    Author     : emanuelle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"  %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="enrollment.edit.pageTitle"/></title>
    </head>
    <body>
        <sec:authorize ifAnyGranted="ROLE_COORD">
            <h2><s:text name="enrollment.edit.title"/></h2>
            <s:form id="form_edit" action="enrollment!edit.action" method="post">
                <table border="1" >
                    <tr>
                        <td><s:text name="enrollment.edit.select"/></td>
                        <td><s:select list="gradeList" listKey="id" listValue="name" name="grade.id" theme="simple" onchange="" /></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><s:submit key="enrollment.edit.buttonSelect" theme="simple"/></td>
                    </tr>
                </table>
            </s:form>
            <table border="1">
                <tr>
                    <td><s:text name="enrollment.edit.courseName"/></td>
                    <td><s:text name="enrollment.edit.grade"/></td>
                    <td><s:text name="enrollment.edit.validation"/></td>
                    <td><s:text name="enrollment.edit.gradePeriod"/></td>
                    <td><s:text name="enrollment.edit.systemUserName"/></td>
                    <td><s:text name="enrollment.edit.systemUserState"/></td>
                    <td><s:text name="enrollment.edit.systemUserCity"/></td>
                    <td><s:text name="enrollment.edit.status"/></td>
                </tr>
                <s:iterator value="enrollmentList" status="count">
                    <tr>
                        <td>
                            <s:property value="grade.course.name" />
                        </td>
                        <td>
                            <s:property value="grade.name" />
                        </td>
                        <td>
                            <s:property value="grade.requiresEnrollmentValidation" />
                        </td>
                        <td>
                            <s:property value="grade.period" />
                        </td>
                        <td>
                            <s:property value="systemUser.profile.name" />
                        </td>
                        <td>
                            <s:property value="systemUser.profile.state" />
                        </td>
                        <td>
                            <s:property value="systemUser.profile.city" />
                        </td>
                        <td>    
                            <s:form id="%{#count.index}" action="enrollment!update.action" method="get">
                                <s:hidden name="enrollment.id" value="%{id}"/>
                                <s:select name="enrollment.status" value="%{status}" list="#{'0':'Pending','1':'Active', '2':'Suspended'}" />
                                <s:submit key="enrollment.edit.buttonSelect"/>
                            </s:form>
                        </td>
                    </tr>
                </s:iterator>
            </table>
        </sec:authorize>
        <sec:authorize ifNotGranted="ROLE_COORD">
            <s:text name="enrollment.edit.notCoordinator"/>
        </sec:authorize>
    </body>
</html>
