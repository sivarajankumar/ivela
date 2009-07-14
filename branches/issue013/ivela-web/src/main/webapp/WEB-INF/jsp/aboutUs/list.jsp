<%-- 
    Document   : aboutus
    Created on : Dec 16, 2008, 4:56:59 PM
    Author     : emanuelle
--%>


<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>


<html>
    <h1><s:text name="list.about.us"/></h1> <br />
    <input class="btn-back" type="button" onclick="history.back(1)" value="<s:text name="systemUser.input.btnBack"/>" />
    <br />
        
    
    <iframe id="html" frameborder="0" width="800" height="1500" src="RenderServlet?file=globals/html/aboutus.html"></iframe>
    <br />
    <input class="btn-back" type="button" onclick="history.back(1)" value="<s:text name="systemUser.input.btnBack"/>" />
</html>
