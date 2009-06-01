/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.Address;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rodrigo
 */
@Remote
public interface AddressRemote {
    /**
     * retrieves a list of address
     * @return List<Address>
     */
    public List<Address> getAll();
    
    /**
     * adds an address
     * @param address
     * @return id of the inserted record
     */
    public Long add(Address address);
    
    /**
     * retrieves an address by id
     * @param Id
     * @return Address
     */
    public Address get(Long Id);
    
    /**
     * removes an address by id
     * @param Id
     * @return true, if removed
     *         false, otherwise
     */
    public boolean remove(Long Id);
    
    /**
     * updates an address
     * @param address
     * @return true, if updated
     *         false, otherwise
     */
    public boolean update(Address address);
    
    public Long lastId();
    
     public Address getByProfile(Long idProfile);
    
}
