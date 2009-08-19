<%-- 
    Document   : list Exercise
    Created on : Jun 24, 2008, 2:31:54 PM
    Author     : Maristella Myrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="exercise.list.pageTitle" /></title>
        <link href="RenderServlet?file=/globals/css/internas.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/prototype/prototype.js"></script>
        <script type="text/javascript" src="js/util/ajax.js"></script>
        <script type="text/javascript" src="js/util/util.js"></script>
        <style type="text/css">
            .box-error {width: 600px; height:auto; background:#FFFFCC; border:1px solid #ff7202; padding: 5px;}
            .box-error h4 {width: 500px; height:auto; margin-top: 0px; margin-bottom: 0px; font: bold 17px Arial, Helvetica, sans-serif; color:#ff3333; background: url(../images/icon/icon-error.gif) no-repeat 0 3px; padding-left: 2px;}
            
            .box-error ul {list-style-type: none; color:#333333; font: 12px Arial, Helvetica, sans-serif;}
            .box-error ul li{ background: url(../images/icon/icon-item-error.gif) no-repeat 0 5px; padding-left: 20px; margin-bottom: 10px;}
        </style>
        <script type="text/javascript">
            function getRequisitesFromExercise(longId){
               
               
                var questionNumber = getJsonFromUrl('exercise!getExerciseQuestionNumberJson.action?exercise.id=' + longId);
                
                if(questionNumber.questionNumber!=0){
                    var requisites = getJsonFromUrl('exercise!getExerciseRequistedJson.action?exercise.id=' + longId);
                    var exists= requisites.vector!='';

                    //var requisitesNumber = requisites.vector.exercises.length;

                    if(exists==true){
                        var reqString = " ";

                        for(i=0;i<requisites.vector.exercises.length;i++){
                                
                            var text = requisites.vector.exercises[i];
                            text= text.replace("#","<br />"); 
                            reqString += "<b> " +text+ " </b>"
                        }
                           
                        reqString+= " "; 
                        $('reqError').innerHTML = "<div class='box-error'><h4><s:text name="exercise.list.alert" /></h4><ul> "+"<s:text name="exercise.list.requisites1"/><br /><br />" + reqString + "<br />" + "<s:text name="exercise.list.requisites2"/>"+"</ul></div>";



                    }else{

                        var url = 'exercise!showPaging.action?unitContent.id='+<s:property value="unitContent.id"/>+'&page=1&exercise.id=' + longId+'&course.id='+<s:property value="course.id"/>;
                        window.location.href=url;
                    }
                        
                }else{
                    $('reqError').innerHTML = "<div class='box-error'><h4><s:text name="exercise.list.alert" /> </h4> " +"<s:text name="exercise.list.noExercise1" />"
                }
               
            }
            
            function getJsonFromUrl(url){
                var json;
                new Ajax.Request(url,
                {
                    method:'get',
                    requestHeaders: {Accept: 'application/json'}, 
                    asynchronous: false,
                    onSuccess: function(transport) {
                        json = transport.responseText.evalJSON(true);
                    },
                    onFailure: function() { alert('Message: Something went wrong...') },
                    onException:function(request, exception) {         
                        // Temporary Solution that checks for a bad formed and see if
                        // it is the login page, so redirects.                 
                        var message = exception.message;
                        if(message.match(/Badly formed JSON string/)!= null) {
                            if (message.match(/login-container/) != null) {
                                document.location = "./home.action";                                
                            }
                        }
                   }
                });
                return json;
            }
            
            function getCheckBoxes(exerciseId){
                var inputs = $("requisitesForm").getElementsByTagName("input");
                var checkedOnes = exerciseId+"a";
                for(i=0;i<inputs.length;i++){
                    if(inputs[i].getAttribute('type') == 'checkbox' && inputs[i].checked==true){
                        checkedOnes += "b"+inputs[i].value;
                    }
                }
                //alert(checkedOnes);
                getJsonFromUrl('exercise!addRequisitesWithJason.action?requisitesString=' + checkedOnes)
            }

        </script>
        <s:head />
    </head>
    
    <div id="container">
        
        <div id="content">
            
            <div id="col-2-course">
                <div class="lesson-content">
                    
                    <div class="list-exe" style="margin-top: 100px;" >
                        
                        <s:if test="exerciseListFromUnitContent.size()==0">
                            <h2><s:text name="exercise.list.noExercise" /></h2>
                        </s:if><s:else>
                        
                                               
                        
                            <span id="reqError" class="error" >
                            </span>
                            <% int completed[] = (int[]) request.getSession().getAttribute("COMPLETED");
            int i = 0;%>
                            <s:iterator value="exerciseListFromUnitContent" status="count">
                                <s:url id="listQuestionExerciseUrl" action="exercise" method="showPaging">
                                    <s:param name="page" value="1"/>
                                    <s:param name="exercise.id" value="id"/>
                                    
                                </s:url>
                                
                                <%if (completed[i] == 0) {%>
                                <div class="exame-box-disable">
                                    <span><s:property value="%{(#count.index) +1}" /> </span>
                                    <h3><s:property value="title" /></h3>
                                </div>
                                <%} else {%>
                                
                                
                                <div class="exame-box-avaliable">
                                    <%--<span><s:a href="%{listQuestionExerciseUrl}"><s:property value="%{(#count.index) +1}" /></s:a></span>
                                    --%>
                                    <span><a id="showExerciseJson" onclick="getRequisitesFromExercise('<s:property value="id"/>')" href="#"  ><s:property value="%{(#count.index) +1}" /></a></span>
                                    <h3><s:property value="title" /></h3>
                                    <p><s:text name="exercise.list.avaliableChances" />: <%=completed[i]%></p>
                                </div>
                                
                                <%}
            i++;%>
                                
                            </s:iterator>
                        </s:else>
                        <br class="clear" /> 
                        
                    </div>
                </div>
            </div>
        </div>
        <%--
        ----------------------------<br />
        Teste: Inserindo NAO APAGUE !!!! Requisitos <br />
        ----------------------------<br />
        
        <s:form action="exercise!addRequisites.action" method="get" id="requisitesForm">
            <table>
                <s:iterator value="exerciseListFromUnitContent">  
                            <tr>
                                <td>                       
                                    <input type="checkbox" name="requisitesChecked" value="<s:property value="id"/>"> <s:property value="title"/>
                                </td>

                            </tr>
               </s:iterator>
           </table>
           <a href="#" onclick="javascript:getCheckBoxes('1');" > OK </a>
       </s:form>
        --%>
    </div>
</html>
