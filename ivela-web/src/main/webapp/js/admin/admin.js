var current_course;
var fileExtOk = true;

// Array de ids de alunos selecionados
var checkedStudents = new Array();

Event.observe(window, 'load', loadAccordions, false);
Event.observe(window, 'load', function() {Event.observe(content, 'click', click, false);}, false);


function validateFile(fileName){
    if(fileName != ""){
           var ext = fileName.split(".",2);
           if(ext[1]!="xml"){
               alert("Arquivo inválido!!!");
               fileExtOk = false;
               $('idFile').focus();
           }
           else {
               fileExtOk = true;
     }
    }else {
               fileExtOk = true;
     }
           
}

function getJson(url){
    var json = '';
    new Ajax.Request(url,
    {
        method:'get',
        requestHeaders: {Accept: 'application/json'},
        asynchronous: false,
        onSuccess: function(transport) {
            json = transport.responseText.evalJSON(true);
        },
        onFailure: function() { alert('Message: Something went wrong...') }
    });
    return json;
}

function textCounter(field, countfield, maxlimit) {
    if (field.value.length > maxlimit) // if too long...trim it!
        field.value = field.value.substring(0, maxlimit);
    // otherwise, update 'characters left' counter
    else 
        countfield.value = maxlimit - field.value.length;
}

function click(e){ 
    if (!e) e = window.event;
    var evt;
    if (e.target) evt = e.target;
        else if (e.srcElement) evt = e.srcElement;
    if ((evt.className) != null) {       
        var clazz = evt.className.toString();

        if(clazz == 'accordion_toggle_grade2 accordion_toggle_active_grade2'){

            var course = evt.next(0).getAttribute('id');

            if(course != current_course){
                //eh um curso    
                current_course = course;
                showCourse(course);
            }
        } else if(clazz == 'vertical_accordion_toggle vertical_accordion_toggle_active'){
            var grade = evt.next(0).getAttribute('id');

            //eh uma grade
            showGrade(grade);
        } else if(clazz == 'vertical_accordion_toggle2 vertical_accordion_toggle_active2'){
            var element = evt.getAttribute('id');
            
            //eh uma unidade
            //if (element == 'professors')
            //  showProfessor($('grade.id').value, $('grade.course.id').value);
            //if (element == 'tutors')
            //  showTutor($('grade.id').value, $('grade.course.id').value);
            if (element == 'students'){
                $('pieCanvas').style.display = "none";
                showStudent($('grade.id').value, $('grade.course.id').value, $('grade.name').innerHTML, $('grade.course.name').innerHTML);
            }
                
        } 
    }
}


function submitList(){
    if(fileExtOk){
      $('show.enrollementList.grade').value = $('student.grade.id').value;
      $('formUpload').submit();
      $('studentsUp').onload = showFinish;
    }else{
        alert("Tipo de arquivo inválido!!!")
    }
}

function showFinish(){
    $('formUpload').reset();
    closeAll();
    $('showResultEnrollment').style.display = "block";
    $('listResultStudent').innerHTML = "";
    var html ="";
    html = "<table border ='1'><tr><td>Aluno</td><td>Matricula</td></tr>";
    for (i=0;i<$('studentsUp').contentDocument.forms[0].elements.length;i++){
        var x = $('studentsUp').contentDocument.forms[0].elements[i].value.split("#");
        var situacao;
        if(x[1]=="true"){
            situacao = "Realizada";
        }
        else{
            situacao = "Não realizada";
        }
        html+="<tr><td>"+x[0]+"</td><td>"+situacao+"</td></tr>"
    }
    html +="</table><br><br><a href='home.action'>Concluir</a>";
    $('listResultStudent').innerHTML = html;
    
    
}
        
//
//	Set up all accordions
//
function loadAccordions() {		
    var bottomAccordion = new accordion('vertical_container2', {
        classNames : {
            toggle : 'accordion_toggle_grade2',
            toggleActive : 'accordion_toggle_active_grade2',
            content : 'accordion_content_grade2'
        },
        direction : 'vertical'
    });

    if($('vertical_nested_container') != null) {
        new accordion('vertical_nested_container', {
            classNames : {
                toggle : 'vertical_accordion_toggle',
                toggleActive : 'vertical_accordion_toggle_active',
                content : 'vertical_accordion_content'
            }
        });            
    }
    
    if($('vertical_nested_container2') != null) {
        new accordion('vertical_nested_container2', {
            classNames : {
                toggle : 'vertical_accordion_toggle2',
                toggleActive : 'vertical_accordion_toggle_active2',
                content : 'vertical_accordion_content2'
            }
        });
    }

    if($$('#vertical_container2 .accordion_toggle_grade2') != null && $$('#vertical_container2 .accordion_toggle_grade2').length > 0) {
      first = bottomAccordion.activate($$('#vertical_container2 .accordion_toggle_grade2')[0]);
      current_course = first.getAttribute("id");      
      showCourse(first.getAttribute('id'));
    }
}

function showMessage() {
    $('student.message').style.display = 'block';
    $('input.course.message.description.len').value = 250;
    $('input.grade.message.description.len').value = 250;
    $('input.student.message.description.len').value = 250;
    
    $('input.course.newsflash.message.len').value = 250;
    $('input.grade.newsflash.message.len').value = 250;
    $('input.student.newsflash.message.len').value = 250;
    
}


function showNewsflash() {
    $('student.newsflash').style.display = 'block';

    $('input.course.message.description.len').value = 250;
    $('input.grade.message.description.len').value = 250;
    $('input.student.message.description.len').value = 250;
    
    $('input.course.newsflash.message.len').value = 250;
    $('input.grade.newsflash.message.len').value = 250;
    $('input.student.newsflash.message.len').value = 250;

}

function showMessageGrade() {
    $('grade.message').style.display = 'block';

    $('input.course.message.description.len').value = 250;
    $('input.grade.message.description.len').value = 250;
    $('input.student.message.description.len').value = 250;
    
    $('input.course.newsflash.message.len').value = 250;
    $('input.grade.newsflash.message.len').value = 250;
    $('input.student.newsflash.message.len').value = 250;
    
}

function showNewsflashGrade() {
    $('grade.newsflash').style.display = 'block';
    
    $('input.course.message.description.len').value = 250;
    $('input.grade.message.description.len').value = 250;
    $('input.student.message.description.len').value = 250;
    
    $('input.course.newsflash.message.len').value = 250;
    $('input.grade.newsflash.message.len').value = 250;
    $('input.student.newsflash.message.len').value = 250;
    
}

function showMessageCourse() {
    $('course.message').style.display = 'block';
    
    $('input.course.message.description.len').value = 250;
    $('input.grade.message.description.len').value = 250;
    $('input.student.message.description.len').value = 250;
    
    $('input.course.newsflash.message.len').value = 250;
    $('input.grade.newsflash.message.len').value = 250;
    $('input.student.newsflash.message.len').value = 250;
    
}

function showNewsflashCourse() {
    $('course.newsflash').style.display = 'block';
    
    $('input.course.message.description.len').value = 250;
    $('input.grade.message.description.len').value = 250;
    $('input.student.message.description.len').value = 250;
    
    $('input.course.newsflash.message.len').value = 250;
    $('input.grade.newsflash.message.len').value = 250;
    $('input.student.newsflash.message.len').value = 250;    
}

/* Funcoes para Chamar Json por Ajax */


function closeAll() {
    $('showCourse').style.display = 'none';
    
    $('showGrade').style.display = 'none';
    $('showStudent').style.display = 'none';

    $('studentData').innerHTML = '';
    
    $('grade.message').style.display = 'none';
    $('grade.newsflash').style.display = 'none';
    $('showScorecard').style.display = 'none';
    $('showCorrectQuestions').style.display = 'none';
    $('showTranscript').style.display = 'none';
    $('showEnrollmentList').style.display = 'none';
    $('showResultEnrollment').style.display = 'none';
    
    checkedTutors = new Array();
    checkedProfessors = new Array();
    checkedStudents = new Array();
    
    unCheckAll();
    closeAllMessages();
}

function unCheckAll() {
    var i = 0;
    var inputs = document.getElementsByTagName('input');
    for (i = 0; i < inputs.length; i++) {
        if (inputs[i].checked != null && inputs[i].checked == true)
        {
            inputs[i].checked = false;
            changeCheckboxStyle(inputs[i]);
        }
    }    
}

function showCourse(courseId) {
    closeAll();
    $('showCourse').style.display = 'block';
            
    $('course.id').value = courseId;
    $('course.grades').value = $('course.grades.' + courseId).innerHTML;
    $('course.name').innerHTML = $('course.name.' + courseId).innerHTML;
    $('course.description').innerHTML = $('course.description.' + courseId).innerHTML;
    $('course.image').src = '../RenderServletPartner?id=' + $('course.id').value;
    
    $('admin.course.student.count').innerHTML = $('studentsCount.course.' + courseId).innerHTML;
    $('admin.course.coordinators').innerHTML = $('coordinators.course.' + courseId).innerHTML;
    $('admin.course.professors').innerHTML = $('professors.course.' + courseId).innerHTML;
    $('admin.course.tutors').innerHTML = $('tutors.course.' + courseId).innerHTML;
}

function showGrade(gradeId) {
    closeAll();
    $('showGrade').style.display = 'block';
            
    $('grade.id').value = gradeId;
    $('grade.name').innerHTML = $('grade.name.' + gradeId).innerHTML;
    
    $('grade.course.id').value = $('grade.course.id.' + gradeId).innerHTML;
    $('grade.course.name').innerHTML = $('grade.course.name.' + gradeId).innerHTML;
    $('grade.course.description').innerHTML = $('grade.course.description.' + gradeId).innerHTML;
    
    $('admin.grade.student.count').innerHTML = $('studentsCount.grade.' + gradeId).innerHTML;
    $('admin.grade.coordinators').innerHTML = $('coordinators.grade.' + gradeId).innerHTML;
    $('admin.grade.professors').innerHTML = $('professors.grade.' + gradeId).innerHTML;
    $('admin.grade.tutors').innerHTML = $('tutors.grade.' + gradeId).innerHTML;
    $('admin.grade.startdate').innerHTML = $('startdate.grade.' + gradeId).innerHTML;
    $('admin.grade.enddate').innerHTML = $('enddate.grade.' + gradeId).innerHTML;
}

function showStudent(gradeId, courseId, gradeName, courseName) {
    closeAll();
    $('showStudent').style.display = 'block';
            
    var i = 0;
            
    //var jsonGrade = getJsonFromUrl('grade!getGradeInfo.action?grade.id=' + gradeId);
    //var jsonCourse = getJsonFromUrl('course!getCourseInfo.action?course.id=' + courseId);
    //var jsonStudents = getJsonFromUrl('grade!getStudentsInfo.action?grade.id=' + gradeId);
    
    var students = '';

    $('student.grade.course.id').value = courseId;
    $('student.grade.id').value = gradeId;
    $('student.grade.name').innerHTML = gradeName;
    $('student.grade.course.name').innerHTML = courseName;
   
    $('student.grade.students').innerHTML = students;
}

function updateStudents(obj, id, userId)
{        
    var studentDataHtml = "";
    $('reportData').innerHTML = "";
    $('pieCanvas').style.display = "none";
    var checks = document.getElementsByName(obj.name);
    
    checkedStudents = new Array();
    var arrayIndex = 0;

    var checkedElements = 0;
    for ( var i = 0; i < checks.length; i++ ){
        changeCheckboxStyle(checks[i]);
        if ( checks[i].checked ) {
            checkedElements++;
        }
    }
    
    var multiple = "";
    var divId;
    
    if ( checkedElements > 1 ) {
        multiple = ".multiple";
    }

    for ( var i = 0; i < checks.length; i++ ) {
        if ( checks[i].checked) { 

            // update the div in the multiple group
            var childs = document.getElementById("div" + multiple + ".inner." + checks[i].id).getElementsByTagName("img");
            for(var j = 0; j < childs.length; j++){
                if(childs[j].src.search("images/foto_profile.jpg") != -1 && checks[i].id == id){
                    childs[j].src = "../RenderServletProfile?id=" + userId;
                }           
            }
            
            // update the div in the single group
            var childs = document.getElementById("div.inner." + checks[i].id).getElementsByTagName("img");
            for(var j = 0; j < childs.length; j++){
                if(childs[j].src.search("images/foto_profile.jpg") != -1 && checks[i].id == id){
                    childs[j].src = "../RenderServletProfile?id=" + userId;
                }           
            }

            // acrescenta o id do tutor numa variável global se estiver marcado
            checkedStudents[arrayIndex++] = checks[i].value;
            var divContent = document.getElementById("div" + multiple + "." + checks[i].id).innerHTML;
            divContent = divContent.replace(new RegExp("display: none", "i"),"display: block");
            studentDataHtml += divContent;
        }
    }

    studentDataHtml += "<br class=\"clear\" />";
    
    $('studentData').innerHTML = studentDataHtml;
  
}

function getCheckedStudents()
{
    return checkedStudents;
}

function formatDate(param) {
    var i = 0;
    while (i >= 0) {
        i = param.indexOf('/');
        if (i >= 0) {
            param = param.substring(0, i) + param.substring(i + 1);
        }
    }
    return param;
}

function updateStudent(id, username, email, createdAt) {
    alert(username);
}

function closeAllMessages() {
    $('course.message').style.display = 'none';
    $('course.newsflash').style.display = 'none';
    $('grade.message').style.display = 'none';
    $('grade.newsflash').style.display = 'none';
    $('student.message').style.display = 'none';
    $('student.newsflash').style.display = 'none';   
    $('showTranscript').style.display ='none';     

    $('input.course.message.title').value = '';  
    $('input.course.message.description').value = '';  
    $('input.course.newsflash.message').value = '';  
    $('input.grade.message.title').value = '';  
    $('input.grade.message.description').value = '';  
    $('input.grade.newsflash.message').value = '';  
    $('input.student.message.title').value = '';  
    $('input.student.message.description').value = '';  
    $('input.student.newsflash.message').value = '';  
}

function sendMessageGradeId(id, title, description) {
    if (id.value == '' || id.value.length < 1) {
        alert('Não há turma selecionada');
        return;
    }
    if (title.value == '' || title.value.length < 1) {
        alert('Entre com o título da mensagem');
        title.focus();
        return;
    }
    if (description.value == '' || description.value.length < 1) {
        alert('Entre com a mensagem no campo descrição');
        description.focus();
        return;
    }
    var url = '';
    var params = '';
    url += 'grade!sendMessageGrade.action';
    params += 'grade.id=' + id.value;
    params += '&title=' + title.value;
    params += '&description=' + description.value;
    var json = getJsonFromUrl(url + '?' + params);
    if ((json.result + '') == 'true') {
        alert('Mensagem enviada');
        title.value = '';
        description.value = '';        
    }
    else {
        alert('Mensagem não enviada');
    }
}

function sendNewsFlashGradeId(id, message) {
    if (id.value == '' || id.value.length < 1) {
        alert('Não há turma selecionada');
        return;
    }
    if (message.value == '' || message.value.length < 1) {
        alert('Entre com a mensagem');
        message.focus();
        return;
    }
    var url = '';
    var params = '';
    url += 'grade!sendNewsFlashGrade.action';
    params += 'grade.id=' + id.value;
    params += '&message=' + message.value;
    var json = getJsonFromUrl(url + '?' + params);
    if ((json.result + '') == 'true') {
        alert('Mensagem enviada');
        message.value = '';        
    }
    else {
        alert('Mensagem não enviada');
    }
}

function sendMessageGrade(title, description) {
    var grades = $('course.grades').value;
    if (grades == '' || grades.length < 1) {
        alert('Não há turmas para este curso');
        return;
    }
    if (title.value == '' || title.value.length < 1) {
        alert('Entre com o título da mensagem');
        title.focus();
        return;
    }
    if (description.value == '' || description.value.length < 1) {
        alert('Entre com a mensagem no campo descrição');
        description.focus();
        return;
    }    
    var url = 'grade!sendMessageGrades.action';
    var params = '';
    params += 'gradeIds=' + grades;
    params += '&title=' + title.value;
    params += '&description=' + description.value;
    var json = getJsonFromUrl(url + '?' + params);
    if ((json.result + '') == 'true') {
        alert('Mensagem enviada');
        title.value = '';
        description.value = '';
    }
    else {
        alert('Mensagem não enviada');
    }
}

function sendNewsFlashGrade(message) {
    var grades = $('course.grades').value;
    if (grades == '' || grades.length < 1) {
        alert('Não há turmas para este curso');
        return;
    }
    if (message.value == '' || message.value.length < 1) {
        alert('Entre com a mensagem');
        message.focus();
        return;
    }      
    var url = 'grade!sendNewsFlashGrades.action';
    var params = '';
    params += 'gradeIds=' + grades;
    params += '&message=' + message.value;
    var json = getJsonFromUrl(url + '?' + params);
    if ((json.result + '') == 'true') {
        alert('Mensagem enviada');
        message.value = '';
    }
    else {
        alert('Mensagem não enviada');        
    }
}

function sendMessageStudent(title, description) {
    var students = getCheckedStudents();
    if (students == '' || students.length < 1) {
        alert('Não há estudantes selecionados');
        return;
    }
    if (title.value == '' || title.value.length < 1) {
        alert('Entre com o título da mensagem');
        title.focus();
        return;
    }              
    if (description.value == '' || description.value.length < 1) {
        alert('Entre com a mensagem no campo descrição');
        description.focus();
        return;
    }          
    
    var i = 0;
    var studentIds = '';
    var enviadas = 0;
    var falhas = 0;    
    for (i = 0; i < students.length; i++) {
        studentIds += students[i];
        if (i != students.length - 1)
            studentIds += ';';
    }
    var url = 'grade!sendMessageUsers.action';
    var params = '';
    params += 'systemUserIds=' + studentIds;
    params += '&title=' + title.value;
    params += '&description=' + description.value;
    var json = getJsonFromUrl(url + '?' + params);
    if ((json.result + '') == 'true') {
        enviadas++;
    }
    else {
        falhas++;
    }        
    if (enviadas > 0) {
        alert('Mensagem enviada');
        title.value = '';
        description.value = '';
    }
    else
        alert('Mensagem não enviada');
}

function sendNewsFlashStudent(message) {
    var students = getCheckedStudents();
    if (students == '' || students.length < 1) {
        alert('Não há estudantes selecionados');
        return;
    }
    if (message.value == '' || message.value.length < 1) {
        alert('Entre com a mensagem');
        message.focus();
        return;
    }          
    var i = 0;
    var studentIds = '';
    var enviadas = 0;
    var falhas = 0;    
    for (i = 0; i < students.length; i++) {
        studentIds += students[i];
        if (i != students.length - 1)
            studentIds += ';';
    }
    var url = 'grade!sendNewsFlashUsers.action';
    var params = '';
    params += 'systemUserIds=' + studentIds;
    params += '&message=' + message.value;
    var json = getJsonFromUrl(url + '?' + params);
    if ((json.result + '') == 'true') {
        enviadas++;
    }
    else {
        falhas++;
    }        
    if (enviadas > 0) {
        alert('Mensagem enviada');
        message.value = '';
    }
    else
        alert('Mensagem não enviada');
}

function setEnrollments(gradeId, status) {
    //$('showStudent').style.display = 'none';
    var students = getCheckedStudents();
    if (students == '') {
        alert('Selecione pelo menos um aluno');
        return;
    }
    showLoading();
    var i = 0;
    var studentIds = '';
    var enviadas = 0;
    var falhas = 0;   
    for (i = 0; i < students.length; i++) {
        studentIds += students[i];
        if (i != students.length - 1)
            studentIds += ';';
    }
    var url = 'grade!setEnrollments.action';
    var params = '';
    params += 'systemUserIds=' + studentIds;
    params += '&status=' + status;
    params += '&grade.id=' + gradeId;
    var json = getJsonFromUrl(url + '?' + params);
    if ((json.result + '') == 'true') {
        enviadas++;
    } else {
        falhas++;
    }        
    if (enviadas > 0) {
        //alert('Estado(s) atualizado(s)');
        //document.location = 'home.action';
        updateStructure();
    } else {
        alert('Estado(s) não atualizado(s)');    
        closeLoading();
    }
}

function setCheckedAllStudents(gradeId, bool)  {
    var i = 0;
    var inputs = document.getElementsByTagName('input');
    var checkedStudents = new Array();
    var checkedStudentsIndex = 0;
    for (i = 0; i < inputs.length; i++) {
        if (inputs[i].id.indexOf('student.id_' + gradeId + '_') > -1) {
            checkedStudents[checkedStudentsIndex++] = inputs[i];
            inputs[i].checked = bool;
        }
    }

    if ( checkedStudents[0] != null ) {
        updateStudents(checkedStudents[0], checkedStudents[0].id);            
    }
    
}

var col1Content = '';
var col2Content = '';

function showLoading() {
    col1Content = $('col-1-home').innerHTML;
    col2Content = $('col-2-home').innerHTML;
    $('col-1-home').innerHTML = '<img src="../images/ajax-loading.gif" />';
    $('col-2-home').innerHTML = '<img src="../images/ajax-loading.gif" />';
}

function closeLoading() {
    $('col-1-home').innerHTML = col1Content;
    $('col-2-home').innerHTML = col2Content;    
}

function changeCheckboxStyle(obj) {
    if ( obj.checked ) {
        if(!$('li.' + obj.id ).hasClassName("box-person-selected")){
            $('li.' + obj.id ).addClassName("box-person-selected");
            if($('li.' + obj.id ).hasClassName("box-person")){
                $('li.' + obj.id ).removeClassName("box-person");
            }
        }
    } else {
        if($('li.' + obj.id ).hasClassName("box-person-selected")){
            $('li.' + obj.id ).removeClassName("box-person-selected");
            if(!$('li.' + obj.id ).hasClassName("box-person")){
                $('li.' + obj.id ).addClassName("box-person");            
            }
        }
    }
}
/*
function mouseOverPerson(id) {
    $('li.' + id ).style.backgroundColor = "#FFE79C";
    $('li.' + id ).style.border = "1px solid #FF7202";
}

function mouseOutPerson(id) {
    if ( !$(id).checked )
    {
        $('li.' + id ).style.backgroundColor = "#FFFFFF";
        $('li.' + id ).style.border = "1px solid #D9E5F2";
    }
}
*/
function showEnrollmentList() {
    closeAll();
    $('showEnrollmentList').style.display = 'block';
}


