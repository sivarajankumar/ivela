/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.QuestionExam;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Maristella Myrian / Emanuelle Vieira
 * s
 * Interface of ejb representing the methods of contracting business Question Exam
 */
@Remote
public interface QuestionExamRemote {
    
    
    /**
     * Method which represents the recovery of a questionExam by identifier
     * @param id
     * @return a QuestionExam
     */
    public QuestionExam get(Long id);
    
    /**
     * Method to retrieve a list of QuestionExam related to a exam
     * 
     * @param exam
     * @return a list of questionExams
     */
    public List<QuestionExam> getByExam(Long exam);
    
    /**
     * Method that is the insertion of a questionExam
     * 
     * @param questionExam
     * @return a id of the QuestionExam
     */
    public Long add(QuestionExam questionExam);

    /**
     * Method which represents the removal of a QuestionExam
     * 
     * @param id
     * @return true if it can remove the questionExam and false if can't
     */
    public boolean remove(Long id);
    public QuestionExam getByQuestionByExam(Long questionId, Long examId);   
    public boolean update(QuestionExam questionExam);
    
    
}
