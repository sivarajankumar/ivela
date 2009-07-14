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
# File: forgotPassword.jsp                                                                   #
# Document: Forgot Password page                                                            # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 24-JUN-2008 - Marcus (UFC)                      - XXXXXX - Initial Version                #
# 24-JUN-2009 - otofuji (Instituto Eldorado)      - 000007 - IE7 compatibility              #
#############################################################################################
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript" src="js/prototype/prototype.js"></script>
<script type="text/javascript">
function getJsonFromUrl(url){
var json;
new Ajax.Request(url,
{
    method:'post',
    requestHeaders: {Accept: 'application/json'}, 
    asynchronous: false,
    onSuccess: function(transport) {
        json = transport.responseText.evalJSON(true);
    },
    onFailure: function() { alert('Message: Something went wrong...') }
});
return json;
}   
function forgotPassword() {
    var json = getJsonFromUrl('systemUser!forgotPasswordExecute.action?username=' + document.getElementById('username').value + '&email=' + document.getElementById('email').value);
    if (json != null && json != '') {
        if (json.result == 'false') {
            if (json.message == 'inconsistence') {
                alert('<s:text name="systemUser.input.forgot.error"/>');
                
                
            }
        }
        else {            
            alert('<s:text name="systemUser.input.forgot.sent"/>');
            document.location = 'login.jsp';
        }
    }
}
</script>
<html>
    <h1><s:text name="login.forgot.password"/></h1> <br />
    
    <p class="tip-login"><s:text name="login.message" /></p>
    
    
    
        <p class="form-user">
            <label><s:text name="systemUser.input.username"/></label><br/>
            <input type="text" id="username" style="width:350px" /><br/><br/>
            <label><s:text name="general.email.cc"/></label><br/>
            <input type="text" id="email" style="width:450px;"/><br/><br/>
            <input class="btn-back" type="button" onclick="history.back(1)" value="<s:text name="systemUser.input.btnBack"/>" />
            <input class="btn-save" type="button" onclick="forgotPassword();" value="<s:text name="systemUser.input.btnSubmit"/>" />
            
            
            
        </p>
        
</html>
