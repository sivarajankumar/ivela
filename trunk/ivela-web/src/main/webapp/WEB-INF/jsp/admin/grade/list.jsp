<%-- 
    Document   : list Grade
    Created on : Aug 7, 2008, 9:14:56 AM
    Author     : leoomoreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="grade.pageTitle" /></title>
        <link href="../css/grade_admin.css" rel="stylesheet" type="text/css" />
        <s:head />
        <cal:head />
    </head>
    <div id="breadcrumb">
        <p><s:text name="admin.breadcrumb.youAreHere"/></p>
        <ul>
            <li><a href="home.action" title="<s:property value="admin.home.title"/>"><s:text name="admin.home"/></a></li>
            <li class="current" title="<s:property value="grade.pageTitle"/>"><s:text name="grade.pageTitle"/></li>
        </ul>
    </div>
    <h2><s:text name="grade.pageTitle" /></h2>
    <s:a href="grade!input.action" cssClass="add-grade"><s:text name="grade.add.action" /></s:a>
    
    <s:actionerror />
    <table id="grades">
        <s:iterator id="git" value="gradeList" status="gstat">
            <s:url id="editUrl" action="grade" method="edit">
                <s:param name="grade.id" value="id"/>
            </s:url>
            
            <s:url id="deleteUrl" action="grade" method="remove">
                <s:param name="grade.id" value="id"/>
            </s:url>
            
            <s:url id="addProfessorUrl" action="professor" method="input">
                <s:param name="grade.id" value="id"/>
            </s:url>                
            
            <tr>
                <td class="title-grade"><s:a href="%{editUrl}"><s:property value="name" /></s:a></td>
                <td class="edit-grade"><s:a href="%{editUrl}"><s:text name="grade.edit.action" /></s:a></td>
                <td class="delete-grade"><a href="grade!remove.action?grade.id=<s:property value="id" />" onclick="return confirm('<s:text name="admin.alert.remove"/>')"><s:text name="grade.remove.action" /></a></td>
                <td class="edit-grade"><s:a href="%{addProfessorUrl}"><s:text name="grade.input.addProfessor" /></s:a></td>
            </tr>
            <tr>
                <td colspan="4" class="grade-description">
                    <p>
                        <s:text name="grade.list.course"/>: <s:property value="course.name" /><br /> 
                        <s:text name="grade.list.period" />: <s:property value="period" /><br />
                        <s:text name="grade.list.maxStudents"/>: <s:property value="maxStudents" /><br/>
                        <s:text name="grade.list.coordinator"/>: <s:property value="coordinator.username" />
                        
                        
                        <s:text name="grade.input.professor" /><br />
                        <ul>
                            <s:iterator value="professors">
                                <s:url id="deleteProfessorUrl" action="grade" method="removeProfessor">
                                    <s:param name="grade.id" value="%{gradeList[#gstat.index].id}"/>
                                    <s:param name="systemUser.id" value="id"/>
                                </s:url>
                                <li>
                                    <s:a href="%{deleteProfessorUrl}"><s:property value="username"/></s:a>
                                    <s:a cssClass="delete-grade" href="%{deleteProfessorUrl}"><s:text name="grade.input.removeprofessor" /></s:a>
                                </li>
                            </s:iterator>
                        </ul>
                    </p>
                </td>
            </tr>                
        </s:iterator>
    </table>
</html>