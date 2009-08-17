function getJsonFromUrl(url, params){
    var json = '';    
    new Ajax.Request(url,
    {
        method:'post',
        parameters: params,
        requestHeaders: {Accept: 'application/json'},
        asynchronous: false,
        onSuccess: function(transport) {
            json = transport.responseText.evalJSON(true);
        },
        onFailure: function() { alert(errorAjax) },
        onException:function(request, exception) {         
            // Temporary Solution that checks for a bad formed and see if
            // it is the login page, so redirects.                 
            var message = exception.message;
            if(message.match(/Badly formed JSON string/)!= null) {
                if (message.match(/login-container/) != null) {
                    document.location = "./home.action";                    
                }
            }
       }
    });    
    return json;
}

function showEntryTopic() {    
    $('topic.title').value = '';
    $('topic.description').value = '';
    
    $('showEntryTopic').style.display = 'block';
    $('topic-new').style.display = 'none';    
    var scrollx = (document.all)?document.body.scrollLeft:window.pageXOffset;
    var scrolly = $('showEntryTopic').offsetHeight;      
    window.scroll(scrollx, scrolly * 2); 
}

function submitTopic(forumId)
{             
    if ($('topic.title').value == '' || $('topic.title').value.length < 1) {
        alert(errorTitle);
        $('topic.title').focus();
        return;
    }
    if ($('topic.description').value == '' || $('topic.description').value.length < 1) {
        alert(errorDescr);
        $('topic.description').focus();
        return;
    }
              
    new Lightbox.base('loading');
    var url = 'topic!add.action';

    var params = 'topic.forum.id=' + forumId;
    params += '&topic.title=' + $('topic.title').value;
    params += '&topic.description=' + $('topic.description').value;
    
    var jsonTopic = getJsonFromUrl(url,params);
    
    if ( (jsonTopic != null) && (jsonTopic.result == 'true') )
    {
        document.location = 'topic!listByForum.action?forum.id=' + forumId;
    }
    else
    {   
        Lightbox.hideAll()        
        if (jsonTopic != null && jsonTopic.result === undefined) {
            alert(errorTopic);
        } else {
            alert(jsonTopic.result);
            
        }
    }

}

function deleteTopic(forumId, topicId) {
    new Lightbox.base('loading');
    var url = 'topic!remove.action';
    
    var params = 'topic.id=' + topicId;
    var jsonTopic = getJsonFromUrl(url, params);

    if (jsonTopic != null && jsonTopic.result == 'true') {
        document.location = 'topic!listByForum.action?forum.id=' + forumId;
    } else {
        Lightbox.hideAll();
        alert(errorTopicD);
    }
}