/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.model.Challenge;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author emanuelle
 */
public class ChallengeBeanTest {

    public ChallengeBeanTest() {
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

//    @Test
//    public void testAdd_Challenge() {
//        System.out.println("add");
//        Challenge challenge = null;
//        ChallengeBean instance = new ChallengeBean();
//        Long expResult = null;
//        Long result = instance.add(challenge);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//////
//    @Test
//    public void testGet() {
//        System.out.println("get");
//        String challid = "";
//        long uid = 0L;
//        ChallengeBean instance = new ChallengeBean();
//        Challenge expResult = null;
//        Challenge result = instance.get(challid, uid);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
    @Test
    public void testAdd_3args() {
        System.out.println("add");
        String challid = "2";
        long uid = 1L;
        double challvalue = 7.0;
        System.out.print("blabla666666");
        ChallengeBean instance = new ChallengeBean();
        System.out.print("blabla");
        Long expResult = 2L;
        Long result = instance.add(challid, uid, challvalue);
        System.out.print("kkkk");
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

//    @Test
//    public void testGetChallValue() {
//        System.out.println("getChallValue");
//        String challid = "";
//        long uid = 0L;
//        ChallengeBean instance = new ChallengeBean();
//        double expResult = 0.0;
//        double result = instance.getChallValue(challid, uid);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }

}