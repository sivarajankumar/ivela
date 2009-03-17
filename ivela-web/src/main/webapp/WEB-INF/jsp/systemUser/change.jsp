<%-- 
    Document   : change
    Created on : Nov 19, 2008, 1:55:04 PM
    Author     : marcus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/profile.css" rel="stylesheet" type="text/css" />

        <s:head />
    </head>

    <s:actionmessage/>
    
    <div class="register">
        <h1><s:text name="systemUser.password.change"/></h1> <br />
        <br />

        <s:form action="systemUser!change.action" method="post" cssClass="form">

            <fieldset>
                <p>
                    <label style="margin-right: 5px; display:block; width: 150px; float:left; text-align:right; padding-top: 6px;"><s:text name="systemUser.password.actual"/>:</label>
                    <span><s:password cssClass="general-input" name="password" theme="simple"/></span>
                </p>
                <p>
                    <label style="margin-right: 5px; display:block; width: 150px; float:left; text-align:right; padding-top: 6px;"><s:text name="systemUser.password.new"/>:</label>
                    <span><s:password cssClass="general-input" name="newPassword" theme="simple"/></span>
                </p>
                <p>
                     <label style=" margin-right: 5px; display:block; width: 150px; float:left; text-align:right; padding-top: 6px;"><s:text name="systemUser.password.retype"/>:</label>
                    <span><s:password cssClass="general-input" name="retypePassword" theme="simple"/></span>
                </p>

            </fieldset>

            <p style="text-align:center;" >
            <span><s:submit cssClass="btn-save" key="systemUser.input.btnSave" theme="simple"/></span>

        </s:form>
    </div>
</html>
</s:form>
    </div>
</html>
