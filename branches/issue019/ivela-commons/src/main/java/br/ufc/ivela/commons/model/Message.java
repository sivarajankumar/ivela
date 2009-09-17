/*    
# Copyright(c) 2009 by IBM Brasil Ltda and others                                           #
# This file is part of ivela project, an open-source                                        #
# Program URL   : http://code.google.com/p/ivela/                                           #  
#                                                                                           #
# This program is free software; you can redistribute it and/or modify it under the terms   #
# of the GNU General Public License as published by the Free Software Foundation; either    #
# version 3 of the License, or (at your option) any later version.                          #
#                                                                                           #
# This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; #
# without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. #
# See the GNU General Public License for more details.                                      #  
#                                                                                           #
#############################################################################################
# File: Message.java                                                                        #
# Document: Message's Model                                                                 # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# ??-???-2008 - rodrigo                           - XXXXXX - Initial Version                #
# 15-JUN-2009 - otofuji (Instituto Eldorado)      - 000010 - General Fixes                  #
*/

package br.ufc.ivela.commons.model;

import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "message")
@NamedQueries({@NamedQuery(name = "Message.findById", query = "SELECT m FROM Message m WHERE m.id = :id"), @NamedQuery(name = "Message.findByTitle", query = "SELECT m FROM Message m WHERE m.title = :title"), @NamedQuery(name = "Message.findByDescription", query = "SELECT m FROM Message m WHERE m.description = :description"), @NamedQuery(name = "Message.findByDatetime", query = "SELECT m FROM Message m WHERE m.datetime = :datetime"), @NamedQuery(name = "Message.findByRead", query = "SELECT m FROM Message m WHERE m.read = :read")})
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_message", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "datetime", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;
    @Column(name = "read", nullable = false)
    private boolean read;
    @Column(name = "s_delete", nullable = false)
    private boolean senderDeleted;
    @Column(name = "r_delete", nullable = false)
    private boolean recipientDeleted;
    @JoinColumn(name = "sender", referencedColumnName = "id")
    @ManyToOne
    private SystemUser sender;
    @JoinColumn(name = "recipient", referencedColumnName = "id")
    @ManyToOne
    private SystemUser recipient;

    public Message() {
    }

    public Message(Long id) {
        this.id = id;
    }

    public Message(Long id, String title, Date datetime, boolean read) {
        this.id = id;
        this.title = title;
        this.datetime = datetime;
        this.read = read;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public boolean getRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
    
    public boolean getSenderDeleted() {
        return senderDeleted;
    }

    public void setSenderDeleted(boolean senderDeleted) {
        this.senderDeleted = senderDeleted;
    }
    
    public boolean getRecipientDeleted() {
        return recipientDeleted;
    }
   
    public void setRecipientDeleted(boolean recipientDeleted) {
        this.recipientDeleted = recipientDeleted;
    }
    
    public SystemUser getSender() {
        return sender;
    }

    public void setSender(SystemUser sender) {
        this.sender = sender;
    }

    public SystemUser getRecipient() {
        return recipient;
    }

    public void setRecipient(SystemUser recipient) {
        this.recipient = recipient;
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
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.Message[id=" + id + "]";
    }

}
