<%-- 
    Document   : list Topic
    Created on : May 15, 2008, 1:38:17 PM
    Author     : leoomoreira
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

<jsp:include page="paginator.jsp"/>
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
                <span class="font-orange-td"><s:text name="topic.list.createdBy" /></span><span class="name-user"><a href=""><s:property value="topic.createdBy.username" /></a></span>		
            </td>
            <td class="topics-views"><s:property value="topicReplies" /></td>
            <!--<td class="topics-views"><s:property value="topicViews" /></td>-->
            <td>
                <span class="font-orange-td"><s:text name="topic.list.date" /></span>&nbsp;<s:date name="lastPost.createdAt" format="%{getText('formatDateLanguage1')}" /><br/>
                <p><a href=""><s:property value="lastPost.message" /></a></p>
                <span class="font-orange-td"><s:text name="topic.list.postedBy" /></span><span class="name-user"><a href=""><s:property value="lastPost.createdBy.username"/></a></span>
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
       var out = replaceAll( quotedText, '[quote]', '<div class=\"quote-answer\">' );
       out = replaceAll( out, '[/quote]', '</div>' );

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

<jsp:include page="paginator.jsp"/>inator.jsp"/>