package br.ufc.ivela.ejb.impl;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.springframework.security.GrantedAuthority;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.Enrollment;
import br.ufc.ivela.commons.model.Forum;
import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.commons.model.Topic;
import br.ufc.ivela.commons.model.SystemUser.AUTHORITY;
import br.ufc.ivela.ejb.interfaces.CourseRemote;
import br.ufc.ivela.ejb.interfaces.GradeRemote;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Class of ejb which implements the interface GradeLocal
 */
@Stateless(mappedName="GradeBean")
public class GradeBean implements GradeRemote {

    @EJB
    private CourseRemote courseRemote;
    
    private GenericDao<Course> daoCourse = DaoFactory.getInstance(Course.class);
    private GenericDao<Grade> daoGrade = DaoFactory.getInstance(Grade.class);
    private GenericDao<Forum> daoForum = DaoFactory.getInstance(Forum.class);
    private GenericDao<Topic> daoTopic = DaoFactory.getInstance(Topic.class);
    private GenericDao<Enrollment> daoEnroll = DaoFactory.getInstance(Enrollment.class);
    private GenericDao<SystemUser> daoSu = DaoFactory.getInstance(SystemUser.class);    

    public Long add(Grade grade) {
        return (Long) daoGrade.save(grade);
    }

    public boolean remove(Long id) {
        return daoGrade.remove(id);
    }

    public Grade get(Long id) {
        return daoGrade.get(id);
    }

    public boolean update(Grade grade) {
        return daoGrade.update(grade);
    }

    public List<Grade> getAll() {
        return daoGrade.find("select g from Grade g, Course c WHERE g.courseId = c.id and c.active = true", new Object[] {});
    }

    public List<Grade> getByCoordinator(Long idUser, boolean validation) {
        Object[] params = new Object[]{idUser, validation};

        return daoGrade.find("select g from Grade g, Course c WHERE g.courseId = c.id and c.active = true and g.coordinator.id= ? and g.requiresEnrollmentValidation= ?", params);
    //return daoGrade.getByFK("coordinator.id", idUser);
    }

    /**
     * Retrives a list of grades
     * @param idUser
     * @param validation
     * @return
     */
    public List<Grade> getByCoordinator(Long idUser) {
        Object[] params = new Object[]{idUser};

        return daoGrade.find("select g from Grade g, Course c WHERE g.courseId = c.id and c.active = true and g.coordinator.id= ?", params);
    }

    public List<Grade> getByStatus(int status) {

        Object[] params = new Object[]{status};

        return (List<Grade>) daoGrade.find("select g from Grade g, Course c WHERE g.courseId = c.id and c.active = true and g.status = ?", params);
    }
    
    public Grade getActiveByStudentByCourse(Long userId,Long courseId) {
        //System.out.print(courseId);
        Object[] params = new Object[]{userId, 1,courseId};
         List<Grade> list = (List<Grade>)daoEnroll.find("select e.grade from Enrollment e WHERE e.systemUser.id = ? and e.status = ? and e.grade.courseId=?", params);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        else return null; 
    } 

    /**
     * retrieve the list of grade by professors
     * @param userId Id of the Professor User
     * @return
     */
    public List getGradeByProfessors(Long userId) {
        Object[] params = new Object[]{userId};        
        return daoGrade.find("select p.grade from Professor p WHERE p.systemUser.id =?", params);
    }

    public List<Grade> getGradeByTutors(Long systemUserId) {
        Object[] params = new Object[]{systemUserId};
        return daoGrade.find("select t.grade from Tutor t WHERE t.systemUser.id =?", params);
    }
    
    public List<Grade> getByStudent(SystemUser user) {
        Object[] params = new Object[]{user};
        return daoGrade.find("select g from Grade g, Course c, Enrollment e WHERE g.courseId = c.id and c.active = true and g.id = e.grade.id ? and g.requiresEnrollmentValidation = ?", params);
    }
    
    public List<Grade> getGradesByStudent(SystemUser user) {
        Object[] params = new Object[]{user};
        return daoGrade.find("select e.grade from Enrollment e WHERE e.systemUser = ?", params);
    }
    
    public List<Grade> getGradesActiveByStudent(Long userId) {
        Object[] params = new Object[]{userId, 1};
        return (List<Grade>)daoEnroll.find("select e.grade from Enrollment e WHERE e.systemUser.id = ? and e.status = ? ", params);
        
    } 
    
    public List<Grade> getGradesByStudent(Long userId) {
        Object[] params = new Object[]{userId};
        return daoGrade.find("select e.grade from Enrollment e WHERE e.systemUser.id = ?", params);
    }
    
    public List<Grade> getByCourse(Long courseId) {
        return daoGrade.getByFK("courseId", courseId);
    }
    
    public List<Course> getStructure() {
        List<Course> list = daoCourse.find("from Course c where c.active = true", new Object[] {});
        
        buildCourseListContent(list);
        return list;
    }

    public boolean remove(Grade grade) {
        return daoGrade.remove(grade);
    }

    public List<SystemUser> getProfessors(Long gradeId) {
        return (List<SystemUser>) daoGrade.find("select p from Grade g join g.professors p where g.id = ?", new Object[] { gradeId });
    }

    public List<SystemUser> getTutors(Long gradeId) {
        return (List<SystemUser>) daoGrade.find("select p from Grade g join g.tutors p where g.id = ?", new Object[] { gradeId });
    }

    public List<Grade> getGradesInProgressAndEnrolled(Long student, Long courseId) {
        Object[] params = new Object[]{student, courseId};
        return daoGrade.find("select g from Grade g, Enrollment e WHERE g.id = e.grade.id and e.systemUser.id = ? and g.courseId = ? and (e.status = 1 or e.status = 0)", params);
    }

    public List<Grade> getGradesByCourseAndStatus(Long course, int status) {
        Object[] params = new Object[]{course, status};
        return daoGrade.find("select g from Grade g Where g.courseId = ? and status = ?", params);
    }
    
    public List<Grade> getGradeListByProfessorSystemUser(Long systemUserId) {
        return daoSu.find("from Grade g where g.id = (select distinct p.grade.id from Professor p where p.systemUser.id = ? and p.systemUser.enabled = true)", new Object[] { systemUserId });
    }

    public List<Course> getStructure(SystemUser systemUser) {
        List<Course> list = new ArrayList<Course>(0);
        
        if (systemUser == null || systemUser.getId() == null) {
            return list;
        }
        
        GrantedAuthority[] authorities = systemUser.getAuthorities();
        String authority_ = authorities != null && authorities.length > 0 ? authorities[0].getAuthority() : null;

        if (AUTHORITY.ROLE_ADMIN.hasAuthority(authority_)) {
            list = courseRemote.getAll();
        } else if (AUTHORITY.ROLE_PROFESSOR.hasAuthority(authority_)) {
            list = courseRemote.getCoursesByProfessor(systemUser.getId());            
        } else if (AUTHORITY.ROLE_TUTOR.hasAuthority(authority_)) {
            list = courseRemote.getCoursesByTutor(systemUser.getId());
        } else if (AUTHORITY.ROLE_COORD.hasAuthority(authority_)) {
            list = courseRemote.getCoursesByCoordinator(systemUser.getId());
        }
        
        buildCourseListContent(list);
        
        return list;
    }

    private void buildCourseListContent(List<Course> courseList) {
        for (Course course : courseList) {
            course.setGrades(daoGrade.getByFK("courseId", course.getId()));
            for (Grade grade : course.getGrades()) {
                grade.setEnrollments(daoEnroll.find("select e from Enrollment e, SystemUser su where e.grade.id = ? and e.systemUser.id = su. id and su.enabled = true", new Object[]{grade.getId()}));
                List<SystemUser> professors = getProfessors(grade.getId());
                List<SystemUser> tutors = getTutors(grade.getId());
                List<Forum> forums = daoForum.getByFK("grade.id", grade.getId());
                grade.setProfessors(new HashSet<SystemUser>(professors));
                grade.setTutors(new HashSet<SystemUser>(tutors));
                grade.setCoordinator(daoSu.get(grade.getCoordinatorId()));
                grade.setCourse(course);
                for (Forum f : forums) {
                    List<Topic> topicList = daoTopic.getByFK("forum.id", f.getId());
                    f.setTopicCollection(topicList);
                }
                grade.setForums(forums);                
            }
        }
    }
}
