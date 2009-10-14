/*  
#############################################################################################
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
# File: CourseAction.java                                                                   #
# Document: Course Action                                                                   # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 07-JAN-2009 - Maristella Myrian (UFC)           - XXXXXX - Initial Version                #
# 02-SEP-2009 - Rafael Lagôa (Instituto Eldorado) - 000016 - Fix showChat action            #
#############################################################################################
*/

package br.ufc.ivela.web.action.admin;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.util.StringUtils;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.ContentPackageUtils;
import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.Discipline;
import br.ufc.ivela.commons.model.Enrollment;
import br.ufc.ivela.commons.model.Forum;
import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.ejb.interfaces.DisciplineRemote;
import br.ufc.ivela.ejb.interfaces.ForumRemote;
import br.ufc.ivela.ejb.interfaces.RepositoryRemote;
import br.ufc.ivela.web.action.CourseAwareAction;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

public class CourseAction extends CourseAwareAction {
    
    private RepositoryRemote repositoryRemote;
    private DisciplineRemote disciplineRemote;
    private Discipline discipline;    
    private ForumRemote forumRemote;        
    private InputStream inputStream;
    private String message;
    private java.io.File upload;
    private String uploadFileName;
    private String nick = "";
    private String chatRoomName = "";
    private String teacherName;
    private Long chatUId;
    private long courseId;
    private long disciplineId;

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public ForumRemote getForumRemote() {
        return forumRemote;
    }

    public void setForumRemote(ForumRemote forumRemote) {
        this.forumRemote = forumRemote;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public DisciplineRemote getDisciplineRemote() {
        return disciplineRemote;
    }

    public void setDisciplineRemote(DisciplineRemote disciplineRemote) {
        this.disciplineRemote = disciplineRemote;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public void setCourseId(Long id) {
        this.courseId = id;
    }

    public void setDisciplineId(Long id) {
        this.disciplineId = id;
    }

    /**
     * Add a new Course, perform validation
     * if hasn't errors add a new course
     * if has, return a message of error
     */
    public String add() {
        //validates the add
        performValidateAdd();
        if (!hasActionErrors()) {
            course.setActive(true);
            course.setRepositoryStructure(repositoryRemote.getInitialStructure());
            courseRemote.add(course);
            return list();
        }
        addActionError("Unable to add a new course");
        return list();
    }

    /**
     * Add a new course, performs a validate to the add method, 
     * if it does not have errors. if it has error return an error message
     * @return
     */
    public String addCourse() {
        //validates the add
        performValidateAdd();
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        if (!hasActionErrors()) {
            course.setActive(true);
            course.setRepositoryStructure(repositoryRemote.getInitialStructure());
            Long id = courseRemote.add(course);
            course = courseRemote.get(id);
            try {
                Forum forum = new Forum();
                forum.setTitle(course.getName());                
                forum.setPublic1(true);                
                forum.setCourse(course);                    
                forum.setCreatedBy(getAuthenticatedUser());
                Long result = forumRemote.add(forum);
                if (result == null) {
                    log.warn("Forum Has not been saved for course: "
                            + course.getName() + "|" + course.getId());
                }
            } catch (Exception e) {
                // Does not Cancel the Transaction if the Forum creation Fails.
                // An admin may create the Forum later in case of errors.
                log.error("Forum Creation Failed for course: "
                        + course.getName() + "|" + course.getId(), e);                
            }
            xStream.alias("course", Course.class);
            String json = xStream.toXML(course);
            setInputStream(new ByteArrayInputStream(json.getBytes()));
        } else {
            Course courseTemp = new Course();
            xStream.alias("course", Course.class);
            String json = xStream.toXML(courseTemp);
            setInputStream(new ByteArrayInputStream(json.getBytes()));
        }
        return "json";
    }

    /**
     * Update a course, perform a validation
     * if hasn't error run a json structure
     * @return a string
     */
    public String updateCourse() {
        performValidateAdd();
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        if (!hasActionErrors()) {
            course.setActive(true);
            boolean result = courseRemote.update(course);
            course = courseRemote.get(course.getId());
            xStream.alias("course", Course.class);
            String json = xStream.toXML(course);
            setInputStream(new ByteArrayInputStream(json.getBytes()));
        } else {
            Course courseTemp = new Course();
            xStream.alias("course", Course.class);
            String json = xStream.toXML(courseTemp);
            setInputStream(new ByteArrayInputStream(json.getBytes()));
        }
        return "json";
    }

    public String updateImage() {
        java.io.File fileIo = upload;
            course = courseRemote.get(course.getId());

            if(!new File(Constants.FILE_UPLOAD_PARTNERS+course.getId()).exists()){
                ContentPackageUtils.createDir(new File(Constants.FILE_UPLOAD_PARTNERS), course.getId().toString());
            }
            course.setImage(Constants.FILE_UPLOAD_PARTNERS + course.getId() + "/" + uploadFileName);
            boolean result = courseRemote.update(course);
            log.info("file" + course.getImage());
            if (fileIo != null && uploadFileName != null && uploadFileName.trim().length() > 0)
                courseRemote.savePhoto(course, fileIo);
        return "courses";
    }    

    /**
     * Remove a course, perform a validation on the method,
     * if hasn't error return a list of the course
     */
    public String remove() {
        performValidateRemove();
        if (!hasActionErrors()) {
            course.setActive(false);
            //courseRemote.remove(course.getId());
            courseRemote.update(course);
            return list();
        }
        addActionError("Unable to remove a course");
        return list();
    }

    /**
     * Remove a course, perform a validation on the method,
     * if hasn't error return a string
     * @return  a string in the json format
     */
    public String removeCourse() {
        course = courseRemote.get(course.getId());
        course.setActive(false);
        //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        //course.setDisciplines(disciplineRemote.getByCourse(course.getId()));
        performValidateRemove();
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        if (!hasActionErrors()) {
            //boolean result = courseRemote.remove(course);
            boolean result = courseRemote.update(course);
            //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + result);
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
     * Update a course
     * @return a list of the courses
     */
    public String update() {
        course.setActive(true);
        courseRemote.update(course);
        return list();
    }

    /**
     * Edit a course
     * @return a string
     */
    public String edit() {

        Course aux = courseRemote.get(course.getId());
        setCourse(aux);
        return "edit";
    }

    /**
     * Sets the variables to be used on the input course
     */
    @Override
    public String input() {
        return INPUT;
    }

    /**
     * Show all the course of a structure
     * @return a string
     */
    public String show() {
        setMessage(getMessage());
        courseList = courseRemote.getStructure(getAuthenticatedUser());

        return "show";
    }

    public String showChat() {
        course = courseRemote.get(courseId);
        grade = gradeRemote.get(grade.getId());
        this.nick = this.getAuthenticatedUser().getUsername();
        this.chatRoomName = "#course_"+course.getName()+"_grade_"+grade.getName();
        this.teacherName = this.nick;
        return "chat";
    }

    /**
     * Retrieves a info from a course
     * @return a string in the json format
     */
    public String getCourseInfo() {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("course", Course.class);
        course = courseRemote.get(course.getId());
        setInputStream(new ByteArrayInputStream(xStream.toXML(course).getBytes()));

        return "json";
    }

    /**
     * Retrieves a info of the requisites from a course
     * @return a string in the json format
     */
    public String getRequisitesInfo() {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("course", Course.class);
        List<Course> results = courseRemote.getRequisiteByCourse(course.getId());
        setInputStream(new ByteArrayInputStream(xStream.toXML(results).getBytes()));
        return "json";
    }

    /**
     * Retrieves a info about students
     * @return a string in the json format
     */
    public String getStudentsInfo() {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        Integer result = courseRemote.getStudentsCount(course.getId());
        String json = xStream.toXML(result);

        json = json.replaceAll("int", "count");

        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }

    /**
     * Retrieves a info about a grades
     * @return a string in the json format
     */
    public String getGradesInfo() {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        Integer result = courseRemote.getGradesCount(course.getId());

        String json = xStream.toXML(result);

        json = json.replaceAll("int", "count");

        setInputStream(new ByteArrayInputStream(json.getBytes()));

        return "json";
    }

    /**
     * Retrieves a info about graduated students
     * @return a string in the json format
     */
    public String getGraduatedStudentsInfo() {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        Integer result = courseRemote.getGraduatedStudentsCount(course.getId());

        String json = xStream.toXML(result);

        json = json.replaceAll("int", "count");

        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }
    
     public String getGraduatedStudents(){
        int tResult = 0;
        String json ="";
        List<Grade> l = (List<Grade>) gradeRemote.getByCourse(course.getId());
         for (Grade grade : l) {
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
        
            
         }
        json = "{\"result\":"+tResult+"}";
        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }

    /**
     * Retrieves a info about the professores
     * @return a string in the json format
     */
    public String getProfessorsInfo() {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("systemUser", SystemUser.class);
        xStream.omitField(SystemUser.class, "profile");
        xStream.omitField(SystemUser.class, "lastUnitContent");
        xStream.omitField(SystemUser.class, "lastGrade");
        xStream.omitField(SystemUser.class, "authentications");
        xStream.omitField(SystemUser.class, "functionalities");

        List<SystemUser> result = courseRemote.getProfessors(course.getId());
        setInputStream(new ByteArrayInputStream(xStream.toXML(result).getBytes()));
        return "json";
    }

    /**
     *  Retrieves a info about tutors
     * @return a string in the json format
     */
    public String getTutorsInfo() {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("systemUser", SystemUser.class);
        xStream.omitField(SystemUser.class, "profile");
        xStream.omitField(SystemUser.class, "lastUnitContent");
        xStream.omitField(SystemUser.class, "lastGrade");
        xStream.omitField(SystemUser.class, "authentications");
        xStream.omitField(SystemUser.class, "functionalities");

        List<SystemUser> result = courseRemote.getTutors(course.getId());
        setInputStream(new ByteArrayInputStream(xStream.toXML(result).getBytes()));
        return "json";
    }

    /**
     * Retrieves a info about disciplines
     * @return a string in the json format
     */
    public String getDisciplinesInfo() {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("discipline", Discipline.class);
        List<Discipline> result = courseRemote.getDisciplines(course.getId());
        setInputStream(new ByteArrayInputStream(xStream.toXML(result).getBytes()));
        return "json";
    }
    
    public String getGrades() {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("grade", Grade.class);
        List<Grade> result = gradeRemote.getByCourse(course.getId());
        setInputStream(new ByteArrayInputStream(xStream.toXML(result).getBytes()));
        return "json";
    }

    /**
     * List all course
     * @return a list of course
     */
    public String list() {
        List<Course> courseList = courseRemote.getAll();
        setCourseList(courseList);
        return "list";
    }

    /**
     * Perform a validation in the add method
     */
    private void performValidateAdd() {
        //verifies if the course is null
        if (course == null) {
            addActionError(getText("course.input.validation"));
        }
        //verifies id the name of the course is empty
        if (!StringUtils.hasText(course.getName())) {
            addActionError(getText("course.input.validation.name"));
        }
        if (!StringUtils.hasText(course.getDescription())) {
            addActionError(getText("course.input.validation.description"));
        }
        if (!StringUtils.hasText(course.getTargetAudience())) {
            addActionError(getText("course.input.validation.targetAudience"));
        }

        if (!StringUtils.hasText(course.getContents())) {
            addActionError(getText("course.input.validation.contents"));
        }
    }

    /**
     * Perform a validation in the remove method
     */
    private void performValidateRemove() {
        //verifies if there is an id
        if (course.getId() == null) {
            addActionError(getText("course.input.validation.requiredId"));
        } else {
            //verifies if the id is valid
            if (courseRemote.get(course.getId()) == null) {
                addActionError(getText("course.input.validation.invalidId"));
            }
        }

    }

    /**
     * Retrieves a input stream
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

    public RepositoryRemote getRepositoryRemote() {
        return repositoryRemote;
    }

    public void setRepositoryRemote(RepositoryRemote repositoryRemote) {
        this.repositoryRemote = repositoryRemote;
    }
    
    public String getCourseInfoJson() {
        course = courseRemote.get(course.getId());
        List<Discipline> disciplines = disciplineRemote.getByCourse(course.getId());
        List<SystemUser> professors = courseRemote.getProfessors(course.getId());
        List<SystemUser> tutors = courseRemote.getTutors(course.getId());
        List<Course> requisites = courseRemote.getRequisiteByCourse(course.getId());
        List<Grade> grades = gradeRemote.getByCourse(course.getId());
        String studentsCount = String.valueOf(courseRemote.getStudentsCount(course.getId()));
        String gradesCount = String.valueOf(courseRemote.getGradesCount(course.getId()));
        String graduatedStudentCount = String.valueOf(courseRemote.getGraduatedStudentsCount(course.getId()));
        Boolean uploadPackageEnabled = course.getUploadPackageEnabled();                        
        Boolean customToc = course.getCustomToc();
        Integer challengeRetries = course.getChallengeRetries();
        Integer challengeCount = course.getChallengeCount();
        Integer challengeWeight = course.getChallengeWeight();
        StringBuilder json = new StringBuilder();
        json.append("{");
            json.append("\"course\":{");
                json.append("\"id\":\"" + course.getId() + "\",");
                json.append("\"name\":\"" + course.getName() + "\",");
                json.append("\"description\":\"" + course.getDescription() + "\",");
                json.append("\"targetAudience\":\"" + course.getTargetAudience() + "\",");
                json.append("\"image\":\"" + course.getImage() + "\",");
                json.append("\"uploadPackageEnabled\":\"" + uploadPackageEnabled + "\",");                
                json.append("\"uploadPackageEnabled\":\"" + uploadPackageEnabled + "\",");                
                json.append("\"customToc\":\"" + customToc + "\",");
                json.append("\"challengeRetries\":\"" + challengeRetries + "\",");
                json.append("\"challengeCount\":\"" + challengeCount + "\",");
                json.append("\"challengeWeight\":\"" + challengeWeight + "\",");
                json.append("\"studentsCount\":\"" + studentsCount + "\",");
                json.append("\"gradesCount\":\"" + gradesCount + "\",");
                json.append("\"graduatedStudentCount\":\"" + graduatedStudentCount + "\",");
                json.append("\"disciplines\":[");
                    for (Discipline d : disciplines) {
                    json.append("{");        
                        json.append("\"id\":\"" + d.getId() + "\",");
                        json.append("\"name\":\"" + d.getName() + "\"");
                    json.append("},");
                    }
                    if (json.substring(json.length() - 1).equals(","))
                        json = new StringBuilder(json.substring(0, json.length() - 1));               
                json.append("],");
                json.append("\"professors\":[");
                    for (SystemUser su : professors) {
                    json.append("{");        
                        json.append("\"id\":\"" + su.getId() + "\",");
                        json.append("\"name\":\"" + su.getUsername() + "\"");
                    json.append("},");
                    }
                    if (json.substring(json.length() - 1).equals(","))
                        json = new StringBuilder(json.substring(0, json.length() - 1));    
                json.append("],");  
                json.append("\"tutors\":[");
                    for (SystemUser su : tutors) {
                    json.append("{");        
                        json.append("\"id\":\"" + su.getId() + "\",");
                        json.append("\"name\":\"" + su.getUsername() + "\"");
                    json.append("},");
                    }
                    if (json.substring(json.length() - 1).equals(","))
                        json = new StringBuilder(json.substring(0, json.length() - 1));
                json.append("],");         
                json.append("\"requisites\":[");
                    for (Course c : requisites) {
                    json.append("{");        
                        json.append("\"id\":\"" + c.getId() + "\",");
                        json.append("\"name\":\"" + c.getName() + "\"");
                    json.append("},");
                    }
                    if (json.substring(json.length() - 1).equals(","))
                        json = new StringBuilder(json.substring(0, json.length() - 1));                    
                json.append("]");                                
            json.append("}");
        json.append("}");
        setInputStream(new ByteArrayInputStream(json.toString().getBytes()));
        return "json";
    }
    
    public Long getChatUId() {
        return chatUId;
    }

    public void setChatUId(Long chatUId) {
        this.chatUId = chatUId;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getChatRoomName() {
        return chatRoomName;
    }

    public void setChatRoomName(String chatRoomName) {
        this.chatRoomName = chatRoomName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    
    
}