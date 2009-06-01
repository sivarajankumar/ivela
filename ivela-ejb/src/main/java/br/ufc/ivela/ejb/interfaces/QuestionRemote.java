/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.Question;
import br.ufc.ivela.commons.model.QuestionText;
import java.io.File;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author emanuelle
 * 
 * Interface of ejb representing the methods of contracting business question
 */
@Remote
public interface QuestionRemote {

    /**
     * Method that is the insertion of a question
     * 
     * @param question
     * @return
     */
    public Long add(Question question);
    
    /**
     * Add questionText
     * @param questionText
     * @return
     */
    public Long addQuestionText(QuestionText questionText, File file, String dir);

    /**
     * Method which represents the recovery of a question by identifier
     * 
     * @param id
     * @return
     */
    public Question get(Long id);

    /**
     * Method which represents the removal of a question
     * 
     * @param id
     * @return
     */
    public boolean remove(Long id);

    /**
     * Method that is used to edit a question
     * 
     * @param question
     * @return
     */
    public boolean edit(Question question);

    /**
     * Method to retrieve a list of question of a specific type
     * 
     * @param type
     * @return
     */
    public List<Question> getByType(int type);

    /**
     * Method which represents the recovery of all questions
     * 
     * @return
     */
    public List<Question> getAll();
    
    public QuestionText getQuestionTextByQuestion(Long questionId);
    
    public QuestionText getQuestionText(Long questionTextId);
    
    public boolean update(Question question);
}
