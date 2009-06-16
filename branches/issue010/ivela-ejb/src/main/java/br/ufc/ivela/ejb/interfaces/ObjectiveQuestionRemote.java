/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;
import br.ufc.ivela.commons.model.ObjectiveQuestion;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author emanuelle
 * Interface of ejb representing the methods of contracting business Objective
 * Question
 */
@Remote
public interface ObjectiveQuestionRemote {
    
     /**
     * Method that is the insertion of a objective question
     * 
     * @param objectiveQuestion
     * @return
     */
    public Long add(ObjectiveQuestion objectiveQuestion);
    
    /**
     * Method which represents the recovery of a objective question by identifier
     * 
     * @param id
     * @return
     */
    public ObjectiveQuestion get(Long id);
    
    /**
     * Method that is used to edit a objective question
     * 
     * @param objectiveQuestion
     * @return
     */
    public boolean edit(ObjectiveQuestion objectiveQuestion);
    
        
    /**
     * Method which represents the recovery of a objective
     * question by identifier of question
     * 
     * @param question
     * @return
     */    
    public ObjectiveQuestion getByQuestion (Long question);
    
}
