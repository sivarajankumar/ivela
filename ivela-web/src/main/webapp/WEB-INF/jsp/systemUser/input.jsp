<%-- 
    Document   : input System User
    Created on : Jun 5, 2008, 9:14:56 AM
    Author     : leoomoreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="cal" uri="/jscalendar" %>

<head>
    <cal:head/>
    <link href="css/login-index.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/prototype/prototype.js"></script>
    <script type="text/javascript" src="js/scriptaculous/scriptaculous.js"></script>
    <script type="text/javascript" src="js/scriptaculous/effects.js"></script>
    <script type="text/javascript" src="js/systemUser/base.js"></script>
    <script type="text/javascript" src="js/systemUser/events.js"></script>
    <script type="text/javascript" src="js/password/passwordmeter.js"></script>
    <script type="text/javascript">
        <!--
        var labelShowFields = "<s:text name="systemUser.input.showFields"/>";
        var labelHideFields = "<s:text name="systemUser.input.hideFields"/>";
        -->
    </script>
    <script type="text/javascript">

        var r={'special':/[\W]/g}
        function valid(o,w)
        {
            if ( o.value.search(r[w]) > -1 )
            {
                //      alert('Caractere inválido');
            }

            o.value = o.value.replace(r[w],'');
        }

    </script> 
    
    
</head>

<div class="register">
    <h1><s:text name="systemUser.input.pageTitle"/></h1> <br />
    <p class="tip-login"><s:text name="systemUser.input.greeting"/></p>
    <p class="tip-login"><s:text name="systemUser.input.intro"/></p> <br />
    <p class="tip-login"><s:text name="systemUser.input.requiredFields"/> <br /> <br />
        <a class="show-field" href="#" id="toolsUser">
            <s:text name="systemUser.input.showFields"/>
        </a>        
    </p>
    
    <br />
    <s:actionmessage/>
    
    <s:form name="passwordForm" action="systemUser!add.action" method="post" theme="simple">
        <fieldset>
            <legend><s:text name="systemUser.input.loginPassTitle"/></legend>
            <p>
                <label class="required-field">Login:</label>
                <s:textfield name="systemUser.username" cssClass="field-full-name" theme="simple" onkeyup="valid(this,'special')" onblur="valid(this,'special')" />
            </p>
            <p>
                <label class="required-field"><s:text name="systemUser.input.password"/>:</label>
                <s:password name="systemUser.password" cssClass="field-full-name" theme="simple" onkeyup="testPassword(this.value)" /> <span><img id="img_password" /> <span id="verdict_msg"></span></span>
                <input id="score_password" name="scorePassword" type="hidden" value="0" />
                
            </p>
            <p>
                <label class="required-field"><s:text name="systemUser.input.retype"/>:</label>
                <s:password name="retypePassword" cssClass="field-full-name" theme="simple"/>
            </p>
            <p>
                <label class="required-field"><s:text name="systemUser.input.email"/>:</label>
                <s:textfield name="systemUser.email" cssClass="field-full-name" theme="simple"/>
            </p>
            <p>
                <label class="required-field"><s:text name="systemUser.input.retypeEmail"/>:</label>
                <s:textfield name="retypeEmail" cssClass="field-full-name" theme="simple"/>
            </p>                        
        </fieldset>
        
        <div id="hidden" style="display:none;">            
            <fieldset>
                <legend><s:text name="systemUser.input.personalInfo"/></legend>
                <p>
                    <label><s:text name="systemUser.input.honorific"/>:</label>
                    <s:select list="honorificList" listKey="id" listValue="title" name="honorific" id="honorific"></s:select>
                </p>
                <p>
                    <label><s:text name="systemUser.input.name"/>:</label>
                    <s:textfield name="profile.firstName" cssClass="field-full-name" theme="simple"/>
                </p>
                <p>
                    <label><s:text name="systemUser.input.surname"/>:</label>
                    <s:textfield name="profile.lastName" cssClass="field-full-name" theme="simple"/>
                </p>
                <p>
                    <label><s:text name="systemUser.input.abbreviation"/>:</label>
                    <input name="profile.initials" type="text" />
                </p>
                
                <p>
                    <label><s:text name="systemUser.input.birthdate"/>:</label>
                    <cal:jscalendar name="profile.birthDate"  format="%m/%d/%Y" showstime="true" theme="simple"/>
                </p>
                <p>
                    <label><s:text name="systemUser.input.socialNumber"/>:</label>
                    <s:textfield name="systemUser.socialNumber" onkeypress="mascara(this,socialnumber)" maxlength="14" />
                </p>
            </fieldset>
            
            <fieldset>
                <legend><s:text name="systemUser.input.addressTitle"/></legend>
                <p>
                    <label><s:text name="systemUser.input.addSt"/>:</label>
                    <s:select list="locationTypeList" listKey="id" listValue="description" name="inAddress.locationType.id" id="locationType"></s:select>
                    <input name="inAddress.location" id="location" type="text" />
                </p>
                <p>
                    <label><s:text name="systemUser.input.addNumber"/>:</label>
                    <input name="inAddress.number" type="text" />
                </p>
                <p>
                    <label><s:text name="systemUser.input.zipCode"/>:</label>
                    <input id="izipcode" name="inAddress.zipCode" onkeypress="mascara(this,zipcode)" maxlength="9" />
                </p>
                <p>     
                    <label><s:text name="systemUser.input.country"/>:</label>
                    <s:select list="countryList" listKey="id"  onchange="selectStates(this.value)" listValue="name" name="country" id="country"></s:select>                  
                </p>
                <p>
                    <label><s:text name="systemUser.input.state"/>:</label>
                    <select id="stateId" name="inAddress.state.id" ></select>
                </p>
                <p>
                    <label><s:text name="systemUser.input.city"/>:</label>
                    <input name="inAddress.city" type="text" />
                </p>
                
            </fieldset>
            
            <fieldset>
                <legend><s:text name="systemUser.input.phoneTitle"/></legend>
                <p>
                    <label><s:text name="systemUser.input.phone"/>:</label>
                    <input id="iphone" name="mainPhone" maxlength="14" onkeypress="mascara(this,phone)"/>
                </p>
                <p>
                    <label><s:text name="systemUser.input.mobile"/>:</label>
                    <input id="iphone" name ="mobile" maxlength="14" onkeypress="mascara(this,phone)"/>
                </p>
            </fieldset>
            
            <fieldset>
                <legend><s:text name="systemUser.input.otherTitle"/></legend>
                <p>
                    <label><s:text name="systemUser.input.language"/>:</label>
                    <s:select list="languageInternationalizationList" listKey="id" listValue="language.name" name="language" id="language"></s:select>
                </p>
                <p>
                    <label><s:text name="systemUser.input.ethnicity"/>:</label>
                    <s:select list="ethnicityList" listKey="id" listValue="name" name="ethnicity" id="ethnicity"></s:select>
                </p>
            </fieldset>
            
        </div>
        <%--
        <fieldset>
            <legend><s:text name="systemUser.input.codeTitle"/></legend>
            <p>                
                <label class="required-field">*<s:text name="systemUser.input.code"/>:</label>
                <s:textfield name="captchaValue" theme="simple" cssClass="field-security-cod"/>
                
                <img src="captcha.jpg" width="200" height="50"/>
            </p>
        </fieldset>
        --%>
        <p style="text-align:center;" >
            <input class="btn-back" type="button" onclick="history.back(1)" value="<s:text name="systemUser.input.btnBack"/>" />
            <s:submit cssClass="btn-save" key="systemUser.input.btnSave" theme="simple"/>
            <input class="btn-cancel" type="reset" value="<s:text name="systemUser.input.btnClean"/>" />
        </p>
    </s:form>
</div>
