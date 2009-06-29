<%-- 
    Document   : list Post
    Created on : May 15, 2008, 1:38:17 PM
    Author     : rodrigofelix
--%>
<%@ page import="org.springframework.security.context.SecurityContextHolder"%>
<%@ page import="org.springframework.security.userdetails.UserDetails"%>
<%@ page import="br.ufc.ivela.commons.model.SystemUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<link href="css/forum.css" rel="stylesheet" type="text/css" />
    

    <%

            Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            SystemUser systemUser = null;


            if (obj instanceof UserDetails) {
                systemUser = (SystemUser) obj;
            }

    %>
    
<script>
    function validate(){
       var validate = true;
        if(document.getElementById('post.input.title').value==''){
            alert("Title is required");
            validate = false
        }
        
        if(document.getElementById('post.input.message').value==''){
            alert("Message is required");
            validate = false
        }
        if(validate){
            document.getElementById('form-answer').submit();
        }
    }
    function addQuote(postId, topicId, message) {
        var hform = '';
        //hform += '<h2><s:text name="post.input.sessionTitle" /></h2><br />';
        hform += '<form id="form-answer" style="padding-left: 170px; text-aling: right; width: 800px; position: relative;" name="post" onsubmit="return true;" action="/ivela-web/post!add.action" method="POST" enctype="multipart/form-data">';
        hform += '<div style="border:1px solid #ccc; background: #f3f3f3; padding: 20px; margin-top: 0px; width: 405px; height: auto;" >';
        hform += '<s:hidden name="post.topic.id" value="' + topicId + '" />';
            hform += '<label><s:text name="post.input.title"  />:</label><br />';
            hform += '<s:textfield name="post.title" theme="simple" cssStyle="width: 400px;"  id="post.input.title"/><br />';
            hform += '<label><s:text name="post.input.message" />:</label><br />';
            hform += '<s:textarea id="post.input.message" name="post.message" cssStyle="width:400px;" theme="simple" value="' + '[quote]' + message + '[/quote]' + '" /><br />';
        hform += '</div>';

        hform += '<div style="display: block; position: absolute; top: 0; right: 0px; border:1px solid #ccc; background: #f3f3f3; padding: 20px; width: 300px; height: auto;" >';
            hform += '<label><s:text name="post.input.attach" />:</label><br />';
            hform += '<label><s:text name="repository.input.file" />:</label><br />';
            hform += '<s:file name="upload" theme="simple"/><br />';
            hform += '<label><s:text name="repository.input.file" />:</label><br />';
            hform += '<s:file name="upload" theme="simple"/><br />';
            hform += '<label><s:text name="repository.input.file" />:</label><br />';
            hform += '<s:file name="upload" theme="simple"/><br />';
        hform += '</div>';
        hform += '<input type="button" id="form-answer_0" value="Send" class="btn-send" onclick="validate();"/>';
        hform += '</form>';
        document.getElementById('addQuote_' + postId).innerHTML = hform;
        document.getElementById('addQuote_' + postId).style.display = 'block';
    }
</script>

<h1><s:property value="topic.forum.title" /></h1>
<h4><s:property value="topic.title" /></h4>
<s:actionmessage />
<div id="breadcrumb">
    <s:url id="listForum" includeParams="false" action="forum" method="list">
        <s:param name="forum.id" value="forum.id"/>
    </s:url>    
    <s:url id="listTopic" includeParams="false" action="topic" method="listByForum">
        <s:param name="forum.id" value="forum.id"/>
    </s:url>        
    <s:url id="listPost" includeParams="false" action="post" method="list">
        <s:param name="forum.id" value="forum.id"/>
        <s:param name="topic.id" value="topic.id"/>
    </s:url>        
    <p><s:text name="breadcrumb.youAreHere"/></p>
    <ul>
        <li><s:a href="%{listForum}"><s:text name="forum.breadcrumb"/></s:a></li>
        <li><s:a href="%{listTopic}"><s:text name="topic.breadcrumb"/></s:a></li>
        <li class="current"><s:a href="%{listPost}"><s:text name="post.breadcrumb"/></s:a></li>
    </ul>
</div>
<div id="post">
    <s:iterator value="postList" status="pstat">
        <table id="forum">
            <tr>
                <th><s:text name="post.list.author"/></th>
                <th><s:property value="title" /></th>
            </tr>
            
            <tr>
                <td class="profile-user">
                    <img src="RenderServletProfile?id=<s:property value="createdBy.id" />" /><br/>
                    <a href=""><s:property value="createdBy.name" /></a><br/>
                    <s:text name="post.list.stateSince"/><s:date name="createdAt" format="%{getText('formatDateLanguage1')}" /><br />
                    <%--<s:text name="post.list.messages"/><br />--%>
                    <!--
                            Location: Fortaleza-CE<br/>
                            <span class="offline">offline</span>
                            <span class="online">online</span>
                    -->
                </td>
                <td>
                    
                    <p><s:property value="message" /></p>
                    
                    <s:iterator value="attachPosts">
                        <s:url id="urlFile" action="post" method="download">
                            <s:param name="post.id" value="post.id"/>
                            <s:param name="file.id" value="file.id"/>
                        </s:url>
                        <s:a href="%{urlFile}" cssClass="annex"><!--img src="images/post_down/download.png" /--><s:property value="file.title" /></s:a><br />
                    </s:iterator>
                    
                    <ul id="attachments">
                        <!--
                     <li><a class="perfil" href="">Perfil</a></li>
                     <li><a class="private-message" href="">Private Message</a></li>
                     <li><a class="answer" href="">Answer</a></li>
                                -->
                                <s:url id="urlRemove" action="post" method="remove">
                                    <s:param name="post.id" value="id"/>
                                    <s:param name="topic.id" value="topic.id"/>
                                </s:url>
                                <s:url id="urlQuote" action="post" method="input">
                                    <s:param name="post.id" value="id"/>
                                    <s:param name="post.topic.id" value="topic.id"/>
                                </s:url>
                        <%--<li><s:a cssClass="btn-answer" href="">Answer</s:a></li>--%>
                        <li><a class="btn-quote" href="javascript:addQuote(<s:property value="id" />, <s:property value="topic.id" />, '<s:property value="message" />');">Quote</a></li>
                        <li><s:a cssClass="btn-remove" href="%{urlRemove}">Remove</s:a></li>
                        <!--
                     <li><a class="print" href="">Print</a></li>
                        -->
                    </ul>
                    
                </td>
            </tr>
        </table>
        <div style="display: none; width: 940px;" id="addQuote_<s:property value="id" />">45456546456
        </div>
    </s:iterator>                
</div>
<div id="quick-answer">
    <h3>Quick Answer</h3>
    <s:actionerror />    
    <s:form action="post!addQuickAnswer.action" method="POST" id="form-answer" theme="simple">
        <s:hidden name="post.topic.id" value="%{topic.id}" />
        <label>Title:</label><br /><br />
        <s:textfield name="post.title" cssStyle="width: 960px;" /><br/> 
        <label>Description:</label><br />
        <s:textarea name="post.message" />
        <s:submit cssClass="btn-send" value="Send" />
    </s:form>
    
</div>

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

<%--
</body>
</html>--%>
