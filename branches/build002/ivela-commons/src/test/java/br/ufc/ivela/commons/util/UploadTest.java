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
public class UploadTest {

    public UploadTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of renameFile method, of class Upload.
     */
    @Test
    public void testRenameFile() {
        Upload u = new Upload();
        String result = u.renameFile("rodrigofelix.tar.gz");
        System.out.println(result);
        assertNotNull(u);
    }

    /**
     * Test of getExtension method, of class Upload.
     */
    @Test
    public void testGetExtension() {
        Upload u = new Upload();
        String result = u.getExtension("rodrigo.tar.gz");
        System.out.println(result);
        
        assertTrue(result.equals("tar.gz"));
        
        result = u.getExtension("rodrigo-arquivo-sem-extensao");
        System.out.println(result);
        
        assertTrue(result.equals(""));
    }

}