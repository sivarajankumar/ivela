/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function getJsonFromUrlPost(url, params){
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
        onFailure: function() { alert('Message: Something went wrong...') },
        onException:function(request, exception) {         
            // Temporary Solution that checks for a bad formed and see if
            // it is the login page, so redirects.                 
            var message = exception.message;
            if(message.match(/Badly formed JSON string/)!= null) {
                if (message.match(/login-container/) != null) {
                    document.location = "./login.action";                    
                }
            }
       }        
    });
    return json;
}

function getJsonFromUrl(url){
    var json;
    new Ajax.Request(url,
    {
        method:'get',
        requestHeaders: {Accept: 'application/json'}, 
        asynchronous: false,
        onSuccess: function(transport) {                        
            json = transport.responseText.evalJSON(true);
        },
        onFailure: function() { alert('Message: Something went wrong...') },        
        onException:function(request, exception) {         
                 // Temporary Solution that checks for a bad formed and see if
                 // it is the login page, so redirects.                 
                 var message = exception.message;
                 if(message.match(/Badly formed JSON string/)!= null) {
                     if (message.match(/login-container/) != null) {
                         document.location = "./index.action";
                     }
                 }
            }        
    });
    return json;
}


function getJsonFromUrlPostLoad(params, func){
    new Ajax.Request(url,
    {
        method:'post',
        parameters: params,
        requestHeaders: {Accept: 'application/json'},
        onLoading: function(req){new Lightbox.base('box1');},
        onSuccess: func,
        onFailure: function() { alert('Message: Something went wrong...'); Lightbox.hideAll(); },
        onException:function(request, exception) {         
            // Temporary Solution that checks for a bad formed and see if
            // it is the login page, so redirects.                 
            var message = exception.message;
            if(message.match(/Badly formed JSON string/)!= null) {
                if (message.match(/login-container/) != null) {
                    document.location = "./login.action";
                }
            }
       }        
        //asynchronous: false
    });
}