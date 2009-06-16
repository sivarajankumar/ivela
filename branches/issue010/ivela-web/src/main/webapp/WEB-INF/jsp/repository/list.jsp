<%-- 
    Document   : list
    Created on : Jun 18, 2008, 10:35:55 AM
    Author     : marcus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
        <link href="css/repository.css" rel="stylesheet" type="text/css" />
        <link href="js/dijit/themes/tundra/tundra.css" rel="stylesheet" type="text/css"/>
    </head>    
    
    <script type="text/javascript" src="js/dojo/dojo.js" djConfig="parseOnLoad: true"></script>
    <script language="JavaScript" type="text/javascript">
        dojo.require("dojo.data.ItemFileReadStore");
        dojo.require("dijit.Dialog");
        dojo.require("dijit.Tree");
        dojo.require("dijit.Menu");
        dojo.require("dojo.parser");  // scan page for widgets and instantiate them
            
        var courseId = <s:property value="courseId"/>;
    </script>
    <script type="text/javascript" src="js/repository/functions.js"></script>
    
    <div dojoType="dojo.data.ItemFileReadStore" jsId="jsonStore"
         url="repository!json.action?courseId=<s:property value="courseId"/>"></div> 
    
    <ul dojoType="dijit.Menu" id="tree_menu" style="display: none;" class="tundra">
        <li dojoType="dijit.MenuItem" onclick="addFile();" class="tundra">Add File</li>
        <li dojoType="dijit.MenuItem" onclick="addDir();" class="tundra">Add Directory</li>
        <li dojoType="dijit.MenuItem" onclick="addRootDir();" class="tundra">Add Directory on Root</li>
        <li dojoType="dijit.MenuItem" onclick="rmDir();" class="tundra">Remove Directory</li>            
    </ul>
    
    <h1><s:text name="repository.list.title"/></h1>
    <div id="col-1-repository">
        <h2>Directories</h2>
        <div id="box-directory">      
            <div dojoType="dijit.Tree" id="tree1" store="jsonStore" class="tundra"
                 labelAttr="name" showRoot="false">  
                <script type="dojo/connect">
                    var menu = dijit.byId("tree_menu");
                    // when we right-click anywhere on the tree, make sure we open the menu
                    menu.bindDomNode(this.domNode);
                        
                    dojo.connect(menu, "_openMyself", this, function(e){
                        var domElement = e.target;
                        // find node
                        var nodeWidget = dijit.getEnclosingWidget(domElement);	
                    
                        if(nodeWidget && nodeWidget.isTreeNode){
                            itemClicked = nodeWidget.item;
                        }                    
                    });

                    dojo.connect(dijit.byId("tree1").domNode, "onclick", function(e){
                        var domElement = e.target;
                        // find node
                        var nodeWidget = dijit.getEnclosingWidget(domElement);
                    
                        if(nodeWidget && nodeWidget.isTreeNode){
                            itemClicked = nodeWidget.item;
                            var id = jsonStore.getValue(itemClicked, "id");
                                
                            document.location = "repository!list.action?courseId="+courseId+"&dirId="+id;
                        }
                    });                
                </script>
            </div>
        </div>    
    </div>
    
    <div id="col-2-repository">
        <h2>Files</h2>
        <div id="box-files">
            <!--div class="action-repository">
                    <a class="add-file" href="#" onclick="addFile();">Add File</a>
                    <a class="delete-file" href="#">Delete Selected</a>
                </div-->
            <br class="clear" />
            
            <div id="files">
                <s:if test="dirId == null">
                    <s:text name="repository.list.nodirectory"/>
                </s:if>
                <s:elseif test="files == null">
                    <s:text name="repository.list.nofiles"/>
                </s:elseif>
                <s:else>
                    <table class="table-files-repository" cellspacing="10">
                        <s:form action="repository!rmfile.action" method="get" theme="simple">            
                            <s:iterator value="filesByThree" status="stat" id="it">
                                
                                <tr>
                                    <s:iterator value="#it">
                                        <s:url id="download" action="repository" method="download">
                                            <s:param name="fileId" value="id"/>
                                        </s:url>
                                        
                                        <td class="file">
                                            <img src="images/icon/icon-image-repository-default.jpg" alt="<s:property value="title"/>"/><br />
                                            <s:a href="%{download}"><s:property value="name"/></s:a>					
                                        </td> 
                                    </s:iterator>
                                </tr>
                                
                                <tr>
                                    <s:iterator value="#it">    
                                        <td align="right" width="91"><s:checkbox name="markedFiles(%{id})" theme="simple" /></td>   
                                    </s:iterator>
                                </tr>
                                
                            </s:iterator>
                        </s:form>
                    </table>
                </s:else>
            </div>
        </div>
    </div>  
    
    <s:include value="dialogs.jsp"/>
</html>
