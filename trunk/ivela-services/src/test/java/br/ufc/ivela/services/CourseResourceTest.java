/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.services;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author maristella
 */
public class CourseResourceTest {

    public CourseResourceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of get method, of class CourseResource.
     */
    @Test
    public void testGet_String() {
        System.out.println("get");
        String id = "";
        CourseResource instance = new CourseResource();
        String expResult = "";
        String result = instance.get(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class CourseResource.
     */
    @Test
    public void testGet_0args() {
        System.out.println("get");
        CourseResource instance = new CourseResource();
        String expResult = "";
        String result = instance.get();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class CourseResource.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        String id = "";
        CourseResource instance = new CourseResource();
        String expResult = "";
        String result = instance.remove(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class CourseResource.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        String name = "";
        CourseResource instance = new CourseResource();
        String expResult = "";
        String result = instance.add(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}