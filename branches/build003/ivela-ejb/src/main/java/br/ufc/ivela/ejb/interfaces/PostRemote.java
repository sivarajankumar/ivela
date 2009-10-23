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
# File: PostRemote.java                                                                     #
# Document: Post Facade                                                                     # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# ??-???-2008 - nelson (UFC)                      - XXXXXX - Initial Version                #
# 10-SEP-2009 - otofuji (Instituto Eldorado)      - 000016 - Review Forum                   #
#############################################################################################
*/
package br.ufc.ivela.ejb.interfaces;

import java.io.File;
import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.ufc.ivela.commons.dao.Page;
import br.ufc.ivela.commons.model.AttachPost;
import br.ufc.ivela.commons.model.Post;

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
    public Long add(Post post, File[] files, br.ufc.ivela.commons.model.File[] modelFile);
    
    public Page getPostList(Long systemUser, boolean isAdministrator, boolean isPublic, Long topic, int page, int pageSize);
    
    public List<Post> getPostList(Long systemUser, boolean isAdministrator, boolean isPublic, Long topic);
    
    public Post getPost(Long systemUser, boolean isAdministrator, Long post);
}
