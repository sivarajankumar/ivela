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
 * @author leoomoreira
 */
@Entity
@Table(name = "npd_phrase")
public class NpdPhrase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "sq_npd_phrase", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @Column(name = "id", nullable = false)
    private Long id;
    
    @JoinColumn(name = "npd_user", referencedColumnName = "id")
    @ManyToOne
    private NpdUser npdUser;
    
    @Column(name = "phrase")
    private Integer phrase;
    
    @Column(name = "tries")
    private Integer tries;

    public NpdPhrase() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTries() {
        return tries;
    }

    public void setTries(Integer tries) {
        this.tries = tries;
    }

    public NpdUser getNpdUser() {
        return npdUser;
    }

    public void setNpdUser(NpdUser npdUser) {
        this.npdUser = npdUser;
    }

    public Integer getPhrase() {
        return phrase;
    }

    public void setPhrase(Integer phrase) {
        this.phrase = phrase;
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
        if (!(object instanceof NpdPhrase)) {
            return false;
        }
        NpdPhrase other = (NpdPhrase) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.Address[id=" + id + "]";
    }
}
