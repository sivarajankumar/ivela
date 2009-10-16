<%--
#############################################################################################
# Copyright(c) 2008-2009 by IBM Brasil Ltda and others                                      #
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
# File: list.jsp                                                                            #
# Document: list Forum                                                                      # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 15-JUL-2008 - Nelson                            - XXXXXX - Initial Version                #
# 17-JUN-2009 - Otofuji (Instituto Eldorado)      - 000007 - IE7 compatibility              #
# 15-JUL-2009 - Rafael Lagoa (Instituto Eldorado) - 000012 - Remove breadcrumb area         #
#############################################################################################
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

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

<br>
<br> 

<h1><s:text name="history.list.title"/></h1>



<div id="my-timeline" class="timeline-default"></div>
