/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.Profile;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.commons.util.Thumbnail;
import br.ufc.ivela.ejb.interfaces.ProfileRemote;
import br.ufc.ivela.ejb.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Emanuelle
 * 
 * Class of ejb which implements the interface ProfileLocal
 */
@Stateless(mappedName="ProfileBean")
public class ProfileBean implements ProfileRemote {

    private GenericDao<Profile> daoProfile = DaoFactory.getInstance(Profile.class);
    private GenericDao<SystemUser> daoSystemUser = DaoFactory.getInstance(SystemUser.class);

    public Long add(Profile profile) {
        return (Long) daoProfile.save(profile);
    }

    public Profile get(Long id) {
        return daoProfile.get(id);
    }

    public boolean remove(Long id) {
        return daoProfile.remove(id);
    }

    public boolean edit(Profile profile) { 
        return daoProfile.update(profile);
    }

    public List<Profile> getAll() {
        return daoProfile.getAll();
    }

    public void savePhoto(Profile p, File file) {
        InputStream data = null;
        try {
            java.io.File f = new java.io.File(p.getPhoto());
            data = new FileInputStream(file);
            OutputStream out = new FileOutputStream(f);
            byte[] buf = new byte[1024];
            int len;
            while ((len = data.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            data.close();
            //Thumbnail.createThumbnail(p.getPhoto(), p.getPhoto(), 100);            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Profile getBySystemUserId(Long systemUserId) {
        SystemUser systemUser = daoSystemUser.get(systemUserId);
        try {
            return daoProfile.get(systemUser.getProfileId());    
        }
        catch(Exception e) {
            return null;
        }
        
    }
}