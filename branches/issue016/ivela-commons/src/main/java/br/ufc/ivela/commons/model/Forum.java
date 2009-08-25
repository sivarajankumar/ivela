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

/**
 *
 * @author rodrigo
 */
@Entity
@Table(name = "forum")
@NamedQueries({@NamedQuery(name = "Forum.findById", query = "SELECT f FROM Forum f WHERE f.id = :id"), @NamedQuery(name = "Forum.findByTitle", query = "SELECT f FROM Forum f WHERE f.title = :title"), @NamedQuery(name = "Forum.findByDescription", query = "SELECT f FROM Forum f WHERE f.description = :description"), @NamedQuery(name = "Forum.findByStudentCreateTopic", query = "SELECT f FROM Forum f WHERE f.studentCreateTopic = :studentCreateTopic"), @NamedQuery(name = "Forum.findByStudentUploadPost", query = "SELECT f FROM Forum f WHERE f.studentUploadPost = :studentUploadPost"), @NamedQuery(name = "Forum.findByStudentUploadRepository", query = "SELECT f FROM Forum f WHERE f.studentUploadRepository = :studentUploadRepository"), @NamedQuery(name = "Forum.findByStudentLinkPost", query = "SELECT f FROM Forum f WHERE f.studentLinkPost = :studentLinkPost"), @NamedQuery(name = "Forum.findByPublic1", query = "SELECT f FROM Forum f WHERE f.public1 = :public1")})
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
    private boolean studentCreateTopic;
    @Column(name = "student_upload_post", nullable = false)
    private boolean studentUploadPost;
    @Column(name = "student_upload_repository", nullable = false)
    private boolean studentUploadRepository;
    @Column(name = "student_link_post", nullable = false)
    private boolean studentLinkPost;
    @Column(name = "public", nullable = false)
    private boolean public1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "forum")
    private Collection<Topic> topicCollection;
    @JoinColumn(name = "grade", referencedColumnName = "id")
    @ManyToOne
    private Grade grade;
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @ManyToOne
    private SystemUser createdBy;

    public Forum() {
    }

    public Forum(Long id) {
        this.id = id;
    }

    public Forum(Long id, String title, boolean studentCreateTopic, boolean studentUploadPost, boolean studentUploadRepository, boolean studentLinkPost, boolean public1) {
        this.id = id;
        this.title = title;
        this.studentCreateTopic = studentCreateTopic;
        this.studentUploadPost = studentUploadPost;
        this.studentUploadRepository = studentUploadRepository;
        this.studentLinkPost = studentLinkPost;
        this.public1 = public1;
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

    public boolean getStudentUploadRepository() {
        return studentUploadRepository;
    }

    public void setStudentUploadRepository(boolean studentUploadRepository) {
        this.studentUploadRepository = studentUploadRepository;
    }

    public boolean getStudentLinkPost() {
        return studentLinkPost;
    }

    public void setStudentLinkPost(boolean studentLinkPost) {
        this.studentLinkPost = studentLinkPost;
    }

    public boolean getPublic1() {
        return public1;
    }

    public void setPublic1(boolean public1) {
        this.public1 = public1;
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
