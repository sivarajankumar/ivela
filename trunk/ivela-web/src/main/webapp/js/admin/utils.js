var current_course;
       
Event.observe(window, 'load', loadAccordions, false);
Event.observe(window, 'load', function() {Event.observe(content, 'click', click, false);}, false);

function click(e){ 
    if (!e) e = window.event;
    var evt;
    if (e.target) evt = e.target;
        else if (e.srcElement) evt = e.srcElement;
    if ((evt.className) != null) {       
        var clazz = evt.className.toString();

        if(clazz == 'accordion_toggle_course2 accordion_toggle_active_course2'){

            var course = evt.next(0).getAttribute('id');

            if(course != current_course){
                //eh um curso    
                        
                current_course = course;
                showCourse(course);
            }
        } else if(clazz == 'vertical_accordion_toggle vertical_accordion_toggle_active'){
            var disc = evt.next(0).getAttribute('id');

            //eh uma disciplina
            showDiscipline(disc);
        } else if(clazz == 'vertical_accordion_toggle2 vertical_accordion_toggle_active2'){
            var unit = evt.next(0).getAttribute('id');

            //eh uma unidade
            showUnit(unit);
        } else if(clazz == 'vertical_accordion_toggle3 vertical_accordion_toggle_active3'){
            var unitContent = evt.next(0).getAttribute('id');

            //eh uma aula
            showUnitContent(unitContent);
        } else if(clazz == 'vertical_accordion_toggle4 vertical_accordion_toggle_active4'){
            var unitContent = evt.next(0).getAttribute('id');

            //eh uma aula
            if (evt.getAttribute('id') == 'exercises')
                showManagerExercises(unitContent);
            if (evt.getAttribute('id') == 'exams')
                showManagerExams(unitContent);
            
        }
    }
}
        
//
//	Set up all accordions
//
function loadAccordions() {		
    var bottomAccordion = new accordion('vertical_container2', {
        classNames : {
            toggle : 'accordion_toggle_course2',
            toggleActive : 'accordion_toggle_active_course2',
            content : 'accordion_content_course2'
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

    if($$('#vertical_container2 .accordion_toggle_course2') != null && $$('#vertical_container2 .accordion_toggle_course2').length > 0) {
        first = bottomAccordion.activate($$('#vertical_container2 .accordion_toggle_course2')[0]);
        current_course = first.getAttribute("id");
        showCourse(first.getAttribute('id'));
    }
}

/* Funcoes para Chamar Json por Ajax */


function closeAll() {
    $('showCourse').style.display = 'none';
    $('showDiscipline').style.display = 'none';
    $('showUnit').style.display = 'none';
    $('showUnitContent').style.display = 'none';              

    $('showEntryCourse').style.display = 'none';
    $('showEntryDiscipline').style.display = 'none';
    $('showEntryUnit').style.display = 'none';
    $('showEntryUnitContent').style.display = 'none';
    $('showEntryExercise').style.display = 'none';
    $('showUploadUnitContent').style.display = 'none';
    $('showEntryExam').style.display = 'none';
    $('showManagerExercises').style.display = 'none';
    $('showManagerExams').style.display = 'none';
    $('showEntryQuestion').style.display = 'none';
    $('questionSubjective').style.display = 'none';
    $('questionObjective').style.display = 'none';
    $('questionAuditive').style.display = 'none';
    $('showExam').style.display = 'none';
    $('showExercise').style.display = 'none';
    $('showQuestion').style.display = 'none';
    $('showQuestionSubjective').style.display = 'none';
    $('showDictionary').style.display = 'none';
    $('deleteDict').style.display = 'none';
    $('updateDict').style.display = 'none';
    $('showChallenge').style.display= 'none';
    $('removeCha').style.display= 'none';
    
}

function isArray(o){
    return(typeof(o.length)=="undefined")?false:true;
}

function replaceAll(text){
    
    strA = '?';
    strB = 'IV_IGV';
    while ( text.indexOf(strA) != -1)
    {
        text = text.replace(strA,strB);
    }
    
    strA = '!';
    strB = 'IV_EXC';
    while ( text.indexOf(strA) != -1)
    {
        text = text.replace(strA,strB);
    }
    
    strA = '#';
    strB = 'IV_CER';
    while ( text.indexOf(strA) != -1)
    {
        text = text.replace(strA,strB);
    }
    
    strA = '%';
    strB = 'IV_PER';
    while ( text.indexOf(strA) != -1)
    {
        text = text.replace(strA,strB);
    }
    
    strA = '<';
    strB = 'IV_MEN';
    while ( text.indexOf(strA) != -1)
    {
        text = text.replace(strA,strB);
    }
    
    strA = '>';
    strB = 'IV_MAI';
    while ( text.indexOf(strA) != -1)
    {
        text = text.replace(strA,strB);
    }
    return text;
    
        
    strA = '"';
    strB = 'IV_ASP';
    while ( text.indexOf(strA) != -1)
    {
        text = text.replace(strA,strB);
    }
    return text;
}


function hint(obj, validated){
    var td = obj.parentNode.parentNode.getElementsByTagName("td")[1];
    
    //a bolinha aparece verde se o campo não for obrigatório
    if(validated)
        td.className = 'welldone';
    
    var span = td.getElementsByTagName("span")[0];
    var span2 = td.getElementsByTagName("span")[1];
    
    name = '';
    if(span2.getAttribute("name"))
        name = span2.getAttribute("name").substring(4)+'';
    if(name == "Virgin")
        span2.setAttribute("name","hint");
    
    span.style.display = "inline";
    span2.style.display = "none";
}


function clearHint(obj, validated){    
    var td = obj.parentNode.parentNode.getElementsByTagName("td")[1];
    if(td.className != 'welldone' && !validated){
        td.className = 'error';
        obj.parentNode.parentNode.getElementsByTagName("td")[1].getElementsByTagName("span")[1].style.display = "inline";
    }
    obj.parentNode.parentNode.getElementsByTagName("td")[1].getElementsByTagName("span")[0].style.display = "none";
    
    //para as bolinhas ficarem aparecendo
    //descomente o codigo a baixo e coloque a linha 215 na posição 218
    //    if(!validation){
    //        obj.parentNode.parentNode.getElementsByTagName("td")[1].getElementsByTagName("span")[1].style.display = "none";
    //    }
    
}
function clearAllHints(form){
    hints = form.getElementsByTagName('td');
    for(i = 1; i < hints.length; i=i+2){
        hints[i].className = "";
    }
    
    for(i = 0; i < form.getElementsByTagName('span').length; i++){
        form.getElementsByTagName('span')[i].style.display = "none";
        if(form.getElementsByTagName('span')[i].getAttribute('name') == 'hint')
            form.getElementsByTagName('span')[i].setAttribute('name','hintVirgin');
    }
}

function pseudoVirgin(form){
    for(i = 0; i < form.getElementsByTagName('span').length; i++){
        if(form.getElementsByTagName('span')[i].getAttribute('name') == 'hintVirgin')
            form.getElementsByTagName('span')[i].setAttribute('name','hint');
    }
}

//function classHint(obj, text, style){
//    var td = obj.parentNode.parentNode.getElementsByTagName("td")[1];
//    var span = td.getElementsByTagName("span")[0];
//    span.innerHTML = '';
//    span.innerHTML = text;
//    span.style.display = "inline";
//    td.className = style;
//}

function validationHints(obj){
    spans = obj.getElementsByTagName('span');
    
    for (i = 0; i < spans.length; i++){
        name = spans[i].getAttribute('name')+'';
        if(spans[i].style.display == 'inline' || name.substring(4) == 'Virgin'){
            td = spans[i].parentNode.parentNode.getElementsByTagName("td")[0];
            input = td.getElementsByTagName('input');
            
            if(input.length > 0)
                input[0].focus();
            else
                td.getElementsByTagName('textarea')[0].focus();
            return false;
        }
    }
    return true;
    
}


function text(obj){
    var td = obj.parentNode.parentNode.getElementsByTagName("td")[1];
    var span = td.getElementsByTagName("span")[1];
    var txt = obj.value;
    //var regexTexto = /^[a-z' 'A-ZãÃáÁàÀêÊéÉèÈíÍìÌôÔõÕóÓòÒúÚùÙûÛçÇ.;:,ºª?-]+$/;
    //regexTexto.test(txt);
    if(txt.length > 0){
        if (txt.length >= 4) {
            //classe verde
            td.className = "welldone";
            return true;
        }
        //        else if(!regexTexto.test(txt)){
        //            //classe vermelha
        //            td.className = "error";
        //            return false;
        //        }
        else {
            //classes cinza
            td.className = "";
            return false;
        }
    }
    else
        td.className = "";
    return false;
}



function validaEmail (email) {

    if (!(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email.value))) {

        return false;
    }
    else{
        return true;
    }
}