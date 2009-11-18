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
# 15-JUL-2009 - Rafael Lagoa (Instituto Eldorado) - 000012 - Remove breadcrumb area         #
# 11-AUG-2009 - Otofuji (Instituto Eldorado)      - 000012 - Add Topic Functionality        #
############################################################################################# 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<head>
<link href="css/forum.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/prototype/prototype.js"></script>
<script type="text/javascript" src="js/scriptaculous/scriptaculous.js"></script>
<script type="text/javascript" src="js/forum/lightbox.js"></script>        
<script type="text/javascript" src="js/forum/forum.js"> </script>
<script type="text/javascript">
    var errorTitle = '<s:text name="forum.error.title"/>' ;
    var errorDescr = '<s:text name="forum.error.description"/>';
    var errorTopic  = '<s:text name="forum.error.topic.fail"/>';
    var errorTopicD = '<s:text name="forum.error.topic.fail.delete"/>';
    var errorAjax = '<s:text name="forum.error.topic.ajax"/>';    
</script>
</head>

<h1><s:property value="forum.title" /></h1>

<s:form cssClass="form-search-forum" action="topic!search" method="post" theme="simple">    
    <label><s:text name="topic.search" /></label>
    <s:hidden id="forum.id" name="forum.id" value="%{forum.id}" />
    <s:hidden name="page" value="%{page}" />
    <s:textfield cssClass="field" name="topicTitle" />
    <s:submit cssClass="button" src="images/bottons/btn-search.gif" type="image" />
</s:form>
<div id="breadcrumb">    
    <ul>        
        <li><a href="forum!list.action" title="<s:property value="forum.pageTitle"/>"><s:text name="forum.pageTitle"/></a></li>
        <li class="current" title="<s:property value="topic.pageTitle"/>"><s:property value="forum.title"/></li>
    </ul>
</div> 
<jsp:include page="paginator.jsp"/>
<br />
<s:actionmessage />
<table id="forum-table">
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
                <s:if test="getCreatedByUser() || hasAuthorization(authority)">                   
                    - <span> <s:a cssClass="btn-remove" href="javascript:deleteTopic(%{forum.id}, %{topic.id});"><s:text name="topic.list.remove"/></s:a> </span>
                </s:if>                
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

<s:if test="forum.studentCreateTopic||#session.role=='admin'">
<a id="topic-new" href="javascript:showEntryTopic(document.getElementById('forum.id').value);"><s:text name= "forumId.grade.new.topic" /></a>
</s:if>

<jsp:include page="paginator.jsp"/>

<br class="clear" />  

<%-- Topic Creation --%>
<div id="showEntryTopic" style="display: none;">
    <form>
    <h3><s:text name="topic.input.pageTitle" /></h3>
        
    <br/>
    <label><s:text name="topic.input.title" /><br />
    </label> <input type="text" id="topic.title" /><br />
    <label><s:text name="topic.input.description" /><br />
    </label> <textarea name="topic.description" id="topic.description"
        cols="50"></textarea><br />
    <br />
    <br />    
    <input type="button" value="<s:text name="topic.input.create"/>"
        onclick="submitTopic($('forum.id').value)">     
    <br />     
    <br />
    </form>
</div>

<div id='loading' style="display:none;">   
    <img  src='images/loader.gif' />
</div>

