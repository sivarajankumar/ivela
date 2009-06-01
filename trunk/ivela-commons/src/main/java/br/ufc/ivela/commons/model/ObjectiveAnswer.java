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
@Table(name = "objective_answer")
@NamedQueries({@NamedQuery(name = "ObjectiveAnswer.findById", query = "SELECT o FROM ObjectiveAnswer o WHERE o.id = :id"), @NamedQuery(name = "ObjectiveAnswer.findByAnswer", query = "SELECT o FROM ObjectiveAnswer o WHERE o.answer = :answer")})
public class ObjectiveAnswer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_objective_answer", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "answer", nullable = false)
    private String answer;
    @JoinColumn(name = "question", referencedColumnName = "id")
    @ManyToOne
    private Question question;

    public ObjectiveAnswer() {
    }

    public ObjectiveAnswer(Long id) {
        this.id = id;
    }

    public ObjectiveAnswer(Long id, String answer) {
        this.id = id;
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
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
        if (!(object instanceof ObjectiveAnswer)) {
            return false;
        }
        ObjectiveAnswer other = (ObjectiveAnswer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.ObjectiveAnswer[id=" + id + "]";
    }

}
