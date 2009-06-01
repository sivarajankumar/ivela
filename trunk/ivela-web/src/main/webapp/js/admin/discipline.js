/* Funcionalidades de Disciplinas */

function showDiscipline(disciplineId) {
    closeAll();
    $('showDiscipline').style.display = 'block';

    var i = 0;
            
    var jsonDiscipline = getJsonFromUrl('discipline!getDisciplineInfo.action?discipline.id=' + disciplineId);
    var jsonUnits = getJsonFromUrl('discipline!getUnitsInfo.action?discipline.id=' + disciplineId);
            
    var name = jsonDiscipline.discipline.name;
    var units = '';

    /* Tratamento da lista de unidades */
    if (jsonUnits.list != '') {
        units += '<ul>';
        var open;
        var close;
        if (jsonUnits.list.unit.length == null) {
            var temp = jsonUnits.list.unit.name;
            if(jsonUnits.list.unit.active==true){
                    open ='<input type="radio" name="unit.active'+jsonUnits.list.unit.id +'" value="true" checked onclick="updateActive(true,'+jsonUnits.list.unit.id +')">open';
                    close='<input type="radio" name="unit.active'+jsonUnits.list.unit.id +'" value="false" onclick="updateActive(false,'+jsonUnits.list.unit.id +')">close';             
             
            }    
            else{
                open='<input type="radio" name="unit.active'+jsonUnits.list.unit.id +'" value="true" onclick="updateActive(true,'+jsonUnits.list.unit.id +')">open';               
                close = '<input type="radio" name="unit.active'+jsonUnits.list.unit.id +'" value="false" checked onclick="updateActive(false,'+jsonUnits.list.unit.id +')">close';
                   
            }
            units = '<li><input type="checkbox" id="unit.id_' + 0 + '" value="' +jsonUnits.list.unit.id + '" />' + temp + 
                open+close+'  </li>';
        }
        else {
            for (i = 0; i < jsonUnits.list.unit.length; i++) {
                if(jsonUnits.list.unit[i].active==true){
                    open ='<input type="radio" name="unit.active'+jsonUnits.list.unit[i].id +'" value="true" checked onclick="updateActive(true,'+jsonUnits.list.unit[i].id +')">open';
                    close='<input type="radio" name="unit.active'+jsonUnits.list.unit[i].id +'" value="false" onclick="updateActive(false,'+jsonUnits.list.unit[i].id +')">close';             
             
                }    
                else{
                    open='<input type="radio" name="unit.active'+jsonUnits.list.unit[i].id +'" value="true" onclick="updateActive(true,'+jsonUnits.list.unit[i].id +')">open';               
                    close = '<input type="radio" name="unit.active'+jsonUnits.list.unit[i].id +'" value="false" checked onclick="updateActive(false,'+jsonUnits.list.unit[i].id +')">close';
                
                }
                units += '<li><input type="checkbox" id="unit.id_' + i + '" value="' + jsonUnits.list.unit[i].id + '" />' + jsonUnits.list.unit[i].name + 
                    open+close+'</li>';
            }
        }
        units += '</ul>';
    }
    $('discipline.id').value = jsonDiscipline.discipline.id;
    $('discipline.name').innerHTML = name;
    $('discipline.units').innerHTML = units;
}

function deleteDiscipline() {
    var check = 0;
    var i = 0;
    var checkIds = new Array();
    while (check != null) {
        check = document.getElementById('discipline.id_' + i);
        if (check != null && check.checked == true) {
            checkIds[checkIds.length] = check
        }
        i++;
    }
    
    if ( checkIds.length == 0 )
    {
        alert('Por favor, selecione a(s) disciplina(s) a ser(em) removida(s)');
    }
    else
    {
        new Lightbox.base('box1');
    }
    
    for (i = 0; i < checkIds.length; i++) {
        var url = '';
        var disciplineId = checkIds[i].value;
        if (disciplineId != null && disciplineId.length > 0) {
            url += 'discipline!removeDiscipline.action?';
            url += 'discipline.id=' + disciplineId;
        }
        var jsonDiscipline = getJsonFromUrl(url);
        if (jsonDiscipline != null && jsonDiscipline.result == true) {
            document.location = 'course!show.action';
        }
        else {
            Lightbox.hideAll();
            alert('Não removido');
        }
    }
}

function submitDiscipline(disciplineId) {
new Lightbox.base('box1');
if ($('input.discipline.name').value == '' || $('input.discipline.name').value.length < 1) {
    Lightbox.hideAll();
    alert('Informe o nome da disciplina');
    $('input.discipline.name').focus();
    return;
}    
var url = '';
if (disciplineId != null && disciplineId.length > 0) {
    url += 'discipline!updateDiscipline.action?';
    url += 'discipline.id=' + disciplineId;
    url += '&discipline.name=' + $('input.discipline.name').value;
    url += '&discipline.courseId=' + $('input.discipline.course.id').value;
}
else {
url += 'discipline!addDiscipline.action?';
url += 'discipline.name=' + $('input.discipline.name').value;
url += '&discipline.courseId=' + $('input.discipline.course.id').value;
}
            
var jsonDiscipline = getJsonFromUrl(url);
            
if (disciplineId != null && disciplineId.length > 0) {
if (jsonDiscipline != null && jsonDiscipline.discipline != null && jsonDiscipline.discipline.id != '') {
    //showCourse(jsonCourse.course.id);
    document.location = 'course!show.action';
}
else {
    Lightbox.hideAll();
    alert('Não alterado');
}
}
else {
if (jsonDiscipline != null && jsonDiscipline.discipline != null && jsonDiscipline.discipline.id != '') {
    //showCourse(jsonCourse.course.id);
    document.location = 'course!show.action';
}
else {
    Lightbox.hideAll();
    alert('Não inserido');
}
}
}

function showEntryDiscipline(courseId) {
closeAll();
$('showEntryDiscipline').style.display = 'block';
        
$('input.discipline.course.id').value = courseId;
$('input.discipline.id').value = '';
$('input.discipline.name').value = '';
            
$('input.discipline.name').focus();
}

// Retorna as disciplinas selecionadas
function getCheckedDisciplines()
{
var check = 0;
var i = 0;
var checkIds = new Array();
while (check != null) {
check = document.getElementById('discipline.id_' + i);
if (check != null && check.checked == true) {
checkIds[checkIds.length] = check.value;
}
i++;
}

return checkIds;
}

function showEditDiscipline() {
    
var disciplineIds = getCheckedDisciplines();
var disciplineCount = disciplineIds.length;

if ( disciplineCount == 0 )
{
alert('Por favor, selecione a disciplina a ser editada.');
return;
}
else if ( disciplineCount > 1 )
{
alert('Por favor, selecione apenas uma disciplina a ser editada.');
return;
}
    
var disciplineId = disciplineIds[0];
closeAll();
$('showEntryDiscipline').style.display = 'block';
            
var jsonDiscipline = getJsonFromUrl('discipline!getDisciplineInfo.action?discipline.id=' + disciplineId);
            
$('input.discipline.id').value = jsonDiscipline.discipline.id;
$('input.discipline.course.id').value = jsonDiscipline.discipline.courseId
$('input.discipline.name').value = jsonDiscipline.discipline.name;
            
$('input.discipline.name').focus();
}

function showEditDisciplineById(disciplineId) {
closeAll();
$('showEntryDiscipline').style.display = 'block';
            
var jsonDiscipline = getJsonFromUrl('discipline!getDisciplineInfo.action?discipline.id=' + disciplineId);
            
$('input.discipline.id').value = jsonDiscipline.discipline.id;
$('input.discipline.course.id').value = jsonDiscipline.discipline.courseId
$('input.discipline.name').value = jsonDiscipline.discipline.name;
            
$('input.discipline.name').focus();
}