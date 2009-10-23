/*    
#############################################################################################
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
# File: DelayedMail.java                                                                   #
# Document: Delayed Mail Model                                                              # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 24-SEP-2009 - otofuji (Instituto Eldorado)      - 000016 - Review Mail                    #
#############################################################################################
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

@Entity
@Table(name="delayed_mail")
public class DelayedMail implements Serializable {
        
    private static final long serialVersionUID = -897300929276651025L;

    @Id
    @SequenceGenerator(name="seque", sequenceName="sq_delayed_mail", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seque")
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "retries", nullable = false)
    private Integer retries = new Integer(0);
    
    @Column(name = "recipient", nullable = false)
    private String recipient;
    
    @Column(name = "sender")
    private String sender;
    
    @Column(name = "subject", nullable = false)
    private String subject;
    
    @Column(name = "body")
    private String body;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setRetries(Integer retries) {
        this.retries = retries;
    }

    public Integer getRetries() {
        return retries;
    }
}
