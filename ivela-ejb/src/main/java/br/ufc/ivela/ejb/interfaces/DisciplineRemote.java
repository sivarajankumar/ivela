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

    public boolean isDisciplineFinished(Long studentId, Long disciplineId, long gradeId);
}