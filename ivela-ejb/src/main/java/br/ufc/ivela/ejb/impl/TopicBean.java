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
# File: TopicBean.java                                                                      #
# Document: Topic EJB                                                                       # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# ??-???-2008 - Leonardo / Emanuelle Vieira (UFC) - XXXXXX - Initial Version                #
# 10-SEP-2009 - otofuji (Instituto Eldorado)      - 000016 - Review Forum                   #
#############################################################################################
*/
package br.ufc.ivela.ejb.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.Page;
import br.ufc.ivela.commons.dao.interfaces.TopicDao;
import br.ufc.ivela.commons.model.Enrollment;
import br.ufc.ivela.commons.model.Forum;
import br.ufc.ivela.commons.model.Topic;
import br.ufc.ivela.ejb.interfaces.ForumRemote;
import br.ufc.ivela.ejb.interfaces.TopicRemote;

/**
 *
 * Class of ejb which implements the interface TopicLocal
 */
@Stateless(mappedName="TopicBean")
public class TopicBean implements TopicRemote {

    private TopicDao<Topic> daoTopic = (TopicDao) DaoFactory.getInstance(Topic.class);
    
    @EJB
    private ForumRemote forumRemote;
    
    public Topic get(Long id) {
        if (id == null) {
            return null;
        }
        return daoTopic.get(new Long(id));
    }

    public List<Topic> getByForum(Long forum) {
        return daoTopic.getByFK("forum.id", forum);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Long add(Topic topic) {
        topic.setCreatedAt(new Date());
        topic.setLastPostDate(new Date(1));
        
        Forum forum = forumRemote.get(topic.getForum().getId());
        forum.incrementTopicsCount();
        forumRemote.update(forum);
        
        return  (Long) daoTopic.save(topic);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean remove(Long id) {
        Topic topic = get(id);
        Forum forum = topic.getForum();
        forum.decrementTopicsCount();
        forumRemote.update(forum);
        return daoTopic.remove(id);
    }

    public Topic getLastTopicByForum(Long forumId) {
        return daoTopic.getLastTopicByForum(forumId);
    }

    public Page getByForumPage(Long forum, String title, int page, int pageSize) {
        if (page == 0)
            page = 1;
        if (title == null)
            title = "";
        title = "%" + title + "%";
        Object[] params = new Object[] { forum, title };
        String countQuery = "select count(id) from Topic t where t.forum.id = ? and t.title LIKE ?";
        String query = "from Topic t where t.forum.id = ? and t.title LIKE ?";
        
        Page p = new Page(query, countQuery, params, params, page, pageSize);
               
        return p;
    }
     
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean update(Topic topic) {
        topic.setCreatedAt(new Date());
        return daoTopic.update(topic);
    }
    
    /**
     * Return the (count) most recent topics of Public forums and from a list of Courses. 
     */
    public List<Topic> getRecentTopics(int count, List<Enrollment> gradeIds) {
       return daoTopic.getRecentTopics(count, gradeIds);
    }

    public boolean isAccess(Long systemUser, Long course) {
        String query = "select t from Forum f, Enrollment e, Grade g, Topic t where " +
                "f.grade.id = e.grade.id and " +
                "e.systemUser.id = ? and " +
                "e.grade.id = g.id and " +
                "g.courseId = ? and t.forum.id = f.id";
        Object[] params = new Object[]{ systemUser, course };
        List<Topic> topics = daoTopic.find(query, params);
        return (topics != null && topics.size() > 0);
    }


    public Topic getTopic(Long systemUser, boolean isAdministrator, Long topic) {        
        if (isAdministrator)
            return daoTopic.get(topic);
        else {
            Object[] params = new Object[] { topic, systemUser };
            List<Topic> topics = daoTopic.find("select t from Forum f, Topic t, Enrollment e where f.id = t.forum.id and t.id = ? and f.grade.id = e.grade.id and e.systemUser.id = ?", params);
            if (topics != null && topics.size() == 1)
                return topics.get(0);
        }
        return null;
    }

    public Page getTopicList(Long systemUser, boolean isAdministrator, boolean isPublic, Long forum, String title, int page, int pageSize) {
        String countQuery = daoTopic.getTopicListCountQuery(systemUser, isAdministrator, isPublic, forum, title);
        String query = daoTopic.getTopicListQuery(systemUser, isAdministrator, isPublic, forum, title);
        
        Page p = new Page(query, countQuery, new Object[] { }, new Object[] { }, page, pageSize);

        return p;
    }

    public List<Topic> getTopicList(Long systemUser, boolean isAdministrator, boolean isPublic, Long forum, String title) {        
        return daoTopic.getTopicList(systemUser, isAdministrator, isPublic, forum, title);
    }
}