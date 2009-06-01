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
        <cal:head/>
        
        <s:head />
    </head>
    
    <script type="text/javascript">
        function reply(div_id, to, username, title, description) {
            if (document.getElementById(div_id).style.display == 'none') {
                document.getElementById(div_id).style.display = 'block';
                document.getElementById('message.recipient.id').value = to;
                document.getElementById('message.title').value = 'Re: ' + title;
                document.getElementById('message.description').value = '\n\n' + '---' + '\n' + description;
                document.getElementById('reply.username').value = username;                    
                document.getElementById('message.description').focus();
            }
            else {
                document.getElementById(div_id).style.display = 'none';
                document.getElementById('message.recipient.id').value = '';
                document.getElementById('message.title').value = '';
                document.getElementById('message.description').value = '';
                document.getElementById('reply.username').value = '';
            }
        }
    </script>
    
    <h1><s:text name="message.pageTitle"/></h1>
    <div id="col-1-messages">
        <ul class="message-action">
            <li><a class="create-new-message" href="message!input.action"><s:text name="message.newMessage"/></a></li>
            <li><a class="inbox-message" href="message!inbox.action"><s:text name="message.inbox"/></a></li>
            <li><a class="sent-message" href="message!outbox.action"><s:text name="message.sentMessages"/></a></li>
        </ul>
    </div>
    
    
    <div id="col-2-messages">
        
        
        <h2><s:text name="message.inbox" /></h2>
        <s:url id="removeMessage" action="message" method="removeInbox">
            <s:param name="message.id" value="message.id"/>
        </s:url>
        <p>
            <span class="topic-message"><s:text name="message.list.title" />:</span><br />
            <span class="title-subject-message"><s:property value="message.title" /></span>
        </p>
        <p>
            <span class="topic-message"><s:text name="message.list.sender" />:</span><br /><s:property value="message.sender.username" />
        </p>
        <p class="message-reply">
            <span class="topic-message"><s:text name="message.list.description" />:</span><br /><s:property value="message.description" />
        </p>
        
        <a class="btn-reply-message" href="#" onclick="reply('div-reply', '<s:property value="message.sender.id" />', '<s:property value="message.sender.username" />', '<s:property value="message.title" />', '<s:property value="message.description" />')"><s:text name="message.input.reply" /></a>
        <s:a cssClass="btn-delete-message" href="%{removeMessage}"><s:text name="message.input.delete" /></s:a>
        
        <div class="div-reply" id="div-reply" style="display: none;">
            <s:form theme="simple" action="message!reply.action" cssClass="form-system-message">
                <s:hidden id="message.recipient.id" name="message.recipient.id" />                    
                <p>
                    <label><s:text name="message.list.recipient" />:</label>
                    <input readonly class="to-message" id="reply.username" name="reply.username" type="text" />
                </p>
                <p>
                    <label><s:text name="message.list.title" />:</label>
                    <s:textfield cssClass="subject-message" id="message.title" name="message.title" />
                </p>
                <p>
                    <label><s:text name="message.list.description" />:</label>
                    <s:textarea cssClass="text-area-message" id="message.description" name="message.description"></s:textarea>
                </p>
                
                <s:submit cssClass="btn-save" key="message.input.submit" />            
            </s:form>
        </div>
    </div>
    <br class="clear" />  
    
</html>
