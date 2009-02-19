/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.dao.Page;
import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.Discipline;
import br.ufc.ivela.commons.model.SystemUser;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author maristella
 */
@Remote
public interface CourseRemote {

    /**
     * Method that is the insertion of a course
     * 
     * @param question
     * @return
     */
    public Long add(Course course);

    /**
     * Method which represents the recovery of a course by identifier
     * 
     * @param id
     * @return a couse
     */
    public Course get(Long id);

    /**
     * Method which represents the removal of a course
     * 
     * @param id
     * @return true if can be remove a course by id or false if can't
     */
    public boolean remove(Long id);
    
    public boolean remove(Course course);

    /**
     * Method which represents the recovery of all course
     * 
     * @return a list of course
     */
    public List<Course> getAll();
    
    /**
     * Method to update a course
     * @param course
     * @return true if can update a course or false if can't
     */
    public boolean update(Course course);
    
    public List<Course> getRequisiteByCourse(Long courseId);
    
    public List<Course> getCourseByUserApproved(Long userId);
    
    public Page getAllPage(int page, int pageSize);
    
    public List<Course> getStructure();
    
    public String getCourseJsonStructure(Long courseId);
    
    public Course getCourseStructure(Long courseId);
    
    public String getJsonStructure();
    
    public Integer getStudentsCount(Long courseId);
    
    public List<SystemUser> getProfessors(Long courseId);
    
    public List<SystemUser> getTutors(Long courseId);
    
    public List<Discipline> getDisciplines(Long courseId);
    
    public Integer getGradesCount(Long courseId);
    
    public Integer getGraduatedStudentsCount(Long courseId);
    
    Integer getProgress(Long systemUserId, Long courseId);
    
    public void savePhoto(Course p, java.io.File file);
    
    public int isFinishedCourse(Long studentId, Long courseId, long gradeId);
    
    public List<Course> getCourses(String name, String description);
}

