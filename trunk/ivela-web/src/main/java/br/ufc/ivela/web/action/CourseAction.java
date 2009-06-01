/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.web.action;

import br.ufc.ivela.commons.dao.Page;
import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.Discipline;
import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.ejb.interfaces.CourseRemote;
import br.ufc.ivela.ejb.interfaces.GradeRemote;
import br.ufc.ivela.ejb.interfaces.ProfessorRemote;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Maristella Myrian
 */
public class CourseAction extends GenericAction {

    private CourseRemote courseRemote;
    private GradeRemote gradeRemote;
    private ProfessorRemote professorRemote;
    private Course course;
    private Discipline discipline;
    private List<Course> courseList;
    private int pageCount;
    private int page;
    private int pageSize = 5;
    private int count;
    private InputStream inputStream;
    private String nick;
    private String chatRoomName;
    private String teacherName;
    private Grade grade;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
    
    /**
     * List all course per page
     */
    public String list() {
        if (page < 1) {
            page = 1;
        }
        Page p = courseRemote.getAllPage(page, pageSize);
        courseList = p.getList();
        setCount(p.getCount());
        setPageCount(p.getPageCount());
        return "list";
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
     * Retrieves the courseLocal
     * @return courseLocal
     */
    public CourseRemote getCourseRemote() {
        return courseRemote;
    }

    /**
     *Sets the course Local
     * @param courseRemote
     */
    public void setCourseRemote(CourseRemote courseRemote) {
        this.courseRemote = courseRemote;
    }

    /**
     * Retrieves a course List
     * @return courseList
     */
    public List<Course> getCourseList() {
        return courseList;
    }

    /**
     *Sets the list of course
     * @param courseList
     */
    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    /**
     * Retrieves the count
     * @return
     */
    public int getCount() {
        return count;
    }

    /**
     * Set a count
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Retrieves the Page
     * @return page
     */
    public int getPage() {
        return page;
    }

    /**
     * Sets a page
     * @param page
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * Retrieves the Page count
     * @return pageCount
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * Sets a Page count
     * @param pageCount
     */
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
    

    /**
     * Retrieves the Page size
     * @return
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets a page size
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    public String showChatStd(){
        System.out.println("teste chat");
        this.nick = this.getAuthenticatedUser().getUsername();
        //this.chatRoomName = "#course_"+this.course.getId();
        this.chatRoomName = "#course_"+course.getId(); 
        this.grade = gradeRemote.getActiveByStudentByCourse(this.getAuthenticatedUser().getId(), course.getId());
        Set<SystemUser> list = (Set<SystemUser>)grade.getProfessors();
        Iterator i = list.iterator();
        if(i.hasNext())
            this.teacherName =  ((SystemUser)i.next()).getUsername();
        else 
            this.teacherName = "";
        System.out.println("course id: "+course.getId());
        System.out.println("teacher "+teacherName);
        
        
        return "showChatStd";
    }

    public String getChatRoomName() {
        return chatRoomName;
    }

    public void setChatRoomName(String chatRoomName) {
        this.chatRoomName = chatRoomName;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public GradeRemote getGradeRemote() {
        return gradeRemote;
    }

    public void setGradeRemote(GradeRemote gradeRemote) {
        this.gradeRemote = gradeRemote;
    }

    public ProfessorRemote getProfessorRemote() {
        return professorRemote;
    }

    public void setProfessorRemote(ProfessorRemote professorRemote) {
        this.professorRemote = professorRemote;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    
    
}