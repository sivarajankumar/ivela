/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.dao.Page;
import br.ufc.ivela.commons.model.Exercise;
import br.ufc.ivela.commons.model.Question;
import br.ufc.ivela.commons.model.QuestionExercise;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author maristella
 */

@Remote
public interface ExerciseRemote {
    /**
     *  Method which represents the recovery of a exercise by identifier
     * @param id
     * @return an exercise
     */
    public Exercise get(Long id);
    
    /**
     * Method that is the insertion of a exercise
     * @param exercise
     * @return
     */
    public Long add(Exercise exercise);

    /**
     *  Method which represents the removal of a exercise
     * @param id
     * @return true if can remove bye id or false if can't
     */
    public boolean remove(Long id);

    /**
     *  Method to retrieve a list of exercise
     * @return a list of exercise
     */
    public List<Exercise> getAll();
    
    /**
     * 
     * @param idUnitContent
     * @return
     */
    public List<Exercise> getListExerciseByUnitContent(Long idUnitContent); 
    
    /**
     * 
     * @param exerciseId
     * @param page
     * @param pageSize
     * @return
     */
    
    public Page getQuestionPageByExercise(Long exerciseId, int page, int pageSize);
    
    /**
     * Method to update an exercise
     * @param exercise
     * @return true if can update a exercise or false if can't
     */
    public boolean update(Exercise exercise);
    /**
     * Get question by exercise
     * @param exerciseId
     * @return
     */
    
    //public List<Exercise> getExercisesByUnitContent(Long idUnitContent);
    public List<Question> getQuestions(Long exerciseId);
    public void addRequisite(Long exerciseId, Long requisiteId);
    public int getMaxOrderNByUnitContent(Long unitContentId);
    public boolean isExerciseFinishedForStudent(Long idExercise, Long idStudent);
    public Long addQuestion(QuestionExercise questionExercise);
    public void updateStudentExerciseChances(Long studentId, Long exerciseId);
    public int getChancesStudentExercise(Long studentId, Long exerciseId,Long courseId);
    public void setGradeForStudentExerciseId(Long studentExerciseId, double grade);
    public int finishedExerciseForUnitContent(Long studentId, Long unitContentId, Long gradeId);
}


