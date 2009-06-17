/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function showDictionary(){
    closeAll();
    $('showDictionary').style.display = "block";
    $('createDict').style.display = "block";
    $('dictionary.id').value = '';
    $('dictionary.form').reset();
}

function submitDictionary(dictionaryId){
    var url = '';
    var params = '';
   
        
    if($('dictionary.title').value == ''){
        $('dictionary.title').focus();
        
    }else if($('dictionary.description').value == ''){
        $('dictionary.description').focus();
        
    }else{
    
        if (dictionaryId != null && dictionaryId.length > 0) {
            url += 'dictionary!updateJson.action';
            params += 'dictionary.id=' + dictionaryId;
            params += '&dictionary.title=' + replaceAll($('dictionary.title').value);
            params += '&dictionary.description=' + replaceAll($('dictionary.description').value);
        }
        else {
            url += 'dictionary!addJson.action';
            params += 'dictionary.title=' + replaceAll($('dictionary.title').value);
            params += '&dictionary.description=' + replaceAll($('dictionary.description').value);
        }
        var jsonDictionary = getJsonFromUrlPost(url,params);
        
        
        if(jsonDictionary.dictionary == 'exists'){
            alert('palavra já existente!');
        }else{
            if(jsonDictionary.update){
                alert('atualizado com sucesso!');
            }
            else if(jsonDictionary.id != null){
                alert('inserido com sucesso');
            }
            showDictionary();
        }
    }
}

function searchDictionary(){
    if($('search.dictionary').value != ''){
        title = replaceAll($('search.dictionary').value);
        var jsonDictionary = getJsonFromUrlPost('dictionary!getJson.action','dictionary.title='+title);
        
        if(jsonDictionary.dictionary.id != null){
            $('dictionary.id').value = jsonDictionary.dictionary.id;
            $('dictionary.title').value = jsonDictionary.dictionary.title;
            $('dictionary.description').value = jsonDictionary.dictionary.description;
            $('deleteDict').style.display = 'block';
            $('updateDict').style.display = 'block';
            $('createDict').style.display = 'none';
        }
        else{
            alert('palavra não existente');
            showDictionary();
        }
    }
}
function deleteDictionary(dictionaryId){
    var url = "dictionary!deleteJson.action";
    var params = "dictionary.id="+dictionaryId;
    params += '&dictionary.title=' + replaceAll($('dictionary.title').value);
    params += '&dictionary.description=' + replaceAll($('dictionary.description').value);
    var jsonDictionary = getJsonFromUrlPost(url,params); 
    if(jsonDictionary.del){
        alert("excluída com sucesso!");
    }
    else{
        alert("não foi possível excluir!");
    }
    showDictionary();
    
    
}
function backDictionary(){
    $('showDictionary').style.display = "none";
    $('showCourse').style.display = "block";
}