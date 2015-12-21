## Colocando arquivos diversos numa página de conteúdo ##

---


### Estrutura de diretórios ###

---

A aplicação utiliza uma pasta do servidor para gerenciar os conteúdos das lições. O acesso a essa pasta é feito internamente pela aplicação para garantir a segurança dos arquivos localizados nessas pastas.

A estrutura de diretórios abaixo fica dentro dessa pasta no servidor e pode ser utilizada pelos conteúdos através de servlts especificos, como veremos posteriormente

```
/curso (id) 
+---/disciplina (id)
|   +---/unidade (id)
|       +---/lição (id)
|           +---/css     - CSS especifico das paginas da lição
|           +---/js      - Java script especifico das paginas da lição
|           +---/julius  - arquivos do Julius para a lição
|           +---/xml     - arquivos xml para contabilizar o score da lição
|           +---/images  - todas as imagens da lição
|           +---/audio   - todos os arquivos de audio (ogg,mp3,outros) da lição
|           +---/video   - todos os videos da lição
+---/css     - CSS para todas as paginas do curso
+---/js      - Java Script para todas as paginas do curso
+---/images  - Imagens comuns para todas as paginas do curso
```

Para criar um conteúdo do ivela é necessário saber os id´s que compoem a lição. Por exemplo:

```
Curso de Inglês --> Modulo 1 --> Unidade 1 --> Lição 1
    (1)                (1)          (1)          (1)
diretorio desta lição => 1/1/1/1

Curso Exemplo --> Modulo 1 -->  Unidade 1 -->  Lição 1
    (12)            (23)          (58)          (184)
diretorio desta lição => 12/23/58/184
```

Coloque todo o conteúdo da lição em uma pasta que será zipada em um arquivo para ser enviada pro Ivela. Essa pasta deve conter os seguintes arquivos e subdiretórios

```
+---/css     - CSS especifico das paginas da lição
+---/js      - Java script especifico das paginas da lição
+---/julius  - arquivos do Julius para a lição
+---/xml     - arquivos xml para contabilizar o score da lição
+---/images  - todas as imagens da lição
+---/audio   - todos os arquivos de audio (ogg,mp3,outros) da lição
+---/video   - todos os videos da lição
index.html   - página principal do curso 
*.html       - páginas de conteudo (use a nomenclatura que preferir)
```

Crie o arquivo contentpackage.ivela.xml com o seguinte conteudo:
```
<?xml version="1.0" encoding="ISO-8859-1"?>
<ivela>
<header doctype="plugin" docsubtype="contentpackage"/>
    <payload>
	<contentpackage course="id_curso" discipline="id_disciplina" unit="id_unidade">
		<filesystem value="/index.html"/>
		<filesystem value="/*.html"/>
		<filesystem value="/css/"/>
		<filesystem value="/js/"/>
		<filesystem value="/images/"/>			
		<filesystem value="/julius/"/>
		<filesystem value="/xml/"/>
		<filesystem value="/audio/"/>
		<filesystem value="/video/"/>
	</contentpackage>
   </payload>
</ivela>
```

Use o index.html conforme o modelo abaixo:

**lição é `1/1/1/1`**

**todos os arquivos estão nos subdiretorios apropriados (js, audio , images)**

**Para acessar esses arquivos utilize `RenderServlet?file=1/1/1/1/<diretorio /<arquivo>`** Ex: `RenderServlet?file=1/1/1/1/images/img.jpg`
**Substitua em `showPage('RenderServlet?file=1/1/1/1/part1.html')` o nome do html que
será carregado como a primeira página do conteúdo
```
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" >

<script type="text/javascript" src="RenderServlet?file=1/1/1/1/js/prototype.js"></script>
<script type="text/javascript" src="RenderServlet?file=1/1/1/1/js/deserialize.js"></script>
    <script type="text/javascript">
	function showPage(page) {
	    var html = getHtml(page);
	    document.body.innerHTML = html;
	}
	function getHtml(strUrl){
	    var html;
	    new Ajax.Request(strUrl,
	    {
		method:'get',// tipo de mÃ©todo
		requestHeaders: {Accept: 'text/plain'}, // tipo de consumo
		asynchronous: false, // sincrono ou assincrono
		onSuccess: function(transport) { // funcao de sucesso
		    html = transport.responseText;
		},
		onFailure: function() { // funcao para mostrar problemas
		    alert('Message: Something went wrong...')
		}
	    });
	    return html;
	}

	function getJsonFromUrlByGet(strUrl){
	    var json;

	    new Ajax.Request(strUrl,
	    {
		method:'get',// tipo de método
		requestHeaders: {Accept: 'application/json'}, // tipo de consumo
		asynchronous: false, // sincrono ou assincrono
		onSuccess: function(transport) { // funcao de sucesso
		    json = transport.responseText.evalJSON();

		},
		onFailure: function() { // funcao para mostrar problemas
                    alert('Message: Something went wrong...')
                }
	    });
	    return json;
	}
        /* Funcao para recuperar um Json atraves de uma URL */
	function getJsonFromUrlByPost(strUrl){
	    var json;
	    new Ajax.Request(strUrl,
	    {
		method:'post',// tipo de método
		requestHeaders: {Accept: 'application/json'}, // tipo de consumo
		asynchronous: false, // sincrono ou assincrono
		onSuccess: function(transport) { // funcao de sucesso
		    json = transport.responseText.evalJSON(true);
		},
		onFailure: function() { // funcao para mostrar problemas
                    alert('Message: Something went wrong...')
                }
	    });
	    return json;
	}
        /* Funcao ativada pela interface atraves de um evento onclick de um elemente input type="button" passando a url para o Json */
	function requestJson(value) {
	    var url = value;
       var json = getJsonFromUrlByGet(url);


var name = json.name;
var status = json.status;
var div = $(name);
var divStyle = "red";

if(status == "ok") {divStyle = "green";}


	    var html = '<div id=\"innercontent\" style=\"background: '+divStyle+'; width: 100px; height: 20px; border: 1px solid #ffe79c; margin: 20px; padding: 10px; text-align: center; font:bold 12px Arial, Helvetica, sans-serif;\">';
            var i = 0;
           for (i = 0; i < json.list.results.length; i++) {
	        html += '<td>Score: '+json.list.results[i].ret+'%<br>';
	   		}
            html += '</div>';
            div.innerHTML = html;
	}

	function doSubmitSerialize(form) {
	   var value = Form.serialize(form, $('usejson').checked);
		requestJson('/ivela-web/ChallengeSolver?'+value);
	}

	function positionX(elem)
{
 x = elem.offsetLeft;
 return x;
}

function positionY(elem)
{
y = elem.offsetTop;
 return y;
}

function toolTip(obj){

	text = obj.innerHTML
	url = "/ivela-web/resources/dictionary/get/"+text;

	json = getJsonFromUrlByGet(url);
	$('tip').innerHTML = "<b>"+text+"</b><br>"+json.busca;
	$('tip').style.position = 'absolute';
	$('tip').style.left = (positionX(obj)+50)+"px";
	$('tip').style.top = (positionY(obj)-50)+"px";
	$('tip').style.display = "block";
}

function toolBasic(name, title, obj){

	text = name
	url = "/ivela-web/resources/dictionary/get/"+text;

	json = getJsonFromUrlByGet(url);
	$('tip').innerHTML = "<b>"+title+"</b><br>"+json.busca;
	$('tip').style.position = 'absolute';
	$('tip').style.left = (positionX(obj)+50)+"px";
	$('tip').style.top = (positionY(obj)-50)+"px";
	$('tip').style.display = "block";
}

function clearTip(){
	$('tip').innerHTML = "";
	$('tip').style.display = "none";

}

    </script>

<link rel='stylesheet' href='RenderServlet?file=1/1/1/1/css/default.css' media="screen">
</head>
<body onLoad="showPage('RenderServlet?file=1/1/1/1/part1.html');">
</body>
</html>
```**


No seu html de conteudo podem aparecer qualquer tipo de estrutura: applets, videos,
audio em mp3, ogg , etc.

Todos os applets utilizados pelo Ivela estarão disponivel no endereço: `RenderServlet?file=/opt/globals/applets/<applet.jar>`