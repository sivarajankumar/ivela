<%-- 
    Document   : paginator Forum
    Created on : Aug 7, 2008, 1:56:33 PM
    Author     : leonardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="pages">
    <s:if test="pageCount > 1"> 
        <a href="" class="pages-all"><s:property value="pageCount"/> page(s)</a>
        <ul class="pagination">
            
            <s:if test="page == 1 && pageCount >= 3">
                <s:iterator status="stat" value="{page, (page + 1), (page + 2)}" id="it" > 
                    <s:url id="linkTop">
                        <s:param name="page" value="%{it}"/>
                    </s:url>
                    
                    <s:if test="#it == page">
                        <li><span class="current"><s:property/></span></li>
                    </s:if>
                    <s:else>
                        <li><s:a href="%{linkTop}"><s:property/></s:a></li>
                    </s:else>
                </s:iterator>
                
                
            </s:if>
            
            <s:if test="page == 1 && pageCount == 2">
                <s:iterator status="stat" value="{page, (page + 1)}" id="it" >     
                    <s:url id="linkTop">
                        <s:param name="page" value="%{it}"/>
                    </s:url>
                    
                    <s:if test="#it == page">
                        <li><span class="current"><s:property/></span></li>
                    </s:if>
                    <s:else>
                        <li><s:a href="%{linkTop}"><s:property/></s:a></li>
                    </s:else>
                </s:iterator>
            </s:if>
            
            <s:if test="page == pageCount && pageCount >= 3">   
                
                
                <s:iterator status="stat" value="{(page - 2), (page - 1), page}" id="it" >   
                    <s:url id="linkTop">
                        <s:param name="page" value="%{it}"/>
                    </s:url>
                    
                    <s:if test="#it == page">
                        <li><span class="current"><s:property/></span></li>
                    </s:if>
                    <s:else>
                        <li><s:a href="%{linkTop}"><s:property/></s:a></li>
                    </s:else>
                </s:iterator>
            </s:if>
            
            <s:if test="page == pageCount && pageCount == 2"> 
                <s:iterator status="stat" value="{(page - 1), page}" id="it" >  
                    <s:url id="linkTop">
                        <s:param name="page" value="%{it}"/>
                    </s:url>
                    
                    <s:if test="#it == page">
                        <li><span class="current"><s:property/></span></li>
                    </s:if>
                    <s:else>
                        <li><s:a href="%{linkTop}"><s:property/></s:a></li>
                    </s:else>
                </s:iterator>
            </s:if>
            
            <s:if test="page > 1 && page < pageCount"> 
                
                
                <s:iterator status="stat" value="{(page -1), page, (page + 1)}" id="it" >        
                    <s:url id="linkTop">
                        <s:param name="page" value="%{it}"/>
                    </s:url>
                    
                    <s:if test="#it == page">
                        <li><span class="current"><s:property/></span></li>
                    </s:if>
                    <s:else>
                        <li><s:a href="%{linkTop}"><s:property/></s:a></li>
                    </s:else>
                </s:iterator>  
                
                
            </s:if>                
            <%--
            <s:if test="page != pageCount && pageCount > 3">
                <s:url id="linkTop">
                    <s:param name="page" value="%{pageCount}"/>
                </s:url>
                
                <li><s:a href="%{linkTop}"><s:property value="pageCount"/></s:a></li>  
            </s:if>
            --%>
        </ul>
    </s:if>
</div>

