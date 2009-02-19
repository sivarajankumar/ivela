/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.web.action;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.dao.Page;
import br.ufc.ivela.commons.model.Forum;
import br.ufc.ivela.commons.model.Post;
import br.ufc.ivela.commons.model.Topic;
import br.ufc.ivela.ejb.interfaces.ForumRemote;
import br.ufc.ivela.ejb.interfaces.GradeRemote;
import br.ufc.ivela.ejb.interfaces.PostRemote;
import br.ufc.ivela.ejb.interfaces.TopicRemote;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.util.StringUtils;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * 
 */
public class TopicAction extends GenericAction {

    private ForumRemote forumRemote;
    private TopicRemote topicRemote;
    private PostRemote postRemote;
    private GradeRemote gradeRemote;
    private Forum forum;
    private Topic topic;
    private List<Topic> topicList;
    private List<TopicLine> topicLines;
    private int pageCount;
    private int page;
    private int pageSize = 1;
    private int count;
    private String topicTitle;

    public GradeRemote getGradeRemote() {
        return gradeRemote;
    }

    public void setGradeRemote(GradeRemote gradeRemote) {
        this.gradeRemote = gradeRemote;
    }

    /**
     * Add a new topic, perform a validate 
     * if hasn't error, add a new topic
     * if has return list by forum
     */
    public String add() {
        performValidateAdd();
        forum = topic.getForum();
        topic.setCreatedBy(getAuthenticatedUser());
        topic.setCreatedAt(new Date());
        if (!hasActionErrors()) {
            Long result = topicRemote.add(topic);
            if (result != null) {
                return listByForum();
            }
        }
        addActionMessage("Unable to Add");
        return listByForum();
    }

    /**
     * Sets the variables to be used on the input form
     * @return
     */
    @Override
    public String input() {
        return INPUT;
    }

    /**
     * Execute a search in the topic
     * @return searchTopic
     */
    public String search() {
        return "searchTopic";
    }

    /**
     * Remove a topic, perform a validate
     * if hasn't error return a list by forum
     * if has return a list by forum
     */
    public String remove() {
        performValidateRemove();
        if (!hasActionErrors()) {
            boolean result = topicRemote.remove(topic.getId());
            if (result) {
                return listByForum();
            }
        }
        addActionMessage("Unable to remove");
        return listByForum();
    }

    /**
     * Retrieves the forum's list of topic
     * 
     */
    public String listByForum() {
        forum = forumRemote.get(forum.getId());
        if (page < 1) {
            page = 1;
        }
        if (topicTitle == null) {
            topicTitle = "";
        }
        boolean isAdministrator = ! (getAuthenticatedUser().getAuthentication().getId().equals(Constants.ROLE_USER));
        Page p = topicRemote.getTopicList(getAuthenticatedUser().getId(), isAdministrator, true, forum.getId(), topicTitle, page, pageSize);
        topicList = p.getList();
        System.out.println(topicList.size());
        setCount(p.getCount());
        setPageCount(p.getPageCount());
        topicLines = new ArrayList<TopicLine>();
        for (int i = 0; topicList != null && i < topicList.size(); i++) {
            Topic t = topicList.get(i);
            Post lastPost = postRemote.getLastPostByTopic(t.getId());
            String message = "";
            if (lastPost != null) {
                message = lastPost.getMessage();
                //message = message.replaceAll("\\[quote\\]", "<p class=\"quote-answer\">");
                //message = message.replaceAll("\\[/quote\\]", "</p>");
                lastPost.setMessage(message);
            }
            TopicLine line = new TopicLine();
            line.setTopic(t);

            long topicView = 0;
            long topicReplies = 0;

            topicView += topicList.size();
            List<Post> postList = postRemote.getByTopic(t.getId());
            if (postList != null) {
                topicReplies = postList.size();
            }
            line.setTopicReplies(topicReplies);
            line.setTopicViews(topicView);

            if (lastPost != null) {
                line.setLastPost(lastPost);
            }
            topicLines.add(line);
        }
        return "list";
    }

    /**
     * Perform a validation in the add method
     */
    private void performValidateAdd() {
        if (topic == null) {
            addActionError(getText("topic.input.validation.required"));
        }
        // verifies if the title is empty
        if (!StringUtils.hasText(topic.getTitle())) {
            addActionError(getText("topic.input.validation.title"));
        }

    }

    /**
     * Perform a validate in the method remove
     */
    private void performValidateRemove() {
        // verifies if there is an id
        if (topic.getId() == null) {
            addActionError(getText("topic.input.validation.requiredId"));
        } else {
            // verifies if this id is valid
            if (topicRemote.get(topic.getId()) == null) {
                addActionError(getText("topic.input.validation.invalidId"));
            }
        }
    }

    /**
     * Retrieves the title of the topic
     * @return 
     */
    public String getTopicTitle() {
        return topicTitle;
    }

    /**
     * Sets the title of the topic
     * @param topicTitle
     */
    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    /**
     * Retrieves the variable count
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the variable count
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Retrieves the current page
     * @return page
     */
    public int getPage() {
        return page;
    }

    /**
     * Sets the current page
     * @param page
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * Retrieves the variable page count
     * @return page count int
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * Sets a variable page count
     * @param pageCount
     */
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * Retrieves the page size number
     * @return pageSize int
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets the page size number
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Retrives the value of forumRemote variable
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
     * Retrives the value of topic variable
     * @return topic
     */
    public Topic getTopic() {
        return topic;
    }

    /**
     * Sets the value of topic variable
     * @param topic
     */
    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    /**
     * Retrives the value of topicList variable
     * @return topicList
     */
    public List<Topic> getTopicList() {
        return topicList;
    }

    /**
     * Sets the value of topicList variable
     * @param topicList
     */
    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }

    /**
     * Retrives the value of topicRemote variable
     * @return topicRemote
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
     * Retrives the value of forum variable
     * @return forum
     */
    public Forum getForum() {
        return forum;
    }

    /**
     * Sets the value of forum variable
     * @param forum
     */
    public void setForum(Forum forum) {
        this.forum = forum;
    }

    /**
     * Retrives the value of topicLines variable
     * @return topicLines
     */
    public List<TopicLine> getTopicLines() {
        return topicLines;
    }

    /**
     * Sets the value of topicLines variable
     * @param topicLines
     */
    public void setTopicLines(List<TopicLine> topicLines) {
        this.topicLines = topicLines;
    }

    /**
     * Retrives the value of postRemote variable
     * @return postRemote
     */
    public PostRemote getPostRemote() {
        return postRemote;
    }

    /**
     * Sets the value of postRemote variable
     * @param postRemote
     */
    public void setPostRemote(PostRemote postRemote) {
        this.postRemote = postRemote;
    }

    /**
     * Class that represents a item of the topic list in the view
     */
    public class TopicLine {

        private Topic topic;
        private Long topicReplies;
        private Long topicViews;
        private Post lastPost;

        /**
         * Retrieves the last post
         * @return lastPost
         */
        public Post getLastPost() {
            return lastPost;
        }

        /**
         * Sets the last post
         * @param lastPost
         */
        public void setLastPost(Post lastPost) {
            this.lastPost = lastPost;
        }

        /**
         * Retrieves a topic
         * @return topic
         */
        public Topic getTopic() {
            return topic;
        }

        /**
         * Sets a topic
         * @param topic
         */
        public void setTopic(Topic topic) {
            this.topic = topic;
        }

        /**
         * Retrieves a topic replies
         * @return topicReplies
         */
        public Long getTopicReplies() {
            return topicReplies;
        }

        /**
         * Sets a topic replies
         * @param topicReplies
         */
        public void setTopicReplies(Long topicReplies) {
            this.topicReplies = topicReplies;
        }

        /**
         * Retrieves the topic views
         * @return topicViews
         */
        public Long getTopicViews() {
            return topicViews;
        }

        /**
         * Sets a topic views
         * @param topicViews
         */
        public void setTopicViews(Long topicViews) {
            this.topicViews = topicViews;
        }
    }
}
