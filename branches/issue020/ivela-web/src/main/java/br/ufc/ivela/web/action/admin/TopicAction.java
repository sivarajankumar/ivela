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
# File: TopicAction.java                                                                    #
# Document: Topic User Action                                                               # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 07-JAN-2009 - Leonardo Oliveira (UFC)           - XXXXXX - Initial Version                #
# 16-SEP-2009 - Otofuji (Instituto Eldorado)      - 000016 - General Fixes                  #
#############################################################################################
*/
package br.ufc.ivela.web.action.admin;

import br.ufc.ivela.web.action.*;
import br.ufc.ivela.commons.model.Forum;
import br.ufc.ivela.commons.model.Post;
import br.ufc.ivela.commons.model.Topic;
import br.ufc.ivela.ejb.interfaces.ForumRemote;
import br.ufc.ivela.ejb.interfaces.PostRemote;
import br.ufc.ivela.ejb.interfaces.TopicRemote;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.util.StringUtils;

public class TopicAction extends CourseAwareAction {

    private ForumRemote forumRemote;
    private TopicRemote topicRemote;
    private PostRemote postRemote;
    private Forum forum;
    private Topic topic;
    private List<Topic> topicList;
    private List<TopicLine> topicLines;
    private int pageCount;
    private int page;
    private int pageSize = 1;
    private int count;
    private String topicTitle;
    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
    
    /**
     * Add a new topic
     * @return a list of topics
     */
    public String add() {
        performValidateAdd();
        forum = topic.getForum();
        topic.setCreatedBy(getAuthenticatedUser());        
        if (!hasActionErrors()) {
            Long result = topicRemote.add(topic);
            if (result != null) {
                return listByForum();
            }
        }
        return listByForum();
    }
    
    public String addTopic() {
        boolean bResult = false;
        StringBuilder json = new StringBuilder();
        topic.setCreatedBy(getAuthenticatedUser());        
        performValidateAdd();
        if (!hasActionErrors()) {
            Long result = topicRemote.add(topic);
            if (result != null) {
                bResult = true;
            }
        }
        json.append("{");
        json.append("\"result\":\"" + bResult + "\"");
        json.append("}");
        setInputStream(new ByteArrayInputStream(json.toString().getBytes()));
        return "json";
    }
    
    public String removeTopic() {
        boolean bResult = false;
        StringBuilder json = new StringBuilder();
        bResult = topicRemote.remove(topic.getId());
        json.append("{");
        json.append("\"result\":\"" + bResult + "\"");
        json.append("}");
        setInputStream(new ByteArrayInputStream(json.toString().getBytes()));
        return "json";
    }    
    
    public String updateTopic() {
        boolean bResult = false;
        StringBuilder json = new StringBuilder();        
        topic.setCreatedBy(getAuthenticatedUser());        
        bResult = topicRemote.update(topic);
        json.append("{");
        json.append("\"result\":\"" + bResult + "\"");
        json.append("}");
        setInputStream(new ByteArrayInputStream(json.toString().getBytes()));
        return "json";
    }

    /**
     * Sets the variables to be used on the input form
     */
    @Override
    public String input() {

        forum.setId(forum.getId());
        return INPUT;
    }

    /**
     * Execute a search in the topics
     * @return a string
     */
    public String search() {
        return "searchTopic";
    }

    /**
     * Remove a topic
     * @return a list of topics by forum
     */
    public String remove() {
        performValidateRemove();
        if (!hasActionErrors()) {
            topic = topicRemote.get(topic.getId());
            Forum t = forumRemote.get(topic.getForum().getId());
            topic.setForum(t);
            boolean result = topicRemote.remove(topic.getId());
            if (result) {
                forum = t;
                return listByForum();
            }
        }
        addActionError("Unable to remove a topic");
        return listByForum();
    }

    /**
     * Retrieves the forum's list of topic
     * @return a string
     */
    public String listByForum() {
        if (page < 1) {
            page = 1;
        }
        forum = forumRemote.get(forum.getId());
        //List<Topic> topicList = topicRemote.getByForum(forum.getId());
        if (topicTitle == null) {
            topicTitle = "";
        }
        topicList = topicRemote.getByForum(forum.getId());

        //topicList = p.getList();
        //setCount(p.getCount());
        //setPageCount(p.getPageCount());

        List<TopicLine> topicLines = new ArrayList<TopicLine>();
        for (int i = 0; topicList != null && i < topicList.size(); i++) {
            Topic t = topicList.get(i);
            Post lastPost = postRemote.getLastPostByTopic(t.getId());
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


            line.setLastPost(lastPost);
            topicLines.add(line);
        }

        return "list";
    }
    
    public String listTopics() {
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"topics\":[");
        topicList = topicRemote.getByForum(forum.getId());
        for (Topic f : topicList) {
            json.append("{");
            json.append("\"id\":\"" + f.getId() + "\",");
            json.append("\"title\":\"" + f.getTitle() + "\",");
            json.append("\"description\":\"" + f.getDescription() + "\",");
            json.append("\"createdBy\":\"" + f.getCreatedBy() + "\",");
            json.append("\"createdAt\":\"" + f.getCreatedAt() + "\",");
            json.append("\"forumId\":\"" + f.getForum().getId() + "\"");
            json.append("}");
            json.append(",");
        }
        json = new StringBuilder(json.substring(0, json.length() - 1));
        json.append("]");
        json.append("}");
        setInputStream(new ByteArrayInputStream(json.toString().getBytes()));
        return "json";
    }    

    /**
     * Edit a topic
     * @return a string
     */
    public String edit() {
        setTopic(topicRemote.get(topic.getId()));
        return "edit";
    }

    /**
     * Update a topic
     * @return
     */
    public String update() {
        // validates the update
        // validateUpdate();

        // if it has some error
        if (hasActionErrors()) {
            return edit();
        }
        forum = forumRemote.get(topic.getForum().getId());
        topic.setCreatedBy(getAuthenticatedUser());
        topic.setCreatedAt(new Date());
        if (topicRemote.update(topic)) {
            return listByForum();
        } else {
            addActionError("Unable to update a topic");
            return listByForum();
        }
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
     * Perform a validation in the remove method
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
     * Retrieves a count variable
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets a count variable
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Retrieves a title of a topic
     * @return
     */
    public String getTopicTitle() {
        return topicTitle;
    }

    /**
     * Sets a title of a topic
     * @param topicTitle
     */
    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    /**
     * Retrieve a number of a page
     * @return page
     */
    public int getPage() {
        return page;
    }

    /**
     * Sets a number of a page
     * @param page
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * Retrieves a page count
     * @return pageCount
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * Sets a page count
     * @param pageCount
     */
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * Retrieves a page size
     * @return pageSize
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

    /**
     * Retrieves the value of forumRemote variable
     * @return forumRemote
     */
    public ForumRemote getForumRemote() {
        return forumRemote;
    }

    /**
     *Sets the value of forumRemote variable
     * @param forumRemote
     */
    public void setForumRemote(ForumRemote forumRemote) {
        this.forumRemote = forumRemote;
    }

    /**
     * Retrives the value of topic variable
     * @return  topic
     */
    public Topic getTopic() {
        return topic;
    }

    /**
     *Sets the value of topic variable
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
     *Sets the value of topicList variable
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
     *Sets the value of topicRemote variable
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
     *Sets the value of forum variable
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
     *Sets the value of topicLines variable
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
     *Sets the value of postRemote variable
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
         * Retrieves the lasst post
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
         * Retrieves a topic views
         * @return
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
