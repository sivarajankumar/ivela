<%--
    Document   : show library
    Created on : Nov 4, 2008, 2:06:16 PM
    Author     : marcus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<head>
    <link href="css/accordion_library.css"  rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/util/util.js"></script>
    <script type="text/javascript" src="js/prototype/prototype.js"></script>
    <script type="text/javascript" src="js/scriptaculous/scriptaculous.js"></script>
    <script type="text/javascript" src="js/scriptaculous/effects.js"></script>
    <script type="text/javascript" src="js/accordion.js"></script>
    <script type="text/javascript" src="js/ead/library.js"></script>
    <script language="JavaScript" type="text/javascript">
        var courseId = <s:property value="courseId"/>;
    </script>
</head>

<h1><s:text name="repository.list.title"/></h1>

<div id="col-1-home">
    <p><a href="#" onclick="newDir();">Add Directory</a></p>

    <div id="category_container" >
        <s:iterator value="repository.directories">
            <h3 class="category_toggle"><s:property value="name" /></h3>
            <div class="category_content" id="<s:property value="id" />">
                <div id="category_container2" >
                    <s:iterator value="directories">
                        <h4 class="category_toggle2"><s:property value="name" /></h4>
                        <div class="category_content2" id="<s:property value="id" />">
                            <ul class="list-files">
                                <s:iterator value="files">
                                    <li onclick="showFileInfo(<s:property value="id"/>);"><s:property value="name" /></li>
                                </s:iterator>
                            </ul>

                            <br class="clear"/>
                        </div>
                    </s:iterator>
                </div>

                <ul class="list-files">
                    <s:iterator value="files">
                        <li onclick="showFileInfo(<s:property value="id"/>);"><s:property value="name" /></li>
                    </s:iterator>
                </ul>

                <br class="clear"/>
            </div>
        </s:iterator>
    </div>
</div>

<div id="fileInfo" style="display:none;">
    <div id="col-2-home">
        <div class="container-courses">
            <div class="content-courses">
                <h1><span id="fileInfo.name"></span></h1>

                <p>Title: <span id="fileInfo.title"></span></p>
                <p>Author: <span id="fileInfo.author"></span></p>
                <p>Description: <span id="fileInfo.description"></span></p>
                <p>Keywords: <span id="fileInfo.keywords"></span></p>
                <p>Sent By: <span id="fileInfo.sentby"></span></p>

                <p></p>

                <input type="hidden" id="file.id"/>
                <p><a href="#" onclick="download();">Download file</a></p>
                <p><a href="#" onclick="removeFile();">Remove file</a></p>

                <br class="clear" />
            </div>
        </div>
    </div>
</div>

<div id="dirInfo" style="display:none;">
    <div id="col-2-home">
        <div class="container-courses">
            <div class="content-courses">
                <h1><span id="dirInfo.name"></span></h1>

                <p><span id="dirInfo.count"></span></p>

                <p><a href="#" onclick="openUpload();">Add File</a></p>
                <div id="subdir" style="display:none;">
                    <p><a href="#" onclick="openSubDir();">Add Sub-Directory</a></p>
                </div>
                <p><a href="#" onclick="removeDir();">Remove Directory</a></p>

                <br class="clear" />
            </div>
        </div>
    </div>
</div>

<div id="upload" style="display:none;">
    <div id="col-2-home">
        <div class="container-courses">
            <div class="content-courses">
                <h1><s:text name="repository.input.upload"/></h1>

                <s:form  id="uploadId" action="repository!upload.action" method="POST" enctype="multipart/form-data">

                    <s:file  id="fileId" name="upload" key="repository.input.file"/>

                    <s:hidden name="courseId" key="repository.input.gradeId"/>
                    <s:hidden id="upload_dirId" name="dirId" key="repository.input.directoryID"/>
                    <s:textfield name="file.description" key="repository.input.description"/>
                    <s:textfield name="file.author" key="repository.input.author"/>
                    <s:textfield name="file.title" key="repository.input.title"/>
                    <s:textfield name="file.keywords" key="repository.input.keywords"/>
                    <s:submit onclick="validateUpload()" value="Submit" />

                </s:form>

                <br class="clear" />
            </div>
        </div>
    </div>
</div>

<div id="addDir" style="display:none;">
    <div id="col-2-home">
        <div class="container-courses">
            <div class="content-courses">
                <h1><s:text name="repository.input.addDirectory"/></h1>

                <s:form id="repo" action="repository!add.action" method="POST">

                    <s:hidden name="courseId" key="repository.input.gradeId"/>
                    <s:hidden id="repo_dirId" name="dirId" key="repository.input.parentDir"/>
                    <s:textfield id="dirName" name="dirName" key="repository.input.directoryName"/>
                    <s:submit onclick="validateDir()" value="Submit" />
                    
                </s:form>

                <br class="clear" />
            </div>
        </div>
    </div>
</div>