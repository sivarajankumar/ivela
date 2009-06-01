<%-- 
    Document   : show BulletinBoard
    Created on : Jul 31, 2008, 8:47:43 AM
    Author     : leoomoreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="RenderServlet?file=/globals/css/base.css" rel="stylesheet" type="text/css" />
        <link href="css/bulletin.css" rel="stylesheet" type="text/css" />
        <title><s:text name="bulletin.pageTitle" /></title>
        <cal:head/>
        <s:head />
    </head>
    <div id="breadcrumb">
        <p>you are here</p>
        <ul>
            <li><a href="home.action" title="Back to home">Home</a></li>
            <li class="current"><s:text name="bulletin.pageTitle"/></li>
        </ul>
    </div>
    <h1><s:text name="bulletin.pageTitle"/></h1>
    
    <div id="col-1-bulletins">
        <h3><s:text name="bulletin.search"/></h3>
        <div id="box-search">
            <form action="" class="form-system-bulletin">
                <input type="text" name="searchBulletin" id="searchBulletin" class="name-search" value="<s:text name="bulletin.search.initialText"/>" />
                <br />
                <input type="submit" value="<s:text name="bulletin.search"/>" class="button" />                  
            </form>
        </div>
        <ul class="bulletin-action">
            <li><a class="inbox-bulletin" href="bulletinBoard!list.action"><s:text name="bulletin.list"/></a></li>
        </ul>
    </div>
    
    <div id="col-2-bulletins">
        
        <h2><s:text name="bulletin.show.sessionTitle" /></h2>
        <p>
            <span class="topic-bulletin"><s:text name="bulletin.list.title" />:</span><br />
            <span class="title-subject-bulletin"><s:property value="bulletinBoard.title" /></span>
        </p>
        <p>
            <span class="topic-bulletin"><s:text name="bulletin.list.sender" />:</span><br /><s:property value="bulletinBoard.createdBy.username" />
        </p>
        <p class="bulletin-reply">
            <span class="topic-bulletin"><s:text name="bulletin.list.description" />:</span><br /><s:property value="bulletinBoard.description" />
        </p>
        
    </div>
    <br class="clear" />        
</html>