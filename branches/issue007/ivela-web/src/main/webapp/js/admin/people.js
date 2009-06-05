Event.observe(window, 'load', loadAccordions, false);
Event.observe(window, 'click', click, false);

var actualType = '';

function isArray(o){
    return(typeof(o.length)=="undefined")?false:true;
}

function click(e){ 
    if(e.target.getAttribute('class') != null){       
        var clazz = e.target.getAttribute('class').toString();

        if(clazz == 'category_toggle category_active'){

            var type = e.target.next(0).getAttribute('id');   
            actualType = type;
            unCheckAll();
            closeAllMessages();
            show(type);
        }
    }
}
        
//
//	Set up all accordions
//
function loadAccordions() {		
    var acc = new accordion('category_container', {
        classNames : {
            toggle : 'category_toggle',
            toggleActive : 'category_active',
            content : 'category_content'
        },
        direction : 'vertical'
    });

    acc.activate($$('#category_container .category_toggle')[0]);

    actualType = $$('#category_container .category_toggle')[0].next(0).getAttribute('id');
    show(actualType);
}

/* Funcoes para Chamar Json por Ajax */


function closeAll() {
    $('adminUser').style.display = 'none';
    $('coordinatorUser').style.display = 'none';
    $('professorUser').style.display = 'none';
    $('tutorUser').style.display = 'none';
    $('studentUser').style.display = 'none';
    $('userInfo').style.display = 'none';
    $('multipleUsers').style.display = 'none';
    $('studentUser').style.display = 'none';
}

function show(type) {
    closeAll();
    
    if(type == 1){
        $('adminUser').style.display = 'block';
    } else if(type == 2){
        $('coordinatorUser').style.display = 'block';
    } else if(type == 6){
        $('professorUser').style.display = 'block';
    } else if(type == 3){
        $('tutorUser').style.display = 'block';
    } else if(type == 5){
        $('studentUser').style.display = 'block';
    }
}

function deleteStudent(delStudent){
    if(delStudent){
    var users = getChecked(actualType);
    
    if(users.length == 0){
        alert('Marque um ou mais usuários!');
        return;
    }
    var ids = '';   
    for (var i = 0; i < users.length; i++) {
        ids += users[i];
        if (i != users.length - 1)
            ids += ';';
    }
    var url = 'systemUser!deleteUser.action';
    var params = '';
    params += 'systemUserIds=' + ids;
    var json = getJsonFromUrl(url + '?' + params);
    if(json.result==true){
        alert("Delete sucess");
        
    }
    else alert("Delete Failed");
  }
  closeAllMessages();
  document.location = '';
    
}

function unCheckAll() {
    var inputs = document.getElementsByTagName('input');
    for (var i = 0; i < inputs.length; i++) {
        if (inputs[i].checked != null && inputs[i].checked == true)
        {
            inputs[i].checked = false;
            changeCheckboxStyle(inputs[i]);
        }
    }    
}

function hideAll() {
    var inputs = document.getElementsByTagName('input');
    for (var i = 0; i < inputs.length; i++) {
        if (inputs[i].type == 'checkbox' && inputs[i].checked != null) {
            var userId = inputs[i].value;
            
            $('div.inner.user.id_'+userId).style.display = 'none'; 
        }
    }    
}




function setTypesByFieldName(fieldName) {
    var selectMultiple = $(fieldName + '.multiple.change.types');
    
    selectMultiple.options.length = 0;
    
    var items = [
    [ 1, 'Administrator'],
    [ 2, 'Coordinator'],
    [ 6, 'Professor'],
    [ 3, 'Tutor'],
    [ 5, 'Student']
    ]

    var idx;

    for (var i = 0; i < items.length; i++) {
        if(items[i][0] == actualAuth) {
            idx = i;
        }
    }

    for (var j = 0; j < items.length; j++) {
        if(j >= idx) {
            selectMultiple.options[selectMultiple.options.length] = new Option(items[j][1], items[j][0]);
        }
    }
}

function getChecked(type) {
    var prefix = '';
    
    if(type == 1){
        prefix = 'admin.id_';
    } else if(type == 2){
        prefix = 'coord.id_';
    } else if(type == 6){
        prefix = 'prof.id_';
    } else if(type == 3){
        prefix = 'tutor.id_';
    } else if(type == 5){
        prefix = 'student.id_';
    }
    
    
    var check = 0;
    var i = 0;
    var checkIds = new Array();
    while (check != null) {
        check = document.getElementById(prefix + i);
        if (check != null && check.checked == true) {
            checkIds[checkIds.length] = check.value;
        }
        i++;
    }

    return checkIds;
}

function sendMessage(title, description) {
    var users = getChecked(actualType);
    
    if(users.length == 0){
        alert('Marque um ou mais usuários!');
        return;
    }
    
    var ids = '';
    var enviadas = 0;
    var falhas = 0;    
    for (var i = 0; i < users.length; i++) {
        ids += users[i];
        if (i != users.length - 1)
            ids += ';';
    }
    var url = 'grade!sendMessageUsers.action';
    var params = '';
    params += 'systemUserIds=' + ids;
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
      
        closeAllMessages();
    }
    else{
        alert('Mensagem não enviada');
    }   
}

function change(type) {
    var users = getChecked(actualType);
    
    if(users.length == 0){
        alert('Marque um ou mais usuários!');
        return;
    }
    
    var ids = '';
    var enviadas = 0;
    var falhas = 0;    
    for (var i = 0; i < users.length; i++) {
        ids += users[i];
        if (i != users.length - 1)
            ids += ';';
    }
    var url = 'systemUser!change.action';
    var params = '';
    params += 'systemUserIds=' + ids;
    params += '&type=' + type;
    var json = getJsonFromUrl(url + '?' + params);
    if ((json.boolean + '') == 'true') {
        enviadas++;
    }
    else {
        falhas++;
    }        
    if (enviadas > 0) {
        alert('Perfil atualizado!');
      
        document.location = '';
    }
    else{
        alert('Perfil não atualizado!');
    }   
}

function add(userId) {     
    var url = 'systemUser!add.action';
    var params = '';
    params += 'systemUser.id=' + userId;
    params += '&type=' + actualType;
    
    var json = getJsonFromUrl(url + '?' + params);
        
    if ((json.boolean + '') == 'true') {
        alert('Perfil adicionado!');
    }
    else {
        alert('Perfil não adicionado!');
    }       
        
    document.location = '';
}

function sendNewsFlash(message) {
    var users = getChecked(actualType);
    
    if(users.length == 0){
        alert('Marque um ou mais usuários!');
        return;
    }
    
    var i = 0;
    var ids = '';
    var enviadas = 0;
    var falhas = 0;
    for (i = 0; i < users.length; i++) {
        ids += users[i];
        if (i != users.length - 1)
            ids += ';';
    }
    var url = 'grade!sendNewsFlashUsers.action';
    var params = '';
    params += 'systemUserIds=' + ids;
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
      
        closeAllMessages();
    }
    else
        alert('Mensagem não enviada');
}

function closeAllMessages() {
    $('user.message').style.display = 'none'; 
    $('user.newsflash').style.display = 'none'; 
    $('multiple.message').style.display = 'none'; 
    $('multiple.newsflash').style.display = 'none';
    $('admin.add').style.display = 'none';
    $('coord.add').style.display = 'none';
    $('prof.add').style.display = 'none';
    $('tutor.add').style.display = 'none';
    $('user.change').style.display = 'none';
    $('multiple.change').style.display = 'none';
    
    $('input.user.message.title').value = '';  
    $('input.user.message.description').value = '';  
    $('input.user.newsflash.message').value = '';  
    $('input.multiple.message.title').value = '';  
    $('input.multiple.message.description').value = '';  
    $('input.multiple.newsflash.message').value = '';      


    $('multiple.message.admin').style.display = 'none'; 
    $('multiple.newsflash.admin').style.display = 'none';
    $('multiple.change.admin').style.display = 'none';
    $('admin.input.multiple.message.title').value = '';  
    $('admin.input.multiple.message.description').value = '';  
    $('admin.input.multiple.newsflash.message').value = '';      

    $('multiple.message.coord').style.display = 'none'; 
    $('multiple.newsflash.coord').style.display = 'none';
    $('multiple.change.coord').style.display = 'none';
    $('coord.input.multiple.message.title').value = '';  
    $('coord.input.multiple.message.description').value = '';  
    $('coord.input.multiple.newsflash.message').value = '';      

    $('multiple.message.prof').style.display = 'none'; 
    $('multiple.newsflash.prof').style.display = 'none';
    $('multiple.change.prof').style.display = 'none';
    $('prof.input.multiple.message.title').value = '';  
    $('prof.input.multiple.message.description').value = '';  
    $('prof.input.multiple.newsflash.message').value = '';      

    $('multiple.message.tutor').style.display = 'none'; 
    $('multiple.newsflash.tutor').style.display = 'none';
    $('multiple.change.tutor').style.display = 'none';
    $('tutor.input.multiple.message.title').value = '';  
    $('tutor.input.multiple.message.description').value = '';  
    $('tutor.input.multiple.newsflash.message').value = '';      

    $('multiple.message.student').style.display = 'none'; 
    $('multiple.newsflash.student').style.display = 'none';
    $('multiple.change.student').style.display = 'none';
    $('student.input.multiple.message.title').value = '';  
    $('student.input.multiple.message.description').value = '';  
    $('student.input.multiple.newsflash.message').value = '';
    $('multiple.delete.student').value = ''; 


}

function changeCheckboxStyle(obj)
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

function checkAllFieldsByName(fieldName,gradeId,type)
{
    var checks = document.getElementsByName(fieldName);
    for (i = 0; i < checks.length; i++)
    {
        checks[i].checked = true;
        changeCheckboxStyle(checks[i]);
    }
    
    if ( checks[0] != null )
    {
        updatePerson(checks[0], checks[0].id);            
    }

}

function unCheckAllFieldsByName(fieldName,divId)
{
    var checks = document.getElementsByName(fieldName);
    for (i = 0; i < checks.length; i++)
    {
        checks[i].checked = false;
        changeCheckboxStyle(checks[i]);
    }
    
    $(divId).innerHTML = '';
    if ( divId.indexOf("admin") > -1 )
    {
        $('action.add.admin').style.display = 'block';
        $('actions.all.admin').style.display = 'none';
    }
    else if ( divId.indexOf("coord") > -1 )
    {
        $('action.add.coord').style.display = 'block';
        $('actions.all.coord').style.display = 'none';
    }
    else if ( divId.indexOf("prof") > -1 )
    {
        $('action.add.prof').style.display = 'block';
        $('actions.all.prof').style.display = 'none';
    }
    else if ( checks[0].id.indexOf("tutor") > -1 )
    {
        $('action.add.tutor').style.display = 'block';
        $('actions.all.tutor').style.display = 'none';
    }

}

function updatePerson(obj,id)
{
    var personDataHtml = "";
    var checks = document.getElementsByName(obj.name);

    checkedPerson = new Array();
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
    
    for ( var i = 0; i < checks.length; i++ ) {
        if ( checks[i].checked) { 

            // update the div in the multiple group
            var childs = document.getElementById("div.multiple.inner." + checks[i].id).getElementsByTagName("img");
            for(var j = 0; j < childs.length; j++){
                if(childs[j].src.search("images/foto_profile.jpg") != -1 && checks[i].value == id){
                    childs[j].src = "../RenderServletProfile?id=" + id;
                }           
            }
            
            // update the div in the single group
            var childs = document.getElementById("div.inner." + checks[i].id).getElementsByTagName("img");
            for(var j = 0; j < childs.length; j++){
                if(childs[j].src.search("images/foto_profile.jpg") != -1 && checks[i].value == id){
                    childs[j].src = "../RenderServletProfile?id=" + id;
                }           
            }

            // acrescenta o id do tutor numa variável global se estiver marcado
            checkedPerson[arrayIndex++] = checks[i].value;
            var divContent = document.getElementById("div" + multiple + "." + checks[i].id).innerHTML;
            divContent = divContent.replace("display: none;","display: block;");
            personDataHtml += divContent;
        }
    }
    
    personDataHtml += "<br class=\"clear\" />";

    if ( checks[0].id.indexOf("admin") > -1 )
    {
        $('adminData').innerHTML = personDataHtml;
        $('action.add.admin').style.display = ( checkedElements == 0 ) ? 'block' : 'none';
        $('actions.all.admin').style.display = ( checkedElements == 0 ) ? 'none' : 'block';
        setTypesByFieldName('admin');
    }
    else if ( checks[0].id.indexOf("coord") > -1 )
    {
        $('coordData').innerHTML = personDataHtml;
        $('action.add.coord').style.display = ( checkedElements == 0 ) ? 'block' : 'none';
        $('actions.all.coord').style.display = ( checkedElements == 0 ) ? 'none' : 'block';
        setTypesByFieldName('coord');
    }
    else if ( checks[0].id.indexOf("prof") > -1 )
    {
        $('profData').innerHTML = personDataHtml;
        $('action.add.prof').style.display = ( checkedElements == 0 ) ? 'block' : 'none';
        $('actions.all.prof').style.display = ( checkedElements == 0 ) ? 'none' : 'block';
        setTypesByFieldName('prof');
    }
    else if ( checks[0].id.indexOf("tutor") > -1 )
    {
        $('tutorData').innerHTML = personDataHtml;
        $('action.add.tutor').style.display = ( checkedElements == 0 ) ? 'block' : 'none';
        $('actions.all.tutor').style.display = ( checkedElements == 0 ) ? 'none' : 'block';
        setTypesByFieldName('tutor');
    }
    else if ( checks[0].id.indexOf("student") > -1 )
    {
        $('studentData').innerHTML = personDataHtml;
        //$('action.add.student').style.display = ( checkedElements == 0 ) ? 'block' : 'none';
        $('actions.all.student').style.display = ( checkedElements == 0 ) ? 'none' : 'block';
        setTypesByFieldName('student');
    }


/*
    if (obj.checked == true)
      $('tutorData').innerHTML = tutorDataHtml;
    else
      alert('retirar');
*/    
}

function showDiv(divId)
{
    $('admin.input.multiple.message.description.len').value = '250';
    $('admin.input.multiple.newsflash.message.len').value = '250';
    $('coord.input.multiple.message.description.len').value = '250';
    $('coord.input.multiple.newsflash.message.len').value = '250';
    $('prof.input.multiple.message.description.len').value = '250';
    $('prof.input.multiple.newsflash.message.len').value = '250';
    $('tutor.input.multiple.message.description.len').value = '250';
    $('tutor.input.multiple.newsflash.message.len').value = '250';
    $('student.input.multiple.message.description.len').value = '250';
    $('student.input.multiple.newsflash.message.len').value = '250';
    $('input.user.message.description.len').value = '250';
    $('input.user.newsflash.message.len').value = '250';
    $('input.multiple.message.description.len').value = '250';
    $('input.multiple.newsflash.message.len').value = '250';
    closeAllMessages(); 
    $(divId).style.display = 'block';
}

function textCounter(field, countfield, maxlimit) {
    if (field.value.length > maxlimit) // if too long...trim it!
        field.value = field.value.substring(0, maxlimit);
    // otherwise, update 'characters left' counter
    else 
        countfield.value = maxlimit - field.value.length;
}