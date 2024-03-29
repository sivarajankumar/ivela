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
#                                                                                           #
#############################################################################################
# File: Course.java                                                                         #
# Document: Course Model                                                                    # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 07-JAN-2009 - Leonardo Oliveira (UFC)           - XXXXXX - Initial Version                #
# 16-SEP-2009 - Otofuji (Instituto Eldorado)      - 000016 - General Fixes                  #
# 06-OCT-2009 - Fabio Fantato (Instituto Eldorado)- 000017 - Table of Contents              # 
#############################################################################################
*/

package br.ufc.ivela.commons.model;

import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "course")
@Cache(region="courseCache", usage = CacheConcurrencyStrategy.READ_WRITE)  
@NamedQueries({@NamedQuery(name = "Course.findById", query = "SELECT c FROM Course c WHERE c.id = :id"), @NamedQuery(name = "Course.findByName", query = "SELECT c FROM Course c WHERE c.name = :name"), @NamedQuery(name = "Course.findByDescription", query = "SELECT c FROM Course c WHERE c.description = :description"), @NamedQuery(name = "Course.findByImage", query = "SELECT c FROM Course c WHERE c.image = :image"), @NamedQuery(name = "Course.findByTargetAudience", query = "SELECT c FROM Course c WHERE c.targetAudience = :targetAudience"), @NamedQuery(name = "Course.findByContents", query = "SELECT c FROM Course c WHERE c.contents = :contents"), @NamedQuery(name = "Course.findByActive", query = "SELECT c FROM Course c WHERE c.active = :active")})
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_course", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String image;
    @Column(name = "target_audience")
    private String targetAudience;
    @Column(name = "contents")
    private String contents;
    @Column(name = "repository_structure")
    private String repositoryStructure;
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="course", targetEntity=CourseRequisite.class)
    private Collection<CourseRequisite> courseRequisites;
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="courseId", targetEntity=Discipline.class)
    private Collection<Discipline> disciplines;
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="courseId", targetEntity=Grade.class)
    private Collection<Grade> grades;
    @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="course", targetEntity=Forum.class)
    private Forum forum;
    @Column(name = "active")
    private boolean active;
    @Column(name = "upload_package_enabled")
    private boolean uploadPackageEnabled;
    @Column(name = "challenge_retries", nullable = false)
    private Integer challengeRetries = 0;
    @Column(name = "challenge_count")
    private Integer challengeCount = 0;
    @Column(name = "challenge_weight")
    private Integer challengeWeight = 0;
    @Column(name = "custom_toc")
    private boolean customToc;

    public Course() {
    }

    public Course(Long id) {
        this.id = id;
    }

    public Course(Long id, String name) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Collection<CourseRequisite> getCourseRequisites() {
        return courseRequisites;
    }

    public void setCourseRequisites(Collection<CourseRequisite> courseRequisites) {
        this.courseRequisites = courseRequisites;
    }

    public Collection<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(Collection<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    public Collection<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Collection<Grade> grades) {
        this.grades = grades;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }
    
    public String getRepositoryStructure() {
        return repositoryStructure;
    }

    public void setRepositoryStructure(String repositoryStructure) {
        this.repositoryStructure = repositoryStructure;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public boolean getUploadPackageEnabled() {
        return uploadPackageEnabled;
    }

    public void setUploadPackageEnabled(boolean uploadPackageEnabled) {
        this.uploadPackageEnabled = uploadPackageEnabled;
    }    

    public boolean getCustomToc() {
        return customToc;
    }

    public void setCustomToc(boolean customToc) {
        this.customToc = customToc;
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
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.Course[id=" + id + "]";
    }

    /**
     * How many retries for Challenge is possible
     * 
     * @param challengeRetries the challengeRetries to set
     */
    public void setChallengeRetries(Integer challengeRetries) {
        if (challengeRetries < 0) challengeRetries = 0; 
        this.challengeRetries = challengeRetries;
    }

    /**
     * How many retries for Challenge is possible
     * 
     * @return the challengeRetries
     */
    public Integer getChallengeRetries() {
        return challengeRetries;
    }

    /**
     * Number of Challenges in this Course.
     * @param challengeCount the challengeCount to set
     */
    public void setChallengeCount(Integer challengeCount) {
        if (challengeCount < 0) challengeRetries = 0;
        
        this.challengeCount = challengeCount;
    }

    /**
     * Number of Challenges in this Course.
     * 
     * @return the challengeCount
     */
    public Integer getChallengeCount() {
        return challengeCount;
    }

    /**
     * @param challengeWeight the challengeWeight to set
     */
    public void setChallengeWeight(Integer challengeWeight) {
        this.challengeWeight = challengeWeight;
    }

    /**
     * @return the challengeWeight
     */
    public Integer getChallengeWeight() {
        return challengeWeight;
    }

}
