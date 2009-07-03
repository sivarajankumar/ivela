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
# File: login.jsp                                                                           #
# Document: Login page                                                                      # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# XX-XXX-XXXX - XXX                               - XXXXXX - Initial Version                #
# 03-JUL-2009 - mileine (Instituto Eldorado)      - 000010 - Login fields position fixed    #
#############################################################################################
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="org.springframework.security.ui.AbstractProcessingFilter" %>
<%@ page import="org.springframework.security.ui.webapp.AuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.AuthenticationException" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<link href="css/login-index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/prototype/prototype.js"></script>
<script type="text/javascript" src="js/login.js"></script>
 
<c:if test="${param.success == true}">
    <p class="sucess"> <s:text name="systemUser.input.loginInsertedSucessfully"/></p>
</c:if>

<div class="login-container">
    <form name="f" class="form-login-index" action="<c:url value='j_spring_security_check'/>" method="POST">
      <table border="0"  cellpadding="1" >
      <tr class="box-user-index" valign = "middle" >
      	<td align="right"><label><s:text name="login.user"/></label></td>
      	<td colspan="4"><input class="field-user-index" id="loginfield" type="text" name="j_username" value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>' maxlength="33" onkeyup="valid(this,'special')" onblur="valid(this,'special')" /></td>
      </tr>
      
      <tr class="box-pass-index" valign = "middle" >
      	<td><label><s:text name="login.password"/></label></td>
      	<td><input class="field-pass-index" type="password" name="j_password" maxlength="17" /></td>
      	<td><input class="buton-login-index" type="image" src="images/login/buton-login-index.gif" /> </td>
      </tr>
      
      <tr class="box-pass-index" valign = "top">
            <td></td>
      		<td class="forgot">
           		<table border="0" style="position:relative;top:0px;left:0px"><tr><td><a href="forgotPassword.action" class="forgot"><s:text name="login.forgot.password"/></a></td></tr></table> 
           	</td>
            <td></td>
      </tr>
      <tr>
      		<td colspan="3"  valign = "bottom"> 
      			<p class="error"><c:if test="${not empty param.login_error}">
               		<s:text name="login.fail"/>
            	</c:if>  </p>
            </td>
      </tr>
      </table>
     
         
    </form>
    <div class="tools-login">
        
        <a href="faq!list.action" title="FAQ">FAQ</a>
        <a href="aboutUs!list.action" title=<s:text name="login.about.us"/>><s:text name="login.about.us"/></a>
        <a href="systemUser!input.action" title=<s:text name="login.join.now"/>><s:text name="login.join.now"/></a>
    </div>
    <br class="clear" />
</div>