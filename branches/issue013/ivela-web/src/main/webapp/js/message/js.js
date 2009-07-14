/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function listInbox(page, pageSize, messageTitle) {
    $('write-message').style.display = 'none';
    $('outbox').style.display = 'none';
    $('reply-message').style.display = 'none';        
    $('inbox').style.display = 'block';
    new Ajax.Request('message!inboxLastMessages.action?page=' + page + '&messageTitle=' + messageTitle,
    {
        method:'get',
        requestHeaders: {Accept: 'application/json'}, 
        onSuccess: function(transport) {
            var json = transport.responseText.evalJSON(true);

            var messages = json.messages.message;

            //var msg = '<s:text name="home.message.empty" />';
            var msg = 'Não há mensagens';
            
            if (messages == undefined) {
                $('inbox').innerHTML = '<p class="first-access">' + msg + '</p>';
                $('inbox').style.display = 'block';
            }
            else
              printInbox(messages, messageTitle, page, pageSize);
 
        },
        onFailure: function() { alert('Message: Something went wrong...') }
    });        

}

function listOutbox(page, pageSize, messageTitle) {
    $('write-message').style.display = 'none';
    $('inbox').style.display = 'none';
    $('reply-message').style.display = 'none';        
    $('outbox').style.display = 'block';
    new Ajax.Request('message!outboxLastMessages.action?page=' + page + '&messageTitle=' + messageTitle,
    {
        method:'get',
        requestHeaders: {Accept: 'application/json'}, 
        onSuccess: function(transport) {
            var json = transport.responseText.evalJSON(true);

            var messages = json.messages.message;
            
            //var msg = '<s:text name="home.message.empty" />';
            var msg = 'Não há mensagens';

            if (messages == undefined) {
                $('outbox').innerHTML = '<p class="first-access">' + msg + '</p>';
                $('outbox').style.display = 'block';
            }
            else
                printOutbox(messages, messageTitle, page, pageSize);

        },
        onFailure: function() { alert('Message: Something went wrong...') }
    });        

}

function printInbox(messages, messageTitle, page, pageSize) {
    var html = '';

    if (messages.length == null) {
        var temp = messages;
        messages = new Array(1);
        messages[0] = temp;
    }
    var messagesLength = messages.length;

    if (messageTitle == null || messageTitle == '')
        messageTitle = '\'\'';

    var next = null;
    var previous = null;

    var start = 0;
    var end = pageSize;
    if (end > messagesLength)
        end = messagesLength;

    var pagePrevious = page - 1;
    previous = 'listInbox(' + pagePrevious + ', ' + pageSize + ', ' + messageTitle + ')';
    if (pagePrevious < 1) {
        pagePrevious = 1;
        previous = null;
    }

    var pageNext = page + 1;
    if (end == messagesLength)
        pageNext--;
    else
        next = 'listInbox(' + pageNext + ', ' + pageSize + ', ' + messageTitle + ')';

    for (var i = start; i < end; i++) {               
        var message = messages[i];

        var id = message.id;
        var title = message.title;                    
        var description = message.description;
        var datetime = message.datetime.$.substring(0, 16);
        var senderId = message.sender.id;
        var senderName = message.sender.username;

        var textSender = '<s:text name="message.list.sender" />';
        var textDate = '<s:text name="message.list.datetime" />';

        var showFunction = 'showInboxMessage(\'' + id + '\', \'' + title + '\', \'' + description + '\', \'' + datetime + '\', \'' + senderId + '\', \'' + senderName + '\')';

        html += '<p>';
        html += '<img src="images/icon/icon-message-no-read.gif" />' + '<span onclick="' + showFunction + '">' + title + '</span><br />' + textSender + ':' + '<span onclick="' + showFunction + '">' + senderName + '</span>' + textDate + ':' + '<span class="date">' + datetime + '</span>';
        html += '</p>';
    }

    html += '<p>';
        if (previous != null)
            html += '<span onclick="'+ previous + '">' + 'Previous' + '</span><br />';
        if (next != null)
            html += '<span onclick="'+ next + '">' + 'Next' + '</span>';
    html += '</p>';

    $('inbox').innerHTML = html;
}

function printOutbox(messages, messageTitle, page, pageSize) {
    var html = '';

    if (messages.length == null) {
        var temp = messages;
        messages = new Array(1);
        messages[0] = temp;
    }
    var messagesLength = messages.length;

    if (messageTitle == null || messageTitle == '')
        messageTitle = '\'\'';

    var next = null;
    var previous = null;

    var start = 0;
    var end = pageSize;
    if (end > messagesLength)
        end = messagesLength;

    var pagePrevious = page - 1;
    previous = 'listOutbox(' + pagePrevious + ', ' + pageSize + ', ' + messageTitle + ')';
    if (pagePrevious < 1) {
        pagePrevious = 1;
        previous = null;
    }

    var pageNext = page + 1;
    if (end == messagesLength)
        pageNext--;
    else
        next = 'listOutbox(' + pageNext + ', ' + pageSize + ', ' + messageTitle + ')';

    for (var i = start; i < end; i++) {               
        var message = messages[i];

        var id = message.id;
        var title = message.title;                    
        var description = message.description;
        var datetime = message.datetime.$.substring(0, 16);
        var recipient = message.recipient.username;

        var textRecipient = '<s:text name="message.list.recipient" />';
        var textDate = '<s:text name="message.list.datetime" />';

        var showFunction = 'showOutboxMessage(\'' + id + '\', \'' + title + '\', \'' + description + '\', \'' + datetime + '\', \'' + recipient + '\')';

        html += '<p>';
        html += '<img src="images/icon/icon-message-no-read.gif" />' + '<span onclick="' + showFunction + '">' + title + '</span><br />' + textRecipient + ':' + '<span onclick="' + showFunction + '">' + recipient + '</span>' + textDate + ':' + '<span class="date">' + datetime + '</span>';
        html += '</p>';
    }

    html += '<p>';
        if (previous != null)
            html += '<span onclick="'+ previous + '">' + 'Previous' + '</span><br />';
        if (next != null)
            html += '<span onclick="'+ next + '">' + 'Next' + '</span>';
    html += '</p>';

    $('outbox').innerHTML = html;
}

function writeMessage() {
    $('inbox').style.display = 'none';
    $('outbox').style.display = 'none';
    $('reply-message').style.display = 'none';
    $('write-message').style.display = 'block';
    $('message.recipient.name').focus();
}

function showInboxMessage(id, title, description, datetime, senderId, senderName) {
    var html = '';
    html += '<p>';
    html += '<span>title</span>: ' + title + '<br />';
    html += '<span>description</span>: ' + description + '<br />';
    html += '<span>datetime</span>: ' + datetime + '<br />';
    html += '<span>sender</span>: ' + senderName + '<br />';
    html += '<span onclick="listInbox(1, 5, \'\')">' + 'Voltar' + '</span><br />';
    html += '<span onclick="replyMessage(\'' + senderId + '\', \'' + senderName + '\', \'' + title + '\', \'' + description + '\')">' + 'Reply' + '</span><br />';
    html += '<span onclick="deleteInboxMessage(\'' + id + '\')">' + 'Delete' + '</span><br />';        
    html += '</p>';
    $('inbox').innerHTML = html;
}

function showOutboxMessage(id, title, description, datetime, recipient) {
    var html = '';
    html += '<p>';
    html += '<span>title</span>: ' + title + '<br />';
    html += '<span>description</span>: ' + description + '<br />';
    html += '<span>datetime</span>: ' + datetime + '<br />';
    html += '<span>recipient</span>: ' + recipient + '<br />';
    html += '<span onclick="listOutbox(1, 5, \'\')">' + 'Voltar' + '</span><br />';
    html += '<span onclick="deleteOutboxMessage(\'' + id + '\')">' + 'Delete' + '</span><br />';                
    html += '</p>';
    $('outbox').innerHTML = html;
}

function deleteInboxMessage(id) {
    var result = confirm('Confirm?');
    if (result) {
        new Ajax.Request('message!removeInboxMessage.action?message.id=' + id,
        {
            method:'get',
            requestHeaders: {Accept: 'application/json'},
            onSuccess: function(transport) {
                var json = transport.responseText.evalJSON(true);
                alert('Mensagem removida com sucesso');
                listInbox(1, 5, '');
            },
            onFailure: function() { alert('Message: Something went wrong...') }
        });
    }
}

function deleteOutboxMessage(id) {
    var result = confirm('Confirm?');
    if (result) {
        new Ajax.Request('message!removeOutboxMessage.action?message.id=' + id,
        {
            method:'get',
            requestHeaders: {Accept: 'application/json'},
            onSuccess: function(transport) {
                var json = transport.responseText.evalJSON(true);
                alert('Mensagem removida com sucesso');
                listOutbox(1, 5, '');
            },
            onFailure: function() { alert('Message: Something went wrong...') }
        });        
    }
}

function replyMessage(recipientId, recipientName, title, description) {
    $('reply.message.recipient.id').value = recipientId;
    $('reply.message.recipient.name').value = recipientName;
    $('reply.message.title').value = 'Re: ' + title;
    $('reply.message.description').value = '\n\n---\n' + description;

    $('inbox').style.display = 'none';
    $('outbox').style.display = 'none';
    $('write-message').style.display = 'none';
    $('reply-message').style.display = 'block';

    $('reply.message.description').focus();        
}

function sendMessage(title, description, recipient) {
    new Ajax.Request('message!sendMessage.action?message.title=' + title + '&message.description=' + description + '&message.recipient.id=' + recipient,
    {
        method:'get',
        requestHeaders: {Accept: 'application/json'},
        onSuccess: function(transport) {
            //var json = transport.responseText.evalJSON(true);
            alert('Mensagem enviada com sucesso');
            listInbox(1, 5, '');
        },
        onFailure: function() { alert('Message: Something went wrong...') }
    });        
}

function writeMessageAutoCompleterEvent(param, e) {
    new Ajax.Request('message!prepareWriteMessage.action',
    {
        method:'get',
        requestHeaders: {Accept: 'application/json'}, 
        onSuccess: function(transport) {
            var keynum;
            if (window.event) { // IE
                keynum = e.keyCode
            }
            else if(e.which) { // Netscape/Firefox/Opera
                keynum = e.which
            }
            if ($('message.recipient.name').value.length == 0 || keynum == 27){
                messageAutoCompleterClear();
            } 
            else {
                var json = transport.responseText.evalJSON(true);
                $('recipientDivAutoCompleter').style.display = "block";
                $('recipientDivAutoCompleter').innerHTML = "";
                for (var i = 0; i < json.systemUsers.systemUser.length; i++) {
                    var item = json.systemUsers.systemUser[i].username + '';
                    if (item.indexOf(param) > -1) {
                        var a = document.createElement("a");
                        a.setAttribute("href", "");
                        a.innerHTML = json.systemUsers.systemUser[i].username + '';
                        a.id = 'messageAutoCompleter_' + json.systemUsers.systemUser[i].id;
                        a.onclick = function() {
                            $('message.recipient.id').value = this.id.substring(21);
                            $('message.recipient.name').value = this.innerHTML;
                            $('recipientDivAutoCompleter').style.display = "none";
                            return false;
                        }
                        $('recipientDivAutoCompleter').appendChild(a);
                    }
                }
            }
        },
        onFailure: function() { alert('Message: Something went wrong...') }
    });        
}

function messageAutoCompleterClear() {
    $('recipientDivAutoCompleter').style.display = 'none';
    $('recipientDivAutoCompleter').innerHTML = '';
    $('message.recipient.name').value ='';
    $('message.recipient.id').value = '';
}