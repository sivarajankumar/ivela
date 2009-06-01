<%--
    Document   : tools
    Created on : Aug 23, 2008, 8:45:12 AM
    Author     : leoomoreira
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript">
    <!--
        var from = '<s:text name="tools.messages.from" />';
    -->
</script>

<div class="messages">
    <h3><s:text name="tools.messages" /></h3>

    <div id="messages.empty">
        <p class="first-access"><s:text name="home.message.empty" /></p>
    </div>
    <div id="messages.list"></div>
    <a href="../message!inbox.action" id="btn-goto-messages" class="lightwindow page-options"
       params="lightwindow_type=external,lightwindow_width=1024" ><s:text name="tools.goMessages" /></a>
</div>