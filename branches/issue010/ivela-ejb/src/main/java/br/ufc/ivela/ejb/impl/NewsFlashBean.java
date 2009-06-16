/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.NewsFlash;
import br.ufc.ivela.ejb.interfaces.NewsFlashRemote;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author marcus
 */

@Stateless(mappedName="NewsFlashBean")
public class NewsFlashBean implements NewsFlashRemote{

    private GenericDao<NewsFlash> dao = DaoFactory.getInstance(NewsFlash.class);
    
    public Long addNewsFlash(NewsFlash newsFlash) {
        return (Long) dao.save(newsFlash);
    }

    public List<NewsFlash> getUnreadNewsByUser(Long userId) {
        return dao.find("from NewsFlash n where n.read = false and n.receiverId = ?", new Object[]{userId});
    }

    public boolean markAsRead(Long newsFlashId) {
        NewsFlash newsFlash = dao.get(newsFlashId);
        newsFlash.setRead(true);
        
        return dao.update(newsFlash);
    }
}
