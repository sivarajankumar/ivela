/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.HistoryParams;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Interface of ejb representing the methods of contracting business history params
 */
@Remote
public interface HistoryParamsRemote {
    
    /**
     * Method which represents the recovery of a message by identifier
     * 
     * @param id
     * @return
     */
    public HistoryParams get(Long id);
    
    /**
     * Method that is the insertion of a history
     * 
     * @param discipline
     * @return
     */
    public Long add(HistoryParams historyParams);
    
    /**
     * Method which represents the removal of a message
     * 
     * @param id
     * @return
     */
    public boolean remove(Long id);
    
    /**
     * Method which represents the recovery of all messages
     * 
     * @return
     */
    public List<HistoryParams> getAll();
    
    /**
     * Method which represents the recovery of all messages recipient by system user
     * 
     * @param systemUserId
     * @return
     */
    public List<HistoryParams> getByHistory(Long historyId);

}
