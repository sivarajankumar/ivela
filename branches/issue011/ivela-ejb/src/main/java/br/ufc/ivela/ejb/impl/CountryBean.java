/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.Country;
import br.ufc.ivela.ejb.interfaces.CountryRemote;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author rodrigo
 */
@Stateless(mappedName="CountryBean")
public class CountryBean implements CountryRemote {

    private GenericDao<Country> daoCountry = DaoFactory.getInstance(Country.class);
    private StateBean stateBean;
    
    
    public List<Country> getAll() {
        List<Country> l = daoCountry.getAll();
        stateBean = new StateBean();
        for (Country country : l) {
            country.setStates(stateBean.getByCountry(country.getId()));
        }
        return l;
    }

    public Long add(Country country) {
        return (Long) daoCountry.save(country);
    }

    public Country get(Long Id) {
        return daoCountry.get(Id);
    }

    public boolean remove(Long Id) {
        return daoCountry.remove(Id);
    }

    public boolean update(Country country) {
        return daoCountry.update(country);
    }
}
