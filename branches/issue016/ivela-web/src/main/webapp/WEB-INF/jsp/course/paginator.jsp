<%-- 
    Document   : paginator
    Created on : Aug 7, 2008, 1:56:33 PM
    Author     : leonardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="navegation">
    <s:if test="pageCount > 1"> 
        <s:if test="page == 1 && pageCount >= 3">
            <s:iterator status="stat" value="{page, (page + 1), (page + 2)}" id="it" > 
                <s:url id="link">
                    <s:param name="page" value="%{it}"/>
                </s:url>
                <s:if test="#it == page + 1">
                    <s:a href="%{link}" cssClass="next"><s:text name="pagination.next" /></s:a>
                </s:if>
            </s:iterator>
        </s:if>

        <s:if test="page == 1 && pageCount == 2">
            <s:iterator status="stat" value="{page, (page + 1)}" id="it" >     
                <s:url id="link">
                    <s:param name="page" value="%{it}"/>
                </s:url>

                <s:if test="#it == page + 1">
                    <s:a href="%{link}" cssClass="next"><s:text name="pagination.next" /></s:a>                            
                </s:if>
            </s:iterator>
        </s:if>

        <s:if test="page == pageCount && pageCount >= 3">   
            <s:iterator status="stat" value="{(page - 2), (page - 1), page}" id="it" >   
                <s:url id="link">
                    <s:param name="page" value="%{it}"/>
                </s:url>

                <s:if test="#it == page - 1">
                    <s:a href="%{link}" cssClass="previous"><s:text name="pagination.previous" /></s:a>                            
                </s:if>
            </s:iterator>
        </s:if>

        <s:if test="page == pageCount && pageCount == 2"> 
            <s:iterator status="stat" value="{(page - 1), page}" id="it" >  
                <s:url id="link">
                    <s:param name="page" value="%{it}"/>
                </s:url>
                <s:if test="#it == page - 1">
                    <s:a href="%{link}" cssClass="previous"><s:text name="pagination.previous" /></s:a>                            
                </s:if>
            </s:iterator>
        </s:if>

        <s:if test="page > 1 && page < pageCount"> 
            <s:iterator status="stat" value="{(page -1), page, (page + 1)}" id="it" >        
                <s:url id="link">
                    <s:param name="page" value="%{it}"/>
                </s:url>
                <s:if test="#it == page - 1">
                    <s:a href="%{link}" cssClass="previous"><s:text name="pagination.previous" /></s:a>
                </s:if>
                <s:else>
                    <s:if test="#it == page + 1">
                        <s:a href="%{link}" cssClass="next"><s:text name="pagination.next" /></s:a>
                    </s:if>
                </s:else>
            </s:iterator>  
        </s:if>                
    </s:if>
</div>

