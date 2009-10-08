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
# File: ChallengeSolver.java                                                                       #
# Document: Challenge Solver Servlet                                                               #
# Date        - Author(Company)                   - Issue# - Summary                               #
# XX-XXX-XXXX - Jose Damico                       - XXXXXX - Initial Version                       #
# 07-OCT-2009 - Otofuji (Instituto Eldorado)      - 000017 - Review Course, Challenge refactory    #
##################################################################################################*/
package br.ufc.ivela.servlets;

import br.ufc.ivela.commons.model.Challenge;
import br.ufc.ivela.commons.model.ChallengeItems;
import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.commons.model.Transcript;
import br.ufc.ivela.ejb.interfaces.ChallengeItemsRemote;
import br.ufc.ivela.ejb.interfaces.ChallengeRemote;
import br.ufc.ivela.ejb.interfaces.CourseRemote;
import br.ufc.ivela.ejb.interfaces.HistoryRemote;
import br.ufc.ivela.servlets.challenge.XMLObject;
import br.ufc.ivela.servlets.challenge.Constants;
import br.ufc.ivela.servlets.challenge.Converter2ObjectFactory;
import br.ufc.ivela.servlets.challenge.Field;
import br.ufc.ivela.servlets.challenge.IvelaObj;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.context.SecurityContextHolder;


public class ChallengeSolver extends HttpServlet {

    /** Common Logging interface */
    protected Log log = LogFactory.getLog(this.getClass());
    
    private static final long serialVersionUID = 2091702349031027682L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double result = 0.0;
        int points = 0;        
        String reqParam = "clear";
        String dbAnswer = "clear";
        String status = "err";
        response.setContentType("application/json");
        ChallengeRemote challRemote = this.getChallengeRemote();
        String challid = request.getParameter("challid");    
        Long unitId = Long.decode(request.getParameter("unitId"));
        Long gradeId = Long.decode(request.getParameter("gradeId"));        
        Long uid = 0L;
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (obj != null && obj instanceof SystemUser) {
            uid = ((SystemUser) obj).getId();
        }
                
        ChallengeRemote chalRemote = null;
        Challenge currentStats = null;
        Course course = null;
        ChallengeItems challItem = null;
        try {
            if (!challid.equals("audio")) {                
                challItem = getChallengeItems(challid, unitId);
                boolean scorable = challItem.getScorable();                
                if (scorable) {
                    course = getCourseRemote().get(challItem.getCourse().getId());
                
                    chalRemote = getChallengeRemote();
                    currentStats = chalRemote.get(uid, challItem.getId());                    
                    
                    // Maximum number of retries reached?
                    if (course.getChallengeRetries() != 0
                            && (currentStats != null)
                            && (currentStats.getRetries() >= course.getChallengeRetries())) {                        
                        response.getWriter().println(getAnswers(challItem, currentStats.getChallvalue()));
                        return;
                    }
                }
                                
                List<Field> answers = getRightAnswers(challItem);
                                
                String option = challid;
                List<String> rightFields = new ArrayList<String>(answers.size());
                List<String> wrongFields = new ArrayList<String>(answers.size());
                for (int i = 0; i < answers.size(); i++) {
                    option = answers.get(i).getName();
                    reqParam = request.getParameter(option);
                    dbAnswer = answers.get(i).getValue();                    

                    if (dbAnswer.equalsIgnoreCase(reqParam) && !reqParam.equals("")) {
                        points++;
                        rightFields.add(option);
                    } else {
                        wrongFields.add(option);
                    }

                }
                
                result = ((double) (points * 100)) / (double) answers.size();
                
                status = "err";
                if (result >= 70) {
                    status = "ok";
                }
                                                
                // Should store Scores in the database
                if (scorable) {                                       
                    HistoryRemote historyRemote = getHistoryRemote();
                    List<Transcript> transcripts = historyRemote.getTranscriptsByStudentByGrade(uid, gradeId);
                    if (transcripts.size() > 0) {
                        Transcript transcript = transcripts.get(0);
                        double average = transcript.getAverageChallenge();
                        double total = transcript.getTotalChallenge();
                        double challengesDone = (double) transcript.getChallengesDone();
                        double totalChallenges = (double) course.getChallengeCount();
                        if (currentStats != null) {
                            average = (average * challengesDone) - currentStats.getChallvalue();
                            total = (total * challengesDone) - currentStats.getChallvalue();
                            challengesDone--;
                        } else {
                            average = (average * challengesDone);
                            total = (total * challengesDone);
                        }
                        
                        challengesDone++;
                        
                        if (challengesDone > 0) 
                            average = (average + result) / challengesDone;
                        else 
                            average = 0;

                        if (totalChallenges > 0)
                            total = (total + result) / totalChallenges;
                        else 
                            totalChallenges = 0;
                        
                        
                                                
                        transcript.setAverageChallenge(average);
                        transcript.setTotalChallenge(total);
                        transcript.setChallengesDone((int) challengesDone);
                        
                        historyRemote.updateTranscript(transcript);
                    }
                }
                
                // Update Later, in case of fail here, the student has their score at least
                challRemote.add(challid, uid, result, challItem.getId());
                
                String json = buildJsonAnswer(rightFields, wrongFields, null, result, challid, status);
                response.getWriter().println(json);

            } else if (challid.equals("audio")) {
                //challRemote.add(request.getParameter("value"), uid, 100);
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, request.getParameter("value").trim());

            }        
        } catch (Exception e) {
            challRemote.add(challid, uid, result);
            response.getWriter().println("{\"list\":{\"results\":[{\"ret\":\"" + result + "\"}]},\"name\":\"" + challid + "\", \"status\": \"" + status + "\"}");            
            log.error("Exception in Challenge solver: " + uid + '|' + challid, e);
        }
    }

    private ChallengeItems getChallengeItems(String challid, Long unitId) {
        ChallengeItemsRemote challItemsRemote = this.getChallengeItemsRemote();
        return challItemsRemote.getByUnit(challid, unitId);        
    }

    private List<Field> getRightAnswers(ChallengeItems challItems) {        
        XMLObject xo = new XMLObject();
        xo.setIntType(Constants.CHALLENGE_TYPE);
        xo.setEntireXml(challItems.getXml());
        IvelaObj ivelaObj = Converter2ObjectFactory.getConverter(xo).getConverterType();
        return ivelaObj.getIvelaPayload().getChallenge().getField();
    }

    
    private String stripPontuation(String source) {
        source = source.replaceAll("!", "");
        source = source.replaceAll("\\?", "");
        source = source.replaceAll(":", "");
        source = source.replaceAll(".", "");
        source = source.replaceAll(",", "");
        source = source.replaceAll("  ", " ");
        return source;
    }

    private ChallengeRemote getChallengeRemote() {
        ChallengeRemote challengeRemote = null;

        try {
            InitialContext initialContext = new InitialContext();
            java.lang.Object ejbRemoteRef = initialContext.lookup("ChallengeBean#br.ufc.ivela.ejb.interfaces.ChallengeRemote");
            challengeRemote = (ChallengeRemote) javax.rmi.PortableRemoteObject.narrow(ejbRemoteRef, ChallengeRemote.class);
        } catch (NamingException e) {
            log.error("Error Retrieving Challenge Remote", e);
        }

        return challengeRemote;
    }

    private ChallengeItemsRemote getChallengeItemsRemote() {
        ChallengeItemsRemote challengeItemsRemote = null;

        try {
            InitialContext initialContext = new InitialContext();
            java.lang.Object ejbRemoteRef = initialContext.lookup("ChallengeItemsBean#br.ufc.ivela.ejb.interfaces.ChallengeItemsRemote");
            challengeItemsRemote = (ChallengeItemsRemote) javax.rmi.PortableRemoteObject.narrow(ejbRemoteRef, ChallengeItemsRemote.class);
        } catch (NamingException e) {
            log.error("Error Retrieving Challenge Items Remote", e);
        }

        return challengeItemsRemote;
    }
    
    private CourseRemote getCourseRemote() {
        CourseRemote courseRemote = null;

        try {
            InitialContext initialContext = new InitialContext();
            java.lang.Object ejbRemoteRef = initialContext.lookup("CourseBean#br.ufc.ivela.ejb.interfaces.CourseRemote");
            courseRemote = (CourseRemote) javax.rmi.PortableRemoteObject.narrow(ejbRemoteRef, CourseRemote.class);
        } catch (NamingException e) {
            log.error("Error Retrieving Course Remote", e);            
        }

        return courseRemote;
    }

    private HistoryRemote getHistoryRemote() {
        HistoryRemote historyRemote = null;

        try {
            InitialContext initialContext = new InitialContext();
            java.lang.Object ejbRemoteRef = initialContext.lookup("HistoryBean#br.ufc.ivela.ejb.interfaces.HistoryRemote");
            historyRemote = (HistoryRemote) javax.rmi.PortableRemoteObject.narrow(ejbRemoteRef, HistoryRemote.class);
        } catch (NamingException e) {
            log.error("Error Retrieving History Remote", e);            
        }

        return historyRemote;
    }
    
    private String buildJsonAnswer(List<String> rightFields , List<String> wrongFields , List<String> answers, double results, String challengeId, String status) {
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
        builder.append("\"}");
        
        return builder.toString();
    }
    
    private String getAnswers(ChallengeItems challItem, double results) {
        List<Field> answers = getRightAnswers(challItem);
        
        List<String>rightFields = new ArrayList<String>(answers.size());
        List<String>answerFields = new ArrayList<String>(answers.size());
        
        for (int i = 0; i < answers.size(); i++) {
            rightFields.add(answers.get(i).getName());
            answerFields.add(answers.get(i).getValue());

        }        
        
        return buildJsonAnswer(rightFields, null, answerFields, results, challItem.getName(), "fin");
    }
}
