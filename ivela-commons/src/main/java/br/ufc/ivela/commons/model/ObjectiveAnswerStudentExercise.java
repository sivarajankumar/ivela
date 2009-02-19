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
@Table(name = "objective_answer_student_exercise")
@NamedQueries({@NamedQuery(name = "ObjectiveAnswerStudentExercise.findById", query = "SELECT o FROM ObjectiveAnswerStudentExercise o WHERE o.id = :id")})
public class ObjectiveAnswerStudentExercise implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_objective_answer_student_exercise", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @JoinColumn(name = "answer_student_exercise", referencedColumnName = "id")
    @ManyToOne
    private AnswerStudentExercise answerStudentExercise;
    @JoinColumn(name = "objective_answer", referencedColumnName = "id")
    @ManyToOne
    private ObjectiveAnswer objectiveAnswer;

    public ObjectiveAnswerStudentExercise() {
    }

    public ObjectiveAnswerStudentExercise(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnswerStudentExercise getAnswerStudentExercise() {
        return answerStudentExercise;
    }

    public void setAnswerStudentExercise(AnswerStudentExercise answerStudentExercise) {
        this.answerStudentExercise = answerStudentExercise;
    }

    public ObjectiveAnswer getObjectiveAnswer() {
        return objectiveAnswer;
    }

    public void setObjectiveAnswer(ObjectiveAnswer objectiveAnswer) {
        this.objectiveAnswer = objectiveAnswer;
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
        if (!(object instanceof ObjectiveAnswerStudentExercise)) {
            return false;
        }
        ObjectiveAnswerStudentExercise other = (ObjectiveAnswerStudentExercise) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.ObjectiveAnswerStudentExercise[id=" + id + "]";
    }

}
