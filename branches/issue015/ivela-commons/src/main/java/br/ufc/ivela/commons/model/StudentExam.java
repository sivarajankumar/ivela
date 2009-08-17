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
 * @author leoomoreira
 */
@Entity
@Table(name = "student_exam")
@NamedQueries({@NamedQuery(name = "StudentExam.findById", query = "SELECT s FROM StudentExam s WHERE s.id = :id"), @NamedQuery(name = "StudentExam.findByStatus", query = "SELECT s FROM StudentExam s WHERE s.status = :status")})
public class StudentExam implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_student_exam", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "status", nullable = false)
    private int status;
    @JoinColumn(name = "exam", referencedColumnName = "id")
    @ManyToOne
    private Exam exam;
    @JoinColumn(name = "student", referencedColumnName = "id")
    @ManyToOne
    private SystemUser student;
    @JoinColumn(name = "grade", referencedColumnName = "id")
    @ManyToOne
    private Grade grade;
    @Column(name = "score", nullable = true)
    private Double score;

    @Column(name = "manual_score", nullable = true)
    private Double manualScore;
    
    public StudentExam() {
    }

    public StudentExam(Long id) {
        this.id = id;
    }

    public StudentExam(Long id, int status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }



    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public SystemUser getStudent() {
        return student;
    }

    public void setStudent(SystemUser student) {
        this.student = student;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getManualScore() {
        return manualScore;
    }

    public void setManualScore(Double manualScore) {
        this.manualScore = manualScore;
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
        if (!(object instanceof StudentExam)) {
            return false;
        }
        StudentExam other = (StudentExam) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.StudentExam[id=" + id + "]";
    }

}
