/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.History;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.commons.model.Transcript;
import java.util.Date;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Class that implements the test of ejb ForumBean
 */
public class HistoryBeanTest {

    HistoryBean historyBean;
    private long lastId;
    
    public HistoryBeanTest() {
        historyBean = new HistoryBean();
    }

    /**
     * Test of add method, of class ForumBean.
     */
//    @Test
//    public void testAdd() {
//        History history = new History();
//        GenericDao<SystemUser> daoSystemUser = DaoFactory.getInstance(SystemUser.class);
//        history.setSystemUser(daoSystemUser.get(1L));
//        history.setDatetime(new Date());
//        history.setTitle("Teste");
//        history.setDescription("Teste Description");
//        lastId = historyBean.add(history);
//        assertNotNull(lastId);
//    }
//
//    @Test
//    public void testRemove() {
//        System.out.println("remove");
//        Long id = null;
//        HistoryBean instance = new HistoryBean();
//        boolean expResult = false;
//        boolean result = instance.remove(id);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGet() {
//        System.out.println("get");
//        Long id = null;
//        HistoryBean instance = new HistoryBean();
//        History expResult = null;
//        History result = instance.get(id);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testAdd_History() {
//        System.out.println("add");
//        History history = null;
//        HistoryBean instance = new HistoryBean();
//        Long expResult = null;
//        Long result = instance.add(history);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetAll() {
//        System.out.println("getAll");
//        HistoryBean instance = new HistoryBean();
//        List<History> expResult = null;
//        List<History> result = instance.getAll();
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetBySystemUser() {
//        System.out.println("getBySystemUser");
//        Long systemUserId = null;
//        HistoryBean instance = new HistoryBean();
//        List<History> expResult = null;
//        List<History> result = instance.getBySystemUser(systemUserId);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testUpdate() {
//        System.out.println("update");
//        History history = null;
//        HistoryBean instance = new HistoryBean();
//        boolean expResult = false;
//        boolean result = instance.update(history);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testAddTranscript() {
//        System.out.println("addTranscript");
//        Long gradeId = null;
//        Long studentId = null;
//        HistoryBean instance = new HistoryBean();
//        Long expResult = null;
//        Long result = instance.addTranscript(gradeId, studentId);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetTranscriptsByStudent() {
//        System.out.println("getTranscriptsByStudent");
//        Long studentId = null;
//        HistoryBean instance = new HistoryBean();
//        List<Transcript> expResult = null;
//        List<Transcript> result = instance.getTranscriptsByStudent(studentId);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }

    @Test
    public void testGetTranscript() {
        System.out.println("getTranscript");
        Long transcriptId = null;
        HistoryBean instance = new HistoryBean();
        Transcript expResult = null;
        instance.getTranscriptsByStudent(6L);
       
    }

//    @Test
//    public void testCalcAverageCourse() {
//        System.out.println("calcAverageCourse");
//        Long gradeId = null;
//        Long studentId = null;
//        HistoryBean instance = new HistoryBean();
//        instance.calcAverageCourse(gradeId, studentId);
//        fail("The test case is a prototype.");
//    }

//    @Test
//    public void testGetAll() {
//        List<History> list = historyBean.getAll();
//        assertTrue(list.size() > 0);
//        lastId = list.get(list.size() - 1).getId();
//    }
//    
//    @Test
//    public void testGet() {
//        assertNotNull(historyBean.get(1L));
//    }
//
//    @Test
//    public void testRemove() {
//        assertTrue(historyBean.remove(1L));
//    }

}