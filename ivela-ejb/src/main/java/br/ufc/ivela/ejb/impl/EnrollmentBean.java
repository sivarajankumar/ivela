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
# File: EnrollmentBean.java                                                                        #
# Document: Bean Implementation for Enrollment Remote                                              #
# Date        - Author(Company)                   - Issue# - Summary                               #
# XX-XXX-XXX -  Emanuelle                         - XXXXXX - Initial Version                       #
# 02-DEC-2009 - Otofuji (Eldorado)                - 000021 - Add remove by grade and user          #
##################################################################################################*/
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.Enrollment;
import br.ufc.ivela.ejb.interfaces.*;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author emanuelle
 */
@Stateless(mappedName="EnrollmentBean")
public class EnrollmentBean implements EnrollmentRemote {

    private GenericDao<Enrollment> daoEnrollment = DaoFactory.getInstance(Enrollment.class);
    private GenericDao<Course> daoCourse = DaoFactory.getInstance(Course.class);

    public Long add(Enrollment enrollment) {
        if (enrollment.getGrade().getRequiresEnrollmentValidation()) {
            enrollment.setStatus(Constants.ENROLLMENT_PENDING);
        } else {
            enrollment.setStatus(Constants.ENROLLMENT_ACTIVE);
        }
        return (Long) daoEnrollment.save(enrollment);
    }

    public boolean update(Enrollment enrollment) {
        return daoEnrollment.update(enrollment);
    }

    public Enrollment get(Long id) {
        return daoEnrollment.get(id);
    }

    public List<Enrollment> getByUser(Long idUser) {
        Object[] params = new Object[]{idUser};
        return daoEnrollment.find("select e from Enrollment e, Grade g, Course c WHERE e.systemUser.id = ? and e.grade.id = g.id and g.courseId = c.id and c.active = true", params);

        //return daoEnrollment.getByFK("systemUser.id", idUser);
    }

    /**
     * Return List enrollment by user and status
     * 
     */
    public List<Enrollment> getByUserAndStatus(Long idUser, int status) {
        Object[] params = new Object[]{idUser, status};
        
        List<Enrollment> list = daoEnrollment.find("select e from Enrollment e, Grade g, Course c WHERE e.systemUser.id = ? and e.status = ? and e.grade.id = g.id and g.courseId = c.id and c.active = true", params);
        
        for (Enrollment enrollment : list) {
            Long courseId = enrollment.getGrade().getCourseId();
            enrollment.getGrade().setCourse(daoCourse.get(courseId));
        }
        
        return list;
    }

    public List<Enrollment> getByGrade(Long idGrade) {
        //return daoEnrollment.getByFK("grade.id", idGrade);
        Object[] params = new Object[]{idGrade};
        return daoEnrollment.find("select e from Enrollment e, Grade g, Course c, SystemUser su WHERE e.grade.id = ? and e.grade.id = g.id and g.courseId = c.id and c.active = true and e.systemUser.id = su.id and su.enabled = true", params);

    }

    public boolean getByUserGrade(Long userId, Long gradeId) {
        Object[] params = new Object[]{userId, gradeId};

        List<Enrollment> e = daoEnrollment.find("select e from Enrollment e, Grade g, Course c WHERE e.systemUser.id = ? and e.grade.id = ? and e.grade.id = g.id and g.courseId = c.id and c.active = true", params);
        if (e.isEmpty()) 
            return false;        
        
        return false;        
    }

    public boolean remove(Long userId, Long gradeId) {
        Object[] params = new Object[]{userId, gradeId};

        List<Enrollment> e = daoEnrollment.find("select e from Enrollment e, Grade g, Course c WHERE e.systemUser.id = ? and e.grade.id = ? and e.grade.id = g.id and g.courseId = c.id and c.active = true", params);
        if (e.isEmpty()) 
            return false;        
        
        return remove(e.get(0).getId());        
    }
    
    public boolean remove(Long id) {
        return daoEnrollment.remove(id);
    }

    public List<Enrollment> getByStatus(int status) {
        //Enrollment enrollment = new Enrollment();
        //enrollment.setStatus(status);
        //return daoEnrollment.getByExample(enrollment);
        Object[] params = new Object[]{status};
        return daoEnrollment.find("select e from Enrollment e, Grade g, Course c WHERE e.status = ? and e.grade.id = g.id and g.course.id = c.id and c.active = true", params);
    }
}
