<%-- 
    Document   : edit Profile
    Created on : Jun 27, 2008, 3:04:37 PM
    Author     : Maristella Myrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="cal" uri="/jscalendar" %>


    <head>
       
    <link href="css/profile.css" rel="stylesheet" type="text/css" />
        
    <script type="text/javascript" src="js/prototype/prototype.js"></script>
    <script type="text/javascript" src="js/scriptaculous/scriptaculous.js"></script>
    <script type="text/javascript" src="js/systemUser/profile.js"></script>
    <script type="text/javascript" src="js/systemUser/base.js"></script>
        <cal:head/>
        <s:head />
    </head>
    
    
    <div class="register">
        
        
        <h1><s:text name="profile.edit"/></h1> <br />
        <br />
        <s:if test="sucess==true"><h2> <s:text name="profile.updateSucess" /></h2> </s:if>
        <s:form action="profile!update.action" method="post" cssClass="form"enctype="multipart/form-data" >
        
            <p><a href="systemUser!inputChange.action"><s:text name="profile.edit.password" /></a></p>
            <br />
            <s:hidden name="profile.id" value="%{profile.id}"/>
            <s:hidden name="address.state.id" value="%{inAddress.state.id}"/>
            <fieldset><legend><s:text name="systemUser.input.personalInfo"/></legend>
                <p >
                    <label><s:text name="profile.disabilities" />:</label>
                    <span><s:radio name="profile.disabilities" list="#{'true':'yes','false':'no'}" theme="simple"/></span>
                </p>
                <p>
                    <label><s:text name="profile.honorific" />:</label>
                    <s:select list="honorificList" listKey="id" listValue="title" value="%{profile.honorific.id}" name="profile.honorific.id" id="honorific" theme="simple"/>
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
                    <span><s:radio name="profile.gender" list="#{'1':'masculino','0':'feminino'}"  theme="simple"/></span>
                </p>
                <p>
                    <label><s:text name="profile.initials" />:</label>
                    <s:textfield cssClass="general-input"name="profile.initials" theme="simple"  />
                </p>
                
                <p>
                    <label><s:text name="profile.photo" />:</label>
                    <s:file name="upload" theme="simple"  />
                    <span name="profile.photo" onclick="insertFile()" /> 
                </p>
                <p>
                    <label><s:text name="profile.socialNumber" />:</label>
                    <s:textfield cssClass="general-input" name="profile.socialNumber" theme="simple"  />
                </p>
                <p>
                    <label><s:text name="profile.ethnicity" />:</label>
                    <s:select  list="ethnicityList" listKey="id" listValue="name" value="%{profile.ethnicity.id}" name="profile.ethnicity.id" id="ethnicity" theme="simple"></s:select>
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
            
            <s:hidden name="inAddress.id" value="%{inAddress.id}"/>
            <s:hidden name="inAddress.profileId" value="%{profile.id}" />
            <fieldset>
                <legend><s:text name="systemUser.input.addressTitle"/></legend>
                <p>
                    <label><s:text name="systemUser.input.addSt"/>:</label>
                    <s:select list="locationTypeList" listKey="id" listValue="description" name="inAddress.locationType.id" id="locationType"  value="%{inAddress.locationType.id}" />
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
                    <s:select list="countryList" listKey="id" listValue="name"  onchange="selectStates(this.value)" name="country.id" id="country" value="%{inAddress.state.country.id}"></s:select>                  
                </p>
                <p>
                    <label><s:text name="systemUser.input.state"/>:</label>
                    <s:select  id="stateId" name="inAddress.state.id" listKey="id" listValue="name" list="inAddress.state.country.states" value="%{inAddress.state.id}">
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
    
