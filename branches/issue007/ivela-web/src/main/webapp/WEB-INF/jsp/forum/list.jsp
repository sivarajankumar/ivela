<%-- 
    Document   : list Forum
    Created on : May 15, 2008, 1:38:17 PM
    Author     : leoomoreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<link href="css/forum.css" rel="stylesheet" type="text/css" />


<h1><s:text name="forum.list.pageTitle"/></h1>

<s:if test="(forumLines == null || forumLines.size() == 0)">
    <h4 style="margin-top:100px;"><s:text name="home.forum.empty"/></h4>
</s:if>
<s:else>
    
    <s:form cssClass="form-search-forum" action="forum!search" method="post" theme="simple">
        <label><s:text name="forum.search" /></label>
        <s:hidden name="page" value="%{page}" />
        <s:textfield cssClass="field" name="forumTitle" />
        <s:submit cssClass="button" src="images/bottons/btn-search.gif" type="image" />
    </s:form>
    <br />
    <jsp:include page="paginatorTop.jsp"/>
    <br />
    <s:actionmessage/>
    <div id="breadcrumb">
        <s:url id="listForum" action="forum" method="list">
            <s:param name="forum.id" value="forum.id"/>
        </s:url>
        <p><s:text name="breadcrumb.youAreHere"/></p>
        <ul>
            <li class="current"><s:a href="%{listForum}"><s:text name="forum.breadcrumb"/></s:a></li>
        </ul>
    </div>
    <table id="forum">
        <tr>
            <th><s:text name="forum.pageTitle"/></th>
            <th><s:text name="forum.list.replies"/></th>
            <th><s:text name="forum.list.topics"/></th>
            <th><s:text name="forum.list.lasttopic"/></th>
        </tr>
        <s:iterator value="forumLines">
            <tr>
                <s:url id="listTopicUrl" action="topic" method="listByForum" includeParams="false">
                    <s:param name="forum.id" value="forum.id"/>
                </s:url>
                <td>
                    <h3><s:a href="%{listTopicUrl}"><s:property value="forum.title"/></s:a></h3>
                    <p><s:property value="forum.description"/></p>
                    <span class="font-orange-td"><s:text name="forum.list.createdBy" /></span><span class="name-user"><a href="#" class="tooltip" onmouseover="return escape('<s:property value="forum.createdBy.email"/>')"><s:property value="forum.createdBy.username"/></a></span>		
                </td>
                <td class="topics-views"><s:property value="forumReplies"/></td>
                <td class="topics-views"><s:property value="forumViews"/></td>
                <td>
                    <p><a href="post!list.action?forum.id=<s:property value="forum.id" />&topic.id=<s:property value="lastTopic.id" />"><s:property value="lastTopic.title"/></a>
                    </p><span class="font-orange-td"><s:text name="forum.list.postedBy" /></span>
                    <span class="name-user"><a href="#" class="tooltip" onmouseover="return escape('<s:property value="forum.createdBy.email"/>')"><s:property value="lastTopic.createdBy.username"/></a></span><br />
                    <span class="font-orange-td"><s:text name="forum.list.date" /></span>&nbsp;<s:date name="lastTopic.createdAt" format="%{getText('formatDateLanguage1')}" />
                </td>
            </tr>
        </s:iterator>
    </table>
    <s:if test="back">
        <span class="name-user"> <a href="forum!list.action"><s:text name="forum.list.seeAll"/></a></span>
    </s:if>   
     <%--
    <s:form cssClass="form-search-forum" action="forum!search" method="post" theme="simple">
        <s:hidden name="page" value="%{page}" />
        <s:textfield cssClass="field" name="forumTitle" />
        <s:submit cssClass="button" src="images/bottons/btn-search.gif" type="image" />
    </s:form>
    --%>      
    
    <jsp:include page="paginatorBottom.jsp"/>        
    
</s:else>

<script  language="JavaScript" type="text/javascript" src="js/tooltip/wz_tooltip.js"></script>


