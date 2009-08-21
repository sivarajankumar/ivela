// Indicates that a Timeout has happened
var sessionTimeout = false;
var defaultAjaxErrorMessage = 'Something went wrong...';
var ajaxErrorMessage = "";

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
        onFailure: function() {
            if (ajaxErrorMessage == "") {
                alert(defaultAjaxErrorMessage);    
            } else {
                alert(ajaxErrorMessage);
            }
             
            },
        onException:function(request, exception) {                         
            var message = exception.message;
            if(message.match(/Badly formed JSON string/)!= null) {
                if (message.match(/login-container/) != null) {
                    sessionTimeout = true;
                    json = "";
                }
            }
       }     
    });
    return json;
}    

function returnToHome() {
    document.location = "./home.action";
}

function execute(url,pars,funcao) {
    new Ajax.Request( url, {method: 'post', parameters: pars,onComplete: funcao});
}


function include(url, pars, div){
    new Ajax.Updater( div, url, {method: 'post',parameters: pars });
}