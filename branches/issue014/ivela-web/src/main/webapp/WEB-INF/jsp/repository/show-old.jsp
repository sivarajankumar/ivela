<%-- 
    Document   : show repository
    Created on : Jul 7, 2008, 3:27:35 PM
    Author     : marcus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<link href="css/repository.css" rel="stylesheet" type="text/css" />

<s:if test="files == null">
    <s:text name="repository.list.nofiles"/>
</s:if>
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
            
            <s:submit theme="simple"/>
        </s:form>
    </table>
</s:else>