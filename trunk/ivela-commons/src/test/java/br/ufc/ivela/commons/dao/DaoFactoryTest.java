/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.commons.dao;

import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.SystemUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marcus
 */ 
public class DaoFactoryTest {
    
    @Before
    public void before(){
    }

    @After
    public void after(){
    }
    /**
     * Test of get method, of class GenericDaoImpl.
     */
    @Test
    public void testGet() {
        GenericDao<SystemUser> dao = DaoFactory.getInstance(SystemUser.class);
        
        assertNotNull(dao);
        assertNotNull(((GenericDaoImpl) dao).getSessionFactory());
        assertNotNull(dao.get(1L));
        
        GenericDao<Course> daoCourse = DaoFactory.getInstance(Course.class);
        
        assertNotNull(daoCourse);
        assertNotNull(((GenericDaoImpl) daoCourse).getSessionFactory());
        assertNotNull(daoCourse.get(1L));
    }
}