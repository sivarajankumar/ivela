<%-- 
    Document   : input Faq
    Created on : Jun 5, 2008, 9:14:56 AM
    Author     : leoomoreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="faq.pageTitle" /></title>
        <link href="../css/faq_admin.css" rel="stylesheet" type="text/css" />
        <cal:head/>
        <s:head />
    </head>
    <div id="breadcrumb">
        <p><s:text name="admin.breadcrumb.youAreHere"/></p>
        <ul>
            <li><a href="home.action" title="<s:property value="admin.home.title"/>"><s:text name="admin.home"/></a></li>
            <li><a href="faq!list.action" title="<s:property value="faq.pageTitle"/>"><s:text name="faq.pageTitle"/></a></li>
            <li class="current" title="<s:property value="faq.edit.pageTitle"/>"><s:text name="faq.edit.pageTitle"/></li>
        </ul>
    </div>
    <h2><s:text name="faq.edit.pageTitle" /></h2>
    <s:actionerror />
    <s:form theme="simple" action="faq!update.action" method="post" id="form-faq">
        <s:hidden name="faq.id"/>
        <label><s:text name="faq.input.question" /></label><br/>
        <s:textfield  name="faq.question" cssClass="name-faq"/><br/>
        <label><s:text name="faq.input.answer" /></label><br/>
        <s:textarea name="faq.answer" cssClass="textarea-faq"/><br/>
        <s:submit key="faq.edit.submit" cssClass="btn-save"/>
        <s:a href="faq!list.action" cssClass="btn-cancel"><s:text name="faq.edit.cancel" /></s:a>
    </s:form>
</html>