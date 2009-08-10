<%--    
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
# File: input.jsp                                                                           #
# Document: Messages Input                                                                  # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 15-JUL-2008 - nelson                            - XXXXXX - Initial Version                #
# 08-JUN-2009 - otofuji (Instituto Eldorado)      - 000007 - IE7 compatibility              #
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
        <s:actionerror />
        <s:form theme="simple" action="message!add.action" name="create-new-message">
            <p>
                <label><s:text name="message.input.recipient"/>:</label>
                <s:hidden name="message.recipient.id" id="message.recipient.id" />
                <input type="text" name="username" id="username" onblur="validateUsername();" /><span style="color:red; font-weight:bolder;">* <s:text name="message.input.user"/></span>                                
            </p>
            <div id="recipientDivAutoCompleter2" class="autocomplete"></div>
                <script type="text/javascript">                
                     new Ajax.Autocompleter("username","recipientDivAutoCompleter2","message!searchUsers.action");
                
                     function validateUsername() {                       
                         new Ajax.Request('message!retrieveUsers.action',
                            {
                                method:'post',
                                requestHeaders: {
                                    Accept: 'application/json'
                                },           
                                postBody: 'username='+$('username').value,                        
                                onSuccess: function(transport) {                                       
                                    var json = transport.responseText;  
                                                                               
                                    $('message.recipient.id').value = json;
                                                                                                                                                                                                                                   
                                }
                            });  
                    }   
                </script>
            
            <p>
                <label class="label-message"><s:text name ="message.input.subject"/></label>
                <s:textfield name="message.title" cssClass="field-subject" />
            </p>
            <p>
                <label class="label-message" for="mesa"><s:text name="message.input.description"/></label>
                <s:textarea cssClass="field-message" name="message.description" cols="45" rows="5"></s:textarea>
            </p>
            <p>
                
                <s:reset cssClass="btn-cancel-message" key="message.input.reset" />
                <s:submit cssClass="btn-send-message" key="message.input.submit" />
            </p>
        </s:form>
    </div>               
    <br  class="clear"/> 
    
</html>