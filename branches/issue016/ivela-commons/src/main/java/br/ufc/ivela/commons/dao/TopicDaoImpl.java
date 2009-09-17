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
# File: TopicDaoImpl.java                                                                   #
# Document: Topic Dao Implementation                                                        # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 10-SEP-2009 - otofuji (Instituto Eldorado)      - 000016 - Initial Creation               #
#############################################################################################
*/
package br.ufc.ivela.commons.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import br.ufc.ivela.commons.dao.interfaces.TopicDao;
import br.ufc.ivela.commons.model.Enrollment;
import br.ufc.ivela.commons.model.Topic;

@Transactional
public class TopicDaoImpl<T> extends GenericDaoImpl<T> implements TopicDao<T> {

    public Topic getLastTopicByForum(Long forumId) {
        Object[] params = new Object[]{forumId};
        
         List list = find("from Topic t where t.id = " +
                 "(select max(t.id) from Topic t where t.forum.id = ?)", params);
         
         if(list != null && list.size() > 0){
             return (Topic) list.get(0);
         } else {
             return null;
         }
    }
    
    public List<Topic> getRecentTopics(int count, List<Enrollment> gradeIds) {
        
        String whereGrade = ""; 
        if (gradeIds != null && !gradeIds.isEmpty()) {
            StringBuilder where = new StringBuilder("or t.forum.grade.id in(");
            for (Enrollment enroll: gradeIds) {
                where.append(enroll.getGrade().getId());
                where.append(',');
            }
            where.deleteCharAt(where.length() - 1);
            where.append(')');
            where.append(' ');
            whereGrade = where.toString();
        }

        //List<Topic> results = daoTopic.find("select t from Post p, Topic t " +
        //        "where p.topic.id = t.id and t.forum.public1 = true " +
        //        "order by p.createdAt desc", new Object[]{});
        List<Topic> results = find("select t from Topic t " +
                "where t.forum.public1 = true " + whereGrade +
                "order by t.createdAt desc", new Object[]{});
        
        if(results!=null && results.size() > count){
            return new ArrayList<Topic>(results.subList(0, count));
        } else {
            return results;
        }
    }
    
    public String getTopicListCountQuery(Long systemUser, boolean isAdministrator, boolean isPublic, Long forum, String title) {
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
        if (isAdministrator) {
            countQuery = "select count(t.id) from Forum f, Topic t where f.id = " + forum + " and t.forum.id = f.id and t.title LIKE '" + title + "'";                  
        }
        
        return countQuery;
    }
    
    public String getTopicListQuery(Long systemUser, boolean isAdministrator, boolean isPublic, Long forum, String title) {
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
                ") order by tt.lastPostDate desc, tt.createdAt desc";
        if (isAdministrator) {
            query = "select t from Forum f, Topic t where f.id = " + forum + " and t.forum.id = f.id and t.title LIKE '" + title + "'" + " order by t.createdAt desc, t.lastPostDate desc";
        }       
        return query;
    }
    
    public List<Topic> getTopicList(Long systemUser, boolean isAdministrator, boolean isPublic, Long forum, String title) {        
        return find(getTopicListQuery(systemUser, isAdministrator, isPublic, forum, title), new Object[] { });
    }
    
}
