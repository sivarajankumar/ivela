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
# File: CourseBean.java                                                                     #
# Document: Course Stateless Bean                                                           # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 07-JAN-2009 - Maristella Myrian (UFC)           - XXXXXX - Initial Version                #
# 16-SEP-2009 - Otofuji (Instituto Eldorado)      - 000016 - General Fixes                  #
#############################################################################################
*/
package br.ufc.ivela.ejb.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.dao.Page;
import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.Discipline;
import br.ufc.ivela.commons.model.Exam;
import br.ufc.ivela.commons.model.Exercise;
import br.ufc.ivela.commons.model.Grade;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.commons.model.Unit;
import br.ufc.ivela.commons.model.UnitContent;
import br.ufc.ivela.commons.util.Thumbnail2;
import br.ufc.ivela.ejb.interfaces.CourseRemote;


@Stateless(mappedName="CourseBean")
public class CourseBean implements CourseRemote {

    private GenericDao<Course> daoCourse = DaoFactory.getInstance(Course.class);
    private GenericDao<Grade> daoGrade = DaoFactory.getInstance(Grade.class);
    private GenericDao<Discipline> daoDisc = DaoFactory.getInstance(Discipline.class);
    private GenericDao<SystemUser> daoSu = DaoFactory.getInstance(SystemUser.class);
    private GenericDao<Unit> daoUnit = DaoFactory.getInstance(Unit.class);
    private GenericDao<UnitContent> daoUContent = DaoFactory.getInstance(UnitContent.class);
    private GenericDao<UnitContent> daoExercise = DaoFactory.getInstance(Exercise.class);
    private GenericDao<UnitContent> daoExam = DaoFactory.getInstance(Exam.class);
     
    
    /**
     * Connect to the server and add a new course
     * @param course
     * @return true if the new course can be add, and false if can't
     */
    public Long add(Course course) {
        return (Long) daoCourse.save(course);
    }

    /**
     * Connect to the server and get the course by id
     * @param id
     * @return course
     */
    public Course get(Long id) {
        if (id == null) {
            return null;
        }
        return daoCourse.get(id);
    }

    /**
     * Connect to the server and remove a course by id
     * @param id
     * @return true if the new course can be remove, and false if cannot
     */
    public boolean remove(Long id) {
        return daoCourse.remove(id);
    }

    /**
     * try to connect to the server and get all course and lists
     *
     * @return a list of course
     */
    public List<Course> getAll() {
        return (List<Course>) daoCourse.find("from Course c WHERE c.active = true", new Object[] {});
        //return daoCourse.getAll();
    }

    /**
     * try to connect to the server and update a course
     * @param course
     * @return
     */
    public boolean update(Course course) {
        return daoCourse.update(course);

    }

    public List<Course> getRequisiteByCourse(Long courseId) {
        Object[] params = new Object[]{courseId};

        return (List<Course>) daoCourse.find("select cr.requisite from CourseRequisite cr WHERE cr.course.id = ?", params);
    }

    public List<Course> getCourseByUserApproved(Long userId) {

        Object[] params = new Object[]{userId};

        return (List<Course>) daoCourse.find("select t.grade.course from Transcript t where t.systemUser.id = ? and t.status = 1) ", params);
    }

    public Page getAllPage(int page, int pageSize) {
        if (page == 0) {
            page = 1;
        }
        Object[] params = null;
        String countQuery = "select count(id) from Course where active = true";
        String query = "from Course where active = true";

        Page p = new Page(query, countQuery, params, params, page, pageSize);

        return p;
    }

    public String getJsonStructure() {
        //var json = {"id":"1",  "name":"Computacao",  "children":[
        //	{"id":"2", "name":"Teste", "children":[
        //          {"id":"3", "name":"xxx", "children":[]}
        //      ]},
        //	{"id":"4", "name":"Giggity", "children":[]} ]
        // };

        List<Course> list = getStructure();
        StringBuffer jsonBuffer = new StringBuffer();
        String json = null;

        jsonBuffer.append("{\"id\":\"0\", \"name\":\"Cursos\", \"children\":[ ");

        if (!list.isEmpty()) {

            for (int i = 0; i < list.size(); i++) {
                Course course = list.get(i);
                jsonBuffer.append("{\"id\":\"c" + course.getId() + "\", \"name\":\"" + course.getName() + "\", \"children\":[");

                if (course.getDisciplines() != null) {
                    for (int j = 0; j < course.getDisciplines().size(); j++) {
                        Discipline discipline = (Discipline) ((List) course.getDisciplines()).get(j);
                        jsonBuffer.append("{\"id\":\"d" + discipline.getId() + "\", \"name\":\"" + discipline.getName() + "\", \"children\":[");

                        if (discipline.getUnits() != null) {
                            for (int k = 0; k < discipline.getUnits().size(); k++) {
                                Unit unit = (Unit) ((List) discipline.getUnits()).get(k);
                                jsonBuffer.append("{\"id\":\"u" + unit.getId() + "\", \"name\":\"" + unit.getName() + "\", \"children\":[");

                                if (unit.getUnitContents() != null) {
                                    for (int l = 0; l < unit.getUnitContents().size(); l++) {
                                        UnitContent unitContent = (UnitContent) ((List) unit.getUnitContents()).get(l);
                                        jsonBuffer.append("{\"id\":\"a" + unitContent.getId() + "\", \"name\":\"" + unitContent.getTitle() + "\", \"children\":[] }");

                                        if (l != unit.getUnitContents().size() - 1) {
                                            jsonBuffer.append(",");
                                        }
                                    }
                                }

                                jsonBuffer.append("] }");
                                if (k != discipline.getUnits().size() - 1) {
                                    jsonBuffer.append(",");
                                }
                            }
                        }

                        jsonBuffer.append("] }");
                        if (j != course.getDisciplines().size() - 1) {
                            jsonBuffer.append(",");
                        }
                    }
                }

                jsonBuffer.append("] }");
                if (i != list.size() - 1) {
                    jsonBuffer.append(",");
                }
            }
        }

        jsonBuffer.append("] }");

        json = jsonBuffer.toString();

        return json;
    }

    public String getCourseJsonStructure(Long courseId) {
        //var json = {"id":"1",  "name":"Computacao",  "children":[
        //	{"id":"2", "name":"Teste", "children":[
        //          {"id":"3", "name":"xxx", "children":[]}
        //      ]},
        //	{"id":"4", "name":"Giggity", "children":[]} ]
        // };

        Course course = getCourseStructure(courseId);
        StringBuffer jsonBuffer = new StringBuffer();
        String json = null;

        if (course != null) {
            jsonBuffer.append("{\"id\":\"c" + course.getId() + "\", \"name\":\"" + course.getName() + "\", \"children\":[");

            if (course.getDisciplines() != null) {
                for (int j = 0; j < course.getDisciplines().size(); j++) {
                    Discipline discipline = (Discipline) ((List) course.getDisciplines()).get(j);
                    jsonBuffer.append("{\"id\":\"d" + discipline.getId() + "\", \"name\":\"" + discipline.getName() + "\", \"children\":[");

                    if (discipline.getUnits() != null) {
                        for (int k = 0; k < discipline.getUnits().size(); k++) {
                            Unit unit = (Unit) ((List) discipline.getUnits()).get(k);
                            jsonBuffer.append("{\"id\":\"u" + unit.getId() + "\", \"name\":\"" + unit.getName() + "\", \"children\":[");

                            if (unit.getUnitContents() != null) {
                                for (int l = 0; l < unit.getUnitContents().size(); l++) {
                                    UnitContent unitContent = (UnitContent) ((List) unit.getUnitContents()).get(l);
                                    jsonBuffer.append("{\"id\":\"a" + unitContent.getId() + "\", \"name\":\"" + unitContent.getTitle() + "\", \"children\":[] }");

                                    if (l != unit.getUnitContents().size() - 1) {
                                        jsonBuffer.append(",");
                                    }
                                }
                            }

                            jsonBuffer.append("] }");
                            if (k != discipline.getUnits().size() - 1) {
                                jsonBuffer.append(",");
                            }
                        }
                    }

                    jsonBuffer.append("] }");
                    if (j != course.getDisciplines().size() - 1) {
                        jsonBuffer.append(",");
                    }
                }
            }

            jsonBuffer.append("] }");
        }

        json = jsonBuffer.toString();
        return json;
    }

    public List<Course> getStructure() {
        List<Course> list = getAll();

        for (Course course : list) {
            course.setDisciplines(daoDisc.getByFK("courseId", course.getId()));

            if (course.getDisciplines() != null) {
                for (Discipline discipline : course.getDisciplines()) {
                    discipline.setUnits(daoUnit.getByFK("disciplineId", discipline.getId()));

                    if (discipline.getUnits() != null) {
                        for (Unit unit : discipline.getUnits()) {
                            unit.setUnitContents(daoUContent.getByFK("unitId", unit.getId()));

                            if (unit.getUnitContents() != null) {
                                for (UnitContent unitContent : unit.getUnitContents()) {
                                    //unitContent.setExercises(daoExercise.getByFK("unitContentId", unitContent.getId()));
                                    unitContent.setExercises(daoExercise.find("from Exercise e where e.unitContentId = ? and e.active = ? order by e.order", new Object[]{unitContent.getId(), true}));
                                    //unitContent.setExams(daoExam.getByFK("unitContentId", unitContent.getId()));
                                    unitContent.setExams(daoExam.find("from Exam e where e.unitContentId = ? and e.active = ? order by e.order", new Object[]{unitContent.getId(), true}));
                                }
                            }
                        }
                    }
                }
            }
        }

        return list;
    }

    public Course getCourseStructure(Long courseId) {

        if(courseId == null) {
            return null;
        }
        
        Course course = get(courseId);

        course.setDisciplines(daoDisc.getByFK("courseId", course.getId()));

        if (course.getDisciplines() != null) {
            for (Discipline discipline : course.getDisciplines()) {
                discipline.setUnits(daoUnit.getByFK("disciplineId", discipline.getId()));

                if (discipline.getUnits() != null) {
                    for (Unit unit : discipline.getUnits()) {
                        unit.setUnitContents(daoUContent.getByFK("unitId", unit.getId()));

                        if (unit.getUnitContents() != null) {
                            for (UnitContent unitContent : unit.getUnitContents()) {
                                //unitContent.setExercises(daoExercise.getByFK("unitContentId", unitContent.getId()));
                                unitContent.setExercises(daoExercise.find("from Exercise e where e.unitContentId = ? and e.active = ? order by e.order", new Object[]{unitContent.getId(), true}));
                                //unitContent.setExams(daoExam.getByFK("unitContentId", unitContent.getId()));
                                unitContent.setExams(daoExam.find("from Exam e where e.unitContentId = ? and e.active = ? order by e.order", new Object[]{unitContent.getId(), true}));
                            }
                        }
                    }
                }
            }
        }

        return course;
    }

    public Integer getStudentsCount(Long courseId) {
        Object[] params = new Object[]{courseId};
        List result = daoSu.find("select count(e) from SystemUser su, Enrollment e, Grade g where g.courseId = ? and g.id = e.grade.id and e.systemUser.id = su.id and e.systemUser.enabled = true", params);
        if (result == null || result.get(0) == null) {
            return 0;
        }
        return ((Long) result.get(0)).intValue();
    }

    public List<SystemUser> getCoordinators(Long courseId) {
        return daoGrade.find("select g.coordinator from Grade g where g.courseId = ? ", new Object[]{courseId});
    }
    
    public List<SystemUser> getProfessors(Long courseId) {
        return daoGrade.find("select g.professors from Grade g where g.courseId = ? ", new Object[]{courseId});
    }

    public Integer getGradesCount(Long courseId) {
        Object[] params = new Object[]{courseId};
        List results = daoGrade.find("select count(g) from Grade g where g.courseId = ?", params);
        if (results == null || results.get(0) == null) {
            return 0;
        }
        return ((Long) results.get(0)).intValue();
    }

    public Integer getGraduatedStudentsCount(Long courseId) {
        Object[] params = new Object[]{courseId};
        List results = daoSu.find("select count(e) from SystemUser su, Enrollment e, Grade g where g.courseId = ? and g.id = e.grade.id and e.systemUser.id = su.id and e.systemUser.enabled = true", params);
        if (results == null || results.get(0) == null) {
            results = new ArrayList<SystemUser>();
        }
        return ((Long) results.get(0)).intValue();
    }

    public List<SystemUser> getTutors(Long courseId) {
        return daoGrade.find("select g.tutors from Grade g where g.courseId = ? ", new Object[]{courseId});
    }

    public List<Discipline> getDisciplines(Long courseId) {
        Object[] params = new Object[]{courseId};
        List<Discipline> results = daoDisc.find("from Discipline d where d.courseId = ?", params);
        if (results == null) {
            results = new ArrayList<Discipline>();
        }
        return results;
    }

    public boolean remove(Course course) {
        return daoCourse.remove(course);
    }

    public Integer getProgress(Long systemUserId, Long courseId) {
        List allUnitContents = daoCourse.find("select uc from UnitContent uc, Unit u, " +
                "Discipline d where uc.unitId = u.id and u.disciplineId = d.id " +
                "and d.courseId = ?", new Object[]{courseId});
        
        List allExercises = daoCourse.find("" +
                "select e " +
                "from Exercise e, UnitContent uc, Unit u, Discipline d " +
                "where e.unitContentId = uc.id " +
                "and uc.unitId = u.id " +
                "and u.disciplineId = d.id " +
                "and d.courseId = ?", new Object[]{courseId});

        List allExams = daoCourse.find("" +
                "select e " +
                "from Exam e, UnitContent uc, Unit u, Discipline d " +
                "where e.unitContentId = uc.id " +
                "and uc.unitId = u.id " +
                "and u.disciplineId = d.id " +
                "and d.courseId = ?", new Object[]{courseId});

        
        List finishedUnitContents = daoCourse.find("select f.unitContent from FinishedUnitContent f " +
                "where f.course = ? and f.systemUser = ?", new Object[]{courseId, systemUserId});

        List finishedExercises = daoCourse.find("select f.exercise from FinishedExercise f, UnitContent uc, Unit u, Discipline d " +
                "where f.unitContent = uc.id " +
                "and uc.unitId = u.id " +
                "and u.disciplineId = d.id " +
                "and d.courseId = ? and f.systemUser = ?", new Object[]{courseId, systemUserId});

        List finishedExams = daoCourse.find("select f.exam from FinishedExam f, UnitContent uc, Unit u, Discipline d " +
                "where f.unitContent = uc.id " +
                "and uc.unitId = u.id " +
                "and u.disciplineId = d.id " +
                "and d.courseId = ? and f.systemUser = ?", new Object[]{courseId, systemUserId});

        int cUnitContents = (allUnitContents != null) ? allUnitContents.size() : 0;
        int cExercises = (allExercises != null) ? allExercises.size() : 0;
        int cExams = (allExams != null) ? allExams.size() : 0;
        
        int cFinishedUnitContents = (finishedUnitContents != null) ? finishedUnitContents.size() : 0;
        int cFinishedExercises = (finishedExercises != null) ? finishedExercises.size() : 0;
        int cFinishedExams = (finishedExams != null) ? finishedExams.size() : 0;

        int all =  cUnitContents + cExercises + cExams;
        int done = cFinishedUnitContents + cFinishedExercises + cFinishedExams;
        
        double rate = (double) ((double) done / (double) all) * 100;
        
        return (int) rate;
    }
    
    public void savePhoto(Course p, File file) {
        InputStream data = null;
        try {
            java.io.File f = new java.io.File(p.getImage());
            data = new FileInputStream(file);
            OutputStream out = new FileOutputStream(f);
            byte[] buf = new byte[1024];
            int len;
            while ((len = data.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            data.close();
            Thumbnail2.processSquareThumbnail(f.getPath(), f.getPath(), 80, "80");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public int isFinishedCourse(Long studentId, Long courseId, long gradeId){
        int res = 1;
        String sql = "select id from Discipline d where d.courseId=?";
        Object[] params = new Object[]{courseId};
        
        List<Long> disciplines = daoDisc.find(sql, params);
        DisciplineBean disciplineBean = new DisciplineBean();
        int total = disciplines.size();
        int finished = 0;
        for(Long disciplineId : disciplines){
            if(disciplineBean.isDisciplineFinished(studentId, disciplineId, gradeId))
               finished++; 
        }
        
        double resd = finished/(double)total;
        res = (int)(resd*10);
        return res;
    }

    public List<Course> getCourses(String name, String description) {
        if (name == null || name.trim().length() == 0)
            name = "%";
        else
            name = "%" + name.toUpperCase() + "%";
        if (description == null || description.trim().length() == 0)
            description = "%";
        else
            description = "%" + description.toUpperCase() + "%";        
        Object[] params = new Object[]{ name, description };
        List<Course> results = daoCourse.find("from Course c where upper(c.name) like ? and upper(c.description) like ? and c.active = true", params);
        if (results == null)
            results = new ArrayList<Course>();
        return results;
    }
}
