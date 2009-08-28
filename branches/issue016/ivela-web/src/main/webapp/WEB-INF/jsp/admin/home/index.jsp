<%-- 
    Document   : show grade
    Created on : Sep 15, 2008, 2:07:13 PM
    Author     : marcus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<head>
    <link href="../css/lightbox.css"  rel="stylesheet" type="text/css" />
    <link href="../css/cadAtividades.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../js/admin/admin.js"></script>
    <script type="text/javascript" src="../js/admin/scorecard.js"></script>
    <script type="text/javascript" src="../js/admin/tools.js"></script>
    <script type="text/javascript" src="../js/lightbox.js"></script>
    <script type="text/javascript" src="../js/chart/wz_jsgraphics.js "></script>
    <script type="text/javascript" src="../js/chart/pie.js"></script>
</head>
<s:actionerror />

<div id="breadcrumb">
    <p><s:text name="breadcrumb.youAreHere"/></p>
    <ul>
        <li><a href="index.action"><s:text name="admin.home"/></a></li>
        <li class="current"><s:text name="admin.controlPanel" /></li>
    </ul>
</div>

<div id="col-1-home">
    <h2><s:text name="admin.pageTitle" /></h2>
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
                <div id="course.name.<s:property value="id" />" style="display: none;"><s:property value="name" /></div>
                <div id="course.description.<s:property value="id" />" style="display: none;"><s:property value="description" /></div>
                <div id="coordinators.course.<s:property value="id" />" style="display: none;"></div>
                <div id="professors.course.<s:property value="id" />" style="display: none;"></div>
                <div id="tutors.course.<s:property value="id" />" style="display: none;"></div>
                <div id="studentsCount.course.<s:property value="id" />" style="display: none;"></div>
                <div id="countCoordinators.course.<s:property value="id" />" style="display: none;"></div>
                <div id="countProfessors.course.<s:property value="id" />" style="display: none;"></div>
                <div id="countTutor.course.<s:property value="id" />" style="display: none;"></div>
                <div id="course.grades.<s:property value="id" />" style="display: none;"></div>                
                <script language="JavaScript">
                    var professors = ',';
                    var tutors = ',';
                    var grades = '';
                    var coordinators = ',';
                    var studentsCount = 0;
                    var professorsCount = 0;
                    var tutorCount = 0;
                    var coordinatorCount =0;
                    <s:iterator value="grades">
                        grades += '<s:property value="id" />' + ';';
                        if (coordinators.indexOf(',<s:property value="coordinator.username" />,') == -1){
                            coordinators += '<s:property value="coordinator.username" />' + ',';
                            coordinatorCount++ ;
                        }
                        <s:iterator value="professors">
                            if (professors.indexOf(',<s:property value="username" />,') == -1){
                                professors += '<s:property value="username" />' + ',';
                            }
                        </s:iterator>
                            professorsCount += <s:property value="professors.size()" />;
                        <s:iterator value="tutors">
                            if (tutors.indexOf(',<s:property value="username" />,') == -1){
                                tutors += '<s:property value="username" />' + ',';
                            }
                        </s:iterator>
                            tutorCount += <s:property value="tutors.size()" />;
                            studentsCount += <s:property value="enrollments.size()" />;
                    </s:iterator>
                        if (grades.length > 0) {
                            grades = grades.substring(0, grades.length - 1);
                        }
                        if (coordinators.length > 1) {
                            coordinators = coordinators.substring(0, coordinators.length - 1);
                            coordinators += '.';          
                        }   
                        if (professors.length > 1) {
                            professors = professors.substring(0, professors.length - 1);
                            professors += '.';
                        }
                        if (tutors.length > 1) {
                            tutors = tutors.substring(0, tutors.length - 1);
                            tutors += '.';
                        }
                        if (coordinators.length > 0 && coordinators.indexOf(',') == 0)
                            coordinators = coordinators.substring(1, coordinators.length);
                        if (professors.length > 0 && professors.indexOf(',') == 0)
                            professors = professors.substring(1, professors.length);
                        if (tutors.length > 0 && tutors.indexOf(',') == 0)
                            tutors = tutors.substring(1, tutors.length);                    
                        if (coordinators.length == 0)
                            coordinators = 'none.';
                        if (professors.length == 0)
                            professors = 'none.';
                        if (tutors.length == 0)
                            tutors = 'none.';
                        $('course.grades.<s:property value="id" />').innerHTML = grades;
                        $('coordinators.course.<s:property value="id" />').innerHTML = coordinators;
                        $('professors.course.<s:property value="id" />').innerHTML = professors;
                        $('countProfessors.course.<s:property value="id" />').innerHTML = "("+ professorsCount+"):";
                        $('countTutor.course.<s:property value="id" />').innerHTML = "("+ tutorCount+"):";
                        $('tutors.course.<s:property value="id" />').innerHTML = tutors;
                        $('countCoordinators.course.<s:property value="id" />').innerHTML = "("+coordinatorCount+"):";
                        $('studentsCount.course.<s:property value="id" />').innerHTML = studentsCount;
                </script>
                <div id="vertical_nested_container">
                    
                    <s:if test="grades.size() == 0">
                        <h4 class="no_grades"><s:text name="admin.nogrades" /></h4>
                    </s:if>
                        
                    <s:iterator value="grades" status="gstat">
                        <h4 class="vertical_accordion_toggle"><s:property value="name" /> - <s:property value="enrollments.size()" /> student(s) </h4>
                        <div class="vertical_accordion_content" id="<s:property value="id" />">
                            <div id="grade.name.<s:property value="id" />" style="display: none;"><s:property value="name" /></div>
                            <div id="grade.course.id.<s:property value="id" />" style="display: none;"><s:property value="courseId" /></div>
                            <div id="grade.course.name.<s:property value="id" />" style="display: none;"><s:property value="course.name" /></div>
                            <div id="grade.course.description.<s:property value="id" />" style="display: none;"><s:property value="course.description" /></div>
                            <div id="coordinators.grade.<s:property value="id" />" style="display: none;"></div>
                            <div id="professors.grade.<s:property value="id" />" style="display: none;"></div>
                            <div id="tutors.grade.<s:property value="id" />" style="display: none;"></div>
                            <div id="studentsCount.grade.<s:property value="id" />" style="display: none;"></div>
                            <div id="countProfessors.grade.<s:property value="id" />" style="display: none;"></div>
                            <div id="countTutor.grade.<s:property value="id" />" style="display: none;"></div>
                            <div id="startdate.grade.<s:property value="id" />" style="display: none;"></div>
                            <div id="enddate.grade.<s:property value="id" />" style="display: none;"></div>
                            <div id="vertical_nested_container2">
                                <h5 class="vertical_accordion_toggle2" id="students"><s:text name="grade.show.students" /></h5>                        
                                <div class="vertical_accordion_content2" id="<s:property value="id" />">
                                    <script language="JavaScript">
                                        var professors = ',';
                                        var tutors = ',';
                                        var coordinators = ',';
                                        var studentsCount = 0;
                                        var professorsCount = 0;
                                        var tutorCount = 0;
                                        if (coordinators.indexOf(',<s:property value="coordinator.username" />,') == -1)
                                        coordinators += '<s:property value="coordinator.username" />' + ','; 
                                    <s:iterator value="professors">
                                        if (professors.indexOf(',<s:property value="username" />,') == -1)
                                        professors += '<s:property value="username" />' + ','; 
                                    </s:iterator>
                                        professorsCount += <s:property value="professors.size()" />;
                                    <s:iterator value="tutors">
                                        if (tutors.indexOf(',<s:property value="username" />,') == -1)
                                        tutors += '<s:property value="username" />' + ','; 
                                    </s:iterator>
                                        tutorCount += <s:property value="tutors.size()" />;
                                        studentsCount += <s:property value="enrollments.size()" />;
                                        if (coordinators.length > 1) {
                                            coordinators = coordinators.substring(0, coordinators.length - 1);
                                            coordinators += '.';          
                                        }   
                                        if (professors.length > 1) {
                                            professors = professors.substring(0, professors.length - 1);
                                            professors += '.';
                                        }
                                        if (tutors.length > 1) {
                                            tutors = tutors.substring(0, tutors.length - 1);
                                            tutors += '.';
                                        }
                                        if (coordinators.length > 0 && coordinators.indexOf(',') == 0)
                                            coordinators = coordinators.substring(1, coordinators.length);
                                        if (professors.length > 0 && professors.indexOf(',') == 0)
                                            professors = professors.substring(1, professors.length);
                                        if (tutors.length > 0 && tutors.indexOf(',') == 0)
                                            tutors = tutors.substring(1, tutors.length);                    
                                        if (coordinators.length == 0)
                                            coordinators = 'none.';
                                        if (professors.length == 0)
                                            professors = 'none.';
                                        if (tutors.length == 0)
                                            tutors = 'none.';
                                        $('coordinators.grade.<s:property value="id" />').innerHTML = coordinators;
                                        $('professors.grade.<s:property value="id" />').innerHTML = professors;
                                        $('countProfessors.grade.<s:property value="id" />').innerHTML = "("+ professorsCount+") :";
                                        $('countTutor.grade.<s:property value="id" />').innerHTML = "("+ tutorCount+") :";
                                        $('tutors.grade.<s:property value="id" />').innerHTML = tutors;
                                        $('studentsCount.grade.<s:property value="id" />').innerHTML = studentsCount;
                                        $('startdate.grade.<s:property value="id" />').innerHTML = '<s:property value="startDatetime" />';
                                        $('enddate.grade.<s:property value="id" />').innerHTML = '<s:property value="endDatetime" />';
                                    </script>
                                    
                                    <s:if test="(enrollments != null && enrollments.size() > 0)"> 
                                        <ul class="list-students">
                                            <input type="button" value="<s:text name="home.mark" />"  class="btn-check-all" onclick="setCheckedAllStudents('<s:property value="id" />');" />
                                            <input type="button" value="<s:text name="home.unmark" />" class="btn-uncheck-all" onclick="setUncheckedAllStudents('<s:property value="id" />');" />
                                            <br />
                                            <s:iterator value="enrollments" status="stat"> 
                                                <s:if test="enrollments[#stat.index].status == 0">
                                                    <li title="<s:property value="systemUser.username" />" id="li.student.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />" class="box-person">
                                                        <img id="img_<s:property value="grades[#gstat.index].id" />_<s:property value="systemUser.id" />" src="../images/icon/icon-person-yellow.gif" /><br />
                                                        <s:if test="systemUser.username.length() > 8">
                                                            <s:property value="systemUser.username.substring(0,8)+ \"...\"" />
                                                        </s:if>
                                                        <s:else>
                                                            <s:property value="systemUser.username" />
                                                        </s:else>
                                                            <br /><input type="checkbox" value="<s:property value="systemUser.id" />" id="student.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />" 
                                                                         name="studentsCheck" onclick="updateStudents(this, '<s:property value="systemUser.username" />', '<s:property value="systemUser.email" />', '<s:property value="systemUser.createdAt" />', '<s:text name="student.input.username"/>', '<s:text name="student.input.email"/>', '<s:text name="student.input.createdAt"/>');" />
                                                    </li>
                                                </s:if>
                                                <s:if test="enrollments[#stat.index].status == 1">
                                                    <li title="<s:property value="systemUser.username" />" id="li.student.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />" class="box-person">
                                                        <img id="img_<s:property value="grades[#gstat.index].id" />_<s:property value="systemUser.id" />" src="../images/icon/icon-person-green.gif" /><br />
                                                        <s:if test="systemUser.username.length() > 8">
                                                            <s:property value="systemUser.username.substring(0,8)+ \"...\"" />
                                                        </s:if>
                                                        <s:else>
                                                            <s:property value="systemUser.username" />
                                                        </s:else> 
                                                            <br /><input type="checkbox" value="<s:property value="systemUser.id" />" id="student.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />" 
                                                                         name="studentsCheck" onclick="updateStudents(this, '<s:property value="systemUser.username" />', '<s:property value="systemUser.email" />', '<s:property value="systemUser.createdAt" />', '<s:text name="student.input.username"/>', '<s:text name="student.input.email"/>', '<s:text name="student.input.createdAt"/>');" />
                                                    </li>
                                                </s:if>
                                                <s:if test="enrollments[#stat.index].status == 2">
                                                    <li title="<s:property value="systemUser.username" />" id="li.student.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />" class="box-person">
                                                        <img id="img_<s:property value="grades[#gstat.index].id" />_<s:property value="systemUser.id" />" src="../images/icon/icon-person-red.gif" /><br />
                                                        <s:if test="systemUser.username.length() > 8">
                                                            <s:property value="systemUser.username.substring(0,8)+ \"...\"" />
                                                        </s:if>
                                                        <s:else>
                                                            <s:property value="systemUser.username" />
                                                        </s:else>
                                                            <br /><input type="checkbox" value="<s:property value="systemUser.id" />" id="student.id_<s:property value="grades[#gstat.index].id" />_<s:property value="#stat.index" />"
                                                                         name="studentsCheck" onclick="updateStudents(this, '<s:property value="systemUser.username" />', '<s:property value="systemUser.email" />', '<s:property value="systemUser.createdAt" />', '<s:text name="student.input.username"/>', '<s:text name="student.input.email"/>', '<s:text name="student.input.createdAt"/>');" />
                                                    </li>
                                                </s:if>
                                            </s:iterator>
                                            </s:if>
                                        
                                            <br class="clear" />
                                        </ul>
                                        
                                        <!-- Begin: Student Data -->
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
                                        <br>
                                        <!-- End: Student Data -->    
                                    <s:else>
                                        <span class="no_grades"><s:text name="admin.noStudents" /></span>
                                    </s:else>
                                    <br />
                                    
                                </div>
                            </div>
                        </div>
                    </s:iterator>
                </div>
            </div>
        </s:iterator>            
        </s:else>

    </div>
</div>
<!-- end col-1-home -->

<div id="box1" style="display:none; text-align:center;">
    <iframe id="waitingFrame" frameborder="0" src="../waiting.jsp" style="width:150px;height:100px;border:0px solid #fff;" scrolling="no" >
    </iframe><br/>
</div>

<div id="showCourse" style="display: none;">
    <div id="col-2-home">
        <div class="container-grades">
            <div class="content-grades">
                <h1><span id="course.name"></span></h1>
                <br class="clear" />
                <form action="" class="form-edit">
                    <input type="hidden" id="course.id" name="course.id" value="" />
                    <input type="hidden" id="course.grades" name="course.grades" value="" />
                </form>
                <br class="clear" />
                
                <h2><span><s:text name="admin.show.description" />:</span> </h2> <p><span id="course.description"></span><br /> </p>
                <br/>
                <img id="course.image" src="" width="80" height="80" />
                <br/>
                <div>
                    <p><s:text name="admin.show.studentsCount" /> <span id="admin.course.student.count"></span></p>
                    <p><s:text name="admin.show.coordinators" /> <span id="admin.course.coordinators"></span></p>
                    <p><s:text name="admin.show.professors" /> <span id="admin.course.professors"></span></p>
                    <p><s:text name="admin.show.tutors" /> <span id="admin.course.tutors"></span></p>
                </div>
                <div class="actions-box">
                    <h2><s:text name="course.show.actions" /></h2>
                    <div class="edit-tools">
                        <ul>
                            <%--
                            <li><a class="icon-new" href="#" onclick="showEntryGrade(document.getElementById('course.id').value);"><s:text name= "course.new.grade" /></a></li>
                            <li><a class="icon-edit" href="javascript:showEditGrade();"><s:text name= "course.edit.grade" /></a></li>
                            <li><a class="icon-delete" href="#" onclick="deleteGrade();"><s:text name= "course.delete.grade" /></a></li>
                            --%>
                            <li><a class="icon-newsFlash"href="javascript:closeAllMessages(); showNewsflashCourse();"><s:text name= "course.send.newsflash" /></a></li>
                            <li><a class="icon-message"href="javascript:closeAllMessages(); showMessageCourse();"><s:text name= "course.send.message" /></a></li>
                            <br class="clear" />
                        </ul>
                    </div>
                    <div id="course.message" style="display:none;" class="course-message">
                        <h4><s:text name ="course.send.message" /></h4><br />
                        <s:text name="message.title" /><br /> <input type="text" name="input.course.message.title" id="input.course.message.title" size="55" maxlength="50" /><br />
                        <s:text name="message.type"/><br /> <textarea name="input.course.message.description" id="input.course.message.description" cols="62" rows="4"onKeyDown="textCounter(this,$('input.course.message.description.len'),250);" onKeyUp="textCounter(this,$('input.course.message.description.len'),250);"></textarea><br />
                        <s:text name="message.remaining" /><input readonly type=text id="input.course.message.description.len" name="input.course.message.description.len" size=3 maxlength=3 value="250"/><br/>
                        <input type="button" value="<s:text name="systemUser.input.btnSave" />" onclick="sendMessageGrade($('input.course.message.title'), $('input.course.message.description'));" />
                    </div>
                    <div id="course.newsflash" style="display:none;" class="course-message">
                        <h4><s:text name="course.send.newsflash"/></h4><br />
                        <s:text name="message.type" /><br /> <textarea name="input.course.newsflash.message" id="input.course.newsflash.message" cols="62" rows="4" onKeyDown="textCounter(this,$('input.course.newsflash.message.len'),250);" onKeyUp="textCounter(this,$('input.course.newsflash.message.len'),250);"></textarea><br />
                        <s:text name="message.remaining" /><input readonly type=text id="input.course.newsflash.message.len" name="input.course.newsflash.message.len" size=3 maxlength=3 value="250"/><br/>                        
                        <input type="button" value="<s:text name="systemUser.input.btnSave" />" onclick="sendNewsFlashGrade($('input.course.newsflash.message'));" />
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
                    <input type="hidden" id="grade.id" name="grade.id" value="" />
                    <input type="hidden" id="grade.course.id" name="grade.course.id" value="" />
                </form>
                <br class="clear" />
                
                <h2><s:text name="grade.input.course.description" />:</h2> <p style="margin-top:0;"><span id="grade.course.description"></span></p>
                
                <div>
                    <p><s:text name="admin.show.studentsCount" /> <span id="admin.grade.student.count"></span></p>
                    <p><s:text name="admin.show.coordinators" /> <span id="admin.grade.coordinators"></span></p>
                    <p><s:text name="admin.show.professors" />  <span id="admin.grade.professors"></span></p>
                    <p><s:text name="admin.show.tutors" /> <span id="admin.grade.tutors"></span></p>
                    <p><s:text name="admin.show.startDate" /> <span id="admin.grade.startdate"></span></p>
                    <p><s:text name="admin.show.endDate" /> <span id="admin.grade.enddate"></span></p>
                </div>
                <div class="actions-box">
                    <h2><s:text name="grade.show.actions" /></h2>
                    <div class="edit-tools">
                        <ul>
                            <%--
            <li><a class="icon-new" href="#" onclick="showEntryStudent($('grade.id').value, $('grade.course.id').value);"><s:text name= "grade.students.new" /></a></li>
            <li><a class="icon-new" href="#" onclick="showEntryProfessor($('grade.id').value, $('grade.course.id').value);"><s:text name= "grade.professors.new" /></a></li>
            <li><a class="icon-new" href="#" onclick="showEntryTutor($('grade.id').value, $('grade.course.id').value);"><s:text name= "grade.tutors.new" /></a></li>
                            --%>
                            <li><a class="icon-newsFlash"href="javascript:closeAllMessages(); showNewsflashGrade();"><s:text name= "grade.send.newsflash" /></a></li>
                            <li><a class="icon-message" href="javascript:closeAllMessages(); showMessageGrade();"><s:text name= "grade.send.message" /></a></li>
                            
                            <br class="clear" />
                        </ul>
                    </div>
                    <div id="grade.message" style="display:none;"class="course-message">
                        <h4><s:text name ="course.send.message" /></h4><br />
                        <s:text name="message.title" /><br /> <input type="text" name="input.grade.message.title" id="input.grade.message.title" size="55" maxlength="50" /><br />
                        <s:text name="message.type"/><br /> <textarea name="input.grade.message.description" id="input.grade.message.description" cols="62" rows="4" onKeyDown="textCounter(this,$('input.grade.message.description.len'),250);" onKeyUp="textCounter(this,$('input.grade.message.description.len'),250);"></textarea><br />
                        <s:text name="message.remaining" /><input readonly type=text id="input.grade.message.description.len" name="input.grade.message.description.len" size=3 maxlength=3 value="250"/><br/>                                                
                        <input type="button" value="<s:text name="systemUser.input.btnSave" />" onclick="sendMessageGradeId($('grade.id'), $('input.grade.message.title'), $('input.grade.message.description'));" />
                    </div>
                    <div id="grade.newsflash" style="display:none;" class="course-message" >
                        <h4><s:text name="course.send.newsflash"/></h4><br />
                        <s:text name="message.type" /><br /><textarea name="input.grade.newsflash.message" id="input.grade.newsflash.message" cols="62" rows="4" onKeyDown="textCounter(this,$('input.grade.newsflash.message.len'),250);" onKeyUp="textCounter(this,$('input.grade.newsflash.message.len'),250);"></textarea><br />
                        <s:text name="message.remaining" /><input readonly type=text id="input.grade.newsflash.message.len" name="input.grade.newsflash.message.len" size=3 maxlength=3 value="250"/><br/>                                                                        
                        <input type="button" value="<s:text name="systemUser.input.btnSave" />" onclick="sendNewsFlashGradeId($('grade.id'), $('input.grade.newsflash.message'));" />
                    </div>    
                </div>
                <!-- end actions-box -->
            </div>
            <!-- End content-grades -->
        </div>
        
    </div>    
</div>

<div id="showScorecard" style="display: none;">
    <div id="col-2-home">
        <div class="container-grades">
            <div class="content-grades">
                <table id="showScoreHead" class="table-exercises-info" width="568" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="100" class="titulo"><s:text name="scorecard.course" />:</td>
                        <td width="468" id="show.scorecard.course"></td>
                        
                    </tr>
                    <tr>
                        <td class="titulo"><s:text name="scorecard.grade" />:</td>
                        <td id="show.scorecard.grade"></td>
                    </tr>
                    <tr>
                        <td class="titulo"><s:text name="scorecard.student" />:</td>
                        <td id="show.scorecard.student"></td>
                    </tr>
                </table>
                <div id="show.scorecard.notes"></div>
            </div>
        </div>
    </div>
</div>
<div id="showTranscript" style="display:none;">
    <div id="col-2-home">
        <div class="container-grades">
            <div class="content-grades">
               <table class="table-exercises-info" width="568" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="100" class="titulo"><s:text name="scorecard.course" />:</td>
                        <td width="468" id="show.transcript.course"> </td>
                        
                    </tr>
                    <tr>
                        <td class="titulo"><s:text name="scorecard.grade" />:</td>
                        <td id="show.transcript.grade"> </td>
                    </tr>
                    <tr>
                        <td class="titulo"><s:text name="scorecard.student" />:</td>
                        <td id="show.transcript.student"> </td>
                    </tr>
                </table>
                <div id="show.transcript.notes"></div>
            </div>
        </div>
    </div>
</div>

<div id="showCorrectQuestions" style="display:none;">
    <div id="col-2-home">
        <div class="container-grades">
            <div class="content-grades">
                <table id="scoreCorrectHead" class="table-exercises-info" width="568" border="0" cellspacing="0" cellpadding="0">
                </table>
                <a href="javascript:showScorecard($('student.grade.id').value);" ><s:text name="systemUser.input.btnBack" /></a>:
                <div id="show.correct.question"></div>
                <a href="javascript:showScorecard($('student.grade.id').value);" ><s:text name="systemUser.input.btnBack" /></a>:
            </div>
        </div>
    </div>
</div>


<div id="showEnrollmentList" style="display:none;">
    <div id="col-2-home">
        <div class="container-grades">
            <div class="content-grades">
                <br class="clear" />
                <div class="steps-box">
                    <div>
                        <div>
                            <span id="step"><s:text name="course.show.step" /></span>
                            <span id="current" >1</span>
                            <span id="">2</span>
                        </div>
                    </div>
                    <div class="tip-step"><s:text name="enrollment.message.index"/></div>
                    <div class="tip-step">
                        <s:text name="enrollment.message.example"/>
                        <span class="xml-example"><br />
                            <b>&lt;students&gt;</b><br />
                                <b>&nbsp;&nbsp;&nbsp;&nbsp;&lt;student&gt;</b><br />
                                    <b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;firstName&gt;</b>Andrew<b>&lt;/firstName&gt;</b><br />
                                    <b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;lastName&gt;</b>Klerk<b>&lt;/lastName&gt;</b><br />
                                    <b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;email&gt;</b>andrew@company.com<b>&lt;/email&gt;</b><br />
                                <b>&nbsp;&nbsp;&nbsp;&nbsp;&lt;/student&gt;</b><br />
                                <b>&nbsp;&nbsp;&nbsp;&nbsp;&lt;student&gt;</b><br />
                                    <b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;firstName&gt;</b>Joseph<b>&lt;/firstName&gt;</b><br />
                                    <b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;lastName&gt;</b>Schnider<b>&lt;/lastName&gt;</b><br />
                                    <b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;email&gt;</b>joseph@mycompany.net<b>&lt;/email&gt;</b><br />
                                <b>&nbsp;&nbsp;&nbsp;&nbsp;&lt;/student&gt;</b><br />
                                <b>&lt;/students&gt;</b><br />
                        </span>
                    </div>
                </div>
                <s:form name="formUpload" id="formUpload" action="grade!enrollmentStudents.action" method="post" enctype="multipart/form-data" target="iframeStudents">
                      <s:hidden  id="show.enrollementList.grade" name="grade.id" value=""/>
                       <p><s:text name="enrollment.upload.file" />:</p>
                       <s:file id="idFile" name="fileStudents" theme="simple" value="" onchange="validateFile(this.value)" />
                      <br>
                      <br>
                      <input type="button" value="<s:text name="message.input.submit" />" class="create-question" onclick="submitList()"/>  
                </s:form>
                 <iframe id="studentsUp" name="iframeStudents" src="" style="width:0;height:0;border:0px solid #fff;" >
                 </iframe>
            </div>
        </div>
    </div>
</div>


<div id="showResultEnrollment" style="display:none;">
    <div id="col-2-home">
        <div class="container-grades">
            <div class="content-grades">
                <br class="clear" />
                <div class="steps-box">
                    <div>
                        <div>
                            <span id="step"><s:text name="course.show.step" /></span>
                            <span id="" >1</span>
                            <span id="current">2</span>
                        </div>
                    </div>
                    <div class="tip-step"><s:text name="Os alunos foram cadastrados no sistema, e foi enviado para o email de cada um, o login e a senha de acesso ao sistema. 
                    Conforme a disponibilidade de vaga na turma foram realizadas as matriculas. Abaixo segue a descrição das matriculas realizadas."/></div>
                </div>
                <div id="listResultStudent">
                </div>    

            </div>
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
                <br class="clear" />
                <div id="reportData"></div> 
                <div id="pieCanvas" style="overflow: auto; position:relative;height:330px;width:570px; display:none"></div>
                <div class="actions-box">
                    <h2><s:text name="grade.show.actions" /></h2>
                    <div class="edit-tools">
                        <ul>
                            <%--
                            <li><a class="icon-new" href="#" onclick="showEntryStudent(document.getElementById('student.grade.id').value, document.getElementById('student.grade.course.id').value);"><s:text name= "student.grade.new.student" /></a></li>
                            <li><a class="icon-delete" href="#" onclick="deleteStudent(document.getElementById('student.grade.id').value);"><s:text name= "student.grade.delete.student" /></a></li>
                            --%>
                            <li><a class="icon-newsFlash" href="javascript:closeAllMessages(); showNewsflash();"><s:text name= "student.send.newsflash" /></a></li>
                            <li><a class="icon-message" href="javascript:closeAllMessages(); showMessage();"><s:text name= "student.send.message" /></a></li>
                            <li><a class="icon-activeEnrollment" href="javascript:closeAllMessages(); setEnrollments($('student.grade.id').value, <%= br.ufc.ivela.commons.Constants.ENROLLMENT_ACTIVE%>);"><s:text name= "student.active.enrollment" /></a></li>
                            <li><a class="icon-pendingEnrollment" href="javascript:closeAllMessages(); setEnrollments($('student.grade.id').value, <%= br.ufc.ivela.commons.Constants.ENROLLMENT_PENDING%>);"><s:text name= "student.pending.enrollment" /></a></li>
                            <li><a class="icon-suspendEnrollment" href="javascript:closeAllMessages(); setEnrollments($('student.grade.id').value, <%= br.ufc.ivela.commons.Constants.ENROLLMENT_SUSPENDED%>);"><s:text name= "student.suspend.enrollment" /></a></li>
                            <li><a class="icon-scoreCard" href="javascript:closeAllMessages();showTranscript($('student.grade.id').value);"><s:text name= "student.show.scorecard" /></a></li>
                            <li><a class="icon-report" href="javascript:closeAllMessages();showReport($('student.grade.id').value,$('student.grade.course.id').value);"><s:text name= "student.show.report" /></a></li>
                            <li><a class="icon-enroll" href="javascript:closeAllMessages();showEnrollmentList();"><s:text name= "student.show.enrollementListStudent" /></a></li>
                            <br class="clear" />
                        </ul>
                    </div>
                    <div class="edit-box" id="student.grade.students">
                    </div>
                    <div id="student.message" style="display:none;" class="course-message">
                        <h4><s:text name ="course.send.message" /></h4><br />
                        <s:text name="message.title" /><br /> <input type="text" name="input.student.message.title" id="input.student.message.title" size="55" maxlength="50" /><br />
                        <s:text name="message.type"/><br /><textarea name="input.student.message.description" id="input.student.message.description" cols="62" rows="4"onKeyDown="textCounter(this,$('input.student.message.description.len'),250);" onKeyUp="textCounter(this,$('input.student.message.description.len'),250);"></textarea><br />
                        <s:text name="message.remaining" /><input readonly type=text id="input.student.message.description.len" name="input.student.message.description.len" size=3 maxlength=3 value="250"/><br/>                        
                        <input type="button" value="<s:text name="systemUser.input.btnSave" />" onclick="sendMessageStudent($('input.student.message.title'), $('input.student.message.description'));" />
                    </div>
                    <div id="student.newsflash" style="display:none;" class="course-message">
                        <h4><s:text name="course.send.newsflash"/></h4><br />
                        <s:text name="message.type"/><br /><textarea name="input.student.newsflash.message" id="input.student.newsflash.message" cols="62" rows="4"onKeyDown="textCounter(this,$('input.student.newsflash.message.len'),250);" onKeyUp="textCounter(this,$('input.student.newsflash.message.len'),250);"></textarea><br />
                        <s:text name="message.remaining" /><input readonly type=text id="input.student.newsflash.message.len" name="input.student.newsflash.message.len" size=3 maxlength=3 value="250"/><br/>                        
                        <input type="button" value="<s:text name="systemUser.input.btnSave" />" onclick="sendNewsFlashStudent($('input.student.newsflash.message'));" />
                    </div>

                </div>
            </div>
        </div>
    </div>       
</div>

<!-- end col-2-home -->
<br class="clear" />

<!-- end content -->
<br class="clear" />
