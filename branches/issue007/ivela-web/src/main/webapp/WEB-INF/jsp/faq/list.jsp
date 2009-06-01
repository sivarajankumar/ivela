<%-- 
    Document   : list Faq
    Created on : May 15, 2008, 1:38:17 PM
    Author     : leoomoreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<link href="RenderServlet?file=/globals/css/base.css" rel="stylesheet" type="text/css" />
<link href="css/faq.css" rel="stylesheet" type="text/css" />

<h1 style="margin: 30px 0 40px 0;"><s:text name="faq.list.pageTitle"/></h1>
<s:actionmessage/>
<s:if test="(faqList == null || faqList.size() == 0)">
    <h4 style="margin-top:100px;"><s:text name="home.faq.empty"/></h4>
</s:if>
<s:else>
    
    <s:iterator value="faqList">
        <p class="item-faq">
            <span><s:property value="question" /></span><br />
            <s:property value="answer"/>
        </p>
    </s:iterator>
    
</s:else>

<input class="btn-back" type="button" onclick="history.back(1)" value="<s:text name="systemUser.input.btnBack"/>" />

