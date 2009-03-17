<%-- 
    Document   : show grade
    Created on : Sep 15, 2008, 2:07:13 PM
    Author     : marcus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="cal" uri="/jscalendar" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<head>
    <link href="../css/accordion_grade.css"  rel="stylesheet" type="text/css" />
    <link href="../css/lightbox.css"  rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../fckeditor/fckeditor.js"></script>
    <script type="text/javascript" src="../js/admin/tools.js"></script>
    <script type="text/javascript" src="../js/admin/grade.js"></script>
    <script type="text/javascript" src="../js/lightbox.js"></script>
    
    <script type="text/javascript">
        <!--
        var message = '<s:property value="message" />';
        if (message != null && message != '') {
            //alert(message);
        }
        
        function showImage(id) {
            if($(id).src.search("images/foto_profile.jpg") != -1){
                $(id).src = $(id).alt;
            }
        }
        -->
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
        <li class="current"><s:text name="admin.grade" /></li>
    </ul>
</div>

<div id="col-1-home">
    <h2><s:text name="grade.pageTitle" /></h2>
    <s:if test="(courseList != null && courseList.size() > 0)">
        <div class="create_new_grade">
            <form action="">
                <input class="btn-new-one" type="button" value="<s:text name="grade.show.add" />" onclick="showEntryGrade(null);" />                
            </form>
        </div>
    </s:if>
    
    <div id="vertical_container2" >
        <s:if test="(courseList == null || courseList.size() == 0)">
            <h3 class="no_grades"><s:text name="admin.nocourses" /></h3>
        </s:if>
        <s:else>
            <s:iterator value="courseList" status="cstat">
                <s:if test="name.length() > 22">
                    <h3 class="accordion_toggle_grade2" title="<s:property value="name" />"><s:property value="name.substring(0, 22)" />...</h3>
                </s:if>
                <s:else>
                    <h3 class="accordion_toggle_grade2"><s:property value="name" /></h3>
                </s:else>
                <div class="accordion_content_grade2" id="<s:property value="id" />">
                    
                    <div id="vertical_nested_container">
                        <s:if test="(grades == null || grades.size() == 0)">
                            <h4 class="no_grades"><s:text name="admin.nogrades" /></h4>
                        </s:if>
                        <s:else>
                            <s:iterator value="grades" status="gstat">
                                <h4 class="vertical_accordion_toggle"><s:property value="name" /> - <s:property value="enrollments.size()" /> student(s) <img id='imageStatus_<s:property value="id" />' title='<s:property value="statusToText(status)" />' alt='<s:property value="statusToText(status)" />' src='../images/icon/status-grade-<s:property value="status" />.gif' /></h4>
                                <div class="vertical_accordion_content" id="<s:property value="id" />">
                                    <div id="vertical_nested_container2">
                                        <h5 class="vertical_accordion_toggle2" id="students"><s:text name="grade.show.students" /></h5>                        
                                        <div class="vertical_accordion_content2" id="<s:property value="id" />">
                                            
                                            <s:if test="(enrollments != null && enrollments.size() > 0)">                                   
                                                <input type="button" name="CheckAllStudents" value="Marcar Todos" class="btn-check-all"
                                                       onClick="checkAllFieldsByName('studentsCheck', '<s:property value="id" />','students')">
                                                <input type="button" name="UnCheckAllStudents" value="Desmarcar Todos" class="btn-uncheck-all"
                                                       onClick="unCheckAllFieldsByName('studentsCheck','studentData')">
                                            </s:if>
                                            
                                            <ul class="list-students">
                                                <s:iterator value="enrollments" status="stat">      
                                                    <li title="<s:property value="systemUser.username" />" id="li.student.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />" onMouseOver="mouseOverPerson('student.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />');" onMouseOut="mouseOutPerson('student.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />');"><img src="../images/icon/icon-person.gif" /><br /><s:if test="systemUser.username.length() > 8"><s:property value="systemUser.username.substring(0,8)+ \"...\"" /></s:if><s:else><s:property value="systemUser.username" /></s:else><br /><input type="checkbox" value="<s:property value="systemUser.id" />" id="student.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />" name="studentsCheck" onclick="updateStudents(this,'student.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />');" /></li>                         
                                                </s:iterator>
                                                <br class="clear" />
                                            </ul>
                                            <!-- Início : Divs com características do aluno -->
                                        <s:iterator value="enrollments" status="stat">                             
                                                <div id="div.student.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />" style="display: none;">
                                                    <div id="div.inner.student.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />" class="member-info" style="display: none;">
                                                        <img id="img_div.student.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />" class="picture" src="../images/foto_profile.jpg" alt="../RenderServletProfile?id=<s:property value="systemUser.id" />" width="80" height="80" />
                                                        <div>
                                                            <div>
                                                                <p><s:text name="student.input.username"/> <span><s:property value="systemUser.username" /></span></p>
                                                                <p><s:text name="student.input.email"/> <span><s:property value="systemUser.email" /></span></p>
                                                                <p><s:text name="student.input.createdAt"/> <span><s:property value="systemUser.createdAt" /></span></p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </s:iterator>
                                            <s:iterator value="enrollments" status="stat">                             
                                                <div id="div.multiple.student.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />" style="display: none;">
                                                    <div id="div.multiple.inner.student.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />" class="member-info" style="display: none; float: left;">
                                                        <img id="img_div.multiple.student.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />" class="picture" src="../images/foto_profile.jpg" alt="../RenderServletProfile?id=<s:property value="systemUser.id" />" width="80" height="80" /><br /><span><s:property value="systemUser.username" /></span>
                                                    </div>
                                                </div>
                                            </s:iterator>
                                            <br />
                                            <!-- Fim : Divs com caracterísitcas do aluno -->
                                    
                                        </div>
                                        <h5 class="vertical_accordion_toggle2" id="professors"><s:text name="grade.show.professors" /></h5>                        
                                        <div class="vertical_accordion_content2" id="<s:property value="id" />">
                                            <s:if test="(professors != null && professors.size() > 0)">                                   
                                                <input type="button" name="CheckAllProfessors" value="Marcar Todos" class="btn-check-all"
                                                       onClick="checkAllFieldsByName('professorsCheck', '<s:property value="id" />','professors')">
                                                <input type="button" name="UnCheckAllProfessors" value="Desmarcar Todos" class="btn-uncheck-all"
                                                       onClick="unCheckAllFieldsByName('professorsCheck','professorData')">
                                            </s:if>
                                            
                                            <ul class="list-students">
                                                <s:iterator value="professors" status="stat">                               
                                                    <li title="<s:property value="username" />" id="li.professor.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />" onMouseOver="mouseOverPerson('professor.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />');" onMouseOut="mouseOutPerson('professor.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />');"><img src="../images/icon/icon-person.gif" /><br /><s:if test="username.length() > 8"><s:property value="username.substring(0,8)+ \"...\"" /></s:if><s:else><s:property value="username" /></s:else><br /><input type="checkbox" value="<s:property value="id" />" id="professor.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />" name="professorsCheck" onclick="updateProfessor(this,'professor.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />');" /></li>                         
                                                </s:iterator>
                                                <br class="clear" />
                                            </ul>
                                            <!-- Início : Divs com características do professor -->
                                        <s:iterator value="professors" status="stat">                             
                                                <div id="div.professor.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />" style="display: none;">
                                                    <div id="div.inner.professor.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />" class="member-info" style="display: none;">
                                                        <img id="img_div.professor.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />" class="picture" src="../images/foto_profile.jpg" alt="../RenderServletProfile?id=<s:property value="id" />" width="80" height="80" />
                                                        <div>
                                                            <div>
                                                                <p><s:text name="professor.input.username"/>: <span><s:property value="username" /></span></p>
                                                                <p><s:text name="professor.input.email"/>: <span><s:property value="email" /></span></p>
                                                                <p><s:text name="professor.input.createdAt"/>: <span><s:property value="createdAt" /></span></p>
                                                            </div>
                                                        </div>
                                                        <br class="clear" />
                                                        
                                                    </div>
                                                </div>
                                            </s:iterator>
                                            <s:iterator value="professors" status="stat">                             
                                                <div id="div.multiple.professor.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />" style="display: none;">
                                                    <div id="div.multiple.inner.professor.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />" class="member-info" style="display: none; float: left;">
                                                        <img id="img_div.multiple.professor.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />" class="picture" src="../images/foto_profile.jpg" alt="../RenderServletProfile?id=<s:property value="id" />" width="80" height="80" /><br /><span><s:property value="username" /></span>
                                                    </div>
                                                </div>
                                            </s:iterator>
                                            <br />
                                            
                                            <!-- Fim : Divs com caracterísitcas do professor -->
                                    
                                        </div>
                                        <h5 class="vertical_accordion_toggle2" id="tutors"><s:text name="grade.show.tutors" /></h5>                        
                                        <div class="vertical_accordion_content2" id="<s:property value="id" />">
                                            
                                            <s:if test="(tutors != null && tutors.size() > 0)">                                   
                                                <input type="button" name="CheckAllTutors" value="Marcar Todos" class="btn-check-all"
                                                       onClick="checkAllFieldsByName('tutorsCheck', '<s:property value="id" />','tutors')">
                                                <input type="button" name="UnCheckAllTutors" value="Desmarcar Todos" class="btn-uncheck-all"
                                                       onClick="unCheckAllFieldsByName('tutorsCheck','tutorData')">
                                            </s:if>
                                            
                                            <ul class="list-students">
                                                <s:iterator value="tutors" status="stat">                               
                                                    <li title="<s:property value="username" />" id="li.tutor.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />" onMouseOver="mouseOverPerson('tutor.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />');" onMouseOut="mouseOutPerson('tutor.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />');"><img src="../images/icon/icon-person.gif" /><br /><s:if test="username.length() > 8"><s:property value="username.substring(0,8)+ \"...\"" /></s:if><s:else><s:property value="username" /></s:else><br /><input type="checkbox" value="<s:property value="id" />" name="tutorsCheck" id="tutor.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />" onclick="updateTutor(this,'tutor.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />');" /></li>
                                                </s:iterator>
                                                <br class="clear" />
                                            </ul>
                                            <!-- Início : Divs com características do tutor -->
                                        <s:iterator value="tutors" status="stat">                             
                                                <div id="div.tutor.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />" style="display: none;">
                                                    <div id="div.inner.tutor.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />" class="member-info" style="display: none;">
                                                        <img id="img_div.tutor.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />" class="picture" src="../images/foto_profile.jpg" alt="../RenderServletProfile?id=<s:property value="id" />" width="80" height="80" />
                                                        <div>
                                                            <div>
                                                                <p><s:text name="tutor.input.username"/>: <span><s:property value="username" /></span></p>
                                                                <p><s:text name="tutor.input.email"/>: <span><s:property value="email" /></span></p>
                                                                <p><s:text name="tutor.input.createdAt"/>: <span><s:property value="createdAt" /></span></p>
                                                            </div>
                                                        </div>
                                                        <br class="clear" />
                                                        
                                                    </div>
                                                </div>
                                            </s:iterator>
                                            <s:iterator value="tutors" status="stat">                             
                                                <div id="div.multiple.tutor.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />" style="display: none;">
                                                    <div id="div.multiple.inner.tutor.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />" class="member-info" style="display: none; float: left;">
                                                        <img id="img_div.multiple.tutor.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />" class="picture" src="../images/foto_profile.jpg" alt="../RenderServletProfile?id=<s:property value="id" />" width="80" height="80" /><br /><span><s:property value="username" /></span>
                                                    </div>
                                                </div>
                                            </s:iterator>
                                            <br />
                                            
                                            <!-- Fim : Divs com caracterísitcas do tutor -->
                                        </div>
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        <h5 class="vertical_accordion_toggle2" id="forums"><s:text name="grade.show.forums" /></h5>                        
                                        <div class="vertical_accordion_content2" id="<s:property value="id" />">
                                            
                                            <div id="vertical_nested_container3">
                                            <s:iterator value="forums" status="stat">                               
                                                    <s:if test="title.length() > 22">
                                                        <h6 class="vertical_accordion_toggle3" title="<s:property value="title" />"><span onclick="showForumId('<s:property value="grade.id" />', '<s:property value="grade.courseId" />', '<s:property value="id" />');"><s:property value="title.substring(0, 22)" />...</span></h6>
                                                    </s:if>
                                                    <s:else>
                                                        <h6 class="vertical_accordion_toggle3"><span onclick="showForumId('<s:property value="grade.id" />', '<s:property value="grade.courseId" />', '<s:property value="id" />');"><s:property value="title" /></span></h6>
                                                    </s:else>                                    
                                                    <div class="vertical_accordion_content3" id="<s:property value="id" />">
                                                    <div id="vertical_nested_container4">
                                                    <s:iterator value="topicCollection.toArray()" status="stat">                               
                                                    <h6 class="vertical_accordion_toggle4" id="topics" ><s:property value="title" /></h6>
                                                          <div class="vertical_accordion_content4" id="<s:property value="id" />">
                                                          </div>
                                                    </s:iterator>
                                                    </div>
                                                    </div>
                                                    
                                            </s:iterator>
                                            </div>
                                            <br class="clear" />
    
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
<!-- end col-1-home -->

<div id="showEntryGrade" style="display: none;">
    <div id="col-2-home">
        <div class="container-grades">
            <div class="content-grades">
                <input type="hidden" name="input.grade.id" id="input.grade.id" value="" />
                <h1><s:text name="grade.list.add" /></h1>
                <br /> <br />
                <label><s:text name="grade.input.course" /></label><br />
                <select name="input.grade.course" id="input.grade.course">
                    <option value=""><s:text name="grade.select" /></option>                    
                    <s:iterator value="courseList">
                        <option value="<s:property value="id" />"><s:property value="name" /></option>
                    </s:iterator>
                </select><a href="#" onmouseover="return escape('Selecione o curso da turma')">?</a>
                <br />
                
                <label><s:text name="grade.input.name" /></label><br />
                <input type="text" name="input.grade.name" id="input.grade.name" value="" maxlength="20" size="25"/><a href="#" onmouseover="return escape('Entre com o nome da turma')">?</a><br />
                
                
                <label><s:text name="grade.input.period" /></label><br />
                <input type="text" name="input.grade.period" id="input.grade.period" value="" maxlength="6" size="11" /><a href="#" onmouseover="return escape('Entre com o período do turma (Exemplo: 2008.1 para turma que iniciou no primeiro semestre de 2008')">?</a><br />
                
                
                <label><s:text name="grade.input.maxstudents" /></label><br />
                <input type="text" name="input.grade.maxstudents" id="input.grade.maxstudents" value="" maxlength="4" size="7" /><a href="#" onmouseover="return escape('Entre com o número máximo de estudantes nesta turma')">?</a><br />
                
                <label><s:text name="grade.input.coordinator" /></label><br />
                <select name="input.grade.coordinator" id="input.grade.coordinator">
                    <option value=""><s:text name="grade.select" /></option>
                    <s:iterator value="coordinatorList">
                        <option value="<s:property value="id" />"><s:property value="username" /></option>
                    </s:iterator>
                </select><a href="#" onmouseover="return escape('Selecione o coordenador da turma')">?</a>
                <br />
                
                <label><s:text name="grade.input.maxduration" /></label><br />
                <input type="text" name="input.grade.maxduration" id="input.grade.maxduration" value="" maxlength="4" size="7" /><a href="#" onmouseover="return escape('Entre com o tempo máximo (em dias) de duração da turma')">?</a><br />
                
                <label><s:text name="grade.input.requiresNavigation" /></label><br />
                <s:radio list="#{true:'Yes', false :'No'}" value="true" id="input.grade.requires" name="input.grade.requires" /><a href="#" onmouseover="return escape('Matrícula sujeita a aprovação')">?</a>
                <br />
                
                <label><s:text name="grade.input.status" /></label><br />
                <select id="input.grade.status">
                    <option value="0">Inactive</option>
                    <option value="1">Period of Enrollment</option>
                    <option value="3">In Progress</option>
                    <option value="2">Registration Finished</option>
                </select><a href="#" onmouseover="return escape('Escolha o estatus da Turma')">?</a>
                <br />
                <label><s:text name="grade.input.startdatetime" /></label><br />
                <!--input type="text" name="input.grade.startdatetime" id="input.grade.startdatetime" value="" /><img id="startdatetime" src="../images/icon/icon-agenda.gif" /-->
                <cal:jscalendar format="%d/%m/%Y" name="input.grade.startdatetime" id="input.grade.startdatetime"/><a href="#" onmouseover="return escape('Entre com a data de início da turma')">?</a><br />
                
                <label><s:text name="grade.input.enddatetime" /></label><br />
                <cal:jscalendar format="%d/%m/%Y" name="input.grade.enddatetime" id="input.grade.enddatetime"/><a href="#" onmouseover="return escape('Entre com a data de término da turma')">?</a><br />
                
                <input class="btn-save-courses" type="button" name="input.grade.submit" id="input.grade.submit" value="<s:text name="grade.submit" />" onclick="submitGrade(document.getElementById('input.grade.id').value)" /><br />
            </div>
        </div>
    </div>
</div>

<div id="box1" style="display:none; text-align:center;">
    <iframe id="waitingFrame" src="../waiting.jsp" style="width:150px;height:100px;border:0px solid #fff;" scrolling="no" >
    </iframe><br/>
</div>


<div id="showEntryStudent" style="display: none;">
    <div id="col-2-home">
        <div class="container-grades">
            <div class="content-grades">
                <input type="hidden" name="input.student.grade.id" id="input.student.grade.id" value="" />
                <h1><s:text name="student.list.add" /></h1>
                <br /> <br />
                <label><s:text name="student.input.course" /></label><br />
                <select name="input.student.grade.course" id="input.student.grade.course">
                    <option value=""><s:text name="grade.select" /></option>                    
                    <s:iterator value="courseList">
                        <option value="<s:property value="id" />"><s:property value="name" /></option>
                    </s:iterator>
                </select>
                <br />
                
                <label><s:text name="student.input.student" /></label><br />
                <select name="input.student.grade.student" id="input.student.grade.student">
                    <option value=""><s:text name="grade.select" /></option>
                    <s:iterator value="studentList">
                        <option value="<s:property value="id" />"><s:property value="username" /></option>
                    </s:iterator>
                </select>
                <br />
                
                <input class="bbtn-new-one" type="button" name="input.student.submit" id="input.student.submit" value="<s:text name="grade.submit" />" onclick="submitStudent(document.getElementById('input.student.grade.id').value, document.getElementById('input.student.grade.student').value);" /><br />
            </div>
        </div>
    </div>
</div>

<div id="showEntryProfessor" style="display: none;">
    <div id="col-2-home">
        <div class="container-grades">
            <div class="content-grades">
                <input type="hidden" name="input.professor.grade.id" id="input.professor.grade.id" value="" />
                <h1><s:text name="professor.list.add" /></h1>
                <br /> <br />
                <label><s:text name="professor.input.course" /></label><br />
                <select name="input.professor.grade.course" id="input.professor.grade.course">
                    <option value=""><s:text name="grade.select" /></option>                    
                    <s:iterator value="courseList">
                        <option value="<s:property value="id" />"><s:property value="name" /></option>
                    </s:iterator>
                </select>
                <br />
                
                <label><s:text name="professor.input.professor" /></label><br />
                <select name="input.professor.grade.professor" id="input.professor.grade.professor">
                    <option value=""><s:text name="grade.select" /></option>
                    <s:iterator value="professorList">
                        <option value="<s:property value="id" />"><s:property value="username" /></option>
                    </s:iterator>
                </select>
                <br />
                
                <input class="bbtn-new-one" type="button" name="input.professor.submit" id="input.professor.submit" value="<s:text name="grade.submit" />" onclick="submitProfessor(document.getElementById('input.professor.grade.id').value, document.getElementById('input.professor.grade.professor').value);" /><br />
            </div>
        </div>
    </div>
</div>

<div id="showEntryTutor" style="display: none;">
    <div id="col-2-home">
        <div class="container-grades">
            <div class="content-grades">
                <input type="hidden" name="input.tutor.grade.id" id="input.tutor.grade.id" value="" />
                <h1><s:text name="tutor.list.add" /></h1>
                <br /> <br />
                <label><s:text name="tutor.input.course" /></label><br />
                <select name="input.tutor.grade.course" id="input.tutor.grade.course">
                    <option value=""><s:text name="grade.select" /></option>                    
                    <s:iterator value="courseList">
                        <option value="<s:property value="id" />"><s:property value="name" /></option>
                    </s:iterator>
                </select>
                <br />
                
                <label><s:text name="grade.input.tutor" /></label><br />
                <select name="input.tutor.grade.tutor" id="input.tutor.grade.tutor">
                    <option value=""><s:text name="grade.select" /></option>
                    <s:iterator value="tutorList">
                        <option value="<s:property value="id" />"><s:property value="username" /></option>
                    </s:iterator>
                </select>
                <br />
                
                <input class="bbtn-new-one" type="button" name="input.tutor.submit" id="input.tutor.submit" value="<s:text name="grade.submit" />" onclick="submitTutor(document.getElementById('input.tutor.grade.id').value, document.getElementById('input.tutor.grade.tutor').value);" /><br />
            </div>
        </div>
    </div>
</div>

<div id="showEntryForum" style="display: none;">
    <div id="col-2-home">
        <div class="container-grades">
            <div class="content-grades">
                
                <h1><s:text name="forum.input.pageTitle"/></h1>
                <input type="hidden" id="forum.grade.id" value="" />
                <br/><br/>
                <s:text name="forum.input.title"/><br/>
                <input type="text" id="forum.title"/><br/>
                <s:text name="forum.input.description"/><br/>
                <textarea  name="forum.description" id="forum.description" cols="50"></textarea><br/><br/>
                
                <input type="checkbox" id="forum.studentCreateTopic" name="forum.studentCreateTopic" value="true" /> <s:text name="forum.input.createTopic"/><br/>
                <input type="checkbox" id="forum.studentUploadPost" name="forum.studentUploadPost" value="true" /> <s:text name="forum.input.uploadPost"/><br/>
                <input type="checkbox" id="forum.studentUploadRepository" name="forum.studentUploadRepository" value="true" /> <s:text name="forum.input.uploadRepository"/><br/>
                <input type="checkbox" id="forum.studentLinkPost" name="forum.studentLinkPost" value="true" /> <s:text name="forum.input.linkPost"/><br/>
                
                <input type="checkbox" id="forum.public1" name="forum.public1" value="true" /> <s:text name="forum.input.public"/><br/>
                
                <br/>
                <input type="button" value="<s:text name="forum.input.create"/>" onclick="submitForum($('forum.grade.id').value);">
                
            </div>
        </div>
    </div>
</div>

<div id="showEntryTopic" style="display: none;">
    <div id="col-2-home">
        <div class="container-grades">
            <div class="content-grades">
                
                <h1><s:text name="topic.input.pageTitle"/></h1>
                <input type="hidden" id="topic.forum.id" value="" />
                <br/><br/>
                <s:text name="topic.input.title"/><br/>
                <input type="text" id="topic.title"/><br/>
                <s:text name="topic.input.description"/><br/>
                <textarea  name="topic.description" id="topic.description" cols="50"></textarea><br/><br/>
                <br/>
                <input type="button" value="<s:text name="topic.input.create"/>" onclick="submitTopic($('topic.forum.id').value);">
                
            </div>
        </div>
    </div>
</div>

<div id="showCourse" style="display: none;">
    <div id="col-2-home">
        <div class="container-grades">
            <div class="content-grades">
                <h1><span id="course.name"></span></h1>
                <br class="clear" />
                <form action="" class="form-edit">
                    <input type="hidden" id="course.id" name="course.id" value="" />
                </form>
                <br class="clear" />
                
                <h2><span><s:text name="grade.show.description" />:</span> </h2> <p><span id="course.description"></span><br /> </p>
                <br/>
                <img id="course.image" src="" width="100" height="100" />
                <br/>
                <div>
                    <p><s:text name="grade.show.gradesCount" />: <span id="course.grade.count"></span></p>
                    <p><s:text name="grade.show.coordinatorsCount" />: <span id="course.coordinator.count"></span></p>
                    <p><s:text name="grade.show.professorsCount" />: <span id="course.professor.count"></span></p>
                    <p><s:text name="grade.show.tutorsCount" />: <span id="course.tutor.count"></span></p>
                    <p><s:text name="grade.show.studentsCount" />: <span id="course.student.count"></span></p>
                    <p><s:text name="grade.show.graduatedStudentsCount" />: <span id="course.graduated.count"></span></p>
                </div>
                <div class="actions-box">
                    <h2><s:text name="course.show.actions" />:</h2>
                    <div class="edit-tools">
                        <ul>
                            <li><a class="icon-new" href="javascript:showEntryGrade(document.getElementById('course.id').value);"><s:text name= "course.new.grade" /></a></li>
                            <li><a class="icon-edit" href="javascript:showEditGrade();"><s:text name= "course.edit.grade" /></a></li>
                            <li><a class="icon-delete" href="javascript:deleteGrade();"><s:text name= "course.delete.grade" /></a></li>
                            <li><a class="icon-newsFlash" href="javascript:showSendNewsFlash('course.newsflash');"><s:text name= "course.send.newsflash" /></a></li>
                            <li><a class="icon-message" href="javascript:showSendMessage('course.message');"><s:text name= "course.send.message" /></a></li>
                            <br class="clear" />
                        </ul>
                    </div>
                    <div class="edit-box" id="course.grades">
                    </div>
                    <div id="course.message" style="display:none;" class="course-message">
                        <h4><s:text name ="student.send.message" /></h4><br />
                        <s:text name="message.title" /><br /> <input type="text" name="input.course.message.title" id="input.course.message.title" size="55" maxlength="50" /><br />
                        <s:text name="message.type"/><br /> <textarea cols="62" rows="4" onKeyDown="textCounter(this,$('input.course.message.description.len'),250);" onKeyUp="textCounter(this,$('input.course.message.description.len'),250);" name="input.course.message.description" id="input.course.message.description"></textarea><br />
                        <s:text name="message.remaining" /><input readonly type=text id="input.course.message.description.len" name="input.course.message.description.len" size=3 maxlength=3 value="250"/><br/>
                        <input type="button" value="Submit" onclick="sendMessageGrade($('input.course.message.title'), $('input.course.message.description'));" />
                    </div>
                    <div id="course.newsflash" style="display:none;"class="course-message">
                        <h4><s:text name="student.send.newsflash"/></h4><br />
                        <s:text name="message.type" /><br /> <textarea cols="62" rows="4" onKeyDown="textCounter(this,$('input.course.newsflash.message.len'),250);" onKeyUp="textCounter(this,$('input.course.newsflash.message.len'),250);" name="input.course.newsflash.message" id="input.course.newsflash.message"></textarea><br />
                        <s:text name="message.remaining" /><input readonly type=text id="input.course.newsflash.message.len" name="input.course.newsflash.message.len" size=3 maxlength=3 value="250"/><br/>
                        <input type="button" value="Submit" onclick="sendNewsFlashGrade($('input.course.newsflash.message'));" />
                    </div>
                </div>
                <!-- end actions-box -->
            </div>
            <!-- End content-grades -->
        </div>
        
    </div>    
</div>

<div id="showGrade" style="display: none;">
    <div id="col-2-home">
        <div class="container-grades">
            <div class="content-grades">
                <h1><span id="grade.course.name"></span></h1>
                <h2 style="float:left; width:400px; padding-top:45px;"><span id="grade.name"></span></h2>
                <br class="clear" />
                <form action="" class="form-edit">
                    <input class="icon-edit" type="button" value="<s:text name="grade.show.editGrade" />" onclick="showEditGradeById(document.getElementById('grade.id').value);" />
                    <input class="icon-delete" type="button" value="<s:text name="grade.show.removeGrade"/>" onclick="deleteGradeId(document.getElementById('grade.id').value);" />
                    <input type="hidden" id="grade.id" name="grade.id" value="" />
                    <input type="hidden" id="grade.course.id" name="grade.course.id" value="" />
                </form>
                <br class="clear" />
                
                <h2><s:text name="grade.input.course.description" />:</h2> <p><span id="grade.course.description"></span></p>
                
                <div>
                    <p><s:text name="grade.input.coordinatorsCount" />: <span id="grade.coordinator.count"></span></p>
                    <p><s:text name="grade.input.professorsCount" />: <span id="grade.professor.count"></span></p>
                    <p><s:text name="grade.input.tutorsCount" />: <span id="grade.tutor.count"></span></p>
                    <p><s:text name="grade.input.studentsCount" />: <span id="grade.student.count"></span></p>
                    <p><s:text name="grade.input.graduatedStudentsCount" />: <span id="grade.graduated.count"></span></p>
                </div>
                <br />
                <br />
                <ul class="list-status-grade" id="ulStatus">
                    <li onclick="checkStatusGrade(this)">
                        <img src="../images/icon/status-grade-big-0.gif"/>
                        <br/>
                        <s:text name="grade.status.inactive" />
                        <br/>
                        <input type="checkbox" id="grade.status0" value="0" onfocus="checkStatusSelect(this.value)" />
                    </li>
                    <li onclick="checkStatusGrade(this)">
                        <img src="../images/icon/status-grade-big-1.gif"/>
                        <br/>
                        <s:text name="grade.status.periodOfEnrollment" />
                        <br/>
                        <input type="checkbox" id="grade.status1" value="1" onfocus="checkStatusSelect(this.value)" />
                    </li>
                    <li onclick="checkStatusGrade(this)">
                        <img src="../images/icon/status-grade-big-3.gif"/>
                        <br/>
                        <s:text name="grade.status.inProgress"/>
                        <br/>
                        <input type="checkbox" id="grade.status3" value="3" onfocus="checkStatusSelect(this.value)" />
                    </li>
                    <li onclick="checkStatusGrade(this)">
                        <img src="../images/icon/status-grade-big-2.gif"/>
                        <br/>
                        <s:text name="grade.status.finished" />
                        <br/>
                        <input type="checkbox" id="grade.status2" value="2" onfocus="checkStatusSelect(this.value)" />
                    </li>
                    <br class="clear"/>
                </ul>
                
                <div class="actions-box">
                    <h2><s:text name="grade.show.actions" /></h2>
                    <div class="edit-tools">
                        <ul>
                            <li><a class="icon-new" href="javascript:showEntryStudent($('grade.id').value, $('grade.course.id').value);"><s:text name= "grade.students.new" /></a></li>
                            <li><a class="icon-new" href="javascript:showEntryProfessor($('grade.id').value, $('grade.course.id').value);"><s:text name= "grade.professors.new" /></a></li>
                            <li><a class="icon-new" href="javascript:showEntryTutor($('grade.id').value, $('grade.course.id').value);"><s:text name= "grade.tutors.new" /></a></li>
                            <li><a  class ="icon-new" href="javascript:showEntryForum($('grade.id').value)" class="lightwindow page-options" params="lightwindow_type=external,lightwindow_width=1024"><s:text name="forum.input.list" /></a></li>
                            <li><a class="icon-newsFlash" href="javascript:showSendNewsFlash('grade.newsflash');"><s:text name= "student.send.newsflash" /></a></li>
                            <li><a class="icon-message" href="javascript:showSendMessage('grade.message');"><s:text name= "student.send.message" /></a></li>
                            
                            <br class="clear" />
                        </ul>
                    </div>
                    <div id="grade.message" style="display:none;"class="course-message">
                        <h4><s:text name ="student.send.message" /></h4><br />
                        <s:text name="message.title" /><br /><input type="text" name="input.grade.message.title" id="input.grade.message.title" size="55" maxlength="50" /><br />
                        <s:text name="message.type"/><br /><textarea cols="62" rows="4" onKeyDown="textCounter(this,$('input.grade.message.description.len'),250);" onKeyUp="textCounter(this,$('input.grade.message.description.len'),250);" name="input.grade.message.description" id="input.grade.message.description"></textarea><br />
                        <s:text name="message.remaining" /><input readonly type=text id="input.grade.message.description.len" name="input.grade.message.description.len" size=3 maxlength=3 value="250"/><br/>
                        <input type="button" value="Submit" onclick="sendMessageGradeId($('grade.id'), $('input.grade.message.title'), $('input.grade.message.description'));" />
                    </div>
                    <div id="grade.newsflash" style="display:none;"class="course-message">
                        <h4><s:text name="student.send.newsflash"/></h4><br />
                        <s:text name="message.type" /><br /><textarea cols="62" rows="4" onKeyDown="textCounter(this,$('input.grade.newsflash.message.len'),250);" onKeyUp="textCounter(this,$('input.grade.newsflash.message.len'),250);" name="input.grade.newsflash.message" id="input.grade.newsflash.message"></textarea><br />
                        <s:text name="message.remaining" /><input readonly type=text id="input.grade.newsflash.message.len" name="input.grade.newsflash.message.len" size=3 maxlength=3 value="250"/><br/>
                        <input type="button" value="Submit" onclick="sendNewsFlashGradeId($('grade.id'), $('input.grade.newsflash.message'));" />
                    </div>    
                </div>
                <!-- end actions-box -->
            </div>
            <!-- End content-grades -->
        </div>
        
    </div>    
</div>

<div id="showStudent" style="display: none;">
    <div id="col-2-home">
        <div class="container-grades">
            <div class="content-grades">
                <h1><span id="student.grade.course.name"></span></h1>
                <h2 style="float:left; width:400px; padding-top:45px;"><span id="student.grade.name"></span></h2>
                <h2 style="float:left; width:400px; padding-top:7px;"><s:text name="grade.show.students" /></h2>
                <br class="clear" />
                <form action="" class="form-edit">
                    <input type="hidden" id="student.grade.id" name="student.grade.id" value="" />
                    <input type="hidden" id="student.grade.course.id" name="student.grade.course.id" value="" />
                </form>
                <br class="clear" />
                <div id="studentData"></div>
                <div class="actions-box">
                    <h2><s:text name="grade.show.actions" /></h2>
                    <div class="edit-tools">
                        <ul>
                            
                            
                            <li><a class="icon-new" href="javascript:showEntryStudent(document.getElementById('student.grade.id').value, document.getElementById('student.grade.course.id').value);"><s:text name= "student.grade.new.student" /></a></li>
                            <li><a class="icon-delete" href="javascript:deleteStudent(document.getElementById('student.grade.id').value);"><s:text name= "student.grade.delete.student" /></a></li>
                            <li><a class="icon-newsFlash" href="javascript:showSendNewsFlash('student.newsflash');"><s:text name= "student.send.newsflash" /></a></li>
                            <li><a class="icon-message" href="javascript:showSendMessage('student.message');"><s:text name= "student.send.message" /></a></li>
                            <li><a class="icon-message" href="javascript:showDiv('student.note');"><s:text name= "student.send.note" /></a></li>
                            <br class="clear" />
                        </ul>
                    </div>
                    <div class="edit-box" id="student.grade.students">
                    </div>
                    <div id="student.message" style="display:none;"class="course-message">
                        <h4><s:text name ="student.send.message" /></h4><br />
                        <s:text name="message.title" /><br /><input type="text" name="input.student.message.title" id="input.student.message.title" size="55" maxlength="50" /><br />
                        <s:text name="message.type"/><br /><textarea cols="62" rows="4" onKeyDown="textCounter(this,$('input.student.message.description.len'),250);" onKeyUp="textCounter(this,$('input.student.message.description.len'),250);" name="input.student.message.description" id="input.student.message.description"></textarea><br />
                        <s:text name="message.remaining" /><input readonly type=text id="input.student.message.description.len" name="input.student.message.description.len" size=3 maxlength=3 value="250"/><br/>
                        <input type="button" value="Submit" onclick="sendMessageStudent($('input.student.message.title'), $('input.student.message.description'));" />
                    </div>
                    <div id="student.newsflash" style="display:none;"class="course-message">
                        <h4><s:text name="student.send.newsflash"/></h4><br />
                        <s:text name="message.type" /><br /> <textarea cols="62" rows="4" onKeyDown="textCounter(this,$('input.student.newsflash.message.len'),250);" onKeyUp="textCounter(this,$('input.student.newsflash.message.len'),250);" name="input.student.newsflash.message" id="input.student.newsflash.message"></textarea><br />
                        <s:text name="message.remaining" /><input readonly type=text id="input.student.newsflash.message.len" name="input.student.newsflash.message.len" size=3 maxlength=3 value="250"/><br/>
                        <input type="button" value="Submit" onclick="sendNewsFlashStudent($('input.student.newsflash.message'));" />
                    </div>                        
                    <div id="student.note" style="display:none;"class="course-message">
                        <h4><s:text name ="student.send.note" /></h4><br />
                        <s:text name="note.what"/><br /><input type="text" name="input.student.note.what" id="input.student.note.what" maxlength="50" size="55" /><br />
                        <s:text name="note.where" /><br /><input type="text" name="input.student.note.where" id="input.student.note.where" maxlength="50" size="55" /><br />
                        <s:text name="note.dtStart" /><br />
                        <cal:jscalendar format="%d/%m/%Y %H:%M:%S" name="input.student.note.dtStart" id="input.student.note.dtStart"/><a href="#" onmouseover="return escape('Entre com a data de início do compromisso')">?</a><br />
                        <br />
                        <s:text name="note.dtEnd" /><br />
                        <cal:jscalendar format="%d/%m/%Y %H:%M:%S" name="input.student.note.dtEnd" id="input.student.note.dtEnd"/><a href="#" onmouseover="return escape('Entre com a data de término do compromisso')">?</a><br />
                        <br />
                        <s:text name="note.description"/><br /><textarea cols="65" rows="8" name="input.student.note.description" id="input.student.note.description"></textarea><br />
                        <input type="button" value="Submit" onclick="sendNoteStudent($('input.student.note.dtStart'), $('input.student.note.dtEnd'), $('input.student.note.where'), $('input.student.note.what'), $('input.student.note.description'));" />
                    </div>                    
                </div>
            </div>
        </div>
    </div>    
</div>

<div id="showProfessor" style="display: none;">
    <div id="col-2-home">
        <div class="container-grades">
            <div class="content-grades">
                <h1><span id="professor.grade.course.name"></span></h1>
                <h2 style="float:left; width:400px; padding-top:45px;"><span id="professor.grade.name"></span></h2>
                <h2 style="float:left; width:400px; padding-top:7px;"><s:text name="grade.input.professors" /></h2>
                <br class="clear" />
                <form action="" class="form-edit">
                    <input type="hidden" id="professor.grade.id" name="professor.grade.id" value="" />
                    <input type="hidden" id="professor.grade.course.id" name="professor.grade.course.id" value="" />
                </form>
                <br class="clear" />
                <div id="professorData"></div>
                <div class="actions-box">
                    <h2><s:text name="grade.show.actions" /></h2>
                    <div class="edit-tools">
                        <ul>
                            <li><a class="icon-new" href="javascript:showEntryProfessor(document.getElementById('professor.grade.id').value, document.getElementById('professor.grade.course.id').value);"><s:text name= "professor.grade.new.professor" /></a></li>
                            <li><a class="icon-delete" href="javascript:deleteProfessor(document.getElementById('professor.grade.id').value);"><s:text name= "professor.grade.delete.professor" /></a></li>
                            <li><a class="icon-newsFlash" href="javascript:showSendNewsFlash('professor.newsflash');"><s:text name= "student.send.newsflash" /></a></li>
                            <li><a class="icon-message" href="javascript:showSendMessage('professor.message');"><s:text name= "student.send.message" /></a></li>
                            <%-- <li><a class="icon-message" href="javascript:showDiv('professor.note');"><s:text name= "student.send.note" /></a></li>--%>
                            <br class="clear" />
                        </ul>
                    </div>
                    <div class="edit-box" id="professor.grade.professors">
                    </div>
                    <div id="professor.message" style="display:none;"class="course-message">
                        <h4><s:text name ="student.send.message" /></h4><br />
                        <s:text name="message.title" /><br /><input type="text" name="input.professor.message.title" id="input.professor.message.title" size="55" maxlength="50" /><br />
                        <s:text name="message.type"/><br /><textarea cols="62" rows="4" onKeyDown="textCounter(this,$('input.professor.message.description.len'),250);" onKeyUp="textCounter(this,$('input.professor.message.description.len'),250);" name="input.professor.message.description" id="input.professor.message.description"></textarea><br />
                        <s:text name="message.remaining" /><input readonly type=text id="input.professor.message.description.len" name="input.professor.message.description.len" size=3 maxlength=3 value="250"/><br/>
                        <input type="button" value="Submit" onclick="sendMessageProfessor($('input.professor.message.title'), $('input.professor.message.description'));" />
                    </div>
                    <div id="professor.newsflash" style="display:none;"class="course-message">
                        <h4><s:text name="student.send.newsflash"/></h4><br />
                        <s:text name="message.type" /><br /><textarea cols="62" rows="4" onKeyDown="textCounter(this,$('input.professor.newsflash.message.len'),250);" onKeyUp="textCounter(this,$('input.professor.newsflash.message.len'),250);" name="input.professor.newsflash.message" id="input.professor.newsflash.message"></textarea><br />
                        <s:text name="message.remaining" /><input readonly type=text id="input.professor.newsflash.message.len" name="input.professor.newsflash.message.len" size=3 maxlength=3 value="250"/><br/>
                        <input type="button" value="Submit" onclick="sendNewsFlashProfessor($('input.professor.newsflash.message'));" />
                    </div>                     
                </div>
            </div>
        </div>
    </div>    
</div>

<div id="showTutor" style="display: none;">
    <div id="col-2-home">
        <div class="container-grades">
            <div class="content-grades">
                <h1><span id="tutor.grade.course.name"></span></h1>
                <h2 style="float:left; width:400px; padding-top:45px;"><span id="tutor.grade.name"></span></h2>
                <h2 style="float:left; width:400px; padding-top:7px;"><s:text name="grade.input.tutors" /></h2>
                <br class="clear" />
                <form action="" class="form-edit">
                    <input type="hidden" id="tutor.grade.id" name="tutor.grade.id" value="" />
                    <input type="hidden" id="tutor.grade.course.id" name="tutor.grade.course.id" value="" />
                </form>
                <br class="clear" />
                <div id="tutorData"></div>
                <div class="actions-box">
                    <h2><s:text name="grade.show.actions" /></h2>
                    <div class="edit-tools">
                        <ul>
                            <li><a class="icon-new" href="javascript:showEntryTutor(document.getElementById('tutor.grade.id').value, document.getElementById('tutor.grade.course.id').value);"><s:text name= "tutor.grade.new.tutor" /></a></li>
                            <li><a class="icon-delete" href="javascript:deleteTutor(document.getElementById('tutor.grade.id').value);"><s:text name= "tutor.grade.delete.tutor" /></a></li>
                            <li><a class="icon-newsFlash" href="javascript:showSendNewsFlash('tutor.newsflash');"><s:text name= "student.send.newsflash" /></a></li>
                            <li><a class="icon-message" href="javascript:showSendMessage('tutor.message');"><s:text name= "student.send.message" /></a></li>                            
                            <%--<li><a class="icon-message" href="javascript:showDiv('tutor.note');"><s:text name= "student.send.note" /></a></li>--%>
                            <br class="clear" />
                        </ul>
                    </div>
                    <div class="edit-box" id="tutor.grade.tutors">
                    </div>
                    <div id="tutor.message" style="display:none;"class="course-message">
                        <h4><s:text name ="student.send.message" /></h4><br />
                        <s:text name="message.title" /><br /><input type="text" name="input.tutor.message.title" id="input.tutor.message.title" size="55" maxlength="50" /><br />
                        <s:text name="message.type"/><br /><textarea cols="62" rows="4" onKeyDown="textCounter(this,$('input.tutor.message.description.len'),250);" onKeyUp="textCounter(this,$('input.tutor.message.description.len'),250);" name="input.tutor.message.description" id="input.tutor.message.description"></textarea><br />
                        <s:text name="message.remaining" /><input readonly type=text id="input.tutor.message.description.len" name="input.tutor.message.description.len" size=3 maxlength=3 value="250"/><br/>
                        <input type="button" value="Submit" onclick="sendMessageTutor($('input.tutor.message.title'), $('input.tutor.message.description'));" />
                    </div>
                    <div id="tutor.newsflash" style="display:none;"class="course-message">
                        <h4><s:text name="student.send.newsflash"/></h4><br />
                        <s:text name="message.type" /><br /><textarea cols="62" rows="4" onKeyDown="textCounter(this,$('input.tutor.newsflash.message.len'),250);" onKeyUp="textCounter(this,$('input.tutor.newsflash.message.len'),250);" name="input.tutor.newsflash.message" id="input.tutor.newsflash.message"></textarea><br />
                        <s:text name="message.remaining" /><input readonly type=text id="input.tutor.newsflash.message.len" name="input.tutor.newsflash.message.len" size=3 maxlength=3 value="250"/><br/>
                        <input type="button" value="Submit" onclick="sendNewsFlashTutor($('input.tutor.newsflash.message'));" />
                    </div>                     
                    
                </div>
            </div>
        </div>
    </div>    
</div>

<div id="showForum" style="display: none;">
    <div id="col-2-home">
        <div class="container-grades">
            <div class="content-grades">
                <h1><span id="forum.grade.course.name"></span></h1>
                <h2 style="float:left; width:400px; padding-top:45px;"><span id="forum.grade.name"></span></h2>
                <h2 style="float:left; width:400px; padding-top:7px;"><s:text name="grade.show.forums" /></h2>
                <br class="clear" />
                <form action="" class="form-edit">
                    <input type="hidden" id="forum.show.grade.id" name="forum.show.grade.id" value="" />
                    <input type="hidden" id="forum.grade.course.id" name="forum.grade.course.id" value="" />
                </form>
                <br class="clear" />
                <div class="actions-box">
                    <h2><s:text name="grade.show.actions" /></h2>
                    <div class="edit-tools">
                        <ul>
                            <li><a class="icon-new" href="javascript:showEntryForum(document.getElementById('forum.show.grade.id').value);"><s:text name= "forum.grade.new.forum" /></a></li>
                            <li><a class="icon-delete" href="javascript:deleteForum();"><s:text name= "forum.grade.delete.forum" /></a></li>
                            <br class="clear" />
                        </ul>
                    </div>
                    <div class="edit-box" id="forum.grade.forums">
                    </div>
                </div>
            </div>
        </div>
    </div>    
</div>

<div id="showForumId" style="display: none;">
    <div id="col-2-home">
        <div class="container-grades">
            <div class="content-grades">
                <h1><span id="forumId.grade.course.name"></span></h1>
                <h2 style="float:left; width:400px; padding-top:45px;"><span id="forumId.grade.name"></span></h2>
                <h2 style="float:left; width:400px; padding-top:7px;"><s:text name="grade.show.forums" /></h2>
                <h2 style="float:left; width:400px; padding-top:7px;"><span id="forumId.grade.forum.title"></span></h2>
                <br class="clear" />
                <form action="" class="form-edit">
                    <input type="hidden" id="forumId.show.grade.id" name="forumId.show.grade.id" value="" />
                    <input type="hidden" id="forumId.grade.course.id" name="forumId.grade.course.id" value="" />
                    <input type="hidden" id="forumId.grade.forum.id" name="forumId.grade.forum.id" value="" />
                </form>
                <br class="clear" />
                <div class="actions-box">
                    <h2><s:text name="grade.show.actions" /></h2>
                    <div class="edit-tools">
                        <ul>
                            <li><a class="icon-new" href="javascript:showEntryTopic(document.getElementById('forumId.grade.forum.id').value);"><s:text name= "forumId.grade.new.topic" /></a></li>
                            <li><a class="icon-delete" href="javascript:deleteTopic();"><s:text name= "forumId.grade.delete.topic" /></a></li>
                            <br class="clear" />
                        </ul>
                    </div>
                    <div class="edit-box" id="forumId.grade.topics">
                    </div>
                </div>
            </div>
        </div>
    </div>    
</div>

<!-- end col-2-home -->
<br class="clear" />
<script  language="JavaScript" type="text/javascript" src="../js/tooltip/wz_tooltip.js"></script>
<!-- end content -->
<br class="clear" />
ut.tutor.newsflash.message.len'),250);" name="input.tutor.newsflash.message" id="input.tutor.newsflash.message"></textarea><br />
                        <s:text name="message.remaining" /><input readonly type=text id="input.tutor.newsflash.message.len" name="input.tutor.newsflash.message.len" size=3 maxlength=3 value="250"/><br/>
                        <input type="button" value="Submit" onclick="sendNewsFlashTutor($('input.tutor.newsflash.message'));" />
                    </div>                     
                    
                </div>
            </div>
        </div>
    </div>    
</div>

<div id="showForum" style="display: none;">
    <div id="col-2-home">
        <div class="container-grades">
            <div class="content-grades">
                <h1><span id="forum.grade.course.name"></span></h1>
                <h2 style="float:left; width:400px; padding-top:45px;"><span id="forum.grade.name"></span></h2>
                <h2 style="float:left; width:400px; padding-top:7px;"><s:text name="grade.show.forums" /></h2>
                <br class="clear" />
                <form action="" class="form-edit">
                    <input type="hidden" id="forum.show.grade.id" name="forum.show.grade.id" value="" />
                    <input type="hidden" id="forum.grade.course.id" name="forum.grade.course.id" value="" />
                </form>
                <br class="clear" />
                <div class="actions-box">
                    <h2><s:text name="grade.show.actions" /></h2>
                    <div class="edit-tools">
                        <ul>
                            <li><a class="icon-new" href="javascript:showEntryForum(document.getElementById('forum.show.grade.id').value);"><s:text name= "forum.grade.new.forum" /></a></li>
                            <li><a class="icon-delete" href="javascript:deleteForum();"><s:text name= "forum.grade.delete.forum" /></a></li>
                            <br class="clear" />
                        </ul>
                    </div>
                    <div class="edit-box" id="forum.grade.forums">
                    </div>
                </div>
            </div>
        </div>
    </div>    
</div>

<div id="showForumId" style="display: none;">
    <div id="col-2-home">
        <div class="container-grades">
            <div class="content-grades">
                <h1><span id="forumId.grade.course.name"></span></h1>
                <h2 style="float:left; width:400px; padding-top:45px;"><span id="forumId.grade.name"></span></h2>
                <h2 style="float:left; width:400px; padding-top:7px;"><s:text name="grade.show.forums" /></h2>
                <h2 style="float:left; width:400px; padding-top:7px;"><span id="forumId.grade.forum.title"></span></h2>
                <br class="clear" />
                <form action="" class="form-edit">
                    <input type="hidden" id="forumId.show.grade.id" name="forumId.show.grade.id" value="" />
                    <input type="hidden" id="forumId.grade.course.id" name="forumId.grade.course.id" value="" />
                    <input type="hidden" id="forumId.grade.forum.id" name="forumId.grade.forum.id" value="" />
                </form>
                <br class="clear" />
                <div class="actions-box">
                    <h2><s:text name="grade.show.actions" /></h2>
                    <div class="edit-tools">
                        <ul>
                            <li><a class="icon-new" href="javascript:showEntryTopic(document.getElementById('forumId.grade.forum.id').value);"><s:text name= "forumId.grade.new.topic" /></a></li>
                            <li><a class="icon-delete" href="javascript:deleteTopic();"><s:text name= "forumId.grade.delete.topic" /></a></li>
                            <br class="clear" />
                        </ul>
                    </div>
                    <div class="edit-box" id="forumId.grade.topics">
                    </div>
                </div>
            </div>
        </div>
    </div>    
</div>

<!-- end col-2-home -->
<br class="clear" />
<script  language="JavaScript" type="text/javascript" src="../js/tooltip/wz_tooltip.js"></script>
<!-- end content -->
<br class="clear" />
