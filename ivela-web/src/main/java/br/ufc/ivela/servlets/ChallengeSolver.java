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
import br.ufc.ivela.commons.challenger.util.XMLObject;
import br.ufc.ivela.commons.challenger.config.Constants;
import br.ufc.ivela.commons.challenger.util.Converter2ObjectFactory;
import br.ufc.ivela.commons.challenger.util.Field;
import br.ufc.ivela.commons.challenger.dataobject.IvelaObj;
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
        response.setContentType("application/json");
        ChallengeRemote challRemote = this.getChallengeRemote();
        String challid = request.getParameter("challid");    
        Long unitId = Long.decode(request.getParameter("unitId"));
        Long gradeId = Long.decode(request.getParameter("gradeId"));
        String retrieveAnswers = request.getParameter("answers");
        String dependency = request.getParameter("dependency");
        Boolean checkDependency = Boolean.FALSE;
        if (dependency != null) {
            checkDependency = Boolean.parseBoolean(dependency);
        }
        
        Long uid = 0L;
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if (obj != null && obj instanceof SystemUser) {
            uid = ((SystemUser) obj).getId();
        }
        
        try {
            if (retrieveAnswers != null && retrieveAnswers.equals("t")) {
                String json = getChallengeRemote().retrieveChallengeAnswers(challid, uid, unitId, gradeId);
                response.getWriter().println(json);
            } else if (!challid.equals("audio")) {                
                String json = getChallengeRemote().executeChallenge(challid, uid, unitId, gradeId, request.getParameterMap(), checkDependency);
                response.getWriter().println(json);                
            } else if (challid.equals("audio")) {
                challRemote.add(request.getParameter("value"), uid, 100);
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, request.getParameter("value").trim());
            }        
        } catch (Exception e) {            
            response.getWriter().println("{\"list\":{\"results\":[{\"ret\":\"\"}]},\"name\":\"" + challid + "\", \"status\": \"err\"}");            
            log.error("Exception in Challenge solver: " + uid + '|' + challid, e);
        }
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
    
}
