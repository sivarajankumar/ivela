/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.AnswerStudentExercise;
import br.ufc.ivela.commons.model.AnswerSubjectiveQuestionStudentExercise;
import br.ufc.ivela.commons.model.State;
import br.ufc.ivela.commons.model.StudentExercise;
import br.ufc.ivela.commons.model.Transcript;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jefferson
 */
public class AnswerStudentExerciseBeanTest {

    private AnswerStudentExerciseBean aseb;
   private GenericDao<Transcript> daoTranscript = DaoFactory.getInstance(Transcript.class);

    public AnswerStudentExerciseBeanTest() {
        aseb = new AnswerStudentExerciseBean();
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

//    @Test
//    public void testAddStudentExercise() {
//        //Long exeid = new Long(1);
//        //Long userid = new Long(9);
//        //aseb.addAnswerStudentQuestionExercise(616L, null, "Darth Vader", 102L);
//         
//      
//    }
//    @Test
//    public void testListAnswerSubjectiveStudentExercise() {
//        Long exeid = new Long(292);
//        Long userid = new Long(9);
//
//        List<AnswerSubjectiveQuestionStudentExercise> list = new ArrayList<AnswerSubjectiveQuestionStudentExercise>();
//
//        List<StudentExercise> listStudentExercise = aseb.getStudentExercise(userid, exeid);
//
//        String json = "";
//        json += "{'list':";
//        if (!listStudentExercise.isEmpty()) {
//            StudentExercise se = listStudentExercise.get(0);
//            List<AnswerStudentExercise> listASE = aseb.getListAnswerStudentExerciseTypeSubjective(se.getId());
//            json += "{'asqse':";
//            if (!listASE.isEmpty()) {
//                json += "[";
//                for (AnswerStudentExercise answerStudentExercise : listASE) {
//                    AnswerSubjectiveQuestionStudentExercise temp = aseb.getListAnswerSubjectiveQuestionStudentExercise(answerStudentExercise.getId()).get(0);
//                    //temp.setAnswerStudentExercise(null);
//                    json += "{'id':'" + temp.getId() + "',";
//                    json += "'question':'" + temp.getQuestion().getId() + "',";
//                    json += "'score':'" + temp.getScore() + "',";
//                    json += "'answer':'" + temp.getAnswer() + "'},";
//
//                    list.add(temp);
//                }
//                json = json.substring(0, json.length() - 1);
//                json += "]";
//            }
//            else{
//                json += "''";
//            }
//            json += "}";
//        } else {
//            json += "''";
//        }
//
//        json += "}";
//
//        System.out.println(json);
//
//    }
//}
//    @Test
//    public void testAddStudentExercise() {
//        //Long exeid = new Long(1);
//        //Long userid = new Long(9);
//        //aseb.addAnswerStudentQuestionExercise(616L, null, "Skywalker", 9L,292L);
//         System.out.println("==>"+aseb.averageMark(9L,214L));
//      
//    }
//}

        @Test
    public void testAddStudentExercise() {
           StateBean sb = new StateBean();
           
            
            // PhoneBean pb = new PhoneBean();
           // System.out.println("--"+pb.lastId());
            
            //StateBean sb = new StateBean();
            //System.out.println("---"+sb.lastId());
        //Long cursoid = 7L;
        //Long userid = 9L;
        
        //System.out.println("==>"+aseb.getScoresExerciseExamByCourse(cursoid, userid));
            //System.out.println("==>"+aseb.averageMark(26L,306L,false));
        //System.out.println("-->"+aseb.getWeight(300L, 660L));     
        //aseb.getWeight(300L, 660L);
             
    }
}
    
//@Test
//    public void testAddStudentExercise() {
//
//        Long id = new Long(34);
//
//        AnswerSubjectiveQuestionStudentExercise temp = aseb.getAnswerSubjectiveQuestionStudentExerciseById(id);
//        temp.setScore(null);
//
//        aseb.updateAnswerSubjectiveQuestionStudentExercise(temp);
//    }
//}
    