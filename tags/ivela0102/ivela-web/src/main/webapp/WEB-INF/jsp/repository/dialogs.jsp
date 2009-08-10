<%-- 
    Document   : dialogs
    Created on : Aug 13, 2008, 12:45:22 PM
    Author     : marcus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>


<div dojoType="dijit.Dialog" id="dialog1" title="<s:text name="repository.input.upload"/>" class="tundra" style="background:white; border:1px; bordercolor:black;">
     <s:form action="repository!upload.action" method="POST" enctype="multipart/form-data">
        
        <s:file name="upload" key="repository.input.file"/>
        
        <s:hidden name="courseId" key="repository.input.gradeId"/>
        <s:hidden name="dirId" key="repository.input.directoryID"/>
        <s:textfield name="file.description" key="repository.input.description"/>
        <s:textfield name="file.author" key="repository.input.author"/>
        <s:textfield name="file.title" key="repository.input.title"/>
        <s:textfield name="file.keywords" key="repository.input.keywords"/>
        
        <s:submit key="repository.input.submit"/>
    </s:form>
</div>

<div dojoType="dijit.Dialog" id="dialog2" title="<s:text name="repository.input.addDirectory"/>" class="tundra" style="background:white; border:1px; bordercolor:black;">
     <s:form id="repo" action="repository!add.action" method="POST">
        
        <s:hidden name="courseId" key="repository.input.gradeId"/>
        <s:hidden name="dirId" key="repository.input.parentDir"/>
        <s:textfield name="dirName" key="repository.input.directoryName"/>
        
        <s:submit key="repository.input.submit"/>
    </s:form>
</div>   
