/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.LanguageInternationalization;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rodrigo
 */
@Remote
public interface LanguageInternationalizationRemote {
    /**
     * retrieves a list of languageInternationalization
     * @return List<LanguageInternationalization>
     */
    public List<LanguageInternationalization> getAll();
    
    /**
     * adds a languageInternationalization
     * @param languageInternationalization
     * @return id of the inserted record
     */
    public Long add(LanguageInternationalization languageInternationalization);
    
    /**
     * retrieves a languageInternationalization by id
     * @param Id
     * @return LanguageInternationalization
     */
    public LanguageInternationalization get(Long Id);
    
    /**
     * removes a languageInternationalization by id
     * @param Id
     * @return true, if removed
     *         false, otherwise
     */
    public boolean remove(Long Id);
    
    /**
     * updates a languageInternationalization
     * @param languageInternationalization
     * @return true, if updated
     *         false, otherwise
     */
    public boolean update(LanguageInternationalization languageInternationalization);
    
}
