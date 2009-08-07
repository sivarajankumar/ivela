/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.model.Course;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Class that implements the test of ejb GradeBean
 */
public class GradeBeanTest {

    GradeBean gradeBean;
    private long lastId;
    
    public GradeBeanTest() {
        gradeBean = new GradeBean();
    }

    @Test
    public void testGet() {
        List<Course> list = gradeBean.getStructure();
        assertNotNull(list);
    }
    
//    @BeforeClass
//    public static void setUpClass() throws Exception {
//        
//    }
//
//    @AfterClass
//    public static void tearDownClass() throws Exception {
//    }
//
//    /**
//     * Test of getAll method, of class GradeBean.
//     */
//    @Test
//    public void testGetAll() {
//        List<Grade> list = gradeBean.getAll();
//        assertTrue(list.size() > 0);
//        lastId = list.get(list.size() - 1).getId();
//    }
//    
//    /**
//     * Test of get method, of class GradeBean.
//     */
//    @Test
//    public void testGet() {
//        assertNotNull(gradeBean.get(lastId));
//    }
//
//    /**
//     * Test of add method, of class GradeBean.
//     */
//    @Test
//    public void testAdd() {
//        Grade grade = new Grade();    
//        grade.setName("Test of Grade");
//        grade.setPeriod("20081");
//        assertNotNull(gradeBean.add(grade));
//    }
//
//    /**
//     * Test of remove method, of class GradeBean.
//     */
//    @Test
//    public void testRemove() {
//        assertTrue(gradeBean.remove(lastId));
//    }

}