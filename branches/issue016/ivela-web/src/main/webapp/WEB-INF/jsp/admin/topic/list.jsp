<%-- 
    Document   : list Topic
    Created on : May 15, 2008, 1:38:17 PM
    Author     : leoomoreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link href="../css/topic_admin.css" rel="stylesheet" type="text/css" />
        <s:head />
    </head>
    <div id="breadcrumb">
        <p><s:text name="admin.breadcrumb.youAreHere"/></p>
        <ul>
            <li><a href="home.action" title="<s:property value="admin.home.title"/>"><s:text name="admin.home"/></a></li>
            <li><a href="forum!list.action" title="<s:property value="forum.pageTitle"/>"><s:text name="forum.pageTitle"/></a></li>
            <li class="current" title="<s:property value="topic.pageTitle"/>"><s:text name="topic.pageTitle"/></li>
        </ul>
    </div>
    <h2><s:text name="topic.list.pageTitle" /></h2>
    <s:url id="newTopic" action="topic" method="input">
        <s:param name="forum.id" value="forum.id"/>
    </s:url>
    <s:a href="%{newTopic}" cssClass="add-topic"><s:text name="topic.list.add" /></s:a>
    <s:actionerror />
    <table id="topics">
        <s:iterator value="topicList">
            <s:url id="editUrl" action="topic" method="edit">
                <s:param name="topic.id" value="id"/>
            </s:url>
            
            <s:url id="deleteUrl" action="topic" method="remove">
                <s:param name="topic.id" value="id"/>
            </s:url>
            
            <tr>
                <td class="title-topic"><s:a href="%{editUrl}"><s:property value="title" /></s:a></td>
                <td class="edit-topic"><s:a href="%{editUrl}"><s:text name="topic.list.edit" /></s:a></td>
                <td class="delete-topic"><s:a href="%{deleteUrl}"><s:text name="topic.list.remove" /></s:a></td>
            </tr>
            <tr>
                <td colspan="3" class="topic-description">
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
