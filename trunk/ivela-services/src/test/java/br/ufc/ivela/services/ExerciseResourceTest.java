/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.services;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author maristella
 */
public class ExerciseResourceTest {

    ExerciseResource exerciseResource;

    public ExerciseResourceTest() {
        exerciseResource = new ExerciseResource();
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of get method, of class ExerciseResource.
     */
    @Test
    public void testGet() {
        assertNotNull(exerciseResource.get("1"));
    }

    /**
     * Test of remove method, of class ExerciseResource.
     */
    @Test
    public void testRemove() {
        assertNotNull(exerciseResource.remove("9"));
    }

    /**
     * Test of add method, of class ExerciseResource.
     */
    @Test
    public void testAdd() {
        assertTrue(exerciseResource.add("1", "Teste", "1").equals("ok"));
    }
}