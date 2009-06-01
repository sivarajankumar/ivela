var Log = {
	elem: $('log'),
	write: function(text) {
		if(!this.elem) this.elem = $('log');
		this.elem.set('html', text);
	}
};

// limita os nomes contidos no JSON a 30 caracteres para aparecer na árvore
function look(a) 
{
   
   if ( a.name.length > 29 )
   {
      a.name = a.name.substr(0,27) + "...";
   }

   for ( var i = 0; i < a.children.length; i++ )
   {
       look(a.children[i]);
   }

} 

function init(course) {
	//computes page layout (not a library function, used to adjust some css thingys on the page)
	Infovis.initLayout();
        
	//Create random generated tree.
		
	var json = getJson(course);

        look(json);

	 //var json = {"id":"1",  "name":"Computacao",  "children":[
	//	{"id":"2", "name":"Teste", "children":[]},
	//	{"id":"3", "name":"Giggity", "children":[]} ]
	// };
	 
	  //Create a new canvas instance.
	  var canvas= new Canvas('infovis');
	  //Create a new ST instance
	  st= new ST(canvas);

          // muda a orientação da árvore para mostrar de cima para baixo
          Config.orientation = 'top';

          //load json data
	  st.loadFromJSON(json);
	  //compute node positions and layout
	  st.compute();
	  //optional: make a translation of the tree
	  Tree.Geometry.translate(st.tree, new Complex(-200, 0), "startPos");
	  //Emulate a click on the root node.
	  st.onClick(st.tree.id);

//	  Add click handler to switch spacetree orientation.
//	  var checkbox = document.getElementById('switch');
//	  checkbox.onclick = function () {
//	  	st.switchPosition({
//	  		onComplete: function() {}
//	  	});
//	  };
}

function getJson(course) {
	var retorno;

	new Ajax.Request('home!getCourse.action?courseId='+course,
	    {
		method:'get',		
		requestHeaders: {Accept: 'application/json'}, 
		asynchronous: false,		
		onSuccess: function(transport) {
		    retorno = transport.responseText.evalJSON(true);
		},
		onFailure: function() { alert('Message: Something went wrong...') }
	    });
	    
	return retorno;
}