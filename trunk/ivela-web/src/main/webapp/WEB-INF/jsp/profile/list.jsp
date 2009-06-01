<%-- 
    Document   : list
    Created on : Jun 19, 2008, 3:03:24 PM
    Author     : Maristella Myrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="cal" uri="/jscalendar" %>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <s:head />
    </head>
    <h1>Profile</h1>
    
    <h2>Profile List</h2>
    
    <cal:head/>
    <table border="1">
        <tr>
            
            <td><s:text name="profile.disabilities"/></td>
            <td><s:text name="profile.honorific"/></td>
            <td><s:text name="profile.firstName"/></td>
            <td><s:text name="profile.lastName"/></td>
            <td><s:text name="profile.initials"/></td>
            <td><s:text name="profile.gender"/></td>
            <td><s:text name="profile.ethnicity"/></td>
            <td><s:text name="profile.birthDate"/></td>
            <td><s:text name="profile.language"/></td>
            <td><s:text name="profile.phones"/></td>
            <td><s:text name="profile.photo"/></td>
            <td><s:text name="profile.socialNumber"/></td>
            
        </tr>
        <s:iterator value="profileList">
            <tr>
                <td><s:property value="disabilities"/></td>
                <td><s:property value="honorific.title"/></td>
                <td><s:property value="firstName"/></td>
                <td><s:property value="lastName"/></td>
                <td><s:property value="initials"/></td>
                <td><s:property value="gender"/></td>
                <td><s:property value="ethnicity.name"/></td>
                <td><s:property value="birthDate"/></td>
                <td><s:property value="language.language.name"/></td>
                <td><s:property value="phones"/></td>
                <td><s:property value="photo"/></td>
                <td><s:property value="socialNumber"/></td>
                
                
                <s:url id="editUrl" action="profile" method="edit">
                    <s:param name="profile.id" value="id"/>
                </s:url>
                
                <s:url id="deleteUrl" action="profile" method="remove">
                    <s:param name="profile.id" value="id"/>
                </s:url>
                
                    <td><s:a href="%{editUrl}">Edit</s:a>/<s:a href="%{deleteUrl}" onclick="return confirm('Are you sure?')">Delete</s:a></td>
            </tr>
        </s:iterator>
    </table>
        <s:a href="profile!input.action">Create a new profile</s:a>
    
    
</html>
