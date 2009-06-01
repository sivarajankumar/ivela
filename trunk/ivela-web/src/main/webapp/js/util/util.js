/**
 * Get the language of the clint's browser
 * 2 letters only (E.g.: 'pt' for portuguese )
 */
function getLanguage (){
    return navigator.language.substring(0,2);
}

function classCss(){
    
    var css;
    
    if ((!document.all)&&($))
    {
        css = "class";
    }
    //Tratamento para I.E
    if ((document.all)&&($)){
        css = "className";
    }
    return css;
}