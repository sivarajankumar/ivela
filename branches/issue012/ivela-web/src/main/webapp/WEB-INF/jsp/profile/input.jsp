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
# File: input.jsp                                                                            #
# Document: Input Profile Page                                                               # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 27-JUN-2008 - Maristella Myrian (UFC)           - XXXXXX - Initial Version                #
# 22-JUN-2009 - otofuji (Instituto Eldorado)      - 000010 - General Issues                 #
#############################################################################################
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="cal" uri="/jscalendar" %>

<head>
    <link href="css/profile.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript" src="js/prototype/prototype.js"></script>
    <script type="text/javascript" src="js/scriptaculous/scriptaculous.js"></script>    
    <script type="text/javascript" src="js/systemUser/base.js"></script>
    <cal:head/>
    <s:head />
</head>
    
    
<div class="register">


    <h1><s:text name="profile.edit"/></h1> <br />
    <br />
    
    <s:actionerror />
    <s:fielderror/>
    <s:if test="sucess==true"><h2> <s:text name="profile.updateSucess" /></h2> </s:if>
    <s:form action="profile!update.action" method="post" cssClass="form"enctype="multipart/form-data" theme="simple">

        <p><a href="systemUser!inputChange.action"><s:text name="profile.edit.password" /></a></p>
        
        <s:hidden name="profile.id" value="%{profile.id}"/>
        <s:hidden name="address.state.id" value="%{inAddress.state.id}"/>
        <fieldset><legend><s:text name="systemUser.input.personalInfo"/></legend>
            <p >
                <label><s:text name="profile.disabilities" />:</label>
                <span><s:radio name="profile.disabilities" list="disabilitiesList" theme="simple"/></span>
            </p>            
            <p>
                <label><s:text name="profile.firstName" />:</label>
                <s:textfield cssClass="general-input" name="profile.firstName" theme="simple" />
            </p>
            <p>
                <label><s:text name="profile.lastName" />:</label>
                <s:textfield cssClass="general-input"name="profile.lastName" theme="simple" />
            </p>
            <p class="select-type">
                <label><s:text name="profile.gender" />:</label>
                <span><s:radio name="profile.gender" list="genderList"  theme="simple"/></span>
            </p>
            <p>
                <label><s:text name="profile.initials" />:</label>
                <s:textfield cssClass="general-input"name="profile.initials" theme="simple"  />
            </p>

            <p>
                <label><s:text name="profile.photo" />:</label>
                <s:file name="upload" theme="simple"  />                
            </p>
            <p>
                <label><s:text name="profile.socialNumber" />:</label>
                <s:textfield cssClass="general-input" name="profile.socialNumber" theme="simple"  />
            </p>
            <p>
                <label><s:text name="profile.ethnicity" />:</label>
                <s:select  list="ethnicityList" value="%{profile.ethnicity}" name="profile.ethnicity" id="ethnicity" theme="simple"></s:select>
            </p>            
            <p>
                <label><s:text name="profile.birthDate" />:</label>
                <cal:jscalendar name="profile.birthDate"  format="%{dateFormat}" showstime="true" theme="simple"/>
            </p>

        </fieldset>

        <s:hidden name="inAddress.id" value="%{inAddress.id}"/>
        <s:hidden name="inAddress.profileId" value="%{profile.id}" />
        <fieldset>
            <legend><s:text name="systemUser.input.addressTitle"/></legend>
            <p>
                <label><s:text name="systemUser.input.addSt"/>:</label>                
                <input name="inAddress.location" id="location" type="text" value="<s:property value="%{inAddress.location}"/>" />
            </p>
            <p>
                <label><s:text name="systemUser.input.addNumber"/>:</label>
                <input name="inAddress.number" type="text"  value="<s:property value="%{inAddress.number}"/>" />
            </p>
            <p>
                <label><s:text name="systemUser.input.zipCode"/>:</label>
                <input id="izipcode" name="inAddress.zipCode" value="<s:property value="%{inAddress.zipCode}"/> " onkeypress="mascara(this,zipcode)" maxlength="9" />
            </p>
            <p>     
                <label><s:text name="systemUser.input.country"/>:</label>
                <s:select list="countryList" onchange="selectStates(this.value)" name="inAddress.country" id="country" value="%{inAddress.country}"></s:select>                  
            </p>
            <p>
                <label><s:text name="systemUser.input.state"/>:</label>
                <s:select  id="stateId" name="inAddress.state" list="stateList" value="%{inAddress.state}">
                </s:select>
            </p>
            <p>
                <label><s:text name="systemUser.input.city"/>:</label>
                <input name="inAddress.city" type="text" value="<s:property value="%{inAddress.city}"/>" />
            </p>

        </fieldset>
        <fieldset>
            <s:hidden  name="listPhones[0].id" value="%{listPhones[0].id}"/>
            <s:hidden  name="listPhones[1].id" value="%{listPhones[1].id}"/>
            <s:hidden  name="listPhones[0].profileId" value="%{profile.id}"/>
            <s:hidden  name="listPhones[1].profileId" value="%{profile.id}"/>
            <legend><s:text name="systemUser.input.phoneTitle"/></legend>
            <p>
                <label><s:text name="systemUser.input.phone"/>:</label>
                <input id="iphone" name="listPhones[0].number" value="<s:property value="%{listPhones[0].number}"/>" maxlength="14" onkeypress="mascara(this,phone)"/>
            </p>
            <p>
                <label><s:text name="systemUser.input.mobile"/>:</label>
                <input id="iphone"  name="listPhones[1].number"  maxlength="14" value="<s:property value="%{listPhones[1].number}"/>" onkeypress="mascara(this,phone)"/>
            </p>
        </fieldset>

        <span><s:submit cssClass="btn-save" key="systemUser.input.btnSave" theme="simple"/></span>

    </s:form>
</div>
    
