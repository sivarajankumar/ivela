/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.commons.model;

import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author rodrigo
 */
@Entity
@Table(name = "language_system_user")
@NamedQueries({@NamedQuery(name = "LanguageSystemUser.findById", query = "SELECT l FROM LanguageSystemUser l WHERE l.id = :id"), @NamedQuery(name = "LanguageSystemUser.findByReading", query = "SELECT l FROM LanguageSystemUser l WHERE l.reading = :reading"), @NamedQuery(name = "LanguageSystemUser.findByWriting", query = "SELECT l FROM LanguageSystemUser l WHERE l.writing = :writing"), @NamedQuery(name = "LanguageSystemUser.findByComprehension", query = "SELECT l FROM LanguageSystemUser l WHERE l.comprehension = :comprehension"), @NamedQuery(name = "LanguageSystemUser.findBySpeaking", query = "SELECT l FROM LanguageSystemUser l WHERE l.speaking = :speaking")})
public class LanguageSystemUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_language_system_user", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "reading", nullable = false)
    private int reading;
    @Column(name = "writing", nullable = false)
    private int writing;
    @Column(name = "comprehension", nullable = false)
    private int comprehension;
    @Column(name = "speaking", nullable = false)
    private int speaking;
    @JoinColumn(name = "language", referencedColumnName = "id")
    @ManyToOne
    private Language language;
    @JoinColumn(name = "system_user", referencedColumnName = "id")
    @ManyToOne
    private SystemUser systemUser;

    public LanguageSystemUser() {
    }

    public LanguageSystemUser(Long id) {
        this.id = id;
    }

    public LanguageSystemUser(Long id, int reading, int writing, int comprehension, int speaking) {
        this.id = id;
        this.reading = reading;
        this.writing = writing;
        this.comprehension = comprehension;
        this.speaking = speaking;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getReading() {
        return reading;
    }

    public void setReading(int reading) {
        this.reading = reading;
    }

    public int getWriting() {
        return writing;
    }

    public void setWriting(int writing) {
        this.writing = writing;
    }

    public int getComprehension() {
        return comprehension;
    }

    public void setComprehension(int comprehension) {
        this.comprehension = comprehension;
    }

    public int getSpeaking() {
        return speaking;
    }

    public void setSpeaking(int speaking) {
        this.speaking = speaking;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
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
        if (!(object instanceof LanguageSystemUser)) {
            return false;
        }
        LanguageSystemUser other = (LanguageSystemUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.LanguageSystemUser[id=" + id + "]";
    }

}
