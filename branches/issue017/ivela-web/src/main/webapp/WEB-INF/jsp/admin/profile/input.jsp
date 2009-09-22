<%--  
#############################################################################################  
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
# File: input.jsp                                                                           #
# Document: Input Profile Page                                                              # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 27-JUN-2008 - leomoreira (UFC)                  - XXXXXX - Initial Version                #
# 22-JUN-2009 - otofuji (Instituto Eldorado)      - 000010 - General Issues                 #
# 28-AGO-2009 - lagoa   (Instituto Eldorado)      - 000016 - Set date field as readonly     #
#############################################################################################
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="cal" uri="/jscalendar" %>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="css/profile.css" rel="stylesheet" type="text/css" />
        
        <cal:head/>
        <script type="text/javascript" src="js/util/calendar.js"></script>
        <s:head />
    </head>
    <div class="register">
        
    <h1><s:text name="profile.edit"/></h1> <br />
    
    <br />
        <s:if test="sucess==true">
        <h2> <s:text name="profile.updateSucess" /></h2> </s:if>
        <s:form action="profile!update.action" method="post" cssClass="form"enctype="multipart/form-data" >
            
            <s:hidden name="profile.id" />
            <fieldset><legend><s:text name="systemUser.input.personalInfo"/></legend>
                <p>
                    <label><s:text name="profile.disabilities" />:</label>                    
                    <span><s:radio name="profile.disabilities" list="disabilitiesList" theme="simple"/></span>
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
                    <span><s:radio name="profile.gender" list="genderList"  theme="simple"/></span>
                </p>
                <p>
                    <label><s:text name="profile.initials" />:</label>
                    <s:textfield name="profile.initials" theme="simple"  />
                </p>
                
                <p>
                    <label><s:text name="profile.photo" />:</label>
                    <s:file name="upload" theme="simple"  />
                    <span name="profile.photo" onclick="insertFile()" /> 
                </p>
                <p>
                    <label><s:text name="profile.socialNumber" />:</label>
                    <s:textfield name="profile.socialNumber" theme="simple"  />
                </p>
                <p>
                    <label><s:text name="profile.ethnicity" />:</label>
                    <s:select list="ethnicityList" name="profile.ethnicity.id" id="ethnicity" theme="simple"></s:select>
                </p>                
                <p>
                    <label><s:text name="profile.birthDate" />:</label>
                    <cal:jscalendar name="profile.birthDate"  format="%m/%d/%Y" showstime="true" theme="simple" onfocus="this.readOnly=true;"/>
                    <script>document.getElementsByName('profile.birthDate')[0].readOnly=true;</script>
                </p>
                
            </fieldset>
            
            <p style="text-align:center;" >
            <span><s:submit cssClass="btn-save" key="systemUser.input.btnSave" theme="simple"/></span>
            
            
        </s:form>
        
    </div>
</html>