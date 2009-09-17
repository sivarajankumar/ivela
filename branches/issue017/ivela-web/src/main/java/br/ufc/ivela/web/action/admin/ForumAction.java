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
# File: ForumAction.java                                                                    #
# Document: Forum Admin Action                                                              # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 07-JAN-2009 - Leonardo Oliveira (UFC)           - XXXXXX - Initial Version                #
# 16-SEP-2009 - Otofuji (Instituto Eldorado)      - 000016 - General Fixes                  #
#############################################################################################
*/
package br.ufc.ivela.web.action.admin;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.springframework.util.StringUtils;

import br.ufc.ivela.commons.model.Forum;
import br.ufc.ivela.ejb.interfaces.ForumRemote;
import br.ufc.ivela.ejb.interfaces.PostRemote;
import br.ufc.ivela.ejb.interfaces.TopicRemote;
import br.ufc.ivela.web.action.CourseAwareAction;

public class ForumAction extends CourseAwareAction {

    private ForumRemote forumRemote;
    private TopicRemote topicRemote;
    private PostRemote postRemote;
    private Forum forum;
    private List<Forum> forumList;    
    private InputStream inputStream;
    private int pageCount;
    private int page;
    private int pageSize = 5;
    private int count;
    private String forumTitle;

    public PostRemote getPostRemote() {
        return postRemote;
    }

    public void setPostRemote(PostRemote postRemote) {
        this.postRemote = postRemote;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getForumTitle() {
        return forumTitle;
    }

    public void setForumTitle(String forumTitle) {
        this.forumTitle = forumTitle;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String listForums() {
        StringBuilder json = new StringBuilder();
        course = courseRemote.get(course.getId());
        grade = gradeRemote.get(grade.getId());
        json.append("{");
        json.append("\"forums\":[");
        List<Forum> forumList = forumRemote.getForumList(getAuthenticatedUser().getId(), course.getId(), true, true, "");
        for (Forum f : forumList) {
            if (f.getGrade() != null && f.getGrade().getId().longValue() == grade.getId().longValue()) {
                json.append("{");
                json.append("\"id\":\"" + f.getId() + "\",");
                json.append("\"title\":\"" + f.getTitle() + "\",");
                json.append("\"description\":\"" + f.getDescription() + "\",");
                json.append("\"gradeId\":\"" + f.getGrade().getId() + "\",");
                json.append("\"createdBy\":\"" + f.getCreatedBy() + "\",");
                json.append("\"public\":\"" + f.getPublic1() + "\",");
                json.append("\"studentCreateTopic\":\"" + f.getStudentCreateTopic() + "\",");                
                json.append("\"studentUploadPost\":\"" + f.getStudentUploadPost()+ "\"");                
                json.append("}");
                json.append(",");
            }
        }
        json = new StringBuilder(json.substring(0, json.length() - 1));
        json.append("]");
        json.append("}");
        setInputStream(new ByteArrayInputStream(json.toString().getBytes()));
        return "json";
    }
    
    public String getForumInfo() {
        forum = forumRemote.get(forum.getId());
        if (forum.getGrade() != null) {
            grade = gradeRemote.get(forum.getGrade().getId());
        }
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"forum\":{");
        json.append("\"id\":\"" + forum.getId() + "\",");
        json.append("\"title\":\"" + forum.getTitle() + "\",");
        json.append("\"description\":\"" + forum.getDescription() + "\"");
        if (forum.getGrade() != null) {
            json.append(",\"gradeId\":\"" + grade.getId() + "\",");
            json.append("\"courseId\":\"" + grade.getCourseId() + "\"");
        }
        json.append("}");
        json.append("}");
        setInputStream(new ByteArrayInputStream(json.toString().getBytes()));
        return "json";
    }

    
    public String add() {
        gradeList = gradeRemote.getAll();
        forum.setCreatedBy(getAuthenticatedUser());
        performValidateAdd();
        if (!hasActionErrors()) {
            Long result = forumRemote.add(forum);
            if (result != null) {
                return list();
            }
        }
        return list();
    }
    
    public String addForum() {
        boolean bResult = false;
        StringBuilder json = new StringBuilder();
        forum.setCreatedBy(getAuthenticatedUser());
        performValidateAdd();
        if (!hasActionErrors()) {
            Long result = forumRemote.add(forum);
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
    
    public String updateForum() {
        boolean bResult = false;
        StringBuilder json = new StringBuilder();
        forum.setCreatedBy(getAuthenticatedUser());
        forum.setGrade(gradeRemote.get(forum.getGrade().getId()));
        bResult = forumRemote.update(forum);
        json.append("{");
        json.append("\"result\":\"" + bResult + "\"");
        json.append("}");
        setInputStream(new ByteArrayInputStream(json.toString().getBytes()));
        return "json";
    }
    
    public String removeForum() {
        boolean bResult = false;
        StringBuilder json = new StringBuilder();
        forum = forumRemote.get(forum.getId());
        bResult = forumRemote.remove(forum.getId());
        json.append("{");
        json.append("\"result\":\"" + bResult + "\"");
        json.append("}");
        setInputStream(new ByteArrayInputStream(json.toString().getBytes()));
        return "json";
    }

    public String remove() {
        performValidateRemove();
        if (!hasActionErrors()) {
            boolean result = forumRemote.remove(forum.getId());
            if (result) {
                return list();
            }
        }
        return list();
    }

    public String edit() {
        gradeList = gradeRemote.getAll();
        setForum(forumRemote.get(forum.getId()));
        return "edit";
    }

    public String update() {
        gradeList = gradeRemote.getAll();
        if (hasActionErrors()) {
            return edit();
        }
        forum.setCreatedBy(getAuthenticatedUser());
        if (forumRemote.update(forum)) {
            return list();
        } else {
            return ERROR;
        }
    }

    public String input() {
        gradeList = gradeRemote.getAll();
        return INPUT;
    }
    
    public String list() {
        gradeList = gradeRemote.getAll();
        forumList = forumRemote.getAll();
        return "list";        
    }

    private void performValidateAdd() {
        if (forum == null) {
            addActionError(getText("forum.input.validation.required"));
        }
        if (!StringUtils.hasText(forum.getTitle())) {
            addActionError(getText("forum.input.validation.title"));
        }
    }

    private void performValidateRemove() {
        if (forum.getId() == null) {
            addActionError(getText("forum.input.validation.requiredId"));
        } else {
            if (forumRemote.get(forum.getId()) == null) {
                addActionError(getText("forum.input.validation.invalidId"));
            }
        }
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public Forum getForum() {
        return forum;
    }

    public ForumRemote getForumRemote() {
        return forumRemote;
    }

    public void setForumRemote(ForumRemote forumRemote) {
        this.forumRemote = forumRemote;
    }

    public List<Forum> getForumList() {
        return forumList;
    }

    public void setForumList(List<Forum> forumList) {
        this.forumList = forumList;
    }

    public TopicRemote getTopicRemote() {
        return topicRemote;
    }

    public void setTopicRemote(TopicRemote topicRemote) {
        this.topicRemote = topicRemote;
    }

}
