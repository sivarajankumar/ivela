/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var courseId;

function markNews(newsId) {
    new Ajax.Request('home!markNewsFlash.action?newsFlash.id=' + newsId,
    {
        method:'get',
        requestHeaders: {Accept: 'application/json'}, 
        onSuccess: function(transport) {
            var json = transport.responseText.evalJSON(true);
        },
        onFailure: function() { alert('Message: Something went wrong...') }
    });  
    
    Effect.toggle('hiddenFlash', 'appear');
}

function display(divBlock, divNone){
    document.getElementById(divBlock).style.display = 'block';
    document.getElementById(divNone).style.display = 'none';
}

function msgChangeClassCssOver(param){
    if (param != method ){
        document.getElementById('spanMessage_'+param).setAttribute(classCss(),"current");
    }
}

function msgChangeClassCssOut(param){
    if (param != method){
        document.getElementById('spanMessage_'+param).setAttribute(classCss(),"");
    }
}

//  In my case I want to load them onload, this is how you do it!
Event.observe(window, 'load', loadAccordions, false);
Event.observe(window, 'load', function() {Event.observe(content, 'click', click, false);}, false);

var current;

function click(e){ 
	if (!e) e = window.event;
    var evt;
    if (e.target) evt = e.target;
        else if (e.srcElement) evt = e.srcElement;
    if ((evt.className) != null) {
        if(evt.className.toString() == 'accordion_toggle accordion_toggle_active'){

            var course = evt.next(0).getAttribute('id');

            if(current != course){
                current = course;
                courseId = course;
                showDisciplines("course.id="+course);
            }                
        }
    }
}

//
//	Set up all accordions
//
function loadAccordions() {		
    var bottomAccordion = new accordion('vertical_container');
    // Open first one

    if($('vertical_container').getElementsByClassName('accordion_toggle') != ""){
        $('vertical_container').style.display = "block";

        first = bottomAccordion.activate($$('#vertical_container .accordion_toggle')[0]);
        current = first.getAttribute("id");
        courseId = current;
        showDisciplines("course.id="+first.getAttribute("id"));    
    }
}


function showDisciplines(params) {
    new Ajax.Request('discipline!listByCourse.action?' + params,
    {
        method:'get',
        requestHeaders: {Accept: 'application/json'}, 
        onSuccess: function(transport) {
            var json = transport.responseText.evalJSON(true);
            var disciplines = json.disciplines.discipline;
            var msg = it_nodisciplines;

            if (disciplines == undefined) {
                var html = '';
                html += '<h4 class="accordion_toggle2">' + msg + '</h4>';
                $('vertical_container2').innerHTML = html;
                var rightAccordion = new accordion('vertical_container2',{
                    classNames : {
                        toggle : 'accordion_toggle2',
                        toggleActive : 'accordion_toggle_active2',
                        content : 'accordion_content2' }
                    , direction : 'vertical'});

                rightAccordion.activate($$('#vertical_container2 .accordion_toggle2')[0]);
            }
            else
                printDisciplines(disciplines);

        },
        onFailure: function() { alert('Message: Something went wrong...') }
    });        
}

function printDisciplines(disciplines) {
    var html = '';

    if (disciplines.length == null) {
        var temp = disciplines;
        disciplines = new Array(1);
        disciplines[0] = temp;
    }
    var disciplinesLength = disciplines.length;

    var start = 0;
    var end = disciplinesLength;

    for (var i = start; i < end; i++) {               
        var discipline = disciplines[i];

        var id = discipline.id;
        var name = discipline.name;                    


        html += '<h4 class="accordion_toggle2"><s:text name="home.discipline"/> ' + name + '</h4>';
        html += '<div class="accordion_content2">';
        html += '<ul class="units">';
        var unit = showUnits(id);
        html += unit;
        html += '</ul>';
        html += '</div>';
    }

    $('vertical_container2').innerHTML = html;

    var rightAccordion = new accordion('vertical_container2',{
        classNames : {
            toggle : 'accordion_toggle2',
            toggleActive : 'accordion_toggle_active2',
            content : 'accordion_content2' }
        , direction : 'vertical'});

    rightAccordion.activate($$('#vertical_container2 .accordion_toggle2')[0]);
}

function printUnits(disciplineId, units) {
    var html = '';

    if (units.length == null) {
        var temp = units;
        units = new Array(1);
        units[0] = temp;
    }
    var unitsLength = units.length;

    var start = 0;
    var end = unitsLength;

    for (var i = start; i < end; i++) {               
        var unit = units[i];

        var id = unit.id;
        var name = unit.name;                    

        html += '<li><a href="discipline!showContent.action?course.id='+courseId+ '&discipline.id=' + disciplineId + '&unit.id=' + id + '">' + name + '</a></li>';
    }
    return html;
}


function showUnits(disciplineId) {
    var html = '';
    new Ajax.Request('unit!listJsonByDiscipline.action?discipline.id=' + disciplineId,
    {
        method:'get',
        requestHeaders: {Accept: 'application/json'},
        asynchronous: false,
        onSuccess: function(transport) {
            var json = transport.responseText.evalJSON(true);
            var units = json.units.unit;
            var msg = it_nounits;
            html = '';
            if (units == undefined) {
                html = '<li><a>' + msg + '</a></li>';
            }
            else {
                html = printUnits(disciplineId, units);
            }
        },
        onFailure: function() { alert('Message: Something went wrong...') }
    });          
    return html;
}
