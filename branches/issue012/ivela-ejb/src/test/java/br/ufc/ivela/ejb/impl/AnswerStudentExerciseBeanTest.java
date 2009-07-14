/*    
# Copyright(c) 2009 by IBM Brasil Ltda and others                                           #
# This file is part of ivela project, an open-source                                        #
# Program URL   : http://code.google.com/p/ivela/                                           #  
#                                                                                           #
# This program is free software; you can redistribute it and/or modify it under the terms   #
# of the GNU General Public License as published by the Free Software Foundation; either    #
# version 3 of the License, or (at your option) any later version.                          #
#                                                                                           #
# This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; #
# without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. #
# See the GNU General Public License for more details.                                      #  
#                                                                                           #
#############################################################################################
# File: AnswerStudentExerciseBeanTest.java                                                  #
# Document: Answer Student Exercise Unit Testl                                              # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# ??-???-2008 - jefferson (UFC)                   - XXXXXX - Initial Version                #
# 22-JUN-2009 - otofuji (Instituto Eldorado)      - 000010 - General Initial Fixes          #
*/
package br.ufc.ivela.ejb.impl;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.Transcript;

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
//           StateBean sb = new StateBean();
           
            
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
    