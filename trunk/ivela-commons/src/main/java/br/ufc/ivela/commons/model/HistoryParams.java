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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author leoomoreira
 */
@Entity
@Table(name = "history_params")
@NamedQueries({@NamedQuery(name = "HistoryParams.findById", query = "SELECT e FROM HistoryParams e WHERE e.id = :id"), @NamedQuery(name = "HistoryParams.findByParam", query = "SELECT e FROM HistoryParams e WHERE e.param = :param"), @NamedQuery(name = "HistoryParams.findByValue", query = "SELECT e FROM HistoryParams e WHERE e.value = :value")})
public class HistoryParams implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_history_params", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "param", nullable = false)
    private String param;
    @Column(name = "value", nullable = false)
    private String value;
    @Column(name="history")
    private Long historyId;
    
    @Transient
    private History history;    
    
    public HistoryParams() {
    }

    public HistoryParams(Long id) {
        this.id = id;
    }

    public HistoryParams(Long id, String param, String value) {
        this.id = id;
        this.param = param;
        this.value = value;        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    public Long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
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
        if (!(object instanceof HistoryParams)) {
            return false;
        }
        HistoryParams other = (HistoryParams) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.HistoryParams[id=" + id + "]";
    }

}
