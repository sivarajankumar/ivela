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
# File: Topic.java                                                                          #
# Document: Topic Model                                                                     # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# ??-???-2008 - leoomoreira (UFC)                 - XXXXXX - Initial Version                #
# 01-SEP-2009 - otofuji (Instituto Eldorado)      - 000016 - Review Forum                   #
#############################################################################################
*/

package br.ufc.ivela.commons.model;

import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author leoomoreira
 */
@Entity
@Table(name = "topic")
@NamedQueries({@NamedQuery(name = "Topic.findById", query = "SELECT t FROM Topic t WHERE t.id = :id"), @NamedQuery(name = "Topic.findByTitle", query = "SELECT t FROM Topic t WHERE t.title = :title"), @NamedQuery(name = "Topic.findByCreatedAt", query = "SELECT t FROM Topic t WHERE t.createdAt = :createdAt"), @NamedQuery(name = "Topic.findByDescription", query = "SELECT t FROM Topic t WHERE t.description = :description")})
public class Topic implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_topic", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "forum", referencedColumnName = "id")
    @ManyToOne
    private Forum forum;
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @ManyToOne
    private SystemUser createdBy;
    @Column(name = "posts_count", nullable = false)
    private Integer postsCount = 0;
    @OneToOne
    @JoinColumn(name = "last_post_id", referencedColumnName = "id")    
    private Post lastPost;
    @Column(name = "last_post_date")
    private Date lastPostDate;

    public Topic() {
    }

    public Topic(Long id) {
        this.id = id;
    }

    public Topic(Long id, String title, Date createdAt) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public SystemUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(SystemUser createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getPostsCount() {
        return postsCount;
    }
    
    public void setPostsCount(Integer postsCount) {
        this.postsCount = postsCount;
    }
    
    public void incrementPostsCount() {
        this.postsCount++;
    }
    
    public void decrementPostsCount() {
        this.postsCount--;
    }
    
    public Post getLastPost() {
        return lastPost;
    }
    
    public void setLastPost(Post post) {
        this.lastPost = post;
    }
    
    public Date getLastPostDate() {
        return lastPostDate;
    }

    public void setLastPostDate(Date lastPostDate) {
        this.lastPostDate = lastPostDate;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Topic)) {
            return false;
        }
        Topic other = (Topic) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.Topic[id=" + id + "]";
    }

}
