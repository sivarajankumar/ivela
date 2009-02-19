/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.ejb.interfaces.SystemUserRemote;
import br.ufc.ivela.ejb.*;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.commons.model.Profile;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author Rodrigo Félix de Almeida / Leonardo Oliveira Moreira
 */
@Stateless(mappedName="SystemUserBean")
public class SystemUserBean implements SystemUserRemote {

    private GenericDao<SystemUser> daoSystemUser = DaoFactory.getInstance(SystemUser.class);
    private GenericDao<Profile> daoProfile = DaoFactory.getInstance(Profile.class);
    
    public boolean exists(String login){        
        List result = daoSystemUser.find("from SystemUser s where s.username = ?", new Object[]{login});
        
        if(result != null && result.size() > 0){
            return true;
        }
        
        return false;
    }
    
    public SystemUser get(Long systemUserId) {
        return daoSystemUser.get(systemUserId); 
    }
    
    public Profile getProfile(Long systemUserId){
        return (Profile)daoProfile.getByFK("system_user.id", systemUserId);
    }

    public List<SystemUser> list() {
        return daoSystemUser.getAll();
    }

    public Long add(SystemUser su) {
        su.setEnabled(true);
        su.setCreatedAt(new Date());
        return (Long) daoSystemUser.save(su);
    }

    public boolean remove(Long systemUserId) {
        return daoSystemUser.remove(systemUserId);
    }

    public String encrypt(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return new String(hexCodes(md.digest(password.getBytes())));
        } 
        catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SystemUserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Method used to transform a string of bytes in the format in an array of hexadecimal characters
     * 
     * @param text
     * @return
     */
    private static char[] hexCodes(byte[] text) {
        char[] hexOutput = new char[text.length * 2];
        String hexString;
        for (int i = 0; i < text.length; i++) {
            hexString = "00" + Integer.toHexString(text[i]);
            hexString.getChars(hexString.length() - 2, hexString.length(), hexOutput, i * 2);
        }
        return hexOutput;
    }

    /**
     * updates a system user
     * @param user
     * @return true, if everything worked
     *         false, otherwise
     */ 
    public boolean update(SystemUser user) {
        return daoSystemUser.update(user);
    }

    public List<SystemUser> getAll() {
        return daoSystemUser.getAll();
    }

    public List<SystemUser> getByAuthentication(Long authenticationId) {        
        return daoSystemUser.find("select s from SystemUser s where s.authentication.id  = ? and s.enabled = true", new Object[]{authenticationId});
    }

    public List<SystemUser> getByUsername(String username) {
        return (List<SystemUser>) daoSystemUser.find("select su from SystemUser su where su.username = ? and su.enabled = true", new Object[] { username });
    }
    
    public List<SystemUser> getUsersByUsername(String username) {
        return (List<SystemUser>) daoSystemUser.find("select su from SystemUser su where su.username LIKE ? and su.enabled = true", new Object[] { username + "%" });
    }
    
}