window.onload = loadTools;

function loadTools() {
    getMessages();
    getTopics();
};

function vaiLightWindow(url) {
  //alert('true');
  myLightWindow.activateWindow({
	href: url, 
	//title: 'Waiting for the show to start in Las Vegas', 
	//author: 'Jazzmatt', 
	//caption: 'Mmmmmm Margaritas! And yes, this is me...', 
	width: 1024
        //params: 'lightwindow_type=external,lightwindow_width=1024'
  });
}


function getTopics() {
    new Ajax.Request('../home!getToolsTopics.action',
    {
        method:'post',
        requestHeaders: {
            Accept: 'application/json'
        },
        onSuccess: function(transport) {
            var json = transport.responseText.evalJSON(true);

            if (json.topics != null && json.topics.length > 0) {
                $('topics.empty').style.display = 'none';
                $('topics.list').style.display = 'block'

                var html = '<ul>';

                for(var i = 0; i < json.topics.length; i++) {

                    html += '<li> <a class="lightwindow page-options" '+
                            'href="javascript:vaiLightWindow(\'../topic!listByForum.action?forum.id='+ json.topics[i].forumId /*+'&topic.id='+json.topics[i].id*/+'\');" '+
                            '> ';

                    html += json.topics[i].title;

                    html += '</a> <br /> ' + postBy + ' '+ json.topics[i].createdBy +' - '+ json.topics[i].createdAt.substring(0,19) +' </li>';
                }

                html += '</ul>';

                $('topics.list').innerHTML = html;

            } else {
                $('topics.empty').style.display = 'block';
                $('topics.list').style.display = 'none'
            }
        },
        onFailure: function() {
            alert('Message: Error retrieving topics...')
        }
    });
}

function getMessages() {
    new Ajax.Request('../home!getToolsMessages.action',
            {
                method:'post',
                requestHeaders: {
                    Accept: 'application/json'
                },
                onSuccess: function(transport) {            
                    var json = transport.responseText.evalJSON(true);            
                    if (json.message) {
                        $('messages.empty').style.display = 'none';
                        $('messages.list').style.display = 'block'
                        var html = '<ul>';
                        if (json.message.length > 0) {                                
                            for(var i = 0; i < json.message.length; i++) {                        
                                html += '<li> <a class="lightwindow page-options" '+
                                    'href="javascript: vaiLightWindow(\'../message!getInbox.action?message.id='+ json.message[i].id +'\');">' + json.message[i].title + '</a> <br /> ' + from + ' '+ json.message[i].sender +' - '+ json.message[i].datetime +' </li>';
                            }
                        } else {
                            html += '<li> <a class="lightwindow page-options" '+
                            'href="javascript: vaiLightWindow(\'../message!getInbox.action?message.id='+ json.message.id +'\');">';

                            html += json.message.title;

                            html += '</a> <br /> ' + from + ' '+ json.message.sender.username +' - '+ json.message.datetime.$ +' </li>';                                    
                        }
                        html += '</ul>';

                        $('messages.list').innerHTML = html;
                    } else {
                        $('messages.empty').style.display = 'block';
                        $('messages.list').style.display = 'none';
                    }

                },
                onFailure: function() {
                    alert('Message: Error retrieving messages...')
                }
            });
}