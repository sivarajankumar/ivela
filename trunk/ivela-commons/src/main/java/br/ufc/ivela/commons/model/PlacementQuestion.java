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
@Table(name = "placement_question")
@NamedQueries({@NamedQuery(name = "PlacementQuestion.findById", query = "SELECT p FROM PlacementQuestion p WHERE p.id = :id"), @NamedQuery(name = "PlacementQuestion.findByQuestion", query = "SELECT p FROM PlacementQuestion p WHERE p.question = :question"), @NamedQuery(name = "PlacementQuestion.findByType", query = "SELECT p FROM PlacementQuestion p WHERE p.type = :type"), @NamedQuery(name = "PlacementQuestion.findByLevel", query = "SELECT p FROM PlacementQuestion p WHERE p.level = :level")})
public class PlacementQuestion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_placement_question", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "question", nullable = false)
    private String question;
    @Column(name = "type", nullable = false)
    private int type;
    @Column(name = "level", nullable = false)
    private int level;
    @JoinColumn(name = "correct_answer", referencedColumnName = "id")
    @ManyToOne
    private PlacementAnswer correctAnswer;

    public PlacementQuestion() {
    }

    public PlacementQuestion(Integer id) {
        this.id = id;
    }

    public PlacementQuestion(Integer id, String question, int type, int level) {
        this.id = id;
        this.question = question;
        this.type = type;
        this.level = level;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public PlacementAnswer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(PlacementAnswer correctAnswer) {
        this.correctAnswer = correctAnswer;
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
        if (!(object instanceof PlacementQuestion)) {
            return false;
        }
        PlacementQuestion other = (PlacementQuestion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.PlacementQuestion[id=" + id + "]";
    }

}
