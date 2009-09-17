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
# Document: Input                                                                           #
# Date        - Author(Company)                   - Issue# - Summary                        #
# 05-JUN-2008 - Leo Moreira                       - XXXXXX - Initial Version                #
# 08-JUN-2009 - Fabio Fantato(Instituto Eldorado) - 000007 - IE7 compatibility              #
# 22-JUN-2009 - otofuji (Instituto Eldorado)      - 000010 - General Issues: i18n           #
# 27-AUG-2009 - Rafael Lagôa (Instituto Eldorado) - 000016 - Fix calendar positioning on IE7#
#############################################################################################
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="cal" uri="/jscalendar" %>

<head>
    <cal:head/>
    <script type="text/javascript" src="js/util/calendar.js"></script>
    <link href="css/login-index.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/prototype/prototype.js"></script>
    <script type="text/javascript" src="js/scriptaculous/scriptaculous.js"></script>
    <script type="text/javascript" src="js/scriptaculous/effects.js"></script>
    <script type="text/javascript" src="js/systemUser/base.js"></script>    
    <script type="text/javascript" src="js/password/passwordmeter.js"></script>       
    <script type="text/javascript">
        window.onload=function() {
            var height = 860;
            if( typeof( window.innerWidth ) == 'number' ) {
                //Not IE <= 7
                return;                            
            } else if( document.documentElement && ( document.documentElement.clientWidth || document.documentElement.clientHeight ) ) {              
                height = document.documentElement.clientHeight;
            } else if( document.body && ( document.body.clientWidth || document.body.clientHeight ) ) {                            
                height = document.body.clientHeight;
            }
            if (height < 860) {
               document.body.style.height = "auto";
            }                                 
        }
        
        var labelShowFields = "<s:text name="systemUser.input.showFields"/>";
        var labelHideFields = "<s:text name="systemUser.input.hideFields"/>";
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
                <label class="required-field"><s:text name="systemUser.input.login" />:</label>
                <s:textfield name="systemUser.username" cssClass="field-full-name" theme="simple" onkeyup="valid(this,'special')" onblur="valid(this,'special')" />
            </p>
            <p>
                <label class="required-field"><s:text name="systemUser.input.password"/>:</label>
                <s:password name="systemUser.password" cssClass="field-full-name" theme="simple" onkeyup="testPassword(this.value)" /> <span><img id="img_password" src="images/progress-bar/password/empty.jpg" /> <span id="verdict_msg"></span></span>
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
                    <cal:jscalendar name="profile.birthDate" format="%{dateFormat}" showstime="true" theme="simple"/>
                    <script>document.getElementsByName('profile.birthDate')[0].readOnly=true;</script>
                </p>
                <p>
                    <label><s:text name="systemUser.input.socialNumber"/>:</label>
                    <s:textfield name="systemUser.socialNumber" maxlength="14" />
                </p>
            </fieldset>
            
            <fieldset>
                <legend><s:text name="systemUser.input.addressTitle"/></legend>
                <p>
                    <label><s:text name="systemUser.input.addSt"/>:</label>                    
                    <input name="inAddress.location" id="location" type="text" />
                </p>
                <p>
                    <label><s:text name="systemUser.input.addNumber"/>:</label>
                    <input name="inAddress.number" type="text" />
                </p>
                <p>
                    <label><s:text name="systemUser.input.zipCode"/>:</label>
                    <input id="izipcode" name="inAddress.zipCode" maxlength="9" />
                </p>
                <p>     
                    <label><s:text name="systemUser.input.country"/>:</label>
                    <s:select list="countryList" onchange="selectStates(this.value)" name="inAddress.country" id="country"></s:select>                  
                </p>
                <p>
                    <label><s:text name="systemUser.input.state"/>:</label>
                    <select id="stateId" name="inAddress.state" ></select>
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
                    <input id="iphone" name="mainPhone" maxlength="14"/>
                </p>
                <p>
                    <label><s:text name="systemUser.input.mobile"/>:</label>
                    <input id="iphone" name ="mobile" maxlength="14"/>
                </p>
            </fieldset>
            
            <fieldset>
                <legend><s:text name="systemUser.input.otherTitle"/></legend>                
                <p>                    
                    <label><s:text name="systemUser.input.ethnicity"/>:</label>
                    <s:select list="ethnicityList" name="ethnicity" id="ethnicity"></s:select>
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
