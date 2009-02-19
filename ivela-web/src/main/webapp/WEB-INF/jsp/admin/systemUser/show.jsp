<%-- 
    Document   : show systemUser
    Created on : Sep 30, 2008, 2:06:16 PM
    Author     : marcus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<head>
    <link href="../css/accordion_people.css"  rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../js/admin/people.js"></script>
    <script type="text/javascript" src="../js/admin/tools.js"></script>
    <script type="text/javascript">
        var actualAuth = <sec:authentication property="principal.authentication.id"/>;
    </script>
</head>

<s:actionerror />

<div id="breadcrumb">
    <p><s:text name="breadcrumb.youAreHere"/></p>
    <ul>
        <li><a href="home.action"><s:text name="admin.home"/></a></li>
        <li class="current"><s:text name="admin.people" /></li>
    </ul>
</div>

<div id="col-1-home">    
    <div id="category_container" >
        <sec:authorize ifAnyGranted="ROLE_ADMIN">
            <h3 class="category_toggle"> <s:text name="systemUser.type.admin"/> (<s:property  value="listAdmin.size()"/>)</h3>
            <div class="category_content" id="1">
                
                <s:if test="(listAdmin != null && listAdmin.size() > 0)">                                   
                    <input type="button" name="CheckAllAdmins" value="Marcar Todos" class="btn-check-all"
                           onClick="checkAllFieldsByName('adminCheck', '<s:property value="id" />','admins')">
                    <input type="button" name="UnCheckAllAdmins" value="Desmarcar Todos" class="btn-uncheck-all"
                           onClick="unCheckAllFieldsByName('adminCheck','adminData')">
                </s:if>
                
                <ul class="list-students">
                    <s:iterator value="listAdmin" status="stat">                
                        <li title="<s:property value="username" />" id="li.admin.id_<s:property value="#stat.index"/>" onMouseOver="mouseOverPerson('admin.id_<s:property value="#stat.index"/>');" onMouseOut="mouseOutPerson('admin.id_<s:property value="#stat.index"/>');">
                            <img src="../images/icon/icon-person.gif" /><br />
                            <s:if test="username.length() > 8"><s:property value="username.substring(0,8)+ \"...\"" /></s:if><s:else><s:property value="username" /></s:else><br />
                            <input type="checkbox" name="adminCheck" id="admin.id_<s:property value="#stat.index"/>" value="<s:property value="id"/>" onclick="updatePerson(this,<s:property value="id"/>);"/>
                        </li>                
                    </s:iterator>
                </ul>
                
                <!-- Início : Divs com características do administrador -->
                <s:iterator value="listAdmin" status="stat">
                    <div id="div.admin.id_<s:property value="#stat.index" />" style="display: none;">
                        <div id="div.inner.admin.id_<s:property value="#stat.index" />" class="member-info" style="display: none;">
                            <img class="picture" src="../images/foto_profile.jpg" width="80" height="80"/>
                            <div>
                                <div>
                                    <p><s:text name="systemUser.input.username"/>: <span><s:property value="username" /></span></p>
                                    <p><s:text name="systemUser.input.email"/>: <span><s:property value="email" /></span></p>
                                    <p><s:text name="systemUser.input.memberSince"/>: <span><s:property value="createdAt" /></span></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </s:iterator>
                <s:iterator value="listAdmin" status="stat">                             
                    <div id="div.multiple.admin.id_<s:property value="#stat.index" />" style="display: none;">
                        <div id="div.multiple.inner.admin.id_<s:property value="#stat.index" />" class="member-info" style="display: none; float: left;">
                            <img class="picture" src="../images/foto_profile.jpg" width="80" height="80" /><br /><span><s:property value="username" /></span>
                        </div>
                    </div>
                </s:iterator>
                <br />
                <!-- Fim : Divs com caracterísitcas do administrador -->

                <br class="clear"/>
            </div>
        </sec:authorize>
        
        <sec:authorize ifAnyGranted="ROLE_ADMIN, ROLE_COORD">
            <h3 class="category_toggle"> <s:text name="systemUser.type.coordinator"/> (<s:property  value="listCoord.size()"/>)</h3>
            <div class="category_content" id="2">
                
                <s:if test="(listCoord != null && listCoord.size() > 0)">                                   
                    <input type="button" name="CheckAllCoords" value="Marcar Todos" class="btn-check-all"
                           onClick="checkAllFieldsByName('coordCheck', '<s:property value="id" />','coords')">
                    <input type="button" name="UnCheckAllCoords" value="Desmarcar Todos" class="btn-uncheck-all"
                           onClick="unCheckAllFieldsByName('coordCheck','coordData')">
                </s:if>
                
                <ul class="list-students">
                    <s:iterator value="listCoord" status="stat">                
                        <li title="<s:property value="username" />" id="li.coord.id_<s:property value="#stat.index"/>" onMouseOver="mouseOverPerson('coord.id_<s:property value="#stat.index"/>');" onMouseOut="mouseOutPerson('coord.id_<s:property value="#stat.index"/>');">
                            <img src="../images/icon/icon-person.gif" /><br />
                            <s:if test="username.length() > 8"><s:property value="username.substring(0,8)+ \"...\"" /></s:if><s:else><s:property value="username" /></s:else><br />
                            <input type="checkbox"  name="coordCheck" id="coord.id_<s:property value="#stat.index"/>" value="<s:property value="id"/>" onclick="updatePerson(this,<s:property value="id"/>);"/>
                        </li>                
                    </s:iterator>
                </ul>
                
                <!-- Início : Divs com características do coordenador -->
                <s:iterator value="listCoord" status="stat">
                    <div id="div.coord.id_<s:property value="#stat.index" />" style="display: none;">
                        <div id="div.inner.coord.id_<s:property value="#stat.index" />" class="member-info" style="display: none;">
                            <img class="picture" src="../images/foto_profile.jpg" width="80" height="80" />
                            <div>
                                <div>
                                    <p><s:text name="systemUser.input.username"/>: <span><s:property value="username" /></span></p>
                                    <p><s:text name="systemUser.input.email"/>: <span><s:property value="email" /></span></p>
                                    <p><s:text name="systemUser.input.memberSince"/>: <span><s:property value="createdAt" /></span></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </s:iterator>
                <s:iterator value="listCoord" status="stat">                             
                    <div id="div.multiple.coord.id_<s:property value="#stat.index" />" style="display: none;">
                        <div id="div.multiple.inner.coord.id_<s:property value="#stat.index" />" class="member-info" style="display: none; float: left;">
                            <img class="picture" src="../images/foto_profile.jpg" width="80" height="80" /><br /><span><s:property value="username" /></span>
                        </div>
                    </div>
                </s:iterator>
                <br />
                <!-- Fim : Divs com caracterísitcas do coordenador -->            
                <br class="clear"/>
            </div>
        </sec:authorize>
        
        <sec:authorize ifAnyGranted="ROLE_ADMIN, ROLE_COORD, ROLE_PROFESSOR">
            <h3 class="category_toggle"> <s:text name="systemUser.type.professor"/> (<s:property  value="listProf.size()"/>)</h3>
            <div class="category_content" id="6">
                
                <s:if test="(listProf != null && listProf.size() > 0)">                                   
                    <input type="button" name="CheckAllProfs" value="Marcar Todos" class="btn-check-all"
                           onClick="checkAllFieldsByName('profCheck', '<s:property value="id" />','profs')">
                    <input type="button" name="UnCheckAllProfs" value="Desmarcar Todos" class="btn-uncheck-all"
                           onClick="unCheckAllFieldsByName('profCheck','profData')">
                </s:if>
                
                <ul class="list-students">
                    <s:iterator value="listProf" status="stat">                
                        <li title="<s:property value="username" />" id="li.prof.id_<s:property value="#stat.index"/>" onMouseOver="mouseOverPerson('prof.id_<s:property value="#stat.index"/>');" onMouseOut="mouseOutPerson('prof.id_<s:property value="#stat.index"/>');">
                            <img src="../images/icon/icon-person.gif" /><br />
                            <s:if test="username.length() > 8"><s:property value="username.substring(0,8)+ \"...\"" /></s:if><s:else><s:property value="username" /></s:else><br />
                            <input type="checkbox"  name="profCheck" id="prof.id_<s:property value="#stat.index"/>" value="<s:property value="id"/>" onclick="updatePerson(this,<s:property value="id"/>);"/>
                        </li>                
                    </s:iterator>
                </ul>
                
                <!-- Início : Divs com características do professor -->
                <s:iterator value="listProf" status="stat">
                    <div id="div.prof.id_<s:property value="#stat.index" />" style="display: none;">
                        <div id="div.inner.prof.id_<s:property value="#stat.index" />" class="member-info" style="display: none;">
                            <img class="picture" src="../images/foto_profile.jpg" width="80" height="80" />
                            <div>
                                <div>
                                    <p><s:text name="systemUser.input.username"/>: <span><s:property value="username" /></span></p>
                                    <p><s:text name="systemUser.input.email"/>: <span><s:property value="email" /></span></p>
                                    <p><s:text name="systemUser.input.memberSince"/>: <span><s:property value="createdAt" /></span></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </s:iterator>
                <s:iterator value="listProf" status="stat">                             
                    <div id="div.multiple.prof.id_<s:property value="#stat.index" />" style="display: none;">
                        <div id="div.multiple.inner.prof.id_<s:property value="#stat.index" />" class="member-info" style="display: none; float: left;">
                            <img class="picture" src="../images/foto_profile.jpg" width="80" height="80" /><br /><span><s:property value="username" /></span>
                        </div>
                    </div>
                </s:iterator>
                <br />
                <!-- Fim : Divs com caracterísitcas do professor -->

            
                <br class="clear"/>
            </div>
        </sec:authorize>
        
        <sec:authorize ifAnyGranted="ROLE_ADMIN, ROLE_COORD, ROLE_PROFESSOR, ROLE_TUTOR">
            <h3 class="category_toggle"> <s:text name="systemUser.type.tutor"/> (<s:property  value="listTutor.size()"/>)</h3>
            <div class="category_content" id="3">
                
                <s:if test="(listTutor != null && listTutor.size() > 0)">                                   
                    <input type="button" name="CheckAllTutors" value="Marcar Todos" class="btn-check-all"
                           onClick="checkAllFieldsByName('tutorCheck', '<s:property value="id" />','tutors')">
                    <input type="button" name="UnCheckAllTutors" value="Desmarcar Todos" class="btn-uncheck-all"
                           onClick="unCheckAllFieldsByName('tutorCheck','tutorData')">
                </s:if>
                
                <ul class="list-students">
                    <s:iterator value="listTutor" status="stat">                
                        <li title="<s:property value="username" />" id="li.tutor.id_<s:property value="#stat.index"/>" onMouseOver="mouseOverPerson('tutor.id_<s:property value="#stat.index"/>');" onMouseOut="mouseOutPerson('tutor.id_<s:property value="#stat.index"/>');">
                            <img src="../images/icon/icon-person.gif" /><br />
                            <s:if test="username.length() > 8"><s:property value="username.substring(0,8)+ \"...\"" /></s:if><s:else><s:property value="username" /></s:else><br />
                            <input type="checkbox"  name="tutorCheck" id="tutor.id_<s:property value="#stat.index"/>" value="<s:property value="id"/>" onclick="updatePerson(this,<s:property value="id"/>);"/>
                        </li>                
                    </s:iterator>
                </ul>
                
                <!-- Início : Divs com características do tutor -->
                <s:iterator value="listTutor" status="stat">
                    <div id="div.tutor.id_<s:property value="#stat.index" />" style="display: none;">
                        <div id="div.inner.tutor.id_<s:property value="#stat.index" />" class="member-info" style="display: none;">
                            <img class="picture" src="../images/foto_profile.jpg" width="80" height="80" />
                            <div>
                                <div>
                                    <p><s:text name="systemUser.input.username"/>: <span><s:property value="username" /></span></p>
                                    <p><s:text name="systemUser.input.email"/>: <span><s:property value="email" /></span></p>
                                    <p><s:text name="systemUser.input.memberSince"/>: <span><s:property value="createdAt" /></span></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </s:iterator>
                <s:iterator value="listTutor" status="stat">                             
                    <div id="div.multiple.tutor.id_<s:property value="#stat.index" />" style="display: none;">
                        <div id="div.multiple.inner.tutor.id_<s:property value="#stat.index" />" class="member-info" style="display: none; float: left;">
                            <img class="picture" src="../images/foto_profile.jpg" width="80" height="80" /><br /><span><s:property value="username" /></span>
                        </div>
                    </div>
                </s:iterator>
                <br />
                <!-- Fim : Divs com caracterísitcas do tutor -->
            
                <br class="clear"/>
            </div>
        </sec:authorize>
        
        <h3 class="category_toggle"> <s:text name="systemUser.type.student"/> (<s:property  value="listUser.size()"/>)</h3>
        <div class="category_content" id="5">
            
            <s:if test="(listUser != null && listUser.size() > 0)">                                   
                <input type="button" name="CheckAllStudents" value="Marcar Todos" class="btn-check-all"
                       onClick="checkAllFieldsByName('studentCheck', '<s:property value="id" />','students')">
                <input type="button" name="UnCheckAllStudents" value="Desmarcar Todos" class="btn-uncheck-all"
                       onClick="unCheckAllFieldsByName('studentCheck','studentData')">
            </s:if>
            
            <ul class="list-students">
                <s:iterator value="listUser" status="stat">                
                    <li title="<s:property value="username" />" id="li.student.id_<s:property value="#stat.index"/>" onMouseOver="mouseOverPerson('student.id_<s:property value="#stat.index"/>');" onMouseOut="mouseOutPerson('student.id_<s:property value="#stat.index"/>');">
                        <img src="../images/icon/icon-person.gif" /><br />
                        <s:if test="username.length() > 8"><s:property value="username.substring(0,8)+ \"...\"" /></s:if><s:else><s:property value="username" /></s:else><br />
                        <input type="checkbox"  name="studentCheck" id="student.id_<s:property value="#stat.index"/>" value="<s:property value="id"/>" onclick="updatePerson(this,<s:property value="id"/>);"/>
                    </li>                
                </s:iterator>
            </ul>
            
            <!-- Início : Divs com características do aluno -->
            <s:iterator value="listUser" status="stat">
                <div id="div.student.id_<s:property value="#stat.index" />" style="display: none;">
                    <div id="div.inner.student.id_<s:property value="#stat.index" />" class="member-info" style="display: none;">
                        <img class="picture" src="../images/foto_profile.jpg" width="80" height="80"/>
                        <div>
                            <div>
                                <p><s:text name="systemUser.input.username"/>: <span><s:property value="username" /></span></p>
                                <p><s:text name="systemUser.input.email"/>: <span><s:property value="email" /></span></p>
                                <p><s:text name="systemUser.input.memberSince"/>: <span><s:property value="createdAt" /></span></p>
                            </div>
                        </div>
                    </div>
                </div>
            </s:iterator>
            <s:iterator value="listUser" status="stat">                             
                <div id="div.multiple.student.id_<s:property value="#stat.index" />" style="display: none;">
                    <div id="div.multiple.inner.student.id_<s:property value="#stat.index" />" class="member-info" style="display: none; float: left;">
                        <img class="picture" src="../images/foto_profile.jpg" width="80" height="80" /><br /><span><s:property value="username" /></span>
                    </div>
                </div>
            </s:iterator>
            <br />
            <!-- Fim : Divs com caracterísitcas do aluno -->
            
            <br class="clear"/>
        </div>
    </div>
</div>
<!-- end col-1-home -->

<div id="adminUser" style="display:none;">
    <div id="col-2-home">
        <div class="container-courses">
            <div class="content-courses">
                <h1><s:text name="systemUser.type.admin"/></h1>
                <br class="clear" />
                <p></p>
                
                <div id="adminData"></div>
                <div class="actions-box" id="action.add.admin">
                    <h2><s:text name="course.show.actions"/>:</h2>
                    <div class="edit-tools">
                        <ul>
                            <li><a class="icon-new-person" href="javascript:showDiv('admin.add');"><s:text name="systemUser.show.admin" /></a></li>
                            <br class="clear" />
                        </ul>                        
                    </div>
                    <div id="admin.add" style="display:none;" class="course-message">
                        <br /><s:text name="systemUser.show.add" /><br /> <br />
                        <s:text name="systemUser.show.name" />
                        <select id="input.admin.add.user">
                            <s:iterator value="systemUsers" status="stat">                                
                                <s:if test="!listAdmin.contains(systemUsers[#stat.index])">
                                    <option value="<s:property value="id"/>"> <s:property value="username"/> </option>
                                </s:if>
                            </s:iterator>
                        </select>
                        <br /><br />
                        <input type="button" value="<s:text name="systemUser.input.btnSave" />" onclick="add($('input.admin.add.user').value)" />
                    </div>
                </div>   
                <!-- end actions-box -->
                
                <div class="actions-box" id="actions.all.admin" style="display:none">
                    <h2><s:text name="course.show.actions"/>:</h2>
                    <div class="edit-tools">
                        <ul>
                            <li><a class="icon-edit" href="javascript:showDiv('multiple.message.admin');"><s:text name="course.send.message"/></a></li>
                            <li><a class="icon-message"  href="javascript:showDiv('multiple.newsflash.admin');"><s:text name="course.send.newsflash" /></a></li>
                            <li><a class="icon-newsFlash" href="javascript:showDiv('multiple.change.admin');"><s:text name="course.show.perfil" /></a></li>
                            <li><a class="icon-newsFlash" href="javascript:showDiv('multiple.delete.admin');"><s:text name="course.show.deleteAdmin" /></a></li>
                            <br class="clear" />
                        </ul>                        
                    </div>
                    <div id="multiple.message.admin" style="display:none;" class="course-message">
                        <h4><s:text name ="student.send.message" /></h4><br />
                        <s:text name="message.title" /><br /> <input type="text" id="admin.input.multiple.message.title" size="55" maxlength="50" /><br />
                        <s:text name="message.type"/><br /><textarea cols="62" rows="4" onKeyDown="textCounter(this,$('admin.input.multiple.message.description.len'),250);" onKeyUp="textCounter(this,$('admin.input.multiple.message.description.len'),250);" id="admin.input.multiple.message.description"></textarea><br />
                        <s:text name="message.remaining" /><input readonly type=text id="admin.input.multiple.message.description.len" name="admin.input.multiple.message.description.len" size=3 maxlength=3 value="250"/><br/>
                        <input type="button" value="Submit" onclick="sendMessage($('admin.input.multiple.message.title'), $('admin.input.multiple.message.description'));" />
                    </div>
                    <div id="multiple.newsflash.admin" style="display:none;" class="course-message">
                        <h4><s:text name="student.send.newsflash"/></h4><br />
                        <s:text name="message.type" /><br /> <textarea cols="62" rows="4" onKeyDown="textCounter(this,$('admin.input.multiple.newsflash.message.len'),250);" onKeyUp="textCounter(this,$('admin.input.multiple.newsflash.message.len'),250);" id="admin.input.multiple.newsflash.message"></textarea><br />
                        <s:text name="message.remaining" /><input readonly type=text id="admin.input.multiple.newsflash.message.len" name="admin.input.multiple.newsflash.message.len" size=3 maxlength=3 value="250"/><br/>
                        <input type="button" value="Submit" onclick="sendNewsFlash($('admin.input.multiple.newsflash.message'));" />
                    </div>
                    <div id="multiple.change.admin" style="display:none;">
                        <br /><s:text name="systemUser.show.changeType"/><br /> <br />
                        <s:text name="systemUser.show.type" />
                        <select id="admin.multiple.change.types">
                        </select>
                        <br />
                        <input type="button" value="<s:text name="systemUser.input.btnSave" />" onclick="change($('admin.multiple.change.types').value)" />
                    </div>
                    <div id="multiple.delete.admin" style="display:none;">
                        <br /><s:text name="systemUser.show.delete"/><br /> <br />                        
                        <input type="button" value="<s:text name="systemUser.input.deleteYes" />" onclick="deleteStudent(true)" />
                        <input type="button" value="<s:text name="systemUser.input.deleteNo" />" onclick="deleteStudent(false)" />
                    </div>
                    
                </div>
                <!-- end actions-box -->
                
            </div>
            <!-- End content-courses -->
        </div>    
    </div>
</div>

<div id="coordinatorUser" style="display:none;">
    <div id="col-2-home">
        <div class="container-courses">
            <div class="content-courses">
                <h1><s:text name="systemUser.type.coordinator"/></h1>
                <br class="clear" />
                <p></p>
                
                <div id="coordData"></div>
                <div class="actions-box" id="action.add.coord">
                    <h2><s:text name="course.show.actions"/>:</h2>
                    <div class="edit-tools">
                        <ul>
                            <li><a class="icon-new-person"  href="javascript:showDiv('coord.add')"><s:text name="systemUser.show.coordenator"/></a></li>
                            <br class="clear" />
                        </ul>                        
                    </div>
                    <div id="coord.add" style="display:none;" class="course-message">
                        <br /><s:text name="systemUser.show.add" /><br /> <br />
                        <s:text name="systemUser.show.name" />
                        <select id="input.coord.add.user">
                            <s:iterator value="systemUsers" status="stat">                                
                                <s:if test="!listCoord.contains(systemUsers[#stat.index]) && authentication.id != 1">
                                    <option value="<s:property value="id"/>"> <s:property value="username"/> </option>
                                </s:if>
                            </s:iterator>
                        </select>
                        <br /><br />
                        <input type="button" value="<s:text name="systemUser.input.btnSave" />" onclick="add($('input.coord.add.user').value)" />
                    </div>
                </div>
                <!-- end actions-box -->
                
                <div class="actions-box" id="actions.all.coord" style="display:none">
                    <h2><s:text name="course.show.actions"/>:</h2>
                    <div class="edit-tools">
                        <ul>
                            <li><a class="icon-edit" href="javascript:showDiv('multiple.message.coord');"><s:text name="course.send.message"/></a></li>
                            <li><a class="icon-message"  href="javascript:showDiv('multiple.newsflash.coord');"><s:text name="course.send.newsflash" /></a></li>
                            <li><a class="icon-newsFlash" href="javascript:showDiv('multiple.change.coord');"><s:text name="course.show.perfil" /></a></li>
                            <li><a class="icon-newsFlash" href="javascript:showDiv('multiple.delete.coord');"><s:text name="course.show.deleteCoor" /></a></li>
                            <br class="clear" />
                        </ul>                        
                    </div>
                    <div id="multiple.message.coord" style="display:none;" class="course-message">
                        <h4><s:text name ="student.send.message" /></h4><br />
                        <s:text name="message.title" /><br /> <input size="55" maxlength="50" type="text" id="coord.input.multiple.message.title" /><br />
                        <s:text name="message.type"/><br /> <textarea cols="62" rows="4" onKeyDown="textCounter(this,$('coord.input.multiple.message.description.len'),250);" onKeyUp="textCounter(this,$('coord.input.multiple.message.description.len'),250);" id="coord.input.multiple.message.description"></textarea><br />
                        <s:text name="message.remaining" /><input readonly type=text id="coord.input.multiple.message.description.len" name="coord.input.multiple.message.description.len" size=3 maxlength=3 value="250"/><br/>
                        <input type="button" value="Submit" onclick="sendMessage($('coord.input.multiple.message.title'), $('coord.input.multiple.message.description'));" />
                    </div>
                    <div id="multiple.newsflash.coord" style="display:none;">
                        <h4><s:text name="student.send.newsflash"/></h4><br />
                        <s:text name="message.type" /><br /> <textarea cols="62" rows="4" onKeyDown="textCounter(this,$('coord.input.multiple.newsflash.message.len'),250);" onKeyUp="textCounter(this,$('coord.input.multiple.newsflash.message.len'),250);" id="coord.input.multiple.newsflash.message"></textarea><br />
                        <s:text name="message.remaining" /><input readonly type=text id="coord.input.multiple.newsflash.message.len" name="coord.input.multiple.newsflash.message.len" size=3 maxlength=3 value="250"/><br/>
                        <input type="button" value="Submit" onclick="sendNewsFlash($('coord.input.multiple.newsflash.message'));" />
                    </div>
                    <div id="multiple.change.coord" style="display:none;">
                        <br /><s:text name="systemUser.show.changeType"/><br /> <br />
                        <s:text name="systemUser.show.type" /> 
                        <select id="coord.multiple.change.types">
                        </select>
                        <br />
                        <input type="button" value="<s:text name="systemUser.input.btnSave" />" onclick="change($('coord.multiple.change.types').value)" />
                    </div>
                    <div id="multiple.delete.coord" style="display:none;">
                        <br /><s:text name="systemUser.show.delete"/><br /> <br />                        
                        <input type="button" value="<s:text name="systemUser.input.deleteYes" />" onclick="deleteStudent(true)" />
                        <input type="button" value="<s:text name="systemUser.input.deleteNo" />" onclick="deleteStudent(false)" />
                    </div>
                </div>
                <!-- end actions-box -->

                
            </div>
            <!-- End content-courses -->
        </div>    
    </div>
</div>

<div id="professorUser" style="display:none;">
    <div id="col-2-home">
        <div class="container-courses">
            <div class="content-courses">
                <h1><s:text name="systemUser.type.professor"/></h1>
                <br class="clear" />
                <p></p>
                
                <div id="profData"></div>
                <div class="actions-box" id="action.add.prof">
                    <h2><s:text name="course.show.actions"/>:</h2>
                    <div class="edit-tools">
                        <ul>
                            <li><a class="icon-new-person"  href="javascript:showDiv('prof.add')"><s:text name="systemUser.show.professor" /></a></li>
                            <br class="clear" />
                        </ul>                        
                    </div>
                    <div id="prof.add" style="display:none;" class="course-message">
                        <br /><s:text name="systemUser.show.add" /><br /> <br />
                        <s:text name="systemUser.show.name" />
                        <select id="input.prof.add.user">
                            <s:iterator value="systemUsers" status="stat">                                
                                <s:if test="!listProf.contains(systemUsers[#stat.index]) && authentication.id != 1 && authentication.id != 2">
                                    <option value="<s:property value="id"/>"> <s:property value="username"/> </option>
                                </s:if>
                            </s:iterator>
                        </select>
                        <br /><br />
                        <input type="button" value="<s:text name="systemUser.input.btnSave" />" onclick="add($('input.prof.add.user').value)" />
                    </div>
                </div>
                <!-- end actions-box -->
                
                
                <div class="actions-box" id="actions.all.prof" style="display:none">
                    <h2><s:text name="course.show.actions"/>:</h2>
                    <div class="edit-tools">
                        <ul>
                            <li><a class="icon-edit" href="javascript:showDiv('multiple.message.prof');"><s:text name="course.send.message"/></a></li>
                            <li><a class="icon-message"  href="javascript:showDiv('multiple.newsflash.prof');"><s:text name="course.send.newsflash" /></a></li>
                            <li><a class="icon-newsFlash" href="javascript:showDiv('multiple.change.prof');"><s:text name="course.show.perfil" /></a></li>
                            <li><a class="icon-newsFlash" href="javascript:showDiv('multiple.delete.prof');"><s:text name="course.show.deleteProf" /></a></li>
                            <br class="clear" />
                        </ul>                        
                    </div>
                    <div id="multiple.message.prof" style="display:none;">
                        <h4><s:text name ="student.send.message" /></h4><br />
                        <s:text name="message.title" /><br /> <input type="text" id="prof.input.multiple.message.title" size="55" maxlength="50" /><br />
                        <s:text name="message.type"/><br /> <textarea cols="62" rows="4" onKeyDown="textCounter(this,$('prof.input.multiple.message.description.len'),250);" onKeyUp="textCounter(this,$('prof.input.multiple.message.description.len'),250);" id="prof.input.multiple.message.description"></textarea><br />
                        <s:text name="message.remaining" /><input readonly type=text id="prof.input.multiple.message.description.len" name="prof.input.multiple.message.description.len" size=3 maxlength=3 value="250"/><br/>
                        <input type="button" value="Submit" onclick="sendMessage($('prof.input.multiple.message.title'), $('prof.input.multiple.message.description'));" />
                    </div>
                    <div id="multiple.newsflash.prof" style="display:none;">
                        <h4><s:text name="student.send.newsflash"/></h4><br />
                        <s:text name="message.type" /><br /> <textarea cols="62" rows="4" onKeyDown="textCounter(this,$('prof.input.multiple.newsflash.message.len'),250);" onKeyUp="textCounter(this,$('prof.input.multiple.newsflash.message.len'),250);" id="prof.input.multiple.newsflash.message"></textarea><br />
                        <s:text name="message.remaining" /><input readonly type=text id="prof.input.multiple.newsflash.message.len" name="prof.input.multiple.newsflash.message.len" size=3 maxlength=3 value="250"/><br/>
                        <input type="button" value="Submit" onclick="sendNewsFlash($('prof.input.multiple.newsflash.message'));" />
                    </div>
                    <div id="multiple.change.prof" style="display:none;">
                        <br /><s:text name="systemUser.show.changeType"/><br /> <br />
                        <s:text name="systemUser.show.type" />
                        <select id="prof.multiple.change.types">
                        </select>
                        <br />
                        <input type="button" value="<s:text name="systemUser.input.btnSave" />" onclick="change($('prof.multiple.change.types').value)" />
                    </div>
                    <div id="multiple.delete.prof" style="display:none;">
                        <br /><s:text name="systemUser.show.delete"/><br /> <br />                        
                        <input type="button" value="<s:text name="systemUser.input.deleteYes" />" onclick="deleteStudent(true)" />
                        <input type="button" value="<s:text name="systemUser.input.deleteNo" />" onclick="deleteStudent(false)" />
                    </div>
                </div>
                <!-- end actions-box -->
                
            </div>
            <!-- End content-courses -->
        </div>    
    </div>
</div>

<div id="tutorUser" style="display:none;">
    <div id="col-2-home">
        <div class="container-courses">
            <div class="content-courses">
                <h1><s:text name="systemUser.type.tutor"/></h1>
                <br class="clear" />
                <p></p>
                
                <div id="tutorData"></div>
                <div class="actions-box" id="action.add.tutor">
                    <h2><s:text name="course.show.actions"/>:</h2>
                    <div class="edit-tools">
                        <ul>
                            <li><a class="icon-new-person"  href="javascript:showDiv('tutor.add')"><s:text name="systemUser.show.tutor"/></a></li>
                            <br class="clear" />
                        </ul>                        
                    </div>
                    <div id="tutor.add" style="display:none;" class="course-message">
                        <br /><s:text name="systemUser.show.add" /><br /> <br />
                        <s:text name="systemUser.show.name" />
                        <select id="input.tutor.add.user">
                            <s:iterator value="systemUsers" status="stat">                                
                                <s:if test="!listTutor.contains(systemUsers[#stat.index]) && authentication.id != 1 && authentication.id != 2 && authentication.id != 6">
                                    <option value="<s:property value="id"/>"> <s:property value="username"/> </option>
                                </s:if>
                            </s:iterator>
                        </select>
                        <br /><br />
                        <input type="button" value="<s:text name="systemUser.input.btnSave" />" onclick="add($('input.tutor.add.user').value)" />
                    </div>
                </div>
                <!-- end actions-user -->
                
                <div class="actions-box" id="actions.all.tutor" style="display:none">
                    <h2><s:text name="course.show.actions"/>:</h2>
                    <div class="edit-tools">
                        <ul>
                            <li><a class="icon-edit" href="javascript:showDiv('multiple.message.tutor');"><s:text name="course.send.message"/></a></li>
                            <li><a class="icon-message"  href="javascript:showDiv('multiple.newsflash.tutor');"><s:text name="course.send.newsflash" /></a></li>
                            <li><a class="icon-newsFlash" href="javascript:showDiv('multiple.change.tutor');"><s:text name="course.show.perfil" /></a></li>
                            <li><a class="icon-newsFlash" href="javascript:showDiv('multiple.delete.tutor');"><s:text name="course.show.deleteTutor" /></a></li>
                            <br class="clear" />
                        </ul>                        
                    </div>
                    <div id="multiple.message.tutor" style="display:none;">
                        <h4><s:text name ="student.send.message" /></h4><br />
                        <s:text name="message.title" /><br /><input type="text" id="tutor.input.multiple.message.title" size="55" maxlength="50" /><br />
                        <s:text name="message.type"/><br /> <textarea cols="62" rows="4" onKeyDown="textCounter(this,$('tutor.input.multiple.message.description.len'),250);" onKeyUp="textCounter(this,$('tutor.input.multiple.message.description.len'),250);" id="tutor.input.multiple.message.description"></textarea><br />
                        <s:text name="message.remaining" /><input readonly type=text id="tutor.input.multiple.message.description.len" name="tutor.input.multiple.message.description.len" size=3 maxlength=3 value="250"/><br/>
                        <input type="button" value="Submit" onclick="sendMessage($('tutor.input.multiple.message.title'), $('tutor.input.multiple.message.description'));" />
                    </div>
                    <div id="multiple.newsflash.tutor" style="display:none;">
                        <h4><s:text name="student.send.newsflash"/></h4><br />
                        <s:text name="message.type" /><br /> <textarea cols="62" rows="4" onKeyDown="textCounter(this,$('tutor.input.multiple.newsflash.message.len'),250);" onKeyUp="textCounter(this,$('tutor.input.multiple.newsflash.message.len'),250);" id="tutor.input.multiple.newsflash.message"></textarea><br />
                        <s:text name="message.remaining" /><input readonly type=text id="tutor.input.multiple.newsflash.message.len" name="tutor.input.multiple.newsflash.message.len" size=3 maxlength=3 value="250"/><br/>
                        <input type="button" value="Submit" onclick="sendNewsFlash($('tutor.input.multiple.newsflash.message'));" />
                    </div>
                    <div id="multiple.change.tutor" style="display:none;">
                        <br /><s:text name="systemUser.show.changeType"/><br /> <br />
                        <s:text name="systemUser.show.type" /> 
                        <select id="tutor.multiple.change.types">
                        </select>
                        <br />
                        <input type="button" value="<s:text name="systemUser.input.btnSave" />" onclick="change($('tutor.multiple.change.types').value)" />
                    </div>
                    <div id="multiple.delete.tutor" style="display:none;">
                        <br /><s:text name="systemUser.show.delete"/><br /> <br />                        
                        <input type="button" value="<s:text name="systemUser.input.deleteYes" />" onclick="deleteStudent(true)" />
                        <input type="button" value="<s:text name="systemUser.input.deleteNo" />" onclick="deleteStudent(false)" />
                    </div>
                </div>
                <!-- end actions-box -->

                
            </div>
            <!-- End content-courses -->
        </div>    
    </div>
</div>

<div id="studentUser" style="display:none;">
    <div id="col-2-home">
        <div class="container-courses">
            <div class="content-courses">
                <h1><s:text name="systemUser.type.student"/></h1>
                <br class="clear" />
                <p></p>
                <div id="studentData"></div>
                <div class="actions-box" id="actions.all.student" style="display:none">
                    <h2><s:text name="course.show.actions"/>:</h2>
                    <div class="edit-tools">
                        <ul>
                            <li><a class="icon-edit" href="javascript:showDiv('multiple.message.student');"><s:text name="course.send.message"/></a></li>
                            <li><a class="icon-message"  href="javascript:showDiv('multiple.newsflash.student');"><s:text name="course.send.newsflash" /></a></li>
                            <li><a class="icon-newsFlash" href="javascript:showDiv('multiple.change.student');"><s:text name="course.show.perfil" /></a></li>
                            <li><a class="icon-newsFlash" href="javascript:showDiv('multiple.delete.student');"><s:text name="course.show.deleteStudent" /></a></li>
                            <br class="clear" />
                        </ul>                        
                    </div>
                    <div id="multiple.message.student" style="display:none;">
                        <h4><s:text name ="student.send.message" /></h4><br />
                        <s:text name="message.title" /><br /> <input type="text" id="student.input.multiple.message.title" size="55" maxlength="50" /><br />
                        <s:text name="message.type"/><br /> <textarea cols="62" rows="4" onKeyDown="textCounter(this,$('student.input.multiple.message.description.len'),250);" onKeyUp="textCounter(this,$('student.input.multiple.message.description.len'),250);" id="student.input.multiple.message.description"></textarea><br />
                        <s:text name="message.remaining" /><input readonly type=text id="student.input.multiple.message.description.len" name="student.input.multiple.message.description.len" size=3 maxlength=3 value="250"/><br/>
                        <input type="button" value="Submit" onclick="sendMessage($('student.input.multiple.message.title'), $('student.input.multiple.message.description'));" />
                    </div>
                    <div id="multiple.newsflash.student" style="display:none;">
                        <h4><s:text name="student.send.newsflash"/></h4><br />
                        <s:text name="message.type" /><br /> <textarea cols="62" rows="4" onKeyDown="textCounter(this,$('student.input.multiple.newsflash.message.len'),250);" onKeyUp="textCounter(this,$('student.input.multiple.newsflash.message.len'),250);" id="student.input.multiple.newsflash.message"></textarea><br />
                        <s:text name="message.remaining" /><input readonly type=text id="student.input.multiple.newsflash.message.len" name="student.input.multiple.newsflash.message.len" size=3 maxlength=3 value="250"/><br/>
                        <input type="button" value="Submit" onclick="sendNewsFlash($('student.input.multiple.newsflash.message'));" />
                    </div>
                    <div id="multiple.change.student" style="display:none;">
                        <br /><s:text name="systemUser.show.changeType"/><br /> <br />
                        <s:text name="systemUser.show.type" /> 
                        <select id="student.multiple.change.types"></select>
                        <br />
                        <input type="button" value="<s:text name="systemUser.input.btnSave" />" onclick="change($('student.multiple.change.types').value)" />
                    </div>
                     <div id="multiple.delete.student" style="display:none;">
                        <br /><s:text name="systemUser.show.delete"/><br /> <br />                        
                        <input type="button" value="<s:text name="systemUser.input.deleteYes" />" onclick="deleteStudent(true)" />
                        <input type="button" value="<s:text name="systemUser.input.deleteNo" />" onclick="deleteStudent(false)" />
                    </div>
                </div>
                <!-- end actions-box -->

                
            </div>
            <!-- End content-courses -->
        </div>    
    </div>
</div>

<div id="userInfo" style="display:none;" >
    <div id="col-2-home">
        <div class="container-courses">
            <div class="content-courses">
                <div class="member-info">
                    <h1><span id="userInfo.name"></span></h1>
                    <br class="clear" />
                    <img class="picture" src="../images/foto_profile.jpg" />
                    
                    <div>
                        <div>                            
                            <p><s:text name="systemUser.input.username"/>: <span id="userInfo.username"></span></p>
                            <p><s:text name="systemUser.input.email"/>: <span id="userInfo.email"></span></p>
                            <p><s:text name="systemUser.input.memberSince"/>: <span id="userInfo.since"></span> </p>
                        </div>                     
                    </div>
                    <br class="clear" />
                    
                </div>
                
                <div class="actions-box">
                    <h2><s:text name="course.show.actions"/>:</h2>
                    <div class="edit-tools">
                        <ul>
                            <li><a class="icon-edit" href="#" onclick="closeAllMessages(); $('user.message').style.display = 'block';"><s:text name="course.send.message"/></a></li>
                            <li><a class="icon-message"  href="#" onclick="closeAllMessages(); $('user.newsflash').style.display = 'block';"><s:text name="course.send.newsflash" /></a></li>
                            <li><a class="icon-newsFlash" href="#" onclick="closeAllMessages(); $('user.change').style.display = 'block';"><s:text name="course.show.perfil" /></a></li>
                            <br class="clear" />
                        </ul>                        
                    </div>
                    <div id="user.message" style="display:none;">
                        <h4><s:text name ="student.send.message" /></h4><br />
                        <s:text name="message.title" /><br /> <input type="text" id="input.user.message.title" size="55" maxlength="50" /><br />
                        <s:text name="message.type"/> <br /> <textarea cols="62" rows="4" onKeyDown="textCounter(this,$('input.user.message.description.len'),250);" onKeyUp="textCounter(this,$('input.user.message.description.len'),250);" id="input.user.message.description"></textarea><br />
                        <s:text name="message.remaining" /><input readonly type=text id="input.user.message.description.len" name="input.user.message.description.len" size=3 maxlength=3 value="250"/><br/>
                        <input type="button" value="Submit" onclick="sendMessage($('input.user.message.title'), $('input.user.message.description'));" />
                    </div>
                    <div id="user.newsflash" style="display:none;">
                        <h4><s:text name="student.send.newsflash"/></h4><br />
                        <s:text name="message.type" /><br /> <textarea cols="62" rows="4" onKeyDown="textCounter(this,$('input.user.newsflash.message.len'),250);" onKeyUp="textCounter(this,$('input.user.newsflash.message.len'),250);" id="input.user.newsflash.message"></textarea><br />
                        <s:text name="message.remaining" /><input readonly type=text id="input.user.newsflash.message.len" name="input.user.newsflash.message.len" size=3 maxlength=3 value="250"/><br/>
                        <input type="button" value="Submit" onclick="sendNewsFlash($('input.user.newsflash.message'));" />
                    </div>
                    <div id="user.change" style="display:block;">
                        <br /><s:text name="systemUser.show.changeType"/><br /> <br />
                        <s:text name="systemUser.show.type" />
                        <select id="user.change.types">
                        </select>
                        <br />
                        <input type="button" value="<s:text name="systemUser.input.btnSave" />" onclick="change($('user.change.types').value)" />
                    </div>
                </div>
                <!-- end actions-box -->
            </div>
            <!-- End content-courses -->
        </div>  
    </div>
</div>

<div id="multipleUsers" style="display:none;" >
    <div id="col-2-home">
        <div class="container-courses">
            <div class="content-courses">
                
                <h1>Múltiplos Usuários</h1>
                <br class="clear" />
                
                <s:iterator value="allUsers" status="stat">   
                    <div id="div.inner.user.id_<s:property value="id" />" class="member-info" style="display: none;">
                        <img class="picture" src="../images/foto_profile.jpg" width="80" height="80" />
                        <div>
                            <div>                                
                                <p><s:text name="systemUser.input.username"/>: <span><s:property value="username" /></span></p>
                                <p><s:text name="systemUser.input.email"/>: <span><s:property value="email" /></span></p>
                                <p><s:text name="systemUser.input.memberSince"/>: <span><s:date name="createdAt" format="MM/dd/yyyy"/> </span></p>
                            </div>
                        </div>
                        <br class="clear" />
                        
                    </div>
                </s:iterator>
                
                <div class="actions-box">
                    <h2><s:text name="course.show.actions"/>:</h2>
                    <div class="edit-tools">
                        <ul>
                            <li><a class="icon-edit" href="#" onclick="closeAllMessages(); $('multiple.message').style.display = 'block';"><s:text name="course.send.message"/></a></li>
                            <li><a class="icon-message"  href="#" onclick="closeAllMessages(); $('multiple.newsflash').style.display = 'block';"><s:text name="course.send.newsflash" /></a></li>
                            <li><a class="icon-newsFlash" href="#" onclick="closeAllMessages(); $('multiple.change').style.display = 'block';"><s:text name="course.show.perfil" /></a></li>
                            <br class="clear" />
                        </ul>                        
                    </div>
                    <div id="multiple.message" style="display:none;">
                        <h4><s:text name ="student.send.message" /></h4><br />
                        <s:text name="message.title" /><br /> <input type="text" id="input.multiple.message.title" size="55" maxlength="50" /><br />
                        <s:text name="message.type"/><br /> <textarea cols="62" rows="4" onKeyDown="textCounter(this,$('input.multiple.message.description.len'),250);" onKeyUp="textCounter(this,$('input.multiple.message.description.len'),250);" id="input.multiple.message.description"></textarea><br />
                        <s:text name="message.remaining" /><input readonly type=text id="input.multiple.message.description.len" name="input.multiple.message.description.len" size=3 maxlength=3 value="250"/><br/>
                        <input type="button" value="Submit" onclick="sendMessage($('input.multiple.message.title'), $('input.multiple.message.description'));" />
                    </div>
                    <div id="multiple.newsflash" style="display:none;">
                        <h4><s:text name="student.send.newsflash"/></h4><br />
                        <s:text name="message.type" /><br /> <textarea cols="62" rows="4" onKeyDown="textCounter(this,$('input.multiple.newsflash.message.len'),250);" onKeyUp="textCounter(this,$('input.multiple.newsflash.message.len'),250);" id="input.multiple.newsflash.message"></textarea><br />
                        <s:text name="message.remaining" /><input readonly type=text id="input.multiple.newsflash.message.len" name="input.multiple.newsflash.message.len" size=3 maxlength=3 value="250"/><br/>
                        <input type="button" value="Submit" onclick="sendNewsFlash($('input.multiple.newsflash.message'));" />
                    </div>
                    <div id="multiple.change" style="display:none;">
                        <br /><s:text name="systemUser.show.changeType"/><br /> <br />
                        <s:text name="systemUser.show.type" />
                        <select id="multiple.change.types">
                        </select>
                        <br />
                        <input type="button" value="<s:text name="systemUser.input.btnSave" />" onclick="change($('multiple.change.types').value)" />
                    </div>
                </div>
                <!-- end actions-box -->
            </div>
            <!-- End content-courses -->
        </div>  
    </div>
</div>