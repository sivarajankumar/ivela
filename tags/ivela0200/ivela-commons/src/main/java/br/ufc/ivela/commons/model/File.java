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
# File: File.java                                                                           #
# Document: File Uploads Model                                                              # 
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
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author rodrigo
 */
@Entity
@Table(name = "file")
@NamedQueries({@NamedQuery(name = "File.findById", query = "SELECT f FROM File f WHERE f.id = :id"), @NamedQuery(name = "File.findByTitle", query = "SELECT f FROM File f WHERE f.title = :title"), @NamedQuery(name = "File.findByAuthor", query = "SELECT f FROM File f WHERE f.author = :author"), @NamedQuery(name = "File.findByDescription", query = "SELECT f FROM File f WHERE f.description = :description"), @NamedQuery(name = "File.findByKeywords", query = "SELECT f FROM File f WHERE f.keywords = :keywords"), @NamedQuery(name = "File.findByFilename", query = "SELECT f FROM File f WHERE f.filename = :filename"), @NamedQuery(name = "File.findByMimetype", query = "SELECT f FROM File f WHERE f.mimetype = :mimetype"), @NamedQuery(name = "File.findByUploadDate", query = "SELECT f FROM File f WHERE f.uploadDate = :uploadDate"), @NamedQuery(name = "File.findByPath", query = "SELECT f FROM File f WHERE f.path = :path")})
public class File implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_file", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "description")
    private String description;
    @Column(name = "keywords")
    private String keywords;
    @Column(name = "filename", nullable = false)
    private String filename;
    @Column(name = "mimetype", nullable = false)
    private String mimetype;
    @Column(name = "upload_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadDate;
    @Column(name = "path", nullable = false)
    private String path;
    @Column(name = "course", nullable = false)
    private Long courseId;
    @Column(name = "grade")
    private Long gradeId;
    
    @Transient
    private Course course;
    
    @Transient
    private Grade grade;
    
    @JoinColumn(name = "sent_by", referencedColumnName = "id")
    @ManyToOne
    private SystemUser sentBy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "file")
    private Collection<AttachPost> attachPostCollection;

    public File() {
    }

    public File(Long id) {
        this.id = id;
    }

    public File(Long id, String title, String filename, String mimetype, Date uploadDate, String path) {
        this.id = id;
        this.title = title;
        this.filename = filename;
        this.mimetype = mimetype;
        this.uploadDate = uploadDate;
        this.path = path;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public SystemUser getSentBy() {
        return sentBy;
    }

    public void setSentBy(SystemUser sentBy) {
        this.sentBy = sentBy;
    }

    public Collection<AttachPost> getAttachPostCollection() {
        return attachPostCollection;
    }

    public void setAttachPostCollection(Collection<AttachPost> attachPostCollection) {
        this.attachPostCollection = attachPostCollection;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Long getCourseId() {
        return courseId;
    }
    
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Grade getGrade() {
        return grade;
    }
    
    public Long getGradeId() {
        return gradeId;
    }
    
    public void setGrade(Grade grade) {
        this.grade = grade;        
    }
    
    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
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
        if (!(object instanceof File)) {
            return false;
        }
        File other = (File) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.File[id=" + id + "]";
    }

}
