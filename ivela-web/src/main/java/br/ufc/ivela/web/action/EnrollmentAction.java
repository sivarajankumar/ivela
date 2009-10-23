/*
###############################################################################################
# Copyright(c) 2008-2009 by IBM Brasil Ltda and others                                        #
# This file is part of ivela project, an open-source                                          #
# Program URL   : http://code.google.com/p/ivela/                                             #  
#                                                                                             #
# This program is free software; you can redistribute it and/or modify it under the terms     #
# of the GNU General Public License as published by the Free Software Foundation; either      #
# version 3 of the License, or (at your option) any later version.                            #
#                                                                                             #
# This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;   #
# without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.   #
# See the GNU General Public License for more details.                                        #  
#                                                                                             #
###############################################################################################
# File: list.jsp                                                                              #
# Document: list BulletinBoard                                                                # 
# Date        - Author(Company)                   - Issue# - Summary                          #
# XX-XXX-XXXX - Emanuelle                         - XXXXXX - Initial Version                  #
# 28-AGO-2009 - Mileine      (Instituto Eldorado) - 000016 - Same course enrollment msg added #
###############################################################################################
*/

package br.ufc.ivela.web.action;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.Enrollment;
import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.commons.model.Transcript;
import br.ufc.ivela.ejb.interfaces.CourseRemote;
import br.ufc.ivela.ejb.interfaces.EnrollmentRemote;
import br.ufc.ivela.ejb.interfaces.GradeRemote;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author emanuelle
 */
public class EnrollmentAction extends GenericAction {

    private EnrollmentRemote enrollmentRemote;
    private GradeRemote gradeRemote;
    private Enrollment enrollment;
    private List<Enrollment> enrollmentList;
    private List<Grade> gradeList;
    private List<Grade> gradeRequiresValidationList;
    private List<Grade> gradeNotRequiresValidationList;
    private Grade grade;
    private CourseRemote courseRemote;
    private List<Course> courseRequisiteList;
    private List<Course> approvedCourseList;
    private Course course;
    private String message;

    /**
     * Add a new Enrollment, in a course, if there is no max students
     */
    public String add() {
        logger.log("\n\n\nENTREI NO ADD\n\n\n");
        Grade g = gradeRemote.get(enrollment.getGrade().getId());
        
        List<Grade> grades = gradeRemote.getGradesByStudent(getAuthenticatedUser().getId());
        boolean canAdd = true;
        for (Grade gr : grades) {
            if (gr.getId().longValue() == g.getId().longValue()) {
                canAdd = false;
                break;
            }
        }
        
        
        if (canAdd) {
            enrollment.setGrade(g);
            enrollment.setSystemUser(getAuthenticatedUser());

            List count = enrollmentRemote.getByGrade(g.getId());

            if (count.size() < g.getMaxStudents()) {
                enrollmentRemote.add(enrollment);
            } else {
                addActionMessage(getText("enrollment.list.add"));
            }
            
        }
        else {
            addActionMessage(getText("enrollment.list.notadd"));
        }

        return list();
    }

    public String enroll() {
        List<Grade> grades = gradeRemote.getGradesByStudent(getAuthenticatedUser().getId());
        boolean canAdd = true;
        Grade g = gradeRemote.get(grade.getId());
        for (Grade gr : grades) {
            if (gr.getId().longValue() == g.getId().longValue()) {
                canAdd = false;
                break;
            }
        }
        
        if (canAdd) {
            enrollment = new Enrollment();
            enrollment.setGrade(g);
            enrollment.setStartDatetime(new Date());
            enrollment.setSystemUser(getAuthenticatedUser());

            List count = enrollmentRemote.getByGrade(g.getId());

            if (count.size() < g.getMaxStudents()) {
                enrollmentRemote.add(enrollment);
                grade = gradeRemote.get(grade.getId());
                List<Transcript> transcripts = historyRemote.getTranscriptsByStudentByGrade(getAuthenticatedUser().getId(), grade.getId());
                if (transcripts.isEmpty()) {
                    historyRemote.addTranscript(grade.getId(), getAuthenticatedUser().getId());    
                } else {
                    Transcript trans = transcripts.get(0);
                    trans.setAverageChallenge(0.0);
                    trans.setChallengesTotal(0.0);
                    trans.setChallengesWeight(0);
                    trans.setChallengesDone(0);
                    historyRemote.updateTranscript(trans);
                }
                addHistory("history.enrolluser.title", "history.enrolluser.description", getAuthenticatedUser().getUsername(), grade.getName());                                               
            } else {
                addActionMessage(getText("enrollment.list.add"));
                return listGrades();
            }
            
        }
        else {
            addActionMessage(getText("enrollment.list.notadd"));
            return listGrades();
        }
        return "index";
    }

    /**
     * Remove a enrollment of one course
     */
    public String remove() {
        logger.log("\n\n\nENTREI NO REMOVE\n\n\n");
        enrollmentRemote.remove(enrollment.getId());
        return list();
    }

    public String listGrades() {
        gradeList = gradeRemote.getGradesByCourseAndStatus(course.getId(), Constants.GRADE_PERIOD_OF_ENROLLMENT);

        for (Grade grade : gradeList) {
            grade.setEnrollments((Collection<Enrollment>) enrollmentRemote.getByGrade(grade.getId()));
        }

        List<Grade> yourGradeByCourse = gradeRemote.getGradesInProgressAndEnrolled(getAuthenticatedUser().getId(), course.getId());
        
        boolean already = false;

        if (!yourGradeByCourse.isEmpty()) {
            already = true;
            gradeList = null;
            message = "enrolled";
        }
            
        else if (gradeList.size() == 0) {
            gradeList = null;
            message = "noGrades";
        }

        return "list";
    }

    /**
     * List all enrollments
     */
    public String list() {

        logger.log("\n\n\nENTREI NO LIST\n\n\n");
        gradeList = new ArrayList<Grade>();

        enrollmentList = enrollmentRemote.getByUserAndStatus(getAuthenticatedUser().getId(), Constants.ENROLLMENT_ACTIVE);

        //List of courses where the user has been approved
        approvedCourseList = courseRemote.getCourseByUserApproved(getAuthenticatedUser().getId());

        //list the courses that have vague
        List<Grade> listGrade = gradeRemote.getByStatus(Constants.GRADE_PERIOD_OF_ENROLLMENT);

        //link in the list of all classes offered
        for (Grade g : listGrade) {
            g.setEnrollments((Collection<Enrollment>) enrollmentRemote.getByGrade(g.getId()));

            if (!approvedCourseList.contains(g.getCourse())) {
                //The variable courseRequisiteList armezena to each iteration the list of courses pre-requisites
                //for the course offered by the current grade
                courseRequisiteList = courseRemote.getRequisiteByCourse(g.getCourse().getId());

                //compares to see if the courses where the user was approved is pre-requisite for any grade offered
                if (approvedCourseList.containsAll(courseRequisiteList) && enrollmentRemote.getByUserGrade(getAuthenticatedUser().getId(), g.getId())) {
                    gradeList.add(g);
                }
            }

        }

        return "list";
    }

    public int complement(int x, int y) {
        return x - y;
    }

    public String show() {
        logger.log("\n\n\nENTREI NO SHOW\n\n\n");
        enrollmentList = enrollmentRemote.getByUser(getAuthenticatedUser().getId());
        return "show";
    }

    /**
     * Cancel enrollment if  period of enrollment is active
     * 
     */
    public String cancel() {
        logger.log("\n\n\nENTREI NO CANCEL\n\n\n");
        Enrollment e = enrollmentRemote.get(enrollment.getId());

        if (e.getGrade().getStatus() == Constants.GRADE_PERIOD_OF_ENROLLMENT) {
            enrollmentRemote.remove(enrollment.getId());            
        } else if (e.getGrade().getStatus() == Constants.GRADE_IN_PROGRESS) {
            e.setStatus(Constants.ENROLLMENT_SUSPENDED);
            enrollmentRemote.update(e);
        }
        return list();
    }

    public String update() {
        logger.log("\n\n\nENTREI NO UPDATE\n\n\n");
        Enrollment e = enrollmentRemote.get(enrollment.getId());
        grade = e.getGrade();
        e.setStatus(enrollment.getStatus());
        enrollmentRemote.update(e);
        return edit();
    }

    public String edit() {
        logger.log("\n\n\nENTREI NO EDIT\n\n\n");
        if (grade != null) {
            enrollmentList = enrollmentRemote.getByGrade(grade.getId());
        }

        gradeList = gradeRemote.getByCoordinator(getAuthenticatedUser().getId(), true);

        return "edit";
    }

    public String statusGrade(int status) {
        String a = "";
        switch (status) {
            case 0:
                a = "Inactive";
                break;
            case 1:
                a = "Period of enrollment";
                break;
            case 2:
                a = "Registration Finished";
                break;
        }
        return a;
    }

    /**
     * Retrieves a enrollment
     * @return enrollment
     */
    public Enrollment getEnrollment() {
        return enrollment;
    }

    /**
     * Sets a enrollment
     * @param enrollment
     */
    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }

    /**
     * Retrieves a list of enrollment
     * @return enrollmentList
     */
    public List<Enrollment> getEnrollmentList() {
        return enrollmentList;
    }

    /**
     * Sets a enrollment List
     * @param enrollmentList
     */
    public void setEnrollmentList(List<Enrollment> enrollmentList) {
        this.enrollmentList = enrollmentList;
    }

    /**
     * Retrieves a remote enrollment
     * @return enrollmentRemote
     */
    public EnrollmentRemote getEnrollmentRemote() {
        return enrollmentRemote;
    }

    /**
     * Sets a remote enrollment
     * @param enrollmentRemote
     */
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
     * Retrieves a list of grades
     * @return gradeList
     */
    public List<Grade> getGradeList() {
        return gradeList;
    }

    /**
     * Set a List of grades
     * @param gradeList
     */
    public void setGradeList(List<Grade> gradeList) {
        this.gradeList = gradeList;
    }

    /**
     * Retrieves a remote grade
     * @return gradeRemote
     */
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
     * Retrieves a list of approved courses
     * @return approvedCourseList
     */
    public List<Course> getApprovedCourseList() {
        return approvedCourseList;
    }

    /**
     * Sets a list of approved courses
     * @param approvedCourseList
     */
    public void setApprovedCourseList(List<Course> approvedCourseList) {
        this.approvedCourseList = approvedCourseList;
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

    /**
     * Retrieves a list of requisites to the course
     * @return courseRequisiteList
     */
    public List<Course> getCourseRequisiteList() {
        return courseRequisiteList;
    }

    /**
     * Sets the list of requisites to the course
     * @param courseRequisiteList
     */
    public void setCourseRequisiteList(List<Course> courseRequisiteList) {
        this.courseRequisiteList = courseRequisiteList;
    }

    /**
     * Retrieves a list of the grade which don't requires validation
     * @return gradeNotRequiresValidationList
     */
    public List<Grade> getGradeNotRequiresValidationList() {
        return gradeNotRequiresValidationList;
    }

    /**
     * Sets a list of the grade which don't requires validation
     * @param gradeNotRequiresValidationList
     */
    public void setGradeNotRequiresValidationList(List<Grade> gradeNotRequiresValidationList) {
        this.gradeNotRequiresValidationList = gradeNotRequiresValidationList;
    }

    /**
     * Retrieves a list of the grade which requires validation
     * @return gradeRequiresValidationList
     */
    public List<Grade> getGradeRequiresValidationList() {
        return gradeRequiresValidationList;
    }

    /**
     * Sets a list of the grade which requires validation
     * @param gradeRequiresValidationList
     */
    public void setGradeRequiresValidationList(List<Grade> gradeRequiresValidationList) {
        this.gradeRequiresValidationList = gradeRequiresValidationList;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
