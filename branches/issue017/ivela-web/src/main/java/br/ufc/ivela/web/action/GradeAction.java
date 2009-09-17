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
# File: GradeAction.java                                                                    #
# Document: Action for the Grades                                                           # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# ??-???-2008 - nelson                            - XXXXXX - Initial Version                #
# 24-JUN-2009 - otofuji (Instituto Eldorado)      - 000010 - General Fixes                  #
#############################################################################################
*/

package br.ufc.ivela.web.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.ejb.interfaces.RepositoryRemote;
import br.ufc.ivela.ejb.interfaces.SystemUserRemote;

import com.opensymphony.xwork2.Preparable;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

public class GradeAction extends CourseAwareAction implements Preparable {
        
    private RepositoryRemote repositoryRemote;
    private SystemUserRemote systemUserRemote;
    private List<SystemUser> systemUserList;
    private InputStream inputStream;

    /**
     * Get a list of the grades
     * @return list
     */
    public String list() {
        gradeList = gradeRemote.getAll();

        return "list";
    }

    /**
     * Retrieves a grade in format of json
     * @return a json
     */
    public String getGradeJson() {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("grade", Grade.class);
        xStream.alias("course", Course.class);
        grade = gradeRemote.get(grade.getId());
        setInputStream(new ByteArrayInputStream(xStream.toXML(grade).getBytes()));
        return "json";
    }

    /**
     * Retrieves a list of system user
     * @return systemUserList
     */
    public List<SystemUser> getSystemUserList() {
        return systemUserList;
    }

    /**
     * Sets a list of system user
     * @param systemUserList
     */
    public void setSystemUserList(List<SystemUser> systemUserList) {
        this.systemUserList = systemUserList;
    }

    /**
     * Retrieves a remote system user
     * @return systemUserRemote
     */
    public SystemUserRemote getSystemUserRemote() {
        return systemUserRemote;
    }

    /**
     * Sets a remote system user
     * @param systemUserRemote
     */
    public void setSystemUserRemote(SystemUserRemote systemUserRemote) {
        this.systemUserRemote = systemUserRemote;
    }

    /**
     * Sets a course list and a system user list
     * @throws java.lang.Exception
     */
    public void prepare() throws Exception {
        courseList = courseRemote.getAll();
        systemUserList = systemUserRemote.getAll();
    }

    /**
     * Sets a remote repository
     * @param repositoryRemote
     */
    public void setRepositoryRemote(RepositoryRemote repositoryRemote) {
        this.repositoryRemote = repositoryRemote;
    }

    /**
     * Retrieves the remote repository
     * @return repositoryRemote
     */
    public RepositoryRemote getRepositoryRemote() {
        return repositoryRemote;
    }

    /**
     * Retrieves the input stream
     * @return inputStream
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * Sets a input stream
     * @param inputStream
     */
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}