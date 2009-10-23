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
@Table(name = "sentence_auditive_question")
@NamedQueries({@NamedQuery(name = "SentenceAuditiveQuestion.findById", query = "SELECT s FROM SentenceAuditiveQuestion s WHERE s.id = :id"), @NamedQuery(name = "SentenceAuditiveQuestion.findBySentence", query = "SELECT s FROM SentenceAuditiveQuestion s WHERE s.sentence = :sentence"), @NamedQuery(name = "SentenceAuditiveQuestion.findByFile", query = "SELECT s FROM SentenceAuditiveQuestion s WHERE s.file = :file"), @NamedQuery(name = "SentenceAuditiveQuestion.findBySequence", query = "SELECT s FROM SentenceAuditiveQuestion s WHERE s.sequence = :sequence")})
public class SentenceAuditiveQuestion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_sentence_auditive_question", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "sentence", nullable = false)
    private String sentence;
    @Column(name = "file", nullable = false)
    private String file;
    @Column(name = "sequence", nullable = false)
    private int sequence;
    @JoinColumn(name = "question", referencedColumnName = "id")
    @ManyToOne
    private AuditiveQuestion question;

    public SentenceAuditiveQuestion() {
    }

    public SentenceAuditiveQuestion(Long id) {
        this.id = id;
    }

    public SentenceAuditiveQuestion(Long id, String sentence, String file, int sequence) {
        this.id = id;
        this.sentence = sentence;
        this.file = file;
        this.sequence = sequence;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public AuditiveQuestion getQuestion() {
        return question;
    }

    public void setQuestion(AuditiveQuestion question) {
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
        if (!(object instanceof SentenceAuditiveQuestion)) {
            return false;
        }
        SentenceAuditiveQuestion other = (SentenceAuditiveQuestion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.SentenceAuditiveQuestion[id=" + id + "]";
    }

}
