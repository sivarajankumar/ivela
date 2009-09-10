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
@Table(name = "auditive_question")
@NamedQueries({@NamedQuery(name = "AuditiveQuestion.findById", query = "SELECT a FROM AuditiveQuestion a WHERE a.id = :id")})
public class AuditiveQuestion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_auditive_question", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "conf_file", nullable = false)
    private String confFile;
    @Column(name = "chance_sentence", nullable = false)
    private Integer chanceSentence;
    @JoinColumn(name = "question", referencedColumnName = "id")
    @ManyToOne
    private Question question;

    public AuditiveQuestion() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getConfFile() {
        return confFile;
    }

    public void setConfFile(String confFile) {
        this.confFile = confFile;
    }

    public Integer getChanceSentence() {
        return chanceSentence;
    }

    public void setChanceSentence(Integer chanceSentence) {
        this.chanceSentence = chanceSentence;
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
        if (!(object instanceof AuditiveQuestion)) {
            return false;
        }
        AuditiveQuestion other = (AuditiveQuestion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.AuditiveQuestion[id=" + id + "]";
    }

}
