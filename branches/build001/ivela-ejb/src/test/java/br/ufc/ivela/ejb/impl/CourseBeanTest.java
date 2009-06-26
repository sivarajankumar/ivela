/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;


import br.ufc.ivela.commons.model.Discipline;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author maristella
 */
public class CourseBeanTest {

    CourseBean courseBean;
    Long lastId;


    public CourseBeanTest() {
        courseBean = new CourseBean();
        lastId = 1L;
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class CourseBean.
     */
    //@Test
    /*
    public void testAdd() {
    String c = courseBean.getJsonStructure();
    
    assertNotNull(c);
    }
     *///    /**
//     * Test of get method, of class CourseBean.
//     */
//    @Test
//    public void testGet() {
//        assertNotNull(courseBean.get(2L));
//    }
//
//    /**
//     * Test of remove method, of class CourseBean.
//     */
//    @Test
//    public void testRemove() {
//        assertNotNull(courseBean.remove(3L));
//    }
//
//    /**
//     * Test of getAll method, of class CourseBean.
//     */
//    @Test
//    public void testGetAl() {
//        String c = courseBean.getJsonStructure();
//        assertNotNull(c);
//    }
    
    @Test
    public void testGet() {
        CourseBean bean = new CourseBean();
        
        int prog = bean.getProgress(26L, 19L);
        
        assertTrue(prog >= 0);
    }
}