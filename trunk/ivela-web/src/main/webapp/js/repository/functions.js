var itemClicked;
                        
function addFile() {
    var dialog = dijit.byId('dialog1');
                
    var id = jsonStore.getValue(itemClicked, "id");   
                
    document.getElementById('repository_dirId').value = id;          
                    
    dialog.show();                                 
}
            
function addDir() {
    var dialog = dijit.byId('dialog2');
                
    var id = jsonStore.getValue(itemClicked, "id");   
                
    document.getElementById('repo_dirId').value = id;          
                    
    dialog.show(); 
}
            
function addRootDir() {
    var dialog = dijit.byId('dialog2');
                
    var id = '';   
                
    document.getElementById('repo_dirId').value = id;          
                    
    dialog.show(); 
}
            
function rmDir() {
    var dirId = jsonStore.getValue(itemClicked, "id"); 
                
    document.location = "repository!remove.action?gradeId=" +gradeId+ "&dirId=" +dirId;                    
}

