/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.services;

import br.ufc.ivela.services.NoteResource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nelson
 */
public class NoteResourceTest {
    
    NoteResource noteResource;

    public NoteResourceTest() {
        noteResource = new NoteResource();
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

    @Test
    public void testGet_String() {
        System.out.println("get");
        String id = "";
        NoteResource instance = new NoteResource();
        String expResult = "";
        String result = instance.get(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetByUser() {
        System.out.println("getByUser");
        String systemUser = "";
        NoteResource instance = new NoteResource();
        String expResult = "";
        String result = instance.getByUser(systemUser);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetByDate() {
        System.out.println("getByDate");
        String datetime = "";
        NoteResource instance = new NoteResource();
        String expResult = "";
        String result = instance.getByDate(datetime);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGet_0args() {
        System.out.println("get");
        NoteResource instance = new NoteResource();
        String expResult = "";
        String result = instance.get();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testRemove() {
        System.out.println("remove");
        String id = "";
        NoteResource instance = new NoteResource();
        String expResult = "";
        String result = instance.remove(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testAdd() {
        assertTrue(noteResource.add("1", "note", "descrição", "2008-01-01 12:12:00", "true", "false", "1").equals("tenho que pensar em uma comparação!"));
    }

}