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
@Table(name = "external_question")
@NamedQueries({@NamedQuery(name = "ExternalQuestion.findById", query = "SELECT e FROM ExternalQuestion e WHERE e.id = :id"), @NamedQuery(name = "ExternalQuestion.findByUrlBinary", query = "SELECT e FROM ExternalQuestion e WHERE e.urlBinary = :urlBinary"), @NamedQuery(name = "ExternalQuestion.findByUrlResult", query = "SELECT e FROM ExternalQuestion e WHERE e.urlResult = :urlResult")})
public class ExternalQuestion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_external_question", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "url_binary", nullable = false)
    private String urlBinary;
    @Column(name = "url_result", nullable = false)
    private String urlResult;
    @Column(name = "bin_type", nullable = true)
    private Integer binType;
        
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "external_params_external_question",
    joinColumns = @JoinColumn(name = "external_question"),
    inverseJoinColumns = @JoinColumn(name = "external_params"))
    private Set<ExternalParams> externalParams;
        
    @ManyToOne
    @JoinColumn(name = "question", referencedColumnName = "id")
    private Question question;

    public ExternalQuestion() {
    }

    public ExternalQuestion(Long id) {
        this.id = id;
    }

    public ExternalQuestion(Long id, String urlBinary, String urlResult) {
        this.id = id;
        this.urlBinary = urlBinary;
        this.urlResult = urlResult;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrlBinary() {
        return urlBinary;
    }

    public void setUrlBinary(String urlBinary) {
        this.urlBinary = urlBinary;
    }

    public String getUrlResult() {
        return urlResult;
    }

    public void setUrlResult(String urlResult) {
        this.urlResult = urlResult;
    }

    public Set<ExternalParams> getExternalParams() {
        return externalParams;
    }

    public void setExternalParams(Set<ExternalParams> externalParams) {
        this.externalParams = externalParams;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Integer getBinType() {
        return binType;
    }

    public void setBinType(Integer binType) {
        this.binType = binType;
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
        if (!(object instanceof ExternalQuestion)) {
            return false;
        }
        ExternalQuestion other = (ExternalQuestion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.ExternalQuestion[id=" + id + "]";
    }

}
