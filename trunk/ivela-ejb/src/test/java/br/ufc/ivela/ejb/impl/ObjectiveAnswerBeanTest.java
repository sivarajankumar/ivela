/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.dao.GenericDaoImpl;
import br.ufc.ivela.commons.model.ObjectiveAnswer;
import br.ufc.ivela.commons.model.Question;
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
public class ObjectiveAnswerBeanTest {

    ObjectiveAnswerBean objectiveAnswerBean;

    public ObjectiveAnswerBeanTest() {
        objectiveAnswerBean = new ObjectiveAnswerBean();
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
     * Test of get method, of class ObjectiveAnswerBean.
     */
    @Test
    public void testGet() {
        assertNotNull(objectiveAnswerBean.get(1L));
    }

    /**
     * Test of getByQuestion method, of class ObjectiveAnswerBean.
     */
    @Test
    public void testGetByQuestion() {
        List<ObjectiveAnswer> list = objectiveAnswerBean.getByQuestion(2L);
        assertTrue(list.size() > 0);
    }

    /**
     * Test of edit method, of class ObjectiveAnswerBean
     */
    @Test
    public void testEdit() {
        ObjectiveAnswer objectiveAnswer = new ObjectiveAnswer();
        objectiveAnswer.setAnswer("novoteste");
        objectiveAnswer.setId(1L);
        assertTrue(objectiveAnswerBean.edit(objectiveAnswer));
    }

    /**
     * Test of add method, of class ObjectiveAnswerBean.
     */
    @Test
    public void testAdd() {
        ObjectiveAnswer objectiveAnswer = new ObjectiveAnswer();
        GenericDao<Question> daoQuestion = DaoFactory.getInstance(Question.class);
        objectiveAnswer.setAnswer("testeanswer");
        objectiveAnswer.setQuestion(daoQuestion.get(1L));
        assertNotNull(objectiveAnswerBean.add(objectiveAnswer));
    }

    /**
     * Test of remove method, of class ObjectiveAnswerBean.
     */
    @Test
    public void testRemove() {
        GenericDao<ObjectiveAnswer> daoObjectiveAnswer = DaoFactory.getInstance(ObjectiveAnswer.class);
        System.out.println("size:" + (daoObjectiveAnswer.getAll().size() - 1));
        System.out.println(daoObjectiveAnswer.getAll().get((daoObjectiveAnswer.getAll().size()) - 1).getId().toString());
        assertTrue(objectiveAnswerBean.remove(daoObjectiveAnswer.getAll().get(daoObjectiveAnswer.getAll().size() - 1).getId()));
    }
}