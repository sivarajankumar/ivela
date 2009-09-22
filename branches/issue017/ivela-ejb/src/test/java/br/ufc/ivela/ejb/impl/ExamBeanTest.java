/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.model.Exam;
import java.util.Date;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Maristella Myrian
 * /

/**
 * 
 * Class that implements the test of ejb ExamBean
 */
public class ExamBeanTest {

    ExamBean examBean;
    Long removeId;
    Long getId;

    public ExamBeanTest() {
        examBean = new ExamBean();
        removeId =4L;
        getId = 2L;
        
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of get method, of class ExamBean.
     */
    @Test
    public void testGet() {
       //System.out.println (examBean.isExamFinishedForStudent(6L,9L));
        //System.out.println(examBean.getQuestionNumber(63L));
        //assertNotNull(examBean.getMaxOrderNByUnitContent(1L));
    }
//
//    /**
//     * Test of add method, of class ExamBean.
//     */
//    @Test
//    public void testAdd() {
//        Exam exam = new Exam();
//        GenericDao<Grade> daoGrade = DaoFactory.getInstance(Grade.class);
//        exam.setId(getId+1);
////        exam.setGrade(daoGrade.get(10));
//        exam.setTitle("Teste");
//        exam.setStartDatetime(new Date());
//        exam.setEndDatetime(new Date());
//        assertNotNull(examBean.add(exam));
//    }
//
//    /**
//     * Test of remove method, of class ExamBean.
//     */
//    @Test
//    public void testRemove() {
//        assertNotNull(examBean.remove(removeId));
//    }
//
    /**
     * Test of getAll method, of class ExamBean.
     */
    @Test
    public void testGetAll() {
        /*Date date = new Date();
        Exam e =examBean.get(84L);
        if (date.after(e.getCreatedAt())) {
            System.out.print("data:"+"data");
        }
         if (date.before(e.getCreatedAt())) {
            System.out.print("databefore:"+"data");
        }
        List<Exam> l = examBean.getListExamByUnitContent(4L);
         System.out.println("size:"+l.size());
        for (Exam eem : l) {
            System.out.println("ex"+em.toString());
        }
        */
        System.out.println(":"+examBean.finishedExamsForUnitContent(4L, 5L,3L));
    }
//    
//    /**
//     * Test of listByUnit method, of class ExamBean.
//     */
//    @Test
//    public void testListByUnit() {
//        List<Exam> l = this.examBean.listByUnit(new Long(1));
//        assertTrue(l.size()>0);
//        for(Exam e : l){
//            System.out.println(e.getTitle());
//        }
//    }
}