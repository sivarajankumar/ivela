<%-- 
    Document   : edit System User
    Created on : Jul 30, 2008, 3:02:43 PM
    Author     : Maristella Myrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="systemUser.edit.pageTitle" /></title>
        <cal:head/>
        <s:head />
    </head>
    
    <tr>
        <td colspan="2"><h1><s:text name="systemUser.edit.pageTitle"/></h1></td>
    </tr>
    <s:form action="systemUser!update.action" method="post">
        <s:hidden name="systemUser.id"/>
        <s:textfield name="systemUser.username" key="systemUser.list.username"/>
        <s:textfield name="systemUser.email" key="systemUser.list.email"/>
        <s:select name="systemUser.enabled" list="#{'true':'yes','false':'no'}" key="systemUser.list.enabled"/>
        
        <s:select multiple="true" list="systemUser.authentications" listKey="id" 
                  listValue="name" key="systemUser.edit.authentications"/>
        
        <s:select multiple="true" list="systemUser.functionalities" listKey="id" 
                  listValue="name" key="systemUser.edit.functionalities"/>
        
        <s:submit key="systemUser.edit.update"/>
        
        <s:a href="systemUser!list.action" onclick="return confirm('Are you sure?')">Cancel</s:a>
    </s:form>
    
</html>