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
# File: CourseAction.java                                                                   #
# Document: Course User Action                                                              # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 07-JAN-2009 - Leonardo Oliveira (UFC)           - XXXXXX - Initial Version                #
# 16-SEP-2009 - Otofuji (Instituto Eldorado)      - 000016 - General Fixes                  #
# 08-OCT-2009 - Fabio Fantato (Instituto Eldorado)- 000017 - Table of Contents              #
#############################################################################################
*/
package br.ufc.ivela.web.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import br.ufc.ivela.commons.dao.Page;
import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.Discipline;
import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.ejb.interfaces.DisciplineRemote;
import br.ufc.ivela.ejb.interfaces.EnrollmentRemote;
import br.ufc.ivela.ejb.interfaces.GradeRemote;
import br.ufc.ivela.util.PropertiesUtil;
import br.ufc.ivela.util.PropertiesUtil.IVELA_PROPERTIES;

public class CourseAction extends CourseAwareAction {
        
    private DisciplineRemote disciplineRemote;    
    private Discipline discipline;
    private int pageCount;
    private int page;
    private int pageSize = 5;
    private int count;
    private InputStream inputStream;
    private String nick;
    private String chatRoomName;
    private String teacherName; 
    private String ircServer;
    private String chatColor;
    private String blackGet;
    private String blackSave;
    private long courseId;
    private long disciplineId;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
    
    /**
     * List all course per page
     */
    public String list() {
        if (page < 1) {
            page = 1;
        }
        Page p = courseRemote.getAllPage(page, pageSize);
        courseList = p.getList();
        setCount(p.getCount());
        setPageCount(p.getPageCount());
        return "list";
    }

    /**
     * Retrieves the count
     * @return
     */
    public int getCount() {
        return count;
    }

    /**
     * Set a count
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Retrieves the Page
     * @return page
     */
    public int getPage() {
        return page;
    }

    /**
     * Sets a page
     * @param page
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * Retrieves the Page count
     * @return pageCount
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * Sets a Page count
     * @param pageCount
     */
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }    

    /**
     * Retrieves the Page size
     * @return
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets a page size
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String showChatStd() {
        course = courseRemote.get(courseId);
        this.nick = this.getAuthenticatedUser().getUsername();
        this.grade = gradeRemote.getActiveByStudentByCourse(this.getAuthenticatedUser().getId(), course.getId());
        this.chatRoomName = "#room_" + course.getId() + "_" + grade.getId();
        this.chatRoomName = this.chatRoomName.replace(' ', '_');
        PropertiesUtil putil = PropertiesUtil.getPropertiesUtil();
        this.ircServer = putil.getProperty(IVELA_PROPERTIES.IRC_SERVER);
        this.chatColor = putil.getProperty(IVELA_PROPERTIES.CHAT_COLOR);
        this.blackGet = putil.getProperty(IVELA_PROPERTIES.BLACKBOARD_SERVER_GET);
        this.blackSave = putil.getProperty(IVELA_PROPERTIES.BLACKBOARD_SERVER_SAVE);
        Set<SystemUser> list = (Set<SystemUser>)grade.getProfessors();
        Iterator i = list.iterator();
        if(i.hasNext())
            this.teacherName =  ((SystemUser)i.next()).getUsername();
        else 
            this.teacherName = "admin";
        return "showChatStd";
    }

    /**
     * Gathering information to verify if there are a custom Toc for this course.
     * @return json: redirect -> true/false , params -> course.id and grade.id
     */
    public String getCustomTocJson() {
        course = courseRemote.get(course.getId());
        Long user = this.getAuthenticatedUser().getId();
        List<Grade> gradeList = gradeRemote.getGradesInProgressAndEnrolled(user, course.getId());
        //Get only the first element of grade. There are no multiple grades in progress for a given course.
        Long gradeEnrolled = ((!gradeList.isEmpty())?gradeList.get(0).getId():0);        
        Boolean customToc = course.getCustomToc();
        String customTocParams = "course.id="+course.getId()+"&grade.id="+gradeEnrolled+"&goToIndex=yes&goToPage=index.html";
        StringBuilder json = new StringBuilder();
        json.append("{");
            json.append("\"customToc\":{");
                json.append("\"redirect\":\"" + customToc + "\",");
                json.append("\"params\":\"" + customTocParams + "\"");                
            json.append("}");
        json.append("}");
        setInputStream(new ByteArrayInputStream(json.toString().getBytes()));
        return "json";
    }
    
    

    public String getChatRoomName() {
        return chatRoomName;
    }

    public void setChatRoomName(String chatRoomName) {
        this.chatRoomName = chatRoomName;
    }

    public String getIrcServer() {
        return ircServer;
    }
    
    public String getChatColor() {
        return chatColor;
    }
    
    public String getBlackboardServerGet() {
        return blackGet;        
    }
    
    public String getBlackboardServerSave() {
        return blackSave;
    }
    
    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public void setCourseId(Long id) {
        this.courseId = id;
    }
    
    public void setDisciplineId(Long id) {
        this.disciplineId = id;
    }
    
    public DisciplineRemote getDisciplineRemote() {
        return disciplineRemote;
    }

    public void setDisciplineRemote(DisciplineRemote disciplineRemote) {
        this.disciplineRemote = disciplineRemote;
    }
    
}