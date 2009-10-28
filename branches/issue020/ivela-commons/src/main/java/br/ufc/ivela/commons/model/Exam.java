/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.commons.model;

import java.io.Serializable;
import java.util.Set;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author leoomoreira
 */
@Entity
@Table(name = "exam")
@NamedQueries({@NamedQuery(name = "Exam.findById", query = "SELECT e FROM Exam e WHERE e.id = :id"), @NamedQuery(name = "Exam.findByTitle", query = "SELECT e FROM Exam e WHERE e.title = :title"), @NamedQuery(name = "Exam.findByStartDatetime", query = "SELECT e FROM Exam e WHERE e.startDatetime = :startDatetime"), @NamedQuery(name = "Exam.findByEndDatetime", query = "SELECT e FROM Exam e WHERE e.endDatetime = :endDatetime"), @NamedQuery(name = "Exam.findByDuration", query = "SELECT e FROM Exam e WHERE e.duration = :duration"), @NamedQuery(name = "Exam.findByQuestionsPerPage", query = "SELECT e FROM Exam e WHERE e.questionsPerPage = :questionsPerPage"), @NamedQuery(name = "Exam.findByRandomizeQuestionsOrder", query = "SELECT e FROM Exam e WHERE e.randomizeQuestionsOrder = :randomizeQuestionsOrder"), @NamedQuery(name = "Exam.findByNavigable", query = "SELECT e FROM Exam e WHERE e.navigable = :navigable")})
public class Exam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "sq_exam", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "start_datetime", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDatetime;
    @Column(name = "end_datetime", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDatetime;
    @Column(name = "duration", nullable = false)
    private int duration;
    @Column(name = "questions_per_page", nullable = false)
    private int questionsPerPage;
    @Column(name = "randomize_questions_order", nullable = false)
    private boolean randomizeQuestionsOrder;
    @Column(name = "navigable", nullable = false)
    private boolean navigable;
    @Column(name = "order_n", nullable = false)
    private Integer order;
    @Column(name = "active", nullable = true)
    private boolean active;
    @Column(name = "weight", nullable = false)
    private Integer weight;
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @ManyToOne
    private SystemUser createdBy;
    
//    @JoinColumn(name="unit_content", referencedColumnName = "id")
//    @ManyToOne
//    private UnitContent unitContent;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "exam_requisite",
    joinColumns = @JoinColumn(name = "exam"),
    inverseJoinColumns = @JoinColumn(name = "requisite"))
    private Set<Exam> requisites;
    @Column(name = "unit_content")
    private Long unitContentId;
    @Transient
    private UnitContent unitContent;

    public Exam() {
    }

    public Exam(Long id) {
        this.id = id;
    }

    public Exam(Long id, String title, Date startDatetime, Date endDatetime, int duration, int questionsPerPage, boolean randomizeQuestionsOrder, boolean navigable) {
        this.id = id;
        this.title = title;
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
        this.duration = duration;
        this.questionsPerPage = questionsPerPage;
        this.randomizeQuestionsOrder = randomizeQuestionsOrder;
        this.navigable = navigable;
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

    public Date getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
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

    

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getQuestionsPerPage() {
        return questionsPerPage;
    }

    public void setQuestionsPerPage(int questionsPerPage) {
        this.questionsPerPage = questionsPerPage;
    }

    public boolean getRandomizeQuestionsOrder() {
        return randomizeQuestionsOrder;
    }

    public void setRandomizeQuestionsOrder(boolean randomizeQuestionsOrder) {
        this.randomizeQuestionsOrder = randomizeQuestionsOrder;
    }

    public boolean getNavigable() {
        return navigable;
    }

    public void setNavigable(boolean navigable) {
        this.navigable = navigable;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public UnitContent getUnitContent() {
        return unitContent;
    }

    public void setUnitContent(UnitContent unitContent) {
        this.unitContent = unitContent;
    }

    public Set<Exam> getRequisites() {
        return requisites;
    }

    public void setRequisites(Set<Exam> requisites) {
        this.requisites = requisites;
    }

    public Long getUnitContentId() {
        return unitContentId;
    }

    public void setUnitContentId(Long unitContentId) {
        this.unitContentId = unitContentId;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
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
        if (!(object instanceof Exam)) {
            return false;
        }
        Exam other = (Exam) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.Exam[id=" + id + "]";
    }
}
