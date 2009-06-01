/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.FinishedExercise;
import br.ufc.ivela.ejb.interfaces.FinishedExerciseRemote;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 */
@Stateless(mappedName="FinishedExerciseBean")
public class FinishedExerciseBean implements FinishedExerciseRemote {

    private GenericDao<FinishedExercise> daoFinishedExercise = DaoFactory.getInstance(FinishedExercise.class);

    public List<FinishedExercise> getByUnitContent(Long unitContentId) {
        return daoFinishedExercise.getByFK("unitContent.id", unitContentId);
    }

    public Long add(FinishedExercise finishedExercise) {
        return (Long) daoFinishedExercise.save(finishedExercise);
    }

    public boolean update(FinishedExercise finishedExercise) {
        return daoFinishedExercise.update(finishedExercise);
    }

    public FinishedExercise get(Long finishedExerciseId) {
        return daoFinishedExercise.get(finishedExerciseId);
    }

    public boolean remove(Long finishedExerciseId) {
        return daoFinishedExercise.remove(daoFinishedExercise.get(finishedExerciseId));
    }

    public List<FinishedExercise> getByExerciseAndSystemUser(Long exerciseId, Long systemUserId) {
        Object[] params = new Object[] { exerciseId, systemUserId };
        List<FinishedExercise> list = (List<FinishedExercise>) daoFinishedExercise.find("from FinishedExercise c where c.exercise = ? and c.systemUser = ?", params);
        if (list == null)
            list = new ArrayList<FinishedExercise>();
        return list;
    }

}
