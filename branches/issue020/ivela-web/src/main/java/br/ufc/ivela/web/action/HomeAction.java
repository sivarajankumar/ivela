/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.web.action;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.Enrollment;
import br.ufc.ivela.commons.model.Message;
import br.ufc.ivela.commons.model.NewsFlash;
import br.ufc.ivela.commons.model.Note;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.commons.model.Topic;
import br.ufc.ivela.commons.model.UnitContent;
import br.ufc.ivela.ejb.interfaces.CourseRemote;
import br.ufc.ivela.ejb.interfaces.EnrollmentRemote;
import br.ufc.ivela.ejb.interfaces.MessageRemote;
import br.ufc.ivela.ejb.interfaces.NewsFlashRemote;
import br.ufc.ivela.ejb.interfaces.NoteRemote;
import br.ufc.ivela.ejb.interfaces.ProfileRemote;
import br.ufc.ivela.ejb.interfaces.SystemUserRemote;
import br.ufc.ivela.ejb.interfaces.TopicRemote;
import br.ufc.ivela.ejb.interfaces.UnitContentRemote;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Action of the EAD's home
 */
public class HomeAction extends GenericAction {

    /**
     * 
     */
    private static final long serialVersionUID = -8068776172601074387L;
    
    private MessageRemote messageRemote;
    private EnrollmentRemote enrollmentRemote;
    private ProfileRemote profileRemote;
    private SystemUserRemote systemUserRemote;
    private NewsFlashRemote newsFlashRemote;
    private List<Enrollment> enrollmentList;
    private List<Enrollment> pendentList;
    private CourseRemote courseRemote;
    private List<Course> courseList;
    private List<Message> messageList;
    private List<Topic> recentTopics;
    private TopicRemote topicRemote;
    private NoteRemote noteRemote;
    private List<Note> noteList;
    private InputStream inputStream;    
    private NewsFlash newsFlash;
    private String profilePhoto;
    private Long courseId;
    private UnitContentRemote unitContentRemote;
    private UnitContent lastUnitContent;
    private Enrollment enrollment;    

    /**
     * Get a list of messages by the authenticated user, and the last topics, and the last notes
     * @return Sucess if the user could see all the messages
     */
    @Override
    public String execute() {
        enrollmentList = enrollmentRemote.getByUserAndStatus(getAuthenticatedUser().getId(), Constants.ENROLLMENT_ACTIVE);

        pendentList = enrollmentRemote.getByUserAndStatus(getAuthenticatedUser().getId(), Constants.ENROLLMENT_PENDING);
        
        //get the last news
        List<NewsFlash> news = newsFlashRemote.getUnreadNewsByUser(getAuthenticatedUser().getId());
                
        if(news != null && news.size() > 0){
            setNewsFlash(news.get(0));
        }

        return SUCCESS;
    }
    
    public String cancel() {
        logger.log("\n\n\nENTREI NO CANCEL\n\n\n");
        Enrollment e = enrollmentRemote.get(enrollment.getId());

//        if (e.getGrade().getStatus() == Constants.GRADE_PERIOD_OF_ENROLLMENT) {
//            enrollmentRemote.remove(enrollment.getId());
//        } else if (e.getGrade().getStatus() == Constants.GRADE_REGISTRATION_FINISHED) {
//            e.setStatus(Constants.ENROLLMENT_SUSPENDED);
//            enrollmentRemote.update(e);
//        }
        
        if(e.getStatus() == Constants.ENROLLMENT_PENDING){
            enrollmentRemote.remove(enrollment.getId());
        }
        return execute();
    }

    public String getToolsTopics() {
        enrollmentList = enrollmentRemote.getByUserAndStatus(getAuthenticatedUser().getId(), Constants.ENROLLMENT_ACTIVE);
        recentTopics = this.topicRemote.getRecentTopics(3, enrollmentList);

        StringBuilder json = new StringBuilder("{\"topics\":["); // fake json!!!

        for (int i = 0; i < recentTopics.size(); i++) {
            Topic topic = recentTopics.get(i);
            
            json.append("{\"id\":\""+ topic.getId() +"\", \"title\":\""+ topic.getTitle() +"\"," +
                    " \"createdBy\":\""+ topic.getCreatedBy().getUsername() + "\"," +
                    " \"createdAt\":\""+ topic.getCreatedAt() + "\", \"forumId\":\""+ topic.getForum().getId() +"\"}");

            if(i != recentTopics.size() - 1) {
                json.append(",");
            }
        }

        json.append("]}");

        setInputStream(new ByteArrayInputStream(json.toString().getBytes()));

        return "json";
    }

    public String getToolsMessages() {

        messageList = messageRemote.getBySystemUserRecipient(getAuthenticatedUser().getId(), "", 1, 3);
        
        StringBuilder json = new StringBuilder("{\"message\":["); 

        if (messageList == null) {
            messageList = new ArrayList<Message>();
        } else {
            if (messageList.size() > 3) {
                messageList = messageList.subList(0, 3);
            }
        }
        for (int i = 0; i < messageList.size(); i++) {
            Message message = messageList.get(i);

            json.append("{\"id\":\"" + message.getId() + "\", \"title\":\""
                    + message.getTitle() + "\"," + " \"sender\":\""
                    + message.getSender().getUsername() + "\","
                    + " \"datetime\":\"" + message.getDatetime()
                    + "\"}");

            if (i != messageList.size() - 1) {
                json.append(",");
            }
        }

        json.append("]}");
        setInputStream(new ByteArrayInputStream(json.toString().getBytes()));        

        return "json";
    }

    public String getToolsLastUnitContent() {

        SystemUser atual = systemUserRemote.get(getAuthenticatedUser().getId());

        if (atual.getLastUnitContentId() != null) {
            lastUnitContent = unitContentRemote.get(atual.getLastUnitContentId());
        }

        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("unitContent", UnitContent.class);

        setInputStream(new ByteArrayInputStream(xStream.toXML(lastUnitContent).getBytes()));

        return "json";
    }

    /**
     * Retrieves the courses from the json
     * @return json
     */
    public String getCourses() {
        String results = courseRemote.getJsonStructure();
        setInputStream(new ByteArrayInputStream(results.getBytes()));

        return "json";
    }
    
    public String getCourse() {
        String results = courseRemote.getCourseJsonStructure(courseId);
        setInputStream(new ByteArrayInputStream(results.getBytes()));

        return "json";
    }
    
    public String markNewsFlash() {
        boolean ok = newsFlashRemote.markAsRead(newsFlash.getId());
                
        String result = "";
        
        if(ok){
            result = "{\"ok\":\"true\"}";
        } else {
            result = "{\"ok\":\"false\"}";
        }
        
        setInputStream(new ByteArrayInputStream(result.getBytes()));

        return "json";
    }

    /**
     * Retrieves the maximun date to conclude
     * @param startDate
     * @param duration
     * @param format
     * @return a date 
     */
    public String getMaximumDateToConclude(Date startDate, Integer duration, String format) {
        logger.log("\n\nCalculating the maximum date: " + startDate.toString() + " - " + duration);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_MONTH, duration);

        return formatDate(calendar.getTime(), format);
    }

    /**
     * Calculates a progressive bar from the percents of the conclusion of the course 
     * @param maximum
     * @param percentage
     * @return
     */
    public Integer calculateProgressBarWidth(Integer maximum, Float percentage) {
        logger.log("\n\nCalculating the progress bar: " + maximum.toString() + " - " + percentage.toString());
        return Math.round((maximum * percentage) / 100);
    }

    /**
     * Format a date 
     * @param date
     * @param format
     * @return the date correctly formated
     */
    public String formatDate(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * Retrieves the recently topics
     * @return recentlyTopics
     */
    public List<Topic> getRecentlyTopics() {
        return recentTopics;
    }

    /**
     * Sets the recently topics
     * @param recentlyTopics
     */
    public void setRecentlyTopics(List<Topic> recentlyTopics) {
        this.recentTopics = recentlyTopics;
    }

    /**
     * Retrieves the remote topic
     * @return topicRemote
     */
    public TopicRemote getTopicRemote() {
        return topicRemote;
    }

    /**
     * Sets the remote topic
     * @param topicRemote
     */
    public void setTopicRemote(TopicRemote topicRemote) {
        this.topicRemote = topicRemote;
    }

    /**
     * Retrieves the list of notes
     * @return noteList
     */
    public List<Note> getNoteList() {
        return noteList;
    }

    /**
     * Sets the list of notes
     * @param noteList
     */
    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }

    /**
     * Retrieves the remote note
     * @return noteRemote
     */
    public NoteRemote getNoteRemote() {
        return noteRemote;
    }

    /**
     * Sets the remote note
     * @param noteRemote
     */
    public void setNoteRemote(NoteRemote noteRemote) {
        this.noteRemote = noteRemote;
    }

    /**
     * Retrieves the list of course
     * @return courseList
     */
    public List<Course> getCourseList() {
        return courseList;
    }

    /**
     * Sets the list of course
     * @param courseList
     */
    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    /**
     * Retrieves the remote course
     * @return courseRemote
     */
    public CourseRemote getCourseRemote() {
        return courseRemote;
    }

    /**
     * Sets the remote course
     * @param courseRemote
     */
    public void setCourseRemote(CourseRemote courseRemote) {
        this.courseRemote = courseRemote;
    }

    /**
     * Retrieves the remote enrollment
     * @return enrollmentRemote
     */
    public EnrollmentRemote getEnrollmentRemote() {
        return enrollmentRemote;
    }

    /**
     * Sets the remote enrollment
     * @param enrollmentRemote
     */
    public void setEnrollmentRemote(EnrollmentRemote enrollmentRemote) {
        this.enrollmentRemote = enrollmentRemote;
    }

    /**
     * Retrieves the list of enrollment
     * @return enrollmentList
     */
    public List<Enrollment> getEnrollmentList() {
        return enrollmentList;
    }

    /**
     * Sets the list of enrollment
     * @param enrollmentList
     */
    public void setEnrollmentList(List<Enrollment> enrollmentList) {
        this.enrollmentList = enrollmentList;
    }

    /**
     * Retrieves the list of message
     * @return messageList
     */
    public List<Message> getMessageList() {
        return messageList;
    }

    /**
     * Sets the list of message
     * @param messageList
     */
    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    /**
     * Retrieves the remote message
     * @return messageRemote
     */
    public MessageRemote getMessageRemote() {
        return messageRemote;
    }

    /**
     * Sets the remote message
     * @param messageRemote
     */
    public void setMessageRemote(MessageRemote messageRemote) {
        this.messageRemote = messageRemote;
    }

    /**
     * Retrieves the input Stream
     * @return inputStream
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * Sets the input Stream
     * @param inputStream
     */
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public NewsFlash getNewsFlash() {
        return newsFlash;
    }

    public void setNewsFlash(NewsFlash newsFlash) {
        this.newsFlash = newsFlash;
    }

    public NewsFlashRemote getNewsFlashRemote() {
        return newsFlashRemote;
    }

    public void setNewsFlashRemote(NewsFlashRemote newsFlashRemote) {
        this.newsFlashRemote = newsFlashRemote;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public ProfileRemote getProfileRemote() {
        return profileRemote;
    }

    public void setProfileRemote(ProfileRemote profileRemote) {
        this.profileRemote = profileRemote;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public UnitContent getLastUnitContent() {
        return lastUnitContent;
    }

    public void setLastUnitContent(UnitContent lastUnitContent) {
        this.lastUnitContent = lastUnitContent;
    }

    public UnitContentRemote getUnitContentRemote() {
        return unitContentRemote;
    }

    public void setUnitContentRemote(UnitContentRemote unitContentRemote) {
        this.unitContentRemote = unitContentRemote;
    }

    public SystemUserRemote getSystemUserRemote() {
        return systemUserRemote;
    }

    public void setSystemUserRemote(SystemUserRemote systemUserRemote) {
        this.systemUserRemote = systemUserRemote;
    }
    
    public String getCourseProgress() {
        Integer progress = courseRemote.getProgress(getAuthenticatedUser().getId(), courseId);
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"progress\":\"" + progress.intValue() + "\"");
        json.append("}");
        setInputStream(new ByteArrayInputStream(json.toString().getBytes()));
        return "json";        
    }

    public List<Enrollment> getPendentList() {
        return pendentList;
    }

    public void setPendentList(List<Enrollment> pendentList) {
        this.pendentList = pendentList;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }
}