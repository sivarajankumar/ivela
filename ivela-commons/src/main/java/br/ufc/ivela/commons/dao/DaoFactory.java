/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.commons.dao;

import java.io.Serializable;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author marcus
 */
public abstract class DaoFactory implements Serializable{

    private static SessionFactory sessionFactory;

    public static GenericDao getInstance(Class c) {

        init();
        
        GenericDaoImpl dao = new GenericDaoImpl();
        dao.setSessionFactory(sessionFactory);
        dao.setClazz(c);

        return dao;
    }
    
    public static GenericDao getInstance() {

        init();
        
        GenericDaoImpl dao = new GenericDaoImpl();
        dao.setSessionFactory(sessionFactory);
        
        return dao;
    }
    
    private static void init(){
        if(sessionFactory == null) {            
            ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
        }
    }
}
