/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.interfaces;

import br.ufc.ivela.commons.model.FinishedUnitContent;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Leonardo Oliveira Moreira
 */
@Remote
public interface FinishedUnitContentRemote {

    public List<FinishedUnitContent> getByUnitContent(Long unitContentId);
    
    public List<FinishedUnitContent> getByUnitContentAndSystemUser(Long unitContentId, Long systemUserId);

    public Long add(FinishedUnitContent finishedUnitContent);
    
    public boolean update(FinishedUnitContent finishedUnitContent);
    
    public FinishedUnitContent get(Long finishedUnitContentId);
    
    public boolean remove(Long finishedUnitContentId);
    
}