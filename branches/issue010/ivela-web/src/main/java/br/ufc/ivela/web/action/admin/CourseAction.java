/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.web.action.admin;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.ContentPackageUtils;
import br.ufc.ivela.web.action.*;
import org.springframework.util.StringUtils;
import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.Discipline;
import br.ufc.ivela.commons.model.Enrollment;
import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.ejb.interfaces.CourseRemote;
import br.ufc.ivela.ejb.interfaces.DisciplineRemote;
import br.ufc.ivela.ejb.interfaces.EnrollmentRemote;
import br.ufc.ivela.ejb.interfaces.GradeRemote;
import br.ufc.ivela.ejb.interfaces.RepositoryRemote;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maristella Myrian
 */
public class CourseAction extends GenericAction {

    private CourseRemote courseRemote;
    private RepositoryRemote repositoryRemote;
    private DisciplineRemote disciplineRemote;
    private EnrollmentRemote enrollmentRemote;
    private GradeRemote gradeRemote;
    private Course course;
    private List<Course> courseList;
    private InputStream inputStream;
    private String message;
    private java.io.File upload;
    private String uploadFileName;
    private String nick = "";
    private String chatRoomName = "";
    private String teacherName;
    private Long chatUId;

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
    
    public DisciplineRemote getDisciplineRemote() {
        return disciplineRemote;
    }

    public void setDisciplineRemote(DisciplineRemote disciplineRemote) {
        this.disciplineRemote = disciplineRemote;
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
        //logger.log("oldPhoto: ->" + oldPhoto + "<-");
        //logger.log("upload: ->" + upload + "<-");

        //if (upload == null || upload.equals(" ")) {
            //course.setImage(oldFromSession);
        //} else {
            course = courseRemote.get(course.getId());

            if(!new File(Constants.FILE_UPLOAD_PARTNERS+course.getId()).exists()){
                ContentPackageUtils.createDir(new File(Constants.FILE_UPLOAD_PARTNERS), course.getId().toString());
            }
            course.setImage(Constants.FILE_UPLOAD_PARTNERS + course.getId() + "/" + uploadFileName);
            boolean result = courseRemote.update(course);
            logger.log("file" + course.getImage());
            if (fileIo != null && uploadFileName != null && uploadFileName.trim().length() > 0)
                courseRemote.savePhoto(course, fileIo);
        //}

        //setSucess(profileRemote.edit(profile));
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
        courseList = courseRemote.getStructure();

        return "show";
    }
    
    public String showChat(){
        this.nick = getAuthenticatedUser().getUsername();
        this.chatRoomName = "#course_"+course.getId(); 
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
     * Sets the value of course variable
     * @param course
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * Retrieves the value of course variable
     * @return course
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Retrieves the value of courseRemote variable
     * @return courseRemote
     */
    public CourseRemote getCourseRemote() {
        return courseRemote;
    }

    /**
     * Sets the value of courseRemote variable
     * @param courseRemote
     */
    public void setCourseRemote(CourseRemote courseRemote) {
        this.courseRemote = courseRemote;
    }

    /**
     * Retrieves the value of courseList variable
     * @return courseList
     */
    public List<Course> getCourseList() {
        return courseList;
    }

    /**
     * Sets the value of courseList variable
     * @param courseList
     */
    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
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
       // String graduatedStudentCount = String.valueOf(courseRemote.getGraduatedStudentsCount(course.getId()));
        StringBuilder json = new StringBuilder();
        json.append("{");
            json.append("\"course\":{");
                json.append("\"id\":\"" + course.getId() + "\",");
                json.append("\"name\":\"" + course.getName() + "\",");
                json.append("\"description\":\"" + course.getDescription() + "\",");
                json.append("\"targetAudience\":\"" + course.getTargetAudience() + "\",");
                json.append("\"image\":\"" + course.getImage() + "\",");
                json.append("\"updloadPackageEnabled\":\"" + course.getUploadPackageEnabled() + "\",");
                json.append("\"challengeItensEnabled\":\"" + course.getChallengeItensEnabled() + "\",");
                json.append("\"studentsCount\":\"" + studentsCount + "\",");
                json.append("\"gradesCount\":\"" + gradesCount + "\",");
                //json.append("\"graduatedStudentCount\":\"" + graduatedStudentCount + "\",");
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

    public EnrollmentRemote getEnrollmentRemote() {
        return enrollmentRemote;
    }

    public void setEnrollmentRemote(EnrollmentRemote enrollmentRemote) {
        this.enrollmentRemote = enrollmentRemote;
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