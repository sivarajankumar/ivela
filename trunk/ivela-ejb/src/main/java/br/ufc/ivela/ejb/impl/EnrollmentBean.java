/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
        if (e.isEmpty()) {
            return true;
        } else {
            return false;
        }
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
