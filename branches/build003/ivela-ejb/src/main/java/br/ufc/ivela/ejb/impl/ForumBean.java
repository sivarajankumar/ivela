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
# File: ForumBean.java                                                                      #
# Document: Forum EJB                                                                       # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# ??-???-2008 - Leonardo Moreira (UFC)            - XXXXXX - Initial Version                #
# 10-SEP-2009 - otofuji (Instituto Eldorado)      - 000016 - Review Forum                   #
#############################################################################################
*/
package br.ufc.ivela.ejb.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.Page;
import br.ufc.ivela.commons.dao.interfaces.ForumDao;
import br.ufc.ivela.commons.model.Forum;
import br.ufc.ivela.ejb.interfaces.ForumRemote;

/** 
 * Class of ejb which implements the interface ForumLocal
 */
@Stateless(mappedName = "ForumBean")
public class ForumBean implements ForumRemote {

    private ForumDao<Forum> daoForum = (ForumDao) DaoFactory.getInstance(Forum.class);

    public Forum get(Long id) {
        if (id == null) {
            return null;
        }
        return daoForum.get(id);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Long add(Forum forum) {
        return (Long) daoForum.save(forum);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean remove(Long id) {
        return daoForum.remove(id);
    }

    public List<Forum> getAll() {
        return daoForum.getAll();
    }

    public List<Forum> getForumListBySystemUser(Long systemUser) {        
        List list = daoForum.getForumListBySystemUser(systemUser);
        if (list == null) {
            list = new ArrayList();
        }
        return list;
    }

    public List<Forum> getOpenedForumList() {
        Object[] params = new Object[]{new Boolean(true)};
        List list = daoForum.find("from Forum f where f.public1 = ?", params);
        if (list == null) {
            list = new ArrayList();
        }
        return list;
    }

    public Page getForumListPageBySystemUser(Long systemUser, String title, int page, int pageSize) {
        return daoForum.getForumListPageBySystemUser(systemUser, title, page, pageSize);
    }

    public Page getPublicForumListPage(String title, int page, int pageSize) {
        if (page == 0) {
            page = 1;
        }
        if (title == null) {
            title = "";
        }
        title = "%" + title + "%";
        Object[] params = new Object[]{new Boolean(true), title};
        String countQuery = "select count(id) from Forum f where f.public1 = ? and f.title LIKE ?";
        String query = "from Forum f where f.public1 = ? and f.title LIKE ?";

        Page p = new Page(query, countQuery, params, params, page, pageSize);

        return p;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean update(Forum forum) {                
        return daoForum.update(forum);
    }

    public List<Forum> getForumListBySystemUserGrade(Long systemUser, Long gradeId) {
        List list = daoForum.getForumListBySystemUserGrade(systemUser, gradeId);
        if (list == null) {
            list = new ArrayList();
        }        
        return list;
    }
   
    public List<Forum> getForumListByGrade(Long gradeId) {
        List list = daoForum.getForumListBySystemUserGrade(null, gradeId);
        if (list == null) {
            list = new ArrayList();
        }        
        return list;
    }

    public Page getForumListPageBySystemUserGrade(Long systemUser, Long grade, String title, int page, int pageSize) {        
        return daoForum.getForumListPageBySystemUserGrade(systemUser, grade, title, page, pageSize);
    }

    public Page getForumListPageByGrade(Long grade, String title, int page, int pageSize) {
        return daoForum.getForumListPageBySystemUserGrade(null, grade, title, page, pageSize);
    }

    public List<Forum> getLastRecords(String fieldToOrder, int orderType, int number) {
        return daoForum.getLastRecords(fieldToOrder, orderType, number);
    }

    public boolean isAccess(Long systemUser, Long course) {
        String query = "select f from Forum f, Enrollment e, Grade g where " +
                "f.grade.id = e.grade.id and " +
                "e.systemUser.id = ? and " +
                "e.grade.id = g.id and " +
                "g.courseId = ?";        
        Object[] params = new Object[]{systemUser, course};
        List<Forum> forums = daoForum.find(query, params);
        return (forums != null && forums.size() > 0);
    }

    public Page getAllForumListPage(String title, int page, int pageSize) {
        if (page == 0) {
            page = 1;
        }
        if (title == null) {
            title = "";
        }
        title = "%" + title + "%";
        Object[] params = new Object[]{title};
        String countQuery = "select count(id) from Forum f where f.title LIKE ?";
        String query = "from Forum f where f.title LIKE ?";

        Page p = new Page(query, countQuery, params, params, page, pageSize);

        return p;
    }
    
    public Page getForumList(Long systemUser, boolean isAdministrator, boolean isPublic, String title, int page, int pageSize) {
        String countQuery = daoForum.getForumListCountQuery(systemUser, null, isAdministrator, isPublic, title);
        String query = daoForum.getForumListQuery(systemUser, null, isAdministrator, isPublic, title);
        
        Page p = new Page(query, countQuery, new Object[]{}, new Object[]{}, page, pageSize);

        return p;                
    }
        
    public List<Forum> getForumList(Long systemUser, boolean isAdministrator, boolean isPublic, String title) {
        return daoForum.getForumList(systemUser, null, isAdministrator, isPublic, title);
    }

    public Forum getForum(Long systemUser, boolean isAdministrator, Long forum) {        
        if (isAdministrator) {
            Forum forumObj = daoForum.get(forum);
            return forumObj;
        } else {
            Object[] params = new Object[]{forum, systemUser};
            List<Forum> forums = daoForum.find("select f from Forum f, Enrollment e where f.id = ? and f.grade.id = e.grade.id and e.systemUser.id = ?", params);
            if (forums != null && forums.size() == 1) {
                return forums.get(0);
            }
        }
        return null;
    }
    
    public Page getForumList(Long systemUser, Long course, boolean isAdministrator, boolean isPublic, String title, int page, int pageSize) {
        String countQuery = daoForum.getForumListCountQuery(systemUser, course, isAdministrator, isPublic, title);
        String query = daoForum.getForumListQuery(systemUser, course, isAdministrator, isPublic, title);
        
        Page p = new Page(query, countQuery, new Object[]{}, new Object[]{}, page, pageSize);

        return p;        
    }
        
    public List<Forum> getForumList(Long systemUser, Long course, boolean isAdministrator, boolean isPublic, String title) {
        return daoForum.getForumList(systemUser, course, isAdministrator, isPublic, title);
    }
}
