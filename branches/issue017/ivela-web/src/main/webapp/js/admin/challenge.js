function showNewsChallenge(){
    closeAll();
    showDependency();
    $('challengeItens.id').value = '';
    $('formChallenge').reset();
    $('formSearchChallenge').reset();
    $('submitChall').value = "Add";
    $('showChallenge').style.display= "block";
}

function showDependency(){
   var json = getJsonFromUrlPost("challenge!getByUnit.action","unit.id="+$('unit.id').value);
   var html = "";
   for (i=0;i<json.list.length;i++){
       html += "<option value="+json.list[i].id+" >"+json.list[i].name;
   }
   $('challengeItens.dependency').innerHTML = html;
}

function submitChallenge(){
    var url = "";
    var params = "";
    var str = "";
    params += "challengeItems.name="+$('challengeItens.name').value;
    params += "&challengeItems.course.id="+$('course.id').value;
    params += "&challengeItems.discipline.id="+$('discipline.id').value;
    params += "&challengeItems.unit.id="+$('unit.id').value;
    params += "&challengeItems.weight="+$('challengeItens.weight').value;
    params += "&challengeItems.xml="+replaceAll($('challengeItens.xml').value);
    params += "&challengeItems.dependency="+$('challengeItens.dependency').value;
    if($('challengeItens.id').value!=''){
         url = "challenge!update.action";
         params += "&challengeItems.id="+$('challengeItens.id').value;
         str = "atualizado";
    }
    else{
         url = "challenge!add.action";
         str = "inserido";
    }
    var json = getJsonFromUrlPost(url,params);
    if(json.challengeItem!=undefined && json.challengeItem!=""){
        alert(str+" com sucesso!");
        
     }
     else{
        alert("não foi possivel o challenge ser " +str+"!");
     }
    
    showNewsChallenge();
    
}
function submitSearch(){
    var json = getJsonFromUrlPost("challenge!getByName.action","challengeItems.name="+$('challengeItensSearch.name').value);
    if(json.challengeItems!=""){
        $('challengeItens.id').value = json.challengeItems.id;
        $('challengeItens.name').value =json.challengeItems.name;
        $('challengeItens.dependency').value = json.challengeItems.dependency;
        $('challengeItens.weight').value = json.challengeItems.weight;
        $('challengeItens.xml').value =   json.challengeItems.xml; 
        $('submitChall').value = "Update";
        $('removeCha').style.display= "block";
    }
    else{
        alert("Não encontrado!");
        showNewsChallenge();
    }
}

function removeChallenge(){
     var json = getJsonFromUrlPost("challenge!remove.action","challengeItems.id="+$('challengeItens.id').value);
     if(json.challengeItem){
         alert("Removido com sucesso!");
     }    
     else{ 
         alert("Não foi possível excluir!");
     }    
      showNewsChallenge();
}