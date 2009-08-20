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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author jefferson
 */
@Entity
@Table(name = "answer_external_question_student_exercise")
@NamedQueries({@NamedQuery(name = "AnswerExternalQuestionStudentExercise.findById", query = "SELECT a FROM AnswerExternalQuestionStudentExercise a WHERE a.id = :id"), @NamedQuery(name = "AnswerExternalQuestionStudentExercise.findByAproved", query = "SELECT a FROM AnswerExternalQuestionStudentExercise a WHERE a.aproved = :aproved"), @NamedQuery(name = "AnswerExternalQuestionStudentExercise.findByResultValue", query = "SELECT a FROM AnswerExternalQuestionStudentExercise a WHERE a.resultValue = :resultValue")})
public class AnswerExternalQuestionStudentExercise implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id    
    @SequenceGenerator(name="seq", sequenceName="sq_answer_external_question_student_exercise", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "aproved", nullable = false)
    private boolean aproved;
    @Column(name = "result_value", nullable = false)
    private String resultValue;
    @JoinColumn(name = "answer_student_exercise", referencedColumnName = "id")
    @ManyToOne
    private AnswerStudentExercise answerStudentExercise;
    @JoinColumn(name = "question", referencedColumnName = "id")
    @ManyToOne
    private Question question;

    public AnswerExternalQuestionStudentExercise() {
    }

    public AnswerExternalQuestionStudentExercise(Long id) {
        this.id = id;
    }

    public AnswerExternalQuestionStudentExercise(Long id, boolean aproved, String resultValue) {
        this.id = id;
        this.aproved = aproved;
        this.resultValue = resultValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getAproved() {
        return aproved;
    }

    public void setAproved(boolean aproved) {
        this.aproved = aproved;
    }

    public String getResultValue() {
        return resultValue;
    }

    public void setResultValue(String resultValue) {
        this.resultValue = resultValue;
    }

    public AnswerStudentExercise getAnswerStudentExercise() {
        return answerStudentExercise;
    }

    public void setAnswerStudentExercise(AnswerStudentExercise answerStudentExercise) {
        this.answerStudentExercise = answerStudentExercise;
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
        if (!(object instanceof AnswerExternalQuestionStudentExercise)) {
            return false;
        }
        AnswerExternalQuestionStudentExercise other = (AnswerExternalQuestionStudentExercise) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.AnswerExternalQuestionStudentExercise[id=" + id + "]";
    }

}
