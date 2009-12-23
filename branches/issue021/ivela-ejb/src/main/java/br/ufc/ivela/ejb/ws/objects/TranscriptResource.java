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
# File: TranscriptResource.java                                                                    #
# Document: Resource object for Transcript                                                         #
# Date        - Author(Company)                   - Issue# - Summary                               #
# XX-XXX-XXX -  Maristella Myrian                 - XXXXXX - Initial Version                       #
# 02-DEC-2009 - Otofuji (Eldorado)                - 000021 - Create Initial Ivela Services         #
##################################################################################################*/
package br.ufc.ivela.ejb.ws.objects;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.model.Transcript;

public class TranscriptResource {
    private Long id;

    private Long gradeid;
    
    private String gradeName;
    
    private String status;
    
    private Double score;
    
    private Double manualScore;
    
    private Double average_exam;
    
    private Double average_exercise;
    
    private Double average_challenge;
    
    private Integer challenges_done;
        
    private String systemUserName;
    
    public TranscriptResource() {}
    
    public TranscriptResource(Transcript transcript) {
        this.id = transcript.getId();
        this.gradeid = transcript.getGrade().getId();
        setStatus(transcript.getStatus());
        this.score = transcript.getScore();
        this.manualScore = transcript.getManualScore();
        this.average_exam = transcript.getAverageExam();
        this.average_exercise = transcript.getAverageExercise();
        this.average_challenge = transcript.getAverageChallenge();
        this.challenges_done = transcript.getChallengesDone();        
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
     * @param gradeid the gradeid to set
     */
    public void setGradeid(Long gradeid) {
        this.gradeid = gradeid;
    }

    /**
     * @return the gradeid
     */
    public Long getGradeid() {
        return gradeid;
    }
    
    /**
     * @param status the status to set
     * 
     * @see br.ufc.ivela.commons.Constants
     */    
    public void setStatus(int status) {
        switch(status) {
            case Constants.TRANSCRIPT_FAILED:
                this.status = "FAILED";
                break;
            case Constants.TRANSCRIPT_APPROVED:
                this.status = "APPROVED";
                break;
            case Constants.TRANSCRIPT_SUSPENDED:
                this.status = "SUSPENDED";
                break;
            default:
                this.status = "ENROLLMENT";
        }
    }
    
    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param score the score to set
     */
    public void setScore(Double score) {
        this.score = score;
    }

    /**
     * @return the score
     */
    public Double getScore() {
        return score;
    }

    /**
     * @param manualScore the manualScore to set
     */
    public void setManualScore(Double manualScore) {
        this.manualScore = manualScore;
    }

    /**
     * @return the manualScore
     */
    public Double getManualScore() {
        return manualScore;
    }

    /**
     * @param average_exam the average_exam to set
     */
    public void setAverage_exam(Double average_exam) {
        this.average_exam = average_exam;
    }

    /**
     * @return the average_exam
     */
    public Double getAverage_exam() {
        return average_exam;
    }

    /**
     * @param average_exercise the average_exercise to set
     */
    public void setAverage_exercise(Double average_exercise) {
        this.average_exercise = average_exercise;
    }

    /**
     * @return the average_exercise
     */
    public Double getAverage_exercise() {
        return average_exercise;
    }

    /**
     * @param average_challenge the average_challenge to set
     */
    public void setAverage_challenge(Double average_challenge) {
        this.average_challenge = average_challenge;
    }

    /**
     * @return the average_challenge
     */
    public Double getAverage_challenge() {
        return average_challenge;
    }

    /**
     * @param challenges_done the challenges_done to set
     */
    public void setChallenges_done(Integer challenges_done) {
        this.challenges_done = challenges_done;
    }

    /**
     * @return the challenges_done
     */
    public Integer getChallenges_done() {
        return challenges_done;
    }

    /**
     * @param gradeName the gradeName to set
     */
    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    /**
     * @return the gradeName
     */
    public String getGradeName() {
        return gradeName;
    }

    /**
     * @param systemUserName the systemUserName to set
     */
    public void setSystemUserName(String systemUserName) {
        this.systemUserName = systemUserName;
    }

    /**
     * @return the systemUserName
     */
    public String getSystemUserName() {
        return systemUserName;
    }
}
