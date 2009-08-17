<%-- 
    Document   : showInbox message
    Created on : Jul 31, 2008, 8:47:43 AM
    Author     : leoomoreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="cal" uri="/jscalendar" %>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link href="css/system_message.css" rel="stylesheet" type="text/css" />
        <s:head />
        
    </head>
    
    <h1><s:text name="message.pageTitle"/></h1>
    <div id="col-1-messages">
        <ul class="message-action">
            <li><a class="create-new-message" href="message!input.action"><s:text name="message.newMessage"/></a></li>
            <li><a class="inbox-message" href="message!inbox.action"><s:text name="message.inbox"/></a></li>
            <li><a class="sent-message" href="message!outbox.action"><s:text name="message.sentMessages"/></a></li>
        </ul>
    </div>
    
    <div id="col-2-messages">
        
        
        <h2><s:text name="message.outbox" /></h2>
        <s:url id="removeMessage" action="message" method="removeOutbox">
            <s:param name="message.id" value="message.id"/>
        </s:url>
        <p>
            <span class="topic-message"><s:text name="message.list.title" />:</span><br />
            <span class="title-subject-message"><s:property value="message.title" /></span>
        </p>
        <p>
            <span class="topic-message"><s:text name="message.list.recipient" />:</span><br /><s:property value="message.recipient.username" />
        </p>
        <p class="message-reply">
            <span class="topic-message"><s:text name="message.list.description" />:</span><br /><s:property value="message.description" />
        </p>
        
        <s:a cssClass="btn-delete-message" href="%{removeMessage}"><s:text name="message.input.delete" /></s:a>
        
        
    </div>
    <br class="clear" />  
    
</html>
