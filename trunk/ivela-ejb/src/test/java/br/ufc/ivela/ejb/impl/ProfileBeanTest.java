/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.dao.GenericDaoImpl;
import br.ufc.ivela.commons.model.Profile;
import br.ufc.ivela.commons.model.SystemUser;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author emanuelle
 * 
 * Class that implements the test of ejb ProfileBean
 */
public class ProfileBeanTest {
    
    ProfileBean profileBean;
    Profile profile;
    Long Lastid;
    public ProfileBeanTest() {
        profile = new Profile();
        profileBean = new ProfileBean();
        Lastid = 1L;
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
     * Test of getAll method, of class ProfileBean.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        List<Profile> list = profileBean.getAll();
        System.out.println(list.size());
        assertTrue(list.size() > 0);

    }


    /**
     * Test of get method, of class ProfileBean.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        assertNotNull(profileBean.get(Lastid));
    }
    
    
    /**
     * Test of add method, of class QuestionBean.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Profile profile1 = new Profile();
        SystemUser systeUser = new SystemUser();
        systeUser.setEmail("ema@g.com");
        systeUser.setPassword("123");
        systeUser.setSocialNumber("0215748");
        systeUser.setUsername("manu");
        systeUser.setEnabled(true);
        GenericDao<SystemUser> dao = DaoFactory.getInstance(SystemUser.class);
        System.out.println(dao.save(systeUser));
//        profile1.setName("emanuelle");
//        profile1.setNickname("manu");
        profile1.setPhoto("dfj");
        profile1.setGender(2);
        assertNotNull(profileBean.add(profile1));
    }

    /**
     * Test of remove method, of class QuestionBean.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        GenericDao<Profile> dao = DaoFactory.getInstance(Profile.class);
        assertTrue(profileBean.remove(dao.get(new Long(dao.getAll().size())).getId()));
    }
}