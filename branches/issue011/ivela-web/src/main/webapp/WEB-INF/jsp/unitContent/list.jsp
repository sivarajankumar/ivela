<%-- 
    Document   : list
    Created on : Aug 22, 2008, 11:03:30 AM
    Author     : emanuelle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<link href="css/content_unit.css" rel="stylesheet" type="text/css" />

<div id="breadcrumb">
    <p><s:text name="breadcrumb.youAreHere"/></p>
    <ul>
        <li><a href="home.action" title="<s:property value="home.title"/>"><s:text name="home.name"/></a></li>
        <li><a href="discipline!listByCourse.action?course.id=<s:property value="grade.course.id"/>&grade.id=<s:property value="grade.id"/>" title="<s:property value="grade.course.name"/>"><s:property value="grade.course.name"/></a></li>
        <li class="current" title="Presente Simples"><s:text name="Presente Simples"/></li>
    </ul>
</div>
<h1><s:property value="grade.course.name"/></h1>
<h2><s:property value="unit.discipline.name"/></h2>
<div id="col-1-discipline">
    <dl class="menu-discipline">
        <s:iterator value="unitList" status="stat">
            <dt><s:property value="name" /></dt>
            <s:iterator value="%{list[#stat.index]}">
                <dd><a href=""><s:property  value="title"/></a></dd>
            </s:iterator>
        </s:iterator>
        <dt><a class="exercise" href="question!show.action?question.id=6">Exercise 1</a></dt>
        <dt><a class="exercise" href="question!show.action?question.id=7">Exercise 2</a></dt>
        <dt><a class="test" href="">Test</a></dt>
        <dt><a class="test" href="message!inbox.action">Message</a></dt>
        <dt><a class="test" href="repository!list.action?gradeId=<s:property value="grade.id" />">Multimedia library</a></dt>
        <dt><a class="forum" href="forum!list.action?grade.id=<s:property value="grade.id" />">Forum</a></dt>
        <dt><a class="chat" href="">Chat</a></dt>
    </dl>
</div>
<div id="col-2-discipline"  style="border:10px solid #c0dafb; padding: 20px;">
    <div  style="border-bottom: 1px solid #ccc; height: 50px; margin-bottom: 50px" >
        <img src="images/logo-partner2.gif" />
    </div>
    <div class="div-discipline">
        <h2><s:property value="unitContentList[0].title"/></h2> 
        <br />
        <p>O simple presente é formado pelo verbo em sua forma original na maioria das pessoas, com exceção da terceira pessoa do singular. </p>
        <p>Veja estas frases com o verbo drink (beber) como exemplo:<br />
            <br />
            I <strong>drink</strong> orange juice every day. <br />
            You <strong>drink</strong> beer in the bar. <br />
            We <strong>drink</strong> champagne in New Year's Eve. <br />
            <br />
            Entretanto, a 3ª pessoa do singular tem necessidade de uma conjugação. <br />
            <br />
            He <strong>drinks</strong> wine with his parents. <br />
            She <strong>drinks</strong> water after gym. <br />
            <br />
            É um detalhe bastante pequeno, mas precisa ser constantemente  recordado, pois é muito comum nos esquecermos de flexionar o verbo. Há  alguns casos em que acrescentar o &quot;s&quot; Ã  terceira pessoa do singular  exigirá mudanças no próprio verbo. <br />
            <br />
            
            <p><b>facts (something is generally known to be true)</b></p>
            <br/>
            <dd>
                <table>
                    <tr>
                        <td ><img src="images/simple-present-2.png" alt="" height="148" width="190" /><br /><span xml:lang="en" lang="en">The sun sets in the west.</span></td>
                        <td>
                            <p>The sun sets in the west.</p>
                        </td>
                    </tr>
                </table>
            </dd>
            <br/>
            <p><b>action in the present taking place once, never or several times</b></p>
            <br/>
            <dd>
                <table>
                    <tr>
                        <td ><img src="images/simple-present-3.png" alt="" height="148" width="190" /><br /><span xml:lang="en" lang="en">The sun sets in the west.</span></td>
                        <td>
                            <p>Colin always plays soccer on Tuesdays.</p>
                        </td>
                    </tr>
                </table>
            </dd>
            <br/>
            <p><b>actions in the present taking place one after another</b></p>
            <br/>
            <dd>
                <table>
                    <tr>
                        <td ><img src="images/simple-present-4.png" alt="" height="148" width="190" /><br /><span xml:lang="en" lang="en">The sun sets in the west.</span></td>
                        <td>
                            <p>She takes her bag and leaves.</p>
                        </td>
                    </tr>
                </table>
            </dd>
            <br/>
            <strong>Vebos terminados em Y</strong>: Os verbos terminado sem Y precedido de consoante, como study ( <a href="http://www.brasilescola.com/ingles/simple-present.htm#" onclick="hwClick17877817691375(-1105360623);return false;" onmouseover="hw17877817691375(event, this, '-1105360623'); this.style.cursor='hand'; this.style.textDecoration='underline'; this.style.borderBottom='solid';" onmouseout="hideMaybe(event, this); this.style.cursor='hand'; this.style.textDecoration='underline'; this.style.borderBottom='dotted 1px'; " oncontextmenu="return false;">estudar</a>),  try (tentar), fly (voar) e outros, perderão o Y, que será substituição do  por &quot;ie&quot; + &quot;s&quot;, ficando então. Ex: He studies, she studies. <br />
            <br />
            Os verbos também terminados em Y são que precedidos de vogal, como play  (jogar), say (dizer), não terão esta alteração. A sua flexão se farão como qualquer outro verbo. <br />
            <br />
            Ex: I play, he plays, she plays, they play. <br />
            You say, he says, she says, we say. <br />
            <br />
            <strong>Verbos terminados em S, SH, CH, Z, X, O</strong>: Os verbos  terminados com estas letras, como guess (adivinhar), push (empurrar),  watch (assistir), buzz (zumbir), receberÃ£o umÂ &quot;e&quot; antes do &quot;s&quot; na  terceira pessoa do singular. <br />
            <br />
            I watch the games but he watches a different movie every night. <br />
            <br />
            <strong>Uso</strong><br />
            <br />
        O simple present é um tempo verbal fácil de se identificar, pois eles  usado em poucas situações e elas são facilmente percebidas através de  algumas palavras que aparecem com certa frequencia: os advérbios de  tempo. </p>
        <br/>
        <p class="description-discipline"></p>
    </div>
</div>               

