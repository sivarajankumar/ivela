<%--
#############################################################################################
# Copyright(c) 2008-2009 by IBM Brasil Ltda and others                                      #
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
# XX-XXX-XXXX - X                                 - XXXXXX - Initial Version                #
# 10-JUN-2009 - Rafael Lagoa (Instituto Eldorado) - 000011 - System pre-requisites check    #
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
<script type="text/javascript" src="js/util/deployJava.js"></script>
<script type="text/javascript" src="js/login.js"></script>
 
<c:if test="${param.success == true}">
    <p class="sucess"> <s:text name="systemUser.input.loginInsertedSucessfully"/></p>
</c:if>

<div class="login-container">
    <div id="dependenciesWarning" style="width:100px ; height:30px; text-align:center; font:15px Arial, Helvetica, Sans-Serif; color:white; font-weight:bold; line-height: 30px"></div>
    <form name="f" class="form-login-index" action="<c:url value='j_spring_security_check'/>" method="POST">
        <div class="box-user-index">
            <label><s:text name="login.user"/></label>
            <input class="field-user-index" id="loginfield" type="text" name="j_username" value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>' maxlength="33" onkeyup="valid(this,'special')" onblur="valid(this,'special')" />
        </div>
        <div class="box-pass-index">
            <label><s:text name="login.password"/></label>
            <input class="field-pass-index" type="password" name="j_password" maxlength="17" />
            <input class="buton-login-index" type="image" src="images/login/buton-login-index.gif" /> <br /> 
            <p class="check">             
                <%--input type="checkbox" name="_spring_security_remember_me" id="checkbox" /><s:text name="login.remember"/--%>
                <a href="forgotPassword.action" class="forgot"><s:text name="login.forgot.password"/></a> <br />
            </p>
            <c:if test="${not empty param.login_error}">
               <p class="error"><s:text name="login.fail"/></p>
            </c:if>            
        </div>
    </form>
    
    <div class="tools-login">
        
        <a href="faq!list.action" title="FAQ">FAQ</a>
        <a href="aboutUs!list.action" title=<s:text name="login.about.us"/>><s:text name="login.about.us"/></a>
        <a href="systemUser!input.action" title=<s:text name="login.join.now"/>><s:text name="login.join.now"/></a>
    </div>
    <br class="clear" />
</div>
<div id="dependenciesTooltip" style="display:none; background:#efefef; border:1px solid #999; padding:5px; font:11px Arial, Helvetica, Sans-Serif"></div>
<div id="dependenciesOK" style="display:none"><s:text name="login.check.ok"/></div>
<div id="dependenciesERROR" style="display:none"><s:text name="login.check.error"/></div>
<div id="dependenciesSCREEN" style="display:none"><s:text name="login.check.screen"/></div>
<div id="dependenciesBROWSER" style="display:none"><s:text name="login.check.browser"/></div>
<div id="dependenciesJRE" style="display:none"><s:text name="login.check.jre"/></div>
