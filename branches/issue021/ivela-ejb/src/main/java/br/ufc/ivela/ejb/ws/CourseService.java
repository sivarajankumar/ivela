/*##################################################################################################
# Copyright(c) 2008-2009 by IBM Brasil Ltda and others                                             #
# This file is part of ivela project, an open-source                                               #
# Program URL   : http://code.google.com/p/ivela/                                                  #   
#                                                                                                  #
# This program is free software; you can redistribute it and/or modify it under the terms          #
# of the GNU General Public License as published by the Free Software Foundation; either           #
# version 3 of the License, or (at your option) any later version.                                 #
#                                                                                                  #
# This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;        #
# without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.        #
# See the GNU General Public License for more details.                                             #  
#                                                                                                  #
####################################################################################################
# File: CourseService.java                                                                         #
# Document: Web Services for Courses and Grades                                                    #
# Date        - Author(Company)                   - Issue# - Summary                               #
# XX-XXX-XXX -  Maristella Myrian                 - XXXXXX - Initial Version                       #
# 02-DEC-2009 - Otofuji (Eldorado)                - 000021 - Create Initial Ivela Services         #
##################################################################################################*/
package br.ufc.ivela.ejb.ws;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.Enrollment;
import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.ejb.interfaces.CourseRemote;
import br.ufc.ivela.ejb.interfaces.EnrollmentRemote;
import br.ufc.ivela.ejb.interfaces.GradeRemote;
import br.ufc.ivela.ejb.ws.objects.CourseResource;
import br.ufc.ivela.ejb.ws.objects.GradeResource;

import com.thoughtworks.xstream.XStream;

/**
 * Bean class for Ivela Course Services (List, join, etc..)
 */
@Stateless
@WebService(name = "Course", serviceName = "IvelaServices")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
@TransactionAttribute(TransactionAttributeType.NEVER)
public class CourseService extends AbstractService {

    private static Log logger = LogFactory.getLog(CourseService.class);
    
    @EJB
    private CourseRemote courseLocal;
    
    @EJB
    private GradeRemote gradeRemote;
    
    @EJB
    private EnrollmentRemote enrollmentRemote;
    
    private XStream xStream = new XStream();  
   
    @WebMethod
    public String getAllCourses() {
        List<Course> db = courseLocal.getAll();
        List<CourseResource> result = new ArrayList<CourseResource>(db.size());            
        for (Course course: db) {
            result.add(new CourseResource(course));    
        }
        xStream.alias("Course", CourseResource.class);
        return xStream.toXML(result);
    }
    
    @WebMethod
    public String getAllGrades(
            @WebParam(name = "courseId") Long courseId) {
        if (courseId == null) {
            return "Error: No Course ID parameter";
        }
        
        try {
            List<Grade> db = gradeRemote.getByCourse(courseId);
            List<GradeResource> result = new ArrayList<GradeResource>(db.size());            
            for (Grade grade: db) {
                List<Enrollment> count = enrollmentRemote.getByGrade(grade.getId());
                GradeResource gr = new GradeResource(grade);
                gr.setCountStudents(count.size());
                result.add(gr);                    
            }
            
            xStream.alias("Grade", GradeResource.class);
            return xStream.toXML(result);
        } catch (Exception e) {
            logger.error("IvelaServices:Course:Error: Could not retrieve course", e);
            return "Error: Could not retrieve course";
        }        
   }
    
    @WebMethod
    public String getActiveGrades( 
            @WebParam(name = "courseId") Long courseId) {
        if (courseId == null) {
            return "Error: No Course ID parameter";
        }
        
        try {
            List<Grade> db = gradeRemote.getByCourse(courseId);
            List<GradeResource> result = new ArrayList<GradeResource>(db.size());
            Date now = new Date();
            for (Grade grade: db) {
                if (now.before(grade.getStartDatetime()) || grade.getEndDatetime().before(now)) {
                    continue;
                }
                List<Enrollment> count = enrollmentRemote.getByGrade(grade.getId());
                GradeResource gr = new GradeResource(grade);
                gr.setCountStudents(count.size());
                result.add(gr);                    
            }
            xStream.alias("Grade", GradeResource.class);
            return xStream.toXML(result);
        } catch (Exception e) {
            logger.error("IvelaServices:Course:Error: Could not retrieve course", e);
            return "Error: Could not retrieve course";
        }
        
    }
    
    @WebMethod
    public String getGradesByStudent(
            @WebParam(name = "studentName") String studentName,
            @WebParam(name = "StudentPassword") String studentPassword) {                
        List<Grade> grades = new ArrayList<Grade>();
        List<GradeResource> result = new ArrayList<GradeResource>();
        SystemUser user = getSystemUser(studentName, studentPassword);
        
        if (user != null) {                        
            grades = gradeRemote.getGradesByStudent(user.getId());            
                        
            for (Grade grade: grades) {
                result.add(new GradeResource(grade));    
            }
        } else {
            return "Error: No Student found with the studentName/password combination " + studentName;
        }
        xStream.alias("Grade", GradeResource.class);
        return xStream.toXML(result);
    }
    
    @WebMethod
    public String enrollGrade(
            @WebParam(name = "gradeId") Long gradeId,
            @WebParam(name = "studentName") String studentName,
            @WebParam(name = "StudentPassword") String studentPassword) {
                
        if (gradeId == null) {
            return "Error: No Grade ID Parameter";
        }
        if ((studentName == null)||(studentName.isEmpty())) {
            return "Error: No Student parameter";
        }
        if ((studentPassword == null)||(studentPassword.isEmpty())) {
            return "Error: You must supply the password for the student";
        }
        
        Grade g = gradeRemote.get(gradeId);
        
        SystemUser user = getSystemUser(studentName, studentPassword);            
            
        if (user != null) {
            List<Grade> grades = gradeRemote.getGradesByStudent(user.getId());
            boolean canAdd = true;
            for (Grade gr : grades) {
                if (gr.getId().longValue() == g.getId().longValue()) {
                    canAdd = false;
                    break;
                }
            }
            
            
            if (canAdd) {
                try {
                    Enrollment enrollment = new Enrollment();
                    enrollment.setGrade(g);
                    enrollment.setSystemUser(user);
                    enrollment.setStartDatetime(new Date());
                    List<Enrollment> count = enrollmentRemote.getByGrade(g.getId());

                    if (count.size() < g.getMaxStudents()) {
                        enrollmentRemote.add(enrollment);
                    } else {
                        return "Error: Max Students count reached for " + g.getName();
                    }
                    addHistory("history.enrolluser.title", "history.enrolluser.description", user, user
                            .getUsername(), g.getName());
                } catch (Exception e) {
                    logger.error("IvelaServices:Course:Error: Could not enroll user " + user.getUsername(), e);
                    return "Error: Enrolling User " + user.getUsername();
                }
            }
            else {
                return "Error: Student already enrolled for " + g.getName();
            }          
            
        } else {
            return "Error: No Student found with the studentName/password combination " + studentName;   
        }
        
        return "OK";
    }

    @WebMethod
    public String cancelEnrollmentGrade(
            @WebParam(name = "gradeId") Long gradeId,
            @WebParam(name = "studentName") String studentName,
            @WebParam(name = "StudentPassword") String studentPassword) {
        
        if (gradeId == null) {
            return "Error: No Grade ID Parameter";
        }
        if ((studentName == null)||(studentName.isEmpty())) {
            return "Error: No Student parameter";
        }
        if ((studentPassword == null)||(studentPassword.isEmpty())) {
            return "Error: You must supply the password for the student";
        }
                
        SystemUser user = getSystemUser(studentName, studentPassword);
        
        if (user != null) {
            try {
                enrollmentRemote.getByUserGrade(gradeId, user.getId());               
            } catch (Exception e) {
                logger.error("IvelaServices:Course:Error: Could not enroll user " + user.getUsername(), e);
                return "Error: Deleting Enrollment for User " + user.getUsername();
            }
                                              
        } else {
            return "Error: No Student found with the studentName/password combination " + studentName;
        }
        
        return "OK";
        
    }
}

