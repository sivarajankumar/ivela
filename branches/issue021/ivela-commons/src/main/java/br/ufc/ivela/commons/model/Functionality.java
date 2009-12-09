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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author leoomoreira
 */
@Entity
@Table(name = "functionality")
@Cache(region="functionalityCache", usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries({@NamedQuery(name = "Functionality.findById", query = "SELECT f FROM Functionality f WHERE f.id = :id"), @NamedQuery(name = "Functionality.findByName", query = "SELECT f FROM Functionality f WHERE f.name = :name"), @NamedQuery(name = "Functionality.findByAction", query = "SELECT f FROM Functionality f WHERE f.action = :action"), @NamedQuery(name = "Functionality.findByMethod", query = "SELECT f FROM Functionality f WHERE f.method = :method"), @NamedQuery(name = "Functionality.findByDescription", query = "SELECT f FROM Functionality f WHERE f.description = :description")})
public class Functionality implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_functionality", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "action", nullable = false)
    private String action;
    @Column(name = "method", nullable = false)
    private String method;
    @Column(name = "description", nullable = false)
    private String description;

    public Functionality() {
    }

    public Functionality(Long id) {
        this.id = id;
    }

    public Functionality(Long id, String name, String action, String method, String description) {
        this.id = id;
        this.name = name;
        this.action = action;
        this.method = method;
        this.description = description;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof Functionality)) {
            return false;
        }
        Functionality other = (Functionality) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.Functionality[id=" + id + "]";
    }

}
