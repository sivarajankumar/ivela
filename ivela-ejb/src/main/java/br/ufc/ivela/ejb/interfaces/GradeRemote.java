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
     * @return List instance with the grade objects, the list will be empty if no result if found
     */
    public List<Grade> getAll();
    
    /**
     * Method which get the grades by coordinator
     * @param idUser
     * @param validation
     * @return List instance with the grade objects, the list will be empty if no result if found
     */
    public List<Grade> getByCoordinator(Long idUser, boolean validation);

      /**
     * Method which get the grades by coordinator
     * @param idUser
     * @return List instance with the grade objects, the list will be empty if no result if found
     * 
     * @see br.ufbr.ufc.ivela.commons.model.SystemUser
     */
    public List<Grade> getByCoordinator(Long idUser);
    /**
     * Method which represents the update of a grade
     * @param grade
     * @return
     * 
     * @see br.ufbr.ufc.ivela.commons.model.Grade
     */
    public boolean update(Grade grade);
    
    /**
     * Method that get the list of grades by professor
     * @param userId the System User Id of the Professor
     * @return List instance with the grade objects, the list will be empty if no result if found
     * 
     * @see br.ufbr.ufc.ivela.commons.model.SystemUser
     */
    public List getGradeByProfessors(Long userId);
    
    /**
     * Method that get the list of grades by Tutors
     * 
     * @param systemUserId the System User Id of the Tutor
     * @return List instance with the grade objects, the list will be empty if no result if found
     * 
     * @see br.ufbr.ufc.ivela.commons.model.SystemUser
     */
    public List<Grade> getGradeByTutors(Long systemUserId);
    
    /**
     * retrieves a list of grades by status
     * @param status
     * @return List instance with the grade objects, the list will be empty if no result if found
     * 
     * @see br.ufbr.ufc.ivela.commons.model.Grade
     */
    public List<Grade> getByStatus(int status);

    /**
     * retrieves the grades of a student user
     * @param user
     * @return List<Grade>
     * 
     * @see br.ufbr.ufc.ivela.commons.model.SystemUser
     */
    List<Grade> getByStudent(SystemUser user);
    List<Grade> getGradesByStudent(SystemUser user);
    List<Grade> getByCourse(Long courseId);
    
    public List<Course> getStructure();
    
    public List<Course> getStructure(SystemUser systemUser);
    
    public boolean remove(Grade grade);
    
    public List<SystemUser> getProfessors(Long gradeId);
    public List<SystemUser> getTutors(Long gradeId);
    
    /**
     * Retrieves the List of grades by Student
     * 
     * @param userId the Id of the Student
     * @return List instance with the grade objects, the list will be empty if no result if found
     * 
     * @see br.ufbr.ufc.ivela.commons.model.SystemUser
     */
    public List<Grade> getGradesByStudent(Long userId) ;
    
    /**
     * Retrieves the List of Active Grades by Student
     *  
     * @param userId the Id of the student
     * @return List instance with the grade objects, the list will be empty if no result if found
     * 
     * @see br.ufbr.ufc.ivela.commons.model.SystemUser
     */
    public List<Grade> getGradesActiveByStudent(Long userId);
    
    /**
     * Retrieves the List of Active Grades by Student and a specific course
     * 
     * @param userId the Id of the student
     * @param courseId the id of the course.
     * @return List instance with the grade objects, the list will be empty if no result if found
     * 
     * @see br.ufbr.ufc.ivela.commons.model.SystemUser
     * @see br.ufbr.ufc.ivela.commons.model.Course
     */
    public Grade getActiveByStudentByCourse(Long userId,Long courseId);
    
    /**
     * Retrieves the list of Grades in Progress by Student and Course.
     * 
     * @param student the id of the student
     * @param courseId the id of the course.
     * @return List instance with the grade objects, the list will be empty if no result if found
     * 
     * @see br.ufbr.ufc.ivela.commons.model.SystemUser
     * @see br.ufbr.ufc.ivela.commons.model.Course
     */
    public List<Grade> getGradesInProgressAndEnrolled(Long student, Long courseId);
    
    /**
     * Retrieves the list of Grades by Course and Status
     * @param course the id of the course.
     * @param status the status of the Grade
     * @return List instance with the grade objects, the list will be empty if no result if found
     * 
     * @see br.ufbr.ufc.ivela.commons.model.Course
     * @see br.ufbr.ufc.ivela.commons.model.Grade
     */
    public List<Grade> getGradesByCourseAndStatus(Long course, int status);
        
    
}

