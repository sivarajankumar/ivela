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
# File: PostBean.java                                                                       #
# Document: Post EJB                                                                        # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# ??-???-2008 - nelson (UFC)                      - XXXXXX - Initial Version                #
# 10-SEP-2009 - otofuji (Instituto Eldorado)      - 000016 - Review Forum                   #
#############################################################################################
*/
package br.ufc.ivela.ejb.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.dao.Page;
import br.ufc.ivela.commons.dao.interfaces.PostDao;
import br.ufc.ivela.commons.model.AttachPost;
import br.ufc.ivela.commons.model.Post;
import br.ufc.ivela.commons.model.Topic;
import br.ufc.ivela.ejb.interfaces.PostRemote;
import br.ufc.ivela.ejb.interfaces.TopicRemote;


@Stateless(mappedName="PostBean")
public class PostBean implements PostRemote {

    private PostDao<Post> daoPost = (PostDao) DaoFactory.getInstance(Post.class);
    private GenericDao<br.ufc.ivela.commons.model.File> daoFile = DaoFactory.getInstance(br.ufc.ivela.commons.model.File.class);
    private GenericDao<AttachPost> daoAttach = DaoFactory.getInstance(AttachPost.class);
    
    @EJB
    private TopicRemote topicRemote;
    
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

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Long add(Post post) {
        Topic topic = topicRemote.get(post.getTopic().getId());        
        post.setCreatedAt(new Date());
        topic.incrementPostsCount();          
        topic.setLastPost(post);
        topic.setLastPostDate(post.getCreatedAt());
        Long result = (Long) daoPost.save(post);
        topicRemote.update(topic);
        return result;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Long add(Post post, File[] files, br.ufc.ivela.commons.model.File[] modelFile) {
        Long postId = (Long) add(post);
        post = get(postId);
        
        if (files != null && modelFile != null) {
            for (int i = 0; i < files.length; i++) {
                if (post.getTopic().getForum().getGrade() != null) {
                    modelFile[i].setCourseId(post.getTopic().getForum().getGrade().getCourseId());
                    modelFile[i].setGrade(post.getTopic().getForum().getGrade());
                } else { 
                    modelFile[i].setCourseId(post.getTopic().getForum().getCourse().getId());
                }
                modelFile[i].setSentBy(post.getCreatedBy());
                Long idFile = addFile(files[i], modelFile[i]);
                if (idFile != null) {
                    br.ufc.ivela.commons.model.File f = getFile(idFile);
                    AttachPost attachPost = new AttachPost();
                    attachPost.setFile(f);
                    attachPost.setPost(post);
                    addAttach(attachPost);
                }
            }
        }
        
        return postId;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean remove(Long postId) {
        
        Collection<AttachPost> attachCollection = getAttachsByPost(postId);
        Post post = get(postId);
        Topic topic = post.getTopic();
        topic = topicRemote.get(topic.getId());        
        topic.decrementPostsCount();        
        
        Post lastPost = topic.getLastPost();
        
        if (post.getId().equals(lastPost.getId())) {
            List<Post> list = daoPost.getPostList(topic.getId());
            if (list.size() > 1) {
                topic.setLastPost(list.get(list.size() - 2));
                topic.setLastPostDate(list.get(list.size() - 2).getCreatedAt());
            } else {
                topic.setLastPost(null);
                topic.setLastPostDate(new Date(1));    
            }
            
        }        
        
        if (attachCollection != null) {            
            for (AttachPost attachPost : attachCollection) {
                File file = new File(attachPost.getFile().getPath());
                boolean del = daoFile.remove(attachPost.getFile());                
                if (del == true){
                    try {
                        file.delete();
                    } catch (Exception e) {
                        
                    }
                }                
            }                
        }
        
        boolean result = topicRemote.update(topic);

        result = daoPost.remove(postId);         
                    
        return result;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
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
        String countQuery = daoPost.getPostListCountQuery(systemUser, isAdministrator, isPublic, topic);
        String query = daoPost.getPostListQuery(systemUser, isAdministrator, isPublic, topic);
        
        Page p = new Page(query, countQuery, new Object[] { }, new Object[] { }, page, pageSize);

        return p;
    }

    public List<Post> getPostList(Long systemUser, boolean isAdministrator, boolean isPublic, Long topic) {                
        return daoPost.getPostList(systemUser, isAdministrator, isPublic, topic);
    }

    public Post getPost(Long systemUser, boolean isAdministrator, Long post) {        
        if (isAdministrator)
            return daoPost.get(post);
        else {
            Object[] params = new Object[] { post, systemUser };
            List<Post> posts = daoPost.find("select p from Forum f, Topic t, Post p, Enrollment e where f.id = t.forum.id and p.topic.id = t.id and p.id = ? and f.grade.id = e.grade.id and e.systemUser.id = ?", params);
            if (posts != null && posts.size() == 1)
                return posts.get(0);
        }
        return null;
    }
}