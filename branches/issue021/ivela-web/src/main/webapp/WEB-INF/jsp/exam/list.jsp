<%-- 
    Document   : list Exam
    Created on : Jun 19, 2008, 3:03:24 PM
    Author     : Maristella Myrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="exam.list.pageTitle" /></title>
        <link href="css/internas.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/prototype/prototype.js"></script>
        <script type="text/javascript" src="js/util/ajax.js"></script>
        <script type="text/javascript" src="js/util/util.js"></script>
        <style type="text/css">
            .box-error {width: 600px; height:auto; background:#FFFFCC; border:1px solid #ff7202; padding: 5px;}
.box-error h4 {width: 500px; height:auto; margin-top: 0px; margin-bottom: 2px; font: bold 17px Arial, Helvetica, sans-serif; color:#ff3333; background: url(../images/icon/icon-error.gif) no-repeat 0 3px; padding-left: 2px;}

.box-error ul {list-style-type: none; color:#333333; font: 12px Arial, Helvetica, sans-serif;}
.box-error ul li{ background: url(../images/icon/icon-item-error.gif) no-repeat 0 5px; padding-left: 20px; margin-bottom: 10px;}
        </style>
        <script type="text/javascript">
            function getRequisitesFromExam(longId){
               
               
               var questionNumber = getJsonFromUrl('exam!getExamQuestionNumberJson.action?exam.id=' + longId);
                
               if(questionNumber.questionNumber!=0){
                   //alert(1);
                       var requisites = getJsonFromUrl('exam!getExamRequisitsFromJson.action?exam.id=' + longId);
                       var exists= requisites.vector!='';

                       //var requisitesNumber = requisites.vector.exercises.length;

                       if(exists==true){
                           var reqString = "<br /> <br />";
                           
                               for(i=0;i<requisites.vector.exams.length;i++){
                               
                                    var text = requisites.vector.exams[i];
                                    text= text.replace("#","<br />");
                                    
                                    reqString += "<b> " +text+ " </b>"
                               }
                           


                           $('reqError').innerHTML = "<div class='box-error'><h4><s:text name="exercise.list.alert" /></h4><ul> "+"<s:text name="exams.list.requisites1"/>" + reqString + "<br />" + "<s:text name="exams.list.requisites2"/>"+"</ul></div>";



                       }else{

                           var url = 'exam!showPaging.action?unitContent.id='+<s:property value="unitContent.id"/>+'&page=1&exam.id=' + longId+'&course.id='+<s:property value="course.id"/>;
                            window.location.href=url;
                       }
                        
               }else{
                   $('reqError').innerHTML = "<div class='box-error'><h4><s:text name="exercise.list.alert" /></h4><ul> "+" <s:text name="exam.list.noExam1" />"
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

        </script>
        <s:head />
    </head>
    <s:actionerror />
    <div id="col-2-course">
        
        <div class="lesson-content">
            <div class="list-exe" style="margin-top: 100px;" >
            <s:if test="examListFromUnitContent.size()==0">
                <h2><s:text name="exam.list.noExam" /></h2>
            </s:if><s:else>
                <span id="reqError" class="error" >
                        </span>
                <% boolean completed[] = (boolean[]) request.getSession().getAttribute("COMPLETED");
            int i = 0;
                %>
                
                <s:iterator value="examListFromUnitContent" status="count">
                    <s:url id="listQuestionExamUrl" action="exam" method="showPaging">
                        <s:param name="page" value="1"/>
                        <s:param name="exam.id" value="id"/>
                        
                    </s:url>
                    
                    
                    <%if (completed[i]) {%>
                    <div class="exame-box-disable">
                        <span><s:property value="%{(#count.index) +1}" /> </span>
                        <h3><s:property value="title" /></h3>
                    </div>
                    
                    
                    <%} else {%>
                    
                    <div class="exame-box-avaliable">
                        <%--
                        <span><s:a href="%{listQuestionExamUrl}"><s:property value="%{(#count.index) +1}" /></s:a></span>
                        --%>
                        <span><a id="showExamJson" onclick="getRequisitesFromExam('<s:property value="id"/>')" href="#"  ><s:property value="%{(#count.index) +1}" /></a>
                                    
                                </span>
                        <h3><s:property value="title" /></h3>
                    </div>
                    <%}
            i++;%>
                </s:iterator>
            </s:else>
             <br class="clear" /> 
        </div>
        </div>
    </div>
</html>
