/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.commons.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author jefferson
 */
@Entity
@Table(name = "challenger_result")
public class ChallengerResult implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_challenger_result", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "mark", nullable = true)
    private Double mark;
    
    @JoinColumn(name = "system_user", referencedColumnName = "id")
    @ManyToOne
    private SystemUser student;
    @JoinColumn(name = "grade", referencedColumnName = "id")
    @ManyToOne
    private Grade grade;
    @JoinColumn(name = "unit_content", referencedColumnName = "id")
    @ManyToOne
    private UnitContent unitContent;
    
    @Column(name = "teacher", nullable = false)
    private Long teacher;
    
    @Column(name = "type", nullable = false)
    private Integer type;
    
    @Column(name = "xml_fk_id", nullable = false)
    private Integer xmlFkId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public Long getTeacher() {
        return teacher;
    }

    public void setTeacher(Long teacher) {
        this.teacher = teacher;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public SystemUser getStudent() {
        return student;
    }

    public void setStudent(SystemUser student) {
        this.student = student;
    }

    public UnitContent getUnitContent() {
        return unitContent;
    }

    public void setUnitContent(UnitContent unitContent) {
        this.unitContent = unitContent;
    }

    
    
    public Integer getXmlFkId() {
        return xmlFkId;
    }

    public void setXmlFkId(Integer xmlFkId) {
        this.xmlFkId = xmlFkId;
    }
    
    
    
}
