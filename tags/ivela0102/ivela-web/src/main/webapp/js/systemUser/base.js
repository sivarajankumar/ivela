/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var hide = true;
var originalStyle;
Event.observe(window, 'load', function() {
    if(isAvailable('toolsUser')){
        Event.observe($('toolsUser'), 'click', function(){
            // Workaround for IE not expanding when showing hidden fields
            if (originalStyle == undefined) {
                originalStyle = document.body.style.height;
            }
            if(!(typeof( window.innerWidth ) == 'number')) {
                if (!hide) {
                    document.body.style.height = originalStyle;
                } else {
                    document.body.style.height = "auto";                    
                }
            }
            hideFields('hidden', 'blind');
            selectStates($('country').value);
            
        })
    }
});

// If an Element with an specific id is available
function isAvailable(element) {
    if ($(element)) {
        return true;
    }
}

function hideFields(s1, s2){
    Effect.toggle(s1, s2);
    hide=!hide;
    
    if(hide){
        $('toolsUser').innerHTML = labelShowFields;
    }else{
        $('toolsUser').innerHTML = labelHideFields;
    }
    
}

function mascara(o,field){
    v_obj=o;
    v_fun=field;
    setTimeout("execmascara()",1);
}

function execmascara(){
    v_obj.value= v_fun(v_obj.value)
}
/**
 * Comment: clean all of non digits and put a mask on a date
 */
function birth(v) {
                
    v= v.replace(/\D/g,"");
    v=v.replace(/(\d{2})(\d)/,"$1/$2");
    v=v.replace(/(\d{2})(\d)/,"$1/$2");
                
    if(v.length == 10){
        doDate(v);
    }
                
    return v
}

function doDate(pStr)
{
    var reDate = /^((0[1-9]|[12]\d)\/(0[1-9]|1[0-2])|30\/(0[13-9]|1[0-2])|31\/(0[13578]|1[02]))\/(19)|(20)\d{2}$/;
    eval("reDate");
    if (reDate.test(pStr)) {
                   
    } else if (pStr != null && pStr != "") {
        alert(pStr + " Not a valid date.");
    }
} // doDate

            
/**
 * Remove
 */
function phone(v){
    // Temp Solution so it will only format Brazilian Phones
    var countrySel = $("country");    
    var countrySelected =countrySel[countrySel.selectedIndex];
    if (countrySelected.value == "1") {
    v=v.replace(/\D/g,"")
    v=v.replace(/^(\d\d)(\d)/g,"($1) $2") 
    v=v.replace(/(\d{4})(\d)/,"$1-$2")
    }
    return v
}

function socialnumber(v){
    // Temp Solution so it will only format Brazilian social numbers
    var countrySel = $("country");    
    var countrySelected =countrySel[countrySel.selectedIndex];
    if (countrySelected.value == "1") {
    v=v.replace(/\D/g,"") 
    v=v.replace(/(\d{3})(\d)/,"$1.$2") 
    v=v.replace(/(\d{3})(\d)/,"$1.$2") 
    v=v.replace(/(\d{3})(\d{1,2})$/,"$1-$2")
    }
    return v
}

function zipcode(v){
    var countrySel = $("country");    
    var countrySelected =countrySel[countrySel.selectedIndex];
    if (countrySelected.value == "1") {
        v=v.replace(/\D/g,"") 
        v=v.replace(/^(\d{5})(\d)/,"$1-$2")
    }
    return v
}
 

function showUnits(disciplineId) {
    var html = '';
    new Ajax.Request('unit!listJsonByDiscipline.action?discipline.id=' + disciplineId,
    {
        method:'get',
        requestHeaders: {Accept: 'application/json'},
        asynchronous: false,
        onSuccess: function(transport) {
            var json = transport.responseText.evalJSON(true);
            var units = json.units.unit;
            var msg = 'Não há unidades';
            html = '';
            if (units == undefined) {
                html = '<p class="first-access">' + msg + '</p>';
            }
            else {
                html = printUnits(disciplineId, units);
            }
        },
        onFailure: function() { alert('Message: Something went wrong...') }
    });          
    return html;
}

function selectStates(countryId) {
    var url = "profile!getStatesByCountry.action";
    var params = "inAddress.country=" + countryId;
    var json = getJsonFromUrlPost(url, params);    
    var html = "";      
    var i;            
    // IE has a known bug where is not possible to set the innerHtml of a select
    if (Prototype.Browser.IE) {
        html = '<SELECT id="stateId" name="inAddress.state">';
    }
    if ((json != undefined) && (json.list != "")) {
        for (i = 0; i < json.list.state.length; i += 2) {
            if (i == 0)
                html += "<option value='" + json.list.state[i] + "' selected>"
                        + json.list.state[i + 1] + "</option>";
            else
                html += "<option value='" + json.list.state[i] + "'>"
                        + json.list.state[i + 1] + "</option>";
        }        
    } else {
        html += "<option value='' selected> </option>";
    }
    
    if (Prototype.Browser.IE) {
        html += "</SELECT>";            
        $('stateId').outerHTML = html;
    } else {
        $('stateId').innerHTML = html;
    }        
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
