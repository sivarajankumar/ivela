/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;


import br.ufc.ivela.commons.model.QuestionExercise;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Maristella Myrian
 * 
 * Interface of ejb representing the methods of contracting business Question Exercise
 */
@Remote
public interface QuestionExerciseRemote {
    
    
    /**
     * Method which represents the recovery of a Question by identifier
     * 
     * @param id
     * @return a questionExercise
     */
    public QuestionExercise get(Long id);
    
    /**
     * Method to retrieve a list of exercise related to a question
     * 
     * @param exercise
     * @return a list of questionExercise
     */
    public List<QuestionExercise> getByExercise(Long exercise);
    
    /**
     * Method that is the insertion of a questionExercise
     * 
     * @param forum
     * @return the Id of the questionExercise
     */
    public Long add(QuestionExercise questionExercise);

    /**
     * Method which represents the removal of a questionExam
     * 
     * @param id
     * @return true if it can remove a questionExercise and false if can't
     */
    public boolean remove(Long id);
    
    public QuestionExercise getByQuestionByExercise(Long questionId, Long exerciseId);
    public boolean update(QuestionExercise questionExercise);
    
    public Double questionAverageMark(Long exerciseId);
    
}
