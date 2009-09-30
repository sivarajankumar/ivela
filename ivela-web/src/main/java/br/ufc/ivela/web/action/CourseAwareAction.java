/*###########################################################################################
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
# File: CourseAwareAction.java                                                              #
# Document: Course Aware Action                                                             #
# Date        - Author(Company)                    - Issue# - Summary                       #
# 16-SEP-2009 - otofuji (Instituto Eldorado)       - 000016 - Initial Version               #
#############################################################################################    
 */
package br.ufc.ivela.web.action;

import java.util.List;
import java.util.Set;

import org.springframework.security.GrantedAuthority;

import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.commons.model.SystemUser.AUTHORITY;
import br.ufc.ivela.ejb.interfaces.CourseRemote;
import br.ufc.ivela.ejb.interfaces.EnrollmentRemote;
import br.ufc.ivela.ejb.interfaces.GradeRemote;
import br.ufc.ivela.util.Mailer;

/**
 * Course Aware Action implements default methods used by Actions that 
 * rely on Course or Grade content.  
 *
 */
public class CourseAwareAction extends GenericAction {

    private static final long serialVersionUID = -3441113145584978610L;
    
    protected CourseRemote courseRemote;
    protected GradeRemote gradeRemote;
    protected EnrollmentRemote enrollmentRemote;
    protected List<Grade> gradeList;    
    protected List<Course> courseList;    
    protected Grade grade;    
    protected Course course;    
    protected Mailer mailer;    
    private List<Course> userCourses;
    private List<Grade> userGrades;
    
    /**
     * Retrieves a List of grade
     * @return gradeList
     */
    public List<Grade> getGradeList() {
        return gradeList;
    }

    /**
     * Sets a List of grade
     * @param gradeList
     */
    public void setGradeList(List<Grade> gradeList) {
        this.gradeList = gradeList;
    }

    public GradeRemote getGradeRemote() {
        return gradeRemote;
    }

    /**
     * Sets a remote grade
     * @param gradeRemote
     */
    public void setGradeRemote(GradeRemote gradeRemote) {
        this.gradeRemote = gradeRemote;
    }
    
    /**
     * Retrieves a list of course
     * @return courseList
     */
    public List<Course> getCourseList() {
        return courseList;
    }

    /**
     * Sets a list of course
     * @param courseList
     */
    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    /**
     * Retrieves a remote course
     * @return courseRemote
     */
    public CourseRemote getCourseRemote() {
        return courseRemote;
    }

    /**
     * Sets a remote course
     * @param courseRemote
     */
    public void setCourseRemote(CourseRemote courseRemote) {
        this.courseRemote = courseRemote;
    }
    

    public EnrollmentRemote getEnrollmentRemote() {
        return enrollmentRemote;
    }

    public void setEnrollmentRemote(EnrollmentRemote enrollmentRemote) {
        this.enrollmentRemote = enrollmentRemote;
    }

    /**
     * Retrieves a grade
     * @return grade
     */
    public Grade getGrade() {
        return grade;
    }

    /**
     * Sets a grade
     * @param grade
     */
    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    /**
     * Sets a course
     * @param course
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * Retrieves a course
     * @return course
     */
    public Course getCourse() {
        return course;
    }
    
    /**
     * Retrieves the Mailer used by this Action
     * @return mailer
     */
    public Mailer getMailer() {
        return mailer;
    }
    
    /**
     * Sets the Mailer used by this Action
     * @param mailer
     */
    public void setMailer(Mailer mailer) {
        this.mailer = mailer;
    }
    
    /**
     * If the user requesting the action has admin privileges for the action in
     * place.
     * 
     * @return a String with the Authority for the Authenticated user
     */
    
    public boolean hasAuthorization(String authority) {                        
        if ((!AUTHORITY_USER.equals(authority))
                && (!AUTHORITY_EAD_USER.equals(authority))
                && (!AUTHORITY_NONE.equals(authority))) {
            return true;
        }        
        
        return false;
    }
    
    public String getAuthority(Object object) {
        String authority_ = AUTHORITY.ROLE_NONE.getAuthority();
        SystemUser user = getAuthenticatedUser();
        if (object instanceof Course) {
            Course objCourse = (Course) object;
            GrantedAuthority[] authorities = user.getAuthorities();              
            String authorityTemp = authorities != null && authorities.length > 0? authorities[0].getAuthority() : null;
            if (userHasCourse(objCourse, null, authorityTemp)) {
                authority_ = authorityTemp;
            }
        } else if (object instanceof Grade) {
            Grade objGrade = (Grade) object;
            GrantedAuthority[] authorities = user.getAuthorities();              
            String authorityTemp = authorities != null && authorities.length > 0? authorities[0].getAuthority() : null;
            if (userHasGrade(objGrade, null, authorityTemp)) {
                authority_ = authorityTemp;
            }
        } else {            
            GrantedAuthority[] authorities = user.getAuthorities();            
            authority_ = authorities != null && authorities.length > 0? authorities[0].getAuthority() : authority_;            
        }
            
        return authority_;
    }
    
    @Override
    public String getAuthority() {        
        if (authority != null && !authority.isEmpty()) {
            return authority;
        }
        
        SystemUser user = getAuthenticatedUser();
        
        if (user == null) {
            return "";
        }
        
        // No Authorization by default;
        this.authority = AUTHORITY.ROLE_NONE.getAuthority();
        
        for (GrantedAuthority authority : user.getAuthorities()) {
            //
            // User Authorization
            //
            if (AUTHORITY.ROLE_USER.hasAuthority(authority)) {                
                if (grade != null && grade.getId() != null) {                      
                    if (enrollmentRemote.getByUserGrade(user.getId(), grade.getId())) 
                        this.authority = AUTHORITY.ROLE_USER.getAuthority();                            
                }                
                break;
            //
            // Admin Authorization
            //
            } else if (AUTHORITY.ROLE_ADMIN.hasAuthority(authority)) {
                this.authority = AUTHORITY.ROLE_ADMIN.getAuthority();
                break;
            //
            // Professor Authorization
            //
            } else if (AUTHORITY.ROLE_PROFESSOR.hasAuthority(authority)) {                
                if (grade != null && grade.getId() != null) {                                                            
                    if (userHasGrade(grade, null, AUTHORITY.ROLE_PROFESSOR.getAuthority()))
                        this.authority = AUTHORITY.ROLE_PROFESSOR.getAuthority();    
                } else if (course != null && course.getId() != null) {
                    // No Grade, It is course access.                    
                    if (userHasCourse(course, null, AUTHORITY.ROLE_PROFESSOR.getAuthority()))
                        this.authority = AUTHORITY.ROLE_PROFESSOR.getAuthority();                        
                } 
                break;
            //
            // Tutor Authorization
            //
            } else if (AUTHORITY.ROLE_TUTOR.hasAuthority(authority)) {                
                if (grade != null && grade.getId() != null) {
                    if (userHasGrade(grade, null, AUTHORITY.ROLE_TUTOR.getAuthority()))                        
                        this.authority = AUTHORITY.ROLE_TUTOR.getAuthority();    
                }                
                break;
            //
            // Coordinator Authorization
            //                
            } else if (AUTHORITY.ROLE_COORD.hasAuthority(authority)) {
                this.authority = AUTHORITY.ROLE_COORD.getAuthority();
                if (grade != null && grade.getId() != null) {
                    if (user.getId().equals(grade.getCoordinatorId()))                                                
                        this.authority = AUTHORITY.ROLE_COORD.getAuthority();                    
                } else if (course != null && course.getId() != null) {
                    // No Grade, It is course access.                                        
                    if (userHasCourse(course, null, AUTHORITY.ROLE_COORD.getAuthority()))
                        this.authority = AUTHORITY.ROLE_COORD.getAuthority();
                }                  
                break;
            }                                         
        }
        
        return authority;  
    }
    
    /**
     * Checks if the User has Access to a specific Course
     * 
     * @param course The Course
     * @param systemUser System User that should have the Course, if null the method will get the authenticated user.
     * @param authorization GrantedAuthority that should be used, if null it will use the first GrantedAuthority for the user
     * 
     * @return <code> true </code> if the User has access to the course, <code> false </code> otherwise.
     */
    public boolean userHasCourse(Course course, SystemUser systemUser, String authorization) {
        if (userCourses == null) {
            SystemUser user = systemUser != null && systemUser.getId() != null? systemUser : getAuthenticatedUser();
            String authority_;
            if (authorization != null && !authorization.isEmpty()) {
                authority_ = authorization;
            } else {
                GrantedAuthority[] authorities = user.getAuthorities();
                authority_ = authorities != null && authorities.length > 0? authorities[0].getAuthority() : null;   
            }
                                                
            if (AUTHORITY.ROLE_USER.hasAuthority(authority_)) {                
                return false;
            } else if (AUTHORITY.ROLE_ADMIN.hasAuthority(authority_)) {
                return true;
            } else if (AUTHORITY.ROLE_PROFESSOR.hasAuthority(authority_)) {                
                userCourses = courseRemote.getCoursesByProfessor(user.getId());
            } else if (AUTHORITY.ROLE_TUTOR.hasAuthority(authority_)) {                
                userCourses = courseRemote.getCoursesByTutor(user.getId());
            } else if (AUTHORITY.ROLE_COORD.hasAuthority(authority_)) {
                userCourses = courseRemote.getCoursesByCoordinator(user.getId());
            } 
            
        }
        
        return userCourses.contains(course);
    }
    
    /**
     * Checks if the User has Access to a specific Grade
     * 
     * @param grade The Grade
     * @param systemUser System User that should have the Course, if null the method will get the authenticated user.
     * @param authorization GrantedAuthority that should be used, if null it will use the first GrantedAuthority for the user
     * 
     * @return <code> true </code> if the User has access to the course, <code> false </code> otherwise.
     */
    public boolean userHasGrade(Grade grade, SystemUser systemUser, String authorization) {
        if (userGrades == null) {
            SystemUser user = systemUser != null && systemUser.getId() != null? systemUser : getAuthenticatedUser();
            String authority_;
            if (authorization != null && !authorization.isEmpty()) {
                authority_ = authorization;
            } else {
                GrantedAuthority[] authorities = user.getAuthorities();
                authority_ = authorities != null && authorities.length > 0? authorities[0].getAuthority() : null;   
            }
            
            if (AUTHORITY.ROLE_USER.hasAuthority(authority_)) {                
                userGrades = gradeRemote.getGradesByStudent(user.getId());
            } else if (AUTHORITY.ROLE_ADMIN.hasAuthority(authority_)) {
                return true;
            } else if (AUTHORITY.ROLE_PROFESSOR.hasAuthority(authority_)) {                
                userGrades = gradeRemote.getGradeByProfessors(user.getId());
            } else if (AUTHORITY.ROLE_TUTOR.hasAuthority(authority_)) {                
                userGrades = gradeRemote.getGradeByTutors(user.getId());
            } else if (AUTHORITY.ROLE_COORD.hasAuthority(authority_)) {
                userGrades = gradeRemote.getByCoordinator(user.getId());
            } 
            
        }
        
        return userGrades.contains(grade);
    }
}
