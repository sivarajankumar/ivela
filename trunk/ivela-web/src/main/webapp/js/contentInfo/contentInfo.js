// Temporary Workaround for the problem of IE breaking in some tag imgs,
// resending a request that may be broke in some IEs and thus breaking the session
//if (window.opener) {
//  document.cookie = window.opener.document.cookie;
//}

var progressArrowCont = 0;
var accessed = "";

function goToHome(goToPage) {
    var url ="contentInfo!showContentCustom.action?course.id="+idCourse+"&grade.id="+idGrade+"&goToIndex=yes&goToPage=";
    if (!goToPage)
        url += "index.html";
    else
        url += goToPage;            
    document.location = url;
}

function goToPage(goToPage) {
    document.location = 'contentInfo!showContentCustom.action?course.id='+idCourse+"&grade.id="+idGrade+'&discipline.id='+idDiscipline+'&unit.id='+idUnit+'&unitContent.id='+idUnitContent+'&goToPage='+goToPage;
}

function goToDiscipline(disciplineTag) {
    document.location = 'contentInfo!showContentCustom.action?course.id='+idCourse+"&grade.id="+idGrade+'&disciplineTag='+disciplineTag+'&goToPage=table_contents.html';
}

function goToDisciplineHelp(disciplineTag, goToPage) {
    document.location = 'contentInfo!showContentCustom.action?course.id='+idCourse+"&grade.id="+idGrade+'&disciplineTag='+disciplineTag+'&goToPage='+goToPage;
}

function goToUnit(unitTag,goToPage) {
    document.location = 'contentInfo!showContentCustom.action?course.id='+idCourse+"&grade.id="+idGrade+'&discipline.id='+idDiscipline+'&unit.id='+idUnit+'&unitTag='+unitTag+'&goToPage='+goToPage;
}

function displayForum() {
	strWidth = screen.availWidth - 200;
	strHeight = screen.availHeight - 150;
	var showUrl = "forum!list.action?course.id="+idCourse;
	var home = new Ext.Window({
	       title: 'Forum',
	       width: strWidth,
	       height:strHeight,
	       x: 30,
	       y: 30,
	       minWidth: 30,
	       minHeight: 30,
	       collapsible:true,	     
	       autoScroll:true,
	       plain:true,
	       bodyStyle:'padding:0px;',
	       body: new Ext.ux.ManagedIframe({autoCreate:{src:showUrl, cls:'x-window-body'}})});
	home.show();
}

function translateWord(keyWord){
	width = screen.availWidth - 200;
	height = screen.availHeight - 150;
	var showUrl = "http://www.google.com/dictionary?aq=f&langpair=en|pt&q="+keyWord+"&hl=pt-BR";
	var home = new Ext.Window({
	       title: 'Translate Word',
	       width: width,
	       height:height,
	       x: 30,
	       y: 30,
	       minWidth: 30,
	       minHeight: 30,
	       collapsible:true,	     
	       autoScroll:true,
	       plain:true,
	       bodyStyle:'padding:0px;',
	       body: new Ext.ux.ManagedIframe({autoCreate:{src:showUrl, cls:'x-window-body'}})});
	home.show();
	return false;
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
    mailToWindow = window.open(getHtml('contentInfo!getTutorsEmail.action?grade.id='+idGrade+'&unitContent.id='+idUnitContent), 'mailToWindow');
    if (mailToWindow) mailToWindow.close();
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
    var host = address + 'RenderServlet?file=/'+idCourse+'/'+idDiscipline+'/'+idUnit+'/'+idUnitContent+'/';
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
        applet += host;
        applet += '" /> <param name="installerHost" value="';
        applet += address + "RenderServlet?file=julius/";        
        applet += '" /> </applet>';
        document.write(applet);
}
     
function includeGlobalCss(css) {
    document.write('<link href="RenderServlet?file=/'+idCourse+'/'+css+'" rel="stylesheet" type="text/css" />');
}

function showImage(image) {
    document.write('<img src="RenderServlet?file=/'+idCourse+'/'+idDiscipline+'/'+idUnit+'/'+idUnitContent+'/'+image+'">');
}

function labelDisciplineStatus(disciplineTag) {
    var status = getHtml('contentInfo!isDisciplineCompleted.action?grade.id='+idGrade+'&course.id='+idCourse+'&discipline.tag='+disciplineTag);
    var onClickFunc = "javascript:goToDiscipline('"+disciplineTag+"');";
    if ("0"==status) {
        document.write('<a href="'+onClickFunc+'"><img src="RenderServlet?file=/'+idCourse+'/images/modulo_selo_concluido.png"></a>');
    } else if ("-1"==status){
        document.write('<a href="'+onClickFunc+'"><img src="RenderServlet?file=/'+idCourse+'/images/modulo_selo_acesse.png"></a>');
    } else {
        document.write('<a href="'+onClickFunc+'"><img src="RenderServlet?file=/'+idCourse+'/images/modulo_selo_continuar.png"></a>');
    }
}

function labelUnitStatus(unitContentTag, goToPage) {
    var onClickFunc = "javascript:goToUnit('"+unitContentTag+"', '"+goToPage+"');";
    if ("true"==getHtml('contentInfo!isUnitCompleted.action?unitContent.tag='+unitContentTag)) {
        document.write('<a href="'+onClickFunc+'"><img src="RenderServlet?file=/'+idCourse+'/images/modulo_selo_concluido_menor.png"></a>');
    } else {
        if (("true"==getHtml('contentInfo!isUnitUnlocked.action?grade.id='+idGrade+'&unitContent.tag='+unitContentTag)) && (accessed == "")) {
            accessed = 'true';
            document.write('<a href="'+onClickFunc+'"><img src="RenderServlet?file=/'+idCourse+'/images/modulo_selo_acesse_menor.png"></a>');
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
	strWidth = screen.availWidth - 200;
	strHeight = screen.availHeight - 150;
	var showUrl = 'course!showChatStd.action?courseId='+idCourse+'&disciplineId='+idDiscipline;
	var home = new Ext.Window({
	       title: 'Chat',
	       width: strWidth,
	       height:strHeight,
	       x: 30,
	       y: 30,
	       minWidth: 30,
	       minHeight: 30,
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
var oldStyleBackground;
var retrieslLeft;

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
        return;
    }
    
    if (status == "fin") {
        //Maximum number of retries reached
        return;
    }
    
    if (status.indexOf("dep") != -1) {
        //alert(status.substring(4));
        return;
    }

    var i = 0;    
    var name = json.name;
    var fieldsHash = new Hash();

    for (i = 0; i < json.list.right.length; i++) {   
        var field = json.list.right[i];
        if (field.indexOf('|') > -1) {
           var sp = field.split('|'); 
           fieldsHash[sp[0]] = 't';           
        } else {
       fieldsHash[field] = 't';          
        }
    }

    for (i = 0; i < json.list.wrong.length; i++) {   
    var field = json.list.wrong[i];
        if (field.indexOf('|') > -1) {
           var sp = field.split('|'); 
           fieldsHash[sp[0]] = 'f';          
        } else {
       fieldsHash[field] = 'f';          
        }
    }

    var keys = fieldsHash.keys();
    for (i = 0; i < keys.length; i++) {   
       if (fieldsHash[keys[i]] == 't') {
        var field = json.list.right[i];
        var rightDiv = $(keys[i] + "_check");
        if (rightDiv == undefined) rightDiv = $(name + "_" + keys[i] + "_check");
          if (rightDiv != undefined) {
            if (!oldStyleBackground) {
               var backStyle = "";
               if (rightDiv.style.background) {
                 backStyle = rightDiv.style.background;
               } else if (rightDiv.currentStyle) {
                 backStyle += rightDiv.currentStyle.backgroundColor;
                 backStyle += ' ';
                 backStyle += rightDiv.currentStyle.backgroundImage;
                 backStyle += ' ';
                 backStyle += rightDiv.currentStyle.backgroundY;
                 backStyle += ' ';
                 backStyle += rightDiv.currentStyle.backgroundX;
                 backStyle += ' ';
                 backStyle += rightDiv.currentStyle.backgroundRepeat;
               }
               oldStyleBackground = backStyle;
            }
            rightDiv.style.background = rightAnswerStyle;    
          }
       } else {
        var errorDiv = $(keys[i] + "_check");
        if (errorDiv == undefined) errorDiv = $(name + "_" + keys[i] + "_check");
          if (errorDiv != undefined) {
            if (!oldStyleBackground) {
              var backStyle = "";
              if (errorDiv.style.background) {
                backStyle = errorDiv.style.background;
              } else if (errorDiv.currentStyle) {
                backStyle += errorDiv.currentStyle.backgroundColor;
                backStyle += ' ';
                backStyle += errorDiv.currentStyle.backgroundImage;
                backStyle += ' ';
                backStyle += errorDiv.currentStyle.backgroundY;
                backStyle += ' ';
                backStyle += errorDiv.currentStyle.backgroundX;
                backStyle += ' ';
                backStyle += errorDiv.currentStyle.backgroundRepeat;
              }
              oldStyleBackground = backStyle;
            }
            errorDiv.style.background = wrongAnswerStyle;
          }
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

    if (json.list.wrong.length > 0) {
      var errorPop = $('error_popup');
      if(errorPop) errorPop.style.visibility = 'visible';
    }

    if (json.count) {
      retriesLeft = json.count;
    }    
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
        return;
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

        var checkboxes = form.getInputs('checkbox', json.list.right[i]);
        if (checkboxes[0] == undefined) continue;
        if (checkboxes[0] != undefined) {
           var checkboxesValue = json.list.answers[i];        
           if (checkboxesValue.indexOf("[and]") != -1) {
                var splitValues = checkboxesValue.split("[and]");
                for (z = 0; z < splitValues.length; z++) {
                  var fieldName = json.list.right[i];
                  checkboxes = form.getInputs('checkbox', fieldName);
                  for (j = 0; j < checkboxes.length; j++) {
                    if (checkboxes[j].value == splitValues[z]) checkboxes[j].checked = 1
                  }
                }
                continue;
            }
           if (checkboxesValue.indexOf("[or]") != -1) {
                var splitValues = checkboxesValue.split("[or]");
                for (z = 0; z < splitValues.length; z++) {
                  checkboxes = form.getInputs('checkbox', fieldName);
                  var fieldName = json.list.right[i];
                  for (j = 0; j < checkboxes.length; j++) {
                    if (checkboxes[j].value == splitValues[z]) checkboxes[j].checked = 1
                  }
                }
                continue;
           }

           var checkboxesValue = json.list.answers[i];        

           for (j = 0; j < checkboxes.length; j++) {
               if (checkboxes[j].value == checkboxesValue) checkboxes[j].checked = 1
           }                                

        }
    }    
}

function redoExercise(form) {
    if (form == undefined) 
        form = $('exercise')
    
    var inputs = form.getInputs();    

    for (i = 0; i < inputs.length; i++) {
        var input = inputs[i];
        var div = document.getElementById(input.name + "_check");
        if (div == undefined) div = document.getElementById(name + "_" + input.name + "_check");
        if (div != undefined) {
           div.style.background = oldStyleBackground;
        }
        
        if (input.type == 'text') {
           input.value = '';
        } else if (input.type == 'radio') {
           input.checked = 0;
        } else if (input.type == 'checkbox') {
           input.checked = 0;
        }           
    }
}

function closePopUpError() {
     var errorPop = $('error_popup');
     if(errorPop) errorPop.style.visibility = 'hidden';
     retriesLeft = null;
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