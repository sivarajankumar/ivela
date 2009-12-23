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
# File: HistoryService.java                                                                        #
# Document: Web Service for History                                                                #
# Date        - Author(Company)                   - Issue# - Summary                               #
# 16-DEC-2009 - Otofuji (Eldorado)                - 000021 - Create Initial Ivela Services         #
##################################################################################################*/
package br.ufc.ivela.ejb.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.ejb.interfaces.CourseRemote;
import br.ufc.ivela.ejb.interfaces.GradeRemote;
import br.ufc.ivela.ejb.interfaces.HistoryRemote;
import br.ufc.ivela.ejb.ws.objects.TranscriptResource;

import com.thoughtworks.xstream.XStream;

/**
 * Bean class for Ivela User History Services (Transcripts, History, etc..)
 */
@Stateless
@WebService(name = "History", serviceName = "IvelaServices")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
@TransactionAttribute(TransactionAttributeType.NEVER)
public class HistoryService extends AbstractService {

    private static Log logger = LogFactory.getLog(HistoryService.class);
    
    @EJB
    private HistoryRemote historyRemote;

    @EJB
    private CourseRemote courseRemote;
    
    @EJB
    private GradeRemote gradeRemote;
    
    private XStream xStream = new XStream();
    
    @WebMethod
    public String getAllReports(@WebParam(name = "studentName") String studentName,
            @WebParam(name = "studentPassword") String studentPassword) {
        
        SystemUser user = getSystemUser(studentName, studentPassword);
        
        if (user == null) {
            return "Error: No Student found with the studentName/password combination " + studentName;   
        }
        
        List<TranscriptResource> transcripts = new ArrayList<TranscriptResource>();
        
        try {
            List<Grade> listGrades = gradeRemote.getGradesActiveByStudent(user.getId());
            for (Grade grade : listGrades) {
                TranscriptResource transResource = new TranscriptResource(historyRemote.calcAverageCourse(grade.getId(), user
                        .getId()));
                transResource.setSystemUserName(user.getUsername());
                transResource.setGradeName(grade.getName());
                transcripts.add(transResource);
            }
        } catch (Exception e) {
            logger.error("IvelaServices:History:Error: Could not retrieve transcripts", e);
            return "Error: Could not retrieve reports";
        }
        
        xStream.alias("Report", TranscriptResource.class);
        return xStream.toXML(transcripts);        
    }
    
    @WebMethod
    public String getReport(@WebParam(name ="grade") Long gradeId,
            @WebParam(name = "studentName") String studentName, 
            @WebParam(name = "studentPassword") String studentPassword) {
        
        SystemUser user = getSystemUser(studentName, studentPassword);
        
        if (user == null) {
            return "Error: No Student found with the studentName/password combination " + studentName;
        }        
                
        TranscriptResource transResource = new TranscriptResource(historyRemote.calcAverageCourse(gradeId, user
                .getId()));
        transResource.setSystemUserName(user.getUsername());        

        xStream.alias("Report", TranscriptResource.class);
        return xStream.toXML(transResource);
    }
    
    @WebMethod
    public int getProgress(@WebParam(name = "courseId") Long courseId,
            @WebParam(name = "studentName") String studentName, 
            @WebParam(name = "studentPassword") String studentPassword) {
        
        SystemUser user = getSystemUser(studentName, studentPassword);
        Integer progress = 0;
        if (user != null) {
            progress = courseRemote.getProgress(user.getId(), courseId);
            if (!new Integer(0).equals(progress)) {
                progress = progress/10;
                }
        } else {
            return -1;
        }
         
        return progress;
    }
}

