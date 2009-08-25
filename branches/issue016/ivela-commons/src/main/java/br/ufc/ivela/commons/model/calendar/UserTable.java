/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.commons.model.calendar;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author marcus
 */
@Entity
@Table(name = "user_table", schema="public")
public class UserTable {
    
    @Id
    @Column(name = "usrif", nullable = false)
    private String usrif;
    
    @Column(name = "firstname")
    private String firstName;
    
    @Column(name = "lastnameprefix")
    private String lastNamePrefix;
    
    @Column(name = "lastname")
    private String lastName;
    
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    public UserTable() {
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastNamePrefix() {
        return lastNamePrefix;
    }

    public void setLastNamePrefix(String lastNamePrefix) {
        this.lastNamePrefix = lastNamePrefix;
    }

    public String getUsrif() {
        return usrif;
    }

    public void setUsrif(String usrif) {
        this.usrif = usrif;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserTable other = (UserTable) obj;
        if (this.usrif != other.usrif && (this.usrif == null || !this.usrif.equals(other.usrif))) {
            return false;
        }
        if (this.firstName != other.firstName && (this.firstName == null || !this.firstName.equals(other.firstName))) {
            return false;
        }
        if (this.lastNamePrefix != other.lastNamePrefix && (this.lastNamePrefix == null || !this.lastNamePrefix.equals(other.lastNamePrefix))) {
            return false;
        }
        if (this.lastName != other.lastName && (this.lastName == null || !this.lastName.equals(other.lastName))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + (this.usrif != null ? this.usrif.hashCode() : 0);
        hash = 43 * hash + (this.firstName != null ? this.firstName.hashCode() : 0);
        hash = 43 * hash + (this.lastNamePrefix != null ? this.lastNamePrefix.hashCode() : 0);
        hash = 43 * hash + (this.lastName != null ? this.lastName.hashCode() : 0);
        return hash;
    }
}