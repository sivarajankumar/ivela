/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.Honorific;
import br.ufc.ivela.ejb.interfaces.HonorificRemote;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author rodrigo
 */
@Stateless(mappedName="HonorificBean")
public class HonorificBean implements HonorificRemote {

    private GenericDao<Honorific> daoHonorific = DaoFactory.getInstance(Honorific.class);

    public List<Honorific> getAll() {
        return (List<Honorific>)daoHonorific.getAll();
    }

    public Long add(Honorific honorific) {
        return (Long) daoHonorific.save(honorific);
    }

    public Honorific get(Long Id) {
        return daoHonorific.get(Id);
    }

    public boolean remove(Long Id) {
        return daoHonorific.remove(Id);
    }

    public boolean update(Honorific honorific) {
        return daoHonorific.update(honorific);
    }
}
