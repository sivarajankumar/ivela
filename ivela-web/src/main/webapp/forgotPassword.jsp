<%-- 
    Document   : Forgot Password System User
    Created on : Jun 24, 2008, 2:31:54 PM
    Author     : Marcus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript" src="js/prototype/prototype.js"></script>
<script type="text/javascript">
function getJsonFromUrl(url){
var json;
new Ajax.Request(url,
{
    method:'get',
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
                alert('Login ou email não conferem');
            }
        }
        else {
            alert('Sua nova senha foi enviada para o seu e-mail');
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
            <label>email</label><br/>
            <input type="text" id="email" style="width:450px;"/><br/><br/>
            <input class="btn-back" type="button" onclick="history.back(1)" value="<s:text name="systemUser.input.btnBack"/>" />
            <input class="btn-save" type="button" onclick="forgotPassword();" value="Submit" />
            
            
            
        </p>
        
</html>
