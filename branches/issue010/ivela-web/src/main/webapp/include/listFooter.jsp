<%-- 
    Document   : listFooter
    Created on : Aug 7, 2008, 1:56:33 PM
    Author     : marcus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">


<s:text name="pagination.found"/> <s:property value="count"/> <s:text name="pagination.results"/> <br>

<s:if test="pageCount > 1">    
    Navegação: 
    
    <s:if test="page == 1 && pageCount >= 3">
        <s:iterator status="stat" value="{page, (page + 1), (page + 2)}" id="it" >        
            <s:if test="#it == page">
                <a href="?page=<s:property/>"><strong><s:property/></strong></a> 
            </s:if>
            <s:else>
                <a href="?page=<s:property/>"><s:property/></a> 
            </s:else>
        </s:iterator>
        
        <a href="?page=<s:property value="(page + 1)"/>"><s:text name="pagination.next"/></a> 
        <a href="?page=<s:property value="pageCount"/>"><s:text name="pagination.last"/></a>    
    </s:if>
    
    <s:if test="page == 1 && pageCount == 2">
        <s:iterator status="stat" value="{page, (page + 1)}" id="it" >        
            <s:if test="#it == page">
                <a href="?page=<s:property/>"><strong><s:property/></strong></a> 
            </s:if>
            <s:else>
                <a href="?page=<s:property/>"><s:property/></a> 
            </s:else>
        </s:iterator>
        
        <a href="?page=<s:property value="(page + 1)"/>"><s:text name="pagination.next"/></a> 
        <a href="?page=<s:property value="pageCount"/>"><s:text name="pagination.last"/></a>  
    </s:if>
    
    <s:if test="page == pageCount && pageCount >= 3">    
        <a href="?page=<s:property value="1"/>"><s:text name="pagination.first"/></a>
        <a href="?page=<s:property value="(page - 1)"/>"><s:text name="pagination.previous"/></a> 
        
        <s:iterator status="stat" value="{(page - 2), (page - 1), page}" id="it" >        
            <s:if test="#it == page">
                <a href="?page=<s:property/>"><strong><s:property/></strong></a> 
            </s:if>
            <s:else>
                <a href="?page=<s:property/>"><s:property/></a> 
            </s:else>
        </s:iterator>
    </s:if>
    
    <s:if test="page == pageCount && pageCount == 2">    
        <a href="?page=<s:property value="1"/>"><s:text name="pagination.first"/></a>
        <a href="?page=<s:property value="(page - 1)"/>"><s:text name="pagination.previous"/></a> 
        
        <s:iterator status="stat" value="{(page - 1), page}" id="it" >        
            <s:if test="#it == page">
                <a href="?page=<s:property/>"><strong><s:property/></strong></a> 
            </s:if>
            <s:else>
                <a href="?page=<s:property/>"><s:property/></a> 
            </s:else>
        </s:iterator>
    </s:if>
    
    <s:if test="page > 1 && page < pageCount">
        <a href="?page=<s:property value="1"/>"><s:text name="pagination.first"/></a>
        <a href="?page=<s:property value="(page - 1)"/>"><s:text name="pagination.previous"/></a> 
        
        <s:iterator status="stat" value="{(page -1), page, (page + 1)}" id="it" >        
            <s:if test="#it == page">
                <a href="?page=<s:property/>"><strong><s:property/></strong></a> 
            </s:if>
            <s:else>
                <a href="?page=<s:property/>"><s:property/></a> 
            </s:else>
        </s:iterator>
        
        <a href="?page=<s:property value="(page + 1)"/>"><s:text name="pagination.next"/></a> 
        <a href="?page=<s:property value="pageCount"/>"><s:text name="pagination.last"/></a>  
    </s:if>    
</s:if>