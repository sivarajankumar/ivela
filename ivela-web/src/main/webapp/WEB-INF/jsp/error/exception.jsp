<%-- 
    Document   : exception
    Created on : Jul 2, 2008, 11:46:17 AM
    Author     : marcus
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<html>
    <body>
        <h2><s:text name="error.exception.error"/></h2>
        <p>
            <s:text name="error.exception.contact"/>
        </p>
        <hr/>
        <h3><s:text name="error.exception.message"/></h3>
        <s:actionerror/>
        <p>
            <s:property value="%{exception.message}"/>
        </p>
        <hr/>
        <h3><s:text name="error.exception.details"/></h3>
        <p>
            <s:property value="%{exceptionStack}"/>
        </p>
    </body>
</html>
