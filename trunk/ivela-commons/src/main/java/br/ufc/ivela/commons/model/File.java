/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
    
    @Transient
    private Course course;
    
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
