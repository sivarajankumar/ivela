/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.model.SystemUser;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rodrigo
 */
public class SystemUserBeanTest {

    SystemUserBean su;
    
    public SystemUserBeanTest() {
        su = new SystemUserBean();
    }
/*
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
*/
    /**
     * Test of get method, of class SystemUserBean.
     */
    @Test
    public void testGet() {
        List<SystemUser> list = su.getByUsername("marcu");
        assertNotNull(list);
    }

//    /**
//     * Test of list method, of class SystemUserBean.
//     */
//    @Test
//    public void testList() {
//        assertNotNull(su.list());
//        assertTrue(su.list().size() > 0);
//    }
//
//    /**
//     * Test of add method, of class SystemUserBean.
//     */
//    @Test
//    public void testAdd() {
//        SystemUser systemUser = new SystemUser();
//        systemUser.setUsername("André Carlos Vieira");
//        systemUser.setEmail("asdasd@dasdasd.com");
//        systemUser.setUsername("andrejr");
//        systemUser.setPassword("dasdasd");
//        
//        assertNotNull(su.add(systemUser));
//    }
//
//    /**
//     * Test of remove method, of class SystemUserBean.
//     */
//    @Test
//    public void testRemove() {
//        assertTrue(su.remove(1L));
//    }

}