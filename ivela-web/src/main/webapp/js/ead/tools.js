window.onload = loadTools;

function loadTools() {
    getLastUnitContent();
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
    new Ajax.Request('home!getToolsTopics.action',
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
                            'href="javascript: vaiLightWindow(\'topic!listByForum.action?forum.id='+ json.topics[i].forumId /*+'&topic.id='+json.topics[i].id*/+'\');" '+
                            '>';

                    html += json.topics[i].title;

                    html += '</a> <br /> ' + postBy + ' '+ json.topics[i].createdBy +' - '+ json.topics[i].createdAt +' </li>';
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
    new Ajax.Request('home!getToolsMessages.action',
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
                            'href="javascript: vaiLightWindow(\'message!getInbox.action?message.id='+ json.list.message[i].id +'\');" '+
                            '> ';

                    html += json.list.message[i].title;

                    html += '</a> <br /> ' + from + ' '+ json.list.message[i].sender.username +' - '+ json.list.message[i].datetime.$ +' </li>';
                }

                html += '</ul>';

                $('messages.list').innerHTML = html;
                //lightwindowInit();
                //alert('true');

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


function getLastUnitContent() {
    new Ajax.Request('home!getToolsLastUnitContent.action',
    {
        method:'get',
        requestHeaders: {
            Accept: 'application/json'
        },
        onSuccess: function(transport) {
            var json = transport.responseText.evalJSON(true);

            if (json.unitContent != null && json.unitContent != '') {
                $('last_unit_content_div').style.display = 'block';
                $('last_unit_content_title').innerHTML = json.unitContent.title;
            }
        },
        onFailure: function() { 
            alert('Message: Error retrieving last unit content...')
        }
    });  
}
    
function getCourseProgress(courseId) {
    new Ajax.Request('home!getCourseProgress.action?courseId=' + courseId,
    {
        method:'get',
        requestHeaders: {
            Accept: 'application/json'
        },
        onSuccess: function(transport) {
            var json = transport.responseText.evalJSON(true);

            if (json.progress != null && json.progress != '') {
                $('course.' + courseId + '.progress').innerHTML = json.progress + '%';
                $('course.' + courseId + '.image').width = ((json.progress * 70) / 100);
            }
        },
        onFailure: function() { 
            alert('Message: Error retrieving last unit content...')
        }
    });  
}