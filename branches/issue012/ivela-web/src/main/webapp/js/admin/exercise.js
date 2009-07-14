/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function showManagerExercises(unitContentId) {
    closeAll();
    $('input.question.id').value = '';
    $('showManagerExercises').style.display = 'block';              
    $('nextExercise').innerHTML = 'Next';
    $('input.exercise.id').value='';
    $('input.exam.id').value ='';

    var i = 0;
            
    var jsonUnitContent = getJsonFromUrl('unit!getUnitContentInfo.action?unitContent.id=' + unitContentId);
    var jsonExercises = getJsonFromUrl('unitContent!getExercisesInfo.action?unitContent.id=' + unitContentId);

    var id = jsonUnitContent.unitContent.id;
    
    var exercises = '';
    var buttons = '';

    if (jsonExercises.list != '') {
        buttons += '<input class="btn-check-all" type="button" onclick="checkAllFieldsByUl(\'ulExercises\', true)" value="Marcar Todos" name="Check All"/> ';
        buttons += '<input class="btn-uncheck-all" type="button" onclick="checkAllFieldsByUl(\'ulExercises\',false)" value="Desmarcar Todos" name="UnCheck All"/>'
        
        exercises += '<ul id="ulExercises">';
        if (jsonExercises.list.exercises.length == null) {
            var temp = jsonExercises.list.exercises.title;
            exercises += '<li id="listExercise_'+jsonExercises.list.exercises.id+'"><input type="checkbox" id="exercises.id_' + 0 + '" value="' +jsonExercises.list.exercises.id + '" name="requisitesChecked" />' + temp + '</li>';
        }
        else {
            for (i = 0; i < jsonExercises.list.exercises.length; i++) {
                exercises += '<li id="listExercise_'+jsonExercises.list.exercises[i].id+'"><input type="checkbox" id="exercises.id_' + i + '" value="' + jsonExercises.list.exercises[i].id + '" name="requisitesChecked" />' + jsonExercises.list.exercises[i].title + '</li>';
            }
        }
        exercises += '</ul>';
    }
    
    $('unitContent.id').value = id;
    $('unitContent.exercises').innerHTML = exercises;
    $('unitContent.buttons.exercise').innerHTML = buttons;

}

function getExercisesLessExerciseId(exerciseId, unitContentId){
    
    var jsonExercises = getJsonFromUrl('unitContent!getExercisesInfo.action?unitContent.id=' + unitContentId);
    var exercises = 'Select requisites';
    var buttons = '';
    if (jsonExercises.list != '') {
        buttons += '<input class="btn-check-all" type="button" onclick="checkAllFieldsByUl(\'ulExercises\', true)" value="Marcar Todos" name="Check All"/> ';
        buttons += '<input class="btn-uncheck-all" type="button" onclick="checkAllFieldsByUl(\'ulExercises\',false)" value="Desmarcar Todos" name="UnCheck All"/>'
        
        exercises += '<ul id="ulExercises">';
        if (jsonExercises.list.exercises.length == null) {
            var temp = jsonExercises.list.exercises.title;
            alert("a"+exerciseId+"--"+jsonExercises.list.exercises.id);
            if(jsonExercises.list.exercises.id != exerciseId)
              exercises += '<li id="listExercise_'+jsonExercises.list.exercises.id+'"><input type="checkbox" id="exercises.id_' + 0 + '" value="' +jsonExercises.list.exercises.id + '" name="requisitesChecked" />' + temp + '</li>';
        }
        else {
            for (i = 0; i < jsonExercises.list.exercises.length; i++) {
                //alert("b"+exerciseId+"--"+jsonExercises.list.exercises[i].id);
                if(jsonExercises.list.exercises[i].id != exerciseId)
                  exercises += '<li id="listExercise_'+jsonExercises.list.exercises[i].id+'"><input type="checkbox" id="exercises.id_' + i + '" value="' + jsonExercises.list.exercises[i].id + '" name="requisitesChecked" />' + jsonExercises.list.exercises[i].title + '</li>';
            }
        }
        exercises += '</ul>';
    }
    
    return exercises;
}


function showEntryExercise(unitContentId) {
    closeAll();
    //$('form.input.exercise').reset();
    $('form_exercise_entry').reset();
    $('showEntryExercise').style.display = 'block';
    $('form_exam_entry').reset();   
    $('input.unitContent.id').value = unitContentId;
    $('input.unitContent.unit.id').value = '';
    $('input.unitContent.title').value = '';
    
    //limpar spans com erros de validação
    clearAllHints($('form_exercise_entry'));
    
//    for(i = 0; i < $('form_exercise_entry').getElementsByTagName('span').length; i++){
//        $('form_exercise_entry').getElementsByTagName('span')[i].style.display = "none";
//        
//    }
  
    var html = '';
    var url = 'exercise!getExerciseUnitContentJson.action?unitContent.id='+ unitContentId;

    $('input.exam.id').value = '';
    $('input.exercise.id').value = '';
    
           
    var jsonExercise = getJsonFromUrl(url);
    
    if(jsonExercise.list != ''){
        
        html = exerciseRequisite;
        html +='<ul>';

        if(isArray(jsonExercise.list.exerciseArray)){

            //    alert('aqui1');

            for(i=0; i<jsonExercise.list.exerciseArray.length;i++){
                //html += '<input type="checkbox" name="requisitesChecked" value="'+jsonExercise.list.exerciseArray[i].id+'" />'+jsonExercise.list.exerciseArray[i].title+'<br/>';
                html += '<li><input type="checkbox" name="requisitesChecked" value="'+jsonExercise.list.exerciseArray[i].id+'">'+jsonExercise.list.exerciseArray[i].title+'</li>';
                //        alert(html); 
            }
        }
        else{
            // alert('aqui2');
            html += '<li><input type="checkbox" name="requisitesChecked" value="'+jsonExercise.list.exerciseArray.id+'">'+jsonExercise.list.exerciseArray.title+'</li>';
        }
        html +='</ul>';
    }
    $('exercise.list.unitContent').innerHTML = html;
   
    $('input.exercise.title').focus();
}

function submitExercise(ExerciseId) {
    var navigable;
    
    var arrayInput = $('form_exercise_entry').getElementsByTagName('input');
    
    for (i = 0; i < arrayInput.length; i++){
        if(arrayInput[i].getAttribute('type') == 'radio' && arrayInput[i].checked){
            navigable = arrayInput[i].value
        }
    }
    
    var url = '';
    var params = '';

    if(validationHints($('form_exercise_entry'))){
    
        if (ExerciseId != null && ExerciseId.length > 0) {
            url += 'exercise!update.action';
            params += 'exercise.id=' + ExerciseId;
            params += '&exercise.title=' + replaceAll($('input.exercise.title').value);
            params += '&exercise.description=' + replaceAll($('input.exercise.description').value);
            params += '&exercise.weight=' + $('input.exercise.weight').value;
            params += '&exercise.navigable=' + navigable;
            params += '&exercise.chances=' + $('input.exercise.chances').value;
        }
        else {
            url += 'exercise!add.action';
            params += 'exercise.title=' + replaceAll($('input.exercise.title').value);
            params += '&exercise.description=' + replaceAll($('input.exercise.description').value);
            params += '&exercise.weight=' + $('input.exercise.weight').value;
            params += '&exercise.navigable=' + navigable;
            params += '&exercise.chances=' + $('input.exercise.chances').value;
            params += '&exercise.unitContentId=' + $('input.unitContent.id').value;
        }
            
        var jsonExercise = getJsonFromUrlPost(url,params);
            
        if (ExerciseId != null && ExerciseId.length > 0) {
            if (jsonExercise != null && jsonExercise.update == true) {
            
                var checkedOnes =ExerciseId +"a";
                for(i=0;i<arrayInput.length;i++){
                    if(arrayInput[i].getAttribute('type') == 'checkbox' && arrayInput[i].checked==true && arrayInput[i].getAttribute('name')=='requisitesChecked'){
                        checkedOnes += "b"+arrayInput[i].value;
                    }
                }
                
                $("listExerciseAccordeon_"+ExerciseId).innerHTML = '<a href="#" onclick="showExercise(\''+ExerciseId+'\',false)">'+$('input.exercise.title').value+'</a>'
                
                getJsonFromUrl('exercise!updateRequisitesWithJason.action?requisitesString=' + checkedOnes);
            
                showExercise(ExerciseId,false);
            }
            else {
                alert('Não alterado');
            }
        }
        else {
            if (jsonExercise != null && jsonExercise.exercise != null && jsonExercise.exercise.id != '') {
                $('input.exam.id').value = '';
                $('input.exercise.id').value = jsonExercise.exercise.id;
                $('atividade.name').innerHTML ='';
                $('atividade.name').innerHTML =jsonExercise.exercise.title;
                
                //ADD o exercise ao accordeon
                li = document.createElement('li');
                li.setAttribute("id","listExerciseAccordeon_"+jsonExercise.exercise.id);
                li.innerHTML = '<a onclick="showExercise('+jsonExercise.exercise.id+',false)" href="#" >'+jsonExercise.exercise.title+'</a>'
                
                $('ulExerciseAccordeon_'+jsonExercise.exercise.unitContentId).appendChild(li);
            
                //ADD requisite
                var checkedOnes =jsonExercise.exercise.id +"a";
                for(i=0;i<arrayInput.length;i++){
                    if(arrayInput[i].getAttribute('type') == 'checkbox' && arrayInput[i].checked==true && arrayInput[i].getAttribute('name')=='requisitesChecked'){
                        checkedOnes += "b"+arrayInput[i].value;
                    }
                }
                getJsonFromUrl('exercise!updateRequisitesWithJason.action?requisitesString=' + checkedOnes);
                //add question
                //ulExercise é a lista de checkboxs
                //<ul id="ulExercises">
                //<li id="listExercise_9">
                //<input id="exercises.id_0" type="checkbox" name="requisitesChecked" value="9"/>manu</li>
                //
                //<ul id="ulExerciseAccordeon">
                //<li id="listExerciseAccordeon_9">
                //<a onclick="showExercise('9')" href="#">manu</a></li>
            
                showEntryQuestion();
            
  
            }
            else {
                alert('Não inserido');
        
            }
        }
    }

}


function showExercise(exerciseId,isNextStep){
    closeAll();
    if(isNextStep){
        
       var css = ' <div class="steps-box">\n\
                    <div>'+
                      '<div>\n\
                            <span id="step">Step:</span>'+                            
                            '<span  href="">1</span>\n\
                             <span >2</span>'+
                            '<span id="current">3</span>\n\
                       </div>\n\
                    </div>'+
                    '<div class="tip-step">\n\
                        Exercício criado com sucesso!!!! Caso queira altera-lo selecione Editar Exercício. Caso queira adicionar mais questões selecione add questão.Para remover uma questão, primeiro selecione a questão, em seguida clique em Remover Questão'+
                    '</div>'+
                '</div>';
       $('exerciseExp').innerHTML = css;
    }
    else 
        $('exerciseExp').innerHTML = '';
    
    $('showExercise').style.display = 'block';
    
    var i = 0;
            
    
    var jsonQuestions = getJsonFromUrl('question!questionsByExercises.action?exercise.id=' + exerciseId);
    var jsonExercise = getJsonFromUrl('exercise!getExerciseJson.action?exercise.id=' + exerciseId);
    var jsonRequisites = getJsonFromUrl('exercise!requisitesByExercise.action?exercise.id=' + exerciseId);
    
    var id = jsonExercise.exercise.id;
    $('exam.id').value = '';
    $('input.exam.id').value = '';
    
    ////depois consertar, duas variaveis definidas para a mesma coisa
    $("exercise.id").value = id;
    $('input.exercise.id').value = id;
    
    $("exercise.title").innerHTML = jsonExercise.exercise.title;
    $("exercise.navigable").innerHTML = jsonExercise.exercise.navigable;
    $("exercise.createdAt").innerHTML = jsonExercise.exercise.createdAt.$.substring(0,19);
    $("exercise.weight").innerHTML = jsonExercise.exercise.weight;
    $("exercise.chances").innerHTML = jsonExercise.exercise.chances;
    $("exercise.description").innerHTML = jsonExercise.exercise.description;
    $('unitContent.id').value = jsonExercise.exercise.unitContentId;
    
    //add name 
    $('show.atividade.name').innerHTML = jsonExercise.exercise.title;
    $('atividade.name').innerHTML = jsonExercise.exercise.title;
    
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
    
    $('exercise.questions').innerHTML = html;
    
    var htmlRequisites = '';
    
    if(jsonRequisites.list.set != ''){
        
        htmlRequisites = '<ul>'
        
        if(isArray(jsonRequisites.list.set.exercise)){
            for(i=0; i<jsonRequisites.list.set.exercise.length;i++){
                htmlRequisites += '<li id="requisiteId_'+jsonRequisites.list.set.exercise[i].id+'" value="'+jsonRequisites.list.set.exercise[i].id+'" onclick="showExercise(\''+jsonRequisites.list.set.exercise[i].id+'\',false)">'+jsonRequisites.list.set.exercise[i].title+'</li>'
            }
        }
        else{
            htmlRequisites += '<li id="requisiteId_'+jsonRequisites.list.set.exercise.id+'" value="'+jsonRequisites.list.set.exercise.id+'" onclick="showExercise(\''+jsonRequisites.list.set.exercise.id+'\',false)" >'+jsonRequisites.list.set.exercise.title+'</li>'
        }
        
        htmlRequisites += '</ul>'
    }
    
    $('exercise.requisites').innerHTML = htmlRequisites;
}

function showEditExercise(exerciseId, unitContentId){
    
    $('form_exercise_entry').reset();
    clearAllHints($('form_exercise_entry'));
    pseudoVirgin($('form_exercise_entry'));
     
    if(exerciseId != null){
    
        closeAll();
        $('showEntryExercise').style.display = 'block';
     
        $('input.exercise.id').value = exerciseId;
        $('input.exercise.title').value = $("exercise.title").innerHTML;
        $('input.exercise.description').value = $("exercise.description").innerHTML;
        $('input.exercise.weight').value = $("exercise.weight").innerHTML;
        $('input.exercise.chances').value = $("exercise.chances").innerHTML;
        $('nextExercise').innerHTML = 'Submit';
    
        if($("exercise.navigable").innerHTML == 'true')
            $('input.exercise.navigabletrue').checked = "checked";
        else
            $('input.exercise.navigablefalse').checked = "checked";
    
         
        //$('exercise.list.unitContent').innerHTML = $('unitContent.exercises').innerHTML;
        
        $('exercise.list.unitContent').innerHTML = getExercisesLessExerciseId(exerciseId,unitContentId);
        
        var requisitesExercise = $('exercise.requisites').getElementsByTagName('li');
        var arrayRequisites = $('exercise.list.unitContent').getElementsByTagName('input')
    
        for(i = 0; i < arrayRequisites.length;i++){
            for(j = 0; j < requisitesExercise.length; j++){
                if(requisitesExercise[j].getAttribute('id').substring(12) == arrayRequisites[i].getAttribute('value')){
                    arrayRequisites[i].checked = 'checked';
                }
            }
        }
    }
    else{
         
        if(getCheckedExercises().length == ''){
            alert("escolha um Exercise");
        }else if(getCheckedExercises().length > 1){
            alert("mais de um");
        }
        else{
            closeAll();
             
            $('showEntryExercise').style.display = 'block';
            
            var jsonExercise = getJsonFromUrl('exercise!getExerciseJson.action?exercise.id=' + getCheckedExercises()[0]);
            var jsonRequisites = getJsonFromUrl('exercise!requisitesByExercise.action?exercise.id=' + getCheckedExercises()[0]);
    
            $('input.exercise.id').value = jsonExercise.exercise.id;
            $('input.exercise.title').value = jsonExercise.exercise.title
            $('input.exercise.description').value = jsonExercise.exercise.description;
            $('input.exercise.weight').value = jsonExercise.exercise.weight
            $('input.exercise.chances').value = jsonExercise.exercise.chances;
            
            if(jsonExercise.exercise.navigable){
                $('input.exercise.navigabletrue').checked = "checked";
            }
            else{
                $('input.exercise.navigablefalse').checked = "checked";
            }
    
            //$('exercise.list.unitContent').innerHTML = $('unitContent.exercises').innerHTML;
            
            $('exercise.list.unitContent').innerHTML = getExercisesLessExerciseId(getCheckedExercises()[0],unitContentId);
            
            var arrayRequisites = $('exercise.list.unitContent').getElementsByTagName('input')
            
            if(jsonRequisites.list.set != ''){
                
                if(isArray(jsonRequisites.list.set.exercise)){
                    
                    for(i = 0; i < arrayRequisites.length;i++){
                        for(j = 0; j < jsonRequisites.list.set.exercise.length; j++){
                            if(jsonRequisites.list.set.exercise[j].id == arrayRequisites[i].getAttribute('value')){
                                arrayRequisites[i].checked = 'checked';
                            }
                        }
                    }
                }
                else{
                    
                    for(i = 0; i < arrayRequisites.length;i++){
                        if(jsonRequisites.list.set.exercise.id == arrayRequisites[i].getAttribute('value')){
                            arrayRequisites[i].checked = 'checked';
                        
                        }
                    }
                }
            }
            
        }
    }
}

function getCheckedExercises()
{
    check = $('unitContent.exercises').getElementsByTagName('input');
    var checkIds = new Array();
    
    for(i = 0; i< check.length; i++){
        if (check != null && check[i].getAttribute('type') == 'checkbox' && check[i].checked) {
            checkIds[checkIds.length] = check[i].value;
        }
    }
    
    return checkIds;
}

function deleteExercises(){
    var exercisesCheckeds = getCheckedExercises();
    
    if(exercisesCheckeds == ''){
        alert("escolha pelo menos um Exercise");
    }
    else{
    
        params = ""
        if(exercisesCheckeds.length > 0){
            for(j = 0;j< exercisesCheckeds.length;j++){
                params += "exercisesCheckeds="+exercisesCheckeds[j]+"&";   
            }
        }
    
        getJsonFromUrl('exercise!updateActive.action?'+params);
    
        for(j = 0;j< exercisesCheckeds.length;j++){
            $('listExercise_'+exercisesCheckeds[j]).parentNode.removeChild($('listExercise_'+exercisesCheckeds[j]));
        
            $('listExerciseAccordeon_'+exercisesCheckeds[j]).parentNode.removeChild($('listExerciseAccordeon_'+exercisesCheckeds[j]));
        }
    }

}

function checkAllFieldsByUl(ul, check){
    arrayLi = $(ul).getElementsByTagName('input');
    
    for(i = 0; i < arrayLi.length; i++){
        arrayLi[i].checked = check ;
    }
    
}


function addLoadEvent(func) {
    var oldonload = window.onload;
    if (typeof window.onload != 'function') {
        window.onload = func;
    } else {
        window.onload = function() {
            oldonload();
            func();
        }
    }
}