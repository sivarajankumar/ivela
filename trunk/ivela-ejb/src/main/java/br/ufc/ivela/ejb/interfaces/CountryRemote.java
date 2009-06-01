/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.Country;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rodrigo
 */
@Remote
public interface CountryRemote {
    /**
     * retrieves a list of country
     * @return List<Country>
     */
    public List<Country> getAll();
    
    /**
     * adds a country
     * @param country
     * @return id of the inserted record
     */
    public Long add(Country country);
    
    /**
     * retrieves a country by id
     * @param Id
     * @return Country
     */
    public Country get(Long Id);
    
    /**
     * removes a country by id
     * @param Id
     * @return true, if removed
     *         false, otherwise
     */
    public boolean remove(Long Id);
    
    /**
     * updates a country
     * @param country
     * @return true, if updated
     *         false, otherwise
     */
    public boolean update(Country country);
    
}
