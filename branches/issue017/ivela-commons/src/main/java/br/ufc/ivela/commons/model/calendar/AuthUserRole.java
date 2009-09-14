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
@Table(name = "_auth_userrole", schema="public")
public class AuthUserRole {

    @Id
    @Column(name = "username", nullable = false)
    private String username;
    
    @Column(name = "role", nullable = false)
    private String role;

    public AuthUserRole() {
    }
        
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AuthUserRole other = (AuthUserRole) obj;
        if (this.username != other.username && (this.username == null || !this.username.equals(other.username))) {
            return false;
        }
        if (this.role != other.role && (this.role == null || !this.role.equals(other.role))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.username != null ? this.username.hashCode() : 0);
        hash = 71 * hash + (this.role != null ? this.role.hashCode() : 0);
        return hash;
    }
}