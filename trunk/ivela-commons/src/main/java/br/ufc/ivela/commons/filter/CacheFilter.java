/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.commons.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author : Leonardo Oliveira Moreira
 * 
 */
public class CacheFilter implements Filter {

    private FilterConfig filterConfig;

    /**
     * Place this filter into service.
     *
     * @param filterConfig {@link FilterConfig}
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    /**
     * Take this filter out of service.
     */
    public void destroy() {
        this.filterConfig = null;
    }

    /**
     * Sets cache headers directives.
     *
     * @param servletRequest  {@link ServletRequest}
     * @param servletResponse {@link ServletResponse}
     * @param filterChain     {@link FilterChain}
     * @throws IOException      {@link FilterChain}
     * @throws ServletException {@link ServletException}
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //System.out.println("Setting cache headers for file " + ((HttpServletRequest) servletRequest).getRequestURI());
        
        String privacy = filterConfig.getInitParameter("privacy");
        String expirationTime = filterConfig.getInitParameter("expirationTime");

        if (privacy != null && privacy.trim().length() > 0 && expirationTime != null && expirationTime.trim().length() > 0) {
            // set the provided HTTP response parameters
            setCacheExpireDate((HttpServletResponse) servletResponse, privacy, Integer.valueOf(expirationTime).intValue());
        }
        // pass the request/response on
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void setCacheExpireDate(HttpServletResponse response, String privacy, int seconds) {
        if (response != null) {
            Calendar cal = new GregorianCalendar();
            cal.add(Calendar.SECOND, seconds);
            response.setHeader("Cache-Control", privacy + ", max-age=" + seconds + ", must-revalidate");
            response.setHeader("Expires", htmlExpiresDateFormat().format(cal.getTime()));
            
            //System.out.println("Seconds: " + seconds);
            //System.out.println("Expires: " + htmlExpiresDateFormat().format(cal.getTime()));
        }
    }

    private DateFormat htmlExpiresDateFormat() {
        DateFormat httpDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        httpDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return httpDateFormat;
    }
    
}