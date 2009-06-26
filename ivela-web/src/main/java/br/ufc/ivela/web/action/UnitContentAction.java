/*    
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
# File: UnitContentAction.java                                                              #
# Document: Unit Content Action                                                             # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# ??-???-2008 - Emanuelle                         - XXXXXX - Initial Version                #
# 22-JUN-2009 - otofuji (Instituto Eldorado)      - 000010 - General Fixes                  #
#############################################################################################
*/
package br.ufc.ivela.web.action;

import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.Discipline;
import br.ufc.ivela.commons.model.Exam;
import br.ufc.ivela.commons.model.Exercise;
import br.ufc.ivela.commons.model.FinishedUnitContent;
import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.commons.model.Unit;
import br.ufc.ivela.commons.model.UnitContent;
import br.ufc.ivela.ejb.interfaces.CourseRemote;
import br.ufc.ivela.ejb.interfaces.DisciplineRemote;
import br.ufc.ivela.ejb.interfaces.FinishedUnitContentRemote;
import br.ufc.ivela.ejb.interfaces.GradeRemote;
import br.ufc.ivela.ejb.interfaces.SystemUserRemote;
import br.ufc.ivela.ejb.interfaces.UnitContentRemote;
import br.ufc.ivela.ejb.interfaces.UnitRemote;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UnitContentAction extends GenericAction {

    private DisciplineRemote disciplineRemote;
    private CourseRemote courseRemote;
    private FinishedUnitContentRemote finishedUnitContentRemote;
    private SystemUserRemote systemUserRemote;
    private UnitContentRemote unitContentRemote;
    private GradeRemote gradeRemote;
    private UnitRemote unitRemote;
    private Unit unit;
    private Grade grade;
    private Discipline discipline;
    private UnitContent unitContent;
    private List<UnitContent> unitContentList;
    private List<List> list;
    private List<Unit> unitList;
    private List<Exam> examList;
    private List<Exercise> exerciseList;
    private InputStream inputStream;

    public CourseRemote getCourseRemote() {
        return courseRemote;
    }

    public void setCourseRemote(CourseRemote courseRemote) {
        this.courseRemote = courseRemote;
    }

    public DisciplineRemote getDisciplineRemote() {
        return disciplineRemote;
    }

    public void setDisciplineRemote(DisciplineRemote disciplineRemote) {
        this.disciplineRemote = disciplineRemote;
    }

    public FinishedUnitContentRemote getFinishedUnitContentRemote() {
        return finishedUnitContentRemote;
    }

    public void setFinishedUnitContentRemote(FinishedUnitContentRemote finishedUnitContentRemote) {
        this.finishedUnitContentRemote = finishedUnitContentRemote;
    }

    public SystemUserRemote getSystemUserRemote() {
        return systemUserRemote;
    }

    public void setSystemUserRemote(SystemUserRemote systemUserRemote) {
        this.systemUserRemote = systemUserRemote;
    }

    /**
     * List all the unit by grade, and discipline
     * @return a list 
     */
    public String listByUnit() {
        setGrade(gradeRemote.get(grade.getId()));
        setUnit(unitRemote.get(unit.getId()));
        setUnitList(unitRemote.getByDiscipline(discipline.getId()));
        list = new ArrayList<List>();
        for (Unit u : unitList) {
            unitContentList = unitContentRemote.getByUnitOrdered(u.getId());
            list.add(unitContentList);
        }
        setUnitContentList(unitContentRemote.getByUnitOrdered(unit.getId()));
        return "list";
    }


    /**
     * Retrieves a unitContent of one json structure
     * @return
     */
    public String getUnitContentJson() {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("unitContent", UnitContent.class);
        xStream.alias("unit", Unit.class);
        xStream.alias("discipline", Discipline.class);
        unitContent = unitContentRemote.get(unitContent.getId());
        setInputStream(new ByteArrayInputStream(xStream.toXML(unitContent).getBytes()));
        addHistory("history.viewunitcontent.title", "history.viewunitcontent.description", unitContent.getTitle());
        List<FinishedUnitContent> list = finishedUnitContentRemote.getByUnitContentAndSystemUser(unitContent.getId(), getAuthenticatedUser().getId());
        if (list.size() == 0) {
            FinishedUnitContent finishedUnitContent = new FinishedUnitContent();
            finishedUnitContent.setSystemUser(getAuthenticatedUser().getId());
            Unit u = unitRemote.get(unitContent.getUnitId());
            Discipline d = disciplineRemote.get(u.getDisciplineId());
            Course c = courseRemote.get(d.getCourseId());
            finishedUnitContent.setCourse(c.getId());
            finishedUnitContent.setUnitContent(unitContent.getId());
            finishedUnitContentRemote.add(finishedUnitContent);
        }
        SystemUser user = systemUserRemote.get(getAuthenticatedUser().getId());
        user.setLastUnitContentId(unitContent.getId());
        systemUserRemote.update(user);
        return "json";
    }

    /**
     * Retrieves a unit
     * @return a  unit
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * Sets a unit
     * @param unit
     */
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    /**
     * Retrieves a list of unit
     * @return unitList
     */
    public List<Unit> getUnitList() {
        return unitList;
    }

    /**
     * Sets a list of units
     * @param unitList
     */
    public void setUnitList(List<Unit> unitList) {
        this.unitList = unitList;
    }

    /**
     * Retrieves a discipline
     * @return discipline
     */
    public Discipline getDiscipline() {
        return discipline;
    }

    /**
     * Sets a discipline
     * @param discipline
     */
    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    /**
     * Retrieves a list of unit content
     * @return unitContentList
     */
    public List<UnitContent> getUnitContentList() {
        return unitContentList;
    }

    /**
     * Sets a list of unit content
     * @param unitContentList
     */
    public void setUnitContentList(List<UnitContent> unitContentList) {
        this.unitContentList = unitContentList;
    }

    /**
     * Retrieves the list of exam
     * @return examList
     */
    public List<Exam> getExamList() {
        return examList;
    }

    /**
     * Sets a list of exam
     * @param examList
     */
    public void setExamList(List<Exam> examList) {
        this.examList = examList;
    }

    /**
     * Retrieves a list
     * @return a list
     */
    public List<List> getList() {
        return list;
    }

    /**
     * Sets a list
     * @param list
     */
    public void setList(List<List> list) {
        this.list = list;
    }

    /**
     * Retrieves a unit content
     * @return unitContent
     */
    public UnitContent getUnitContent() {
        return unitContent;
    }

    /**
     * Sets a unit content
     * @param unitContent
     */
    public void setUnitContent(UnitContent unitContent) {
        this.unitContent = unitContent;
    }

    /**
     * Retrieves a remote unit content
     * @return unitContentRemote
     */
    public UnitContentRemote getUnitContentRemote() {
        return unitContentRemote;
    }

    /**
     * Sets a remote unit content
     * @param unitContentRemote
     */
    public void setUnitContentRemote(UnitContentRemote unitContentRemote) {
        this.unitContentRemote = unitContentRemote;
    }

    /**
     * Sets a grade
     * @return grade
     */
    public Grade getGrade() {
        return grade;
    }

    /**
     * Sets a grade
     * @param grade
     */
    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    /**
     * Retrieves a remote grade
     * @return gradeRemote
     */
    public GradeRemote getGradeRemote() {
        return gradeRemote;
    }

    /**
     * Sets a remote grade
     * @param gradeRemote
     */
    public void setGradeRemote(GradeRemote gradeRemote) {
        this.gradeRemote = gradeRemote;
    }

    /**
     * Retrieves a remote unit
     * @return
     */
    public UnitRemote getUnitRemote() {
        return unitRemote;
    }

    /**
     * Sets a remote unit
     * @param unitRemote
     */
    public void setUnitRemote(UnitRemote unitRemote) {
        this.unitRemote = unitRemote;
    }

    /**
     * Retrieves a list of exercise
     * @return exerciseList
     */
    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    /**
     * Sets a list of exercise
     * @param exerciseList
     */
    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    /**
     * Retrieves a input stream
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
