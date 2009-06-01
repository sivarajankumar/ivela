/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;
import br.ufc.ivela.commons.model.ObjectiveAnswer;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author emanuelle
 * Interface of ejb representing the methods of contracting business Objective
 * Answer
 */
@Remote
public interface ObjectiveAnswerRemote {
    
     /**
     * Method that is the insertion of an objective answer
     * 
     * @param objectiveAnswer
     * @return
     */
    public Long add(ObjectiveAnswer objectiveAnswer);
    
     /**
     * Method which represents the recovery of an objective answer by identifier
     * 
     * @param id
     * @return
     */
    public ObjectiveAnswer get(Long id);
    
     /**
     * Method which represents the removal of an objective answer
     * 
     * @param id
     * @return
     */
    public boolean remove(Long id);
     
    /**
     * Method that is used to edit a objective answer
     * 
     * @param objective answer
     * @return
     */
    public boolean edit(ObjectiveAnswer objectiveAnswer);
    
    /**
     * Method to retrieve a list of objective answer of a specific question
     * 
     * @param type
     * @return
     */
    public List<ObjectiveAnswer> getByQuestion(Long question);
    
}
