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
# File: GradeResource.java                                                                         #
# Document: Resource object for Grades                                                             #
# Date        - Author(Company)                   - Issue# - Summary                               #
# XX-XXX-XXX -  Maristella Myrian                 - XXXXXX - Initial Version                       #
# 02-DEC-2009 - Otofuji (Eldorado)                - 000021 - Create Initial Ivela Services         #
##################################################################################################*/
package br.ufc.ivela.ejb.ws.objects;

import java.util.Date;

import br.ufc.ivela.commons.model.Grade;


public class GradeResource {    
    private Long id;

    private String name;

    private String period;

    private int maxStudents;
    
    private boolean requiresEnrollmentValidation;
    
    private Date startDatetime;
    
    private Date endDatetime;
    
    private int maxDuration;    

    private int countStudents;
    
    public GradeResource() {}
    
    public GradeResource(Grade grade) {
        this.id = grade.getId();
        this.name = grade.getName();
        this.maxStudents = grade.getMaxStudents();
        this.requiresEnrollmentValidation = grade.getRequiresEnrollmentValidation();
        this.startDatetime = grade.getStartDatetime();
        this.endDatetime = grade.getEndDatetime();
        this.maxDuration = grade.getMaxDuration();
    }
    
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param period the period to set
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * @return the period
     */
    public String getPeriod() {
        return period;
    }

    /**
     * @param maxStudents the maxStudents to set
     */
    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    /**
     * @return the maxStudents
     */
    public int getMaxStudents() {
        return maxStudents;
    }

    /**
     * @param requiresEnrollmentValidation the requiresEnrollmentValidation to set
     */
    public void setRequiresEnrollmentValidation(boolean requiresEnrollmentValidation) {
        this.requiresEnrollmentValidation = requiresEnrollmentValidation;
    }

    /**
     * @return the requiresEnrollmentValidation
     */
    public boolean isRequiresEnrollmentValidation() {
        return requiresEnrollmentValidation;
    }

    /**
     * @param startDatetime the startDatetime to set
     */
    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    /**
     * @return the startDatetime
     */
    public Date getStartDatetime() {
        return startDatetime;
    }

    /**
     * @param maxDuration the maxDuration to set
     */
    public void setMaxDuration(int maxDuration) {
        this.maxDuration = maxDuration;
    }

    /**
     * @return the maxDuration
     */
    public int getMaxDuration() {
        return maxDuration;
    }

    /**
     * @param endDatetime the endDatetime to set
     */
    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }

    /**
     * @return the endDatetime
     */
    public Date getEndDatetime() {
        return endDatetime;
    }

    /**
     * @param countStudents the countStudents to set
     */
    public void setCountStudents(int countStudents) {
        this.countStudents = countStudents;
    }

    /**
     * @return the countStudents
     */
    public int getCountStudents() {
        return countStudents;
    }
}
