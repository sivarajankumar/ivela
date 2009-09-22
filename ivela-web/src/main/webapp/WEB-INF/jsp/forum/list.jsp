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
# File: list.jsp                                                                            #
# Document: list Forum                                                              # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 15-MAY-2008 - Leo Moreira                       - XXXXXX - Initial Version                #
# 15-JUL-2009 - Rafael Lagoa (Instituto Eldorado) - 000012 - Remove breadcrumb area         #
#############################################################################################
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
    <table id="forum-table">
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


