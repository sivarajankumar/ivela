var idCourse = getUrlParameter("course.id");
var idDiscipline = getUrlParameter("discipline.id");
var idUnit = getUrlParameter("unit.id");
var idUnitContent = getUrlParameter("unitContent.id");
var idGrade = getUrlParameter("grade.id");

function goToPage(goToPage) {
    window.location = 'contentInfo!showContentCustom.action?course.id='+idCourse+'&discipline.id='+idDiscipline+'&unit.id='+idUnit+'&unitContent.id='+idUnitContent+'&goToPage='+goToPage;
}

function getSystemUser() {
    document.write(getHtml('contentInfo!getSystemUser.action'));
}

function getProgress() {
    document.write(getHtml('contentInfo!getProgress.action?course.id='+idCourse));
}

function showGlobalImage(image) {
     document.write('RenderServlet?file='+idCourse+'/'+image);
//    document.write('<img src="RenderServlet?file=/'+idCourse+'/'+image+'">');
}

function includeGlobalCss(css) {
    document.write('<link href="RenderServlet?file=/'+idCourse+'/'+css+'" rel="stylesheet" type="text/css" />');
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





/*************************/
/*** Challenges Section **/
/*************************/

var paintAnswerBackground;
var wrongAnswerStyle;
var rightAnswerStyle;

/* Submit a Exercise for answer, expects a Form Element as parameter that will be serialized and sent. The input fields for your exercise */
function submitExercise(form) {
    
    if (rightAnswerStyle == undefined || rightAnswerStyle == "") {
    rightAnswerStyle = '<img class="challengeCheck" src="images/course/default/lesson_li_right.gif" alt="" />';
    }
    
    if (wrongAnswerStyle == undefined || rightAnswerStyle == "") {
    wrongAnswerStyle = '<img class="challengeCheck" src="images/course/default/lesson_li_wrong.gif" alt="" />';
    }

    var value = Form.serialize(form, false);
    
    var url = '/ivela-web/ChallengeSolver?gradeId='+idGrade+'&unitId='+idUnit+'&'+value;
    var json = getJson(url);

    if (json == undefined || json == "") return false;
    
    if (json.status == "err") {
        //alert();
        return false;
    }
    
    if (json.status == "fin") {
        //Maximum number of retries reached
        return false;        
    }
    
    var i = 0;    
    for (i = 0; i < json.list.right.length; i++) {   
    rightDiv = document.getElementById(json.list.right[i] + "_check");
        if (rightDiv != undefined) {
            rightDiv.innerHTML = rightAnswerStyle;
        }
    }

    for (i = 0; i < json.list.wrong.length; i++) {   
        errorDiv = document.getElementById(json.list.wrong[i] + "_check");
        if (errorDiv != undefined) {
            errorDiv.innerHTML = wrongAnswerStyle;
        }
    }
    
    var name = json.name;
    var status = json.status;
    var div = document.getElementById(name + "_status");
    if (div != undefined) {
      var divStyle = "fail";
     
      if(status == "ok") {divStyle = "ok";}        

      var html = '<div id=\"status_content'+divStyle+'\">';
      html += '<div id=\"status\">Score: '+json.results;                     
      html += '</div>';
      div.innerHTML = html;      
    }

    return false;
}

/* Use this to set what shoulda appear in the check div in case of right answers in the challenge */
function setRightAnswerStyle(style) {
    rightAnswerStyle = style;
}

/* Use this to set what shoulda appear in the check div in case of wrong answers in the challenge */
function setWrongAnswerStyle(style) {
    wrongAnswerStyle = style;
}

/*****************************/
/*** end Challenges Section **/
/*****************************/