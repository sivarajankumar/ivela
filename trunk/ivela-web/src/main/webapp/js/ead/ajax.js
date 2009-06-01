function execute(url,pars,funcao) {
	new Ajax.Request( url, {method: 'post',	parameters: pars,onComplete: funcao});
}


function include(url, pars, div){
	new Ajax.Updater( div, url, {method: 'post',parameters: pars });
}