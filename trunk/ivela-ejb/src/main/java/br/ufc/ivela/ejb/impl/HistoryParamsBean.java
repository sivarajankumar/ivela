/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.HistoryParams;
import br.ufc.ivela.ejb.*;
import br.ufc.ivela.ejb.interfaces.HistoryParamsRemote;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Class of ejb which implements the interface HistoryParamsLocal
 */
@Stateless(mappedName="HistoryParamsBean")
public class HistoryParamsBean implements HistoryParamsRemote {
    
    private GenericDao<HistoryParams> daoHistoryParams = DaoFactory.getInstance(HistoryParams.class);

    public boolean remove(Long id) {
        return daoHistoryParams.remove(daoHistoryParams.get(id));
    }

    public HistoryParams get(Long id) {
        return daoHistoryParams.get(id);
    }

    public Long add(HistoryParams historyParams) {
        return (Long) daoHistoryParams.save(historyParams);
    }

    public List<HistoryParams> getAll() {
        return daoHistoryParams.getAll();
    }

    public List<HistoryParams> getByHistory(Long historyId) {
        return daoHistoryParams.getByFK("historyId", historyId);
    }
    
}
