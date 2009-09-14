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

/**
 *
 * @author leoomoreira
 */
@Entity
@Table(name = "authentication")
@NamedQueries({@NamedQuery(name = "Authentication.findById", query = "SELECT a FROM Authentication a WHERE a.id = :id"), @NamedQuery(name = "Authentication.findByName", query = "SELECT a FROM Authentication a WHERE a.name = :name"), @NamedQuery(name = "Authentication.findByDescription", query = "SELECT a FROM Authentication a WHERE a.description = :description")})
public class Authentication implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_authentication", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "authentication_functionality",
    joinColumns = @JoinColumn(name = "authentication"),
    inverseJoinColumns = @JoinColumn(name = "functionality"))
    private Collection<Functionality> functionalities;

    public Authentication() {
    }

    public Authentication(Long id) {
        this.id = id;
    }

    public Authentication(Long id, String name, String description) {
        this.id = id;
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Functionality> getFunctionalities() {
        return functionalities;
    }

    public void setFunctionalities(Collection<Functionality> functionalities) {
        this.functionalities = functionalities;
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
        if (!(object instanceof Authentication)) {
            return false;
        }
        Authentication other = (Authentication) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.Authentication[id=" + id + "]";
    }

}
