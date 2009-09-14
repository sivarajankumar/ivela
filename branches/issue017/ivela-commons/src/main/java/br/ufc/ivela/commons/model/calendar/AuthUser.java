/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.commons.model.calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author marcus
 */

@Entity
@Table(name = "_auth_user", schema="public")
public class AuthUser {

    @Id
    @Column(name = "username", nullable = false)
    private String username;
    
    @Column(name = "userpass", nullable = false)
    private String userpass;

    public AuthUser() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AuthUser other = (AuthUser) obj;
        if (this.username != other.username && (this.username == null || !this.username.equals(other.username))) {
            return false;
        }
        if (this.userpass != other.userpass && (this.userpass == null || !this.userpass.equals(other.userpass))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.username != null ? this.username.hashCode() : 0);
        hash = 79 * hash + (this.userpass != null ? this.userpass.hashCode() : 0);
        return hash;
    }
}