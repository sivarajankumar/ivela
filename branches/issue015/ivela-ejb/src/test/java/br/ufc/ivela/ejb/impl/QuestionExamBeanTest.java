/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.model.Exam;
import br.ufc.ivela.commons.model.Question;
import br.ufc.ivela.commons.model.QuestionExam;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author maristella
 */
public class QuestionExamBeanTest {

    QuestionExamBean questionExamBean;
    Long lastId;

    public QuestionExamBeanTest() {
        questionExamBean = new QuestionExamBean();
        lastId = 2L;
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of get method, of class QuestionExamBean.
     */
    @Test
    public void testGet() {
        assertNotNull(questionExamBean.get(lastId));
    }

    /**
     * Test of getByExam method, of class QuestionExamBean.
     */
    @Test
    public void testGetByExam() {
        assertNotNull(questionExamBean.getByExam(lastId));

    }

    /**
     * Test of add method, of class QuestionExamBean.
     */
    @Test
    public void testAdd() {
        Exam exam = new Exam();
        Question question = new Question();
        QuestionExam qe = new QuestionExam();
        exam.setId(1L);
        question.setId(2L);
        qe.setExam(exam);
        qe.setQuestion(question);
        assertNotNull(questionExamBean.add(qe));
    }

    /**
     * Test of remove method, of class QuestionExamBean.
     */
    @Test
    public void testRemove() {
        assertNotNull(questionExamBean.remove(lastId));
    }
}