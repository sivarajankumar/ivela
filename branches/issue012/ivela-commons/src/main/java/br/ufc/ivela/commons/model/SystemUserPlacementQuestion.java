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
@Table(name = "system_user_placement_question")
@NamedQueries({@NamedQuery(name = "SystemUserPlacementQuestion.findById", query = "SELECT s FROM SystemUserPlacementQuestion s WHERE s.id = :id")})
public class SystemUserPlacementQuestion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_system_user_placement_question", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Integer id;
    @JoinColumn(name = "placement_question", referencedColumnName = "id")
    @ManyToOne
    private PlacementQuestion placementQuestion;
    @JoinColumn(name = "system_user", referencedColumnName = "id")
    @ManyToOne
    private SystemUser systemUser;

    public SystemUserPlacementQuestion() {
    }

    public SystemUserPlacementQuestion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PlacementQuestion getPlacementQuestion() {
        return placementQuestion;
    }

    public void setPlacementQuestion(PlacementQuestion placementQuestion) {
        this.placementQuestion = placementQuestion;
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
        if (!(object instanceof SystemUserPlacementQuestion)) {
            return false;
        }
        SystemUserPlacementQuestion other = (SystemUserPlacementQuestion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.SystemUserPlacementQuestion[id=" + id + "]";
    }

}
