var progressArrowCont = 0;
var accessed = "";

function goToHome(goToPage) {
    var url ="contentInfo!showContentCustom.action?course.id="+idCourse+"&grade.id="+idGrade+"&goToIndex=yes&goToPage=";
    if (!goToPage)
        url += "index.html";
    else
        url += goToPage;            
    window.location = url;
}

function goToPage(goToPage) {
    window.location = 'contentInfo!showContentCustom.action?course.id='+idCourse+"&grade.id="+idGrade+'&discipline.id='+idDiscipline+'&unit.id='+idUnit+'&unitContent.id='+idUnitContent+'&goToPage='+goToPage;
}

function goToDiscipline(disciplineTag) {
    window.location = 'contentInfo!showContentCustom.action?course.id='+idCourse+"&grade.id="+idGrade+'&disciplineTag='+disciplineTag+'&goToPage=table_contents.html';
}

function goToUnit(unitTag,goToPage) {
    window.location = 'contentInfo!showContentCustom.action?course.id='+idCourse+"&grade.id="+idGrade+'&discipline.id='+idDiscipline+'&unit.id='+idUnit+'&unitTag='+unitTag+'&goToPage='+goToPage;
}

function displayForum() {
	strWidth = screen.availWidth - 150;
	strHeight = screen.availHeight - 150;
	var showUrl = "forum!list.action?course.id="+idCourse;
	var home = new Ext.Window({
	       title: 'Forum',
	       width: strWidth,
	       height:strHeight,
	       x: 30,
	       y: 30,
	       minWidth: strWidth-100,
	       minHeight: strWidth-50,
	       collapsible:true,	     
	       autoScroll:true,
	       plain:true,
	       bodyStyle:'padding:0px;',
	       body: new Ext.ux.ManagedIframe({autoCreate:{src:showUrl, cls:'x-window-body'}})});
	home.show();
}

function translateWord(keyWord){
	width = screen.availWidth - 150;
	height = screen.availHeight - 150;
	var url = "http://www.google.com/dictionary?aq=f&langpair=en|pt&q="+keyWord+"&hl=pt-BR";
	var showUrl = "forum!list.action?course.id="+idCourse;
	var home = new Ext.Window({
	       title: 'Translate Word',
	       width: width,
	       height:height,
	       x: 30,
	       y: 30,
	       minWidth: width-100,
	       minHeight: height-50,
	       collapsible:true,	     
	       autoScroll:true,
	       plain:true,
	       bodyStyle:'padding:0px;',
	       body: new Ext.ux.ManagedIframe({autoCreate:{src:showUrl, cls:'x-window-body'}})});
	home.show();
}


function closeCourse() {
	window.close();
}

function displayUserName() {
    document.write(getHtml('contentInfo!getSystemUser.action'));
}

function ProgressCourse() {
    var progress = getHtml('contentInfo!getProgress.action?course.id='+idCourse);
    progressArrowCont = 0;
    for (j=0 ; j < 10 ; j++) {
        if(j < progress) {
            document.write('<img src="RenderServlet?file=/'+idCourse+'/'+idDiscipline+'/'+idUnit+'/'+idUnitContent+'/images/home_andamento_feito.gif" />');
            progressArrowCont += 11;
        } else {
            document.write('<img src="RenderServlet?file=/'+idCourse+'/'+idDiscipline+'/'+idUnit+'/'+idUnitContent+'/images/home_andamento_falta.gif" />');
        }
    }
    addLoadEvent(ProgressCourseArrow);
}

function ProgressCourseArrow() {
    var arr = document.getElementsByTagName("div");
    for (i = 0; i < arr.length; i++) {
        if (arr[i].className == "home_andamento_seta") {
           arr[i].style.left = progressArrowCont+"px";
        }
    }
}

function getTimeRemaining() {
    document.write(getHtml('contentInfo!getTimeLeft.action?course.id='+idCourse));
}

function getScoreOnly() {
    document.write(getHtml('contentInfo!getScore.action?grade.id='+idGrade+"&ScoreType=only"));
}

//
function getScore() {
    document.write(getHtml('contentInfo!getScore.action?grade.id='+idGrade+"&ScoreType=current"));
}

// Total Score for the User for a course
function getScoreTotal() {
    document.write(getHtml('contentInfo!getScore.action?course.id='+idCourse+"&grade.id="+idGrade+"&ScoreType=total"));
}

function displayTutor() {    
    window.open(getHtml('contentInfo!getTutorsEmail.action?grade.id='+idGrade+'&unitContent.id='+idUnitContent));
}

function showGlobalImage(image) {
    document.write('<img src="RenderServlet?file=/'+idCourse+'/'+image+'">');
}

function showGlobalImage(image,params) {
    document.write('<img src="RenderServlet?file=/'+idCourse+'/'+image+'" '+params+'>');
}

function showPlayer(sound_file, height, width) {
    var address = document.location.href;
    address = address.split(".action")[0];
    address = address.substring(0, address.lastIndexOf("/") + 1);

    var applet = '<applet code=" br.ufc.ivela.voice.sound.PlayerApplet" archive="applet/ivela_sound.jar, applet/jogg-0.0.7.jar,  applet/jorbis-0.0.15.jar, applet/tritonus_share.jar, applet/vorbisspi1.0.3.jar" ';
    if (!width) 
    applet += 'width="75"';
    else
        applet += 'width=' + width; 
    if (!height) 
    applet += ' height="27"';
    else
        applet += ' height=' + height;
    
    sound_file = 'RenderServlet?file=/'+idCourse+'/'+idDiscipline+'/'+idUnit+'/'+idUnitContent+'/' + sound_file;
    applet +=  ' > <param name="audio_url" value="';
    applet += sound_file;    
    applet += '"/> <param name="audioHost" value="';
    applet += address;
    applet += '" /> </applet>';
    document.write(applet);
}

function showRecorder(question, phrases, grammar_file, chances, audio_files, exercise) {
    var address = document.location.href;
    address = address.split(".action")[0];
    address = address.substring(0, address.lastIndexOf("/") + 1);

    var applet = '<applet code="br.ufc.ivela.voice.BlackBoardApplet" archive="applet/ivela_voice_new.jar, applet/jogg-0.0.7.jar,  applet/jorbis-0.0.15.jar, applet/tritonus_share.jar, applet/vorbisspi1.0.3.jar" ';    
        applet += 'width="490" height="250"  >';                
        applet += '<param name="question" value="';    
        applet += question;        
        applet += '" /> <param name="exe" value="';    
        applet += phrases;        
        applet += '" /> <param name="conf" value="';    
        applet += grammar_file;        
        applet += '" /> <param name="chances" value="';    
        applet += chances;        
        applet += '" /> <param name="audio" value="';    
        applet += audio_files;        
        applet += '" /> <param name="exeId" value="';
        applet += exercise;        
        applet += '" /> <param name="audioHost" value="';
        applet += address;
        applet += '" /> </applet>';
}
     
function includeGlobalCss(css) {
    document.write('<link href="RenderServlet?file=/'+idCourse+'/'+css+'" rel="stylesheet" type="text/css" />');
}

function showImage(image) {
    document.write('<img src="RenderServlet?file=/'+idCourse+'/'+idDiscipline+'/'+idUnit+'/'+idUnitContent+'/'+image+'">');
}

function labelDisciplineStatus(disciplineTag) {
    var status = getHtml('contentInfo!isDisciplineCompleted.action?grade.id='+idGrade+'&course.id='+idCourse+'&discipline.tag='+disciplineTag);
    var onClickFunc = "goToDiscipline('"+disciplineTag+"');";
    if ("0"==status) {
        document.write('<a href="#" onclick="'+onClickFunc+'"><img src="RenderServlet?file=/'+idCourse+'/images/modulo_selo_concluido.png"></a>');
    } else if ("-1"==status){
        document.write('<a href="#" onclick="'+onClickFunc+'"><img src="RenderServlet?file=/'+idCourse+'/images/modulo_selo_acesse.png"></a>');
    } else {
        document.write('<a href="#" onclick="'+onClickFunc+'"><img src="RenderServlet?file=/'+idCourse+'/images/modulo_selo_continuar.png"></a>');
    }
}

function labelUnitStatus(unitContentTag, goToPage) {
    var onClickFunc = "goToUnit('"+unitContentTag+"', '"+goToPage+"');";
    if ("true"==getHtml('contentInfo!isUnitCompleted.action?unitContent.tag='+unitContentTag)) {
        document.write('<a href="#" onclick="'+onClickFunc+'"><img src="RenderServlet?file=/'+idCourse+'/images/modulo_selo_concluido_menor.png"></a>');
    } else {
        if (("true"==getHtml('contentInfo!isUnitUnlocked.action?grade.id='+idGrade+'&unitContent.tag='+unitContentTag)) && (accessed == "")) {
            accessed = 'true';
            document.write('<a href="#" onclick="'+onClickFunc+'"><img src="RenderServlet?file=/'+idCourse+'/images/modulo_selo_acesse_menor.png"></a>');
        } else {
            document.write('<img src="RenderServlet?file=/'+idCourse+'/images/modulo_selo_bloqueado_menor.png">');
            accessed = 'locked';
        }
    }
}

function finishLesson() {
    window.location = 'contentInfo!finishLesson.action?course.id='+idCourse+'&discipline.id='+idDiscipline+'&unitContent.id='+idUnitContent+'&grade.id='+idGrade;
}

function displayChat() {
	strWidth = screen.availWidth - 150;
	strHeight = screen.availHeight - 150;
	var showUrl = 'course!showChatStd.action?courseId='+idCourse+'&disciplineId='+idDiscipline;
	var home = new Ext.Window({
	       title: 'Chat',
	       width: strWidth,
	       height:strHeight,
	       x: 30,
	       y: 30,
	       minWidth: strWidth-100,
	       minHeight: strWidth-50,
	       collapsible:true,	     
	       autoScroll:true,
	       plain:true,
	       bodyStyle:'padding:0px;',
	       body: new Ext.ux.ManagedIframe({autoCreate:{src:showUrl, cls:'x-window-body'}})});
	home.show();
}


function fechar() {
    window.close();
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

function addLoadEvent(func) {
    var oldonload = window.onload;
    if (typeof window.onload != 'function') {
        window.onload = func;
    } else {
        window.onload = function() {
            if (oldonload) {
                oldonload();
            }
            func();
        }
    }
}

/*************************/
/*** Challenges Section **/
/*************************/

var paintAnswerBackground;
var wrongAnswerStyle;
var rightAnswerStyle;

/* Submit a Exercise for answer, expects a Form Element as parameter that will be serialized and sent. The input fields for your exercise */
function submitExercise(form) {
    
    if (form == undefined) 
        form = $('exercise');
    
    var chalName = form.name;
    
    if (chalName) {
      chalName = '&challenge='+chalName;
    } else {
      chalName = "";
    }

    if (rightAnswerStyle == undefined || rightAnswerStyle == "") {
    rightAnswerStyle = "transparent url(images/course/default/lesson_li_right.gif) no-repeat scroll left top";
    }
    
    if (wrongAnswerStyle == undefined || rightAnswerStyle == "") {
    wrongAnswerStyle = "transparent url(images/course/default/lesson_li_wrong.gif) no-repeat scroll left top";
    }

    var value = Form.serialize(form, false);
    
    var url = '/ivela-web/ChallengeSolver?gradeId='+idGrade+'&unitId='+idUnit+chalName+'&'+value;
    var json = getJson(url);

    if (json == undefined || json == "") return false;

    var status = json.status;

    if (status == "err") {
        //alert();
        return false;
    }
    
    if (status == "fin") {
        //Maximum number of retries reached
        // return false;
    }
    
    if (status.indexOf("dep") != -1) {
        //alert(status.substring(4));
    }
   
    var i = 0;    
    var name = json.name;

    for (i = 0; i < json.list.right.length; i++) {   
    var rightDiv = document.getElementById(json.list.right[i] + "_check");
    if (rightDiv == undefined) rightDiv = document.getElementById(name + "_" + json.list.right[i] + "_check");
        if (rightDiv != undefined) {
            rightDiv.style.background = rightAnswerStyle;
        }
    }

    for (i = 0; i < json.list.wrong.length; i++) {   
        var errorDiv = document.getElementById(json.list.wrong[i] + "_check");
        if (errorDiv == undefined) errorDiv = document.getElementById(name + "_" + json.list.wrong[i] + "_check");
        if (errorDiv != undefined) {
            errorDiv.style.background = wrongAnswerStyle;
        }
    }
    
    var div = document.getElementById(name + "_status");
    if (div != undefined) {
      var divStyle = "fail";
     
      if(status == "ok") {divStyle = "ok";}        

      var html = '<div id=\"status_content'+divStyle+'\">';
      html += '<div id=\"status\">Score: '+json.results;                     
      html += '</div>';
      div.innerHTML = html;      
    }

    var count_sta = json.count;
    if (count_sta != undefined) {
      var divC = document.getElementById(name + "_count_status");
      if (divC != undefined) {
        var html = '<div id=\"count_status_content\">';
        html += '<div id=\"count_status\"> ';
        html += count_sta;                     
        html += '</div>';
        div.innerHTML = html;      
      }
    }
    return false;
}

/* Retrieves the Right Answers for the Exercise, this will mark the exercise as done */
function getExerciseAnswers(form) {
    if (form == undefined) 
        form = $('exercise');
    
    var chalName = form.name;
    
    if (chalName) {
      chalName = '&challenge='+chalName;
    } else {
      chalName = "";
    }

    var value = Form.serialize(form, false);    
    var url = '/ivela-web/ChallengeSolver?answers=t&gradeId='+idGrade+'&unitId='+idUnit+chalName+'&'+value;
    var json = getJson(url);
    if (json == undefined || json == "") return false;

    if (json.status == "err") {
        //alert();
        return false;
    }
         
    for (i = 0; i < json.list.right.length; i++) {   
        var text = form.getInputs('text', json.list.right[i]);    
        if (text[0] != undefined) {     
            var ans = json.list.answers[i];
            if (ans.indexOf("[or]") != -1) {
                ans = ans.substring(0, ans.indexOf("[or]"));
            }
            text[0].value = ans;
            continue;
        } 
        var button = form.getInputs('radio', json.list.right[i]);
        if (button[0] != undefined) {
           var radioValue = json.list.answers[i];        
           for (j = 0; j < button.length; j++) {
               if (button[j].value == radioValue) button[j].checked = 1
           }                                
        }
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