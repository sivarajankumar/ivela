/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var fileExtOk = true;

function validateFile(fileName){
    if(fileName != ""){
           var ext = fileName.split(".",2);
           if(ext[1]!="ogg"){
               alert("Tipo de arquivo inválido!!!");
               fileExtOk = false;
               $('id').focus();
           }
           else {
               fileExtOk = true;
     }
    }else {
               fileExtOk = true;
     }
           
}

function showQuestion(unitContentId) {
    closeAll();
    $('showEntryQuestion').style.display = 'block';

    var i = 0;
            
    //var jsonUnit = getJsonFromUrl('exam!getQuestionUnitContentJson.action?unitContent.id=' + unitContentId);
    //    var jsonUnitsContent = getJsonFromUrl('unit!getUnitsContentInfo.action?unit.id=' + unitId);
    //            
    //    var name = jsonUnit.unit.name;
    //    var unitsContent = '';
    //
    //    /* Tratamento da lista de unidades */
    //    if (jsonUnitsContent.list != '') {
    //        unitsContent += '<ul>';
    //        if (jsonUnitsContent.list.unitContent.length == null) {
    //            var temp = jsonUnitsContent.list.unitContent.title;
    //            unitsContent = '<li><input type="checkbox" />' + temp + '</li>';
    //        }
    //        else {
    //            for (i = 0; i < jsonUnitsContent.list.unitContent.length; i++) {
    //                unitsContent += '<li><input type="checkbox" />' + jsonUnitsContent.list.unitContent[i].title + '</li>';
    //            }
    //        }
    //        unitsContent += '</ul>';
    //    }
    //    $('unit.id').value = jsonUnit.unit.id;
    //    $('unit.name').innerHTML = name;
    //    $('unit.unitsContent').innerHTML = unitsContent;
}

//function cleanFields(){
//    $('textQuestion').value = '';
//    $('question.description').value = '';
//    var arrayInput = $('form_question').getElementsByTagName('input');
//    for (i = 0; i< arrayInput.length; i++){
//        if(arrayInput[i].getAttribute('type')=='text' && arrayInput[i].getAttribute('name') == 'answerOption'){
//            arrayInput[i].value='';
//        }       
//        if(arrayInput[i].getAttribute('type')=='text' && arrayInput[i].getAttribute('name') == 'sentence'){
//            arrayInput[i].value='';
//        }
//        
//    }
// $('formQuestionAddFile').reset();
// $('formFileQuestionAuditive').reset();
//
//}

function showEntryQuestion() {
    closeAll();    
    $('formUpload').reset();
    $('showEntryQuestion').style.display = 'block';
    $('questionSubjective').style.display = 'block';
    $('question_question_type').value = '1';
    
    //limpar spans com erros de validação
    clearAllHints($('form_question'));
    pseudoVirgin($('form_exercise_entry'));
    //    for(i = 0; i < $('form_question').getElementsByTagName('span').length; i++){
    //        $('form_question').getElementsByTagName('span')[i].style.display = "none";
    //    }
        
    // $('input.unitContent.id').value = unitContentId;
    //  $('input.unitContent.unit.id').value = '';
    //  $('input.unitContent.title').value = '';
    
    
    var jsonQuestions = '';
    var html = '';
    
    //show question by exam
    if($('input.exam.id').value!=''){
        jsonQuestions = getJsonFromUrl('question!questionsByExam.action?exam.id=' +$('input.exam.id').value);
    }
    
    if($('input.exercise.id').value!=''){
        jsonQuestions = getJsonFromUrl('question!questionsByExercises.action?exercise.id=' +$('input.exercise.id').value);
    }
    
    
    
    if(jsonQuestions.list != ''){
        
        html = questoesCriadas+'<ol>'
        
        if(isArray(jsonQuestions.list.questionArray)){
            for(i=0; i<jsonQuestions.list.questionArray.length;i++){
                html += '<li><span class="titulo-questão">'+jsonQuestions.list.questionArray[i].question+'</span></li>'
            }
        }
        else{
            html += '<li><span class="titulo-questão">'+jsonQuestions.list.questionArray.question+'</span></li>'
        }
        
        html += '</ol>'
    }
    
    $('showEntryQuestion.questions').innerHTML = html;
            
    $('question_question_type').focus();
}


//
//function submitQuestion(QuestionId) {
//    
//    var finish = false;
//    var correctAnswer;
//    var tense;
//    var required;
//    var arrayAnswerOption = '';
//    var arraySentence = '';
//    var arrayUpload = '';
//    var arrayKey = '';
//    var arrayValue = '';
//    var uploadFile = '';
//    var tipoAtividade ='';
//    var qType = $('question_question_type').value;
//    if(QuestionId.substring(0,5) == "final"){
//        finish = true;
//        QuestionId = QuestionId.substring(5);
//    }
//    
//    for(i = 0; i < $('form_question').getElementsByTagName('span').length; i++){
//        $('form_question').getElementsByTagName('span')[i].style.display = "none";
//    }
//    
//    if(true){
//        
//        var arrayInput = $('form_question').getElementsByTagName('input');
//        for (i = 0; i< arrayInput.length; i++){
//            if(arrayInput[i].value!=''){
//                if(arrayInput[i].getAttribute('type') == 'radio' && arrayInput[i].getAttribute('name') == 'radioQuestObj' && arrayInput[i].checked){
//                    correctAnswer = arrayInput[i].value;
//                }
//        
//                if(arrayInput[i].getAttribute('type') == 'radio' && arrayInput[i].getAttribute('name') == 'radioTense' && arrayInput[i].checked){
//                    tense = arrayInput[i].value;
//                }
//            
//                if(arrayInput[i].getAttribute('type') == 'radio' && arrayInput[i].getAttribute('name') == 'question.required' && arrayInput[i].checked){
//                    required = arrayInput[i].value;
//                }
//                if(arrayInput[i].getAttribute('type')=='text' && arrayInput[i].getAttribute('name') == 'answerOption'){
//                    arrayAnswerOption += '&answerOption='+arrayInput[i].value;
//                }       
//                if(arrayInput[i].getAttribute('type')=='text' && arrayInput[i].getAttribute('name') == 'sentence'){
//                    arraySentence  += '&sentence='+arrayInput[i].value;
//                }
//        
//                if(arrayInput[i].getAttribute('type')=='file' && arrayInput[i].getAttribute('name') == 'upload'){
//                    arrayUpload += '&upload='+arrayInput[i].value;
//                }
//                
//                //if(arrayInput[i].getAttribute('type')=='file' && arrayInput[i].getAttribute('name') == 'uploadFile'){
//                //     uploadFile += '&uploadFileQuestion='+arrayInput[i].value+'&questionText.audio='+arrayInput[i].value ;
//                //   }
//                
//                //params gambi
//                if(arrayInput[i].getAttribute('type')=='text' && arrayInput[i].getAttribute('name') == 'paramKey'){
//                    arrayKey  += '&paramKey='+arrayInput[i].value;
//                }
//                if(arrayInput[i].getAttribute('type')=='text' && arrayInput[i].getAttribute('name') == 'paramValue'){
//                    arrayValue  += '&paramValue='+arrayInput[i].value;
//                }
//            
//            }
//
//        }
//    
//
//
//    
//        var url = '';
//        var params = '';
//    
//    
//    
//        if (QuestionId != null && QuestionId.length > 0) {
//            url += 'question!update.action?';
//            url += 'question.id=' + QuestionId;
//            //    url += '&question.title=' + $('input.exam.title').value;
//            //   url += '&question.description=' + $('input.exam.description').value;
//        }
//        else {
//            url += 'question!add.action';
//            params += 'question.question=' + replaceAll($('question.description').value);
//            params += '&question.type=' + $('question_question_type').value;
//            params += '&radio=' + correctAnswer;
//            params += arrayAnswerOption;
//            params += '&radioTense='+tense;
//            params += arraySentence;
//            params += arrayUpload;
//            params +="&auditiveQuestion.chanceSentence="+$('question.input.chanceSentence').value;
//            
//            if($('textQuestion').value!=''){
//                params += '&questionText.text=' + replaceAll($('textQuestion').value);
//            }
//            // if(uploadFile != ''){
//            //     params += uploadFile;
//            // }
//            
//            if(qType==4){
//                params += "&urlBinary="+$('urlBinary').value;
//                params += "&urlResult="+$('urlResult').value;
//                params += "&binType="+$('binType').value;
//                params += arrayKey;
//                params += arrayValue;
//            }
//
//        }
//    
//        var jsonQuestion = getJsonFromUrlPost(url,params);
//
//
//        if (QuestionId != null && QuestionId.length > 0) {
//            if (jsonQuestion != null && jsonQuestion.exam != null && jsonQuestion.exam.id != '') {
//                //showCourse(jsonCourse.course.id);
//                document.location = 'course!show.action';
//            }
//            else {
//                alert('Não alterado');
//            }
//        }
//        else {
//            if (jsonQuestion != null && jsonQuestion.question != null && jsonQuestion.question.id != '') {
//              
//                
//                params='';
//                url='';
//                //add question for exam
//                //alert(document.getElementById('input.exam.id').value);
//                if(document.getElementById('input.exam.id').value!=''){
//                    $('questionUp').contentDocument.forms[0].elements[4].value = "exam";
//                    $('questionUp').contentDocument.forms[0].elements[6].value = document.getElementById('input.exam.id').value;
//                    url += 'question!addQuestionExam.action';
//                    params += "questionExam.weight="+$('question.weight').value;
//                    params += "&questionExam.exam.id="+$('input.exam.id').value;
//                    params += "&questionExam.question.id="+jsonQuestion.question.id;
//                    params += "&questionExam.required="+required;
//                    getJsonFromUrlPost(url,params);
//                
//                }
//            
//                url='';
//                params = '';
//               // alert(document.getElementById('input.exercise.id').value);
//                //add question for exercise
//                if(document.getElementById('input.exercise.id').value!=''){
//                    $('questionUp').contentDocument.forms[0].elements[4].value = "exercise";
//                    $('questionUp').contentDocument.forms[0].elements[5].value = document.getElementById('input.exercise.id').value;
//                    url += 'question!addQuestionExercise.action';
//                    params += "questionExercise.weight="+$('question.weight').value;
//                    params += "&questionExercise.exercise.id="+$('input.exercise.id').value;
//                    params += "&questionExercise.question.id="+jsonQuestion.question.id;
//                    params += "&questionExercise.required="+required;
//                    getJsonFromUrlPost(url,params);
//                
//                }
//                
//               $('questionUp').contentDocument.forms[0].elements[0].value =  replaceAll($('textQuestion').value);
//               $('questionUp').contentDocument.forms[0].elements[1].value = jsonQuestion.question.id;
//               $('questionUp').contentDocument.forms[0].elements[2].value = $('unit.id').value ;
//               $('questionUp').contentDocument.forms[0].elements[3].value = $('unitContent.id').value;
//               //submit form
//               $('questionUp').contentDocument.forms[0].submit();
//            
//            
//                if(finish){
//                    //perguntar para a manu como é que isso funciona!
//                    //alert($('unitContent.id').value);
//                    //showManagerExercises($('unitContent.id').value);
//                    
//                    if($('input.exercise.id').value!=''){
//                        showExercise($('input.exercise.id').value,true);
//                    }
//                    if($('input.exam.id').value!=''){
//                        showExam($('input.exam.id').value,true);
//                    }
//                    
//                }
//                else{
//                 
//                    //add question
//                    showEntryQuestion();
//                }
//          
//            }
//            else {
//                alert('Não inserido');
//        
//            }
//        }
//    }
//
//}

// código para mostrar mensagem de envio de arquivo
var ie = document.all;
var moz = document.getElementById && !document.all; 
var intr;

function Message_UpdatePos(msg, dy) {
    var el = document.getElementById(msg);
    if (ie) {
        el.style.pixelTop = document.body.scrollTop + dy;
    }
    else if (moz) {
        el.style.top = window.pageYOffset + dy + 'px';
    }
}

function Message_Display(msg, vis, dx, dy) {
    var el = document.getElementById(msg);

    // Position Message

    if (ie) {
        el.style.pixelTop = document.body.scrollTop + dy;
        el.style.pixelLeft = document.body.clientWidth - dx;
    }
    else if (moz) {
        el.style.top = window.pageYOffset + dy + 'px';
        el.style.left = window.innerWidth - dx + 'px';
    }
    if (vis) {  // and display it
        el.style.visibility = "visible";
        intr = setInterval("Message_UpdatePos('" + msg + "', " + dy + ")", 1);
    }
    else {  // or hide it
        el.style.visibility = "hidden";
        if (intr)
            clearInterval(intr);
    }
}


function questionUpSubmit(finish)
{
    if(validationHints($('form_question')) && fileExtOk){
        if($('question_question_type').value == 2){
            inputs = $('questionObjective').getElementsByTagName('input');
            for(i = 0; i < inputs.length; i++){
                if(inputs[i].getAttribute('type') == 'text' && inputs[i].getAttribute('name') == 'answerOption' && inputs[i].value == ''){
                    alert("opção em branco");
                    inputs[i].focus();
                    return false;
                }
            }
        }
        setHiddenValues();
        $('formUpload').submit();
        //Message_Display('testmsg', 1, 700, 50);
        new Lightbox.base('box1');
        if(finish){
            $('questionUp').onload = showFinish;
        }
        else {
            $('questionUp').onload = questionUpOk;
        }
        
    }
    else {
        if(!fileExtOk){
            alert('Arquivo Inválido');
            $('id').focus();
        }
    }
}

function setHiddenValues()
{
    $('hidden.unitContent.id').value = $('unitContent.id').value;
    $('hidden.unit.id').value = $('unit.id').value;
    if(document.getElementById('input.exercise.id').value!=''){
        $('hidden.atividade').value = 'exercise';
        $('hidden.exercise.id').value = $('input.exercise.id').value;
        $('hidden.exam.id').value = '';
        
    }
    else if(document.getElementById('input.exam.id').value!=''){
        $('hidden.atividade').value = 'exam';  
        $('hidden.exam.id').value = $('input.exam.id').value;
        $('hidden.exercise.id').value = '';
   }
}

function displayMessageQuestion(){
    if($('questionUp').contentDocument.forms[0].elements[0].value=="yes"){
        alert("Questão salva com sucesso!!!");
    }
    else if($('questionUp').contentDocument.forms[0].elements[0].value=="no"){
        alert("A questão não pode ser salva, o arquivo não é de uma extensão válida ou seu tamanho é superior ao permitido!");
    }
}

function showFinish(){
    //Message_Display('testmsg', 0, 700, 50);
    Lightbox.hideAll();
    displayMessageQuestion();
    if($('input.exercise.id').value!=''){
        showExercise($('input.exercise.id').value,true);
    }
    if($('input.exam.id').value!=''){
        showExam($('input.exam.id').value,true);
    }
}

function questionUpOk()
{
    //Message_Display('testmsg', 0, 700, 50);
    
    Lightbox.hideAll();
    displayMessageQuestion();
    showEntryQuestion();
}

function showQuestion(idQuestion){
    var url = '';
    var params='';
    var jsonQuestion = '';
    url += 'question!show.action';
    params += "question.id="+idQuestion;
    
    if($('exercise.id').value!=''){
        params += "&exercise.id="+$('exercise.id').value;
    }
    else if($('exam.id').value!=''){
        params += "&exam.id="+$('exam.id').value;
    }
    jsonQuestion = getJsonFromUrlPost(url,params);
    
    //alert(jsonQuestion);
    //
    closeAll();
    $('showQuestionSubjective').style.display = 'block';
    $('showQuestion').style.display = 'block';
    $('show.question.id').value = jsonQuestion.question.id;
    $('show.question.weight').innerHTML = jsonQuestion.question.weight;
    $('show.question.required').innerHTML = jsonQuestion.question.required;
    $('show.question.description').innerHTML = jsonQuestion.question.description.replace(/IVELA/g,"<br>");
    $('typeUnitContent').value = jsonQuestion.question.typeUnitContent;
    if(jsonQuestion.question.questionText != ''){
        var text = questionText+'<p id="show.question.text">'+
            (jsonQuestion.question.questionText).replace(/IVELA/g,"<br>")+'</p>';
        $('showQuestionText').innerHTML = text;        
    }
    if(jsonQuestion.question.type==1){
        $('show.question.type').innerHTML = 'Subjective';
    }
    if(jsonQuestion.question.type==2){
        $('show.question.type').innerHTML = 'Objective';
    }
}

    
function removeQuestion(idQuestion){
        
    url = "question!updateQuestion.action?question.id="+idQuestion;
    if($('exercise.id').value!=''){
        url += "&exercise.id="+$('exercise.id').value;
    }
    else if($('exam.id').value!=''){
        url += "&exam.id="+$('exam.id').value;
    }
        
    jsonQuestion = getJsonFromUrl(url);
        
    if(jsonQuestion.update){
        if($('exercise.id').value!=''){
            showExercise($('exercise.id').value,false);
        }
        else if($('exam.id').value!=''){
            showExam($('exam.id').value,false);
        }
    }
    else
        alert("Não foi possivel apagar");
        
}

function validationQuestion(type){

    //limpar spans com erros de validação
    //    for(i = 0; i < $('form_question').getElementsByTagName('span').length; i++){
    //        $('form_question').getElementsByTagName('span')[i].style.display = "none";
    //    }
    
    if($('question.description').value == ''){
        $('errorQuestionDescription').style.display = "block";
        $('question.description').focus();
        return false;
        
    }else if(type == 2){
        inputs = $('form_question').getElementsByTagName('input');
        for(i = 0; i < inputs.length; i++){
            if(inputs[i].getAttribute('type') == 'text' && inputs[i].getAttribute('name') == 'answerOption' && inputs[i].value == ''){
                //$('errorQuestionText').style.display = "block";
                alert("opção em branco");
                inputs[i].focus();
                return false;
            }
            else
                return true;
        }
    }
    else if(type == 3){
        inputs = $('form_question').getElementsByTagName('input');
        for(i = 0; i < inputs.length; i++){
            var ret = false;
            if(inputs[i].getAttribute('type') =='text' && inputs[i].getAttribute('name') == 'sentence' && inputs[i].value == ''){
                alert("sentença vazia");
                inputs[i].focus();
                ret = false;
            }
            else{
                ret = true;
            }
        
            if(inputs[i].getAttribute('type') =='file' && inputs[i].getAttribute('name') == 'upload' && inputs[i].value == ''){
                alert("file vazio");
                inputs[i].focus();
                ret = false;
            }
            else{
                ret = true;
            }
            return ret;
        }
    }else if(type == 4){
        inputs = $('form_question').getElementsByTagName('input'); 
        
        for(i = 0; i < inputs.length; i++){
            if(inputs[i].getAttribute('type') =='text' && inputs[i].getAttribute('name') == 'urlBinary' && inputs[i].value == ''){
                alert("url binary vazia");
                inputs[i].focus();
                return false;
            }
        
            if(inputs[i].getAttribute('type') =='text' && inputs[i].getAttribute('name') == 'urlResult' && inputs[i].value == ''){
                alert("url result vazia");
                inputs[i].focus();
                return false;
            }
                
            if(inputs[i].getAttribute('type') =='text' && inputs[i].getAttribute('name') == 'paramKey' && inputs[i].value == ''){
                alert("key vazia");
                inputs[i].focus();
                return false      
            }
             
        
            if(inputs[i].getAttribute('type') =='text' && inputs[i].getAttribute('name') == 'paramValue' && inputs[i].value == ''){
                alert("value vazio");
                inputs[i].focus();
                return false
            } 
        }
        
        return true;
    } 
    else{
        return true;
    }

}

function showTypeUnitContent(type){
    if(type == 0){
        showExercise($('exercise.id').value,false);
    }
    else if(type == 1){
        showExam($('exam.id').value,false);
    }
        
    
}

function startCallback() {
    // make something useful before submit (onStart)

    return true;
}

function completeCallback(response) {
    return true
}


function insertOptions(){
                
    var nextId = Math.round(($('questionObjective').getElementsByTagName('input').length-1)/2);
    lastTr = $('questionObjective').getElementsByTagName('tr')[$('questionObjective').getElementsByTagName('tr').length - 1];
                
    $('questionObjective').getElementsByTagName('tbody')[0].removeChild(lastTr);
                
    tr = document.createElement('tr');
    td1 = document.createElement('td');
    td2 = document.createElement('td');
    td3 = document.createElement('td');
    
    inputRadio = document.createElement('input');
    inputText = document.createElement('input');
    span = document.createElement('span');
    remove = document.createTextNode(' X');
    
                
    inputRadio.setAttribute("id", "question_radio"+nextId);
    inputRadio.setAttribute("type", "radio");
    inputRadio.setAttribute("name", "radioQuestObj");
    inputRadio.setAttribute(classCss(), "input_radio");
    inputRadio.setAttribute("value", nextId);
    span.setAttribute("onclick", "removeRow(this)");
                
    inputText.setAttribute("id", "answerOption");
    inputText.setAttribute("name", "answerOption");
    inputText.setAttribute("type", "text");
    td1.appendChild(inputRadio);
    td2.appendChild(inputText);
    span.appendChild(remove);
    td3.appendChild(span);
                
    tr.appendChild(td1);
    tr.appendChild(td2);
    tr.appendChild(td3);
                
    $('questionObjective').getElementsByTagName('tbody')[0].appendChild(tr);
    $('questionObjective').getElementsByTagName('tbody')[0].appendChild(lastTr);
                
    //$('tableQuestion').innerHTML += '<tr><td><input id="question_radio'+nextId+'" type="radio" value="'+nextId+'" name="radio"/></td><td><s:textfield name="answerOption" theme="simple"/></td></tr>';
                
}
function removeRow(elem){
    elem.parentNode.parentNode.parentNode.removeChild(elem.parentNode.parentNode);
}

function selectTypeQuestion(typeQuestion){
    //remove child
    removeChild();
                
    //list="#{'1':'Subjective','2':'Objective','3':'Auditive', '4':'External'}
    switch(typeQuestion){
        case "1":
            $('questionObjective').style.display = "none";
            $('questionSubjective').style.display = "block";
            $('questionAuditive').style.display = "none";
            $('questionExternal').style.display = "none";
            break;
        case "2":
            $('questionObjective').style.display = "block";
            $('questionSubjective').style.display = "block";
            $('questionAuditive').style.display = "none";
            $('questionExternal').style.display = "none";
            break;
        case "3":
            $('questionObjective').style.display = "none";
            $('questionSubjective').style.display = "block";
            $('questionAuditive').style.display = "block";
            $('questionExternal').style.display = "none";
            break;
        case "4":
                    
            $('questionObjective').style.display = "none";
            $('questionSubjective').style.display = "block";
            $('questionAuditive').style.display = "none";
            $('questionExternal').style.display = "block";
            break
                        
    }
                
}

function removeChild(){
    var arrayTr = $('questionObjective').getElementsByTagName('tr'); 
    for (i= (arrayTr.length-2);i>3; i--){
        $('questionObjective').getElementsByTagName('tbody')[0].removeChild(arrayTr[i]);
    }
    var arrayTrAuditive = $('questionAuditive').getElementsByTagName('tr');
    for (i= (arrayTrAuditive.length-2);i>1; i--){
        $('questionAuditive').getElementsByTagName('tbody')[0].removeChild(arrayTrAuditive[i]);
    }
}

function insertFile(){
                
    lastTr = $('questionAuditive').getElementsByTagName('tr')[$('questionAuditive').getElementsByTagName('tr').length - 1];
                
    $('questionAuditive').getElementsByTagName('tbody')[0].removeChild(lastTr);
                
    tr = document.createElement('tr');
    td1 = document.createElement('td');
    td2 = document.createElement('td');
    inputText = document.createElement('input');
    inputFile = document.createElement('input');
                
    inputText.setAttribute("id", "question_sentence");
    inputText.setAttribute("type", "text");
    inputText.setAttribute("name", "sentence");
                
    inputFile.setAttribute("id", "question_upload");
    inputFile.setAttribute("name", "upload");
    inputFile.setAttribute("type", "file");
                
    td2.appendChild(inputFile);
    td1.appendChild(inputText);
                
    tr.appendChild(td1);
    tr.appendChild(td2);
                
    $('questionAuditive').getElementsByTagName('tbody')[0].appendChild(tr);
    $('questionAuditive').getElementsByTagName('tbody')[0].appendChild(lastTr);
                
    //$('tableQuestionAuditive').innerHTML += '<tr><td><s:textfield name="sentence" theme="simple"/></td><td><s:file name="upload" theme="simple"/></td></tr>';
                
}

function insertParam(){
                
    lastTr = $('questionExternal').getElementsByTagName('tr')[$('questionExternal').getElementsByTagName('tr').length - 1];
                
    $('questionExternal').getElementsByTagName('tbody')[0].removeChild(lastTr);
                
    tr = document.createElement('tr');
    td1 = document.createElement('td');
    td2 = document.createElement('td');
            
    inputTextKey = document.createElement('input');
    inputTextValue = document.createElement('input');
                
    inputTextKey.setAttribute("id", "question_paramKey");
    inputTextKey.setAttribute("type", "text");
    inputTextKey.setAttribute("name", "paramKey");
                
    inputTextValue.setAttribute("id", "question_paramValue");
    inputTextValue.setAttribute("type", "text");
    inputTextValue.setAttribute("name", "paramValue"); 
                
            
    td1.appendChild(inputTextKey);
    td2.appendChild(inputTextValue);
            
    tr.appendChild(td1);
    tr.appendChild(td2);
                
    $('questionExternal').getElementsByTagName('tbody')[0].appendChild(tr);
    $('questionExternal').getElementsByTagName('tbody')[0].appendChild(lastTr);
    
}   