<%--    
#############################################################################################
# Copyright(c) 2008-2009 by IBM Brasil Ltda and others                                      #
# This file is part of ivela project, an open-source                                        #
# Program URL   : http://code.google.com/p/ivela/                                           #  
#                                                                                           #
# This program is free software; you can redistribute it and/or modify it under the terms   #
# of the GNU General Public License as published by the Free Software Foundation; either    #
# version 3 of the License, or (at your option) any later version.                          #
#                                                                                           #
# This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; #
# without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. #
# See the GNU General Public License for more details.                                      #  
#                                                                                           #
#############################################################################################
# File: show.jsp                                                                            #
# Document: Display course information for Administrators                                   # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 15-SEP-2008 - marcus                            - XXXXXX - Initial Version                #
# 09-JUN-2009 - otofuji (Instituto Eldorado)      - 000007 - IE7 compatibility              #
# 10-JUN-2009 - mileine (Instituto Eldorado)      - 000007 - waitingFrame layout fixed      #
# 15-JUN-2009 - fantato (Instituto Eldorado)      - 000010 - broken icons                   #
# 17-JUN-2009 - fantato (Instituto Eldorado)      - 000010 - multimidia link was wrong      #
# 25-JUN-2009 - otofuji (Instituto Eldorado)      - 000010 - i18n support                   #
# 20-AGO-2009 - mileine (Instituto Eldorado)      - 000015 - upload translation tip fixed   #
# 28-AGO-2009 - lagoa   (Instituto Eldorado)      - 000016 - Set date field to readonly     #
# 02-SEP-2009 - Rafael Lagôa (Instituto Eldorado) - 000016 - Fix showChat action            #
#############################################################################################
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
    <script type="text/javascript" src="../js/util/util.js"></script>
    <script type="text/javascript" src="../js/admin/courses.js"></script>
    <script type="text/javascript" src="../js/admin/discipline.js"></script>
    <script type="text/javascript" src="../js/admin/unit.js"></script>
    <script type="text/javascript" src="../js/admin/unitContent.js"></script>
    <script type="text/javascript" src="../js/admin/challenge.js"></script>
    <script type="text/javascript" src="../js/admin/exercise.js"></script>
    <script type="text/javascript" src="../js/admin/exam.js"></script>
    <script type="text/javascript" src="../js/admin/question.js"></script>
    <script type="text/javascript" src="../js/admin/dictionary.js"></script>
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

		function getRepository(value) {
			return "repository!show.action?courseId="+value; 
		}
    
        function textCounter(field, countfield, maxlimit) {
            if (field.value.length > maxlimit) // if too long...trim it!
                field.value = field.value.substring(0, maxlimit);
            // otherwise, update 'characters left' counter
            else 
                countfield.value = maxlimit - field.value.length;
        }

        function showChat(courseId, disciplineId) {
        	window.open('course!showChat.action?courseId='+courseId+'&disciplineId='+disciplineId, '');
        }
    </script>
    <cal:head />
    <script type="text/javascript" src="../js/util/calendar.js"></script>
</head>
<s:actionerror />

<div id="breadcrumb">
    <p><s:text name="breadcrumb.youAreHere"/></p>
    <ul>
        <li><a href="index.action"><s:text name="admin.home"/></a></li>
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
            <s:iterator value="courseList" id="courseList">
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
                                                <div class="vertical_accordion_content2" id="<s:property value="id" />_<s:property value="#courseList.uploadPackageEnabled.toString()" />_<s:property value="#courseList.challengeItensEnabled.toString()" />">
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
                                                                <div class="vertical_accordion_content3" id="<s:property value="id" />_<s:property value="id" />_<s:property value="id" />">                                                                    
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

<!-- block to add a new course -->
<div id="showEntryCourse" style="display: none;">
    <div id="col-2-home">
        <div class="container-courses">
            <div class="content-courses">
                <input type="hidden" name="input.course.id" id="input.course.id" value="" />
                <h1><s:text name="home.course" /></h1><br/><br/><br/>
                <label><s:text name="course.input.name" /></label><br/>
                <input type="hidden" name="input.course.repository" id="input.course.repository" value=""/>
                <input type="hidden" name="input.course.challengeCount" id="input.course.challengeCount" value=""/>
                <input type="hidden" name="input.course.challengeWeight" id="input.course.challengeWeight" value=""/>
                <input type="text" name="input.course.name" id="input.course.name" value="" size="61" maxlength="35" /><span class="tooltip" onmouseover="return escape('<s:text name="admin.course.tip.01"/>')"> </span><br/>
                <label><s:text name="course.input.description" /></label><br/>
                <textarea onKeyDown="textCounter(this,$('remLen'),250);" onKeyUp="textCounter(this,$('remLen'),250);" rows="4" name="input.course.description" id="input.course.description" cols="70" ></textarea><span class="tooltip" onmouseover="return escape('<s:text name="admin.course.tip.02"/>')"></span><br/>
                <s:text name="course.show.remaining" /><input readonly type=text id="remLen" name=remLen size=3 maxlength=3 value="250"/><br/>
                <label><s:text name="course.input.targetAudience" /></label><br/>
                <textarea onKeyDown="textCounter(this,$('remLenTargetAudience'),250);" onKeyUp="textCounter(this,$('remLenTargetAudience'),250);" rows="3" name="input.course.targetAudience" id="input.course.targetAudience" cols="70"></textarea><span class="tooltip" onmouseover="return escape('<s:text name="admin.course.tip.03"/>')"></span><br/>
                <s:text name="course.show.remaining" /><input readonly type=text id="remLenTargetAudience" name=remLenTargetAudience size=3 maxlength=3 value="250"/><br/>
                <s:form action="course!updateImage" id="updateImage" method="post" enctype="multipart/form-data">
                    <label><s:text name="course.input.image" /></label><br/>
                    <s:hidden id="iform.course.id" name="course.id" />
                    <s:file name="upload" theme="simple" id="input.course.image"  /><br/>                    
                </s:form>
                <label><s:text name="course.input.contents" /></label><br/>
                <!--<input type="text" name="input.course.contents" id="input.course.contents" value="" /><br />-->
                <textarea onKeyDown="textCounter(this,$('remLenContents'),500);" rows="6" onKeyUp="textCounter(this,$('remLenContents'),500);" name="input.course.contents" id="input.course.contents" cols="70"></textarea><span class="tooltip" onmouseover="return escape('<s:text name="admin.course.tip.04"/>')"></span><br/>
                <s:text name="course.show.remaining" /><input readonly type=text id="remLenContents" name=remLen size=3 maxlength=3 value="500"/><br/>


                <label><s:text name="course.input.uploadPackage" /></label>
                <label class="label-boxfield"><input type="radio" name="input.course.uploadPackage" id="input.course.uploadPackage.yes" value="true"> &nbsp;<s:text name="course.input.yes" /></label>
                <label class="label-boxfield"><input type="radio" name="input.course.uploadPackage" id="input.course.uploadPackage.no" value="false" checked="checked"> &nbsp;<s:text name="course.input.no" /></label> <span class="tooltip" onmouseover="return escape('<s:text name="admin.course.tip.05" />')"></span><br/>

                <label><s:text name="course.input.challengeItens" /></label>
                <label class="label-boxfield"><input type="radio" name="input.course.challengeItens" id="input.course.challengeItens.yes" value="true"> &nbsp;<s:text name="course.input.yes" /></label>
                <label class="label-boxfield"><input type="radio" name="input.course.challengeItens" id="input.course.challengeItens.no" value="false" checked="checked"> &nbsp;<s:text name="course.input.no" /></label> <span class="tooltip" onmouseover="return escape('<s:text name="admin.course.tip.06" />')"></span><br/>

                <label><s:text name="course.input.customToc" /></label>
                <label class="label-boxfield"><input type="radio" name="input.course.customToc" id="input.course.customToc.yes" value="true"> &nbsp;<s:text name="course.input.yes" /></label>
                <label class="label-boxfield"><input type="radio" name="input.course.customToc" id="input.course.customToc.no" value="false" checked="checked"> &nbsp;<s:text name="course.input.no" /></label> <span class="tooltip" onmouseover="return escape('<s:text name="admin.course.tip.07" />')"></span><br/>

                <label><s:text name="course.input.retries" /></label>
                <input type="text" name="input.course.challengeRetries" id="input.course.challengeRetries" value="" size="2" maxlength="2" /><span class="tooltip" onmouseover="return escape('<s:text name="admin.course.tip.08" />')"></span><br/>

  				<label>&nbsp;</label>
                <input type="button" name="input.course.submit" id="input.course.submit" value="<s:text name="systemUser.input.btnSave" />" onclick="submitCourse($('input.course.id').value)" />
            </div>
        </div>
    </div>
</div>

<!-- block to add a new discipline -->
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
                <input type="text" name="input.discipline.name" id="input.discipline.name" value="" size="61" maxlength="35" /><span class="tooltip" onmouseover="return escape('<s:text name="admin.discipline.tip.01"/>')"></span><br />
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
                <input type="text" name="input.unit.name" id="input.unit.name" value="" maxlength="35" size="61" /><span class="tooltip" onmouseover="return escape('<s:text name="admin.unit.tip.01"/>')"></span><br />
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
                    <input type="text" name="unitContent.title" id="input.unitContent.title" value="" maxlength="35" size="61" /><span class="tooltip" onmouseover="return escape('<s:text name="admin.unitContent.tip.01"/>')"></span><br />                
                    <label><s:text name="unitContent.input.order_n" /></label><br />
                    <select style="width: 80px;" name="unitContent.orderN" id="input.unitContent.order_n"></select><span class="tooltip" onmouseover="return escape('<s:text name="admin.unitContent.tip.02"/>')"></span><br />                                    
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
                    <h2><s:text name="course.input.partner" /><br /><img id="course.image" src="../images/foto_profile.jpg" width="80" height="80" /></h2>
                    <br class="clear" />
                </div>
                <div class="stats">
                    <h2><s:text name="course.input.statitics" /></h2>
                    <p><s:text name="course.input.students" /><br  /><span id="course.student.count"></span></p>
                    
                    <p><s:text name="course.input.grade" /><br  /><span id="course.grade.count"></span></p>
                    <!--p><s:text name="course.input.graduated" /> <br  /><span id="course.graduated.count"></span></p-->
                    <br class="clear" />
                </div>
                <h2 style="margin-bottom:10px;"><s:text name="course.input.professor" />:</h2>
                <div id="course.professors">
                
                </div>
                <h2 style="margin-bottom:10px;"><s:text name="course.input.tutor" />:</h2>
                <div id="course.tutors">
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
                            <li><a class="icon-dictionary" href="javascript:showDictionary();"><s:text name= "dictionary.show" /></a></li>
                            <li><a id="pnlRepositorio" href="javascript:getRepository($('course.id').value);" id="btn-goto-avaliacao" class="icon-multimedia lightwindow page-options" params="lightwindow_type=external,lightwindow_width=1024" ><s:text name="discipline.show.biblioteca"/></a></li>
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
                            <li id="unit.upload"><a class="icon-new" href="javascript:showUploadUnitContent($('unit.id').value);"><s:text name="unitContent.input.upload" /></a></li>
                            <li><a class="icon-edit" href="javascript:showEditUnitContent();"><s:text name="unitContent.input.edit" /></a></li>                            
                            <li><a class="icon-delete" href="javascript:deleteUnitContent();"><s:text name="unitContent.input.remove" /></a></li>
                            <li id="unit.challenge"><a class="icon-message"href="javascript:showNewsChallenge();"><s:text name= "course.add.challenge" /></a></li>
                            <li><a id="chatId" class="icon-chat" href="javascript:showChat($('course.id').value, $('discipline.id').value);"><s:text name= "course.show.chat" /></a></li>
                            
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
                    <br />
                    <br />
                    <label><s:text name="unitContent.input.title" /></label><br />
                    <s:textfield name="unitContent.title" id="upload.unitContent.title" maxLength="35" size="61" /><span class="tooltip" onmouseover="return escape('<s:text name="admin.unitContent.tip.01"/>')"></span>
                    <br />               
                    <label><s:text name="unitContent.input.order_n" /></label><br />
                    <select style="width: 80px;" name="unitContent.orderN" id="upload.unitContent.order_n"></select><span  class="tooltip"onmouseover="return escape('<s:text name="admin.unitContent.tip.01"/>')"></span><br />                                    
                    <br />
                    <label><s:text name="unitContent.input.package" /></label><br />
                    <s:file name="upload" key="repository.input.file" theme="simple" /><span class="tooltip" onmouseover="return escape('<s:text name="admin.unitContent.tip.04"/>')"></span>
                    <br />
                    <br />
                    <label><s:text name="unitContent.input.width" /></label><br />
                    <s:textfield name="unitContent.width" id="upload.unitContent.width" maxLength="4" size="8" /><span class="tooltip"  onmouseover="return escape('<s:text name="admin.unitContent.tip.03"/>')"></span><br />
                    <label><s:text name="unitContent.input.height" /></label><br />
                    <s:textfield name="unitContent.height" id="upload.unitContent.height" maxLength="4" size="8" /><span class="tooltip" onmouseover="return escape('<s:text name="admin.unitContent.tip.03"/>')"></span><br /><br />
                    <s:submit key="systemUser.input.btnSave" />
                </s:form>
            </div>
        </div>
    </div>
</div>


<div id="box1" style="display:none; text-align:center;">
    <iframe id="waitingFrame"  frameborder="0" src="../waiting.jsp" style="width:150px;height:100px;border:0px;background: #ddd;" scrolling="no" >
    </iframe><br/>
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
                            <td><s:text name="show.challenge.weight"/></td>
                            <td><input type="text" id="challengeItens.weight" name="challengeItens.weight" /> <span class="tooltip" onmouseover="return escape('<s:text name="admin.course.tip.09" />')"></span></td>                            
                        </tr>
                        
                        <tr>
                            <td><s:text name="show.challenge.xml"/></td>
                            <td><textarea id="challengeItens.xml" name="challengeItens.xml" label="show.challenge.xml" cols="50" rows="25"></textarea></td>
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
