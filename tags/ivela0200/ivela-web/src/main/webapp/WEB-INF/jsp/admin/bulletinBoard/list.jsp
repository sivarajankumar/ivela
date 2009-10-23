<%-- 
    Document   : list BulletinBoard
    Created on : May 15, 2008, 1:38:17 PM
    Author     : Nelson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="bulletin.pageTitle" /></title>
        <link href="../css/bulletin_admin.css" rel="stylesheet" type="text/css" />
        <s:head />
    </head>
    <div id="breadcrumb">
        <p><s:text name="admin.breadcrumb.youAreHere"/></p>
        <ul>
            <li><a href="home.action" title="<s:property value="admin.home.title"/>"><s:text name="admin.home"/></a></li>
            <li class="current" title="<s:property value="bulletin.pageTitle"/>"><s:text name="bulletin.pageTitle"/></li>
        </ul>
    </div>
    <h2><s:text name="bulletin.pageTitle" /></h2>
    <s:a href="bulletinBoard!input.action" cssClass="add-bulletin"><s:text name="bulletin.list.add" /></s:a>
    <s:actionerror />
    <table id="bulletins">
        <s:iterator value="bulletinList">
            <s:url id="editUrl" action="bulletinBoard" method="edit">
                <s:param name="bulletinBoard.id" value="id"/>
            </s:url>
            
            <s:url id="deleteUrl" action="bulletinBoard" method="remove">
                <s:param name="bulletinBoard.id" value="id"/>
            </s:url>
            
            <tr>
                <td class="title-bulletin"><s:a href="%{editUrl}"><s:property value="title" /></s:a></td>
                <td class="edit-bulletin"><s:a href="%{editUrl}"><s:text name="bulletin.list.edit" /></s:a></td>
                <td class="delete-bulletin"><a href="bulletinBoard!remove.action?bulletinBoard.id=<s:property value="id" />" onclick="return confirm('<s:text name="admin.alert.remove"/>')"><s:text name="bulletin.list.remove" /></a></td>
            </tr>
            <tr>
                <td colspan="3" class="bulletin-description">
                    <p>
                        <s:if test="description.length() > 250">
                            <s:property value="description.substring(0,250)" /> ...
                        </s:if>
                        <s:else>
                            <s:property value="description" />
                        </s:else>                            
                    </p>
                </td>
            </tr>                
        </s:iterator>
    </table>
</html>
