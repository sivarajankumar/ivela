/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.Language;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rodrigo
 */
@Remote
public interface LanguageRemote {
    /**
     * retrieves a list of language
     * @return List<Language>
     */
    public List<Language> getAll();
    
    /**
     * adds a language
     * @param language
     * @return id of the inserted record
     */
    public Long add(Language language);
    
    /**
     * retrieves a language by id
     * @param Id
     * @return Language
     */
    public Language get(Long Id);
    
    /**
     * removes a language by id
     * @param Id
     * @return true, if removed
     *         false, otherwise
     */
    public boolean remove(Long Id);
    
    /**
     * updates a language
     * @param language
     * @return true, if updated
     *         false, otherwise
     */
    public boolean update(Language language);
    
}
