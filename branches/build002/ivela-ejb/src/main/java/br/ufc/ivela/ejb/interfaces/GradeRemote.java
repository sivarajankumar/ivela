package br.ufc.ivela.ejb.interfaces;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.commons.model.SystemUser;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Interface of ejb representing the methods of contracting business grade
 */
@Remote
public interface GradeRemote {
    
    /**
     * Method which represents the recovery of a grade by identifier
     * 
     * @param id
     * @return
     */
    public Grade get(Long id);
    
    /**
     * Method that is the insertion of a grade
     * 
     * @param forum
     * @return
     */
    public Long add(Grade grade);
    
    /**
     * Method which represents the removal of a grade
     * 
     * @param id
     * @return
     */
    public boolean remove(Long id);
    
    /**
     * Method which represents the recovery of all grades
     * 
     * @return
     */
    public List<Grade> getAll();
    
    /**
     * Method which get the grades by coordinator
     * @param idUser
     * @param validation
     * @return
     */
    public List<Grade> getByCoordinator(Long idUser, boolean validation);

      /**
     * Method which get the grades by coordinator
     * @param idUser
     * @return
     */
    public List<Grade> getByCoordinator(Long idUser);
    /**
     * Method which represents the update of a grade
     * @param grade
     * @return
     */
    public boolean update(Grade grade);
    
    /**
     * Method that get the list of grade by professor
     * @param userId
     * @return
     */
    public List getGradeByProfessors(Long gradeId);
    /**
     * retrieves a list of grades by status
     * @param status
     * @return
     */
    public List<Grade> getByStatus(int status);

    /**
     * retrieves the grades of a student user
     * @param user
     * @return List<Grade>
     */
    List<Grade> getByStudent(SystemUser user);
    List<Grade> getGradesByStudent(SystemUser user);
    List<Grade> getByCourse(Long courseId);
    
    public List<Course> getStructure();
    
    public boolean remove(Grade grade);
    
    public List<SystemUser> getProfessors(Long gradeId);
    public List<SystemUser> getTutors(Long gradeId);
    public List<Grade> getGradesByStudent(Long userId) ;
    public List<Grade> getGradesActiveByStudent(Long userId);
    public Grade getActiveByStudentByCourse(Long userId,Long courseId);
    
    public List<Grade> getGradesInProgressAndEnrolled(Long student, Long courseId);
    public List<Grade> getGradesByCourseAndStatus(Long course, int status);
}
