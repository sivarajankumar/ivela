var current_course;

// Array de ids de tutores selecionados
var checkedTutors = new Array();

// Array de ids de professores selecionados
var checkedProfessors = new Array();

// Array de ids de alunos selecionados
var checkedStudents = new Array();

Event.observe(window, 'load', loadAccordions, false);
Event.observe(window, 'load', function() {Event.observe(content, 'click', click, false);}, false);
 
function click(e){
	if (!e) e = window.event;
	var evt;
    if (e.target) evt = e.target;
		else if (e.srcElement) evt = e.srcElement;	
	if ((evt.className) != null){       
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
            if (element == 'professors')
                showProfessor($('grade.id').value, $('grade.course.id').value);
            if (element == 'tutors')
                showTutor($('grade.id').value, $('grade.course.id').value);
            if (element == 'students')
                showStudent($('grade.id').value, $('grade.course.id').value);
            if (element == 'forums')
                showForum($('grade.id').value, $('grade.course.id').value);
        } 
    }
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

    if ($('vertical_nested_container') != null) {
        new accordion('vertical_nested_container', {
            classNames : {
                toggle : 'vertical_accordion_toggle',
                toggleActive : 'vertical_accordion_toggle_active',
                content : 'vertical_accordion_content'
            }
        });
    }
    if ($('vertical_nested_container2') != null) {
        new accordion('vertical_nested_container2', {
            classNames : {
                toggle : 'vertical_accordion_toggle2',
                toggleActive : 'vertical_accordion_toggle_active2',
                content : 'vertical_accordion_content2'
            }
        });
    }
    if ($('vertical_nested_container3') != null) {
        new accordion('vertical_nested_container3', {
            classNames : {
                toggle : 'vertical_accordion_toggle3',
                toggleActive : 'vertical_accordion_toggle_active3',
                content : 'vertical_accordion_content3'
            }
        });
    }
    if ($('vertical_nested_container4') != null) {
        new accordion('vertical_nested_container4', {
            classNames : {
                toggle : 'vertical_accordion_toggle4',
                toggleActive : 'vertical_accordion_toggle_active4',
                content : 'vertical_accordion_content4'
            }
        });
    }

    if($$('#vertical_container2 .accordion_toggle_grade2') != null && $$('#vertical_container2 .accordion_toggle_grade2').length > 0) {
        first = bottomAccordion.activate($$('#vertical_container2 .accordion_toggle_grade2')[0]);
        current_course = first.getAttribute("id");
        showCourse(first.getAttribute('id'));
    }
    

}

/* Funcoes para Chamar Json por Ajax */


function closeAll() {
    $('showCourse').style.display = 'none';
    
    $('showGrade').style.display = 'none';
    $('showProfessor').style.display = 'none';
    $('showTutor').style.display = 'none';
    $('showStudent').style.display = 'none';
    $('showForum').style.display = 'none';
    $('showForumId').style.display = 'none';

    $('showEntryGrade').style.display = 'none';
    $('showEntryProfessor').style.display = 'none';
    $('showEntryTutor').style.display = 'none';
    $('showEntryStudent').style.display = 'none';  
    $('showEntryForum').style.display = 'none';  
    $('showEntryTopic').style.display = 'none';
    
    $('tutorData').innerHTML = '';
    $('professorData').innerHTML = '';
    $('studentData').innerHTML = '';
    
    $('grade.message').style.display = 'none';
    $('grade.newsflash').style.display = 'none';
    
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
            
    var i = 0;

    var json = getJsonFromUrl('grade!getCourseInfoJson.action?course.id=' + courseId);

    //var jsonCourse = getJsonFromUrl('course!getCourseInfo.action?course.id=' + courseId);
    //var jsonStudents = getJsonFromUrl('course!getStudentsInfo.action?course.id=' + courseId);
    //var jsonGrades = getJsonFromUrl('course!getGrades.action?course.id=' + courseId);
    //var jsonGradesInfo = getJsonFromUrl('course!getGradesInfo.action?course.id=' + courseId);
    //var jsonGraduatedStudentsInfo = getJsonFromUrl('course!getGraduatedStudentsInfo.action?course.id=' + courseId);
    //var jsonProfessors = getJsonFromUrl('course!getProfessorsInfo.action?course.id=' + courseId);
    //var jsonTutors = getJsonFromUrl('course!getTutorsInfo.action?course.id=' + courseId);
            
    var studentsCount = json.course.studentsCount;                
    //var graduatedStudentCount = json.course.graduatedStudentCount;
    var professorsCount = 0;
    var tutorsCount = 0;
    var gradesCount = json.course.gradesCount;
    
    var grades = '';

    if (json.course.grades != '') {
        grades += '<ul>';
        if (json.course.grades.length == null) {
            var temp = json.course.grades.name;
            if (temp != undefined)
                grades = '<li><input type="checkbox" id="grade.id_' + 0 + '" value="' + json.course.grades.id + '" />' + temp + '</li>';
        }
        else {
            for (i = 0; i <json.course.grades.length; i++) {
                grades += '<li><input type="checkbox" id="grade.id_' + i + '" value="' + json.course.grades[i].id + '" />' + json.course.grades[i].name + '</li>';
            }
        }
        grades += '</ul>';
    }

    $('course.id').value = json.course.id;
    $('course.name').innerHTML = json.course.name;
    $('course.description').innerHTML = json.course.description;
    $('course.coordinator.count').innerHTML = studentsCount;
    $('course.image').src = '../RenderServletPartner?id=' + $('course.id').value;
    
    $('course.professor.count').innerHTML = professorsCount;
    $('course.tutor.count').innerHTML = tutorsCount;
    $('course.student.count').innerHTML = studentsCount;
    //$('course.graduated.count').innerHTML = graduatedStudentCount;
    $('course.grade.count').innerHTML = gradesCount;
   
    $('course.grades').innerHTML = grades;    
}


function showGrade(gradeId) {
    closeAll();
    $('showGrade').style.display = 'block';

    var i = 0;
    
    var jsonGrade = getJsonFromUrl('grade!getGradeInfo.action?grade.id=' + gradeId);
    var courseId = jsonGrade.grade.courseId;
    var jsonGraduatedStudentsInfo = getJsonFromUrl('course!getGraduatedStudentsInfo.action?course.id=' + courseId);

    var studentsCount = 'none';
    if (jsonGrade.grade.enrollments != null) {
        studentsCount = '0';
        if (jsonGrade.grade.enrollments.length != null) {
            studentsCount = jsonGrade.grade.enrollments.length;    
        }
    }
    var professorsCount = 'none';
    if (jsonGrade.grade.professors != null) {
        professorsCount = '0';
        if (jsonGrade.grade.professors.length != null) {
            professorsCount = jsonGrade.grade.professors.length;    
        }
    }
    var tutorsCount = 'none';
    if (jsonGrade.grade.tutors != null) {
        tutorsCount = '0';
        if (jsonGrade.grade.tutors.length != null) {
            tutorsCount = jsonGrade.grade.tutors.length;    
        }
    }
    var coordinatorsCount = '1';
    //var graduatedStudentCount = jsonGraduatedStudentsInfo.count;

    $('grade.id').value = jsonGrade.grade.id;
    $('grade.course.id').value = jsonGrade.grade.course.id;    
    $('grade.course.name').innerHTML = jsonGrade.grade.course.name;    
    $('grade.name').innerHTML = jsonGrade.grade.name;
    $('grade.course.description').innerHTML = jsonGrade.grade.course.description;
    $('grade.coordinator.count').innerHTML = coordinatorsCount;
    $('grade.professor.count').innerHTML = professorsCount;
    $('grade.tutor.count').innerHTML = tutorsCount;
    $('grade.student.count').innerHTML = studentsCount;
    //$('grade.graduated.count').innerHTML = graduatedStudentCount;
    
    checkStatusSelect(jsonGrade.grade.status);
    
}

function showStudent(gradeId, courseId) {
    closeAll();
    $('showStudent').style.display = 'block';
            
    var i = 0;
            
    var jsonGrade = getJsonFromUrl('grade!getGradeInfo.action?grade.id=' + gradeId);
    var jsonCourse = getJsonFromUrl('course!getCourseInfo.action?course.id=' + courseId);
    //var jsonStudents = getJsonFromUrl('grade!getStudentsInfo.action?grade.id=' + gradeId);
    
    var students = '';
    /*
    if (jsonStudents.list != '') {
        students += '<ul>';
        if (jsonStudents.list.systemUser.length == null) {
            var temp = jsonStudents.list.systemUser.username;
            if (temp != undefined)
                students = '<li><input type="checkbox" id="student.id_' + 0 + '" value="' + jsonStudents.list.systemUser.id + '" />' + temp + '</li>';
        }
        else {
            for (i = 0; i < jsonStudents.list.systemUser.length; i++) {
                students += '<li><input type="checkbox" id="student.id_' + i + '" value="' + jsonStudents.list.systemUser[i].id + '" />' + jsonStudents.list.systemUser[i].username + '</li>';
            }
        }
        students += '</ul>';
    }
     */

    $('student.grade.course.id').value = jsonCourse.course.id;
    $('student.grade.id').value = jsonGrade.grade.id;
    $('student.grade.name').innerHTML = jsonGrade.grade.name;
    $('student.grade.course.name').innerHTML = jsonCourse.course.name;
   
    $('student.grade.students').innerHTML = students;
}

function showProfessor(gradeId, courseId) {
    closeAll();
    $('showProfessor').style.display = 'block';
            
    var i = 0;
            
    var jsonGrade = getJsonFromUrl('grade!getGradeInfo.action?grade.id=' + gradeId);
    var jsonCourse = getJsonFromUrl('course!getCourseInfo.action?course.id=' + courseId);
    //var jsonProfessors = getJsonFromUrl('grade!getProfessorsInfo.action?grade.id=' + gradeId);
    
    var professors = '';

    /*
    if (jsonProfessors.list != '') {
        professors += '<ul>';
        if (jsonProfessors.list.systemUser.length == null) {
            var temp = jsonProfessors.list.systemUser.username;
            if (temp != undefined)
                professors = '<li><input type="checkbox" id="professor.id_' + 0 + '" value="' + jsonProfessors.list.systemUser.id + '" />' + temp + '</li>';
        }
        else {
            for (i = 0; i < jsonProfessors.list.systemUser.length; i++) {
                professors += '<li><input type="checkbox" id="professor.id_' + i + '" value="' + jsonProfessors.list.systemUser[i].id + '" />' + jsonProfessors.list.systemUser[i].username + '</li>';
            }
        }
        professors += '</ul>';
    }
     */

    $('professor.grade.course.id').value = jsonCourse.course.id;
    $('professor.grade.id').value = jsonGrade.grade.id;
    $('professor.grade.name').innerHTML = jsonGrade.grade.name;
    $('professor.grade.course.name').innerHTML = jsonCourse.course.name;
   
    $('professor.grade.professors').innerHTML = professors;
}

function showForum(gradeId, courseId) {
    closeAll();
    $('showForum').style.display = 'block';

    var jsonGrade = getJsonFromUrl('grade!getGradeInfo.action?grade.id=' + gradeId);
    var jsonCourse = getJsonFromUrl('course!getCourseInfo.action?course.id=' + courseId);

    $('forum.show.grade.id').value = gradeId;
    $('forum.grade.course.id').value =courseId;
    
    $('forum.grade.course.name').innerHTML = jsonCourse.course.name;
    $('forum.grade.name').innerHTML = jsonGrade.grade.name;
    
    var json = getJsonFromUrl('forum!listForums.action?course.id=' + courseId + '&grade.id=' + gradeId);
    var forums = '';
    if (json != null && json != '' && json.forums != '') {
        forums += '<ul>';
        if (json.forums == null) {
            var temp = json.forums.title;
            if (temp != undefined)
                forums = '<li><input type="checkbox" id="forum.id_' + 0 + '" value="' + json.forums.id + '" />' + temp + '</li>';
        }
        else {
            for (i = 0; i <json.forums.length; i++) {
                forums += '<li><input type="checkbox" id="forum.id_' + i + '" value="' + json.forums[i].id + '" />' + json.forums[i].title + '</li>';
            }
        }
        forums += '</ul>';
    }   
    $('forum.grade.forums').innerHTML = forums;
}

function showForumId(gradeId, courseId, forumId) {
    closeAll();
    $('showForumId').style.display = 'block';

    var jsonGrade = getJsonFromUrl('grade!getGradeInfo.action?grade.id=' + gradeId);
    var jsonCourse = getJsonFromUrl('course!getCourseInfo.action?course.id=' + courseId);
    var jsonForum = getJsonFromUrl('forum!getForumInfo.action?forum.id=' + forumId);

    $('forumId.show.grade.id').value = gradeId;
    $('forumId.grade.course.id').value = courseId;
    $('forumId.grade.forum.id').value = forumId;
    
    $('forumId.grade.course.name').innerHTML = jsonCourse.course.name;
    $('forumId.grade.name').innerHTML = jsonGrade.grade.name;
    $('forumId.grade.forum.title').innerHTML = jsonForum.forum.title;
    
    var json = getJsonFromUrl('topic!listTopics.action?forum.id=' + forumId);
    var topics = '';
    if (json != null && json != '' && json.topics != '') {
        topics += '<ul>';
        if (json.topics == null) {
            var temp = json.topics.title;
            if (temp != undefined)
                topics = '<li><input type="checkbox" id="topic.id_' + 0 + '" value="' + json.topics.id + '" />' + temp + '</li>';
        }
        else {
            for (i = 0; i <json.topics.length; i++) {
                topics += '<li><input type="checkbox" id="topic.id_' + i + '" value="' + json.topics[i].id + '" />' + json.topics[i].title + '</li>';
            }
        }
        topics += '</ul>';
    }   
    $('forumId.grade.topics').innerHTML = topics;
}

function updateTutor(obj,id)
{
    var tutorDataHtml = "";
    var checks = document.getElementsByName(obj.name);
    
    checkedTutors = new Array();
    var arrayIndex = 0;

    var checkedElements = 0;
    for ( var i = 0; i < checks.length; i++ )
    {
        changeCheckboxStyle(checks[i]);
        if ( checks[i].checked )
        {
            checkedElements++;
        }
    }
    
    var multiple = "";
    if ( checkedElements > 1 )
    {
        multiple = ".multiple";
    }

    for ( var i = 0; i < checks.length; i++ )
    {
        if ( checks[i].checked )
        {
            showImage("img_div" + multiple + "." + checks[i].id);
            
            // acrescenta o id do tutor numa variável global se estiver marcado
            checkedTutors[arrayIndex++] = checks[i].value;
            
            var divContent = document.getElementById("div" + multiple + "." + checks[i].id).innerHTML;

            divContent = divContent.replace(new RegExp("display: none", "i"),"display: block");
            tutorDataHtml += divContent;
        }
    }

    tutorDataHtml += "<br class=\"clear\" />";

    $('tutorData').innerHTML = tutorDataHtml;

    /*
    if (obj.checked == true)
      $('tutorData').innerHTML = tutorDataHtml;
    else
      alert('retirar');
     */    
}

function updateProfessor(obj,id)
{
    var professorDataHtml = "";
    var checks = document.getElementsByName(obj.name);
    
    checkedProfessors = new Array();
    var arrayIndex = 0;

    var checkedElements = 0;
    for ( var i = 0; i < checks.length; i++ )
    {
        changeCheckboxStyle(checks[i]);
        if ( checks[i].checked )
        {
            checkedElements++;
        }
    }
    
    var multiple = "";
    if ( checkedElements > 1 )
    {
        multiple = ".multiple";
    }

    for ( var i = 0; i < checks.length; i++ )
    {
        if ( checks[i].checked )
        {
            showImage("img_div" + multiple + "." + checks[i].id);
            // acrescenta o id do professor numa variável global se estiver marcado
            checkedProfessors[arrayIndex++] = checks[i].value;
            
            var divContent = document.getElementById("div" + multiple + "." + checks[i].id).innerHTML;
            divContent = divContent.replace(new RegExp("display: none", "i"),"display: block");
            professorDataHtml += divContent;
        }
    }

    professorDataHtml += "<br class=\"clear\" />";

    $('professorData').innerHTML = professorDataHtml;

    /*
    if (obj.checked == true)
      $('tutorData').innerHTML = tutorDataHtml;
    else
      alert('retirar');
     */    
}

function updateStudents(obj,id)
{
    var studentDataHtml = "";
    var checks = document.getElementsByName(obj.name);
    
    checkedStudents = new Array();
    var arrayIndex = 0;

    var checkedElements = 0;
    for ( var i = 0; i < checks.length; i++ )
    {
        changeCheckboxStyle(checks[i]);
        if ( checks[i].checked )
        {
            checkedElements++;
        }
    }
    
    var multiple = "";
    if ( checkedElements > 1 )
    {
        multiple = ".multiple";
    }

    for ( var i = 0; i < checks.length; i++ )
    {
        if ( checks[i].checked )
        {
            // acrescenta o id do tutor numa variável global se estiver marcado
            showImage("img_div" + multiple + "." + checks[i].id);
            checkedStudents[arrayIndex++] = checks[i].value;
            var divContent = document.getElementById("div" + multiple + "." + checks[i].id).innerHTML;
            divContent = divContent.replace(new RegExp("display: none", "i"),"display: block");
            studentDataHtml += divContent;
        }
    }

    studentDataHtml += "<br class=\"clear\" />";

    $('studentData').innerHTML = studentDataHtml;

    /*
    if (obj.checked == true)
      $('tutorData').innerHTML = tutorDataHtml;
    else
      alert('retirar');
     */    
}

function getCheckedTutors()
{
    return checkedTutors;
}

function getCheckedProfessors()
{
    return checkedProfessors;
}

function getCheckedStudents()
{
    return checkedStudents;
}

function showTutor(gradeId, courseId) {
    closeAll();
    $('showTutor').style.display = 'block';
            
    var i = 0;
            
    var jsonGrade = getJsonFromUrl('grade!getGradeInfo.action?grade.id=' + gradeId);
    var jsonCourse = getJsonFromUrl('course!getCourseInfo.action?course.id=' + courseId);
    //var jsonTutors = getJsonFromUrl('grade!getTutorsInfo.action?grade.id=' + gradeId);
    
    var tutors = '';

    /*
    if (jsonTutors.list != '') {
        tutors += '<ul>';
        if (jsonTutors.list.systemUser.length == null) {
            var temp = jsonTutors.list.systemUser.username;
            if (temp != undefined)
                tutors = '<li><input type="checkbox" id="tutor.id_' + 0 + '" value="' + jsonTutors.list.systemUser.id + '" />' + temp + '</li>';
        }
        else {
            for (i = 0; i < jsonTutors.list.systemUser.length; i++) {
                tutors += '<li><input type="checkbox" id="tutor.id_' + i + '" value="' + jsonTutors.list.systemUser[i].id + '" />' + jsonTutors.list.systemUser[i].username + '</li>';
            }
        }
        tutors += '</ul>';
    }
     */

    $('tutor.grade.course.id').value = jsonCourse.course.id;
    $('tutor.grade.id').value = jsonGrade.grade.id;
    $('tutor.grade.name').innerHTML = jsonGrade.grade.name;
    $('tutor.grade.course.name').innerHTML = jsonCourse.course.name;
   
    $('tutor.grade.tutors').innerHTML = tutors;
}

function showEntryGrade(courseId) {
    closeAll();
    $('showEntryGrade').style.display = 'block';
    //não sei porque o radio não ficou marcado na jsp ai tive que marca-lo aqui na linha abaixo
    $('input.grade.requirestrue').checked = "checked";

    $('input.grade.id').value = '';
    if (courseId == null)
        $('input.grade.course').value = '';
    else 
        $('input.grade.course').value = courseId;
    $('input.grade.name').value = '';
    $('input.grade.period').value = '';
    $('input.grade.maxstudents').value = '';
    $('input.grade.coordinator').value = '';
    $('input.grade.maxduration').value = '';
    $('input.grade.startdatetime').value = '';
    $('input.grade.enddatetime').value = '';
    $('input.grade.status').value = 0;

    $('input.grade.name').focus();
}

function showEntryProfessor(gradeId, courseId) {
    closeAll();

    $('showEntryProfessor').style.display = 'block';
    if (gradeId == null)
        $('input.professor.grade.id').value = '';
    else
        $('input.professor.grade.id').value = gradeId;
    if (courseId == null) {
        $('input.professor.grade.course').value = '';
    }
    else {
        $('input.professor.grade.course').value = courseId;
    }
    $('input.professor.grade.course').disabled = true;
    
    $('input.professor.grade.professor').value = '';
    
    $('input.professor.grade.professor').focus();
}

function showEntryStudent(gradeId, courseId) {
    closeAll();

    $('showEntryStudent').style.display = 'block';
    if (gradeId == null)
        $('input.student.grade.id').value = '';
    else
        $('input.student.grade.id').value = gradeId;
    if (courseId == null) {
        $('input.student.grade.course').value = '';
    }
    else {
        $('input.student.grade.course').value = courseId;
    }
    $('input.student.grade.course').disabled = true;
    
    $('input.student.grade.student').value = '';
    
    $('input.student.grade.student').focus();
}

function showEntryTutor(gradeId, courseId) {
    closeAll();

    $('showEntryTutor').style.display = 'block';
    if (gradeId == null)
        $('input.tutor.grade.id').value = '';
    else
        $('input.tutor.grade.id').value = gradeId;
    if (courseId == null) {
        $('input.tutor.grade.course').value = '';
    }
    else {
        $('input.tutor.grade.course').value = courseId;
    }
    
    $('input.tutor.grade.course').disabled = true;
    
    $('input.tutor.grade.tutor').value = '';
    
    $('input.tutor.grade.tutor').focus();
}

function showEntryForum(gradeId) {
    closeAll();

    $('forum.title').value = '';
    $('forum.description').innerHTML = '';
    $('forum.description').value = '';
    $('forum.studentCreateTopic').checked = false;
    $('forum.studentUploadPost').checked = false;
    $('forum.studentUploadRepository').checked = false;
    $('forum.studentLinkPost').checked = false;
    $('forum.public1').checked = false;

    $('showEntryForum').style.display = 'block';
    if (gradeId == null)
        $('forum.grade.id').value = '';
    else
        $('forum.grade.id').value = gradeId;
}

function showEntryTopic(forumId) {
    closeAll();

    $('topic.title').value = '';
    //$('topic.description').innerHTML = '';
    $('topic.description').value = '';

    $('showEntryTopic').style.display = 'block';
    if (forumId == null)
        $('topic.forum.id').value = '';
    else
        $('topic.forum.id').value = forumId;
}

function submitForum(gradeId)
{
    var url = '';
    var params = '';
    
    if ($('forum.title').value == '' || $('forum.title').value.length < 1) {
        alert('Informe o título do fórum');
        $('forum.title').focus();
        return;
    }
    if ($('forum.description').value == '' || $('forum.description').value.length < 1) {
        alert('Informe uma descrição para o fórum');
        $('forum.description').focus();
        return;
    }
    
    new Lightbox.base('box1');

    url += 'forum!addForum.action?';
    url += 'forum.grade.id=' + gradeId;
    url += '&forum.title=' + $('forum.title').value;
    url += '&forum.description=' + $('forum.description').value;
    url += '&forum.studentCreateTopic=' + (($('forum.studentCreateTopic').checked) ? "true" : "false");
    url += '&forum.studentUploadPost=' + (($('forum.studentUploadPost').checked) ? "true" : "false");
    url += '&forum.studentUploadRepository=' + (($('forum.studentUploadRepository').checked) ? "true" : "false");
    url += '&forum.studentLinkPost=' + (($('forum.studentLinkPost').checked) ? "true" : "false");
    url += '&forum.public1=' + (($('forum.public1').checked) ? "true" : "false");
    
    var jsonForum = getJsonFromUrl(url);

    if ( (jsonForum != null) && (jsonForum.result == 'true') )
    {
        document.location = 'grade!show.action';
    }
    else
    {
        Lightbox.hideAll();
        alert('Não inserido');
    }

}

function submitTopic(forumId)
{
    var url = '';
    
    if ($('topic.title').value == '' || $('topic.title').value.length < 1) {
        alert('Informe o título do tópico');
        $('topic.title').focus();
        return;
    }
    if ($('topic.description').value == '' || $('topic.description').value.length < 1) {
        alert('Informe uma descrição para o tópico');
        $('topic.description').focus();
        return;
    }
    
    new Lightbox.base('box1');

    url += 'topic!addTopic.action?';
    url += 'topic.forum.id=' + forumId;
    url += '&topic.title=' + $('topic.title').value;
    url += '&topic.description=' + $('topic.description').value;
    
    var jsonTopic = getJsonFromUrl(url);

    if ( (jsonTopic != null) && (jsonTopic.result == 'true') )
    {
        document.location = 'grade!show.action';
    }
    else
    {
        Lightbox.hideAll();
        alert('Não inserido');
    }

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

function submitGrade(gradeId) {
    new Lightbox.base('box1');
    
    var requires = true;
    
    if($('input.grade.requiresfalse').checked){
        requires = false;
    }
        
    
    var url = '';
    var params = '';
    
    if ($('input.grade.course').value == '' || $('input.grade.course').value.length < 1) {
        Lightbox.hideAll();
        alert('Selecione algum curso');
        $('input.grade.course').focus();
        return;
    }
    if ($('input.grade.name').value == '' || $('input.grade.name').value.length < 1) {
        Lightbox.hideAll();
        alert('Infome o nome da turma');
        $('input.grade.name').focus();
        return;
    }
    if ($('input.grade.period').value == '' || $('input.grade.period').value.length < 1) {
        Lightbox.hideAll();
        alert('Infome o período da turma (Exemplo: 2008.1 para o primeiro semestre de 2008)');
        $('input.grade.period').focus();
        return;
    }    
    
    if ($('input.grade.period').value.length != 6 || 
        $('input.grade.period').value.split('.').length != 2 ||
        ! isNumber($('input.grade.period').value.substring(0, 4)) ||     
        ! isNumber($('input.grade.period').value.substring(5, 6))) {
        Lightbox.hideAll();
        alert('Informe um périodo de turma correto (Exemplo: 2008.1 para o primeiro semestre de 2008)');
        $('input.grade.period').focus();
        return;
    }      

    if ($('input.grade.maxstudents').value == '' || $('input.grade.maxstudents').value.length < 1) {
        Lightbox.hideAll();
        alert('Infome o número máximo de estudantes desta turma');
        $('input.grade.maxstudents').focus();
        return;
    }  
    if (! isNumber($('input.grade.maxstudents').value)) {
        Lightbox.hideAll();
        alert('Informe o número máximo de estudantes correto (Somente números)');
        $('input.grade.maxstudents').focus();
        return;
    }           
    if ($('input.grade.coordinator').value == '' || $('input.grade.coordinator').value.length < 1) {
        Lightbox.hideAll();
        alert('Selecione o coordenador desta turma');
        $('input.grade.coordinator').focus();
        return;
    }       
    if ($('input.grade.maxduration').value == '' || $('input.grade.maxduration').value.length < 1) {
        Lightbox.hideAll();
        alert('Informe o tempo máximo, em dias, de duração desta turma');
        $('input.grade.maxduration').focus();
        return;
    }       
    if (! isNumber($('input.grade.maxduration').value)) {
        Lightbox.hideAll();
        alert('Informe o tempo máximo de duração correto (Somente números)');
        $('input.grade.maxduration').focus();
        return;
    }       
    if ($('input.grade.startdatetime').value == '' || $('input.grade.startdatetime').value.length < 1) {
        Lightbox.hideAll();
        alert('Informe a data de início desta turma');
        $('input.grade.startdatetime').focus();
        return;
    }         
        
    //if (! isDate($('input.grade.startdatetime').value)) {
    //  alert('Informe uma data de início correta (Exemplo: 01/02/2008 para o primeiro dia de fevereiro de 2008');
    //   $('input.grade.startdatetime').focus();
    //   return;
    //}         
    
    if ($('input.grade.enddatetime').value == '' || $('input.grade.enddatetime').value.length < 1) {
        Lightbox.hideAll();
        alert('Informe a data de término desta turma');
        $('input.grade.enddatetime').focus();
        return;
    }         

    // if (! isDate($('input.grade.enddatetime').value)) {
    //    alert('Informe uma data de término correta (Exemplo: 01/02/2008 para o primeiro dia de fevereiro de 2008');
    //   $('input.grade.enddatetime').focus();
    //  return;
    //}         


    if (gradeId != null && gradeId.length > 0) {
        url += 'grade!updateGrade.action';
        params += 'grade.id=' + gradeId;
        params += '&grade.courseId=' + $('input.grade.course').value;
        params += '&grade.name=' + $('input.grade.name').value;
        params += '&grade.period=' + $('input.grade.period').value;
        params += '&grade.maxStudents=' + $('input.grade.maxstudents').value;
        params += '&grade.coordinatorId=' + $('input.grade.coordinator').value;
        params += '&grade.maxDuration=' + $('input.grade.maxduration').value;
        params += '&startDate=' + formatDate($('input.grade.startdatetime').value);
        params += '&endDate=' + formatDate($('input.grade.enddatetime').value);
        params += '&status=' + $('input.grade.status').value;
        params += '&requires=' + requires;
        //alert(url);

    }
    else {
        url += 'grade!addGrade.action';
        params += 'grade.courseId=' + $('input.grade.course').value;
        params += '&grade.name=' + $('input.grade.name').value;
        params += '&grade.period=' + $('input.grade.period').value;
        params += '&grade.maxStudents=' + $('input.grade.maxstudents').value;
        params += '&grade.coordinatorId=' + $('input.grade.coordinator').value;
        params += '&grade.maxDuration=' + $('input.grade.maxduration').value;
        params += '&startDate=' + formatDate($('input.grade.startdatetime').value);
        params += '&endDate=' + formatDate($('input.grade.enddatetime').value);
        params += '&status=' + $('input.grade.status').value;
        params += '&requires=' + requires;
    }
    jsonGrade = getJsonFromUrl(url + '?' + params);
    if (gradeId != null && gradeId.length > 0) {
        if (jsonGrade != null && jsonGrade.grade != null && jsonGrade.grade.id != '') {
            document.location = 'grade!show.action';
        }
        else {
            Lightbox.hideAll();
            alert('Não alterado');
        }
    }
    else {
        if (jsonGrade != null && jsonGrade.grade != null && jsonGrade.grade.id != '') {
            document.location = 'grade!show.action';
        }
        else {
            Lightbox.hideAll();
            alert('Não inserido');
        }
    }
    
}

function deleteGradeId(gradeId) {
    new Lightbox.base('box1');
    var url = '';
    if (gradeId != null && gradeId.length > 0) {
        url += 'grade!removeGrade.action?';
        url += 'grade.id=' + gradeId;
    }
    var jsonGrade = getJsonFromUrl(url);
    if (jsonGrade != null && jsonGrade.result == true) {
        document.location = 'grade!show.action';
    }
    else {
        Lightbox.hideAll();
        alert('Não removido');
    }
}

function deleteForum() {
    new Lightbox.base('box1');
    var check = 0;
    var i = 0;
    var checkIds = new Array();
    while (check != null) {
        check = document.getElementById('forum.id_' + i);
        if (check != null && check.checked == true) {
            checkIds[checkIds.length] = check;
        }
        i++;
    }
    if (checkIds == null || checkIds.length < 1) {
        Lightbox.hideAll();
        alert('Selecione pelo menos um fórum');
        return;
    }
    for (i = 0; i < checkIds.length; i++) {
        var url = '';
        var forumId = checkIds[i].value;
        if (forumId != null && forumId.length > 0) {
            url += 'forum!removeForum.action?';
            url += 'forum.id=' + forumId;
        }
        var jsonForum = getJsonFromUrl(url);
        if (jsonForum != null && jsonForum.result == 'true') {
            document.location = 'grade!show.action';
        }
        else {
            Lightbox.hideAll();
            alert('Não removido');
        }
    }
}

function deleteTopic() {
    new Lightbox.base('box1');
    var check = 0;
    var i = 0;
    var checkIds = new Array();
    while (check != null) {
        check = document.getElementById('topic.id_' + i);
        if (check != null && check.checked == true) {
            checkIds[checkIds.length] = check;
        }
        i++;
    }
    if (checkIds == null || checkIds.length < 1) {
        Lightbox.hideAll();
        alert('Selecione pelo menos um tópico');
        return;
    }
    for (i = 0; i < checkIds.length; i++) {
        var url = '';
        var topicId = checkIds[i].value;
        if (topicId != null && topicId.length > 0) {
            url += 'topic!removeTopic.action?';
            url += 'topic.id=' + topicId;
        }
        var jsonTopic = getJsonFromUrl(url);
        if (jsonTopic != null && jsonTopic.result == 'true') {
            document.location = 'grade!show.action';
        }
        else {
            Lightbox.hideAll();
            alert('Não removido');
        }
    }
}

function deleteGrade() {
    new Lightbox.base('box1');
    var check = 0;
    var i = 0;
    var checkIds = new Array();
    while (check != null) {
        check = document.getElementById('grade.id_' + i);
        if (check != null && check.checked == true) {
            checkIds[checkIds.length] = check;
        }
        i++;
    }
    if (checkIds == null || checkIds.length < 1) {
        Lightbox.hideAll();
        alert('Selecione pelo menos uma turma');
        return;
    }
    for (i = 0; i < checkIds.length; i++) {
        var url = '';
        var gradeId = checkIds[i].value;
        if (gradeId != null && gradeId.length > 0) {
            url += 'grade!removeGrade.action?';
            url += 'grade.id=' + gradeId;
        }
        var jsonGrade = getJsonFromUrl(url);
        if (jsonGrade != null && jsonGrade.result == true) {
            document.location = 'grade!show.action';
        }
        else {
            Lightbox.hideAll();
            alert('Não removido');
        }
    }
}

function deleteStudent(gradeId) {
    new Lightbox.base('box1');
    var check = 0;
    var i = 0;
    var checkIds = new Array();
    while (check != null) {
        check = document.getElementById('student.id_' + gradeId + '_' + i);
        if (check != null && check.checked == true) {
            checkIds[checkIds.length] = check;
        }
        i++;
    }
    if (checkIds == null || checkIds.length == 0) {
        Lightbox.hideAll();
        alert('Selecione pelo menos um estudante desta turma');
        return;
    }
    var c = 0;
    for (i = 0; i < checkIds.length; i++) {
        var url = '';
        var studentId = checkIds[i].value;
        if (studentId != null && studentId.length > 0) {
            url += 'grade!removeStudent.action?';
            url += 'systemUser.id=' + studentId;
            url += '&grade.id=' + gradeId;
        }
        var jsonStudent = getJsonFromUrl(url);
        if (jsonStudent != null && jsonStudent.result == true) {
            c++;
        }
    }
    if (c > 0)
        document.location = 'grade!show.action';
}

function deleteProfessor(gradeId) {
    new Lightbox.base('box1');
    var check = 0;
    var i = 0;
    var checkIds = new Array();
    while (check != null) {
        check = document.getElementById('professor.id_' + gradeId + '_' + i);
        if (check != null && check.checked == true) {
            checkIds[checkIds.length] = check;
        }
        i++;
    }
    if (checkIds == null || checkIds.length == 0) {
        Lightbox.hideAll();
        alert('Selecione pelo menos um professor desta turma');
        return;
    }
    var c = 0;
    for (i = 0; i < checkIds.length; i++) {
        var url = '';
        var professorId = checkIds[i].value;
        if (professorId != null && professorId.length > 0) {
            url += 'grade!removeProfessor.action?';
            url += 'systemUser.id=' + professorId;
            url += '&grade.id=' + gradeId;
        }
        var jsonProfessor = getJsonFromUrl(url);
        if (jsonProfessor != null && jsonProfessor.result == true) {
            c++;
        }
    }
    if (c > 0)
        document.location = 'grade!show.action';
}

function deleteTutor(gradeId) {
    new Lightbox.base('box1');
    var check = 0;
    var i = 0;
    var checkIds = new Array();
    while (check != null) {
        check = document.getElementById('tutor.id_' + gradeId + '_' + i);
        if (check != null && check.checked == true) {
            checkIds[checkIds.length] = check;
        }
        i++;
    }
    if (checkIds == null || checkIds.length == 0) {
        Lightbox.hideAll();
        alert('Selecione pelo menos um tutor desta turma');
        return;
    }
    var c = 0;
    for (i = 0; i < checkIds.length; i++) {
        var url = '';
        var tutorId = checkIds[i].value;
        if (tutorId != null && tutorId.length > 0) {
            url += 'grade!removeTutor.action?';
            url += 'systemUser.id=' + tutorId;
            url += '&grade.id=' + gradeId;
        }
        var jsonTutor = getJsonFromUrl(url);
        if (jsonTutor != null && jsonTutor.result == true) {
            c++;
        }
    }
    if (c > 0)
        document.location = 'grade!show.action';

}

function submitProfessor(gradeId, professorId) {
    new Lightbox.base('box1');
    if (professorId == '') {
        Lightbox.hideAll();
        alert('Infome o professor da turma');
        return;
    }    
    var url = '';
    if (gradeId != null && gradeId.length > 0 && professorId != null && professorId.length > 0) {
        url += 'grade!addProfessor.action?';
        url += 'grade.id=' + gradeId;
        url += '&systemUser.id=' + professorId;
    }
    jsonGrade = getJsonFromUrl(url);
    if (jsonGrade != null && jsonGrade.grade != null && jsonGrade.grade.id != '') {
        document.location = 'grade!show.action';
    }
    else {
        Lightbox.hideAll();
        alert('Não inserido');
    }
}

function deleteProfessorId(gradeId, professorId) {
    var url = '';
    if (gradeId != null && gradeId.length > 0 && professorId != null && professorId.length > 0) {
        url += 'grade!removeProfessor.action?';
        url += 'grade.id=' + gradeId;
        url += '&systemUser.id=' + professorId;
    }
    var jsonGrade = getJsonFromUrl(url);
    if (jsonGrade != null && jsonGrade.result == true) {
        document.location = 'grade!show.action';
    }
    else {
        alert('Não removido');
    }
}

function submitTutor(gradeId, tutorId) {
    new Lightbox.base('box1');
    if (tutorId == '') {
        Lightbox.hideAll();
        alert('Infome o tutor da turma');
        return;
    }        
    var url = '';
    if (gradeId != null && gradeId.length > 0 && tutorId != null && tutorId.length > 0) {
        url += 'grade!addTutor.action?';
        url += 'grade.id=' + gradeId;
        url += '&systemUser.id=' + tutorId;
    }
    jsonGrade = getJsonFromUrl(url);
    if (jsonGrade != null && jsonGrade.grade != null && jsonGrade.grade.id != '') {
        document.location = 'grade!show.action';
    }
    else {
        Lightbox.hideAll();
        alert('Não inserido');
    }
}

function submitStudent(gradeId, systemUserId) {
    
    new Lightbox.base('box1');
    
    if (systemUserId == '') {
        Lightbox.hideAll();
        alert('Infome o aluno da turma');
        return;
    }            
    var url = '';
    if (gradeId != null && gradeId.length > 0 && systemUserId != null && systemUserId.length > 0) {
        url += 'grade!addStudent.action?';
        url += 'grade.id=' + gradeId;
        url += '&systemUser.id=' + systemUserId;
    }
    jsonGrade = getJsonFromUrl(url);
    if (jsonGrade != null && jsonGrade.grade != null && jsonGrade.grade.id != '') {
        document.location = 'grade!show.action';
    }
    else {
        Lightbox.hideAll();
        alert('Não inserido');
    }
}

function deleteTutorId(gradeId, tutorId) {
    var url = '';
    if (gradeId != null && gradeId.length > 0 && tutorId != null && tutorId.length > 0) {
        url += 'grade!removeTutor.action?';
        url += 'grade.id=' + gradeId;
        url += '&systemUser.id=' + tutorId;
    }
    var jsonGrade = getJsonFromUrl(url);
    if (jsonGrade != null && jsonGrade.result == true) {
        document.location = 'grade!show.action';
    }
    else {
        alert('Não removido');
    }
}

function updateStudent(id, username, email, createdAt) {
    alert(username);
}

function showEditGradeById(gradeId) 
{
    closeAll();
    $('showEntryGrade').style.display = 'block';

    var jsonGrade = getJsonFromUrl('grade!getGradeInfo.action?grade.id=' + gradeId);
    $('input.grade.id').value = jsonGrade.grade.id;
    $('input.grade.course').value = jsonGrade.grade.course.id;
    $('input.grade.name').value = jsonGrade.grade.name;
    $('input.grade.period').value = jsonGrade.grade.period;
    $('input.grade.maxstudents').value = jsonGrade.grade.maxStudents;
    $('input.grade.coordinator').value = jsonGrade.grade.coordinatorId;
    $('input.grade.maxduration').value = jsonGrade.grade.maxDuration;
    $('input.grade.status').value = jsonGrade.grade.status;
    if(jsonGrade.grade.requiresEnrollmentValidation)
        $('input.grade.requirestrue').checked = "checked";
    else
        $('input.grade.requiresfalse').checked = "checked";
    var startDate = '';
    if (jsonGrade.grade.startDatetime.$ != null) {
        startDate = jsonGrade.grade.startDatetime.$.substring(5, 7)  + '/' + jsonGrade.grade.startDatetime.$.substring(8, 10) + '/' + jsonGrade.grade.startDatetime.$.substring(0, 4);
    }
    var endDate = '';
    if (jsonGrade.grade.endDatetime.$ != null) {
        endDate = jsonGrade.grade.endDatetime.$.substring(5, 7)  + '/' + jsonGrade.grade.endDatetime.$.substring(8, 10) + '/' + jsonGrade.grade.endDatetime.$.substring(0, 4);
    }
    $('input.grade.startdatetime').value = startDate;
    $('input.grade.enddatetime').value = endDate;

    $('input.grade.name').focus();
}

// Retorna as turmas selecionadas
function getCheckedGrades()
{
    var check = 0;
    var i = 0;
    var checkIds = new Array();
    while (check != null) {
        check = document.getElementById('grade.id_' + i);
        if (check != null && check.checked == true) {
            checkIds[checkIds.length] = check.value;
        }
        i++;
    }

    return checkIds;
}

function showEditGrade() {
    
    var gradeIds = getCheckedGrades();
    var gradeCount = gradeIds.length;

    if ( gradeCount == 0 )
    {
        alert('Por favor, selecione a turma a ser editada.');
        return;
    }
    else if ( gradeCount > 1 )
    {
        alert('Por favor, selecione apenas uma turma a ser editada.');
        return;
    }
    
    var gradeId = gradeIds[0];
    closeAll();
    $('showEntryGrade').style.display = 'block';

    var jsonGrade = getJsonFromUrl('grade!getGradeInfo.action?grade.id=' + gradeId);
    $('input.grade.id').value = jsonGrade.grade.id;
    $('input.grade.course').value = jsonGrade.grade.course.id;
    $('input.grade.name').value = jsonGrade.grade.name;
    $('input.grade.period').value = jsonGrade.grade.period;
    $('input.grade.maxstudents').value = jsonGrade.grade.maxStudents;
    $('input.grade.coordinator').value = jsonGrade.grade.coordinatorId;
    $('input.grade.maxduration').value = jsonGrade.grade.maxDuration;
    if(jsonGrade.grade.requiresEnrollmentValidation)
        $('input.grade.requirestrue').checked = "checked";
    else
        $('input.grade.requiresfalse').checked = "checked";
    var startDate = '';
    if (jsonGrade.grade.startDatetime.$ != null) {
        startDate = jsonGrade.grade.startDatetime.$.substring(5, 7)  + '/' + jsonGrade.grade.startDatetime.$.substring(8, 10) + '/' + jsonGrade.grade.startDatetime.$.substring(0, 4);
    }
    var endDate = '';
    if (jsonGrade.grade.endDatetime.$ != null) {
        endDate = jsonGrade.grade.endDatetime.$.substring(5, 7)  + '/' + jsonGrade.grade.endDatetime.$.substring(8, 10) + '/' + jsonGrade.grade.endDatetime.$.substring(0, 4);
    }
    $('input.grade.startdatetime').value = startDate;
    $('input.grade.enddatetime').value = endDate;

    $('input.grade.name').focus();

}

function closeAllMessages() {
    $('course.message').style.display = 'none';
    $('course.newsflash').style.display = 'none';
    $('grade.message').style.display = 'none';
    $('grade.newsflash').style.display = 'none';
    $('student.message').style.display = 'none';
    $('student.newsflash').style.display = 'none';    
    $('student.note').style.display = 'none';    
    $('professor.message').style.display = 'none';
    $('professor.newsflash').style.display = 'none';    
    $('tutor.message').style.display = 'none';
    $('tutor.newsflash').style.display = 'none';

    $('input.course.message.title').value = '';  
    $('input.course.message.description').value = '';  
    $('input.course.newsflash.message').value = '';  
    $('input.grade.message.title').value = '';  
    $('input.grade.message.description').value = '';  
    $('input.grade.newsflash.message').value = '';  
    $('input.student.message.title').value = '';  
    $('input.student.message.description').value = '';  
    $('input.student.newsflash.message').value = '';  
    $('input.professor.message.title').value = '';  
    $('input.professor.message.description').value = '';  
    $('input.professor.newsflash.message').value = ''; 
    $('input.tutor.message.title').value = '';  
    $('input.tutor.message.description').value = '';  
    $('input.tutor.newsflash.message').value = '';     
    $('input.student.note.what').value = '';  
    $('input.student.note.where').value = '';  
    $('input.student.note.dtStart').value = '';  
    $('input.student.note.dtEnd').value = '';  
    $('input.student.note.description').value = '';     
}

function sendMessageGradeId(id, title, description) {
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
    if (title.value == '' || title.value.length < 1) {
        alert('Entre com o título da mensagem');
        title.focus();
        return;
    }        
    if (description.value == '' || description.value.length < 1) {
        alert('Entre com a mensagem');
        description.focus();
        return;
    }    
    var grades = getCheckedGrades();
    if (grades == '' || grades.length < 1) {
        alert('Selecione pelo menos uma turma');
        return;
    }        
    var i = 0;
    var gradeIds = '';
    for (i = 0; grades != null && i < grades.length; i++) {
        gradeIds += grades[i];
        if (i != grades.length - 1)
            gradeIds += ';'
    }
    var url = 'grade!sendMessageGrades.action';
    var params = '';
    params += 'gradeIds=' + gradeIds;
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
    if (message.value == '' || message.value.length < 1) {
        alert('Entre com a mensagem');
        message.focus();
        return;
    }    
    var grades = getCheckedGrades();
    if (grades == '' || grades.length < 1) {
        alert('Selecione pelo menos uma turma');
        return;
    }            
    var i = 0;
    var gradeIds = '';
    for (i = 0; grades != null && i < grades.length; i++) {
        gradeIds += grades[i];
        if (i != grades.length - 1)
            gradeIds += ';'
    }
    var url = 'grade!sendNewsFlashGrades.action';
    var params = '';
    params += 'gradeIds=' + gradeIds;
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
    if (title.value == '' || title.value.length < 1) {
        alert('Entre com o título da mensagem');
        title.focus();
        return;
    }        
    if (description.value == '' || description.value.length < 1) {
        alert('Entre com a mensagem');
        description.focus();
        return;
    }        
    var students = getCheckedStudents();
    if (students == '' || students.length < 1) {
        alert('Selecione pelo menos um estudante desta turma');
        return;
    }       
    var i = 0;
    var studentIds = '';  
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
        alert('Mensagem enviada');
        title.value = '';
        description.value = '';
    }
    else {
        alert('Mensagem não enviada');
    }        
}

function sendNoteStudent(dtStart, dtEnd, where, what, description) {
    if (what.value == '' || what.value.length < 1) {
        alert('Entre com o assunto do compromisso');
        what.focus();
        return;
    }        
    if (where.value == '' || where.value.length < 1) {
        alert('Entre com o local onde acontecerá o compromisso');
        where.focus();
        return;
    }        
    if (dtStart.value == '' || dtStart.value.length < 1) {
        alert('Entre com a data de início realização do compromisso');
        dtStart.focus();
        return;
    }        
    if (dtEnd.value == '' || dtEnd.value.length < 1) {
        dtEnd.value = dtStart.value;
    }        
    if (description.value == '' || description.value.length < 1) {
        alert('Entre com a descrição do compromisso');
        description.focus();
        return;
    }            
    var students = getCheckedStudents();
    if (students == '' || students.length < 1) {
        alert('Selecione pelo menos um estudante desta turma');
        return;
    }       
    var i = 0;
    var studentIds = '';  
    for (i = 0; i < students.length; i++) {
        studentIds += students[i];
        if (i != students.length - 1)
            studentIds += ';';
    }
    dtStart.value = dtStart.value.substring(6, 10) + dtStart.value.substring(3, 5) + dtStart.value.substring(0, 2) + dtStart.value.substring(11, 13) + dtStart.value.substring(14, 16) + dtStart.value.substring(17, 19);
    dtEnd.value = dtEnd.value.substring(6, 10) + dtEnd.value.substring(3, 5) + dtEnd.value.substring(0, 2) + dtEnd.value.substring(11, 13) + dtEnd.value.substring(14, 16) + dtEnd.value.substring(17, 19);
    var url = 'grade!sendNoteUsers.action';
    var params = '';
    params += 'systemUserIds=' + studentIds;
    params += '&what=' + what.value;
    params += '&where=' + where.value;
    params += '&dtStart=' + dtStart.value;
    params += '&dtEnd=' + dtEnd.value;
    params += '&description=' + description.value;
    var json = getJsonFromUrl(url + '?' + params);
    if ((json.result + '') == 'true') {
        alert('Compromisso enviado');
        what.value = '';
        where.value = '';
        dtStart.value = '';
        dtEnd.value = '';
        description.value = '';
    }
    else {
        alert('Compromisso não enviado');
    }        
}

function sendNewsFlashStudent(message) {
    if (message.value == '' || message.value.length < 1) {
        alert('Entre com a mensagem');
        message.focus();
        return;
    }        
    var students = getCheckedStudents();
    if (students == '' || students.length < 1) {
        alert('Selecione pelo menos um estudante desta turma');
        return;
    }          
    var i = 0;
    var studentIds = '';
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
        alert('Mensagem enviada');
        message.value = '';
    }
    else {
        alert('Mensagem não enviada');
    }        
}

function sendMessageProfessor(title, description) {
    if (title.value == '' || title.value.length < 1) {
        alert('Entre com o título da mensagem');
        title.focus();
        return;
    }        
    if (description.value == '' || description.value.length < 1) {
        alert('Entre com a mensagem');
        description.focus();
        return;
    }        
    var professors = getCheckedProfessors();
    if (professors == '' || professors.length < 1) {
        alert('Selecione pelo menos um professor desta turma');
        return;
    }          
    var i = 0;
    var professorIds = '';  
    for (i = 0; i < professors.length; i++) {
        professorIds += professors[i];
        if (i != professors.length - 1)
            professorIds += ';';
    }
    var url = 'grade!sendMessageUsers.action';
    var params = '';
    params += 'systemUserIds=' + professorIds;
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

function sendNewsFlashProfessor(message) {
    if (message.value == '' || message.value.length < 1) {
        alert('Entre com a mensagem');
        message.focus();
        return;
    }        
    var professors = getCheckedProfessors();
    if (professors == '' || professors.length < 1) {
        alert('Selecione pelo menos um professor desta turma');
        return;
    }          
    var i = 0;
    var professorIds = '';
    for (i = 0; i < professors.length; i++) {
        professorIds += professors[i];
        if (i != professors.length - 1)
            professorIds += ';';
    }
    var url = 'grade!sendNewsFlashUsers.action';
    var params = '';
    params += 'systemUserIds=' + professorIds;
    params += '&message=' + message.value;
    var json = getJsonFromUrl(url + '?' + params);
    if ((json.result + '') == 'true') {
        alert('Mensagem enviada');
        message.value = ''
    }
    else {
        alert('Mensagem não enviada');
    }        
}

function sendMessageTutor(title, description) {
    if (title.value == '' || title.value.length < 1) {
        alert('Entre com o título da mensagem');
        title.focus();
        return;
    }        
    if (description.value == '' || description.value.length < 1) {
        alert('Entre com a mensagem');
        description.focus();
        return;
    }        
    var tutors = getCheckedTutors();
    if (tutors == '' || tutors.length < 1) {
        alert('Selecione pelo menos um tutor desta turma');
        return;
    }          
    var i = 0;
    var tutorIds = '';
    for (i = 0; i < tutors.length; i++) {
        tutorIds += tutors[i];
        if (i != tutors.length - 1)
            tutorIds += ';';
    }
    var url = 'grade!sendMessageUsers.action';
    var params = '';
    params += 'systemUserIds=' + tutorIds;
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

function sendNewsFlashTutor(message) {     
    if (message.value == '' || message.value.length < 1) {
        alert('Entre com a mensagem');
        message.focus();
        return;
    }        
    var tutors = getCheckedTutors();
    if (tutors == '' || tutors.length < 1) {
        alert('Selecione pelo menos um tutor desta turma');
        return;
    }              
    var i = 0;
    var tutorIds = '';
    for (i = 0; i < tutors.length; i++) {
        tutorIds += tutors[i];
        if (i != tutors.length - 1)
            tutorIds += ';';
    }
    var url = 'grade!sendNewsFlashUsers.action';
    var params = '';
    params += 'systemUserIds=' + tutorIds;
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

function checkAllFieldsByName(fieldName,gradeId,type)
{
    var checks = document.getElementsByName(fieldName);
    for (i = 0; i < checks.length; i++)
    {
        if ( checks[i].id.indexOf('_' + gradeId + '_') > 0 )
        {
            checks[i].checked = true ;                            
        }
    }

    if ( checks[0] != null )
    {
        if ( type == "students" )
        {
            updateStudents(checks[0], checks[0].id);            
        }
        else if ( type == "professors" )
        {
            updateProfessor(checks[0], checks[0].id);            
        }
        else if ( type == "tutors" )
        {
            updateTutor(checks[0], checks[0].id);            
        }
    }

}

function unCheckAllFieldsByName(fieldName,divId)
{
    var checks = document.getElementsByName(fieldName);
    for (i = 0; i < checks.length; i++)
    {
        checks[i].checked = false ;
        changeCheckboxStyle(checks[i]);
    }

    $(divId).innerHTML = '';
}

function changeCheckboxStyle(obj)
{
    if ( $('li.' + obj.id ) != null )
    {
        if ( obj.checked )
        {
            $('li.' + obj.id ).style.backgroundColor = "#FFE79C";
            $('li.' + obj.id ).style.border = "1px solid #FF7202";
        }
        else
        {
            $('li.' + obj.id ).style.backgroundColor = "#FFFFFF";
            $('li.' + obj.id ).style.border = "1px solid #D9E5F2";
        }
    }
}

function mouseOverPerson(id)
{
    $('li.' + id ).style.backgroundColor = "#FFE79C";
    $('li.' + id ).style.border = "1px solid #FF7202";
}

function mouseOutPerson(id)
{
    if ( !$(id).checked )
    {
        $('li.' + id ).style.backgroundColor = "#FFFFFF";
        $('li.' + id ).style.border = "1px solid #D9E5F2";
    }
}

function isNumber(param)  {
    var numericExpression = /^[0-9]+$/;
    if (param.match(numericExpression)) {
        return true;
    }
    else {
        return false;
    }
}

function isDate(data) {
    var expReg = /^(([0-2]\d|[3][0-1])\/([0]\d|[1][0-2])\/[1-2][0-9]\d{2})$/;
    //var msgErro = 'Formato inválido de data.';
    if ((data.match(expReg)) && (data.value !='')) {        
        var dia = data.value.substring(0,2);
        var mes = data.value.substring(3,5);
        var ano = data.value.substring(6,10);
        if (mes == 4 || mes == 6 || mes == 9 || mes == 11 && dia > 30) {
            //alert("Dia incorreto !!! O mês especificado contém no máximo 30 dias.");
            return false;
        } 
        else {
            if (ano % 4 != 0 && mes == 2 && dia > 28) {
                //alert("Data incorreta!! O mês especificado contém no máximo 28 dias.");
                return false;
            } 
            else {
                if (ano % 4 == 0 && mes == 2 && dia > 29) {
                    //alert("Data incorreta!! O mês especificado contém no máximo 29 dias.");
                    return false;
                } 
                else {
                    //alert ("Data correta!");
                    return true;
                }
            }
        }
    } 
    else {
        //alert(msgErro);
        //campo.focus();
        return false;
    }
}

function showSendNewsFlash(nfId)
{
    showDiv(nfId);
}

function showSendMessage(msgId)
{
    showDiv(msgId);
}

function showDiv(divId)
{
    $('input.course.message.description.len').value = '250';
    $('input.course.newsflash.message.len').value = '250';
    $('input.grade.message.description.len').value = '250';
    $('input.grade.newsflash.message.len').value = '250';
    $('input.student.message.description.len').value = '250';
    $('input.student.newsflash.message.len').value = '250';
    $('input.professor.message.description.len').value = '250';
    $('input.professor.newsflash.message.len').value = '250';
    $('input.tutor.message.description.len').value = '250';
    $('input.tutor.newsflash.message.len').value = '250';

    closeAllMessages(); 
    $(divId).style.display = 'block';
}

function checkStatusGrade(li){
    
    box = li.getElementsByTagName('input')[0];
    if(box.checked == ''){
    
        checkStatusSelect(box.value);
        
        url = 'grade!updateStatusGrade.action?grade.id=' +$('grade.id').value+'&status='+box.value;
    
        var json = getJsonFromUrl(url);
    
        if(json.update)
            $('imageStatus_'+$('grade.id').value).src = '../images/icon/status-grade-'+box.value+'.gif';
        else
            alert('Não foi possível mudar o estatus!');
    }
    
}

function checkStatusSelect(status){

    if($('grade.status'+status).checked == ''){
        boxes = $('ulStatus').getElementsByTagName('input');
        
        for(i=0; i < boxes.length ; i++){
            boxes[i].checked = ""
            boxes[i].parentNode.className = '';
        }
        
        $('grade.status'+status).checked = 'checked';
        $('grade.status'+status).parentNode.className = 'selected';
    }
}