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
#############################################################################################
# File: ContentInfoAction.java                                                              #
# Document: Action for Course Content                                                       #
# Date        - Author(Company)                   - Issue# - Summary                        #
# xx-xxx-xxxx - Leo Moreira                       - xxxxxx - Initial version                #
# 09-OCT-2009 - Rafael Lagoa (Instituto Eldorado) - 000017 - Adding new fields              #
#############################################################################################
*/
package br.ufc.ivela.commons.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "unit_content")
@Cache(region="unitContentCache", usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries({@NamedQuery(name = "UnitContent.findById", query = "SELECT u FROM UnitContent u WHERE u.id = :id"), @NamedQuery(name = "UnitContent.findByOrderN", query = "SELECT u FROM UnitContent u WHERE u.orderN = :orderN"), @NamedQuery(name = "UnitContent.findByTitle", query = "SELECT u FROM UnitContent u WHERE u.title = :title"), @NamedQuery(name = "UnitContent.findByDescription", query = "SELECT u FROM UnitContent u WHERE u.description = :description")})
public class UnitContent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_unit_content", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "order_n", nullable = false)
    private int orderN;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="unitContentId", targetEntity=Exercise.class)
    private Collection<Exercise> exercises;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="unitContentId", targetEntity=Exam.class)
    private Collection<Exam> exams; 

    @Column(name="unit")
    private Long unitId;    
    @Transient
    private Unit unit;

    @Column(name="tag")
    private String tag;
    
    private Integer type;

    @Column(name = "width")
    private Integer width;

    @Column(name = "height")
    private Integer height;

    @Column(name = "duration")
    @Temporal(TemporalType.TIMESTAMP)
    private Date duration;

    public UnitContent() {
        try {
			this.duration = new SimpleDateFormat("hh:mm:ss").parse("00:00:00");
		} catch (ParseException e) {
			// do nothing
		}
    }

    public UnitContent(Long id) {
        this.id = id;
        try {
			this.duration = new SimpleDateFormat("hh:mm:ss").parse("00:00:00");
		} catch (ParseException e) {
			// do nothing
		}
    }

    public UnitContent(Long id, int orderN, String title, String description) {
        this.id = id;
        this.orderN = orderN;
        this.title = title;
        this.description = description;
        try {
			this.duration = new SimpleDateFormat("hh:mm:ss").parse("00:00:00");
		} catch (ParseException e) {
			// do nothing
		}
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOrderN() {
        return orderN;
    }

    public void setOrderN(int orderN) {
        this.orderN = orderN;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public void setTag(String tag) {
    	this.tag = tag;
    }
    
    public String getTag() {
    	return tag;
    }
    
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Collection<Exam> getExams() {
        return exams;
    }

    public void setExams(Collection<Exam> exams) {
        this.exams = exams;
    }

    public Collection<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(Collection<Exercise> exercises) {
        this.exercises = exercises;
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
        if (!(object instanceof UnitContent)) {
            return false;
        }
        UnitContent other = (UnitContent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.UnitContent[id=" + id + "]";
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }
}