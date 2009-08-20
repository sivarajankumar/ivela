/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.commons.security;

import br.ufc.ivela.commons.model.Functionality;
import br.ufc.ivela.commons.model.SystemUser;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.security.context.GlobalSecurityContextHolderStrategy;
import org.springframework.security.context.SecurityContextHolder;

/**
 *
 * @author marcus
 */
public class IvelaAuthenticationFilter implements Filter, Ordered {

    ServletContext context;

    public void init(FilterConfig filterConfig) throws ServletException {
        setContext(filterConfig.getServletContext());
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String path = ((HttpServletRequest) request).getServletPath();

        path = path.substring(1);
        
        if (path.endsWith(".action") && !path.equals("accessDenied.action")) {

            String[] splitted = path.split("!");            
            String action = splitted[0];            
            int i = splitted[1].indexOf(".");            
            String method = splitted[1].substring(0, i);
            
            if (obj instanceof SystemUser) {
                SystemUser systemUser = (SystemUser) obj;
                
//                for (Functionality functionality : systemUser.getFunctionalities()) {
//                    System.out.println(functionality.getAction()+" - "+functionality.getMethod());
//                }
                
                for (Functionality functionality : systemUser.getFunctionalities()) {
                    if(functionality.getAction().equals(action) && functionality.getMethod().equals(method)){
                        chain.doFilter(request, response);
                    } else {
                        ((HttpServletResponse) response).sendRedirect("accessDenied.action");
                    }
                }
            } else {
                ((HttpServletResponse) response).sendRedirect("accessDenied.action");
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
    }

    public ServletContext getContext() {
        return context;
    }

    public void setContext(ServletContext context) {
        this.context = context;
    }

    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }
}