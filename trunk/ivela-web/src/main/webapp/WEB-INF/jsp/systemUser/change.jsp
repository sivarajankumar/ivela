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
# File: change.jsp                                                                          #
# Document: Change                                                                          #
# Date        - Author(Company)                   - Issue# - Summary                        #
# 19-NOV-2008 - marcus                            - XXXXXX - Initial Version                #
# 01-JUL-2009 - mileine (Instituto Eldorado)      - 000010 - Password strength test added   #
# 26-AUG-2009 - Rafael Lagoa (Instituto Eldorado) - 000902 - Fieldset legend line fix       #
#############################################################################################
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/profile.css" rel="stylesheet" type="text/css" />
        <link href="css/login-index.css" rel="stylesheet" type="text/css" />
		 <script type="text/javascript" src="js/prototype/prototype.js"></script>
		 <script type="text/javascript" src="js/password/passwordmeter.js"></script>       
        <s:head />
    </head>
    
    <div class="register">
      <br><br>

        <a href="profile!edit.action"><img src="images/arrow-left.gif" /> <s:text name="systemUser.input.btnBack" /></a>
        
        <s:actionerror/>
        <s:actionmessage/>

        <s:form name="changePasswordForm" action="systemUser!change.action" method="post" theme="simple">

            <fieldset>
                <legend><s:text name="systemUser.password.change"/></legend>
                <p>
                    <label style="margin-right: 5px; display:block; width: 150px; float:left; text-align:right; padding-top: 6px;"><s:text name="systemUser.password.actual"/>:</label>
                    <span><s:password cssClass="general-input" name="password" theme="simple"/></span>
                </p>
                <p>
                    <label style="margin-right: 5px; display:block; width: 150px; float:left; text-align:right; padding-top: 6px;"><s:text name="systemUser.password.new"/>:</label>
                    <span><s:password cssClass="general-input" name="newPassword" theme="simple" onkeyup="testPassword(this.value)" /></span> <span><img id="img_password" src="images/progress-bar/password/empty.jpg" /> <span id="verdict_msg"></span></span>
                </p>
                <p>
                     <label style="margin-right: 5px; display:block; width: 150px; float:left; text-align:right; padding-top: 6px;"><s:text name="systemUser.password.retype"/>:</label>
                    <span><s:password cssClass="general-input" name="retypePassword" theme="simple"/></span>
                </p>

            </fieldset>

            <p style="text-align:center;" >
            <span><s:submit cssClass="btn-save" key="systemUser.input.btnSave" theme="simple"/></span>
			</p>
            <input id="score_password" name="scorePassword" type="hidden" value="0" />
        </s:form>
    </div>
</html>
