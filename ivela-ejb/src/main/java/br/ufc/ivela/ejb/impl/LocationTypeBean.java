/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.LocationType;
import br.ufc.ivela.ejb.interfaces.LocationTypeRemote;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author rodrigo
 */
@Stateless(mappedName="LocationTypeBean")
public class LocationTypeBean implements LocationTypeRemote {

    private GenericDao<LocationType> daoLocationType = DaoFactory.getInstance(LocationType.class);

    public List<LocationType> getAll() {
        return daoLocationType.getAll();
    }

    public Long add(LocationType locationType) {
        return (Long) daoLocationType.save(locationType);
    }

    public LocationType get(Long Id) {
        return daoLocationType.get(Id);
    }

    public boolean remove(Long Id) {
        return daoLocationType.remove(Id);
    }

    public boolean update(LocationType locationType) {
        return daoLocationType.update(locationType);
    }
}
