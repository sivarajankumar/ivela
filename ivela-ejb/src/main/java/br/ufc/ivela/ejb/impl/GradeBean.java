package br.ufc.ivela.ejb.impl;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.Enrollment;
import br.ufc.ivela.commons.model.Forum;
import br.ufc.ivela.ejb.interfaces.GradeRemote;
import br.ufc.ivela.ejb.*;
import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.commons.model.Topic;
import java.util.HashSet;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Class of ejb which implements the interface GradeLocal
 */
@Stateless(mappedName="GradeBean")
public class GradeBean implements GradeRemote {

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
     * retrive the list of grade by professors
     * @param userId
     * @return
     */
    public List getGradeByProfessors(Long gradeId) {

        Object[] params = new Object[]{gradeId};

        return daoGrade.find("select g from Grade g, Professor p, Course c WHERE g.courseId = c.id and c.active = true and g.id = p.grade.id =?", params);
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
        for (Course course : list) {
            course.setGrades(daoGrade.getByFK("courseId", course.getId()));
            for (Grade grade : course.getGrades()) {
                grade.setEnrollments(daoEnroll.getByFK("grade.id", grade.getId()));
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
}
