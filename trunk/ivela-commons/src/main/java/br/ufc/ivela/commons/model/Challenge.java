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

/**
 *
 * @author jdamico
 */

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
}
