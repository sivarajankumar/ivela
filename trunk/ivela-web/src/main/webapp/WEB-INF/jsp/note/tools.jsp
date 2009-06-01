<%-- 
    Document   : tools
    Created on : Aug 23, 2008, 1:52:59 PM
    Author     : leoomoreira
--%>

<%@ page import="org.springframework.security.context.SecurityContextHolder"%>
<%@ page import="org.springframework.security.userdetails.UserDetails"%>
<%@ page import="br.ufc.ivela.commons.model.SystemUser"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

    <%

            Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            SystemUser systemUser = null;


            if (obj instanceof UserDetails) {
                systemUser = (SystemUser) obj;
            }

    %>

<div class="agenda">
    
    <h3><s:text name="tools.note" /></h3> 
    
    <s:if test="(noteList == null || noteList.size() == 0)">
        <p class="first-access"><s:text name="home.note.empty" /></p>
    </s:if> 
    <s:else>
        <ul>
            <s:iterator value="noteList">
                <li>
                    <a href="http://webical:webical@200.17.41.215:8080/webical/app/calendar"
                       class="lightwindow page-options" 
                       params="lightwindow_type=external,lightwindow_width=1024" 
                       title="<s:property value="title" />"  caption="note">                        
                        <s:property value="title" /> <br />
                    </a> 
                    <s:text name="tools.note.on" /><s:date name="datetime" format="M/d/y H:m:s"/> 
                </li>
            </s:iterator>   
        </ul>   
    </s:else>
        <!-- http://<%= systemUser.getUsername() %>:<%= systemUser.getUsername() %>@<%= request.getServerName() %>:<%= request.getServerPort() %>/webical/app/calendar -->
    <a href="http://webical:webical@200.17.41.215:8080/webical/app/calendar" id="btn-goto-agenda" class="lightwindow page-options" 
       params="lightwindow_type=external,lightwindow_width=1024" ><s:text name="tools.note.goNote" /></a>        
    
</div>    
