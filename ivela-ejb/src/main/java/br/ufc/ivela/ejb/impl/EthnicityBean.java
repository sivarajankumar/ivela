/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.Ethnicity;
import br.ufc.ivela.ejb.interfaces.EthnicityRemote;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author rodrigo
 */
@Stateless(mappedName="EthnicityBean")
public class EthnicityBean implements EthnicityRemote {

    private GenericDao<Ethnicity> daoEthnicity = DaoFactory.getInstance(Ethnicity.class);

    public List<Ethnicity> getAll() {
        return daoEthnicity.getAll();
    }

    public Long add(Ethnicity ethnicity) {
        return (Long) daoEthnicity.save(ethnicity);
    }

    public Ethnicity get(Long Id) {
        return daoEthnicity.get(Id);
    }

    public boolean remove(Long Id) {
        return daoEthnicity.remove(Id);
    }

    public boolean update(Ethnicity ethnicity) {
        return daoEthnicity.update(ethnicity);
    }
}
