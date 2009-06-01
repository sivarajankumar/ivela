<%-- 
    Document   : input Question
    Created on : Jun 20, 2008, 10:40:05 AM
    Author     : emanuelle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script>
            function insertOptions(){
                
                var nextId = $('questionObjective').getElementsByTagName('input').length/2;
                
                lastTr = $('questionObjective').getElementsByTagName('tr')[$('questionObjective').getElementsByTagName('tr').length - 1];
                
                $('questionObjective').getElementsByTagName('tbody')[0].removeChild(lastTr);
                
                tr = document.createElement('tr');
                td1 = document.createElement('td');
                td2 = document.createElement('td');
                inputRadio = document.createElement('input');
                inputText = document.createElement('input');
                
                inputRadio.setAttribute("id", "question_radio"+nextId);
                inputRadio.setAttribute("type", "radio");
                inputRadio.setAttribute("name", "radio");
                inputRadio.setAttribute("value", nextId);
                
                inputText.setAttribute("id", "answerOption");
                inputText.setAttribute("name", "answerOption");
                inputText.setAttribute("type", "text");
                td1.appendChild(inputRadio);
                td2.appendChild(inputText);
                
                tr.appendChild(td1);
                tr.appendChild(td2);
                
                $('questionObjective').getElementsByTagName('tbody')[0].appendChild(tr);
                $('questionObjective').getElementsByTagName('tbody')[0].appendChild(lastTr);
                
                //$('tableQuestion').innerHTML += '<tr><td><input id="question_radio'+nextId+'" type="radio" value="'+nextId+'" name="radio"/></td><td><s:textfield name="answerOption" theme="simple"/></td></tr>';
                
            }
            
            function insertFile(){
                
                lastTr = $('questionAuditive').getElementsByTagName('tr')[$('questionAuditive').getElementsByTagName('tr').length - 1];
                
                $('questionAuditive').getElementsByTagName('tbody')[0].removeChild(lastTr);
                
                tr = document.createElement('tr');
                td1 = document.createElement('td');
                td2 = document.createElement('td');
                inputText = document.createElement('input');
                inputFile = document.createElement('input');
                
                inputText.setAttribute("id", "question_sentence");
                inputText.setAttribute("type", "text");
                inputText.setAttribute("name", "sentence");
                
                inputFile.setAttribute("id", "question_upload");
                inputFile.setAttribute("name", "upload");
                inputFile.setAttribute("type", "file");
                
                td2.appendChild(inputFile);
                td1.appendChild(inputText);
                
                tr.appendChild(td1);
                tr.appendChild(td2);
                
                $('questionAuditive').getElementsByTagName('tbody')[0].appendChild(tr);
                $('questionAuditive').getElementsByTagName('tbody')[0].appendChild(lastTr);
                
                //$('tableQuestionAuditive').innerHTML += '<tr><td><s:textfield name="sentence" theme="simple"/></td><td><s:file name="upload" theme="simple"/></td></tr>';
                
            }
            
            function selectTypeQuestion(typeQuestion){
                switch(typeQuestion){
                    case "1":
                        $('questionObjective').style.display = "none";
                        $('questionSubjective').style.display = "block";
                        $('questionAuditive').style.display = "none";
                        break;
                    case "2":
                        $('questionObjective').style.display = "block";
                        $('questionSubjective').style.display = "block";
                        $('questionAuditive').style.display = "none";
                        break;
                    case "3":
                        $('questionObjective').style.display = "none";
                        $('questionSubjective').style.display = "block";
                        $('questionAuditive').style.display = "block";
                        break;
                        
                }
                
            }
            
        </script>
    </head>
    <tr>
        <td colspan="2"><h1><s:text name="question.input.sessionTitle"/></h1></td>
    </tr>
    <s:form action="question!add.action" method="get" enctype="multipart/form-data">
        
        <s:select label="Question Type" name="question.type" onchange="selectTypeQuestion(this.value)" value ="selectedQuestionType" list="#{'1':'Subjective','2':'Objective','3':'Auditive', '4':'External'}"/>
        
        <table id="questionSubjective">
            <tr>
                <td><s:text name="question.input.text" /></td>
                <td><s:textarea id="textQuestion" rows="8" cols="60" name="questionText.text" /></td>
            </tr> 
            <tr>
                <td><s:text name="question.input.description" /></td>
                <td><s:textfield name="question.question" /></td>
            </tr>
        </table>
        <table id="questionObjective" style="display:none">
            <tr>
                <td colspan="2"><h2><s:text name="question.input.option" /></h2></td>
            </tr>
            <tr>
                <td><s:text name="question.input.correctOption"/></td>
                <td><s:text name="question.input.description"/></td>
            </tr>
            <tr>
                <td><input  id ="question_radio0" type="radio" value="0" checked name="radio"/></td>
                <td><s:textfield name="answerOption" theme="simple"/></td>
            </tr>
            <tr>
                <td><input  id ="question_radio1" type="radio" value="1" name="radio"/></td>
                <td><s:textfield name="answerOption" theme="simple"/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="button" onclick="insertOptions()" value="OK!" /></td>
            </tr>
        </table>
        
        <table id="questionAuditive" style="display:none">
            <tr>
                <td colspan="2"><h2><s:text name="question.input.selectTense" /></h2></td>
            </tr>
            <tr>
                <td colspan="2"><s:radio list="#{'0':'present simple<br/>', '1' :'present continuous<br/>', '2':'present perfect<br/>', '3':'past simple<br/>', '4':'past continuous<br/>', '5':'past perfect<br/>', '6':'future simple<br/>', '7' : 'future continuous<br/>','8' :'future perfect<br/>'}" name="radioTense"/></td>
            </tr>
            <tr>
                <td colspan="2"><h2><s:text name="question.input.sentence" /></h2></td>
            </tr>
            <tr>
                <td><s:text name="question.input.frase"/></td>
                <td><s:text name="question.input.audiofile"/></td>
            </tr>
            <tr>
                <td><s:textfield name="sentence" theme="simple"/></td>
                <td><s:file name="upload" theme="simple" value=""/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="button" onclick="insertFile()" value="OK!" /></td>
            </tr>
        </table>
        
        <br/>          
        
        
        <br/>
        <s:submit key="question.input.create"/>
    </s:form>
    
</html>
