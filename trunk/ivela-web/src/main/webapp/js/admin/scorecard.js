/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function showUser(){  
  
    var studentId;
    var students = getCheckedStudents();
    if(students.length >1){
        alert("Selecione apenas um estudante");
         
    }else if(students.length == 0){
        alert("Selecione um estudante");
    }
    else if(students.length ==1){
        //new Lightbox.base('box1');
        studentId = students[0];
        closeAllButKeepStudentChecked()
        var json = getJsonFromUrl('systemUser!getInfo.action?systemUser.id=' + studentId);
        $('showScorecard').style.display = 'block';
        $('show.scorecard.course').innerHTML= $('student.grade.course.name').innerHTML ;
        $('show.scorecard.grade').innerHTML = $('student.grade.name').innerHTML ;
        if (json != null && json != '') {
            $('show.scorecard.student').innerHTML = json.systemUser.username;
        }
    }
    return studentId;
}

function showScorecard(gradeId){  
    
    var students = getCheckedStudents();
    if(students.length >1){
        alert("Selecione apenas um estudante");
         
    }else if(students.length == 0){
        alert("Selecione um estudante");
    }
    else if(students.length ==1){
        var url = "scorecard!show.action";
        var params ="grade.id="+gradeId +"&student.id="+showUser();
        getJsonFromUrlPostLoad(url, params, showScorecardRe);
    }
   
}
function showScorecardRe(json){
    jsonUnitContent = json.responseText.evalJSON(true);
    
    var studentId;
    var students = getCheckedStudents();
    studentId = students[0];
            
    var html = '<input type="hidden" id="scorecard.student.id" name="course.grades" value="'+studentId+'" />';
    if(jsonUnitContent.unitContents !=''){
        for(i = 0 ; i < jsonUnitContent.unitContents.length; i++){
            var notResponseExercise = false;
            var notResponseExam = false;
            
            html += '<p style="font: bold 20px Arial, Helvetica, sans-serif; color:#ff7202; margin-top:20px;" class="titulo-exercises-result">'+jsonUnitContent.unitContents[i].name+'</p>';
                    
            html += '<table class="table-exercises-result" width="568" border="0" cellspacing="0" cellpadding="0">\n\
                                      <tr>\n\
                                         <td width="368" class="titulo">Exercícios</td>\n\
                                         <td width="100" class="titulo">Nota</td>\n\
                                         <td width="100" class="titulo">Nota Manual</td>\n\
                                      </tr>';
                  
                    
            if(jsonUnitContent.unitContents[i].exercises != ""){
                
                for(j=0;j<jsonUnitContent.unitContents[i].exercises.length;j++){
                    html += '<tr>';
                    if ( (jsonUnitContent.unitContents[i].exercises[j].score >= 0) && (jsonUnitContent.unitContents[i].exercises[j].hasSubjective == "true") )
                    {
                        html +='<td><a href="javascript:showCorrectQuestionsSubjectiveExercise(\''+jsonUnitContent.unitContents[i].exercises[j].id+'\', \''+studentId+'\',\''+$('student.grade.id').value+'\')">'+jsonUnitContent.unitContents[i].exercises[j].name+'</a></td>';
                    }
                    else
                    {
                        html +='<td>'+jsonUnitContent.unitContents[i].exercises[j].name+'</td>';
                    }
                      
                          
                    if(jsonUnitContent.unitContents[i].exercises[j].score < 0){
                        notResponseExercise = true;
                        html+='<td class="score">*</td>\n\
                                       <td class="manual-mark">*</td>';
                               
                    }
                    else {
                        var selectedManualScoreExercise = jsonUnitContent.unitContents[i].exercises[j].manualScore;
                        html+='<td class="score">'+Number(jsonUnitContent.unitContents[i].exercises[j].score).toFixed(1)+'</td>\n\
                                       <td class="manual-mark">\n\
                                        <select onchange="setManualScoreExercise(this.value,' +jsonUnitContent.unitContents[i].exercises[j].id+', '+studentId+','+$('student.grade.id').value+')" name="manualScore_'+ studentId + '_' + jsonUnitContent.unitContents[i].exercises[j].id + '">\n\
                                           <option value="0" ' + ( (selectedManualScoreExercise == "0.0") ? ("selected") : ("") ) + '>0.0</option>\n\
                                           <option value="1" ' + ( (selectedManualScoreExercise == "1.0") ? ("selected") : ("") ) + '>1.0</option>\n\
                                           <option value="2" ' + ( (selectedManualScoreExercise == "2.0") ? ("selected") : ("") ) + '>2.0</option>\n\
                                           <option value="3" ' + ( (selectedManualScoreExercise == "3.0") ? ("selected") : ("") ) + '>3.0</option>\n\
                                           <option value="4" ' + ( (selectedManualScoreExercise == "4.0") ? ("selected") : ("") ) + '>4.0</option>\n\
                                           <option value="5" ' + ( (selectedManualScoreExercise == "5.0") ? ("selected") : ("") ) + '>5.0</option>\n\
                                           <option value="6" ' + ( (selectedManualScoreExercise == "6.0") ? ("selected") : ("") ) + '>6.0</option>\n\
                                           <option value="7" ' + ( (selectedManualScoreExercise == "7.0") ? ("selected") : ("") ) + '>7.0</option>\n\
                                           <option value="8" ' + ( (selectedManualScoreExercise == "8.0") ? ("selected") : ("") ) + '>8.0</option>\n\
                                           <option value="9" ' + ( (selectedManualScoreExercise == "9.0") ? ("selected") : ("") ) + '>9.0</option>\n\
                                           <option value="10" ' + ( (selectedManualScoreExercise == "10.0") ? ("selected") : ("") ) + '>10.0</option>\n\
                                        </select>\n\
                                      </td>';
                    }
                    html += '</tr>';
                }
            }
            else html += '<td colspan="3">Não existe exercicios cadastrados para esta Aula</td>'; 
                    
            if(notResponseExercise){
                html+='<p style="font-size: 9px;"> (*) Exercício não resolvido</p>';
            }
            html += '</table>';
                    
                    
            html += '<table class="table-exams-result" width="568" border="0" cellspacing="0" cellpadding="0">\n\
                                  <tr>\n\
                                    <td width="368" class="titulo">Exames</td>\n\
                                    <td width="100" class="titulo">Nota</td>\n\
                                    <td width="100" class="titulo">Nota Manual</td>\n\
                                  </tr>';
            if(jsonUnitContent.unitContents[i].exams != ""){
                
                for(j=0;j<jsonUnitContent.unitContents[i].exams.length;j++){
                    html += '<tr>'
                    if ( (jsonUnitContent.unitContents[i].exams[j].score >= 0) && (jsonUnitContent.unitContents[i].exams[j].hasSubjective == "true") )
                    {
                        html +='<td><a href="javascript:showCorrectQuestionsSubjectiveExam(\''+jsonUnitContent.unitContents[i].exams[j].id+'\', \''+studentId+'\',\''+$('student.grade.id').value+'\')">'+jsonUnitContent.unitContents[i].exams[j].name+'</a></td>';
                    }
                    else
                    {
                        html +='<td>'+jsonUnitContent.unitContents[i].exams[j].name+'</td>';   
                    }
                      
                           
                    if(jsonUnitContent.unitContents[i].exams[j].score < 0){
                        notResponseExam = true;
                        html+='<td class="score">*</td>\n\
                                       <td class="manual-mark">*</td>';
                               
                    }
                    else {
                        var selectedManualScoreExam = jsonUnitContent.unitContents[i].exams[j].manualScore;
                        html+='<td class="score">'+Number(jsonUnitContent.unitContents[i].exams[j].score).toFixed(1)+'</td>\n\
                                   <td class="manual-mark">\n\
                                     <select onchange="setManualScoreExam(this.value,' +jsonUnitContent.unitContents[i].exams[j].id+', '+studentId+', '+$('student.grade.id').value+')" name="manualScore_'+ studentId + '_' + jsonUnitContent.unitContents[i].exams[j].id + '">\n\
                                       <option value="0" ' + ( (selectedManualScoreExam == "0.0") ? ("selected") : ("") ) + '>0.0</option>\n\
                                       <option value="1" ' + ( (selectedManualScoreExam == "1.0") ? ("selected") : ("") ) + '>1.0</option>\n\
                                       <option value="2" ' + ( (selectedManualScoreExam == "2.0") ? ("selected") : ("") ) + '>2.0</option>\n\
                                       <option value="3" ' + ( (selectedManualScoreExam == "3.0") ? ("selected") : ("") ) + '>3.0</option>\n\
                                       <option value="4" ' + ( (selectedManualScoreExam == "4.0") ? ("selected") : ("") ) + '>4.0</option>\n\
                                       <option value="5" ' + ( (selectedManualScoreExam == "5.0") ? ("selected") : ("") ) + '>5.0</option>\n\
                                       <option value="6" ' + ( (selectedManualScoreExam == "6.0") ? ("selected") : ("") ) + '>6.0</option>\n\
                                       <option value="7" ' + ( (selectedManualScoreExam == "7.0") ? ("selected") : ("") ) + '>7.0</option>\n\
                                       <option value="8" ' + ( (selectedManualScoreExam == "8.0") ? ("selected") : ("") ) + '>8.0</option>\n\
                                       <option value="9" ' + ( (selectedManualScoreExam == "9.0") ? ("selected") : ("") ) + '>9.0</option>\n\
                                       <option value="10" ' + ( (selectedManualScoreExam == "10.0") ? ("selected") : ("") ) + '>10.0</option>\n\
                                     </select>\n\
                                   </td>';
                    }
                    html += '</tr>';
                }
            }
            else html += '<tr><td colspan="3">Não existe exames cadastrados para esta Aula</td></tr>';
            
            if(notResponseExam){
                html+='<p style="font-size: 9px;"> (*) Exam não resolvido</p>';
            }
            html += '</table>';
            
            html += '<br><a href="javascript:showTranscript('+$('student.grade.id').value+')">Show Transcript</a>';
            
            
        }
        
        
    }
    else html += '<b>Não existem exercicios e exames cadastrados para esse curso</b>';
            
  
    $('show.scorecard.notes').innerHTML = html;
            
    Lightbox.hideAll();
}
            

function setManualScoreExam( manualScore, examId, studentId ,gradeId)

{
    
    var url = "scorecard!updateStudentexam.action";
    var params = "studentExam.manualScore="+manualScore +"&studentExam.exam.id="+examId +"&studentExam.student.id="+studentId+"&studentExam.grade.id="+gradeId;
    var jsonUnitContent = getJsonFromUrlPost(url, params);
    
    if ( ( jsonUnitContent != null ) && ( jsonUnitContent != '' ) )
    {
        alert('Nota manual alterada com sucesso');
    }
    else
    {
        alert('Não foi possível alterar a nota manual');
    }

}

function setManualScoreTranscript( manualScore, transcriptId)

{
    
    var url = "scorecard!updateTrascript.action";
    var params = "transcript.manualScore="+manualScore +"&transcript.id="+transcriptId;
    var jsonUnitContent = getJsonFromUrlPost(url, params);
    
    if ( ( jsonUnitContent != null ) && ( jsonUnitContent != '' ) )
    {
        alert('Nota manual alterada com sucesso');
    }
    else
    {
        alert('Não foi possível alterar a nota manual');
    }

}

function setManualScoreExercise( manualScore, exerciseId, studentId,gradeId )
{
    
    var url = "scorecard!updateStudentexercise.action";
    var params = "studentExercise.manualScore="+manualScore +"&studentExercise.exercise.id="+exerciseId +"&studentExercise.student.id="+studentId+"&studentExercise.grade.id="+gradeId;
    var jsonUnitContent = getJsonFromUrlPost(url, params);
    
    if ( ( jsonUnitContent != null ) && ( jsonUnitContent != '' ) )
    {
        alert('Nota manual alterada com sucesso');
    }
    else
    {
        alert('Não foi possível alterar a nota manual');
    }

}


function closeAllButKeepStudentChecked()
{
    var checks = document.getElementsByName('studentsCheck');
    var checkedElement = null;
    for ( var i = 0; i < checks.length; i++ )
    {
        if ( checks[i].checked )
        {
            checkedElement = checks[i];
            break;
        }
    }
    var students = checkedStudents;
    closeAll();
    checkedStudents = students;
    checkedElement.checked = true;    
    changeCheckboxStyle(checkedElement);
}


function showCorrectQuestionsSubjectiveExercise(idExercise, idUser,gradeId){
    
    closeAllButKeepStudentChecked();
    $('scoreCorrectHead').innerHTML = $('showScoreHead').innerHTML;
    $('showCorrectQuestions').style.display = "block";
    
    var url = "scorecard!showQuestionsSubjectivesExercise.action";
    var params ="exercise.id="+idExercise +"&student.id="+idUser+"&grade.id="+gradeId;
    var json = getJsonFromUrlPost(url, params);
    
    
    var html = '';
    
    for(i=0; i < json.list.asqse.length; i++){
        html += '<table class="table-question-subjective" width="568" border="0" cellspacing="0" cellpadding="0">';
        html += '<tr>\n\
                    <td width="100" valign="top" class="title">Question:</td>\n\
                    <td width="468" class="question-text">'+json.list.asqse[i].question+'</td>\n\
                 </tr>\n\
                 <tr>\n\
                    <td valign="top" class="question">Answer:</td>\n\
                    <td>'+json.list.asqse[i].answer+'</td>\n\
                 </tr>\n\
                 <tr>\n\
                    <td class="question">Nota:</td>\n\
                    <td><select id="corretExercise_'+json.list.asqse[i].id+'" onchange="updateScoreExercise('+json.list.asqse[i].id+', this.value)">\n\
                          <option value="null">Pendente</option>\n\
                          <option value="10.00">Certo</option>\n\
                          <option value="0.00">Errado</option>\n\
                        </select>\n\
                    </td>\n\
                 </tr>';
        html += '</table>';
    }
    
    $('show.correct.question').innerHTML = html;
    
    for(i=0; i < json.list.asqse.length; i++){
        $("corretExercise_"+json.list.asqse[i].id).value = json.list.asqse[i].score;
    }
}

function showCorrectQuestionsSubjectiveExam(idExam, idUser,gradeId){
    closeAllButKeepStudentChecked()
    $('scoreCorrectHead').innerHTML = $('showScoreHead').innerHTML;    
    $('showCorrectQuestions').style.display = "block";
    
    var url = "scorecard!showQuestionsSubjectivesExam.action";
    var params ="exam.id="+idExam +"&student.id="+idUser+"&grade.id="+gradeId;
    var json = getJsonFromUrlPost(url, params);
        
    var html = '';
    
    for(i=0; i < json.list.asqse.length; i++){
        html += '<table class="table-question-subjective" width="568" border="0" cellspacing="0" cellpadding="0">';
        html += '<tr>\n\
                    <td width="100" valign="top" class="title">Question:</td>\n\
                    <td width="468" class="question-text">'+json.list.asqse[i].question+'</td>\n\
                 </tr>\n\
                 <tr>\n\
                    <td valign="top" class="question">Answer:</td>\n\
                    <td>'+json.list.asqse[i].answer+'</td>\n\
                 </tr>\n\
                 <tr>\n\
                    <td class="question">Nota:</td>\n\
                    <td><select id="corretExam_'+json.list.asqse[i].id+'" onchange="updateScoreExam('+json.list.asqse[i].id+', this.value)">\n\
                          <option value="null">Pendente</option>\n\
                          <option value="10.00">Certo</option>\n\
                          <option value="0.00">Errado</option>\n\
                        </select>\n\
                    </td>\n\
                 </tr>';
        html += '</table>';
    }
    
    $('show.correct.question').innerHTML = html;
    
    for(i=0; i < json.list.asqse.length; i++){
        $("corretExam_"+json.list.asqse[i].id).value = json.list.asqse[i].score;
    }
}


function isArray(o){
    return(typeof(o.length)=="undefined")?false:true;
}

function updateScoreExercise(id, score){
    
    var url = "scorecard!updateScoreSubjectiveQuestionExercise.action"
    var params = "answerSubjectiveQuestionStudentExercise.id="+id+"&score="+score;
    
    var json = getJsonFromUrlPost(url, params);
    
    if(!json.update){
        alert('Não foi possivel atualizar a nota!')
    }
    
}

function updateScoreExam(id, score){
    
    var url = "scorecard!updateScoreSubjectiveQuestionExam.action"
    var params = "answerSubjectiveQuestionStudentExam.id="+id+"&score="+score;
    
    var json = getJsonFromUrlPost(url, params);
    
    if(!json.update){
        alert('Não foi possivel atualizar a nota!')
    }
}

function showReport(gradeId,courseId){
    var studentId;
    var students = getCheckedStudents();
    
    if(students.length == 0){
        alert("Select at least one student");    
    }
    else{
        
        if(students.length ==1){
            studentId = students[0];
            var url = "systemUser!getReport.action";
            var params ="studentId=" + studentId+"&courseId="+ courseId +"&gradeId="+gradeId;
            
            getJsonFromUrlPostLoad(url, params, showReportRe);
                        
        }else{
            
            ids="";
            
            for(i=0;i<students.length;i++){
                ids+="&userIds="+students[i];
            }
            
            var url = "systemUser!getReportForManyTeste.action";
            var params ="courseId="+ courseId +"&gradeId="+gradeId+ids;
            
            getJsonFromUrlPostLoad(url, params, showReportRe);
           
        }
    }
}

function showReportRe(json){
    
    $('showStudent').style.display = "block";
    
    jsonMany = json.responseText.evalJSON(true);
    var p = new pie();
    $('pieCanvas').innerHTML="";
    html = "";
    
    if(jsonMany.count != undefined){
        
        p.add("Cursado",jsonMany.count);
        p.add("Não Cursado",10 - jsonMany.count);
            
        colorArray = new Array("#ff7202","#999999");

        html+="<table><tr><td>Progress in this course</td></tr>";
        html+="<tr><td>";
        html+= jsonMany.count*10+"%";
        html+="</td></tr></table>";
            
    }else{
        html+="<table><tr><td>Progress in this course</td></tr></table>";
        for(i=0; i < jsonMany.map.entry.length; i++){
            p.add("<b>"+jsonMany.map.entry[i].qtd+"</b> Alunos com "+jsonMany.map.entry[i].percent*10+"%<br /> do curso concluído",jsonMany.map.entry[i].qtd);
        }
            
        colorArray = new Array("#ff0400","#007fff","#37ae47","#fffd00","#ff7202");
    }
    
    $('reportData').innerHTML = html;
    p.render("pieCanvas", "Pie Graph", colorArray);
    $('pieCanvas').style.display = "block";
    Lightbox.hideAll();
    
}


/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function showTranscript(gradeId){  
  
    var studentId;
    var students = getCheckedStudents();
    if(students.length >1){
        alert("Selecione apenas um estudante");
        
    }
    else{ 
        if(students.length ==1){
            new Lightbox.base('box1');
            studentId = students[0];
            closeAllButKeepStudentChecked();
            var json = getJsonFromUrl('systemUser!getInfo.action?systemUser.id=' + studentId);
            $('showTranscript').style.display = 'block';
            $('show.transcript.course').innerHTML= $('student.grade.course.name').innerHTML ;
            $('show.transcript.grade').innerHTML = $('student.grade.name').innerHTML ;
            if (json != null && json != '') {
                $('show.transcript.student').innerHTML = json.systemUser.username;
            }
    
            var url = "scorecard!showTranscript.action";
            var params ="grade.id="+gradeId+"&student.id="+studentId;
            var jsonUnitContent = getJsonFromUrlPost(url, params);
            
            var html = '<input type="hidden" id="scorecard.student.id" name="course.grades" value="'+studentId+'" />';
            
            if(jsonUnitContent.transcript!=''){
                html += '<p style="font: bold 20px Arial, Helvetica, sans-serif; color:#ff7202; margin-top:20px;" class="titulo-exercises-result"></p>';
                    
                html += '<table class="table-exercises-result" width="500" border="0" cellspacing="0" cellpadding="0">\n\
                                      <tr>\n\
                                         <td width="100" class="titulo">Average Exam</td>\n\
                                         <td width="100" class="titulo">Average Exercise</td>\n\
                                         <td width="100" class="titulo">Average Final</td>\n\
                                         <td width="100" class="titulo">Manual Score</td>\n\
                                         <td width="100" class="titulo">Status</td>\n\
                                    </tr>';
                  
                    
                html += '<tr>';
                html += '<td width="368">';
                if(jsonUnitContent.transcript.averageExam<0){
                    html += '*';
                }
                else{
                    html += jsonUnitContent.transcript.averageExam;
                }
                html += '</td>';
                html += '<td width="100">';
                if(jsonUnitContent.transcript.averageExercise<0){
                    html += '*';
                }
                else{
                    html += jsonUnitContent.transcript.averageExercise;
                }
                html += '</td>';
                html += '<td width="100">'; 
                if(jsonUnitContent.transcript.score<0){
                    html += '*';
                }
                else{
                    html += jsonUnitContent.transcript.score;
                }    
                html += '</td>';
                selectedManualScore = jsonUnitContent.transcript.manualScore;
                html += '<td class="manual-mark" >\n\
                                        <select onchange="setManualScoreTranscript(this.value,'+jsonUnitContent.transcript.id+')" name="manualScore_'+ jsonUnitContent.transcript.id +'">\n\
                                           <option value="0" ' + ( (selectedManualScore == "0.0") ? ("selected") : ("") ) + '>0.0</option>\n\
                                           <option value="1" ' + ( (selectedManualScore == "1.0") ? ("selected") : ("") ) + '>1.0</option>\n\
                                           <option value="2" ' + ( (selectedManualScore == "2.0") ? ("selected") : ("") ) + '>2.0</option>\n\
                                           <option value="3" ' + ( (selectedManualScore == "3.0") ? ("selected") : ("") ) + '>3.0</option>\n\
                                           <option value="4" ' + ( (selectedManualScore == "4.0") ? ("selected") : ("") ) + '>4.0</option>\n\
                                           <option value="5" ' + ( (selectedManualScore == "5.0") ? ("selected") : ("") ) + '>5.0</option>\n\
                                           <option value="6" ' + ( (selectedManualScore == "6.0") ? ("selected") : ("") ) + '>6.0</option>\n\
                                           <option value="7" ' + ( (selectedManualScore == "7.0") ? ("selected") : ("") ) + '>7.0</option>\n\
                                           <option value="8" ' + ( (selectedManualScore == "8.0") ? ("selected") : ("") ) + '>8.0</option>\n\
                                           <option value="9" ' + ( (selectedManualScore == "9.0") ? ("selected") : ("") ) + '>9.0</option>\n\
                                           <option value="10" ' + ( (selectedManualScore == "10.0") ? ("selected") : ("") ) + '>10.0</option>\n\
                                        </select>\n\
                                   </td>';
                switch(jsonUnitContent.transcript.status){
                    case '1':
                        status = '';
                        break;
                    case '3':
                        status = 'in progress';
                        break;
                }
                html +='<td width="100">'+status+'</td>\n\
                                    </tr>';
                   
                html += '</table>';
            
            }
        
        
        }
        html +='<br><a href="javascript:showScorecard('+gradeId+')">See details scoreCard</a>';
        $('show.transcript.notes').innerHTML = html;
            
        Lightbox.hideAll();
    }
    
}