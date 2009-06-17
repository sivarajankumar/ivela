/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.dao.Page;
import br.ufc.ivela.commons.model.Message;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Interface of ejb representing the methods of contracting business message
 */
@Remote
public interface MessageRemote {
    
    /**
     * Method which represents the recovery of a message by identifier
     * 
     * @param id
     * @return
     */
    public Message get(Long id);
    
    /**
     * Method that is the insertion of a message
     * 
     * @param discipline
     * @return
     */
    public Long add(Message message);
    
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
    public List<Message> getAll();
    
    /**
     * Method which represents the recovery of all messages recipient by system user
     * 
     * @param systemUserId
     * @return
     */
    public List<Message> getBySystemUserRecipient(Long systemUserId);
    
    public List<Message> getLastMessagesBySystemUserRecipient(Long systemUserId);

    /**
     * Method which represents the recovery of all messages sender by system user
     * 
     * @param systemUserId
     * @return
     */
    public List<Message> getBySystemUserSender(Long systemUserId);
    
    /**
     * Method which represents the update of the messsage
     * 
     * @param message
     * @return
     */
    public boolean update(Message message);
    
    public Page getBySystemUserPageSender(Long systemUser, String title, int page, int pageSize);
    public Page getBySystemUserPageRecipient(Long systemUser, String title, int page, int pageSize);

    public List<Message> getBySystemUserSender(Long systemUser, String title, int page, int pageSize);
    public List<Message> getBySystemUserRecipient(Long systemUser, String title, int page, int pageSize);

    public int getBySystemUserSenderSize(Long systemUser, String title, int page, int pageSize);
    public int getBySystemUserRecipientSize(Long systemUser, String title, int page, int pageSize);

}
