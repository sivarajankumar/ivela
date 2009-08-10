/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Maristella Myrian
 */
public class ExerciseBeanTest {

    ExerciseBean exerciseBean;
    AnswerStudentExerciseBean answerStudentExerciseBean;
    QuestionExerciseBean qeb = new QuestionExerciseBean(); 
    ChallengeItemsBean cib = new ChallengeItemsBean();

    
    public ExerciseBeanTest() {
        exerciseBean = new ExerciseBean();
        answerStudentExerciseBean = new AnswerStudentExerciseBean();
        //studentExercise = new StudentExerciseBean();
    //lastId = 3;
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of getAll method, of class ExerciseBean.
     */
    @Test
    public void testGetAll() {
        /*Page p = exerciseBean.getQuestionPageByExercise(new Long(1),5,1);
        for(int i=0;i<p.getList().size();i++){
        Question q = (Question)p.getList().get(i);
        System.out.println("--->"+q.getId());
        }*/

        /*Page p = exerciseBean.getQuestionPageByExerciseGambi(new Long(1), 5, 1);
        for (int i = 0; i < p.getList().size(); i++) {
            Question q = (Question) p.getList().get(i);
            System.out.println("---+" + q.getId());
        }*/
        /*List<Question>  questions = exerciseBean.getQuestions(new Long(1));
        for(Question q: questions){
            System.out.println("$$$$"+q.getId()+"-"+q.getType());
        }*/
           
        //int i = exerciseBean.getMaxOrederNByUnitContent(1L);
        //System.out.println("i"+i);
        //Exercise e = exerciseBean.get(4L);
        //System.out.println(exerciseBean.isExerciseFinishedForStudent(1L, 9L));
        //Assert.assertTrue(e.getRequisites().size() == 1);
        //exerciseBean.addRequisite(8L, 1L);
        //this.addRequisitesWithJason("1ab7b4");
        //StudentExercise se = daoStudentExercise.get(6L);
        //System.out.println("-->"+se.getChances());
        //exerciseBean.updateStudentExerciseChances(9L, 1L);
        //System.out.println("-->"+exerciseBean.getChancesStudentExercise(9L, 3L));
        //answerStudentExerciseBean.addStudentExercise(2L,2L);
        //exerciseBean.updateStudentExerciseChances(2L, 2L);
        //System.out.println(exerciseBean.getChancesStudentExercise(2L, 2L));
        //System.out.println(answerStudentExerciseBean.averageMark(9L,1L));
        //System.out.println(cib.getChallengerResult(1L));
        //System.out.println(exerciseBean.finishedExerciseForUnitContent(4L, 5L,3L));
        //UnitBean ub = new UnitBean();
        //System.out.println("--"+ub.isUnitFinished(4L, 4L, 3L));
        //DisciplineBean disciplineBean = new DisciplineBean();
        //ystem.out.println("==> "+disciplineBean.isDisciplineFinished(4L, 2L, 3L));
        CourseBean cb = new CourseBean();
        System.out.println("--"+cb.isFinishedCourse(4L, 2L, 1L));
    }
    
    /*public String addRequisitesWithJason(String requisitesString){
       
        Exercise exe = this.exerciseBean.get(66L);
        System.out.println(exe.getRequisites().size());
        Set<Exercise> requisites = exe.getRequisites();
        Exercise erq = this.exerciseBean.get(5L); 
        requisites.add(erq);
        exerciseBean.update(exe);    
        return "list";
    }*/
}