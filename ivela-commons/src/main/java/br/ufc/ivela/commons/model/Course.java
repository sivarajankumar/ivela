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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author leoomoreira
 */
@Entity
@Table(name = "course")
@NamedQueries({@NamedQuery(name = "Course.findById", query = "SELECT c FROM Course c WHERE c.id = :id"), @NamedQuery(name = "Course.findByName", query = "SELECT c FROM Course c WHERE c.name = :name"), @NamedQuery(name = "Course.findByDescription", query = "SELECT c FROM Course c WHERE c.description = :description"), @NamedQuery(name = "Course.findByImage", query = "SELECT c FROM Course c WHERE c.image = :image"), @NamedQuery(name = "Course.findByTargetAudience", query = "SELECT c FROM Course c WHERE c.targetAudience = :targetAudience"), @NamedQuery(name = "Course.findByContents", query = "SELECT c FROM Course c WHERE c.contents = :contents"), @NamedQuery(name = "Course.findByActive", query = "SELECT c FROM Course c WHERE c.active = :active")})
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_course", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String image;
    @Column(name = "target_audience")
    private String targetAudience;
    @Column(name = "contents")
    private String contents;
    @Column(name = "repository_structure")
    private String repositoryStructure;
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="course", targetEntity=CourseRequisite.class)
    private Collection<CourseRequisite> courseRequisites;
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="courseId", targetEntity=Discipline.class)
    private Collection<Discipline> disciplines;
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="courseId", targetEntity=Grade.class)
    private Collection<Grade> grades;
    @Column(name = "active")
    private boolean active;

    public Course() {
    }

    public Course(Long id) {
        this.id = id;
    }

    public Course(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Collection<CourseRequisite> getCourseRequisites() {
        return courseRequisites;
    }

    public void setCourseRequisites(Collection<CourseRequisite> courseRequisites) {
        this.courseRequisites = courseRequisites;
    }

    public Collection<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(Collection<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    public Collection<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Collection<Grade> grades) {
        this.grades = grades;
    }

    public String getRepositoryStructure() {
        return repositoryStructure;
    }

    public void setRepositoryStructure(String repositoryStructure) {
        this.repositoryStructure = repositoryStructure;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.Course[id=" + id + "]";
    }

}
