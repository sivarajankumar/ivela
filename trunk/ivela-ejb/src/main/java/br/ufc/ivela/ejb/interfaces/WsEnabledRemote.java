/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.dao.Page;
import br.ufc.ivela.commons.model.Forum;
import br.ufc.ivela.commons.model.WsEnabled;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Interface of ejb representing the methods of contracting business ws
 */
@Remote
public interface WsEnabledRemote {
    
    /**
     * Method which represents the recovery of a ws by identifier
     * 
     * @param id
     * @return
     */
    public WsEnabled get(Long id);
    
    /**
     * Method that is the insertion of a ws
     * 
     * @param forum
     * @return
     */
    public Long add(WsEnabled wsEnabled);
    
    /**
     * Method which represents the removal of a ws
     * 
     * @param id
     * @return
     */
    public boolean remove(Long id);
    
    /**
     * Method which represents the recovery of all ws
     * 
     * @return
     */
    public List<WsEnabled> getAll();
      
    public boolean ipEnabled(String ip);
    
}
