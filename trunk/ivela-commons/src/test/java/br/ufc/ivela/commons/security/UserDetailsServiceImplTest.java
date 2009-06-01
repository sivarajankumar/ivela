/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.commons.security;

import br.ufc.ivela.commons.model.SystemUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marcus
 */
public class UserDetailsServiceImplTest {

    UserDetailsServiceImpl userDetailsService;

    @Before
    public void setUp() {
        userDetailsService = new UserDetailsServiceImpl();
    }

    @After
    public void tearDown() {
        userDetailsService = null;
    }

    /**
     * Test of loadUserByUsername method, of class UserDetailsServiceImpl.
     */
    @Test
    public void testLoadUserByUsername() {
        SystemUser systemUser = (SystemUser) userDetailsService.loadUserByUsername("marcus");
        
        assertNotNull(systemUser);
    }

}