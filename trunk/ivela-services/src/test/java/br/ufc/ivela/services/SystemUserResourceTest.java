/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.services;

import br.ufc.ivela.commons.model.SystemUser;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rodrigo
 */
public class SystemUserResourceTest {
    
    SystemUserResource su;

    public SystemUserResourceTest() {
        su = new SystemUserResource();
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of add method, of class SystemUserResource.
     */
    @Test
    public void testAdd() {
        assertTrue(su.add("teste", "asdasd@dasdasd.com", "andrejr", "dasdasd", "dasdasdas").equals("ok"));
    }

    /**
     * Test of list method, of class SystemUserResource.
     */
    @Test
    public void testList() {
        assertNotNull(su.list());
    }

    /**
     * Test of get method, of class SystemUserResource.
     */
    @Test
    public void testGet() {
        assertNotNull(su.get("1"));
    }

    /**
     * Test of remove method, of class SystemUserResource.
     */
    @Test
    public void testRemove() {
        assertTrue(su.remove("17").equals("ok"));
    }

}