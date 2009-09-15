<%-- 
    Document   : show grade
    Created on : Sep 15, 2008, 2:07:13 PM
    Author     : marcus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--@ taglib prefix="cal" uri="/jscalendar" --%>

<head>
    <link href="../css/lightbox.css"  rel="stylesheet" type="text/css" />
    <link href="../css/cadAtividades.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../js/admin/admin.js"></script>
    <cal:head />
</head>
<s:actionerror />

<div id="breadcrumb">
    <p><s:text name="breadcrumb.youAreHere"/></p>
    <ul>
        <li><a href="index.action"><s:text name="admin.home"/></a></li>
        <li class="current"><s:text name="admin.controlPanel" /></li>
    </ul>
</div>

<div id="col-1-home">
    <h2><s:text name="admin.pageTitle" /></h2>
    <div id="vertical_container2" >                  
    </div>
</div>
<!-- end col-1-home -->

<div id="box1" style="display:none; text-align:center;">
    <iframe id="waitingFrame" frameborder="0" src="../waiting.jsp" style="width:150px;height:100px;border:0px solid #fff;" scrolling="no" >
    </iframe><br/>
</div>


<div id="showCourse" style="display: none;">
    <div id="col-2-home">
        <div class="container-grades">
            <div class="content-grades">
                <div class="actions-box">
                    <div class="edit-tools">
                    </div>
                    <div id="course.message" style="display:none;" class="course-message">
                    </div>
                    <div id="course.newsflash" style="display:none;" class="course-message">
                    </div>
                </div>
            </div>
        </div>
    </div>    
</div>


<!-- end col-2-home -->
<br class="clear" />

<!-- end content -->
<br class="clear" />
