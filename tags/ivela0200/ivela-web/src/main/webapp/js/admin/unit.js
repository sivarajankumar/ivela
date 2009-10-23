/* Funcionalidades de Unidades */

function showUnit(unitId) {
    closeAll();
    $('showUnit').style.display = 'block';

    var i = 0;
            
    jsonUnitId = unitId.split("_",1)[0];
    
    var jsonUnit = getJsonFromUrl('unit!getUnitInfo.action?unit.id=' + jsonUnitId);
    var jsonUnitsContent = getJsonFromUrl('unit!getUnitsContentInfo.action?unit.id=' + jsonUnitId);

    var name = jsonUnit.unit.name;
    var unitsContent = '';

    /* Tratamento da lista de aulas */
    if (jsonUnitsContent.list != '') {
        unitsContent += '<ul>';
        if (jsonUnitsContent.list.unitContent.length == null) {
            var temp = jsonUnitsContent.list.unitContent.title;
            unitsContent = '<li><input type="checkbox" id="unitContent.id_' + 0 + '" value="' +jsonUnitsContent.list.unitContent.id + '" />' + temp +
                            ' </li>';
        }
        else {
            for (i = 0; i < jsonUnitsContent.list.unitContent.length; i++) {
                unitsContent += '<li><input type="checkbox" id="unitContent.id_' + i + '" value="' + jsonUnitsContent.list.unitContent[i].id + '" />' + jsonUnitsContent.list.unitContent[i].title + 
                    '</li>';
             
            }
        }
        unitsContent += '</ul>';
    }
    $('unit.id').value = jsonUnit.unit.id;
    $('unit.name').innerHTML = name;
    $('unit.unitsContent').innerHTML = unitsContent;
}

function updateActive(active,unitId){
    
    var jsonUnit = getJsonFromUrl('unit!updateActive.action?unit.id=' + unitId+'&unit.active='+active);
    if( jsonUnit.result ==true){
        alert("Modificado com sucesso!!");
        
    }
    else alert ("Não foi possível fazer a alteração");
}

function deleteUnit() {
    var check = 0;
    var i = 0;
    var checkIds = new Array();
    while (check != null) {
        check = document.getElementById('unit.id_' + i);
        if (check != null && check.checked == true) {
            checkIds[checkIds.length] = check
        }
        i++;
    }
    
    if ( checkIds.length == 0 )
    {
       alert('Por favor, selecione a(s) unidade(s) a ser(em) removida(s)');
    }
    else
    {
       new Lightbox.base('box1');
    }
    
    for (i = 0; i < checkIds.length; i++) {
        var url = '';
        var unitId = checkIds[i].value;
        if (unitId != null && unitId.length > 0) {
            url += 'unit!removeUnit.action?';
            url += 'unit.id=' + unitId;
        }
        var jsonUnit = getJsonFromUrl(url);
        if (jsonUnit != null && jsonUnit.result == true) {
            document.location = 'course!show.action';
        }
        else {
            Lightbox.hideAll();
            alert('Não removido');
        }
    }
}

function showEntryUnit(disciplineId) {
    closeAll();
    $('showEntryUnit').style.display = 'block';
        
    $('input.unit.id').value = '';
    $('input.unit.discipline.id').value = disciplineId;
    $('input.unit.name').value = '';
            
    $('input.unit.name').focus();
}

// Retorna as unidades selecionadas
function getCheckedUnits()
{
    var check = 0;
    var i = 0;
    var checkIds = new Array();
    while (check != null) {
        check = document.getElementById('unit.id_' + i);
        if (check != null && check.checked == true) {
            checkIds[checkIds.length] = check.value;
        }
        i++;
    }

    return checkIds;
}

function showEditUnit() {
    var unitIds = getCheckedUnits();
    var unitCount = unitIds.length;

    if ( unitCount == 0 )
    {
        alert('Por favor, selecione a unidade a ser editada.');
        return;
    }
    else if ( unitCount > 1 )
    {
        alert('Por favor, selecione apenas uma unidade a ser editada.');
        return;
    }
    
    var unitId = unitIds[0];

    closeAll();
    $('showEntryUnit').style.display = 'block';
            
    var jsonUnit = getJsonFromUrl('unit!getUnitInfo.action?unit.id=' + unitId);
            
    $('input.unit.id').value = jsonUnit.unit.id;
    $('input.unit.discipline.id').value = jsonUnit.unit.disciplineId
    $('input.unit.name').value = jsonUnit.unit.name;
            
    $('input.unit.name').focus();
}

function showEditUnitById(unitId) {
    closeAll();
    $('showEntryUnit').style.display = 'block';
            
    var jsonUnit = getJsonFromUrl('unit!getUnitInfo.action?unit.id=' + unitId);
            
    $('input.unit.id').value = jsonUnit.unit.id;
    $('input.unit.discipline.id').value = jsonUnit.unit.disciplineId
    $('input.unit.name').value = jsonUnit.unit.name;
            
    $('input.unit.name').focus();
}

function submitUnit(unitId) {
   new Lightbox.base('box1');
   var url = '';
    if ($('input.unit.name').value == '' || $('input.unit.name').value.length < 1) {
        Lightbox.hideAll();
        alert('Informe o nome da unidade');
        $('input.unit.name').focus();
        return;
    }        
    if (unitId != null && unitId.length > 0) {
        url += 'unit!updateUnit.action?';
        url += 'unit.id=' + unitId;
        url += '&unit.name=' + $('input.unit.name').value;
        url += '&unit.disciplineId=' + $('input.unit.discipline.id').value;
    }
    else {
        url += 'unit!addUnit.action?';
        url += 'unit.name=' + $('input.unit.name').value;
        url += '&unit.disciplineId=' + $('input.unit.discipline.id').value;
    }
            
    var jsonUnit = getJsonFromUrl(url);
            
    if (unitId != null && unitId.length > 0) {
        if (jsonUnit != null && jsonUnit.unit != null && jsonUnit.unit.id != '') {
            //showCourse(jsonCourse.course.id);
            document.location = 'course!show.action';
        }
        else {
            Lightbox.hideAll();
            alert('Não alterado');
        }
    }
    else {
        if (jsonUnit != null && jsonUnit.unit != null && jsonUnit.unit.id != '') {
            //showCourse(jsonCourse.course.id);
            document.location = 'course!show.action';
        }
        else {
            Lightbox.hideAll();
            alert('Não inserido');
        }
    }
}