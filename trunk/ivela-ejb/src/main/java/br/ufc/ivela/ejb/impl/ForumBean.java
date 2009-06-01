/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.dao.Page;
import br.ufc.ivela.ejb.interfaces.ForumRemote;
import br.ufc.ivela.ejb.*;
import br.ufc.ivela.commons.model.Forum;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Class of ejb which implements the interface ForumLocal
 */
@Stateless(mappedName = "ForumBean")
public class ForumBean implements ForumRemote {

    private GenericDao<Forum> daoForum = DaoFactory.getInstance(Forum.class);

    public Forum get(Long id) {
        if (id == null) {
            return null;
        }
        return daoForum.get(id);
    }

    public Long add(Forum forum) {
        return (Long) daoForum.save(forum);
    }

    public boolean remove(Long id) {
        return daoForum.remove(id);
    }

    public List<Forum> getAll() {
        return daoForum.getAll();
    }

    public List<Forum> getForumListBySystemUser(Long systemUser) {
        Object[] params = new Object[]{systemUser};
        List list = daoForum.find("from Forum f where f.grade.id = (select g.id from Enrollment e, Grade g, SystemUser su where su.id = ? and e.systemUser.id = su.id and e.grade.id = g.id)", params);
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

    public List<Forum> getForumListBySystemUserGrade(Long systemUser, Long gradeId) {
        Object[] params = new Object[]{systemUser, gradeId};
        List list = daoForum.find("from Forum f where f.grade.id = (select g.id from Enrollment e, Grade g, SystemUser su where su.id = ? and e.systemUser.id = su.id and e.grade.id = g.id and g.id = ?)", params);
        if (list == null) {
            list = new ArrayList();
        }
        return list;
    }

    public Page getForumListPageBySystemUser(Long systemUser, String title, int page, int pageSize) {
        if (page == 0) {
            page = 1;
        }
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

    public boolean update(Forum forum) {
        return daoForum.update(forum);
    }

    public List<Forum> getForumListByGrade(Long gradeId) {
        Object[] params = new Object[]{gradeId};
        List list = daoForum.find("from Forum f where f.grade.id = (select g.id from Enrollment e, Grade g, SystemUser su where e.systemUser.id = su.id and e.grade.id = g.id and g.id = ?)", params);
        if (list == null) {
            list = new ArrayList();
        }
        return list;
    }

    public Page getForumListPageBySystemUserGrade(Long systemUser, Long grade, String title, int page, int pageSize) {
        if (page == 0) {
            page = 1;
        }
        if (title == null) {
            title = "";
        }
        title = "%" + title + "%";
        Object[] params = new Object[]{systemUser, grade, title};
        String countQuery = "select count(id) from Forum f where f.grade.id = (select g.id from Enrollment e, Grade g, SystemUser su where su.id = ? and e.systemUser.id = su.id and e.grade.id = g.id and g.id = ?) and f.title LIKE ?";
        String query = "from Forum f where f.grade.id = (select g.id from Enrollment e, Grade g, SystemUser su where su.id = ? and e.systemUser.id = su.id and e.grade.id = g.id and g.id = ?) and f.title LIKE ?";

        Page p = new Page(query, countQuery, params, params, page, pageSize);

        return p;
    }

    public Page getForumListPageByGrade(Long grade, String title, int page, int pageSize) {
        if (page == 0) {
            page = 1;
        }
        if (title == null) {
            title = "";
        }
        title = "%" + title + "%";
        Object[] params = new Object[]{grade, title};
        String countQuery = "select count(id) from Forum f where f.grade.id = (select g.id from Enrollment e, Grade g, SystemUser su where e.systemUser.id = su.id and e.grade.id = g.id and g.id = ?) and f.title LIKE ?";
        String query = "from Forum f where f.grade.id = (select g.id from Enrollment e, Grade g, SystemUser su where e.systemUser.id = su.id and e.grade.id = g.id and g.id = ?) and f.title LIKE ?";

        Page p = new Page(query, countQuery, params, params, page, pageSize);

        return p;
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
        if (page == 0) {
            page = 1;
        }
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
                "g.courseId = c.id and " +
                "f.title LIKE '" + title + "' and f.public1 = false)" +
                ")";
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
                "g.courseId = c.id and " +
                "f.title LIKE '" + title + "' and f.public1 = false)" +
                ")";
        if (isAdministrator) {
            countQuery = "select count(f.id) from Forum f where f.title LIKE '" + title + "'";
            query = "select f from Forum f where f.title LIKE '" + title + "'";
        }

        Page p = new Page(query, countQuery, new Object[]{}, new Object[]{}, page, pageSize);

        return p;
    }

    public List<Forum> getForumList(Long systemUser, boolean isAdministrator, boolean isPublic, String title) {
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
                "g.courseId = c.id and " +
                "f.title LIKE '" + title + "' and f.public1 = false)" +
                ")";
        if (isAdministrator) {
            query = "select f from Forum f where f.title LIKE '" + title + "'";
        }
        List<Forum> forums = daoForum.find(query, new Object[]{});
        return forums;
    }

    public Forum getForum(Long systemUser, boolean isAdministrator, Long forum) {
        Forum forumObj = daoForum.get(forum);
        if (isAdministrator) {
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
        if (page == 0) {
            page = 1;
        }
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
                "g.courseId = c.id and " +
                "c.id = " + course + " and " +
                "f.title LIKE '" + title + "' and f.public1 = false)" +
                ")";
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
                "g.courseId = c.id and " +
                "c.id = " + course + " and " +
                "f.title LIKE '" + title + "' and f.public1 = false)" +
                ")";
        if (isAdministrator) {
            countQuery = "select count(f.id) from Forum f where f.title LIKE '" + title + "'";
            query = "select f from Forum f where f.title LIKE '" + title + "'";
        }

        Page p = new Page(query, countQuery, new Object[]{}, new Object[]{}, page, pageSize);

        return p;
    }

    public List<Forum> getForumList(Long systemUser, Long course, boolean isAdministrator, boolean isPublic, String title) {
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
                "g.courseId = c.id and " +
                "c.id = " + course + " and " +
                "f.title LIKE '" + title + "' and f.public1 = false)" +
                ")";
        if (isAdministrator) {
            query = "select f from Forum f where f.title LIKE '" + title + "'";
        }
        List<Forum> forums = daoForum.find(query, new Object[]{});
        return forums;
    }
}
