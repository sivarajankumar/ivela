/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.ejb.interfaces.UnitRemote;
import br.ufc.ivela.ejb.*;
import br.ufc.ivela.commons.model.Unit;
import br.ufc.ivela.commons.model.UnitContent;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Maristella Myrian
 */
@Stateless(mappedName="UnitBean")
public class UnitBean implements UnitRemote {

    private GenericDao<Unit> daoUnit = DaoFactory.getInstance(Unit.class);
    private GenericDao<UnitContent> daoUnitContent = DaoFactory.getInstance(UnitContent.class);

    /**
     * Connect to the server and add a new unit
     * @param unit
     * @return true if the new unit can be add, and false if can't
     */
    public Long add(Unit unit) {
        unit.setActive(true);
        return (Long) daoUnit.save(unit);
    }

    /**
     * Connect to the server and get the unit by id
     * @param id
     * @return unit
     */
    public Unit get(Long id) {
        if (id == null) {
            return null;
        }
        return daoUnit.get(id);
    }

    public List<Unit> getByDiscipline(Long disciplineId) {
        return daoUnit.getByFK("disciplineId", disciplineId);
    }
    
    public List<Unit> getByDisciplineOpen(Long disciplineId) {
        return daoUnit.find("from Unit u where u.disciplineId = ? and u.active = true", new Object[]{disciplineId});
    }


    /**
     * Connect to the server and remove a unit by id
     * @param id
     * @return true if the new unit can be remove, and false if cannot
     */
    public boolean remove(Long id) {
        return daoUnit.remove(id);
    }

    /**
     * try to connect to the server and get all unit and lists
     *
     * @return a list of unit
     */
    public List<Unit> getAll() {
        return daoUnit.getAll();
    }

    /**
     * try to connect to the server and update a unit
     * @param unit
     * @return
     */
    public boolean update(Unit unit) {
        return daoUnit.update(unit);

    }

    public boolean remove(Unit unit) {
        return daoUnit.remove(unit);
    }
    
    public boolean isUnitFinished(Long studentId, Long unitId, long gradeId){
        boolean res = true;
        UnitContentBean ucBean = new UnitContentBean();
        String sql = "select uc.id from UnitContent uc" +
                     " where uc.unitId=?";
        Object[] params = new Object[]{unitId};
        
        List<Long> unitContents = daoUnitContent.find(sql, params);
        
        for(Long unitContentId: unitContents){
            if(!ucBean.isUnitContentFinished(studentId, unitContentId, gradeId))
                return false;
        }
        
        return res;
    }
}
