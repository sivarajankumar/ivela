var idCourse = getUrlParameter("course.id");
var idDiscipline = getUrlParameter("discipline.id");
var idUnit = getUrlParameter("unit.id");
var idUnitContent = getUrlParameter("unitContent.id");;
var idGrade = getUrlParameter("grade.id");;

function goToPage(goToPage) {
    window.location = 'contentInfo!showContent.action?course.id='+idCourse+'&discipline.id='+idDiscipline+'&unit.id='+idUnit+'&unitContent.id='+idUnitContent+'&goToPage='+goToPage;
}

function getSystemUser() {
    document.write(getHtml('contentInfo!getSystemUser.action'));
}

function getProgress() {
    document.write(getHtml('contentInfo!getProgress.action?course.id='+idCourse));
}

function showImage(image) {
    document.write('<img src="RenderServlet?file=/'+idCourse+'/'+idDiscipline+'/'+idUnit+'/'+idUnitContent+'/'+image+'">');
}

function isUnlocked() {
    document.write(getHtml('contentInfo!isUnlocked.action?grade.id='+idGrade+'&unitContent.id='+idUnitContent));
}

/*************************/
/** Auxiliary Functions **/
/*************************/

function computeUrl(urlId) {
    // do nothing
}

function computeExe(urlExe) {
    // do nothing
}

function getUrlParameter(name) {
    name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
    var regexS = "[\\?&]"+name+"=([^&#]*)";
    var regex = new RegExp(regexS);
    var results = regex.exec(window.location.href);
    if (results == null) {
        return "";
    } else {
        return results[1];
    }
}

function getHtml(strUrl) {
    var html;
    new Ajax.Request(strUrl, {
        method:'get',
        requestHeaders: { Accept: 'text/plain' },
        asynchronous: false,
        onSuccess: function(transport) { html = transport.responseText; },
        onFailure: function() { alert('Message: Something went wrong...') }
    });
    return html;
}

function getJson(strUrl) {
    var json;
    new Ajax.Request(strUrl, {
        method:'get',
        requestHeaders: { Accept: 'application/json' },
        asynchronous: false,
        onSuccess: function(transport) { json = transport.responseText.evalJSON(); },
        onFailure: function() { alert('Message: Something went wrong...') }
    });
    return json;
}

/*function goToPage(goToPage) {
    document.body.innerHTML = getHtml('contentInfo!showContent.action?course.id='+idCourse+'&discipline.id='+idDiscipline+'&unit.id='+idUnit+'&unitContent.id='+idUnitContent+'&goToPage='+goToPage);
}
function goToPage(goToPage) {
    window.location = 'http://localhost:8080/ivela-web/discipline!showContent.action?course.id=2&discipline.id=1&unit.id=1';
}
function goToPage(goToPage) {
    window.location = 'RenderServlet?file=/'+idCourse+'/'+idDiscipline+'/'+idUnit+'/'+idUnitContent+'/'+goToPage;
}
function goToPage(goToPage) {
    var html = getHtml('http://localhost:8080/ivela-web/RenderServlet?file=/2/1/1/1/'+goToPage);    document.body.innerHTML = html;}
function previousPage(previousPage) {
    alert(previousPage);
}

function nextPage(nextPage) {
    alert(nextPage);
}
function getCurrentLocation() {
    currentLocation = getJson();
}*/
