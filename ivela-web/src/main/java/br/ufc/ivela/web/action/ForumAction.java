/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.web.action;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.dao.Page;
import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.Discipline;
import br.ufc.ivela.commons.model.Forum;
import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.commons.model.Post;
import br.ufc.ivela.commons.model.Topic;
import br.ufc.ivela.ejb.interfaces.CourseRemote;
import br.ufc.ivela.ejb.interfaces.ForumRemote;
import br.ufc.ivela.ejb.interfaces.GradeRemote;
import br.ufc.ivela.ejb.interfaces.PostRemote;
import br.ufc.ivela.ejb.interfaces.TopicRemote;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Action of the forum
 */
public class ForumAction extends GenericAction {

    private GradeRemote gradeRemote;
    private CourseRemote courseRemote;    
    private ForumRemote forumRemote;
    private TopicRemote topicRemote;
    private PostRemote postRemote;
    private Forum forum;
    private Grade grade;
    private Course course;
    private Discipline discipline;
    private List<Forum> forumList;
    private List<ForumLine> forumLines;
    private int pageCount;
    private int page;
    private int pageSize = 5;
    private int count;
    private String forumTitle;
    private java.io.InputStream inputStream;

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public String getForumTitle() {
        return forumTitle;
    }

    public void setForumTitle(String forumTitle) {
        this.forumTitle = forumTitle;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    /**
     * List all forum by user in session
     */
    public String list() {
        if (page < 1) {
            page = 1;
        }
        if (forumTitle == null) {
            forumTitle = "";
        }
        boolean isAdministrator = ! (getAuthenticatedUser().getAuthentication().getId().equals(Constants.ROLE_USER));
        Page  p = forumRemote.getForumList(getAuthenticatedUser().getId(), isAdministrator, true, forumTitle, page, pageSize);
        forumList = p.getList();
        setCount(p.getCount());
        setPageCount(p.getPageCount());
        forumLines = new ArrayList<ForumLine>();
        for (int i = 0; forumList != null && i < forumList.size(); i++) {
            Forum f = forumList.get(i);
            Topic lastTopic = topicRemote.getLastTopicByForum(f.getId());
            ForumLine line = new ForumLine();
            line.setForum(f);

            long forumReplies = 0;
            long forumViews = 0;

            List<Topic> topicList = topicRemote.getByForum(f.getId());
            if (topicList != null) {
                forumViews += topicList.size();
                for (Topic t : topicList) {
                    List<Post> postList = postRemote.getByTopic(t.getId());
                    if (postList != null) {
                        forumReplies += postList.size();
                    }
                }
            }

            line.setForumReplies(forumReplies);
            line.setForumViews(forumViews);
            line.setLastTopic(lastTopic);
            forumLines.add(line);
        }
        return "list";
    }

    //checa se existem foruns para aquele course
    public String getOpenForunsByCourseJson() {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        //System.out.println("COURSE ID: " + getCourse().getId());
        Course c = courseRemote.get(getCourse().getId());

        Forum forum = new Forum();

        List<Grade> list = gradeRemote.getGradesByStudent(getAuthenticatedUser());

        for (Grade grade : list) {
            grade.setCourse(courseRemote.get(grade.getCourseId()));
            if (grade.getCourse().getId().longValue() == c.getId().longValue()) {
                forum.setGrade(grade);
            }
        }

        if (page < 1) {
            page = 1;
        }

        if (forumTitle == null) {
            forumTitle = "";
        }
        Page p = forumRemote.getForumListPageBySystemUserGrade(getAuthenticatedUser().getId(), forum.getGrade().getId(), forumTitle, page, pageSize);

        forumList = p.getList();
        //System.out.println("---"+forumList.size());
        String json = xStream.toXML(forumList.size());
        json = json.replaceAll("int", "count");

        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }

    public String listByCourse() {

        Course c = courseRemote.get(getCourse().getId());

        if (forumRemote.isAccess(getAuthenticatedUser().getId(), c.getId())) {
//            List<Grade> list = gradeRemote.getGradesByStudent(getAuthenticatedUser());
//
//            for (Grade grade : list) {
//                grade.setCourse(courseRemote.get(grade.getCourseId()));
//                if (grade.getCourse().getId().longValue() == c.getId().longValue()) {
//                    forum.setGrade(grade);
//                }
//            }

            if (page < 1) {
                page = 1;
            }

            if (forumTitle == null) {
                forumTitle = "";
            }
            
            Page p = forumRemote.getForumList(getAuthenticatedUser().getId(), c.getId(), false, false, forumTitle, page, pageSize);
            
            forumList = p.getList();
            setCount(p.getCount());
            setPageCount(p.getPageCount());
        }


        forumLines = new ArrayList<ForumLine>();

        for (int i = 0; forumList != null && i < forumList.size(); i++) {
            Forum f = forumList.get(i);
            Topic lastTopic = topicRemote.getLastTopicByForum(f.getId());
            ForumLine line = new ForumLine();
            line.setForum(f);

            long forumReplies = 0;
            long forumViews = 0;

            List<Topic> topicList = topicRemote.getByForum(f.getId());
            if (topicList != null) {
                forumViews += topicList.size();
                for (Topic t : topicList) {
                    List<Post> postList = postRemote.getByTopic(t.getId());
                    if (postList != null) {
                        forumReplies += postList.size();
                    }
                }
            }

            line.setForumReplies(forumReplies);
            line.setForumViews(forumViews);
            line.setLastTopic(lastTopic);
            forumLines.add(line);
        }

        return "list";
    }

    public String search() {
        return "searchForum";
    }

    /**
     *Sets the value of forum variable
     * 
     */
    public void setForum(Forum forum) {
        this.forum = forum;
    }

    /**
     * Retrieves the value of forum variabl
     * @return forum
     */
    public Forum getForum() {
        return forum;
    }

    /**
     * Retrieves the value of forumRemote variable
     * @return forumRemote
     */
    public ForumRemote getForumRemote() {
        return forumRemote;
    }

    /**
     * Sets the value of forumRemote variable
     * @param forumRemote
     */
    public void setForumRemote(ForumRemote forumRemote) {
        this.forumRemote = forumRemote;
    }

    /**
     * Retrieves the value of forumList variable
     * @return forumList
     */
    public List<Forum> getForumList() {
        return forumList;
    }

    /**
     * Sets the value of forumList variable
     * @param forumList
     */
    public void setForumList(List<Forum> forumList) {
        this.forumList = forumList;
    }

    /**
     * Retrieves the value of topicRemote variable
     * @return
     */
    public TopicRemote getTopicRemote() {
        return topicRemote;
    }

    /**
     * Sets the value of topicRemote variable
     * @param topicRemote
     */
    public void setTopicRemote(TopicRemote topicRemote) {
        this.topicRemote = topicRemote;
    }

    /**
     * Retrieves the value of forumLines variable
     * @return
     */
    public List<ForumLine> getForumLines() {
        return forumLines;
    }

    /**
     * Sets the value of forumLines variable
     * @param forumLines
     */
    public void setForumLines(List<ForumLine> forumLines) {
        this.forumLines = forumLines;
    }

    /**
     * Retrieves the value of count
     * @return
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the value of count
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Retrieves the number of the page
     * @return
     */
    public int getPage() {
        return page;
    }

    /**
     * Sets the number of a page
     * @param page
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * Retrieves the value of page count
     * @return pageCount
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * Sets the value of page count
     * @param pageCount
     */
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * Retrieves the value of page size
     * @return pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets the value of page size
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Retrieves a remote post
     * @return postRemote
     */
    public PostRemote getPostRemote() {
        return postRemote;
    }

    /**
     * Sets a remote post
     * @param postRemote
     */
    public void setPostRemote(PostRemote postRemote) {
        this.postRemote = postRemote;
    }

    /**
     * Class that represents a item of the forum list in the view
     */
    public class ForumLine {

        private Forum forum;
        private Long forumReplies;
        private Long forumViews;
        private Topic lastTopic;

        /**
         * Retrieves a forum
         * @return forum
         */
        public Forum getForum() {
            return forum;
        }

        /**
         * Sets a forum
         * @param forum
         */
        public void setForum(Forum forum) {
            this.forum = forum;
        }

        /**
         * Retrieves the forum replies
         * @return forumRepliesS
         */
        public Long getForumReplies() {
            return forumReplies;
        }

        /**
         * Sets the forum replies
         * @param forumReplies
         */
        public void setForumReplies(Long forumReplies) {
            this.forumReplies = forumReplies;
        }

        /**
         * Retrieves the value of the forum views
         * @return
         */
        public Long getForumViews() {
            return forumViews;
        }

        /**
         * Sets the value of the forum views
         * @param forumViews
         */
        public void setForumViews(Long forumViews) {
            this.forumViews = forumViews;
        }

        /**
         * Retrieves the last topic
         * @return lastTopic
         */
        public Topic getLastTopic() {
            return lastTopic;
        }

        /**
         * Sets the last Topic
         * @param lastTopic
         */
        public void setLastTopic(Topic lastTopic) {
            this.lastTopic = lastTopic;
        }
    }

    /**
     * Retrieves a course
     * @return course
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Sets a course
     * @param course
     */
    public void setCourse(Course course) {
        this.course = course;
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
     * Retrieves a remote grade
     * @return gradeRemote
     */
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

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
