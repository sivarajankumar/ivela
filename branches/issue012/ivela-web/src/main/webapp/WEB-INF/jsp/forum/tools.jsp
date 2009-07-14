<%-- 
    Document   : tools Forum
    Created on : Sep 5, 2008, 8:52:14 AM
    Author     : jefferson
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<head>
    <script type="text/javascript">
        var postBy = '<s:text name="tools.forum.postBy"/>';
    </script>
</head>

<div class="forum">
    <h3><s:text name="tools.forum" /></h3>

    <div id="topics.empty">
        <p class="first-access"><s:text name="home.forum.empty" /></p>
    </div>
    <div id="topics.list">
        <%--
        <ul>
            <s:iterator value="recentlyTopics">
                <li>
                    <a href="post!list.action?forum.id=<s:property value="forum.id" />&topic.id=<s:property value="id" />" class="lightwindow page-options" params="lightwindow_type=external,lightwindow_width=1024" >
                        <s:property value="title" />
                    </a>
                    <br />
                    <s:text name="tools.forum.postBy"/> <s:property value="createdBy.username" />  - <s:date name="createdAt" format="M/d/y H:m:s"/>
                </li>
            </s:iterator>
        </ul>
        --%>
    </div>
    <a href="forum!list.action" id="btn-goto-forum" class="lightwindow page-options"
       params="lightwindow_type=external,lightwindow_width=1024"><s:text name ="tools.forum.goToForum"/></a>

</div>    
