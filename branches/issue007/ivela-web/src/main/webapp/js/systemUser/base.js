/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var hide = true;
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
        alert(pStr + " NÃO é uma data válida.");
    }
} // doDate

            
/**
 * Remove
 */
function phone(v){
    v=v.replace(/\D/g,"")
    v=v.replace(/^(\d\d)(\d)/g,"($1) $2") 
    v=v.replace(/(\d{4})(\d)/,"$1-$2") 
    return v
}

function socialnumber(v){
    v=v.replace(/\D/g,"") 
    v=v.replace(/(\d{3})(\d)/,"$1.$2") 
    v=v.replace(/(\d{3})(\d)/,"$1.$2") 
    v=v.replace(/(\d{3})(\d{1,2})$/,"$1-$2")
    return v
}

function zipcode(v){
    v=v.replace(/\D/g,"") 
    v=v.replace(/^(\d{5})(\d)/,"$1-$2")
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
