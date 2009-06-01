/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function showManagerExams(unitContentId) {
    closeAll();
    $('showManagerExams').style.display = 'block';  
    $('nextExam').innerHTML = 'Next';
    $('input.exercise.id').value='';
    $('input.exam.id').value ='';
    var i = 0;
            
    var jsonUnitContent = getJsonFromUrl('unit!getUnitContentInfo.action?unitContent.id=' + unitContentId);
    var jsonExams = getJsonFromUrl('unitContent!getExamsInfo.action?unitContent.id=' + unitContentId);

    var id = jsonUnitContent.unitContent.id;
    
    var exams = '';
    var buttons = '';

    if (jsonExams.list != '') {
        buttons += '<input class="btn-check-all" type="button" onclick="checkAllFieldsByUl(\'ulExams\', true)" value="Marcar Todos" name="Check All"/>';
        buttons += '<input class="btn-uncheck-all" type="button" onclick="checkAllFieldsByUl(\'ulExams\',false)" value="Desmarcar Todos" name="UnCheck All"/>'
        
        exams += '<ul id="ulExams">';
        if (jsonExams.list.exams.length == null) {
            var temp = jsonExams.list.exams.title;
            exams += '<li id="listExam_'+jsonExams.list.exams.id+'"><input type="checkbox" name="requisitesChecked" id="exams.id_' + 0 + '" value="' +jsonExams.list.exams.id + '" />' + temp + '</li>';
        }
        else {
            for (i = 0; i < jsonExams.list.exams.length; i++) {
                exams += '<li id="listExam_'+jsonExams.list.exams[i].id+'"><input type="checkbox" name="requisitesChecked" id="exams.id_' + i + '" value="' + jsonExams.list.exams[i].id + '" />' + jsonExams.list.exams[i].title + '</li>';
            }
        }
        exams += '</ul>';
    }
    
    $('unitContent.id').value = id;
    $('unitContent.exams').innerHTML = exams;
    $('unitContent.buttons.exam').innerHTML = buttons;

}

function getExercisesLessExamId(examId,unitContentId) {
     
    var jsonExams = getJsonFromUrl('unitContent!getExamsInfo.action?unitContent.id=' + unitContentId);
    var exams = 'Select requisites';
    var buttons = '';

    if (jsonExams.list != '') {
        buttons += '<input class="btn-check-all" type="button" onclick="checkAllFieldsByUl(\'ulExams\', true)" value="Marcar Todos" name="Check All"/>';
        buttons += '<input class="btn-uncheck-all" type="button" onclick="checkAllFieldsByUl(\'ulExams\',false)" value="Desmarcar Todos" name="UnCheck All"/>'
        
        exams += '<ul id="ulExams">';
        if (jsonExams.list.exams.length == null) {
            var temp = jsonExams.list.exams.title;
            if(examId!=jsonExams.list.exams.id)
              exams += '<li id="listExam_'+jsonExams.list.exams.id+'"><input type="checkbox" name="requisitesChecked" id="exams.id_' + 0 + '" value="' +jsonExams.list.exams.id + '" />' + temp + '</li>';
        }
        else {
            for (i = 0; i < jsonExams.list.exams.length; i++) {
                if(examId!=jsonExams.list.exams[i].id)
                  exams += '<li id="listExam_'+jsonExams.list.exams[i].id+'"><input type="checkbox" name="requisitesChecked" id="exams.id_' + i + '" value="' + jsonExams.list.exams[i].id + '" />' + jsonExams.list.exams[i].title + '</li>';
            }
        }
        exams += '</ul>';
    }
    
     return exams;

}

function showEntryExam(unitContentId) {
    
    $('form_exam_entry').reset();
    clearAllHints($('form_exam_entry'));
    
    if($('input.exam.id').value ==''){
        closeAll();
        $('showEntryExam').style.display = 'block';
        $('input.unitContent.id').value = unitContentId;
        $('input.unitContent.unit.id').value = '';
        $('input.unitContent.title').value = '';
        $('input.exam.id').value = '';
        $('input.exercise.id').value = '';
        
    
    
        //show requisites
        var html= '';
        var url = 'exam!getExamUnitContentJson.action?unitContent.id='+ unitContentId;        
        var jsonExam = getJsonFromUrl(url);
        if(jsonExam.list != ''){
            html = examRequisite;
            html +='<ul>';
            if(isArray(jsonExam.list.examArray)){
                // alert('aqui1');
                for(i=0; i<jsonExam.list.examArray.length;i++){
                    html += '<li><input type="checkbox" name="requisitesChecked" value="'+jsonExam.list.examArray[i].id+'">'+jsonExam.list.examArray[i].title+'</li>';
                    // alert(html); 
                }
            }
            else{
                //alert('aqui2');
                html += '<li><input type="checkbox" name="requisitesChecked" value="'+jsonExam.list.examArray.id+'">'+jsonExam.list.examArray.title+'</li>';
                // alert(html);
            }
        
            html += '</ul>';
        }
        $('exam.list.unitContent').innerHTML = html;    
    
        //
        $('input.exam.title').focus();
    }
    else{
        if(getCheckedExams().length > 1){
            alert("mais de um");
        }
        else{
            closeAll();
            $('showEntryExam').style.display = 'block';
            
            jsonExam = '';
            jsonExam = getJsonFromUrl('exam!getExamJson.action?exam.id=' + getCheckedExams()[0]);
            var jsonRequisites = getJsonFromUrl('exam!requisitesByExam.action?exam.id=' + getCheckedExams()[0]);
    
            $('input.exam.id').value = jsonExam.exam.id;
            $('input.exam.title').value = jsonExam.exam.title
            $('input.exam.description').value = jsonExam.exam.description;
            $('input.exam.weight').value = jsonExam.exam.weight
            $('input.exam.chances').value = jsonExam.exam.chances;
            
            if(jsonExercise.exam.navigable){
                $('input.exam.navigabletrue').checked = "checked";
            }
            else{
                $('input.exam.navigablefalse').checked = "checked";
            }
    
            $('exam.list.unitContent').innerHTML = $('unitContent.exams').innerHTML;
            
            var arrayRequisites = $('exam.list.unitContent').getElementsByTagName('input')
            
            if(isArray(jsonRequisites.list.set.exam)){
                for(i = 0; i < arrayRequisites.length;i++){
                    for(j = 0; j < jsonRequisites.list.set.exam.length; j++){
                        if(jsonRequisites.list.set.exam[j].id == arrayRequisites[i].getAttribute('value')){
                            arrayRequisites[i].checked = 'checked';
                        }
                    }
                }
            }
            else{
                for(i = 0; i < arrayRequisites.length;i++){
                    if(jsonRequisites.list.set.exam.id == arrayRequisites[i].getAttribute('value')){
                        arrayRequisites[i].checked = 'checked';
                        
                    }
                }
            }
    
        }
    }
}

function submitExam(ExamId) {
    var navigable;
    
    var arrayInput = $('form_exam_entry').getElementsByTagName('input');
    for (i = 0; i < arrayInput.length; i++){
        if(arrayInput[i].getAttribute('type') == 'radio' && arrayInput[i].checked){
            navigable = arrayInput[i].value
        }
    }
    
    var url = '';
    var params = '';
    
    if(validationHints($('form_exam_entry'))){
    
        if($('input.exam.startdatetime').value == ''){
            $('input.exam.startdatetime').focus();
        
        }else if($('input.exam.enddatetime').value == ''){
            $('input.exam.enddatetime').focus();
        
        }else{
        
    
            if (ExamId != null && ExamId.length > 0) {
                url += 'exam!update.action';
                params += 'exam.id=' + ExamId;
                params += '&exam.title=' + replaceAll($('input.exam.title').value);
                params += '&exam.weight=' + $('input.exam.weight').value;
                params += '&exam.navigable=' + navigable;
                params += '&exam.duration=' + $('input.exam.duration').value;
                params += '&dateStartTime=' + $('input.exam.startdatetime').value;
                params += '&dateEndTime=' + $('input.exam.enddatetime').value;
            }
            else {
                url += 'exam!add.action';
                params += 'exam.title=' + replaceAll($('input.exam.title').value);
                params += '&exam.weight=' + $('input.exam.weight').value;
                params += '&exam.navigable=' + navigable;
                params += '&exam.duration=' + $('input.exam.duration').value;
                params += '&dateStartTime=' + $('input.exam.startdatetime').value;
                params += '&dateEndTime=' + $('input.exam.enddatetime').value;
                params += '&exam.unitContentId=' + $('input.unitContent.id').value;
            }
            //  alert(params);
            
            var jsonExam = getJsonFromUrlPost(url,params);
            // alert(jsonExam);
            
            if (ExamId != null && ExamId.length > 0) {
                if (jsonExam != null && jsonExam.update == true) {
            
                    var checkedOnes =ExamId +"a";
                    for(i=0;i<arrayInput.length;i++){
                        if(arrayInput[i].getAttribute('type') == 'checkbox' && arrayInput[i].checked==true && arrayInput[i].getAttribute('name')=='requisitesChecked'){
                            checkedOnes += "b"+arrayInput[i].value;
                        }
                    }
                    $("listExamAccordeon_"+ExamId).innerHTML = '<a href="#" onclick="showExam(\''+ExamId+'\',false)">'+$('input.exam.title').value+'</a>'
                
                    getJsonFromUrl('exam!updateRequisitesWithJason.action?requisitesString=' + checkedOnes);
            
                    showExam(ExamId);
                }
                else {
                    alert('Não alterado');
                }
            }
            else {
                if (jsonExam != null && jsonExam.exam != null && jsonExam.exam.id != '') {
                    $('input.exercise.id').value = '';
                    $('input.exam.id').value = jsonExam.exam.id;
                    $('atividade.name').innerHTML = jsonExam.exam.title;
            
                    li = document.createElement('li');
                    li.setAttribute("id","listExamAccordeon_"+jsonExam.exam.id);
                    li.innerHTML = '<a onclick="showExam('+jsonExam.exam.id+',false)" href="#" >'+jsonExam.exam.title+'</a>'
                
                    $('ulExamAccordeon_'+jsonExam.exam.unitContentId).appendChild(li);
                
                    //ADD requisite
                    var checkedOnes =jsonExam.exam.id +"a";
                    for(i=0;i<arrayInput.length;i++){
                        if(arrayInput[i].getAttribute('type') == 'checkbox' && arrayInput[i].checked==true && arrayInput[i].getAttribute('name')=='requisitesChecked'){
                            checkedOnes += "b"+arrayInput[i].value;
                        }
                    }
                    getJsonFromUrl('exam!updateRequisitesWithJason.action?requisitesString=' + checkedOnes);
            
                    showEntryQuestion();
                    //document.location = 'question!input.action';
                }
                else {
                    alert('Não inserido');
        
                }
            }
        }
    }

}


function showExam(examId,isNextStep){
    closeAll();
    if(isNextStep){
        
        var css = ' <div class="steps-box"><div>'+
            '<div><span id="step">Step:</span>'+                            
            '<span  href="">1</span><span >2</span>'+
            '<span id="current">3</span></div></div>'+
            '<div class="tip-step">Exame criado com sucesso!!!! Caso queira altera-lo selecione Editar Exame. Caso queira adicionar mais questões selecione add questão.Para remover uma questão, primeiro selecione a questão, em seguida clique em Remover Questão'+
            '</div></div>';
        $('examExp').innerHTML = css;
    }
    else 
        $('examExp').innerHTML = '';
    $('showExam').style.display = 'block';

    var i = 0;
            
    
    var jsonQuestions = getJsonFromUrl('question!questionsByExam.action?exam.id=' + examId);
    var jsonExam = getJsonFromUrl('exam!getExamJson.action?exam.id=' + examId);
    var jsonRequisites = getJsonFromUrl('exam!requisitesByExam.action?exam.id=' + examId);
    
    var id = jsonExam.exam.id;
    $("exercise.id").value = '';
    $("input.exercise.id").value = '';
    
    //depois consertar, duas variaveis definidas para a mesma coisa
    $("exam.id").value = id;
    $('input.exam.id').value = id;
    
    $("exam.title").innerHTML = jsonExam.exam.title;
    $("exam.navigable").innerHTML = jsonExam.exam.navigable;
    $("exam.createdAt").innerHTML = jsonExam.exam.createdAt.$.substring(0,19);;
    $("exam.weight").innerHTML = jsonExam.exam.weight;
    $("exam.startDatetime").innerHTML = jsonExam.exam.startDatetime.$;
    $("exam.endDatetime").innerHTML = jsonExam.exam.endDatetime.$;
    $("exam.duration").innerHTML = jsonExam.exam.duration;
    $('unitContent.id').value = jsonExam.exam.unitContentId;
    
    var html = '';
    
    if(jsonQuestions.list != ''){
        
        html = '<ul>'
        
        if(isArray(jsonQuestions.list.questionArray)){
            for(i=0; i<jsonQuestions.list.questionArray.length;i++){
                html += '<li><span onclick=showQuestion(\''+jsonQuestions.list.questionArray[i].id+'\') >'+jsonQuestions.list.questionArray[i].question+'</span></li>'
            }
        }
        else{
            html += '<li><span onclick=showQuestion(\''+jsonQuestions.list.questionArray.id+'\') >'+jsonQuestions.list.questionArray.question+'</span></li>'
        }
        
        html += '</ul>'
    }
    
    $('exam.questions').innerHTML = html;
    
    var htmlRequisites = '';
    
    if(jsonRequisites.list.set != ''){
        
        htmlRequisites = '<ul>'
        
        if(isArray(jsonRequisites.list.set.exam)){
            for(i=0; i<jsonRequisites.list.set.exam.length;i++){
                htmlRequisites += '<li id="requisiteId_'+jsonRequisites.list.set.exam[i].id+'" onclick="showExam(\''+jsonRequisites.list.set.exam[i].id+'\',false)">'+jsonRequisites.list.set.exam[i].title+'</li>'
            }
        }
        else{
            htmlRequisites += '<li id="requisiteId_'+jsonRequisites.list.set.exam.id+'" onclick="showExam(\''+jsonRequisites.list.set.exam.id+'\',false)" >'+jsonRequisites.list.set.exam.title+'</li>'
        }
        
        htmlRequisites += '</ul>'
    }
    
    $('exam.requisites').innerHTML = htmlRequisites;
    //add name
    $('show.atividade.name').innerHTML = jsonExam.exam.title;
    $('atividade.name').innerHTML = jsonExam.exam.title;
    
    
}

function showEditExam(examId,unitContentId){
    $('form_exam_entry').reset();   
    if(examId != null){
        
        closeAll();
        clearAllHints($('form_exam_entry'));
        pseudoVirgin($('form_exam_entry'));
        $('showEntryExam').style.display = 'block';
       
     
        $('input.exam.id').value = examId;
        $('input.exam.title').value = $("exam.title").innerHTML;
        $('input.exam.weight').value = $("exam.weight").innerHTML;
        $('input.exam.duration').value = $("exam.duration").innerHTML;
        $('nextExam').innerHTML = 'Submit';
    
        arrayStartDate = $("exam.startDatetime").innerHTML.split("-");
        startDate = arrayStartDate[1]+"/"+arrayStartDate[2].substring(0,2)+"/"+arrayStartDate[0]+" "+$("exam.startDatetime").innerHTML.substring(11,19);
        $('input.exam.startdatetime').value = startDate;
    
        arrayEndDate = $("exam.endDatetime").innerHTML.split("-");
        endDate = arrayEndDate[1]+"/"+arrayEndDate[2].substring(0,2)+"/"+arrayEndDate[0]+" "+$("exam.endDatetime").innerHTML.substring(11,19);
        $('input.exam.enddatetime').value = endDate;
    
        if($("exam.navigable").innerHTML == 'true')
            $('input.exam.navigabletrue').checked = "checked";
        else
            $('input.exam.navigablefalse').checked = "checked";
    
    
        //$('exam.list.unitContent').innerHTML = $('unitContent.exams').innerHTML;
        $('exam.list.unitContent').innerHTML = getExercisesLessExamId(examId,unitContentId);
    
        var requisitesExam = $('exam.requisites').getElementsByTagName('li');
        var arrayRequisites = $('exam.list.unitContent').getElementsByTagName('input')
    
        for(i = 0; i < arrayRequisites.length;i++){
            for(j = 0; j < requisitesExam.length; j++){
                if(requisitesExam[j].getAttribute('id').substring(12) == arrayRequisites[i].getAttribute('value')){
                    arrayRequisites[i].checked = 'checked';
                }
            }
        }
    }
    else{
         
        if(getCheckedExams().length == ''){
            alert("escolha um Exam");
        }else if(getCheckedExams().length > 1){
            alert("mais de um");
        }
        else{
            closeAll();
            $('showEntryExam').style.display = 'block';
            
            var jsonExam = getJsonFromUrl('exam!getExamJson.action?exam.id=' + getCheckedExams()[0]);
            var jsonRequisites = getJsonFromUrl('exam!requisitesByExam.action?exam.id=' + getCheckedExams()[0]);
    
            $('input.exam.id').value = jsonExam.exam.id;
            $('input.exam.title').value = jsonExam.exam.title
            $('input.exam.duration').value = jsonExam.exam.duration;
            $('input.exam.weight').value = jsonExam.exam.weight
            sArrayDate = jsonExam.exam.startDatetime.$.split("-");
            startDate = sArrayDate[1]+"/"+sArrayDate[2].substring(0,2)+"/"+sArrayDate[0]+" "+jsonExam.exam.startDatetime.$.substring(11,19);
            
            eArrayDate = jsonExam.exam.startDatetime.$.split("-");
            endDate = eArrayDate[1]+"/"+eArrayDate[2].substring(0,2)+"/"+eArrayDate[0]+" "+jsonExam.exam.startDatetime.$.substring(11,19);
            
            $('input.exam.startdatetime').value = startDate;
            $('input.exam.enddatetime').value = endDate;
            
            if(jsonExam.exam.navigable){
                $('input.exam.navigabletrue').checked = "checked";
            }
            else{
                $('input.exam.navigablefalse').checked = "checked";
            }
    
            //$('exam.list.unitContent').innerHTML = $('unitContent.exams').innerHTML;
            $('exam.list.unitContent').innerHTML = getExercisesLessExamId(getCheckedExams()[0],unitContentId);
            var arrayRequisites = $('exam.list.unitContent').getElementsByTagName('input')
            
            if(jsonRequisites.list.set != ''){
                if(isArray(jsonRequisites.list.set.exam)){
                    for(i = 0; i < arrayRequisites.length;i++){
                        for(j = 0; j < jsonRequisites.list.set.exam.length; j++){
                            if(jsonRequisites.list.set.exam[j].id == arrayRequisites[i].getAttribute('value')){
                                arrayRequisites[i].checked = 'checked';
                            }
                        }
                    }
                }
                else{
                    for(i = 0; i < arrayRequisites.length;i++){
                        if(jsonRequisites.list.set.exam.id == arrayRequisites[i].getAttribute('value')){
                            arrayRequisites[i].checked = 'checked';
                        
                        }
                    }
                }
            }
    
        }
    }
}

function getCheckedExams()
{
    check = $('unitContent.exams').getElementsByTagName('input');
    var checkIds = new Array();
    
    for(i = 0; i< check.length; i++){
        if (check != null && check[i].getAttribute('type') == 'checkbox' && check[i].checked) {
            checkIds[checkIds.length] = check[i].value;
        }
    }
    
    return checkIds;
}

function deleteExams(){
    var examsCheckeds = getCheckedExams();
    if(examsCheckeds == ''){
        alert("escolha pelo menos um Exam");
    }
    else{
        params = ""
        if(examsCheckeds.length > 0){
            for(j = 0;j< examsCheckeds.length;j++){
                params += "examsCheckeds="+examsCheckeds[j]+"&";   
            }
        }
    
        getJsonFromUrl('exam!updateActive.action?'+params);
    
        for(j = 0;j< examsCheckeds.length;j++){
            $('listExam_'+examsCheckeds[j]).parentNode.removeChild($('listExam_'+examsCheckeds[j]));
        
            $('listExamAccordeon_'+examsCheckeds[j]).parentNode.removeChild($('listExamAccordeon_'+examsCheckeds[j]));
        }
    }
}

function hintCalendar(input){
    button = input.getAttribute("id")+"_button";
    $(button).focus();
    hint(input,true);
    
    $(button).onblur = function () {
        var td = input.parentNode.parentNode.getElementsByTagName("td")[1];
        if(input.value != ''){
            td.className = "welldone";
            clearHint(this, true);
        }
        else {
            td.className = "error";
            clearHint(this, false);
        }
    }
}

