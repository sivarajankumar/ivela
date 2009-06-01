/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.commons.model;

import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "answer_auditive_question_student_exam")
@NamedQueries({@NamedQuery(name = "AnswerAuditiveQuestionStudentExam.findById", query = "SELECT a FROM AnswerAuditiveQuestionStudentExam a WHERE a.id = :id"), @NamedQuery(name = "AnswerAuditiveQuestionStudentExam.findByTimes", query = "SELECT a FROM AnswerAuditiveQuestionStudentExam a WHERE a.times = :times"), @NamedQuery(name = "AnswerAuditiveQuestionStudentExam.findByScore", query = "SELECT a FROM AnswerAuditiveQuestionStudentExam a WHERE a.score = :score")})
public class AnswerAuditiveQuestionStudentExam implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_answer_auditive_question_student_exam", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "times", nullable = false)
    private int times;
    @Column(name = "score")
    private BigDecimal score;
    @JoinColumn(name = "answer_student_exam", referencedColumnName = "id")
    @ManyToOne
    private AnswerStudentExam answerStudentExam;
    @JoinColumn(name = "question", referencedColumnName = "id")
    @ManyToOne
    private Question question;

    public AnswerAuditiveQuestionStudentExam() {
    }

    public AnswerAuditiveQuestionStudentExam(Long id) {
        this.id = id;
    }

    public AnswerAuditiveQuestionStudentExam(Long id, int times) {
        this.id = id;
        this.times = times;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
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
        if (!(object instanceof AnswerAuditiveQuestionStudentExam)) {
            return false;
        }
        AnswerAuditiveQuestionStudentExam other = (AnswerAuditiveQuestionStudentExam) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.AnswerAuditiveQuestionStudentExam[id=" + id + "]";
    }

}
