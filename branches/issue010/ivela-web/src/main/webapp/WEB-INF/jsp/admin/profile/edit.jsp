<%-- 
    Document   : edit Profile
    Created on : Jun 27, 2008, 3:04:37 PM
    Author     : Maristella Myrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="cal" uri="/jscalendar" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="../css/profile.css" rel="stylesheet" type="text/css" />
    
    <cal:head/>
    <s:head />
</head>


<div class="register">
    
    
    <h1><s:text name="profile.edit"/></h1> <br />
    <br />
    <s:if test="sucess==true">
    <h2> <s:text name="profile.updateSucess" /></h2> </s:if>
    <s:form action="profile!update.action" method="post" cssClass="form" enctype="multipart/form-data" >
        
        <s:hidden name="profile.id" />
        <fieldset><legend><s:text name="systemUser.input.personalInfo"/></legend>
            
            <p>
                <label><s:text name="profile.disabilities" />:</label>
                <span><s:radio name="profile.disabilities" list="#{'true':'yes','false':'no'}" theme="simple"/></span>
            </p>
            <p>
                <label><s:text name="profile.honorific" />:</label>
                <s:select list="honorificList" listKey="id" listValue="title" name="profile.honorific.id" id="honorific" theme="simple"/>
            </p>
            <p>
                <label><s:text name="profile.firstName" />:</label>
                <s:textfield name="profile.firstName" theme="simple" />
            </p>
            <p>
                <label><s:text name="profile.lastName" />:</label>
                <s:textfield name="profile.lastName" theme="simple" />
            </p>
            <p>
                <label><s:text name="profile.gender" />:</label>
                <span><s:radio name="profile.gender" list="#{'1':'masculino','0':'feminino'}"  theme="simple"/></span>
            </p>
            <p>
                <label><s:text name="profile.initials" />:</label>
                <s:textfield name="profile.initials" theme="simple"  />
            </p>
            
            <p>
                <label><s:text name="profile.photo" />:</label>
                <s:file name="upload" theme="simple" value=""/>
                <span name="profile.photo" onclick="insertFile()" /> 
            </p>
            <p>
                <label><s:text name="profile.socialNumber" />:</label>
                <s:textfield name="profile.socialNumber" theme="simple"  />
            </p>
            <p>
                <label><s:text name="profile.ethnicity" />:</label>
                <s:select list="ethnicityList" listKey="id" listValue="name" name="profile.ethnicity.id" id="ethnicity" theme="simple"></s:select>
            </p>
            <p>
                <label><s:text name="profile.language" />:</label>
                <s:select list="languageInternationalizationList" listKey="id" listValue="language.name" name="profile.language.id" id="language" theme="simple"></s:select>
            </p>
            <p>
                <label><s:text name="profile.birthDate" />:</label>
                <cal:jscalendar name="profile.birthDate"  format="%m/%d/%Y" showstime="true" theme="simple"/>
            </p>
            
        </fieldset>
        
        <p style="text-align:center;" >
        <span><s:submit cssClass="btn-save" key="systemUser.input.btnSave" theme="simple"/></span>
        
        
    </s:form>
    
</div>
