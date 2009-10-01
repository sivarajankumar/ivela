/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function execute(url, params, funcao) {
    new Ajax.Request(url, { method: 'post', parameters: params, onComplete: funcao });
}


function include(url, params, div){
    new Ajax.Updater(div, url, { method: 'post', parameters: params });
}

function retorno(resposta){
    if (resposta.readyState == 4)
    {
        if (resposta.status == 200 || resposta.status == 0)
        {
            obj = resposta.responseXML;
            if (obj.getElementsByTagName('usuarios')[0])
            {
                var dados = obj.getElementsByTagName('usuarios')[0];
                for(i = 0 ; i < dados.getElementsByTagName('usuario').length; i++){
                    var usuario = dados.getElementsByTagName('usuario')[i];
                    var id = usuario.getAttribute('id')+"<br>";
                    var nome = usuario.getAttribute('nome')+"<br>";

                    $('tabela').innerHTML+= "<tr><td>"+id+"</td><td>"+nome+"</td></tr>"
                }
            }
        }
    }
}