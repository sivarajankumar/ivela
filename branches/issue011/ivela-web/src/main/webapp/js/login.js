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

// System Pre-Requisites for Browsers: Netscape version 5 or above, Mozilla v5 or above and Internet Explorer v4 or above
var result1 = error;
var viewportwidth = getWidth();
var viewportheight = getHeight();

function getWidth() {
    return window.innerWidth ? window.innerWidth : /* For non-IE */
    document.documentElement ? document.documentElement.clientWidth : /* IE 6+ (Standards Compilant Mode) */
    document.body ? document.body.clientWidth : /* IE 4 Compatible */
    window.screen.width; /* Others (It is not browser window size, but screen size) */
}

function getHeight() {
    return window.innerHeight ? window.innerHeight : /* For non-IE */  
    document.documentElement ? document.documentElement.clientHeight : /* IE 6+ (Standards Compilant Mode) */  
    document.body ? document.body.clientHeight : /* IE 4 Compatible */  
    window.screen.height; /* Others (It is not browser window size, but screen size) */  
}  

if (viewportwidth >= 1024) {
    result1 = ok;
}

// System Pre-Requisites for Screen Resolution: Minimum width of 1024 pixels
var result2 = error;
var x = navigator
var iVer = parseInt(x.appVersion);
var sName = x.appName;

if (sName == "Netscape") {
    if (iVer >= 5) {
        result2 = ok;
    }
} else if (sName == "Mozilla") {
    if (iVer >= 5) {
        result2 = ok;
    }
} else if (sName == "Microsoft Internet Explorer") {
    if (iVer >= 4) {
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

    if (result1 == ok && result2 == ok && result3 == ok) {
        dependenciesWarning.innerHTML = dependenciesOK.innerHTML;
        dependenciesWarning.style.backgroundColor = '#00ff00';
    } else {
        dependenciesWarning.innerHTML = dependenciesERROR.innerHTML;
        dependenciesWarning.style.backgroundColor = '#ff0000';
        if (result3 == error) {
            dependenciesWarning.onclick = function() {deployJava.installLatestJRE();};
        }
    }

    dependenciesTooltip.style.position = 'absolute';
    dependenciesTooltip.style.display = 'none';
    dependenciesTooltip.innerHTML = '<table border="1">' +
                                    '<tr><td>'+dependenciesSCREEN.innerHTML+'</td><td>'+viewportwidth+'x'+viewportheight+'</td><td>'+result1+'</td></tr>' +
                                    '<tr><td>'+dependenciesBROWSER.innerHTML+'</td><td>'+x.appCodeName+' '+x.appVersion+'</td><td>'+result2+'</td></tr>' +
                                    '<tr><td>'+dependenciesJRE.innerHTML+'</td><td>'+deployJava.getJREs()+'</td><td>'+result3+'</td></tr>' +
                                    '</table>';
    dependenciesWarning.onmouseover = function() {
        document.getElementById('dependenciesTooltip').style.display = 'inline';
        document.getElementById('dependenciesTooltip').style.left = '300px';
        document.getElementById('dependenciesTooltip').style.top = '300px';};
    dependenciesWarning.onmouseout = function() {document.getElementById('dependenciesTooltip').style.display = 'none'};
}
