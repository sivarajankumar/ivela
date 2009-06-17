/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.State;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rodrigo
 */
@Remote
public interface StateRemote {
    /**
     * retrieves a list of state
     * @return List<State>
     */
    public List<State> getAll();
    
    /**
     * adds a state
     * @param state
     * @return id of the inserted record
     */
    public Long add(State state);
    
    /**
     * retrieves a state by id
     * @param Id
     * @return State
     */
    public State get(Long Id);
    
    /**
     * removes a state by id
     * @param Id
     * @return true, if removed
     *         false, otherwise
     */
    public boolean remove(Long Id);
    
    /**
     * updates a state
     * @param state
     * @return true, if updated
     *         false, otherwise
     */
    public boolean update(State state);
    
    public Long lastId();
    
    public List<State> getByCountry(Long countryId);
}
