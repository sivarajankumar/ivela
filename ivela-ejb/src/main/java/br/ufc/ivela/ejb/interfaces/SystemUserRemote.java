/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.Profile;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.commons.model.UnitContent;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Rodrigo Félix de Almeida / Leonardo Oliveira Moreira
 * 
 * Ejb's interface that implements the methods of business entity for the system users
 */
@Remote
public interface SystemUserRemote {

    boolean exists(String login);
    
    public List<SystemUser> getAll();
    
    public SystemUser get(Long id);
        
    /**
     * Method that retrieves the profile through id
     * @param systemUserId
     * @return profile
     */
    public Profile getProfile(Long systemUserId);
        
    /**
     * Method used to encrypt a password
     * 
     * @param password
     * @return
     */
    public String encrypt(String password);
    
    /**
     * Method used to recover all the user of the systemmethod used to recover all the user of the system
     * 
     * @return
     */
    public java.util.List<SystemUser> list();
    
    /**
     * Method used to add a user in the system
     * 
     * @param su
     * @return
     */
    public Long add(SystemUser su);
    
    /**
     * Method used to remove a user of the system
     * 
     * @param systemUserId
     * @return
     */
    public boolean remove(Long systemUserId);

    /**
     * Method used to update an user
     * 
     * @param systemUserId
     * @return
     */
    boolean update(SystemUser user);
    
    List<SystemUser> getByAuthentication(Long authenticationId);
    
    public List<SystemUser> getByUsername(String username);
    
    public List<SystemUser> getUsersByUsername(String username);
    
    public List<SystemUser> getByUsernameByAuthentication(String username,Long authenticationId);
    
}
