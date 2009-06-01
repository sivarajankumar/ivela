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
 * @author jefferson
 */
@Entity
@Table(name = "external_params")
@NamedQueries({@NamedQuery(name = "ExternalParams.findById", query = "SELECT e FROM ExternalParams e WHERE e.id = :id"), @NamedQuery(name = "ExternalParams.findByValue", query = "SELECT e FROM ExternalParams e WHERE e.value = :value"), @NamedQuery(name = "ExternalParams.findByKey", query = "SELECT e FROM ExternalParams e WHERE e.key = :key")})
public class ExternalParams implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_external_params", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "value", nullable = false)
    private String value;
    @Column(name = "key", nullable = false)
    private String key;

    public ExternalParams() {
    }

    public ExternalParams(Long id) {
        this.id = id;
    }

    public ExternalParams(Long id, String value, String key) {
        this.id = id;
        this.value = value;
        this.key = key;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
        if (!(object instanceof ExternalParams)) {
            return false;
        }
        ExternalParams other = (ExternalParams) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.ExternalParams[id=" + id + "]";
    }

}
