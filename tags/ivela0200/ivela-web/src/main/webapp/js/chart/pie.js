hD="0123456789ABCDEF";function d2h(b){var a=hD.substr(b&15,1);while(b>15){b>>=4;a=hD.substr(b&15,1)+a}return a}function h2d(a){return parseInt(a,16)}function pie(){this.ct=0;this.data=new Array();this.x_name=new Array();this.max=0;this.c_array=new Array();this.c_array[0]=new Array(255,192,95);this.c_array[1]=new Array(80,127,175);this.c_array[2]=new Array(159,87,112);this.c_array[3]=new Array(111,120,96);this.c_array[4]=new Array(224,119,96);this.c_array[5]=new Array(80,159,144);this.c_array[6]=new Array(207,88,95);this.c_array[7]=new Array(64,135,96);this.c_array[8]=new Array(239,160,95);this.c_array[9]=new Array(144,151,80);this.c_array[10]=new Array(255,136,80);this.getColor=function(){if(this.ct>=(this.c_array.length-1)){this.ct=0}else{this.ct++}return"#"+d2h(this.c_array[this.ct][0])+d2h(this.c_array[this.ct][1])+d2h(this.c_array[this.ct][2])};this.add=function(a,b){this.x_name.push(a);this.data.push(parseInt(b,10));this.max+=parseInt(b,10)};this.fillArc=function(k,j,a,m,c,g){var d=c-m;var e=2*Math.PI/d;var l=new Array();var f=new Array();st_r=m*Math.PI/180;en_r=c*Math.PI/180;for(angle=st_r;angle<=en_r;angle+=e){if(en_r<angle+e){angle=en_r}var h=Math.sin(angle)*a;var b=Math.cos(angle)*a;l.push(k+b);f.push(j-h);g.drawLine(k+b,j-h,k+b,j-h)}l.push(k);f.push(j);g.fillPolygon(l,f)};this.render=function(c,l,p){var f=new jsGraphics(c);var a=75;var k=200;var j=200;var e=100;var o=12;f.setColor("gray");f.fillEllipse(k+5-a,j+5-a,2*a,2*a);var g=0;for(i=0;i<this.data.length;i++){var d=Math.round(this.data[i]/this.max*360);var h=Math.round(this.data[i]/this.max*100);f.setColor(p[i]);this.fillArc(k,j,a,g,g+d,f);var b=(g+(d/2))*2*Math.PI/360;var m=Math.sin(b)*e;var n=Math.cos(b)*e;g+=d;mxa=(n<0?50:0);f.setColor("black");f.drawString(this.x_name[i]+"<table><tr><td bgcolor='white'><b>("+h+"%)</b></td></tr></table>",k+n-mxa,j-m)}f.setColor("black");f.drawEllipse(k-a,j-a,2*a,2*a);f.paint()}};