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