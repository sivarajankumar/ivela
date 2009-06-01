/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.model.Honorific;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rodrigo
 */
public class HonorificBeanTest {

    public HonorificBeanTest() {
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
     * Test of getAll method, of class HonorificBean.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        HonorificBean instance = new HonorificBean();
        List<Honorific> expResult = null;
        List<Honorific> result = instance.getAll();
        //assertEquals(expResult, result);
        System.out.println("Tamanho: " + result.size());
        assertTrue(result.size() > 0);
    }

    /**
     * Test of add method, of class HonorificBean.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Honorific honorific = new Honorific();
        honorific.setTitle("Baron");
        HonorificBean instance = new HonorificBean();
        Long result = instance.add(honorific);
        assertTrue(result > 0);
    }

    /**
     * Test of get method, of class HonorificBean.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        Long Id = 1L;
        HonorificBean instance = new HonorificBean();
        Honorific expResult = null;
        Honorific result = instance.get(Id);
        assertEquals(expResult, result);
    }

    /**
     * Test of remove method, of class HonorificBean.
     */
    @Test
    public void testRemove() {
//        System.out.println("remove");
//        Long Id = null;
//        HonorificBean instance = new HonorificBean();
//        boolean expResult = false;
//        boolean result = instance.remove(Id);
//        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class HonorificBean.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Honorific honorific = null;
        HonorificBean instance = new HonorificBean();
        boolean expResult = false;
        boolean result = instance.update(honorific);
        assertEquals(expResult, result);
    }

}