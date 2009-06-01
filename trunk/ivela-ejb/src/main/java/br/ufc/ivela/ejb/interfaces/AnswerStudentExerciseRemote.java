/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.AnswerAuditiveQuestionStudentExercise;
import br.ufc.ivela.commons.model.AnswerExternalQuestionStudentExercise;
import br.ufc.ivela.commons.model.AnswerStudentExercise;
import br.ufc.ivela.commons.model.AnswerSubjectiveQuestionStudentExercise;
import br.ufc.ivela.commons.model.ObjectiveAnswerStudentExercise;
import br.ufc.ivela.commons.model.StudentExercise;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author emanuelle
 */
@Remote
public interface AnswerStudentExerciseRemote {
    
    
    public AnswerStudentExercise getAnswerStudentExercise(Long id);
//    public Long addStudentExercise(Long exerciseId, Long userId);
    public Long addAnswerStudentExercise(Long studentExerciseId,int questionType);
    
    /**
     * get number
     * @param userId
     * @param exerciseId
     * @return
     */
    public boolean getNumberStudentExercise(Long userId, Long exerciseId);
    
//    public List<StudentExercise> getStudentExercise(Long userId, Long exerciseId);
    
    /**
     * Overload for AnswerSubjectiveQuestionStudentExercise
     * @param question
     * @param answerStudentExercise
     * @param answer
     * @return
     */
    public Long addAnswerStudentQuestionExercise(Long questionId, Long answerStudentExerciseId,String answer, Long studentId, Long exerciseId,Long courseId);
    /**
     * ObjectiveAnswerStudentExercise
     * @param question
     * @param answerStudentExercise
     * @param objectiveAnswer
     * @return
     */
    public Long addAnswerStudentQuestionExercise(Long answerStudentExerciseId,Long objectiveAnswerId);    
    /**
     * AnswerAuditiveQuestionStudentExercise
     * @param question
     * @param answerStudentExercise
     * @param score
     * @param times
     * @return
     */
    public Long addAnswerStudentQuestionExercise(Long questionId, Long answerStudentExerciseId,BigDecimal score, int times);    
    /**
     * AnswerExternalQuestionStudentExercise
     * @param question
     * @param answerStudentExercise
     * @param aproved
     * @param resultValue
     * @return
     */
    public Long addAnswerStudentQuestionExercise(Long questionId, Long answerStudentExerciseId, Boolean aproved, String resultValue);    
    
    /**
     * Get AnswerObjective by answerstudentexerciseId
     * @param answerStudentExerciseId
     * @return
     */
     public ObjectiveAnswerStudentExercise getObjectiveAnswerStudentExerciseByAnswerStudentExercise( Long answerStudentExerciseId);
     /**
      * Get AnswerSubjective
      * @param anwerStudentExercise
      * @param questionId
      * @return
      */
     public AnswerSubjectiveQuestionStudentExercise getAnswerSubjectiveQuestionStudentExercise(Long anwerStudentExercise, Long questionId);
     /**
      * get answerauditve
      * @param anwerStudentExercise
      * @param questionId
      * @return
      */
     public AnswerAuditiveQuestionStudentExercise getAnswerAuditiveQuestionStudentExercise(Long anwerStudentExercise, Long questionId);
     /**
      * get answerExternal
      * @param anwerStudentExercise
      * @param questionId
      * @return
      */
      public AnswerExternalQuestionStudentExercise getAnswerExternalQuestionStudentExercise(Long anwerStudentExercise, Long questionId);
      
      /**
       * 
       * @param studentId
       * @param unitContentId
       */
      public List averageMark(Long studentId, Long unitContentId, Long gradeId, boolean isSubjectiveDone);
      
      public List<AnswerSubjectiveQuestionStudentExercise> getListAnswerSubjectiveQuestionStudentExercise(Long anwerStudentExercise);
      
      public List<AnswerStudentExercise> getListAnswerStudentExerciseTypeSubjective(Long idStudentExercise);
      
      public boolean updateAnswerSubjectiveQuestionStudentExercise(AnswerSubjectiveQuestionStudentExercise answerSubjectiveQuestionStudentExercise);
      
      public AnswerSubjectiveQuestionStudentExercise getAnswerSubjectiveQuestionStudentExerciseById(Long id);
      
      public boolean updateAllStudentExercise(StudentExercise studentExercise);
      
      public boolean hasSubjectiveQuestion(Long exerciseId);
      
      public Double averageExercisesByGrade(Long gradeId,Long studentId );
      
      public List<StudentExercise> getStudentExercise(Long userId, Long exerciseId, Long gradeId) ;
      
      public Long addStudentExercise(Long exerciseId, Long userId, Long courseId);

}
