/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 function selectStates(countryId){
        var url = "profile!getStatesByCountry.action";
        var params = "country.id="+countryId;
        var json  = getJsonFromUrlPost(url, params);
        var html = "";
        for(i=0;i<json.list.state.length;i++){
            if(i==0) html += "<option value='"+json.list.state[i].id+"' selected>"+json.list.state[i].name+"</option>";
            else html += "<option value='"+json.list.state[i].id+"'>"+json.list.state[i].name+"</option>";
        }
        $('stateId').innerHTML = html;
    }
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

