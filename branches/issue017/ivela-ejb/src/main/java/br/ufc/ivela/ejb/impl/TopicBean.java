/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.dao.Page;
import br.ufc.ivela.commons.model.Topic;
import br.ufc.ivela.ejb.interfaces.TopicRemote;
import br.ufc.ivela.ejb.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Leonardo Oliveira Moreira / Emanuelle Vieira
 * 
 * Class of ejb which implements the interface TopicLocal
 */
@Stateless(mappedName="TopicBean")
public class TopicBean implements TopicRemote {

    private GenericDao<Topic> daoTopic = DaoFactory.getInstance(Topic.class);
    
    public Topic get(Long id) {
        if (id == null) {
            return null;
        }
        return daoTopic.get(new Long(id));
    }

    public List<Topic> getByForum(Long forum) {
        return daoTopic.getByFK("forum.id", forum);
    }

    public Long add(Topic topic) {
        topic.setCreatedAt(new Date());
        
        return  (Long) daoTopic.save(topic);
    }

    public boolean remove(Long id) {
        return daoTopic.remove(id);
    }

    public Topic getLastTopicByForum(Long forumId) {
        Object[] params = new Object[]{forumId};
        
         List list = daoTopic.find("from Topic t where t.id = " +
                 "(select max(t.id) from Topic t where t.forum.id = ?)", params);
         
         if(list != null && list.size() > 0){
             return (Topic) list.get(0);
         } else {
             return null;
         }
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
    
    public boolean update(Topic topic) {
        return daoTopic.update(topic);
    }
    
    /**
     * Return the (count) most recent topics from a given forum Id. 
     * 
     */
    public List<Topic> getRecentTopics(int count) {
                
        //List<Topic> results = daoTopic.find("select t from Post p, Topic t " +
        //        "where p.topic.id = t.id and t.forum.public1 = true " +
        //        "order by p.createdAt desc", new Object[]{});
        List<Topic> results = daoTopic.find("select t from Topic t " +
                "where t.forum.public1 = true " +
                "order by t.createdAt desc", new Object[]{});
        
        if(results!=null && results.size() > count){
            return new ArrayList<Topic>(results.subList(0, count));
        } else {
            return results;
        }
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
        Topic topicObj = daoTopic.get(topic);
        if (isAdministrator)
            return topicObj;
        else {
            Object[] params = new Object[] { topic, systemUser };
            List<Topic> topics = daoTopic.find("select t from Forum f, Topic t, Enrollment e where f.id = t.forum.id and t.id = ? and f.grade.id = e.grade.id and e.systemUser.id = ?", params);
            if (topics != null && topics.size() == 1)
                return topics.get(0);
        }
        return null;
    }

    public Page getTopicList(Long systemUser, boolean isAdministrator, boolean isPublic, Long forum, String title, int page, int pageSize) {
        if (page == 0) {
            page = 1;
        }
        if (title == null) {
            title = "";
        }
        title = "%" + title + "%";
        String countQuery = "";
        countQuery = "select count(tt.id) from Topic tt where tt.id " +
                "in (" +
                "select distinct t.id from Forum f, Topic t, Enrollment e, Grade g, Course c where ";
        if (isPublic)
            countQuery +="(f.id = " + forum + " and t.forum.id = f.id and f.title LIKE '" + title + "' and f.public1 = " + isPublic + ") or ";
        countQuery +="(f.id = " + forum + " and t.forum.id = f.id and " +
                "f.grade.id = e.grade.id and " +
                "e.systemUser.id = " + systemUser + " and " +
                "e.grade.id = g.id and " +
                "g.courseId = c.id and " +
                "f.title LIKE '" + title + "' and f.public1 = false)" +
                ")";
        String query = "";
        query = "select tt from Topic tt where tt.id " +
                "in (" +
                "select distinct t.id from Forum f, Topic t, Enrollment e, Grade g, Course c where ";
        if (isPublic)
            query +="(f.id = " + forum + " and t.forum.id = f.id and f.title LIKE '" + title + "' and f.public1 = " + isPublic + ") or ";
        query +="(f.id = " + forum + " and t.forum.id = f.id and " +
                "f.grade.id = e.grade.id and " +
                "e.systemUser.id = " + systemUser + " and " +
                "e.grade.id = g.id and " +
                "g.courseId = c.id and " +
                "f.title LIKE '" + title + "' and f.public1 = false)" +
                ")";
        if (isAdministrator) {
            countQuery = "select count(t.id) from Forum f, Topic t where f.id = " + forum + " and t.forum.id = f.id and t.title LIKE '" + title + "'";
            query = "select t from Forum f, Topic t where f.id = " + forum + " and t.forum.id = f.id and t.title LIKE '" + title + "'";            
        }
        
        Page p = new Page(query, countQuery, new Object[] { }, new Object[] { }, page, pageSize);

        return p;
    }

    public List<Topic> getTopicList(Long systemUser, boolean isAdministrator, boolean isPublic, Long forum, String title) {
        if (title == null) {
            title = "";
        }
        title = "%" + title + "%";
        String query = "";
        query = "select tt from Topic tt where tt.id " +
                "in (" +
                "select distinct t.id from Forum f, Topic t, Enrollment e, Grade g, Course c where ";
        if (isPublic)
            query +="(f.id = " + forum + " and t.forum.id = f.id and f.title LIKE '" + title + "' and f.public1 = " + isPublic + ") or ";
        query +="(f.id = " + forum + " and t.forum.id = f.id and " +
                "f.grade.id = e.grade.id and " +
                "e.systemUser.id = " + systemUser + " and " +
                "e.grade.id = g.id and " +
                "g.courseId = c.id and " +
                "f.title LIKE '" + title + "' and f.public1 = false)" +
                ")";
        if (isAdministrator) {
            query = "select t from Forum f, Topic t where f.id = " + forum + " and t.forum.id = f.id and t.title LIKE '" + title + "'";
        }
        List<Topic> topics = daoTopic.find(query, new Object[] { });
        return topics;
    }
}