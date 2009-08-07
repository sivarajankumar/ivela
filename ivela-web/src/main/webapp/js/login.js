window.onload = check;

function check() {
    $('loginfield').focus();
    var loc = parent.location.toString();
    if(loc.indexOf("login.action", 0) == -1) {
        parent.document.location = "index.jsp";
    }
    // Verify system pre-requisites and update error/success message
    verifyDependencies();
}

var r={'special':/[\W]/g}
function valid(o,w) {
    if ( o.value.search(r[w]) > -1 ) {
//      alert('Caractere inválido');
    }
    o.value = o.value.replace(r[w],'');
}

// System Dependencies Verification
var error = "<font color='red'>error</font>";
var ok = "<font color='green'>ok</font>";

// System Pre-Requisites for Screen Resolution: Minimum width of 1024 pixels
var result1 = error;
var viewportwidth = getWidth();
var viewportheight = getHeight();

function getWidth() {
    return window.screen.width;
}

function getHeight() {
    return window.screen.height;
}  

if (viewportwidth >= 1024) {
    result1 = ok;
}

// System Pre-Requisites for Browsers: Firefox v2 or above and Internet Explorer v6 and v7
var result2 = error;
var x = navigator;
if (/Firefox[\/\s](\d+\.\d+)/.test(navigator.userAgent)){ //test for Firefox/x.x or Firefox x.x (ignoring remaining digits);
    var ffversion=new Number(RegExp.$1); // capture x.x portion and store as a number
    if (ffversion>=2) {
        result2 = ok;
    }
}

if (/MSIE (\d+\.\d+);/.test(navigator.userAgent)){ //test for MSIE x.x;
    var ieversion=new Number(RegExp.$1); // capture x.x portion and store as a number
    if (ieversion>=6 && ieversion<8) {
        result2 = ok;
    }
}

// System Pre-Requisites for Java Virtual Machine: Java Runtime Environment 1.6 or higher
// Important: the "deployJava" object is created in the Deployment Toolkit script ("http://java.com/js/deployJava.js").
// It must be imported in the same page and before where this javascript file is used.
var result3 = error;

if (deployJava.versionCheck('1.6')) {
    result3 = ok;
}

// Error/Success Message
function verifyDependencies() {
    var dependenciesWarning = document.getElementById('dependenciesWarning'); /* located at login.jsp page */
    var dependenciesTooltip = document.getElementById('dependenciesTooltip'); /* located at login.jsp page */
    var dependenciesOK = document.getElementById('dependenciesOK'); /* located at login.jsp page */
    var dependenciesERROR = document.getElementById('dependenciesERROR'); /* located at login.jsp page */
    var dependenciesSCREEN = document.getElementById('dependenciesSCREEN'); /* located at login.jsp page */
    var dependenciesBROWSER = document.getElementById('dependenciesBROWSER'); /* located at login.jsp page */
    var dependenciesJRE = document.getElementById('dependenciesJRE'); /* located at login.jsp page */

    dependenciesWarning.style.cursor = 'default';
    if (result1 == ok && result2 == ok && result3 == ok) {
        dependenciesWarning.innerHTML = dependenciesOK.innerHTML;
        dependenciesWarning.style.backgroundImage = 'url(images/login/buton-warning-ok.gif)';
    } else {
        dependenciesWarning.innerHTML = dependenciesERROR.innerHTML;
        dependenciesWarning.style.backgroundImage = 'url(images/login/buton-warning-error.gif)';
        if (result3 == error) {
            dependenciesWarning.onclick = function() {deployJava.installLatestJRE();};
            dependenciesWarning.style.cursor = 'pointer';
        }
    }

    dependenciesTooltip.style.position = 'absolute';
    dependenciesTooltip.style.display = 'none';
    dependenciesTooltip.innerHTML = '<table>' +
                                    (result1==error?'<tr><td colspan="3"><b>'+dependenciesSCREEN.innerHTML.split('|')[1]+'</b></td></tr><tr><td colspan="3" height="3px"></td></tr>':'') +
                                    (result2==error?'<tr><td colspan="3"><b>'+dependenciesBROWSER.innerHTML.split('|')[1]+'</b></td></tr><tr><td colspan="3" height="3px"></td></tr>':'') +
                                    (result3==error?'<tr><td colspan="3"><b>'+dependenciesJRE.innerHTML.split('|')[1]+'</b></td></tr><tr><td colspan="3" height="3px"></td></tr>':'') +
                                    '<tr><td>'+dependenciesSCREEN.innerHTML.split('|')[0]+':&nbsp;</td><td>'+viewportwidth+'x'+viewportheight+'</td><td>&nbsp;'+result1+'</td></tr>' +
                                    '<tr><td>'+dependenciesBROWSER.innerHTML.split('|')[0]+':&nbsp;</td><td>'+x.appCodeName+' '+x.appVersion+'</td><td>&nbsp;'+result2+'</td></tr>' +
                                    '<tr><td>'+dependenciesJRE.innerHTML.split('|')[0]+':&nbsp;</td><td>'+deployJava.getJREs()+'</td><td>&nbsp;'+result3+'</td></tr>' +
                                    '</table>';

    dependenciesWarning.style.position = 'relative';
    dependenciesWarning.style.left = dependenciesWarning.offsetLeft + 25 + 'px';
    dependenciesWarning.style.top = dependenciesWarning.offsetTop + 'px';
    dependenciesWarning.onmouseover = function() {
        document.getElementById('dependenciesTooltip').style.display = 'inline';
        document.getElementById('dependenciesTooltip').style.left = getXY(document.getElementById('dependenciesWarning'))['x'] + document.getElementById('dependenciesWarning').offsetWidth - 5 + 'px';
        document.getElementById('dependenciesTooltip').style.top = getXY(document.getElementById('dependenciesWarning'))['y'] - 10 + 'px';};
    dependenciesWarning.onmouseout = function() {document.getElementById('dependenciesTooltip').style.display = 'none'};
}

/**
 * Returns the absolute X and Y positions of an object.
 * @param {HTMLObject} obj HTML Object.
 * @return {Object} Returns an accessor with .x and .y values.
 */
function getXY(obj) {
    var curleft = 0;
    var curtop = obj.offsetHeight + 5;
    var border;
    if (obj.offsetParent) {
        do {
            curleft += obj.offsetLeft;
            curtop += obj.offsetTop;
        } while (obj = obj.offsetParent)
    } else if (obj.x) {
        curleft += obj.x;
        curtop += obj.y;
    }
    return {'x': curleft, 'y': curtop};
}

/**
 * Returns the specified computed style on an object.
 * @param {HTMLObject} obj HTML Object
 * @param {String} styleProp Property name.
 * @return {Mixed} Computed style on object.
 */
function getStyle(obj, styleProp) {
    if (obj.currentStyle)
        return obj.currentStyle[styleProp];
    else if (window.getComputedStyle)
        return document.defaultView.getComputedStyle(obj,null).getPropertyValue(styleProp);
}
