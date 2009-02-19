<%-- 
    Document   : input Disciplina
    Created on : Jul 15, 2008, 1:48:54 PM
    Author     : nelson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="cal" uri="/jscalendar" %>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/system_message.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/util/util.js"></script>
        <script type="text/javascript" src="js/prototype/prototype.js"></script>
        <script type="text/javascript" src="js/scriptaculous/scriptaculous.js"></script>
        <title><s:text name="message.pageTitle" /></title>
        <cal:head/>
        
        <s:head />
    </head>
    
    <style>

        div.autocomplete {
          position:absolute;
          width:250px;
          background-color:white;
          border:1px solid #888;
          margin:0px;
          padding:0px;
        }
        div.autocomplete ul {
          list-style-type:none;
          margin:0px;
          padding:0px;
        }
        div.autocomplete ul li.selected { background-color: #ffb;}
        div.autocomplete ul li {
          list-style-type:none;
          display:block;
          margin:0;
          padding:2px;
          height:20px;
          cursor:pointer;
        }

    </style>        
    
    <h1><s:text name="message.pageTitle"/></h1>
    <div id="col-1-messages">
        <ul class="message-action">
            <li><a class="create-new-message" href="message!input.action"><s:text name="message.newMessage"/></a></li>
            <li><a class="inbox-message" href="message!inbox.action"><s:text name="message.inbox"/></a></li>
            <li><a class="sent-message" href="message!outbox.action"><s:text name="message.sentMessages"/></a></li>
        </ul>
    </div>
    
    <div id="col-2-messages">
        <h2><s:text name="message.input.sessionTitle" /></h2>
        <s:form theme="simple" action="message!add.action" name="create-new-message">
            <p>
                <label><s:text name="message.input.recipient"/>:</label>
                <s:hidden name="message.recipient.id" id="message.recipient.id" />
                <input type="text" name="username" id="username" onblur="validateUsername();" /><span style="color:red; font-weight:bolder;">* <s:text name="message.input.user"/></span>
                <div id="recipientDivAutoCompleter2" class="autocomplete"></div>
                <script type="text/javascript">
                   new Ajax.Autocompleter("username","recipientDivAutoCompleter2","message!searchUsers.action", {afterUpdateElement : getSelectionId});

                   function getSelectionId(text, li) 
                   {
                      $('message.recipient.id').value=li.id;
                   }
                   
                   function validateUsername() {
                       var id = $('message.recipient.id').value;
                       if (id == null || id == '') {
                           $('username').value = '';
                           $('username').focus();
                       }
                   }
                </script>
                
            </p>
            
            <p>
                <label class="label-message"><s:text name ="message.input.subject"/></label>
                <s:textfield name="message.title" cssClass="field-subject" />
            </p>
            <p>
                <label class="label-message" for="mesa"><s:text name="message.input.description"/></label>
                <s:textarea cssClass="field-message" name="message.description" cols="45" rows="5"></s:textarea>
            </p>
            <p>
                <s:submit cssClass="btn-send-message" key="message.input.submit" />
                <s:reset cssClass="btn-cancel-message" key="message.input.reset" />
            </p>
        </s:form>
    </div>               
    <br  class="clear"/> 
    
</html>