/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.Question;
import br.ufc.ivela.commons.model.QuestionExercise;
import br.ufc.ivela.ejb.*;
import br.ufc.ivela.ejb.interfaces.QuestionExerciseRemote;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Maristella Myrian
 * 
 * Class of ejb which implements the interface QuestionExerciseLocal
 */
@Stateless(mappedName="QuestionExerciseBean")
public class QuestionExerciseBean implements QuestionExerciseRemote {

    private GenericDao<QuestionExercise> daoQuestionExercise = DaoFactory.getInstance(QuestionExercise.class);
    private GenericDao<Question> daoQuestion = DaoFactory.getInstance(QuestionExercise.class);
    
    /**
     * Connect to the server and get the QuestionExercise by id
     * @param id
     * @return the questionExercise by the id
     */
    public QuestionExercise get(Long id) {
        if (id == null) {
            return null;
        }
        return daoQuestionExercise.get(id);
    }

    /**
     * Connect to the server and get a List of questionExercise by the id of exercise
     * @param exercise
     * @return a list of questionExercises
     */
    public List<QuestionExercise> getByExercise(Long exercise) {
        return (List<QuestionExercise>)daoQuestionExercise.getByFK("exercise.id", exercise);
    }
    
    
     public QuestionExercise getByQuestionByExercise(Long questionId, Long exerciseId){
        return (QuestionExercise)daoQuestionExercise.find("from QuestionExercise qe where qe.question.id = ? and qe.exercise.id = ?", new Object[]{questionId,exerciseId}).get(0);
    }
     
     

    /**
     * Connect to the server and add a questionexercise
     * @param questionExercise
     * @return a id of the QuestionExercise
     */
    public Long add(QuestionExercise questionExercise) {
        return  (Long) daoQuestionExercise.save(questionExercise);
    }

    /**
     * Connect to the server and try to remove a questionExercise by the id
     * @param id
     * @return true if it can remove the questionExercise and false if can't
     */
    public boolean remove(Long id) {
        return daoQuestionExercise.remove(id);
    }
    
    public boolean update(QuestionExercise questionExercise){
        return daoQuestionExercise.update(questionExercise);
    }
    
    /**
     * SCORE CARD
     * @param exerciseId
     * @return
     */
    public Double questionAverageMark(Long exerciseId){
        Double avrg = null;
        String sql = "from QuestionExercise qe where qe.exercise.id=?";
        Object params[] = new Object[]{exerciseId};
        List<QuestionExercise> questionExerciseList = daoQuestionExercise.getByFK(sql, params);
        if(questionExerciseList!=null){
            for(QuestionExercise qe: questionExerciseList){
                System.out.println("--"+qe.getQuestion());
            }
        }
        
        return avrg;
    }
}