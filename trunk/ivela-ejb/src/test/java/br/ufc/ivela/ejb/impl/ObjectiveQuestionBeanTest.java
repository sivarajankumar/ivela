/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.model.ObjectiveQuestion;
import br.ufc.ivela.commons.model.Question;
import br.ufc.ivela.commons.model.ObjectiveAnswer;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.SystemUser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author emanuelle
 * Class that implements the test of ejb ObjectiveQuestionBean
 */
public class ObjectiveQuestionBeanTest {

    ObjectiveQuestionBean objectiveQuestionBean;

    public ObjectiveQuestionBeanTest() {
        objectiveQuestionBean = new ObjectiveQuestionBean();
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
     * Test of add method, of class ObjectiveQuestionBean.
     */
    @Test
    public void testAdd() {
        ObjectiveQuestion objectiveQuestion = new ObjectiveQuestion();

        Question question = new Question();
        QuestionBean questionBean = new QuestionBean();
        GenericDao<SystemUser> daoSystemUser = DaoFactory.getInstance(SystemUser.class);
        question.setCreatedBy(daoSystemUser.get(new Long(1)));
        question.setQuestion("testedoTeste2");
        question.setType(2);
        questionBean.add(question);

        GenericDao<Question> daoQuestion = DaoFactory.getInstance(Question.class);
        Question q = daoQuestion.getByExample(question).get(0);

        ObjectiveAnswer correctAnswer = new ObjectiveAnswer();
        ObjectiveAnswerBean objectiveAnswerBean = new ObjectiveAnswerBean();
        correctAnswer.setQuestion(q);
        correctAnswer.setAnswer("testeresp");
        objectiveAnswerBean.add(correctAnswer);

        GenericDao<ObjectiveAnswer> daoObjectiveAnswer = DaoFactory.getInstance(ObjectiveAnswer.class);
        ObjectiveAnswer oa = daoObjectiveAnswer.getByExample(correctAnswer).get(0);

        objectiveQuestion.setQuestion(q);
        objectiveQuestion.setCorrectAnswer(oa);
        assertNotNull(objectiveQuestionBean.add(objectiveQuestion));
    }

    /**
     * Test of get method, of class ObjectiveQuestionBean.
     */
    @Test
    public void testGet() {

        assertNotNull(objectiveQuestionBean.get(1L));
    }

    /**
     * Test of getByQuestion method, of class ObjectiveQuestionBean.
     */
    @Test
    public void testGetByQuestion() {

        assertNotNull(objectiveQuestionBean.getByQuestion(1L));
    }

    /**
     * Test of edit method, of class ObjectiveQuestionBean
     */
    @Test
    public void testEdit() {
        ObjectiveQuestion objectiveQuestion = new ObjectiveQuestion();
        GenericDao<ObjectiveQuestion> daoObjectiveQuestion = DaoFactory.getInstance(ObjectiveQuestion.class);
        GenericDao<ObjectiveAnswer> daoObjectiveAnswer = DaoFactory.getInstance(ObjectiveAnswer.class);
        objectiveQuestion.setId(daoObjectiveQuestion.get(1L).getId());
        objectiveQuestion.setCorrectAnswer(daoObjectiveAnswer.get(1L));
        assertTrue(objectiveQuestionBean.edit(objectiveQuestion));
    }
}