<%--    
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
# File: main.jsp                                                                            #
# Document: Decorator for main pages                                                        # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 16-DEC-2008 - emanuelle                         - XXXXXX - Initial Version                #
# 19-AUG-2009 - Otofuji (Instituto Eldorado)      - 000007 - Move to Action                 #
#############################################################################################
--%>


<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>


<html>
    <head>
        <style type="text/css">
         html, body {height:auto; min-height: 100%;}
        </style>
    </head> 
    
    <h1><s:text name="list.about.us"/></h1> <br />
    <input class="btn-back" type="button" onclick="history.back(1)" value="<s:text name="systemUser.input.btnBack"/>" />
    <br />
        
    
   <div style="height: auto; width: auto; margin: 0 auto;" > 
    <table width="750" border="0" align="center">
     <tr>
        <td valign="top">&nbsp;</td>
        <td valign="top">&nbsp;</td>
     </tr>
     <tr>
        <td width="1780" valign="top"><a href="http://www.eldorado.org.br" target="_blank"><img src="./images/partners/eldorado.png" alt="IBM" width="170" height="80" border="0"></a></td>
        <td valign="top"><h2><a href="http://www.eldorado.org.br" target="_blank"><s:text name="about.eldorado.name" /></a></h2>
        <s:text name="about.eldorado" /></td>
      </tr>
      <tr>
        <td valign="top">&nbsp;</td>
        <td valign="top">&nbsp;</td>
      </tr>
      <tr>
        <td width="1780" valign="top"><a href="http://www.ibm.com.br" target="_blank"><img src="./images/partners/ibm.jpeg" alt="IBM" width="170" height="121" border="0"></a></td>
        <td valign="top"><h2><a href="http://www.ibm.com.br" target="_blank"><s:text name="about.ibm.name" /></a></h2>
        <s:text name="about.ibm" /></td>
      </tr>
      <tr>
        <td valign="top">&nbsp;</td>
        <td valign="top">&nbsp;</td>
      </tr>
      <tr>
        <td width="1780" valign="top"><a href="http://www.fit-tecnologia.org.br" target="_blank"><img src="./images/partners/fit.jpeg" alt="FIT" width="170" height="77" border="0"></a></td>
      <td width="79%" valign="top"><h2><a href="http://www.fit-tecnologia.org.br" target="_blank"><s:text name="about.fit.name" /></a></h2>
      <s:text name="about.fit" /></td>
      </tr>
      <tr>
        <td valign="top">&nbsp;</td>
        <td valign="top">&nbsp;</td>
      </tr>      
      <tr>
        <td width="1780" valign="top"><a href="http://www.ufc.br" target="_blank"><img src="./images/partners/ufc.jpeg" alt="UFC" width="170" height="207" border="0"></a></td>
        <td valign="top"><h2><a href="http://www.ufc.br" target="_blank"><s:text name="about.ufc.name" /></a></h2>
        <s:text name="about.ufc" /></td>
      </tr>
      <tr>
        <td valign="top">&nbsp;</td>
        <td valign="top">&nbsp;</td>
      </tr>
    </table>
   <hr>
  
   <p>&nbsp;</p>
   <table width="750" border="0" align="center">
     <tr>
       <td width="178" valign="top"><a href="http://code.google.com/p/ivela/" target="_blank"><img src="./images/ivela-logo-full.gif" alt="IVELA" width="178" height="118" border="0"></a></td>
       <td width="79%" valign="top"><h2><a href="http://code.google.com/p/ivela/" target="_blank"><s:text name="about.ivela.name" /></a></h2>
           <p><s:text name="about.ivela" /><br>
         </p></td>
     </tr>
     <tr>
       <td valign="top">&nbsp;</td>
       <td valign="top">&nbsp;</td>
     </tr>
   </table>
    </div>
    <br />
    <input class="btn-back" type="button" onclick="history.back(1)" value="<s:text name="systemUser.input.btnBack"/>" />
</html>
