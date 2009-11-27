/*  
#############################################################################################
# Copyright(c) 2009 by IBM Brasil Ltda and others                                           #
# This file is part of ivela project, an open-source                                        #
# Program URL   : http://code.google.com/p/ivela/                                           #  
#                                                                                           #
# This program is free software; you can redistribute it and/or modify it under the terms   #
# of the GNU General Public License as published by the Free Software Foundation; either    #
# version 3 of the License, or (at your option) any later version.                          #
#                                                                                           #
# This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; #
# without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. #
# See the GNU General Public License for more details.                                      #  
#                                                                                           #
#############################################################################################
# File: CourseRemote.java                                                                   #
# Document: Course Remote                                                                   # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 06-OCT-2009 - Fabio Fantato (Instituto Eldorado)- 000017 - Table of Contents              # 
#############################################################################################
*/
package br.ufc.ivela.ejb.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import br.ufc.ivela.commons.dao.Page;
import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.Discipline;
import br.ufc.ivela.commons.model.SystemUser;

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
    
    public List<Course> getStructure(SystemUser systemUser);
    
    public String getCourseJsonStructure(Long courseId);
    
    public Course getCourseStructure(Long courseId);
    
    public String getJsonStructure();
    
    public Integer getStudentsCount(Long courseId);
    
    public List<SystemUser> getCoordinators(Long courseId);
            
    public List<SystemUser> getProfessors(Long courseId);
    
    public List<SystemUser> getTutors(Long courseId);
    
    public List<Discipline> getDisciplines(Long courseId);
    
    public Integer getGradesCount(Long courseId);
    
    public Integer getGraduatedStudentsCount(Long courseId);
    
    Integer getProgress(Long systemUserId, Long courseId);

    public String getTimeLeft(Long systemUserId, Long courseId);

    public String getTimeLeftByDiscipline(Long systemUserId, Long courseId, Long disciplineId);

    public void savePhoto(Course p, java.io.File file);
    
    public int isFinishedCourse(Long studentId, Long courseId, long gradeId);
    
    public List<Course> getCourses(String name, String description);
    
    /**
     * Method that get the list of courses by Coordinator
     *  
     * @param userId the System User Id of the Professor
     * @return List instance with the course objects, the list will be empty if no result if found
     * 
     * @see br.ufbr.ufc.ivela.commons.model.SystemUser
     */
    public List<Course> getCoursesByCoordinator(Long userId);
    
    /**
     * Method that get the list of courses by Professor
     * 
     * @param userId the System User Id of the Professor
     * @return List instance with the course objects, the list will be empty if no result if found
     * 
     * @see br.ufbr.ufc.ivela.commons.model.SystemUser
     */
    public List<Course> getCoursesByProfessor(Long userId);
    
    /**
     * Method that get the list of courses by Tutor
     * 
     * @param userId the System User Id of the Professor
     * @return List instance with the course objects, the list will be empty if no result if found
     * 
     * @see br.ufbr.ufc.ivela.commons.model.SystemUser
     */
    public List<Course> getCoursesByTutor(Long userId);
}

