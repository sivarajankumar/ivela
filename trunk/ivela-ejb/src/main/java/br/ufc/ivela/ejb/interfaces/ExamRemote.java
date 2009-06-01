/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.dao.Page;
import br.ufc.ivela.commons.model.Exam;
import br.ufc.ivela.commons.model.Question;
import br.ufc.ivela.commons.model.QuestionExam;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author maristella
 */
@Remote
public interface ExamRemote {

    /**
     *  Method which represents the recovery of a exam by identifier
     * @param id
     * @return an exam
     */
    public Exam get(Long id);

    /**
     * Method that is the insertion of a exam
     * @param exam
     * @return
     */
    public Long add(Exam exam);

    /**
     *  Method which represents the removal of a exam
     * @param id
     * @return true if it can remove an exam by id or false if can't
     */
    public boolean remove(Long id);

    /**
     *  Method to retrieve a list of exam
     * @return a list of exam
     */
    public List<Exam> getAll();

    /**
     * 
     * @param idUnitContent
     * @return
     */
    public List<Exam> getListExamByUnitContent(Long idUnitContent);

    /**
     *  Method to retrieve a list of exam
     * @return a list of exam
     */
    public List<Exam> listByUnit(Long unitId);

    /**
     * Method to update an exam
     * @param exam
     * @return true if it can update an exam or false if can't
     */
    
    
    
    public boolean update(Exam exam);

    /**
     * get question by page for exam
     * @param examId
     * @param page
     * @param pageSize
     * @return
     */
    public Page getQuestionPageByExam(Long examId, int page, int pageSize);

    /**
     * get questions by exam
     * @param examId
     * @return
     */
    public List<Question> getQuestions(Long examId);

    public int getMaxOrderNByUnitContent(Long unitContentId);

    public Long addQuestion(QuestionExam questionExam);

    public void addRequisite(Long examId, Long requisiteId);

    public boolean isExamFinishedForStudent(Long idExam, Long idStudent,Long courseId);
    public boolean isExamFinishedForStudent(Long idExam, Long idStudent);
    
    public int getQuestionNumber(Long idExam);
    
    public boolean updateJson(Exam exam);
    
    public List<Exam> getListAllExamByUnitContent(Long idUnitContent);
    
    //0 = not finished, 1 = finished, 2 = incorrect parameters.
    public int finishedExamsForUnitContent(Long studentId, Long unitContentId, Long gradeId);
}
