/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.commons.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author jefferson
 */
@Entity
@Table(name = "question_text")
@NamedQueries({@NamedQuery(name = "QuestionText.findById", query = "SELECT q FROM QuestionText q WHERE q.id = :id"), @NamedQuery(name = "QuestionText.findByAudio", query = "SELECT q FROM QuestionText q WHERE q.audio = :audio"), @NamedQuery(name = "QuestionText.findByText", query = "SELECT q FROM QuestionText q WHERE q.text = :text")})
public class QuestionText implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id    
    @SequenceGenerator(name="seq", sequenceName="sq_question_text", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "audio", nullable = true)
    private String audio;
    @Column(name = "text", nullable = true)
    private String text;
    @JoinColumn(name = "question", referencedColumnName = "id")
        
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "question_question_text",
    joinColumns = @JoinColumn(name = "question_text"),
    inverseJoinColumns = @JoinColumn(name = "question"))
    private Set<Question> questions;

    public QuestionText() {
    }

    public QuestionText(Long id) {
        this.id = id;
    }

    public QuestionText(Long id, String audio, String text) {
        this.id = id;
        this.audio = audio;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
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
        if (!(object instanceof QuestionText)) {
            return false;
        }
        QuestionText other = (QuestionText) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.QuestionText[id=" + id + "]";
    }

}
