/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.Discipline;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Interface of ejb representing the methods of contracting business discipline
 */
@Remote
public interface DisciplineRemote {
    
    /**
     * Method which represents the recovery of a discipline by identifier
     * 
     * @param id
     * @return
     */
    public Discipline get(Long id);
    
    /**
     * Method that is the insertion of a discipline
     * 
     * @param discipline
     * @return
     */
    public Long add(Discipline discipline);
    
    /**
     * Method which represents the removal of a discipline
     * 
     * @param id
     * @return
     */
    public boolean remove(Long id);
    
    public boolean remove(Discipline discipline);
    
    /**
     * Method which represents the recovery of all disciplines
     * 
     * @return
     */
    public List<Discipline> getAll();
    
    //public boolean edit();
    
    public List<Discipline> getByCourse(Long courseId);
    
    public Discipline getByCourseAndTag(Long courseId,String tag);
    
    public String getByCourseTest(Long courseId);
    
    public boolean update(Discipline discipline);
    
    public String getByCourseOrderByName(Long courseId);

    /**
     * 
     * @param studentId
     * @param disciplineId
     * @param courseId
     * @param gradeId
     * @return 0 for finished, > 0 for not finished, -1 for not started
     */
    public int isDisciplineFinished(Long studentId, String disciplineTag, long courseId, long gradeId);
    
    public int isDisciplineFinished(Long studentId, Long disciplineId, long courseId, long gradeId);
    
    
    
    
    
    
    
}