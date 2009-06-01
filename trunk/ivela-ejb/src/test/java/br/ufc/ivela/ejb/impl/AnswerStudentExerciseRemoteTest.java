/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.model.AnswerAuditiveQuestionStudentExercise;
import br.ufc.ivela.commons.model.AnswerExternalQuestionStudentExercise;
import br.ufc.ivela.commons.model.AnswerStudentExercise;
import br.ufc.ivela.commons.model.AnswerSubjectiveQuestionStudentExercise;
import br.ufc.ivela.commons.model.ObjectiveAnswerStudentExercise;
import br.ufc.ivela.commons.model.StudentExercise;
import br.ufc.ivela.ejb.interfaces.AnswerStudentExerciseRemote;
import java.math.BigDecimal;
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
 */
public class AnswerStudentExerciseRemoteTest {

    public AnswerStudentExerciseRemoteTest() {
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
//    public void testGetAnswerStudentExercise() {
//        System.out.println("getAnswerStudentExercise");
//        Long id = null;
//        AnswerStudentExerciseRemote instance = new AnswerStudentExerciseRemote();
//        AnswerStudentExercise expResult = null;
//        AnswerStudentExercise result = instance.getAnswerStudentExercise(id);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testAddStudentExercise() {
//        System.out.println("addStudentExercise");
//        Long exerciseId = null;
//        Long userId = null;
//        AnswerStudentExerciseRemote instance = new AnswerStudentExerciseRemote();
//        Long expResult = null;
//        Long result = instance.addStudentExercise(exerciseId, userId);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testAddAnswerStudentExercise() {
//        System.out.println("addAnswerStudentExercise");
//        Long studentExerciseId = null;
//        int questionType = 0;
//        AnswerStudentExerciseRemote instance = new AnswerStudentExerciseRemote();
//        Long expResult = null;
//        Long result = instance.addAnswerStudentExercise(studentExerciseId, questionType);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetNumberStudentExercise() {
//        System.out.println("getNumberStudentExercise");
//        Long userId = null;
//        Long exerciseId = null;
//        AnswerStudentExerciseRemote instance = new AnswerStudentExerciseRemote();
//        boolean expResult = false;
//        boolean result = instance.getNumberStudentExercise(userId, exerciseId);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetStudentExercise() {
//        System.out.println("getStudentExercise");
//        Long userId = null;
//        Long exerciseId = null;
//        AnswerStudentExerciseRemote instance = new AnswerStudentExerciseRemote();
//        List<StudentExercise> expResult = null;
//        List<StudentExercise> result = instance.getStudentExercise(userId, exerciseId);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testAddAnswerStudentQuestionExercise_3args() {
//        System.out.println("addAnswerStudentQuestionExercise");
//        Long questionId = null;
//        Long answerStudentExerciseId = null;
//        String answer = "";
//        AnswerStudentExerciseRemote instance = new AnswerStudentExerciseRemote();
//        Long expResult = null;
//        Long result = instance.addAnswerStudentQuestionExercise(questionId, answerStudentExerciseId, answer);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testAddAnswerStudentQuestionExercise_Long_Long() {
//        System.out.println("addAnswerStudentQuestionExercise");
//        Long answerStudentExerciseId = null;
//        Long objectiveAnswerId = null;
//        AnswerStudentExerciseRemote instance = new AnswerStudentExerciseRemote();
//        Long expResult = null;
//        Long result = instance.addAnswerStudentQuestionExercise(answerStudentExerciseId, objectiveAnswerId);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testAddAnswerStudentQuestionExercise_4args_1() {
//        System.out.println("addAnswerStudentQuestionExercise");
//        Long questionId = null;
//        Long answerStudentExerciseId = null;
//        BigDecimal score = null;
//        int times = 0;
//        AnswerStudentExerciseRemote instance = new AnswerStudentExerciseRemote();
//        Long expResult = null;
//        Long result = instance.addAnswerStudentQuestionExercise(questionId, answerStudentExerciseId, score, times);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testAddAnswerStudentQuestionExercise_4args_2() {
//        System.out.println("addAnswerStudentQuestionExercise");
//        Long questionId = null;
//        Long answerStudentExerciseId = null;
//        Boolean aproved = null;
//        String resultValue = "";
//        AnswerStudentExerciseRemote instance = new AnswerStudentExerciseRemote();
//        Long expResult = null;
//        Long result = instance.addAnswerStudentQuestionExercise(questionId, answerStudentExerciseId, aproved, resultValue);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetObjectiveAnswerStudentExerciseByAnswerStudentExercise() {
//        System.out.println("getObjectiveAnswerStudentExerciseByAnswerStudentExercise");
//        Long answerStudentExerciseId = null;
//        AnswerStudentExerciseRemote instance = new AnswerStudentExerciseRemote();
//        ObjectiveAnswerStudentExercise expResult = null;
//        ObjectiveAnswerStudentExercise result = instance.getObjectiveAnswerStudentExerciseByAnswerStudentExercise(answerStudentExerciseId);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetAnswerSubjectiveQuestionStudentExercise() {
//        System.out.println("getAnswerSubjectiveQuestionStudentExercise");
//        Long anwerStudentExercise = null;
//        Long questionId = null;
//        AnswerStudentExerciseRemote instance = new AnswerStudentExerciseRemote();
//        AnswerSubjectiveQuestionStudentExercise expResult = null;
//        AnswerSubjectiveQuestionStudentExercise result = instance.getAnswerSubjectiveQuestionStudentExercise(anwerStudentExercise, questionId);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetAnswerAuditiveQuestionStudentExercise() {
//        System.out.println("getAnswerAuditiveQuestionStudentExercise");
//        Long anwerStudentExercise = null;
//        Long questionId = null;
//        AnswerStudentExerciseRemote instance = new AnswerStudentExerciseRemote();
//        AnswerAuditiveQuestionStudentExercise expResult = null;
//        AnswerAuditiveQuestionStudentExercise result = instance.getAnswerAuditiveQuestionStudentExercise(anwerStudentExercise, questionId);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetAnswerExternalQuestionStudentExercise() {
//        System.out.println("getAnswerExternalQuestionStudentExercise");
//        Long anwerStudentExercise = null;
//        Long questionId = null;
//        AnswerStudentExerciseRemote instance = new AnswerStudentExerciseRemote();
//        AnswerExternalQuestionStudentExercise expResult = null;
//        AnswerExternalQuestionStudentExercise result = instance.getAnswerExternalQuestionStudentExercise(anwerStudentExercise, questionId);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testAverageMark() {
//        System.out.println("averageMark");
//        Long studentId = null;
//        Long unitContentId = null;
//        AnswerStudentExerciseRemote instance = new AnswerStudentExerciseRemote();
//        double expResult = 0.0;
//        double result = instance.averageMark(studentId, unitContentId);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
   /* @Test
    public void testGetScoresExerciseExamByCourse() {
        System.out.println("getScoresExerciseExamByCourse");
        Long courseId = 7L;
        Long studentId = 6L;
        AnswerStudentExerciseBean instance = new AnswerStudentExerciseBean();
        //String result = instance.getScoresExerciseExamByCourse(courseId, studentId);
        assertNotNull(result);
        fail("The test case is a prototype.");
    }*/

}