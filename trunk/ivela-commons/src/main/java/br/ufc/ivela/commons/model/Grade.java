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
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author leoomoreira
 */
@Entity
@Table(name = "grade")
@NamedQueries({@NamedQuery(name = "Grade.findById", query = "SELECT g FROM Grade g WHERE g.id = :id"), @NamedQuery(name = "Grade.findByName", query = "SELECT g FROM Grade g WHERE g.name = :name"), @NamedQuery(name = "Grade.findByPeriod", query = "SELECT g FROM Grade g WHERE g.period = :period"), @NamedQuery(name = "Grade.findByMaxStudents", query = "SELECT g FROM Grade g WHERE g.maxStudents = :maxStudents"), @NamedQuery(name = "Grade.findByStatus", query = "SELECT g FROM Grade g WHERE g.status = :status"), @NamedQuery(name = "Grade.findByRequiresEnrollmentValidation", query = "SELECT g FROM Grade g WHERE g.requiresEnrollmentValidation = :requiresEnrollmentValidation"), @NamedQuery(name = "Grade.findByStartDatetime", query = "SELECT g FROM Grade g WHERE g.startDatetime = :startDatetime"), @NamedQuery(name = "Grade.findByEndDatetime", query = "SELECT g FROM Grade g WHERE g.endDatetime = :endDatetime"), @NamedQuery(name = "Grade.findByMaxDuration", query = "SELECT g FROM Grade g WHERE g.maxDuration = :maxDuration")})
public class Grade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_grade", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "period", nullable = false)
    private String period;
    @Column(name = "max_students", nullable = false)
    private int maxStudents;
    @Column(name = "status", nullable = false)
    private int status;
    @Column(name = "requires_enrollment_validation", nullable = false)
    private boolean requiresEnrollmentValidation;
    @Column(name = "start_datetime", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDatetime;
    @Column(name = "end_datetime", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDatetime;
    @Column(name = "max_duration", nullable = false)
    private int maxDuration;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "professor",
    joinColumns = @JoinColumn(name = "grade"),
    inverseJoinColumns = @JoinColumn(name = "system_user"))
    private Set<SystemUser> professors;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "tutor",
    joinColumns = @JoinColumn(name = "grade"),
    inverseJoinColumns = @JoinColumn(name = "system_user"))
    private Set<SystemUser> tutors;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grade")
    private Collection<Enrollment> enrollments;
    
    @Column(name="course")
    private Long courseId;    
    @Transient
    private Course course;
        
    @Column(name="coordinator")
    private Long coordinatorId;    
    @Transient
    private SystemUser coordinator;
    
    @Transient
    private List<Forum> forums;
    
    public Grade() {
    }

    public Grade(Long id) {
        this.id = id;
    }

    public Grade(Long id, String name, String period, int maxStudents, int status, boolean requiresEnrollmentValidation, Date startDatetime, Date endDatetime, int maxDuration) {
        this.id = id;
        this.name = name;
        this.period = period;
        this.maxStudents = maxStudents;
        this.status = status;
        this.requiresEnrollmentValidation = requiresEnrollmentValidation;
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
        this.maxDuration = maxDuration;
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

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean getRequiresEnrollmentValidation() {
        return requiresEnrollmentValidation;
    }

    public void setRequiresEnrollmentValidation(boolean requiresEnrollmentValidation) {
        this.requiresEnrollmentValidation = requiresEnrollmentValidation;
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

    public int getMaxDuration() {
        return maxDuration;
    }

    public void setMaxDuration(int maxDuration) {
        this.maxDuration = maxDuration;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


    public SystemUser getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(SystemUser coordinator) {
        this.coordinator = coordinator;
    }

    public Set<SystemUser> getProfessors() {
        return professors;
    }

    public void setProfessors(Set<SystemUser> professors) {
        this.professors = professors;
    }

    public Set<SystemUser> getTutors() {
        return tutors;
    }

    public void setTutors(Set<SystemUser> tutors) {
        this.tutors = tutors;
    }

    public Collection<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Collection<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public Long getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(Long coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public List<Forum> getForums() {
        return forums;
    }

    public void setForums(List<Forum> forums) {
        this.forums = forums;
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
        if (!(object instanceof Grade)) {
            return false;
        }
        Grade other = (Grade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.Grade[id=" + id + "]";
    }

}
