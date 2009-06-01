/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.Dictionary;
import javax.ejb.Remote;

/**
 *
 * @author nelson
 */
@Remote
public interface DictionaryRemote {
    
    public Dictionary get(String title);
    
    public Dictionary getById(Long id);
    
    public Long add(Dictionary dictionary);
    
    public boolean update(Dictionary dictionary);
    
     public boolean delete(Dictionary dictionary);
}
