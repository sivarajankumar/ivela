/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.Dictionary;
import br.ufc.ivela.ejb.interfaces.*;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author nelson
 */
@Stateless(mappedName="DictionaryBean")
public class DictionaryBean implements DictionaryRemote {
    
    private GenericDao<Dictionary> daoDictionary = DaoFactory.getInstance(Dictionary.class);

    public Dictionary get(String title) {
        Dictionary dictionary = new Dictionary();
        dictionary.setTitle(title);
        
        List<Dictionary> list = daoDictionary.getByExample(dictionary);
        
        if(list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }
    
    public Dictionary getById(Long id) {
        return daoDictionary.get(id);
    }

    public Long add(Dictionary dictionary) {
        dictionary.setCreatedAt(new Date());
        
        return (Long) daoDictionary.save(dictionary);
    }

    public boolean update(Dictionary dictionary) {
        return daoDictionary.update(dictionary);
    }
    
    public boolean delete(Dictionary dictionary){
        return daoDictionary.remove(dictionary);
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "EJB Methods > Add Business Method" or "Web Service > Add Operation")
 
}
