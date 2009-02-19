/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.Profile;
import java.io.File;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Emanuelle Vieira
 * 
 * Ejb's interface that implements the methods of business entity for the profile
 */
@Remote
public interface ProfileRemote {

    /**
     * Method that is the insertion of a profile
     * 
     * @param profile
     * @return
     */
    public Long add(Profile profile);

    /**
     * Method which represents the recovery of a profile by identifier
     * 
     * @param id
     * @return Profile
     */
    public Profile get(Long id);

    /**
     * Method which represents the removal of a profile
     * 
     * @param id
     * @return 
     */
    public boolean remove(Long id);

    /**
     * Method that is used to edit a profile
     * 
     * @param Profile
     * @return
     */
    public boolean edit(Profile profile);

    /**
     * Method which represents the recovery of all questions
     * 
     * @return
     */
    public List<Profile> getAll();

    /**
     * Method which represents the recovery of all questions
     * 
     * @return
     */
    public void savePhoto(Profile p, File fileIo);
    
    public Profile getBySystemUserId(Long systemUserId);
}
