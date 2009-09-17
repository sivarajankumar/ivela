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
# File: ForumDaoImpl.java                                                                   #
# Document: Forum Dao Implementation                                                        # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 10-SEP-2009 - otofuji (Instituto Eldorado)      - 000016 - Initial Creation               #
#############################################################################################
*/
package br.ufc.ivela.commons.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import br.ufc.ivela.commons.dao.interfaces.ForumDao;
import br.ufc.ivela.commons.model.Forum;

@Transactional
public class ForumDaoImpl<T> extends GenericDaoImpl<T> implements ForumDao<T> {
   
    public Page getForumListPageBySystemUser(Long systemUser, String title, int page, int pageSize) {
        if (title == null) {
            title = "";
        }
        title = "%" + title + "%";
        Object[] params = new Object[]{systemUser, title};
        String countQuery = "select count(id) from Forum f where f.grade.id IN (select g.id from Enrollment e, Grade g, SystemUser su where su.id = ? and e.systemUser.id = su.id and e.grade.id = g.id) and f.title LIKE ?";
        String query = "from Forum f where f.grade.id IN (select g.id from Enrollment e, Grade g, SystemUser su where su.id = ? and e.systemUser.id = su.id and e.grade.id = g.id) and f.title LIKE ?";

        Page p = new Page(query, countQuery, params, params, page, pageSize);

        return p;
    }
    
    public List<Forum> getForumListBySystemUser(Long systemUser) {
        Object[] params = new Object[]{systemUser};
        
        return find("from Forum f where f.grade.id = (select g.id from Enrollment e, Grade g, SystemUser su where su.id = ? and e.systemUser.id = su.id and e.grade.id = g.id)", params);
    }
    
    public List<Forum> getForumListBySystemUserGrade(Long systemUser,
            Long gradeId) {
        Object[] params = new Object[] { systemUser, gradeId };
        String query = "from Forum f where f.grade.id = (select g.id from Enrollment e, Grade g, SystemUser su where ";

        if (systemUser != null && systemUser > 0)
            query += "su.id = ? and ";
        query += "e.systemUser.id = su.id and e.grade.id = g.id and g.id = ?)";

        return find(query, params);        
    }
    
    public Page getForumListPageBySystemUserGrade(Long systemUser, Long grade, String title, int page, int pageSize) {        
        if (title == null) {
            title = "";
        }
        title = "%" + title + "%";
        Object[] params = new Object[]{systemUser, grade, title};
        String countQuery = "select count(id) from Forum f where f.grade.id = (select g.id from Enrollment e, Grade g, SystemUser su where ";
        
        if (systemUser != null && systemUser > 0)
            countQuery+= "su.id = ? and ";
        
        countQuery += "e.systemUser.id = su.id and e.grade.id = g.id and g.id = ?) and f.title LIKE ?";
        
        String query = "from Forum f where f.grade.id = (select g.id from Enrollment e, Grade g, SystemUser su where ";
        if (systemUser != null && systemUser > 0)
            query+= "su.id = ? and ";
        
        query += "e.systemUser.id = su.id and e.grade.id = g.id and g.id = ?) and f.title LIKE ?";
                  
        Page p = new Page(query, countQuery, params, params, page, pageSize);

        return p;
    }
    
    public String getForumListCountQuery(Long systemUser, Long course, boolean isAdministrator, boolean isPublic, String title) {        
        if (title == null) {
            title = "";
        }
        title = "%" + title + "%";

        String countQuery = "";
        countQuery = "select count(ff.id) from Forum ff where ff.id " +
                "in (" +
                "select distinct f.id from Forum f, Enrollment e, Grade g, Course c where ";
        if (isPublic) {
            countQuery += "(f.title LIKE '" + title + "' and f.public1 = " + isPublic + ") or ";
        }
        countQuery += "(" +
                "f.grade.id = e.grade.id and " +
                "e.systemUser.id = " + systemUser + " and " +
                "e.grade.id = g.id and " +
                "g.courseId = c.id and ";
        
        if (course != null && course > 0)
            countQuery += "c.id = " + course + " and ";
        
        countQuery += "f.title LIKE '" + title + "' and f.public1 = false)" + ")";
        
       
        if (isAdministrator) {
            countQuery = "select count(f.id) from Forum f where f.title LIKE '" + title + "'";            
        }      

        return countQuery;
    }
    
    public String getForumListQuery(Long systemUser, Long course, boolean isAdministrator, boolean isPublic, String title) {
        if (title == null) {
            title = "";
        }        
        title = "%" + title + "%";
        String query = "";
        query = "select ff from Forum ff where ff.id " +
                "in (" +
                "select distinct f.id from Forum f, Enrollment e, Grade g, Course c where ";
        if (isPublic) {
            query += "(f.title LIKE '" + title + "' and f.public1 = " + isPublic + ") or ";
        }
        query += "(" +
                "f.grade.id = e.grade.id and " +
                "e.systemUser.id = " + systemUser + " and " +
                "e.grade.id = g.id and " +
                "g.courseId = c.id and ";
        
        if (course != null && course > 0)
            query += "c.id = " + course + " and ";
        
        query += "f.title LIKE '" + title + "' and f.public1 = false)" +
                ")";
        
        if (isAdministrator) {
            query = "select f from Forum f where f.title LIKE '" + title + "'";
        }   
        
        return query;
    }
    
    public List<Forum> getForumList(Long systemUser, Long course, boolean isAdministrator, boolean isPublic, String title) {               
        return find(getForumListQuery(systemUser,course,isAdministrator, isPublic, title), new Object[]{});
    }
    
    
}
