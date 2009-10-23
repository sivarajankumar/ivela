/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.FinishedExam;
import br.ufc.ivela.ejb.interfaces.FinishedExamRemote;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 */
@Stateless(mappedName="FinishedExamBean")
public class FinishedExamBean implements FinishedExamRemote {

    private GenericDao<FinishedExam> daoFinishedExam = DaoFactory.getInstance(FinishedExam.class);
    
    public List<FinishedExam> getByUnitContent(Long unitContentId) {
        return daoFinishedExam.getByFK("unitContent.id", unitContentId);
    }

    public Long add(FinishedExam finishedExam) {
       return (Long) daoFinishedExam.save(finishedExam);
    }

    public boolean update(FinishedExam finishedExam) {
        return daoFinishedExam.update(finishedExam);
    }

    public FinishedExam get(Long finishedExamId) {
        return daoFinishedExam.get(finishedExamId);
    }

    public boolean remove(Long finishedExamId) {
        return daoFinishedExam.remove(daoFinishedExam.get(finishedExamId));
    }

    public List<FinishedExam> getByExamAndSystemUser(Long examId, Long systemUserId) {
        Object[] params = new Object[] { examId, systemUserId };
        List<FinishedExam> list = (List<FinishedExam>) daoFinishedExam.find("from FinishedExam c where c.exam = ? and c.systemUser = ?", params);
        if (list == null)
            list = new ArrayList<FinishedExam>();
        return list;

    }

}
