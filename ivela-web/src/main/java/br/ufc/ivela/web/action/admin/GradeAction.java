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
# File: GradeAction.java                                                                    #
# Document: Grade Admin Action                                                              #
# Date        - Author(Company)                    - Issue# - Summary                       #
# XX-XXX-XXX -  nelson                             - XXXXXX - Initial Version               #
# 26-JUN-2009 - otofuji (Instituto Eldorado)       - 000010 - General i18n Fixes            #
# 13-AUG-2009 - fantato (Instituto Eldorado)       - 000014 - fixing multiple students enrollment #
 #############################################################################################    
 */
package br.ufc.ivela.web.action.admin;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.mail.MailSender;
import br.ufc.ivela.commons.model.Authentication;
import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.Enrollment;
import br.ufc.ivela.commons.model.Forum;
import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.commons.model.Message;
import br.ufc.ivela.commons.model.NewsFlash;
import br.ufc.ivela.commons.model.Profile;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.ejb.interfaces.CalendarRemote;
import br.ufc.ivela.ejb.interfaces.ForumRemote;
import br.ufc.ivela.ejb.interfaces.MessageRemote;
import br.ufc.ivela.ejb.interfaces.NewsFlashRemote;
import br.ufc.ivela.ejb.interfaces.ProfileRemote;
import br.ufc.ivela.ejb.interfaces.RepositoryRemote;
import br.ufc.ivela.ejb.interfaces.SystemUserRemote;
import br.ufc.ivela.ejb.interfaces.TopicRemote;
import br.ufc.ivela.web.action.CourseAwareAction;

import com.opensymphony.xwork2.ActionContext;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

public class GradeAction extends CourseAwareAction {
    
    private SystemUser systemUser;
    private String message;
    private String startDate;
    private String endDate;
    private InputStream inputStream;
    private String title;
    private String description;
    private String systemUserIds;
    private String status;
    private String gradeIds;
    private String what;
    private String dtStart;
    private String dtEnd;
    private String where;
    private String text;    
    private RepositoryRemote repositoryRemote;
    private SystemUserRemote systemUserRemote;
    
    private MessageRemote messageRemote;
    private NewsFlashRemote newsFlashRemote;
    private CalendarRemote calendarRemote;
    private ProfileRemote profileRemote;
    private String requires;
    private List<SystemUser> coordinatorList;
    private List<SystemUser> studentList;
    private List<SystemUser> professorList;
    private List<SystemUser> tutorList;
    private File fileStudents;
    private String fileStudentsFileName;
    private List<String> studentsEnrollment;
    private Enrollment enrollment;
    private Profile profile;
    /* Forum */
    private ForumRemote forumRemote;
    private TopicRemote topicRemote;
    private boolean userOk;
    private boolean ignoredMail;

    public ForumRemote getForumRemote() {
        return forumRemote;
    }

    public void setForumRemote(ForumRemote forumRemote) {
        this.forumRemote = forumRemote;
    }

    public ProfileRemote getProfileRemote() {
        return profileRemote;
    }

    public void setProfileRemote(ProfileRemote profileRemote) {
        this.profileRemote = profileRemote;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public TopicRemote getTopicRemote() {
        return topicRemote;
    }

    public void setTopicRemote(TopicRemote topicRemote) {
        this.topicRemote = topicRemote;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }

    public File getFileStudents() {
        return fileStudents;
    }

    public void setFileStudents(File fileStudents) {
        this.fileStudents = fileStudents;
    }

    public String getFileStudentsFileName() {
        return fileStudentsFileName;
    }

    public void setFileStudentsFileName(String fileStudentsFileName) {
        this.fileStudentsFileName = fileStudentsFileName;
    }

    public List<String> getStudentsEnrollment() {
        return studentsEnrollment;
    }

    public void setStudentsEnrollment(List<String> studentsEnrollment) {
        this.studentsEnrollment = studentsEnrollment;
    }

    public String enrollmentStudents() {
        try {
            String email = "";
            boolean enroll;
            studentsEnrollment = new ArrayList<String>();     
            SAXBuilder sb = new SAXBuilder();
            Document d = sb.build(fileStudents);
            Element mural = d.getRootElement();
            List elements = mural.getChildren();
            Iterator i = elements.iterator();      
            while (i.hasNext()) {
                userOk = false;
                ignoredMail = false;
                enroll = false;
            	Element element = (Element) i.next();
                this.addProfile(element.getChildText("firstName"), element.getChildText("lastName"));
                email = element.getChildText("email");

                // This code is a trick to ignore exceptions from mail server
                try {
                	this.addSystemUser(email);
                	if (userOk) { 
                		enroll = this.enroll();
                	}                	
                } catch (Throwable ex) {
                	Logger.getLogger(GradeAction.class.getName()).log(Level.SEVERE, null, ex);
                	enroll = false;
                }
                enroll = (enroll & userOk);
                studentsEnrollment.add(element.getChildText("firstName") + "#" + element.getChildText("lastName")+"#"+Boolean.toString(enroll));
            }

           getSession().put("studentsEnroll", studentsEnrollment);

        } catch (JDOMException ex) {
            log.error("enrollmentStudents", ex);
        } catch (IOException ex) {
            log.error("enrollmentStudents", ex);            
        }
      
        return "input";
    }

    /**
     * Retrieves a info about the professores
     * @return a string in the json format
     */
    public String getEnrollResults() {
    	StringBuilder json = new StringBuilder();
    	List<String> studentsEnroll = (List<String>)getSession().get("studentsEnroll");
    	if (studentsEnroll != null) {
	        json.append("{");
	            json.append("\"students\":[");
	                    for (String d : studentsEnroll) {
	                    json.append("{");        
	                    	String[] students = d.split("#");
	                        json.append("\"firstName\":\"" + students[0] + "\",");
	                        json.append("\"lastName\":\"" + students[1] + "\",");
	                        json.append("\"status\":\"" + students[2] + "\"");
	                    json.append("},");
	                    }
	                    if (json.substring(json.length() - 1).equals(","))
	                        json = new StringBuilder(json.substring(0, json.length() - 1));                   
	                json.append("],");                 
	        json.append("\"result\": \"true\"");
    	} else {
    		json.append("\"result\": \"false\"");
    	}
    	json.append("}");
        setInputStream(new ByteArrayInputStream(json.toString().getBytes()));
        return "json";
    }
    
    
    public boolean enroll() {
        log.debug("enroll");
        List<Grade> grades = gradeRemote.getGradesByStudent(this.systemUser.getId());
        boolean canAdd = true;
        Grade g = gradeRemote.get(grade.getId());
        for (Grade gr : grades) {
            if (gr.getId().longValue() == g.getId().longValue()) {
                log.debug("has");
                canAdd = false;
                break;
            }
        }

        if (canAdd) {
            log.debug("canAdd");
            enrollment = new Enrollment();
            enrollment.setGrade(g);
            enrollment.setStartDatetime(new Date());
            enrollment.setSystemUser(this.systemUser);

            List count = enrollmentRemote.getByGrade(g.getId());

            if (count.size() < g.getMaxStudents()) {
                log.debug("tam");
                enrollmentRemote.add(enrollment);
                grade = gradeRemote.get(grade.getId());
                addHistory("history.enrolluser.title", "history.enrolluser.description", this.systemUser.getUsername(), grade.getName());
                return true;
            }

        }
        return false;
    }

    public void addSystemUser(String email) {
        this.systemUser = new SystemUser();
        this.systemUser.setEnabled(true);


        //gerar login
        this.systemUser.setUsername(createUserNameRandom());

        //gerar senha
        String password = createStringRandom();
        this.systemUser.setPassword(systemUserRemote.encrypt(password));

        //set email
        this.systemUser.setEmail(email);

        //set social number
        this.systemUser.setSocialNumber("");

        //set  profile
        this.systemUser.setProfile(profile);


        this.systemUser.setAuthentication(new Authentication(Constants.ROLE_USER));
        userOk = false;
        try { 
        	Long id = systemUserRemote.add(this.systemUser);
        	this.systemUser = systemUserRemote.get(id);
        
        	try {
        		MailSender.send(new String[]{this.systemUser.getEmail()}, "[ivela] Request password", "Your username is " + this.systemUser.getUsername() + "<br>Your password is: " + password);
        	} catch (Throwable ex) {
        		ignoredMail = true;
        	}
        	
        	HttpServletRequest request = ServletActionContext.getRequest();

        	boolean result = calendarRemote.addInfo(request.getServerName(), String.valueOf(request.getServerPort()), systemUser.getUsername());

        	addHistory("history.createuser.title", "history.createuser.description", this.systemUser, this.systemUser.getUsername());
        	userOk = true;
        } catch (Throwable ex){
        	Logger.getLogger(GradeAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addProfile(String firstName, String lastName) {
        profile = new Profile();
        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        Long id = profileRemote.add(profile);
        profile = profileRemote.get(id);
    }

    public String createStringRandom() {
        String[] carct = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
            "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"
        };

        String str = "";

        for (int x = 0; x < 6; x++) {
            int j = (int) (Math.random() * carct.length);
            str += carct[j];

        }

        return str;
    }

    public String createUserNameRandom() {
        String[] carct = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        String str = "";

        String[] fNames = profile.getFirstName().split(" ");
        String[] lNames = profile.getLastName().split(" ");
        if (fNames.length > 0 && lNames.length > 0) {
            str += fNames[0] + lNames[0];

        } else if (fNames.length == 0) {
            for (int i = 0; i < lNames.length; i++) {
                str += lNames[i];
            }
        } else if (lNames.length == 0) {
            for (int i = 0; i < fNames.length; i++) {
                str += fNames[i];
            }
        }

        if (systemUserRemote.exists(str)) {

            for (int x = 0; x < 2; x++) {
                int j = (int) (Math.random() * carct.length);
                str += carct[j];

            }
        }


        if (systemUserRemote.exists(str)) {

            for (int x = 0; x < 2; x++) {
                int j = (int) (Math.random() * carct.length);
                str += carct[j];

            }

        }

        if (str.length() > 20) {
            str = str.substring(0, 18);
            if (systemUserRemote.exists(str)) {
                str += carct[(int) (Math.random() * carct.length)];

            }

        }


        return str.toLowerCase();
    }

    public String getForumListJson() {
        StringBuilder json = new StringBuilder();
        grade = gradeRemote.get(grade.getId());
        json.append("{");
        json.append("\"forumList\":[");

        List<Forum> forumList = forumRemote.getForumList(getAuthenticatedUser().getId(), grade.getCourseId(), true, true, null);
        for (Forum f : forumList) {
            if (f.getGrade().getId().longValue() == grade.getId().longValue()) {
                json.append("{");
                json.append("\"id\":\"" + f.getId() + "\",");
                json.append("\"title\":\"" + f.getTitle() + "\",");
                json.append("\"description\":\"" + f.getDescription() + "\",");
                json.append("\"studentCreateTopic\":\"" + f.getStudentCreateTopic() + "\",");                
                json.append("\"studentUploadPost\":\"" + f.getStudentUploadPost() + "\",");                
                json.append("\"createBy\":{");
                json.append("\"id\":\"" + f.getCreatedBy().getId() + "\",");
                json.append("\"username\":\"" + f.getCreatedBy().getUsername() + "\",");
                json.append("\"email\":\"" + f.getCreatedBy().getEmail() + "\",");
                json.append("\"createdAt\":\"" + f.getCreatedBy().getCreatedAt() + "\"");
                json.append("},");
                json.append("\"grade\":{");
                json.append("\"id\":\"" + f.getGrade().getId() + "\",");
                json.append("\"name\":\"" + f.getGrade().getName() + "\"");
                json.append("},");
                json.append("\"public\":\"" + f.getPublic1() + "\"");
                json.append("}");
            }
            json.append(",");
        }
        json = new StringBuilder(json.substring(json.length() - 1));
        json.append("]");
        json.append("}");
        setInputStream(new ByteArrayInputStream(json.toString().getBytes()));
        return "json";
    }

    /**
     * Add a new tutors
     * @return a list of tutors
     */
    public String addTutor() {
        boolean isTutorGrade =false;
        String json = "{\"result\":\"";
        List<SystemUser> list = gradeRemote.getTutors(grade.getId());
        for (SystemUser su : list) {
            if(su.getId().equals(systemUser.getId())){
                isTutorGrade = true;
                break;
                       
            }
        }
        if(!isTutorGrade){
            grade = gradeRemote.get(grade.getId());
            systemUser = systemUserRemote.get(systemUser.getId());
            grade.getTutors().add(systemUser);
            json +=gradeRemote.update(grade);
        }
        else{
            json +="-1";
        }
        json += "\"}";
        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }

    /**
     * Remove a tutor
     * @return a list of tutors
     */
    public String removeTutor() {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("grade", Grade.class);
        xStream.alias("course", Course.class);
        xStream.alias("systemUser", SystemUser.class);
        grade = gradeRemote.get(grade.getId());
        systemUser = systemUserRemote.get(systemUser.getId());
        Set<SystemUser> tutors = grade.getTutors();
        for (SystemUser tutor : tutors) {
            if (tutor.getId().longValue() == systemUser.getId().longValue()) {
                tutors.remove(tutor);
                break;
            }
        }
        boolean result = gradeRemote.update(grade);
        xStream.alias("boolean", Boolean.class);
        String json = xStream.toXML(new Boolean(result));
        json = json.replaceAll("boolean", "result");
        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }

    /**
     * Add a new professor
     * @return a list of professors
     */
    public String addProfessor() {
        boolean isProfessorGrade =false;
        String json = "{\"result\":\"";
        List<SystemUser> list = gradeRemote.getProfessors(grade.getId());
        for (SystemUser su : list) {
            if(su.getId().equals(systemUser.getId())){
                isProfessorGrade = true;
                break;
            }
        }
        if(!isProfessorGrade){
            grade = gradeRemote.get(grade.getId());
            systemUser = systemUserRemote.get(systemUser.getId());
            grade.getProfessors().add(systemUser);
            json += gradeRemote.update(grade);
        }
        else{
            json +="-1";
        }
        json += "\"}";
        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }

    public String addStudent() {

        boolean isStudentGrade =false;
        String json = "{\"result\":\"";
        List<Enrollment> l = enrollmentRemote.getByGrade(grade.getId());
        for (Enrollment en : l) {
            if(en.getSystemUser().getId().equals( systemUser.getId())){
                isStudentGrade = true;
                break;
            }
        }
        if(!isStudentGrade){
            grade = gradeRemote.get(grade.getId());
            systemUser = systemUserRemote.get(systemUser.getId());
            Enrollment e = new Enrollment();
            e.setGrade(grade);
            e.setStartDatetime(new Date());
            e.setStatus(1);
            e.setSystemUser(systemUser);
            json +=enrollmentRemote.add(e);
        }
        else{
            json += "-1";
        }
        json += "\"}";

        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }

    public String removeStudent() {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("grade", Grade.class);
        xStream.alias("course", Course.class);
        xStream.alias("systemUser", SystemUser.class);
        grade = gradeRemote.get(grade.getId());
        systemUser = systemUserRemote.get(systemUser.getId());
        List<Enrollment> enrollments = enrollmentRemote.getByGrade(grade.getId());
        for (Enrollment e : enrollments) {
            if (e.getSystemUser().getId().longValue() == systemUser.getId().longValue()) {
                enrollmentRemote.remove(e.getId());
                break;
            }
        }
        boolean result = gradeRemote.update(grade);
        xStream.alias("boolean", Boolean.class);
        String json = xStream.toXML(new Boolean(result));
        json = json.replaceAll("boolean", "result");
        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }

    /**
     * Remove a professor
     * @return a list of professor
     */
    public String removeProfessor() {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("grade", Grade.class);
        xStream.alias("course", Course.class);
        xStream.alias("systemUser", SystemUser.class);
        grade = gradeRemote.get(grade.getId());
        systemUser = systemUserRemote.get(systemUser.getId());
        Set<SystemUser> professors = grade.getProfessors();
        for (SystemUser professor : professors) {
            if (professor.getId().longValue() == systemUser.getId().longValue()) {
                professors.remove(professor);
                break;
            }
        }
        boolean result = gradeRemote.update(grade);
        xStream.alias("boolean", Boolean.class);
        String json = xStream.toXML(new Boolean(result));
        json = json.replaceAll("boolean", "result");
        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }

    /**
     * List all grades
     * @return a string of list
     */
    public String list() {
        gradeList = gradeRemote.getAll();
        return "list";
    }

    /**
     * Sets the variables to be used on the input form
     * @return
     */
    public String input() {
        return INPUT;
    }

    public String show() {
        setMessage(getMessage());        
        courseList = gradeRemote.getStructure();
        coordinatorList = systemUserRemote.getByAuthentication(Constants.ROLE_COORD);
        professorList = systemUserRemote.getByAuthentication(Constants.ROLE_PROFESSOR);
        tutorList = systemUserRemote.getByAuthentication(Constants.ROLE_TUTOR);
        studentList = systemUserRemote.getByAuthentication(Constants.ROLE_USER);
        return "show";
    }
    
    public String getStudentNotEnrollment(){
    
        return "json";
    }

    public String getCourseInfoJson() {
        course = courseRemote.get(course.getId());
        List<SystemUser> professors = courseRemote.getProfessors(course.getId());
        List<SystemUser> tutors = courseRemote.getTutors(course.getId());
        List<Grade> grades = gradeRemote.getByCourse(course.getId());
        String studentsCount = String.valueOf(courseRemote.getStudentsCount(course.getId()));
        String gradesCount = String.valueOf(courseRemote.getGradesCount(course.getId()));
        String graduatedStudentCount = String.valueOf(courseRemote.getGraduatedStudentsCount(course.getId()));
        String professorsCount = String.valueOf(courseRemote.getProfessors(course.getId()).size());
        String tutorsCount = String.valueOf(courseRemote.getTutors(course.getId()).size());
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"course\":{");
        json.append("\"id\":\"" + course.getId() + "\",");
        json.append("\"name\":\"" + course.getName() + "\",");
        json.append("\"description\":\"" + course.getDescription() + "\",");
        json.append("\"targetAudience\":\"" + course.getTargetAudience() + "\",");
        json.append("\"studentsCount\":\"" + studentsCount + "\",");
        json.append("\"gradesCount\":\"" + gradesCount + "\",");
        json.append("\"professorsCount\":\"" + professorsCount + "\",");
        json.append("\"tutorsCount\":\"" + tutorsCount + "\",");        
        json.append("\"graduatedStudentCount\":\"" + graduatedStudentCount + "\",");
        json.append("\"grades\":[");
        List<Grade> gradeList = gradeRemote.getByCourse(course.getId());
        for (Grade g : gradeList) {
            json.append("{");
            json.append("\"id\":\"" + g.getId() + "\",");
            json.append("\"name\":\"" + g.getName() + "\",");
            json.append("\"period\":\"" + g.getPeriod() + "\",");
            json.append("\"coordinatorId\":\"" + g.getCoordinatorId() + "\",");
            json.append("\"courseId\":\"" + g.getCourseId() + "\",");
            json.append("\"status\":\"" + g.getStatus() + "\",");
            json.append("\"maxDuration\":\"" + g.getMaxDuration() + "\",");
            json.append("\"maxStudents\":\"" + g.getMaxStudents() + "\",");
            json.append("\"startDatetime\":\"" + g.getStartDatetime() + "\",");
            json.append("\"endDatetime\":\"" + g.getEndDatetime() + "\",");
//            json.append("\"course\":{");
//            json.append("\"id\":\"" + course.getId() + "\",");
//            json.append("\"name\":\"" + course.getName() + "\",");
//            json.append("\"description\":\"" + course.getDescription() + "\"");
//            json.append("},");
            SystemUser coordinator = systemUserRemote.get(g.getCoordinatorId());
            json.append("\"coordinator\":{");
            json.append("\"id\":\"" + coordinator.getId() + "\",");
            json.append("\"username\":\"" + coordinator.getUsername() + "\",");
            json.append("\"email\":\"" + coordinator.getEmail() + "\"");
            json.append("},");
    //      json.append(getEnrollmentsJson(g.getId()));
 //         json.append(",");
            json.append(getProfessorsJson(g.getId()));
            json.append(",");
            json.append(getTutorsJson(g.getId()));
            json.append("},");
        }
        if (json.substring(json.length() - 1).equals(",")) {
            json = new StringBuilder(json.substring(0, json.length() - 1));
        }
        json.append("]");
        json.append("}");
        json.append("}");
        setInputStream(new ByteArrayInputStream(json.toString().getBytes()));
        return "json";
    }

    public String getStructureJson() {
        StringBuilder json = new StringBuilder();
        courseList = courseRemote.getAll();
        json.append("{");
        json.append("\"courses\":[");
        for (Course c : courseList) {
            json.append("{");
            json.append("\"id\":\"" + c.getId() + "\",");
            json.append("\"name\":\"" + c.getName() + "\",");
            json.append("\"targetAudience\":\"" + c.getTargetAudience() + "\",");
            json.append("\"description\":\"" + c.getDescription() + "\",");
            json.append("\"grades\":[");
            List<Grade> gradeList = gradeRemote.getByCourse(c.getId());
            for (Grade g : gradeList) {
                json.append("{");
                json.append("\"id\":\"" + g.getId() + "\",");
                json.append("\"name\":\"" + g.getName() + "\",");
                json.append("\"period\":\"" + g.getPeriod() + "\",");
                json.append("\"coordinatorId\":\"" + g.getCoordinatorId() + "\",");
                json.append("\"courseId\":\"" + g.getCourseId() + "\",");
                json.append("\"status\":\"" + g.getStatus() + "\",");
                json.append("\"maxDuration\":\"" + g.getMaxDuration() + "\",");
                json.append("\"maxStudents\":\"" + g.getMaxStudents() + "\",");
                json.append("\"startDatetime\":\"" + g.getStartDatetime() + "\",");
                json.append("\"endDatetime\":\"" + g.getEndDatetime() + "\",");
                json.append("\"course\":{");
                json.append("\"id\":\"" + c.getId() + "\",");
                json.append("\"name\":\"" + c.getName() + "\",");
                json.append("\"description\":\"" + c.getDescription() + "\"");
                json.append("},");
                SystemUser coordinator = systemUserRemote.get(g.getCoordinatorId());
                json.append("\"coordinator\":{");
                json.append("\"id\":\"" + coordinator.getId() + "\",");
                json.append("\"username\":\"" + coordinator.getUsername() + "\",");
                json.append("\"email\":\"" + coordinator.getEmail() + "\"");
                json.append("},");
                json.append(getEnrollmentsJson(g.getId()));
                json.append(",");
                json.append(getProfessorsJson(g.getId()));
                json.append(",");
                json.append(getTutorsJson(g.getId()));
                json.append("},");
            }
            if (json.substring(json.length() - 1).equals(",")) {
                json = new StringBuilder(json.substring(0, json.length() - 1));
            }
            json.append("]");
            json.append("}");
        }
        json.append("]");
        json.append("}");
        setInputStream(new ByteArrayInputStream(json.toString().getBytes()));
        return "json";
    }

    private String getTutorsJson(Long gradeId) {
        StringBuilder json = new StringBuilder();
        List<SystemUser> tutors = gradeRemote.getTutors(gradeId);
        json.append("\"tutors\":[");
        for (SystemUser su : tutors) {
            json.append("{");
            json.append("\"id\":\"" + su.getId() + "\",");
            json.append("\"username\":\"" + su.getUsername() + "\",");
            json.append("\"email\":\"" + su.getEmail() + "\",");
            json.append("\"createdAt\":\"" + su.getCreatedAt() + "\"");
            json.append("},");
        }
        if (json.substring(json.length() - 1).equals(",")) {
            json = new StringBuilder(json.substring(0, json.length() - 1));
        }
        json.append("]");
        return json.toString();
    }

    private String getProfessorsJson(Long gradeId) {
        StringBuilder json = new StringBuilder();
        List<SystemUser> professors = gradeRemote.getProfessors(gradeId);
        json.append("\"professors\":[");
        for (SystemUser su : professors) {
            json.append("{");
            json.append("\"id\":\"" + su.getId() + "\",");
            json.append("\"username\":\"" + su.getUsername() + "\",");
            json.append("\"email\":\"" + su.getEmail() + "\",");
            json.append("\"createdAt\":\"" + su.getCreatedAt() + "\"");
            json.append("},");
        }
        if (json.substring(json.length() - 1).equals(",")) {
            json = new StringBuilder(json.substring(0, json.length() - 1));
        }
        json.append("]");
        return json.toString();
    }

    private String getEnrollmentsJson(Long gradeId) {
        StringBuilder json = new StringBuilder();
        List<Enrollment> enrollments = enrollmentRemote.getByGrade(gradeId);
        json.append("\"enrollments\":[");
        for (Enrollment e : enrollments) {
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
        if (json.substring(json.length() - 1).equals(",")) {
            json = new StringBuilder(json.substring(0, json.length() - 1));
        }
        json.append("]");
        return json.toString();
    }

    /**
     * Add a new grade
     * @return list of grades
     */
    public String add() {
        gradeRemote.add(grade);
        return list();
    }

    public String getGradeInfo() {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("grade", Grade.class);
        xStream.alias("course", Course.class);
        xStream.alias("professors", SystemUser.class);
        xStream.alias("tutors", SystemUser.class);
        grade = gradeRemote.get(grade.getId());
        grade.setCourse(courseRemote.get(grade.getCourseId()));

        setInputStream(new ByteArrayInputStream(xStream.toXML(grade).getBytes()));
        return "json";
    }
        public String getGradeInfoJson() {
       StringBuilder json = new StringBuilder();
       Grade g = gradeRemote.get(grade.getId());
       g.setCourse(courseRemote.get(grade.getCourseId()));
       String studentsCount = String.valueOf(enrollmentRemote.getByGrade(grade.getId()).size());
       String professorsCount = String.valueOf(getProfessorsJson(g.getId()).length());
       String tutorsCount = String.valueOf( getTutorsJson(g.getId()).length());
       json.append("{\"grade\":");
            json.append("{");
            json.append("\"id\":\"" + g.getId() + "\",");
            json.append("\"name\":\"" + g.getName() + "\",");
            json.append("\"period\":\"" + g.getPeriod() + "\",");
            json.append("\"studentsCount\":\"" + studentsCount + "\",");
            json.append("\"coordinatorId\":\"" + g.getCoordinatorId() + "\",");
            json.append("\"courseId\":\"" + g.getCourseId() + "\",");
            json.append("\"status\":\"" + g.getStatus() + "\",");
            json.append("\"maxDuration\":\"" + g.getMaxDuration() + "\",");
            json.append("\"maxStudents\":\"" + g.getMaxStudents() + "\",");
            json.append("\"startDatetime\":\"" + g.getStartDatetime() + "\",");
            json.append("\"endDatetime\":\"" + g.getEndDatetime() + "\",");
            json.append("\"studentsCount\":\"" + studentsCount + "\",");
            json.append("\"professorsCount\":\"" + professorsCount + "\",");
            json.append("\"tutorsCount\":\"" + tutorsCount + "\",");        
            json.append("\"course\":{");
            json.append("\"id\":\"" + g.getCourse().getId() + "\",");
            json.append("\"name\":\"" + g.getCourse().getName() + "\",");
            json.append("\"description\":\"" + g.getCourse().getDescription() + "\"");
            json.append("},");
            SystemUser coordinator = systemUserRemote.get(g.getCoordinatorId());
            json.append("\"coordinator\":{");
            json.append("\"id\":\"" + coordinator.getId() + "\",");
            json.append("\"username\":\"" + coordinator.getUsername() + "\",");
            json.append("\"email\":\"" + coordinator.getEmail() + "\"");
            json.append("},");
    //      json.append(getEnrollmentsJson(g.getId()));
 //         json.append(",");
            json.append(getProfessorsJson(g.getId()));
            json.append(",");
            json.append(getTutorsJson(g.getId()));
            json.append("}");
        json.append("}");
 
        setInputStream(new ByteArrayInputStream(json.toString().getBytes()));
        return "json";
    }

    public String getProfessorsInfo() {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("systemUser", SystemUser.class);
        xStream.omitField(SystemUser.class, "profile");
        xStream.omitField(SystemUser.class, "lastUnitContent");
        xStream.omitField(SystemUser.class, "lastGrade");
        xStream.omitField(SystemUser.class, "authentications");
        xStream.omitField(SystemUser.class, "functionalities");

        List<SystemUser> result = gradeRemote.getProfessors(grade.getId());
        setInputStream(new ByteArrayInputStream(xStream.toXML(result).getBytes()));
        return "json";
    }

    public String getStudentsInfo() {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("systemUser", SystemUser.class);
        xStream.omitField(SystemUser.class, "profile");
        xStream.omitField(SystemUser.class, "lastUnitContent");
        xStream.omitField(SystemUser.class, "lastGrade");
        xStream.omitField(SystemUser.class, "authentications");
        xStream.omitField(SystemUser.class, "functionalities");

        List<Enrollment> enroll = enrollmentRemote.getByGrade(grade.getId());
        Set<SystemUser> resultSet = new HashSet<SystemUser>();
        for (Enrollment e : enroll) {
            resultSet.add(e.getSystemUser());
        }
        List<SystemUser> result = new ArrayList<SystemUser>(resultSet);
        setInputStream(new ByteArrayInputStream(xStream.toXML(result).getBytes()));
        return "json";
    }

    public String getTutorsInfo() {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("systemUser", SystemUser.class);
        xStream.omitField(SystemUser.class, "profile");
        xStream.omitField(SystemUser.class, "lastUnitContent");
        xStream.omitField(SystemUser.class, "lastGrade");
        xStream.omitField(SystemUser.class, "authentications");
        xStream.omitField(SystemUser.class, "functionalities");

        List<SystemUser> result = gradeRemote.getTutors(grade.getId());
        setInputStream(new ByteArrayInputStream(xStream.toXML(result).getBytes()));
        return "json";
    }

    public String addGrade() {
        //validates the add
        //performValidateAdd();
        //grade.setCoordinator(systemUserRemote.get(grade.getCoordinatorId()));
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = sdf.parse(getStartDate());
            endDate = sdf.parse(getEndDate());
            grade.setStartDatetime(startDate);
            grade.setEndDatetime(endDate);
        } catch (ParseException ex) {
            Logger.getLogger(GradeAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        if (!hasActionErrors()) {
            grade.setRequiresEnrollmentValidation(new Boolean(requires));
            grade.setStatus(Integer.parseInt(status));
            Long id = gradeRemote.add(grade);
            grade = gradeRemote.get(id);
            try {
                Forum forum = new Forum();
                forum.setTitle(grade.getName());                                               
                forum.setCourse(course);
                forum.setGrade(grade);
                forum.setCreatedBy(getAuthenticatedUser());
                Long result = forumRemote.add(forum);
                if (result == null) {
                    log.warn("Forum Has not been saved for grade: "
                            + grade.getName() + "|" + grade.getId());
                }
            } catch (Exception e) {
                // Does not Cancel the Transaction if the Forum creation Fails.
                // An admin may create the Forum later in case of errors.
                log.error("Forum Creation Failed for course: "
                        + grade.getName() + "|" + grade.getId(), e);                
            }
            xStream.alias("grade", Grade.class);
            String json = xStream.toXML(grade);
            setInputStream(new ByteArrayInputStream(json.getBytes()));
        } else {
            Grade gradeTemp = new Grade();
            xStream.alias("grade", Grade.class);
            String json = xStream.toXML(gradeTemp);
            setInputStream(new ByteArrayInputStream(json.getBytes()));
        }
        return "json";
    }

    public String updateGrade() {
        //performValidateAdd();
        //grade.setCoordinator(systemUserRemote.get(grade.getCoordinatorId()));
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = sdf.parse(getStartDate());
            endDate = sdf.parse(getEndDate());
            grade.setStartDatetime(startDate);
            grade.setEndDatetime(endDate);
        } catch (ParseException ex) {
            log.error("Update Grade", ex);            
        }
        
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        if (!hasActionErrors()) {
            grade.setStatus(Integer.parseInt(status));
            grade.setRequiresEnrollmentValidation(new Boolean(requires));
            boolean result = gradeRemote.update(grade);
            grade = gradeRemote.get(grade.getId());
            xStream.alias("grade", Grade.class);
            String json = xStream.toXML(grade);
            setInputStream(new ByteArrayInputStream(json.getBytes()));
        } else {
            Grade gradeTemp = new Grade();
            xStream.alias("grade", Grade.class);
            String json = xStream.toXML(gradeTemp);
            setInputStream(new ByteArrayInputStream(json.getBytes()));
        }
        return "json";
    }

    public String updateStatusGrade() {

        String json;
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("update", boolean.class);

        Grade g = gradeRemote.get(grade.getId());
        g.setStatus(Integer.parseInt(status));
        if (gradeRemote.update(g)) {
            json = xStream.toXML(true);
        } else {
            json = xStream.toXML(false);
        }

        setInputStream(new ByteArrayInputStream(json.getBytes()));

        return "json";
    }

    public String removeGrade() {
        grade = gradeRemote.get(grade.getId());
        grade.getEnrollments();
        grade.setEnrollments(enrollmentRemote.getByGrade(grade.getId()));
        grade.setForums(forumRemote.getForumListByGrade(grade.getId()));
        //grade.setDisciplines(disciplineRemote.getByCourse(course.getId()));
        //performValidateRemove();
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        if (!hasActionErrors()) {
            if (grade.getForums() != null) {                                
                for (Forum forumToBeRemoved : grade.getForums()) {
                    forumRemote.remove(forumToBeRemoved.getId());                    
                }
            }
            boolean result = gradeRemote.remove(grade);            
            xStream.alias("boolean", Boolean.class);
            String json = xStream.toXML(new Boolean(result));
            json = json.replaceAll("boolean", "result");
            setInputStream(new ByteArrayInputStream(json.getBytes()));
        } else {
            xStream.alias("boolean", Boolean.class);
            String json = xStream.toXML(new Boolean(false));
            json = json.replaceAll("boolean", "result");
            setInputStream(new ByteArrayInputStream(json.getBytes()));
        }
        return "json";
    }

    /**
     * edit a grade
     * @return a string 
     */
    public String edit() {
        Grade g = gradeRemote.get(grade.getId());
        setGrade(g);
        return "edit";
    }

    /**
     * Update a grade
     * @return a list of grades
     */
    public String update() {
        gradeRemote.update(grade);
        return list();
    }

    /**
     * Remove a grade
     * @return a list of grades
     */
    public String remove() {
        gradeRemote.remove(grade.getId());
        return list();
    }

    public String sendMessageGrade() {
        grade = gradeRemote.get(grade.getId());
        List<Enrollment> enrollments = enrollmentRemote.getByGrade(grade.getId());
        boolean result = true;
        for (Enrollment e : enrollments) {
            Message message = new Message();
            message.setTitle(getTitle());
            message.setDescription(getDescription());
            message.setRead(false);
            message.setDatetime(new Date());
            message.setSender(getAuthenticatedUser());
            message.setRecipient(e.getSystemUser());
            if (messageRemote.add(message) == null) {
                result = false;
            }
        }
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("boolean", Boolean.class);
        String json = xStream.toXML(new Boolean(result));
        json = json.replaceAll("boolean", "result");
        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }

    public String sendMessageGrades() {
        String[] grades = gradeIds.split(";");
        if (grades == null) {
            grades = new String[]{gradeIds};
        }
        boolean result = true;
        for (int i = 0; grades != null && i < grades.length; i++) {
            grade = gradeRemote.get(new Long(grades[i]));
            List<Enrollment> enrollments = enrollmentRemote.getByGrade(grade.getId());
            for (Enrollment e : enrollments) {
                Message message = new Message();
                message.setTitle(getTitle());
                message.setDescription(getDescription());
                message.setRead(false);
                message.setDatetime(new Date());
                message.setSender(getAuthenticatedUser());
                message.setRecipient(e.getSystemUser());
                if (messageRemote.add(message) == null) {
                    result = false;
                }
            }
        }
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("boolean", Boolean.class);
        String json = xStream.toXML(new Boolean(result));
        json = json.replaceAll("boolean", "result");
        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }

    public String sendNewsFlashGrades() {
        String[] grades = gradeIds.split(";");
        if (grades == null) {
            grades = new String[]{gradeIds};
        }
        boolean result = true;
        for (int i = 0; grades != null && i < grades.length; i++) {
            grade = gradeRemote.get(new Long(grades[i]));
            List<Enrollment> enrollments = enrollmentRemote.getByGrade(grade.getId());
            for (Enrollment e : enrollments) {
                NewsFlash newsFlash = new NewsFlash();
                newsFlash.setRead(false);
                newsFlash.setMessage(message);
                newsFlash.setSenderId(getAuthenticatedUser().getId());
                newsFlash.setSender(getAuthenticatedUser());
                newsFlash.setReceiverId(e.getSystemUser().getId());
                newsFlash.setReceiver(e.getSystemUser());
                if (newsFlashRemote.addNewsFlash(newsFlash) == null) {
                    result = false;
                }
            }
        }
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("boolean", Boolean.class);
        String json = xStream.toXML(new Boolean(result));
        json = json.replaceAll("boolean", "result");
        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }

    
    public String getGraduatedStudents(){
        int tResult = 0;
        String json ="";
        List<Enrollment> list = enrollmentRemote.getByGrade(grade.getId());
        ThreadToResolve[] workers = new ThreadToResolve[list.size()];
        
         for(int i=0;i<list.size();i++){
            workers[i] = new ThreadToResolve();
            workers[i].courseId = grade.getCourseId();
            workers[i].gradeId = grade.getId();
            workers[i].studentId = list.get(i).getSystemUser().getId();
            workers[i].courseRemote = courseRemote;
            workers[i].setPriority(Thread.MAX_PRIORITY);
            workers[i].start();
        }
        
        for(int i=0;i<list.size();i++){
            try {
                workers[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(SystemUserAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        for(int i=0;i<list.size();i++){
            if(workers[i].res == 10)
                tResult++;
        }
        
        
        json = "{\"result\":"+tResult+"}";
        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }
    
    public String sendNewsFlashGrade() {
        grade = gradeRemote.get(grade.getId());
        List<Enrollment> enrollments = enrollmentRemote.getByGrade(grade.getId());
        boolean result = true;
        for (Enrollment e : enrollments) {
            NewsFlash newsFlash = new NewsFlash();
            newsFlash.setRead(false);
            newsFlash.setMessage(message);
            newsFlash.setSenderId(getAuthenticatedUser().getId());
            newsFlash.setSender(getAuthenticatedUser());
            newsFlash.setReceiverId(e.getSystemUser().getId());
            newsFlash.setReceiver(e.getSystemUser());
            if (newsFlashRemote.addNewsFlash(newsFlash) == null) {
                result = false;
            }
        }
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("boolean", Boolean.class);
        String json = xStream.toXML(new Boolean(result));
        json = json.replaceAll("boolean", "result");
        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }

    public String sendMessageUsers() {
        boolean result = true;
        String[] ids = getSystemUserIds().split(";");
        for (int i = 0; ids != null && i < ids.length; i++) {
            systemUser = systemUserRemote.get(new Long(ids[i]));
            Message message = new Message();
            message.setTitle(getTitle());
            message.setDescription(getDescription());
            message.setRead(false);
            message.setDatetime(new Date());
            message.setSender(getAuthenticatedUser());
            message.setRecipient(systemUser);
            if (messageRemote.add(message) == null) {
                result = false;
            }
        }
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("boolean", Boolean.class);
        String json = xStream.toXML(new Boolean(result));
        json = json.replaceAll("boolean", "result");
        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }

    public String sendNoteUsers() {
        boolean result = true;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSS");
        String[] ids = getSystemUserIds().split(";");
        for (int i = 0; ids != null && i < ids.length; i++) {
            systemUser = systemUserRemote.get(new Long(ids[i]));
            Date dtI = null;
            Date dtF = null;
            try {
                dtI = sdf.parse(getDtStart());
                dtF = sdf.parse(getDtEnd());
            } catch (ParseException ex) {
                Logger.getLogger(GradeAction.class.getName()).log(Level.SEVERE, null, ex);
            }
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
            log.debug("porta" + request.getLocalPort());
            log.debug("ip" + request.getRequestURI());
            log.debug("ip" + request.getRemotePort());
            log.debug("ip" + request.getRemoteAddr());
            result = calendarRemote.addEvent(request.getLocalAddr(), new Integer(request.getLocalPort()).toString(), systemUser.getUsername(), getDescription(), dtI, dtF, getWhere(), getWhat());
        }
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("boolean", Boolean.class);
        String json = xStream.toXML(new Boolean(result));
        json = json.replaceAll("boolean", "result");
        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }

    public String sendNewsFlashUsers() {
        boolean result = true;
        String[] ids = getSystemUserIds().split(";");
        for (int i = 0; ids != null && i < ids.length; i++) {
            systemUser = systemUserRemote.get(new Long(ids[i]));
            NewsFlash newsFlash = new NewsFlash();
            newsFlash.setRead(false);
            newsFlash.setMessage(message);
            newsFlash.setSenderId(getAuthenticatedUser().getId());
            newsFlash.setSender(getAuthenticatedUser());
            newsFlash.setReceiverId(systemUser.getId());
            newsFlash.setReceiver(systemUser);
            if (newsFlashRemote.addNewsFlash(newsFlash) == null) {
                result = false;
            }
        }
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("boolean", Boolean.class);
        String json = xStream.toXML(new Boolean(result));
        json = json.replaceAll("boolean", "result");
        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }

    public String setEnrollments() {
        boolean result = true;
        String[] ids = getSystemUserIds().split(";");
        if (ids == null) {
            ids = new String[]{getSystemUserIds()};
        }
        for (int i = 0; i < ids.length; i++) {
            List<Enrollment> enrollments = enrollmentRemote.getByUser(new Long(ids[i]));
            for (Enrollment e : enrollments) {
                if (e.getGrade().getId().longValue() == grade.getId().longValue()) {
                    e.setStatus(Integer.parseInt(getStatus()));
                    if (e.getGrade().getStatus() == Constants.GRADE_PERIOD_OF_ENROLLMENT && e.getStatus() == Constants.ENROLLMENT_SUSPENDED) {
                        enrollmentRemote.remove(e.getId());
                    } else {
                        if (!enrollmentRemote.update(e)) {
                            result = false;
                        }
                    }
                }
            }
        }
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("boolean", Boolean.class);
        String json = xStream.toXML(new Boolean(result));
        json = json.replaceAll("boolean", "result");
        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }

    public String statusToText(int status) {
        text = "";
        switch (status) {
            case 0:
                text = getText("grade.status.inactive");
                break;
            case 1:
                text = getText("grade.status.periodOfEnrollment");
                break;
            case 2:
                text = getText("grade.status.finished");
                break;
            case 3:
                text = getText("grade.status.inProgress");
                break;
        }
        return text;
    }
    
    /**
     * Retrieves a system user
     * @return systemUser
     */
    public SystemUser getSystemUser() {
        return systemUser;
    }

    /**
     * Sets a system user
     * @param systemUser
     */
    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    /**
     * Retrieves a remote system user
     * @return
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
     * Retrieves a remote repository
     * @return repositoryRemote
     */
    public RepositoryRemote getRepositoryRemote() {
        return repositoryRemote;
    }

    /**
     * Sets a remote repository
     * @param repositoryRemote
     */
    public void setRepositoryRemote(RepositoryRemote repositoryRemote) {
        this.repositoryRemote = repositoryRemote;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CalendarRemote getCalendarRemote() {
        return calendarRemote;
    }

    public void setCalendarRemote(CalendarRemote calendarRemote) {
        this.calendarRemote = calendarRemote;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public String getDtEnd() {
        return dtEnd;
    }

    public void setDtEnd(String dtEnd) {
        this.dtEnd = dtEnd;
    }

    public String getDtStart() {
        return dtStart;
    }

    public void setDtStart(String dtStart) {
        this.dtStart = dtStart;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getGradeIds() {
        return gradeIds;
    }
    

    public void setGradeIds(String gradeIds) {
        this.gradeIds = gradeIds;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSystemUserIds() {
        return systemUserIds;
    }

    public void setSystemUserIds(String systemUserIds) {
        this.systemUserIds = systemUserIds;
    }

    public NewsFlashRemote getNewsFlashRemote() {
        return newsFlashRemote;
    }

    public void setNewsFlashRemote(NewsFlashRemote newsFlashRemote) {
        this.newsFlashRemote = newsFlashRemote;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MessageRemote getMessageRemote() {
        return messageRemote;
    }

    public void setMessageRemote(MessageRemote messageRemote) {
        this.messageRemote = messageRemote;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public List<SystemUser> getProfessorList() {
        return professorList;
    }

    public void setProfessorList(List<SystemUser> professorList) {
        this.professorList = professorList;
    }

    public List<SystemUser> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<SystemUser> studentList) {
        this.studentList = studentList;
    }

    public List<SystemUser> getTutorList() {
        return tutorList;
    }

    public void setTutorList(List<SystemUser> tutorList) {
        this.tutorList = tutorList;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public List<SystemUser> getCoordinatorList() {
        return coordinatorList;
    }

    public void setCoordinatorList(List<SystemUser> coordinatorList) {
        this.coordinatorList = coordinatorList;
    }

    public String getRequires() {
        return requires;
    }

    public void setRequires(String requires) {
        this.requires = requires;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    /**
     * Retrieve the possible Grade Status Value properly translated.
     * 
     * @return A Map with the options for the Grade Status
     */
    public Map<Integer, String> getGradeStatusList() {
        Map<Integer, String> gradeStatusList = new LinkedHashMap<Integer, String>();
        gradeStatusList.put(0, getText("grade.input.inactive", "Inactive"));
        gradeStatusList.put(1, getText("grade.input.periodOfEnrollment", "Period of enrollment"));
        gradeStatusList.put(2, getText("grade.input.finished", "Registration finished"));
        
        return gradeStatusList;
    }
}


