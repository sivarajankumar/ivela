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
    var json = getJson(course);    
        
    // Create random generated tree.            

    look(json);

    // var json = {"id":"1", "name":"Computacao", "children":[
    // {"id":"2", "name":"Teste", "children":[]},
    // {"id":"3", "name":"Giggity", "children":[]} ]
    // };
   
    var infovis = document.getElementById('infovis');
    var w = infovis.offsetWidth, h = infovis.offsetHeight;
    
    var j = Window.getSize();
    var e = $("header"), d = $("left"), h = $("infovis");       
    
    var i = e.getSize().y, b = d.getSize().y;
    var a = {
        height : Math.floor((j.y - i) / 1),
        width : Math.floor((j.x - b) / 1)
    };
    
      var canvas= new Canvas('infoCanvas', {  
                    // Where to inject canvas. Any HTML container will do.  
                    'injectInto':'infovis',  
                    //Set width and height, default's to 200.  
                    'width': a.width,  
                    'height':a.height,
                    'backgroundColor': '#fff',
                     'styles': {  
                        'fillStyle': '#ccddee',  
                         'strokeStyle': '#772277'  
                         } 
                 });                  
      
        var st = new ST(canvas, {
            orientation: 'left',
            //set duration for the animation
            duration: 400,
            //set animation transition type
            transition: Trans.Quart.easeInOut,
            //set distance between node and its children
            levelDistance: 35,
            Node: {             
            width: 150,
            type: 'rectangle',
            color: '#666'
            
        },

        onCreateLabel: function(label, node){
            label.id = node.id;            
            label.innerHTML = node.name;
            label.onclick = function(){
                st.onClick(node.id);
            };
            //set label styles
            var style = label.style; 
            
            style.cursor = 'pointer';
            style.color = '#fff';
            style.fontSize = '1em';
            style.textAlign= 'center';
            style.paddingTop = '3px';            
        },
        onBeforePlotNode: function(node){                       
            if (node.selected) {
                node.name.$color = "#ff7";
                node.color = '#fff';
            }
            else {
                delete node.name.$color;
                node.name.$color = '#fff';                                    
            }            
            
        },
        onBeforePlotLine: function(adj){
            if (adj.nodeFrom.selected && adj.nodeTo.selected) {
                adj.data.$color = "#eed";
                adj.data.$lineWidth = 3;
            }
            else {
                delete adj.data.$color;
                delete adj.data.$lineWidth;
            }
        }       

        });     

      // load json data
      st.loadJSON(json);
      // compute node positions and layout
      st.compute();
      // optional: make a translation of the tree
      st.geom.translate(new Complex(-200, 0), "startPos");
      // Emulate a click on the root node.
      st.onClick(st.root);

}

function getJson(course) {
    var retorno;
  
    var request = new Request.JSON( {
        method : 'get',
        url : 'home!getCourse.action?courseId=' + course,
        async : false, 
        onComplete : function(response) {            
            retorno = response;
        },
        onFailure : function() {
            alert('Message: Something went wrong...')
        }
    }).send();
          
    return retorno;
}