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
        onFailure: function() { alert('Message: Something went wrong...') }
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
        onFailure: function() { alert('Message: Something went wrong...') }
    });
    return json;
}


function getJsonFromUrlPostLoad(url, params, func){
    new Ajax.Request(url,
    {
        method:'post',
        parameters: params,
        requestHeaders: {Accept: 'application/json'},
        onLoading: function(req){new Lightbox.base('box1');},
        onSuccess: func,
        onFailure: function() { alert('Message: Something went wrong...'); Lightbox.hideAll(); }
        //asynchronous: false
    });
}