window.onload = check;

function check() {
    $('loginfield').focus();

    var loc = parent.location.toString();

    if(loc.indexOf("login.action", 0) == -1) {
        parent.document.location = "index.jsp";
    }
}

var r={'special':/[\W]/g}
function valid(o,w)
{
  if ( o.value.search(r[w]) > -1 )
  {
//      alert('Caractere inválido');
  }

  o.value = o.value.replace(r[w],'');
}