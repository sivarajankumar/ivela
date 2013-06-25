/**
 * @(#)ChallengeSolver.java
 *
 * Eldorado Confidential and Proprietary Information
 * Copyright (C)2010 Eldorado, All Rights Reserved
 *
 * Hist�rico de Modifica��es
 *
 * Data           Quem              Descri��o
 * ------------------------------------------------------------------------------------------------
 * 07 May 2010    Rafael Lagoa      (4115)Reajuste arquitetural.  
 */
package org.ivela.offline.functions;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ivela.offline.challenger.Converter2ObjectFactory;
import org.ivela.offline.challenger.Field;
import org.ivela.offline.challenger.IvelaObj;
import org.ivela.offline.challenger.XMLObject;
import org.ivela.offline.commons.ConnectionUtil;
import org.ivela.offline.commons.LogWrapper;
import org.ivela.offline.dao.ChallengeDAO;
import org.ivela.offline.dao.ChallengeItemsDAO;
import org.ivela.offline.dao.CourseDAO;
import org.ivela.offline.dao.TranscriptDAO;
import org.ivela.offline.domain.Challenge;
import org.ivela.offline.domain.ChallengeExample;
import org.ivela.offline.domain.ChallengeItems;
import org.ivela.offline.domain.ChallengeItemsExample;
import org.ivela.offline.domain.Course;
import org.ivela.offline.domain.Transcript;
import org.ivela.offline.domain.TranscriptExample;

public class ChallengeSolver extends BrowserFunction {

    private static LogWrapper logger = new LogWrapper(ChallengeSolver.class);
    private static CourseDAO courseDao = new CourseDAOImpl(ConnectionUtil.getSqlMapClient());
    private static TranscriptDAO transcriptDao = new TranscriptDAOImpl(ConnectionUtil.getSqlMapClient());
    private static ChallengeDAO challengeDao = new ChallengeDAOImpl(ConnectionUtil.getSqlMapClient());
    private static ChallengeItemsDAO challengeItemsDAO = new ChallengeItemsDAOImpl(ConnectionUtil.getSqlMapClient());

    public ChallengeSolver (Browser browser, String name) {
        super (browser, name);
    }
    public Object function (Object[] arguments) {
        String challid = arguments[0].toString();
        String retrieveAnswers = arguments[1].toString();
        String dependency = arguments[2].toString();
        Boolean checkDependency = Boolean.FALSE;
        if (dependency != null) {
            checkDependency = Boolean.parseBoolean(dependency);
        }

        try {
            if (retrieveAnswers != null && retrieveAnswers.equals("t")) {
                return retrieveChallengeAnswers(challid);
            } else if (!challid.equals("audio")) {
                Map parameterMap = new HashMap();
                if ((arguments[3] != null) && (!"".equals(arguments[3].toString()))) {
                    String[] parameters = arguments[3].toString().split("&");
                    for (int i=0; i<parameters.length; i++) {
                        if (parameters[i].split("=").length!=0) {
                        	String key = parameters[i].split("=")[0];
                        	String value = parameters[i].split("=").length==1?"":parameters[i].split("=")[1];
                        	if (parameterMap.containsKey(key)) {
                        		Object tempValue = parameterMap.get(key);
                        		String[] finalValue; 
                        		if (tempValue instanceof String) {
                        			finalValue = new String[2];
                        			finalValue[0] = (String) tempValue;
                        			finalValue[1] = value;
                        		} else {
                        			String[] tempArray = (String[]) tempValue;
                        			finalValue = new String[tempArray.length + 1];
                        			for (int z=0; z < tempArray.length; z++) {
                        				finalValue[z] = tempArray[z];
                        			}
                        			finalValue[finalValue.length - 1] = value;
                        		}
                        		parameterMap.put(key, finalValue);
                        	} else {
                        		parameterMap.put(key, value);	
                        	}
                        }
                    }
                }
                return executeChallenge(challid, parameterMap, checkDependency);
// ATTENTION: This condition is to be used in the future if needed (Voice Recorder)
//            } else if (challid.equals("audio")) {
//                challRemote.add(request.getParameter("value"), uid, 100);
//                Logger.getLogger(this.getClass().getName()).log(Level.INFO, request.getParameter("value").trim());
            }
        } catch (SQLException e) {
        	logger.error(e);
            return "{\"list\":{\"results\":[{\"ret\":\"\"}]},\"name\":\"" + challid + "\", \"status\": \"err\"}";
        }
        return "";
    }

    public String executeChallenge(String challid, Map userAnswers, boolean dependency) throws SQLException {
        Challenge currentStats = null;
        Course course = null;
        ChallengeItems challItem = null;
        int retriesLeft = -1;
        ChallengeItemsExample challengeItemsExample = new ChallengeItemsExample();
        challengeItemsExample.createCriteria().andNameEqualTo(challid);
        challItem = (ChallengeItems)challengeItemsDAO.selectByExample(challengeItemsExample).get(0);
        if (challItem == null) {
        	logger.error(new IllegalArgumentException(), "Invalid Challenge Item:" + challid);
        }
        Integer weight = challItem.getWeight();        
        boolean scorable = weight > 0? true : false;     
        boolean addScoreUser = true;
        
        if (dependency) {
            Long dependentChallengeId = challItem.getDependency();
            if (dependentChallengeId == null) dependentChallengeId =  new Long(0);
            ChallengeItems dependentChallenge = null;
            try {
                dependentChallenge = challengeItemsDAO.selectByPrimaryKey(Integer.parseInt(dependentChallengeId.toString()));
            } catch (NumberFormatException nfe) {
            	logger.error(nfe);
            }

            if (dependentChallenge != null) {
                Challenge depStats = null;
                ChallengeExample challengeExample = new ChallengeExample();
                challengeExample.createCriteria().andChallidEqualTo(dependentChallenge.getName());
                try {
                    depStats = (Challenge)challengeDao.selectByExample(challengeExample).get(0);
                } catch(Exception e) {
                	// do nothing
                }
                if (depStats == null) {
                    return buildJsonAnswer(null, null, null, 0, challid, "dep:" + dependentChallenge.getName(), retriesLeft);
                }
            }
            
        }
        if (scorable) {
            course = courseDao.selectByPrimaryKey(challItem.getCourse());
            ChallengeExample challengeExample = new ChallengeExample();
            challengeExample.createCriteria().andChallidEqualTo(challItem.getName());
            try {
                currentStats = (Challenge)challengeDao.selectByExample(challengeExample).get(0);
            } catch(Exception e) {
            	// do nothing
            }

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

        TranscriptExample transcriptExample = new TranscriptExample();
        List<Transcript> transcripts = null;
        transcripts = transcriptDao.selectByExample(transcriptExample);

        if (transcripts.size() > 0) {
            if (scorable) {
                // Leverages the weight of the exercise                
                Transcript transcript = transcripts.get(0);
                double average = transcript.getAverageChallenge().doubleValue();
                double challengesDone = (double) transcript.getChallengesWeight();
                double total = transcript.getChallengesTotal().doubleValue();
                int numberChallenges = transcript.getChallengesDone();
                if (currentStats != null) {
                    // The student has done the exercise before
                    average = (average * challengesDone)
                            - (currentStats.getChallvalue() * weight);
                    total = total - (currentStats.getChallvalue() * weight);
                } else {
                    average = (average * challengesDone);
                    challengesDone = challengesDone + weight;
                    numberChallenges++;
                }
                total = total + (result * weight);

                if (challengesDone > 0) {
                    average = (average + (result * weight)) / challengesDone;
                } else {
                    average = 0; // Should not be possible
                }

                transcript.setChallengesTotal(BigDecimal.valueOf(total));
                transcript.setAverageChallenge(BigDecimal.valueOf(average));
                transcript.setChallengesDone(Short.valueOf(String.valueOf(numberChallenges)));
                transcript.setChallengesWeight((int) challengesDone);
                transcriptDao.updateByPrimaryKey(transcript);
            } else {
                Transcript transcript = transcripts.get(0);
                ChallengeExample challengeExample = new ChallengeExample();
                challengeExample.createCriteria().andChallidEqualTo(challItem.getName());
                try {
                    currentStats = (Challenge)challengeDao.selectByExample(challengeExample).get(0);
                } catch(Exception e) {
                	// do nothing
                }
                if (currentStats == null) {
                    transcript.setChallengesDone(Short.valueOf(String.valueOf(transcript.getChallengesDone() + 1)));
                    transcriptDao.updateByPrimaryKey(transcript);
                }
            }
        }
        // Update Later, in case of fail here, the student has their score at least
        if (addScoreUser) add(challid, result, challItem.getId());

        return buildJsonAnswer(rightFields, wrongFields, null, result, challid, status, retriesLeft);
    }

    public Long add(String challid, double challvalue, long challengeItemId) throws SQLException {
        ChallengeExample challengeExample = new ChallengeExample();
        challengeExample.createCriteria().andChallidEqualTo(challid);
        challengeExample.createCriteria().andIdEqualTo(challengeItemId);
        List<Challenge> list = null;
        list = challengeDao.selectByExample(challengeExample);
        if(list!=null && list.size() > 0){
            Challenge chal = list.get(0);
            chal.setChallvalue(challvalue);
            chal.setRetries(Short.valueOf(String.valueOf(chal.getRetries() + 1)));
            challengeDao.updateByPrimaryKey(chal);
            return chal.getId();
        } else {
            Challenge chall =  new Challenge();
            chall.setChallid(challid);
            chall.setUid(new Long(1));
            chall.setChallvalue(challvalue);
            chall.setId(challengeItemId);
            chall.setUnitId(1);
            chall.setRetries(Short.valueOf(String.valueOf(1)));
                challengeDao.updateByPrimaryKey(chall);
            return add(chall);
        }
    }

    public Long add(Challenge challenge) throws SQLException {
        Challenge c = null;
        try {
            c = challengeDao.selectByPrimaryKey(Long.valueOf(challenge.getId()));
        } catch (NumberFormatException nfe) {
        	logger.error(nfe);
        } 
        if (c == null) {
            challengeDao.insert(challenge);
            return challenge.getId();
        } else {
            c.setChallvalue(challenge.getChallvalue());
            c.setRetries(challenge.getRetries());
            challengeDao.updateByPrimaryKey(c);
            return challenge.getId();
        }
    }

    public String retrieveChallengeAnswers(String challid) throws SQLException {
        ChallengeItemsExample challengeItemsExample = new ChallengeItemsExample();
        challengeItemsExample.createCriteria().andNameEqualTo(challid);
        ChallengeItems challItem = null;
        challItem = (ChallengeItems)challengeItemsDAO.selectByExample(challengeItemsExample).get(0);
        if (challItem == null) {
            logger.error(new IllegalArgumentException(), "Invalid Challenge Item:" + challid);
        }
        Challenge currentStats = null;
        ChallengeExample challengeExample = new ChallengeExample();
        challengeExample.createCriteria().andChallidEqualTo(challItem.getName());
        try {
            currentStats = (Challenge)challengeDao.selectByExample(challengeExample).get(0);
        } catch(Exception e) {
        	// do nothing
        }

        double results = 0.0;
        if (currentStats != null) {
            results = currentStats.getChallvalue();
            currentStats.setRetries(Short.valueOf("-1"));
        } else {
            currentStats = new Challenge();
            currentStats.setChallid(challid);
            currentStats.setUid(new Long(1));
            currentStats.setRetries(Short.valueOf("-1"));
            currentStats.setChallvalue((double)0);

            TranscriptExample transcriptExample = new TranscriptExample();
            List<Transcript> transcripts = null;
            transcripts = transcriptDao.selectByExample(transcriptExample);

            if (transcripts.size() > 0) {
                Transcript transcript = transcripts.get(0);
                int weight = challItem.getWeight();
                double average = transcript.getAverageChallenge().doubleValue();                
                double challengesDone = (double) transcript.getChallengesWeight();                                
                int numberChallenges = transcript.getChallengesDone();
                   
                challengesDone = challengesDone + weight;
                numberChallenges++;                
                
                average = (double) (average / challengesDone);
                transcript.setAverageChallenge(BigDecimal.valueOf(average));
                transcript.setChallengesDone(Short.valueOf(String.valueOf(numberChallenges)));
                transcript.setChallengesWeight((int) challengesDone);
                transcriptDao.updateByPrimaryKey(transcript);
            }
        }

        challengeDao.updateByPrimaryKey(currentStats);
        return getAnswers(challItem, results);        
    }

    private String buildJsonAnswer(List<String> rightFields , List<String> wrongFields , 
            List<String> answers, double results, String challengeId, String status, int retriesLeft) {
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
        xo.setIntType(Converter2ObjectFactory.CHALLENGE_TYPE);
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
            answerFields.add(answers.get(i).getValue().trim());
        }
        return buildJsonAnswer(rightFields, null, answerFields, results, challItem.getName(), "fin", 0);
    }

    private boolean compareAnswer(String correctAnswer, String userAnswer) {
        boolean isCorrect = false;
        if (userAnswer == null) {
            return isCorrect;
        }

        userAnswer = userAnswer.trim();        
        String[] listAnswer = correctAnswer.split("\\[or\\]");
        outerLoop:
        for (String answer: listAnswer) {
            String[] andAnswer = correctAnswer.split("\\[and\\]");
            if (userAnswer.isEmpty()) {
                isCorrect = userAnswer.equals(answer);
                break;
            }

            if (andAnswer.length > 1) {                
                for (String andStr: andAnswer) {
                    andStr = andStr.trim();
                    String cmpAnswer = userAnswer;
                    userAnswer = userAnswer.replace(andStr, "");
                    if (userAnswer.equals(cmpAnswer)) {                        
                        continue outerLoop;
                    }                    
                }
                if (userAnswer.trim().isEmpty()) {
                    isCorrect = true;
                    break;
                }
            } else {
                if (answer.trim().equals(userAnswer)) {
                    isCorrect = true;
                    break;
                }       
            }            
        }
        return isCorrect;
    }
}