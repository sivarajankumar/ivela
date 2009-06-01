/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.Question;
import br.ufc.ivela.commons.model.QuestionText;
import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author emanuelle
 * Class that implements the test of ejb QuestionBean
 */
public class QuestionBeanTest {

    QuestionBean questionBean;
    Question qt;
    long lastId;

    private GenericDao<QuestionText> daoQuestionText = DaoFactory.getInstance(QuestionText.class);
    public QuestionBeanTest() {
        questionBean = new QuestionBean();
        lastId = 2;
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

//    /**
//     * Test of getAll method, of class QuestionBean.
//     */
//    @Test
//    public void testGetAll() {
//        System.out.println("getAll");
//        //List<QuestionText> questionTexts = questionBean.getQuestionTextByQuestion(3L);
//       //System.out.println("getAll: " + questionTexts.size());
//        //assertNotNull(questionTexts);
//        
//    }
//
//    @Test
//    public void testAdd() {
//        System.out.println("add");
//        Question question = null;
//        QuestionBean instance = new QuestionBean();
//        Long expResult = null;
//        Long result = instance.add(question);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }

    @Test
    public void testAddQuestionText() {
       // System.out.println("addQuestionText");
       // Long t = 298L;
        //QuestionBean instance = new QuestionBean();
       // QuestionText questionText = questionBean.getQuestionText(32L) ;
       // File file = new File("/home/emanuelle/Desktop/cnpq.gif");
       // Long result = instance.addQuestionText(questionText, file, "/var/www/ivela_sound/1/1/exercise/228");
       // assertEquals(t, result);
       // fail("The test case is a prototype.");
        QuestionText qt = daoQuestionText.get(7L);
        System.out.println(qt.getText().trim());
    }
//
//    @Test
//    public void testGet() {
//        System.out.println("get");
//        Long id = null;
//        QuestionBean instance = new QuestionBean();
//        Question expResult = null;
//        Question result = instance.get(id);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testRemove() {
//        System.out.println("remove");
//        Long id = null;
//        QuestionBean instance = new QuestionBean();
//        boolean expResult = false;
//        boolean result = instance.remove(id);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testEdit() {
//        System.out.println("edit");
//        Question question = null;
//        QuestionBean instance = new QuestionBean();
//        boolean expResult = false;
//        boolean result = instance.edit(question);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetByType() {
//        System.out.println("getByType");
//        int type = 0;
//        QuestionBean instance = new QuestionBean();
//        List<Question> expResult = null;
//        List<Question> result = instance.getByType(type);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetQuestionTextByQuestion() {
//        System.out.println("getQuestionTextByQuestion");
//        Long questionId = null;
//        QuestionBean instance = new QuestionBean();
//        QuestionText expResult = null;
//        QuestionText result = instance.getQuestionTextByQuestion(questionId);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetQuestionText() {
//        System.out.println("getQuestionText");
//        Long questionTextId = null;
//        QuestionBean instance = new QuestionBean();
//        QuestionText expResult = null;
//        QuestionText result = instance.getQuestionText(questionTextId);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetAll1() {
//        System.out.println("getAll");
//        QuestionBean instance = new QuestionBean();
//        List<Question> expResult = null;
//        List<Question> result = instance.getAll();
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testUpdate() {
//        System.out.println("update");
//        Question question = null;
//        QuestionBean instance = new QuestionBean();
//        boolean expResult = false;
//        boolean result = instance.update(question);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }


}
