<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/prototype/prototype.js"></script>
        <script type="text/javascript" src="js/scriptaculous/scriptaculous.js"></script>
        <script type="text/javascript" src="js/scriptaculous/effects.js"></script>
        <script type="text/javascript" src="js/scriptaculous/lightwindow.js"></script>
    <decorator:head />
  </head>
  <body>
     <div id="container">
            <div id="course-content">
            <decorator:body/>
            </div>
        </div>
    </div>
  </body>
</html>