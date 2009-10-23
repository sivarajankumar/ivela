/*    
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
# File: BeanExceptionInterceptor.java                                                       #
# Document: Interceptor for the Stateless Session beans                                     # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 11-SEP-2009 - otofuji (Instituto Eldorado)      - 000016 - Initial Version                #
#############################################################################################
*/
package br.ufc.ivela.ejb.interceptors;

import javax.interceptor.InvocationContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BeanExceptionInterceptor {
    
    private static Log logger = LogFactory.getLog(BeanExceptionInterceptor.class);

    /**
     * Log Interceptors for EJB, it is configured by default to run over all
     * bean methods, if you wish to disable for a specific bean, use the 
     * ExcludeDefaultInterceptors annotation.
     */
    public Object log(InvocationContext invocationContext) throws Exception {        
        Object result = null;
        boolean debug =logger.isDebugEnabled();
        if (debug) {
            String methodName = invocationContext.getMethod().getName();
            String className = invocationContext.getTarget().getClass().getName();
            logger.debug( "Calling Method: " + className + "." + methodName );                            
            Object[] params = invocationContext.getParameters();
            if (params != null) {            
                StringBuilder builder = new StringBuilder("Parameters: ");
                for (Object param : params) {
                    builder.append('[');
                    if (param != null) {
                        builder.append(param.getClass().getName());
                        builder.append(':');
                        builder.append(param.toString());
                    } else {
                        logger.debug("null");
                    }
                    builder.append(']');
                }
                logger.debug(builder.toString());
            }
        }
        
        try {            
            result = invocationContext.proceed();            
            if (debug) {                 
                if (result != null)             
                    logger.debug("Result: " + result.getClass());
                else 
                    logger.debug("Result: null");                
            }
        } catch (Exception e) {            
            String methodName = invocationContext.getMethod().getName();
            String className = invocationContext.getTarget().getClass().getName();
            logger.error( "Error in: " + className + "." + methodName, e);                    
            throw e;
        }
        return result;
    }
    
}

