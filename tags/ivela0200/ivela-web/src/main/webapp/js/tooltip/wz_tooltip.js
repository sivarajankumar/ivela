var ttAbove=false;var ttBgColor="#e6ecff";var ttBgImg="";var ttBorderColor="#003399";var ttBorderWidth=1;var ttClickClose=false;var ttDelay=500;var ttFontColor="#000066";var ttFontFace="arial,helvetica,sans-serif";var ttFontSize="12px";var ttFontWeight="bold";var ttLeft=false;var ttOffsetX=12;var ttOffsetY=15;var ttOpacity=90;var ttPadding=3;var ttShadowColor="";var ttShadowWidth=0;var ttStatic=false;var ttSticky=false;var ttTemp=0;var ttTextAlign="left";var ttTitleColor="#ffffff";var ttWidth=150;var tt_tags=new Array("a","area","b","big","caption","center","code","dd","div","dl","dt","em","h1","h2","h3","h4","h5","h6","i","img","input","li","map","ol","p","pre","s","select","small","span","strike","strong","sub","sup","table","td","textarea","th","tr","tt","u","var","ul","layer");var tt_obj=null,tt_ifrm=null,tt_objW=0,tt_objH=0,tt_objX=0,tt_objY=0,tt_offX=0,tt_offY=0,xlim=0,ylim=0,tt_sup=false,tt_sticky=false,tt_wait=false,tt_act=false,tt_sub=false,tt_u="undefined",tt_mf=null,tt_tag=null;var tt_db=(document.compatMode&&document.compatMode!="BackCompat")?document.documentElement:document.body?document.body:null,tt_n=navigator.userAgent.toLowerCase(),tt_nv=navigator.appVersion;var tt_op=!!(window.opera&&document.getElementById),tt_op6=tt_op&&!document.defaultView,tt_op7=tt_op&&!tt_op6,tt_ie=tt_n.indexOf("msie")!=-1&&document.all&&tt_db&&!tt_op,tt_ie7=tt_ie&&typeof document.body.style.maxHeight!=tt_u,tt_ie6=tt_ie&&!tt_ie7&&parseFloat(tt_nv.substring(tt_nv.indexOf("MSIE")+5))>=5.5,tt_n4=(document.layers&&typeof document.classes!=tt_u),tt_n6=(!tt_op&&document.defaultView&&typeof document.defaultView.getComputedStyle!=tt_u),tt_w3c=!tt_ie&&!tt_n6&&!tt_op&&document.getElementById,tt_ce=document.captureEvents&&!tt_n6;function tt_Int(b){var a;return isNaN(a=parseInt(b))?0:a}function wzReplace(b,a){var d="",c=this,e;while((e=c.indexOf(b))!=-1){d+=c.substring(0,e)+a;c=c.substring(e+b.length)}return d+c}String.prototype.wzReplace=wzReplace;function tt_N4Tags(b,e,a){e=e||document;a=a||new Array();var c=(b=="a")?e.links:e.layers;for(var d=c.length;d--;){a[a.length]=c[d]}for(d=e.layers.length;d--;){a=tt_N4Tags(b,e.layers[d].document,a)}return a}function tt_Htm(a,k,l){var s=(typeof a.T_BGCOLOR!=tt_u)?a.T_BGCOLOR:ttBgColor,t=(typeof a.T_BGIMG!=tt_u)?a.T_BGIMG:ttBgImg,b=(typeof a.T_BORDERCOLOR!=tt_u)?a.T_BORDERCOLOR:ttBorderColor,p=(typeof a.T_BORDERWIDTH!=tt_u)?a.T_BORDERWIDTH:ttBorderWidth,i=(typeof a.T_FONTFACE!=tt_u)?a.T_FONTFACE:ttFontFace,j=(typeof a.T_FONTCOLOR!=tt_u)?a.T_FONTCOLOR:ttFontColor,q=(typeof a.T_FONTSIZE!=tt_u)?a.T_FONTSIZE:ttFontSize,o=(typeof a.T_FONTWEIGHT!=tt_u)?a.T_FONTWEIGHT:ttFontWeight,f=(typeof a.T_OPACITY!=tt_u)?a.T_OPACITY:ttOpacity,c=(typeof a.T_PADDING!=tt_u)?a.T_PADDING:ttPadding,n=(typeof a.T_SHADOWCOLOR!=tt_u)?a.T_SHADOWCOLOR:(ttShadowColor||0),g=(typeof a.T_SHADOWWIDTH!=tt_u)?a.T_SHADOWWIDTH:(ttShadowWidth||0),r=(typeof a.T_TEXTALIGN!=tt_u)?a.T_TEXTALIGN:ttTextAlign,d=(typeof a.T_TITLE!=tt_u)?a.T_TITLE:"",m=(typeof a.T_TITLECOLOR!=tt_u)?a.T_TITLECOLOR:ttTitleColor,v=(typeof a.T_WIDTH!=tt_u)?a.T_WIDTH:ttWidth;if(n||g){n=n||"#c0c0c0";g=g||5}if(tt_n4&&(q=="10px"||q=="11px")){q="12px"}var h=(tt_n4?"":tt_n6?("-moz-opacity:"+(f/100)):tt_ie?("filter:Alpha(opacity="+f+")"):("opacity:"+(f/100)))+";";var u='<div id="'+k+'" style="position:absolute;z-index:1010;';u+="left:0px;top:0px;width:"+(v+g)+"px;visibility:"+(tt_n4?"hide":"hidden")+";"+h+'"><table border="0" cellpadding="0" cellspacing="0"'+(b?(' bgcolor="'+b+'" style="background:'+b+';"'):"")+' width="'+v+'">';if(d){u+='<tr><td style="padding-left:3px;padding-right:3px;" align="'+r+'"><font color="'+m+'" face="'+i+'" style="color:'+m+";font-family:"+i+";font-size:"+q+';"><b>'+(tt_n4?"&nbsp;":"")+d+"</b></font></td></tr>"}u+='<tr><td><table border="0" cellpadding="'+c+'" cellspacing="'+p+'" width="100%"><tr><td'+(s?(' bgcolor="'+s+'"'):"")+(t?' background="'+t+'"':"")+' style="text-align:'+r+";";if(tt_n6){u+="padding:"+c+"px;"}u+='" align="'+r+'"><font color="'+j+'" face="'+i+'" style="color:'+j+";font-family:"+i+";font-size:"+q+";font-weight:"+o+';">';if(o=="bold"){u+="<b>"}u+=l;if(o=="bold"){u+="</b>"}u+="</font></td></tr></table></td></tr></table>";if(g){var e=Math.round(g*1.3);if(tt_n4){u+='<layer bgcolor="'+n+'" left="'+v+'" top="'+e+'" width="'+g+'" height="0"></layer><layer bgcolor="'+n+'" left="'+e+'" align="bottom" width="'+(v-e)+'" height="'+g+'"></layer>'}else{h=tt_n6?"-moz-opacity:0.85;":tt_ie?"filter:Alpha(opacity=85);":"opacity:0.85;";u+='<div id="'+k+'R" style="position:absolute;background:'+n+";left:"+v+"px;top:"+e+"px;width:"+g+"px;height:1px;overflow:hidden;"+h+'"></div><div style="position:relative;background:'+n+";left:"+e+"px;top:0px;width:"+(v-e)+"px;height:"+g+"px;overflow:hidden;"+h+'"></div>'}}return(u+"</div>")}function tt_EvX(c){var a=tt_Int(c.pageX||c.clientX||0)+tt_Int(tt_ie?tt_db.scrollLeft:0)+tt_offX;if(a>xlim){a=xlim}var b=tt_Int(window.pageXOffset||(tt_db?tt_db.scrollLeft:0)||0);if(a<b){a=b}return a}function tt_EvY(c){var b;var a=tt_Int(c.pageY||c.clientY||0)+tt_Int(tt_ie?tt_db.scrollTop:0);if(tt_sup&&(b=a-(tt_objH+tt_offY-15))>=tt_Int(window.pageYOffset||(tt_db?tt_db.scrollTop:0)||0)){a-=(tt_objH+tt_offY-15)}else{if(a>ylim||!tt_sub&&a>ylim-24){a-=(tt_objH+5);tt_sub=false}else{a+=tt_offY;tt_sub=true}}return a}function tt_ReleasMov(){if(document.onmousemove==tt_Move){if(!tt_mf&&tt_ce){document.releaseEvents(Event.MOUSEMOVE)}document.onmousemove=tt_mf}}function tt_ShowIfrm(a){if(!tt_obj||!tt_ifrm){return}if(a){tt_ifrm.style.width=tt_objW+"px";tt_ifrm.style.height=tt_objH+"px";tt_ifrm.style.display="block"}else{tt_ifrm.style.display="none"}}function tt_GetDiv(a){return(tt_n4?(document.layers[a]||null):tt_ie?(document.all[a]||null):(document.getElementById(a)||null))}function tt_GetDivW(){return tt_Int(tt_n4?tt_obj.clip.width:(tt_obj.offsetWidth||tt_obj.style.pixelWidth))}function tt_GetDivH(){return tt_Int(tt_n4?tt_obj.clip.height:(tt_obj.offsetHeight||tt_obj.style.pixelHeight))}function tt_SetDivZ(){var a=tt_obj.style||tt_obj;if(a){if(window.dd&&dd.z){a.zIndex=Math.max(dd.z+1,a.zIndex)}if(tt_ifrm){tt_ifrm.style.zIndex=a.zIndex-1}}}function tt_SetDivPos(b,a){var d=tt_obj.style||tt_obj;var c=(tt_op6||tt_n4)?"":"px";d.left=(tt_objX=b)+c;d.top=(tt_objY=a)+c;if(window.tt_ifrm){tt_ifrm.style.left=d.left;tt_ifrm.style.top=d.top}}function tt_ShowDiv(a){tt_ShowIfrm(a);if(tt_n4){tt_obj.visibility=a?"show":"hide"}else{tt_obj.style.visibility=a?"visible":"hidden"}tt_act=a}function tt_DeAlt(b){if(b){if(b.alt){b.alt=""}if(b.title){b.title=""}var a=b.children||b.childNodes||null;if(a){for(var c=a.length;c;){tt_DeAlt(a[--c])}}}}function tt_OpDeHref(b){var a;if(b){a=b.target;while(a){if(a.hasAttribute("href")){tt_tag=a;tt_tag.t_href=tt_tag.getAttribute("href");tt_tag.removeAttribute("href");tt_tag.style.cursor="hand";tt_tag.onmousedown=tt_OpReHref;tt_tag.stats=window.status;window.status=tt_tag.t_href;break}a=a.parentElement}}}function tt_OpReHref(){if(tt_tag){tt_tag.setAttribute("href",tt_tag.t_href);window.status=tt_tag.stats;tt_tag=null}}function tt_Show(l,d,g,c,f,h,i,m,k,o,b,a){if(tt_obj){tt_Hide()}tt_mf=document.onmousemove||null;if(window.dd&&(window.DRAG&&tt_mf==DRAG||window.RESIZE&&tt_mf==RESIZE)){return}var n,j;tt_obj=tt_GetDiv(d);if(tt_obj){l=l||window.event;tt_sub=!(tt_sup=g);tt_sticky=b;tt_objW=tt_GetDivW();tt_objH=tt_GetDivH();tt_offX=i?-(tt_objW+m):m;tt_offY=k;if(tt_op7){tt_OpDeHref(l)}if(tt_n4){if(tt_obj.document.layers.length){n=tt_obj.document.layers[0];n.clip.height=tt_objH-Math.round(n.clip.width*1.3)}}else{n=tt_GetDiv(d+"R");if(n){j=tt_objH-tt_Int(n.style.pixelTop||n.style.top||0);if(typeof n.style.pixelHeight!=tt_u){n.style.pixelHeight=j}else{n.style.height=j+"px"}}}xlim=tt_Int((tt_db&&tt_db.clientWidth)?tt_db.clientWidth:window.innerWidth)+tt_Int(window.pageXOffset||(tt_db?tt_db.scrollLeft:0)||0)-tt_objW-(tt_n4?21:0);ylim=tt_Int(window.innerHeight||tt_db.clientHeight)+tt_Int(window.pageYOffset||(tt_db?tt_db.scrollTop:0)||0)-tt_objH-tt_offY;tt_SetDivZ();if(h){tt_SetDivPos(tt_Int((h=h.split(","))[0]),tt_Int(h[1]))}else{tt_SetDivPos(tt_EvX(l),tt_EvY(l))}var e="tt_ShowDiv('true');";if(b){e+="{tt_ReleasMov();"+(c?("window.tt_upFunc = document.onmouseup || null;if(tt_ce) document.captureEvents(Event.MOUSEUP);document.onmouseup = new Function(\"window.setTimeout('tt_Hide();', 10);\");"):"")+"}"}else{if(o){e+="tt_ReleasMov();"}}if(a>0){e+="window.tt_rtm = window.setTimeout('tt_sticky = false; tt_Hide();',"+a+");"}window.tt_rdl=window.setTimeout(e,f);if(!h){if(tt_ce){document.captureEvents(Event.MOUSEMOVE)}document.onmousemove=tt_Move}}}var tt_area=false;function tt_Move(a){if(!tt_obj){return}if(tt_n6||tt_w3c){if(tt_wait){return}tt_wait=true;setTimeout("tt_wait = false;",5)}var b=a||window.event;tt_SetDivPos(tt_EvX(b),tt_EvY(b));if(window.tt_op6){if(tt_area&&b.target.tagName!="AREA"){tt_Hide()}else{if(b.target.tagName=="AREA"){tt_area=true}}}}function tt_Hide(){if(window.tt_obj){if(window.tt_rdl){window.clearTimeout(tt_rdl)}if(!tt_sticky||!tt_act){if(window.tt_rtm){window.clearTimeout(tt_rtm)}tt_ShowDiv(false);tt_SetDivPos(-tt_objW,-tt_objH);tt_obj=null;if(typeof window.tt_upFunc!=tt_u){document.onmouseup=window.tt_upFunc}}tt_sticky=false;if(tt_op6&&tt_area){tt_area=false}tt_ReleasMov();if(tt_op7){tt_OpReHref()}}}function tt_Init(){if(!(tt_op||tt_n4||tt_n6||tt_ie||tt_w3c)){return}var e=tt_n4?'<div style="position:absolute;"></div>':"",l,d,g,k,h="return escape(";for(var c=tt_tags.length;c;){--c;l=tt_ie?(document.all.tags(tt_tags[c])||1):document.getElementsByTagName?(document.getElementsByTagName(tt_tags[c])||1):(!tt_n4&&tt_tags[c]=="a")?document.links:1;if(tt_n4&&(tt_tags[c]=="a"||tt_tags[c]=="layer")){l=tt_N4Tags(tt_tags[c])}for(var a=l.length;a;){--a;if(typeof(d=l[a]).onmouseover=="function"&&d.onmouseover.toString().indexOf(h)!=-1&&!tt_n6||tt_n6&&(g=d.getAttribute("onmouseover"))&&g.indexOf(h)!=-1){if(g){d.onmouseover=new Function(g)}var b=unescape(d.onmouseover());e+=tt_Htm(d,"tOoLtIp"+c+""+a,b.wzReplace("& ","&"));d.onmouseover=new Function("e",'if(window.tt_Show && tt_Show) tt_Show(e,"tOoLtIp'+c+""+a+'",'+((typeof d.T_ABOVE!=tt_u)?d.T_ABOVE:ttAbove)+","+((typeof d.T_CLICKCLOSE!=tt_u)?d.T_CLICKCLOSE:ttClickClose)+","+((typeof d.T_DELAY!=tt_u)?d.T_DELAY:ttDelay)+","+((typeof d.T_FIX!=tt_u)?'"'+d.T_FIX+'"':'""')+","+((typeof d.T_LEFT!=tt_u)?d.T_LEFT:ttLeft)+","+((typeof d.T_OFFSETX!=tt_u)?d.T_OFFSETX:ttOffsetX)+","+((typeof d.T_OFFSETY!=tt_u)?d.T_OFFSETY:ttOffsetY)+","+((typeof d.T_STATIC!=tt_u)?d.T_STATIC:ttStatic)+","+((typeof d.T_STICKY!=tt_u)?d.T_STICKY:ttSticky)+","+((typeof d.T_TEMP!=tt_u)?d.T_TEMP:ttTemp)+");");d.onmouseout=tt_Hide;tt_DeAlt(d)}}}if(tt_ie6){e+='<iframe id="TTiEiFrM" src="javascript:false" scrolling="no" frameborder="0" style="filter:Alpha(opacity=0);position:absolute;top:0px;left:0px;display:none;"></iframe>'}k=document.getElementsByTagName?document.getElementsByTagName("body")[0]:tt_db;if(k&&k.insertAdjacentHTML){k.insertAdjacentHTML("AfterBegin",e)}else{if(k&&typeof k.innerHTML!=tt_u&&document.createElement&&k.appendChild){var f=document.createElement("div");k.appendChild(f);f.innerHTML=e}else{document.write(e)}}if(document.getElementById){tt_ifrm=document.getElementById("TTiEiFrM")}}tt_Init();