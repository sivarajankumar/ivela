<%--    
# Copyright(c) 2008-2009 by IBM Brasil Ltda and others                                        #
# This file is part of ivela project, an open-source                                          #
# Program URL   : http://code.google.com/p/ivela/                                             #   
#                                                                                             #
# This program is free software; you can redistribute it and/or modify it under the terms     #
# of the GNU General Public License as published by the Free Software Foundation; either      #
# version 3 of the License, or (at your option) any later version.                            #
#                                                                                             #
# This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;   #
# without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.   #
# See the GNU General Public License for more details.                                        #  
#                                                                                             #
###############################################################################################
# File: waiting.jsp                                                                           #
# Document: Display 'Loading...' message while saving changes                                 # 
# Date        - Author(Company)                   - Issue# - Summary                          #
# XX-XXX-XXXX - X                                 - XXXXXX - Initial Version                  #
# 10-JUN-2009 - mileine (Instituto Eldorado)      - 000007 - animate function disabled for IE # 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>


<SCRIPT language="JavaScript" type="text/javascript">
	var ie4 = false;
	if (document.all) {
		ie4 = true;
	}
	function setContent(name, value) {
		var d;
		if (ie4) {
			d = document.all[name];
		} else {
			d = document.getElementById(name);
		}
		d.innerHTML = value;
	}

	function getContent(name) {
		var d;
		if (ie4) {
			d = document.all[name];
		} else {
			d = document.getElementById(name);
		}
		return d.innerHTML;
	}

	function setColor(name, value) {
		var d;
		if (ie4) {
			d = document.all[name];
		} else {
			d = document.getElementById(name);
		}
		d.style.color = value;
	}

	function getColor(name) {
		var d;
		if (ie4) {
			d = document.all[name];
		} else {
			d = document.getElementById(name);
		}
		return d.style.color;
	}

	function animate(name, col) {
		var value = getContent(name);
		if (value.indexOf('<span') >= 0) {
			return;
		}
		var length = 0;
		var str = '';
		var ch;
		var token = '';
		var htmltag = false;
		for (i = 0; i < value.length; i++) {
			ch = value.substring(i, i + 1);
			if (i < value.length - 1) {
				nextch = value.substring(i + 1, i + 2);
			} else {
				nextch = ' ';
			}
			token += ch;
			if (ch == '<' && '/aAbBpPhHiIoOuUlLtT'.indexOf(nextch) >= 0) {
				htmltag = true;
			}
			if (ch == '>' && htmltag) {
				htmltag = false;
			}
			if (!htmltag && ch.charCodeAt(0) > 30 && ch != ' ' && ch != '\n') {
				str += '<span id="' + name + '_' + length + '">' + token
						+ '</span>';
				token = '';
				length++;
			}
		}
		setContent(name, str);
		command = 'animateloop(\'' + name + '\', ' + length + ', 0, 1, \''
				+ col + '\')';
		setTimeout(command, 100);
	}

	function animateloop(name, length, ind, delta, col) {
		var next = ind + delta;
		if (next >= length) {
			delta = delta * -1;
			next = ind + delta;
		}
		if (next < 0) {
			delta = delta * -1;
			next = ind + delta;
		}
		setColor(name + '_' + ind, getColor(name + '_' + next));
		setColor(name + '_' + next, col);
		command = 'animateloop(\'' + name + '\', ' + length + ', ' + next
				+ ', ' + delta + ', \'' + col + '\')';
		setTimeout(command, 100);

		
	}
</SCRIPT>



</head>
<body bgcolor="#dddddd" border="0">

<SPAN ID="animate"
	style="color: #454545; font: 25px Arial, Helvetica, sans-serif;"><B><s:text
	name="loading" /></B></SPAN>

<SCRIPT language="JavaScript">
if ((navigator.appCodeName== 'Mozilla')&& (navigator.appVersion.substring(0,1)>=5)) animate('animate', '#ff8000');

</SCRIPT>

</body>
</html>
