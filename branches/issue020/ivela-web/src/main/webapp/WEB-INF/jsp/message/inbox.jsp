<%-- 
    Document   : inbox message
    Created on : Jul 30, 2008, 12:48:27 PM
    Author     : leoomoreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/system_message.css" rel="stylesheet" type="text/css" />
        
        <s:head />
    </head>
    
    <h1><s:text name="message.pageTitle"/></h1>
    <s:actionerror />
    <div id="col-1-messages">
        <ul class="message-action">
            <li><a class="create-new-message" href="message!input.action"><s:text name="message.newMessage"/></a></li>
            <li><a class="inbox-message" href="message!inbox.action"><s:text name="message.inbox"/></a></li>
            <li><a class="sent-message" href="message!outbox.action"><s:text name="message.sentMessages"/></a></li>
        </ul>
    </div>
    
    <div id="col-2-messages">
        <h2><s:text name="message.inbox" /></h2>
        
        <jsp:include page="paginator.jsp"/>

        <div id="box-search">
            <h3><s:text name="message.search"/></h3>
            <s:form theme="simple" action="message!searchInbox" cssClass="form-system-message">
                <s:hidden name="page" value="%{page}" />
                <s:textfield cssClass="name-search" name="messageTitle" />
                <s:submit cssClass="button" src="images/bottons/btn-search.gif" type="image" />
            </s:form>
        </div>        

        <table class="table-message">
            <tr>
                <th><s:text name="message.list.title" /></th>
                <th><s:text name="message.list.sender" /></th>
                <th><s:text name="message.list.datetime" /></th>
            </tr>
            <s:iterator value="messageList">
                <s:url id="listMessage" action="message" method="getInbox">
                    <s:param name="message.id" value="id"/>
                </s:url>
                <tr>
                    <td class="subject"><img src="images/icon/icon-message-no-read.gif" align="left" alt="Title of the Image" /><s:a href="%{listMessage}"><s:property value="title" /></s:a></td>
                    <td class="author"><s:a href="%{listMessage}"><s:property value="sender.username" /></s:a></td>
                    <td class="date-message"><s:date name="datetime" format="%{getText('formatDateLanguage1')} (HH:mm:ss)"/></td>
                </tr>
            </s:iterator>
        </table>
        
        <jsp:include page="paginator.jsp"/>
        
    </div>               
    <br class="clear" />  
</html>
