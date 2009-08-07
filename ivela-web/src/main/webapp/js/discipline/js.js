/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


//
//  In my case I want to load them onload, this is how you do it!
// 
Event.observe(window, 'load', loadAccordions, false);
Event.observe(window, 'load', function() {Event.observe(content, 'click', click, false);}, false);

var current;

function click(e){ 
    if (!e) e = window.event;
    var evt;
    if (e.target) evt = e.target;
        else if (e.srcElement) evt = e.srcElement;
    if ((evt.className) != null) {
        if (evt.className.toString() == 'accordion_toggle accordion_toggle_active') {

            var course = evt.next(0).getAttribute('id');

            if (current != course) {
                current = course;

                //showDisciplines("course.id="+course);
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
    if ($('vertical_container').getElementsByClassName('accordion_toggle') != "") {
        $('vertical_container').style.display = "block";

        first = bottomAccordion.activate($$('#vertical_container .accordion_toggle')[0]);
        current = first.getAttribute("id");

        //showDisciplines("course.id="+first.getAttribute("id"));    
    }
    else {
        //$('hiddenFlash').innerHTML = '<div class="content-newsflash"><a href="course!list.action"><h3><s:text name="home.enrollment"/></h3></a><p><s:text name="home.enrollment.sub"/></p>';
        $('hiddenFlash').innerHTML = '<div class="content-newsflash"><a href="course!list.action"><h3><s:text name="home.enrollment"/></h3></a><p><s:text name="home.enrollment.sub"/></p>';
    }
}
