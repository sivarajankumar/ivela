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
# File: ChallengeBean.java                                                                         #
# Document: Challenge Bean                                                                         #
# Date        - Author(Company)                   - Issue# - Summary                               #
# XX-XXX-XXXX - Jose Damico                       - XXXXXX - Initial Version                       #
# 07-OCT-2009 - Otofuji (Instituto Eldorado)      - 000017 - Review Course, Challenge refactory    #
##################################################################################################*/
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.Challenge;
import br.ufc.ivela.commons.model.ChallengeItems;
import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.Transcript;
import br.ufc.ivela.ejb.interfaces.ChallengeItemsRemote;
import br.ufc.ivela.ejb.interfaces.ChallengeRemote;
import br.ufc.ivela.ejb.interfaces.CourseRemote;
import br.ufc.ivela.ejb.interfaces.HistoryRemote;
import br.ufc.ivela.commons.challenger.util.XMLObject;
import br.ufc.ivela.commons.challenger.config.Constants;
import br.ufc.ivela.commons.challenger.util.Converter2ObjectFactory;
import br.ufc.ivela.commons.challenger.util.Field;
import br.ufc.ivela.commons.challenger.dataobject.IvelaObj;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;


@Stateless(mappedName="ChallengeBean")
public class ChallengeBean implements ChallengeRemote {


    private GenericDao<Challenge> daoChallenge = DaoFactory.getInstance(Challenge.class);
    
    @EJB
    private ChallengeItemsRemote challengeItemsRemote;
    
    @EJB
    private CourseRemote courseRemote;
    
    @EJB
    private HistoryRemote historyRemote;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Long add(Challenge challenge) {
        Challenge c = get(challenge.getUid(), challenge.getChallengeId());
        if (c == null)
            return (Long) daoChallenge.save(challenge);
        else {
            c.setChallvalue(challenge.getChallvalue());
            c.setRetries(challenge.getRetries());
            if (update(c))
                return challenge.getId();
        }
        return null;
    }
    
    public Challenge get(String challid, long uid) {
        if (challid == null || challid.trim().length() < 1)
            return null;
        Object[] params = new Object[]{challid, uid};
        Challenge challengeResult = null;
        List list = daoChallenge.find("from Challenge n WHERE n.challid = ? and n.uid = ?", params);
        
        if (list != null && list.size() > 0) {
            try{
                challengeResult = ((Challenge) list.get(0));
            } catch (Exception e) {
                challengeResult = null;
            }
        }

        return challengeResult;            
    }
    
    public Challenge get(Long uid, Long challengeItemId) {
        if (uid == null || challengeItemId == null) return null;
        
        Object[] params = new Object[]{uid, challengeItemId};
        Challenge challengeResult = null;
        List list = daoChallenge.find("from Challenge n WHERE n.uid = ? and n.challengeId = ?", params);
        
        if (list != null && list.size() > 0) {
            try{
                challengeResult = ((Challenge) list.get(0));
            } catch (Exception e) {
                challengeResult = null;
            }
        }

        return challengeResult;        
    }
    
    private boolean update(Challenge challenge) {
        return  daoChallenge.update(challenge);
    }
    
    public Long add(String challid, long uid, double challvalue) {
        List<Challenge> list = daoChallenge.find("select c from Challenge c where c.challid = ? and c.uid = ?", new Object[]{challid,uid});        
        if(list!=null && list.size() > 0){
            Challenge chal = list.get(0);
            chal.setChallvalue(challvalue);
            chal.setRetries(chal.getRetries() + 1);
            chal.setChallengeId((long) 0);
            this.update(chal);
            return list.get(0).getId();
        }
        else{        
            Challenge chall =  new Challenge();
            chall.setChallid(challid);
            chall.setUid(uid);
            chall.setChallvalue(challvalue);
            chall.setRetries(1);
            return add(chall);            
        }
    }

    
    public Long add(String challid, long uid, double challvalue, long challengeItemId) {
        List<Challenge> list = daoChallenge.find("select c from Challenge c where c.challid = ? and c.uid = ? and c.challengeId = ?", new Object[]{challid,uid, challengeItemId});        
        if(list!=null && list.size() > 0){
            Challenge chal = list.get(0);
            chal.setChallvalue(challvalue);
            chal.setRetries(chal.getRetries() + 1);
            this.update(chal);
            return chal.getId();
        }
        else{        
            Challenge chall =  new Challenge();
            chall.setChallid(challid);
            chall.setUid(uid);
            chall.setChallvalue(challvalue);
            chall.setChallengeId(challengeItemId);
            chall.setRetries(1);
            return add(chall);
        }
    }


    public double getChallValue(String challid, long uid) {

        Object[] params = new Object[]{challid, uid};

        List list = daoChallenge.find("from Challenge n WHERE n.challid = ? and ? and n.uid = ?", params);
        
        if (list != null && list.size() == 1) {            
            return ((Challenge) list.get(0)).getChallvalue();            
        }
        
        return -1;        
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)    
    public String executeChallenge(String challid, Long userId, Long unitId, Long gradeId, Map userAnswers, boolean dependency) {                       
        Challenge currentStats = null;
        Course course = null;
        ChallengeItems challItem = null;
        int retriesLeft = -1;
        challItem = challengeItemsRemote.getByUnit(challid, unitId);
        if (challItem == null) {
            throw new IllegalArgumentException("Invalid Challenge Item:" + challid + ',' + userId + ',' + unitId);
        }
        Integer weight = challItem.getWeight();        
        boolean scorable = weight > 0? true : false;     
        boolean addScoreUser = true;
        
        if (dependency) {
            Long dependentChallengeId = challItem.getDependency();
            if (dependentChallengeId == null) dependentChallengeId =  new Long(0);
            ChallengeItems dependentChallenge = challengeItemsRemote.get(dependentChallengeId);      
            
            if (dependentChallenge != null) {
                Challenge depStats = get(userId, dependentChallenge.getId());
                if (depStats == null) 
                    return buildJsonAnswer(null, null, null, 0, challid, "dep:" + dependentChallenge.getName(), retriesLeft);    
            }
            
        }
        if (scorable) {
            course = challItem.getCourse();
            course = course.getChallengeRetries() != null ? course : courseRemote.get(challItem.getCourse().getId());
                    
            currentStats = get(userId, challItem.getId());                    
                        
            // Maximum number of retries reached?            
            if ((currentStats != null)&&(currentStats.getRetries() < 0)) {
                scorable = false;
                addScoreUser = false;
            }
            
            if (course.getChallengeRetries() > 0) {
                if (currentStats != null) {
                    retriesLeft = course.getChallengeRetries() - currentStats.getRetries();
                    retriesLeft = retriesLeft < 0? 0 : retriesLeft;                                           
                } else {
                    retriesLeft = course.getChallengeRetries() - 1;
                }              
                
                if (retriesLeft <= 0) {
                    scorable = false;
                    addScoreUser = false;
                }
            }
        }
                        
        List<Field> answers = getRightAnswers(challItem);
                        
        String option = challid;
        List<String> rightFields = new ArrayList<String>(answers.size());
        List<String> wrongFields = new ArrayList<String>(answers.size());
        
        int points = 0;
        
        for (int i = 0; i < answers.size(); i++) {
            option = answers.get(i).getName();
            String userAnswer = "";
            Object values = userAnswers.get(option);
            if (values != null) {
                if (values instanceof String[]) {
                    String[] valoues = (String[]) values;
                    if (valoues.length == 1) 
                        userAnswer = valoues[0];
                    else
                        for (String value: valoues) {
                            userAnswer += value;
                        }                    
                } else if (values instanceof String) {                
                    userAnswer = (String) values;
                }                
            }
                            
            String dbAnswer = answers.get(i).getValue();                    

            if (compareAnswer(dbAnswer, userAnswer)) {
                points++;
                rightFields.add(option);
            } else {
                wrongFields.add(option);
            }
        }
        
        double result = ((double) (points * 100)) / (double) answers.size();
        
        String status = "nok";
        if (result >= 70) {
            status = "ok";
        }
                                        
        // Should store Scores in the database
        List<Transcript> transcripts = historyRemote.getTranscriptsByStudentByGrade(userId, gradeId);
        
        if (transcripts.size() > 0) {
            if (scorable) {
                // Leverages the weight of the exercise                
                Transcript transcript = transcripts.get(0);
                double average = transcript.getAverageChallenge();                
                double challengesDone = (double) transcript.getChallengesWeight();                                
                int numberChallenges = transcript.getChallengesDone();
                if (currentStats != null) {
                    // The student has done the exercise before
                    average = (average * challengesDone)
                            - (currentStats.getChallvalue() * weight);                                       
                } else {
                    average = (average * challengesDone);                    
                    challengesDone = challengesDone + weight;
                    numberChallenges++;
                }

                if (challengesDone > 0)
                    average = (average + (result * weight)) / challengesDone;
                else
                    average = 0; // Should not be possible
                
                transcript.setAverageChallenge(average);                
                transcript.setChallengesDone(numberChallenges);
                transcript.setChallengesWeight((int) challengesDone);
                historyRemote.updateTranscript(transcript);

            } else {
                Transcript transcript = transcripts.get(0);
                currentStats = get(userId, challItem.getId());
                if (currentStats == null) {
                    transcript.setChallengesDone(transcript.getChallengesDone() + 1);
                    historyRemote.updateTranscript(transcript);
                }
            }
        }
        // Update Later, in case of fail here, the student has their score at least
        if (addScoreUser) add(challid, userId, result, challItem.getId());
        
        return buildJsonAnswer(rightFields, wrongFields, null, result, challid, status, retriesLeft);
    }    
    
 
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String retrieveChallengeAnswers(String challid, Long userId, Long unidId, Long gradeId) {
        ChallengeItems challItem = challengeItemsRemote.getByUnit(challid, unidId);
        if (challItem == null) {
            throw new IllegalArgumentException("Invalid Challenge Item:" + challid + ',' + userId + ',' + unidId);
        }
        Challenge currentStats = get(userId, challItem.getId());
        double results = 0.0;
        if (currentStats != null) {
            results = currentStats.getChallvalue();
            currentStats.setRetries(-1);
        } else {
            currentStats = new Challenge();
            currentStats.setChallid(challid);
            currentStats.setChallengeId(challItem.getId());
            currentStats.setUid(userId);
            currentStats.setRetries(-1);
            currentStats.setChallvalue(0);
            
            List<Transcript> transcripts = historyRemote.getTranscriptsByStudentByGrade(userId, gradeId);
            
            if (transcripts.size() > 0) {
                Transcript transcript = transcripts.get(0);
                int weight = challItem.getWeight();
                double average = transcript.getAverageChallenge();                
                double challengesDone = (double) transcript.getChallengesWeight();                                
                int numberChallenges = transcript.getChallengesDone();
                   
                challengesDone = challengesDone + weight;
                numberChallenges++;                
                
                average = (double) (average / challengesDone);
                transcript.setAverageChallenge(average);
                transcript.setChallengesDone(numberChallenges);
                transcript.setChallengesWeight((int) challengesDone);
                historyRemote.updateTranscript(transcript);
            }
        }
        
        add(currentStats);
        
        return getAnswers(challItem, results);        
    }
    
    private String buildJsonAnswer(List<String> rightFields , List<String> wrongFields , List<String> answers, double results, String challengeId, String status, int retriesLeft) {
        if (rightFields == null) rightFields = new ArrayList<String>(0);
        if (wrongFields == null) wrongFields = new ArrayList<String>(0);
        if (answers == null) answers = new ArrayList<String>(0);
        
        StringBuilder builder = new StringBuilder("{\"list\":{\"right\":[");
        for (int i = 0; i < rightFields.size(); i++) {
            builder.append('"');
            builder.append(rightFields.get(i));
            builder.append('"');
            if (i < rightFields.size() - 1) builder.append(',');                    
        }
        builder.append("], \"wrong\":[");
        for (int i = 0; i < wrongFields.size(); i++) {
            builder.append('"');
            builder.append(wrongFields.get(i));
            builder.append('"');
            if (i < wrongFields.size() - 1) builder.append(',');                    
        }
        builder.append("], \"answers\":[");
        for (int i = 0; i < answers.size(); i++) {
            builder.append('"');
            builder.append(answers.get(i));
            builder.append('"');
            if (i < answers.size() - 1) builder.append(',');                    
        }
        builder.append("]},\"results\":\"");
        builder.append(results);
        builder.append("%\",\"name\":\"");
        builder.append(challengeId);
        builder.append("\", \"status\": \"");
        builder.append(status);
        if (retriesLeft >= 0) {
            builder.append("\", \"count\": \"");
            builder.append(retriesLeft);
            builder.append("\"}");
        } else {
            builder.append("\"}");    
        }
        
        
        return builder.toString();
    }

    
    private List<Field> getRightAnswers(ChallengeItems challItems) {        
        XMLObject xo = new XMLObject();
        xo.setIntType(Constants.CHALLENGE_TYPE);
        xo.setEntireXml(challItems.getXml());
        IvelaObj ivelaObj = Converter2ObjectFactory.getConverter(xo).getConverterType();
        return ivelaObj.getIvelaPayload().getChallenge().getField();
    }

    private String getAnswers(ChallengeItems challItem, double results) {
        List<Field> answers = getRightAnswers(challItem);
        
        List<String>rightFields = new ArrayList<String>(answers.size());
        List<String>answerFields = new ArrayList<String>(answers.size());
        
        for (int i = 0; i < answers.size(); i++) {
            rightFields.add(answers.get(i).getName());
            answerFields.add(answers.get(i).getValue());
        }        
        
        return buildJsonAnswer(rightFields, null, answerFields, results, challItem.getName(), "fin", 0);
    }
    
    private boolean compareAnswer(String correctAnswer, String userAnswer) {
        boolean isCorrect = false;
        
        if (userAnswer == null || userAnswer.equals("")) {
            return isCorrect;
        }
        
        userAnswer = userAnswer.trim();        
        
        String[] listAnswer = correctAnswer.split(ChallengeItems.ANSWER_OR_SEPARATOR);
                
        for (String answer: listAnswer) {
            if (answer.trim().equalsIgnoreCase(userAnswer)) {
                isCorrect = true;
                break;
            }    
        }
        
        return isCorrect;
    }
}
