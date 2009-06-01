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
@Table(name = "answer_student_exam")
@NamedQueries({@NamedQuery(name = "AnswerStudentExam.findById", query = "SELECT a FROM AnswerStudentExam a WHERE a.id = :id")})
public class AnswerStudentExam implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_answer_student_exam", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "type", nullable = true)
    private int type;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "answerStudentExam")
    private Collection<ExternalAnswerStudentExam> externalAnswerStudentExamCollection;
    @JoinColumn(name = "student_exam", referencedColumnName = "id")
    @ManyToOne
    private StudentExam studentExam;

    public AnswerStudentExam() {
    }

    public AnswerStudentExam(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<ExternalAnswerStudentExam> getExternalAnswerStudentExamCollection() {
        return externalAnswerStudentExamCollection;
    }

    public void setExternalAnswerStudentExamCollection(Collection<ExternalAnswerStudentExam> externalAnswerStudentExamCollection) {
        this.externalAnswerStudentExamCollection = externalAnswerStudentExamCollection;
    }

    public StudentExam getStudentExam() {
        return studentExam;
    }

    public void setStudentExam(StudentExam studentExam) {
        this.studentExam = studentExam;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
        if (!(object instanceof AnswerStudentExam)) {
            return false;
        }
        AnswerStudentExam other = (AnswerStudentExam) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.AnswerStudentExam[id=" + id + "]";
    }

}
