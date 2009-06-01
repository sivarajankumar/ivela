<%-- 
    Document   : list BulletinBoard
    Created on : Jul 30, 2008, 12:48:27 PM
    Author     : rodrigofelix
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

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
        <p><s:text name="breadcrumb.youAreHere" /></p>
        <ul>
            <li><a href="home.action" title="Back to home"><s:text name="front.home" /></a></li>
            <li class="current"><s:text name="bulletin.pageTitle"/></li>
        </ul>
    </div>
    <h1><s:text name="bulletin.pageTitle"/></h1>
    <s:actionerror />
    <div id="col-1-bulletins">
        <h3><s:text name="bulletin.search"/></h3>
        <div id="box-search">
            <s:form theme="simple" action="bulletinBoard!list.action" cssClass="form-system-bulletin">
                <input type="text" name="searchBulletin" id="searchBulletin" class="name-search" value="<s:text name="bulletin.search.initialText"/>" /><br />
                <input type="submit" value="<s:text name="bulletin.search"/>" class="button" />                  
            </s:form>
        </div>
        <ul class="bulletin-action">
            <li><a class="inbox-bulletin" href="bulletinBoard!list.action"><s:text name="bulletin.list"/></a></li>
        </ul>
    </div>
    
    <div id="col-2-bulletins">
        <h2><s:text name="bulletin.list.sessionTitle" /></h2>
        <div class="navegation">
            <a class="previous" href=""><s:text name="bulletin.previous"/></a>
            <a class="next" href=""><s:text name="bulletin.next"/></a>
        </div>
        
        <table class="table-bulletin">
            <tr>
                <th><s:text name="bulletin.list.title" /></th>
                <th><s:text name="bulletin.list.sender" /></th>
                <th><s:text name="bulletin.list.datetime" /></th>
            </tr>
            <s:iterator value="bulletinList">
                <s:url id="listMessage" action="bulletinBoard" method="show">
                    <s:param name="bulletinBoard.id" value="id"/>
                </s:url>
                <tr>
                    <td class="subject"><img src="images/icon/icon-message-no-read.gif" align="left" alt="<s:text name="bulletin.list.title" />" /><s:a href="%{listMessage}"><s:property value="title" /></s:a></td>
                    <td class="author"><s:a href="%{listMessage}"><s:property value="createdBy.username" /></s:a></td>
                    <td class="date-bulletin"><s:property value="formatDate(createdAt,\"dd-MM-yy HH:mm\")" /></td>
                </tr>
            </s:iterator>
        </table>
        
        <div class="navegation">
            <a class="previous" href=""><s:text name="bulletin.previous"/></a>
            <a class="next" href=""><s:text name="bulletin.next"/></a>
        </div>
        
    </div>               
    <br class="clear" />  
</html>
