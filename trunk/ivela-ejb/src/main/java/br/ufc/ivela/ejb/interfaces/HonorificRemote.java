/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.Honorific;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rodrigo
 */
@Remote
public interface HonorificRemote {
    /**
     * retrieves a list of honorific
     * @return List<Honorific>
     */
    public List<Honorific> getAll();
    
    /**
     * adds an honorific
     * @param honorific
     * @return id of the inserted record
     */
    public Long add(Honorific honorific);
    
    /**
     * retrieves an honorific by id
     * @param Id
     * @return Honorific
     */
    public Honorific get(Long Id);
    
    /**
     * removes an honorific by id
     * @param Id
     * @return true, if removed
     *         false, otherwise
     */
    public boolean remove(Long Id);
    
    /**
     * updates an honorific
     * @param honorific
     * @return true, if updated
     *         false, otherwise
     */
    public boolean update(Honorific honorific);
    
}
