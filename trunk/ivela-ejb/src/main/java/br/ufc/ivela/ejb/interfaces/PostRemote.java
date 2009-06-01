/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.dao.Page;
import br.ufc.ivela.commons.model.AttachPost;
import br.ufc.ivela.commons.model.Post;
import br.ufc.ivela.commons.model.Topic;
import java.io.File;
import java.util.Collection;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author nelson
 */
@Remote
public interface PostRemote {

    public Post get(Long postId);
    public br.ufc.ivela.commons.model.File getFile(Long fileId);
    public List<Post> getByTopic(Long topicId);
    public Collection<AttachPost> getAttachsByPost(Long postId);
    public Long add(Post post);
    public boolean remove(Long postId);
    public Long addFile(File file, br.ufc.ivela.commons.model.File dbFile);
    public Long addAttach(AttachPost attachPost);
    public Post getLastPostByTopic(Long topicId);
    public boolean isAccess(Long systemUser, Long course);
    
    
    public Page getPostList(Long systemUser, boolean isAdministrator, boolean isPublic, Long topic, int page, int pageSize);
    
    public List<Post> getPostList(Long systemUser, boolean isAdministrator, boolean isPublic, Long topic);
    
    public Post getPost(Long systemUser, boolean isAdministrator, Long post);
}
