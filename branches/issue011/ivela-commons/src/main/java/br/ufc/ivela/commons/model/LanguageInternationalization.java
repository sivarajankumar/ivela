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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author leoomoreira
 */
@Entity
@Table(name = "language_internationalization")
@NamedQueries({@NamedQuery(name = "LanguageInternationalization.findById", query = "SELECT l FROM LanguageInternationalization l WHERE l.id = :id")})
public class LanguageInternationalization implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_language_internationalization", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")    
    @Column(name = "id", nullable = false)
    private Long id;
    @JoinColumn(name = "language", referencedColumnName = "id")
    @ManyToOne
    private Language language;

    public LanguageInternationalization() {
    }

    public LanguageInternationalization(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
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
        if (!(object instanceof LanguageInternationalization)) {
            return false;
        }
        LanguageInternationalization other = (LanguageInternationalization) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.LanguageInternationalization[id=" + id + "]";
    }

}
