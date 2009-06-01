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
 * @author rodrigo
 */
public class PostResourceTest {
    
    PostResource postResource;
    
    public PostResourceTest() {
        postResource = new PostResource();
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of add method, of class PostResource.
     */
    @Test
    public void testAdd() {       
        assertTrue(postResource.add("1", "Ser ou nao ser", "1", "das dasdasda sda sd asd asd asd asd asd as").equals("ok"));
    }

    /**
     * Test of get method, of class PostResource.
     */
    @Test
    public void testGet() {
        assertNotNull(postResource.get("1"));
    }

    /**
     * Test of remove method, of class PostResource.
     */
    @Test
    public void testRemove() {
        assertNotNull(postResource.remove("9"));
    }

}