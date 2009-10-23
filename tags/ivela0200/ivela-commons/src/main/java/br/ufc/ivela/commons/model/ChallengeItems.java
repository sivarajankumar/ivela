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
# File: ChallengeItems.java                                                                        #
# Document: Challenge Item Model                                                                   #
# Date        - Author(Company)                   - Issue# - Summary                               #
# XX-XXX-XXXX - Leonardo Moreira                  - XXXXXX - Initial Version                       #
# 07-OCT-2009 - Otofuji (Instituto Eldorado)      - 000017 - Review Course, Challenge refactory    #
##################################################################################################*/
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "challenge_items")
public class ChallengeItems implements Comparable<ChallengeItems>, Serializable {
    private static final long serialVersionUID = 1L;
    
    /** Default weight for a challenge */
    public static final int DEFAULT_WEIGHT = 1;
    
    /** Default OR separator used in the answer fields **/
    public static final String ANSWER_OR_SEPARATOR = "\\[or\\]";
    
    /** Default AND separator used in the answer fields **/
    public static final String ANSWER_AND_SEPARATOR = "\\[and\\]";
    
    
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_challenge_items", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", unique= true)
    private String name;
    @Column(name = "xml")
    private String xml;
    @Column(name = "dependency")
    private Long dependency;
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @Column(name = "weight", nullable = false)
    private Integer weight = DEFAULT_WEIGHT;
    
    @JoinColumn(name = "course", referencedColumnName = "id")
    @ManyToOne
    private Course course;
    @JoinColumn(name = "discipline", referencedColumnName = "id")
    @ManyToOne
    private Discipline discipline;
    @JoinColumn(name = "unit", referencedColumnName = "id")
    @ManyToOne
    private Unit unit;
    
    public ChallengeItems() {
    }

    public ChallengeItems(Long id) {
        this.id = id;
    }

    public ChallengeItems(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Long getDependency() {
        return dependency;
    }

    public void setDependency(Long dependency) {
        this.dependency = dependency;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
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
        if (!(object instanceof ChallengeItems)) {
            return false;
        }
        ChallengeItems other = (ChallengeItems) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.ChallengerItems[id=" + id + "]";
    }

    /**
     * Weight for a challenge, 0 or less means that the challenge will not be counted for the student score
     *  
     * @param weight the weight to set
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * Weight for a challenge, 0 or less means that the challenge will not be counted for the student score
     * 
     * @return the weight
     */
    public Integer getWeight() {
        return weight;
    }

    public int compareTo(ChallengeItems o) {
        return name.compareTo(o.getName());
    }

}
