/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.web.action;

import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.ejb.interfaces.CourseRemote;
import br.ufc.ivela.ejb.interfaces.GradeRemote;
import br.ufc.ivela.ejb.interfaces.RepositoryRemote;
import br.ufc.ivela.ejb.interfaces.SystemUserRemote;
import com.opensymphony.xwork2.Preparable;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author nelson
 */
public class GradeAction extends GenericAction implements Preparable {

    private GradeRemote gradeRemote;
    private Grade grade;
    private List<Grade> gradeList;
    private CourseRemote courseRemote;
    private List<Course> courseList;
    private RepositoryRemote repositoryRemote;
    private SystemUserRemote systemUserRemote;
    private List<SystemUser> systemUserList;
    private InputStream inputStream;

    /**
     * Get a list of the grades
     * @return list
     */
    public String list() {
        gradeList = gradeRemote.getAll();

        return "list";
    }

    /**
     * 
     * @return
     */
    public String input() {
        return INPUT;
    }

    /**
     * Add a new grade
     * @return a list of the grades
     */
    public String add() {
        gradeRemote.add(grade);
        return list();
    }

    /**
     * Edit a grade
     * @return edit grade
     */
    public String edit() {
        Grade g = gradeRemote.get(grade.getId());
        setGrade(g);
        return "edit";
    }

    /**
     * Update a grade
     * @return
     */
    public String update() {
        gradeRemote.update(grade);
        return list();
    }

    /**
     * Remove a grade
     * @return
     */
    public String remove() {
        gradeRemote.remove(grade.getId());
        return list();
    }

    /**
     * Retrieves a grade in format of json
     * @return a json
     */
    public String getGradeJson() {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("grade", Grade.class);
        xStream.alias("course", Course.class);
        grade = gradeRemote.get(grade.getId());
        setInputStream(new ByteArrayInputStream(xStream.toXML(grade).getBytes()));
        return "json";
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

    /**
     * Retrieves a list of system user
     * @return systemUserList
     */
    public List<SystemUser> getSystemUserList() {
        return systemUserList;
    }

    /**
     * Sets a list of system user
     * @param systemUserList
     */
    public void setSystemUserList(List<SystemUser> systemUserList) {
        this.systemUserList = systemUserList;
    }

    /**
     * Retrieves a remote system user
     * @return systemUserRemote
     */
    public SystemUserRemote getSystemUserRemote() {
        return systemUserRemote;
    }

    /**
     * Sets a remote system user
     * @param systemUserRemote
     */
    public void setSystemUserRemote(SystemUserRemote systemUserRemote) {
        this.systemUserRemote = systemUserRemote;
    }

    /**
     * Sets a course list and a system user list
     * @throws java.lang.Exception
     */
    public void prepare() throws Exception {
        courseList = courseRemote.getAll();
        systemUserList = systemUserRemote.getAll();
    }

    /**
     * Sets a remote repository
     * @param repositoryRemote
     */
    public void setRepositoryRemote(RepositoryRemote repositoryRemote) {
        this.repositoryRemote = repositoryRemote;
    }

    /**
     * Retrieves the remote repository
     * @return repositoryRemote
     */
    public RepositoryRemote getRepositoryRemote() {
        return repositoryRemote;
    }

    /**
     * Retrieves the input stream
     * @return inputStream
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * Sets a input stream
     * @param inputStream
     */
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}