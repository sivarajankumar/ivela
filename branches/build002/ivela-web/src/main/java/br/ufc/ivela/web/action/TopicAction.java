/*    
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
# File: MessageAction.java                                                                  #
# Document: Action of the message system                                                    # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# ??-???-2008 - Leonardo Oliveira Moreira         - XXXXXX - Initial Version                #
# 11-AUG-2009 - otofuji (Instituto Eldorado)      - 000010 - Topic Creation                 #
#############################################################################################
*/
package br.ufc.ivela.web.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.dao.Page;
import br.ufc.ivela.commons.model.Forum;
import br.ufc.ivela.commons.model.Post;
import br.ufc.ivela.commons.model.Topic;
import br.ufc.ivela.ejb.interfaces.ForumRemote;
import br.ufc.ivela.ejb.interfaces.GradeRemote;
import br.ufc.ivela.ejb.interfaces.PostRemote;
import br.ufc.ivela.ejb.interfaces.TopicRemote;

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
    private int pageSize = 5;
    private int count;
    private String topicTitle;
    private InputStream inputStream;

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
        String hasErrors = performValidateAdd();
        StringBuilder json = new StringBuilder();
        forum = topic.getForum();
        topic.setCreatedBy(getAuthenticatedUser());
        topic.setCreatedAt(new Date());
                
        String bResult = hasErrors;
        if (hasErrors == null) {
            Long result = topicRemote.add(topic);
            if (result != null) {
                bResult = "true";
            }
        }
                
        json.append("{");
        json.append("\"result\":\"" + bResult + "\"");
        json.append("}");
        
        setInputStream(new ByteArrayInputStream(json.toString().getBytes()));
        return "json";
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
        StringBuilder json = new StringBuilder();
        boolean bResult = false;
        if (!hasActionErrors()) {
            boolean result = topicRemote.remove(topic.getId());
            if (result) {
                bResult = true;
            }
        }        
        json.append("{");
        json.append("\"result\":\"" + bResult + "\"");
        json.append("}");
        
        setInputStream(new ByteArrayInputStream(json.toString().getBytes()));
        return "json";
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

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
    
    public InputStream getInputStream() {
        return inputStream;
    }
    
    /**
     * Perform a validation in the add method
     */
    private String performValidateAdd() {
        if (topic == null) {
            return getText("topic.input.validation.required");
        }
        // verifies if the title is empty
        if (!StringUtils.hasText(topic.getTitle())) {
            return getText("topic.input.validation.title");
        }
        if (topic.getForum() != null) {
            Topic lastTopic = topicRemote.getLastTopicByForum(topic.getForum()
                    .getId());
            if ((lastTopic != null)
                    && (lastTopic.getCreatedBy().getId()
                            .equals(getAuthenticatedUser().getId()))
                    && (lastTopic.getTitle().equalsIgnoreCase(topic.getTitle()))
                    && (lastTopic.getDescription().equalsIgnoreCase(topic
                            .getDescription()))) {
                return getText("forum.error.topic.duplicated");
            }
        }
        return null;
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
            if ((topic = topicRemote.get(topic.getId())) == null) {
                addActionError(getText("topic.input.validation.invalidId"));
            }
            
            if (getAuthenticatedUser().getAuthentication().getName()
                    .equalsIgnoreCase("ROLE_ADMIN")) {
                return;
            }
            
            if (!topic.getCreatedBy().getId().equals(
                            getAuthenticatedUser().getId())) {
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
