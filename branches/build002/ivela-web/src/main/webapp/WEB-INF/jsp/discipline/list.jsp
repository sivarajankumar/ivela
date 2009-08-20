<%--
#############################################################################################
# Copyright(c) 2008-2009 by IBM Brasil Ltda and others                                      #
# This file is part of ivela project, an open-source                                        #
# Program URL   : http://code.google.com/p/ivela/                                           #  
#                                                                                           #
# This program is free software; you can redistribute it and/or modify it under the terms   #
# of the GNU General Public License as published by the Free Software Foundation; either    #
# version 3 of the License, or (at your option) any later version.                          #
#                                                                                           #
# This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; #
# without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. #
# See the GNU General Public License for more details.                                      #  
#                                                                                           #
#############################################################################################
# File: list.jsp                                                                            #
# Document: list Discipline                                                                 # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 15-JUL-2008 - Nelson                            - XXXXXX - Initial Version                #
# 15-JUL-2009 - Rafael Lagoa (Instituto Eldorado) - 000012 - Remove breadcrumb area         #
#############################################################################################
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<link href="RenderServlet?file=/globals/css/base.css" rel="stylesheet" type="text/css" />
<link href="RenderServlet?file=/globals/css/internas.css" rel="stylesheet" type="text/css" />
<%--
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <s:head />
        <link href="css/disciplines.css" rel="stylesheet" type="text/css" />
    </head>
    <cal:head />
    
    <body>
        <h1><s:property value="course.name" /></h1>
        
        <div id="col-1-discipline">
            <h3><s:text name="course.list.status"/></h3>
            <div id="box-stats">
                <p><s:text name="course.list.progress"/></p>
                <span class="box-bar"><img height="11" width="35" src="images/progress-bar/bar.gif" alt="progress bar" /></span>
                <span class="progress">50%</span>
                <br />
                <span class="period">Deadline to end: <em>02/15/2009</em></span>
                <p class="last-acess"><s:text name="course.list.lastAcess"/> <span class="list-acess-date">23/08/2008 at 2:00pm</span></p>
                <br class="clear" />
            </div>
            <dl class="menu-discipline">
                <dt><a class="disciplines" href=""><s:text name="course.list.disciplines"/></a></dt>
                <s:iterator value="disciplineList">
                    <s:url action="unit" method="listByDiscipline" id="unitList">
                        <s:param name="discipline.id" value="id" />
                    </s:url>
                    <s:url action="unitContent" method="listByUnit" id="unitContentUrl">
                        <s:param name="unit.id" value="id" />
                    </s:url>
                    <dd><!--s:a href="%{unitContentUrl}"--><s:property value="name" /><!--/s:a--></dd>
                </s:iterator>
               <%-- <dt><a class="exercise" href="question!show.action?question.id=6">Exercise 1</a></dt>
                <dt><a class="exercise" href="question!show.action?question.id=7">Exercise 2</a></dt>
                <dt><a class="test" href="">Test</a></dt>
                <dt><a class="test" href="message!inbox.action">Message</a></dt>
                <dt><a class="test" href="repository!list.action?gradeId=<s:property value="grade.id" />">Repository</a></dt>
                <dt><a class="forum" href="forum!list.action?grade.id=<s:property value="grade.id" />">Forum</a></dt>
                <dt><a class="chat" href="">Chat</a></dt>--%>
                <%--
            </dl>
        </div>
        
        <div id="col-2-discipline">
            
            <s:iterator value="disciplineList" status="dstat" id="dit">
            
                <div class="div-discipline">
                    <h2 class="testh2"><s:property value="name" /></h2>
                    <span class="box-bar-discipline">
                        <img height="11" width="01" src="images/progress-bar/bar.gif" alt="progress bar" />
                    </span>
                    <span class="progress-discipline">10%</span>
<p class="description-discipline">--%>
<!--
Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type.
                        -->
                        <%--
                    </p>
                    <ul class="list-unit">
                        <s:iterator value="%{unitList[#dstat.index]}" status="ustat" id="uit">
                            <s:url action="unitContent" method="listByUnit" id="unitContentUrl">
                                <s:param name="unit.id" value="id" />
                                <s:param name="discipline.id" value="disciplineList[#dstat.index].id" />
                            </s:url>
                            <li>Unidade <s:property value="%{#ustat.index + 1}" /> - <s:a href="%{unitContentUrl}"><s:property value="name" /></s:a></li>
                        </s:iterator>
                    </ul>
                </div>
                
            </s:iterator>
            
        </div>               
        <br class="clear" />  
        
    </body>
</html>
--%>
<%--
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <s:head theme="ajax"/>
        <link href="css/disciplines.css" rel="stylesheet" type="text/css" />
    </head>
    <cal:head />
    
    <body>
        <s:form>
        
        <dojo:AutoComplete action="getBooks.jsp" textboxId="bookName" formId="bookForm"/></dojo:autocomplete>
        
        </s:form>
    </body>
</html>
--%>
<div id="content">
    <s:actionerror />
    <div id="col-1-course">
        <div class="current-course">
            <h1>Curso: Administração</h1>
            <h2>Disciplina: Direito na Administração</h2>
        </div>
        <div class="units-container">
            <div class="units-content">
                <div class="partner">
                    
                    <p>Parceiro</p>
                    <img src="images/partner2.gif" />
                </div>
                <h3 class="openned">Unidade 1</h3>
                <div class="list-class">
                    <ul>
                        <li><a href="#">Aula 1 - DB2 Básico</a></li>
                        
                        <li><a href="#">Aula 2 - Java com NetBeans</a></li>
                        <li><a href="#">Aula 3 - Tableless no Dreamweaver</a></li>
                        <li><a href="#">Aula 4 - Redes Sensores</a></li>
                    </ul>
                </div> 
                <h3 class="closed">Unidade 3</h3>
                <h3 class="closed">Unidade 4</h3>
                
                <h3 class="closed">Unidade 5</h3>
            </div>                  	
        </div>
        
    </div>
    <!-- end col-1-home -->
            
    <div id="col-2-course">
        <div class="lesson-menu">
            <ul>
                <li class="current"><a href="#" title="Aula">Aula</a></li>
                
                <li><a href="#" title="Exercícios">Exercícios</a></li>
                <li><a href="#" title="Avaliações">Avaliações</a></li>
                <li><a href="#" title="Biblioteca Multimídia">Biblioteca Multimídia</a></li>
                <li><a href="#" title="Fórum">Fórum</a></li>
                <li><a href="#" title="Chat">Chat</a></li>
            </ul>
            
        </div>
        <div class="lesson-content">
            
        </div>
        
        
    </div>
    <!-- end col-2-home -->
    <br class="clear" />
</div>
<!-- end content -->
<br class="clear" />