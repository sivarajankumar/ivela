<%-- 
    Document   : list Forum
    Created on : May 15, 2008, 1:38:17 PM
    Author     : Nelson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="forum.pageTitle" /></title>
        <link href="../css/forum_admin.css" rel="stylesheet" type="text/css" />
        <s:head />
    </head>
    <div id="breadcrumb">
        <p><s:text name="admin.breadcrumb.youAreHere"/></p>
        <ul>
            <li><a href="home.action" title="<s:property value="admin.home.title"/>"><s:text name="admin.home"/></a></li>
            <li class="current" title="<s:property value="forum.pageTitle"/>"><s:text name="forum.pageTitle"/></li>
        </ul>
    </div>
    <h2><s:text name="forum.list.pageTitle" /></h2>
    <s:a href="forum!input.action" cssClass="add-forum"><s:text name="forum.list.add" /></s:a>
    <s:actionerror />
    <table id="forums">
        <s:iterator value="forumList">
            <s:url id="editUrl" action="forum" method="edit">
                <s:param name="forum.id" value="id"/>
            </s:url>
            
            <s:url id="deleteUrl" action="forum" method="remove">
                <s:param name="forum.id" value="id"/>
            </s:url>
            
            <s:url id="showUrl" action="topic" method="listByForum">
                <s:param name="forum.id" value="id"/>
            </s:url>
            
            <tr>
                <td class="title-forum"><s:a href="%{editUrl}"><s:property value="title" /></s:a></td>
                <td class="edit-forum"><s:a href="%{editUrl}"><s:text name="forum.list.edit" /></s:a></td>
                <td class="delete-forum"><s:a href="%{deleteUrl}"><s:text name="forum.list.remove" /></s:a></td>
                <td class="delete-forum"><s:a href="%{showUrl}"><s:text name="forum.list.show" /></s:a></td>
            </tr>
            <tr>
                <td colspan="3" class="forum-description">
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
