/*
#############################################################################################
# Copyright(c) 2009 by IBM Brasil Ltda and others                                           #
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
# File: library.js                                                                          #
# Document: Library JavaScript                                                              #
# Date        - Author(Company)                   - Issue# - Summary                        #
# Unknown     - Unknown                           - XXXXXX - Initial Version                #
# 30-JUN-2009 - Fabio Fantato(Instituto Eldorado) - 000010 - JS no IE/FF opening repository #
############################################################################################# 
*/


Event.observe(window, 'load', loadAccordions, false);
Event.observe(window, 'load', function() {Event.observe(content, 'click', click, false);}, false);

var actualDir;

function isArray(o){
    return(typeof(o.length)=="undefined")?false:true;
}

function click(e){ 
    if (!e) e = window.event;
    var evt;
    if (e.target) evt = e.target;
        else if (e.srcElement) evt = e.srcElement;
    if ((evt.className) != null) {          
        var clazz = evt.className.toString();

        if(clazz == 'category_toggle category_active' || clazz == 'category_toggle'){
            actualDir = evt.next(0).getAttribute('id');
            showDirInfo(actualDir, true);
        }

        if(clazz == 'category_toggle2 category_active2' || clazz == 'category_toggle2'){
            actualDir = evt.next(0).getAttribute('id');
            showDirInfo(actualDir, false);
        }
    }
}
        
//
//	Set up all accordions
//
function loadAccordions() {		
    var acc = new accordion('category_container', {
        classNames : {
            toggle : 'category_toggle',
            toggleActive : 'category_active',
            content : 'category_content'
        },
        direction : 'vertical'
    });

    var acc2 = new accordion('category_container2', {
        classNames : {
            toggle : 'category_toggle2',
            toggleActive : 'category_active2',
            content : 'category_content2'
        },
        direction : 'vertical'
    });

    var first = acc.activate($$('#category_container .category_toggle')[0]);
    actualDir = first.getAttribute("id");
    showDirInfo(actualDir, true);
    
}

/* Funcoes para Chamar Json por Ajax */

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
        onFailure: function() { alert('Message: Something went wrong...') }
    });
    return json;
}

function showDirInfo(dir, showSub) {

    closeAll();

    var json = getJsonFromUrl("repository!getDirInfo.action?dirId="+dir+'&courseId='+courseId);

    if(json.directory != "null") {
        
        $('dirInfo.name').innerHTML = json.directory.name;

        if(showSub) {
        	if ($('subdir'))
        		$('subdir').style.display = 'block';
        } else {
        	if ($('subdir'))
        		$('subdir').style.display = 'none';
        }
    }

    $('dirInfo').style.display = 'block';
}

function showFileInfo(file) {

    closeAll();

    var json = getJsonFromUrl("repository!getFileInfo.action?fileId="+file);

    if(json.file != "null") {
        $('file.id').value = json.file.id;
        $('fileInfo.title').innerHTML = json.file.title;
        $('fileInfo.name').innerHTML = json.file.filename;
        $('fileInfo.author').innerHTML = json.file.author;
        $('fileInfo.description').innerHTML = json.file.description;
        $('fileInfo.keywords').innerHTML = json.file.keywords;
        $('fileInfo.sentby').innerHTML = json.file.sentBy.username;
    }

    $('fileInfo').style.display = 'block';
}

function openUpload() {

    closeAll();

    $('upload_dirId').value = actualDir;
    $('upload').style.display = 'block';
}

function openSubDir() {

    closeAll();
    $('repo_dirId').value = actualDir;
    $('addDir').style.display = 'block';
}

function newDir() {

    closeAll();

    $('repo_dirId').value = '';
    $('addDir').style.display = 'block';
}

function removeDir() {

    document.location = 'repository!remove.action?dirId='+actualDir+'&courseId='+courseId;
}

function removeFile() {

    var file = $('file.id').value;
    document.location = 'repository!rmfile.action?fileId='+file+'&courseId='+courseId;
}

function download() {

    var file = $('file.id').value;
    document.location = 'repository!download.action?fileId='+file;
}

function closeAll() {
	if ($('dirInfo'))   
		$('dirInfo').style.display = 'none';
    if ($('fileInfo')) 
        $('fileInfo').style.display = 'none';
    if ($('upload')) 
    	$('upload').style.display = 'none';
    if ($('addDir'))       
    	$('addDir').style.display = 'none';
}
function validateDir(){
    if($('dirName').value==''){
        alert("Informe o nome do diretório!");
    }
    else{
        $('repo').submit();
    }
}


function validateUpload(){
    var fileName = $('fileId').value;
    if(fileName != ""){
           var ext = fileName.split(".",2);
           if(ext[1]=="exe"){
               alert("Por motivo de segurança, esse tipo de arquivo é inválido!!!");
           }
           else {
                $('uploadId').submit();
          }
    }else if(fileName == "") {
               alert('Informe o arquivo para upload');
     }
           
}
