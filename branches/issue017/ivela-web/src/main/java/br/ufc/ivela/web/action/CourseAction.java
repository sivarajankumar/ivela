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
#############################################################################################
*/
package br.ufc.ivela.web.action;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Set;

import br.ufc.ivela.commons.dao.Page;
import br.ufc.ivela.commons.model.Discipline;
import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.ejb.interfaces.DisciplineRemote;
import br.ufc.ivela.ejb.interfaces.GradeRemote;
import br.ufc.ivela.ejb.interfaces.ProfessorRemote;

/**
 *
 * @author Maristella Myrian
 */
public class CourseAction extends CourseAwareAction {
    
    private ProfessorRemote professorRemote;
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
    
    public String showChatStd(){        
        course = courseRemote.get(courseId);
        discipline = disciplineRemote.get(disciplineId);
        this.nick = this.getAuthenticatedUser().getUsername();
        //this.chatRoomName = "#course_"+this.course.getId();
        this.chatRoomName = "#course_"+course.getId()+"_"+discipline.getId()+"_"+discipline.getName(); 
        this.grade = gradeRemote.getActiveByStudentByCourse(this.getAuthenticatedUser().getId(), course.getId());
        Set<SystemUser> list = (Set<SystemUser>)grade.getProfessors();
        Iterator i = list.iterator();
        if(i.hasNext())
            this.teacherName =  ((SystemUser)i.next()).getUsername();
        else 
            this.teacherName = "admin";
        
        return "showChatStd";
    }

    public String getChatRoomName() {
        return chatRoomName;
    }

    public void setChatRoomName(String chatRoomName) {
        this.chatRoomName = chatRoomName;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public GradeRemote getGradeRemote() {
        return gradeRemote;
    }

    public void setGradeRemote(GradeRemote gradeRemote) {
        this.gradeRemote = gradeRemote;
    }

    public ProfessorRemote getProfessorRemote() {
        return professorRemote;
    }

    public void setProfessorRemote(ProfessorRemote professorRemote) {
        this.professorRemote = professorRemote;
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