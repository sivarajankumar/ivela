/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.Phone;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rodrigo
 */
@Remote
public interface PhoneRemote {
    /**
     * retrieves a list of phone
     * @return List<Phone>
     */
    public List<Phone> getAll();
    
    /**
     * adds a phone
     * @param phone
     * @return id of the inserted record
     */
    public Long add(Phone phone);
    
    /**
     * retrieves a phone by id
     * @param Id
     * @return Phone
     */
    public Phone get(Long Id);
    
    /**
     * removes a phone by id
     * @param Id
     * @return true, if removed
     *         false, otherwise
     */
    public boolean remove(Long Id);
    
    /**
     * updates a phone
     * @param phone
     * @return true, if updated
     *         false, otherwise
     */
    public boolean update(Phone phone);
    public Long lastId();
    public List<Phone> getByProfile(Long idProfile);
}
