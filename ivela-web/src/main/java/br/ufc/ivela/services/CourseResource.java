/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.services;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.Enrollment;
import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.ejb.interfaces.CourseRemote;
import br.ufc.ivela.ejb.interfaces.EnrollmentRemote;
import br.ufc.ivela.ejb.interfaces.GradeRemote;
import br.ufc.ivela.ejb.interfaces.SystemUserRemote;
import com.thoughtworks.xstream.XStream;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.ProduceMime;

/**
 *
 * @author maristella
 */
@Path("course")
public class CourseResource {

    @Context
    private UriInfo context;
    private XStream xStream = new XStream();
    private CourseRemote courseRemote = getCourseRemote();
    private GradeRemote gradeRemote = getGradeRemote();
    private SystemUserRemote systemUserRemote = getSystemUserRemote();
    private EnrollmentRemote enrollmentRemote = getEnrollmentRemote();
    

    private CourseRemote getCourseRemote() {
        //<jee:jndi-lookup jndi-name="br.ufc.ivela.ejb.interfaces.CourseRemote"
        //id="courseRemote" expected-type="br.ufc.ivela.ejb.interfaces.CourseRemote"/>        
        try {
            InitialContext initialContext = new InitialContext();
            java.lang.Object ejbRemoteRef = initialContext.lookup("CourseBean#br.ufc.ivela.ejb.interfaces.CourseRemote");
            courseRemote = (CourseRemote) javax.rmi.PortableRemoteObject.narrow(ejbRemoteRef, CourseRemote.class);
        }
        catch (NamingException e) {
            e.printStackTrace();
            courseRemote = null;
        }
        return courseRemote;
    }
    
        private GradeRemote getGradeRemote() {
        //<jee:jndi-lookup jndi-name="br.ufc.ivela.ejb.interfaces.CourseRemote"
        //id="courseRemote" expected-type="br.ufc.ivela.ejb.interfaces.CourseRemote"/>        
        try {
            InitialContext initialContext = new InitialContext();
            java.lang.Object ejbRemoteRef = initialContext.lookup("GradeBean#br.ufc.ivela.ejb.interfaces.GradeRemote");
            gradeRemote = (GradeRemote) javax.rmi.PortableRemoteObject.narrow(ejbRemoteRef, GradeRemote.class);
        }
        catch (NamingException e) {
            e.printStackTrace();
            gradeRemote = null;
        }
        return gradeRemote;
    }
    
    private SystemUserRemote getSystemUserRemote() {
        //<jee:jndi-lookup jndi-name="br.ufc.ivela.ejb.interfaces.CourseRemote"
        //id="courseRemote" expected-type="br.ufc.ivela.ejb.interfaces.CourseRemote"/>        
        try {
            InitialContext initialContext = new InitialContext();
            java.lang.Object ejbRemoteRef = initialContext.lookup("SystemUserBean#br.ufc.ivela.ejb.interfaces.SystemUserRemote");
            systemUserRemote = (SystemUserRemote) javax.rmi.PortableRemoteObject.narrow(ejbRemoteRef, SystemUserRemote.class);
        }
        catch (NamingException e) {
            e.printStackTrace();
            systemUserRemote = null;
        }
        return systemUserRemote;
    }
    
    private EnrollmentRemote getEnrollmentRemote() {
        //<jee:jndi-lookup jndi-name="br.ufc.ivela.ejb.interfaces.CourseRemote"
        //id="courseRemote" expected-type="br.ufc.ivela.ejb.interfaces.CourseRemote"/>        
        try {
            InitialContext initialContext = new InitialContext();
            java.lang.Object ejbRemoteRef = initialContext.lookup("EnrollmentBean#br.ufc.ivela.ejb.interfaces.EnrollmentRemote");
            enrollmentRemote = (EnrollmentRemote) javax.rmi.PortableRemoteObject.narrow(ejbRemoteRef, EnrollmentRemote.class);
        }
        catch (NamingException e) {
            e.printStackTrace();
            enrollmentRemote = null;
        }
        return enrollmentRemote;
    }    

    /** Creates a new instance of CourseResource */
    public CourseResource() {
       
    }

    /**
     * retrieves a course of the database by id
     * @param id
     * @return
     */
    @Path("/get/{id}")
    @GET
    @ProduceMime("application/xml")
    public String get(@PathParam("id") String id) {
        if (id == null || "".equals(id.trim())) {
            return xStream.toXML(null);
        }
        Course course = courseRemote.get(new Long(id));
        if (course == null) {
            return xStream.toXML("");
        }
        xStream.alias("course", Course.class);
        return xStream.toXML(course);
    }
    
    /**
     * 
     */
    @Path("/enrollment/{userId}/{gradeId}")
    @GET
    @ProduceMime("application/xml")
    public String enrollment(@PathParam("userId") String userId,@PathParam("gradeId") String gradeId) {
        Enrollment enrollment;
        String xml="<enrollment>";
        List<Grade> grades = gradeRemote.getGradesByStudent(new Long(userId));
        boolean canAdd = true;
        Grade g = gradeRemote.get(new Long(gradeId));
        for (Grade gr : grades) {
            if (gr.getId().longValue() == g.getId().longValue()) {
                canAdd = false;
                break;
            }
        }
        
        if (canAdd) {
            enrollment = new Enrollment();
            enrollment.setGrade(g);
            enrollment.setStartDatetime(new Date());
            enrollment.setSystemUser(systemUserRemote.get(new Long(userId)));

            List count = enrollmentRemote.getByGrade(g.getId());

            if (count.size() < g.getMaxStudents()) {
                xml += enrollmentRemote.add(enrollment);
            } else {
                xml += "Nao existe mais vagas";
            }
            
        }
        else {
            xml += "Aluno ja matriculado";
        }
        xml += "</enrollment>";
        return xml;
    }    
    
/**
     * 
     */
    @Path("/enrollmentByLogin/{username}/{gradeId}")
    @GET
    @ProduceMime("application/xml")
    public String enrollmentByLogin(@PathParam("username") String username, @PathParam("gradeId") String gradeId) {
        Enrollment enrollment;
        String xml="<enrollment>";
        boolean canEnroll = true;
        List<SystemUser> systemUsers = getSystemUserRemote().getByUsername(username);
        if (systemUsers != null && systemUsers.size() == 1) {
            SystemUser su = systemUsers.get(0);
            List<Grade> grades = gradeRemote.getGradesByStudent(su.getId());
            Grade g = gradeRemote.get(new Long(gradeId));
            for (Grade gr : grades) {
                if (gr.getId().longValue() == g.getId().longValue()) {
                    canEnroll = false;
                    break;
                }
            }
            if (canEnroll) {
                enrollment = new Enrollment();
                enrollment.setGrade(g);
                enrollment.setStartDatetime(new Date());
                enrollment.setSystemUser(systemUserRemote.get(su.getId()));
                List count = enrollmentRemote.getByGrade(g.getId());
                if (count.size() < g.getMaxStudents()) {
                    xml += enrollmentRemote.add(enrollment);
                } else {
                    canEnroll = false;
                    //xml += "Nao existe mais vagas";
                }
            }
            else {
                canEnroll = false;
                //xml += "Aluno ja matriculado";
            }
            
        }
        xml += canEnroll;
        xml += "</enrollment>";
        return xml;
    }        
    
    
    /**
     * getProgress by course
     */
    @Path("/getProgress/{courseId}/{systemUserId}")
    @GET
    @ProduceMime("application/xml")
    public String getProgress(@PathParam("courseId") String courseId, @PathParam("systemUserId") String systemUserId) {
        if (courseId == null || "".equals(courseId.trim()) 
         || systemUserId == null || "".equals(systemUserId.trim())) {
            return xStream.toXML(null);
        }
        int progress = courseRemote.getProgress(new Long(systemUserId), new Long(courseId));
        if (progress < 0)
            progress = 0;
        if (progress > 100)
            progress = 100;
        xStream.alias("progress", Integer.class);
        return xStream.toXML(new Integer(progress));
    }
    /**
     * get courses where student is enrollment
     */

    @Path("/getCoursesXml/{login}/{password}")
    @GET
    @ProduceMime("application/xml")
    public String getCoursesXml(@PathParam("login") String login, @PathParam("password") String password) {
        if (login == null || "".equalsIgnoreCase(login.trim()) || password == null || "".equalsIgnoreCase(login.trim()))
            return xStream.toXML(null);
        List<SystemUser> systemUsers = systemUserRemote.getByUsername(login);
        if (systemUsers == null || systemUsers.size() != 1)
            return xStream.toXML(null);
        SystemUser systemUser = systemUsers.get(0);
        if (systemUser.getUsername().equalsIgnoreCase(login) && systemUser.getPassword().equalsIgnoreCase(password)) {
            List<Enrollment> enrollmentList = enrollmentRemote.getByUser(systemUser.getId());
            String xml = "";
            xml += "<enrollments>";
            for (Enrollment e : enrollmentList) {
                xml += "<enrollment>";
                    xml += "<id>" + e.getId() + "</id>";
                    xml += "<status>" + e.getStatus() + "</status>";
                    xml += "<startDatetime>" + e.getStartDatetime() + "</startDatetime>";
                    xml += "<coordinator>";
                        xml += "<id>" + e.getSystemUser().getId() + "</id>";
                        xml += "<username>" + e.getSystemUser().getUsername() + "</username>";
                    xml += "</coordinator>";
                    xml += "<grade>";
                        xml += "<id>" + e.getGrade().getId() + "</id>";
                        xml += "<name>" + e.getGrade().getName() + "</name>";
                        xml += "<period>" + e.getGrade().getPeriod() + "</period>";
                        xml += "<startDatetime>" + e.getGrade().getStartDatetime() + "</startDatetime>";
                        xml += "<endDatetime>" + e.getGrade().getEndDatetime() + "</endDatetime>";
                        xml += "<course>";
                            xml += "<id>" + e.getGrade().getCourseId() + "</id>";
                            Course c = courseRemote.get(e.getGrade().getCourseId());
                            xml += "<name>" + c.getName() + "</name>";
                            xml += "<description>" + c.getDescription() + "</description>";
                        xml += "</course>";
                    xml += "</grade>";
                xml += "</enrollment>";
                
            }
            xml += "</enrollments>";
            return xml;
        }
        else
            return "<enrollments></enrollments>";
    }

    @Path("/getCoursesJson/{login}/{password}")
    @GET
    @ProduceMime("application/json")
    public String getCoursesJson(@PathParam("login") String login, @PathParam("password") String password) {
        if (login == null || "".equalsIgnoreCase(login.trim()) || password == null || "".equalsIgnoreCase(login.trim()))
            return xStream.toXML(null);
        List<SystemUser> systemUsers = systemUserRemote.getByUsername(login);
        if (systemUsers == null || systemUsers.size() != 1)
            return xStream.toXML(null);
        SystemUser systemUser = systemUsers.get(0);
        if (systemUser.getUsername().equalsIgnoreCase(login) && systemUser.getPassword().equalsIgnoreCase(password)) {
            List<Enrollment> enrollmentList = enrollmentRemote.getByUserAndStatus(systemUser.getId(), Constants.ENROLLMENT_ACTIVE);
            String xml = "{";
            xml += "\"enrollments\":[";
            for (Enrollment e : enrollmentList) {
                xml += "{";
                    xml += "\"id:\"" + e.getId() + "\",";
                    xml += "\"status:\"" + e.getStatus() + "\",";
                    xml += "\"startDatetime:\"" + e.getStartDatetime() + "\",";
                    xml += "\"coordinator\":{";
                        xml += "\"id:\"" + e.getSystemUser().getId() + "\",";
                        xml += "\"username:\"" + e.getSystemUser().getUsername() + "\"";
                    xml += "},";
                    xml += "\"grade\":{";
                        xml += "\"id\":\"" + e.getGrade().getId() + "\",";
                        xml += "\"name\":\"" + e.getGrade().getName() + "\",";
                        xml += "\"period\":\"" + e.getGrade().getPeriod() + "\",";
                        xml += "\"startDatetime\":\"" + e.getGrade().getStartDatetime() + "\",";
                        xml += "\"endDatetime\":\"" + e.getGrade().getEndDatetime() + "\",";
                        xml += "\"course\":{";
                            xml += "\"id\":\"" + e.getGrade().getCourseId() + "\",";
                            Course c = courseRemote.get(e.getGrade().getCourseId());
                            xml += "\"name\":\"" + c.getName() + "\",";
                            xml += "\"description\":\"" + c.getDescription() + "\"";
                        xml += "}";
                    xml += "}";
                xml += "},";
            }
            xml = xml.substring(0, xml.length() - 1);
            xml += "]";
            xml += "}";
            return xml;
        }
        else
            return "<enrollments></enrollments>";
    }
    
    /**
     * get courses all courses
     */
    
    @Path("/getCoursesAvailableXml")
    @GET
    @ProduceMime("application/xml")
    public String getCoursesAvailableXml() {
        String xml = "";
        List<Course> courses = courseRemote.getAll();
         xml += "<courses>";
        for(Course course: courses){
            if(course.getActive()){
                xml += "<course>";
                xml += "<id>"+ course.getId() +"</id>";
                xml += "<name>"+ course.getName() +"</name>";
                xml += "<content>"+ Util.treatValue(course.getContents()) +"</content>";
                xml += "<description>"+ Util.treatValue(course.getDescription()) +"</description>";
                xml += "<audience>"+ Util.treatValue(course.getTargetAudience())+"</audience>";
                xml += "</course>";
            }
           
        }
         xml += "</courses>";
        return xml;
        
    }
    
    @Path("/getGradesAvailableXml")
    @GET
    @ProduceMime("application/xml")
    public String getGradesAvailableXml() {
        String xml = "";
        List<Grade> grades = gradeRemote.getByStatus(Constants.GRADE_PERIOD_OF_ENROLLMENT);
         xml += "<grades>";
        for(Grade grade : grades){
            Course course = courseRemote.get(grade.getCourseId());
            if(course.getActive()) {
                xml +="<grade>";
                xml += "<course>";
                    xml += "<id>"+ course.getId() +"</id>";
                    xml += "<name>"+ course.getName() +"</name>";
                    xml += "<content>"+ Util.treatValue(course.getContents()) +"</content>";
                    xml += "<description>"+ Util.treatValue(course.getDescription()) +"</description>";
                    xml += "<audience>"+ Util.treatValue(course.getTargetAudience())+"</audience>";
                xml += "</course>";
                xml +="<id>" + grade.getId() + "</id>";
                xml += "<name>" + grade.getName() + "</name>";
                xml += "<period>" + grade.getPeriod() + "</period>";
                xml += "<maxStudents>" + grade.getMaxStudents() + "</maxStudents>";
                xml += "<startDatetime>" + grade.getStartDatetime() + "</startDatetime>";
                xml += "<endDatetime>" + grade.getEndDatetime() + "</endDatetime>";
                xml += "<status>" + grade.getStatus() + "</status>";
                xml += "<coordinator>";
                    xml += "<id>"+ grade.getCoordinatorId() + "</id>";
                    xml += "<name>"+ getSystemUserRemote().get(grade.getCoordinatorId()).getUsername() + "</name>";
                xml += "</coordinator>";
                xml += "</grade>";
            }
        }
        xml += "</grades>";
        return xml;
        
    }
    
    /**
     * get grades available  for enrollment by user
     */
    
    @Path("/getGradesAvailableEnrollmentXml/{userId}")
    @GET
    @ProduceMime("application/xml")
    public String getGradesAvailableEnrollmentXml(@PathParam("userId") String userId) {
        String xml = "";
        List<Grade> grades = gradeRemote.getByStatus(Constants.GRADE_PERIOD_OF_ENROLLMENT);
         xml += "<grades>";
        for(Grade grade : grades){
            Course course = courseRemote.get(grade.getCourseId());
            List<Grade> yourGradeByCourse = gradeRemote.getGradesInProgressAndEnrolled(new Long(userId), course.getId());
            if(yourGradeByCourse.isEmpty() && course.getActive()){
                xml +="<grade>";
                xml += "<course>";
                    xml += "<id>"+ course.getId() +"</id>";
                    xml += "<name>"+ course.getName() +"</name>";
                    xml += "<content>"+ Util.treatValue(course.getContents()) +"</content>";
                    xml += "<description>"+ Util.treatValue(course.getDescription()) +"</description>";
                    xml += "<audience>"+ Util.treatValue(course.getTargetAudience())+"</audience>";
                xml += "</course>";
                xml +="<id>"+grade.getId()+"</id>";
                xml += "<name>"+grade.getName()+"</name>";
                xml += "<maxStudents>"+grade.getMaxStudents()+"</maxStudents>";
                xml += "<startDatetime>"+grade.getStartDatetime()+"</startDatetime>";
                xml += "<endDatetime>"+grade.getEndDatetime()+"</endDatetime>";
                xml +="</grade>";
                
            }
           
        }
         xml += "</grades>";
        return xml;
        
    }
    
    
    @Path("/getByGrade/{gradeId}")
    @GET
    @ProduceMime("application/xml")
    public String getByGrade(@PathParam("gradeId") String gradeId) {
        String xml = "";
        xml += "<grades>";
        if (gradeId != null) {
            Grade grade = gradeRemote.get(new Long(gradeId));
            if (grade != null) {
                Course course = courseRemote.get(grade.getCourseId());
                xml +="<grade>";
                xml += "<course>";
                    xml += "<id>"+ course.getId() +"</id>";
                    xml += "<name>"+ course.getName() +"</name>";
                    xml += "<content>"+ Util.treatValue(course.getContents()) +"</content>";
                    xml += "<description>"+ Util.treatValue(course.getDescription()) +"</description>";
                    xml += "<audience>"+ Util.treatValue(course.getTargetAudience())+"</audience>";
                xml += "</course>";
                xml +="<id>"+grade.getId()+"</id>";
                xml += "<name>"+grade.getName()+"</name>";
                xml += "<maxStudents>"+grade.getMaxStudents()+"</maxStudents>";
                xml += "<startDatetime>"+grade.getStartDatetime()+"</startDatetime>";
                xml += "<endDatetime>"+grade.getEndDatetime()+"</endDatetime>";
                xml +="</grade>";
            }            
        }
        xml += "</grades>";
        return xml;
    }
    
    /**
     * get grades of an course
     */
    
    @Path("/getGradesByCourse/{courseId}/{userId}")
    @GET
    @ProduceMime("application/xml")
    public String getGradesByCourse(@PathParam("courseId") String courseId,@PathParam("userId") String userId) {
        List<Grade> gradeList;
        String xml = "";
        gradeList = gradeRemote.getGradesByCourseAndStatus(new Long(courseId), Constants.GRADE_PERIOD_OF_ENROLLMENT);
        List<Grade> yourGradeByCourse = gradeRemote.getGradesInProgressAndEnrolled(new Long(userId), new Long(courseId));
        
        boolean already = false;

        if (!yourGradeByCourse.isEmpty()) {
            already = true;
        }

        if (already || gradeList.size() == 0) {
            gradeList = null;
            xml = "Não há turmas disponíveis";
        }
        else{

            xml += "<grades>";
            for (Grade grade : gradeList) {
                grade.setEnrollments((Collection<Enrollment>) enrollmentRemote.getByGrade(grade.getId()));
                xml +="<grade>";
                xml +="<id>"+grade.getId()+"</id>";
                xml += "<name>"+grade.getName()+"</name>";
                xml += "<maxStudents>"+grade.getMaxStudents()+"</maxStudents>";
                xml += grade.getStartDatetime();
                xml +="</grade>";
            }
            xml += "</grades>";
        }
        return xml;
        
    }
    
    /**
     * check if student is user ivela
     */
    
    @Path("/isIvelaUser/{login}/{password}")
    @GET
    @ProduceMime("application/xml")
    public String isIvelaUser(@PathParam("login") String login, @PathParam("password") String password) {
        String xml = "";
        
        if (login == null || "".equalsIgnoreCase(login.trim()) || password == null || "".equalsIgnoreCase(login.trim()))
            return xStream.toXML(null);
        List<SystemUser> systemUsers = systemUserRemote.getByUsername(login);
        if (systemUsers == null || systemUsers.size() != 1)
            return xStream.toXML(null);
        
        SystemUser systemUser = systemUsers.get(0);
        xml += "<users>";
            xml += "<user>";
                xml += "<id>"+systemUser.getId()+"</id>";
                xml += "<login>"+ systemUser.getUsername()+"</login>";
                xml += "<password>"+ systemUser.getPassword()+"</password>";
            xml += "</user>";
        xml += "</users>";
        return xml;
        
    }
    
    @Path("/getTotalCourses")
    @GET
    @ProduceMime("application/xml")
    public String getTotalCourses() {
        String xml = "";
        int result = 0;
        List<Grade> gradeList = gradeRemote.getByStatus(Constants.GRADE_PERIOD_OF_ENROLLMENT);
        if (gradeList != null)
            result = gradeList.size();
        xml += "<result>";
        xml += result;
        xml += "</result>";
        return xml;
    }    
    
    @Path("/getCourses/{name}/{description}/{maxResults}")
    @GET
    @ProduceMime("application/xml")
    public String getCourses(@PathParam("name") String name, @PathParam("description") String description, @PathParam("maxResults") String maxResults) {
        if (name == null || name.trim().length() == 0)
            name = "%";
        else
            name = "%" + name + "%";
        if (description == null || description.trim().length() == 0)
            description = "%";
        else
            description = "%" + description + "%";
        Integer intMaxResults = null;
        try {
            intMaxResults = Integer.parseInt(maxResults);
        }
        catch (Exception e) {
            intMaxResults = null;
        }
        String xml = "";
        List<Grade> gradeList = gradeRemote.getByStatus(Constants.GRADE_PERIOD_OF_ENROLLMENT);
        xml += "<grades>";
        int count = 0;
        for (int i = 0; gradeList != null && i < gradeList.size(); i++) {
            Grade grade = gradeList.get(i);
            List<Course> courseList = courseRemote.getCourses(name, description);
            Course course = null;
            for (Course c : courseList) {
                if (c.getId().longValue() == grade.getCourseId().longValue()) {
                    course = c;
                    break;
                }
            }
            if (course != null) {
                xml +="<grade>";
                    xml += "<course>";
                        xml += "<id>" + course.getId() + "</id>";
                        xml += "<name>" + course.getName() + "</name>";
                        //xml += "<content>" + Util.treatValue(course.getContents()) + "</content>";
                        xml += "<description>" + Util.treatValue(course.getDescription()) + "</description>";
                        //xml += "<audience>" + Util.treatValue(course.getTargetAudience()) + "</audience>";
                    xml += "</course>";
                xml +="<id>" + grade.getId() + "</id>";
                xml += "<name>" + grade.getName() + "</name>";
                //xml += "<maxStudents>" + grade.getMaxStudents() + "</maxStudents>";
                //xml += "<startDatetime>" + grade.getStartDatetime() + "</startDatetime>";
                //xml += "<endDatetime>" + grade.getEndDatetime() + "</endDatetime>";
                xml +="</grade>";
                count++;
            }
            if (intMaxResults != null && count == intMaxResults.intValue())
                break;
        }
        xml += "</grades>";
        return xml;
    }    

}

