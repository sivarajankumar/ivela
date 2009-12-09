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
# File: Forum.java                                                                          #
# Document: Forum Model                                                                     # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# ??-???-2008 - rodrigo (UFC)                     - XXXXXX - Initial Version                #
# 01-SEP-2009 - otofuji (Instituto Eldorado)      - 000016 - Review Forum                   #
#############################################################################################
*/


package br.ufc.ivela.commons.model;

import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name = "forum")
@Cache(region="forumCache", usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries({@NamedQuery(name = "Forum.findById", query = "SELECT f FROM Forum f WHERE f.id = :id"), @NamedQuery(name = "Forum.findByTitle", query = "SELECT f FROM Forum f WHERE f.title = :title"), @NamedQuery(name = "Forum.findByDescription", query = "SELECT f FROM Forum f WHERE f.description = :description"), @NamedQuery(name = "Forum.findByStudentCreateTopic", query = "SELECT f FROM Forum f WHERE f.studentCreateTopic = :studentCreateTopic"), @NamedQuery(name = "Forum.findByStudentUploadPost", query = "SELECT f FROM Forum f WHERE f.studentUploadPost = :studentUploadPost"), @NamedQuery(name = "Forum.findByPublic1", query = "SELECT f FROM Forum f WHERE f.public1 = :public1")})
public class Forum implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_forum", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description")
    private String description;    
    @Column(name = "student_create_topic", nullable = false)
    private boolean studentCreateTopic = true;
    @Column(name = "student_upload_post", nullable = false)
    private boolean studentUploadPost = true;        
    @Column(name = "public", nullable = false)
    private boolean public1;
    @Column(name = "topics_count", nullable = false)
    private Integer topicsCount = 0;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "forum")
    private Collection<Topic> topicCollection;
    @JoinColumn(name = "grade", referencedColumnName = "id")
    @ManyToOne
    private Grade grade;    
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @ManyToOne
    private SystemUser createdBy;
    @JoinColumn(name = "course", referencedColumnName = "id")
    @OneToOne
    private Course course;
    
    public Forum() {
    }

    public Forum(Long id) {
        this.id = id;
    }

    public Forum(Long id, String title, boolean studentUploadPost, boolean studenCreateTopic, boolean public1) {
        this.id = id;
        this.title = title;        
        this.studentUploadPost = studentUploadPost;        
        this.public1 = public1;
        this.studentCreateTopic = studenCreateTopic;
        // You can't create a Forum that already has topics.        
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getStudentCreateTopic() {
        return studentCreateTopic;
    }

    public void setStudentCreateTopic(boolean studentCreateTopic) {
        this.studentCreateTopic = studentCreateTopic;
    }
    
    public boolean getStudentUploadPost() {
        return studentUploadPost;
    }

    public void setStudentUploadPost(boolean studentUploadPost) {
        this.studentUploadPost = studentUploadPost;
    }

    public boolean getPublic1() {
        return public1;
    }

    public void setPublic1(boolean public1) {
        this.public1 = public1;
    }

    public Integer getTopicsCount() {
        return topicsCount;
    }
    
    public void setTopicsCount(Integer topicsCount) {
        this.topicsCount = topicsCount;
    }
    
    public void incrementTopicsCount() {
        this.topicsCount++;
    }
    
    public void decrementTopicsCount() {
        this.topicsCount--;
    }
    
    public Collection<Topic> getTopicCollection() {
        return topicCollection;
    }

    public void setTopicCollection(Collection<Topic> topicCollection) {
        this.topicCollection = topicCollection;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public SystemUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(SystemUser createdBy) {
        this.createdBy = createdBy;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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
        if (!(object instanceof Forum)) {
            return false;
        }
        Forum other = (Forum) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.Forum[id=" + id + "]";
    }

}
