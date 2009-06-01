/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.LocationType;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rodrigo
 */
@Remote
public interface LocationTypeRemote {
    /**
     * retrieves a list of locationType
     * @return List<LocationType>
     */
    public List<LocationType> getAll();
    
    /**
     * adds a locationType
     * @param locationType
     * @return id of the inserted record
     */
    public Long add(LocationType locationType);
    
    /**
     * retrieves a locationType by id
     * @param Id
     * @return LocationType
     */
    public LocationType get(Long Id);
    
    /**
     * removes a locationType by id
     * @param Id
     * @return true, if removed
     *         false, otherwise
     */
    public boolean remove(Long Id);
    
    /**
     * updates a locationType
     * @param locationType
     * @return true, if updated
     *         false, otherwise
     */
    public boolean update(LocationType locationType);
    
}
