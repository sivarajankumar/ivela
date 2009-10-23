/*##################################################################################################
# Copyright(c) 2008-2009 by IBM Brasil Ltda and others                                             #
# This file is part of ivela project, an open-source                                               #
# Program URL   : http://code.google.com/p/ivela/                                                  # 
#                                                                                                  #
# This program is free software; you can redistribute it and/or modify it under the terms          #
# of the GNU General Public License as published by the Free Software Foundation; either           #
# version 3 of the License, or (at your option) any later version.                                 #
#                                                                                                  #
# This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;        #
# without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.        #
# See the GNU General Public License for more details.                                             #
#                                                                                                  #
####################################################################################################
# File: Challenge.java                                                                             #
# Document: User Challenge Result Model                                                            #
# Date        - Author(Company)                   - Issue# - Summary                               #
# XX-XXX-XXXX - Jose Damico                       - XXXXXX - Initial Version                       #
# 07-OCT-2009 - Otofuji (Instituto Eldorado)      - 000017 - Review Course, Challenge refactory    #
##################################################################################################*/
package br.ufc.ivela.commons.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/*
 * CREATE TABLE challenge (
    id numeric(10,0) NOT NULL,
    challid character varying(100) NOT NULL,
    challvalue double precision,
    uid numeric(10,0) NOT NULL
);

ALTER TABLE ivela.challenge OWNER TO ivela;

CREATE SEQUENCE sq_challenge
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ivela.sq_challenge OWNER TO ivela;

ALTER SEQUENCE sq_challenge OWNED BY challenge.id;

ALTER TABLE user_voice ALTER COLUMN id SET DEFAULT nextval('sq_challenge'::regclass);

ALTER TABLE ONLY challenge
    ADD CONSTRAINT challenge_pkey PRIMARY KEY (id);
 *
 */


@Entity
@Table(name = "challenge")
public class Challenge implements Serializable {
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_challenge", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "challid", nullable = false)
    private String challid;
    @Column(name = "challvalue", nullable = false)
    private double challvalue;
    @Column(name = "uid", nullable = false)
    private Long uid;    
    @Column(name = "unit_id", nullable = false)
    private Long challengeId;    
    @Column(name = "retries", nullable = false)
    private int retries;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public double getChallvalue() {
        return challvalue;
    }

    public void setChallvalue(double challvalue) {
        this.challvalue = challvalue;
    }

    public Challenge() {
    }

    public Challenge(Long id, String challid) {
        this.id = id;
        this.challid = challid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChallid() {
        return challid;
    }

    public void setChallid(String challid) {
        this.challid = challid;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Challenge other = (Challenge) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * @param challengeId the challengeId to set
     */
    public void setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
    }

    /**
     * @return the challengeId
     */
    public Long getChallengeId() {
        return challengeId;
    }

    /**
     * @param retries the retries to set
     */
    public void setRetries(int retries) {
        this.retries = retries;
    }

    /**
     * @return the retries
     */
    public int getRetries() {
        return retries;
    }
}
