/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.Unit;
import java.io.File;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author maristella
 */

@Remote
public interface UnitRemote {

    /**
     * Method that is the insertion of a unit
     * 
     * @param question
     * @return
     */
    public Long add(Unit unit);

    /**
     * Method which represents the recovery of a unit by identifier
     * 
     * @param id
     * @return a couse
     */
    public Unit get(Long id);

    /**
     * Method which represents the removal of a unit
     * 
     * @param id
     * @return true if can be remove a unit by id or false if can't
     */
    
    public List<Unit> getByDiscipline(Long disciplineId);
    
    public boolean remove(Long id);
    
    public boolean remove(Unit unit);

    /**
     * Method which represents the recovery of all unit
     * 
     * @return a list of unit
     */
    public List<Unit> getAll();
    
    /**
     * Method to update a unit
     * @param unit
     * @return true if can update a unit or false if can't
     */
    public boolean update(Unit unit);
    
    /**
     * Return if a unit is finished or not by a student enrolled in a grade
     * @param studentId
     * @param unitId
     * @param gradeId
     * @return
     */
    public boolean isUnitFinished(Long studentId, Long unitId, long gradeId);
    
    public List<Unit> getByDisciplineOpen(Long disciplineId) ;
}

