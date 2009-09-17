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
# File: PostDaoImpl.java                                                                    #
# Document: Post Dao Implementation                                                         # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 10-SEP-2009 - otofuji (Instituto Eldorado)      - 000016 - Initial Creation               #
#############################################################################################
*/
package br.ufc.ivela.commons.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import br.ufc.ivela.commons.dao.interfaces.PostDao;
import br.ufc.ivela.commons.model.Post;

@Transactional
public class PostDaoImpl<T> extends GenericDaoImpl<T> implements PostDao<T> {
    
    public String getPostListCountQuery(Long systemUser, boolean isAdministrator,boolean isPublic, Long topic) {    
        String countQuery = "";
        countQuery = "select count(pp.id) from Post pp where pp.id " +
            "in (" +
            "select distinct p.id from Forum f, Topic t, Post p, Enrollment e, Grade g, Course c where ";
        if (isPublic)
            countQuery +="(t.id = " + topic + " and t.forum.id = f.id and p.topic.id = t.id and f.public1 = " + isPublic + ") or ";
            countQuery +="(t.id = " + topic + " and t.forum.id = f.id and p.topic.id = t.id and " +
                "f.grade.id = e.grade.id and " +
                "e.systemUser.id = " + systemUser + " and " +
                "e.grade.id = g.id and " +
                "g.courseId = c.id and " +
                "f.public1 = false)" +
                ")";    
        if (isAdministrator) {
            countQuery = "select count(p.id) from Forum f, Topic t, Post p where t.id = " + topic + " and t.forum.id = f.id and p.topic.id = t.id";                   
        }
        
        return countQuery;
    }    

    public String getPostListQuery(Long systemUser, boolean isAdministrator,
            boolean isPublic, Long topic) {
        String query = "";
        query = "select pp from Post pp where pp.id " +
                "in (" +
                "select distinct p.id from Forum f, Topic t, Post p, Enrollment e, Grade g, Course c where ";
        if (isPublic)
            query +="(t.id = " + topic + " and t.forum.id = f.id and p.topic.id = t.id and f.public1 = " + isPublic + ") or ";
        query +="(t.id = " + topic + " and t.forum.id = f.id and p.topic.id = t.id and " +
                "f.grade.id = e.grade.id and " +
                "e.systemUser.id = " + systemUser + " and " +
                "e.grade.id = g.id and " +
                "g.courseId = c.id and " +
                "f.public1 = false)" +
                ")";
        if (isAdministrator) {
            query = "select p from Forum f, Topic t, Post p where t.id = " + topic + " and t.forum.id = f.id and p.topic.id = t.id";
        }
        return query;
    }
    
    public List<Post> getPostList(Long systemUser, boolean isAdministrator,
            boolean isPublic, Long topic) {
        return find(getPostListQuery(systemUser, isAdministrator, isPublic, topic), new Object[] {});
    }
    
    public List<Post> getPostList(Long topic) {
        return find("select pp from Post pp where pp.topic.id = " + topic + " order by pp.createdAt", new Object[] {});
    }
}
