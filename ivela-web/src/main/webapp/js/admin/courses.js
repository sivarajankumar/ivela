/* Funcionalidades de Cursos */

function showCourse(courseId) {
    closeAll();
    $('showCourse').style.display = 'block';
            
    var i = 0;
    
    var json = getJsonFromUrl('course!getCourseInfoJson.action?course.id=' + courseId);
            
    var professors = '';
    var tutors = '';
    var requeriments = '';
    var targetPublic = json.course.targetAudience;
    var disciplines = '';

    var studentsCount = json.course.studentsCount;                
    var gradesCount = json.course.gradesCount;
    var graduatedStudentCount = json.course.graduatedStudentCount;
    var image = json.course.image;

    /* Tratamento da lista de professores */
    if (json.course.professors != '') {
        professors += '<p>';
        if (json.course.professors.length == null) {
            var temp = json.course.professors.name;
            if (temp != undefined)
                professors = temp + '.';
        }
        else {
            for (i = 0; i < json.course.professors.length; i++) {
                var temp = json.course.professors[i].name;
                if (temp != undefined){
                    professors += temp
                    if (i != json.course.professors.length - 1)
                        professors += ',&nbsp;';
                    else
                        professors += '.';
                }
            }
        }
        professors += '</p>';
    }

    if (json.course.tutors.list != '') {
        tutors += '<p>';
        if (json.course.tutors.length == null) {
            var temp = json.course.tutors.name;
            if (temp != undefined)
                tutors = temp + '.'
        }
        else {
            for (i = 0; i < json.course.tutors.length; i++) {
                temp = json.course.tutors[i].name;
                if (temp != undefined) {
                    tutors += json.course.tutors[i].username;
                    if (i != json.course.tutors.length - 1)
                        tutors += ',&nbsp;';
                    else
                        tutors += '.';
                }
            }
        }
        tutors += '</p>';
    }

    if (json.course.disciplines != '') {
        disciplines += '<ul>';
        if (json.course.disciplines.length == null) {
            var temp = json.course.disciplines.name;
            if (temp != undefined)
                disciplines = '<li><input type="checkbox" id="discipline.id_' + 0 + '" value="' + json.course.disciplines.id + '" />' + temp + '</li>';
        }
        else {
            for (i = 0; i < json.course.disciplines.length; i++) {
                disciplines += '<li><input type="checkbox" id="discipline.id_' + i + '" value="' + json.course.disciplines[i].id + '" />' + json.course.disciplines[i].name + '</li>';
              
            }
        }
        disciplines += '</ul>';
    }
            
    /* Tratamento da lista de requisitos */
    if (json.course.requisites != '') {
        requeriments += '<ul>';
        if (json.course.requisites.length == null) {
            var temp = json.course.requisites.name;
            if (temp != undefined)
                requeriments = '<li>' + temp + '</li>';
        }
        else {
            for (i = 0; i < json.course.requisites.length; i++) {
                requeriments += '<li>' + json.course.requisites[i].name + '</li>';
            }
        }
        requeriments += '</ul>';
    }

    $('course.id').value = json.course.id;
    $('course.name').innerHTML = json.course.name;
    $('course.description').innerHTML = json.course.description;
    $('course.student.count').innerHTML = studentsCount;
    $('course.grade.count').innerHTML = gradesCount;
    $('course.professors').innerHTML = professors;
    $('course.tutors').innerHTML = tutors;
    $('course.requirements').innerHTML = requeriments;
    $('course.target.public').innerHTML = targetPublic;
    $('course.disciplines').innerHTML = disciplines;
    $('course.image').src = '../RenderServletPartner?id=' + $('course.id').value;
}

function showEntryCourse() {
    closeAll();
    $('showEntryCourse').style.display = 'block';
    $('input.course.challengeItens.yes').checked="checked";
	$('input.course.uploadPackage.yes').checked="checked";
	$('input.course.customToc.yes').checked="checked";    
    $('input.course.id').value = '';
    $('input.course.name').value = '';
    $('input.course.repository').value = '';
    $('input.course.description').value = '';
    $('input.course.targetAudience').value = '';
    $('input.course.contents').value = '';
    $('input.course.uploadPackage').value = '';
    $('input.course.challengeItens').value = '';
    $('input.course.customToc').value = '';
    $('input.course.name').focus();
}

function showEditCourse(courseId) {
    closeAll();
    $('showEntryCourse').style.display = 'block';
            
    var jsonCourse = getJsonFromUrl('course!getCourseInfo.action?course.id=' + courseId);
            
    $('input.course.id').value = jsonCourse.course.id;
    $('input.course.repository').value = jsonCourse.course.repositoryStructure;
    $('input.course.name').value = jsonCourse.course.name;
    $('input.course.description').value = jsonCourse.course.description;
    $('remLen').value = (250 - $('input.course.description').value.length);

    $('input.course.targetAudience').value = jsonCourse.course.targetAudience;
    $('remLenTargetAudience').value = (250 - $('input.course.targetAudience').value.length);
    
    $('input.course.contents').value = jsonCourse.course.contents;
    $('remLenContents').value = (500 - $('input.course.contents').value.length);
    
    var uploadPackageEnabled = jsonCourse.course.uploadPackageEnabled;
    var challengeItensEnabled  = jsonCourse.course.challengeItensEnabled;
    var customToc  = jsonCourse.course.customToc;

    if(jsonCourse.course.challengeItensEnabled)
    	$('input.course.challengeItens.yes').checked="checked";
    else
    	$('input.course.challengeItens.no').checked="checked";    	
    
    if (jsonCourse.course.uploadPackageEnabled)
    	$('input.course.uploadPackage.yes').checked="checked";    
    else
    	$('input.course.uploadPackage.no').checked="checked";
    
    if (jsonCourse.course.customToc)
    	$('input.course.customToc.yes').checked="checked";    
    else
    	$('input.course.customToc.no').checked="checked";
 
    	
    $('input.course.name').focus();
}
        
function submitCourse(courseId) {
    new Lightbox.base('box1');
    var url = '';
    if ($('input.course.name').value == '' || $('input.course.name').value.length < 1) {
        Lightbox.hideAll();
        alert('Informe o nome do curso');
        $('input.course.name').focus();
        return;
    }
    if ($('input.course.description').value == '' || $('input.course.description').value.length < 1) {
        Lightbox.hideAll();
        alert('Informe a descrição do curso');
        $('input.course.description').focus();
        return;
    }
    if ($('input.course.targetAudience').value == '' || $('input.course.targetAudience').value.length < 1) {
        Lightbox.hideAll();
        alert('Informe o público alvo do curso');
        $('input.course.targetAudience').focus();
        return;
    }   
    if ($('input.course.contents').value == '' || $('input.course.contents').value.length < 1) {
        Lightbox.hideAll();
        alert('Informe a ementa do curso');
        $('input.course.contents').focus();
        return;
    }        
   
    
    var challengeItensEnabled = false;
    var uploadPackageEnabled = false;
    var customToc = false;
    
    if ($('input.course.challengeItens.yes').checked) {
    	challengeItensEnabled = true;
    	
    }
    
    if ($('input.course.uploadPackage.yes').checked) {
    	uploadPackageEnabled = true;
    }
    
    if ($('input.course.customToc.yes').checked) {
    	customToc = true;
    }
    
    
    if (courseId != null && courseId.length > 0) {
        url += 'course!updateCourse.action?';
        url += 'course.id=' + courseId;
        url += '&course.name=' + $('input.course.name').value;
        url += '&course.repositoryStructure=' + $('input.course.repository').value;
        url += '&course.description=' + $('input.course.description').value;
        url += '&course.targetAudience=' + $('input.course.targetAudience').value;
        url += '&course.contents=' + $('input.course.contents').value;
        url += '&course.challengeItensEnabled=' + challengeItensEnabled;
        url += '&course.uploadPackageEnabled=' + uploadPackageEnabled;
        url += '&course.customToc=' + customToc;

    }
    else {
        url += 'course!addCourse.action?';
        url += 'course.name=' + $('input.course.name').value;
        url += '&course.description=' + $('input.course.description').value;
        url += '&course.targetAudience=' + $('input.course.targetAudience').value;
        url += '&course.contents=' + $('input.course.contents').value;
        url += '&course.challengeItensEnabled=' + challengeItensEnabled;
        url += '&course.uploadPackageEnabled=' + uploadPackageEnabled;
        url += '&course.customToc=' + customToc;
    }
            
    var jsonCourse = getJsonFromUrl(url);
            
    if (courseId != null && courseId.length > 0) {
        if (jsonCourse != null && jsonCourse.course != null && jsonCourse.course.id != '') {
            //showCourse(jsonCourse.course.id);
            //document.location = 'course!show.action';
            //alert('alterou');
            $('iform.course.id').value = jsonCourse.course.id;
            //alert('Alterado com sucesso');
            //alert($('iform.course.id').value);
            $('updateImage').submit();
        }
        else {
            Lightbox.hideAll();
            alert('Não alterado');
        }
    }
    else {
        if (jsonCourse != null && jsonCourse.course != null && jsonCourse.course.id != '') {
            //showCourse(jsonCourse.course.id);
            //alert('alterou');
            $('iform.course.id').value = jsonCourse.course.id;
            //alert('Inserido com sucesso');
            //alert($('iform.course.id').value);
            $('updateImage').submit();
            //document.location = 'course!show.action';
        }
        else {
            Lightbox.hideAll();
            alert('Não inserido');
        }
    }
}

function deleteCourse(courseId) {
    new Lightbox.base('box1');
    var url = '';
    if (courseId != null && courseId.length > 0) {
        url += 'course!removeCourse.action?';
        url += 'course.id=' + courseId;
    }
    var jsonCourse = getJsonFromUrl(url);
    if (jsonCourse != null && jsonCourse.result == true) {
        document.location = 'course!show.action';
    }
    else {
        Lightbox.hideAll();
        alert('Não removido');
    }
}