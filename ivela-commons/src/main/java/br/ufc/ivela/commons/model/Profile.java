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
# File: Profile.java                                                                        #
# Document: User Profile Model                                                              # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# ??-???-2008 - leoomoreira (UFC)                 - XXXXXX - Initial Version                #
# 22-JUN-2009 - otofuji (Instituto Eldorado)      - 000010 - General Initial Fixes          #
*/

package br.ufc.ivela.commons.model;

import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "profile")
@Cache(region="profileCache",usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries({@NamedQuery(name = "Profile.findById", query = "SELECT p FROM Profile p WHERE p.id = :id"), @NamedQuery(name = "Profile.findByFirstName", query = "SELECT p FROM Profile p WHERE p.firstName = :firstName"), @NamedQuery(name = "Profile.findByInitials", query = "SELECT p FROM Profile p WHERE p.initials = :initials"), @NamedQuery(name = "Profile.findByLastName", query = "SELECT p FROM Profile p WHERE p.lastName = :lastName"), @NamedQuery(name = "Profile.findByBirthDate", query = "SELECT p FROM Profile p WHERE p.birthDate = :birthDate"), @NamedQuery(name = "Profile.findBySocialNumber", query = "SELECT p FROM Profile p WHERE p.socialNumber = :socialNumber"), @NamedQuery(name = "Profile.findByPhoto", query = "SELECT p FROM Profile p WHERE p.photo = :photo"), @NamedQuery(name = "Profile.findByGender", query = "SELECT p FROM Profile p WHERE p.gender = :gender"), @NamedQuery(name = "Profile.findByDisabilities", query = "SELECT p FROM Profile p WHERE p.disabilities = :disabilities")})
public class Profile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_profile", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "initials")
    private String initials;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Column(name = "social_number")
    private String socialNumber;
    @Column(name = "photo")
    private String photo;
    @Column(name = "gender")
    private Integer gender;
    @Column(name = "disabilities")
    private Boolean disabilities;
    @Column(name = "ethnicity")    
    private Integer ethnicity;    
    @JoinColumn(name = "language", referencedColumnName = "id")
    @ManyToOne
    private Language language;
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="id", targetEntity=Address.class)
    private Set<Address> addresses;
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="id", targetEntity=Phone.class)
    private Set<Phone> phones;

    public Profile() {
    }

    public Profile(Long id) {
        this.id = id;
    }

    public Profile(Long id, String initials, String socialNumber) {
        this.id = id;
        this.initials = initials;
        this.socialNumber = socialNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getSocialNumber() {
        return socialNumber;
    }

    public void setSocialNumber(String socialNumber) {
        this.socialNumber = socialNumber;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Boolean getDisabilities() {
        return disabilities;
    }

    public void setDisabilities(Boolean disabilities) {
        this.disabilities = disabilities;
    }

    public Integer getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(Integer ethnicity) {
        this.ethnicity = ethnicity;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
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
        if (!(object instanceof Profile)) {
            return false;
        }
        Profile other = (Profile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.Profile[id=" + id + "]";
    }

}
