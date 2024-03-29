/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.ejb.interfaces.DisciplineRemote;
import br.ufc.ivela.ejb.*;
import br.ufc.ivela.commons.model.Discipline;
import br.ufc.ivela.commons.model.Unit;
import br.ufc.ivela.ejb.interfaces.UnitRemote;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Class of ejb which implements the interface DisciplineLocal
 */
@Stateless(mappedName="DisciplineBean")
public class DisciplineBean implements DisciplineRemote {

    private GenericDao<Discipline> daoDiscipline = DaoFactory.getInstance(Discipline.class);
    private GenericDao<Unit> daoUnit = DaoFactory.getInstance(Unit.class);

    @EJB
    private UnitRemote unitRemote;
    
    public Discipline get(Long id) {
        return daoDiscipline.get(id);
    }

    public Long add(Discipline discipline) {
        return (Long) daoDiscipline.save(discipline);
    }

    public boolean remove(Long id) {

        return daoDiscipline.remove(daoDiscipline.get(id));
    }

    public List<Discipline> getAll() {
        return daoDiscipline.getAll();
    }

    public List<Discipline> getByCourse(Long courseId) {
        return daoDiscipline.getByFK("courseId", courseId);
    }

    public boolean update(Discipline discipline) {
        return daoDiscipline.update(discipline);
    }

    
    public Discipline getByCourseAndTag(Long courseId,String tag){    	
    	List<Discipline> disciplineList = daoDiscipline.find("from Discipline d where d.courseId = ? and d.tag = ?", new Object[] {courseId,tag});
    	return (!disciplineList.isEmpty()? disciplineList.get(0):null);
    }
    
    public String getByCourseTest(Long courseId) {
        List<Discipline> disciplineList = new ArrayList<Discipline>();

        disciplineList = daoDiscipline.getByFK("courseId", courseId);
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        //xStream.setMode(XStream.NO_REFERENCES);
        xStream.alias("disciplines", List.class);
        xStream.alias("discipline", Discipline.class);
        xStream.omitField(Discipline.class, "course");
        xStream.omitField(Discipline.class, "exerciseDisciplineCollection");
        xStream.omitField(Discipline.class, "examDisciplineCollection");
        xStream.omitField(Discipline.class, "homeworkDisciplineCollection");
        xStream.omitField(Discipline.class, "unitCollection");
        xStream.omitField(Unit.class, "discipline");
        return xStream.toXML(disciplineList);
    }

     public String getByCourseOrderByName(Long courseId) {
        List<Discipline> disciplineList = new ArrayList<Discipline>();

        disciplineList = daoDiscipline.find("from Discipline d where d.courseId = ? order by d.name", new Object[] {courseId});
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        //xStream.setMode(XStream.NO_REFERENCES);
        xStream.alias("disciplines", List.class);
        xStream.alias("discipline", Discipline.class);
        xStream.omitField(Discipline.class, "course");
        xStream.omitField(Discipline.class, "exerciseDisciplineCollection");
        xStream.omitField(Discipline.class, "examDisciplineCollection");
        xStream.omitField(Discipline.class, "homeworkDisciplineCollection");
        xStream.omitField(Discipline.class, "unitCollection");
        xStream.omitField(Unit.class, "discipline");
        return xStream.toXML(disciplineList);
    }
    
    public boolean remove(Discipline discipline) {
        return daoDiscipline.remove(discipline);
    }
    
    public int isDisciplineFinished(Long studentId, String disciplineTag, long courseId, long gradeId){
        int result = 0;
        String sqlCourse = "select d.id from Discipline d where d.tag=? and d.courseId=?";
        String sql = "select u.id from Unit u where u.disciplineId=?";        
        Object[] paramsCourse = new Object[]{disciplineTag, courseId};
        List<Long> discIds = daoDiscipline.find(sqlCourse, paramsCourse);
        
        if (discIds.isEmpty()) {
            return -1;
        } 
                
        Object[] params = new Object[]{discIds.get(0)}; 
        
        List<Long> unitsId = daoUnit.find(sql, params);        
        for(Long unitId: unitsId){
            result += unitRemote.isUnitFinished(studentId, unitId, courseId, gradeId);               
        }
        
        if (result < 0) {
            result = -result;
            result = result == unitsId.size()? -1 : result;
        }        
        
        return result;
    }
    
    public int isDisciplineFinished(Long studentId, Long disciplineId, long courseId, long gradeId){
        int result = 0;
        String sql = "select u.id from Unit u where u.disciplineId=?";
        Object[] params = new Object[]{disciplineId}; 
        
        List<Long> unitsId = daoUnit.find(sql, params);
        UnitRemote unitRemote = new UnitBean();
        for(Long unitId: unitsId){
            result += unitRemote.isUnitFinished(studentId, unitId, courseId, gradeId);               
        }
        
        result = result < 0? -1 : result;
        
        return result;
    }
    
}
