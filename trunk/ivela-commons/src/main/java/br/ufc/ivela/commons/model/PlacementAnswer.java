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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author leoomoreira
 */
@Entity
@Table(name = "placement_answer")
@NamedQueries({@NamedQuery(name = "PlacementAnswer.findById", query = "SELECT p FROM PlacementAnswer p WHERE p.id = :id"), @NamedQuery(name = "PlacementAnswer.findByAnswer", query = "SELECT p FROM PlacementAnswer p WHERE p.answer = :answer")})
public class PlacementAnswer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_placement_answer", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "answer", nullable = false)
    private String answer;
    @JoinColumn(name = "question", referencedColumnName = "id")
    @ManyToOne
    private PlacementQuestion question;

    public PlacementAnswer() {
    }

    public PlacementAnswer(Integer id) {
        this.id = id;
    }

    public PlacementAnswer(Integer id, String answer) {
        this.id = id;
        this.answer = answer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public PlacementQuestion getQuestion() {
        return question;
    }

    public void setQuestion(PlacementQuestion question) {
        this.question = question;
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
        if (!(object instanceof PlacementAnswer)) {
            return false;
        }
        PlacementAnswer other = (PlacementAnswer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.PlacementAnswer[id=" + id + "]";
    }

}
