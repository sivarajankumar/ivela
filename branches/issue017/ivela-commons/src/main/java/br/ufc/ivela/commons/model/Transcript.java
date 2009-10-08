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
# File: Transcript.java                                                                            #
# Document: Transcript Model                                                                       #
# Date        - Author(Company)                   - Issue# - Summary                               #
# XX-XXX-XXXX - Rodrigo                           - XXXXXX - Initial Version                       #
# 07-OCT-2009 - Otofuji (Instituto Eldorado)      - 000017 - Review Course, Challenge refactory    #
##################################################################################################*/
package br.ufc.ivela.commons.model;

import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "transcript")
@NamedQueries({@NamedQuery(name = "Transcript.findById", query = "SELECT t FROM Transcript t WHERE t.id = :id"), @NamedQuery(name = "Transcript.findByStatus", query = "SELECT t FROM Transcript t WHERE t.status = :status"), @NamedQuery(name = "Transcript.findByScore", query = "SELECT t FROM Transcript t WHERE t.score = :score")})
public class Transcript implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="sq_transcript", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "status", nullable = false)
    private int status;
    @Column(name = "score", nullable = true)
    private Double score;
    @Column(name = "average_exercise", nullable = true)
    private Double averageExercise;
    @Column(name = "average_exam", nullable = true)
    private Double averageExam;
    @Column(name = "average_challenge", nullable = true)
    private Double averageChallenge;    
    @Column(name = "challenges_done", nullable = true)
    private int challengesDone;
    @Column(name = "challenges_weight", nullable = true)
    private int challengesWeight;
    @Column(name = "manual_score", nullable = true)
    private Double manualScore;
    @JoinColumn(name = "grade", referencedColumnName = "id")
    @ManyToOne
    private Grade grade;
    @JoinColumn(name = "system_user", referencedColumnName = "id")
    @ManyToOne
    private SystemUser systemUser;

    public Transcript() {
    }

    public Transcript(Long id) {
        this.id = id;
    }

    public Transcript(Long id, int status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    public Double getAverageExam() {
        return averageExam;
    }

    public void setAverageExam(Double averageExam) {
        this.averageExam = averageExam;
    }

    public Double getAverageExercise() {
        return averageExercise;
    }

    public void setAverageExercise(Double averageExercise) {
        this.averageExercise = averageExercise;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getManualScore() {
        return manualScore;
    }

    public void setManualScore(Double manualScore) {
        this.manualScore = manualScore;
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
        if (!(object instanceof Transcript)) {
            return false;
        }
        Transcript other = (Transcript) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufc.ivela.commons.model.Transcript[id=" + id + "]";
    }

    /**
     * @param averageChallenge the averageChallenge to set
     */
    public void setAverageChallenge(Double averageChallenge) {
        this.averageChallenge = averageChallenge;
    }

    /**
     * Average up to now.
     * 
     * @return the averageChallenge
     */
    public Double getAverageChallenge() {
        return averageChallenge;
    }

    /**
     * @param challengesDone the challengesDone to set
     */
    public void setChallengesDone(int challengesDone) {
        this.challengesDone = challengesDone;
    }

    /**
     * @return the challengesDone
     */
    public int getChallengesDone() {
        return challengesDone;
    }

    /**
     * @param challengesWeight the challengesWeight to set
     */
    public void setChallengesWeight(int challengesWeight) {
        this.challengesWeight = challengesWeight;
    }

    /**
     * @return the challengesWeight
     */
    public int getChallengesWeight() {
        return challengesWeight;
    }

}
