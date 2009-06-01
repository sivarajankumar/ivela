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
        method:'get',
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
        method:'get',
        requestHeaders: {
            Accept: 'application/json'
        },
        onSuccess: function(transport) {
            var json = transport.responseText.evalJSON(true);

            if (json.list != null && json.list.message.length > 0) {
                $('messages.empty').style.display = 'none';
                $('messages.list').style.display = 'block'

                var html = '<ul>';

                for(var i = 0; i < json.list.message.length; i++) {

                    html += '<li> <a class="lightwindow page-options" '+
                            'href="javascript:vaiLightWindow(\'../message!getInbox.action?message.id='+ json.list.message[i].id +'\');" '+
                            '> ';

                    html += json.list.message[i].title;

                    html += '</a> <br /> ' + from + ' '+ json.list.message[i].sender.username +' - '+ json.list.message[i].datetime.$.substring(0,19) +' </li>';
                }

                html += '</ul>';

                $('messages.list').innerHTML = html;

            } else {
                $('messages.empty').style.display = 'block';
                $('messages.list').style.display = 'none'
            }
        },
        onFailure: function() {
            alert('Message: Error retrieving messages...')
        }
    });
}