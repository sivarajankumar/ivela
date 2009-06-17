<%-- 
    Document   : input Disciplina
    Created on : Jul 15, 2008, 1:48:54 PM
    Author     : nelson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="cal" uri="/jscalendar" %>

<head>
    <link href="css/course.css" rel="stylesheet" type="text/css" />
    <link href="css/scorecard.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/ead/tools.js"></script>
    <script type="text/javascript" src="js/timeline/timeline-api.js"></script>
    <!--script src="http://simile.mit.edu/timeline/api/timeline-api.js" type="text/javascript"></script-->
    <script>

        var tl;
        function onLoad() {

            //var eventSource = new Timeline.DefaultEventSource();
            var eventSource = new Timeline.DefaultEventSource(0);

            var bandInfos = [
                Timeline.createBandInfo({
                    eventSource:    eventSource,
                    width:          "70%",
                    intervalUnit:   Timeline.DateTime.WEEK,
                    intervalPixels: 100
                }),
                Timeline.createBandInfo({
                    showEventText:  false,
                    trackHeight:    0.5,
                    trackGap:       0.2,
                    eventSource:    eventSource,
                    width:          "30%",
                    intervalUnit:   Timeline.DateTime.MONTH,
                    intervalPixels: 200
                })
            ];

            bandInfos[1].syncWith = 0;
            bandInfos[1].highlight = true;
            bandInfos[1].eventPainter.setLayout(bandInfos[0].eventPainter.getLayout());

            tl = Timeline.create(document.getElementById("my-timeline"), bandInfos);
            //Timeline.loadXML("example.xml", function(xml, url) { eventSource.loadXML(xml, url); });
            Timeline.loadJSON("history!getJson.action", function(data, url){ eventSource.loadJSON(data, url); });

        }

        var resizeTimerID = null;
        function onResize() {
            if (resizeTimerID == null) {
                resizeTimerID = window.setTimeout(function() {
                    resizeTimerID = null;
                    tl.layout();
                }, 500);
            }
        }

        window.onload = onLoad;
        window.onresize = onResize;
    </script>
    <script type="text/javascript">
        loadTools();
    </script>
</head>

<div id="breadcrumb">
    <p><s:text name="breadcrumb.youAreHere"/></p>
    <ul>
        <li><a href="index.action"><s:text name="home.name"/></a></li>
        <li class="current"><s:text name="home.history"/></li>
    </ul>
</div>
<br>
<br> 
<s:if test="!transcriptList.isEmpty()">
<table width="100%">
    <tr>
        <td align="right">
            <a id="pnlAvaliacao" href="history!showPrintVersion.action" id="btn-goto-avaliacao" class="lightwindow page-options" params="lightwindow_type=external,lightwindow_width=1024"  
               style="text-decoration: none;">
                <img src="/ivela-web/images/icon/icon-printer.gif" border="0" /> <font style="font-family:arial;font-size: 12px;">Printer Version</font>
            </a>
        </td>
    </tr>
    
</table>
  <h1>My Scores</h1>
    <table class="table-exercises-result" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td width="368" class="titulo"><center>Course</center></td>
            <td width="368" class="titulo"><center>Grade</center></td>
            <td width="368" class="titulo"><center>Average Exercise</center></td>
            <td width="368" class="titulo"><center>Average Exam</center></td>
            <td width="368" class="titulo"><center>Final Average</center></td>
            <td width="368" class="titulo"><center>Result</center></td>
        </tr>
        
        <s:iterator value="transcriptList">
            <tr>
                <td align="left">
                    <s:property value="grade.course.name"/>
                </td>
                <td align="left">
                    <s:property value="grade.name"/>
                </td>
                <td class="score">
                    <s:if test="averageExercise<0.0">--</s:if>
                    <s:else><s:property value="averageExercise"/></s:else>
                </td>
                <td class="score">
                    <s:if test="averageExam<0.0">--</s:if>
                    <s:else><s:property value="averageExam"/></s:else>
                </td>
                <td class="score">
                    <s:if test="score<0.0">--</s:if>
                    <s:else><s:property value="score"/></s:else>
                </td>
                <s:if test="status==0">
                    <td class="manual-mark">FAILED</td>
                </s:if>
                <s:if test="status ==1">
                    <td class="manual-mark">APPROVED</td>
                </s:if>
                <s:if test="status==3">
                    <td class="manual-mark">IN PROGRESS</td>
                </s:if>
            </tr> 
        </s:iterator>

    
</table>    
</s:if>
<br/>

<h1><s:text name="history.list.title"/></h1>



<div id="my-timeline" class="timeline-default"></div>
