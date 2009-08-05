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

function addUnitContentListener(frame) {    
    var iFrame = frame.contentDocument;    
    if (iFrame.attachEvent) {        
        iFrame.attachEvent('DOMNodeInserted', nodeInserted);        
    } else {        
        iFrame.addEventListener("DOMNodeInserted", nodeInserted, false);        
    }       
}


function nodeInserted(e) {    
    if (!e) e = window.event;              
    // Content Lesson is a unit-specific class.... checking by length only for now then 
    if ((e.target.innerHTML != undefined) && (e.target.innerHTML.length > 300)) {        
        var scrollx = (document.all)?document.body.scrollLeft:window.pageXOffset;        
        window.scrollTo(scrollx, 0); 
    }
}