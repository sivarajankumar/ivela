/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.AnswerAuditiveQuestionStudentExam;
import br.ufc.ivela.commons.model.AnswerStudentExam;
import br.ufc.ivela.commons.model.AnswerSubjectiveQuestionStudentExam;
import br.ufc.ivela.commons.model.ExternalAnswerStudentExam;
import br.ufc.ivela.commons.model.ObjectiveAnswerStudentExam;
import br.ufc.ivela.commons.model.StudentExam;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Remote;



/**
 *
 * @author emanuelle
 */
@Remote
public interface AnswerStudentExamRemote {
        public AnswerStudentExam getAnswerStudentExam(Long id);
//    public Long addStudentExam(Long examId, Long userId);
    public Long addAnswerStudentExam(Long studentExamId, int type);
    
     public boolean getNumberStudentExam(Long userId, Long examId);
    
//    public List<StudentExam> getStudentExam(Long userId, Long examId);
    
    /**
     * Overload for AnswerSubjectiveQuestionStudentExam
     * @param question
     * @param answerStudentExam
     * @param answer
     * @return
     */
    public Long addAnswerStudentQuestionExam(Long questionId, Long answerStudentExamId,String answer);
    /**
     * ObjectiveAnswerStudentExam
     * @param question
     * @param answerStudentExam
     * @param objectiveAnswer
     * @return
     */
    public Long addAnswerStudentQuestionExam(Long answerStudentExamId,Long objectiveAnswerId);    
    /**
     * AnswerAuditiveQuestionStudentExam
     * @param question
     * @param answerStudentExam
     * @param score
     * @return
     */
    public Long addAnswerStudentQuestionExam(Long questionId, Long answerStudentExamId,BigDecimal score);    
    /**
     * AnswerExternalQuestionStudentExam
     * @param question
     * @param answerStudentExam
     * @param aproved
     * @param resultValue
     * @return
     */
    public Long addAnswerStudentQuestionExam(Long questionId, Long answerStudentExamId, Boolean aproved, String resultValue);    
    
    /**
     * Get AnswerObjective by answerstudentexamId
     * @param answerStudentExamId
     * @return
     */
     public ObjectiveAnswerStudentExam getObjectiveAnswerStudentExamByAnswerStudentExam( Long answerStudentExamId);
     /**
      * Get AnswerSubjective
      * @param anwerStudentExam
      * @param questionId
      * @return
      */
     public AnswerSubjectiveQuestionStudentExam getAnswerSubjectiveQuestionStudentExam(Long anwerStudentExam, Long questionId);
     /**
      * get answerauditve
      * @param anwerStudentExam
      * @param questionId
      * @return
      */
     public AnswerAuditiveQuestionStudentExam getAnswerAuditiveQuestionStudentExam(Long anwerStudentExam, Long questionId);
     /**
      * get answerExternal
      * @param anwerStudentExam
      * @param questionId
      * @return
      */
      public ExternalAnswerStudentExam getAnswerExternalQuestionStudentExam(Long anwerStudentExam, Long questionId);

      public List<AnswerSubjectiveQuestionStudentExam> getListAnswerSubjectiveQuestionStudentExam(Long anwerStudentExercise);
      
      public List<AnswerStudentExam> getListAnswerStudentExamTypeSubjective(Long idStudentExercise);
      
      public boolean updateAnswerSubjectiveQuestionStudentExam(AnswerSubjectiveQuestionStudentExam answerSubjectiveQuestionStudentExercise);
    
       public List averageMark(Long studentId, Long examId,Long gradeId, boolean isSubjectiveCorrection);
       
       public AnswerSubjectiveQuestionStudentExam getAnswerSubjectiveQuestionStudentExamById(Long id);
       
       public boolean updateAllStudentExam(StudentExam studentExam);
       
       public List<StudentExam> getStudentExam(Long userId, Long examId, Long gradeId) ;
       
       public Double averageExamByGrade(Long gradeId,Long studentId );
       
       public Long addStudentExam(Long examId, Long userId,Long courseId) ;
}
