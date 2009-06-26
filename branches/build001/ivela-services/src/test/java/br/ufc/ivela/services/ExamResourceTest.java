/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.services;

import java.util.Date;
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
public class ExamResourceTest {

    ExamResource examResource;

    public ExamResourceTest() {
        examResource = new ExamResource();
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of get method, of class ExamResource.
     */
    @Test
    public void testGet() {
        assertNotNull(examResource.get("1"));
    }

    /**
     * Test of remove method, of class ExamResource.
     */
    @Test
    public void testRemove() {
        assertNotNull(examResource.remove("9"));
    }

    /**
     * Test of add method, of class ExamResource.
     */
    @Test
    public void testAdd() {
        assertTrue(examResource.add("1", "Teste", "2008-01-04 15:04:2008", "2008-01-07 16:00:00").equals("ok"));
    }
}