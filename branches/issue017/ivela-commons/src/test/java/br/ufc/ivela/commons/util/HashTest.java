/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.commons.util;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rodrigo
 */
public class HashTest {

    public HashTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of generateHash method, of class Hash.
     */
    @Test
    public void testGenerateHash_String() {
        String result = Hash.generateHash("rodrigofelix");
        System.out.println(result);
        
        assertNotNull(Hash.generateHash("rodrigofelix"));        
    }

    /**
     * Test of generateHash method, of class Hash.
     */
    @Test
    public void testGenerateHash_String_String() {
        String result = Hash.generateHash("rodrigofelix", "SHA-1");
        System.out.println(result);
        
        assertNotNull(Hash.generateHash("rodrigofelix"));
        
        result = Hash.generateHash("rodrigofelix","MD5");
        System.out.println(result);
        
        assertNotNull(Hash.generateHash("rodrigofelix"));
    }

    /**
     * Test of hexaToString method, of class Hash.
     */
    @Test
    public void testHexaToString() {
    }

}