/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.impl;

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
public class AnswerStudentExamBeanTest {

    public AnswerStudentExamBeanTest() {
        
        
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
/*
    @Test
    public void testAddStudentExam() {
        System.out.println("addStudentExam");
        Long examId = null;
        Long userId = null;
        AnswerStudentExamBean instance = new AnswerStudentExamBean();
        Long expResult = null;
        Long result = instance.addStudentExam(examId, userId);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetStudentExam() {
        System.out.println("getStudentExam");
        Long userId = null;
        Long examId = null;
        AnswerStudentExamBean instance = new AnswerStudentExamBean();
        List<StudentExam> expResult = null;
        List<StudentExam> result = instance.getStudentExam(userId, examId);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
*/
    @Test
    public void testGetNumberStudentExam() {
        //System.out.println("getNumberStudentExam");
        //Long userId = 4L;
        //Long examId = 138L;
        AnswerStudentExamBean instance = new AnswerStudentExamBean();
       // instance.addStudentExam(examId,userId);
        //instance.addAnswerStudentExam(10L, 1);
        //System.out.println("-======="+instance.averageMark(9L, 130L)); 
        //System.out.println(instance.averageMark(9L, 130L,false));
       //System.out.println(instance.getStudentExam(6L, 130L).toString());
        
         
    }
    /*

    @Test
    public void testGetAnswerStudentExam() {
        System.out.println("getAnswerStudentExam");
        Long id = null;
        AnswerStudentExamBean instance = new AnswerStudentExamBean();
        AnswerStudentExam expResult = null;
        AnswerStudentExam result = instance.getAnswerStudentExam(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetObjectiveAnswerStudentExam() {
        System.out.println("getObjectiveAnswerStudentExam");
        Long id = null;
        AnswerStudentExamBean instance = new AnswerStudentExamBean();
        ObjectiveAnswerStudentExam expResult = null;
        ObjectiveAnswerStudentExam result = instance.getObjectiveAnswerStudentExam(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetObjectiveAnswerStudentExamByAnswerStudentExam() {
        System.out.println("getObjectiveAnswerStudentExamByAnswerStudentExam");
        Long answerStudentExamId = null;
        AnswerStudentExamBean instance = new AnswerStudentExamBean();
        ObjectiveAnswerStudentExam expResult = null;
        ObjectiveAnswerStudentExam result = instance.getObjectiveAnswerStudentExamByAnswerStudentExam(answerStudentExamId);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetAnswerSubjectiveQuestionStudentExam() {
        System.out.println("getAnswerSubjectiveQuestionStudentExam");
        Long anwerStudentExam = null;
        Long questionId = null;
        AnswerStudentExamBean instance = new AnswerStudentExamBean();
        AnswerSubjectiveQuestionStudentExam expResult = null;
        AnswerSubjectiveQuestionStudentExam result = instance.getAnswerSubjectiveQuestionStudentExam(anwerStudentExam, questionId);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetAnswerAuditiveQuestionStudentExam() {
        System.out.println("getAnswerAuditiveQuestionStudentExam");
        Long anwerStudentExam = null;
        Long questionId = null;
        AnswerStudentExamBean instance = new AnswerStudentExamBean();
        AnswerAuditiveQuestionStudentExam expResult = null;
        AnswerAuditiveQuestionStudentExam result = instance.getAnswerAuditiveQuestionStudentExam(anwerStudentExam, questionId);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetAnswerExternalQuestionStudentExam() {
        System.out.println("getAnswerExternalQuestionStudentExam");
        Long anwerStudentExam = null;
        Long questionId = null;
        AnswerStudentExamBean instance = new AnswerStudentExamBean();
        ExternalAnswerStudentExam expResult = null;
        ExternalAnswerStudentExam result = instance.getAnswerExternalQuestionStudentExam(anwerStudentExam, questionId);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testAddAnswerStudentExam() {
        System.out.println("addAnswerStudentExam");
        Long studentExamId = null;
        AnswerStudentExamBean instance = new AnswerStudentExamBean();
        Long expResult = null;
        Long result = instance.addAnswerStudentExam(studentExamId);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testAddAnswerStudentQuestionExam_3args_1() {
        System.out.println("addAnswerStudentQuestionExam");
        Long questionId = null;
        Long answerStudentExamId = null;
        String answer = "";
        AnswerStudentExamBean instance = new AnswerStudentExamBean();
        Long expResult = null;
        Long result = instance.addAnswerStudentQuestionExam(questionId, answerStudentExamId, answer);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testAddAnswerStudentQuestionExam_Long_Long() {
        System.out.println("addAnswerStudentQuestionExam");
        Long answerStudentExamId = null;
        Long objectiveAnswerId = null;
        AnswerStudentExamBean instance = new AnswerStudentExamBean();
        Long expResult = null;
        Long result = instance.addAnswerStudentQuestionExam(answerStudentExamId, objectiveAnswerId);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testAddAnswerStudentQuestionExam_3args_2() {
        System.out.println("addAnswerStudentQuestionExam");
        Long questionId = null;
        Long answerStudentExamId = null;
        BigDecimal score = null;
        AnswerStudentExamBean instance = new AnswerStudentExamBean();
        Long expResult = null;
        Long result = instance.addAnswerStudentQuestionExam(questionId, answerStudentExamId, score);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testAddAnswerStudentQuestionExam_4args() {
        System.out.println("addAnswerStudentQuestionExam");
        Long questionId = null;
        Long answerStudentExamId = null;
        Boolean aproved = null;
        String resultValue = "";
        AnswerStudentExamBean instance = new AnswerStudentExamBean();
        Long expResult = null;
        Long result = instance.addAnswerStudentQuestionExam(questionId, answerStudentExamId, aproved, resultValue);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }*/

}