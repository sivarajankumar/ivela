/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.web.action.admin;

import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.Enrollment;
import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.ejb.interfaces.CourseRemote;
import br.ufc.ivela.ejb.interfaces.EnrollmentRemote;
import br.ufc.ivela.ejb.interfaces.GradeRemote;
import br.ufc.ivela.ejb.interfaces.SystemUserRemote;
import br.ufc.ivela.web.action.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Action of the EAD's home
 */
public class HomeAction extends GenericAction {

    private List<Course> courseList;
    private String message;
    private CourseRemote courseRemote;
    private GradeRemote gradeRemote;
    private EnrollmentRemote enrollmentRemote;
    private SystemUserRemote systemUserRemote;
    private InputStream inputStream;
    
    public String home() {
        return "home";
    }
    
    public String execute() {
        setMessage(getMessage());
        //courseList = gradeRemote.getStructure();
        return SUCCESS;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public CourseRemote getCourseRemote() {
        return courseRemote;
    }

    public void setCourseRemote(CourseRemote courseRemote) {
        this.courseRemote = courseRemote;
    }

    public EnrollmentRemote getEnrollmentRemote() {
        return enrollmentRemote;
    }

    public void setEnrollmentRemote(EnrollmentRemote enrollmentRemote) {
        this.enrollmentRemote = enrollmentRemote;
    }

    public SystemUserRemote getSystemUserRemote() {
        return systemUserRemote;
    }

    public void setSystemUserRemote(SystemUserRemote systemUserRemote) {
        this.systemUserRemote = systemUserRemote;
    }
    
    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public GradeRemote getGradeRemote() {
        return gradeRemote;
    }

    public void setGradeRemote(GradeRemote gradeRemote) {
        this.gradeRemote = gradeRemote;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getStructureJson() {
        StringBuilder json = new StringBuilder();
        
        json.append("{");
        json.append("\"courses\":[");
        List<Course> courseList = courseRemote.getAll();
        for (Course c : courseList) {
            json.append("{");
                json.append("\"id\":\"" + c.getId() + "\",");
                json.append("\"name\":\"" + c.getName() + "\",");
                json.append("\"description\":\"" + c.getDescription() + "\",");
//                json.append("\"grades\":[");
//                List<Grade> gradeList = gradeRemote.getByCourse(c.getId());
//                for (Grade g : gradeList) {
//                    json.append("{");
//                    json.append("\"id\":\"" + g.getId() + "\",");
//                    json.append("\"name\":\"" + g.getName() + "\",");
//                    json.append("\"period\":\"" + g.getPeriod() + "\",");
//                    json.append("\"coordinatorId\":\"" + g.getCoordinatorId() + "\",");
//                    json.append("\"courseId\":\"" + g.getCourseId() + "\",");
//                    json.append("\"status\":\"" + g.getStatus() + "\",");
//                    json.append("\"maxDuration\":\"" + g.getMaxDuration() + "\",");
//                    json.append("\"maxStudents\":\"" + g.getMaxStudents() + "\",");
//                    json.append("\"startDatetime\":\"" + g.getStartDatetime() + "\",");
//                    json.append("\"endDatetime\":\"" + g.getEndDatetime() + "\",");
//                    json.append("\"course\":{");
//                        json.append("\"id\":\"" + c.getId() + "\",");
//                        json.append("\"name\":\"" + c.getName() + "\",");
//                        json.append("\"description\":\"" + c.getDescription() + "\"");
//                    json.append("},");
//                    SystemUser coordinator = systemUserRemote.get(g.getCoordinatorId());
//                    json.append("\"coordinator\":{");
//                        json.append("\"id\":\"" + coordinator.getId() + "\",");
//                        json.append("\"username\":\"" + coordinator.getUsername() + "\",");
//                        json.append("\"email\":\"" + coordinator.getEmail() + "\"");
//                    json.append("},");
//                    // commented by Rodrigo Felix
//                    json.append(getEnrollmentsJson(g.getId()));
//                    json.append(",");        
//                    json.append(getProfessorsJson(g.getId()));
//                    json.append(",");
//                    json.append(getTutorsJson(g.getId()));
//                    json.append("},");
//                }
                if (json.substring(json.length() - 1).equals(","))
                    json = new StringBuilder(json.substring(0, json.length() - 1));
//                json.append("]");
            json.append("},");
        }
        if (json.substring(json.length() - 1).equals(","))
            json = new StringBuilder(json.substring(0, json.length() - 1));
        json.append("]");
        json.append("}");
        setInputStream(new ByteArrayInputStream(json.toString().getBytes()));
        return "json";
    }
    
    private String getTutorsJson(Long gradeId) {
        StringBuilder json = new StringBuilder();
        List<SystemUser> tutors = gradeRemote.getTutors(gradeId);
        json.append("\"tutors\":[");
        for (SystemUser su :tutors) {
            json.append("{");
            json.append("\"id\":\"" + su.getId() + "\",");
            json.append("\"username\":\"" + su.getUsername() + "\"");
            json.append("},");
        }
        if (json.substring(json.length() - 1).equals(","))
            json = new StringBuilder(json.substring(0, json.length() - 1));
        json.append("]");     
        return json.toString();
    }
    
    private String getProfessorsJson(Long gradeId) {
        StringBuilder json = new StringBuilder();
        List<SystemUser> tutors = gradeRemote.getTutors(gradeId);
        json.append("\"professors\":[");
        for (SystemUser su :tutors) {
            json.append("{");
            json.append("\"id\":\"" + su.getId() + "\",");
            json.append("\"username\":\"" + su.getUsername() + "\"");
            json.append("},");
        }
        if (json.substring(json.length() - 1).equals(","))
            json = new StringBuilder(json.substring(0, json.length() - 1));
        json.append("]");     
        return json.toString();
    }    
    
    private String getEnrollmentsJson(Long gradeId) {
        StringBuilder json = new StringBuilder();
        List<Enrollment> enrollments = enrollmentRemote.getByGrade(gradeId);
        json.append("\"enrollments\":[");
        for (Enrollment e :enrollments) {
            json.append("{");
            json.append("\"id\":\"" + e.getId() + "\",");
            json.append("\"startDatetime\":\"" + e.getStartDatetime() + "\",");
            json.append("\"status\":\"" + e.getStatus() + "\",");
                json.append("\"systemUser\":{");
                    json.append("\"id\":\"" + e.getSystemUser().getId() + "\",");
                    json.append("\"username\":\"" + e.getSystemUser().getUsername() + "\",");
                    json.append("\"createdAt\":\"" + e.getSystemUser().getCreatedAt() + "\",");
                    json.append("\"email\":\"" + e.getSystemUser().getEmail() + "\"");
                json.append("},");
                json.append("\"grade\":{");
                    json.append("\"id\":\"" + e.getGrade().getId() + "\",");
                    json.append("\"name\":\"" + e.getGrade().getName() + "\"");
                json.append("}");           
            json.append("},");
        }
        if (json.substring(json.length() - 1).equals(","))
            json = new StringBuilder(json.substring(0, json.length() - 1));
        json.append("]");           
        return json.toString();
    }

}
