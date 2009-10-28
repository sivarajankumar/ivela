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
# File: DaoFactory.java                                                                     #
# Document: Dao factory                                                                     # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# ??-???-2008 - marcus (UFC)                     - XXXXXX - Initial Version                 #
# 01-SEP-2009 - otofuji (Instituto Eldorado)      - 000016 - Review Forum                   #
#############################################################################################
*/
package br.ufc.ivela.commons.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.support.TransactionSynchronizationManager;


public abstract class DaoFactory implements Serializable {

    private static SessionFactory sessionFactory;

    private static final String DAO_PACKAGE = "br.ufc.ivela.commons.dao";
    /** 
     * Retrieves a DAO instance for a specific Model Class.
     * 
     * @param c the Model class that you wish to retrieve a DAO for.
     * @return the Dao instance for the specified parameter.
     */
    public static GenericDao getInstance(Class c) {
        if (sessionFactory == null) init();

        GenericDaoImpl dao = null;
        try {
            String className = DAO_PACKAGE + '.' + c.getSimpleName() + "DaoImpl";
            Class daoClass = Class.forName(className);
            dao = (GenericDaoImpl)daoClass.newInstance();
            dao.setSessionFactory(sessionFactory);
            dao.setClazz(c);
            return dao;
        } catch (Exception ex) {
            dao = new GenericDaoImpl();
            dao.setSessionFactory(sessionFactory);                
            dao.setClazz(c);
        }
        
        return dao;
    }

    public static GenericDao getInstance() {
        if (sessionFactory == null) init();
        
        GenericDaoImpl dao = new GenericDaoImpl();
        dao.setSessionFactory(sessionFactory);

        return dao;
    }

    public static Session getCurrentSession() {
        if (sessionFactory == null) init();            
        return sessionFactory.getCurrentSession();
    }

    private static void init() {
        if (sessionFactory == null) {
            ApplicationContext ctx = new ClassPathXmlApplicationContext(
                    "applicationContext.xml");
            sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");

        }
    }

}
