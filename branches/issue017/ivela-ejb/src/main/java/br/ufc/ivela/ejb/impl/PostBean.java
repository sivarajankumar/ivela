/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.dao.Page;
import br.ufc.ivela.commons.model.AttachPost;
import br.ufc.ivela.commons.model.Topic;
import br.ufc.ivela.ejb.interfaces.PostRemote;
import br.ufc.ivela.ejb.*;
import br.ufc.ivela.commons.model.Post;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author nelson
 */
@Stateless(mappedName="PostBean")
public class PostBean implements PostRemote{

    private GenericDao<Post> daoPost = DaoFactory.getInstance(Post.class);
    private GenericDao<br.ufc.ivela.commons.model.File> daoFile = DaoFactory.getInstance(br.ufc.ivela.commons.model.File.class);
    private GenericDao<AttachPost> daoAttach = DaoFactory.getInstance(AttachPost.class);
    
    public Post get(Long postId) {
        return daoPost.get(postId); 
    }
    public br.ufc.ivela.commons.model.File getFile(Long fileId) {
        return daoFile.get(fileId);
    }
    
    public List<Post> getByTopic(Long topicId) {
        return daoPost.getByFK("topic.id", topicId);
    }

    public Collection<AttachPost> getAttachsByPost(Long postId){
        return (Collection<AttachPost>) daoAttach.getByFK("post.id", postId);
    }
    
    public Long add(Post post) {
        post.setCreatedAt(new Date());
        return (Long) daoPost.save(post);
    }

    public boolean remove(Long postId) {
        Collection<AttachPost> attachCollection = getAttachsByPost(postId);
        
        if (attachCollection != null) {   
            for (AttachPost attachPost : attachCollection) {
                File file = new File(attachPost.getFile().getPath());
                boolean del = file.delete();

                if (del == true){
                    daoFile.remove(attachPost.getFile());    
                }
                else{
                    //System.out.println("###denied permission to remove file!###");
                }
            }    
            daoAttach.removeAll(attachCollection);
        }

        return daoPost.remove(postId);
    }
    
    public Long addFile(File file, br.ufc.ivela.commons.model.File dbFile) {
        
        try {
            java.io.File f = new java.io.File(dbFile.getPath());
            InputStream data = new FileInputStream(file);
            OutputStream out = new FileOutputStream(f);
            byte buf[] = new byte[1024];
            int len;
            while ((len = data.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            data.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return (Long) daoFile.save(dbFile);       
    }

    public Long addAttach(AttachPost attachPost) {
        return (Long) daoAttach.save(attachPost);
    }

    public Post getLastPostByTopic(Long topicId) {
         Object[] params = new Object[]{topicId};
        
         List list = daoPost.find("from Post p where p.id = " +
                 "(select max(p.id) from Post p where p.topic.id = ?)", params);
         
         if(list != null && list.size() > 0){
             return (Post) list.get(0);
         } else {
             return null;
         }
    }

    public boolean isAccess(Long systemUser, Long course) {
        String query = "select p from Forum f, Enrollment e, Grade g, Topic t, Post p where " +
                "f.grade.id = e.grade.id and " +
                "e.systemUser.id = ? and " +
                "e.grade.id = g.id and " +
                "g.courseId = ? and t.forum.id = f.id and p.topic.id = p.id";
        Object[] params = new Object[]{ systemUser, course };
        List<Post> posts = daoPost.find(query, params);
        return (posts != null && posts.size() > 0);
    }

    public Page getPostList(Long systemUser, boolean isAdministrator, boolean isPublic, Long topic, int page, int pageSize) {
        if (page == 0) {
            page = 1;
        }
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
            countQuery = "select count(p.id) from Forum f, Topic t, Post p where t.id = " + topic + " and t.forum.id = f.id and p.topic.id = t.id";
            query = "select p from Forum f, Topic t, Post p where t.id = " + topic + " and t.forum.id = f.id and p.topic.id = t.id";            
        }
        
        Page p = new Page(query, countQuery, new Object[] { }, new Object[] { }, page, pageSize);

        return p;        
    }

    public List<Post> getPostList(Long systemUser, boolean isAdministrator, boolean isPublic, Long topic) {
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
        List<Post> posts = daoPost.find(query, new Object[] { });
        return posts;
    }

    public Post getPost(Long systemUser, boolean isAdministrator, Long post) {
        Post postObj = daoPost.get(post);
        if (isAdministrator)
            return postObj;
        else {
            Object[] params = new Object[] { post, systemUser };
            List<Post> posts = daoPost.find("select p from Forum f, Topic t, Post p, Enrollment e where f.id = t.forum.id and p.topic.id = t.id and p.id = ? and f.grade.id = e.grade.id and e.systemUser.id = ?", params);
            if (posts != null && posts.size() == 1)
                return posts.get(0);
        }
        return null;
    }
}