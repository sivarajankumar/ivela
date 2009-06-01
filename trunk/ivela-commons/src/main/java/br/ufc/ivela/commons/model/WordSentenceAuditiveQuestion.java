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
@Table(name = "word_sentence_auditive_question")
@NamedQueries({@NamedQuery(name = "WordSentenceAuditiveQuestion.findById", query = "SELECT w FROM WordSentenceAuditiveQuestion w WHERE w.id = :id"), @NamedQuery(name = "WordSentenceAuditiveQuestion.findByWord", query = "SELECT w FROM WordSentenceAuditiveQuestion w WHERE w.word = :word"), @NamedQuery(name = "WordSentenceAuditiveQuestion.findByFile", query = "SELECT w FROM WordSentenceAuditiveQuestion w WHERE w.file = :file"), @NamedQuery(name = "WordSentenceAuditiveQuestion.findBySequence", query = "SELECT w FROM WordSentenceAuditiveQuestion w WHERE w.sequence = :sequence")})
public class WordSentenceAuditiveQuestion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_word_sentence_auditive_question", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "word", nullable = false)
    private String word;
    @Column(name = "file", nullable = false)
    private String file;
    @Column(name = "sequence", nullable = false)
    private int sequence;
    @JoinColumn(name = "sentence", referencedColumnName = "id")
    @ManyToOne
    private SentenceAuditiveQuestion sentence;

    public WordSentenceAuditiveQuestion() {
    }

    public WordSentenceAuditiveQuestion(Long id) {
        this.id = id;
    }

    public WordSentenceAuditiveQuestion(Long id, String word, String file, int sequence) {
        this.id = id;
        this.word = word;
        this.file = file;
        this.sequence = sequence;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
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

    public SentenceAuditiveQuestion getSentence() {
        return sentence;
    }

    public void setSentence(SentenceAuditiveQuestion sentence) {
        this.sentence = sentence;
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
        if (!(object instanceof WordSentenceAuditiveQuestion)) {
            return false;
        }
        WordSentenceAuditiveQuestion other = (WordSentenceAuditiveQuestion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.WordSentenceAuditiveQuestion[id=" + id + "]";
    }

}
