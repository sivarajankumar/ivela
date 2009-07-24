<%--
#############################################################################################
# Copyright(c) 2008-2009 by IBM Brasil Ltda and others                                      #
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
# File: index.jsp                                                                           #
# Document: Index Page                                                                      # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# XX-XXX-XXXX - XXXX                              - XXXXXX - Initial Version                #
# 23-JUL-2009 - Rafael Lagoa (Instituto Eldorado) - 000012 - Unicode (UTF-8) support        #
#############################################################################################
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="org.springframework.security.context.SecurityContextHolder"%>
<%@ page import="org.springframework.security.GrantedAuthority" %>
<%@ page import="org.springframework.security.userdetails.UserDetails"%>
<%@ page import="br.ufc.ivela.commons.model.SystemUser"%>

<%

            Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (obj != null && obj instanceof UserDetails) {
                GrantedAuthority[] authorities = ((SystemUser) obj).getAuthorities();

                boolean isAdmin = false;

                for (GrantedAuthority authority : authorities) {
                    
                    String authentication = authority.getAuthority();
                    
                    if (authentication.equals("ROLE_ADMIN") || 
                        authentication.equals("ROLE_COORD") || 
                        authentication.equals("ROLE_TUTOR") ||
                        authentication.equals("ROLE_PROFESSOR") ) {
                        
                        isAdmin = true;
                        break;
                    }
                }

                if (isAdmin) {
                    //response.sendRedirect("admin/home.action");
                    response.sendRedirect("admin/index.action");
                } else {
                    response.sendRedirect("home.action");
                }
            } else {
                response.sendRedirect("login.action");
            }


%>