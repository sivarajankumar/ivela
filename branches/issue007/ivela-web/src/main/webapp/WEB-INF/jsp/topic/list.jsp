<%--
#############################################################################################
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
# File: list.jsp                                                                            #
# Document: List Forum's Topics                                                             #
# Date        - Author(Company)                   - Issue# - Summary                        #
# 15-MAY-2008 - Leonardo Moreira (UFC)            - XXXXXX - Initial Version                #
# 27-JUN-2009 - Otofuji (Instituto Eldorado)      - 000007 - IE7 compatibility              #
############################################################################################# 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<link href="css/forum.css" rel="stylesheet" type="text/css" />

<h1><s:property value="forum.title" /></h1>
<s:form cssClass="form-search-forum" action="topic!search" method="post" theme="simple">
    <s:hidden name="forum.id" value="%{forum.id}" />
    <s:hidden name="page" value="%{page}" />
    <label><s:text name="topic.search" /></label>
    <s:textfield cssClass="field" name="topicTitle" />
    <s:submit cssClass="button" src="images/bottons/btn-search.gif" type="image" />
</s:form>
<br />
<jsp:include page="paginator.jsp"/>
<br />
<s:actionmessage />
<div id="breadcrumb">
    <s:url id="listForum" action="forum" method="list">
        <s:param name="forum.id" value="forum.id"/>
    </s:url>    
    <s:url id="listTopic" action="topic" method="listByForum">
        <s:param name="forum.id" value="forum.id"/>
    </s:url>    
    <p><s:text name="breadcrumb.youAreHere"/></p>
    <ul>
        <li><s:a href="%{listForum}"><s:text name="forum.breadcrumb"/></s:a></li>
        <li class="current"><s:a href="%{listTopic}"><s:text name="topic.breadcrumb"/></s:a></li>
    </ul>
</div>
<table id="forum">
    <tr>
        <th><s:text name="topic.list.title" /></th>
        <th><s:text name="topic.list.replies" /></th>
        <th><s:text name="topic.list.lastPost" /></th>
    </tr>
    <s:iterator value="topicLines">
        <tr>
            <s:url id="listPostUrl" action="post" method="list">
                <s:param name="topic.id" value="topic.id"/>
            </s:url>
            <td>
                <h3><s:a href="%{listPostUrl}"><s:property value="topic.title" /></s:a></h3>
                <p><s:property value="topic.description" /></p>
                <span class="font-orange-td"><s:text name="topic.list.createdBy" /></span><span class="name-user"><b><s:property value="topic.createdBy.username" /></b></span>     
            </td>
            <td class="topics-views"><s:property value="topicReplies" /></td>
            <!--<td class="topics-views"><s:property value="topicViews" /></td>-->
            <td>            
                <span class="font-orange-td"><s:text name="topic.list.date" /></span>&nbsp;<s:date name="lastPost.createdAt" format="%{getText('formatDateLanguage1')}" /><br/>
                <p><s:a href="%{listPostUrl}"><s:property value="lastPost.message" /></s:a></p>
                <span class="font-orange-td"><s:text name="topic.list.postedBy" /></span><span class="name-user"><b><s:property value="lastPost.createdBy.username"/></b></span>
            </td>
        </tr>
    </s:iterator>
</table>

<script>
    
    var msgs = document.getElementsByTagName("p");
    for ( var i = 0; i < msgs.length; i++ )
    {
        msgs[i].innerHTML = replaceQuotes( msgs[i].innerHTML );
    }
    
    function replaceQuotes( quotedText )
    {
       var out = replaceAll( quotedText, '[quote]', '<span class=\"quote-answer\">' );
       out = replaceAll( out, '[/quote]', '</span>' );

//       alert('quotedText=' + quotedText + "\n" + "out=" + out);

       return out;
    }
    
    function replaceAll(string, token, newtoken) 
    {
    while (string.indexOf(token) != -1) 
        {
        string = string.replace(token, newtoken);
    }

        return string;
    }

</script>

<jsp:include page="paginator.jsp"/>