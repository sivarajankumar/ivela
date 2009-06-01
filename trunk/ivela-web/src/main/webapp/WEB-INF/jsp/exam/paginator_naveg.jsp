<%-- 
    Document   : paginator Exam
    Created on : Aug 7, 2008, 1:56:33 PM
    Author     : leonardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript">
    function forceSubmit(value){
        
        
        
        var form = document.getElementById('questionForm');
        
        new_element = document.createElement("input");
        new_element.setAttribute("type", "hidden");
        new_element.setAttribute("name", "page");
        new_element.setAttribute("id", "page");
        new_element.setAttribute("value", value);
        
        form.appendChild(new_element);
        
        <s:if test="question.type==questionObjective">
                var radioInput = document.getElementsByTagName("input");
         
                var check = false;
                for(i=0;i<radioInput.length;i++){
                    if(radioInput[i].getAttribute("type")=="radio"){
                        if(radioInput[i].checked==true){
                            check = true;
                        }
                    }
                }
                
                if(check){
                    form.submit();
                    return true;            
                }else{
                    document.getElementById('error').innerHTML = "Empty Field";
                    return false;
                }   
        </s:if><s:else>
            <s:if test="question.type==questionSubjective">
                var textArea = document.getElementById('subjectiveAnswer');
                if(textArea.value==''){
                    document.getElementById('error').innerHTML = "Empty Field";
                    return false;
                }else{
                    form.submit();
                    return true;    
                }
            </s:if><s:else>
                form.submit();
            </s:else>
        </s:else>
         
            
        
            }
     
            function validateRadio(obj){ 
                var result = false;
                for(var i=0; i<obj.length; i++){ 
                    if(obj[i].checked==true) 
                        result = true;
                }
    
                return result;
            }    
</script>

<div class="navegation">
    <input type="hidden" back="ash" >
    <s:if test="pageCount > 1"> 
        <s:if test="page == 1 && pageCount >= 3">
            <s:iterator status="stat" value="{page, (page + 1), (page + 2)}" id="it" > 
                <s:url id="link">
                    <s:param name="page" value="%{it}"/>
                </s:url>
                <s:if test="#it == page + 1">
                    <a class="naveg-next" href="#" onclick="javascript:forceSubmit('<s:property value="page + 1"/>');" ><s:text name="message.paginator.next" /></a>
                </s:if>
            </s:iterator>
        </s:if>
        
        <s:if test="page == 1 && pageCount == 2">
            <s:iterator status="stat" value="{page, (page + 1)}" id="it" >     
                <s:url id="link">
                    <s:param name="page" value="%{it}"/>
                </s:url>
                
                <s:if test="#it == page + 1">
                
                    <a class="naveg-next" href="#" onclick="javascript:forceSubmit('<s:property value="page + 1"/>');" ><s:text name="message.paginator.next" /></a>
                </s:if>
            </s:iterator>
        </s:if>
        
        <s:if test="page == pageCount && pageCount >= 3">   
            <s:iterator status="stat" value="{(page - 2), (page - 1), page}" id="it" >   
                <s:url id="link">
                    <s:param name="page" value="%{it}"/>
                </s:url>
                
                <s:if test="(#it == page - 1) && exam.navigable==true">
                    <a class="naveg-back" href="#" onclick="javascript:forceSubmit('<s:property value="page - 1"/>');" > <s:text name="message.paginator.previous" /></a>                                                           
                </s:if>
            </s:iterator>
        </s:if>
        
        <s:if test="page == pageCount && pageCount == 2"> 
            <s:iterator status="stat" value="{(page - 1), page}" id="it" >  
                <s:url id="link">
                    <s:param name="page" value="%{it}"/>
                </s:url>
                <s:if test="#it == page - 1">
                    <a class="naveg-back" href="#" onclick="javascript:forceSubmit('<s:property value="page - 1"/>');" > <s:text name="message.paginator.previous" /></a>                               
                </s:if>
            </s:iterator>
        </s:if>
        
        <s:if test="page > 1 && page < pageCount"> 
            
            <s:iterator status="stat" value="{(page -1), page, (page + 1)}" id="it" >        
                
                <s:url id="link">
                    <s:param name="page" value="%{it}"/>
                </s:url>
                
                <s:if test="(#it == page - 1) && exam.navigable==true">
                
                    <a class="naveg-back" href="#" onclick="javascript:forceSubmit('<s:property value="page - 1"/>');" ><s:text name="message.paginator.previous" /></a>     
                </s:if>
                
                <s:else>
                    
                    <s:if test="#it == page + 1">
                    
                        <a class="naveg-next" href="#" onclick="javascript:forceSubmit('<s:property value="page + 1"/>');" ><s:text name="message.paginator.next" /></a>
                    </s:if>
                </s:else>
                
            </s:iterator>  
        </s:if>
        
        
        
    </s:if>
    
     
    
    <s:if test="page == pageCount || pageCount==1">
       <a class="naveg-next" href="#" onclick="javascript:forceSubmit('<s:property value="page + 1"/>');" > Finish </a>     
    </s:if>
    <a class="navegation-back"  href="exam!listExamByUnitContent.action?unitContent.id=<s:property value="unitContent.id"/>&course.id=<s:property value="course.id"/>"><s:text name="exam.show.back" /></a>
</div>

