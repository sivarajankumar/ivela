<%--    
# Copyright(c) 2009 by IBM Brasil Ltda and others                                           #
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
# Document: Show Library Page                                                               # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 04-NOV-2008 - Marcus (UFC)                      - XXXXXX - Initial Version                #
# 24-JUN-2009 - otofuji (Instituto Eldorado)      - 000010 - General i18n Issues            #
#############################################################################################
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
    <!--p><a href="#" onclick="newDir();">Add Directory</a></p-->

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

                <p><s:text name="repository.input.title" />: <span id="fileInfo.title"></span></p>
                <p><s:text name="repository.input.author" />: <span id="fileInfo.author"></span></p>
                <p><s:text name="repository.input.description" />: <span id="fileInfo.description"></span></p>
                <p><s:text name="repository.input.keywords" />: <span id="fileInfo.keywords"></span></p>
                <p><s:text name="repository.list.sentby" />: <span id="fileInfo.sentby"></span></p>

                <p></p>

                <input type="hidden" id="file.id"/>
                <p><a href="#" onclick="download();"><s:text name="repository.list.download"/> <s:text name="repository.input.file"/> </a></p>
                <!--p><a href="#" onclick="removeFile();">Remove file</a></p-->

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

                <!--p><a href="#" onclick="openUpload();">Add File</a></p>
                <div id="subdir" style="display:none;">
                    <p><a href="#" onclick="openSubDir();">Add Sub-Directory</a></p>
                </div>
                <p><a href="#" onclick="removeDir();">Remove Directory</a></p-->

                <br class="clear" />
            </div>
        </div>
    </div>
</div>

<%--div id="upload" style="display:none;">
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
</div--%>