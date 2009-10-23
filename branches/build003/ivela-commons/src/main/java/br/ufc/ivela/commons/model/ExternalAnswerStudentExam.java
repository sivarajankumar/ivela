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
@Table(name = "external_answer_student_exam")
@NamedQueries({@NamedQuery(name = "ExternalAnswerStudentExam.findById", query = "SELECT e FROM ExternalAnswerStudentExam e WHERE e.id = :id"), @NamedQuery(name = "ExternalAnswerStudentExam.findByResultValue", query = "SELECT e FROM ExternalAnswerStudentExam e WHERE e.resultValue = :resultValue"), @NamedQuery(name = "ExternalAnswerStudentExam.findByAproved", query = "SELECT e FROM ExternalAnswerStudentExam e WHERE e.aproved = :aproved")})
public class ExternalAnswerStudentExam implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_external_answer_student_exam", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "result_value", nullable = false)
    private String resultValue;
    @Column(name = "aproved", nullable = false)
    private boolean aproved;
    @JoinColumn(name = "answer_student_exam", referencedColumnName = "id")
    @ManyToOne
    private AnswerStudentExam answerStudentExam;
    @JoinColumn(name = "question", referencedColumnName = "id")
    @ManyToOne
    private Question question;

    public ExternalAnswerStudentExam() {
    }

    public ExternalAnswerStudentExam(Long id) {
        this.id = id;
    }

    public ExternalAnswerStudentExam(Long id, String resultValue, boolean aproved) {
        this.id = id;
        this.resultValue = resultValue;
        this.aproved = aproved;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResultValue() {
        return resultValue;
    }

    public void setResultValue(String resultValue) {
        this.resultValue = resultValue;
    }

    public boolean getAproved() {
        return aproved;
    }

    public void setAproved(boolean aproved) {
        this.aproved = aproved;
    }

    public AnswerStudentExam getAnswerStudentExam() {
        return answerStudentExam;
    }

    public void setAnswerStudentExam(AnswerStudentExam answerStudentExam) {
        this.answerStudentExam = answerStudentExam;
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
        if (!(object instanceof ExternalAnswerStudentExam)) {
            return false;
        }
        ExternalAnswerStudentExam other = (ExternalAnswerStudentExam) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.ExternalAnswerStudentExam[id=" + id + "]";
    }

}
