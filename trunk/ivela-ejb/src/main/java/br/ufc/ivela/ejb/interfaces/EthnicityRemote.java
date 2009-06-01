/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.Ethnicity;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rodrigo
 */
@Remote
public interface EthnicityRemote {
    /**
     * retrieves a list of ethnicity
     * @return List<Ethnicity>
     */
    public List<Ethnicity> getAll();
    
    /**
     * adds an ethnicity
     * @param ethnicity
     * @return id of the inserted record
     */
    public Long add(Ethnicity ethnicity);
    
    /**
     * retrieves an ethnicity by id
     * @param Id
     * @return Ethnicity
     */
    public Ethnicity get(Long Id);
    
    /**
     * removes an ethnicity by id
     * @param Id
     * @return true, if removed
     *         false, otherwise
     */
    public boolean remove(Long Id);
    
    /**
     * updates an ethnicity
     * @param ethnicity
     * @return true, if updated
     *         false, otherwise
     */
    public boolean update(Ethnicity ethnicity);
    
}
