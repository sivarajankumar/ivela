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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author marcus
 */

@Entity
@Table(name = "finished_exercise")
@NamedQueries({@NamedQuery(name = "FinishedExercise.findById", query = "SELECT c FROM FinishedExercise c WHERE c.id = :id"), @NamedQuery(name = "FinishedExercise.findBySystemUser", query = "SELECT c FROM FinishedExercise c WHERE c.systemUser = :systemUser"), @NamedQuery(name = "FinishedExercise.findByUnitContent", query = "SELECT c FROM FinishedExercise c WHERE c.unitContent = :unitContent"), @NamedQuery(name = "FinishedExercise.findByExercise", query = "SELECT c FROM FinishedExercise c WHERE c.exercise = :exercise")})
public class FinishedExercise implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_finished_exercise", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "system_user")
    private Long systemUser;
    
    private Long exercise;
    
    @Column(name = "unit_content")
    private Long unitContent;

    public FinishedExercise() {
    }

    public Long getExercise() {
        return exercise;
    }

    public void setExercise(Long exercise) {
        this.exercise = exercise;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(Long systemUser) {
        this.systemUser = systemUser;
    }

    public Long getUnitContent() {
        return unitContent;
    }

    public void setUnitContent(Long unitContent) {
        this.unitContent = unitContent;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FinishedExercise other = (FinishedExercise) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.systemUser != other.systemUser && (this.systemUser == null || !this.systemUser.equals(other.systemUser))) {
            return false;
        }
        if (this.exercise != other.exercise && (this.exercise == null || !this.exercise.equals(other.exercise))) {
            return false;
        }
        if (this.unitContent != other.unitContent && (this.unitContent == null || !this.unitContent.equals(other.unitContent))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 53 * hash + (this.systemUser != null ? this.systemUser.hashCode() : 0);
        hash = 53 * hash + (this.exercise != null ? this.exercise.hashCode() : 0);
        hash = 53 * hash + (this.unitContent != null ? this.unitContent.hashCode() : 0);
        return hash;
    }    
}