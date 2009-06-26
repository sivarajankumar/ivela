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
# File: Address.java                                                                        #
# Document: Address Model                                                                   # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# ??-???-2008 - leoomoreira (UFC)                 - XXXXXX - Initial Version                #
# 22-JUN-2009 - otofuji (Instituto Eldorado)      - 000010 - General Initial Fixes          #
*/

package br.ufc.ivela.commons.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "address")
@NamedQueries({@NamedQuery(name = "Address.findById", query = "SELECT a FROM Address a WHERE a.id = :id"), @NamedQuery(name = "Address.findByLocation", query = "SELECT a FROM Address a WHERE a.location = :location"), @NamedQuery(name = "Address.findByNumber", query = "SELECT a FROM Address a WHERE a.number = :number"), @NamedQuery(name = "Address.findByAdditionalInformation", query = "SELECT a FROM Address a WHERE a.additionalInformation = :additionalInformation"), @NamedQuery(name = "Address.findByNeighborhood", query = "SELECT a FROM Address a WHERE a.neighborhood = :neighborhood"), @NamedQuery(name = "Address.findByZipCode", query = "SELECT a FROM Address a WHERE a.zipCode = :zipCode"), @NamedQuery(name = "Address.findByCity", query = "SELECT a FROM Address a WHERE a.city = :city")})
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_address", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "location", nullable = false)
    private String location;
    @Column(name = "number", nullable = false)
    private String number;
    @Column(name = "additional_information", nullable = true)
    private String additionalInformation;
    @Column(name = "neighborhood", nullable = true)
    private String neighborhood;
    @Column(name = "zip_code", nullable = false)
    private String zipCode;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "state", nullable = true)
    private String state;        
    @Column(name="country", nullable = false)    
    private Integer country;
    
    @JoinColumn(name = "address_type", referencedColumnName = "id")
    @ManyToOne
    private AddressType addressType;          
    
    @Column(name="profile")
    private Long profileId;    
    @Transient
    private Profile profile;

    public Address() {
    }

    public Address(Long id) {
        this.id = id;
    }

    public Address(Long id, String location, String number, String additionalInformation, String neighborhood, String zipCode, String city, Integer country) {
        this.id = id;
        this.location = location;
        this.number = number;
        this.additionalInformation = additionalInformation;
        this.neighborhood = neighborhood;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public Integer getCountry() {
        return this.country;
    }
    
    public void setCountry(Integer country) {
        this.country = country;
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
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.Address[id=" + id + "]";
    }

}
