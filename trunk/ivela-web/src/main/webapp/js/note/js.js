/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//method = 0:listByDay, 1:listByWeek, 2:listByMonth:
var method = '0';

var page = 1
var pageLast = 1;


function changeClassCssOver(param){
    if (param != method ){
        $('spanCalender_'+param).setAttribute(classCss(),"current");
    }
}

function changeClassCssOut(param){
    if (param != method){
        $('spanCalender_'+param).setAttribute(classCss(),"");
    }
}

function methodAction(methodParam){
    var lastMethod = method;
    
    $('spanCalender_'+lastMethod).setAttribute(classCss(),"");
    $('spanCalender_'+methodParam).setAttribute(classCss(),"current");
    
    var date = $F('dateInput');
    
    if(date != ''){
    
        var arrayDate = date.split("/");
    
        var params = "month="+arrayDate[0]+"&day="+arrayDate[1]+"&year="+arrayDate[2]+"&method="+methodParam+"&page="+page;
    
        method = methodParam;
        
        execute("note!listByDate.action",params,listNotes);

    }
    else{
        alert('set Date');
        date.focus();
    }
    
}

    
function dateChanged(calendar) {
    // Beware that this function is called even if the end-user only
    // changed the month/year.  In order to determine if a date was
    // clicked you can use the dateClicked property of the calendar:
    if (calendar.dateClicked) {
        // OK, a date was clicked, redirect to /yyyy/mm/dd/index.php
        var y = calendar.date.getFullYear();
        var m = calendar.date.getMonth()+1;     // integer, 1..12
        var d = calendar.date.getDate();      // integer, 1..31
        
        $('dateInput').value = m+"/"+d+"/"+y;
        
        var params = "day="+d+"&month="+m+"&year="+y+"&method="+method+"&page=1";
        showList();
        
        execute("note!listByDate.action",params,listNotes);
        page = 1;
        pageLast = 1;
        
        
    }
}
var xhReq;

function listNotes(response){
    xhReq = response;
    if (xhReq.readyState == 4)
    {
        if (xhReq.status == 200)
        {
            obj = xhReq.responseXML;
            if(obj.getElementsByTagName('note')[0]){
         
                pageLast = obj.getElementsByTagName('pageInfo')[0].getAttribute('pageLast');
                $('listNotes').innerHTML = "";
                
                notes = obj.getElementsByTagName('note');
                countNotes = obj.getElementsByTagName('pageInfo')[0].getAttribute('pageSize');
        
                var last;
                if(notes.length > countNotes )
                    last = notes.length - 1;
                else
                    last = notes.length;
        
                for(var i = 0; i < last; i++){
                    var note = notes[i];
            
                    title = note.getAttribute('title');                 
                    description = note.getAttribute('description');
                    arrayDate = note.getAttribute('datetime').substr(0,10).split('-');
                    date = arrayDate[1]+"/"+ arrayDate[2]+"/"+ arrayDate[0];
                    hour = note.getAttribute('datetime').substr(11,8);
                    id = note.getAttribute('id');
            
                    $('listNotes').innerHTML +="<li><span>"+date+" - </span><span class='item-event' onclick='getNote("+i+")' >"+limitString(title, 27)+"</span></li>";
                }
    
                if(pageLast == page){
                    $('next').setAttribute(classCss(),"");
                }
                else{
                    $('next').setAttribute(classCss(),"pagination");
                }
        
                if(page == 1){
                    $('previous').setAttribute(classCss(),"");
                }
                else{
                    $('previous').setAttribute(classCss(),"pagination");
                }
        
                $('pag').innerHTML = page;

            }
                    
            else{
                var text;
                
                switch(navigator.language.substring(0,2)){
                    case 'en'   :
                        text = "Not Exists Note for this Date!";
                        break;
                    case 'pt':
                        text = "Não Existe Anotação Para Este Dia!";
                        break;
                }
                
                $('listNotes').innerHTML = "<li><span>"+text+"</span></li>"
                
                $('next').setAttribute(classCss(),"");
                $('previous').setAttribute(classCss(),"");
                $('pag').innerHTML = 0;
            }
        }
        else{
            alert('Error in EJB');
        }
        
    }
}


function notesPagination(param){
        
    
    if((param > 0) && (param <= pageLast)){
        page = param;

        $('listNotes').innerHTML = "";
    
        if(pageLast == param){
            $('next').setAttribute(classCss(),"");
        }
        else{
            $('next').setAttribute(classCss(),"pagination");
        }
        
        if(param == 1){
            $('previous').setAttribute(classCss(),"");
        }
        else{
            $('previous').setAttribute(classCss(),"pagination");
        }

        methodAction(method);
        
        $('pag').innerHTML = page;
    }
    
    
}

function getNote(i){
    
    obj = xhReq.responseXML;
    note = obj.getElementsByTagName('note')[i];
    
    title = note.getAttribute('title');
    description = note.getAttribute('description');
    arrayDate = note.getAttribute('datetime').substr(0,10).split('-');
    date = arrayDate[1]+"/"+ arrayDate[2]+"/"+ arrayDate[0];
    hour = note.getAttribute('datetime').substr(11,8);
    id = note.getAttribute('id');
    
    $('eventsCalendar').style.display = 'none';
    $('calendarLeft').style.display = 'none';
    $('getNoteCalendar').style.display = 'block';
    
    $('noteGetId').value = id;
    $('noteGetDate').innerHTML = date;
    $('noteGetTime').innerHTML = hour;
    $('noteGetTitle').innerHTML = title;
    $('noteGetDescription').innerHTML = description;
    
}

function showList(){
    
    $('getNoteCalendar').style.display = 'none';
    $('inputNoteCalendar').style.display = 'none';
    $('eventsCalendar').style.display = 'block';
    $('calendarLeft').style.display = 'block';
    $('noteGetId').value = '';
    clearFields();
    
}


function removeNote(){
    var id = $F('noteGetId');
    //ajaxExecute("note!removeAjax.action","note.id="+id,removeNoteRe);
    execute("note!removeAjax.action","note.id="+id,removeNoteRe);
}

function removeNoteRe(response){
    if (response.readyState == 4)
    {
        if (response.status == 200)
        {
            obj = response.responseXML;
            if (obj.getElementsByTagName('booleanNote')){
                
                switch(navigator.language.substring(0,2)){
                    case 'en'   :
                        text = "Not Removed!";
                        break;
                    case 'pt':
                        text = "Anotação Removida!";
                        break;
                }
                
                $('listNotes').innerHTML = "<li><span>"+text+"</span></li>"
                
                setTimeout('methodAction(method)',2000);
                
                showList();
            }
        }
    }
}

function addNote(){
    $('getNoteCalendar').style.display = 'none';
    $('eventsCalendar').style.display = 'none';
    $('calendarLeft').style.display = 'none';
    $('inputNoteCalendar').style.display = 'block';
    
    var date = new Date();
    
    hour = date.getHours();
    min = date.getMinutes();

    if(hour < 10) hour = '0'+hour;
    if(min < 10) min = '0'+min;
    
    clearFields();
    $('noteDate').value = $('dateInput').value+" "+ hour+":"+min+":00";
}

function editNote(){
    $('getNoteCalendar').style.display = 'none';
    $('eventsCalendar').style.display = 'none';
    $('calendarLeft').style.display = 'none';
    $('inputNoteCalendar').style.display = 'block';
    
    $('noteId').value = $('noteGetId').value;
    $('noteTitle').value = $('noteGetTitle').innerHTML;
    $('noteDescription').value = $('noteGetDescription').innerHTML;
    $('noteDate').value = $('noteGetDate').innerHTML +" "+$('noteGetTime').innerHTML;
}

function saveNote(){
    
    var id = $('noteId').value;
    var title = $('noteTitle').value;
    var description = $('noteDescription').value;
    var datetime = $('noteDate').value;
    
    if(id == ''){
        //ajaxExecute("note!addAjax.action","note.title="+title+"&note.description="+description+"&dateNote="+datetime,createNoteRe);
        execute("note!addAjax.action","note.title="+title+"&note.description="+description+"&dateNote="+datetime,createNoteRe);
    }
    else{
        //ajaxExecute("note!updateAjax.action","note.id="+id+"&note.title="+title+"&note.description="+description+"&dateNote="+datetime,editNoteRe);
        execute("note!updateAjax.action","note.id="+id+"&note.title="+title+"&note.description="+description+"&dateNote="+datetime,editNoteRe);
    }
}
function createNoteRe(response){
    if (response.readyState == 4)
    {
        if (response.status == 200)
        {
            obj = response.responseXML;
            if (obj.getElementsByTagName('longNote')){
                //$('dateInput').value = $('noteDate').value.substr(0,10);
                
                switch(navigator.language.substring(0,2)){
                    case 'en'   :
                        text = "Note Created!";
                        break;
                    case 'pt':
                        text = "Anotação Criada!";
                        break;
                }
                
                $('listNotes').innerHTML = "<li><span>"+text+"</span></li>"
                
                setTimeout('methodAction(method)',2000);
                //clearFields();
                showList();
                
                
            }
            else
                alert('Not possible!')
        }
    }
}
function editNoteRe(response){
    if (response.readyState == 4)
    {
        if (response.status == 200)
        {
            obj = response.responseXML;
            if (obj.getElementsByTagName('booleanNote')){
                //$('dateInput').value = $('noteDate').value.substr(0,10);
                
                switch(navigator.language.substring(0,2)){
                    case 'en'   :
                        text = "Note Edited!";
                        break;
                    case 'pt':
                        text = "Anotação Editada!";
                        break;
                }
                
                $('listNotes').innerHTML = "<li><span>"+text+"</span></li>"
                
                setTimeout('methodAction(method)',2000);
                //clearFields();
                showList();
            }
        }
    }
}

function classCss(){
    
    var css;
    
    if ((!document.all)&&($))
    {
        css = "class";
    }
    //Tratamento para I.E
    if ((document.all)&&($)){
        css = "className";
    }
    return css;
}

function isArray(o){
    return(typeof(o.length)=="undefined")?false:true;
}

function clearFields(){
    $('noteForm').reset();
    $('noteId').value = '';
}

function limitString(string, limit){
    
    if(string.length > limit){
        return string.substring(0,limit)+"...";
    }
    else
        return string;
}