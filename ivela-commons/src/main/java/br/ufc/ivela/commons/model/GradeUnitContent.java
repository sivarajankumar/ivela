package br.ufc.ivela.commons.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "grade_unit_content")
public class GradeUnitContent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_grade_unit_content", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="grade")
    private Long gradeId;    
    @Transient
    private Grade grade; 

    @Column(name="unit_content")
    private Long unitContentId;    
    @Transient
    private UnitContent unitContent;

    @Column(name="start_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDatetime;

    @Column(name="finish_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finishDatetime;

    @Column(name="mail_flag")
    private Boolean mailFlag;

    public GradeUnitContent() {
    }

    public GradeUnitContent(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof GradeUnitContent)) {
            return false;
        }
        GradeUnitContent other = (GradeUnitContent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.GradeUnitContent[id=" + id + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Long getUnitContentId() {
        return unitContentId;
    }

    public void setUnitContentId(Long unitContentId) {
        this.unitContentId = unitContentId;
    }

    public UnitContent getUnitContent() {
        return unitContent;
    }

    public void setUnitContent(UnitContent unitContent) {
        this.unitContent = unitContent;
    }

    public Date getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public Date getFinishDatetime() {
        return finishDatetime;
    }

    public void setFinishDatetime(Date finishDatetime) {
        this.finishDatetime = finishDatetime;
    }

    public Boolean getMailFlag() {
        return mailFlag;
    }

    public void setMailFlag(Boolean mailFlag) {
        this.mailFlag = mailFlag;
    }
}