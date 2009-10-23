/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.commons.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author leoomoreira
 */
@Entity
@Table(name = "discipline")
@Cache(region="disciplineCache", usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries({@NamedQuery(name = "Discipline.findById", query = "SELECT d FROM Discipline d WHERE d.id = :id"), @NamedQuery(name = "Discipline.findByName", query = "SELECT d FROM Discipline d WHERE d.name = :name")})
public class Discipline implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_discipline", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;    
    
    @Column(name="course")
    private Long courseId;
    
    @Column(name="tag")
    private String tag;

    
    @Transient
    private Course course;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="disciplineId", targetEntity=Unit.class)
    private Collection<Unit> units;

    public Discipline() {
    }

    public Discipline(Long id) {
        this.id = id;
    }

    public Discipline(Long id, String name) {
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

    public Collection<Unit> getUnits() {
        return units;
    }

    public void setUnits(Collection<Unit> units) {
        this.units = units;
    }
    
    public void setTag(String tag) {
    	this.tag = tag;
    }
    
    public String getTag() {
    	return tag;
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
        if (!(object instanceof Discipline)) {
            return false;
        }
        Discipline other = (Discipline) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.Discipline[id=" + id + "]";
    }

}
