/* Funcionalidades de Aulas */
        
function showUnitContent(unitContentId) {
    
    closeAll();
    $('showUnitContent').style.display = 'block';              

    var i = 0;
    
    jsonUnitContentId = unitContentId.split("_",1)[0];
    var jsonUnitContent = getJsonFromUrl('unit!getUnitContentInfo.action?unitContent.id=' + jsonUnitContentId);
    
    var id = jsonUnitContent.unitContent.id;
    var title = jsonUnitContent.unitContent.title;
    var description = jsonUnitContent.unitContent.description;
    var type = jsonUnitContent.unitContent.type;
 
    $('unitContent.id').value = id;
    $('unitContent.title').innerText = title;
    //$('unitContent.html').innerHTML = description;    
    //var oEditor = FCKeditorAPI.GetInstance('FCKeditor1');
    //oEditor.SetHTML(description);
    if (type == 1) {
        $('unitContent.pdf').innerHTML = '<iframe id="html" frameborder="0" width="100%" height="100%" src="discipline!showPdf.action?unitContent.id=' + jsonUnitContentId + '"></iframe>';
        $('unitContent.html').style.display = 'none';
        $('unitContent.pdf').style.display = 'block';
    }
    else {
        if(type==2){
        var jsonUnitContent = getJsonFromUrl('unitContent!getContentPackageJson.action?unitContent.id=' + jsonUnitContentId);
        var unitId = jsonUnitContent.info.unit;
        var disciplineId = jsonUnitContent.info.discipline;
        var courseId = jsonUnitContent.info.course;
        //width: 665 height: 1000
         
        $('seeUnitContent').href = '../RenderServlet?file=' + courseId + '/' + disciplineId + '/' + unitId + '/' + jsonUnitContentId + '/index.html';
        $('unitContent.pdf').style.display = 'none';
        $('unitContent.html').style.display = 'block';
        }
        else{
            if(type==3){
                  $('unitContent.html').innerHTML = description;
                  $('unitContent.pdf').style.display = 'none';
                  $('unitContent.html').style.display = 'block';
            }
        }
    }
}

 

function deleteUnitContent() {
   
    var check = 0;
    var i = 0;
    var checkIds = new Array();
    while (check != null) {
        check = document.getElementById('unitContent.id_' + i);
        if (check != null && check.checked == true) {
            checkIds[checkIds.length] = check
        }
        i++;
    }
    
    if ( checkIds.length == 0 )
    {
        alert('Por favor, selecione a(s) aula(s) a ser(em) removida(s)');
    }
    else
    {
        new Lightbox.base('box1');
    }
    
    for (i = 0; i < checkIds.length; i++) {
        var url = '';
        var unitContentId = checkIds[i].value;
        if (unitContentId != null && unitContentId.length > 0) {
            url += 'unitContent!removeUnitContent.action?';
            url += 'unitContent.id=' + unitContentId;
        }
        var jsonUnitContent = getJsonFromUrl(url);
        if (jsonUnitContent != null && jsonUnitContent.result == true) {
            document.location = 'course!show.action';
        }
        else {
            Lightbox.hideAll();
            alert('Não removido');
        }
    }
}

/*
function submitJsonPost(url, params){
    var json;
    new Ajax.Request(url,
    {
        method:'post',
        parameters: params,
        requestHeaders: {Accept: 'application/json'}, 
        asynchronous: false,
        onSuccess: function(transport) {
            json = transport.responseText.evalJSON(true);
        },
        onFailure: function() { alert('Message: Something went wrong...') }
    });
    return json;
}
 */
function submitUnitContent(unitContentId) {
    new Lightbox.base('box1');
    if ($('input.unitContent.title').value == '' || $('input.unitContent.title').value.length < 1) {
        Lightbox.hideAll();
        alert('Informe o título da aula');
        $('input.unitContent.title').focus();
        return;
    }        
    var url = '';
    var params = '';
    var oEditor = FCKeditorAPI.GetInstance('FCKeditor1');
    var form = document.getElementById('unitContentForm');
    if ($('input.unitContent.title').value == '' || $('input.unitContent.title').value.length < 1) {
        Lightbox.hideAll();
        alert('Informe o título da aula');
        $('input.unitContent.title').focus();
        return;
    }            
    if (oEditor.GetHTML() == '') {
        Lightbox.hideAll();
        alert('Não há conteúdo nesta aula');
        return;
    }                
    if (unitContentId != null && unitContentId.length > 0) {
        url = 'unit!updateUnitContent.action';
        $('input.unitContent.id').value = unitContentId;    
        /*
        params += 'unitContent.id=' + unitContentId;
        params += '&unitContent.title=' + $('input.unitContent.title').value;
        params += '&unitContent.description=' + oEditor.GetHTML();
        params += '&unitContent.unitId=' + $('input.unitContent.unit.id').value;
        params += '&unitContent.type=' + type;
        */
    }
    else {
        url = 'unit!addUnitContent.action';
        /*
        params += 'unitContent.title=' + $('input.unitContent.title').value;
        params += '&unitContent.description=' + oEditor.GetHTML();
        params += '&unitContent.unitId=' + $('input.unitContent.unit.id').value;
        params += '&unitContent.type=' + type;
        */
    }
    form.action = url;
    $('input.unitContent.description').value = oEditor.GetHTML();
    form.submit();
    /*        
    var jsonUnitContent = getJsonFromUrlPost(url + '?' + params);
            alert(jsonUnitContent);
    if (unitContentId != null && unitContentId.length > 0) {
        if (jsonUnitContent != null && jsonUnitContent.unitContent != null && jsonUnitContent.unitContent.id != '') {
            //showCourse(jsonCourse.course.id);
            document.location = 'course!show.action';
        }
        else {
            alert('Não alterado');
        }
    }
    else {
        if (jsonUnitContent != null && jsonUnitContent.unitContent != null && jsonUnitContent.unitContent.id != '') {
            //showCourse(jsonCourse.course.id);
            document.location = 'course!show.action';
        }
        else {
            alert('Não inserido');
        }
    }
    */
}

// Retorna as aulas selecionadas
function getCheckedUnitContents()
{
    var check = 0;
    var i = 0;
    var checkIds = new Array();
    while (check != null) {
        check = document.getElementById('unitContent.id_' + i);
        if (check != null && check.checked == true) {
            checkIds[checkIds.length] = check.value;
        }
        i++;
    }

    return checkIds;
}

function showEditUnitContent() {
    var unitContentIds = getCheckedUnitContents();
    var unitContentCount = unitContentIds.length;

    if ( unitContentCount == 0 )
    {
        alert('Por favor, selecione a aula a ser editada.');
        return;
    }
    else if ( unitContentCount > 1 )
    {
        alert('Por favor, selecione apenas uma aula a ser editada.');
        return;
    }
    
    var unitContentId = unitContentIds[0];

    closeAll();
    $('showEntryUnitContent').style.display = 'block';
            
    var oEditor = FCKeditorAPI.GetInstance('FCKeditor1');
            
    jsonUnitContentId = unitContentId.split("_",1)[0];
    var jsonUnitContent = getJsonFromUrl('unit!getUnitContentInfo.action?unitContent.id=' + jsonUnitContentId);
    
    $('input.unitContent.id').value = jsonUnitContent.unitContent.id;
    $('input.unitContent.unit.id').value = jsonUnitContent.unitContent.unitId
    $('input.unitContent.title').value = jsonUnitContent.unitContent.title;
    
    var jsonUnitContentOrders = getJsonFromUrl('unitContent!getUnitContentOrdersJson.action?unit.id=' + jsonUnitContent.unitContent.unitId);
    $('input.unitContent.order_n').length = 0;
    var item = 1;
    for (var i = 0; jsonUnitContentOrders != '' && 
                    jsonUnitContentOrders.info != '' && 
                    i < jsonUnitContentOrders.info.length; i++) {
        item = jsonUnitContentOrders.info[i].order_n;
        $('input.unitContent.order_n').options[i] = new Option(Number(item), Number(item));
    }
    $('input.unitContent.order_n').value = jsonUnitContent.unitContent.orderN;
    // $('input.unitContent.description').value = ;
    oEditor.SetHTML(jsonUnitContent.unitContent.description);
            
    $('input.unitContent.title').focus();
}

function showEditUnitContentById(unitContentId) {
    closeAll();
    var jsonUnitContent = getJsonFromUrl('unit!getUnitContentInfo.action?unitContent.id=' + unitContentId);  

    if (jsonUnitContent != null && jsonUnitContent != '' && jsonUnitContent.unitContent != '' && jsonUnitContent.unitContent.type == '2') {
        showUploadUnitContentId(unitContentId, jsonUnitContent);
    }
    else {
        $('showEntryUnitContent').style.display = 'block';

        var oEditor = FCKeditorAPI.GetInstance('FCKeditor1');

        $('input.unitContent.id').value = jsonUnitContent.unitContent.id;
        $('input.unitContent.unit.id').value = jsonUnitContent.unitContent.unitId
        $('input.unitContent.title').value = jsonUnitContent.unitContent.title;        

        if(jsonUnitContent.unitContent.tag)
            $('input.unitContent.tag').value = jsonUnitContent.unitContent.tag;
        
        var jsonUnitContentOrders = getJsonFromUrl('unitContent!getUnitContentOrdersJson.action?unit.id=' + jsonUnitContent.unitContent.unitId);
        $('input.unitContent.order_n').length = 0;
        var item = 1;
        for (var i = 0; jsonUnitContentOrders != '' && 
                        jsonUnitContentOrders.info != '' && 
                        i < jsonUnitContentOrders.info.length; i++) {
            item = jsonUnitContentOrders.info[i].order_n;
            $('input.unitContent.order_n').options[i] = new Option(Number(item), Number(item));
        }
        var type = 'input.unitContent.type'+jsonUnitContent.unitContent.type;
        $(type).setAttribute("checked","");

        $('input.unitContent.order_n').value = jsonUnitContent.unitContent.orderN;

        //    $('input.unitContent.description').value = ;
        oEditor.SetHTML(jsonUnitContent.unitContent.description);

        $('input.unitContent.title').focus();  
    }
}




function showUploadUnitContent(unitId) {
    closeAll();
    $('showUploadUnitContent').style.display = 'block';
    $('upload.unit.id').value = unitId;
    $('upload.unitContent.id').value = "";
    $('upload.unitContent.title').value = "";
    $('upload.unitContent.width').value = "";
    $('upload.unitContent.height').value = "";
    $('upload.unitContent.tag').value = "";
    
        jsonUnitId = unitId.split("_",1)[0];
        var jsonUnitContentOrders = getJsonFromUrl('unitContent!getUnitContentOrdersJson.action?unit.id=' + jsonUnitId);
        $('upload.unitContent.order_n').length = 0;
        var item = 1;
        for (var i = 0; jsonUnitContentOrders != '' && 
                        jsonUnitContentOrders.info != '' && 
                        i < jsonUnitContentOrders.info.length; i++) {
            item = jsonUnitContentOrders.info[i].order_n;
            $('upload.unitContent.order_n').options[i] = new Option(Number(item), Number(item));
        }

    
}

function showUploadUnitContentId(unitContentId, jsonUnitContent) {
    closeAll();
    //var jsonUnitContent = getJsonFromUrl('unit!getUnitContentInfo.action?unitContent.id=' + unitContentId);
    $('upload.unit.id').value = jsonUnitContent.unitContent.unitId;    
    $('upload.unitContent.id').value = unitContentId;
    $('upload.unitContent.title').value = jsonUnitContent.unitContent.title;

    if(typeof(jsonUnitContent.unitContent.width) == "undefined")
        $('upload.unitContent.width').value = 560;
    else
        $('upload.unitContent.width').value = jsonUnitContent.unitContent.width;
    
    if(typeof(jsonUnitContent.unitContent.height) == "undefined")
        $('upload.unitContent.height').value = 1000;
    else
        $('upload.unitContent.height').value = jsonUnitContent.unitContent.height;

    if(jsonUnitContent.unitContent.tag)
        $('upload.unitContent.tag').value = jsonUnitContent.unitContent.tag;    

        var jsonUnitContentOrders = getJsonFromUrl('unitContent!getUnitContentOrdersJson.action?unit.id=' + jsonUnitContent.unitContent.unitId);
        $('upload.unitContent.order_n').length = 0;
        var item = 1;
        for (var i = 0; jsonUnitContentOrders != '' && 
                        jsonUnitContentOrders.info != '' && 
                        i < jsonUnitContentOrders.info.length; i++) {
            item = jsonUnitContentOrders.info[i].order_n;
            $('upload.unitContent.order_n').options[i] = new Option(Number(item), Number(item));
        }


        $('upload.unitContent.order_n').value = jsonUnitContent.unitContent.orderN;


    $('showUploadUnitContent').style.display = 'block';
}

function showEntryUnitContent(unitId) {
    closeAll();
    var oEditor = FCKeditorAPI.GetInstance('FCKeditor1');
    oEditor.SetHTML('');
    $('showEntryUnitContent').style.display = 'block';
        
    $('input.unitContent.id').value = '';
    $('input.unitContent.unit.id').value = unitId;
    $('input.unitContent.title').value = '';
    
    jsonUnitId = unitId.split("_",1)[0];
    
    var jsonUnitContentOrders = getJsonFromUrl('unitContent!getUnitContentOrdersJson.action?unit.id=' + jsonUnitId);
    
    $('input.unitContent.order_n').length = 0;
        var item = 1;
        for (var i = 0; jsonUnitContentOrders != '' && 
                        jsonUnitContentOrders.info != '' && 
                        i < jsonUnitContentOrders.info.length; i++) {
            item = jsonUnitContentOrders.info[i].order_n;
            $('input.unitContent.order_n').options[i] = new Option(Number(item), Number(item));
        }

    $('input.unitContent.type1').setAttribute("checked","");
    $('input.unitContent.title').focus();
}