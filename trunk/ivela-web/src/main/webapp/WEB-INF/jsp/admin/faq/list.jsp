<%-- 
    Document   : list Faq
    Created on : May 15, 2008, 1:38:17 PM
    Author     : Nelson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="faq.pageTitle" /></title>
        <link href="../css/faq_admin.css" rel="stylesheet" type="text/css" />
        <s:head />
        <cal:head />
    </head>
    <div id="breadcrumb">
        <p><s:text name="admin.breadcrumb.youAreHere"/></p>
        <ul>
            <li><a href="home.action" title="<s:property value="admin.home.title"/>"><s:text name="admin.home"/></a></li>
            <li class="current" title="<s:property value="faq.pageTitle"/>"><s:text name="faq.pageTitle"/></li>
        </ul>
    </div>
    <h2><s:text name="faq.list.pageTitle" /></h2>
    <s:a href="faq!input.action" cssClass="add-faq"><s:text name="faq.list.add" /></s:a>
    <s:actionerror />
    <table id="faqs">
        <s:iterator value="faqList">
            <s:url id="editUrl" action="faq" method="edit">
                <s:param name="faq.id" value="id"/>
            </s:url>
            
            <s:url id="deleteUrl" action="faq" method="remove">
                <s:param name="faq.id" value="id"/>
            </s:url>
            
            <tr>
                <td class="title-faq"><s:a href="%{editUrl}"><s:property value="question" /></s:a></td>
                <td class="edit-faq"><s:a href="%{editUrl}"><s:text name="faq.list.edit" /></s:a></td>
                <td class="delete-faq"><s:a href="%{deleteUrl}"><s:text name="faq.list.remove" /></s:a></td>
            </tr>
            <tr>
                <td colspan="3" class="faq-description">
                    <p>
                        <s:if test="answer.length() > 250">
                            <s:property value="answer.substring(0,250)" /> ...
                        </s:if>
                        <s:else>
                            <s:property value="answer" />
                        </s:else>                            
                    </p>
                </td>
            </tr>                
        </s:iterator>
    </table>
</html>
