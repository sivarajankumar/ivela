<%-- 
    Document   : show course
    Created on : Sep 15, 2008, 2:07:13 PM
    Author     : marcus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="cal" uri="/jscalendar" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<head>
    <link href="../css/accordion_course.css"  rel="stylesheet" type="text/css" />
    <link href="../css/cadAtividades.css" rel="stylesheet" type="text/css" />
    <link href="../css/lightbox.css"  rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../fckeditor/fckeditor.js"></script>
    <script type="text/javascript" src="../js/admin/tools.js"></script>
    <script type="text/javascript" src="../js/admin/utils.js"></script>
    <script type="text/javascript" src="../js/admin/courses.js"></script>
    <script type="text/javascript" src="../js/admin/discipline.js"></script>
    <script type="text/javascript" src="../js/admin/unit.js"></script>
    <script type="text/javascript" src="../js/admin/unitContent.js"></script>
    <script type="text/javascript" src="../js/admin/challenge.js"></script>
    <script type="text/javascript" src="../js/admin/exercise.js"></script>
    <script type="text/javascript" src="../js/admin/exam.js"></script>
    <script type="text/javascript" src="../js/admin/question.js"></script>
    <script type="text/javascript" src="../js/admin/dictionary.js"></script>
    <script type="text/javascript" src="../js/admin/webtoolkit.aim.js"></script>
    <script type="text/javascript" src="../js/lightbox.js"></script>
    
    <script type="text/javascript">
        var message = '<s:property value="message" />';
        var questionInputChances = '<h2><s:text name="question.input.chances" /></h2>';
        var exerciseRequisite = '<h2><s:text name="input.exercise.requisite" /></h2>';
        var examRequisite = '<h2><s:text name="input.exam.requisite"/></h2>';
        var questoesCriadas = '<h2><s:text name="questoes.criadas"/></h2>';
        var questionText = '<h2><s:text name="question.input.text" /></h2>';
        if (message != null && message != '') {
            //alert(message);
        }
    </script>
    <script>
        function textCounter(field, countfield, maxlimit) {
            if (field.value.length > maxlimit) // if too long...trim it!
                field.value = field.value.substring(0, maxlimit);
            // otherwise, update 'characters left' counter
            else 
                countfield.value = maxlimit - field.value.length;
        }

    </script>
    <cal:head />
</head>
<s:actionerror />

<div id="breadcrumb">
    <p><s:text name="breadcrumb.youAreHere"/></p>
    <ul>
        <li><a href="home.action"><s:text name="admin.home"/></a></li>
        <li class="current"><s:text name="admin.courses" /></li>
    </ul>
</div>

<div id="col-1-home">
    <h2><s:text name="course.list.title" /></h2>
    <div class="create_new_course">
        <form action="#">
            <input class="btn-new-one" type="button" value="<s:text name="course.list.add" />" onclick="showEntryCourse();" />
        </form>
    </div>
    
    <div id="vertical_container2" >
        <s:if test="(courseList == null || courseList.size() == 0)">
            <h3 class="no_grades"><s:text name="admin.nocourses" /></h3>
        </s:if>
        <s:else>
            <s:iterator value="courseList">
                <s:if test="name.length() > 22">
                    <h3 class="accordion_toggle_course2" title="<s:property value="name" />"><s:property value="name.substring(0, 22)" />...</h3>
                </s:if>
                <s:else>
                    <h3 class="accordion_toggle_course2"><s:property value="name" /></h3>
                </s:else>
                <div class="accordion_content_course2" id="<s:property value="id" />">
                    
                    <div id="vertical_nested_container">
                        <s:if test="(disciplines == null || disciplines.size() == 0)">
                            <h4 class="no_grades"><s:text name="admin.nodisciplines" /></h4>
                        </s:if>
                        <s:else>
                            <s:iterator value="disciplines">
                                <s:if test="name.length() > 25">
                                    <h4 class="vertical_accordion_toggle" title="<s:property value="name" />"><s:property value="name.substring(0, 25)" />...</h4>
                                </s:if>
                                <s:else>
                                    <h4 class="vertical_accordion_toggle"><s:property value="name" /></h4>
                                </s:else>
                                <div class="vertical_accordion_content" id="<s:property value="id" />">
                                    <div id="vertical_nested_container2">
                                        <s:if test="(units == null || units.size() == 0)">
                                            <h4 class="no_grades"><s:text name="admin.nounits" /></h4>
                                        </s:if>
                                        <s:else>
                                            <s:iterator value="units">
                                                <s:if test="name.length() > 22">
                                                    <h5 class="vertical_accordion_toggle2" title="<s:property value="name" />"><s:property value="name.substring(0, 22)" />...</h5>
                                                </s:if>
                                                <s:else>
                                                    <h5 class="vertical_accordion_toggle2"><s:property value="name" /></h5>
                                                </s:else>                                    
                                                <div class="vertical_accordion_content2" id="<s:property value="id" />">
                                                    <div id="vertical_nested_container3">
                                                        <s:if test="(unitContents == null || unitContents.size() == 0)">
                                                            <h4 class="no_grades"><s:text name="admin.nounitcontents" /></h4>
                                                        </s:if>
                                                        <s:else>
                                                            <s:iterator value="unitContents">
                                                                <s:if test="title.length() > 22">
                                                                    <h6 class="vertical_accordion_toggle3" title="<s:property value="title" />"><s:property value="title.substring(0, 22)" />...</h6>
                                                                </s:if>
                                                                <s:else>
                                                                    <h6 class="vertical_accordion_toggle3"><s:property value="title" /></h6>
                                                                </s:else>                                    
                                                                <div class="vertical_accordion_content3" id="<s:property value="id" />">
                                                                    <div id="vertical_nested_container4">
                                                                        
                                                                        <h6 class="vertical_accordion_toggle4" id="exercises" >Exercises</h6>
                                                                        <div class="vertical_accordion_content4" id="<s:property value="id" />">
                                                                            <ul id="ulExerciseAccordeon_<s:property value="id" />">
                                                                                <s:if test="(exercises == null || exercises.size() == 0)">
                                                                                    <h4 class="no_grades"><s:text name="admin.noexercises" /></h4>
                                                                                </s:if>
                                                                                <s:else>
                                                                                    <s:iterator value="exercises">
                                                                                        <li id="listExerciseAccordeon_<s:property value="id" />"><a href="javascript:showExercise('<s:property value="id" />',false)"><s:property value="title" /></a></li>
                                                                                    </s:iterator>
                                                                                </s:else>
                                                                            </ul>
                                                                        </div>
                                                                        <h6 class="vertical_accordion_toggle4" id="exams">Exams</h6>
                                                                        <div class="vertical_accordion_content4" id="<s:property value="id" />">
                                                                            <ul id="ulExamAccordeon_<s:property value="id" />">
                                                                                <s:if test="(exams == null || exams.size() == 0)">
                                                                                    <h4 class="no_grades"><s:text name="admin.noexams" /></h4>
                                                                                </s:if>
                                                                                <s:else>
                                                                                    <s:iterator value="exams">
                                                                                        <li id="listExamAccordeon_<s:property value="id" />"><a href="javascript:showExam('<s:property value="id" />',false)"><s:property value="title" /></a></li>
                                                                                    </s:iterator>
                                                                                </s:else>                                                                        
                                                                            </ul>
                                                                        </div>
                                                                        
                                                                    </div>
                                                                </div>
                                                            </s:iterator>                                                        
                                                        </s:else>
                                                    </div>
                                                </div>
                                            </s:iterator>                                        
                                        </s:else>
                                    </div>
                                </div>
                            </s:iterator>    
                        </s:else>
                    </div>
                </div>
            </s:iterator>
        </s:else>
    </div>
</div>
<!-- end col-1-home -->

<div id="showEntryCourse" style="display: none;">
    <div id="col-2-home">
        <div class="container-courses">
            <div class="content-courses">
                <input type="hidden" name="input.course.id" id="input.course.id" value="" />
                <h1><s:text name="home.course" /></h1><br/><br/><br/>
                <label><s:text name="course.input.name" /></label><br/>
                <input type="hidden" name="input.course.repository" id="input.course.repository" value=""/>
                <input type="text" name="input.course.name" id="input.course.name" value="" size="61" maxlength="35" /><a href="#" class="tooltip" onmouseover="return escape('Entre com o nome do curso')"></a><br/>
                <label><s:text name="course.input.description" /></label><br/>
                <textarea onKeyDown="textCounter(this,$('remLen'),250);" onKeyUp="textCounter(this,$('remLen'),250);" rows="4" name="input.course.description" id="input.course.description" cols="70" ></textarea><a href="#" class="tooltip" onmouseover="return escape('Entre com uma breve descrição do curso')"></a><br/>
                <s:text name="course.show.remaining" /><input readonly type=text id="remLen" name=remLen size=3 maxlength=3 value="250"/><br/>
                <label><s:text name="course.input.targetAudience" /></label><br/>
                <textarea onKeyDown="textCounter(this,$('remLenTargetAudience'),250);" onKeyUp="textCounter(this,$('remLenTargetAudience'),250);" rows="3" name="input.course.targetAudience" id="input.course.targetAudience" cols="70"></textarea><a href="#" class="tooltip" onmouseover="return escape('Entre com o público alvo do curso')"></a><br/>
                <s:text name="course.show.remaining" /><input readonly type=text id="remLenTargetAudience" name=remLenTargetAudience size=3 maxlength=3 value="250"/><br/>
                <s:form action="course!updateImage" id="updateImage" method="post" enctype="multipart/form-data">
                    <label><s:text name="course.input.image" /></label><br/>
                    <s:hidden id="iform.course.id" name="course.id" />
                    <s:file name="upload" theme="simple" id="input.course.image"  /><br/>                    
                </s:form>
                <label><s:text name="course.input.contents" /></label><br/>
                <!--<input type="text" name="input.course.contents" id="input.course.contents" value="" /><br />-->
                <textarea onKeyDown="textCounter(this,$('remLenContents'),500);" rows="6" onKeyUp="textCounter(this,$('remLenContents'),500);" name="input.course.contents" id="input.course.contents" cols="70"></textarea><a href="#" class="tooltip" onmouseover="return escape('Entre com a ementa do curso')"></a><br/>
                <s:text name="course.show.remaining" /><input readonly type=text id="remLenContents" name=remLen size=3 maxlength=3 value="500"/><br/>
                <input type="button" name="input.course.submit" id="input.course.submit" value="<s:text name="systemUser.input.btnSave" />" onclick="submitCourse($('input.course.id').value)" />
            </div>
        </div>
    </div>
</div>

<div id="showEntryDiscipline" style="display: none;">
    <div id="col-2-home">
        <div class="container-courses">
            <div class="content-courses">
                <input type="hidden" name="input.discipline.course.id" id="input.discipline.course.id" value="" />
                <input type="hidden" name="input.discipline.id" id="input.discipline.id" value="" />
                <h1><s:text name="discipline.input.sessionTitle" /></h1><br />
                <br />
                <br />
                <label><s:text name="discipline.input.name" /></label><br />
                <input type="text" name="input.discipline.name" id="input.discipline.name" value="" size="61" maxlength="35" /><a href="#" onmouseover="return escape('Entre com o nome da disciplina')">?</a><br />
                <input type="button" name="input.discipline.submit" id="input.discipline.submit" value="<s:text name="systemUser.input.btnSave" />" onclick="submitDiscipline($('input.discipline.id').value);" /><br />    
            </div>
        </div>
    </div>
</div>

<div id="showEntryUnit" style="display: none;">
    <div id="col-2-home">
        <div class="container-courses">
            <div class="content-courses">
                <input type="hidden" name="input.unit.discipline.id" id="input.unit.discipline.id" value="" />
                <input type="hidden" name="input.unit.id" id="input.unit.id" value="" />
                <h1><s:text name="unit.input.title" /></h1><br />
                <br />
                <br />
                <label><s:text name="unit.input.name" /></label><br />
                <input type="text" name="input.unit.name" id="input.unit.name" value="" maxlength="35" size="61" /><a href="#" onmouseover="return escape('Entre com o nome da unidade')">?</a><br />
                <input type="button" name="input.discipline.submit" id="input.discipline.submit" value="<s:text name ="systemUser.input.btnSave" /> " onclick="submitUnit($('input.unit.id').value);" /><br />        
            </div>
        </div>
    </div>
</div>

<div id="showEntryUnitContent" style="display: none;">
    <div id="col-2-home">
        <div class="container-courses">
            <div class="content-courses">
                <form action="#" method="POST" id="unitContentForm">
                    <input type="hidden" name="unitContent.unitId" id="input.unitContent.unit.id" value="" />
                    <input type="hidden" name="unitContent.id" id="input.unitContent.id" value="" />
                    <input type="hidden" name="unitContent.description" id="input.unitContent.description" value="" />
                    <h1><s:text name="unitContent.input.add" /></h1><br />
                    <br />
                    <br />
                    <label><s:text name="unitContent.input.title" /></label><br />
                    <input type="text" name="unitContent.title" id="input.unitContent.title" value="" maxlength="35" size="61" /><a href="#" onmouseover="return escape('Entre com o nome da aula')">?</a><br />                
                    <label><s:text name="unitContent.input.order_n" /></label><br />
                    <select style="width: 80px;" name="unitContent.orderN" id="input.unitContent.order_n"></select><a href="#" onmouseover="return escape('Entre com a ordem da aula')">?</a><br />                                    
                    <label><s:text name="unitContent.input.type" /></label><br/>
                    <%-- v1 --%>
                    <input type="radio" id="input.unitContent.type1" name="unitContent.type" value="1" checked>Pdf</input>
                    <input type="radio" id="input.unitContent.type3" name="unitContent.type" value="3">Html</input>
                    <%-- v2
                    <s:radio  id="input.unitContent.type" theme="simple" value="unitContent.type" cssClass="input_radio" list="#{'1':'Pdf', '3' :'Html'}" name="unitContent.type"  />
                    --%>
                    <br>
                    <label><s:text name="unitContent.input.content" /></label><br />
                    <div id="unitContent.fck" style="height: 600px;">
                        <script type="text/javascript">
                            var oFCKeditor = new FCKeditor( 'FCKeditor1' ) ;
                            oFCKeditor.BasePath	= '../fckeditor/' ;
                            oFCKeditor.Height	= 400 ;
                            oFCKeditor.Width        = 570;
                            oFCKeditor.ToolbarSet   = "Ivela";
                            oFCKeditor.Value	= '' ;
                            oFCKeditor.Create() ;
                        </script>
                        <br />
                    </div>
                    <input type="button" name="input.unitContent.submit" id="input.unitContent.submit" value="<s:text name="systemUser.input.btnSave" />" onclick="submitUnitContent($('input.unitContent.id').value);" /><br />        
                </form>
            </div>
        </div>
    </div>
</div>

<div id="showCourse" style="display: none;">
    <div id="col-2-home">
        <div class="container-courses">
            <div class="content-courses">
                <h1><span id="course.name"></span></h1>
                <form action="#" class="form-edit">
                    <input type="button" value="<s:text name="course.show.editCourse" />" onclick="showEditCourse($('course.id').value);" />
                    <input type="button" value="<s:text name="course.show.removeCourse"/>" onclick="deleteCourse($('course.id').value);" />
                    <input type="hidden" id="course.id" name="course.id" value="" />
                </form>
                <br class="clear" />
                <p style=" font-family:Arial,Helvetica,sans-serif; font-size:15px;font-weight:bold; color:#444;"><span id="course.description"></span></p>
                <div class="images">
                    
                    <%--<h2><s:text name="course.input.image" /><br /><img id="course.image" src="../images/foto_profile.jpg" width="80" height="80" /></h2>--%>
                    <h2><s:text name="course.input.partner" /><br /><img id="course.image" src="../images/foto_profile.jpg" width="80" height="80" /></h2>
                    <br class="clear" />
                </div>
                <div class="stats">
                    <h2><s:text name="course.input.statitics" /></h2>
                    <p><s:text name="course.input.students" /><br  /><span id="course.student.count"></span></p>
                    
                    <p><s:text name="course.input.grade" /><br  /><span id="course.grade.count"></span></p>
                    <p><s:text name="course.input.graduated" /> <br  /><span id="course.graduated.count"></span></p>
                    <br class="clear" />
                </div>
                <h2 style="margin-bottom:10px;"><s:text name="course.input.professor" />:</h2>
                <div id="course.professors">
                    <p></p>
                </div>
                <h2 style="margin-bottom:10px;"><s:text name="course.input.tutor" />:</h2>
                <div id="course.tutors">
                    <p></p>
                </div>
                <h2 style="margin-bottom:10px;"><s:text name="course.input.requisites" /></h2>
                <div id="course.requirements">
                </div>
                <h2 style="margin-bottom:10px;"><s:text name="course.input.targetAudience" />:</h2>
                <div id="course.target.public">
                </div>
                <div class="actions-box">
                    <h2 style="font-size:25px;color:#ff6600;"><s:text name="discipline.show.discipline" /></h2>
                    <div class="edit-tools">
                        <ul>
                            <li><a class="icon-new" href="javascript:showEntryDiscipline($('course.id').value);"><s:text name= "course.show.new" /></a></li>
                            <li><a class="icon-edit" href="javascript:showEditDiscipline();"><s:text name= "course.show.edit" /></a></li>
                            <li><a class="icon-delete" href="javascript:deleteDiscipline();"><s:text name= "course.show.remove" /></a></li>
                            <li><a class="icon-new" href="javascript:showDictionary();"><s:text name= "dictionary.show" /></a></li>
                            <br class="clear" />
                        </ul>
                        
                    </div>
                    <div class="edit-box" id="course.disciplines">
                    </div>
                </div>
                <!-- end actions-box -->
            </div>
            <!-- End content-courses -->
        </div>
        
    </div>    
</div>

<div id="showDiscipline" style="display: none;">
    <div id="col-2-home">
        <div class="container-courses">
            <div class="content-courses">
                <h1><span id="discipline.name"></span></h1>
                <form action="#" class="form-edit">
                    <input class ="edit"type="button" value="<s:text name="course.show.editDiscipline" />"  onclick="showEditDisciplineById($('discipline.id').value);"/>
                    <input type="hidden" id="discipline.id" name="discipline.id" value="" />
                </form>
                <br class="clear" />
                <div class="actions-box">
                    <h2><s:text name="unit.pageTitle" /></h2>
                    <div class="edit-tools">
                        <ul>
                            <li><a class="icon-new" href="javascript:showEntryUnit($('discipline.id').value);"><s:text name="unit.input.new" /></a></li>
                            <li><a class="icon-edit" href="javascript:showEditUnit();"><s:text name="unit.input.edit" /></a></li>
                            <li><a class="icon-delete"href="javascript:deleteUnit();"><s:text name="unit.input.remove" /></a></li>
                            
                            <br class="clear" />
                        </ul>
                        
                    </div>
                    <div class="edit-box" id="discipline.units">
                    </div>
                </div>
                <!-- end actions-box -->
            </div>
            <!-- End content-courses -->
        </div>
        
    </div>    
</div>

<div id="showDictionary" style="display: none;">
    <div id="col-2-home">
        <div class="container-courses">
            <div class="content-courses">
                <h1><s:text name="course.show.dictionary" /></h1>
                <br class="clear" />
                <div class="actions-box">
                    <form id="dictionary.form">
                        <s:text name="course.show.search" /><input type="text" id="search.dictionary" /><input type="button" onclick="searchDictionary()" value="<s:text name ="dictionary.search.submit" />" /><br /><br />
                        
                        <input type="hidden" id="dictionary.id" /><br />
                        <h2><s:text name="dictionary.Title" /></h2>
                        <input type="text" id="dictionary.title" /><br />
                        <h2><s:text name="dictionary.description" /></h2>
                        <textarea type="text" id="dictionary.description"></textarea><br />
                        <input type="button" value="Back" onclick="backDictionary();"/>
                        <div id="createDict"  style="display: none;">
                            <input type="button" value="<s:text name ="dictionary.show.submit" /> " onclick="submitDictionary($('dictionary.id').value);" />
                        </div>
                        <div id="updateDict" style="display: none;">
                            <input type="button" value="<s:text name ="dictionary.show.update" /> " onclick="submitDictionary($('dictionary.id').value);" />
                        </div>
                        <div id="deleteDict" style="display: none;">
                            <input type="button" value="<s:text name ="dictionary.show.delete" /> " onclick="deleteDictionary($('dictionary.id').value);" />
                        </div>
                    </form>
                    
                    <%--
                    <div class="edit-tools">
                        <ul>
                            <li><a class="icon-new" href="#" onclick="showEntryUnit($('discipline.id').value);"><s:text name="unit.input.new" /></a></li>
                            <li><a class="icon-edit" href="javascript:showEditUnit();"><s:text name="unit.input.edit" /></a></li>
                            
                            <li><a class="icon-delete"href="#" onclick="deleteUnit();"><s:text name="unit.input.remove" /></a></li>
                            <br class="clear" />
                        </ul>
                        
                    </div>
                    <div class="edit-box" id="discipline.units">
                    </div>
                    --%>
                </div>
                <!-- end actions-box -->
            </div>
            <!-- End content-courses -->
        </div>
        
    </div>    
</div>

<div id="showUnit" style="display: none;">
    <div id="col-2-home">
        <div class="container-courses">
            <div class="content-courses">
                <h1><span id="unit.name"></span></h1>
                <form action="#" class="form-edit">
                    <input type="button" value="<s:text name="course.show.editUnit" />" onclick="showEditUnitById($('unit.id').value);"/>
                    <input type="hidden" id="unit.id" name="unit.id" value="" />
                </form>
                <br class="clear" />
                <div class="actions-box">
                    <h2><s:text name="unitContent.input.unitContent" /></h2>
                    <div class="edit-tools">
                        <ul>
                            <li><a class="icon-new" href="javascript:showEntryUnitContent($('unit.id').value);"><s:text name="unitContent.input.add" /></a></li>
                            <li><a class="icon-new" href="javascript:showUploadUnitContent($('unit.id').value);"><s:text name="unitContent.input.upload" /></a></li>
                            <li><a class="icon-edit" href="javascript:showEditUnitContent();"><s:text name="unitContent.input.edit" /></a></li>                            
                            <li><a class="icon-delete" href="javascript:deleteUnitContent();"><s:text name="unitContent.input.remove" /></a></li>
                            <li><a class="icon-message"href="javascript:showNewsChallenge();"><s:text name= "course.add.challenge" /></a></li>
                            <li><a id="chatId" class="icon-chat" href="#" target="_blank" onmouseover="javascript:vaiabri();"><s:text name= "course.show.chat" /></a></li>
                            
                            <br class="clear" />
                        </ul>
                        
                    </div>
                    <div class="edit-box" id="unit.unitsContent">
                    </div>
                    
                   
                </div>
            </div>
        </div>
    </div>    
</div>

<script>
    function vaiabri() {
         
        $('chatId').href = 'course!showChat.action?course.id=' + $('course.id').value; 
        
    }
    </script>

<div id="showUnitContent" style="display: none;">
    <div id="col-2-home">
        <div class="container-courses">
            <div class="content-courses">
                <h1><span id="unitContent.title"></span></h1>
                <form action="#" class="form-edit"><input type="button" value="<s:text name="course.show.editUnitContent" />" onclick="showEditUnitContentById($('unitContent.id').value);" /></form>
                <input type="hidden" id="unitContent.id" name="unitContent.id" value="" />
                <br class="clear" />
                <%--
                <div class="actions-box">
                    <h2><s:text name="course.show.activities" /></h2>
                    <div class="edit-tools">
                        <ul>
                            <li><a class="icon-new" href="#" onclick="showEntryExam($('unitContent.id').value);"><s:text name = "course.show.newExam" /></a></li>
                            <li><a class="icon-new" href="#" onclick="showEntryExercise($('unitContent.id').value);"><s:text name="course.show.newExercise" /></a></li>
                            <li><a class="icon-edit" href="javascript:showEditCourse();"><s:text name="course.show.edit" /></a></li>                            
                            <li><a class="icon-delete"href="#"><s:text name="course.show.remove" /></a></li>
                            <br class="clear" />
                        </ul>
                        
                    </div>
                    <div class="edit-box" id="unitContent.exercises">
                    </div>
                    <div class="edit-box" id="unitContent.exams">
                    </div>
                </div>
                --%>
                
                <p><span id="unitContent.description"></span></p>
                <br class="clear"/>
                <div id="unitContent.pdf" style="height: 600px; display: none;"></div>
                <div id="unitContent.html" style="height: 600px;"><a  id="seeUnitContent" href="" class="lightwindow page-options" params="lightwindow_type=external,lightwindow_width=1024"><s:text name="click.tosee.unitContent"/></a></div>
            </div>
        </div>
    </div>    
</div>


<div id="showUploadUnitContent" style="display: none;">
    <div id="col-2-home">
        <div class="container-courses">
            <div class="content-courses">
                <h1><s:text name="unitContent.input.uploadPackage" /></h1>
                <br class="clear" />
                <s:actionmessage />
                
                <s:form id="_upload" name="_name" action="unitContent!upload.action" method="POST" enctype="multipart/form-data" theme="simple">
                    <s:hidden name="unitContent.id" id="upload.unitContent.id" value="" />
                    <s:hidden name="unitContent.type" value="2" />
                    <s:hidden name="unitContent.unitId" id="upload.unit.id" value="" />
                    <s:hidden name="unitContent.description" value="none" />
                    <h1><s:text name="unitContent.input.add" /></h1><br />
                    <br />
                    <br />
                    <label><s:text name="unitContent.input.add" /></label><br />
                    <s:textfield name="unitContent.title" id="upload.unitContent.title" maxLength="35" size="61" /><a href="#" onmouseover="return escape('Entre com o nome da aula')">?</a>
                    <br />               
                    <label><s:text name="unitContent.input.order_n" /></label><br />
                    <select style="width: 80px;" name="unitContent.orderN" id="upload.unitContent.order_n"></select><a href="#" onmouseover="return escape('Entre com a ordem da aula')">?</a><br />                                    
                    <br />
                    <label><s:text name="unitContent.input.package" /></label><br />
                    <s:file name="upload" key="repository.input.file" theme="simple" /><a href="#" onmouseover="return escape('Entre com o pacote da aula')">?</a>
                    <br />
                    <br />
                    <label><s:text name="unitContent.input.width" /></label><br />
                    <s:textfield name="unitContent.width" id="upload.unitContent.width" maxLength="4" size="8" /><a href="#" onmouseover="return escape('Entre com a largura do frame da aula')">?</a><br />
                    <label><s:text name="unitContent.input.height" /></label><br />
                    <s:textfield name="unitContent.height" id="upload.unitContent.height" maxLength="4" size="8" /><a href="#" onmouseover="return escape('Entre com a altura do frame da aula')">?</a><br />
                    <s:submit key="systemUser.input.btnSave" />
                </s:form>
            </div>
        </div>
    </div>
</div>

<div id="showManagerExercises" style="display: none;">
    <div id="col-2-home">
        <div class="container-courses">
            <div class="content-courses">
                <h1><span><s:text name="course.show.exercise"/></span></h1>
                <%--form action="" class="form-edit"><input type="button" value="<s:text name="course.show.editUnitContent" />" onclick="showEditUnitContent($('unitContent.id').value);" /></form--%>
                <input type="hidden" id="unitContent.id" name="unitContent.id" value="" />
                <br class="clear" />
                <div class="actions-box">
                    <h2><s:text name="course.show.newExercise" /></h2>
                    <div class="edit-tools">
                        <ul>
                            <!--li><a class="icon-new" href="#" onclick="showEntryExam($('unitContent.id').value);"><s:text name = "course.show.newExam" /></a></li-->
                            <li><a class="icon-new" href="javascript:showEntryExercise($('unitContent.id').value);"><s:text name="course.show.newExercise" /></a></li>
                            <li><a class="icon-edit" href="javascript:showEditExercise(null,$('unitContent.id').value);"><s:text name="course.show.edit" /></a></li>                            
                            <li><a class="icon-delete"href="javascript:deleteExercises()"><s:text name="course.show.remove" /></a></li>
                            <br class="clear" />
                        </ul>
                        
                    </div>
                    <div class="edit-box" id="unitContent.buttons.exercise">
                    </div>
                    <div class="edit-box" id="unitContent.exercises">
                    </div>
                </div>
            </div>
        </div>
    </div>    
</div>

<div id="showManagerExams" style="display: none;">
    <div id="col-2-home">
        <div class="container-courses">
            <div class="content-courses">
                <h1><span><s:text name="course.show.exam" /></span></h1>
                <%--form action="" class="form-edit"><input type="button" value="<s:text name="course.show.editUnitContent" />" onclick="showEditUnitContent($('unitContent.id').value);" /></form--%>
                <input type="hidden" id="unitContent.id" name="unitContent.id" value="" />
                <br class="clear" />
                <div class="actions-box">
                    <h2><s:text name="course.show.newExam" /></h2>
                    <div class="edit-tools">
                        <ul>
                            <li><a class="icon-new" href="javascript:showEntryExam($('unitContent.id').value);"><s:text name = "course.show.newExam" /></a></li>
                            <!--li><a class="icon-new" href="#" onclick="showEntryExercise($('unitContent.id').value);"><s:text name="course.show.newExercise" /></a></li-->
                            <li><a class="icon-edit" href="javascript:showEditExam(null,$('unitContent.id').value);"><s:text name="course.show.edit" /></a></li>                            
                            <li><a class="icon-delete"href="javascript:deleteExams();"><s:text name="course.show.remove" /></a></li>
                            <br class="clear" />
                        </ul>
                        
                    </div>
                    <div class="edit-box" id="unitContent.buttons.exam">
                    </div>
                    <div class="edit-box" id="unitContent.exams">
                    </div>
                </div>
            </div>
        </div>
    </div>    
</div>

<%--div id="showEntryExercise" style="display: none;">
    <div id="col-2-home">
        <div class="container-courses">
            <div class="content-courses">
                <input type="hidden" name="input.exercise.id" id="input.exercise.id" value="" />
                <form id="form_exercise_entry" action="#" class="cadastro-avaliacao">
                    <h1><s:text name="course.show.newExercise"/></h1><br />
                    <br />
                    <br />
                    <h2><s:text name="course.input.title"/>:</h2>
                    <input type="text" name="input.exercise.title" id="input.exercise.title" value=""  class="titulo-avaliacao" /><a href="#" class="tooltip" onmouseover="return escape('Enter exercise title')"></a><br />
                    <span id="errorExerciseTitle" class="validation"><s:text name="input.exercise.errorTitle" /></span>
                    <h2><s:text name="exercise.input.description"/></h2>
                    <textarea class="questao-texto" type="text" name="input.exercise.description" id="input.exercise.description" ></textarea> <a href="#" class="tooltip" onmouseover="return escape('Describe the exercise')"></a><br />
                    <span id="errorExerciseDescription" class="validation"><s:text name="input.exercise.errorDescription" /></span>
                    <h2><s:text name="exercise.input.navigable"/></h2><br />
                    <s:radio list="#{'true':'Yes', 'false' :'No'}" value="%{'true'}" name="input.exercise.navigable" id="input.exercise.navigable"/><a href="#" class="tooltip" onmouseover="return escape('Set if this exercise has navigable questions.')"></a><br />
                    <h2><s:text name="exercise.input.weight"/></h2> 
                    <s:select cssClass="lista-peso"  name="input.exercise.weight" id="input.exercise.weight" value="1" list="#{'1':'1','2':'2','3':'3', '4':'4','5':'5','6':'6','7':'7','8':'8','9':'9'}"/>
                    <a class="tooltip" href="#" onmouseover="return escape('Enter exercise weight')"></a><br/>
                    <h2><s:text name="exercise.input.chances"/></h2>
                    <s:select cssClass="lista-peso" name="input.exercise.chances" id="input.exercise.chances" value="1" list="#{'1':'1','2':'2','3':'3', '4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'Sem limites'}"/>
                    <br/>
                     
                    <div class="actions-box">
                        <div class="edit-box" id="exercise.list.unitContent">  
                        </div>
                    </div>
                    <br/>
                    <p class="navega-teste">
                        <a  class="cancel" title="Cancelar criação do Exercicio" onclick="showManagerExercises($('input.unitContent.id').value)">Cancel</a>
                        <!--a class="previous" title="Voltar para a etapa anterior">Previous</a-->
                        <a id="nextExercise" class="next" title="Ir para a próxima etapa" onclick="submitExercise($('input.exercise.id').value);">Next</a>
                        
                        <br class="clear" />
                    </p>
                    
                </form>
            </div>
        </div>
    </div>
</div --%>

<div id="showEntryExercise" style="display: none;">
    <div id="col-2-home">
        <div class="container-courses">
            <div class="content-courses">                
                <h1><s:text name="course.show.newExercise"/></h1>
                <br class="clear" />
                <div class="steps-box">
                    <div>
                        <div>
                            <span id="step"><s:text name="course.show.step" /></span>
                            
                            <span id="current" href="">1</span>
                            <span>2</span>
                            <span>3</span>
                            
                        </div>
                    </div>
                    <div class="tip-step"><s:text name="show.tip" /></div>
                </div>
                
                
                <input type="hidden" name="input.exercise.id" id="input.exercise.id" value="" />
                <form id="form_exercise_entry" action="#" class="cadastro-avaliacao">
                    <table border="0" class="show_hints">
                        <tr>
                            <td>
                                <p><s:text name="course.input.title"/>:</p>
                                <input type="text" id="input.exercise.title" onfocus="hint(this,text(this))" onblur="clearHint(this, text(this));" onkeyup="text(this);" value="" />
                            </td>
                            <td width="50">
                                <span class="hint"><s:text name="show.tip.exercise" /></span>
                                <span class="hint" name="hintVirgin">&nbsp;</span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p><s:text name="exercise.input.description"/></p>
                                <textarea id="input.exercise.description" cols="27" onfocus="hint(this,true)" onblur="clearHint(this, true);" ></textarea>
                            </td>
                            <td>
                                <span class="hint"><s:text name="show.tip.description" /></span>
                                <span class="hint">&nbsp;</span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p><s:text name="exercise.input.navigable"/></p>
                                <s:radio cssClass="input_radio" theme="simple" list="#{'true':'Yes', 'false' :'No'}" value="true" id="input.exercise.navigable" name="input.exercise.navigable" onfocus="hint(this,true)" onblur="clearHint(this, true);"/>
                            </td>
                            <td>
                                <span class="hint"><s:text name="show.tip.description" /></span>
                                <span class="hint">&nbsp;</span>
                            </td>
                        </tr>
                        <tr>
                            <td> 
                                <p><s:text name="exercise.input.weight"/></p>
                                <s:select theme="simple" name="input.exercise.weight" id="input.exercise.weight" value="1" list="#{'1':'1','2':'2','3':'3', '4':'4','5':'5','6':'6','7':'7','8':'8','9':'9'}" onfocus="hint(this,true)" onblur="clearHint(this, true);" />
                            </td>
                            <td>
                                <span class="hint"><s:text name="show.tip.weight" /></span>
                                <span class="hint">&nbsp;</span>
                            </td>
                        </tr>
                        <tr>
                            <td> 
                                <p><s:text name="exercise.input.chances"/></p>
                                <s:select theme="simple" name="input.exercise.chances" id="input.exercise.chances" value="1" list="#{'1':'1','2':'2','3':'3', '4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'Sem limites'}" onfocus="hint(this,true)" onblur="clearHint(this, true);" />
                            </td>
                            <td>
                                <span class="hint"><s:text name="show.tip.trial"/></span>
                                <span class="hint">&nbsp;</span>
                            </td>
                        </tr>
                    </table>
                    <div class="actions-box2">
                        <div class="edit-box" id="exercise.list.unitContent">  
                        </div>
                    </div>
                    <br/>
                    <p class="navega-teste">
                        <a  class="cancel" title="Cancelar criação do Exercicio" onclick="showManagerExercises($('input.unitContent.id').value)">Cancel</a>
                        <!--a class="previous" title="Voltar para a etapa anterior">Previous</a-->
                        <a id="nextExercise" class="next" title="Ir para a próxima etapa" onclick="submitExercise($('input.exercise.id').value);">Next</a>
                        
                        <br class="clear" />
                    </p>
                    
                </form>
            </div>
        </div>
    </div>
</div>

<div id="showEntryExam" style="display: none;">
    <div id="col-2-home">
        <div class="container-courses">
            <div class="content-courses">
                <h1><s:text name="course.show.addExam" /></h1>
                <br class="clear" />
                <div class="steps-box">
                    <div>
                        <div>
                            <span id="step"><s:text name="course.show.step" /></span>
                            <span id="current" href="">1</span>
                            <span>2</span>
                            <span>3</span>
                        </div>
                    </div>
                    <div class="tip-step"><s:text name="show.tip.step" /></div>
                </div>
                <form id="form_exam_entry"  action="#" class="cadastro-avaliacao">
                    <input type="hidden" name="input.exam.id" id="input.exam.id" value="" />
                    <table border="0" class="show_hints">
                        <tr>
                            <td>
                                <p><s:text name="course.input.title"/>:</p>
                                <input type="text" id="input.exam.title" onfocus="hint(this,text(this))" onblur="clearHint(this, text(this));" onkeyup="text(this);" value="" />
                            </td>
                            <td width="50">
                                <span class="hint"><s:text name="show.tip.exam" /></span>
                                <span class="hint" name="hintVirgin">&nbsp;</span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p><s:text name="exam.input.navigable"/></p>
                                <s:radio list="#{'true':'Yes', 'false' :'No'}" value="%{'true'}" cssClass="input_radio" name="input.exam.navigable" id="input.exam.navigable" onfocus="hint(this,true)" onblur="clearHint(this, true);" theme="simple"/>
                            </td>
                            <td>
                                <span class="hint"><s:text name="show.tip.navegable" /></span>
                                <span class="hint">&nbsp;</span>
                            </td>
                        </tr>
                        <tr>
                            <td> 
                                <p><s:text name="exercise.input.weight"/></p>
                                <s:select theme="simple" id="input.exam.weight" name="input.exam.weight" value="1" list="#{'1':'1','2':'2','3':'3', '4':'4','5':'5','6':'6','7':'7','8':'8','9':9}" onfocus="hint(this,true)" onblur="clearHint(this, true);" />
                            </td>
                            <td>
                                <span class="hint"><s:text name="show.tip.weightExam"/></span>
                                <span class="hint">&nbsp;</span>
                            </td>
                        </tr>
                        <tr>
                            <td> 
                                <p><s:text name="exam.input.duration"/></p>
                                <s:select name="input.exam.duration" id="input.exam.duration" value="1" list="#{'1':'1','2':'2','3':'3', '4':'4','5':'5','6':'6'}" theme="simple" onfocus="hint(this,true)" onblur="clearHint(this, true);" />hs
                            </td>
                            <td>
                                <span class="hint"><s:text name="show.tip.time2" /></span>
                                <span class="hint">&nbsp;</span>
                            </td>
                        </tr>
                        <tr>
                            <td> 
                                <p><s:text name="input.exam.startdatetime"/></p>
                                <cal:jscalendar theme="simple" format="%m/%d/%Y %H:%M:00" name="input.exam.startdatetime" id="input.exam.startdatetime" showstime="true" onfocus="hintCalendar(this)" onchange="hint(this,true)" />
                            </td>
                            <td>
                                <span class="hint"><s:text name="show.tip.startDate" /></span>
                                <span class="hint" name="hintVirgin">&nbsp;</span>
                            </td>
                        </tr>
                        <tr>
                            <td> 
                                <p><s:text name="input.exam.enddatetime"/></p>
                                <cal:jscalendar theme="simple" format="%m/%d/%Y %H:%M:00" name="input.exam.enddatetime" id="input.exam.enddatetime" showstime="true" onfocus="hintCalendar(this)" onchange="hint(this,true)"/>
                            </td>
                            <td>
                                <span class="hint"><s:text name="show.tip.endDate" /></span>
                                <span class="hint" name="hintVirgin">&nbsp;</span>
                            </td>
                        </tr>
                    </table>
                    
                    <div class="actions-box2">
                        <div class="edit-box" id="exam.list.unitContent">  
                        </div>
                    </div>   
                    <br/>
                    <p class="navega-teste">
                        <a  class="cancel" title="Cancelar criação da avaliação" onclick="showManagerExams($('input.unitContent.id').value);">Cancel</a>
                        <!--a class="previous" title="Voltar para a etapa anterior">Previous</a-->
                        <a id="nextExam" onclick="submitExam($('input.exam.id').value);"  class="next" title="Ir para a próxima etapa">Next</a>
                        
                        <br class="clear" />
                    </p>
                    
                </form>
            </div>
            <%--input type="button" name="input.exam.submit" id="input.exam.submit" value="Submit" onclick="submitExam($('input.exam.id').value);"  /--%><br />        
        </div>
    </div>
</div>

<div id="showEntryQuestion" style="display: none;">
    <div id="col-2-home">
        <div class="container-courses">
            <div class="content-courses">
                <h1><div id="atividade.name"></div></h1>
                <br class="clear" />
                <div class="steps-box">
                    <div>
                        <div>
                            <span id="step"><s:text name="course.show.step" /></span>
                            <span id="" href="">1</span>
                            <span id="current">2</span>
                            <span class="next-step">3</span>
                        </div>
                    </div>
                    <div class="tip-step"><s:text name="show.tip.step2"/></div>
                </div>
                <h1><s:text name="question.show.addQuestion"/></h1>
                <br class="clear"/>
                
                <div id="form_question"  class="cadastro-avaliacao">
                    <s:form name="formUpload" id="formUpload" action="question!add.action" method="post" enctype="multipart/form-data" target="iframeQuestion">
                        <input type="hidden" name="input.question.id" id="input.question.id" value="" />
                        <s:hidden name="unit.id" id="hidden.unit.id" value=""/>
                        <s:hidden name="unitContent.id" id="hidden.unitContent.id" value=""/>
                        <s:hidden name="atividade" id="hidden.atividade" value=""/>
                        <s:hidden name="exercise.id" id="hidden.exercise.id" value=""/>
                        <s:hidden name="exam.id" id="hidden.exam.id" value=""/>
                        <table border="0" class="show_hints">
                            <tr>
                                <td>
                                    <p><s:text name="question.input.questionType"/>:</p>
                                    <s:select  id="question_question_type" theme="simple" name="question.type" onchange="selectTypeQuestion(this.value)" value ="selectedQuestionType" list="#{'1':'Subjective','2':'Objective'}" onfocus="hint(this,true)" onblur="clearHint(this, true);"/>
                                </td>
                                <td width="50">
                                    <span class="hint"><s:text name="show.tip.type" /></span>
                                    <span class="hint">&nbsp;</span>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <p><s:text name="question.input.weight"/>:</p>
                                    <s:select cssClass="lista-peso" name="questionWeight" value="1" list="#{'1':'1','2':'2','3':'3', '4':'4','5':'5','6':'6','7':'7','8':'8','9':'9'}" theme="simple" onfocus="hint(this,true)" onblur="clearHint(this, true);" />
                                </td>
                                <td width="50">
                                    <span class="hint"><s:text name="show.tip.weight" /></span>
                                    <span class="hint">&nbsp;</span>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <p><s:text name="question.input.required"/>:</p>
                                    <s:radio theme="simple" value="true" cssClass="input_radio" list="#{'true':'true', 'false' :'false'}" name="questionRequired" onfocus="hint(this,true)" onblur="clearHint(this, true);" />
                                </td>
                                <td width="50">
                                    <span class="hint"><s:text name="show.tip.required" /></span>
                                    <span class="hint">&nbsp;</span>
                                </td>
                            </tr>
                        </table>
                        
                        <div id="questionSubjective">
                            <table border="0" class="show_hints">
                                <tr>
                                    <td>
                                        <p><s:text name="question.input.text" />:</p>
                                        <s:textarea theme="simple" cols="27" id="textQuestion" name="questionText.text" onfocus="hint(this,true)" onblur="clearHint(this, true);" />
                                    </td>
                                    <td width="50">
                                        <span class="hint"><s:text name="show.tip.text" /></span>
                                        <span class="hint">&nbsp;</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <p><s:text name="question.input.audioQuestion" />:</p>
                                        <s:file id="id" name="fileQuestion" theme="simple" value="" onfocus="hint(this,true)" onblur="clearHint(this, true);" onchange="validateFile(this.value)"/>
                                    </td>
                                    <td width="50">
                                        <span class="hint"><s:text name="show.tip.exercise" /></span>
                                        <span class="hint">&nbsp;</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <p><s:text name="question.input.typeQuestion" />:</p>
                                        <s:textfield theme="simple" name="question.question" id="question.description" onfocus="hint(this,text(this))" onblur="clearHint(this, text(this));" onkeyup="text(this);"/>
                                    </td>
                                    <td width="50">
                                        <span class="hint"><s:text name="show.tip.question" /></span>
                                        <span class="hint" name="hintVirgin">&nbsp;</span>
                                    </td>
                                </tr>
                            </table>
                        </div>  
                        <table id="questionObjective" border="0" style="display:none" class="show_hints">
                            <tr>
                                <td colspan="3"><h2><s:text name="question.input.option" /></h2></td>
                            </tr>
                            <tr bgcolor="#DDD">
                                <td>
                                    <p style="margin:3px 3px 3px 3px;"><s:text name="question.input.correctOption"/></p>
                                </td>
                                <td>
                                    <p style="margin:3px 3px 3px 3px;"><s:text name="question.input.description"/></p>
                                </td>
                                <td bgcolor="#fff"></td>
                            </tr>
                            <tr>
                                <td><input id="question_radio0" class="input_radio" type="radio" value="0" checked name="radioQuestObj"/></td>
                                <td><s:textfield name="answerOption" theme="simple" id="" /></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td><input id="question_radio1" class="input_radio" type="radio" value="1" name="radioQuestObj"/></td>
                                <td><s:textfield name="answerOption" theme="simple"/></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><input type="button" onclick="insertOptions()" value="Inserir mais uma opção" /></td>
                            </tr>
                        </table>
                        
                        <div id="questionAuditive" style="display:none">
                            <h2><s:text name="question.input.selectTense" /></h2>
                            <p class="question-tense"><s:radio value="0" list="#{'0':'present simple<br/>', '1' :'present continuous<br/>', '2':'present perfect<br/>', '3':'past simple<br/>', '4':'past continuous<br/>', '5':'past perfect<br/>', '6':'future simple<br/>', '7' : 'future continuous<br/>','8' :'future perfect<br/>'}" name="radioTense"/></p>
                            <s:select  cssClass="lista-peso" label="question.input.chanceSentence" id="question.input.chanceSentence" name="question.input.chanceSentence" value="1" list="#{'1':'1','2':'2','3':'3', '4':'4'}"/>
                            <h2><s:text name="question.input.sentence" /></h2>
                            <%--form id="formFileQuestionAuditive" action="#" class="adiciona-audio">
                            <table>    
                                <tr>
                                    <td><s:text name="question.input.frase"  /></td>
                                    <td><s:text name="question.input.audiofile"/></td>
                                </tr>
                                <tr>
                                <td><s:textfield name="sentence" theme="simple" cssClass="frase" /></td>
                                <td><s:file name="upload" theme="simple" value=""/></td>
                                <tr>
                                    <td colspan="2"><input type="button" onclick="insertFile()" value="Inserir mais um arquivo"  class="acrescentar-arquivo"  /></td>
                                </tr>
                            </table>

                            </form--%>
                        </div>
                        <div id="questionExternal" style="display:none">
                            <table>    
                                
                                <tr>
                                    <td colspan="2"><h2><s:text name="question.input.urlBinary"  /></h2></td>    
                                </tr>
                                <tr>
                                    <td colspan="2"><s:textfield name="urlBinary" theme="simple" cssClass="frase" /> </td>
                                </tr>
                                
                                <tr>
                                    <td colspan="2"><h2><s:text name="question.input.urlResult"  /></h2></td>
                                </tr>
                                <tr>
                                    
                                    <td colspan="2"><s:textfield name="urlResult" theme="simple" cssClass="frase" /></td>
                                </tr>
                                
                                <tr>
                                    <td><h2><s:text name="question.input.binType"  /></h2></td>
                                    <td>
                                        <s:select cssClass="lista-peso" name="binType" value="1" list="#{'1':'Applet Java','2':'Arquivo Flash','3':'JavaScript'}" theme="simple" />
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2"><h2><s:text name="question.input.insertParams" /></h2>  </td>
                                </tr>
                                <tr>
                                    <td><h2><s:text name="question.input.paramKey"  /></h2></td>
                                    <td><h2><s:text name="question.input.paramValue"  /></h2></td>
                                </tr>
                                <%--
                            <tr>
                                <td><s:textfield name="paramKey" theme="simple" cssClass="frase" /></td>
                                <td><s:textfield name="paramValue" theme="simple" cssClass="frase" /> <a class="tooltip" href="#" onmouseover="return escape('For each parameter, enter key and value')"></a></td>
                            </tr>

                                --%>
                                <tr>
                                    <td colspan="2"><input type="button" onclick="insertParam()" value="<s:text name="question.input.insertParam"  />"  class="acrescentar-arquivo"  /></td>
                                </tr>
                            </table>
                            
                        </div>
                        <br/>          
                        <br/>
                        
                        <p class="navega-teste">
                            <!--a href="javascript:showUnitContent($('input.unitContent.id').value);" class="cancel" title="Cancelar criação da avaliação">Cancelar</a>
                        <a onclick="submitQuestion($('input.question.id').value);" class="create-question" title="Criar questão">Criar questão</a>
                            <a onclick="submitQuestion('final'+$('input.question.id').value);" class="finish" title="Finalizar">Criar e Finalizar</a-->
                            <input type="button" value="Criar Questão" cssClass="create-question" onclick="questionUpSubmit(false)"/>
                            <input type="button" value="Criar e Finalizar" cssClass="finish" onclick="questionUpSubmit(true)"/>
                            <br class="clear" />
                        </p>
                        
                        <div  id="showEntryQuestion.questions" class="questoes">
                            
                        </div> 
                    </s:form>
                </div>
                <iframe id="questionUp" name="iframeQuestion" src="" style="width:0;height:0;border:0px solid #fff;" >
                </iframe>
            </div>
        </div>
    </div>
</div>

<div id="box1" style="display:none; text-align:center;">
    <iframe id="waitingFrame" src="../waiting.jsp" style="width:150px;height:100px;border:0px solid #fff;" scrolling="no" >
    </iframe><br/>
</div>


<div id="showExercise" style="display: none;">
    <div id="col-2-home">
        <div class="container-courses">
            <div class="content-courses">
                <h1><span><s:text name="course.show.exercise" /></span></h1>
                <br class="clear" />
                <div id="exerciseExp"></div>
                <form action="#" class="form-edit">
                    <input type="button" value="Edit Exercise" onclick="showEditExercise($('exercise.id').value,$('unitContent.id').value);" />
                    <input type="hidden" id="exercise.id" name="exercise.id" value="" />
                </form>
                <br class="clear" />
                <div class="actions-box">
                    <h2><s:text name="course.show.title" /></h2><p id="exercise.title"></p>
                    <h2><s:text name="course.show.navigable" /></h2><p id="exercise.navigable"></p>
                    <h2><s:text name="course.show.createdAt" /></h2><p id="exercise.createdAt"></p>
                    <h2><s:text name="course.show.weight" /></h2><p id="exercise.weight"></p>
                    <h2><s:text name="course.show.chances" /></h2><p id="exercise.chances"></p>
                    <h2><s:text name="course.show.description" /></h2><p id="exercise.description"></p>
                    <h2><s:text name="course.show.question" /></h2><input type="button" value="add question" onclick="showEntryQuestion();" />
                    <div class="list-box" id="exercise.questions">
                    </div>
                    <h2><s:text name="course.show.requisites" /></h2>
                    <div class="list-box" id="exercise.requisites"></div>
                </div>
            </div>
        </div>
    </div>    
</div>

<div id="showExam" style="display: none;">
    <div id="col-2-home">
        <div class="container-courses">
            <div class="content-courses">
                <h1><span><s:text name="course.show.exam" /></span></h1>
                <br class="clear" />
                <div id="examExp"></div>
                <form action="#" class="form-edit">
                    <input type="button" value="Edit Exam" onclick="showEditExam($('exam.id').value,$('unitContent.id').value);" />
                    <input type="hidden" id="exam.id" name="exam.id" value="" />
                </form>
                <br class="clear" />
                <div class="actions-box">
                    <h2><s:text name="course.show.title" /></h2><p id="exam.title"></p>
                    <h2><s:text name="course.show.navigable" /></h2><p id="exam.navigable"></p>
                    <h2><s:text name="course.show.createdAt" /></h2><p id="exam.createdAt"></p>
                    <h2><s:text name="course.show.weight" /></h2><p id="exam.weight"></p>
                    <h2><s:text name="course.show.startDateTime" /></h2><p id="exam.startDatetime"></p>
                    <h2><s:text name="course.show.endDate" /></h2><p id="exam.endDatetime"></p>
                    <h2><s:text name="course.show.duration" /></h2><p id="exam.duration"></p>
                    <h2><s:text name="course.show.question" /></h2><input type="button" value="add question" onclick="showEntryQuestion();" />
                    <div class="list-box" id="exam.questions">
                    </div>
                    <h2><s:text name="course.show.requisites" /></h2>
                    <div class="list-box" id="exam.requisites"></div>
                </div>
            </div>
        </div>
    </div>    
</div>

<div id="showQuestion" style="display: none;">
    <div id="col-2-home">
        <div class="container-courses">
            <div class="content-courses">
                <h1><span id="show.atividade.name"></span></h1>
                <br class="clear" />
                <h2><s:text name="question.show.ShowQuestion"/></h2>
                <br class="clear" />
                <form action="#" class="form-edit">
                    <input type="hidden" id="typeUnitContent" value="" />
                    <input type="button" value="Back" onclick="showTypeUnitContent($('typeUnitContent').value);" /> 
                    <input type="button" value="Remove Question" onclick="removeQuestion($('show.question.id').value);" />
                    <input type="hidden" name="show.question.id" id="show.question.id" value="" />
                </form>
                <br class="clear" />
                <div class="actions-box">
                    <h2><s:text name="question.input.showType"/></h2>
                    <p id="show.question.type"></p>
                    <br/>
                    <h2><s:text name="question.input.weight"/></h2>
                    <p id="show.question.weight"></p>
                    <br/>
                    <h2><s:text name="question.input.required"/></h2>
                    <p id="show.question.required"></p>
                    <br/>
                    
                    <div id="showQuestionSubjective" style="display: none;">
                        
                        <div id="showQuestionText"></div>
                        <h2><s:text name="question.input.description" /></h2>
                        <p id="show.question.description"></p>
                    </div>
                    <table id="showQuestionObjective" style="display:none">
                        <tr>
                            <td colspan="2"><h2><s:text name="question.show.option" /></h2></td>
                        </tr>
                        <tr>
                            <td><s:text name="question.input.correctOption"/></td>
                            <td><s:text name="question.input.description"/></td>
                        </tr>
                    </table>
                    <div id="showQuestionAuditive" style="display:none">
                        <h2><s:text name="question.input.description" /></h2>
                        <p id="show.question.description"></p>
                        <h2><s:text name="question.input.sentence" /></h2>
                        <table>    
                            <tr>
                                <td><s:text name="question.input.frase"  /></td>
                                <td><s:text name="question.input.audiofile"/></td>
                            </tr>
                        </table>
                    </div>
                    
                </div>
            </div>
        </div>    
    </div>
</div>

<div id="showChallenge" style="display:none">
    <div id="col-2-home">
        <div class="container-grades">
            <div class="content-grades">
                <h1> <s:text name="show.challenge"/></h1>
                <br/>
                <br/>
                <br class="clear" />
                <h2><s:text name="show.challenge.search"/></h2>
                <s:form id="formSearchChallenge">
                    <s:text name="show.challenge.name"/>
                    <input type="text" id="challengeItensSearch.name" name="challengeItensSearch.name" />
                    <input type="button" value="Search" onclick="submitSearch()"/>
                </s:form>
                <br/>
                <br class="clear" />
                <h2><s:text name="show.challenge.add"/></h2>
                <s:form id="formChallenge" action="">
                    <input type="hidden" id="challengeItens.id" name="challengeItens.id" />
                    <input type="hidden" id="challengeItens.timestamp" name="challengeItens.timestamp" />
                    <table>
                        <tr>
                            <td><s:text name="show.challenge.name"/></td>
                            <td><input type="text" id="challengeItens.name" name="challengeItens.name" /></td>
                        </tr>
                        <tr>
                            <td><s:text name="show.challenge.xml"/></td>
                            <td><textarea id="challengeItens.xml" name="challengeItens.xml" label="show.challenge.xml" cols="70" rows="25"></textarea></td>
                        </tr>    
                        <tr>
                            <td><s:text name="show.challenge.dependency"/></td>
                            <td><select id="challengeItens.dependency" ></select></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input id="submitChall" type="button" value="Add" onclick="submitChallenge()"/>
                                <div id="removeCha" style="display:none"><input id="removeChall" type="button" value="Remove" onclick="removeChallenge()"/></div>
                            </td>
                        </tr>
                    </table>
                </s:form>
            </div>
        </div>
    </div>
</div>

<!-- end col-2-home -->
<br class="clear" />
<script  language="JavaScript" type="text/javascript" src="../js/tooltip/wz_tooltip.js"></script>
<!-- end content -->
<br class="clear" />
mp" name="challengeItens.timestamp" />
                    <table>
                        <tr>
                            <td><s:text name="show.challenge.name"/></td>
                            <td><input type="text" id="challengeItens.name" name="challengeItens.name" /></td>
                        </tr>
                        <tr>
                            <td><s:text name="show.challenge.xml"/></td>
                            <td><textarea id="challengeItens.xml" name="challengeItens.xml" label="show.challenge.xml" cols="70" rows="25"></textarea></td>
                        </tr>    
                        <tr>
                            <td><s:text name="show.challenge.dependency"/></td>
                            <td><select id="challengeItens.dependency" ></select></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input id="submitChall" type="button" value="Add" onclick="submitChallenge()"/>
                                <div id="removeCha" style="display:none"><input id="removeChall" type="button" value="Remove" onclick="removeChallenge()"/></div>
                            </td>
                        </tr>
                    </table>
                </s:form>
            </div>
        </div>
    </div>
</div>

<!-- end col-2-home -->
<br class="clear" />
<script  language="JavaScript" type="text/javascript" src="../js/tooltip/wz_tooltip.js"></script>
<!-- end content -->
<br class="clear" />
