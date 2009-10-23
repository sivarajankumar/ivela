<%-- 
    Document   : show
    Created on : Jul 25, 2008, 12:01:41 PM
    Author     : emanuelle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <table>
        <s:iterator value="enrollmentList">
            <tr>
                <td>    
                    <s:property value="grade.course.name" />
                </td>
                
                <s:url id="listGradeUrl" action="enrollment" method="cancel">
                    <s:param name="enrollment.id" value="id"/>
                </s:url>
                <td>
                    <s:a href="%{listGradeUrl}"><s:text name="enrollment.remove.action" /></s:a>
                </td>
            </tr>
        </s:iterator>
    </table>
</html>
