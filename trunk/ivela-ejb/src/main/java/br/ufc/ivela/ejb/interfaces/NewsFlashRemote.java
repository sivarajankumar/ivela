/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.NewsFlash;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author marcus
 */

@Remote
public interface NewsFlashRemote {

    Long addNewsFlash(NewsFlash newsFlash);
    
    List<NewsFlash> getUnreadNewsByUser(Long userId);
    
    boolean markAsRead(Long newsFlashId);
}
