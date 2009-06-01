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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author marcus
 */
@Entity
@Table(name = "news_flash")
public class NewsFlash implements Serializable{

    @Id
    @SequenceGenerator(name = "seq", sequenceName = "sq_news_flash", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @Column(name = "id", nullable = false)
    private Long id;
    
    private String message;    
    private boolean read;
    
    @Column(name="sender")
    private Long senderId;
    
    @Transient
    private SystemUser sender;
    
    @Column(name="receiver")
    private Long receiverId;
    
    @Transient
    private SystemUser receiver;

    public NewsFlash() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public SystemUser getReceiver() {
        return receiver;
    }

    public void setReceiver(SystemUser receiver) {
        this.receiver = receiver;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public SystemUser getSender() {
        return sender;
    }

    public void setSender(SystemUser sender) {
        this.sender = sender;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NewsFlash other = (NewsFlash) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.message != other.message && (this.message == null || !this.message.equals(other.message))) {
            return false;
        }
        if (this.senderId != other.senderId && (this.senderId == null || !this.senderId.equals(other.senderId))) {
            return false;
        }
        if (this.sender != other.sender && (this.sender == null || !this.sender.equals(other.sender))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 19 * hash + (this.message != null ? this.message.hashCode() : 0);
        hash = 19 * hash + (this.senderId != null ? this.senderId.hashCode() : 0);
        hash = 19 * hash + (this.sender != null ? this.sender.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.NewsFlash[id=" + id + ", message=" + message +"]";
    }
}
