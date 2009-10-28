<%-- 
    Document   : paginator Exercise
    Created on : Aug 7, 2008, 1:56:33 PM
    Author     : leonardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>


<script type="text/javascript">
    function forceSubmitNext(value){
        var form = document.getElementById('questionForm');
        
        new_element = document.createElement("input");
        new_element.setAttribute("type", "hidden");
        new_element.setAttribute("name", "page");
        new_element.setAttribute("id", "page");
        new_element.setAttribute("value", value);
        form.appendChild(new_element); 

        form.submit();
    }
     
</script>

<div class="navegation" >
     
    <s:if test="pageCount > 1"> 
        <s:if test="page == 1 && pageCount >= 3">
            <s:iterator status="stat" value="{page, (page + 1), (page + 2)}" id="it" > 
                <s:url id="link">
                    <s:param name="page" value="%{it}"/>
                </s:url>
                <s:if test="#it == page + 1">
                    <a class="naveg-question" href="#" onclick="javascript:forceSubmitNext('<s:property value="page + 1"/>');" ><s:text name="message.paginator.next" /></a>
                </s:if>
            </s:iterator>
        </s:if>
        
        <s:if test="page == 1 && pageCount == 2">
            <s:iterator status="stat" value="{page, (page + 1)}" id="it" >     
                <s:url id="link">
                    <s:param name="page" value="%{it}"/>
                </s:url>
                
                <s:if test="#it == page + 1">
                
                    <a class="naveg-question" href="#" onclick="javascript:forceSubmitNext('<s:property value="page + 1"/>');" ><s:text name="message.paginator.next" /></a>
                </s:if>
            </s:iterator>
        </s:if>
        
         
        
         
        <s:if test="page > 1 && page < pageCount"> 
            
            <s:iterator status="stat" value="{(page -1), page, (page + 1)}" id="it" >        
                
                <s:url id="link">
                    <s:param name="page" value="%{it}"/>
                </s:url>
                
                 
                    
                    <s:if test="#it == page + 1">
                        
                        <a class="naveg-question" href="#" onclick="javascript:forceSubmitNext('<s:property value="page + 1"/>');" ><s:text name="message.paginator.next" /></a>
                    </s:if>
                 
                
            </s:iterator>  
        </s:if>
        
        <s:if test="page == pageCount">
            <a class="naveg-question" href="#" onclick="javascript:forceSubmitNext('<s:property value="page + 1"/>');" ><s:text name= "exercise.paginator.finish" /></a>     
        </s:if>
        
    </s:if>
</div>

