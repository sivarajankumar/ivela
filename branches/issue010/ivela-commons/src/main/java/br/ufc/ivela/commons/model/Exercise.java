/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.commons.model;

import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.Set;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author leoomoreira
 */
@Entity
@Table(name = "exercise")
@NamedQueries({@NamedQuery(name = "Exercise.findById", query = "SELECT e FROM Exercise e WHERE e.id = :id"), @NamedQuery(name = "Exercise.findByNavigable", query = "SELECT e FROM Exercise e WHERE e.navigable = :navigable"), @NamedQuery(name = "Exercise.findByTitle", query = "SELECT e FROM Exercise e WHERE e.title = :title"), @NamedQuery(name = "Exercise.findByCreatedAt", query = "SELECT e FROM Exercise e WHERE e.createdAt = :createdAt")})
public class Exercise implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_exercise", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "navigable", nullable = false)
    private boolean navigable;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @ManyToOne
    private SystemUser createdBy;
//    @JoinColumn(name="unit_content", referencedColumnName = "id")
//    @ManyToOne
//    private UnitContent unitContent;
    @Column(name = "order_n", nullable = false)
    private Integer order;
    @Column(name = "weight", nullable = false)
    private Integer weight;
    @Column(name = "chances", nullable = false)
    private Integer chances;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "active", nullable = true)
    private boolean active;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "exercise_requisite",
    joinColumns = @JoinColumn(name = "exercise"),
    inverseJoinColumns = @JoinColumn(name = "requisite"))
    private Set<Exercise> requisites;
    
    @Column(name="unit_content")
    private Long unitContentId;    
    @Transient
    private UnitContent unitContent;
    
    public Exercise() {
    }

    public Exercise(Long id) {
        this.id = id;
    }

    public Exercise(Long id, boolean navigable, String title, Date createdAt) {
        this.id = id;
        this.navigable = navigable;
        this.title = title;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getNavigable() {
        return navigable;
    }

    public void setNavigable(boolean navigable) {
        this.navigable = navigable;
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

    public SystemUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(SystemUser createdBy) {
        this.createdBy = createdBy;
    }

    public UnitContent getUnitContent() {
        return unitContent;
    }

    public void setUnitContent(UnitContent unitContent) {
        this.unitContent = unitContent;
    }

    public Set<Exercise> getRequisites() {
        return requisites;
    }

    public void setRequisites(Set<Exercise> requisites) {
        this.requisites = requisites;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }



    public Integer getChances() {
        return chances;
    }

    public void setChances(Integer chances) {
        this.chances = chances;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUnitContentId() {
        return unitContentId;
    }

    public void setUnitContentId(Long unitContentId) {
        this.unitContentId = unitContentId;
    }

    public boolean isActive() {
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
        if (!(object instanceof Exercise)) {
            return false;
        }
        Exercise other = (Exercise) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.Exercise[id=" + id + "]";
    }

}
