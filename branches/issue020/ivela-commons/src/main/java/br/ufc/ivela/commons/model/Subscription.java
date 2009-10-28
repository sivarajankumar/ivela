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
# File: Subscription.java                                                                   #
# Document: Subscription Model                                                              # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 24-SEP-2009 - otofuji (Instituto Eldorado)      - 000016 - Review Mail                    #
#############################################################################################
*/
package br.ufc.ivela.commons.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="subscription")
public class Subscription implements Serializable {
        
    private static final long serialVersionUID = -7426940402834327740L;

    @Id
    @SequenceGenerator(name="sequencer", sequenceName="sq_subscription", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequencer")
    @Column(name = "id", nullable = false)
    private Long id;    
        
    @JoinColumn(name = "user", referencedColumnName = "id")
    @ManyToOne
    private SystemUser user;
    
    // Optional If you want to override the email for the user, or just send an email.
    @Column(name = "recipient")
    private String recipient;
    
    @Column(name = "category", nullable = false)
    private String category;
    
    @Column(name = "category_id", nullable = false)
    private Long categoryId;    
    
    @Column(name = "type", nullable = false)
    private String type = "";    

    @Column(name = "custom_layout")
    private String customLayout;        
    
    // Retry by Default
    @Column(name= "retry", nullable = false)
    private Boolean retry = true;
    
    public Subscription() {
        
    }
    
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCustomLayout() {
        return customLayout;
    }
    
    public void setCustomLayout(String customLayout) {
        this.customLayout = customLayout;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public void setUser(SystemUser user) {
        this.user = user;
    }

    public SystemUser getUser() {
        return user;
    }

    public void setRetry(Boolean retry) {
        this.retry = retry;
    }

    public Boolean getRetry() {
        return retry;
    }    

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }        
}
