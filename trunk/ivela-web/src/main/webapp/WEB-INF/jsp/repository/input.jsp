<%-- 
    Document   : input repository
    Created on : Jun 23, 2008, 4:51:31 PM
    Author     : marcus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    
    <s:form action="repository!list.action" method="POST">            
        <tr>
            <td colspan="2"><h1><s:text name="repository.input.list"/></h1></td>
        </tr>
        
        <s:textfield name="courseId" key="repository.input.gradeId"/>
        
        <s:submit key="repository.input.list"/>
    </s:form>
    
</html>
